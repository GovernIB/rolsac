package org.ibit.rol.sac.persistence.ejb;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import java.util.StringTokenizer;

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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
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
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Remoto;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.Cadenas;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.persistence.lucene.analysis.AlemanAnalyzer;
import es.caib.rolsac.persistence.lucene.analysis.CastellanoAnalyzer;
import es.caib.rolsac.persistence.lucene.analysis.CatalanAnalyzer;
import es.caib.rolsac.persistence.lucene.analysis.InglesAnalyzer;
import es.caib.rolsac.persistence.lucene.model.Catalogo;
import es.caib.rolsac.persistence.lucene.model.ModelFilterObject;
import es.caib.rolsac.persistence.lucene.model.TraModelFilterObject;
import es.caib.rolsac.utils.ResultadoBusqueda;

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
public abstract class FichaFacadeEJB extends HibernateEJB {

    private static final long serialVersionUID = 4454278347074939459L;

    protected Hashtable contenidos_web; // contiene url y su contenido para agilizar el proceso de indexacion de fichas

    /**
     * Obtiene referencia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * Ubicación del directorio de Lucene a utilizar.
     * @ejb.env-entry value="${index.crawler.location}"
     */
    protected String indexCrawlerLocation;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
    	super.ejbCreate();
    }

    /**
     * Autoriza la creaci�n de una ficha
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaCrearFicha(Integer validacionFicha) throws SecurityException  {
    	return !(validacionFicha.equals(Validacion.PUBLICA) && !userIsSuper()); 
    }


    /**
     * Autoriza la modificaci�n ficha
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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarFicha(Ficha ficha) {

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
     * Busca todas las fichas que cumplen los criterios de busqueda del nuevo back (sacback2). 
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
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarFicha(Long id) {

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
     * 	@ejb.interface-method
     * 	@ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long crearFichaUA2(Long idUA, Long idSeccion, Long idFicha) {

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
    		indexBorraFicha(ficha.getId()) ;
    		indexInsertaFicha(fichasend, null);
    		session.flush();

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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarFichaUA(Long id) {

    	Session session = getSession();

    	try {

    		if (!getAccesoManager().tieneAccesoFichaUnidad(id)) {
    			throw new SecurityException("No tiene acceso a la relaci�n");
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
    		indexBorraFicha(ficha.getId());
    		indexInsertaFicha(ficha,null);

    		if(borrar)
    			log.debug("Entro en borrar remoto ficha UA");
    		//Actualizador.borrar(new FichaUATransferible(idUA,idFicha,ceSeccion));

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


    /**
     * Metodo que obtiene un bean con el filtro para la indexacion
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public ModelFilterObject obtenerFilterObject(Ficha ficha) {

    	//TODO: Sería conveniente optimizar este método.

    	ModelFilterObject filter = new ModelFilterObject();
    	Session session = getSession();

    	try {
    		//de momento, familia y microsites a null
    		filter.setFamilia_id(null);    	
    		filter.setMicrosite_id(null);

    		Iterator iterlang = ficha.getLangs().iterator();
    		while (iterlang.hasNext()) {
    			String idioma = (String) iterlang.next();
    			StringBuilder txids =  new StringBuilder(Catalogo.KEY_SEPARADOR);
    			StringBuilder txtexto = new StringBuilder(" ");//espacio en blanco, que es para tokenizar
    			Iterator iter;

    			TraModelFilterObject trafilter = new TraModelFilterObject();

    			//titulo, de momento vacio			
    			trafilter.setMaintitle(null);

    			//OBTENER LAS UO (en el caso de las fichas hay que recogerlas de la relacion con seccion)
    			Hashtable hsids = new Hashtable(); //para controlar que no se repitan ids
    			Hashtable hslistapadres = new Hashtable(); //para controlar que no se repitan llamadas al delegate

    			if (ficha.getFichasua() != null) {
    				iter = ficha.getFichasua().iterator();
    				while (iter.hasNext()) {
    					FichaUA fichaua = (FichaUA) iter.next();
    					if (!hsids.containsKey("" + fichaua.getUnidadAdministrativa().getId().longValue())) {
    						txids.append(fichaua.getUnidadAdministrativa().getId());
    						txids.append(Catalogo.KEY_SEPARADOR);

    						if (((TraduccionUA) fichaua.getUnidadAdministrativa().getTraduccion(idioma) ) != null) {
    							String nombreUA = ((TraduccionUA) fichaua.getUnidadAdministrativa().getTraduccion(idioma)).getNombre();
    							// Espacio en blanco, que es para tokenizar
    							txtexto.append(nombreUA != null ? nombreUA.concat(" ") : " ");
    						}

    						hsids.put("" + fichaua.getUnidadAdministrativa().getId().longValue(), "" + fichaua.getUnidadAdministrativa().getId().longValue());
    					}

    					if (!hslistapadres.containsKey( "" + fichaua.getUnidadAdministrativa().getId().longValue())) {
    						List listapadres = DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa(fichaua.getUnidadAdministrativa().getId());
    						UnidadAdministrativa ua = null;
    						for (int x = 1; x < listapadres.size(); x++) {
    							ua = (UnidadAdministrativa) listapadres.get(x);
    							if (!hsids.containsKey("" + ua.getId().longValue())) {
    								txids.append(ua.getId());
    								txids.append(Catalogo.KEY_SEPARADOR);
    								if (((TraduccionUA) ua.getTraduccion(idioma)) != null) {
    									String nombreUA = ((TraduccionUA) ua.getTraduccion(idioma)).getNombre();
    									// Espacio en blanco, que es para tokenizar
    									txtexto.append(nombreUA != null ? nombreUA.concat(" ") : " ");
    								}

    								hsids.put("" + ua.getId().longValue(), "" + ua.getId().longValue());
    							}
    						}

    						hslistapadres.put("" + fichaua.getUnidadAdministrativa().getId().longValue(),"" + fichaua.getUnidadAdministrativa().getId().longValue());
    					}
    				}
    			}

    			filter.setUo_id(txids.length() == 1 ? null : txids.toString());
    			trafilter.setUo_text(txtexto.length() == 1 ? null : txtexto.toString());

    			//OBTENER LAS SECCIONES (en el caso de las fichas hay que recogerlas de la relacion con seccion. Solo ponemos la seccion propia)
    			txids = new StringBuilder(Catalogo.KEY_SEPARADOR);
    			txtexto = new StringBuilder(" ");

    			if (ficha.getFichasua() != null) {
    				iter = ficha.getFichasua().iterator();
    				boolean primer = true;
    				while (iter.hasNext()) {
    					FichaUA fichaua = (FichaUA) iter.next();
    					txids.append(fichaua.getSeccion().getId());
    					txids.append(Catalogo.KEY_SEPARADOR);
    					if (fichaua.getSeccion().getTraduccion(idioma) != null) {
    						String nombreSeccion = ((TraduccionSeccion) fichaua.getSeccion().getTraduccion(idioma)).getNombre(); 
    						txtexto.append(nombreSeccion != null ? nombreSeccion.concat(" ") : " ");
    					}

    					if (primer) {
    						//como titulo del servicio principal ponemos únicamente la primera seccion que pillamos
    						String txUA = "";
    						String txSeccion = "";
    						String txMaintitle = "";

    						if (fichaua.getSeccion().getTraduccion(idioma) != null) {
    							if (((TraduccionUA) fichaua.getUnidadAdministrativa().getTraduccion(idioma)) != null) {
    								String nombreUA = ((TraduccionUA) fichaua.getUnidadAdministrativa().getTraduccion(idioma)).getNombre();
    								txUA = (nombreUA != null) ? nombreUA.concat(" ") : " ";
    							}

    							String nombreSeccion = ((TraduccionSeccion) fichaua.getSeccion().getTraduccion(idioma)).getNombre(); 
    							txSeccion = (nombreSeccion != null) ? nombreSeccion.concat(" ") : " ";

    							if (txUA.length() > 1) {
    							    txMaintitle = txUA.substring(0, 1).toUpperCase() + txUA.substring(1, txUA.length() - 1).toLowerCase();
    							}

    							txMaintitle += " / ";
    							txMaintitle += txSeccion;
    							trafilter.setMaintitle(txMaintitle);

    						} else if (fichaua.getSeccion().getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault")) != null) {
    							if (((TraduccionUA) fichaua.getUnidadAdministrativa().getTraduccion(idioma)) != null) {
    								String nombreUA = ((TraduccionUA) fichaua.getUnidadAdministrativa().getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault"))).getNombre(); 
    								txUA = (nombreUA != null) ? nombreUA.concat(" ") : " ";
    							}

    							String nombreSeccion = ((TraduccionSeccion) fichaua.getSeccion().getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault"))).getNombre();
    							txSeccion = (nombreSeccion != null) ? nombreSeccion.concat(" ") : " ";

    							if (txUA.length() > 1) {
    							    txMaintitle = txUA.substring(0, 1).toUpperCase() + txUA.substring(1, txUA.length() - 1).toLowerCase();
    							}

    							txMaintitle += " / ";
    							txMaintitle += txSeccion;
    							trafilter.setMaintitle(txMaintitle);

    						} else {
    							trafilter.setMaintitle("");
    						}

    						primer = false;
    					}
    				}
    			}

    			filter.setSeccion_id(txids.length() == 1 ? null : txids.toString());
    			trafilter.setSeccion_text((txtexto.length() == 1) ? null : txtexto.toString());

    			//OBTENER LAS MATERIAS (ademas de las materias se ponen los textos de los HECHOS VITALES)
    			txids = new StringBuilder(Catalogo.KEY_SEPARADOR);
    			txtexto = new StringBuilder(" ");

    			if (ficha.getMaterias() != null) {
    				iter = ficha.getMaterias().iterator();
    				while (iter.hasNext()) {
    					Materia materia = (Materia) iter.next();
    					txids.append(materia.getId());
    					// Anayadir los ids (los de los hechos vitales no)
    					txids.append(Catalogo.KEY_SEPARADOR);

    					if (materia.getTraduccion(idioma) != null) {
    						String nombreMateria = ((TraduccionMateria) materia.getTraduccion(idioma)).getNombre();
    						String descripcionMateria = ((TraduccionMateria) materia.getTraduccion(idioma)).getDescripcion();
    						String palabrasClave = ((TraduccionMateria) materia.getTraduccion(idioma)).getPalabrasclave();

    						txtexto.append(nombreMateria != null ? nombreMateria.concat(" ") : " ");
    						txtexto.append(descripcionMateria != null ? descripcionMateria.concat(" ") : " ");
    						txtexto.append(palabrasClave != null ? palabrasClave.concat(" ") : " ");
    					}
    				}
    			}

    			if (ficha.getMaterias() != null) {
    				iter = ficha.getHechosVitales().iterator();
    				while (iter.hasNext()) {
    					HechoVital hvital = (HechoVital) iter.next();

    					if (hvital.getTraduccion(idioma) != null) {
    						String nombreHechoVital = ((TraduccionHechoVital) hvital.getTraduccion(idioma)).getNombre(); 
    						String descripcionHechoVital = ((TraduccionHechoVital) hvital.getTraduccion(idioma)).getDescripcion();
    						String palabrasClaveHechoVital = ((TraduccionHechoVital) hvital.getTraduccion(idioma)).getPalabrasclave();

    						txtexto.append(nombreHechoVital != null ? nombreHechoVital.concat(" ") : " ");
    						txtexto.append(descripcionHechoVital != null ? descripcionHechoVital.concat(" ") : " ");
    						txtexto.append(palabrasClaveHechoVital != null ? palabrasClaveHechoVital.concat(" ") : " ");
    					}
    				}
    			}

    			filter.setMateria_id(txids.length() == 1 ? null : txids.toString());
    			trafilter.setMateria_text(txtexto.length() == 1 ? null : txtexto.toString());

    			filter.addTraduccion(idioma, trafilter);
    		}

    	} catch (DelegateException e) {
    		throw new EJBException(e);
    	} finally {
    		close(session);
    	}

    	return filter;
    }


    /**
     * Añade la ficha al índice en todos los idiomas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaFicha(Ficha ficha,  ModelFilterObject filter)  {

    	try {
    		if (filter == null) {
    		    filter = obtenerFilterObject(ficha);
    		}

    		IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();

    		// Obtenemos los documentos de la ficha si tiene, para evitar tener que cargarlos en cada idioma
    		List<Documento> listaDocumentos = obtenerListaDocumentos(ficha);

    		Iterator<String> iterator = ficha.getLangs().iterator();
    		while (iterator.hasNext()) {
    			String idi = iterator.next();

    			// Configuración del writer
    			Directory directory = indexerDelegate.getHibernateDirectory(idi);
    			IndexWriter writer = new IndexWriter(directory, getAnalizador(idi), false, MaxFieldLength.UNLIMITED);
    			writer.setMergeFactor(20);
                writer.setMaxMergeDocs(Integer.MAX_VALUE);

    			IndexObject io = new IndexObject();

    			io = indexarContenidos(ficha, io, filter);

    			io = indexarTraducciones(idi, ficha, io, filter);

    			//No se añaden todos los documentos en todos los idiomas. Ahora en el idioma actual.
    			io = indexarContenidosDocumentos(ficha, idi, writer, io, listaDocumentos);

    			if (io.getText().length() > 0 || io.getTextopcional().length() > 0) {
    			    indexerDelegate.insertaObjeto(io, idi, writer);
    			}

    			writer.close();
    			directory.close();
    		}

    	} catch (Exception ex) {
    		log.warn("[indexInsertaFicha:" + ficha.getId() + "] No se ha podido indexar ficha. " + ex.getMessage());
    	}
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
                Documento doc = documentoDelegate.obtenerDocumento(documento.getId());
                listaDocumentos.add(doc);
            }
        }

        return listaDocumentos;
    }


    private IndexObject indexarContenidos(Ficha ficha, IndexObject io, ModelFilterObject filter) {

        io.setId(Catalogo.SRVC_FICHAS + "." + ficha.getId());
        io.setClasificacion(Catalogo.SRVC_FICHAS);
        io.setMicro(filter.getMicrosite_id());
        io.setDescripcion("");

        if (filter.getUo_id() != null) {
            io.setUo(filter.getUo_id());
        }

        if (filter.getMateria_id() != null) {
            io.setMateria(filter.getMateria_id());
        }

        if (filter.getFamilia_id() != null) {
            io.setFamilia(filter.getFamilia_id());
        }

        if (filter.getSeccion_id() != null) {
            io.setSeccion(filter.getSeccion_id());
        }

        io.setCaducidad("");
        if (ficha.getFechaCaducidad() != null) {
            io.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaCaducidad()));
        }

        if (ficha.getFechaPublicacion() != null) {
            io.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaPublicacion()));
        } else {
            io.setPublicacion("");
        }

        // Se crea la indexación del foro como documento individual y se añade la información para la indexación de la ficha.
        io.setConforo((ficha.getUrlForo() != null && ficha.getUrlForo().length() > 0) ? "S" : "N");

        return io;
    }


    private IndexObject indexarTraducciones(String idioma, Ficha ficha, IndexObject io, ModelFilterObject filter) throws UnsupportedEncodingException {

        TraduccionFicha trad = ((TraduccionFicha) ficha.getTraduccion(idioma));
        if (trad != null) {
            if ((trad.getUrl() != null ) && (trad.getUrl().length() > 0)) {
                io.setUrl("/govern/estadistica?tipus=F&codi=" + ficha.getId() + "&url=" + java.net.URLEncoder.encode(trad.getUrl(),"UTF-8"));
            } else {
                io.setUrl("/govern/sac/fitxa.do?lang=" + idioma + "&codi=" + ficha.getId() + "&coduo=" + obtenerUO_Principal(io.getUo()));
            }

            io.setTituloserviciomain(filter.getTraduccion(idioma).getMaintitle());

            if (trad.getTitulo() != null && trad.getTitulo().length() > 0) {
                io.setTitulo(trad.getTitulo());
                io.addTextLine(trad.getTitulo());
                if (trad.getDescAbr() != null) {
                    io.addTextLine(trad.getDescAbr());
                }

            } else {
                TraduccionFicha trad_ca = ((TraduccionFicha) ficha.getTraduccion("ca"));
                if (trad_ca.getTitulo() != null) {
                    io.setTitulo(trad_ca.getTitulo());
                }
            }

            if (trad.getDescripcion() != null) {
                io.addTextLine(trad.getDescripcion());  
                io.setDescripcion(trad.getDescripcion());
            }

            io.addTextopcionalLine(filter.getTraduccion(idioma).getMateria_text());
            io.addTextopcionalLine(filter.getTraduccion(idioma).getSeccion_text());
            io.addTextopcionalLine(filter.getTraduccion(idioma).getUo_text());
        }

        return io;
    }


    private IndexObject indexarContenidosDocumentos(Ficha ficha, String idioma, IndexWriter writer, IndexObject io, List<Documento> listaDocumentos) throws DelegateException {

        if (ficha.getDocumentos() != null) {
            IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();

            Iterator<Documento> iterdocs = listaDocumentos.iterator();
            while (iterdocs.hasNext()) {
                Documento documento = iterdocs.next();

                /* Se crea la indexación del documento individual y se añade la información para la indexación de la ficha.*/
                IndexObject ioDoc = new IndexObject();
                String textDoc = null;
                Archivo arch = new Archivo();

                if (documento.getTraduccion(idioma) != null) {
                    io.addTextLine(((TraduccionDocumento) documento.getTraduccion(idioma)).getTitulo());
                    io.addTextLine(((TraduccionDocumento) documento.getTraduccion(idioma)).getDescripcion());

                    if (((TraduccionDocumento) documento.getTraduccion(idioma)).getArchivo() != null) {
                        arch = (Archivo) ((TraduccionDocumento) documento.getTraduccion(idioma)).getArchivo();
                        ioDoc.addArchivo(arch);
                    }
                }

                textDoc = ioDoc.getText();

                if (textDoc != null && textDoc.length() > 0 && arch != null) {
                    if (documento.getTraduccion(idioma) != null) {

                        ioDoc.setId(Catalogo.SRVC_FICHAS_DOCUMENTOS + "." + documento.getId());
                        ioDoc.setClasificacion(Catalogo.SRVC_FICHAS + "." + ficha.getId());
                        ioDoc.setCaducidad("");
                        ioDoc.setDescripcion("");
                        ioDoc.setUrl("/fitxer/get?codi=" + arch.getId());
                        ioDoc.setTituloserviciomain(io.getTitulo());
                        ioDoc.setDescripcion(((TraduccionDocumento) documento.getTraduccion(idioma)).getDescripcion());
                        ioDoc.setText(textDoc);

                        ioDoc.addTextLine(((TraduccionDocumento) documento.getTraduccion(idioma)).getDescripcion());
                        ioDoc.addTextLine(arch.getNombre());

                        if (ficha.getFechaPublicacion() != null) {
                            ioDoc.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaPublicacion())); 
                        } else {
                            ioDoc.setPublicacion("");
                        }

                        if (((TraduccionDocumento) documento.getTraduccion(idioma)).getTitulo() == null) {
                            ioDoc.setTitulo(((TraduccionDocumento) documento.getTraduccion("ca") ).getTitulo() + ", (" + arch.getMime().toUpperCase() + ")");
                        } else {
                            ioDoc.setTitulo(((TraduccionDocumento) documento.getTraduccion(idioma)).getTitulo() + ", (" + arch.getMime().toUpperCase() +")");
                        }

                        if (ficha.getFechaCaducidad() != null) {
                            ioDoc.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaCaducidad()));
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
     * Elimina la ficha en el índice en todos los idiomas
     * @param id	Identificador de una ficha
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexBorraFicha(Long id)  {

        try {
            IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
            List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
            for (String lang : langs) {
    		    indexerDelegate.borrarObjeto(Catalogo.SRVC_FICHAS + "." + id, "" + lang);
    		    indexerDelegate.borrarObjetosDependientes(Catalogo.SRVC_FICHAS + "." + id, "" + lang);
    		}

    	} catch (DelegateException ex) {
    		log.warn("[indexBorraFicha:" + id + "] No se ha podido borrar del indice la ficha. " + ex.getMessage());
    	}
    }


    /**
     * Añade la URL externa para un idioma, obteniendola de la ficha
     * El id de la URL será el id de la ficha
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaWEB_EXTERNA(Ficha ficha,  ModelFilterObject filter, String idi)  {

    	try {
    	    if (true) return;

    		IndexObject io = new IndexObject();

    		io.setId( Catalogo.SRVC_WEB_EXTERNA + "." + ficha.getId() );
    		io.setClasificacion(Catalogo.SRVC_WEB_EXTERNA);

    		io.setMicro( filter.getMicrosite_id() ); 
    		io.setUo( filter.getUo_id() );
    		io.setMateria( filter.getMateria_id() );
    		io.setFamilia( filter.getFamilia_id() );
    		io.setSeccion( filter.getSeccion_id() );
    		io.setCaducidad("");

    		if ( ficha.getFechaPublicacion() != null ) 
    			io.setPublicacion( new java.text.SimpleDateFormat("yyyyMMdd").format( ficha.getFechaPublicacion() ) ); 
    		else	
    			io.setPublicacion("");

    		io.setDescripcion("");

    		if ( ficha.getFechaCaducidad() != null ) 
    			io.setCaducidad( new java.text.SimpleDateFormat("yyyyMMdd").format( ficha.getFechaCaducidad() ) );

    		TraduccionFicha trad = ( (TraduccionFicha) ficha.getTraduccion(idi) );

    		if ( trad != null ) {

    			if ( (trad.getUrl()!=null) && (trad.getUrl().length()>0) ) {
    				io.setUrl( trad.getUrl() );

    				if ( trad.getTitulo() != null ) {

    					io.setTitulo( trad.getTitulo() );
    					io.addTextLine( trad.getTitulo() );

    				}

    				if ( trad.getDescAbr() != null )	
    					io.addTextLine( trad.getDescAbr() );


    				if ( trad.getDescripcion() != null )  {

    					io.addTextLine( trad.getDescripcion() );	
    					io.setDescripcion( trad.getDescripcion() );

    				}

    				io.addTextopcionalLine( filter.getTraduccion(idi).getMateria_text() );
    				io.addTextopcionalLine( filter.getTraduccion(idi).getSeccion_text() );
    				io.addTextopcionalLine( filter.getTraduccion(idi).getUo_text() );	

    				getWEB_EXTERNA ( trad.getUrl() );
    				Object contenido=contenidos_web.get( trad.getUrl() );

    				if ( contenido instanceof Archivo )
    					io.addArchivo( (Archivo) contenido );

    				if ( io.getText().length() > 0 ){}
//    					org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);

    			}

    		}

    	} catch (Exception ex) {

    		log.warn( "[indexInsertaWEB_EXTERNA:" + ficha.getId() + "] No se ha podido indexar la WEB_EXTERNA. " + ex.getMessage() );

    	}

    }


    /**
     * Elimina la URL Externa en el índice para un idioma
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexBorraWEB_EXTERNA(Ficha ficha, String idi) {

    	try {
    		DelegateUtil.getIndexerDelegate().borrarObjeto(Catalogo.SRVC_WEB_EXTERNA + "." + ficha.getId(), "" + idi);

    	} catch (DelegateException ex) {
    		log.warn("[indexBorraWEB_EXTERNA:" + ficha.getId() + "] No se ha podido borrar del indice la WEB_EXTERNA. " + ex.getMessage());
    	}
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


    /** Contienen el listado separado por # de las UO que contienen la ficha
	Debe devolver la primera de la lista*/
    private String obtenerUO_Principal(String listaUOs) {

    	if ( listaUOs == null ) 
    		return "";

    	if ( listaUOs.length() == 0 ) 
    		return "";

    	StringTokenizer uos = new StringTokenizer(listaUOs, Catalogo.KEY_SEPARADOR);
    	String primeraUA = "1";

    	if ( uos.hasMoreTokens() )
    		primeraUA = uos.nextToken();

    	return primeraUA;

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


    private static Analyzer getAnalizador(String idi) {

    	Analyzer analyzer;

    	if (idi.toLowerCase().equals("de")) {
    		analyzer = new AlemanAnalyzer();
    	} else if ( idi.toLowerCase().equals("en") ) {
    		analyzer = new InglesAnalyzer();
    	} else if ( idi.toLowerCase().equals("ca") ) {
    		analyzer = new CatalanAnalyzer();
    	} else {
    		analyzer = new CastellanoAnalyzer();
    	}

    	return analyzer;
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


    private void actualizaSeccionesFichaUA( UnidadAdministrativa ua, List<Long> listaBorrar, List<SimpleFichaUA> listaNuevas ) {

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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarFichasUAdeFicha(List<FichaUA> fichasUA) {

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
    		for (FichaUA fua : fichasUA) {
    			session.refresh(fua.getFicha());
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

}
