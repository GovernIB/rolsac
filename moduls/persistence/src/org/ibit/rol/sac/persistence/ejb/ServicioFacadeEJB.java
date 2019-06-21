package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.DocumentoServicio;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalServicio;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoServicio;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.ServicioRemoto;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumentoServicio;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionServicio;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorServicioCriteria;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoServicioDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.util.POUtils;
import org.ibit.rol.sac.persistence.util.RolsacPropertiesUtil;
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
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;



/**
 * SessionBean para mantener y consultar Servicios
 *
 *  Aquesta clase te mes de 1000 linias de codi i 47 servicios. 
 *  Te masses responsabilitats, que haurien de dividir-se. Per exemple: 
 *  - insertar, borrar servei
 *  - buscar servei
 *  - ordenar serveis
 *  - actualitzar a altres administracions
 *  
 *
 * @ejb.bean
 *  name="sac/persistence/ServicioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ServicioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public abstract class ServicioFacadeEJB extends HibernateEJB  {

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
	 * Autoriza la creacion de un servicio
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Deprecated
	public boolean autorizaCrearServicio(Integer validacionServicio) throws SecurityException  {
		return !(validacionServicio.equals(Validacion.PUBLICA) && !userIsSuper()); 
	}


	/**
	 * @deprecated No se usa.
	 * Autoriza la modificacion de un servicio
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Deprecated
	public boolean autorizaModificarServicio(Long idServicio) throws SecurityException {
		return (getAccesoManager().tieneAccesoServicio(idServicio)); 
	}      

	/**
	 * Crea o actualiza un Servicio.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param servicio Indica el servicio a guardar.
	 * @param idUA Identificador de la unidad administrativa a la que es asiganda el nuevo servicio.
	 * 
	 * @return	Devuelve el identificador del servicio guardado.
	 * @throws DelegateException 
	 */
	public Long grabarServicio(Servicio servicio, Long idUA) throws DelegateException {

		Session session = getSession();
		
		try {
			
			Date FechaActualizacionBD = new Date();
			
			if (servicio.getId() == null) {
				
				if (servicio.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
					throw new SecurityException("No puede crear un servicio público");
				}

			} else {
				
				if (!getAccesoManager().tieneAccesoServicio( servicio.getId())) {
					throw new SecurityException("No tiene acceso al servicio");
				}

				Servicio servicioBD = obtenerServicioNewBack(servicio.getId());
				FechaActualizacionBD = servicioBD.getFechaActualizacion();
				
				
			}

			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
			    throw new SecurityException("No tiene acceso a la unidad");
			}

			/* Se alimenta la fecha de actualización de forma automática si no se ha introducido dato*/                      
			if (servicio.getFechaActualizacion() == null 
					|| DateUtils.fechasIguales(FechaActualizacionBD, servicio.getFechaActualizacion())) {
			    servicio.setFechaActualizacion( new Date() );
			}

			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			servicio.setOrganoInstructor(unidad);

			//#399 Check valores SIA
			checkValoresSIA(servicio);
			
			if (servicio.getId() == null) {
				
				session.save(servicio);
				addOperacion(session, servicio, Auditoria.INSERTAR);

			} else {
				
				session.update(servicio);
				addOperacion(session, servicio, Auditoria.MODIFICAR);
				
			}

			Hibernate.initialize(servicio.getMaterias());
			Hibernate.initialize(servicio.getHechosVitalesServicios());

			Actualizador.actualizar(servicio);
			
			session.flush();
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_SERVICIO, servicio.getId(), false);
		    SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_SERVICIO, servicio.getId(), SiaUtils.SIAPENDIENTE_SERVICIO_EXISTE, null, servicio);
			
			return servicio.getId();

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
			Servicio servicioBD = obtenerServicioNewBack(id);
			for(Normativa normativa : servicioBD.getNormativas()) {
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
	 * Checkear valores de SIA.
	 * @param servicio
	 * @throws Exception
	 */
	private void checkValoresSIA(Servicio servicio) throws Exception {
		
		final boolean isNuloCodSia = StringUtils.isEmpty(servicio.getCodigoSIA());			
		final boolean isNuloEstSia = StringUtils.isEmpty(servicio.getEstadoSIA());
		final boolean isNuloFecSia = (servicio.getFechaSIA() == null);
		
		// Verifica que todos estan rellenados o todos estan nulos
		final boolean correcto = (isNuloCodSia && isNuloEstSia && isNuloFecSia) || (!isNuloCodSia && !isNuloEstSia && !isNuloFecSia);
				
		if (!correcto) {		
			String messageError = "Verificacion valores SIA: alguno de los atributos no son nulos y otros si. Servicio: "
					+ servicio.getId()
					+ " - Código SIA: "
					+ servicio.getCodigoSIA()
					+ " - Estado SIA: "
					+ servicio.getEstadoSIA()
					+ " - Fecha SIA: "
					+ servicio.getFechaSIA();
			log.error("Error: " + messageError);
			throw new Exception(
					messageError);
		}
		
	}


	/**
	 * Obtiene un servicio Local.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del servicio.
	 * @return Devuelve <code>Servicio</code> solicitado.
	 */
	public Servicio obtenerServicio(Long id) {

		Session session = getSession();
		Servicio servicio = null;
		try {
			servicio = (Servicio) session.load(Servicio.class, id);
			if (visible(servicio)) {

				Hibernate.initialize(servicio.getDocumentos());
				for (DocumentoServicio d : servicio.getDocumentos()) {
					if (d == null) {
						continue; //por algun motivo, en ocasiones los documentos en la coleccion son nulos
					}

					Map<String, Traduccion> mapaTraduccions = d.getTraduccionMap();
					Set<String> idiomes = mapaTraduccions.keySet();
					for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
						TraduccionDocumentoServicio trad = (TraduccionDocumentoServicio)d.getTraduccion(i.next());
						if (trad != null) {
							Hibernate.initialize( trad.getArchivo() );
						}
					}
				}

				Hibernate.initialize(servicio.getMaterias());
				Hibernate.initialize(servicio.getPublicosObjetivo());

				Hibernate.initialize(servicio.getNormativas());
				for (Normativa n : servicio.getNormativas()) {
					Map<String, Traduccion> mapaTraduccions = n.getTraduccionMap();
					Set<String> idiomes = mapaTraduccions.keySet();
					for (Iterator<String> i = idiomes.iterator(); i.hasNext();) {
						TraduccionNormativa trad = (TraduccionNormativa)n.getTraduccion(i.next());
						if (trad != null) {
							Hibernate.initialize( trad.getArchivo() );
						}
					}
				}

				Hibernate.initialize(servicio.getOrganoInstructor());
				if (servicio.getOrganoInstructor() != null) {
					Hibernate.initialize(servicio.getOrganoInstructor().getHijos());
					Hibernate.initialize(servicio.getOrganoInstructor().getUnidadesNormativas());
					Hibernate.initialize(servicio.getOrganoInstructor().getEdificios());
					if (servicio.getOrganoInstructor().getUnidadesNormativas() != null) {
						for (UnidadNormativa n : servicio.getOrganoInstructor().getUnidadesNormativas()) {
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
				}
				
				Hibernate.initialize(servicio.getServicioResponsable());
				if (servicio.getServicioResponsable() != null) {
					Hibernate.initialize(servicio.getServicioResponsable().getHijos());
				}

				Hibernate.initialize(servicio.getHechosVitalesServicios());
			} else {
				throw new SecurityException("El servicio no es visible");
			}

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

		//Esto no está funcionando bien...
		//----------------------------------------------------------------------
		//ArrayList listaOrdenada = new ArrayList(servicio.getDocumentos());
		//Comparator comp = new DocsServicioComparator();
	  	//Collections.sort(listaOrdenada, comp);
        //----------------------------------------------------------------------
    	
    	//Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
    	Set<DocumentoServicio> docs = new HashSet<DocumentoServicio>(0);
        for (DocumentoServicio documento : servicio.getDocumentos()) {
            if (documento != null) {
                docs.add(documento);
            }
        }
        //Collections.sort(docs, new DocumentoServicio());
	  	servicio.setDocumentos(docs);
	  	
	  	//Ordenamos las materias por el campo id
	  	List<Materia> mats = new ArrayList<Materia>(servicio.getMaterias());
	  	Collections.sort(mats, new Materia());
	  	servicio.setMaterias(new HashSet<Materia>(mats));
	  	
	  	//Ordenamos las normativas por el campo id
	  	List<Normativa> norms = new ArrayList<Normativa>(servicio.getNormativas());
	  	Collections.sort(norms, new Normativa());
	  	servicio.setNormativas(new HashSet<Normativa>(norms));
	  	
		//Ordenamos los Hechos vitales servicios por el campo orden (si nulo, ordena por el campo id)
		List<HechoVitalServicio> hechosVitales = new ArrayList<HechoVitalServicio>(servicio.getHechosVitalesServicios());
		Collections.sort(hechosVitales);
		servicio.setHechosVitalesServicios(new HashSet<HechoVitalServicio>(hechosVitales));
		
		return servicio;
	}


	/**
	 * Obtiene un servicio Local.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public Servicio obtenerServicioNewBack(Long id) {
    	
    	Session session = getSession();
    	Servicio servicio = null;
    	try {
    		servicio = (Servicio) session.load(Servicio.class, id);
    		
    		if (visible(servicio)) {
    			Hibernate.initialize(servicio.getDocumentos());
    			Hibernate.initialize(servicio.getMaterias());
    			Hibernate.initialize(servicio.getPublicosObjetivo());
    			Hibernate.initialize(servicio.getNormativas());
    			Hibernate.initialize(servicio.getOrganoInstructor());
				
    			if (servicio.getOrganoInstructor() != null) {
					Hibernate.initialize(servicio.getOrganoInstructor().getHijos());
					Hibernate.initialize(servicio.getOrganoInstructor().getUnidadesNormativas());
					Hibernate.initialize(servicio.getOrganoInstructor().getEdificios());
					if (servicio.getOrganoInstructor().getUnidadesNormativas() != null) {
						for (UnidadNormativa n : servicio.getOrganoInstructor().getUnidadesNormativas()) {
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
				}
				
				Hibernate.initialize(servicio.getServicioResponsable());
				if (servicio.getServicioResponsable() != null) {
					Hibernate.initialize(servicio.getServicioResponsable().getHijos());
				}
    			

    			Hibernate.initialize(servicio.getHechosVitalesServicios());
    			
    		} else {
    			throw new SecurityException("El servicio no es visible");
    		}

    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}

    	// Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
        Set<DocumentoServicio> docsServicio = new HashSet<DocumentoServicio>(0);
        for (DocumentoServicio documento : servicio.getDocumentos()) {
        	if (documento != null) {
        		docsServicio.add(documento);
        	}
        }
        //Collections.sort(docsServicio, new DocumentoServicio());
        servicio.setDocumentos(docsServicio);

	    // Ordenamos las materias por el campo id
	  	List<Materia> mats = new ArrayList<Materia>(servicio.getMaterias());
	  	Collections.sort(mats, new Materia());
	  	servicio.setMaterias(new HashSet<Materia>(mats));

	  	//Ordenamos las normativas por el campo id
	  	List<Normativa> norms = new ArrayList<Normativa>(servicio.getNormativas());
	  	Collections.sort(norms, new Normativa());
	  	servicio.setNormativas(new HashSet<Normativa>(norms));

	    //Ordenamos los Hechos vitales servicios por el campo orden (si nulo, ordena por el campo id)
	  	List<HechoVitalServicio> hechosVitales = new ArrayList<HechoVitalServicio>(servicio.getHechosVitalesServicios());
	  	Collections.sort(hechosVitales);
	  	servicio.setHechosVitalesServicios(new HashSet<HechoVitalServicio>(hechosVitales));

	  	return servicio;
    }
    
    /**
	 * Comprueba si un servicio tiene como estado de publicación org.ibit.rol.sac.model.Servicio.ESTADO_PUBLICACION_PUBLICA.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public boolean isServicioConEstadoPublicacionPublica(Long idServicio) {
    	
    	Session session = getSession();
    	Servicio servicio = null;
    	
    	try {
    		
    		servicio = (Servicio)session.load(Servicio.class, idServicio);
    		
    		return Servicio.ESTADO_PUBLICACION_PUBLICA.equals(servicio.getValidacion());

    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}
    	
    }


	/**
	 *  @deprecated Se usa desde el back antiguo 
	 * Busca todas los Servicios que cumplen los criterios de busqueda
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public List buscarServicios(Map<String, Object> parametros, Map traduccion) {

		Session session = getSession();
		try {
			if (!userIsOper()) {
				parametros.put("validacion", Validacion.PUBLICA);
			}

			List params = new ArrayList();
			String sQuery = populateQuery(parametros, traduccion, params);

			// Eliminado "left join fetch" por problemas en el cache de traducciones.
			Query query = session.createQuery("select servicio from Servicio as servicio " +
					", servicio.traducciones as trad " + sQuery);
			for (int i = 0; i < params.size(); i++) {
				String o = (String)params.get(i);
				query.setString(i, o);
			}

			List<Servicio> servicios = query.list();

			if (!userIsOper()) {
				//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
				Collections.sort(servicios, new Servicio());
				return servicios;
			} else {
				List serviciosAcceso = new ArrayList();
				Usuario usuario = getUsuario(session);
				for (int i = 0; i < servicios.size(); i++) {
					Servicio servicio =  servicios.get(i);
					if(tieneAcceso(usuario, servicio)){
						serviciosAcceso.add(servicio);
					}
				}
				//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
				Collections.sort(serviciosAcceso, new Servicio());
				return serviciosAcceso;
			}
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * @deprecated No se usa
	 * Busca todas los Servicios que cumplen los criterios de busqueda del nuevo back (rolsacback).
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public ResultadoBusqueda buscadorServicios(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible, String en_plazo, String telematico) {

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
			    uaQuery = " and servicio.organoInstructor.id in (" + uaQuery + ")";
			}

			String from = "from Servicio as servicio, ";
			String where = "";

			if (telematico != null && (telematico.equals("1") || telematico.equals("0")) ) {
				where += " and servicio.telematico = " + ApiRestUtils.intToBool(telematico) + " ";
			}

			if (en_plazo != null && !en_plazo.equals("")) {
				if (en_plazo.equals("1")) {
					where += "and servicio.id in ";
				} else if (en_plazo.equals("0")) {
					where += "and servicio.id not in ";
				}

				where += "( select tra.servicio from Tramite as tra where tra.fase = 1 ";
				where += "and ( " + DateUtils.stringFechaAhoraBBDD() + " < tra.dataTancament or tra.dataTancament is null) ";
				where += "and ( " + DateUtils.stringFechaAhoraBBDD() + " > tra.dataInici or tra.dataInici is null) ) ";
			}

			if (materia != null) {
				where += " and servicio.id in ( select procsLocales.id " +
						"from Materia as mat, mat.serviciosLocales as procsLocales " +
						"where mat.id = " + materia + ")";
			}

			if (fetVital != null) {
				from += "servicio.hechosVitalesServicios as hechosVit, ";
				where += " and hechosVit.hechoVital.id = " + fetVital;
			}

			if (publicObjectiu != null) {
				from += "servicio.publicosObjetivo as pubsObj, ";
				where += " and pubsObj.id = " + publicObjectiu;
			}

			if (visible == 1) {
				where += " and ( " + DateUtils.stringFechaAhoraBBDD() + " < servicio.fechaDespublicacion or servicio.fechaDespublicacion is null) ";
				where += " and ( " + DateUtils.stringFechaAhoraBBDD() + " > servicio.fechaPublicacion or servicio.fechaPublicacion is null) ";
			} else if (visible == 2) {
				where += " and ( " + DateUtils.stringFechaAhoraBBDD() + " > servicio.fechaDespublicacion or " + DateUtils.stringFechaAhoraBBDD() + " < servicio.fechaPublicacion or servicio.validacion = 2 or servicio.validacion = 3) ";
			}

			if ( userIsOper() ) {
				//Filtrar por el acceso del usuario

				//tieneAccesoValidable
				if (!userIsSuper()) {
					where += " and servicio.validacion = " + Validacion.INTERNA;
				}

				// tieneAcceso
				if (!userIsSystem()) {

					if ( StringUtils.isEmpty(uaQuery) ) { //Se está buscando en todas las unidades orgánicas            	
						uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(null, true, true);
						if ( !StringUtils.isEmpty(uaQuery) ) {        	
							uaQuery = " and servicio.organoInstructor.id in (" + uaQuery + ")";
						} else {
							//Esto significa que el usuario no tiene ninguna unidad administrativa configurada, y 
							//no es system. Por lo que en realidad no hace falta ejecutar nada, sino más bien devolver
							//un resultado vacío

							//uaQuery = " and servicio.unidadAdministrativa.id in (-1)";
							ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();			
							resultadoBusqueda.setTotalResultados(0);
							resultadoBusqueda.setListaResultados(new ArrayList<Servicio>());

							return resultadoBusqueda;			            	
						}

					}
				}

			}

			where += " and index(tradFam) = 'ca' ";
			String select = "select new Servicio(servicio.id, trad.nombre, servicio.validacion, " +
					"servicio.fechaActualizacion, servicio.fechaDespublicacion, servicio.fechaPublicacion, " +
					"tradFam.nombre, index(trad), servicio.unidadAdministrativa ) ";

			String selectCount = "select count(*) ";

			String restoQuery = " servicio.traducciones as trad, servicio.familia as fam, fam.traducciones as tradFam " + i18nQuery + uaQuery + where;
			String orderBy = " order by servicio." + parametros.get("ordreCamp") + " " + parametros.get("ordreTipus");

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
	 * Busca todas los Servicios que cumplen los criterios de busqueda del nuevo back (rolsacback).
	 * @param bc	Indica los criterios para la consulta.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda buscadorServicios(BuscadorServicioCriteria bc) {

		Session session = getSession();
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		try {
			PaginacionCriteria paginacion = bc.getPaginacion();

			StringBuilder from = new StringBuilder(" from  Servicio as servicio,  servicio.traducciones as trad");
			StringBuilder where = new StringBuilder("where index(trad) = :idioma ");
			StringBuilder consulta;
			if (bc.getSoloId()) { 
				if ("familia".equals(paginacion.getPropiedadDeOrdenacion())) {
					consulta = new StringBuilder("select distinct servicio.id, tradFam.nombre from where");
				} else {
					consulta = new StringBuilder("select distinct servicio.id, servicio."+paginacion.getPropiedadDeOrdenacion()+" from where");
				} 
				
			} else {
				//consulta = new StringBuilder("select new Servicio(servicio.id, trad.nombre, servicio.validacion, servicio.fechaActualizacion, ");
				//consulta.append("servicio.fechaDespublicacion, servicio.fechaPublicacion, index(trad), servicio.servicioResponsable ) ");
				consulta = new StringBuilder("select new Servicio(servicio.id, trad.nombre, servicio.validacion, servicio.fechaActualizacion, servicio.fechaDespublicacion, servicio.fechaPublicacion, index(trad), servicio.nombreResponsable ) ");
				consulta.append("from where");
			}
			where.append("and servicio.organoInstructor.id in (:UA) ");
			
			if ( bc.getServicio() != null &&  bc.getServicio().getId() != null )
				where.append(" and servicio.id = :id ");


			if ( bc.getServicio() != null && StringUtils.isNotEmpty( bc.getServicio().getNombreServicio() ) ) {
				where.append(" and upper(trad.nombre) like upper(:nombreServicio) ");
			}

			if ( bc.getIdPublicoObjetivo() != null ) {
				if(bc.getIdPublicoObjetivo() ==-1){//Seleccionado cap
					where.append(" and size(servicio.publicosObjetivo)=0 ");
				}else{					
					where.append(" and pubsObj.id = :idPublicoObjetivo ");
					from.append(", servicio.publicosObjetivo as pubsObj");
				}

			}


			if ( bc.getIdMateria() != null ){
				if(bc.getIdMateria() == -1){
					where.append(" and servicio.id  not in ( select servs.id from Materia as mat, mat.servicios as servs where mat.id is not null ) ");
					
				}else{					
					where.append(" and servicio.id in ( select servs.id from Materia as mat, mat.servicios as servs where mat.id = :idMateria ) ");
				}
			}

			if(bc.getServicio() != null && StringUtils.isNotEmpty( bc.getServicio().getCodigoSIA() )  ){
				
				where.append(" and servicio.codigoSIA like upper(:codSIA) ");
			}
			
			if( bc.getServicio() != null && StringUtils.isNotEmpty( bc.getServicio().getEstadoSIA() )){
				if(bc.getServicio().getEstadoSIA().equals("-1")){					
					where.append(" and servicio.estadoSIA is null ");
				}else if(bc.getServicio().getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)){
					where.append(" and servicio.estadoSIA not is null and servicio.estadoSIA <> :estadoSIA ");
				}else{
					where.append(" and servicio.estadoSIA = :estadoSIA ");
				}
			}

			if ( bc.getIdHechoVital() != null ) {

				where.append(" and hechosVit.hechoVital.id = :idHechoVital ");
				from.append(", servicio.hechosVitalesServicios as hechosVit");

			}


			if ( bc.getTelematico() != null ) {
				String telematico = bc.getTelematico()?"1":"0";
				where.append(" and servicio.telematico = " + ApiRestUtils.intToBool(telematico) + " ");	
			}


			if ( bc.getEnPlazo() != null ) {

				if ( bc.getEnPlazo() )
					where.append(" and servicio.id in ");

				else if ( !bc.getEnPlazo() )
					where.append(" and servicio.id not in ");

				where.append(" ( select tra.servicio from Tramite as tra where tra.servicio is not null ");
				where.append("and ( " + DateUtils.stringFechaAhoraBBDD() + " < tra.dataTancament or tra.dataTancament is null) ");
				where.append("and ( " + DateUtils.stringFechaAhoraBBDD() + " > tra.dataInici or tra.dataInici is null) ) ");

			}


			if ( bc.getVisibilidad() == Validacion.PUBLICA ) {
				where.append(" and ( " + DateUtils.stringFechaAhoraBBDD() + " < servicio.fechaDespublicacion or servicio.fechaDespublicacion is null) ");
				where.append(" and ( " + DateUtils.stringFechaAhoraBBDD() + " > servicio.fechaPublicacion or servicio.fechaPublicacion is null) ");
				where.append(" and (servicio.validacion <> "+Validacion.INTERNA+" and servicio.validacion <> "+Validacion.RESERVA+") "); //#355 devolvia no visibles
			} else if ( bc.getVisibilidad() == Validacion.INTERNA ) {
				where.append(" and ( " + DateUtils.stringFechaAhoraBBDD() + " > servicio.fechaDespublicacion or " + DateUtils.stringFechaAhoraBBDD() + " < servicio.fechaPublicacion or servicio.validacion = "+Validacion.INTERNA+" or servicio.validacion = "+Validacion.RESERVA+") ");
			}


			Integer validacion = null;
			if ( userIsOper() && !userIsSuper() ) { //Filtrar por el acceso del usuario, tieneAccesoValidable

				where.append(" and servicio.validacion = :validacion");
				validacion = Validacion.INTERNA;

			} else if ( !userIsOper() ) {

				where.append(" and servicio.validacion = :validacion");
				validacion = Validacion.PUBLICA;

			}

			if (paginacion.getPropiedadDeOrdenacion().equals("familia")) {
				where.append("order by tradFam.nombre");
			} else {
				where.append("order by servicio.").append( paginacion.getPropiedadDeOrdenacion());
			}
			where.append(" ").append( paginacion.getCriterioOrdenacion() );
			//Se incluye la siguiente linea para que siempre ordene luego por el id.
			if (!"id".equals(paginacion.getPropiedadDeOrdenacion())) { 
				where.append(" , servicio.id ").append(" ASC");
			} 
			
			String idUAs =  DelegateUtil.getUADelegate().obtenerCadenaFiltroUA( bc.getUnidadAdministrativa().getId(), bc.getUaHijas(), bc.getUaPropias() );
			String queryString = consulta.toString().replace("from", from.toString()).replace("where", where.toString());


			Query query = session.createQuery(queryString.toString().replace(":UA", idUAs));
			query.setParameter("idioma", bc.getLocale());


			if ( bc.getServicio() != null && bc.getServicio().getId() != null )
				query.setParameter("id", bc.getServicio().getId());


			if ( bc.getServicio() != null && StringUtils.isNotEmpty( bc.getServicio().getNombreServicio() ) )
				query.setParameter("nombreServicio", "%" + bc.getServicio().getNombreServicio() + "%");


			if ( bc.getIdHechoVital() != null )
				query.setParameter("idHechoVital", bc.getIdHechoVital());


			if ( bc.getIdMateria() != null && bc.getIdMateria() != -1)
				query.setParameter("idMateria", bc.getIdMateria());


			if ( bc.getIdPublicoObjetivo() != null && bc.getIdPublicoObjetivo() != -1){				
				query.setParameter("idPublicoObjetivo", bc.getIdPublicoObjetivo());
			}

			if ( validacion != null )
				query.setParameter("validacion", validacion);
			
			if (bc.getServicio() != null && StringUtils.isNotEmpty( bc.getServicio().getCodigoSIA() )  ){
				query.setParameter("codSIA", "%" + bc.getServicio().getCodigoSIA()+ "%");
			}

			if (bc.getServicio() != null && StringUtils.isNotEmpty( bc.getServicio().getEstadoSIA() )  && !bc.getServicio().getEstadoSIA().equals("-1")){
				//No sea baja(reactivación,modificacion,alta)
				if(bc.getServicio().getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)){
					query.setParameter("estadoSIA",SiaUtils.ESTADO_BAJA);
				}else{					
					query.setParameter("estadoSIA",bc.getServicio().getEstadoSIA());
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
	 * Obtiene una lista de servicios de la misma Materia
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public List buscarServiciosMateria(Long id) {
		Session session = getSession();
		try {
			List result = new ArrayList();
			Materia materia = (Materia) session.load(Materia.class, id);
			Hibernate.initialize(materia.getServicios());
			for (Iterator iter = materia.getServicios().iterator(); iter.hasNext();) {
				Servicio servicio = (Servicio) iter.next();
				if (publico(servicio)) {
					result.add(servicio);
				}
			}
			//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
			Collections.sort(result, new Servicio());
			return result;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Borra un servicio.(PORMAD)
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * 
	 * @param id	Identificador del servicio.
	 * @throws DelegateException 
	 */
	public void borrarServicio(Long id) throws DelegateException {

		String idSia;
		Session session = getSession();
		try {

			if ( !getAccesoManager().tieneAccesoServicio(id) )
				throw new SecurityException("No tiene acceso al servicio");

			Servicio servicio = obtenerServicioParaSolr(id, session);
			
			idSia = servicio.getCodigoSIA();
			servicio.getNormativas().clear();

			//Borram els documents directament amb query per evitar el problema del ordres.
			//S'ha llevat el cascade=delete de l'hbm.
			//session.delete( "from DocumentoServicio as doc where doc.servicio.id = ?", id, Hibernate.LONG );

			addOperacion(session, servicio, Auditoria.BORRAR);
			Historico historico = getHistorico( session, servicio );
			( (HistoricoServicio) historico ).setServicio(null);
			servicio.getOrganoInstructor().removeServicio(servicio);

			if ( servicio instanceof ServicioRemoto ) { 

				AdministracionRemota admin = ( (ServicioRemoto) servicio).getAdministracionRemota();
				if ( admin != null ) {
					admin.removeServicioRemoto( (ServicioRemoto) servicio ); 
				}
			} else {

				Actualizador.borrar( new Servicio(id) );

			}

			/*
			// Borrar comentarios
			session.delete("from ComentarioServicio as cp where cp.servicio.id = ?", id, Hibernate.LONG);**/
			session.delete(servicio);
			session.flush();
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_SERVICIO, id, true);
			SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_SERVICIO, id, SiaUtils.SIAPENDIENTE_SERVICIO_BORRADO, idSia, servicio);
			
		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 *  @deprecated Se usa desde el back antiguo
	 * Obtiene los servicios de una unidad administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public List listarServiciosUA(Long id) {
		Session session = getSession();
		try {
			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			Hibernate.initialize(unidadAdministrativa.getServicios());
			
			List result = new ArrayList();
			for (Iterator iter = unidadAdministrativa.getServicios().iterator(); iter.hasNext();) {
				Servicio servicio = (Servicio) iter.next();
				Hibernate.initialize(servicio.getMaterias());
				Hibernate.initialize(servicio.getServicioResponsable());
				Hibernate.initialize(servicio.getOrganoInstructor());
				Hibernate.initialize(servicio.getHechosVitalesServicios());
				Hibernate.initialize(servicio.getPublicosObjetivo());
				Hibernate.initialize(servicio.getNormativas());
				if (publico(servicio)) {
					result.add(servicio);
				}
			}
			//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
			Collections.sort(result, new Servicio());
			
			return result;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	


	private List serviciosPublicosRecursivosUA(UnidadAdministrativa ua) throws HibernateException {

		List result = new ArrayList();
		Hibernate.initialize( ua.getServicios() );

		Iterator iterator = ua.getServicios().iterator();
		while ( iterator.hasNext() ) {

			Servicio proc = (Servicio) iterator.next();
			if ( publico(proc) )
				result.add(proc);

		}

		for ( int i = 0 ; i < ua.getHijos().size() ; i++ ) {

			UnidadAdministrativa uaHijo = ua.getHijos().get(i);
			result.addAll( serviciosPublicosRecursivosUA(uaHijo) );

		}

		//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
		Collections.sort( result, new Servicio() );

		return result;

	}


	/**
	 * Obtiene los ids de los servicios publicos de una unidad administrativa (PORMAD recuperacion de datos inicial)
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
	 * @return Devuelve <code>List<Long></code> de los identificadores de los servicios públicos de la unidad administrativa especificada.
	 */
	@SuppressWarnings("unchecked")
	public List<Long> listarIdsServiciosPublicosUAHVMateria(Long idUA, String[] codEstandarMateria, String[] codEstandarHechoVital) {

		Session session = getSession();
		try {

			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load( UnidadAdministrativa.class, idUA );
			Hibernate.initialize( unidadAdministrativa.getServicios() );

			Set<Servicio> procs = unidadAdministrativa.getServicios();
			List<Long> procsFinales = new ArrayList<Long>();
			for ( Servicio proc: procs ) {

				if ( publico(proc) ) {

					//Variable que indica si el servicio tiene alguna relacion
					boolean relacionada = false;

					//comprobamos materias
					Query queryMat = session.createQuery("select mat.codigoEstandar from Servicio p, p.materias as mat where p.id = :id");
					queryMat.setParameter( "id", proc.getId(), Hibernate.LONG );

					List<String> codigosMaterias = queryMat.list();

					//si el servicio está relacionado con alguna materia la marcamos
					for ( String codigoMat : codEstandarMateria ) {

						if ( relacionada = codigosMaterias.contains(codigoMat) )
							break;

					}

					//Si no tiene niguna relación con ninguna materia miramos si tiene ralación con algún hecho vital
					if ( !relacionada ) {

						Query queryHechos = session.createQuery("select hpv.hechoVital.codigoEstandar from Servicio p, p.hechosVitalesServicios as hpv where p.id = :id");
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
    					Hibernate.initialize(proc.getHechosVitalesServicios());
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
	 * Obtiene los servicios públicos de un Hecho Vital
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador de un hecho vital
	 * 
	 * @return Devuelve <code>List</code> de los servicios con un determinado hecho vital asignado.
	 */
	public List listarServiciosPublicosHechoVital(Long id) {

		Session session = getSession();
		try {

			HechoVital hechoVital = (HechoVital) session.load( HechoVital.class, id );
			List result = new ArrayList();
			for ( int i = 0 ; i < hechoVital.getHechosVitalesServicios().size() ; i++ ) {
 
				HechoVitalServicio hechoVitalServicio = hechoVital.getHechosVitalesServicios().get(i);
				Servicio proc = hechoVitalServicio.getServicio();
				if ( publico(proc) )
					result.add(proc);

			}

			//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
			Collections.sort( result, new Servicio() );

			return result;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * @deprecated No se usa
	 * Consulta toda la informacion de un servicio
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	public Servicio consultarServicio(Long id) {
		Session session = getSession();
		try {
			Servicio servicio = (Servicio) session.load(Servicio.class, id);
			if (userIsOper() || publico(servicio)) {
				Hibernate.initialize(servicio.getDocumentos());
				Hibernate.initialize(servicio.getNormativas());
				Hibernate.initialize(servicio.getMaterias());
				Hibernate.initialize(servicio.getHechosVitalesServicios());

				return servicio;
			} else {
				throw new SecurityException("Servicio no público.");
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
	private String populateQuery(Map<String, Object> parametros, Map traduccion, List<Object> params) {
		String aux = "";

		for (Iterator<String> iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
			String key = iter1.next();
			Object value = parametros.get(key);
			if (!key.startsWith("ordre") && value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (aux.length() > 0) aux = aux + " and ";
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( servicio." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( servicio." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else if (value instanceof Date) {
					if (aux.length() > 0) aux = aux + " and ";
					aux = aux + "servicio." + key + " = '" + value + "'";
				} else {
					if (aux.length() > 0) aux = aux + " and ";
					aux = aux + "servicio." + key + " = " + value;
				}
			}
		}

		// Tratamiento de traducciones
		if (!traduccion.isEmpty()) {
			if (aux.length() > 0) aux = aux + " and ";
			aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
			traduccion.remove("idioma");
		}
		for (Iterator<String> iter2 = traduccion.keySet().iterator(); iter2.hasNext();) {
			String key = iter2.next();
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
	 * Valida si un servicio es público 
	 * 
	 * @param proc	Indica el servicio local a validar.
	 * 
	 * @return Devuelve <code>true</code> si la fecha de caducidad es posterior a la actual o es nula, 
	 * si la fecha de publicación es anterior a la actual o nula y si el campo validación contiene el valor Público.
	 **/
	protected boolean publico(Servicio proc) {

		final Date now = new Date();
		boolean noCaducado = ( proc.getFechaDespublicacion() == null || proc.getFechaDespublicacion().after(now) );
		boolean publicado = ( proc.getFechaPublicacion() == null || proc.getFechaPublicacion().before(now) );

		return visible(proc) && noCaducado && publicado;
	}
	

	private List<DocumentoServicio> obtenerListaDocumentos(Servicio servicio) throws DelegateException {

        int listaSize = 0;
        if (servicio.getDocumentos() != null) {
            listaSize = servicio.getDocumentos().size();
        }
        List<DocumentoServicio> listaDocumentos = new ArrayList<DocumentoServicio>(listaSize);
        if (servicio.getDocumentos() != null) {
            DocumentoServicioDelegate documentoDelegate = DelegateUtil.getDocumentoServicioDelegate();
            for (DocumentoServicio documento : servicio.getDocumentos()) {
            	if (documento == null) continue;
                listaDocumentos.add(documentoDelegate.obtenerDocumentoServicio(documento.getId()));
            }
        }

        return listaDocumentos;
    }




	/**
	 * Buscamos el numero de servicios activos des de la fecha actual
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas.
	 * @param fechaDespublicacion	Filtro que indica el rango temporal en el que se encuentra activo un  servicio.
	 * @return numero de Servicios activos
	 */
	public int buscarServiciosActivos(List<Long> listaUnidadAdministrativaId, Date fechaDespublicacion) {

		Integer resultado = 0;
		Session session = getSession();
		try {
			Query query = null;
			if (listaUnidadAdministrativaId.size() > 0) {

				query = session.createQuery(
						" select count(*) from Servicio as srv " +
								" where srv.validacion = :validacion " +
								" and (srv.fechaDespublicacion >= :fecha or srv.fechaDespublicacion is null) " +
								" and (srv.fechaPublicacion <= :fecha or srv.fechaPublicacion is null) " +
						" and srv.unidadAdministrativa.id in (:lId) ");

				query.setInteger( "validacion", Validacion.PUBLICA );
				query.setDate( "fecha", fechaDespublicacion );
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
	 * Buscamos el numero de servicios activos des de la fecha actual.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas.
	 * @param fechaDespublicacion	Filtro que indica el rango temporal en el que se encuentra activo un  servicio.
	 * @return Devuelve el numero de Servicios caducados
	 */
	public int buscarServiciosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaDespublicacion){

		Integer resultado = 0;		
		Session session = getSession();
		try {
			Query query = null;
			if (listaUnidadAdministrativaId.size() > 0) {

				query = session.createQuery(
						" select count(*) from Servicio as srv " +
								" where ( " +
								" 	( srv.validacion != :validacion ) " +
								" 	or ( srv.validacion = :validacion and srv.fechaDespublicacion < :fecha ) " +
								" 	or ( srv.validacion = :validacion and srv.fechaDespublicacion is null and srv.fechaPublicacion > :fecha ) " +
								" 	or ( srv.validacion = :validacion and srv.fechaDespublicacion >= :fecha and srv.fechaPublicacion > :fecha ) " +
						" ) and srv.unidadAdministrativa.id in (:lId) ");

				query.setInteger("validacion", Validacion.PUBLICA);
				query.setDate("fecha", fechaDespublicacion);
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
	 * Obtiene los servicios publicos de una unidad administrativa (PORMAD recuperacion de datos inicial)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Servicio> listarServiciosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) {
		Session session = getSession();
		try {
			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			Hibernate.initialize(unidadAdministrativa.getServicios());

			Set<Servicio> procs = unidadAdministrativa.getServicios();
			List<Servicio> procsFinales = new ArrayList<Servicio>();
			for(Servicio proc: procs){
				if(publico(proc)){
					//Variable que indica si el servicio tiene alguna relacion
					boolean relacionada = false;

					//comprobamos materias
					Query queryMat = session.createQuery("select mat.codigoEstandar from Servicio p, p.materias as mat where p.id =:id");
					queryMat.setParameter("id", proc.getId(), Hibernate.LONG);

					List<String> codigosMaterias = queryMat.list();

					//si el servicio esta relacionada con alguna materia la marcamos
					for(String codigoMat: codEstMat){
						if (relacionada = codigosMaterias.contains(codigoMat)){
							break;
						}
					}

					//Si no tiene niguna relacion con ninguna materia miramos si teiene ralacion con algun HV
					if(!relacionada){
						Query queryHechos = session.createQuery("select hpv.hechoVital.codigoEstandar from Servicio p, p.hechosVitalesServicios as hpv where p.id =:id");
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
						Hibernate.initialize(proc.getHechosVitalesServicios());
						Hibernate.initialize(proc.getDocumentos());
						procsFinales.add(proc);
					}
				}
			}
			//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
			Collections.sort(procsFinales, new Servicio());
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
	 * Obtiene un servicio Local.
	 * @ejb.interface-method
 	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del servicio.
	 * @return Devuelve <code>Servicio</code> solicitado.
	 */
	public Servicio obtenerServicioParaSolr(Long id, Session iSession) {

		final Session session;
		if (iSession == null) {
			session = getSession();
		} else {
			session = iSession;
		}
		Servicio servicio = null;
		try {
			servicio = (Servicio) session.get(Servicio.class, id);
			if (servicio != null) {

				Hibernate.initialize(servicio.getMaterias());
				Hibernate.initialize(servicio.getPublicosObjetivo());
				Hibernate.initialize(servicio.getNormativas());
				Hibernate.initialize(servicio.getOrganoInstructor());
				if (servicio.getOrganoInstructor() != null) {
					Hibernate.initialize(servicio.getOrganoInstructor().getHijos());
					Hibernate.initialize(servicio.getOrganoInstructor().getUnidadesNormativas());
				}
				Hibernate.initialize(servicio.getServicioResponsable());
				if (servicio.getServicioResponsable() != null) {
					Hibernate.initialize(servicio.getServicioResponsable().getHijos());
					Hibernate.initialize(servicio.getServicioResponsable().getUnidadesNormativas());
				}
				Hibernate.initialize(servicio.getHechosVitalesServicios());
				Hibernate.initialize(servicio.getDocumentos());
			} 

		} catch (HibernateException he) {
			log.error("Error obteniendo el servicio con id " + id, he);
		} finally {
			if (iSession == null) {
				close(session);
			}
		}
		
		return servicio;
	}

	
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
	 * @param solrIndexer
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("ServicioFacadeEJB.indexarSolr. idElemento:" + idElemento +" categoria:"+categoria);
		
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final Servicio servicio = obtenerServicioParaSolr(idElemento, null);
			if (servicio == null) {
				return new SolrPendienteResultado(false, "Error obteniendo la id del servicio");
			}
			
			boolean isIndexable = IndexacionUtil.isIndexable(servicio);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria de tipo ficha.
			final IndexData indexData = new IndexData();
			indexData.setCategoria(categoria);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());
			indexData.setCategoriaRaiz(EnumCategoria.ROLSAC_SERVICIO);
			indexData.setElementoIdRaiz(idElemento.toString());
			
			//Iteramos las traducciones
			final Map<String, Traduccion> traducciones = servicio.getTraduccionMap();
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral searchText = new MultilangLiteral();
			final MultilangLiteral searchTextOptional = new MultilangLiteral();	
			final List<EnumIdiomas> idiomas = new ArrayList<EnumIdiomas>();
			
			final String nomUnidadAministrativa;
			if (servicio.getOrganoInstructor() == null) {
				nomUnidadAministrativa = "";
			} else {
				nomUnidadAministrativa = servicio.getOrganoInstructor().getNombre();
			}
						
			boolean esProcSerInterno = POUtils.contienePOInterno(servicio.getPublicosObjetivo());
			
			//Recorremos las traducciones
			for (String keyIdioma : traducciones.keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionServicio traduccion = (TraduccionServicio)traducciones.get(keyIdioma);
				
				if (traduccion != null && enumIdioma != null) {
					
					if ((traduccion.getNombre() == null || traduccion.getNombre().isEmpty())) {
						continue;
					}
					
					//Anyadimos idioma al enumerado.
					idiomas.add(enumIdioma);
					
					//Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search text.
					titulo.addIdioma(enumIdioma, traduccion.getNombre());
			    	descripcion.addIdioma(enumIdioma, traduccion.getObjeto());
			    	searchText.addIdioma(enumIdioma, traduccion.getNombre()  + " "+ traduccion.getObjeto());
			    	
			    	final StringBuffer textoOptional = new StringBuffer();
					
			    	//materia
			    	for(Materia materia : servicio.getMaterias()) {
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
			    	if (servicio.getServicioResponsable() != null) {
		    			TraduccionUA unidadAdm = (TraduccionUA) servicio.getServicioResponsable().getTraduccion(keyIdioma);
						if (unidadAdm != null) {
							textoOptional.append(" ");
							textoOptional.append(unidadAdm.getNombre());
						}			    		
			    	}
					
					//Publico objetivo, para extraer el nombre del publico objetivo
					String nombrePubObjetivo = "persones";
					String idPubObjetivo = "200";
					for( PublicoObjetivo publicoObjetivo :  servicio.getPublicosObjetivo()) {
						TraduccionPublicoObjetivo traduccionPO = (TraduccionPublicoObjetivo) publicoObjetivo.getTraduccion(keyIdioma);
						if (traduccionPO != null) {
							nombrePubObjetivo = traduccionPO.getTitulo().toLowerCase();
							idPubObjetivo = publicoObjetivo.getId().toString();
							break; //Con encontrar uno nos basta
						}
					}
					
					//UO
					if (servicio.getOrganoInstructor() != null && servicio.getOrganoInstructor().getTraduccion(keyIdioma) != null) {
						TraduccionUA unidadAdm = (TraduccionUA) servicio.getOrganoInstructor().getTraduccion(keyIdioma);
						if (unidadAdm != null) {
							textoOptional.append(" ");
							textoOptional.append(unidadAdm.getNombre());
						}
					} 
					
					//Normativa asociadas
					for(Normativa normativa : servicio.getNormativas()) {
						final TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativa.getTraduccion(keyIdioma);
						if (traduccionNormativa != null) {
							textoOptional.append(traduccionNormativa.getTitulo());
							textoOptional.append(" ");
						}
					}
					
			    	searchTextOptional.addIdioma(enumIdioma, traduccion.getObjeto() +" " +traduccion.getObservaciones() + " " + textoOptional.toString());
				
					if(esProcSerInterno) {
						//Si es interno usamos la url especifica para los Servicios internos
						
						String url = RolsacPropertiesUtil.getPropiedadPOInternoUrlProc().replace("{idioma}", keyIdioma)
								.replace("{idPublicoObjetivo}", idPubObjetivo)
								.replace("{nombrePubObjetivo}",nombrePubObjetivo)
								.replace("{idServicio}",servicio.getId().toString());		
						urls.addIdioma(enumIdioma, url);	
					
					}else {
						//Si no es interno					
						urls.addIdioma(enumIdioma, "/seucaib/"+keyIdioma+"/"+idPubObjetivo+"/"+nombrePubObjetivo+"/tramites/servicio/"+servicio.getId());	
					}	
				    	
			    	
			    	
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
			for(Materia materia : servicio.getMaterias()) {
				materiasId.add(materia.getId().toString());
			}
			indexData.setMateriaId(materiasId);
			
			//Datos IDs publico Objetivos.
	    	final List<String> publicoObjetivoId = new ArrayList<String>();		
			for( PublicoObjetivo publicoObjectivo :  servicio.getPublicosObjetivo()) {
				publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);
			
						
			//Fechas
			indexData.setFechaActualizacion(servicio.getFechaActualizacion());
			indexData.setFechaPublicacion(servicio.getFechaPublicacion());
			indexData.setFechaCaducidad(servicio.getFechaDespublicacion());
			if(esProcSerInterno) {
				indexData.setInterno(true);
			}else {
				indexData.setInterno(false);
			}	
					
			//Telematico
			indexData.setTelematico(servicio.isTelematico());
			
			//UA
			PathUO pathUO = IndexacionUtil.calcularPathUO(servicio.getOrganoInstructor());
			if (pathUO == null) {
				return new SolrPendienteResultado(true, "No se puede indexar: no cuelga de UA visible");
			}
			indexData.getUos().add(pathUO);
			
			indexData.setFamiliaId("none");
			solrIndexer.indexarContenido(indexData);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en servicioFacade intentando indexar. idElemento:" + idElemento +" categoria:"+categoria, exception);
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
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(), EnumCategoria.ROLSAC_SERVICIO);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en servicioFacade intentando desindexar.", exception);			
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}
	
	
	/**
	 *	Devuelve una lista con los ids de los servicios
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> buscarIdsServicios()  {
		Session session = getSession();
	
		try {

    		StringBuilder consulta = new StringBuilder("select proc.id from Servicio as proc ");
    		
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
	 *	Devuelve una lista con los servicios que tienen una normativa
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Servicio> listarServiciosNormativa(Long id){
		Session session = getSession();
		try {
			List<Servicio> result = new ArrayList<Servicio>();
			Normativa normativa = (Normativa) session.load(Normativa.class, id);
			Hibernate.initialize(normativa.getServicios()); 
			for (Iterator<Servicio> iter = normativa.getServicios().iterator(); iter.hasNext();) {
				Servicio servicio = iter.next();
				Hibernate.initialize(servicio.getMaterias());
				Hibernate.initialize(servicio.getServicioResponsable());
				Hibernate.initialize(servicio.getOrganoInstructor());
				Hibernate.initialize(servicio.getHechosVitalesServicios());
				Hibernate.initialize(servicio.getPublicosObjetivo());
				Hibernate.initialize(servicio.getNormativas());
				if (publico(servicio)) {
					result.add(servicio);
				}
			}
			//Ordenamos los servicios por el campo orden (si nulo, ordena por el campo id)
			Collections.sort(result, new Servicio());
			return result;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
	}
	
	/**
	 *	Actualiza el servicio pero no sus relaciones
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public void actualizarServicio(Servicio proc){
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
	 *	Obtiene los servicios que se han alterado por el tiempo su estado SIA.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> getServiciosEstadoSIAAlterado(){
		
		Session session = getSession();
		try {
			
			StringBuffer hql= new StringBuffer();
			
			hql.append(" select srv.id from Servicio srv");
			hql.append(" where ");
			//Servicios caducados son con estado SIA de alta y fechaDespublicacion pasada y son publicas.
			hql.append(" (   srv.estadoSIA is not null ");
			hql.append(" AND srv.estadoSIA like 'A'"); 
			hql.append(" AND srv.validacion = 1 "); 
			hql.append(" AND srv.fechaDespublicacion is not null ");
			hql.append(" AND srv.fechaDespublicacion < " + DateUtils.stringFechaAhoraBBDD() + " ) ");
			
			hql.append(" OR ");
			//Servicios activos sin estado o de baja y cuya fecha de caducidad es futura y son publicas.
			hql.append(" (   (srv.estadoSIA is null     OR   (srv.estadoSIA is NOT NULL AND srv.estadoSIA like 'B')) "); 
			hql.append(" AND srv.validacion = 1 "); 
			hql.append(" AND srv.fechaPublicacion IS NOT NULL ");
			hql.append(" AND srv.fechaPublicacion <= " + DateUtils.stringFechaAhoraBBDD() + " ");
			hql.append("  ) ");
			
			return session.createQuery(hql.toString()).list();
				
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
	}
	
	
	/**
	 *	Obtiene los servicios según el organo resolutorio.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> listarServiciosOrganoResolutori(Long idOrganResolutori) {
		Session session = getSession();
		try {
			StringBuffer hql= new StringBuffer();
	
			hql.append(" select pro.id from Servicio pro");
			hql.append(" where pro.organResolutori.id = " + idOrganResolutori);
			
			return session.createQuery(hql.toString()).list();
			
			
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
	}
	
	
	/**
	 *	Reordena los documentos del servicio.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public void reordenarDocumentos(final Long idServicio, final List<Long> idDocumentos) {
		Session session = getSession(); //session.beginTransaction()
		try {
			//Paso 1. Obtenemos el servicio.
			Servicio servicio = obtenerServicioParaSolr(idServicio, session);
			//Paso 1.1. Comprobamos que tiene permisos para editar los documentos del servicio.
			if (servicio.getId() != null && !getAccesoManager().tieneAccesoServicio(servicio.getId())) {
				throw new SecurityException("No tiene acceso al servicio");
			}
			
			//Paso 2. Obtener los servicios que han sido borrados.
			long maxOrden = 0;
			final List<DocumentoServicio> docsParaBorrar = new ArrayList<DocumentoServicio>();
			if (servicio.getDocumentos() != null) {
				for(DocumentoServicio doc : servicio.getDocumentos()) {
					if (doc != null && doc.getId() != null) {
						if (doc.getOrden() >= maxOrden) {
							maxOrden = doc.getOrden() + 1;
						}
						
						if (!idDocumentos.contains(doc.getId())) {
							docsParaBorrar.add(doc);  //Lo añadimos en la lista.
						}
						
					}
				}
			}
			
			//Paso 3. Los borramos tanto en BBDD como en la relacion.
			for(DocumentoServicio docParaBorrar : docsParaBorrar) {
				session.delete(docParaBorrar);    //Borramos el objeto
				servicio.getDocumentos().remove(docParaBorrar); //Borramos la relacion
			}
			
			//Paso 4. Reordenar.
			for(int i = 0 ; i < idDocumentos.size(); i++) {
				int orden = i; //Empezará en el 0 el orden.
				DocumentoServicio documento = (DocumentoServicio) session.get(DocumentoServicio.class, idDocumentos.get(i));
				documento.setOrden(orden + maxOrden);
				session.update(documento); 
				
				/*for(int j = 0 ; j < servicio.getDocumentos().size(); j++) {
					Documento doc = servicio.getDocumentos().get(j);
					if (doc != null && doc.getId() != null && doc.getId().compareTo(idDocumentos.get(i)) == 0) {
						//doc.setOrden(orden);
						servicio.getDocumentos().set(j, documento);
					}
				}*/
			}
			session.flush();
			
			//Paso 4.2 Reordenar.
			for(int i = 0 ; i < idDocumentos.size(); i++) {
				int orden = i; //Empezará en el 0 el orden.
				DocumentoServicio documento = (DocumentoServicio) session.get(DocumentoServicio.class, idDocumentos.get(i));
				documento.setOrden(Long.valueOf(orden)); 
				session.update(documento); 
			}
			session.flush();
			
			//Paso 5.Llamamos al addOperacion
			addOperacion(session, servicio, Auditoria.MODIFICAR);
			
			//Paso 6. Obtenemos de nuevo el servicio (por si se cachea) y actualizamos la fecha de actualización.
			servicio = obtenerServicioParaSolr(idServicio, session);
			servicio.setFechaActualizacion(new Date());
			session.update(servicio);
			
			//Paso 7. Llamamos al actualizador.
			Actualizador.actualizar(servicio);
			
			//Paso 8. Actualizamos en solr y sia.
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_SERVICIO, idServicio, false);
			SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_SERVICIO, idServicio, SiaUtils.SIAPENDIENTE_SERVICIO_EXISTE, null, servicio);
			
			//Paso 9. Flush.
			session.flush();
			
		} catch (Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	 /**
		 * Consulta los servicios en funcion del filtro generico
		 * 
		 * @ejb.interface-method
		 * @ejb.permission unchecked="true"
		 */
		public ResultadoBusqueda consultaServicios(FiltroGenerico filtro){
		
			Session session = getSession();	
			Integer pageSize = filtro.getPageSize();
			Integer pageNumber = filtro.getPage();
			Long id = filtro.getId();
			String lang = filtro.getLang();
			Map <String,String> parametros = new HashMap<String,String>();
					
			String activo = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_ACTIVO);		
			String codigoUA = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_UA); 
			String codigoUADIR3 = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_UA_DIR3);
		    String descendientes = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_UA_DESCENDIENTES);		
			String vigente = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_VIGENTE);		
			String telematico = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_TELEMATICO);
			String codigoAHV = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_AGRUPACION_HECHO_VITAL);	
			String codigoAM = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_AGRUPACION_MATERIA);
			String codigoSIA = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_CODIGO_SIA);
			String estadoSIA = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_ESTADO_SIA);
			String estadoUA = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_ESTADO_UA);
			String codigoFamilia = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_FAMILIA);
			String fechaActualizacionSia = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_FECHA_ACTUALIZACION_SIA);
			String fechaPublicacionDesde = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_FECHA_PUBLICACION_DESDE);
			String fechaPublicacionHasta = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_FECHA_PUBLICACION_HASTA);
			String codigoMateria = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_MATERIA);
			String codigoPublicoObjetivo = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_PUBLICO);		
			String textos = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_TEXTOS);
			String titulo = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_TITULO);
			String tramiteTelematico = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_TRAMITE_TELEMATICO);
			String versionTramiteTelematico = filtro.getValor(FiltroGenerico.FILTRO_SERVICIOS_VERSION_TRAMITE_TELEMATICO);

					
					
			StringBuilder select = new StringBuilder("SELECT s ");
			StringBuilder selectCount = new StringBuilder("SELECT count(s) ");
			StringBuilder from = new StringBuilder(" FROM Servicio as s, s.traducciones as trad ") ;
			StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
			parametros.put("lang",lang);
			StringBuilder order = new StringBuilder("");		
					
			try {
				
				if(id!=null && id>0) {
					where.append(" AND s.id = :id");
					parametros.put("id", id.toString());					
				}
										
				if(!StringUtils.isEmpty(activo)) {	
					if(activo.equals("1")) {
						// está activo
						where.append(filtroProcedimientosActivos("s"));					
					}else if(activo.equals("0")) {
						// esta caducado
						where.append(filtroProcedimientosCaducados("s"));
					}											
				}
				

				// Buscamos por id de la ua (y sus descendientes si procede)
				final Long idUA = (codigoUA != null) ? Long.parseLong(codigoUA) : null;
				String uaQuery = null;
				try {
					uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, "1".equals(descendientes), false);
				} catch (DelegateException e) {
					e.printStackTrace();
				}			

				if (!StringUtils.isEmpty(uaQuery)) {
					where.append( " AND s.organoInstructor.id in (" + uaQuery + ")");
				}
				
				
				//Buscamos por codigo dir3 de la ua y sus descendientes				
				String uaQueryDir3 = null;
				try {
					uaQueryDir3 = DelegateUtil.getUADelegate().obtenerCadenaFiltroUAPorDir3(codigoUADIR3, "1".equals(descendientes), false);
				} catch (DelegateException e) {
					e.printStackTrace();
				}
				
				
				if (!StringUtils.isEmpty(uaQueryDir3)) {
					// se ha añadido un codigodir3 que concuerda con una UA
					where.append( " AND s.organoInstructor.id in (" + uaQueryDir3 + ")");
				}else if(!StringUtils.isEmpty(codigoUADIR3)) {
					//Se ha añadido un codigo dir3 que no se corresponde con ninguna ua por lo que no tendra descendientes
					//Se añade para forzar que no retorne resultados
					where.append( " AND s.organoInstructor.codigoDIR3 = :codigoUADIR3");
					parametros.put("codigoUADIR3", codigoUADIR3);				
				}
					
				
				if(!StringUtils.isEmpty(estadoUA)) {
					where.append(" AND s.organoInstructor.validacion = :estadoUA");
					parametros.put("estadoUA", estadoUA);					
				}
				
								
				if((!StringUtils.isEmpty(telematico) && (telematico.equals("1") || telematico.equals("0")))) {
					where.append(" AND s.telematico = :telematico ");
					parametros.put("telematico", ApiRestUtils.intToBool(telematico));					
				}
	
				
				if(!StringUtils.isEmpty(codigoAHV)) {
					from.append(" , s.hechosVitalesServicios as hv ");
					where.append(" AND hv.hechoVital.id in ( SELECT hechovital.id FROM  HechoVitalAgrupacionHV as ahv, ahv.hechoVital as hechovital, ahv.agrupacion as agr WHERE agr.id = :codigoAHV ) ");
					parametros.put("codigoAHV", codigoAHV);
				}

					
				if(!StringUtils.isEmpty(codigoAM)) {
					from.append(" , s.materias as mat ");
					where.append(" AND mat.id in (SELECT am.materia FROM MateriaAgrupacionM AS am WHERE am.agrupacion.id = :codigoAM ) ");
					parametros.put("codigoAM", codigoAM);					
				}
							
				
				if(!StringUtils.isEmpty(codigoSIA)) {
					where.append(" AND s.codigoSIA = :codigoSIA");
					parametros.put("codigoSIA", codigoSIA);					
				}
				
				if(!StringUtils.isEmpty(estadoSIA)) {
					where.append(" AND s.estadoSIA = :estadoSIA");
					parametros.put("estadoSIA", estadoSIA);					
				}
				
				/*
				if(!StringUtils.isEmpty(codigoFamilia)) {
					where.append(" AND p.familia.id = :codigoFamilia");
					parametros.put("codigoFamilia", codigoFamilia);					
				}
				*/
				
				if(!StringUtils.isEmpty(fechaActualizacionSia)) {
					where.append(" AND s.fechaSIA = :fechaActualizacionSia");
					parametros.put("fechaActualizacionSia", fechaActualizacionSia);					
				}
			
				
				if(!StringUtils.isEmpty(fechaPublicacionDesde)) {
					where.append(" AND s.fechaPublicacion >= :fechaPublicacionDesde");
					parametros.put("fechaPublicacionDesde", fechaPublicacionDesde);					
				}
				
				if(!StringUtils.isEmpty(fechaPublicacionHasta)) {
					where.append(" AND s.fechaPublicacion <= :fechaPublicacionHasta");
					parametros.put("fechaPublicacionHasta", fechaPublicacionHasta);					
				}
				
				if(!StringUtils.isEmpty(codigoMateria)) {
					from.append(" , s.materias as m ");
					where.append(" AND m.id = :codigoMateria");
					parametros.put("codigoMateria", codigoMateria);					
				}
				
				if(!StringUtils.isEmpty(codigoPublicoObjetivo)) {
					from.append(" , s.publicosObjetivo as po ");
					where.append(" AND po.id = :codigoPublicoObjetivo");
					parametros.put("codigoPublicoObjetivo", codigoPublicoObjetivo);					
				}			
				
				if(!StringUtils.isEmpty(tramiteTelematico)) {
					where.append(" AND s.tramiteId = :tramiteTelematico");
					parametros.put("tramiteTelematico", tramiteTelematico);					
				}
				
				if(!StringUtils.isEmpty(versionTramiteTelematico)) {
					where.append(" AND s.tramiteVersion = :versionTramiteTelematico");
					parametros.put("versionTramiteTelematico", versionTramiteTelematico);					
				}
				
				
				
				
				if(!StringUtils.isEmpty(textos)) {				
				     final String[] camposBuscablesPorTexto = {
				            "s.correo", "s.nombreResponsable", "s.tramiteId", "s.tramiteUrl", //"s.signatura",
				            "trad.destinatarios",
				            "trad.nombre", 
				            "trad.observaciones",
				            "trad.requisitos",
				            "trad.objeto"
		
				        };

				    where.append(" AND ( ");
				    parametros.put("textos", "%" + textos +"%" );	
				    
				    boolean  primero = true;
				    
				    for (String campo : camposBuscablesPorTexto) {
						if (primero) {
							primero = false;					
						}else {
							where.append(" OR ");
						}
				    	
				    	where.append(campo);
				    	where.append(" LIKE :textos ");

					}	
				    
				    where.append(" ) ");
				}
				
				
				if (!StringUtils.isEmpty(titulo)) {
					final String[] camposBuscablesPorTexto = { "trad.nombre" };

					//Si hubiera que buscar en case-insensitive se podria hacer algo tipo:
					// titulo.tolowercase
					//y en la query:
					//where.append("LOWER("+campo+")) like('%pres%')
					where.append(" AND ( ");
					parametros.put("titulo", "%" + titulo + "%");

					boolean primero = true;

					for (final String campo : camposBuscablesPorTexto) {
						if (primero) {
							primero = false;
						} else {
							where.append(" OR ");
						}

						where.append(campo);
						where.append(" LIKE :titulo ");

					}

					where.append(" ) ");
				}
				
				
				
				return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
				
		
			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		
		}
		
		
		
		
		 private String filtroProcedimientosActivos(String alias) {
			 /*
			 AND ( p.validacion = 1 " +
						" AND (p.fechaCaducidad >= :ahora or p.fechaCaducidad is null) " +
						" AND (p.fechaPublicacion <= :ahora or p.fechaPublicacion is null) )
			 */
			 
			 StringBuilder res = new StringBuilder(" AND ( ");
			 res.append(alias);
			 res.append(".validacion = ");
			 res.append(Validacion.PUBLICA);
			 res.append(" AND ( ");
			 res.append(campo(alias,"fechaDespublicacion"));
			 res.append(" >= ");
			 res.append(formatoFecha(DateUtils.getNow()));
			 res.append(" OR ");
			 res.append(campo(alias,"fechaDespublicacion"));
			 res.append(" is null ) AND ( ");
			 res.append(campo(alias,"fechaPublicacion"));
			 res.append(" <= ");
			 res.append(formatoFecha(DateUtils.getNow()));
			 res.append(" OR ");
			 res.append(campo(alias,"fechaPublicacion"));
			 res.append(" is null ))");
			 
			 return res.toString();

		}

		private String filtroProcedimientosCaducados(String alias) {
			/*
			 * 	( p.validacion != 1 ) " +
			" 	or ( p.validacion = 1 and p.fechaCaducidad < :manana ) " +
			" 	or ( p.validacion = 1 and p.fechaCaducidad is null and p.fechaPublicacion > :manana ) " +
			" 	or ( p.validacion = 1 and p.fechaCaducidad >= :manana and p.fechaPublicacion > :manana ) 
			 */

			Date fecha = DateUtils.getNextDay();
			
			StringBuilder res = new StringBuilder(" AND ( ");
			res.append(campo(alias,"validacion"));
			res.append(" != ");
			res.append(Validacion.PUBLICA);
			res.append(" OR ( ");//--1
			res.append(campo(alias,"validacion"));
			res.append(" = ");
			res.append(Validacion.PUBLICA);
			res.append(" AND ");
			res.append(campo(alias,"fechaDespublicacion"));
			res.append(" < " );
			res.append(formatoFecha((fecha)));
			res.append(" ) OR ( "); //--2
			res.append(campo(alias,"validacion"));
			res.append(" = ");
			res.append(Validacion.PUBLICA);		
			res.append(" AND ( ");
			res.append(campo(alias,"fechaDespublicacion"));
			res.append(" is null OR ");
			res.append(campo(alias,"fechaDespublicacion"));
			res.append(" >= " );
			res.append(formatoFecha((fecha)));
			res.append(") AND " );
			res.append(campo(alias,"fechaPublicacion"));
			res.append(" > " );
			res.append(formatoFecha((fecha)));
			res.append(" ))"); 
				 
			return res.toString();

		}
		
		private StringBuilder campo(String alias, String campo) {
			StringBuilder res = new StringBuilder(alias);
			res.append(".");
			res.append(campo);
			return res;
		}
		private String formatoFecha(Date d) {
			return "'"+DateUtils.formatearddMMyyyy(d)+"'";
		}
	
	
}
