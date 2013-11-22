package org.ibit.rol.sac.persistence.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.FetchMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.apache.commons.lang.StringUtils;
import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.IndexEncontrado;
import org.ibit.lucene.indra.model.IndexResultados;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.webcaib.Traduccio;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;


/**
 * SessionBean para mantener y consultar Procedimientos.
 *
 *  TODO (enricjaen@dgtic) 03.03.2011
 *  Aquesta clase te mes de 1000 linias de codi i 47 procediments. 
 *  Te masses responsabilitats, que haurien de dividir-se. Per exemple: 
 *  - insertar, borrar procediment
 *  - buscar procediment
 *  - indexar procediment
 *  - ordenar procediments
 *  - actualitzar a altres administracions
 *  
 *
 * @ejb.bean
 *  name="sac/persistence/ProcedimientoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ProcedimientoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */

public abstract class ProcedimientoFacadeEJB extends HibernateEJB implements ProcedimientoDelegateI {

	private static String idioma_per_defecte = "ca";	
	
    /**
     * Obtiene refer�ncia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }


    /**
     * Autoriza la creaci�n de un procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaCrearProcedimiento(Integer validacionProcedimiento) throws SecurityException  {
    	return !(validacionProcedimiento.equals(Validacion.PUBLICA) && !userIsSuper()); 
    }
    
        
    /**
     * Autoriza la modificaci�n de un procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaModificarProcedimiento(Long idProcedimiento) throws SecurityException {
        return (getAccesoManager().tieneAccesoProcedimiento(idProcedimiento)); 
    }      
    
    
    /**
     * Crea o actualiza un Procedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) {
        Session session = getSession();
        try {
        	Date FechaActualizacionBD = new Date();
            if (procedimiento.getId() == null) {
                if (procedimiento.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
                    throw new SecurityException("No puede crear un procedimiento p�blico");
                }
            } else {
                if (!getAccesoManager().tieneAccesoProcedimiento(procedimiento.getId())) {
                    throw new SecurityException("No tiene acceso al procedimiento");
                }
                ProcedimientoLocal procedimientoBD = obtenerProcedimiento(procedimiento.getId());
                FechaActualizacionBD = procedimientoBD.getFechaActualizacion();
                this.indexBorraProcedimiento(procedimientoBD);
            }

            if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }

            /* Se alimenta la fecha de actualizaci�n de forma autom�tica si no se ha introducido dato*/                      
            if (procedimiento.getFechaActualizacion() == null || DateUtils.fechasIguales(FechaActualizacionBD,procedimiento.getFechaActualizacion())) {
            	procedimiento.setFechaActualizacion(new Date());
            }
            
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
            procedimiento.setUnidadAdministrativa(unidad);

            if (procedimiento.getId() == null) {
                session.save(procedimiento);
                addOperacion(session, procedimiento, Auditoria.INSERTAR);
            } else {
                session.update(procedimiento);
                addOperacion(session, procedimiento, Auditoria.MODIFICAR);
            }
            
            Hibernate.initialize(procedimiento.getTramites());
            Hibernate.initialize(procedimiento.getMaterias());
            Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
            
            Actualizador.actualizar(procedimiento);
            session.flush();
            return procedimiento.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Lista todos los procedimientos.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientos() {
    	//agarcia: antes era @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}". Pero este m�todo debe ser unchecked para permitir accesos via WS 
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(ProcedimientoLocal.class);
            //criteri.setFetchMode("traducciones", FetchMode.EAGER);
            criteri.setCacheable(true);
            List procsvalidos= new ArrayList<ProcedimientoLocal>();
            List procs = criteri.list();
            for (int i = 0; i < procs.size(); i++) {
                ProcedimientoLocal pl =  (ProcedimientoLocal)procs.get(i);
                if(!pl.getTraduccionMap().isEmpty()){
                    procsvalidos.add(pl);
                    Hibernate.initialize( pl.getMaterias() );
                }
            }
            
            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(procsvalidos, new ProcedimientoLocal());
            
            return procsvalidos;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Lista todos los procedimientos.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientosPublicos() {
    	//agarcia: antes era @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
        Session session = this.getSession();
        try {
            Criteria criteri = session.createCriteria( ProcedimientoLocal.class );
            //criteri.setFetchMode("traducciones", FetchMode.EAGER);
            criteri.setCacheable(true);
            List procsvalidos= new ArrayList<ProcedimientoLocal>();
            List procs = criteri.list();
            for (int i = 0; i < procs.size(); i++) {
                ProcedimientoLocal pl =  (ProcedimientoLocal)procs.get(i);
                if(!pl.getTraduccionMap().isEmpty() && this.publico( pl ) )
                {
                    procsvalidos.add(pl);
                	Hibernate.initialize( pl.getMaterias() );//agarcia
                }
            }

            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(procsvalidos, new ProcedimientoLocal());

            return procsvalidos;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            this.close(session);
        }
    }
    
    
    /**
     * Obtiene un procedimiento Local.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimientoLocal obtenerProcedimiento(Long id)
    {
    	Session session = getSession();
    	ProcedimientoLocal procedimiento = null;
    	try {
    		procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
    		if (visible(procedimiento)) {
    			Hibernate.initialize(procedimiento.getDocumentos());
    			for (Documento d : procedimiento.getDocumentos()) {
    				if (d == null) {
    					continue; //por alg�n motivo, en ocasiones los documentos en la colecci�n son nulos
    				}
    				
    				Map<String, Traduccion> mapaTraduccions = d.getTraduccionMap();
    				Set<String> idiomes = mapaTraduccions.keySet();
    				for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
    					TraduccionDocumento trad = (TraduccionDocumento)d.getTraduccion(i.next());
    					if (trad != null) {
    						Hibernate.initialize( trad.getArchivo() );
    					}
    				}
    			}
    			
    			Hibernate.initialize(procedimiento.getMaterias());
    			Hibernate.initialize(procedimiento.getPublicosObjetivo());
    			Hibernate.initialize(procedimiento.getNormativas());
    			for (Normativa n : procedimiento.getNormativas()) {
    				Map<String, Traduccion> mapaTraduccions = n.getTraduccionMap();
    				Set<String> idiomes = mapaTraduccions.keySet();
    				for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
    					TraduccionNormativa trad = (TraduccionNormativa)n.getTraduccion(i.next());
    					if (trad != null) {
    						Hibernate.initialize( trad.getArchivo() );
    					}
    				}
    			}
    			
    			Hibernate.initialize(procedimiento.getUnidadAdministrativa());
    			Hibernate.initialize(procedimiento.getUnidadAdministrativa().getHijos());
    			Hibernate.initialize(procedimiento.getOrganResolutori());
    			if (procedimiento.getOrganResolutori() != null) {
    				Hibernate.initialize(procedimiento.getOrganResolutori().getHijos());
    			}
    			
    			Hibernate.initialize(procedimiento.getUnidadAdministrativa().getNormativas());
                Hibernate.initialize(procedimiento.getUnidadAdministrativa().getEdificios());
                for (Normativa n : procedimiento.getUnidadAdministrativa().getNormativas()) {
                	Map<String, Traduccion> mapaTraduccions = n.getTraduccionMap();
                	Set<String> idiomes = mapaTraduccions.keySet();
                	for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
                		TraduccionNormativa trad = (TraduccionNormativa)n.getTraduccion(i.next());
                		if (trad != null)
                			Hibernate.initialize(trad.getArchivo());
                	}
                }
                
                Hibernate.initialize(procedimiento.getTramites());
                for (Tramite t : procedimiento.getTramites()) {
                	if (t == null) continue;
                	
                	Hibernate.initialize(t.getFormularios());
                	Hibernate.initialize(t.getDocsInformatius());
                	Hibernate.initialize(t.getTaxes());
                	Hibernate.initialize(t.getOrganCompetent());
                	for (DocumentTramit dt : t.getDocsInformatius()) {
                		Hibernate.initialize(dt.getArchivo());
                		Map<String, Traduccion> mapaTraduccions = dt.getTraduccionMap();
                		Set<String> idiomes = mapaTraduccions.keySet();
                		for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
                			TraduccionDocumento trad = (TraduccionDocumento) dt.getTraduccion(i.next());
                			if (trad != null)
                				Hibernate.initialize(trad.getArchivo());
                		}
                	}
                	
                	for (DocumentTramit df : t.getFormularios()) {
                		Hibernate.initialize(df.getArchivo());
                		Map<String, Traduccion> mapaTraduccions = df.getTraduccionMap();
                		Set<String> idiomes = mapaTraduccions.keySet();
                		for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
                			TraduccionDocumento trad = (TraduccionDocumento) df.getTraduccion(i.next());
                			if (trad != null)
                				Hibernate.initialize(trad.getArchivo());
                		}
                	}
                }
                
                Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
                Hibernate.initialize(procedimiento.getIniciacion());
                Hibernate.initialize(procedimiento.getFamilia());
                
            } else {
            	throw new SecurityException("El procedimiento no es visible");
            }
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    		
    	} finally {
    		close(session);
    	}
    	
    	//Esto no est� funcionando bien...
        //----------------------------------------------------------------------
        //ArrayList listaOrdenada = new ArrayList(procedimiento.getDocumentos());
		//Comparator comp = new DocsProcedimientoComparator();
	  	//Collections.sort(listaOrdenada, comp);
        //----------------------------------------------------------------------
    	
    	//Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
        List procs = new ArrayList(procedimiento.getDocumentos());
        Collections.sort(procs, new Documento());
	  	procedimiento.setDocumentos(procs);
	  	
	  	//Ordenamos las materias por el campo id
	  	List mats = new ArrayList(procedimiento.getMaterias());
	  	Collections.sort(mats, new Materia());
	  	procedimiento.setMaterias(new HashSet<Materia>(mats));
	  	
	  	//Ordenamos las normativas por el campo id
	  	List norms = new ArrayList(procedimiento.getNormativas());
	  	Collections.sort(norms, new Normativa());
	  	procedimiento.setNormativas(new HashSet<Normativa>(norms));
	  	
		/* TODO: error de compilaci�n tras el merge con 177
	  	//Ordenamos los normativas por el campo id
	  	List tramites = new ArrayList(procedimiento.getTramites());
	  	Collections.sort(tramites, new Tramite());
	  	procedimiento.setTramites(new HashSet<Tramite>(tramites));
		*/
	  	
	    //Ordenamos los Hechos vitales procedimientos por el campo orden (si nulo, ordena por el campo id)
	  	List hechosVitales = new ArrayList(procedimiento.getHechosVitalesProcedimientos());
	  	Collections.sort(hechosVitales);
	  	procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>(hechosVitales));
	  	
//	  	log.debug("##################################################################################################");
//	  	log.debug("ObtenerProcedimiento: " + id.intValue());
//	  	log.debug("Id Unidad Administrativa: " + procedimiento.getUnidadAdministrativa().getId().intValue());
//	  	log.debug("Tramites: " + procedimiento.getTramites().size());
//	  	log.debug("Documentos: " + procedimiento.getDocumentos().size());
//	  	log.debug("Id Familia: " + procedimiento.getFamilia().getId().intValue());
//	  	log.debug("Ventana: " + procedimiento.getVentana());
//	  	log.debug("Materias: " + procedimiento.getMaterias().size());
//	  	log.debug("Normativas: " + procedimiento.getNormativas().size());
//	  	log.debug("Id Iniciacion: " + procedimiento.getIniciacion().getId());
//	  	log.debug("Responsable: " + procedimiento.getResponsable());
//	  	log.debug("Indicador: " + procedimiento.getIndicador());
//	  	log.debug("Info: " + procedimiento.getInfo());
//	  	log.debug("Signatura: " + procedimiento.getSignatura());
//	  	log.debug("Url: " + procedimiento.getUrl());
//	  	log.debug("Hechos Vitales: " + procedimiento.getHechosVitalesProcedimientos().size());
//	  	log.debug("##################################################################################################");
	  	
	  	return procedimiento;
	}
    
    
    /**
     * Obtiene un procedimiento Local.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimientoLocal obtenerProcedimientoNewBack(Long id)
    {
    	Session session = getSession();
    	ProcedimientoLocal procedimiento = null;
    	try {
    		procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
    		if (visible(procedimiento)) {
    			Hibernate.initialize(procedimiento.getDocumentos());
    			Hibernate.initialize(procedimiento.getMaterias());
    			Hibernate.initialize(procedimiento.getPublicosObjetivo());
    			Hibernate.initialize(procedimiento.getNormativas());
    			Hibernate.initialize(procedimiento.getUnidadAdministrativa());
    			Hibernate.initialize(procedimiento.getUnidadAdministrativa().getHijos());
    			Hibernate.initialize(procedimiento.getOrganResolutori());
    			if (procedimiento.getOrganResolutori() != null) {
    				Hibernate.initialize( procedimiento.getOrganResolutori().getHijos() );
    			}
    			Hibernate.initialize(procedimiento.getTramites());
    			Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
    			Hibernate.initialize(procedimiento.getIniciacion());
    			Hibernate.initialize(procedimiento.getFamilia());
    		} else {
    			throw new SecurityException("El procedimiento no es visible");
    		}
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    		
    	} finally {
    		close(session);
    		
    	}
    	
    	// Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
        List procs = new ArrayList(procedimiento.getDocumentos());
        Collections.sort(procs, new Documento());
	  	procedimiento.setDocumentos(procs);
        
	    // Ordenamos las materias por el campo id
	  	List mats = new ArrayList(procedimiento.getMaterias());
	  	Collections.sort(mats, new Materia());
	  	procedimiento.setMaterias(new HashSet<Materia>(mats));
	  	
	  	//Ordenamos las normativas por el campo id
	  	List norms = new ArrayList(procedimiento.getNormativas());
	  	Collections.sort(norms, new Normativa());
	  	procedimiento.setNormativas(new HashSet<Normativa>(norms));
	  	
	    //Ordenamos los Hechos vitales procedimientos por el campo orden (si nulo, ordena por el campo id)
	  	List hechosVitales = new ArrayList(procedimiento.getHechosVitalesProcedimientos());
	  	Collections.sort(hechosVitales);
	  	procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>(hechosVitales));
	  	
	  	return procedimiento;
    }
    
    
    /**
     * Dice si existe un tramite de inicio distinto a tramiteId para el procedimiento Local procId.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean existeOtroTramiteInicioProcedimiento(Long procId, Long tramiteId) {
        Session session = getSession();
        try {
            String sql = "select count(t.id) from Tramite t where t.procedimiento.id = :procId and t.fase = :fase";
            if (tramiteId != null) {
                sql += " and t.id != :tramiteId"; 
            }

            Query query = session.createQuery(sql);
            query.setLong("fase", 1); // 1 = fase de inicio o inicializacion.
            query.setLong("procId", procId);
            if (tramiteId != null) {
                query.setLong("tramiteId", tramiteId);
            }

            Integer numTramites = (Integer) query.uniqueResult();
            return numTramites > 0;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

	/**
     * Busca todas los Procedimientos que cumplen los criterios de b�squeda
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarProcedimientos(Map parametros, Map traduccion) {
        Session session = getSession();
        try {
            if (!userIsOper()) {
                parametros.put("validacion", Validacion.PUBLICA);
            }

            List params = new ArrayList();
            String sQuery = populateQuery(parametros, traduccion, params);

            // Eliminado "left join fetch" por problemas en el cache de traducciones.
            Query query = session.createQuery("select procedimiento from ProcedimientoLocal as procedimiento " +
                    ", procedimiento.traducciones as trad " + sQuery);
             for (int i = 0; i < params.size(); i++) {
                String o = (String)params.get(i);
                query.setString(i, o);
            }

            List<ProcedimientoLocal> procedimientos = query.list();
            
            if (!userIsOper()) {
            	//Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
                Collections.sort(procedimientos, new ProcedimientoLocal());
                return procedimientos;
            } else {
                List procedimientosAcceso = new ArrayList();
                Usuario usuario = getUsuario(session);
                for (int i = 0; i < procedimientos.size(); i++) {
                    ProcedimientoLocal procedimiento =  (ProcedimientoLocal)procedimientos.get(i);
                    if(tieneAcceso(usuario, procedimiento)){
                       procedimientosAcceso.add(procedimiento);
                    }
                }
                //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
                Collections.sort(procedimientosAcceso, new ProcedimientoLocal());
                return procedimientosAcceso;
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
	 * Busca todas los Procedimientos que cumplen los criterios de busqueda del nuevo back (sacback2).
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible, String en_plazo, String telematico) {
		Session session = getSession();

		try {
			if (!userIsOper()) {
				parametros.put("validacion", Validacion.PUBLICA);
			}

			List params = new ArrayList();
			String i18nQuery = "";
			
			if (traduccion.get("idioma") != null) {
				i18nQuery = populateQuery(parametros, traduccion, params);
			} else {
				String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if (paramsQuery.length() == 0) {
					i18nQuery += " where ";
				} else {
					i18nQuery += paramsQuery + " and ";
				}
				i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ")";
			}

			Long idUA = (ua != null) ? ua.getId() : null;			
			String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaFilles, uaMeves);

            if ( !StringUtils.isEmpty(uaQuery) )            	
            	uaQuery = " and procedimiento.unidadAdministrativa.id in (" + uaQuery + ")";

			String from = "from ProcedimientoLocal as procedimiento, ";
			String where = "";
			
			if (telematico != null && !telematico.equals("")) {
				if (telematico.equals("1"))
					where += "and procedimiento.id in ";
				else if (telematico.equals("0"))
					where += "and procedimiento.id not in ";
				
				where += "( select tra.procedimiento from Tramite as tra where tra.idTraTel is not null )";
			}
			
			if (en_plazo != null && !en_plazo.equals("")) {
				if (en_plazo.equals("1"))
					where += "and procedimiento.id in ";
				else if (en_plazo.equals("0"))
					where += "and procedimiento.id not in ";
				
				where += "( select tra.procedimiento from Tramite as tra where tra.fase = 1 ";
				where += "and (sysdate < tra.dataTancament or tra.dataTancament is null) ";
				where += "and (sysdate > tra.dataInici or tra.dataInici is null) ) ";
			}
			
			if (materia != null) {
				where += " and procedimiento.id in ( select procsLocales.id " +
						"from Materia as mat, mat.procedimientosLocales as procsLocales " +
						"where mat.id = " + materia + ")";
			}
			
			if (fetVital != null) {
				from += "procedimiento.hechosVitalesProcedimientos as hechosVit, ";
				where += " and hechosVit.hechoVital.id = " + fetVital;
			}
			
			if (publicObjectiu != null) {
				from += "procedimiento.publicosObjetivo as pubsObj, ";
				where += " and pubsObj.id = " + publicObjectiu;
			}
			
			if (visible == 1) {
				where += " and (sysdate < procedimiento.fechaCaducidad or procedimiento.fechaCaducidad is null) ";
				where += " and (sysdate > procedimiento.fechaPublicacion or procedimiento.fechaPublicacion is null) ";
			} else if (visible == 2) {
				where += " and (sysdate > procedimiento.fechaCaducidad or sysdate < procedimiento.fechaPublicacion or procedimiento.validacion = 2 or procedimiento.validacion = 3) ";
			}
			
			if ( userIsOper() ) {
				//Filtrar por el acceso del usuario

				//tieneAccesoValidable
				if (!userIsSuper()) {
					where += " and procedimiento.validacion = " + Validacion.INTERNA;
				}

				// tieneAcceso
				if (!userIsSystem()) {

		            if ( StringUtils.isEmpty(uaQuery) ) { //Se está buscando en todas las unidades orgánicas            	
						uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(null, true, true);
			            if ( !StringUtils.isEmpty(uaQuery) ) {        	
			            	uaQuery = " and procedimiento.unidadAdministrativa.id in (" + uaQuery + ")";
			            } else {
			            	//Esto significa que el usuario no tiene ninguna unidad administrativa configurada, y 
			            	//no es system. Por lo que en realidad no hace falta ejecutar nada, sino más bien devolver
			            	//un resultado vacío
			            	
			            	//uaQuery = " and procedimiento.unidadAdministrativa.id in (-1)";
			    			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();			
			    			resultadoBusqueda.setTotalResultados(0);
			    			resultadoBusqueda.setListaResultados(new ArrayList<ProcedimientoLocal>());
			    			
			    			return resultadoBusqueda;			            	
			            }
						
					}
				}

			}
			
			where += " and index(tradFam) = 'ca' ";
			String select = "select new ProcedimientoLocal(procedimiento.id, trad.nombre, procedimiento.validacion, " +
								    "procedimiento.fechaActualizacion, procedimiento.fechaCaducidad, procedimiento.fechaPublicacion, " +
								    "tradFam.nombre, index(trad), procedimiento.unidadAdministrativa ) ";

			String selectCount = "select count(*) ";

			String restoQuery = " procedimiento.traducciones as trad, procedimiento.familia as fam, fam.traducciones as tradFam " + i18nQuery + uaQuery + where;
			String orderBy = " order by procedimiento." + parametros.get("ordreCamp") + " " + parametros.get("ordreTipus");
			
			String queryStr = select + from + restoQuery + orderBy;
			String queryCountStr = selectCount + from + restoQuery;
			
			Query query = session.createQuery(queryStr);
			Query queryCount = session.createQuery(queryCountStr);
			
			for (int i = 0; i < params.size(); i++) {
				String o = (String) params.get(i);
				query.setString(i, o);
				queryCount.setString(i, o);
			}

			int resultadosMax = new Integer(resultats).intValue();
			int primerResultado = new Integer(pagina).intValue() * resultadosMax;
						
			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();			

			resultadoBusqueda.setTotalResultados( (Integer) queryCount.uniqueResult() );
			
			if ( resultadosMax != RESULTATS_CERCA_TOTS) {
				query.setFirstResult(primerResultado);
				query.setMaxResults(resultadosMax);
			}				
			
			resultadoBusqueda.setListaResultados(query.list());				
			
			return resultadoBusqueda;
			
		} catch (DelegateException de) {
			throw new EJBException(de);
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
    
    /**
     * Construye el query de b�squeda multiidioma en todos los campos
     */
    private String i18nPopulateQuery(Map traducciones, List params) {
        String aux = "";

        for (Iterator iterTraducciones = traducciones.keySet().iterator(); iterTraducciones.hasNext();) {
        	
            String key = (String) iterTraducciones.next();
            Object value = traducciones.get(key);
            
            if (value != null) {
            	if (aux.length() > 0) aux = aux + " or ";
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( trad." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( trad." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + " trad." + key + " = ? ";
                    params.add(value);
                }
            }
        }

        return aux;
    }
    
    
    /**
     * Obtiene una lista de procedimientos de la misma Materia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarProcedimientosMateria(Long id) {
        Session session = getSession();
        try {
            List result = new ArrayList();
            Materia materia = (Materia) session.load(Materia.class, id);
            Hibernate.initialize(materia.getProcedimientosLocales());
            for (Iterator iter = materia.getProcedimientosLocales().iterator(); iter.hasNext();) {
                ProcedimientoLocal procedimiento = (ProcedimientoLocal) iter.next();
                if (publico(procedimiento)) {
                    result.add(procedimiento);
                }
            }
            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(result, new ProcedimientoLocal());
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Busca todos los Procedimientos con un texto determinado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarProcedimientosTexto(String texto) {
        IndexerDelegate delegate = DelegateUtil.getIndexerDelegate();
        Long[] ids;
        try {
            ids = delegate.buscarIds(ProcedimientoLocal.class.getName(), texto);
        } catch (DelegateException e) {
            log.error("Error buscando", e);
            ids = new Long[0];
        }

        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }

        Session session = getSession();
        
        try {
        	
            Criteria criteria = session.createCriteria(ProcedimientoLocal.class);
            criteria.add(Expression.in("id", ids));
            criteria.setFetchMode("traducciones", FetchMode.EAGER);
            List result = new ArrayList();
            
            for (Iterator iterator = criteria.list().iterator(); iterator.hasNext();) {
                ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
                if (publico(proc)) {
                    result.add(proc);
                }
            }
            
            return result;
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Busca todos los Procedimientos con un texto determinado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarProcedimientosUATexto(Long idUnidad, String texto) {
        IndexerDelegate delegate = DelegateUtil.getIndexerDelegate();

        // Buscar con lucene, los ids de los procedimientos que continen el texto.
        Long[] ids;
        try {
            ids = delegate.buscarIds(ProcedimientoLocal.class.getName(), texto);
        } catch (DelegateException e) {
            log.error("Error buscando", e);
            ids = new Long[0];
        }

        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }

        Session session = getSession();
        try {
            // Obtener una lista de los identificadores de todo el arbol de Unidades.
            // Habr� una consulta por nivel.
            List idUnidades = new ArrayList();
            List currentParents = Collections.singletonList(idUnidad);
            while (!currentParents.isEmpty()) {
                idUnidades.addAll(currentParents);
                Query uaQuery = session.createQuery("select ua.id from UnidadAdministrativa ua where ua.padre.id in (:uas)");
                uaQuery.setParameterList("uas", currentParents);
                currentParents = uaQuery.list();
            }

            // Filtraremos por los ids obtenidos con lucene.
            // y la lista de unidades administrativas.
            Criteria criteria = session.createCriteria(ProcedimientoLocal.class);
            criteria.add(Expression.in("id", ids));
            criteria.add(Expression.in("unidadAdministrativa.id", idUnidades));
            List result = new ArrayList();
            for (Iterator iterator = criteria.list().iterator(); iterator.hasNext();) {
                ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
                if (publico(proc)) {
                    result.add(proc);
                }
            }
            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(result, new ProcedimientoLocal());
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Busca todos los Procedimientos con un texto determinado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarProcedimientosUATexto(Long idUnidad, String texto, String idioma) {
        
    	IndexerDelegate delegate = DelegateUtil.getIndexerDelegate();
    	
        IndexResultados indexResultados;
        
        List idProcs = new ArrayList();
       
		try {
			indexResultados = delegate.buscaravanzado(texto, "", "", "", "PRC", "", "", null, null, "", idioma, true, true);
			List list = indexResultados.getLista();
			
			log.debug("###################################### buscarProcedimientosUATexto\n RESULTADOS DE LA BUSQUEDA: " + list.size() + "\n#######################################################");
			for (Object obj : list) {
				if (obj instanceof IndexEncontrado) {
					IndexEncontrado indexEncontrado = (IndexEncontrado) obj;
					String str = indexEncontrado.getId(); //El id que devuelve no es de tipo PRC.EXTERNO.DCMTS.39
					
					//Obtenemos el valor numerico del final
					Long n = Long.valueOf(str.substring((str.lastIndexOf('.')+1))).longValue();
					
					//Obtenemos el procedimiento, recuperamos el identificador y lo a�adimos a la lista
					idProcs.add( obtenerProcedimiento(n).getId());
				}
			}
			
			if (idProcs == null || idProcs.size() == 0) {
				return Collections.EMPTY_LIST;
			}
			
		} catch (DelegateException e) {
			log.error("Error buscando", e);
			  throw new EJBException(e);
        }
		
		Session session = getSession();
        try {
            // Obtener una lista de los identificadores de todo el arbol de Unidades.
            // Habr� una consulta por nivel.
            List idUnidades = new ArrayList();
            List currentParents = Collections.singletonList(idUnidad);
            while (!currentParents.isEmpty()) {
                idUnidades.addAll(currentParents);
                Query uaQuery = session.createQuery("select ua.id from UnidadAdministrativa ua where ua.padre.id in (:uas)");
                uaQuery.setParameterList("uas", currentParents);
                currentParents = uaQuery.list();
            }

            // Filtraremos por los ids obtenidos con lucene.
            // y la lista de unidades administrativas.
            Criteria criteria = session.createCriteria(ProcedimientoLocal.class);
            criteria.add(Expression.in("id", idProcs));
            criteria.add(Expression.in("unidadAdministrativa.id", idUnidades));
            criteria.addOrder(Order.desc("tramite"));
            List result = new ArrayList();
            
            
            for (Iterator iterator = criteria.list().iterator(); iterator.hasNext();) {
                ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
                if (publico(proc)) {
                    result.add(proc);
                }
            }
      
            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(result, new ProcedimientoLocal());
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * A�ade un nuevo tramite al procedimiento (el tramite ya existe en la base de datos)
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void anyadirTramite(Long tramite_id, Long proc_id) {
    	log.debug("[anyadirTramite] tramiteId="+tramite_id +" procId=" +proc_id);
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
            Tramite tramite = (Tramite) session.load(Tramite.class, tramite_id);
            log.debug("tramite load="+tramite);
            log.debug("proc load="+procedimiento);
            procedimiento.addTramite(tramite);
            session.flush();
            
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * elimina un tramite del procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void eliminarTramite(Long tramite_id, Long proc_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
            Tramite tramite = (Tramite) session.load(Tramite.class, tramite_id);
            procedimiento.removeTramite(tramite);
            session.flush();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un procedimiento.(PORMAD)
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarProcedimiento(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            procedimiento.getNormativas().clear();

            //Borram els documents directament amb query per evitar el problema del ordres.
            //S'ha llevat el cascade=delete de l'hbm.
            session.delete("from Documento as doc where doc.procedimiento.id = ?",id, Hibernate.LONG);

            addOperacion(session, procedimiento, Auditoria.BORRAR);
            Historico historico = getHistorico(session, procedimiento);
            ((HistoricoProcedimiento) historico).setProcedimiento(null);
            procedimiento.getUnidadAdministrativa().removeProcedimientoLocal(procedimiento);
            
            if(procedimiento instanceof ProcedimientoRemoto){
                AdministracionRemota admin = ((ProcedimientoRemoto)procedimiento).getAdministracionRemota();
                if(admin!=null)
                	admin.removeProcedimientoRemoto((ProcedimientoRemoto)procedimiento);
            }else{
            	Actualizador.borrar(new ProcedimientoLocal(id));
            }
            
            // Borrar comentarios
            session.delete("from ComentarioProcedimiento as cp where cp.procedimiento.id = ?", id, Hibernate.LONG);

            session.delete(procedimiento);
            session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene los procedimientos de una unidad administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientosUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            Hibernate.initialize(unidadAdministrativa.getProcedimientos());
            List procs = new ArrayList(unidadAdministrativa.getProcedimientos());
            
			//Ordena la lista por el atributo id
            Collections.sort(procs, new ProcedimientoLocal());
        	 
            return procs;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
 
    /**
     * Obtiene los procedimientos de una unidad administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientosUO(Long id,Integer conse) {
        Session session = getSession();
         
        try {
            String hql=" from ProcedimientoLocal pro";
            hql+=" where (pro.unidadAdministrativa=:uaid"; 
            hql+=" or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid) ";
            hql+=" or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid)) ";            
            hql+=" )";
            //TODO: comprobar este cambio, estaba en ROLSAC_STAMARGA
            //hql+=" and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > :now ) and pro.validacion = 1";
            if (conse==1) { hql+=" order by pro.orden  asc,pro.fechaActualizacion desc";}
            if (conse==2) { hql+=" order by pro.orden2 asc,pro.fechaActualizacion desc";}	
            if (conse==3) { hql+=" order by pro.orden3 asc,pro.fechaActualizacion desc";} 
            Query query = session.createQuery(hql);
            query.setLong("uaid", id);
            //query.setDate("now", new Date());
            List procs = new ArrayList(query.list());
            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(procs, new ProcedimientoLocal());
            for (Object proc:procs) {
            	//Inicializamos las materias del procedimiento
            	Hibernate.initialize( ((ProcedimientoLocal)proc).getMaterias());
            }
            
            return procs;

        } catch (Exception he) {

            throw new EJBException(he);

        } finally {

            close(session);

        }

    }
    /**
     * Obtiene los procedimientos de una unidad administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientosPublicosUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            return procedimientosPublicosRecursivosUA(unidadAdministrativa);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    private List procedimientosPublicosRecursivosUA(UnidadAdministrativa ua) throws HibernateException {
        List result = new ArrayList();
        Hibernate.initialize(ua.getProcedimientos());
        for (Iterator iterator = ua.getProcedimientos().iterator(); iterator.hasNext();) {
            ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
            if (publico(proc)) {
                result.add(proc);
            }
        }
        for (int i = 0; i < ua.getHijos().size(); i++) {
            UnidadAdministrativa uaHijo = (UnidadAdministrativa) ua.getHijos().get(i);
            result.addAll(procedimientosPublicosRecursivosUA(uaHijo));
        }
        //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
        Collections.sort(result, new ProcedimientoLocal());
        return result;
    }

  /**
     * Obtiene los procedimientos publicos de una unidad administrativa (PORMAD recuperacion de datos inicial)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) {
    	Session session = getSession();
    	try {
    		UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
    		Hibernate.initialize(unidadAdministrativa.getProcedimientos());

    		Set<ProcedimientoLocal> procs = unidadAdministrativa.getProcedimientos();
    		List<ProcedimientoLocal> procsFinales = new ArrayList<ProcedimientoLocal>();
    		for(ProcedimientoLocal proc: procs){
    			if(publico(proc)){
    				//Variable que indica si el procedimiento tiene alguna relacion
    				boolean relacionada = false;

    				//comprobamos materias
    				Query queryMat = session.createQuery("select mat.codigoEstandar from ProcedimientoLocal p, p.materias as mat where p.id =:id");
    				queryMat.setParameter("id", proc.getId(), Hibernate.LONG);

    				List<String> codigosMaterias = queryMat.list();

    				//si el procedimiento esta relacionada con alguna materia la marcamos
    				for(String codigoMat: codEstMat){
    					if (relacionada = codigosMaterias.contains(codigoMat)){
    						break;
    					}
    				}

    				//Si no tiene niguna relacion con ninguna materia miramos si teiene ralacion con algun HV
    				if(!relacionada){
    					Query queryHechos = session.createQuery("select hpv.hechoVital.codigoEstandar from ProcedimientoLocal p, p.hechosVitalesProcedimientos as hpv where p.id =:id");
    					queryHechos.setParameter("id", proc.getId(), Hibernate.LONG);

    					List<String> codigosHechos = queryHechos.list();

    					// si la ficha esta relacionada con el hechovital la marcamos
    					for(String codigoHev: codEstHV){
    						if (relacionada = codigosHechos.contains(codigoHev)){
    							break;
    						}
    					}
    				}

    				if(relacionada){
    					Hibernate.initialize(proc.getMaterias());
    					Hibernate.initialize(proc.getHechosVitalesProcedimientos());
                        Hibernate.initialize(proc.getDocumentos());
    					procsFinales.add(proc);
    				}
    			}
    		}
    		//Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(procsFinales, new ProcedimientoLocal());
    		return procsFinales;
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }

    /**
     * Obtiene los ids de los procedimientos publicos de una unidad administrativa (PORMAD recuperacion de datos inicial)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) {
    	Session session = getSession();
    	try {
    		UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
    		Hibernate.initialize(unidadAdministrativa.getProcedimientos());

    		Set<ProcedimientoLocal> procs = unidadAdministrativa.getProcedimientos();
    		List<Long> procsFinales = new ArrayList<Long>();
    		for(ProcedimientoLocal proc: procs){
    			if(publico(proc)){
    				//Variable que indica si el procedimiento tiene alguna relacion
    				boolean relacionada = false;

    				//comprobamos materias
    				Query queryMat = session.createQuery("select mat.codigoEstandar from ProcedimientoLocal p, p.materias as mat where p.id =:id");
    				queryMat.setParameter("id", proc.getId(), Hibernate.LONG);

    				List<String> codigosMaterias = queryMat.list();

    				//si el procedimiento esta relacionada con alguna materia la marcamos
    				for(String codigoMat: codEstMat){
    					if (relacionada = codigosMaterias.contains(codigoMat)){
    						break;
    					}
    				}

    				//Si no tiene niguna relacion con ninguna materia miramos si teiene ralacion con algun HV
    				if(!relacionada){
    					Query queryHechos = session.createQuery("select hpv.hechoVital.codigoEstandar from ProcedimientoLocal p, p.hechosVitalesProcedimientos as hpv where p.id =:id");
    					queryHechos.setParameter("id", proc.getId(), Hibernate.LONG);

    					List<String> codigosHechos = queryHechos.list();

    					// si la ficha esta relacionada con el hechovital la marcamos
    					for(String codigoHev: codEstHV){
    						if (relacionada = codigosHechos.contains(codigoHev)){
    							break;
    						}
    					}
    				}

    				if(relacionada){
    				/*	Hibernate.initialize(proc.getMaterias());
    					Hibernate.initialize(proc.getHechosVitalesProcedimientos());
                        Hibernate.initialize(proc.getDocumentos());
    				*/	procsFinales.add(proc.getId());
    				}
    			}
    		}
    		return procsFinales;
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Obtiene los procedimientos p�blicos de un Hecho Vital
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientosPublicosHechoVital(Long id) {
        Session session = getSession();
        try {
            HechoVital hechoVital = (HechoVital) session.load(HechoVital.class, id);
            List result = new ArrayList();
            for (int i = 0; i < hechoVital.getHechosVitalesProcedimientos().size(); i++) {
                HechoVitalProcedimiento hechoVitalProcedimiento = (HechoVitalProcedimiento) hechoVital.getHechosVitalesProcedimientos().get(i);
                ProcedimientoLocal proc = hechoVitalProcedimiento.getProcedimiento();
                if (publico(proc)) {
                    result.add(proc);
                }
            }

            //Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
            Collections.sort(result, new ProcedimientoLocal());
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Consulta toda la informaci�n de un procedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimientoLocal consultarProcedimiento(Long id) {
        Session session = getSession();
        try {
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            if (userIsOper() || publico(procedimiento)) {
                Hibernate.initialize(procedimiento.getTramites());
                List<Tramite>tramites = procedimiento.getTramites();
                for (Iterator iter = tramites.iterator(); iter.hasNext();) {
                    Tramite tramite = (Tramite) iter.next();
                    Hibernate.initialize(tramite.getFormularios()); 
                    Hibernate.initialize(tramite.getDocsInformatius());
                    Hibernate.initialize(tramite.getTaxes());
                }
                Hibernate.initialize(procedimiento.getDocumentos());
                Hibernate.initialize(procedimiento.getNormativas());
                Hibernate.initialize(procedimiento.getMaterias());
                Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());

                return procedimiento;
            } else {
                throw new SecurityException("Procedimiento no p�blico.");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }

    }

    /**
     * Construye el query de b�squeda segun los par�metros
     */
    private String populateQuery(Map parametros, Map traduccion, List params) {
        String aux = "";

        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (!key.startsWith("ordre") && value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (aux.length() > 0) aux = aux + " and ";
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( procedimiento." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( procedimiento." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else if (value instanceof Date) {
                    if (aux.length() > 0) aux = aux + " and ";
                    aux = aux + "procedimiento." + key + " = '" + value + "'";
                } else {
                    if (aux.length() > 0) aux = aux + " and ";
                    aux = aux + "procedimiento." + key + " = " + value;
                }
            }
        }

        // Tratamiento de traducciones
        if (!traduccion.isEmpty()) {
            if (aux.length() > 0) aux = aux + " and ";
            aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
            traduccion.remove("idioma");
        }
        for (Iterator iter2 = traduccion.keySet().iterator(); iter2.hasNext();) {
            String key = (String) iter2.next();
            Object value = traduccion.get(key);
            if (value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " and upper( trad." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " and upper( trad." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + " and trad." + key + " = ? ";
                    params.add(value);
                }
            }
        }

        if (aux.length() > 0) {
            aux = "where " + aux;
        }
        return aux;
    }


    protected boolean publico(ProcedimientoLocal proc) {
        final Date now = new Date();
        boolean noCaducado = (proc.getFechaCaducidad() == null || proc.getFechaCaducidad().after(now));
        boolean publicado = (proc.getFechaPublicacion() == null || proc.getFechaPublicacion().before(now));
        return visible(proc) && noCaducado && publicado;
    }
    
    
	/**
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * Debemos incluir las materias y los hechos vitales, la unidad administrativa de la que depende y la familia.
	 * M�todo v�lido para Procedimientos los 3 tipos:
	 * 
	 * Procedimiento No telem�tico, los de Rolsac por defecto (sin url, ni version ni modelo)
	 * Procedimiento Telem�tico de Sistra (tiene versi�n y modelo)
	 * Procedimiento Telem�tico Externo (tiene url)
	 * @throws DelegateException 
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public ModelFilterObject obtenerFilterObject(ProcedimientoLocal proc) throws DelegateException {
		ModelFilterObject filter = new ModelFilterObject();
		
		Session session = getSession();

   		TraModelFilterObject trafilter;
   		String idioma;
   		String txids;
   		String txtexto;
			
   		filter.setMicrosite_id(null);
   		filter.setSeccion_id(null);

   		// Obtenemos las materias y hechos vitales
   		Materia mat;
   		HechoVitalProcedimiento hvital;
   		Hashtable lista_materias = new Hashtable(), lista_hechos = new Hashtable();
   		UnidadAdministrativa ua= proc.getUnidadAdministrativa();
		List listapadres= org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa(ua.getId());
   		
   		if (proc.getMaterias()!=null) {
   			Iterator itmat=proc.getMaterias().iterator();
   			while (itmat.hasNext()) {
   				mat=(Materia)itmat.next();
   				if (!lista_materias.containsKey(mat.getId()))
       					lista_materias.put(mat.getId(), mat);
   			}
   		}

   		if (proc.getHechosVitalesProcedimientos()!=null) {
   			Iterator itvital=proc.getHechosVitalesProcedimientos().iterator();
   			while (itvital.hasNext()) {
   				hvital=(HechoVitalProcedimiento)itvital.next();
   				if (!lista_hechos.containsKey(hvital.getHechoVital().getId()))
   					lista_hechos.put(hvital.getHechoVital().getId(), hvital.getHechoVital());
   			}
   		}
   		
   		Iterator langs= proc.getLangs().iterator();
   		while (langs.hasNext()) {
   			idioma = (String) langs.next();
			txids=Catalogo.KEY_SEPARADOR;
    		txtexto=" ";
	    		
			trafilter = new TraModelFilterObject();
			trafilter.setMaintitle(null); 
				
			// Obtenemos la UA con sus padres excepto el raiz
			if (ua!=null) {
				txids=Catalogo.KEY_SEPARADOR;
				txtexto=" ";

				UnidadAdministrativa ua_padre=null;
				for (int x = 1; x < listapadres.size(); x++) {
					ua_padre=(UnidadAdministrativa)listapadres.get(x);
					txids+=ua_padre.getId()+Catalogo.KEY_SEPARADOR;
					if (ua_padre.getTraduccion(idioma)!=null)
						txtexto+=((TraduccionUA)ua_padre.getTraduccion(idioma)).getNombre()+" ";
				}

				filter.setUo_id( (txids.length()==1) ? null: txids);
				trafilter.setUo_text( (txtexto.length()==1) ? null: txtexto);
			}
			
			// Obtenemos su Familia
			Familia fam= proc.getFamilia();
			if (fam!=null) {
				filter.setFamilia_id(fam.getId());
				if (fam.getTraduccion(idioma)!=null)	
					trafilter.setFamilia_text(((TraduccionFamilia)fam.getTraduccion(idioma)).getNombre());
			}
			
			// Obtenemos las materias y hechos vitales
	    	txids=Catalogo.KEY_SEPARADOR;
	    	txtexto=" ";
	    		
	    	Enumeration i=lista_materias.keys();
	    		
	    	while (i.hasMoreElements()) {
	    		Materia materia = (Materia)lista_materias.get(i.nextElement());
		       	txids+=materia.getId()+Catalogo.KEY_SEPARADOR; //anadir los ids (los de los hechos vitales no)
		       	if (materia.getTraduccion(idioma)!=null) {
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getNombre() + " ";
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getDescripcion() + " ";
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getPalabrasclave() + " ";
		       	}
	    	}

	    	i=lista_hechos.keys();
	    	HechoVital hechovital=null;
	    	
	    	while (i.hasMoreElements()) {
	    		hechovital = (HechoVital)lista_hechos.get(i.nextElement());
		       	if (hechovital.getTraduccion(idioma)!=null) {
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getNombre() + " ";
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getDescripcion() + " ";
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getPalabrasclave() + " ";
		       	}
	    	}
	    		
	    	filter.setMateria_id( (txids.length()==1) ? null: txids);
	    	trafilter.setMateria_text( (txtexto.length()==1) ? null: txtexto);
	    		
    		filter.addTraduccion(idioma, trafilter);
 
		}
   		close(session);
   		
		return filter;
	}        

	
	
    /**
     * A�ade los procedimientos al indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaProcedimiento(ProcedimientoLocal proc, ModelFilterObject filter)  {
    	
    	try {
    		if (proc.getValidacion().equals(2)) return;
    		
    		proc = obtenerProcedimiento(proc.getId()); 
    		
	    	if (filter==null) filter = obtenerFilterObject(proc);  	
	    	String tipo = tipoProcedimiento (proc,false); 
	    	String tipoDoc = tipoProcedimiento (proc,true); 	    	
			for (Iterator iterator = proc.getLangs().iterator(); iterator.hasNext();) {
				String idi = (String) iterator.next();
				IndexObject io= new IndexObject();
				
				io.setId(tipo + "." + proc.getId());
				io.setClasificacion(tipo);
	            
	            io.setMicro( filter.getMicrosite_id() );
	            io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );
				
				io.setCaducidad("");	
				io.setPublicacion("");
				io.setDescripcion(""); 
				if (proc.getFechaCaducidad()!=null)		io.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaCaducidad()));
				if (proc.getFechaPublicacion()!=null)	io.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaPublicacion()));
				

	            TraduccionProcedimientoLocal trad=((TraduccionProcedimientoLocal)proc.getTraduccion(idi));
	            if (trad!=null) {

	            	io.setTituloserviciomain(trad.getNombre());

            		io.setUrl("/govern/sac/visor_proc.do?codi="+proc.getId()+"&lang=" + idi + "&coduo=" + proc.getUnidadAdministrativa().getId());
	            	// Si es externo ponemos su propia URL
            		if (tipo.equals(Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO))	io.setUrl(proc.getUrl());
	            	if (trad.getNombre()!=null) {
	            		io.setTitulo(trad.getNombre());
	            		io.addTextLine(trad.getNombre());
	            		if (trad.getResumen()!=null) {
	            			//if (trad.getResumen().length()>200) io.setDescripcion(trad.getResumen().substring(0,199)+"...");
	            			//else io.setDescripcion(trad.getResumen());
	            			io.setDescripcion(trad.getResumen());
	            		}
	            	}
	            	if (trad.getDestinatarios()!=null) 	io.addTextLine(trad.getDestinatarios());
	            	if (trad.getLugar()!=null)			io.addTextLine(trad.getLugar());
	            	if (trad.getObservaciones()!=null)	io.addTextLine(trad.getObservaciones());
	            	if (trad.getPlazos()!=null)			io.addTextLine(trad.getPlazos());
	            	if (trad.getResolucion()!=null)		io.addTextLine(trad.getResolucion());
	            	if (trad.getNotificacion()!=null)	io.addTextLine(trad.getNotificacion());
	            	if (trad.getRecursos()!=null)		io.addTextLine(trad.getRecursos()); // No est� en el mantenimiento
	            	if (trad.getRequisitos()!=null)		io.addTextLine(trad.getRequisitos());
	            	if (trad.getSilencio()!=null)		io.addTextLine(trad.getSilencio());
					
	            }
				io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getFamilia_text());
				// A�adimos colecciones pero solo t�tulos como opcional
				if (proc.getTramites()!=null) {
					Iterator iter1 = proc.getTramites().iterator();
					while (iter1.hasNext()) {
						Tramite tra = (Tramite)iter1.next();
						if (tra.getTraduccion(idi)!=null) {
							io.addTextopcionalLine(((TraduccionTramite)tra.getTraduccion(idi)).getNombre());
						}
					}
				}

				if (proc.getNormativas()!=null) {
					Iterator iter2 = proc.getNormativas().iterator();
					while (iter2.hasNext()) {
						NormativaLocal norm = (NormativaLocal)iter2.next();
						if (norm.getTraduccion(idi)!=null) {
							io.addTextopcionalLine(((TraduccionNormativa)norm.getTraduccion(idi)).getTitulo());
						}
					}
				}

				if (proc.getMaterias()!=null) {
					Iterator iter3 = proc.getMaterias().iterator();
					while (iter3.hasNext()) {
						Materia mat = (Materia)iter3.next();
						if (mat.getTraduccion(idi)!=null) {
							io.addTextopcionalLine(((TraduccionMateria)mat.getTraduccion(idi)).getNombre());
						}
					}
				}
	            //se a�aden todos los documentos en todos los idiomas
				if (proc.getDocumentos()!=null) {
					Iterator iterdocs = proc.getDocumentos().iterator();
					while (iterdocs.hasNext()) {
						Documento documento = (Documento)iterdocs.next();
						documento = DelegateUtil.getDocumentoDelegate().obtenerDocumento(documento.getId());
						if (documento.getTraduccion(idi)!=null) {
							io.addTextLine(((TraduccionDocumento)documento.getTraduccion(idi)).getTitulo());
							io.addTextLine(((TraduccionDocumento)documento.getTraduccion(idi)).getDescripcion());
						}
						//io.addArchivo((Archivo)documento.getArchivo());

						// Se crea la indexaci�n del documento individual y se a�ade la informaci�n 
		            	// para la indexaci�n de la ficha.
							IndexObject ioDoc = new IndexObject();
			            	String textDoc = null;								
			            	//ioDoc.addArchivo((Archivo)documento.getArchivo());	
			            	Archivo arch = new Archivo();
			            	if (documento.getTraduccion(idi)!=null) {
			            		arch = (Archivo)((TraduccionDocumento)documento.getTraduccion(idi)).getArchivo();
			            		ioDoc.addArchivo(arch);
			            	} else {
			            					            		
			            	}
			            	
			            	textDoc = ioDoc.getText();
				            if (textDoc != null && textDoc.length() > 0) {	
					            if (documento.getTraduccion(idi)!=null) {				            	
									
									ioDoc.setId(tipoDoc + "." + documento.getId());
									ioDoc.setClasificacion(tipo + "." + proc.getId());
									ioDoc.setCaducidad("");
									ioDoc.setPublicacion(""); 
									ioDoc.setDescripcion("");
									if (proc.getFechaCaducidad()!=null)		ioDoc.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaCaducidad()));
									if (proc.getFechaPublicacion()!=null)	ioDoc.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaPublicacion()));
					        		ioDoc.setUrl( "/fitxer/get?codi=" + arch.getId());
					            	ioDoc.setTituloserviciomain(io.getTitulo());  
					            	ioDoc.setTitulo(((TraduccionDocumento)documento.getTraduccion(idi)).getTitulo() + ", (" + arch.getMime().toUpperCase() +")");  
					            	ioDoc.setDescripcion(((TraduccionDocumento)documento.getTraduccion(idi)).getDescripcion());
					            	ioDoc.setText(textDoc);
					            	ioDoc.addTextLine(((TraduccionDocumento)documento.getTraduccion(idi)).getDescripcion());
					            	ioDoc.addTextLine(arch.getNombre());
						            if ( io.getUo()!=null) 	ioDoc.setUo( io.getUo());
						            if ( io.getMateria()!=null) ioDoc.setMateria( io.getMateria());
									if ( io.getSeccion()!=null) ioDoc.setSeccion( io.getSeccion());
									if ( io.getFamilia()!=null) ioDoc.setFamilia( io.getFamilia());
									
						            if (ioDoc.getText().length()>0 || ioDoc.getTextopcional().length()>0)
						            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(ioDoc, idi);								
					            }
				            	
				            	//io.addArchivo((Archivo)documento.getArchivo());
								io.addTextLine(textDoc);					            
				            }
						
						
					}
				}	
	            if (io.getText().length()>0)
	            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);
			}
			//log.warn("[indexInsertaProcedimiento:" + proc.getId() + "] INDEXADO");
		
		}
					
		catch (Exception ex) {
			log.warn("[indexInsertaProcedimiento:" + proc.getId() + "] No se ha podido indexar el procedimiento. " + ex.getMessage());
		}
        
	}
	
	 /**
     * Elimina el procedimiento en el indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void indexBorraProcedimiento(ProcedimientoLocal pro)  {

		try {

			for (Iterator iterator = pro.getLangs().iterator(); iterator.hasNext();) {
				String idi = (String) iterator.next();
				DelegateUtil.getIndexerDelegate().borrarObjeto( tipoProcedimiento(pro,false) + "." + pro.getId(), idi);
				DelegateUtil.getIndexerDelegate().borrarObjetosDependientes( tipoProcedimiento(pro,false) + "." + pro.getId(), idi);				
			}

		}
		catch (DelegateException ex) {
			log.warn("[indexBorraProcedimiento:" + pro.getId() + "] No se ha podido borrar del indice el procedimiento. " + ex.getMessage());
		}
		
	}
	

	
    /**
     * Actualiza el orden de los tramites 
	 *
     * FIXME enric@dgtic: este metodo lo pondria en procedimientoFacadeEJB 
     *  
     * @param map <String,String[]>
     * eg. key= orden_doc396279
     * 	   value={"1"}
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	
	public void actualizarOrdenTramites(Map map) throws DelegateException {
      	Session session = getSession();
        try {
        	Long id;
        	int valor_orden=0;
        	List elem_orden = new ArrayList();
        	
        	//crea la llista de elements a ordenar
        	Iterator it = map.entrySet().iterator();
        	while (it.hasNext()) {
        		Map.Entry e = (Map.Entry)it.next();
        	
            	String paramName = e.getKey().toString();
            	if (paramName.startsWith("orden")) {
            		id=  Long.valueOf(paramName.substring(5)).longValue();
             		String[] parametros=(String[])e.getValue();
            		valor_orden= Integer.parseInt(parametros[0]);            		
            		
            		if (!getAccesoManager().tieneAccesoTramite(id)) {
            			throw new SecurityException("No tiene acceso al tramite");
            		}
            		Tramite tram = (Tramite) session.load(Tramite.class, id);
            		tram.setOrden((long)valor_orden);
            		elem_orden.add(tram);
            	}
            }
            session.flush();
            
            //ordena la llista per numeros relatius, no absoluts. 
            Collections.sort( elem_orden, new TramiteComparator() );

            //estableix un ordre absolut
            Long contador= Long.parseLong("0");  //en hibernate, el indice debe empezar desde 0..N-1
        	it=elem_orden.iterator();
    		Tramite tram=null;
    		while (it.hasNext()) {
    			tram=(Tramite)it.next();
    			tram.setOrden(contador);
    			contador++;
    		}
            session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
            
    }

	
	private String tipoProcedimiento (ProcedimientoLocal proc, boolean doc) {
		
		String tipo="";
		
		if (!doc) {
			if (proc.getUrl()!=null && proc.getUrl().length()>0)			tipo=Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO;
			else if ((proc.getVersion()==null && proc.getTramite()==null) || (proc.getVersion()==null && proc.getTramite()!=null && proc.getTramite().length()==0 )){tipo=Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO;} 	
			else															tipo=Catalogo.SRVC_PROCEDIMIENTOS_SISTRA;
		} else {
			if (proc.getUrl()!=null && proc.getUrl().length()>0)			tipo=Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO_DOCUMENTOS;
			else if (proc.getVersion()==null && proc.getTramite()==null) 	tipo=Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO_DOCUMENTOS;
			else															tipo=Catalogo.SRVC_PROCEDIMIENTOS_SISTRA_DOCUMENTOS;			
		}
    	return tipo;
	}
	
	
    class TramiteComparator implements Comparator {
		public int compare(Object o1, Object o2) { 
			Long x1=new Long (((Tramite)o1).getOrden());
			Long x2=new Long (((Tramite)o2).getOrden());
			return x1.compareTo( x2 ); 
		}
	}
    
    
    /**
     * Buscamos el numero de procedimientos activos des de la fecha actual
     * 
     * @param List<Long> listaUnidadAdministrativaId
     * @param Date fechaCaducidad
     * @return numero de Procedimientos activos
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad){
		Integer resultado = 0;
		Session session = getSession();
	
		try {
			
        	Query query = null;
        	if (listaUnidadAdministrativaId.size() > 0) {
        		query = session.createQuery(" select count(*) from ProcedimientoLocal as prc where prc.validacion = :validacion " +
        				" and (prc.fechaCaducidad >= :fecha or prc.fechaCaducidad is null) " +
        				" and (prc.fechaPublicacion <= :fecha or prc.fechaPublicacion is null) " +
        				" and prc.unidadAdministrativa.id in (:lId) ");
        		query.setInteger("validacion", Validacion.PUBLICA);
	        	query.setDate("fecha", fechaCaducidad);
	        	query.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
	        	
	        	resultado = (Integer) query.uniqueResult();
        	}
        	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
		
		return resultado;
	}
    
	 /**
     * Buscamos el numero de procedimientos activos des de la fecha actual
     * 
     * @param List<Long> listaUnidadAdministrativaId
     * @param Date fechaCaducidad
     * @return numero de Procedimientos caducados
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad){
		
		Integer resultado = 0;		
		Session session = getSession();
		try {
        	Query query = null;
        	if (listaUnidadAdministrativaId.size() > 0) {
	        	query = session.createQuery("select count(*) from ProcedimientoLocal as prc where ( " +
	        			" ( prc.validacion != :validacion ) " +
	        			" or ( prc.validacion = :validacion and prc.fechaCaducidad < :fecha ) " +
	        			" or ( prc.validacion = :validacion and prc.fechaCaducidad is null and prc.fechaPublicacion > :fecha ) " +
	        			" or ( prc.validacion = :validacion and prc.fechaCaducidad >= :fecha and prc.fechaPublicacion > :fecha ) " +
	        			" ) and prc.unidadAdministrativa.id in (:lId) ");
	        	query.setInteger("validacion", Validacion.PUBLICA);
	        	query.setDate("fecha", fechaCaducidad);
	        	query.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
	        	
	        	resultado = (Integer) query.uniqueResult();
        	}
        	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
			
		return resultado;
	}
	
}
