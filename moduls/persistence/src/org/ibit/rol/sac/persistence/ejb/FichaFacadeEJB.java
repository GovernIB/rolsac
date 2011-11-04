package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import net.sf.hibernate.Query;
import net.sf.hibernate.expression.Expression;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.ibit.lucene.analysis.AlemanAnalyzer;
import org.ibit.lucene.analysis.CastellanoAnalyzer;
import org.ibit.lucene.analysis.CatalanAnalyzer;
import org.ibit.lucene.analysis.InglesAnalyzer;
import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.FichaUATransferible;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
	
	protected Hashtable contenidos_web; // contiene url y su contenido para agilizar el proceso de indexacion de fichas
	
    /**
     * Obtiene refer�ncia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * Ubicaci� del directori de Lucene a emprar.
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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarFicha(Ficha ficha) {
        Session session = getSession();
        try {
        	Date FechaActualizacionBD = new Date();
            if (ficha.getId() == null) {
                if (ficha.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
                    throw new SecurityException("No puede crear una ficha p�blica");
                }
            } else {
                if (!getAccesoManager().tieneAccesoFicha(ficha.getId())) {
                    throw new SecurityException("No tiene acceso a la ficha");
                }
            	Ficha fichaBD = obtenerFicha(ficha.getId());
            	FechaActualizacionBD = fichaBD.getFechaActualizacion();            	
            }
            /* Se alimenta la fecha de actualizaci�n de forma autom�tica si no se ha introducido dato*/
            if (ficha.getFechaActualizacion() == null || DateUtils.fechasIguales(FechaActualizacionBD,ficha.getFechaActualizacion())) {
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
     * Obtiene una lista de fichas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarFichas(Map parametros, Map traduccion) {
        Session session = getSession();
        try {
            if (!userIsOper()) {
                parametros.put("validacion", Validacion.PUBLICA);
            }

            List params = new ArrayList();
            String sQuery = populateQuery(parametros, traduccion, params);

            // Eliminado "left join fetch" por problemas en el cache de traducciones. 
            Query query = session.createQuery("select distinct ficha from Ficha as ficha " +
                    ", ficha.traducciones as trad " +
                    "where " + sQuery);
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                query.setParameter(i, o);
            }

            List todas = query.list();
            // Filtramos por las fichas que el usuario no vaya a poder modificar
            List fichas_listadas = new ArrayList();
            for (int i=0;i<todas.size();i++) {
            	Ficha fic = (Ficha)todas.get(i);
            	if (getAccesoManager().tieneAccesoFicha(fic.getId())) 
            		fichas_listadas.add(fic);
            }
            
            return fichas_listadas;
            //return query.list();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una lista de fichas
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List buscarFichasHuerfanas(){
        Session session = getSession();
        try{
            Query query = session.createQuery("select distinct ficha from Ficha as ficha " +
                    "where not exists elements(ficha.fichasua)");
            return query.list();


        }catch(HibernateException he){
            throw new EJBException(he);
        }finally {
            close(session);
        }
    }

    /**
     * Obtiene una Ficha
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Ficha obtenerFicha(Long id) {
        Session session = getSession();
        
        
        Ficha ficha = null;
        try {
            ficha = (Ficha) session.load(Ficha.class, id);
            if (visible(ficha)) {
                Hibernate.initialize(ficha.getDocumentos());
                Hibernate.initialize(ficha.getEnlaces());
                Hibernate.initialize(ficha.getMaterias());
                Hibernate.initialize(ficha.getHechosVitales());
                Hibernate.initialize(ficha.getFichasua());
                Hibernate.initialize(ficha.getBaner());
                Hibernate.initialize(ficha.getImagen());
                Hibernate.initialize(ficha.getIcono());  
            } else {
            	throw new SecurityException("El usuario no tiene el rol operador");
        	}
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
                
   		ArrayList listaOrdenada = new ArrayList(ficha.getDocumentos());
		Comparator comp = new DocsFichaComparator();
	  	Collections.sort(listaOrdenada, comp);
	  	ficha.setDocumentos(listaOrdenada);                
        
   		ArrayList listaOrdenadaEnl = new ArrayList(ficha.getEnlaces());
		Comparator comp_enl = new EnlsFichaComparator();
	  	Collections.sort(listaOrdenadaEnl, comp_enl);
	  	ficha.setEnlaces(listaOrdenadaEnl);                 
        
        return ficha;
        
    }
    
    class DocsFichaComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Long x1; 
			if ((Documento)o1 == null) 	x1 = new Long("0");
			else  						x1=new Long (((Documento)o1).getOrden());
						
			Long x2; 
			if ((Documento)o2 == null) 	x2 = new Long("0");
			else 						x2=new Long (((Documento)o2).getOrden());
			
			return x1.compareTo( x2 ); 
		} 	
    }
    
    class EnlsFichaComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Long x1; 
			if ((Enlace)o1 == null)	x1 = new Long("0");
			else 					x1=new Long (((Enlace)o1).getOrden());
						
			Long x2; 
			if ((Enlace)o2 == null) x2 = new Long("0");
			else 					x2=new Long (((Enlace)o2).getOrden());
			
			return x1.compareTo( x2 ); 
		} 	
    }
    
    /**
     * Obtiene la imagen de una Ficha
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerImagenFicha(Long id) {
        Session session = getSession();
        try {
            Ficha ficha = (Ficha) session.load(Ficha.class, id);
            if (visible(ficha)) {
                Hibernate.initialize(ficha.getImagen());
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerIconoFicha(Long id) {
        Session session = getSession();
        try {
            Ficha ficha = (Ficha) session.load(Ficha.class, id);
            if (visible(ficha)) {
                Hibernate.initialize(ficha.getIcono());
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerBanerFicha(Long id) {
        Session session = getSession();
        try {
            Ficha ficha = (Ficha) session.load(Ficha.class, id);
            if (visible(ficha)) {
                Hibernate.initialize(ficha.getBaner());
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
     * Obtiene una lista de fichas segun materia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarFichasMateria(Long id) {
        Session session = getSession();
        try {
            String sQuery = "mat.id = " + id;
            Query query = session.createQuery("select distinct ficha from Ficha as ficha, ficha.materias as mat where " + sQuery);
            return query.list();
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
    public List buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, boolean uaFilles, boolean uaMeves) {        
        Session session = getSession();
        
        try {           
            if (!userIsOper()) {
                parametros.put("validacion", Validacion.PUBLICA);
            }

            List params = new ArrayList();
            String i18nQuery = "";
            String fetVitalQuery = "";            
            String materiaQuery = "";            
            String mainQuery = "select distinct ficha from Ficha as ficha , ficha.traducciones as trad, ficha.fichasua as fsua ";
            
            if (traduccion.get("idioma") != null) {
                i18nQuery = populateQuery(parametros, traduccion, params);
            } else {
                String paramsQuery = populateQuery(parametros, new HashMap(), params);
                if (paramsQuery.length() != 0) {
                    i18nQuery += paramsQuery + " and ";
                }
                i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ")"; // TODO: dejarlo asi o buscar textos traducidos en lucene?
            }
            
            Set<UnidadAdministrativa> uas = new HashSet<UnidadAdministrativa>();
            if (uaFilles) {
                uas.add(ua);
                ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua.getId());
                Hibernate.initialize(ua.getHijos());
                uas.addAll(ua.getHijos());
            } else if (uaMeves) {
                uas.addAll(getUsuario(session).getUnidadesAdministrativas());
            } else {
                uas.add(ua);
            }
            String uaQuery = " and fsua.unidadAdministrativa.id in (";
            for (Iterator<UnidadAdministrativa> it = uas.iterator(); it.hasNext();) {
                uaQuery += it.next().getId();
                if (it.hasNext()) {
                    uaQuery += ", ";
                } else {
                    uaQuery += ")";
                }
            }
            
            if(idFetVital != null){
              mainQuery += ",ficha.hechosVitales as hec ";  
              fetVitalQuery = " and hec.id = ? ";
              params.add(idFetVital);
            } 
            
            if(idMateria != null){
              mainQuery += ",ficha.materias as mat ";  
              materiaQuery = " and mat.id = ? "; 
              params.add(idMateria);
            }
                       
            Query query = session.createQuery(mainQuery + " where " + i18nQuery + uaQuery + fetVitalQuery + materiaQuery);
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                query.setParameter(i, o);
            }
     
            List<Ficha> fichas = query.list();
            if (!userIsOper()) {
                return fichas;
            } else {
                List<Ficha> fichasAcceso = new ArrayList<Ficha>();
                Usuario usuario = getUsuario(session);
                for (Ficha ficha: fichas){
                    if(tieneAcceso(usuario, ficha)){
                        fichasAcceso.add(ficha);
                     }   
                }
                return fichasAcceso;
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * A�ade una nueva materia a la ficha
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void anyadirMateria(Long materia_id, Long ficha_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFicha(ficha_id)) {
                throw new SecurityException("No tiene acceso a la ficha");
            }

            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
            Materia materia = (Materia) session.load(Materia.class, materia_id);
            ficha.getMaterias().add(materia);
            session.flush();
            Ficha fichasend = obtenerFicha(ficha_id);
            Actualizador.actualizar(fichasend);
            indexBorraFicha(ficha.getId());
            indexInsertaFicha(ficha,null);
        } catch (HibernateException e) {
            throw new EJBException(e);            
        } finally {
            close(session);
        }
    }

    /**
     * elimina una materia de la ficha
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void eliminarMateria(Long materia_id, Long ficha_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFicha(ficha_id)) {
                throw new SecurityException("No tiene acceso a la ficha");
            }

            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
            Materia materia = (Materia) session.load(Materia.class, materia_id);
            ficha.getMaterias().remove(materia);
            session.flush();
            Ficha fichasend = obtenerFicha(ficha_id);
            Actualizador.actualizar(fichasend);
            indexBorraFicha(ficha.getId());
            indexInsertaFicha(ficha,null);
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todas las fichas
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarFichas() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Ficha.class);
            criteri.setFetchMode("traducciones", FetchMode.EAGER);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista todas las fichas para reindexarlas en el crawler
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasCrawler() {
        Session session = getSession();
        try {
            Query query = session.createQuery("select distinct fichaRemota from FichaRemota as fichaRemota");
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Lista todas las fichas y solo rellena el idioma por defecto
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarFichasThin() {
        Session session = getSession();
        try {
        	String sql="select fic.id, trad.titulo, trad.url from Ficha fic join fic.traducciones trad where index(trad)='"+System.getProperty("es.caib.rolsac.idiomaDefault")+"' order by trad.titulo";
        	
        	Query query = session.createQuery(sql);
            
            ArrayList lista=new ArrayList();
            Iterator res=query.iterate();
            int i=0;
            while ( res.hasNext() ) {
                Object[] fila = (Object[]) res.next();
                Ficha fic= new Ficha();
                fic.setId((Long)fila[0]);
                TraduccionFicha trad= new TraduccionFicha();
                trad.setTitulo((String)fila[1]);
                trad.setUrl((String)fila[2]);
                fic.setTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault"),trad);
                lista.add(i,fic);
                i++;
            }

            return lista;
        	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }    
    
    
    /**
     * Lista de Fichas publicas de una unidad administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasUnidad(Long ua_id) {
        Session session = getSession();
        try {
            Query query = session.createQuery("select f from Ficha as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and f.validacion = :validacion " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setCacheable(true);

            List result = query.list();
            for (int i = 0; i < result.size(); i++) {
                Ficha ficha = (Ficha) result.get(i);
                Hibernate.initialize(ficha.getDocumentos());
            }

            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Lista de Fichas publicas de una unidad administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasRecientes() {
        /*
        query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, Materia as mat " +
    			"where f.administracionRemota.id=:idAdmin AND fua.ficha = f " +
    			"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
    			"AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    	"AND mat.id=:idMat AND mat in elements(f.materias) ");
         */
        Session session = getSession();
        try {
            Query query = session.createQuery("select f from FichaRemota as f " +
                    "where f.validacion = :validacion " +
                    "order by desc f.id");
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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarFicha(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFicha(id)) {
                throw new SecurityException("No tiene acceso a la ficha");
            }
            Ficha ficha = (Ficha) session.load(Ficha.class, id);
            addOperacion(session, ficha, Auditoria.BORRAR);
            // Debemos anular en todos los registros del historico de esa ficha, la materia y la ua.  
            Query query1 = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :ficha_id ");
            query1.setParameter("ficha_id", ficha.getId(), Hibernate.LONG);
            query1.setCacheable(true);
            List hfichas = query1.list();
            HistoricoFicha historico;
            for (int j=0;j<hfichas.size();j++) {
            	historico = (HistoricoFicha)hfichas.get(j);
            	historico.setFicha(null);
            	historico.setMateria(null);
            	historico.setUa(null);
            }
           // Historico historico = getHistorico(session, ficha);
           // ((HistoricoFicha) historico).setFicha(null);

            for (Iterator iterator = ficha.getMaterias().iterator(); iterator.hasNext();) {
                Materia mat = (Materia) iterator.next();
                mat.getFichas().remove(ficha);
            }
            for (Iterator iterator = ficha.getHechosVitales().iterator(); iterator.hasNext();) {
                HechoVital hecho = (HechoVital) iterator.next();
                hecho.getFichas().remove(ficha);
            }
            for (Iterator iterator = ficha.getFichasua().iterator(); iterator.hasNext();) {
                FichaUA fichaUA = (FichaUA) iterator.next();
                fichaUA.getSeccion().removeFichaUA(fichaUA);
                UnidadAdministrativa ua = fichaUA.getUnidadAdministrativa();
                if (ua != null) {
                    ua.removeFichaUA(fichaUA);
                }
            }
            
            // Borrar comentarios
            session.delete("from ComentarioFicha as cf where cf.ficha.id = ?", id, Hibernate.LONG);

            if(ficha instanceof FichaRemota){
                AdministracionRemota admin =((FichaRemota)ficha).getAdministracionRemota();
                if(admin!=null)
                	admin.removeFichaRemota((FichaRemota)ficha);
            }else{
            	Actualizador.borrar(new Ficha(id));
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
     * Obtiene las fichas de una Unidad Administrativa relacionada con la seccion
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasSeccionUA(Long ua_id, Long seccion_id) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from Ficha as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and fua.seccion.id = :seccion_id  " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("seccion_id", seccion_id, Hibernate.LONG);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene todas las  fichas de la seccion  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
     
    public List listarFichasSeccionTodas(Long seccion_id) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from Ficha as f, fua in f.fichasua " +
                    "where fua.seccion.id = :seccion_id " +
                    "order by fua.ordenseccion , f.fechaActualizacion desc");
            query.setParameter("seccion_id", seccion_id, Hibernate.LONG);
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<Ficha> listarFichasSeccionUA(Long ua_id, String codEstSecc, String[] codEstHV, String[] codEstMat) {
        Session session = getSession();
        try {
            //obtenemos las fichas de una unidad y una seccion que son publicas.
            Query query = session.createQuery(
                    "select f from Ficha as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and fua.seccion.codigoEstandard = :codEstSecc  " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("codEstSecc", codEstSecc);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            query.setCacheable(true);
            
            List<Ficha> fichas =  query.list();
            List<Ficha> fichasFinales = new ArrayList<Ficha>();
            
            for(Ficha ficha : fichas){
            	//Variable que indica si la ficha tiene alguna relacion
                boolean relacionada = false;

                if(codEstMat.length > 0){
                    // obtenemos los codigos estandar de las materias de la ficha
                    Query queryMat = session.createQuery("select mat.codigoEstandar from Ficha as f, f.materias as mat where f.id = :id");
                    queryMat.setParameter("id", ficha.getId(), Hibernate.LONG);
                    List<String> codigosMaterias = queryMat.list();

                    // si la ficha esta relacionada con alguna materia la marcamos
                    for(String codigoMat: codEstMat){
                        if (relacionada = codigosMaterias.contains(codigoMat)){
                            break;
                        }
                    }
                }
                if(codEstHV.length > 0){
                    //Si no tiene niguna relacion con ninguna materia miramos si teiene ralacion con algun HV
                    if(!relacionada){
                        Query queryHechos = session.createQuery("select hev.codigoEstandar from Ficha as f, f.hechosVitales as hev where f.id = :id");
                        queryHechos.setParameter("id", ficha.getId(), Hibernate.LONG);
                        List<String> codigosHechos = queryHechos.list();

                        // si la ficha esta relacionada con el hechovital la marcamos
                        for(String codigoHev: codEstHV){
                            if (relacionada = codigosHechos.contains(codigoHev)){
                                break;
                            }
                        }
                    }
                }
                
                if(relacionada){
                	Hibernate.initialize(ficha.getIcono());
                    Hibernate.initialize(ficha.getImagen());
                    Hibernate.initialize(ficha.getBaner());
                    Hibernate.initialize(ficha.getMaterias());
                    Hibernate.initialize(ficha.getHechosVitales());
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
    
    /*
     * Obtiene las fichas de una Unidad Administrativa relacionada con la seccion
     * para un conjunto de hechos vitales y un conjunto de materias
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    /*@SuppressWarnings("unchecked")
	public List<Ficha> listarFichasSeccionUA(Long idUA, String codEstSecc, String[] codEstHV, String[] codEstMat) {
        Session session = getSession();
        try {    
        	
            final Query query = session.createQuery(
    				"SELECT f From FichaUA as fua, fua.ficha as f " +
    				"WHERE fua.unidadAdministrativa.id=:unidad " +
    				"  AND fua.seccion.codigoEstandard = :codEstSecc  " +
                    "  AND f.validacion = :validacion " +
                    "  AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "  AND (  elements(f.materias) in (SELECT mat FROM Materia as mat WHERE mat.codigoEstandar in (:mats))  " +
    				"        OR  elements(f.hechosVitales) in (SELECT hecho FROM HechoVital as hecho WHERE hecho.codigoEstandar in (:hecs))  ) " +
    				"");
            
            query.setParameter("unidad", idUA, Hibernate.LONG);
			query.setParameter("codEstSecc", codEstSecc);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.today());
            query.setParameterList("mats",codEstMat,Hibernate.STRING);
            query.setParameterList("hecs",codEstHV,Hibernate.STRING);
            
            List<Ficha> fichas = query.list();
            
            for(Ficha ficha : fichas){
            	Hibernate.initialize(ficha.getIcono());
                Hibernate.initialize(ficha.getImagen());
                Hibernate.initialize(ficha.getBaner());
                Hibernate.initialize(ficha.getMaterias());
                Hibernate.initialize(ficha.getHechosVitales());
            }
            return fichas;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }*/

   /**
     * Obtiene las fichas de una Unidad Administrativa relacionada con la seccion
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasSeccionUA(Long ua_id, String codEstSecc) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from Ficha as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and fua.seccion.codigoEstandard = :codEstSecc  " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("codEstSecc", codEstSecc, Hibernate.STRING);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene todas las fichas de una seccion (novedades toda la web)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasSeccion(Long seccion_id) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from Ficha as f, fua in f.fichasua " +
                    "where fua.seccion.id = :seccion_id " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "order by fua.ordenseccion , f.fechaActualizacion desc");
            query.setParameter("seccion_id", seccion_id, Hibernate.LONG);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            
        
    
           
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    /** (PORMAD pensado para los banners de POMAD, seccion por codigoEstandar)
     * Obtiene todas las fichas de una seccion
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasSeccion(String codEstSecc,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select distinct f from Ficha as f, fua in f.fichasua " +
                    "where fua.seccion.codigoEstandard = :codEstSecc " +
                    "and f.validacion = :validacion " +
                    (!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : ""));
            query.setParameter("codEstSecc", codEstSecc, Hibernate.STRING);
            query.setParameter("validacion", Validacion.PUBLICA);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Crea o actualiza una FichaUA
     * Esta ficha ser� la que tenga el orden 0 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long crearFichaUA(Long unidad_id, Long seccion_id, Long ficha_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFicha(ficha_id)) {
                throw new SecurityException("No tiene acceso a la ficha");
            }

            FichaUA fichaUA = new FichaUA();
            if (unidad_id != null) {
                UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
                if (!getAccesoManager().tieneAccesoUnidad(unidad_id, false)) {
                    throw new SecurityException("No tiene acceso a la ficha");
                }
                // Cuando se aniade una ficha a una seccion o a una seccion + ua por defecto su orden es 1
                fichaUA.setOrden(1);
                unidad.addFichaUA(fichaUA);
            } else {
                if (!userIsSystem()) {
                    throw new SecurityException("No puede crear fichas generales.");
                }
            }

            Seccion seccion = (Seccion) session.load(Seccion.class, seccion_id);
            if (!getAccesoManager().tieneAccesoSeccion(seccion_id)) {
                throw new SecurityException("No tiene acceso a la secci�n");
            }
            
            // Cuando se aniade una ficha a una seccion o a una seccion + ua por defecto su orden es 1
            fichaUA.setOrdenseccion(1);
            seccion.addFichaUA(fichaUA);

            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
            ficha.addFichaUA(fichaUA);
            session.flush();
            Ficha fichasend = obtenerFicha(ficha_id);
            Actualizador.actualizar(fichasend);
            indexBorraFicha(ficha.getId());
            indexInsertaFicha(fichasend,null);
            return ficha.getId();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Subir el orden de una ficha de Unidad administrativa
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void subirFichaUA(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFichaUnidad(id)) {
                throw new SecurityException("No tiene acceso a la relaci�n");
            }
            FichaUA ficha1 = (FichaUA) session.load(FichaUA.class, id);
            UnidadAdministrativa unidad = ficha1.getUnidadAdministrativa();
            Map seccionFichas = unidad.getMapSeccionFichasUA();
            //List fichasUA = (List) seccionFichas.get(ficha1.getSeccion());
            List fichasUA = (List) seccionFichas.get(ficha1.getSeccion().getId().longValue() + "#" + ((TraduccionSeccion)ficha1.getSeccion().getTraduccion("ca")).getNombre());
            int ordenSeccion = fichasUA.indexOf(ficha1);
            if (ordenSeccion > 0) {                
                FichaUA ficha2 = (FichaUA) fichasUA.get(ordenSeccion -1);
                int newOrden = ficha2.getOrden();

                /*
                List hermanos = unidad.getFichasUA();
                ficha2.setOrden(ficha1.getOrden());
                hermanos.set(ficha1.getOrden(), ficha2);

                ficha1.setOrden(newOrden);
                hermanos.set(newOrden, ficha1);
                */
                
                Set hermanos = unidad.getTodasfichas();
                ficha2.setOrden(ficha1.getOrden());
                hermanos.remove(ficha2);
                hermanos.add(ficha2);
                
                ficha1.setOrden(newOrden);
                hermanos.remove(ficha1);
                hermanos.add(ficha1);
                
                //session.saveOrUpdate(ficha1);
                //session.saveOrUpdate(ficha2);
                
            }
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Actualiza los ordenes de las fichas de una secci�n de una Unidad administrativa
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenFichasUA(Enumeration params, Map valores) {
    	
    	Session session = getSession();
        try {
        	Long id;
        	int valor_orden=0;
        	UnidadAdministrativa unidad=null;
        	Set hermanos= null;
        	List fichas_orden = new ArrayList();
        	
            while(params.hasMoreElements()) {
            	String paramName = (String)params.nextElement();
            	if (paramName.startsWith("orden_fic")) {
            		id=new Long(paramName.substring(9));
            		String[] parametros=(String[])valores.get(paramName);
            		valor_orden= Integer.parseInt(parametros[0]);
            		
            		if (!getAccesoManager().tieneAccesoFichaUnidad(id)) {
            			throw new SecurityException("No tiene acceso a la relaci�n");
            		}
            		FichaUA ficha = (FichaUA) session.load(FichaUA.class, id);

            		if (unidad==null) {
            			unidad = ficha.getUnidadAdministrativa();
            			hermanos = unidad.getTodasfichas();
            		}
            		ficha.setOrden(valor_orden);
            		hermanos.remove(ficha);
            		hermanos.add(ficha);
            		fichas_orden.add(ficha);
            	}
            }
            session.flush();
            
            Collections.sort( fichas_orden, new FichaUAComparator() );
            
            int contador=5;
        	
        	Iterator itfic=fichas_orden.iterator();
    		FichaUA fic=null;
    		while (itfic.hasNext()) {
    			fic=(FichaUA)itfic.next();
    			fic.setOrden(contador);
    			hermanos.remove(fic);
        		hermanos.add(fic);
    			contador+=5;
    		}
            session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    /**
     * Borrar una ficha Unidad administrativa
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
            	Actualizador.borrar(new FichaUATransferible(idUA,idFicha,ceSeccion));
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Busca todas las Fichas con un texto determinado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarFichas(String texto) {
        IndexerDelegate delegate = DelegateUtil.getIndexerDelegate();
        Long[] ids;
        try {
            ids = delegate.buscarIds(Ficha.class.getName(), texto);
        } catch (DelegateException e) {
            log.error("Error buscando", e);
            ids = new Long[0];
        }

        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }

        Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(Ficha.class);
            criteria.add(Expression.in("id", ids));
            criteria.setFetchMode("traducciones", FetchMode.EAGER);
            return criteria.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Construye el query de b�squeda segun los parametros
     */
    private String populateQuery(Map parametros, Map traduccion, List params) {
        String aux = "";

        // Tratamiento de parametros
        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (value != null) {
                if (aux.length() > 0) aux = aux + " and ";
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( ficha." + key + " ) like ? " ;
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( ficha." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + "ficha." + key + " =  ? ";
                    params.add(value);
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
                    aux = aux + " and trad." + key + " =  ? ";
                    params.add(value);
                }
            }
        }

        return aux;
    }
    
    
    /**
     * Obtiene una lista de fichas segun hechovital (PORMAD)
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List buscarFichasHechoVital(Long id) {
        Session session = getSession();
        try {
            String sQuery = "hec.id = " + id;
            Query query = session.createQuery("select distinct ficha from Ficha as ficha, ficha.hechosVitales as hec where " + sQuery);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * A�ade un nuevo HechoVital a la ficha (PORMAD)
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void anyadirHechoVital(Long id, Long ficha_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFicha(ficha_id)) {
                throw new SecurityException("No tiene acceso a la ficha");
            }

            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
            HechoVital hechovital = (HechoVital) session.load(HechoVital.class, id);
            ficha.getHechosVitales().add(hechovital);
            session.flush();
            Ficha fichasend = obtenerFicha(ficha_id);
            Actualizador.actualizar(fichasend);
        } catch (HibernateException e) {
        	throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * elimina un hechovital de la ficha (PORMAD)
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void eliminarHechoVital(Long id, Long ficha_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFicha(ficha_id)) {
                throw new SecurityException("No tiene acceso a la ficha");
            }

            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
            HechoVital hechovital = (HechoVital) session.load(HechoVital.class, id);
            ficha.getHechosVitales().remove(hechovital);
            session.flush();
		
            Ficha fichasend = obtenerFicha(ficha_id);
            Actualizador.actualizar(fichasend);
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }
    
	/**
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public ModelFilterObject obtenerFilterObject(Ficha ficha) {
		ModelFilterObject filter = new ModelFilterObject();
		Session session = getSession();

		try {

			
    		//de momento, familia y microsites a null
    		
    		filter.setFamilia_id(null);    	
    		filter.setMicrosite_id(null);
    		
    		
    		Iterator iterlang = ficha.getLangs().iterator();
    		while (iterlang.hasNext()){
    			
    			String idioma = (String) iterlang.next();
				String txids=Catalogo.KEY_SEPARADOR;
	    		String txtexto=" ";//espacio en blanco, que es para tokenizar
	    		Iterator iter;
	    		
				TraModelFilterObject trafilter = new TraModelFilterObject();
				
				//titulo, de momento vacio			
				trafilter.setMaintitle(null);
	        	
	    		//OBTENER LAS UO (en el caso de las fichas hay que recogerlas de la relacion con seccion)
	    		txids=Catalogo.KEY_SEPARADOR;
	    		txtexto=" ";
	    		Hashtable hsids = new Hashtable();//para controlar que no se repitan ids
	    		Hashtable hslistapadres = new Hashtable();//para controlar que no se repitan llamadas al delegate
	    		
	    		if (ficha.getFichasua()!=null) {
	    		
					iter = ficha.getFichasua().iterator();
					
					while (iter.hasNext()) {
						FichaUA fichaua = (FichaUA)iter.next();
			        	if (!hsids.containsKey("" + fichaua.getUnidadAdministrativa().getId().longValue())) {
			    			txids+=fichaua.getUnidadAdministrativa().getId()+Catalogo.KEY_SEPARADOR;
			    			if (((TraduccionUA)fichaua.getUnidadAdministrativa().getTraduccion(idioma))!=null)
			    				txtexto+=((TraduccionUA)fichaua.getUnidadAdministrativa().getTraduccion(idioma)).getNombre()+" ";//espacio en blanco, que es para tokenizar
				        	hsids.put("" + fichaua.getUnidadAdministrativa().getId().longValue(),"" + fichaua.getUnidadAdministrativa().getId().longValue());
			        	}
			        	if (!hslistapadres.containsKey("" + fichaua.getUnidadAdministrativa().getId().longValue())) {
			        		List listapadres= org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa(fichaua.getUnidadAdministrativa().getId());
				    		UnidadAdministrativa ua=null;
				    		for (int x = 1; x < listapadres.size(); x++) {
				    			ua=(UnidadAdministrativa)listapadres.get(x);
				    			if (!hsids.containsKey("" + ua.getId().longValue())) {
					    			txids+=ua.getId()+Catalogo.KEY_SEPARADOR;
					    			if (((TraduccionUA)ua.getTraduccion(idioma))!=null)
					    				txtexto+=((TraduccionUA)ua.getTraduccion(idioma)).getNombre()+" ";//espacio en blanco, que es para tokenizar
					    			hsids.put("" + ua.getId().longValue(),"" + ua.getId().longValue());
				    			}
				    		}
				    		hslistapadres.put("" + fichaua.getUnidadAdministrativa().getId().longValue(),"" + fichaua.getUnidadAdministrativa().getId().longValue());
			        	}
			        	
					}
	    		}
				
	    		filter.setUo_id( (txids.length()==1) ? null: txids);
	    		trafilter.setUo_text( (txtexto.length()==1) ? null: txtexto);
	    		
	    		
	    		//OBTENER LAS SECCIONES (en el caso de las fichas hay que recogerlas de la relacion con seccion. Solo ponemos la seccion propia)
	    		txids=Catalogo.KEY_SEPARADOR;
	    		txtexto=" ";
	    		
	    		if (ficha.getFichasua()!=null) {
					iter = ficha.getFichasua().iterator();
					boolean primer = true; 
					while (iter.hasNext()) {
						FichaUA fichaua = (FichaUA)iter.next();
						txids+=fichaua.getSeccion().getId()+Catalogo.KEY_SEPARADOR;
						if (fichaua.getSeccion().getTraduccion(idioma)!=null)
			        		txtexto+=((TraduccionSeccion)fichaua.getSeccion().getTraduccion(idioma)).getNombre()+" ";
			        	if (primer) {
			        		//como titulo del servicio principal ponemos �nicamente la primera seccion que pillamos
			        		String txUA = "";
			        		String txSeccion = "";
			        		String txMaintitle = "";
			        		if (fichaua.getSeccion().getTraduccion(idioma)!=null) {
			        			if (((TraduccionUA)fichaua.getUnidadAdministrativa().getTraduccion(idioma))!=null)
			        				txUA = ((TraduccionUA)fichaua.getUnidadAdministrativa().getTraduccion(idioma)).getNombre();
			        			txSeccion = ((TraduccionSeccion)fichaua.getSeccion().getTraduccion(idioma)).getNombre();
			        			if (txUA.length()>1)
			        				txMaintitle = txUA.substring(0,1).toUpperCase() + txUA.substring(1,txUA.length()-1).toLowerCase();
			        			txMaintitle += " / ";
			        			txMaintitle += txSeccion;
			        			trafilter.setMaintitle( txMaintitle );
			        		}
							else if (fichaua.getSeccion().getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault"))!=null) {
								if (((TraduccionUA)fichaua.getUnidadAdministrativa().getTraduccion(idioma))!=null)
									txUA = ((TraduccionUA)fichaua.getUnidadAdministrativa().getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault"))).getNombre();
			        			txSeccion = ((TraduccionSeccion)fichaua.getSeccion().getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault"))).getNombre();
			        			if (txUA.length()>1)
			        				txMaintitle = txUA.substring(0,1).toUpperCase() + txUA.substring(1,txUA.length()-1).toLowerCase();
			        			txMaintitle += " / ";
			        			txMaintitle += txSeccion;
			        			trafilter.setMaintitle( txMaintitle );								
							}
							else trafilter.setMaintitle("");
			        		primer=false;
			        	}
			        		
					}
	    		}
	    		filter.setSeccion_id( (txids.length()==1) ? null: txids);
	    		trafilter.setSeccion_text( (txtexto.length()==1) ? null: txtexto);
	    		

	    		//OBTENER LAS MATERIAS (ademas de las materias se ponen los textos de los HECHOS VITALES)
	    		txids=Catalogo.KEY_SEPARADOR;
	    		txtexto=" ";
	    		if (ficha.getMaterias()!=null) {
					iter = ficha.getMaterias().iterator();
					
					while (iter.hasNext()) {
						Materia materia = (Materia)iter.next();
			        	txids+=materia.getId()+Catalogo.KEY_SEPARADOR; //anayadir los ids (los de los hechos vitales no)
			        	if (materia.getTraduccion(idioma)!=null) {
			        		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getNombre() + " ";
			        		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getDescripcion() + " ";
			        		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getPalabrasclave() + " ";
			        	}
			        		
					}
	    		}
	    		if (ficha.getMaterias()!=null) {
					iter = ficha.getHechosVitales().iterator();
					while (iter.hasNext()) {
						HechoVital hvital = (HechoVital)iter.next();
			        	if (hvital.getTraduccion(idioma)!=null) {
			        		txtexto+=((TraduccionHechoVital)hvital.getTraduccion(idioma)).getNombre() + " ";
			        		txtexto+=((TraduccionHechoVital)hvital.getTraduccion(idioma)).getDescripcion() + " ";
			        		txtexto+=((TraduccionHechoVital)hvital.getTraduccion(idioma)).getPalabrasclave() + " ";
			        	}
					}
	    		}
	    		filter.setMateria_id( (txids.length()==1) ? null: txids);
	    		trafilter.setMateria_text( (txtexto.length()==1) ? null: txtexto);
	    		
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
     * A�ade la ficha al indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaFicha(Ficha ficha,  ModelFilterObject filter)  {
		
    	try {

	    	if (ficha.getValidacion().equals(2)) return;
    		
	    	if (filter==null) filter = obtenerFilterObject(ficha);
			
			for (Iterator iterator = ficha.getLangs().iterator(); iterator.hasNext();) {
				
				String idi = (String) iterator.next();
				IndexObject io= new IndexObject();  	

	            io.setId(Catalogo.SRVC_FICHAS + "." + ficha.getId());
	            io.setClasificacion(Catalogo.SRVC_FICHAS);
	            
	            io.setMicro( filter.getMicrosite_id() ); 
	            if ( filter.getUo_id()!=null) 		io.setUo( filter.getUo_id() );
	            if ( filter.getMateria_id()!=null) 	io.setMateria( filter.getMateria_id() );
	            if ( filter.getFamilia_id()!=null) 	io.setFamilia( filter.getFamilia_id() );
				if ( filter.getSeccion_id()!=null) 	io.setSeccion( filter.getSeccion_id() );
				
				io.setCaducidad("");
				if (ficha.getFechaPublicacion()!=null) io.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaPublicacion())); else	io.setPublicacion("");
				io.setDescripcion("");
	            if (ficha.getFechaCaducidad()!=null) 
	            	io.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaCaducidad()));

	            
	            TraduccionFicha trad=((TraduccionFicha)ficha.getTraduccion(idi));
	            
	            if (trad!=null) {
	            
	            	if ( (trad.getUrl()!=null) && (trad.getUrl().length()>0) ) {
	            		io.setUrl( "/govern/estadistica?tipus=F&codi=" + ficha.getId() + "&url=" + java.net.URLEncoder.encode(trad.getUrl(),"UTF-8") );
	            		
	            		if (trad.getUrl().startsWith("http") || trad.getUrl().startsWith("/") ) {
	            			//El servidor no tiene acceso a internet para realizar esta tarea
	            			//Toni comenta que esto no es necesario ya que con la informaci�n de la ficha es suficiente
	            			//indexBorraWEB_EXTERNA (ficha, idi);
	            			//indexInsertaWEB_EXTERNA (ficha, filter, idi);
	            		}

	            	}	
	            	else 
	            		io.setUrl( "/govern/sac/fitxa.do?lang=" + idi + "&codi=" + ficha.getId() + "&coduo="+ obtenerUO_Principal(io.getUo()) );
	            	
	            	io.setTituloserviciomain(filter.getTraduccion(idi).getMaintitle());
	            	
	            	
	            	if (trad.getTitulo()!=null && trad.getTitulo().length() > 0) {
	            		io.setTitulo(trad.getTitulo());
	            		io.addTextLine(trad.getTitulo());
	            		if (trad.getDescAbr()!=null)	io.addTextLine(trad.getDescAbr());
	            	} else {
	            		TraduccionFicha trad_ca=((TraduccionFicha)ficha.getTraduccion("ca"));
	            		if (trad_ca.getTitulo()!=null) io.setTitulo(trad_ca.getTitulo());
	            	}
	            	
	            	if (trad.getDescripcion()!=null)  {
	            		io.addTextLine(trad.getDescripcion());	
	            		//if (trad.getDescripcion().length()>200) io.setDescripcion(trad.getDescripcion().substring(0,199)+"...");
	                	//else io.setDescripcion(trad.getDescripcion());
	            		io.setDescripcion(trad.getDescripcion());
	            	}
	            	
					io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
					io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
					io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());	    
	            	
	            }

	            //No:Se a�aden todos los documentos en todos los idiomas. 
	            //Ahora en el idioma actual.
	            if (ficha.getDocumentos()!=null) {
		            Iterator iterdocs = ficha.getDocumentos().iterator();
		            while (iterdocs.hasNext()) {
		            	Documento documento = (Documento)iterdocs.next();
		            	documento = DelegateUtil.getDocumentoDelegate().obtenerDocumento(documento.getId());
		            	if (documento.getTraduccion(idi)!=null) {
		            		io.addTextLine(((TraduccionDocumento)documento.getTraduccion(idi)).getTitulo());
		            		io.addTextLine(((TraduccionDocumento)documento.getTraduccion(idi)).getDescripcion());
		            	}
				
		            	
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
			            		// arch = (Archivo)((TraduccionDocumento)documento.getTraduccion("ca")).getArchivo();			            		
			            	}
			            	
			            	textDoc = ioDoc.getText();
				            if (textDoc != null && textDoc.length() > 0 && arch != null) {	
					            if (documento.getTraduccion(idi)!=null) {				            	
									
									ioDoc.setId(Catalogo.SRVC_FICHAS_DOCUMENTOS + "." + documento.getId());
									ioDoc.setClasificacion(Catalogo.SRVC_FICHAS + "." + ficha.getId());
									ioDoc.setCaducidad("");
									if (ficha.getFechaPublicacion()!=null) ioDoc.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaPublicacion())); else	ioDoc.setPublicacion("");									
									ioDoc.setDescripcion("");
						            if (ficha.getFechaCaducidad()!=null) 
						            	ioDoc.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaCaducidad()));
					        		ioDoc.setUrl( "/fitxer/get?codi=" + arch.getId());
					            	ioDoc.setTituloserviciomain(io.getTitulo());  
					            	if (((TraduccionDocumento)documento.getTraduccion(idi)).getTitulo() == null)
					            		ioDoc.setTitulo(((TraduccionDocumento)documento.getTraduccion("ca")).getTitulo() + ", (" + arch.getMime().toUpperCase() +")");
					            	else
					            		ioDoc.setTitulo(((TraduccionDocumento)documento.getTraduccion(idi)).getTitulo() + ", (" + arch.getMime().toUpperCase() +")");  
					            	ioDoc.setDescripcion(((TraduccionDocumento)documento.getTraduccion(idi)).getDescripcion());
					            	ioDoc.setText(textDoc);
					            	ioDoc.addTextLine(((TraduccionDocumento)documento.getTraduccion(idi)).getDescripcion());
					            	ioDoc.addTextLine(arch.getNombre());
						            if ( io.getUo()!=null) 	ioDoc.setUo( io.getUo());
						            if ( io.getMateria()!=null) ioDoc.setMateria( io.getMateria());
									if ( io.getSeccion()!=null) ioDoc.setSeccion( io.getSeccion());
									
						            if (ioDoc.getText().length()>0 || ioDoc.getTextopcional().length()>0)
						            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(ioDoc, idi);								
					            }
				            	
								io.addTextLine(textDoc);					            
				            }

		            	
		            }
	            }

            	// Se crea la indexaci�n del foro como documento individual y se a�ade la informaci�n 
            	// para la indexaci�n de la ficha.
            	if (ficha.getUrlForo()!=null && ficha.getUrlForo().length() > 0) {
					io.setConforo("S");
            	} else 
            	{
            		io.setConforo("N");
            	}
	            
	            if (io.getText().length()>0 || io.getTextopcional().length()>0)
	            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);
			}
		}
		catch (Exception ex) {
			log.warn("[indexInsertaFicha:" + ficha.getId() + "] No se ha podido indexar ficha. " + ex.getMessage());
		}
        
	}
	
	 /**
     * Elimina la ficha en el indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void indexBorraFicha(Long id)  {
		
		
		try {

			List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			for (int i = 0; i < langs.size(); i++) {
				DelegateUtil.getIndexerDelegate().borrarObjeto(Catalogo.SRVC_FICHAS + "." + id, ""+langs.get(i));
				DelegateUtil.getIndexerDelegate().borrarObjetosDependientes(Catalogo.SRVC_FICHAS + "." + id, ""+langs.get(i));
			}

		}
		catch (DelegateException ex) {
			log.warn("[indexBorraFicha:" + id + "] No se ha podido borrar del indice la ficha. " + ex.getMessage());
		}
		
        
	}


    /**
     * A�ade la URL externa para un idioma, obteniendola de la ficha
     * El id de la URL ser� el id de la ficha
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaWEB_EXTERNA(Ficha ficha,  ModelFilterObject filter, String idi)  {
		
    	try {
    		
    			
    		IndexObject io= new IndexObject();
				
	        io.setId(Catalogo.SRVC_WEB_EXTERNA + "." + ficha.getId());
	        io.setClasificacion(Catalogo.SRVC_WEB_EXTERNA);
	            
	        io.setMicro( filter.getMicrosite_id() ); 
	        io.setUo( filter.getUo_id() );
			io.setMateria( filter.getMateria_id() );
			io.setFamilia( filter.getFamilia_id() );
			io.setSeccion( filter.getSeccion_id() );
				
			io.setCaducidad("");
			if (ficha.getFechaPublicacion()!=null) io.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaPublicacion())); else	io.setPublicacion("");
			io.setDescripcion("");
	        if (ficha.getFechaCaducidad()!=null) 
	        	io.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(ficha.getFechaCaducidad()));

	        TraduccionFicha trad=((TraduccionFicha)ficha.getTraduccion(idi));
	            
	        if (trad!=null) {
	            
	        	if ( (trad.getUrl()!=null) && (trad.getUrl().length()>0) ) {
	        		io.setUrl( trad.getUrl() );
	            	
	        		if (trad.getTitulo()!=null) {
	            		io.setTitulo(trad.getTitulo());
	            		io.addTextLine(trad.getTitulo());
	            	}
	            	
	            	if (trad.getDescAbr()!=null)	io.addTextLine(trad.getDescAbr());
	            	
	            	if (trad.getDescripcion()!=null)  {
	            		io.addTextLine(trad.getDescripcion());	
	            		//if (trad.getDescripcion().length()>200) io.setDescripcion(trad.getDescripcion().substring(0,199)+"...");
	                	//else io.setDescripcion(trad.getDescripcion());
	            		io.setDescripcion(trad.getDescripcion());
	            	}
	            	
					io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
					io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
					io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());	
	            	
					getWEB_EXTERNA (trad.getUrl() );
					Object contenido=contenidos_web.get(trad.getUrl());
					if (contenido instanceof Archivo)
						io.addArchivo( (Archivo)contenido);
					
		            if (io.getText().length()>0)
		            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);
		            
	            }
	            
			}

		}
		catch (Exception ex) {
			log.warn("[indexInsertaWEB_EXTERNA:" + ficha.getId() + "] No se ha podido indexar la WEB_EXTERNA. " + ex.getMessage());
		}
        
	}
	
	 /**
     * Elimina la URL Externa en el indice para un idioma
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void indexBorraWEB_EXTERNA(Ficha ficha, String idi)  {
		
		try {
			DelegateUtil.getIndexerDelegate().borrarObjeto(Catalogo.SRVC_WEB_EXTERNA + "." + ficha.getId(), ""+idi);
		}
		catch (DelegateException ex) {
			log.warn("[indexBorraWEB_EXTERNA:" + ficha.getId() + "] No se ha podido borrar del indice la WEB_EXTERNA. " + ex.getMessage());
		}
		
        
	}
	
	private void getWEB_EXTERNA (String laurl) {
	    
		try {
	    	
			if (!contenidos_web.containsKey(laurl)) {
			    
				HttpURLConnection connection = (HttpURLConnection)new URL(laurl).openConnection();
            
	            /*
	            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		        String encodedUserPwd =  encoder.encode("fnruiz:xxxxx".getBytes());
		        connection.setRequestProperty ("Proxy-Authorization", "Basic " + encodedUserPwd);
	            */
	            connection.connect();
	            
	            DataInputStream dis = new DataInputStream(connection.getInputStream());
	            String inputLine;
	    	    String str="";
	            while ((inputLine = dis.readLine()) != null) {
	            	str+=inputLine+"\n";
	            }
	            dis.close();

	    	    Archivo archi= new Archivo();
	            archi.setMime("text/html");
	            archi.setPeso(str.length());
	            archi.setDatos(str.getBytes());
	            
	            contenidos_web.put(laurl, archi);

			}
		
	    	
	    } catch (MalformedURLException e) {
	      System.out.println("La URL no es v�lida: "+ laurl+ " "+e);
	      contenidos_web.put(laurl, new String("La URL no es v�lida"));
	    } catch (IOException e) {
	      System.out.println("No puedo conectar con "+ laurl+ " "+e);
	      contenidos_web.put(laurl, new String("No puedo conectar"));
	    }
	    
	}

	 /**
     * Devuelve las posibles fichas relacionadas con un microsite
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public List getFichasMicrosite(String idsite) {
		
		Session session = getSession();
        
		try {
 
			Query query = session.createQuery("select distinct ficha from Ficha as ficha " +
                    ", ficha.traducciones as trad " +
                    "where trad.url like '%sacmicrofront%' AND trad.url like '%idsite="+idsite+"%'");
           
			return query.list();
			/*
        	Criteria cri= session.createCriteria(Ficha.class);
        	cri.setFetchMode("traducciones", FetchMode.EAGER);
        	cri.add( Expression.like("traducciones.url", "%sacmicrofront%")); 
        	cri.add( Expression.like("traducciones.url", "%idsite="+idsite+"%"));
    	    return cri.list();
    	    */
    	    
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
		
	}
	
	
	 /**
     * Devuelve las posibles fichas relacionadas con un foro
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public List getFichasForo(String idforo) {
		
		Session session = getSession();
        
		try {
 
			Query query = session.createQuery("select distinct ficha from Ficha as ficha " +
                    ", ficha.traducciones as trad " +
                    "where trad.url like '%gforumfront%' AND trad.url like '%idforo="+idforo+"%'");
           
			return query.list();
   	    
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
		
	}
	
	
	
	 /**
     * Devuelve las posibles fichas relacionadas con un tema
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public List getFichasTema(String idtema) {
		
		Session session = getSession();
        
		try {
 
			Query query = session.createQuery("select distinct ficha from Ficha as ficha " +
                    ", ficha.traducciones as trad " +
                    "where trad.url like '%gforumfront%' AND trad.url like '%idtema="+idtema+"%'");
           
			return query.list();
   	    
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
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

	// Contienen el listado separado por # de las UO que contienen la ficha
	// Debo devolver la primera de la lista
	private String obtenerUO_Principal(String listaUOs) {
		
		if (listaUOs==null) return "";
		if (listaUOs.length()==0) return "";
		
		StringTokenizer uos= new StringTokenizer(listaUOs, Catalogo.KEY_SEPARADOR);
	      
	    String primera_uo="1";
	    
	    if (uos.hasMoreTokens()) {
	    	primera_uo=uos.nextToken();
	    }

	    return primera_uo;
				
	}
	
	class FichaUAComparator implements Comparator {
		public int compare(Object o1, Object o2) { 
			Integer x1=new Integer( ((FichaUA)o1).getOrden());
			Integer x2=new Integer( ((FichaUA)o2).getOrden());
			return x1.compareTo( x2 ); 
		}
	}
	
	 /**
     * Devuelve las fichasUA de una ficha {PORMAD}
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public List<FichaUA> listFichasUA(Long idFicha) {
		
		Session session = getSession();
        
		try {
 
			Query query = session.createQuery("select fichaua from FichaUA as fichaua where fichaua.ficha="+idFicha);
           
			return query.list();
   	    
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
		
	}

	 /**
     * Obtiene los resultados de buscar en el crawler de fichas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaCrawler> buscarFichasCrawler(final String busqueda, final String idioma,final Date dataInici, final Date dataFi) {
    	List<FichaCrawler> resultado = null ;
    	String index=System.getProperty("es.caib.rolsac.crawler.indexLocation");
        index=index+"/"+idioma;

    	if(busqueda!=null && !"".equals(busqueda.trim())){ 
            try {
            	resultado =  new ArrayList<FichaCrawler>();
            	
                int max = 100;
                log.debug("Buscando por: " + busqueda+" en el path: "+index);
                
                IndexSearcher is   = new IndexSearcher(index);
                BooleanQuery booleanQuery = new BooleanQuery();
                
                QueryParser parser1 = new QueryParser("contents", getAnalizador(idioma));
                org.apache.lucene.search.Query query1 = parser1.parse(busqueda);
                QueryParser parser2 = new QueryParser("title", getAnalizador(idioma));
                org.apache.lucene.search.Query query2 = parser2.parse(busqueda);
                booleanQuery.add(query1, BooleanClause.Occur.SHOULD);
                booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
                
                TopDocCollector collector = new TopDocCollector(max);
                is.search(booleanQuery, collector);
                TopDocs topDocs = collector.topDocs();
                ScoreDoc[] hits = topDocs.scoreDocs;

                log.debug(" results: " + hits.length + " of total " + topDocs.totalHits);

                for (int i = 0; i < hits.length; i++) {
                	FichaCrawler fichaCrawler=new FichaCrawler();
                    float relevance = ((float) Math.round(hits[i].score * 1000)) / 10;
                    String url = is.doc(hits[i].doc).getField("url").stringValue();
                    String tituloURL = is.doc(hits[i].doc).getField("title").stringValue();
                    String idFicha = is.doc(hits[i].doc).getField("idFicha").stringValue();
                    String modified = is.doc(hits[i].doc).getField("timestamp").stringValue();
                    
                    Ficha ficha = obtenerFicha(Long.valueOf(idFicha));
                    if(ficha.getFechaPublicacion()!=null &&dataInici!=null&&dataFi!=null){
                    	log.debug("Buscada Crawler entre fechas: Fecha Publicacion: "+ficha.getFechaPublicacion()+" dataInici: "+dataInici+" dataFi: "+dataFi);
                    	if(ficha.getFechaPublicacion().before(dataFi)&&ficha.getFechaPublicacion().after(dataInici)){
                    		fichaCrawler.setTituloURL(tituloURL);
                            fichaCrawler.setURL(url);
                            fichaCrawler.setFicha(ficha);
                            resultado.add(fichaCrawler);
                        }
                    }
                    else{
                        fichaCrawler.setTituloURL(tituloURL);
                        fichaCrawler.setURL(url);
                        fichaCrawler.setFicha(ficha);
                        resultado.add(fichaCrawler);
                    }

                }
                is.close();
            	
            	
            	
                return resultado;
            } catch (Exception e) {
            	resultado = Collections.emptyList();
            } 
    	}
    	return resultado;

}
    
	private static Analyzer getAnalizador(String idi) {
		Analyzer analyzer;
		if (idi.toLowerCase().equals("de"))      	analyzer = new AlemanAnalyzer();
        else if (idi.toLowerCase().equals("en")) 	analyzer = new InglesAnalyzer();
        else if (idi.toLowerCase().equals("ca")){ 	analyzer = new CatalanAnalyzer();}
		else		            	analyzer = new CastellanoAnalyzer();

		return analyzer;
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
     * Obtiene una Ficha.
     * Sino es visible devuelve null.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Ficha obtenerFichaPMA(Long id) {
        Session session = getSession();


        Ficha ficha = null;
        try {
            ficha = (Ficha) session.load(Ficha.class, id);
            if (visible(ficha)) {
                Hibernate.initialize(ficha.getDocumentos());
                Hibernate.initialize(ficha.getEnlaces());
                Hibernate.initialize(ficha.getMaterias());
                Hibernate.initialize(ficha.getHechosVitales());
                Hibernate.initialize(ficha.getFichasua());
                Hibernate.initialize(ficha.getBaner());
                Hibernate.initialize(ficha.getImagen());
                Hibernate.initialize(ficha.getIcono());
            } else {
            	return null;
        	}
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }

   		ArrayList listaOrdenada = new ArrayList(ficha.getDocumentos());
		Comparator comp = new DocsFichaComparator();
	  	Collections.sort(listaOrdenada, comp);
	  	ficha.setDocumentos(listaOrdenada);

   		ArrayList listaOrdenadaEnl = new ArrayList(ficha.getEnlaces());
		Comparator comp_enl = new EnlsFichaComparator();
	  	Collections.sort(listaOrdenadaEnl, comp_enl);
	  	ficha.setEnlaces(listaOrdenadaEnl);

        return ficha;

    }
    
    /**
     * Buscamos el numero de fichas activas des de la fecha actual
     * 
     * @param unidadAdministrativa
     * @param fecha
     * @return numero de Fichas activas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public int buscarFichasActivas(UnidadAdministrativa unidadAdministrativa, Date fechaCaducidad){
		Integer resultado = 0;
		Session session = getSession();
	
		try {
			
        	Query query = null;
        	if (unidadAdministrativa != null && unidadAdministrativa.getId() != null) {
        		query = session.createQuery("select count(*) from Ficha as fic, FichaUA as ficUA where fic.id = ficUA.ficha.id and ficUA.unidadAdministrativa.id= :id and fic.fechaCaducidad > :fecha");
        		query.setLong("id", unidadAdministrativa.getId());
	        	query.setDate("fecha", fechaCaducidad);
        	} else {
        		query = session.createQuery("select count(*) from Ficha as fic where fic.fechaCaducidad > :fecha");
	        	query.setDate("fecha", fechaCaducidad);
        	}
        	
        	resultado = (Integer) query.uniqueResult();
    		
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
     * @param unidadAdministrativa
     * @param fecha
     * @return numero de Fichas caducadas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public int buscarFichasCaducadas(UnidadAdministrativa unidadAdministrativa, Date fechaCaducidad){
		
		Integer resultado = 0;		
		Session session = getSession();
		try {
        	Query query = null;
        	if (unidadAdministrativa != null && unidadAdministrativa.getId() != null) {
        		query = session.createQuery("select count(*) from Ficha as fic, FichaUA as ficUA where fic.id = ficUA.ficha.id and ficUA.unidadAdministrativa.id= :id and fic.fechaCaducidad < :fecha");
        		query.setLong("id", unidadAdministrativa.getId());
	        	query.setDate("fecha", fechaCaducidad);
        	} else {
        		query = session.createQuery("select count(*) from Ficha as fic where fic.fechaCaducidad < :fecha");
	        	query.setDate("fecha", fechaCaducidad);
        	}
        	resultado = (Integer) query.uniqueResult();
    		
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
			
		return resultado;
	}

}
