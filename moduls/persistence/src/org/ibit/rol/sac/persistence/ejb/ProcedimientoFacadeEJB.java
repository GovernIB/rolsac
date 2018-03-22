package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Procedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.IndexData;
import es.caib.solr.api.model.MultilangLiteral;
import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;



/**
 * SessionBean para mantener y consultar Procedimientos
 *
 *  TODO (enricjaen@dgtic) 03.03.2011
 *  Aquesta clase te mes de 1000 linias de codi i 47 procediments. 
 *  Te masses responsabilitats, que haurien de dividir-se. Per exemple: 
 *  - insertar, borrar procediment
 *  - buscar procediment
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
@SuppressWarnings("deprecation")
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
	 * Autoriza la creacion de un procedimiento
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaCrearProcedimiento(Integer validacionProcedimiento) throws SecurityException  {
		return !(validacionProcedimiento.equals(Validacion.PUBLICA) && !userIsSuper()); 
	}


	/**
	 * @deprecated No se usa.
	 * Autoriza la modificacion de un procedimiento
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
	 * @param procedimiento Indica el procedimiento a guardar.
	 * @param idUA Identificador de la unidad administrativa a la que es asiganda el nuevo procedimiento.
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 * @throws DelegateException 
	 */
	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) throws DelegateException {

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
				
				
			}

			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
			    throw new SecurityException("No tiene acceso a la unidad");
			}

			/* Se alimenta la fecha de actualización de forma automática si no se ha introducido dato*/                      
			if (procedimiento.getFechaActualizacion() == null 
					|| DateUtils.fechasIguales(FechaActualizacionBD, procedimiento.getFechaActualizacion())) {
			    procedimiento.setFechaActualizacion( new Date() );
			}

			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			procedimiento.setUnidadAdministrativa(unidad);

			//#399 Check valores SIA
			checkValoresSIA(procedimiento);
			
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
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);
		    SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(), SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, procedimiento);
			
			return procedimiento.getId();

		} catch (Exception he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
	
	/** 
	 * Check si alguna normativa esta derogada.
	 * 
	 * @ejb.interface-method	  
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean isNormativaDerogada(Long id)   throws DelegateException {
		Session session = getSession();
		boolean resultado = false;
		try {
			ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(id);
			for(Normativa normativa : procedimientoBD.getNormativas()) {
				if (!normativa.isVigente()) {
					resultado = true; break;
				}
			}
		} catch (Exception he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);			
		}
		return resultado;
	}
	
	
	/** 
	 * Check si alguna normativa esta como no valida..
	 * 
	 * @ejb.interface-method	  
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean isNormativaValidas(Long id)   throws DelegateException {
		Session session = getSession();
		boolean resultado = true;
		try {
			ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(id);
			for(Normativa normativa : procedimientoBD.getNormativas()) {
				if (!normativa.isDatosValidos()) {
					resultado = false;
					break;
				}
			}
		} catch (Exception he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);			
		}
		return resultado;
	}
	
	/**
	 * Checkear valores de SIA.
	 * @param procedimiento
	 * @throws Exception
	 */
	private void checkValoresSIA(ProcedimientoLocal procedimiento) throws Exception {
		
		final boolean isNuloCodSia = StringUtils.isEmpty(procedimiento.getCodigoSIA());			
		final boolean isNuloEstSia = StringUtils.isEmpty(procedimiento.getEstadoSIA());
		final boolean isNuloFecSia = (procedimiento.getFechaSIA() == null);
		
		// Verifica que todos estan rellenados o todos estan nulos
		final boolean correcto = (isNuloCodSia && isNuloEstSia && isNuloFecSia) || (!isNuloCodSia && !isNuloEstSia && !isNuloFecSia);
				
		if (!correcto) {		
			String messageError = "Verificacion valores SIA: alguno de los atributos no son nulos y otros si. Procedimiento: "
					+ procedimiento.getId()
					+ " - Código SIA: "
					+ procedimiento.getCodigoSIA()
					+ " - Estado SIA: "
					+ procedimiento.getEstadoSIA()
					+ " - Fecha SIA: "
					+ procedimiento.getFechaSIA();
			log.error("Error: " + messageError);
			throw new Exception(
					messageError);
		}
		
	}

	
	// TODO amartin: ¿intentar unificar los dos métodos de guardado en uno?
	/**
	 * Crea o actualiza un Procedimiento.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param procedimiento Indica el procedimiento a guardar.
	 * @param idUA Identificador de la unidad administrativa a la que es asiganda el nuevo procedimiento.
	 * @param listaTramitesParaBorrar
	 * @param listaIdsTramitesParaActualizar
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 */
	public Long grabarProcedimientoConTramites(ProcedimientoLocal procedimiento, Long idUA, 
			List listaTramitesParaBorrar, List listaIdsTramitesParaActualizar) {
		
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
				
				
			}

			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
			    throw new SecurityException("No tiene acceso a la unidad");
			}

			/* Se alimenta la fecha de actualización de forma automática si no se ha introducido dato*/                      
			if (procedimiento.getFechaActualizacion() == null 
					|| DateUtils.fechasIguales(FechaActualizacionBD, procedimiento.getFechaActualizacion())) {
			    procedimiento.setFechaActualizacion( new Date() );
			}

			UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
			procedimiento.setUnidadAdministrativa(unidad);
			
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			
			if (listaTramitesParaBorrar != null && listaIdsTramitesParaActualizar != null) {
				
				// Eliminar los que no han sido quitados de la lista
		        for (Tramite tramite : (List<Tramite>)listaTramitesParaBorrar) {
		        
		            	// Si se va a borrar un trámite de iniciación con el estado del procedimiento "público", 
		        		// lo impedimos y generamos una excepción.
		            	if (Procedimiento.ESTADO_PUBLICACION_PUBLICA.equals(procedimiento.getValidacion()) && tramite.getFase() == Tramite.INICIACION)
		            		throw new EJBException("No se puede borrar el trámite de iniciación cuando el estado del procedimiento es público");
		        	
		        	DelegateUtil.getProcedimientoDelegate().eliminarTramite(tramite.getId(), procedimiento.getId());
		            tramiteDelegate.borrarTramite(tramite.getId(), procedimiento.getId());
		        
		        }
	
				// Actualizar orden y añadir los trámites 
				if (listaIdsTramitesParaActualizar.size() > 0) {
				    actualizarOrdenTramites(new ArrayList<Long>(listaIdsTramitesParaActualizar));
				}
			
			}

			//#399 Check valores SIA
			checkValoresSIA(procedimiento);
			
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
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(), SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, procedimiento);
			
			return procedimiento.getId();

		} catch (Exception he) {
			
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

		//agarcia: antes era @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}". Pero este metodo debe ser unchecked para permitir accesos via WS 
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
						continue; //por algun motivo, en ocasiones los documentos en la coleccion son nulos
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

				Hibernate.initialize(procedimiento.getUnidadAdministrativa().getUnidadesNormativas());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa().getEdificios());

				if (procedimiento.getUnidadAdministrativa().getUnidadesNormativas() != null) {
					for (UnidadNormativa n : procedimiento.getUnidadAdministrativa().getUnidadesNormativas()) {
						Map<String, Traduccion> mapaTraduccions = n.getNormativa().getTraduccionMap();
						Set<String> idiomes = mapaTraduccions.keySet();
						for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
							TraduccionNormativa trad = (TraduccionNormativa)n.getNormativa().getTraduccion(i.next());
							if (trad != null) {
								Hibernate.initialize(trad.getArchivo());
							}
						}
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
	  	
		/* TODO: error de compilacion tras el merge con 177
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
    			Hibernate.initialize(procedimiento.getServicioResponsable());
    			
    			if (procedimiento.getOrganResolutori() != null) {
    			    Hibernate.initialize(procedimiento.getOrganResolutori().getHijos());
    			}
				
				if (procedimiento.getServicioResponsable() != null) {
    			    Hibernate.initialize(procedimiento.getServicioResponsable().getHijos());
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
	 * Comprueba si un procedimiento tiene como estado de publicación org.ibit.rol.sac.model.Procedimiento.ESTADO_PUBLICACION_PUBLICA.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public boolean isProcedimientoConEstadoPublicacionPublica(Long idProcedimiento) {
    	
    	Session session = getSession();
    	ProcedimientoLocal procedimiento = null;
    	
    	try {
    		
    		procedimiento = (ProcedimientoLocal)session.load(ProcedimientoLocal.class, idProcedimiento);
    		
    		return Procedimiento.ESTADO_PUBLICACION_PUBLICA.equals(procedimiento.getValidacion());

    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}
    	
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
	 * Busca todas los Procedimientos que cumplen los criterios de busqueda
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
	 * @param bc	Indica los criterios para la consulta.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda buscadorProcedimientos(BuscadorProcedimientoCriteria bc) {

		Session session = getSession();
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		try {
			PaginacionCriteria paginacion = bc.getPaginacion();

			StringBuilder from = new StringBuilder(" from  ProcedimientoLocal as procedimiento,  procedimiento.traducciones as trad");
			StringBuilder where = new StringBuilder("where index(trad) = :idioma ");
			StringBuilder consulta;
			if (bc.getSoloId()) { 
				if ("familia".equals(paginacion.getPropiedadDeOrdenacion())) {
					consulta = new StringBuilder("select distinct procedimiento.id, tradFam.nombre from where");
				} else {
					consulta = new StringBuilder("select distinct procedimiento.id, procedimiento."+paginacion.getPropiedadDeOrdenacion()+" from where");
				} 
				
				if(bc.getProcedimiento().getFamilia().getId() == null || bc.getProcedimiento().getFamilia().getId() != -1){
					where.append("and index(tradFam) = :idioma ");
					from.append(", procedimiento.familia as fam, fam.traducciones as tradFam");
				}
			} else {
				consulta = new StringBuilder("select new ProcedimientoLocal(procedimiento.id, trad.nombre, procedimiento.validacion, procedimiento.fechaActualizacion, ");
				if(bc.getProcedimiento().getFamilia().getId() == null || bc.getProcedimiento().getFamilia().getId() != -1){
					consulta.append("procedimiento.fechaCaducidad, procedimiento.fechaPublicacion, tradFam.nombre, index(trad), procedimiento.unidadAdministrativa ) ");
					where.append("and index(tradFam) = :idioma ");
					from.append(", procedimiento.familia as fam, fam.traducciones as tradFam");
				}else{				//Para poder buscar proc sin familia
					//TODO en el constructor s esta pasando familia con el nombre del proc
					consulta.append("procedimiento.fechaCaducidad, procedimiento.fechaPublicacion,trad.nombre , index(trad), procedimiento.unidadAdministrativa ) ");
					
				}
				
				consulta.append("from where");
			}
			where.append("and procedimiento.unidadAdministrativa.id in (:UA) ");
			
			if ( bc.getProcedimiento().getId() != null )
				where.append(" and procedimiento.id = :id ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getNombreProcedimiento() ) )
				where.append(" and upper(trad.nombre) like upper(:nombreProcedimiento) ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getIndicador() )  )
				where.append(" and procedimiento.indicador = :indicador ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getVentanillaUnica() )  )
				where.append(" and procedimiento.ventanillaUnica = :ventanillaUnica ");


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getTramite() ) ) {
				//where.append(" and procedimiento.tramites.id IN (:tramite) ");
				where.append(" and procedimiento.id IN ( select tra.procedimiento from Tramite as tra where tra.id = :tramite ) ");	
			}


			if ( bc.getProcedimiento().getFamilia() != null && bc.getProcedimiento().getFamilia().getId() != null ){
				if(bc.getProcedimiento().getFamilia().getId() == -1){
					where.append(" and procedimiento.familia is null ");

				}else{
					
					where.append(" and procedimiento.familia.id = :familia ");
				}
			}


			if ( bc.getProcedimiento().getIniciacion() != null && bc.getProcedimiento().getIniciacion().getId() != null ){
				if(bc.getProcedimiento().getIniciacion().getId() ==-1){
					where.append(" and procedimiento.iniciacion is null ");
				}else{					
					where.append(" and procedimiento.iniciacion.id = :iniciacion ");
				}
			}


			if ( bc.getIdPublicoObjetivo() != null ) {
				if(bc.getIdPublicoObjetivo() ==-1){//Seleccionado cap
					where.append(" and size(procedimiento.publicosObjetivo)=0 ");
				}else{					
					where.append(" and pubsObj.id = :idPublicoObjetivo ");
					from.append(", procedimiento.publicosObjetivo as pubsObj");
				}

			}


			if ( bc.getIdMateria() != null ){
				if(bc.getIdMateria() == -1){
					where.append(" and procedimiento.id  not in ( select procsLocales.id from Materia as mat, mat.procedimientosLocales as procsLocales where mat.id is not null ) ");
					
				}else{					
					where.append(" and procedimiento.id in ( select procsLocales.id from Materia as mat, mat.procedimientosLocales as procsLocales where mat.id = :idMateria ) ");
				}
			}

	
			if(bc.getProcedimiento().getSilencio() != null && bc.getProcedimiento().getSilencio().getId() != null){
				if(bc.getProcedimiento().getSilencio().getId() ==-1){
					where.append(" and procedimiento.silencio is null ");
				}else{					
					where.append(" and procedimiento.silencio.id = :idSilencio ");
				}
			}

			if(StringUtils.isNotEmpty( bc.getProcedimiento().getCodigoSIA() )  ){
				
				where.append(" and procedimiento.codigoSIA like upper(:codSIA) ");
			}
			
			if( StringUtils.isNotEmpty( bc.getProcedimiento().getEstadoSIA() )){
				if(bc.getProcedimiento().getEstadoSIA().equals("-1")){					
					where.append(" and procedimiento.estadoSIA is null ");
				}else if(bc.getProcedimiento().getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)){
					where.append(" and procedimiento.estadoSIA not is null and procedimiento.estadoSIA <> :estadoSIA ");
				}else{
					where.append(" and procedimiento.estadoSIA = :estadoSIA ");
				}
			}

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

				where.append(" ( select tra.procedimiento from Tramite as tra where tra.procedimiento is not null ");
				where.append("and (sysdate < tra.dataTancament or tra.dataTancament is null) ");
				where.append("and (sysdate > tra.dataInici or tra.dataInici is null) ) ");

			}


			if ( bc.getVisibilidad() == Validacion.PUBLICA ) {
				where.append(" and (sysdate < procedimiento.fechaCaducidad or procedimiento.fechaCaducidad is null) ");
				where.append(" and (sysdate > procedimiento.fechaPublicacion or procedimiento.fechaPublicacion is null) ");
				where.append(" and (procedimiento.validacion <> "+Validacion.INTERNA+" and procedimiento.validacion <> "+Validacion.RESERVA+") "); //#355 devolvia no visibles
			} else if ( bc.getVisibilidad() == Validacion.INTERNA ) {
				where.append(" and (sysdate > procedimiento.fechaCaducidad or sysdate < procedimiento.fechaPublicacion or procedimiento.validacion = "+Validacion.INTERNA+" or procedimiento.validacion = "+Validacion.RESERVA+") ");
			}


			Integer validacion = null;
			if ( userIsOper() && !userIsSuper() ) { //Filtrar por el acceso del usuario, tieneAccesoValidable

				where.append(" and procedimiento.validacion = :validacion");
				validacion = Validacion.INTERNA;

			} else if ( !userIsOper() ) {

				where.append(" and procedimiento.validacion = :validacion");
				validacion = Validacion.PUBLICA;

			}

			if (paginacion.getPropiedadDeOrdenacion().equals("familia")) {
				where.append("order by tradFam.nombre");
			} else {
				where.append("order by procedimiento.").append( paginacion.getPropiedadDeOrdenacion());
			}
			where.append(" ").append( paginacion.getCriterioOrdenacion() );
			//Se incluye la siguiente linea para que siempre ordene luego por el id.
			if (!"id".equals(paginacion.getPropiedadDeOrdenacion())) { 
				where.append(" , procedimiento.id ").append(" ASC");
			} 
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


			if ( bc.getIdMateria() != null && bc.getIdMateria() != -1)
				query.setParameter("idMateria", bc.getIdMateria());


			if ( bc.getIdPublicoObjetivo() != null && bc.getIdPublicoObjetivo() != -1){				
				query.setParameter("idPublicoObjetivo", bc.getIdPublicoObjetivo());
			}


			if ( bc.getProcedimiento().getIniciacion().getId() != null && bc.getProcedimiento().getIniciacion().getId() != -1)
				query.setParameter("iniciacion", bc.getProcedimiento().getIniciacion());


			if ( bc.getProcedimiento().getFamilia().getId() != null && bc.getProcedimiento().getFamilia().getId() != -1)
				query.setParameter("familia", bc.getProcedimiento().getFamilia().getId());


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getTramite() ) )
				query.setParameter("tramite", bc.getProcedimiento().getTramite());


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getIndicador() ) )
				query.setParameter("indicador", Integer.parseInt( bc.getProcedimiento().getIndicador() ) );


			if ( StringUtils.isNotEmpty( bc.getProcedimiento().getVentanillaUnica() ) )
				query.setParameter("ventanillaUnica", Integer.parseInt( bc.getProcedimiento().getVentanillaUnica() ) );


			if ( validacion != null )
				query.setParameter("validacion", validacion);
			
			if (bc.getProcedimiento().getSilencio().getId() != null && bc.getProcedimiento().getSilencio().getId() != -1){
		
				query.setParameter("idSilencio", bc.getProcedimiento().getSilencio().getId());
			}
			
			if (StringUtils.isNotEmpty( bc.getProcedimiento().getCodigoSIA() )  ){
				
				query.setParameter("codSIA", "%" + bc.getProcedimiento().getCodigoSIA()+ "%");
			}

			if (StringUtils.isNotEmpty( bc.getProcedimiento().getEstadoSIA() )  && !bc.getProcedimiento().getEstadoSIA().equals("-1")){
				//No sea baja(reactivación,modificacion,alta)
				if(bc.getProcedimiento().getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)){
					query.setParameter("estadoSIA",SiaUtils.ESTADO_BAJA);
				}else{					
					query.setParameter("estadoSIA",bc.getProcedimiento().getEstadoSIA());
				}
			}

			

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
	 * Asigna un tramite existente al procedimiento.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param idTramite	Identificador del trámite.
	 * 
	 * @param idProcedimiento	Identificador del procedimiento.
	 * @throws DelegateException 
	 */
	public void anyadirTramite(Long idTramite, Long idProcedimiento) throws DelegateException {

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
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);			
			SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(), SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);
			
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
	 * @throws DelegateException 
	 */
	public void eliminarTramite(Long idTramite, Long idProcedimiento) throws DelegateException {

		Session session = getSession();
		
		try {

			if ( !getAccesoManager().tieneAccesoProcedimiento(idProcedimiento) )
				throw new SecurityException("No tiene acceso al procedimiento");

			ProcedimientoLocal procedimiento = (ProcedimientoLocal)session.load( ProcedimientoLocal.class, idProcedimiento );
			Tramite tramite = (Tramite)session.load( Tramite.class, idTramite );
			
			if (isProcedimientoConEstadoPublicacionPublica(procedimiento.getId())) {
				// Comprobamos si el trámite es de iniciación. Si lo es, impedimos su borrado.
				if (tramite.getFase() == Tramite.INICIACION)
					throw new IllegalStateException("No se puede borrar el trámite de iniciación cuando el estado del procedimiento es público");
			}
			
			procedimiento.removeTramite(tramite);
			session.flush();
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);
			SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(), SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);
			
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
	 * @throws DelegateException 
	 */
	public void borrarProcedimiento(Long id) throws DelegateException {

		String idSia;
		Session session = getSession();
		try {

			if ( !getAccesoManager().tieneAccesoProcedimiento(id) )
				throw new SecurityException("No tiene acceso al procedimiento");

			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load( ProcedimientoLocal.class, id );
			idSia = procedimiento.getCodigoSIA();
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
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, id, true);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, id, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO, idSia, procedimiento);
			
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
			
			List result = new ArrayList();
			for (Iterator iter = unidadAdministrativa.getProcedimientos().iterator(); iter.hasNext();) {
				ProcedimientoLocal procedimiento = (ProcedimientoLocal) iter.next();
				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
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
	 * Consulta toda la informacion de un procedimiento
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
					if (tramite == null) continue;
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
	 * Construye el query de búsqueda segun los parametros
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
	

	private List<Documento> obtenerListaDocumentos(ProcedimientoLocal proc) throws DelegateException {

        int listaSize = 0;
        if (proc.getDocumentos() != null) {
            listaSize = proc.getDocumentos().size();
        }
        List<Documento> listaDocumentos = new ArrayList<Documento>(listaSize);
        if (proc.getDocumentos() != null) {
            DocumentoDelegate documentoDelegate = DelegateUtil.getDocumentoDelegate();
            for (Documento documento : proc.getDocumentos()) {
            	if (documento == null) continue;
                listaDocumentos.add(documentoDelegate.obtenerDocumento(documento.getId()));
            }
        }

        return listaDocumentos;
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
	

	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
	 * @param solrIndexer
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		return indexarSolr(solrIndexer, solrPendiente.getIdElemento(), EnumCategoria.fromString(solrPendiente.getTipo()));
	}
	
	
	/**
	 * Obtiene un procedimiento Local.
	 * @ejb.interface-method
 	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del procedimiento.
	 * @return Devuelve <code>ProcedimientoLocal</code> solicitado.
	 */
	public ProcedimientoLocal obtenerProcedimientoParaSolr(Long id, Session iSession) {

		final Session session;
		if (iSession == null) {
			session = getSession();
		} else {
			session = iSession;
		}
		ProcedimientoLocal procedimiento = null;
		try {
			procedimiento = (ProcedimientoLocal) session.get(ProcedimientoLocal.class, id);
			if (procedimiento != null) {

				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				if (procedimiento.getUnidadAdministrativa() != null) {
					Hibernate.initialize(procedimiento.getUnidadAdministrativa().getHijos());
					Hibernate.initialize(procedimiento.getUnidadAdministrativa().getUnidadesNormativas());
				}
				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getFamilia());
				Hibernate.initialize(procedimiento.getServicioResponsable());
				Hibernate.initialize(procedimiento.getDocumentos());
			} 

		} catch (HibernateException he) {
			log.error("Error obteniendo el procedimiento con id " + id, he);
		} finally {
			if (iSession == null) {
				close(session);
			}
		}
		
		return procedimiento;
	}
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
	 * @param solrIndexer
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("FichafacadeEJB.indexarSolr. idElemento:" + idElemento +" categoria:"+categoria);
		
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final ProcedimientoLocal procedimiento = obtenerProcedimientoParaSolr(idElemento, null);
			if (procedimiento == null) {
				return new SolrPendienteResultado(false, "Error obteniendo la id del procedimiento");
			}
			
			boolean isIndexable = IndexacionUtil.isIndexable(procedimiento);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria de tipo ficha.
			final IndexData indexData = new IndexData();
			indexData.setCategoria(categoria);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());
			indexData.setCategoriaRaiz(EnumCategoria.ROLSAC_PROCEDIMIENTO);
			indexData.setElementoIdRaiz(idElemento.toString());
			
			//Iteramos las traducciones
			final Map<String, Traduccion> traducciones = procedimiento.getTraduccionMap();
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral searchText = new MultilangLiteral();
			final MultilangLiteral searchTextOptional = new MultilangLiteral();	
			final List<EnumIdiomas> idiomas = new ArrayList<EnumIdiomas>();
			
			final String nomUnidadAministrativa;
			if (procedimiento.getUnidadAdministrativa() == null) {
				nomUnidadAministrativa = "";
			} else {
				nomUnidadAministrativa = procedimiento.getUnidadAdministrativa().getNombre();
			}
			
			//Recorremos las traducciones
			for (String keyIdioma : traducciones.keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionProcedimiento traduccion = (TraduccionProcedimiento)traducciones.get(keyIdioma);
				
				if (traduccion != null && enumIdioma != null) {
					
					if ((traduccion.getNombre() == null || traduccion.getNombre().isEmpty())) {
						continue;
					}
					
					//Anyadimos idioma al enumerado.
					idiomas.add(enumIdioma);
					
					//Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search text.
					titulo.addIdioma(enumIdioma, traduccion.getNombre());
			    	descripcion.addIdioma(enumIdioma, traduccion.getResumen());
			    	searchText.addIdioma(enumIdioma, traduccion.getNombre()  + " "+ traduccion.getResumen());
			    	
			    	final StringBuffer textoOptional = new StringBuffer();
					
			    	//materia
			    	for(Materia materia : procedimiento.getMaterias()) {
			    		TraduccionMateria traduccionMateria = (TraduccionMateria) materia.getTraduccion(keyIdioma);
			    		if (traduccionMateria != null) {
							textoOptional.append(" ");
							textoOptional.append(traduccionMateria.getNombre());
							textoOptional.append(" ");
							textoOptional.append(traduccionMateria.getDescripcion());
							textoOptional.append(" ");
							textoOptional.append(traduccionMateria.getPalabrasclave());
			    		}
					}
			    	
			    	//Servicio Responsable
			    	if (procedimiento.getServicioResponsable() != null) {
		    			TraduccionUA unidadAdm = (TraduccionUA) procedimiento.getServicioResponsable().getTraduccion(keyIdioma);
						if (unidadAdm != null) {
							textoOptional.append(" ");
							textoOptional.append(unidadAdm.getNombre());
						}			    		
			    	}
					
					//Publico objetivo, para extraer el nombre del publico objetivo
					String nombrePubObjetivo = "persones";
					String idPublicoObjetivo = "200";
					for( PublicoObjetivo publicoObjectivo :  procedimiento.getPublicosObjetivo()) {
						TraduccionPublicoObjetivo traduccionPO = (TraduccionPublicoObjetivo) publicoObjectivo.getTraduccion(keyIdioma);
						if (traduccionPO != null) {
							nombrePubObjetivo = traduccionPO.getTitulo().toLowerCase();
							idPublicoObjetivo = publicoObjectivo.getId().toString();
							break; //Con encontrar uno nos basta
						}
					}
					
					//UO
					if (procedimiento.getUnidadAdministrativa() != null && procedimiento.getUnidadAdministrativa().getTraduccion(keyIdioma) != null) {
						TraduccionUA unidadAdm = (TraduccionUA) procedimiento.getUnidadAdministrativa().getTraduccion(keyIdioma);
						if (unidadAdm != null) {
							textoOptional.append(" ");
							textoOptional.append(unidadAdm.getNombre());
						}
					} 
					
					//Nombre familia
					textoOptional.append(" ");
					textoOptional.append(procedimiento.getNombreFamilia());
					
					//Normativa asociadas
					for(Normativa normativa : procedimiento.getNormativas()) {
						final TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativa.getTraduccion(keyIdioma);
						if (traduccionNormativa != null) {
							textoOptional.append(traduccionNormativa.getTitulo());
							textoOptional.append(" ");
						}
					}
			    	searchTextOptional.addIdioma(enumIdioma, traduccion.getResultat() +" " +traduccion.getObservaciones() + " " + textoOptional.toString());
			    	urls.addIdioma(enumIdioma, "/seucaib/"+keyIdioma+"/"+idPublicoObjetivo+"/"+nombrePubObjetivo+"/tramites/tramite/"+procedimiento.getId());
				}
			}
			
			//Seteamos datos multidioma.
			indexData.setTitulo(titulo);
			indexData.setDescripcion(descripcion);
			indexData.setUrl(urls);
			indexData.setSearchText(searchText);
			indexData.setSearchTextOptional(searchTextOptional);
			indexData.setIdiomas(idiomas);
			
			//Datos IDs materias.
			final List<String> materiasId = new ArrayList<String>();		
			for(Materia materia : procedimiento.getMaterias()) {
				materiasId.add(materia.getId().toString());
			}
			indexData.setMateriaId(materiasId);
			
			//Datos IDs publico Objetivos.
	    	final List<String> publicoObjetivoId = new ArrayList<String>();		
			for( PublicoObjetivo publicoObjectivo :  procedimiento.getPublicosObjetivo()) {
				publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);
			
			//Datos IDs de familia.
			if (procedimiento.getFamilia() != null) {
				indexData.setFamiliaId(procedimiento.getFamilia().getId().toString());
			}
						
			//Fechas
			indexData.setFechaActualizacion(procedimiento.getFechaActualizacion());
			indexData.setFechaPublicacion(procedimiento.getFechaPublicacion());
			indexData.setFechaCaducidad(procedimiento.getFechaCaducidad());
			indexData.setInterno(false);
			
			//UA
			PathUO pathUO = IndexacionUtil.calcularPathUO(procedimiento.getUnidadAdministrativa());
			if (pathUO == null) {
				return new SolrPendienteResultado(true, "No se puede indexar: no cuelga de UA visible");
			}
			indexData.getUos().add(pathUO);
			
			final boolean telematico = IndexacionUtil.isTelematicoProcedimiento(procedimiento);
			indexData.setTelematico(telematico);
			
			final Tramite tramite = IndexacionUtil.getTramiteInicio(procedimiento);
			if (tramite != null) {
				indexData.setFechaPlazoIni(tramite.getDataInici());
				indexData.setFechaPlazoFin(tramite.getDataTancament());
			}
			
			solrIndexer.indexarContenido(indexData);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en procedimientoFacade intentando indexar. idElemento:" + idElemento +" categoria:"+categoria, exception);
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
	 * @param solrIndexer
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		try {
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(), EnumCategoria.ROLSAC_PROCEDIMIENTO);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en procedimientoFacade intentando desindexar.", exception);			
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}

	/**
	 *	Devuelve una lista con los ids de los procedimientos
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> buscarIdsProcedimientos()  {
		Session session = getSession();
	
		try {

    		StringBuilder consulta = new StringBuilder("select proc.id from ProcedimientoLocal as proc ");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setCacheable(true);

    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}
		
	}
	
	/**
	 *	Devuelve una lista con los procedimientos que tienen una normativa
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> listarProcedimientosNormativa(Long id){
		Session session = getSession();
		try {
			List result = new ArrayList();
			Normativa normativa = (Normativa) session.load(Normativa.class, id);
			Hibernate.initialize(normativa.getProcedimientos());
			for (Iterator iter = normativa.getProcedimientos().iterator(); iter.hasNext();) {
				ProcedimientoLocal procedimiento = (ProcedimientoLocal) iter.next();
				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
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
	 *	Actualiza el procedimiento pero no sus relaciones
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public void actualizarProcedimiento(ProcedimientoLocal proc){
		Session session = getSession();
		try {
					
			//#399 Check valores SIA
			checkValoresSIA(proc);
			
			session.update(proc);
			session.flush();
			
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
	}
	
	
	/**
	 *	Obtiene los procedimientos que se han alterado por el tiempo su estado SIA.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> getProcedimientosEstadoSIAAlterado(){
		
		Session session = getSession();
		try {
			StringBuffer hql= new StringBuffer();
	
			hql.append(" select pro.id from ProcedimientoLocal pro");
			hql.append(" where ");
			//Procedimientos caducados son con estado SIA de alta y fechaCaducidad pasada.
			hql.append(" (   pro.estadoSIA is not null ");
			hql.append(" AND pro.estadoSIA like 'A'"); 
			hql.append(" AND pro.fechaCaducidad is not null ");
			hql.append(" AND pro.fechaCaducidad < SYSDATE ) ");
			
			hql.append(" OR ");
			//Procedimientos activos sin estado o de baja y cuya fecha de caducidad es futura.
			hql.append(" (   (pro.estadoSIA is null     OR   (pro.estadoSIA is NOT NULL AND pro.estadoSIA like 'B')) "); 
			hql.append(" AND pro.fechaPublicacion IS NOT NULL ");
			hql.append(" AND pro.fechaPublicacion <= SYSDATE ");
			hql.append("  ) ");
			
			return session.createQuery(hql.toString()).list();
			
			
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
	}
	
	
	/**
	 *	Obtiene los procedimientos según el organo resolutorio.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> listarProcedimientosOrganoResolutori(Long idOrganResolutori) {
		Session session = getSession();
		try {
			StringBuffer hql= new StringBuffer();
	
			hql.append(" select pro.id from ProcedimientoLocal pro");
			hql.append(" where pro.organResolutori.id = " + idOrganResolutori);
			
			return session.createQuery(hql.toString()).list();
			
			
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
	}
	
	
	/**
	 *	Reordena los documentos del procedimiento.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public void reordenarDocumentos(final Long idProcedimiento, final List<Long> idDocumentos) {
		Session session = getSession(); //session.beginTransaction()
		try {
			//Paso 1. Obtenemos el procedimiento.
			ProcedimientoLocal procedimiento = obtenerProcedimientoParaSolr(idProcedimiento, session);
			
			//Paso 2. Obtener los procedimientos que han sido borrados.
			long maxOrden = 0;
			final List<Documento> docsParaBorrar = new ArrayList<Documento>();
			if (procedimiento.getDocumentos() != null) {
				for(Documento doc : procedimiento.getDocumentos()) {
					if (doc != null && doc.getId() != null) {
						if (doc.getOrden() >= maxOrden) {
							maxOrden = doc.getOrden() + 1;
						}
						
						if (!idDocumentos.contains(doc.getId())) {
							docsParaBorrar.add(doc);  //Lo añadimos en la lista.
						}
						
						//Paso 2.1. Comprobamos que tiene permisos para editar los documentos.
						if (!getAccesoManager().tieneAccesoDocumento(doc.getId())) {
							throw new SecurityException("No tiene acceso al documento");
						}
					}
				}
			}
			
			//Paso 3. Los borramos tanto en BBDD como en la relacion.
			for(Documento docParaBorrar : docsParaBorrar) {
				session.delete(docParaBorrar);    //Borramos el objeto
				procedimiento.getDocumentos().remove(docParaBorrar); //Borramos la relacion
			}
			
			//Paso 4. Reordenar.
			for(int i = 0 ; i < idDocumentos.size(); i++) {
				int orden = i; //Empezará en el 0 el orden.
				Documento documento = (Documento) session.get(Documento.class, idDocumentos.get(i));
				documento.setOrden(orden + maxOrden);
				session.update(documento); 
				
				/*for(int j = 0 ; j < procedimiento.getDocumentos().size(); j++) {
					Documento doc = procedimiento.getDocumentos().get(j);
					if (doc != null && doc.getId() != null && doc.getId().compareTo(idDocumentos.get(i)) == 0) {
						//doc.setOrden(orden);
						procedimiento.getDocumentos().set(j, documento);
					}
				}*/
			}
			session.flush();
			
			//Paso 4.2 Reordenar.
			for(int i = 0 ; i < idDocumentos.size(); i++) {
				int orden = i; //Empezará en el 0 el orden.
				Documento documento = (Documento) session.get(Documento.class, idDocumentos.get(i));
				documento.setOrden(orden);
				session.update(documento); 
			}
			session.flush();
			
			//Paso 5.Llamamos al addOperacion
			addOperacion(session, procedimiento, Auditoria.MODIFICAR);
			
			//Paso 6. Obtenemos de nuevo el procedimiento (por si se cachea) y actualizamos la fecha de actualización.
			procedimiento = obtenerProcedimientoParaSolr(idProcedimiento, session);
			procedimiento.setFechaActualizacion(new Date());
			session.update(procedimiento);
			
			//Paso 7. Llamamos al actualizador.
			Actualizador.actualizar(procedimiento);
			
			//Paso 8. Actualizamos en solr y sia.
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, idProcedimiento, false);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, idProcedimiento, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, procedimiento);
			
			//Paso 9. Flush.
			session.flush();
			
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
}
