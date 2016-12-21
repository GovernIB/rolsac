package org.ibit.rol.sac.persistence.ejb;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HistoricoFicha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Remoto;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.EnlaceDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.Cadenas;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.IndexData;
import es.caib.solr.api.model.MultilangLiteral;
import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;

/**
 * SessionBean para mantener y consultar Fichas (PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/FichaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.FichaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public abstract class FichaFacadeEJB extends HibernateEJB {

    private static final long serialVersionUID = 4454278347074939459L;

    protected Hashtable contenidos_web; // contiene url y su contenido para agilizar el proceso de indexacion de fichas

    /**
     * Obtiene referencia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();
  

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
    	super.ejbCreate();
    }

    /**
     * Autoriza la creacion de una ficha
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaCrearFicha(Integer validacionFicha) throws SecurityException  {
    	return !(validacionFicha.equals(Validacion.PUBLICA) && !userIsSuper()); 
    }


    /**
     * Autoriza la modificacion ficha
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaModificarFicha(Long idFicha) throws SecurityException {
    	return (getAccesoManager().tieneAccesoFicha(idFicha));
    }    


    /**
     * Crea o actualiza una Ficha
     * @param	ficha	Indica la ficha a guardar
     * @return	Devuelve el identificador de la ficha guardada
     * @throws Exception 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarFicha(Ficha ficha) throws DelegateException {

		Session session = getSession();
		try {
		    Date FechaActualizacionBD = new Date();

		    if (ficha.getId() == null) {
		        if (ficha.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
		            throw new SecurityException("No puede crear una ficha publica");
		        }

		    } else {
		        if (!getAccesoManager().tieneAccesoFicha(ficha.getId())) {
		            throw new SecurityException("No tiene acceso a la ficha");
		        }
		        Ficha fichaBD = obtenerFicha(ficha.getId());
		        FechaActualizacionBD = fichaBD.getFechaActualizacion();
		    }

		    /* Se alimenta la fecha de actualizacion de forma automatica si no se ha introducido dato */
		    if (ficha.getFechaActualizacion() == null || DateUtils.fechasIguales(FechaActualizacionBD, ficha.getFechaActualizacion())) {
		        ficha.setFechaActualizacion(new Date());
		    }

		    if (ficha.getId() == null) {
		        session.save(ficha);
		        addOperacion(session, ficha, Auditoria.INSERTAR);

		    } else {
		        session.update(ficha);
		        addOperacion(session, ficha, Auditoria.MODIFICAR);
		    }

		    session.flush();
		    Ficha fichasend = obtenerFicha(ficha.getId());
		    Actualizador.actualizar(fichasend);
		    
		    IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, ficha.getId(), false);    
		    
		    return ficha.getId();

		} catch (HibernateException e) {
		    throw new EJBException(e);
		} finally {
		    close(session);
		}
	}


    /**
     * Obtiene una Ficha
     * 
     * @param id	Identificador de la ficha solicitada
     * 
     * @return Devuelve <code>Ficha</code> solicitada.
     * @throws DelegateException 
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public Ficha obtenerFicha(Long id) {

		Session session = getSession();
		Ficha ficha = null;
		try {
		    ficha = (Ficha) session.load(Ficha.class, id);
		    if (visible(ficha)) {
				Hibernate.initialize(ficha.getEnlaces());
				Hibernate.initialize(ficha.getMaterias());
				Hibernate.initialize(ficha.getHechosVitales());
				Hibernate.initialize(ficha.getPublicosObjetivo());
				Hibernate.initialize(ficha.getFichasua());
				Hibernate.initialize(ficha.getBaner());
				Hibernate.initialize(ficha.getImagen());
				Hibernate.initialize(ficha.getIcono());

				session.clear();
				Query query = session.createQuery("from Documento doc where doc.ficha.id = :id");
				query.setParameter("id", id);
				ficha.setDocumentos( (List<Documento>)query.list() );

		    } else {
		    	throw new SecurityException("El usuario no tiene el rol operador");
		    }

		} catch (HibernateException he) {
		    throw new EJBException(he);
		} finally {
		    close(session);
		}

		ArrayList listaOrdenada = new ArrayList( ficha.getDocumentos() );
		Comparator comp = new DocsFichaComparator();
		Collections.sort(listaOrdenada, comp);
		ficha.setDocumentos(listaOrdenada);                

		ArrayList listaOrdenadaEnl = new ArrayList( ficha.getEnlaces() );
		Comparator comp_enl = new EnlacesFichaComparator();
		Collections.sort(listaOrdenadaEnl, comp_enl);
		ficha.setEnlaces(listaOrdenadaEnl);                 

		return ficha;
    }


    class DocsFichaComparator implements Comparator {

		public int compare(Object o1, Object o2) {
	
		    Long x1 = (Documento) o1 == null ? 0L :  ((Documento) o1).getOrden();
	
		    Long x2 = (Documento) o2 == null ? 0L :  ((Documento) o2).getOrden();
	
		    return x1.compareTo(x2);
	
		}

    }


    class EnlacesFichaComparator implements Comparator {

		public int compare(Object o1, Object o2) {
	
		    Long x1 = (Enlace) o1 == null ? 0L :  ((Enlace) o1).getOrden();
	
		    Long x2 = (Enlace) o2 == null ? 0L :  ((Enlace) o2).getOrden();
	
		    return x1.compareTo(x2);
	
		}

    }


    /**
     * Obtiene la imagen de una Ficha
     * 
     * @param	id	Identificador de una ficha
     * 
     * @return Devuelve <code>Archivo</code> que contiene la imagen de la ficha
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerImagenFicha(Long id) {

		Session session = getSession();
	
		try {
	
		    Ficha ficha = (Ficha) session.load(Ficha.class, id);
	
		    if ( visible(ficha) ) {
	
			Hibernate.initialize( ficha.getImagen() );
	
			return ficha.getImagen();
	
		    } else {
	
			throw new SecurityException("El usuario no tiene el rol operador");
	
		    }
	
		} catch (HibernateException he) {
	
		    throw new EJBException(he);
	
		} finally {
	
		    close(session);
	
		}

    }


    /**
     * Obtiene el icono de una Ficha
     * 
     * @param id	Identificador de una ficha
     * 
     * @return <code>Archivo</code> que contiene el icono de la ficha solicitada.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerIconoFicha(Long id) {

		Session session = getSession();
	
		try {
	
		    Ficha ficha = (Ficha) session.load(Ficha.class, id);
	
		    if ( visible(ficha) ) {
	
			Hibernate.initialize( ficha.getIcono() );
	
			return ficha.getIcono();
	
		    } else {
	
			throw new SecurityException("El usuario no tiene el rol operador");
	
		    }
	
		} catch (HibernateException he) {
	
		    throw new EJBException(he);
	
		} finally {
	
		    close(session);
	
		}

    }

    /**
     * Obtiene el baner de una Ficha
     * 
     * @param	id	Identificador de una ficha
     *
     * @return Devuelve <code>Archivo</code> que contiene el baner de una ficha
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerBanerFicha(Long id) {

		Session session = getSession();
	
		try {
	
		    Ficha ficha = (Ficha) session.load(Ficha.class, id);
	
		    if ( visible(ficha) ) {
		
				Hibernate.initialize( ficha.getBaner() );
		
				return ficha.getBaner();
	
		    } else {
	
		    	throw new SecurityException("El usuario no tiene el rol operador");
	
		    }
	
		} catch (HibernateException he) {
	
		    throw new EJBException(he);
	
		} finally {
	
		    close(session);
	
		}

    }


    /**
     *  @deprecated No se usa 
     * Obtiene una lista de fichas usando criteria en lugar de hql
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public List buscarFichas(Map parametros, String traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria,Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden) {

		Session session = getSession();
		Criteria criteria = null;
		List<Ficha> listaFichas = null;
	
		Set<UnidadAdministrativa> uas = new HashSet<UnidadAdministrativa>();
		Set<Long> uasIds = new HashSet<Long>();
	
		try {
	
		    if (!userIsOper()) {
		    	parametros.put("validacion", Validacion.PUBLICA);
		    }
	
		    criteria = session.createCriteria(Ficha.class);
	
		    criteria.setFetchMode("hechosVitales", FetchMode.LAZY);
		    criteria.setFetchMode("materias", FetchMode.LAZY);
		    criteria.setFetchMode("publicosObjetivo", FetchMode.LAZY);
		    criteria.setFetchMode("enlaces", FetchMode.LAZY);
		    criteria.setFetchMode("documentos", FetchMode.LAZY);
	
		    // Unitat administrativa
		    if (ua != null) {
		    	ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua.getId());
		    	uas.add(ua);
		    }

		    if (uaMeves) {
		    	uas.addAll(getUsuario(session).getUnidadesAdministrativas());
		    } 

		    if (uaFilles) {

		    	UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

		    	for (UnidadAdministrativa uaActual : uas) {

		    		uasIds.add(uaActual.getId());
		    		List<Long> idsDescendientes = uaDelegate.cargarArbolUnidadId(uaActual.getId());
		    		uasIds.addAll(idsDescendientes);

		    	}
		    	
		    } else {
		    	
		    	for (UnidadAdministrativa uaActual : uas) {
		    		uasIds.add(uaActual.getId());
		    	}
		    	
		    }

		    if (!uasIds.isEmpty()) {
		    	criteria.createAlias("fichasua", "fsua");
		    	criteria.add(Expression.in("fsua.unidadAdministrativa.id", uasIds));            	
		    }

		    //Fet Vital
		    if (idFetVital != null) {
		    	criteria.createAlias("hechosVitales", "hec");
		    	criteria.add(Expression.eq("hec.id", idFetVital));
		    } 

		    //Materia
		    if (idMateria != null){
		    	criteria.createAlias("materias", "mat");
		    	criteria.add(Expression.eq("mat.id", idMateria));
		    }

		    //Public Objectiu
		    if (idPublic != null){
		    	criteria.createAlias("publicosObjetivo", "pub");
		    	criteria.add(Expression.eq("pub.id", idPublic));
		    }

		    //Par�metros 
		    for (Iterator i = parametros.keySet().iterator(); i.hasNext();) {
		    	String key = (String) i.next();
		    	Object value = parametros.get(key);

		    	if (value != null ) {
		    		if ( value instanceof String ) {
		    			String sValue = (String) value;
		    			if (sValue.length() > 0) {
		    				if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
		    					sValue = sValue.substring(1, (sValue.length() - 1));
		    					criteria.add( Expression.like(key, sValue).ignoreCase() );                            
		    				} else {
		    					criteria.add( Expression.like(key, "%"+sValue+"%").ignoreCase() );
		    				}
		    			}
		    		} else {
		    			criteria.add( Expression.eq(key,  value) );
		    		}
		    	}
		    } 

		    if ( campoOrdenacion != null )
		    	criteria.addOrder( Order.asc( campoOrdenacion ));

		    listaFichas = criteria.list();

		    // Campos multi-idioma (si hay que aplicar filtro)
		    if (traduccion != null && traduccion.length() > 0 ) {

		    	//Por cada ficha, recoger sus traducciones y comprobar si los campos 
		    	//de filtrado que vienen del parametro "traduccion" contienen ese valor
		    	List<Ficha> nuevaListaFichas = new ArrayList<Ficha>();

		    	for (Ficha ficha: listaFichas) {

		    		for (String key : ficha.getTraduccionMap().keySet()) {	
		    			TraduccionFicha tradFicha = (TraduccionFicha) ficha.getTraduccionMap().get(key);
		    			if ( Cadenas.isCadenaEnTraduccion(tradFicha, traduccion) ) {
		    				nuevaListaFichas.add(ficha);
		    				break;
		    			}
		    		}
		    		
		    	}

		    	listaFichas = nuevaListaFichas;	 
		    	
		    }

		    if (!userIsOper()) {
		    	
		    	return listaFichas;
		    	
		    } else {
		    	
		    	List<Ficha> fichasAcceso = new ArrayList<Ficha>();
		    	Usuario usuario = getUsuario(session);
		    	for (Ficha ficha: listaFichas){
		    		if (tieneAcceso(usuario, ficha)){
		    			fichasAcceso.add(ficha);
		    		}   
		    	}
		    	
		    	return fichasAcceso; 
		    	
		    }

		} catch (DelegateException de) {
			
			throw new EJBException(de);
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);     
			
		}
		
    }

    /**
     * Busca todas las fichas que cumplen los criterios de busqueda del nuevo back (rolsacback). 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, 
	    Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, 
	    String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible) {

    	//TODO Refactorizar
    	Session session = getSession();

    	try {

    		if ( !userIsOper() )
    			parametros.put("validacion", Validacion.PUBLICA);

    		List params = new ArrayList();
    		String i18nQuery = "";
    		String fetVitalQuery = "";            
    		String materiaQuery = "";        
    		String publicQuery = "";   
    		String mainQuery = "select distinct ficha from Ficha as ficha , ficha.traducciones as trad, ficha.fichasua as fsua ";

    		if (traduccion.get("idioma") != null) {
    			
    			i18nQuery = populateQuery(parametros, traduccion, params);
    			
    		} else {
    			
    			String paramsQuery = populateQuery(parametros, new HashMap(), params);
    			if (paramsQuery.length() == 0) {
    				i18nQuery += " where ";
    			} else {
    				i18nQuery += paramsQuery + " and ";
    			}
    			
    			i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ") ";
    			
    		}

    		if (campoVisible == 1) {
    			i18nQuery += " and (sysdate < ficha.fechaCaducidad or ficha.fechaCaducidad is null) ";
    			i18nQuery += " and (sysdate > ficha.fechaPublicacion or ficha.fechaPublicacion is null) ";
    		} else if (campoVisible == 2) {
    			i18nQuery += " and (sysdate > ficha.fechaCaducidad or sysdate < ficha.fechaPublicacion or ficha.validacion = 2 or ficha.validacion = 3) ";
    		}

    		String orderBy = "";
    		if (campoOrdenacion != null && orden != null) orderBy = " order by ficha." + campoOrdenacion + " " + orden;

    		Long idUA = (ua != null) ? ua.getId() : null;            
    		String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA( idUA, uaFilles, uaMeves );                        

    		if ( !StringUtils.isEmpty(uaQuery) )            	
    			uaQuery = " and fsua.unidadAdministrativa.id in (" + uaQuery + ") ";

    		if ( idFetVital != null ) {
    			mainQuery += ",ficha.hechosVitales as hec ";  
    			fetVitalQuery = " and hec.id = ? ";
    			params.add( idFetVital );
    		} 

    		if ( idMateria != null ) {
    			mainQuery += ",ficha.materias as mat ";  
    			materiaQuery = " and mat.id = ? "; 
    			params.add( idMateria );
    		}            

    		if ( idPublic != null ) {
    			mainQuery += ",ficha.publicosObjetivo as pob ";  
    			publicQuery = " and pob.id = ? "; 
    			params.add(idPublic);
    		}

    		Query query = session.createQuery(mainQuery + i18nQuery + uaQuery + fetVitalQuery + materiaQuery + publicQuery + orderBy);

    		for (int i = 0; i < params.size(); i++) {
    			Object o = params.get(i);
    			query.setParameter(i, o);
    		}

    		int resultadosMax = new Integer(resultats).intValue();
    		int primerResultado = new Integer(pagina).intValue() * resultadosMax;

    		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

    		if (!userIsOper()) {

    			resultadoBusqueda.setTotalResultados( query.list().size() );

    			if ( resultadosMax != RESULTATS_CERCA_TOTS) {
    				query.setFirstResult(primerResultado);
    				query.setMaxResults(resultadosMax);
    			}            

    			resultadoBusqueda.setListaResultados(query.list());

    		} else {

    			List<Ficha> fichas = query.list();

    			List<Ficha> fichasAcceso = new ArrayList<Ficha>();
    			Usuario usuario = getUsuario(session);

    			// Procesar todas las fichas para saber el total y 
    			// aprovechar el bucle para ir guardando el número 
    			// de fichas solicitadas según los parámetros de paginación.                
    			int contadorTotales = 0;
    			int fichasInsertadas = 0;
    			int iteraciones = 0;

    			for ( Ficha ficha : fichas ) {
    				
    				if ( tieneAcceso(usuario, ficha) ) {
    					
    					if ( fichasInsertadas != resultadosMax && iteraciones >= primerResultado ) { 
    						fichasAcceso.add(ficha);
    						fichasInsertadas++;
    					}    
    					
    					contadorTotales++;    
    					
    				}  
    				
    				iteraciones++;
    				
    			}

    			resultadoBusqueda.setTotalResultados( contadorTotales );
    			resultadoBusqueda.setListaResultados( fichasAcceso );

    		}

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
     * Lista de Fichas publicas de una unidad administrativa
     * 
     * @return Devuelve <code>List</code> de todas las fichas  de una unidad administrativa
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public List listarFichasRecientes() {

    	Session session = getSession();

    	try {

    		StringBuilder consulta = new StringBuilder("select f from FichaRemota as f ");
    		consulta.append("where f.validacion = :validacion ");
    		consulta.append("order by desc f.id");

    		Query query = session.createQuery( consulta.toString() );
    		query.setParameter("validacion", Validacion.PUBLICA);
    		query.setCacheable(true);

    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    }

    /**
     * Borra una Ficha
     * 
     * @param id	Identificador de la ficha a borrar.
     * @throws DelegateException 
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarFicha(Long id) throws DelegateException {

    	Session session = getSession();

    	try {

    		if ( !getAccesoManager().tieneAccesoFicha(id) )
    			throw new SecurityException("No tiene acceso a la ficha");

    		Ficha ficha = (Ficha) session.load(Ficha.class, id);
    		addOperacion(session, ficha, Auditoria.BORRAR);

    		// Debemos anular en todos los registros del historico de esa ficha, la materia y la ua.  
    		Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :idFicha ");
    		query.setParameter("idFicha", ficha.getId(), Hibernate.LONG);
    		query.setCacheable(true);

    		List hfichas = query.list();

    		HistoricoFicha historico;
    		for ( int j = 0 ; j < hfichas.size() ; j++ ) {

    			historico = (HistoricoFicha) hfichas.get(j);
    			historico.setFicha(null);
    			historico.setMateria(null);
    			historico.setUa(null);

    		}

    		//Borrar comentarios
    		session.delete("from ComentarioFicha as cf where cf.ficha.id = ?", id, Hibernate.LONG);

    		if ( ficha instanceof FichaRemota ) {

    			AdministracionRemota admin = ( (FichaRemota) ficha ).getAdministracionRemota();

    			if ( admin != null )
    				admin.removeFichaRemota( (FichaRemota) ficha );

    		} else {

    			Actualizador.borrar( new Ficha(id) );

    		}

    		session.delete(ficha);
    		session.flush();
    		
    		IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, ficha.getId(), true);

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    }

    /**
     * Obtiene todas las  fichas de la seccion
     * 
     * @param	idSeccion	Identificador de una sección
     * 
     * @return Devuelve <code>List</code> de fichas asociadas a una determinada sección
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public List listarFichasSeccionTodas(Long idSeccion) {

    	Session session = getSession();

    	try {

    		StringBuilder consulta = new StringBuilder("select f from Ficha as f, fua in f.fichasua ");
    		consulta.append("where fua.seccion.id = :idSeccion ");
    		consulta.append("order by fua.ordenseccion , f.fechaActualizacion desc");

    		Query query = session.createQuery( consulta.toString() );
    		query.setParameter("idSeccion", idSeccion, Hibernate.LONG);
    		query.setCacheable(true);

    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    }

    /**
     * Obtiene las fichas de una Unidad Administrativa relacionada con la seccion
     * para un conjunto de hechos vitales y un conjunto de materias  (PORMAD)
     * 
     * @param idUA	Identificador de la unidad administrativa
     * 
     * @param codEstandarSeccion	Indica el código estándar de sección
     * 
     * @param codEstandarHechoVital	Indica el código estándar de un hecho vital
     * 
     * @param codEstandarMateria	Indica el código estándar de una materia
     * 
     * @return Devuelve <code>List<Ficha></code> de una unidad administrativa
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<Ficha> listarFichasSeccionUA(Long idUA, String codEstandarSeccion, String[] codEstandarHechoVital, String[] codEstandarMateria) {

    	Session session = getSession();

    	try {

    		//Obtenemos las fichas de una unidad y una sección que son públicas.			
    		StringBuilder consulta = new StringBuilder("select f from Ficha as f, fua in f.fichasua ");
    		consulta.append("where fua.unidadAdministrativa.id = :idUA ");
    		consulta.append("and fua.seccion.codigoEstandard = :codEstSecc  ");
    		consulta.append("and f.validacion = :validacion ");
    		consulta.append("and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) ");
    		consulta.append("and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) ");
    		consulta.append("order by fua.orden");

    		Query query = session.createQuery( consulta.toString() );
    		query.setParameter("idUA", idUA, Hibernate.LONG);
    		query.setParameter("codEstSecc", codEstandarSeccion);
    		query.setParameter("validacion", Validacion.PUBLICA);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("fechap", DateUtils.HoyHora());
    		query.setCacheable(true);

    		List<Ficha> fichas =  query.list();
    		List<Ficha> fichasFinales = new ArrayList<Ficha>();

    		for ( Ficha ficha : fichas ) {

    			boolean relacionada = false; //Variable que indica si la ficha tiene alguna relación

    			if ( codEstandarMateria.length > 0 ) {

    				// obtenemos los codigos estándar de las materias de la ficha
    				Query queryMat = session.createQuery("select mat.codigoEstandar from Ficha as f, f.materias as mat where f.id = :id");
    				queryMat.setParameter("id", ficha.getId(), Hibernate.LONG);
    				List<String> codigosMaterias = queryMat.list();

    				// si la ficha está relacionada con alguna materia la marcamos
    				for ( String codigoMat : codEstandarMateria ) {

    					if ( relacionada = codigosMaterias.contains(codigoMat) )
    						break;

    				}

    			}

    			if ( codEstandarHechoVital.length > 0 ) {

    				//Si no tiene niguna relación con ninguna materia miramos si tiene relación con algún Hecho Vital
    				if ( !relacionada ) {

    					Query queryHechos = session.createQuery("select hev.codigoEstandar from Ficha as f, f.hechosVitales as hev where f.id = :id");
    					queryHechos.setParameter("id", ficha.getId(), Hibernate.LONG);
    					List<String> codigosHechos = queryHechos.list();

    					// si la ficha está relacionada con el hecho vital la marcamos
    					for(String codigoHev: codEstandarHechoVital){

    						if ( relacionada = codigosHechos.contains(codigoHev) )
    							break;

    					}

    				}

    			}

    			if ( relacionada ) {

    				Hibernate.initialize( ficha.getIcono() );
    				Hibernate.initialize( ficha.getImagen() );
    				Hibernate.initialize( ficha.getBaner() );
    				Hibernate.initialize( ficha.getMaterias() );
    				Hibernate.initialize( ficha.getHechosVitales() );

    				fichasFinales.add(ficha);

    			}

    		}

    		return fichasFinales;

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    }

    /**
     * Crea o actualiza una FichaUA, esta ficha será la que tenga el orden 0
     *  @param	idUA	Indentificador de una unidad administrativa
     *  @param	idSeccion	Identificador de una sección
     *  @param	idFicha	Identificador de una ficha
     *  @return Devuelve el identificador de la ficha creada
     * @throws DelegateException 
     * 	@ejb.interface-method
     * 	@ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long crearFichaUA2(Long idUA, Long idSeccion, Long idFicha) throws DelegateException {

    	Session session = getSession();

    	try {
    		
    		if (!getAccesoManager().tieneAccesoFicha(idFicha)) {
    			throw new SecurityException("No tiene acceso a la ficha");
    		}

    		FichaUA fichaUA = new FichaUA();
    		if (idUA != null) {
    			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
    			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
    				throw new SecurityException("No tiene acceso a la ficha");
    			}
    			// Cuando se aniade una ficha a una seccion o a una seccion + ua por defecto su orden es 1
    			fichaUA.setOrden(1);
    			unidad.addFichaUA2(fichaUA);
    		} else if (!userIsSystem()) {
    			throw new SecurityException("No puede crear fichas generales.");
    		}

    		Seccion seccion = (Seccion) session.load(Seccion.class, idSeccion);
    		if (!getAccesoManager().tieneAccesoSeccion(idSeccion)) {
    			throw new SecurityException("No tiene acceso a la seccion");
    		}

    		// Cuando se añade una ficha a una sección o a una sección + ua por defecto su orden es 1
    		fichaUA.setOrdenseccion(1);
    		seccion.addFichaUA2(fichaUA);
    		Ficha ficha = (Ficha) session.load(Ficha.class, idFicha);
    		ficha.addFichaUA(fichaUA);

    		Ficha fichasend = obtenerFicha(idFicha);
    		Actualizador.actualizar(fichasend);
    		    		
    		session.flush();

    		IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, ficha.getId(), false);
		    
    		return ficha.getId();

    	} catch (HibernateException e) {
    		throw new EJBException(e);
    		
    	} finally {
    		
    		close(session);
    		
    	}
    	
    }


	

    /**
     * Borrar una ficha de Unidad administrativa
     * @param	id	Identificador de una unidad administrativa
     * @throws DelegateException 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarFichaUA(Long id) throws DelegateException {

    	Session session = getSession();

    	try {

    		if (!getAccesoManager().tieneAccesoFichaUnidad(id)) {
    			throw new SecurityException("No tiene acceso a la relacion");
    		}

    		FichaUA fichaUA = (FichaUA) session.load(FichaUA.class, id);
    		final  Long idFicha = fichaUA.getFicha().getId();
    		final  String ceSeccion = fichaUA.getSeccion().getCodigoEstandard();
    		final  Long idUA = fichaUA.getUnidadAdministrativa().getId();

    		boolean borrar = !(fichaUA.getFicha() instanceof Remoto); 

    		fichaUA.getFicha().removeFichaUA(fichaUA);
    		fichaUA.getSeccion().removeFichaUA(fichaUA);
    		UnidadAdministrativa unidad = fichaUA.getUnidadAdministrativa();
    		if (unidad != null) {
    			unidad.removeFichaUA(fichaUA);
    		}

    		session.delete(fichaUA);
    		session.flush();
    		
    		Ficha ficha = obtenerFicha(idFicha);

    		if(borrar) {
    			log.debug("Entro en borrar remoto ficha UA");
    		}
    		
    		//Actualizador.borrar(new FichaUATransferible(idUA,idFicha,ceSeccion));

    		IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, ficha.getId(), false);
    		
    		
    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    }


    /**
     * Construye la consulta de búsqueda según los parámetros
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
    						aux = aux + " upper( ficha." + key + " ) like ? ";
    						params.add(sValue);
    					} else {
    						aux = aux + " upper( ficha." + key + " ) like ? ";
    						params.add("%"+sValue+"%");
    					}
    				}
    			} else if (value instanceof Date) {
    				if (aux.length() > 0) aux = aux + " and ";
    				aux = aux + "ficha." + key + " = '" + value + "'";
    			} else {
    				if (aux.length() > 0) aux = aux + " and ";
    				aux = aux + "ficha." + key + " = " + value;
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


  
    
    private List<Documento> obtenerListaDocumentos(Ficha ficha) throws DelegateException {

        int listaSize = 0;
        if (ficha.getDocumentos() != null) {
            listaSize = ficha.getDocumentos().size();
        }
        List<Documento> listaDocumentos = new ArrayList<Documento>(listaSize);
        if (ficha.getDocumentos() != null) {
            DocumentoDelegate documentoDelegate = DelegateUtil.getDocumentoDelegate();
            for (Documento documento : ficha.getDocumentos()) {
            	if (documento == null) continue;
                Documento doc = documentoDelegate.obtenerDocumento(documento.getId());
                listaDocumentos.add(doc);
            }
        }

        return listaDocumentos;
    }




    private void getWEB_EXTERNA (String url) {

    	try {

    		if ( !contenidos_web.containsKey(url) ) {

    			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    			connection.connect();

    			DataInputStream dis = new DataInputStream( connection.getInputStream() );
    			String inputLine;
    			String str = "";

    			while ( (inputLine = dis.readLine() ) != null )
    				str += inputLine + "\n";

    			dis.close();

    			Archivo archi = new Archivo();
    			archi.setMime("text/html");
    			archi.setPeso( str.length() );
    			archi.setDatos( str.getBytes() );

    			contenidos_web.put(url, archi);

    		}

    	} catch (MalformedURLException e) {

    		System.out.println( "La URL no es válida: " + url + " " + e );
    		contenidos_web.put( url, new String("La URL no es válida") );

    	} catch (IOException e) {

    		System.out.println( "No puedo conectar con " + url + " " + e );
    		contenidos_web.put( url, new String("No puedo conectar") );

    	}

    }


    /**
     * Obtiene la hash con los contenidos de las webs externas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Hashtable getContenidos_web() {
    	return contenidos_web;
    }


    /**
     * Establece la hash con los contenidos de las webs externas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void setContenidos_web(Hashtable contenidos_web) {
    	this.contenidos_web = contenidos_web;
    }


    class FichaUAComparator implements Comparator {

    	public int compare(Object o1, Object o2) { 

    		Integer x1 = ((FichaUA) o1).getOrden();
    		Integer x2 = ((FichaUA) o2).getOrden();

    		return x1.compareTo( x2 ); 

    	}

    }


    /**
     * Devuelve las fichasUA de una ficha {PORMAD}
     * 
     * @param idFicha	Identificador de una ficha
     * @return Devuelve <code>List<FichaUA></code> de una determinada ficha informativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaUA> listFichasUA(Long idFicha) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery("select fichaua from FichaUA as fichaua where fichaua.ficha=" + idFicha);
    		return query.list();

    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
	
    }



    /**
     * Construye la consulta de búsqueda multiidioma en todos los campos
     */
    private String i18nPopulateQuery(Map traducciones, List params) {
    	
    	//TODO: Refactorizar
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
     * Obtiene una Ficha.
     * 
     * @param	id	Identificador de una ficha
     * 
     * @return Devuelve <code>Ficha</code> solicitada, en caso contrario devuelve null.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public Ficha obtenerFichaPMA(Long id) {

    	Session session = getSession();
    	Ficha ficha = null;

    	try {

    		ficha = (Ficha) session.load(Ficha.class, id);

    		if ( visible(ficha) ) {

    			Hibernate.initialize( ficha.getDocumentos() );
    			Hibernate.initialize( ficha.getEnlaces() );
    			Hibernate.initialize( ficha.getMaterias() );
    			Hibernate.initialize( ficha.getHechosVitales() );
    			Hibernate.initialize( ficha.getFichasua() );
    			Hibernate.initialize( ficha.getBaner() );
    			Hibernate.initialize( ficha.getImagen() );
    			Hibernate.initialize( ficha.getIcono() );

    		} else {

    			return null;

    		}

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    	ArrayList listaOrdenada = new ArrayList(ficha.getDocumentos());
    	Comparator comparadorFichas = new DocsFichaComparator();
    	Collections.sort(listaOrdenada, comparadorFichas);
    	ficha.setDocumentos(listaOrdenada);

    	ArrayList listaOrdenadaEnlaces = new ArrayList(ficha.getEnlaces());
    	Comparator comparadorEnlaces = new EnlacesFichaComparator();
    	Collections.sort(listaOrdenadaEnlaces, comparadorEnlaces);
    	ficha.setEnlaces(listaOrdenadaEnlaces);

    	return ficha;

    }


    /**
     * Buscamos el numero de fichas activas des de la fecha actual
     * 
     * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas
     * 
     * @param fechaCaducidad	Indica la fecha de caducidad de una ficha
     * 
     * @return Devuelve el número de fichas activas
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public int buscarFichasActivas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) {

    	Integer resultado = 0;
    	Session session = getSession();

    	try {

    		if ( listaUnidadAdministrativaId.size() > 0 ) {

    			StringBuilder consulta = new StringBuilder(" select count( distinct fic.id ) from Ficha as fic, FichaUA as ficUA where fic.id = ficUA.ficha.id ");
    			consulta.append(" and fic.validacion = :validacion ");
    			consulta.append(" and (fic.fechaCaducidad >= :fecha or fic.fechaCaducidad is null) ");
    			consulta.append(" and (fic.fechaPublicacion <= :fecha or fic.fechaPublicacion is null) ");
    			consulta.append(" and ficUA.unidadAdministrativa.id in (:lId)  ");

    			Query query = session.createQuery( consulta.toString() );
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
     * Buscamos el numero de fichas caducadas des de la fecha actual
     * 
     * @param listaUnidadAdministrativaId	Lista de identificadores de unidades administrativas
     * 
     * @param fechaCaducidad	Indica la fecha de caducidad de las fichas
     * 
     * @return Devuelve el número de fichas caducadas
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
    public int buscarFichasCaducadas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) {

    	Integer resultado = 0;		
    	Session session = getSession();

    	try {

    		if ( listaUnidadAdministrativaId.size() > 0 ) {

    			StringBuilder consulta = new StringBuilder("select count( distinct fic.id ) from Ficha as fic, FichaUA as ficUA where fic.id = ficUA.ficha.id and ( ");
    			consulta.append(" ( fic.validacion != :validacion ) ");
    			consulta.append(" or ( fic.validacion = :validacion and fic.fechaCaducidad < :fecha ) ");
    			consulta.append(" or ( fic.validacion = :validacion and fic.fechaCaducidad is null and fic.fechaPublicacion > :fecha ) ");
    			consulta.append(" or ( fic.validacion = :validacion and fic.fechaCaducidad >= :fecha and fic.fechaPublicacion > :fecha ) ");
    			consulta.append(" ) and ficUA.unidadAdministrativa.id in (:lId) ");

    			Query query = session.createQuery( consulta.toString() );
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


    private void actualizaSeccionesFichaUA( UnidadAdministrativa ua, List<Long> listaBorrar, List<SimpleFichaUA> listaNuevas ) throws DelegateException {

    	Session session = null;

    	try {

    		if ( !( listaBorrar.isEmpty() && listaNuevas.isEmpty() ) )
    			session = getSession();

    		// Borrar
    		for ( Long idBorrar : listaBorrar ) {

    			FichaUA fichaUA = (FichaUA) session.load(FichaUA.class, idBorrar);
    			boolean borrar = !( fichaUA.getFicha() instanceof Remoto ); 

    			fichaUA.getFicha().removeFichaUA(fichaUA);
    			fichaUA.getSeccion().removeFichaUA(fichaUA);

    			if ( ua != null ) 
    				ua.removeFichaUA(fichaUA);                

    			//SOLR Indexar ficha.
    			if (fichaUA.getFicha() != null) {
    				SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
        			solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_FICHA.toString(), fichaUA.getFicha().getId(), 1l);
    			}
    			
    			session.delete(fichaUA);

    			
    			if (borrar) 
    				log.debug("Entro en borrar remoto ficha UA");

    		} 

    		// Crear nuevas FichasUA
    		for ( SimpleFichaUA simpleFichaUA : listaNuevas ) {

    			FichaUA fichaUA = new FichaUA();

    			if ( ua.getId() != null ) {

    				fichaUA.setOrdenseccion( simpleFichaUA.getOrdenSeccion().intValue() );	                
    				ua.addFichaUA(fichaUA);

    			} else if ( !userIsSystem() ) {

    				throw new SecurityException("No puede crear fichas generales.");

    			}

    			Seccion seccion = (Seccion) session.load(Seccion.class, simpleFichaUA.getIdSeccion() );

    			// Cuando se anyade una ficha a una seccion o a una seccion + ua por defecto su orden es 1
    			fichaUA.setSeccion(seccion);	            
    			seccion.addFichaUA(fichaUA);

    			Ficha ficha = (Ficha) session.load( Ficha.class, simpleFichaUA.getIdFicha() );
    			ficha.addFichaUA(fichaUA);

    			Ficha fichasend = obtenerFicha( simpleFichaUA.getIdFicha() );
    			Actualizador.actualizar(fichasend);
    			
    			//SOLR Indexar ficha.
    			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
    		    solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_FICHA.toString(), ficha.getId(), 1l);

    		    
    		}

    		if ( session != null ) {

    			session.flush();
    			session.refresh(ua);

    		}

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}

    }


    // Clase de apoyo para uso en "crearSeccionesFichas"
    private class SimpleSeccion {

    	private Long idSeccion;

    	public SimpleSeccion( Long idSeccion) {
    		this.idSeccion = idSeccion;
    	}

    	public Long getIdSeccion() { 
    		return idSeccion; 
    	}

    }


    // Clase de apoyo para uso en "crearSeccionesFichas"
    private class SimpleFichaUA {

    	public SimpleFichaUA(Long idSeccion, Long idFicha, Long ordenSeccion) {

    		this.idSeccion = idSeccion;
    		this.idFicha = idFicha;
    		this.ordenSeccion = ordenSeccion;

    	}

    	private Long idSeccion;
    	private Long idFicha;
    	private Long ordenSeccion;

    	public Long getIdSeccion() {
    		return idSeccion; 
    	}

    	public Long getIdFicha() { 
    		return idFicha; 
    	}

    	public Long getOrdenSeccion() { 
    		return ordenSeccion; 
    	}

    	public boolean equals( Object o ) {

    		if ( !( o instanceof SimpleFichaUA ) ) 
    			return false;

    		return getIdSeccion().equals( ((SimpleFichaUA) o).getIdSeccion() ) && getIdFicha().equals( ((SimpleFichaUA) o).getIdFicha() );

    	}

    }


    /**
     * Borrar varias fichas Unidad administrativa
     * @throws DelegateException 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarFichasUAdeFicha(List<FichaUA> fichasUA) throws DelegateException {

    	Session session = getSession();

    	try {
    		if (fichasUA.size() < 1) {
    			return;
    		}

    		for (FichaUA fua : fichasUA) {
    			if (!getAccesoManager().tieneAccesoFichaUnidad(fua.getId())) {
    				throw new SecurityException("No tiene acceso al documento");
    			}
    		}

    		StringBuilder ids = new StringBuilder();
    		for (FichaUA fua : fichasUA) {
    			FichaUA fichaUA = (FichaUA) session.load(FichaUA.class, fua.getId());
    			
    			
    			if (ids.length() == 0) {
    				ids.append(fichaUA.getId());
    			} else {
    				ids.append(",").append(fichaUA.getId());
    			}
    		}

    		 session.delete("from FichaUA as fua where fua.id in (" + ids.toString() + ")");
    		session.flush();
    		
    		
    		
    	
    		// Marcamos para reindexar las fichas
    		for (FichaUA fua : fichasUA) {
    			session.refresh(fua.getFicha());
    			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, fua.getFicha().getId(), false);    			
    		}

    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    	
    }


    /**
     * Devuelve una ficha según una fichaUA
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Ficha obtenerFichaDeFichaUA(Long idFichaUA) {
    	
    	Session session = getSession();
    	try {
    		FichaUA fichaUA = (FichaUA) session.load(FichaUA.class, idFichaUA);
    		return fichaUA.getFicha();

    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    	
    }
    
    
    /**
     * Actualiza los enlaces de una ficha.
     * 
     * @param id Identificador de la ficha afectada.
     * @param enlacesNuevos Lista de enlaces que se quieren asociar a la ficha.
     * @param enlacesAEliminar Lista de enlaces anteriormente asociados a la ficha y que se quieren borrar.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void actualizaEnlacesFicha(Long id, List<Enlace> enlacesNuevos, List<Enlace> enlacesAEliminar) {

    	try {

    		if ( !getAccesoManager().tieneAccesoFicha(id) )
    			throw new SecurityException("No tiene acceso a la ficha");

    		EnlaceDelegate enllasDelegate = DelegateUtil.getEnlaceDelegate();
			
			// Los insertamos/actualizamos en la base de datos.
    		for (Enlace e : enlacesNuevos) {
    			enllasDelegate.grabarEnlace(e, null, id);
    		}
			
			// Eliminamos los que han quedado en la lista.
			for (Enlace e : enlacesAEliminar) {
				if (e != null)
					enllasDelegate.borrarEnlace(e.getId());
			}

    	} catch (DelegateException de) {

    		throw new EJBException(de);

    	}

    }    	


	
    /**
	 * Metodo para indexar un solrPendiente. Según la documentación, se almacena lo siguiente: <br /><br />
	 * <table style="border: 1px solid black;">
	 * 	<tr><td>Campo    			</td><td> Valor</td></tr>
	 * 	<tr><td>Id 					</td><td> FCH + Ficha.id</td></tr>
	 * 	<tr><td>Categoria			</td><td> FCH</td></tr>
	 * 	<tr><td>AplicacionId		</td><td> ROLSAC</td></tr>
	 * 	<tr><td>Titulo				</td><td> Ficha.traduccion.titulo</td></tr>
	 * 	<tr><td>Descripcion			</td><td> Ficha.traduccion.descripcion</td></tr>
	 * 	<tr><td>Url					</td><td> Si Ficha.traduccion .url  no es nulo:  "/govern/sac/fitxaRedirect.do?codi="+idFicha+"&lang="+idioma
	 * 			Si Ficha.traduccion .url  es nulo: 
	 * 			"/govern/sac/fitxa.do?codi="+ idFicha + "&coduo=" + idUA + "&lang=" + idioma
	 * 			idUA : se usará placeholder de forma que se establecerá valor por defecto (UA primera sección). A la hora de devolver el resultado se buscará en los campos PathUo si hay algún match con la UA especificada en el filtro. Si hubiese match, se sustituirá placeholder por la UA del filtro. Este tratamiento del placeholder se realizará en el API intermedia, siendo transparente a la aplicación.</td></tr>
	 * 	<tr><td>PadreCategoria		</td><td> </td></tr>
	 * 	<tr><td>PadreDescripcion	</td><td> </td></tr>
	 * 	<tr><td>PadreUrl			</td><td> </td></tr>
	 * 	<tr><td>FechaActualizacion	</td><td> Ficha.fechaActualizacion</td></tr>
	 * 	<tr><td>FechaPublicacion	</td><td> Ficha.fechaPublicacion</td></tr>
	 * 	<tr><td>FechaCaducidad		</td><td> Ficha.fechaCaducidad</td></tr>
	 * 	<tr><td>FechaPlazoIni		</td><td> </td></tr>
	 * 	<tr><td>FechaPlazoFin		</td><td> </td></tr>
	 * 	<tr><td>FotoUrl				</td><td> "/govern/rest/arxiu/" + Ficha.imagen</td></tr>
	 * 	<tr><td>Extension			</td><td> </td></tr>
	 * 	<tr><td>PathUo				</td><td> Path Unidades Administrativas de las secciones asociadas  (múltiple)</td></tr>
	 * 	<tr><td>FamiliaId			</td><td> </td></tr>
	 * 	<tr><td>MateriaId			</td><td> Id materia asociada (múltiple)</td></tr>
	 * 	<tr><td>PublicoId			</td><td> Id publico objetivo (múltiple)</td></tr>
	 * 	<tr><td>Telemático			</td><td> </td></tr>
	 * 	<tr><td>MicrositeId			</td><td> </td></tr>
	 * 	<tr><td>Interno				</td><td> </td></tr>
	 * 	<tr><td>SearchText 			</td><td> Ficha.traduccion.titulo + Ficha.traduccion.descAbr  + Ficha.traduccion.descripcion</td></tr>
	 * 	<tr><td>SearchTextOptional	</td><td> materias / hechos vitales (nombre, descripción y palabras clave) + uo’s (nombres descendientes)</td></tr>
	 * </table>
	 * @param solrPendiente
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		return indexarSolr(solrIndexer, solrPendiente.getIdElemento(), EnumCategoria.ROLSAC_FICHA);
	}
	
	 /**
     * Obtener ficha con lo mínimo imprescindible para solr.
     * 
     * @param id	Identificador de la ficha solicitada
     * 
     * @return Devuelve <code>Ficha</code> solicitada.
     * @throws DelegateException 
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     */
	public Ficha obtenerFichaParaSolr(Long id) {

		Session session = getSession();
		Ficha ficha = null;
		try {
		    ficha = (Ficha) session.get(Ficha.class, id);
		    if (ficha != null) {
				Hibernate.initialize(ficha.getMaterias());
				Hibernate.initialize(ficha.getHechosVitales());
				Hibernate.initialize(ficha.getPublicosObjetivo());
				Hibernate.initialize(ficha.getFichasua());
				Hibernate.initialize(ficha.getDocumentos());
				
				session.clear();
				
		    }

		} catch (HibernateException he) {
		    log.error("Error cargando info de la ficha con id " + id, he);
		} finally {
		    close(session);
		}

		return ficha;
    }
	
	/**
	 * Método para indexar según la id y la categoria. 
	 * @param solrIndexer
	 * @param idElemento
	 * @param categoria
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("FichafacadeEJB.indexarSolr. idElemento:" + idElemento +" categoria:"+categoria);
		
		String pasoIndexacion = "inicio indexacion";
		
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			pasoIndexacion = "recuperar ficha";
			final Ficha ficha = obtenerFichaParaSolr(idElemento);
			if (ficha == null) {
				log.error("No se puede obtener la ficha con id: " + idElemento);
				return new SolrPendienteResultado(false, "No se puede obtener la ficha con id: " + idElemento);
			}
			
			pasoIndexacion = "verificar si indexable";
			boolean isIndexable = IndexacionUtil.isIndexable(ficha);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria de tipo ficha.
			pasoIndexacion = "establecer info basica";
			final IndexData indexData = new IndexData();
			indexData.setCategoria(categoria);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());
			
			pasoIndexacion = "calcular path uo";
			List<PathUO> pathUOsFicha = IndexacionUtil.calcularPathUOsFicha(ficha);
			if (pathUOsFicha.size() <= 0) {
				return new SolrPendienteResultado(true, "No se puede indexar: no cuelga de UAs visibles");
			}
			indexData.setUos(pathUOsFicha);
			
			//Iteramos las traducciones
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral searchText = new MultilangLiteral();
			final MultilangLiteral searchTextOptional = new MultilangLiteral();
			final List<EnumIdiomas> idiomas = new ArrayList<EnumIdiomas>();
			
			// Obtenemos primera UA
			pasoIndexacion = "calcular primera ua";
			UnidadAdministrativa primeraUA = IndexacionUtil.calcularPrimeraUAFicha(ficha);	
			
			//Recorremos las traducciones
			pasoIndexacion = "establecer traducciones";
			for (String keyIdioma : ficha.getTraduccionMap().keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionFicha traduccion = (TraduccionFicha) ficha.getTraduccion(keyIdioma);
		    	
				if (traduccion != null && enumIdioma != null) {
					
					//Para saltarse los idiomas sin titulo.
					if ((traduccion.getTitulo() == null || traduccion.getTitulo().isEmpty())) {
						continue;
					}
					
					//Anyadimos idioma al enumerado.
					idiomas.add(enumIdioma);
					
					//Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search text.
					pasoIndexacion = "establecer Titulo, Descripción y el search text " + keyIdioma;
					titulo.addIdioma(enumIdioma, traduccion.getTitulo());
			    	descripcion.addIdioma(enumIdioma, solrIndexer.htmlToText(traduccion.getDescAbr()));
			    	searchText.addIdioma(enumIdioma, traduccion.getTitulo()+ " "+ solrIndexer.htmlToText(traduccion.getDescAbr()) + " "+ solrIndexer.htmlToText(traduccion.getDescripcion()));
			    	
			    	//StringBuffer que tendrá el contenido a agregar en textOptional
			    	final StringBuffer textoOptional = new StringBuffer();
					
			    	//Materia
			    	pasoIndexacion = "establecer text optional materias " + keyIdioma;
			    	for(Materia materia : ficha.getMaterias()) {
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
			    	
			    	//hechos vitales
			    	pasoIndexacion = "establecer text optional hechos vitales " + keyIdioma;
					for(HechoVital hecho : ficha.getHechosVitales()) {
						final TraduccionHechoVital traduccionHechoVital =  (TraduccionHechoVital) hecho.getTraduccion(keyIdioma);
						if (traduccionHechoVital != null) {
							textoOptional.append(" ");
							textoOptional.append(traduccionHechoVital.getNombre());
							textoOptional.append(" ");
							textoOptional.append(traduccionHechoVital.getDescripcion());
							textoOptional.append(" ");
							textoOptional.append(traduccionHechoVital.getPalabrasclave());
						}
					}
					
					//Unidades administrativas de las fichas.
					pasoIndexacion = "establecer text optional uos hechos vitales " + keyIdioma;
					textoOptional.append(IndexacionUtil.calcularPathTextUOsFicha(ficha, keyIdioma));
					textoOptional.append(" ");
					
			    	searchTextOptional.addIdioma(enumIdioma, textoOptional.toString());
			    	
			    	pasoIndexacion = "establecer url " + keyIdioma;
			    	if (traduccion.getUrl() == null || traduccion.getUrl().isEmpty()) {
			    		String idUA = "{#UA:"+primeraUA.getId()+"}";
			    		urls.addIdioma(enumIdioma, "/govern/sac/fitxa.do?codi="+ ficha.getId() + "&coduo=" + idUA + "&lang=" + keyIdioma);	    		
			    	} else {
			    		urls.addIdioma(enumIdioma, "/govern/sac/fitxaRedirect.do?codi="+ficha.getId()+"&lang="+keyIdioma);
			    	}
				}
			}
			
			//Seteamos datos multidioma.
			pasoIndexacion = "Seteamos datos multidioma";
			indexData.setTitulo(titulo);
			indexData.setDescripcion(descripcion);
			indexData.setUrl(urls);
			indexData.setSearchText(searchText);
			indexData.setSearchTextOptional(searchTextOptional);
			indexData.setIdiomas(idiomas);
			
			//Datos IDs materias.
			pasoIndexacion = "Seteamos id materias";
			final List<String> materiasId = new ArrayList<String>();		
			for(Materia materia : ficha.getMaterias()) {
				materiasId.add(materia.getId().toString());
			}
			indexData.setMateriaId(materiasId);
			
			//Datos IDs publico Objetivos.
			pasoIndexacion = "Seteamos id publico Objetivo";
	    	final List<String> publicoObjetivoId = new ArrayList<String>();		
			for( PublicoObjetivo publicoObjectivo :  ficha.getPublicosObjetivo()) {
				publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);
			
			//Datos extras
			pasoIndexacion = "Seteamos imagen";
			if (ficha.getImagen() != null) {
				indexData.setUrlFoto("/govern/rest/arxiu/" + ficha.getImagen().getId());
			}
			
			//Fechas
			pasoIndexacion = "Seteamos fechas";
			indexData.setFechaActualizacion(ficha.getFechaActualizacion());
			indexData.setFechaPublicacion(ficha.getFechaPublicacion());
			indexData.setFechaCaducidad(ficha.getFechaCaducidad());
			indexData.setInterno(false);
			
			pasoIndexacion = "indexar contenido";
			solrIndexer.indexarContenido(indexData);
			pasoIndexacion = "indexado contenido";
			
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en fichafacade intentando indexar. idElemento:" + idElemento +" categoria:"+categoria + "(Paso indexacion: " + pasoIndexacion + ")", exception);			
			
			String stackTrace = ExceptionUtils.getStackTrace(exception);
			if (StringUtils.isEmpty(stackTrace)) {
				stackTrace = "No se ha podido extraer la traza de la excepcion (excepcion: " + exception.getClass().getName() + " - message: " + exception.getMessage() + ")";			
			}
			
			return new SolrPendienteResultado(false, stackTrace);

		}
	}
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		try {
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(), EnumCategoria.fromString(solrPendiente.getTipo()));
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en fichafacade intentando desindexar.", exception);			
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}
	
	/**
	 * Busca los ids de las fichas
	 * 
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 * @return
	 */
	public List<Long> buscarIdsFichas() {
		Session session = getSession();

    	try {

    		StringBuilder consulta = new StringBuilder("select ficha.id from Ficha as ficha ");
    		
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
     * Calcula el número de relaciones ficha-ua
     *  @param	idFicha	Identificador de una ficha
     *  @return Número de relaciones ficha-ua
     * 	@ejb.interface-method
     * 	@ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Integer comprobarRelacionFicha(Long idFicha) {

    	Session session = getSession();
    	Integer numRelaciones = 0;

    	try {
    		
    		StringBuilder consulta = new StringBuilder("select count( ficUA.ficha ) from  FichaUA as ficUA  where ficUA.ficha= :id");
			

			Query query = session.createQuery( consulta.toString() );
			query.setParameter("id", idFicha);
			
			numRelaciones = (Integer) query.uniqueResult();
    		

    		return numRelaciones;

    	} catch (HibernateException e) {
    		
    		throw new EJBException(e);
    		
    	} finally {
    		
    		close(session);
    		
    	}
    	
    }
   
}
