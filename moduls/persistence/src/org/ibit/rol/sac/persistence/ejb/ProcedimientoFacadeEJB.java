package org.ibit.rol.sac.persistence.ejb;

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

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
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
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
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
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.lucene.analysis.AlemanAnalyzer;
import es.caib.rolsac.lucene.analysis.CastellanoAnalyzer;
import es.caib.rolsac.lucene.analysis.CatalanAnalyzer;
import es.caib.rolsac.lucene.analysis.InglesAnalyzer;
import es.caib.rolsac.lucene.model.ModelFilterObject;
import es.caib.rolsac.lucene.model.TraModelFilterObject;
import es.caib.rolsac.lucene.model.Catalogo;
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

	private static final long serialVersionUID = -7754045272386270651L;

	/**
	 * Obtiene referencia al ejb de control de Acceso.
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
	 * @deprecated No se usa. 
	 * Autoriza la creaci�n de un procedimiento
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaCrearProcedimiento(Integer validacionProcedimiento) throws SecurityException  {
		return !(validacionProcedimiento.equals(Validacion.PUBLICA) && !userIsSuper()); 
	}


	/**
	 * @deprecated No se usa.
	 * Autoriza la modificaci�n de un procedimiento
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaModificarProcedimiento(Long idProcedimiento) throws SecurityException {
		return (getAccesoManager().tieneAccesoProcedimiento(idProcedimiento)); 
	}      


	/**
	 * Crea o actualiza un Procedimiento.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param	procedimiento	Indica el procedimiento a guardar.
	 * 
	 * @param	idUA	Identificador de la unidad administrativa a la que es asiganda el nuevo procedimiento.
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 */
	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) {

		Session session = getSession();
		try {
			Date FechaActualizacionBD = new Date();
			if (procedimiento.getId() == null) {
				if (procedimiento.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
					throw new SecurityException("No puede crear un procedimiento público");
				}

			} else {
				if (!getAccesoManager().tieneAccesoProcedimiento( procedimiento.getId())) {
					throw new SecurityException("No tiene acceso al procedimiento");
				}

				ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(procedimiento.getId());
				FechaActualizacionBD = procedimientoBD.getFechaActualizacion();
				this.indexBorraProcedimiento(procedimientoBD);
			}

			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
			    throw new SecurityException("No tiene acceso a la unidad");
			}

			/* Se alimenta la fecha de actualización de forma automática si no se ha introducido dato*/                      
			if (procedimiento.getFechaActualizacion() == null || DateUtils.fechasIguales(FechaActualizacionBD, procedimiento.getFechaActualizacion())) {
			    procedimiento.setFechaActualizacion( new Date() );
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
	 * @return Devuelve <code>List</code> de todos los procedimientos
	 */
	public List listarProcedimientos() {

		//agarcia: antes era @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}". Pero este m�todo debe ser unchecked para permitir accesos via WS 
		Session session = getSession();
		try {
			Criteria criteri = session.createCriteria(ProcedimientoLocal.class);

			//criteri.setFetchMode("traducciones", FetchMode.EAGER);
			criteri.setCacheable(true);
			List procedimientosValidos= new ArrayList<ProcedimientoLocal>();
			List procedimientos = criteri.list();
			for (int i = 0 ; i < procedimientos.size() ; i++) {
				ProcedimientoLocal procedimiento = (ProcedimientoLocal) procedimientos.get(i);
				
				if (!procedimiento.getTraduccionMap().isEmpty()) {
					procedimientosValidos.add(procedimiento);
					Hibernate.initialize(procedimiento.getMaterias());
				}
			}

			//Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
			Collections.sort( procedimientosValidos, new ProcedimientoLocal() );

			return procedimientosValidos;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 *  @deprecated Se usa desde API v1
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
				if(!pl.getTraduccionMap().isEmpty() && this.publico(pl)) {
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
	 * @param id	Identificador del procedimiento.
	 * @return Devuelve <code>ProcedimientoLocal</code> solicitado.
	 */
	public ProcedimientoLocal obtenerProcedimiento(Long id) {

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

		//Esto no está funcionando bien...
		//----------------------------------------------------------------------
		//ArrayList listaOrdenada = new ArrayList(procedimiento.getDocumentos());
		//Comparator comp = new DocsProcedimientoComparator();
	  	//Collections.sort(listaOrdenada, comp);
        //----------------------------------------------------------------------
    	
    	//Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
    	List procs = new ArrayList(0);
        for (Documento documento : procedimiento.getDocumentos()) {
            if (documento != null) {
                procs.add(documento);
            }
        }
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
    public ProcedimientoLocal obtenerProcedimientoNewBack(Long id) {
    	
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
    			    Hibernate.initialize(procedimiento.getOrganResolutori().getHijos());
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
        List procs = new ArrayList(0);
        for (Documento documento : procedimiento.getDocumentos()) {
        	if (documento != null) {
        		procs.add(documento);
        	}
        }
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

	  	// Eliminamos los nulls del ArrayList de la lista de trámites
	  	List<Tramite> listaTramites = new ArrayList<Tramite>(0);
	  	for (Tramite tramite : procedimiento.getTramites()) {
	  	    if (tramite != null) {
	  	        listaTramites.add(tramite);
	  	    }
	  	}
	  	procedimiento.setTramites(listaTramites);

	  	return procedimiento;
    }


	/**
	 * Valida si existe un tramite de inicio distinto a tramiteId para el procedimiento Local procId.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idProcedimiento	Identificador del procedimiento.
	 * @param idTramite	Identificador del trámite.
	 * @return Devuelve <code>true</code> si existe mas de un trámite con el mismo identificador asociado a un determinado procedimiento.
	 */
	public boolean existeOtroTramiteInicioProcedimiento(Long idProcedimiento, Long idTramite) {

		Session session = getSession();
		try {
			StringBuilder consulta = new StringBuilder(" select count(t.id) from Tramite t where t.procedimiento.id = :idProcedimiento and t.fase = :fase ");

			if (idTramite != null) {
			    consulta.append(" and t.id != :tramiteId"); 
			}

			Query query = session.createQuery(consulta.toString());
			query.setLong("fase", 1); // 1 = fase de inicio o inicializacion.
			query.setLong("idProcedimiento", idProcedimiento);

			if (idTramite != null) {
			    query.setLong("tramiteId", idTramite);
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
	 *  @deprecated Se usa desde el back antiguo 
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
	 * @deprecated No se usa
	 * Busca todas los Procedimientos que cumplen los criterios de busqueda del nuevo back (rolsacback).
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

			if (!StringUtils.isEmpty(uaQuery)) {
			    uaQuery = " and procedimiento.unidadAdministrativa.id in (" + uaQuery + ")";
			}

			String from = "from ProcedimientoLocal as procedimiento, ";
			String where = "";

			if (telematico != null && !telematico.equals("")) {
				if (telematico.equals("1")) {
					where += "and procedimiento.id in ";
				} else if (telematico.equals("0")) {
					where += "and procedimiento.id not in ";
				}

				where += "( select tra.procedimiento from Tramite as tra where tra.idTraTel is not null )";
			}

			if (en_plazo != null && !en_plazo.equals("")) {
				if (en_plazo.equals("1")) {
					where += "and procedimiento.id in ";
				} else if (en_plazo.equals("0")) {
					where += "and procedimiento.id not in ";
				}

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
	 * Busca todas los Procedimientos que cumplen los criterios de busqueda del nuevo back (rolsacback).
	 * @param criteria	Indica los criterios para la consulta.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda buscadorProcedimientos(BuscadorProcedimientoCriteria bc) {

		Session session = getSession();
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		try {
			PaginacionCriteria paginacion = bc.getPaginacion();

			StringBuilder consulta = new StringBuilder("select new ProcedimientoLocal(procedimiento.id, trad.nombre, procedimiento.validacion, procedimiento.fechaActualizacion, ");
			consulta.append("procedimiento.fechaCaducidad, procedimiento.fechaPublicacion, tradFam.nombre, index(trad), procedimiento.unidadAdministrativa ) ");
			consulta.append("from where");

			StringBuilder from = new StringBuilder(" from  ProcedimientoLocal as procedimiento,  procedimiento.traducciones as trad");
			from.append(", procedimiento.familia as fam, fam.traducciones as tradFam");

			StringBuilder where = new StringBuilder("where index(trad) = :idioma ");
			where.append("and procedimiento.unidadAdministrativa.id in (:UA) ");
			where.append("and index(tradFam) = :idioma ");


			if ( bc.getProcedimiento().getId() != null )
				where.append(" and procedimiento.id = :id ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getNombreProcedimiento() ) )
				where.append(" and upper(trad.nombre) like upper(:nombreProcedimiento) ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getIndicador() )  )
				where.append(" and procedimiento.indicador = :indicador ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getVentanillaUnica() )  )
				where.append(" and procedimiento.ventanillaUnica = :ventanillaUnica ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getTramite() ) )
				where.append(" and upper(procedimiento.tramite) like upper(:tramite) ");


			if ( bc.getProcedimiento().getFamilia().getId() != null )
				where.append(" and procedimiento.familia.id = :familia ");


			if ( bc.getProcedimiento().getIniciacion().getId() != null )
				where.append(" and procedimiento.iniciacion.id = :iniciacion ");


			if ( bc.getIdPublicoObjetivo() != null ) {

				where.append(" and pubsObj.id = :idPublicoObjetivo ");
				from.append(", procedimiento.publicosObjetivo as pubsObj");

			}


			if ( bc.getIdMateria() != null )
				where.append(" and procedimiento.id in ( select procsLocales.id from Materia as mat, mat.procedimientosLocales as procsLocales where mat.id = :idMateria ) ");


			if ( bc.getIdHechoVital() != null ) {

				where.append(" and hechosVit.hechoVital.id = :idHechoVital ");
				from.append(", procedimiento.hechosVitalesProcedimientos as hechosVit");

			}


			if ( bc.getTelematico() != null ) {

				if ( bc.getTelematico() )
					where.append(" and procedimiento.id in ");

				else if ( !bc.getTelematico() )
					where.append(" and procedimiento.id not in ");

				where.append(" ( select tra.procedimiento from Tramite as tra where tra.idTraTel is not null ) ");	

			}


			if ( bc.getEnPlazo() != null ) {

				if ( bc.getEnPlazo() )
					where.append(" and procedimiento.id in ");

				else if ( !bc.getEnPlazo() )
					where.append(" and procedimiento.id not in ");

				where.append(" ( select tra.procedimiento from Tramite as tra where tra.fase = 1 ");
				where.append("and (sysdate < tra.dataTancament or tra.dataTancament is null) ");
				where.append("and (sysdate > tra.dataInici or tra.dataInici is null) ) ");

			}


			if ( bc.getVisibilidad() == Validacion.PUBLICA ) {

				where.append(" and (sysdate < procedimiento.fechaCaducidad or procedimiento.fechaCaducidad is null) ");
				where.append(" and (sysdate > procedimiento.fechaPublicacion or procedimiento.fechaPublicacion is null) ");

			} else if ( bc.getVisibilidad() == Validacion.INTERNA ) {

				where.append(" and (sysdate > procedimiento.fechaCaducidad or sysdate < procedimiento.fechaPublicacion or procedimiento.validacion = 2 or procedimiento.validacion = 3) ");

			}


			Integer validacion = null;
			if ( userIsOper() && !userIsSuper() ) { //Filtrar por el acceso del usuario, tieneAccesoValidable

				where.append(" and procedimiento.validacion = :validacion");
				validacion = Validacion.INTERNA;

			} else if ( !userIsOper() ) {

				where.append(" and procedimiento.validacion = :validacion");
				validacion = Validacion.PUBLICA;

			}


			where.append("order by procedimiento.").append( paginacion.getPropiedadDeOrdenacion() );
			where.append(" ").append( paginacion.getCriterioOrdenacion() );

			String idUAs =  DelegateUtil.getUADelegate().obtenerCadenaFiltroUA( bc.getUnidadAdministrativa().getId(), bc.getUaHijas(), bc.getUaPropias() );
			String queryString = consulta.toString().replace("from", from.toString()).replace("where", where.toString());


			Query query = session.createQuery(queryString.toString().replace(":UA", idUAs));
			query.setParameter("idioma", bc.getLocale());


			if ( bc.getProcedimiento().getId() != null )
				query.setParameter("id", bc.getProcedimiento().getId());


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getNombreProcedimiento() ) )
				query.setParameter("nombreProcedimiento", "%" + bc.getProcedimiento().getNombreProcedimiento() + "%");


			if ( bc.getIdHechoVital() != null )
				query.setParameter("idHechoVital", bc.getIdHechoVital());


			if ( bc.getIdMateria() != null )
				query.setParameter("idMateria", bc.getIdMateria());


			if ( bc.getIdPublicoObjetivo() != null )
				query.setParameter("idPublicoObjetivo", bc.getIdPublicoObjetivo());


			if ( bc.getProcedimiento().getIniciacion().getId() != null )
				query.setParameter("iniciacion", bc.getProcedimiento().getIniciacion());


			if ( bc.getProcedimiento().getFamilia().getId() != null )
				query.setParameter("familia", bc.getProcedimiento().getFamilia().getId());


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getTramite() ) )
				query.setParameter("tramite", "%" + bc.getProcedimiento().getTramite() + "%");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getIndicador() ) )
				query.setParameter("indicador", Integer.parseInt( bc.getProcedimiento().getIndicador() ) );


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getVentanillaUnica() ) )
				query.setParameter("ventanillaUnica", Integer.parseInt( bc.getProcedimiento().getVentanillaUnica() ) );


			if ( validacion != null )
				query.setParameter("validacion", validacion);


			resultadoBusqueda.setTotalResultados( query.list().size() );

			resultadoBusqueda = PaginatedHibernateEJB.obtenerListadoPaginado(query, paginacion);


		} catch (HibernateException e) {

			throw new EJBException(e);

		} catch (DelegateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

		return resultadoBusqueda;

	}


	/**
	 * Construye el query de búsqueda multiidioma en todos los campos
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
	 * @deprecated Se usa desde la API v1
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
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param nombre	Indica el nombre del procedimiento a buscar. 
	 */
	public List buscarProcedimientosTexto(String nombre) {

		IndexerDelegate delegate = DelegateUtil.getIndexerDelegate();
		Long[] ids;

		try {

			ids = delegate.buscarIds(ProcedimientoLocal.class.getName(), nombre);

		} catch (DelegateException e) {

			log.error("Error buscando", e);
			ids = new Long[0];

		}

		if ( ids == null || ids.length == 0 )
			return Collections.EMPTY_LIST;

		Session session = getSession();
		try {

			Criteria criteria = session.createCriteria(ProcedimientoLocal.class);
			criteria.add( Expression.in("id", ids) );
			criteria.setFetchMode("traducciones", FetchMode.EAGER);
			List result = new ArrayList();

			Iterator iterator = criteria.list().iterator();
			while ( iterator.hasNext() ) {

				ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
				if ( publico(proc) )
					result.add(proc);

			}

			return result;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
	}


	/**
	 * Asigna un tramite existente al procedimiento.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param idTramite	Identificador del trámite.
	 * 
	 * @param idProcedimiento	Identificador del procedimiento.
	 */
	public void anyadirTramite(Long idTramite, Long idProcedimiento) {

		log.debug( "[anyadirTramite] tramiteId=" + idTramite +" procId=" + idProcedimiento );
		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoProcedimiento(idProcedimiento) )
				throw new SecurityException("No tiene acceso al procedimiento");

			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load( ProcedimientoLocal.class, idProcedimiento );
			Tramite tramite = (Tramite) session.load( Tramite.class, idTramite );

			log.debug( "tramite load=" + tramite );
			log.debug( "proc load=" + procedimiento );

			procedimiento.addTramite(tramite);
			session.flush();

		} catch (HibernateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

	}


	/**
	 * Elimina un tramite del procedimiento.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param idTramite	Identificador del trámite.
	 * 
	 * @param idProcedimiento	Identificador del procedimiento.
	 */
	public void eliminarTramite(Long idTramite, Long idProcedimiento) {

		Session session = getSession();
		try {

			if ( !getAccesoManager().tieneAccesoProcedimiento(idProcedimiento) )
				throw new SecurityException("No tiene acceso al procedimiento");

			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load( ProcedimientoLocal.class, idProcedimiento );
			Tramite tramite = (Tramite) session.load( Tramite.class, idTramite );
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
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * 
	 * @param id	Identificador del procedimiento.
	 */
	public void borrarProcedimiento(Long id) {

		Session session = getSession();
		try {

			if ( !getAccesoManager().tieneAccesoProcedimiento(id) )
				throw new SecurityException("No tiene acceso al procedimiento");

			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load( ProcedimientoLocal.class, id );
			procedimiento.getNormativas().clear();

			//Borram els documents directament amb query per evitar el problema del ordres.
			//S'ha llevat el cascade=delete de l'hbm.
			session.delete( "from Documento as doc where doc.procedimiento.id = ?", id, Hibernate.LONG );

			addOperacion(session, procedimiento, Auditoria.BORRAR);
			Historico historico = getHistorico( session, procedimiento );
			( (HistoricoProcedimiento) historico ).setProcedimiento(null);
			procedimiento.getUnidadAdministrativa().removeProcedimientoLocal(procedimiento);

			if ( procedimiento instanceof ProcedimientoRemoto ) {

				AdministracionRemota admin = ( (ProcedimientoRemoto) procedimiento).getAdministracionRemota();
				if ( admin != null )
					admin.removeProcedimientoRemoto( (ProcedimientoRemoto) procedimiento );

			} else {

				Actualizador.borrar( new ProcedimientoLocal(id) );

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
	 *  @deprecated Se usa desde el back antiguo
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
	 * @deprecated Se usa desde el back antiguo
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
	 *  @deprecated Se usa desde API v1 
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
		Hibernate.initialize( ua.getProcedimientos() );

		Iterator iterator = ua.getProcedimientos().iterator();
		while ( iterator.hasNext() ) {

			ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
			if ( publico(proc) )
				result.add(proc);

		}

		for ( int i = 0 ; i < ua.getHijos().size() ; i++ ) {

			UnidadAdministrativa uaHijo = (UnidadAdministrativa) ua.getHijos().get(i);
			result.addAll( procedimientosPublicosRecursivosUA(uaHijo) );

		}

		//Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
		Collections.sort( result, new ProcedimientoLocal() );

		return result;

	}


	/**
	 * Obtiene los ids de los procedimientos publicos de una unidad administrativa (PORMAD recuperacion de datos inicial)
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idUA	Identificador de la unidad administrativa.
	 * 
	 * @param codEstandarMateria	Código estándar materia.
	 * 
	 * @param codEstandarHechoVital	Código estándar hecho vital.
	 * 
	 * @return Devuelve <code>List<Long></code> de los identificadores de los procedimientos públicos de la unidad administrativa especificada.
	 */
	@SuppressWarnings("unchecked")
	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstandarMateria, String[] codEstandarHechoVital) {

		Session session = getSession();
		try {

			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idUA );
			Hibernate.initialize( unidadAdministrativa.getProcedimientos() );

			Set<ProcedimientoLocal> procs = unidadAdministrativa.getProcedimientos();
			List<Long> procsFinales = new ArrayList<Long>();
			for ( ProcedimientoLocal proc: procs ) {

				if ( publico(proc) ) {

					//Variable que indica si el procedimiento tiene alguna relacion
					boolean relacionada = false;

					//comprobamos materias
					Query queryMat = session.createQuery("select mat.codigoEstandar from ProcedimientoLocal p, p.materias as mat where p.id = :id");
					queryMat.setParameter( "id", proc.getId(), Hibernate.LONG );

					List<String> codigosMaterias = queryMat.list();

					//si el procedimiento está relacionado con alguna materia la marcamos
					for ( String codigoMat : codEstandarMateria ) {

						if ( relacionada = codigosMaterias.contains(codigoMat) )
							break;

					}

					//Si no tiene niguna relación con ninguna materia miramos si tiene ralación con algún hecho vital
					if ( !relacionada ) {

						Query queryHechos = session.createQuery("select hpv.hechoVital.codigoEstandar from ProcedimientoLocal p, p.hechosVitalesProcedimientos as hpv where p.id = :id");
						queryHechos.setParameter( "id", proc.getId(), Hibernate.LONG );

						List<String> codigosHechos = queryHechos.list();

						// si la ficha está relacionada con el hecho vital la marcamos
						for ( String codigoHev : codEstandarHechoVital ) {

							if ( relacionada = codigosHechos.contains(codigoHev) )
								break;

						}

					}

					if ( relacionada ) {
						/*	Hibernate.initialize(proc.getMaterias());
    					Hibernate.initialize(proc.getHechosVitalesProcedimientos());
                        Hibernate.initialize(proc.getDocumentos());
						 */	
						procsFinales.add( proc.getId() );

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
	 * Obtiene los procedimientos públicos de un Hecho Vital
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de un hecho vital
	 * 
	 * @return Devuelve <code>List</code> de los procedimientos con un determinado hecho vital asignado.
	 */
	public List listarProcedimientosPublicosHechoVital(Long id) {

		Session session = getSession();
		try {

			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			List result = new ArrayList();
			for ( int i = 0 ; i < hechoVital.getHechosVitalesProcedimientos().size() ; i++ ) {

				HechoVitalProcedimiento hechoVitalProcedimiento = (HechoVitalProcedimiento) hechoVital.getHechosVitalesProcedimientos().get(i);
				ProcedimientoLocal proc = hechoVitalProcedimiento.getProcedimiento();
				if ( publico(proc) )
					result.add(proc);

			}

			//Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo id)
			Collections.sort( result, new ProcedimientoLocal() );

			return result;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * @deprecated No se usa
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
				throw new SecurityException("Procedimiento no público.");
			}

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}


	/**
	 * Construye el query de búsqueda segun los par�metros
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


	/** 
	 * Valida si un procedimiento es público 
	 * 
	 * @param proc	Indica el procedimiento local a validar.
	 * 
	 * @return Devuelve <code>true</code> si la fecha de caducidad es posterior a la actual o es nula, 
	 * si la fecha de publicación es anterior a la actual o nula y si el campo validación contiene el valor Público.
	 **/
	protected boolean publico(ProcedimientoLocal proc) {

		final Date now = new Date();
		boolean noCaducado = ( proc.getFechaCaducidad() == null || proc.getFechaCaducidad().after(now) );
		boolean publicado = ( proc.getFechaPublicacion() == null || proc.getFechaPublicacion().before(now) );

		return visible(proc) && noCaducado && publicado;
	}


	/**
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * 
	 * Debemos incluir las materias y los hechos vitales, la unidad administrativa de la que depende y la familia.
	 * 
	 * Método válido para Procedimientos los 3 tipos:
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
		UnidadAdministrativa ua = proc.getUnidadAdministrativa();
		List listapadres = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa(ua.getId());

		if (proc.getMaterias() != null) {
			Iterator itmat = proc.getMaterias().iterator();
			while (itmat.hasNext()) {
				mat = (Materia) itmat.next();
				if (!lista_materias.containsKey(mat.getId()))
					lista_materias.put(mat.getId(), mat);
			}
		}

		if (proc.getHechosVitalesProcedimientos() != null) {
			Iterator itvital = proc.getHechosVitalesProcedimientos().iterator();
			while (itvital.hasNext()) {
				hvital = (HechoVitalProcedimiento) itvital.next();
				if (!lista_hechos.containsKey(hvital.getHechoVital().getId())) {
				    lista_hechos.put(hvital.getHechoVital().getId(), hvital.getHechoVital());
				}
			}
		}

		Iterator langs = proc.getLangs().iterator();
		while (langs.hasNext()) {
			idioma = (String) langs.next();
			txids = Catalogo.KEY_SEPARADOR;
			txtexto = " ";

			trafilter = new TraModelFilterObject();
			trafilter.setMaintitle(null); 

			// Obtenemos la UA con sus padres excepto el raiz
			if (ua != null) {
				txids = Catalogo.KEY_SEPARADOR;
				txtexto = " ";

				UnidadAdministrativa ua_padre = null;
				for (int x = 1; x < listapadres.size(); x++) {
					ua_padre = (UnidadAdministrativa) listapadres.get(x);
					txids += ua_padre.getId() + Catalogo.KEY_SEPARADOR;
					if (ua_padre.getTraduccion(idioma) != null) {
					    txtexto += ((TraduccionUA) ua_padre.getTraduccion(idioma)).getNombre() + " ";
					}
				}

				filter.setUo_id((txids.length() == 1) ? null : txids);
				trafilter.setUo_text((txtexto.length() == 1) ? null : txtexto);
			}

			// Obtenemos su Familia
			Familia fam = proc.getFamilia();
			if (fam != null) {
				filter.setFamilia_id(fam.getId());
				if (fam.getTraduccion(idioma) != null) {
				    trafilter.setFamilia_text(((TraduccionFamilia) fam.getTraduccion(idioma)).getNombre());
				}
			}

			// Obtenemos las materias y hechos vitales
			txids = Catalogo.KEY_SEPARADOR;
			txtexto = " ";

			Enumeration i = lista_materias.keys();

			while (i.hasMoreElements()) {
				Materia materia = (Materia) lista_materias.get(i.nextElement());
				// Anadir los ids (los de los hechos vitales no)
				txids += materia.getId() + Catalogo.KEY_SEPARADOR;
				if (materia.getTraduccion(idioma) != null) {
					txtexto += ((TraduccionMateria) materia.getTraduccion(idioma)).getNombre() + " ";
					txtexto += ((TraduccionMateria) materia.getTraduccion(idioma)).getDescripcion() + " ";
					txtexto += ((TraduccionMateria) materia.getTraduccion(idioma)).getPalabrasclave() + " ";
				}
			}

			i = lista_hechos.keys();
			HechoVital hechovital = null;

			while (i.hasMoreElements()) {
				hechovital = (HechoVital) lista_hechos.get(i.nextElement());
				if (hechovital.getTraduccion(idioma) != null) {
					txtexto += ((TraduccionHechoVital) hechovital.getTraduccion(idioma)).getNombre() + " ";
					txtexto += ((TraduccionHechoVital) hechovital.getTraduccion(idioma)).getDescripcion() + " ";
					txtexto += ((TraduccionHechoVital) hechovital.getTraduccion(idioma)).getPalabrasclave() + " ";
				}
			}

			filter.setMateria_id((txids.length() == 1) ? null : txids);
			trafilter.setMateria_text((txtexto.length() == 1) ? null : txtexto);

			filter.addTraduccion(idioma, trafilter);
		}

		close(session);
		return filter;
	}


	/**
	 * Añade los procedimientos al índice en todos los idiomas
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param proc	Indica un procedimiento.
	 */
	public void indexInsertaProcedimiento(ProcedimientoLocal proc, ModelFilterObject filter) {

		try {
			if (proc.getValidacion().equals(2)) {
			    return;
			}

			proc = obtenerProcedimientoNewBack(proc.getId());

			if (filter == null) {
                filter = obtenerFilterObject(proc);
            }

			IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();

			// Obtenemos los documentos del procedimiento, si tiene, para evitar tener que cargarlos en cada idioma
            List<Documento> listaDocumentos = obtenerListaDocumentos(proc);

			String tipo = tipoProcedimiento(proc, false); 
			Iterator iterator = proc.getLangs().iterator();
			while (iterator.hasNext()) {
				String idioma = (String) iterator.next();

				// Configuración del writer
                Directory directory = indexerDelegate.getHibernateDirectory(idioma);
                IndexWriter writer = new IndexWriter(directory, getAnalizador(idioma), false, MaxFieldLength.UNLIMITED);
                writer.setMergeFactor(20);
                writer.setMaxMergeDocs(Integer.MAX_VALUE);

				IndexObject io = new IndexObject();

				io = indexarTraducciones(proc, idioma, io, tipo);

				io = indexarContenidos(proc, io, tipo, filter);

				io = indexarContenidosLaterales(proc, idioma, io, filter);

				io = indexarContenidosDocumentos(proc, idioma, writer, io, listaDocumentos, tipo);

				if (io.getText().length() > 0) {
				    indexerDelegate.insertaObjeto(io, idioma, writer);
				}

				writer.close();
                directory.close();
			}

		} catch (Exception ex) {
			log.warn( "[indexInsertaProcedimiento:" + proc.getId() + "] No se ha podido indexar el procedimiento. " + ex.getMessage() );
		}
	}


	private List<Documento> obtenerListaDocumentos(ProcedimientoLocal proc) throws DelegateException {

        int listaSize = 0;
        if (proc.getDocumentos() != null) {
            listaSize = proc.getDocumentos().size();
        }
        List<Documento> listaDocumentos = new ArrayList<Documento>(listaSize);
        if (proc.getDocumentos() != null) {
            DocumentoDelegate documentoDelegate = DelegateUtil.getDocumentoDelegate();
            for (Documento documento : proc.getDocumentos()) {
                listaDocumentos.add(documentoDelegate.obtenerDocumento(documento.getId()));
            }
        }

        return listaDocumentos;
    }


	private IndexObject indexarContenidos(ProcedimientoLocal proc, IndexObject io, String tipo, ModelFilterObject filter) {

	    io.setId(tipo + "." + proc.getId());
        io.setClasificacion(tipo);
        io.setMicro(filter.getMicrosite_id());
        io.setUo(filter.getUo_id());
        io.setMateria(filter.getMateria_id());
        io.setFamilia(filter.getFamilia_id());
        io.setSeccion(filter.getSeccion_id());
        io.setCaducidad("");    
        io.setPublicacion("");
        io.setDescripcion("");

        if (proc.getFechaCaducidad() != null) {
            io.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaCaducidad()));
        }

        if (proc.getFechaPublicacion() != null) {
            io.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaPublicacion()));
        }

        return io;
	}


	private IndexObject indexarTraducciones(ProcedimientoLocal proc, String idioma, IndexObject io, String tipo) {

	    TraduccionProcedimientoLocal trad = ((TraduccionProcedimientoLocal) proc.getTraduccion(idioma));
        if (trad != null) {
            io.setTituloserviciomain(trad.getNombre());
            io.setUrl("/govern/sac/visor_proc.do?codi=" + proc.getId() + "&lang=" + idioma + "&coduo=" + proc.getUnidadAdministrativa().getId());

            // Si es externo ponemos su propia URL
            if (tipo.equals(Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO)) {
                io.setUrl(proc.getUrl());
            }

            if (trad.getNombre() != null) {
                io.setTitulo(trad.getNombre());
                io.addTextLine(trad.getNombre());

                if (trad.getResumen() != null) {
                    //if (trad.getResumen().length()>200) io.setDescripcion(trad.getResumen().substring(0,199)+"...");
                    //else io.setDescripcion(trad.getResumen());
                    io.setDescripcion(trad.getResumen());
                }
            }

            if (trad.getDestinatarios() != null) {
                io.addTextLine(trad.getDestinatarios());
            }

            if (trad.getLugar() != null) {
                io.addTextLine(trad.getLugar());
            }

            if (trad.getObservaciones() != null) {
                io.addTextLine(trad.getObservaciones());
            }

            if (trad.getPlazos() != null) {
                io.addTextLine(trad.getPlazos());
            }

            if (trad.getResolucion() != null) {
                io.addTextLine(trad.getResolucion());
            }

            if (trad.getNotificacion() != null) {
                io.addTextLine(trad.getNotificacion());
            }

            if (trad.getRecursos() != null) {
                // No está en el mantenimiento
                io.addTextLine(trad.getRecursos());
            }

            if (trad.getRequisitos() != null) {
                io.addTextLine(trad.getRequisitos());
            }

            if (trad.getSilencio() != null) {
                io.addTextLine(trad.getSilencio());
            }
        }

        return io;
	}


	private IndexObject indexarContenidosLaterales(ProcedimientoLocal proc, String idioma, IndexObject io, ModelFilterObject filter) {

	    io.addTextopcionalLine(filter.getTraduccion(idioma).getMateria_text());
        io.addTextopcionalLine(filter.getTraduccion(idioma).getSeccion_text());
        io.addTextopcionalLine(filter.getTraduccion(idioma).getUo_text());
        io.addTextopcionalLine(filter.getTraduccion(idioma).getFamilia_text());

        // Añadimos colecciones pero solo títulos como opcional
        if (proc.getTramites() != null) {
            Iterator iteradorTramites = proc.getTramites().iterator();

            while (iteradorTramites.hasNext()) {
                Tramite tra = (Tramite) iteradorTramites.next();
                if (tra.getTraduccion(idioma) != null) {
                    io.addTextopcionalLine(((TraduccionTramite) tra.getTraduccion(idioma)).getNombre());
                }
            }
        }

        if (proc.getNormativas() != null) {
            Iterator iteradorNormativas = proc.getNormativas().iterator();

            while (iteradorNormativas.hasNext()) {
                Object obj = iteradorNormativas.next();
                Normativa norm;
                if (obj instanceof NormativaLocal) {
                    norm = (NormativaLocal) obj;
                } else {
                    norm = (NormativaExterna) obj;
                }

                if (norm.getTraduccion(idioma) != null) {
                    io.addTextopcionalLine(((TraduccionNormativa) norm.getTraduccion(idioma)).getTitulo());
                }
            }
        }

        if (proc.getMaterias() != null) {
            Iterator iteradorMaterias = proc.getMaterias().iterator();

            while (iteradorMaterias.hasNext()) {
                Materia mat = (Materia) iteradorMaterias.next();
                if (mat.getTraduccion(idioma) != null) {
                    io.addTextopcionalLine(((TraduccionMateria) mat.getTraduccion(idioma)).getNombre());
                }
            }
        }

        return io;
	}


	private IndexObject indexarContenidosDocumentos(ProcedimientoLocal proc, String idioma, IndexWriter writer, IndexObject io, List<Documento> listaDocumentos, String tipo) throws DelegateException {

	    // Se añaden todos los documentos en todos los idiomas
        if (proc.getDocumentos() != null) {
            String tipoDoc = tipoProcedimiento(proc, true);

            IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();

            Iterator<Documento> iteradorDocumentos = listaDocumentos.iterator();
            while (iteradorDocumentos.hasNext()) {
                Documento documento = iteradorDocumentos.next();

                if (documento.getTraduccion(idioma) != null) {
                    io.addTextLine(((TraduccionDocumento) documento.getTraduccion(idioma)).getTitulo());
                    io.addTextLine(((TraduccionDocumento) documento.getTraduccion(idioma)).getDescripcion());
                }

                // Se crea la indexación del documento individual y se añade la información para la indexación de la ficha.
                IndexObject ioDoc = new IndexObject();
                String textDoc = null;

                //ioDoc.addArchivo((Archivo)documento.getArchivo());
                Archivo arch = new Archivo();
                if (documento.getTraduccion(idioma) != null) {
                    arch = (Archivo) ((TraduccionDocumento) documento.getTraduccion(idioma)).getArchivo();
                    ioDoc.addArchivo(arch);
                }

                textDoc = ioDoc.getText();
                if (textDoc != null && textDoc.length() > 0) {
                    if (documento.getTraduccion(idioma) != null) {                              

                        ioDoc.setId(tipoDoc + "." + documento.getId());
                        ioDoc.setClasificacion(tipo + "." + proc.getId());
                        ioDoc.setCaducidad("");
                        ioDoc.setPublicacion(""); 
                        ioDoc.setDescripcion("");
                        ioDoc.setUrl("/fitxer/get?codi=" + arch.getId());
                        ioDoc.setTituloserviciomain(io.getTitulo());
                        ioDoc.setTitulo(((TraduccionDocumento) documento.getTraduccion(idioma)).getTitulo() + ", (" + arch.getMime().toUpperCase() + ")");
                        ioDoc.setDescripcion(((TraduccionDocumento) documento.getTraduccion(idioma)).getDescripcion());
                        ioDoc.setText(textDoc);
                        ioDoc.addTextLine(((TraduccionDocumento) documento.getTraduccion(idioma)).getDescripcion());
                        ioDoc.addTextLine(arch.getNombre());

                        if (proc.getFechaCaducidad() != null) {
                            ioDoc.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaCaducidad()));
                        }

                        if (proc.getFechaPublicacion() != null) {
                            ioDoc.setPublicacion( new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaPublicacion()));
                        }

                        if (io.getUo() != null) {
                            ioDoc.setUo(io.getUo());
                        }

                        if (io.getMateria() != null) {
                            ioDoc.setMateria(io.getMateria());
                        }

                        if (io.getSeccion() != null) {
                            ioDoc.setSeccion( io.getSeccion() );
                        }

                        if (io.getFamilia() != null) {
                            ioDoc.setFamilia(io.getFamilia());
                        }

                        if (ioDoc.getText().length() > 0 || ioDoc.getTextopcional().length() > 0) {
                            indexerDelegate.insertaObjeto(ioDoc, idioma, writer);
                        }
                    }

                    io.addTextLine(textDoc);
                }
            }
        }

        return io;
	}


	/**
     * Elimina el procedimiento en el indice en todos los idiomas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @param procedimiento	Indica un procedimiento.
     */
    public void indexBorraProcedimiento(ProcedimientoLocal procedimiento)  {
    
    	try {
    	    IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
    		Iterator iterator = procedimiento.getLangs().iterator();
    		while (iterator.hasNext()) {
    			String idi = (String) iterator.next();
    			indexerDelegate.borrarObjeto(tipoProcedimiento(procedimiento, false) + "." + procedimiento.getId(), idi);
    			indexerDelegate.borrarObjetosDependientes(tipoProcedimiento(procedimiento, false) + "." + procedimiento.getId(), idi);
    		}
    
    	} catch (DelegateException ex) {
    		log.warn( "[indexBorraProcedimiento:" + procedimiento.getId() + "] No se ha podido borrar del indice el procedimiento. " + ex.getMessage() );
    	}
    }


	/**
	 * Actualiza el orden de los tramites 
	 *
	 * FIXME enric@dgtic: este metodo lo pondria en procedimientoFacadeEJB 
	 *  
	 * @param mapaIdOrdenTramite <String,String[]>
	 * eg. key= orden_doc396279
	 * 	   value={"1"}
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */

	public void actualizarOrdenTramites(ArrayList<Long> tramitesId) throws DelegateException {

		Session session = getSession();
		try {
		    for (Long idTramite : tramitesId) {
		        if (!getAccesoManager().tieneAccesoTramite(idTramite)) {
		            //Es necesario comprobar para cada trámite??
		            throw new SecurityException("No tiene acceso al tramite");
		        }
			}

			long orden = 0L;
			for (Long idTramite : tramitesId) {
				Tramite tramite = (Tramite) session.load(Tramite.class, idTramite);
				tramite.setOrden(orden);
				orden ++;
				session.save(tramite);
			}
			session.flush();

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 *  Obtiene el tipo de procedimiento 
	 * @param procedimiento	Indica el procedimiento local a analizar.
	 * @return Devuelve el tipo de procedimiento.
	 * */
	private String tipoProcedimiento (ProcedimientoLocal procedimiento, boolean doc) {

		String tipo = "";
		if (!doc) {
			if (procedimiento.getUrl() != null && procedimiento.getUrl().length() > 0) {
				tipo = Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO;
			} else if ((procedimiento.getVersion() == null && procedimiento.getTramite() == null) || (procedimiento.getVersion() == null && procedimiento.getTramite() != null && procedimiento.getTramite().length() == 0 )) {
			    tipo = Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO;
			} else {
				tipo = Catalogo.SRVC_PROCEDIMIENTOS_SISTRA;
			}

		} else {
			if (procedimiento.getUrl() != null && procedimiento.getUrl().length() > 0) {
				tipo = Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO_DOCUMENTOS;
			} else if (procedimiento.getVersion() == null && procedimiento.getTramite() == null) {
				tipo = Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO_DOCUMENTOS;
			} else {
				tipo = Catalogo.SRVC_PROCEDIMIENTOS_SISTRA_DOCUMENTOS;
			}
		}

		return tipo;
	}


	/** Clase que se utiliza para comparar de trámites. */
	class TramiteComparator implements Comparator {

	    public int compare(Object o1, Object o2) {
	        Long x1 = new Long(((Tramite) o1).getOrden());
			Long x2 = new Long(((Tramite) o2).getOrden());

			return x1.compareTo(x2);
		}

	}


	/**
	 * Buscamos el numero de procedimientos activos des de la fecha actual
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas.
	 * @param fechaCaducidad	Filtro que indica el rango temporal en el que se encuentra activo un  procedimiento.
	 * @return numero de Procedimientos activos
	 */
	public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) {

		Integer resultado = 0;
		Session session = getSession();
		try {
			Query query = null;
			if (listaUnidadAdministrativaId.size() > 0) {

				query = session.createQuery(
						" select count(*) from ProcedimientoLocal as prc " +
								" where prc.validacion = :validacion " +
								" and (prc.fechaCaducidad >= :fecha or prc.fechaCaducidad is null) " +
								" and (prc.fechaPublicacion <= :fecha or prc.fechaPublicacion is null) " +
						" and prc.unidadAdministrativa.id in (:lId) ");

				query.setInteger( "validacion", Validacion.PUBLICA );
				query.setDate( "fecha", fechaCaducidad );
				query.setParameterList( "lId", listaUnidadAdministrativaId, Hibernate.LONG );

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
	 * Buscamos el numero de procedimientos activos des de la fecha actual.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas.
	 * @param fechaCaducidad	Filtro que indica el rango temporal en el que se encuentra activo un  procedimiento.
	 * @return Devuelve el número de Procedimientos caducados
	 */
	public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad){

		Integer resultado = 0;		
		Session session = getSession();
		try {
			Query query = null;
			if (listaUnidadAdministrativaId.size() > 0) {

				query = session.createQuery(
						" select count(*) from ProcedimientoLocal as prc " +
								" where ( " +
								" 	( prc.validacion != :validacion ) " +
								" 	or ( prc.validacion = :validacion and prc.fechaCaducidad < :fecha ) " +
								" 	or ( prc.validacion = :validacion and prc.fechaCaducidad is null and prc.fechaPublicacion > :fecha ) " +
								" 	or ( prc.validacion = :validacion and prc.fechaCaducidad >= :fecha and prc.fechaPublicacion > :fecha ) " +
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


	/**
	 * @deprecated Se usa desde SincronizacionServicio.java
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


	private Analyzer getAnalizador(String idi) {

        Analyzer analyzer;

        if (idi.toLowerCase().equals("de")) {
            analyzer = new AlemanAnalyzer();
        } else if (idi.toLowerCase().equals("en")) {
            analyzer = new InglesAnalyzer();
        } else if (idi.toLowerCase().equals("ca")) {
            analyzer = new CatalanAnalyzer();
        } else {
            analyzer = new CastellanoAnalyzer();
        }

        return analyzer;
    }

}
