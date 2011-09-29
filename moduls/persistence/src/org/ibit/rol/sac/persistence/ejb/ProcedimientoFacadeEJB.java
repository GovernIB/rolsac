package org.ibit.rol.sac.persistence.ejb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
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
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.FetchMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.ibit.lucene.indra.model.Catalogo;
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
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.webcaib.ActuacioMinModel;
import org.ibit.rol.sac.model.webcaib.ActuacioModel;
import org.ibit.rol.sac.model.webcaib.DocumentModel;
import org.ibit.rol.sac.model.webcaib.NormativaModel;
import org.ibit.rol.sac.model.webcaib.TaxaModel;
import org.ibit.rol.sac.model.webcaib.Traduccio;
import org.ibit.rol.sac.model.webcaib.TramitModel;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;


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
     * Obtiene referï¿½ncia al ejb de control de Acceso.
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
     * Autoriza la creación de un procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaCrearProcedimiento(Integer validacionProcedimiento) throws SecurityException  {
    	return !(validacionProcedimiento.equals(Validacion.PUBLICA) && !userIsSuper()); 
    }
    
        
    /**
     * Autoriza la modificación de un procedimiento
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
                    throw new SecurityException("No puede crear un procedimiento público");
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

            /* Se alimenta la fecha de actualización de forma automática si no se ha introducido dato*/                      
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
     * Actualiza los ordenes de los procedimientos de una ua por conselleria
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenPros(Map map) {
   
    	Session session = getSession();
        try {
        	Long id;
        	 
            int valor_orden = 0; 
            int conta =0;
        	List pro_orden = new ArrayList();
        	
        	Iterator it = map.entrySet().iterator();
        	while (it.hasNext()) {
        		Map.Entry e = (Map.Entry)it.next();
        	
            	String paramName = e.getKey().toString();
            	if (paramName.startsWith("orden_pro")) {
            		id=  Long.valueOf(paramName.substring(9)).longValue();
             		String[] parametros=(String[])e.getValue();
             		if( (""+parametros[0]).equals("null")||(""+parametros[0]).equals("")||(""+parametros[0]).equals(" "))
             		{
             			valor_orden = 9999;
             		}
             		else
             		{	
             			conta ++;
             			valor_orden= Integer.parseInt(parametros[0]);
            		}
             		//valor_orden=  Integer.parseInt(parametros[0]);
            		if (!getAccesoManager().tieneAccesoProcedimiento(id)) { 
            			throw new SecurityException("No tiene acceso al procedimiento");
            		}
            		ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            		Long val = new Long(valor_orden);
            		procedimiento.setOrden(val);
            		pro_orden.add(procedimiento);
            	}
            }
            session.flush();
            
            Collections.sort( pro_orden, new ProComparator() );
            Long contador= Long.parseLong("1");
        	
        	Iterator itdoc=pro_orden.iterator();
    		ProcedimientoLocal pro=null;
    		while (itdoc.hasNext()) {
    			pro=(ProcedimientoLocal)itdoc.next();
    			if (contador>conta){
    				pro.setOrden(Long.parseLong("9999"));
    			}
    			else 
    			{	
    				pro.setOrden(contador);
    			}	
    			contador+=1;
    		}
             session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
            
    }
    
    class ProComparator implements Comparator {
		public int compare(Object o1, Object o2) { 
			Long x1=new Long (((ProcedimientoLocal)o1).getOrden());
			Long x2=new Long (((ProcedimientoLocal)o2).getOrden());
			return x1.compareTo( x2 ); 
		}
	}
    
    
    /**
     * Actualiza los ordenes de los procedimientos de una ua por direccion
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */   
    
    public void actualizarOrdenPros2 (Map map ){
    	   
    	Session session = getSession();
        try {
        	Long id;
        	 
            int valor_orden = 0; 
            int conta =0;
        	List pro_orden = new ArrayList();
        	
        	Iterator it = map.entrySet().iterator();
        	while (it.hasNext()) {
        		Map.Entry e = (Map.Entry)it.next();
        	
            	String paramName = e.getKey().toString();
            	if (paramName.startsWith("orden_pro")) {
            		id=  Long.valueOf(paramName.substring(9)).longValue();
             		String[] parametros=(String[])e.getValue();
             		if( (""+parametros[0]).equals("null")||(""+parametros[0]).equals("")||(""+parametros[0]).equals(" "))
             		{
             			valor_orden = 9999;
             		}
             		else
             		{	
             			conta ++;
             			valor_orden= Integer.parseInt(parametros[0]);
            		}
             		         		
             		//valor_orden=  Integer.parseInt(parametros[0]);
            		if (!getAccesoManager().tieneAccesoProcedimiento(id)) { 
            			throw new SecurityException("No tiene acceso al procedimiento");
            		}
            		ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            		Long val = new Long(valor_orden);
            		procedimiento.setOrden2(val); 
            		pro_orden.add(procedimiento);
            	}
            }
            session.flush();
            
          Collections.sort( pro_orden, new ProComparator2() );
            
            Long contador= Long.parseLong("1");
        	
        	Iterator itdoc=pro_orden.iterator();
    		ProcedimientoLocal pro=null;
    		while (itdoc.hasNext()) {
    			 pro=(ProcedimientoLocal)itdoc.next();
     			  
     			if (contador>conta){
    				pro.setOrden2(Long.parseLong("9999"));
    			}
    			else 
    			{	
    				pro.setOrden2(contador);
    			}	
     			contador+=1;
    		}
             session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
         
       
    }
    class ProComparator2 implements Comparator {
		public int compare(Object o1, Object o2) { 
			Long x1=new Long (((ProcedimientoLocal)o1).getOrden2());
			Long x2=new Long (((ProcedimientoLocal)o2).getOrden2());
			return x1.compareTo( x2 ); 
		}
	}
 
    /**
     * Actualiza los ordenes de los procedimientos de una ua por servicio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */   
    
    public void actualizarOrdenPros3 (Map map ){
    	   
    	Session session = getSession();
        try {
        	Long id;
        	 
            int valor_orden = 0; 
            int conta =0;
        	List pro_orden = new ArrayList();
        	
        	Iterator it = map.entrySet().iterator();
        	while (it.hasNext()) {
        		Map.Entry e = (Map.Entry)it.next();
        	
            	String paramName = e.getKey().toString();
            	if (paramName.startsWith("orden_pro")) {
            		id=  Long.valueOf(paramName.substring(9)).longValue();
              		String[] parametros=(String[])e.getValue();
              		if( (""+parametros[0]).equals("null")||(""+parametros[0]).equals("")||(""+parametros[0]).equals(" "))
             		{
             			valor_orden = 9999;
             		}
             		else
             		{	
             			conta ++;
             			valor_orden= Integer.parseInt(parametros[0]);
            		}            		
             		//valor_orden=  Integer.parseInt(parametros[0]);
            		if (!getAccesoManager().tieneAccesoProcedimiento(id)) { 
            			throw new SecurityException("No tiene acceso al procedimiento");
            		}
            		ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            		Long val = new Long(valor_orden);
            		procedimiento.setOrden3(val); 
            		pro_orden.add(procedimiento);
            	}
            }
            session.flush();
            
          Collections.sort( pro_orden, new ProComparator3() );
            
            Long contador= Long.parseLong("1");
        	
        	Iterator itdoc=pro_orden.iterator();
    		ProcedimientoLocal pro=null;
    		while (itdoc.hasNext()) {
    			 pro=(ProcedimientoLocal)itdoc.next();
    			 if (contador>conta){
     				pro.setOrden3(Long.parseLong("9999"));
     			 }
     			 else 
     			 {	
     				pro.setOrden3(contador);
     			 }	
    		  	 contador+=1;
    		}
             session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
         
       
    }
    class ProComparator3 implements Comparator {
		public int compare(Object o1, Object o2) { 
			Long x1=new Long (((ProcedimientoLocal)o1).getOrden3());
			Long x2=new Long (((ProcedimientoLocal)o2).getOrden3());
			return x1.compareTo( x2 ); 
		}
	}   
    /**
     * Lista todos los procedimientos.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarProcedimientos() {
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
                }
            }
            return procsvalidos;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un procedimiento Local.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimientoLocal obtenerProcedimiento(Long id) {
        Session session = getSession();
        ProcedimientoLocal procedimiento = null;
        try {
            procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            if (visible(procedimiento)) {
            	Hibernate.initialize(procedimiento.getDocumentos());
                Hibernate.initialize(procedimiento.getMaterias());
                Hibernate.initialize(procedimiento.getNormativas());
                Hibernate.initialize(procedimiento.getTramites());
                List<Tramite> tramites = procedimiento.getTramites(); 
                for (Iterator iter = tramites.iterator(); iter.hasNext();) {
                    Tramite tramite = (Tramite) iter.next();
                    Hibernate.initialize(tramite.getFormularios()); 
                    Hibernate.initialize(tramite.getDocsInformatius());
                    Hibernate.initialize(tramite.getTaxes());
                }
                Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
            } else {
                throw new SecurityException("El procedimiento no es visible");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
   		ArrayList listaOrdenada = new ArrayList(procedimiento.getDocumentos());
		Comparator comp = new DocsProcedimientoComparator();
	  	Collections.sort(listaOrdenada, comp);
	  	procedimiento.setDocumentos(listaOrdenada);    
        
        return procedimiento;
    }
        
    /**
     * Obtiene un procedimiento Local.{PORMAD}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimientoLocal obtenerProcedimientoPM(Long id) {
        Session session = getSession();
        ProcedimientoLocal procedimiento = null;
        try {
            procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
            if (visible(procedimiento)) {
            	Hibernate.initialize(procedimiento.getDocumentos());
                Hibernate.initialize(procedimiento.getMaterias());
                Hibernate.initialize(procedimiento.getNormativas());
                Hibernate.initialize(procedimiento.getTramites());
                Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
                Hibernate.initialize(procedimiento.getUnidadAdministrativa().getEdificios());
            } else {
                throw new SecurityException("El procedimiento no es visible");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
   		ArrayList listaOrdenada = new ArrayList(procedimiento.getDocumentos());
		Comparator comp = new DocsProcedimientoComparator();
	  	Collections.sort(listaOrdenada, comp);
	  	procedimiento.setDocumentos(listaOrdenada);                   
        
        return procedimiento;
    }
    
    class DocsProcedimientoComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Long x1; 
			if ((Documento)o1 == null) 
				x1 = new Long("0");
			else 
				x1=new Long (((Documento)o1).getOrden());
						
			Long x2; 
			if ((Documento)o2 == null) 
				x2 = new Long("0");
			else 
				x2=new Long (((Documento)o2).getOrden());
			
			return x1.compareTo( x2 ); 
		} 	
    }
  
    /**
	 * Dice si existe un procedimiento Local.   PSALUT
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public boolean existeProcedimiento(Long id) {
	    Session session = getSession();
	    try {
	       // ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
	        Criteria criteri = session.createCriteria(ProcedimientoLocal.class);
	        criteri.add(Expression.eq("id", id));
	        ProcedimientoLocal procedimiento = (ProcedimientoLocal)criteri.uniqueResult();
	        return procedimiento != null;
	    } catch (HibernateException he) {
	        throw new EJBException(he);
	    } finally {
	        close(session);
	    }
	}

	/**
     * Busca todas los Procedimientos que cumplen los criterios de bï¿½squeda
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
            
/* u92770[enric] inicializar todo conlleva mucho tiempo. Solo util para el toString 
            for(ProcedimientoLocal p:procedimientos){
            	Set<Tramite> trams= p.getTramites();
            	for(Iterator<Tramite> it=trams.iterator();it.hasNext();Hibernate.initialize(it.next()));
            	
            	List<Documento> docs= p.getDocumentos();
            	for(Iterator<Documento> it=docs.iterator();it.hasNext();Hibernate.initialize(it.next()));

            	Set<Normativa> norms= p.getNormativas();
            	for(Iterator<Normativa> it=norms.iterator();it.hasNext();Hibernate.initialize(it.next()));

            	Set<Materia> mats= p.getMaterias();
            	for(Iterator<Materia> it=mats.iterator();it.hasNext();Hibernate.initialize(it.next()));

            	Set<HechoVitalProcedimiento> hvp= p.getHechosVitalesProcedimientos();
            	for(Iterator<HechoVitalProcedimiento> it=hvp.iterator();it.hasNext();Hibernate.initialize(it.next()));
            }
*/           	
            if (!userIsOper()) {
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves) {    	
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
            String uaQuery = " and procedimiento.unidadAdministrativa.id in (";
            for (Iterator<UnidadAdministrativa> it = uas.iterator(); it.hasNext();) {
            	uaQuery += it.next().getId();
            	if (it.hasNext()) {
            		uaQuery += ", ";
            	} else {
            		uaQuery += ")";
            	}
            }
            
            Query query = session.createQuery("select distinct procedimiento from ProcedimientoLocal as procedimiento " +
                   ", procedimiento.traducciones as trad " + i18nQuery + uaQuery);
            for (int i = 0; i < params.size(); i++) {
                String o = (String)params.get(i);
                query.setString(i, o);
            }

            List<ProcedimientoLocal> procedimientos = query.list();
            if (!userIsOper()) {
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
                return procedimientosAcceso;
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
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
     * Obtiene una lista de procedimientos de la misma Familia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarProcedimientosFamilia(Long id) {
        Session session = getSession();
        try {
            String sQuery = "procedimiento.familia.id = " + id;
            Query query = session.createQuery("from ProcedimientoLocal as procedimiento where " + sQuery);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
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
            // Habrï¿½ una consulta por nivel.
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
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Aï¿½ade una nueva normativa al procedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void anyadirNormativa(Long norm_id, Long proc_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
            Normativa normativa = (Normativa) session.load(Normativa.class, norm_id);
            procedimiento.getNormativas().add(normativa);

            Actualizador.actualizar(normativa, procedimiento.getId());

            session.flush();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * elimina una normativa del procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void eliminarNormativa(Long normativa_id, Long proc_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
            Normativa normativa = (Normativa) session.load(Normativa.class, normativa_id);
            procedimiento.getNormativas().remove(normativa);
            Actualizador.borrar(normativa, procedimiento.getId());
            session.flush();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Añade una nueva materia al procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void anyadirMateria(Long materia_id, Long proc_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
            Materia materia = (Materia) session.load(Materia.class, materia_id);
            procedimiento.getMaterias().add(materia);
            Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
            Actualizador.actualizar(procedimiento);
            indexBorraProcedimiento(procedimiento);
            indexInsertaProcedimiento(procedimiento,null);
            session.flush();

        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * elimina una materia del procedimiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void eliminarMateria(Long materia_id, Long proc_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
                throw new SecurityException("No tiene acceso al procedimiento");
            }
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
            Materia materia = (Materia) session.load(Materia.class, materia_id);
            procedimiento.getMaterias().remove(materia);
            Actualizador.actualizar(procedimiento);   
            indexBorraProcedimiento(procedimiento);
            indexInsertaProcedimiento(procedimiento,null);
            session.flush();
            
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Añade un nuevo tramite al procedimiento (el tramite ya existe en la base de datos)
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
            addOperacion(session, procedimiento, Auditoria.BORRAR);
            Historico historico = getHistorico(session, procedimiento);
            ((HistoricoProcedimiento) historico).setProcedimiento(null);
            //for (HechoVitalProcedimiento hvp : (Set<HechoVitalProcedimiento>)procedimiento.getHechosVitalesProcedimientos()) {
            //    HechoVital hv = hvp.getHechoVital();
            //    hv.removeHechoVitalProcedimiento(hvp);
            //}
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
            return new ArrayList(unidadAdministrativa.getProcedimientos());
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
            hql+=" and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > :now ) and pro.validacion = 1";
            if (conse==1) { hql+=" order by pro.orden  asc,pro.fechaActualizacion desc";}
            if (conse==2) { hql+=" order by pro.orden2 asc,pro.fechaActualizacion desc";}	
            if (conse==3) { hql+=" order by pro.orden3 asc,pro.fechaActualizacion desc";} 
            Query query = session.createQuery(hql);
            query.setLong("uaid", id);
            query.setDate("now", new Date());
            return query.list();

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
    		return procsFinales;
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    /*
     * Obtiene los procedimientos publicos de una unidad administrativa (PORMAD recuperacion de datos inicial)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    /*@SuppressWarnings("unchecked")
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) {
    	Session session = getSession();
    	try {
    		List<ProcedimientoLocal> procsFinales;
    		
    		final Query query = session.createQuery(
    				"SELECT DISTINCT proc From ProcedimientoLocal as proc " +
    				" join proc.hechosVitalesProcedimientos as mat " +
    				" join proc.materias as hec " +
    				"WHERE proc.unidadAdministrativa.id=:unidad " +
    				"  AND ((mat) in (SELECT mat FROM Materia as mat WHERE mat.codigoEstandar in (:mats)) " +
    				"       OR (hec) in (SELECT elements(hecho.hechosVitalesProcedimientos) FROM HechoVital as hecho WHERE hecho.codigoEstandar in (:hecs))) " +
    				"");
    		query.setLong("unidad",idUA);
    		query.setParameterList("mats",codEstMat,Hibernate.STRING);
			query.setParameterList("hecs",codEstHV,Hibernate.STRING);
    		
			procsFinales = (List<ProcedimientoLocal>)query.list();
			for(ProcedimientoLocal proc: procsFinales){
				Hibernate.initialize(proc.getHechosVitalesProcedimientos());
				Hibernate.initialize(proc.getMaterias());
			}
    		return procsFinales;
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }*/

    /*
     * Listar procedimientos publicos de un Hecho Vital y una Unidad Administrativa(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    /*public List<ProcedimientoLocal> listarProcedimientosHechoVitalUA(Long hecho_id, Long ua_id) {
        Session session = getSession();
        try {
            List<ProcedimientoLocal> result = new ArrayList();
            HechoVital hecho = (HechoVital) session.load(HechoVital.class, hecho_id);

            for (Iterator iterator = hecho.getHechosVitalesProcedimientos().iterator(); iterator.hasNext();) {
                ProcedimientoLocal procedimiento = ((HechoVitalProcedimiento) iterator.next()).getProcedimiento();
                if ( procedimiento.getUnidadAdministrativa().getId().equals(ua_id) && publico(procedimiento)){
                   result.add(procedimiento);
                }
            }
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }*/

     /**
     * Listar procedimientos publicos de una Materia y una Unidad Administrativa(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List<ProcedimientoLocal> listarProcedimientosMateriaUA(Long materia_id, Long ua_id) {
        Session session = getSession();
        try {
            List<ProcedimientoLocal> result = new ArrayList();
            Materia materia = (Materia) session.load(Materia.class, materia_id);
            for (Iterator iterator = materia.getProcedimientosLocales().iterator(); iterator.hasNext();) {
                ProcedimientoLocal procedimiento = (ProcedimientoLocal) iterator.next();
                if ( procedimiento.getUnidadAdministrativa().getId().equals(ua_id) && publico(procedimiento)){
                   result.add(procedimiento);
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
     * Listar procedimientos Hecho Vital
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarProcedimientosHechoVital(Long hecho_id) {
        Session session = getSession();
        try {
            List result = new ArrayList();
            HechoVital hecho = (HechoVital) session.load(HechoVital.class, hecho_id);
            for (Iterator iterator = hecho.getHechosVitalesProcedimientos().iterator(); iterator.hasNext();) {
                ProcedimientoLocal procedimiento = ((HechoVitalProcedimiento) iterator.next()).getProcedimiento();
                result.add(procedimiento);
            }
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene los procedimientos públicos de un Hecho Vital
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

            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Consulta toda la informaciï¿½n de un procedimiento
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
     * Construye el query de búsqueda segun los parámetros
     */
    private String populateQuery(Map parametros, Map traduccion, List params) {
        String aux = "";

        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (value != null) {
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
     * Busca todos los {@link ProcedimientoLocal} cuyo nombre contenga el String de entrada(PORMAD)
     * 
     * @param busqueda
     * @param idioma
     * @return lista de {@link ProcedimientoLocal}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<ProcedimientoLocal> buscar(final String busqueda, final String idioma){
		List<ProcedimientoLocal> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("from ProcedimientoLocal as prc, prc.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	resultado = (List<ProcedimientoLocal>)query.list();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			resultado = Collections.emptyList();
		}
		
		return resultado;
	}


	/**
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * 
	 * Debemos incluir las materias y los hechos vitales, la unidad administrativa de la que depende y la familia.
	 * 
	 * Método válido para Procedimientos los 3 tipos:
	 * 
	 * Procedimiento No telemático, los de Rolsac por defecto (sin url, ni version ni modelo)
	 * Procedimiento Telemático de Sistra (tiene versión y modelo)
	 * Procedimiento Telemático Externo (tiene url)
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
     * Añade los procedimientos al indice en todos los idiomas
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
	            	if (trad.getRecursos()!=null)		io.addTextLine(trad.getRecursos()); // No está en el mantenimiento
	            	if (trad.getRequisitos()!=null)		io.addTextLine(trad.getRequisitos());
	            	if (trad.getSilencio()!=null)		io.addTextLine(trad.getSilencio());
					
	            }
				io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getFamilia_text());
				// Añadimos colecciones pero solo títulos como opcional
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
	            //se añaden todos los documentos en todos los idiomas
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

						// Se crea la indexación del documento individual y se añade la información 
		            	// para la indexación de la ficha.
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
		

    private ProcedimientoLocal inicializaColecciones(ProcedimientoLocal procedimiento) {
        Session session = getSession();
        try {
                Hibernate.initialize(procedimiento.getDocumentos());
                Hibernate.initialize(procedimiento.getMaterias());
                Hibernate.initialize(procedimiento.getNormativas());
                Hibernate.initialize(procedimiento.getTramites());
                Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
                return procedimiento;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
	
    

    class TramiteComparator implements Comparator {
		public int compare(Object o1, Object o2) { 
			Long x1=new Long (((Tramite)o1).getOrden());
			Long x2=new Long (((Tramite)o2).getOrden());
			return x1.compareTo( x2 ); 
		}
	}
    
    
    //WEBCAIB//
    
	 /**
	  * WEBCAIB
     * Obtiene una actuación.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ActuacioModel getActuacio ( String code, String idioma, String previ ) {
    	
        Session session = getSession();
        try {
    		Query query = session.createQuery(
    				"select pro " +
    				"from ProcedimientoLocal as pro " + 					
    					"where pro.id = :id_pro " +
    					("n".equals(previ) ? " and pro.validacion = 1 " : "") );
    		
    		query.setParameter("id_pro", code);
    		
    		ProcedimientoLocal proc = (ProcedimientoLocal)query.uniqueResult();
    		
    		if (proc == null)
    			return null;
    		
    		
    		TraduccionProcedimientoLocal traProcDefecto = (TraduccionProcedimientoLocal)proc.getTraduccion(idioma_per_defecte);
    		TraduccionProcedimientoLocal traProc = (TraduccionProcedimientoLocal)proc.getTraduccion(idioma);
    		if (traProc == null)
    			traProc = traProcDefecto;

    		
    		ActuacioModel actuacioModel = new ActuacioModel();
    		actuacioModel.setCodi(proc.getId().toString());
    		actuacioModel.setDataActualitzacio(proc.getFechaActualizacion());
    		actuacioModel.setData_caducitat(proc.getFechaCaducidad());
    		actuacioModel.setData_publicacio(proc.getFechaPublicacion());
    		UnidadAdministrativa una = proc.getUnidadAdministrativa();
    		if (una != null)
    			actuacioModel.setCodiOrganismeGenerador(una.getId().toString());
    		actuacioModel.setIdTramite(proc.getTramite());
    		
    		
    		Long tmp = proc.getVersion();
    		if (tmp != null)
    			actuacioModel.setVersionTramite(tmp.intValue());
    		    		    		
    		actuacioModel.setUrlExternaTramite(proc.getUrl());
    		actuacioModel.setIndicador(proc.getIndicador());
    		actuacioModel.setTaxa("on".equals(proc.getTaxa()));
    		UnidadAdministrativa unaRes = proc.getOrganResolutori();
    		if (unaRes != null) 
    			actuacioModel.addOrganismeResolutori(unaRes.getId().toString());
    		
    		actuacioModel.setNom(traProc.getNombre() != null ? traProc.getNombre() : traProcDefecto.getNombre());    		
    		actuacioModel.setResum(traProc.getResumen() != null ? traProc.getResumen() : traProcDefecto.getResumen());    		
    		actuacioModel.setResultat(traProc.getResultat() != null ? traProc.getResultat() : traProcDefecto.getResultat());
    		actuacioModel.setDestinataris(traProc.getDestinatarios() != null ? traProc.getDestinatarios() : traProcDefecto.getDestinatarios());
    		actuacioModel.setObservacions(traProc.getObservaciones() != null ? traProc.getObservaciones() : traProcDefecto.getObservaciones());
    		actuacioModel.setTerminis(traProc.getPlazos() != null ? traProc.getPlazos() : traProcDefecto.getPlazos());
    		actuacioModel.setResolucion(traProc.getResolucion() != null ? traProc.getResolucion() : traProcDefecto.getResolucion());
    		actuacioModel.setNotificacion(traProc.getNotificacion() != null ? traProc.getNotificacion() : traProcDefecto.getNotificacion());
    		actuacioModel.setSilenci(traProc.getSilencio() != null ? traProc.getSilencio() : traProcDefecto.getSilencio());
    		Familia fam = proc.getFamilia();
    		if (fam != null) {
    			TraduccionFamilia traFam = (TraduccionFamilia)fam.getTraduccion(idioma);
    			TraduccionFamilia traFamDefecto = (TraduccionFamilia)fam.getTraduccion(idioma_per_defecte);
    			if (traFam == null)
    				traFam = traFamDefecto;
    			actuacioModel.setFamilia(traFam.getNombre() != null ? traFam.getNombre() : traFamDefecto.getNombre());
    		}
    		Iniciacion ini = proc.getIniciacion();
    		if (ini != null) {
    			TraduccionIniciacion traIni = (TraduccionIniciacion)ini.getTraduccion(idioma);
    			TraduccionIniciacion traIniDefecto = (TraduccionIniciacion)ini.getTraduccion(idioma_per_defecte);
    			if (traIni == null)
    				traIni = traIniDefecto;
    			actuacioModel.setIniciacion(traIni.getNombre() != null ? traIni.getNombre() : traIniDefecto.getNombre());
    		}
    		actuacioModel.setRequisits(traProc.getRequisitos() != null ? traProc.getRequisitos() : traProcDefecto.getRequisitos());
    		
    		UnidadAdministrativa unaGen = proc.getUnidadAdministrativa();
    		if (unaGen != null) {
    			TraduccionUA traUnaGen = (TraduccionUA)unaGen.getTraduccion(idioma);
    			TraduccionUA traUnaGenDefecto = (TraduccionUA)unaGen.getTraduccion(idioma_per_defecte);
    			if (traUnaGen ==null)
    				traUnaGen = traUnaGenDefecto;
    			//actuacioModel.setOrganismeGenerador(Cadenas.initTab(traUnaGen.getNombre() != null ? traUnaGen.getNombre() : traUnaGenDefecto.getNombre()));
    			actuacioModel.setOrganismeGenerador(traUnaGen.getNombre() != null ? traUnaGen.getNombre() : traUnaGenDefecto.getNombre());
    		}
    		actuacioModel.setRecursos(traProc.getRecursos() != null ? traProc.getRecursos() : traProcDefecto.getRecursos());
    		actuacioModel.setLloc(traProc.getLugar() != null ? traProc.getLugar() : traProcDefecto.getLugar());
    		    		
    		//trámites
    		
    		//List<Tramite> listaTramites = (List)proc.getTramites(); //así no salen todos...
    		Query queryTramites = session.createQuery(
    				"select tra " +
    				"from Tramite as tra " + 					
    					"where tra.procedimiento = :id_pro " +
    					"and tra.validacio = 1 " +
    					"order by tra.orden");
    		
    		queryTramites.setParameter("id_pro", code);
    		List<Tramite> listaTramites = (List)queryTramites.list();
    		
    		
    		
    		//Long publico = new Long(1);
    		for (Tramite tramite : listaTramites) {
    			/*
    			if (!publico.equals(tramite.getValidacio()))
    				continue;
    			*/
    			TramitModel tramitModel = new TramitModel();
    			tramitModel.setCodi(tramite.getId().intValue());
    			
    			boolean caducat = false;
    			Date datCadu = tramite.getDataCaducitat();
    			if (null != datCadu) {
    				caducat = datCadu.compareTo(new Date()) < 0;
    			}
    			tramitModel.setCaducat(caducat);
    			tramitModel.setFase(String.valueOf(tramite.getFase()));
    			
    			TraduccionTramite traTramite = (TraduccionTramite)tramite.getTraduccion(idioma);
    			TraduccionTramite traTramiteDefecto = (TraduccionTramite)tramite.getTraduccion(idioma_per_defecte);
    			if (traTramite == null)
    				traTramite = traTramiteDefecto;
    			
    			tramitModel.setTitol(traTramite.getNombre() != null ? traTramite.getNombre() : traTramiteDefecto.getNombre());
    			tramitModel.setDescripcio(traTramite.getDescripcion() != null ? traTramite.getDescripcion() : traTramiteDefecto.getDescripcion());
    			tramitModel.setDocumentacio(traTramite.getDocumentacion() != null ? traTramite.getDocumentacion() : traTramiteDefecto.getDocumentacion());
    			tramitModel.setRequisits(traTramite.getRequisits() != null ? traTramite.getRequisits() : traTramiteDefecto.getRequisits());
    			tramitModel.setTermini(traTramite.getPlazos() != null ? traTramite.getPlazos() : traTramiteDefecto.getPlazos());
    			tramitModel.setLugar(traTramite.getLugar() != null ? traTramite.getLugar() : traTramiteDefecto.getLugar());
    			
    			//Documentos informativos del trámite
    			Set<DocumentTramit> documentosInformativos = tramite.getDocsInformatius();
    			for (DocumentTramit doc : documentosInformativos) {
    				DocumentModel docModel = new DocumentModel();
    				TraduccionDocumento traDoc = (TraduccionDocumento)doc.getTraduccion(idioma);
    				TraduccionDocumento traDocDefecto = (TraduccionDocumento)doc.getTraduccion(idioma_per_defecte);    				
    				if (traDoc == null)
    					traDoc = traDocDefecto;
    				
    				docModel.setDescripcio(traDoc.getDescripcion() != null ? traDoc.getDescripcion() : traDocDefecto.getDescripcion());
    				docModel.setTitol(traDoc.getTitulo() != null ? traDoc.getTitulo() : traDocDefecto.getTitulo());
    				docModel.setArxiu(doc.getArchivo() != null ? doc.getArchivo().getId().intValue() : 0);
    				
    				tramitModel.addDocInformatiu(docModel);
    			}
    			
    			//Formularios del trámite
    			Set<DocumentTramit> forms = tramite.getFormularios();
    			for (DocumentTramit doc : forms) {
    				DocumentModel docModel = new DocumentModel();
    				TraduccionDocumento traDoc = (TraduccionDocumento)doc.getTraduccion(idioma);
    				TraduccionDocumento traDocDefecto = (TraduccionDocumento)doc.getTraduccion(idioma_per_defecte);
    				if (traDoc == null)
    					traDoc = traDocDefecto;    				
    				
    				docModel.setDescripcio(traDoc.getDescripcion() != null ? traDoc.getDescripcion() : traDocDefecto.getDescripcion());
    				docModel.setTitol(traDoc.getTitulo() != null ? traDoc.getTitulo() : traDocDefecto.getTitulo());
    				docModel.setArxiu(doc.getArchivo() != null ? doc.getArchivo().getId().intValue() : 0);
    				
    				tramitModel.addFormulari(docModel);    				
    			}
    			
    			//Tasas del trámite
    			if ("on".equals(proc.getTaxa())) {
    				Set<Taxa> tasas = tramite.getTaxes();
    				for (Taxa tasa : tasas) {
    					TaxaModel taxaModel = new TaxaModel();

    					taxaModel.setCodiTra(tramitModel.getCodi());
    					
    					TraduccionTaxa traTasa = (TraduccionTaxa)tasa.getTraduccion(idioma);
    					TraduccionTaxa traTasaDefecto = (TraduccionTaxa)tasa.getTraduccion(idioma_per_defecte);
    					if (traTasa == null)
    						traTasa = traTasaDefecto;
    					
    					taxaModel.setCodiTax(traTasa.getCodificacio() != null ? traTasa.getCodificacio() : traTasaDefecto.getCodificacio());
    					taxaModel.setDesc(traTasa.getDescripcio() != null ? traTasa.getDescripcio() : traTasaDefecto.getDescripcio());
    					taxaModel.setFormPag(traTasa.getFormaPagament() != null ? traTasa.getFormaPagament() : traTasaDefecto.getFormaPagament());
    					
    					tramitModel.addTaxa(taxaModel);
    				}    				
    			}
    			
    			actuacioModel.addTramit(tramitModel);
    		}
    		
    		//Documentos
    		List<Documento> listaDocs = proc.getDocumentos();
    		for (Documento doc : listaDocs) {
    			DocumentModel docModel = new DocumentModel();
    			
    			TraduccionDocumento traDoc = (TraduccionDocumento)doc.getTraduccion(idioma);
    			TraduccionDocumento traDocDefecto = (TraduccionDocumento)doc.getTraduccion(idioma_per_defecte);
    			if (traDoc == null)
    				traDoc = traDocDefecto;
    			
    			docModel.setDescripcio(traDoc.getDescripcion() != null ? traDoc.getDescripcion() : traDocDefecto.getDescripcion());
    			docModel.setTitol(traDoc.getTitulo() != null ? traDoc.getTitulo() : traDocDefecto.getTitulo());
    			docModel.setArxiu(doc.getArchivo() != null ? doc.getArchivo().getId().intValue() : 0);
    			
    			actuacioModel.addDocuments(docModel);
    		}
    		
    		//Normativas
    		Set<Normativa> normativas = proc.getNormativas();
    		for (Normativa normativa : normativas) {
    			NormativaModel normModel = new NormativaModel();
    			
    			TraduccionNormativa traNorm = (TraduccionNormativa)normativa.getTraduccion(idioma);
    			TraduccionNormativa traNormDefecto = (TraduccionNormativa)normativa.getTraduccion(idioma_per_defecte);
    			
    			normModel.setCodi(normativa.getId().intValue());
    			normModel.setNumeroBoib(normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : null);
    			normModel.setData(normativa.getFecha());
    			normModel.setNumero(normativa.getNumero().toString());
    			normModel.setSumari(traNorm.getTitulo() != null ? traNorm.getTitulo() : traNormDefecto.getTitulo());
    			normModel.setPagini(traNorm.getPaginaInicial() != null ? traNorm.getPaginaInicial() : traNormDefecto.getPaginaInicial() != null ? traNormDefecto.getPaginaInicial() : null);
    			normModel.setPagfin(traNorm.getPaginaFinal() != null ? traNorm.getPaginaFinal() : traNormDefecto.getPaginaFinal() != null ? traNormDefecto.getPaginaFinal() : null);
    			
    			actuacioModel.addNormativa(normModel);
    		}
    		
    		
    		return actuacioModel;
        	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }    	
    }

    
	/** 
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions que estan relacionades amb la 
	 * fitxa especificada, en l'idioma especificat
	 *
	 * @param codiFitxa codi de la fitxa que interessa
	 * @param idioma en el que volem la informació.
	 * 
	 * @return col.lecció de ActuacioMinModel amb informació reduida de les actuacions
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public Collection actuacionsByMateria ( Long codiMateria, String idioma ) {
		
        Session session = getSession();
        try {
    		Query query = session.createQuery(
    				"select pro " +
    				"from ProcedimientoLocal as pro inner join pro.materias as mat inner join pro.unidadAdministrativa una " + 					
    					"where mat.id = :id_mat " +
    					"and pro.validacion = 1 " +
    					"and (pro.fechaPublicacion is null or pro.fechaPublicacion <= SYSDATE) " +
    					"and (pro.fechaCaducidad is null or pro.fechaCaducidad > SYSDATE) " +
    					"order by pro.fechaActualizacion desc");
    		
    		query.setParameter("id_mat", codiMateria, Hibernate.LONG);
    		
    		List<ProcedimientoLocal> listaProcedimientos = (List)query.list();
    		
    		Collection values = new Vector();
    		
    		for (ProcedimientoLocal pro : listaProcedimientos) {
    			ActuacioMinModel model = getActuacioMinModelFromProcedimiento(pro, idioma);
    			
    			values.add(model);
    		}
    		
    		return values;

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
	}

	
	/** 
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions que estan relacionades amb la 
	 * fitxa especificada, en l'idioma especificat
	 *
	 * @param codiFitxa codi de la fitxa que interessa
	 * @param idioma en el que volem la informació.
	 * @return col.lecció de ActuacioMinModel amb informació reduida de les actuacions
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Collection actuacionsByUORSS ( Long codiUO, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	String hql="select pro " +
	    			"from ProcedimientoLocal pro";
	    	hql += " where ( pro.unidadAdministrativa=:uaid"; 
	    	hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid) ";
	    	hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid)) ";            
	    	hql += " )";
	    	hql += " and (pro.fechaPublicacion IS NULL OR (pro.fechaPublicacion <= SYSDATE AND pro.fechaPublicacion > SYSDATE - 30)) ";
	    	hql += " and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > SYSDATE ) ";
	    	hql += " and pro.validacion = 1 ";
	    	
	    	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, codiUO);
	    	
	    	if (unidad.getId().intValue() == 1) {
	    		hql += "order by pro.orden asc, pro.fechaActualizacion desc";
	    	} else {
	    		//seguro que la unidad tiene padre distinto de nulo (pq la unica unidad con padre nulo es la 1)
	    		if (unidad.getPadre().getId().intValue() == 1) {
	    			hql += "order by pro.orden asc, pro.fechaActualizacion desc";
	    		} else {
	    			UnidadAdministrativa uniPadre = unidad.getPadre();
	    			if (uniPadre.getPadre().getId().intValue() == 1) {
	    				hql += "order by pro.orden2 asc, pro.fechaActualizacion desc";
	    			} else {
	    				hql += "order by pro.orden3 asc, pro.fechaActualizacion desc";
	    			}
	    		}	    		
	    	}
	    	
	    	Query query = session.createQuery(hql);
	    	query.setParameter("uaid", codiUO, Hibernate.LONG);
	    	
    		List<ProcedimientoLocal> listaProcedimientos = (List)query.list();
    		
    		Collection values = new Vector();
    		
    		for (ProcedimientoLocal pro : listaProcedimientos) {
    			ActuacioMinModel model = getActuacioMinModelFromProcedimiento(pro, idioma);
    			
    			values.add(model);
    		}
    		
    		return values;

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}
	
	
	/** 
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions que estan relacionades amb la 
	 * fitxa especificada, en l'idioma especificat
	 *
	 * @param codiFitxa codi de la fitxa que interessa
	 * @param idioma en el que volem la informació.
	 * @return col.lecció de ActuacioMinModel amb informació reduida de les actuacions
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Collection actuacionsByUO ( Long codiUO, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	String hql="select pro " +
	    			"from ProcedimientoLocal pro";
	    	hql += " where ( pro.unidadAdministrativa=:uaid"; 
	    	hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid) ";
	    	hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid)) ";            
	    	hql += " )";
	    	hql += " and (pro.fechaPublicacion IS NULL OR pro.fechaPublicacion <= SYSDATE) ";
	    	hql += " and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > SYSDATE ) ";
	    	hql += " and pro.validacion = 1 ";
	    	
	    	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, codiUO);
	    	
	    	if (unidad.getId().intValue() == 1) {
	    		hql += "order by pro.orden asc, pro.fechaActualizacion desc";
	    	} else {
	    		//seguro que la unidad tiene padre distinto de nulo (pq la unica unidad con padre nulo es la 1)
	    		if (unidad.getPadre().getId().intValue() == 1) {
	    			hql += "order by pro.orden asc, pro.fechaActualizacion desc";
	    		} else {
	    			UnidadAdministrativa uniPadre = unidad.getPadre();
	    			if (uniPadre.getPadre().getId().intValue() == 1) {
	    				hql += "order by pro.orden2 asc, pro.fechaActualizacion desc";
	    			} else {
	    				hql += "order by pro.orden3 asc, pro.fechaActualizacion desc";
	    			}
	    		}	    		
	    	}
	    	
	    	Query query = session.createQuery(hql);
	    	query.setParameter("uaid", codiUO, Hibernate.LONG);
	    		    	
    		List<ProcedimientoLocal> listaProcedimientos = (List)query.list();
    		
    		Collection values = new Vector();
    		
    		for (ProcedimientoLocal pro : listaProcedimientos) {
    			ActuacioMinModel model = getActuacioMinModelFromProcedimiento(pro, idioma);
    			
    			values.add(model);
    		}
    		
    		return values;

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
	
	
	/** 
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions mes cercades 
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public Collection actuacionsMasVisto () {
		
	    Session session = getSession();
	    
	    try {
	    	String hql = "select pro.id " +
	    			"from ProcedimientoLocal pro, Estadistica est, HistoricoProcedimiento his " +
	    			"where pro = his.procedimiento and pro.id is not null and " +
	    			"est.historico = his " +
	    			"and pro.validacion = 1 " +
	    			"and (pro.fechaPublicacion IS NULL OR pro.fechaPublicacion <= SYSDATE) " +
	    			"and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > SYSDATE ) " +
	    			"group by pro.id " +
	    			"order by sum(est.contador) desc";
	    	
	    		    	
	    	Query query = session.createQuery(hql);
	    	
	    	query.setMaxResults(5);
	    		    	
    		List<Long> listaProcedimientos = (List)query.list();
    		
    		Collection values = new Vector();
    		
    		for (Long idPro : listaProcedimientos) {
    			ActuacioMinModel model = new ActuacioMinModel();
    			model.setCodi(idPro.toString());
    			
    			values.add(model);
    		}
    		
    		return values;

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}
	
	
	/** 
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions que estan relacionades amb la 
	 * paraula de cerca
	 *
	 * @param word
	 * @param idioma en el que volem la informació.
	 * 
	 * @return col.lecció de ActuacioMinModel amb informació reduida de les actuacions
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public Collection actuacionsByWord ( String words, String idioma, String solovigor ) {
	
	    Session session = getSession();
	    
	    Long codiUO = new Long(1);
	    Collection values = new Vector();
	    
	    try {    	
	    	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, codiUO);
	    	
	    	if ( !Validacion.PUBLICA.equals(unidad.getValidacion()) )
		    	return values;
	    		    	
	    	String hql="select pro " +
			"from ProcedimientoLocal as pro, pro.traducciones as trad ";
			hql += " where index(trad) = :idioma and ";
			hql += " ( pro.unidadAdministrativa=:uaid"; 
			hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid) ";
			hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid)) ";            
			hql += " )";
			
			if ("si".equals(solovigor)) {
				hql += " and (pro.fechaPublicacion IS NULL OR pro.fechaPublicacion <= SYSDATE) ";
				hql += " and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > SYSDATE ) ";
				hql += " and pro.validacion = 1 ";	    	
			}
			
			hql += " and upper(trad.nombre) like :busqueda ";
	    	
	    	if (unidad.getId().intValue() == 1) {
	    		hql += "order by pro.orden asc, pro.fechaActualizacion desc";
	    	} else {
	    		//seguro que la unidad tiene padre distinto de nulo (pq la unica unidad con padre nulo es la 1)
	    		if (unidad.getPadre().getId().intValue() == 1) {
	    			hql += "order by pro.orden asc, pro.fechaActualizacion desc";
	    		} else {
	    			UnidadAdministrativa uniPadre = unidad.getPadre();
	    			if (uniPadre.getPadre().getId().intValue() == 1) {
	    				hql += "order by pro.orden2 asc, pro.fechaActualizacion desc";
	    			} else {
	    				hql += "order by pro.orden3 asc, pro.fechaActualizacion desc";
	    			}
	    		}	    		
	    	}
	    	
	    	Query query = session.createQuery(hql);
	    	query.setParameter("uaid", codiUO, Hibernate.LONG);
	    	query.setParameter("idioma", "ca");
	    	query.setParameter("busqueda", "%" + words.trim().toUpperCase() + "%");
	    	
    		List<ProcedimientoLocal> listaProcedimientos = (List)query.list();    		   		
    		
    		for (ProcedimientoLocal pro : listaProcedimientos) {
    			ActuacioMinModel model = getActuacioMinModelFromProcedimiento(pro, idioma);
    			
    			values.add(model);
    		}
    		
    		return values;

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
	
		
	/** 
	 * WEBCAIB
	 * Realiza una búsqueda avanzada en procedimientos.
	 * 
	 * Este método se ha copiado directamente del proyecto WEBCAIB durante la migración de métodos de este proyecto a ROLSAC.
	 * No ha sido posible su implementación en Hibernate debido a que hace uso de las instrucciones start with connect by de ORACLE.
	 * Además recibe en el parámetro condi condiciones where en lenguaje SQL que no entiende HQL y sería bastante costoso traducir de forma
	 * dinámica.
	 * 
	 * @return col.lecció de ActuacioMinModel amb informació reduida de les actuacions
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public Collection actuacionsByAvanzado ( String condi, String idioma, String uo, String solovigor, String idisel ) {
	
	    Session session = getSession();
	    	    
	    Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		Collection values = new Vector();
		Long codi = new Long(1);	    
	    
	    try {    	
	    	
	    	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, codi);
	    	
	    	if ( !Validacion.PUBLICA.equals(unidad.getValidacion()) )
		    	return values;

			String query = "select tpr_codpro codi, " +
			"tpr_nombre nom, " +
			"tpr_resume resume, " +
			"pro_fecact data, " +
			"pro_tramit id, "+
			"pro_versio versio, "+
			"pro_urlext urlexterna, "+ 
			"tun_nombre org "+
			"from rsc_proced, " +
			"rsc_trapro, " +
			"rsc_trauna ";
			//solo si hemos filtrado por tema, añadimos la tabla  
			if(!(condi.indexOf("prm_codmat")==-1))
				query+=",rsc_promat ";	
			//query+="where (pro_coduna =? "+ 
			//"or pro_coduna in (select una_codi from rsc_uniadm where una_coduna =? or  una_coduna in (select una_codi from rsc_uniadm where una_coduna =?))) ";
			
			//CANVI u102068 Aplicar recursivitat de nivells en la cerca per unitat orgànica
			//NOCYCLE query+="where (pro_coduna in (select una_codi from rsc_uniadm start with una_codi=? connect by NOCYCLE prior una_codi=una_coduna))";
			query+="where (pro_coduna in (select una_codi from rsc_uniadm start with una_codi=? connect by prior una_codi=una_coduna))";
			if(solovigor.equals("si")){
				query+=" and (pro_fecpub is null or pro_fecpub <= sysdate) " +
				"and (pro_feccad is null or pro_feccad > sysdate) "+
				"and pro_valida = 1 ";
			}
			query+=condi+
			" and pro_codi=tpr_codpro " +
			"and tun_coduna = pro_coduna "+
			"and tpr_codidi = ? " +
			"and tun_codidi = tpr_codidi ";
			if(!(condi.indexOf("prm_codmat")==-1))
				query+=" and prm_codpro = pro_codi ";
			
			if (unidad.getId().intValue() == 1) {
				query+=	"order by pro_ordcon asc,pro_fecact desc";
			} else {
				//seguro que la unidad tiene padre distinto de nulo (pq la unica unidad con padre nulo es la 1)
				if (unidad.getPadre().getId().intValue() == 1) {
					query+=	"order by pro_ordcon asc,pro_fecact desc";	
				} else {
					UnidadAdministrativa uniPadre = unidad.getPadre();
					if (uniPadre.getPadre().getId().intValue() == 1) {
						query+=	"order by pro_orddir asc,pro_fecact desc";
					} else {
						query+=	"order by pro_ordser asc,pro_fecact desc";
					}
				}	    		
			}			
			
			try {
				dbConnection = session.connection();

				stmt = dbConnection.prepareStatement(query);
				/*stmt.setString(1,uo);
				stmt.setString(2,uo);
				stmt.setString(3,uo);
				stmt.setString(4,idioma);*/
				//CANVI u102068 Aplicar recursivitat de nivells en la cerca per unitat orgànica
				stmt.setString(1,uo);
				stmt.setString(2,idioma);

				result = stmt.executeQuery();

				while (result.next()) {
					values.add(getMinModelFromResultSet(result, idisel, dbConnection));
				}

				return values;

			} finally {
				result.close();
				stmt.close();
			}	    	
	    	
	    	

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
	
	
	/**
	 * WEBCAIB
	 * Retorna el número de actuaciones que tiene la uo.<br/>
	 * 
	 * @param codiUO, int código de unidad orgánica
	 * @param idioma, String en el que volem la informació.

	 * @return int, número de actuaciones que tiene la uo.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Integer cuentaActuacionsByUO ( Long codiUO, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, codiUO);
	    	if (!Validacion.PUBLICA.equals(unidad.getValidacion()))
	    		return 0;
	    	
	    	
	    	String hql="select count(pro) " +
	    			"from ProcedimientoLocal pro";
	    	hql += " where ( pro.unidadAdministrativa=:uaid"; 
	    	hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid) ";
	    	hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid)) ";            
	    	hql += " )";
	    	hql += " and (pro.fechaPublicacion IS NULL OR pro.fechaPublicacion <= SYSDATE) ";
	    	hql += " and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > SYSDATE ) ";
	    	hql += " and pro.validacion = 1 ";
	    		    	
	    	Query query = session.createQuery(hql);
	    	query.setParameter("uaid", codiUO, Hibernate.LONG);
	    		    	
    		return (Integer)query.uniqueResult();

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
	
	
	/**
	 * WEBCAIB
	 * Retorna el número de actuaciones que tiene la uo.<br/>
	 * 
	 * @param codiUO, int código de unidad orgánica
	 * @param idioma, String en el que volem la informació.

	 * @return int, número de actuaciones que tiene la uo.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Integer cuentaActuacionsByFamiliaUO ( Long codiFamilia, Long coduo, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	
	    	String hql="select count(pro) " +
	    			"from ProcedimientoLocal pro, HistoricoProcedimiento his ";
	    	hql += " where his.procedimiento = pro and ";
	    	hql += " pro.familia = :idFamilia and ";
	    	hql += " ( pro.unidadAdministrativa=:uaid"; 
	    	hql += " or EXISTS (from UnidadAdministrativa ua where ua.padre= :uaid and ua.id = pro.unidadAdministrativa ) ";
	    	hql += " )";
	    	hql += " and (pro.fechaPublicacion IS NULL OR pro.fechaPublicacion <= SYSDATE) ";
	    	hql += " and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > SYSDATE ) ";
	    	hql += " and pro.validacion = 1 ";
	    	hql += " and EXISTS ( from Auditoria aud where aud.historico = his ) ";
	    	
	    		    	
	    	Query query = session.createQuery(hql);
	    	query.setParameter("uaid", coduo, Hibernate.LONG);
	    	query.setParameter("idFamilia", codiFamilia, Hibernate.LONG);
	    		    	
    		return (Integer)query.uniqueResult();

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
	
	
	/**
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions d'una familia determinada 
	 * ordenada per data publicació.
	 * 
	 * Este método se ha copiado directamente del proyecto WEBCAIB durante la migración de métodos de este proyecto a ROLSAC.
	 * No ha sido posible su implementación en Hibernate debido a que existe un bug en Hibernate 2 con la instrucción group by. En resumen
	 * requiere poner todos los campos de la entidad en el group by pero al tratarse de una entidad con discriminador no hay forma de 
	 * incluir el campo discriminador en el group by y la consulta resultante SQL falla en ORACLE.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Collection actuacionsByFamilia ( Long codiFamilia, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	
			Connection dbConnection = null;
			PreparedStatement stmt = null;
			ResultSet result = null;

			Collection values = new java.util.Vector();

			String query = "select tpr_codpro codi, "+ 
			"tpr_nombre nom, "+  
			"tpr_resume resume, " +	        	
			"pro_fecact data, "+ 
			"tun_nombre org, "+
			"pro_tramit id, "+
			"pro_versio versio, "+
			"pro_urlext urlexterna, "+ 
			"max(pro_fecact) ultimo "+ 				
			"from rsc_proced, " +
			"rsc_trapro, " +
			"rsc_trauna, "+
			"rsc_histor, "+ 
			"rsc_audito "+	      	        	
			"where  "+
			"pro_codfam = ? "+
			"and (pro_fecpub is null or pro_fecpub <= sysdate) " +
			"and (pro_feccad is null or pro_feccad > sysdate) " +
			"and pro_valida = 1 "+
			"and pro_codi=tpr_codpro "+  
			"and tun_coduna = pro_coduna "+
			"and tpr_codidi = ? "+
			"and tun_codidi = tpr_codidi  "+
			"and his_codpro = tpr_codpro "+ 
			"and his_codi=aud_codhis "+
			"group by tpr_codpro, tpr_nombre, tpr_resume, pro_fecact, tun_nombre, pro_tramit, pro_versio, pro_urlext "+ 
			"order by ultimo desc ";


			try {
				dbConnection = session.connection();

				stmt = dbConnection.prepareStatement(query);
				stmt.setLong(1, codiFamilia);
				stmt.setString(2,idioma_per_defecte);

				result = stmt.executeQuery();

				while (result.next()) {
					values.add(getMinModelFromResultSet(result,idioma, dbConnection));
				}

				return values;

			} finally {
				result.close();
				stmt.close();
			}

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}
	
	
	/**
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions d'una familia  i unitat orgànica determinada 
	 * ordenada per data publicació.
	 * 
	 * Este método se ha copiado directamente del proyecto WEBCAIB durante la migración de métodos de este proyecto a ROLSAC.
	 * No ha sido posible su implementación en Hibernate debido a que existe un bug en Hibernate 2 con la instrucción group by. En resumen
	 * requiere poner todos los campos de la entidad en el group by pero al tratarse de una entidad con discriminador no hay forma de 
	 * incluir el campo discriminador en el group by y la consulta resultante SQL falla en ORACLE.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Collection<ActuacioMinModel> actuacionsByFamiliaUO ( Long codiFamilia, Long coduo, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	
			Connection dbConnection = null;
			PreparedStatement stmt = null;
			ResultSet result = null;

			Collection<ActuacioMinModel> values = new java.util.Vector<ActuacioMinModel>();

			String query = "select tpr_codpro codi, "+ 
			"tpr_nombre nom, "+  
			"tpr_resume resume, " +					
			"pro_fecact data, "+ 
			"tun_nombre org, "+
			"pro_tramit id, "+
			"pro_versio versio, "+	
			"pro_urlext urlexterna, "+ 
			"max( pro_fecact) ultimo "+ 					
			"from rsc_proced, " +
			"rsc_trapro, " +
			"rsc_trauna, "+
			"rsc_histor, "+ 
			"rsc_audito "+			        	
			"where "+ 
			"pro_codfam = ? "+
			"and (pro_fecpub is null or pro_fecpub <= sysdate) " +
			"and (pro_feccad is null or pro_feccad > sysdate) " +
			"and pro_valida = 1 "+ 
			"and pro_codi=tpr_codpro "+  
			"and tpr_codidi = ?"+	 
			"and (pro_coduna in (select distinct(una_codi) from rsc_uniadm where una_coduna = ?) or pro_coduna = ?) "+
			"and tun_coduna = pro_coduna "+
			"and tun_codidi = tpr_codidi  "+
			"and his_codpro = tpr_codpro "+ 
			"and his_codi=aud_codhis "+
			"group by tpr_codpro, tpr_nombre, tpr_resume, pro_fecact, tun_nombre, pro_tramit, pro_versio, pro_urlext "+ 
			"order by ultimo desc ";

			try {
				dbConnection = session.connection();

				stmt = dbConnection.prepareStatement(query);
				stmt.setLong(1, codiFamilia);
				stmt.setString(2,idioma_per_defecte);
				stmt.setLong(3, coduo);
				stmt.setLong(4, coduo);

				result = stmt.executeQuery();

				while (result.next()) {
					values.add(getMinModelFromResultSet(result, idioma, dbConnection));
				}

				return values;

			} finally {
				result.close();
				stmt.close();
			}

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
	
	
	/**
	 * WEBCAIB
	 * Retorna una informació de totes les actuacions d'una familia i matèria determinada 
	 * ordenada per data publicació.
	 * 
	 * Este método se ha copiado directamente del proyecto WEBCAIB durante la migración de métodos de este proyecto a ROLSAC.
	 * No ha sido posible su implementación en Hibernate debido a que existe un bug en Hibernate 2 con la instrucción group by. En resumen
	 * requiere poner todos los campos de la entidad en el group by pero al tratarse de una entidad con discriminador no hay forma de 
	 * incluir el campo discriminador en el group by y la consulta resultante SQL falla en ORACLE.
	 *
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */	
	public Collection actuacionsByFamiliaMat( Long codiFamilia, Long codiMateria, String idioma ) {
	
	    Session session = getSession();
	    
	    try {
	    	
			Connection dbConnection = null;
			PreparedStatement stmt = null;
			ResultSet result = null;

			Collection values = new java.util.Vector();

			String query = "select tpr_codpro codi, "+ 
			"tpr_nombre nom, "+ 
			"tpr_resume resume, " +					
			"pro_fecact data, "+ 
			"tun_nombre org, "+
			"pro_tramit id, "+
			"pro_versio versio, "+
			"pro_urlext urlexterna, "+ 
			"max(aud_fecha) ultimo "+ 						
			"from rsc_proced, " +
			"rsc_trapro, " +
			"rsc_trauna, "+ 
			"rsc_promat, "+
			"rsc_histor, "+ 
			"rsc_audito "+						
			"where  "+
			"pro_codfam = ? "+
			"and (pro_fecpub is null or pro_fecpub <= sysdate) " +
			"and (pro_feccad is null or pro_feccad > sysdate) " +
			"and pro_valida = 1 "+
			"and pro_codi=tpr_codpro "+  
			"and tpr_codidi = ? "+
			"and prm_codpro = pro_codi "+
			"and prm_codmat = ? "+	
			"and tun_coduna = pro_coduna "+
			"and tun_codidi = tpr_codidi  "+
			"and his_codpro = tpr_codpro "+ 
			"and his_codi=aud_codhis "+
			"group by tpr_codpro, tpr_nombre, tpr_resume, pro_fecact, tun_nombre, pro_tramit, pro_versio, pro_urlext "+ 
			"order by ultimo desc ";

			try {
				dbConnection = session.connection();

				stmt = dbConnection.prepareStatement(query);
				stmt.setLong(1, codiFamilia);
				stmt.setString(2,idioma_per_defecte);
				stmt.setLong(3, codiMateria);

				result = stmt.executeQuery();

				while (result.next()) {
					values.add(getMinModelFromResultSet(result, idioma, dbConnection));
				}

				return values;

			} finally {
				result.close();
				stmt.close();
			}

	    } catch (Exception he) {
	    	throw new EJBException(he);
	    } finally {
	    	close(session);
	    }		
		
	}	
		
	
	/**
	 * Método de apoyo para transformar datos de la entidad ProcedimientoLocal a la entidad WEBCAIB ActuacioMinModel.
	 * @param pro ProcedimientoLocal.
	 * @param idioma idioma preferido.
	 * @return ActuacioMinModel.
	 */
	private ActuacioMinModel getActuacioMinModelFromProcedimiento(ProcedimientoLocal pro, String idioma) {
		ActuacioMinModel model = new ActuacioMinModel();
		
		model.setCodi(pro.getId().toString());
		if (pro.getFechaActualizacion() != null)
			model.setData(new java.sql.Date(pro.getFechaActualizacion().getTime()));
		model.setIdTramite(pro.getTramite());
		model.setVersionTramite(pro.getVersion() != null ? pro.getVersion().intValue() : 0);
		model.setUrlExternaTramite(pro.getUrl());
		
		TraduccionProcedimientoLocal traPro = (TraduccionProcedimientoLocal)pro.getTraduccion(idioma);
		TraduccionProcedimientoLocal traProDefecto = (TraduccionProcedimientoLocal)pro.getTraduccion(idioma_per_defecte);
		if (traPro == null)
			traPro = traProDefecto;
		
		model.setNom( traPro.getNombre() != null ? traPro.getNombre() : traProDefecto.getNombre() );
		model.setResum( traPro.getResumen() != null ? traPro.getResumen() : traProDefecto.getResumen());
		
		UnidadAdministrativa una = pro.getUnidadAdministrativa();
		if (una != null) {
			
			TraduccionUA traUna = (TraduccionUA)una.getTraduccion(idioma);
			TraduccionUA traUnaDef = (TraduccionUA)una.getTraduccion(idioma_per_defecte);
			if (traUna == null)
				traUna = traUnaDef;
			
			model.setOrganismeGenerador(traUna.getNombre() != null ? traUna.getNombre() : traUnaDef.getNombre());    				
		}
		return model;
	}	
	
	
	//Método adaptado de WEBCAIB para actuacionsByAvanzado, actuacionsByFamilia, actuacionsByFamiliaUO, actuacionsByFamiliaMat.
	private ActuacioMinModel getMinModelFromResultSet (ResultSet result, String idioma, Connection dbConnection) throws Exception {

		ActuacioMinModel model = new ActuacioMinModel();

		model.setCodi(result.getString("codi"));
		model.setData(result.getDate("data"));
		model.setIdTramite(result.getString("id"));
		model.setVersionTramite(result.getInt("versio"));
		model.setUrlExternaTramite(result.getString("urlexterna"));

		if (idioma.equals(idioma_per_defecte)){
			model.setNom(result.getString("nom"));
			model.setResum(result.getString("resume"));
			model.setOrganismeGenerador(result.getString("org"));
		}else {

				String queryTraduccio ="select " +
				"tpr_nombre nom, tpr_resume resume , tun_nombre org " +
				"from  " +
				"rsc_proced, " +
				"rsc_trapro, " +
				"rsc_trauna "+ 
				"where " +
				"tpr_codpro = " + result.getString("codi") +" "+
				"and pro_codi=tpr_codpro " +
				"and tun_coduna = pro_coduna "+
				"and tpr_codidi ='"+idioma+"' " +
				"and tun_codidi = tpr_codidi ";

				Traduccio acttrad = (Traduccio)getTraduccio(queryTraduccio, 3, dbConnection);
				if (acttrad != null) {
					model.setNom(nvl(acttrad.getCamp1(), result.getString("nom")));
					model.setResum(nvl(acttrad.getCamp2(), result.getString("resume")));
					model.setOrganismeGenerador(nvl(acttrad.getCamp3(), result.getString("org")));
				} else {
					model.setNom(result.getString("nom"));	
					model.setOrganismeGenerador(result.getString("org"));
				}	
		}
		return model;

	}	
	
	
	//Método adaptado de WEBCAIB para actuacionsByAvanzado, actuacionsByFamilia, actuacionsByFamiliaUO, actuacionsByFamiliaMat.
	private Traduccio getTraduccio ( String query, int camps, Connection dbConnection ) throws Exception {

		PreparedStatement stmt = null;
		ResultSet resultTrad = null;
		try{
			stmt = dbConnection.prepareStatement(query);

			resultTrad = stmt.executeQuery();

			if (!resultTrad.next()) {
				//throw new Exception("No s'ha trobat traducció per aquest idioma");
				return null;
			}


			Traduccio trad = new Traduccio();

			if ((resultTrad.getObject(1)!=null)&&(resultTrad.getObject(1).getClass().isPrimitive())){
				trad.setCamp1(resultTrad.getInt(1));
			}else{
				trad.setCamp1(resultTrad.getString(1));
			}

			if (camps > 1){
				if ((resultTrad.getObject(2)!=null)&&(resultTrad.getObject(2).getClass().isPrimitive())){
					trad.setCamp2(resultTrad.getInt(2));
				}else{
					trad.setCamp2(resultTrad.getString(2));
				}
				if (camps > 2){
					trad.setCamp3(resultTrad.getString(3));
					if (camps > 3){
						trad.setCamp4(resultTrad.getString(4));
						if (camps > 4){
							trad.setCamp5(resultTrad.getString(5));
							if (camps > 5){
								trad.setCamp6(resultTrad.getString(6));
								if (camps > 6){
									trad.setCamp7(resultTrad.getString(7));
									if (camps > 7){
										trad.setCamp8(resultTrad.getString(8));
										if (camps > 8){
											trad.setCamp9(resultTrad.getString(9));
											if (camps > 9){
												trad.setCamp10(resultTrad.getString(10));
												if (camps > 10){
													trad.setCamp11(resultTrad.getString(11));
												}	
											}	
										}
									}	
								}	
							}
						}	
					}		
				}		

			}

			return trad;

		} finally {
			resultTrad.close();
			stmt.close();
		}
	}   	
	
	//Método copiado de WEBCAIB para actuacionsByAvanzado.
    /**
     * Mètode d'utilitat per poder evitar els valors nulls
     * (versió amb valor per defecte explícit)
     * 
     * @param value valor que volem comprovar que no sigui null
     * @param defaultValue valor que emprarem si value es null
     * @return value si no es null i defaultValue si es null
     */
    protected String nvl (String value, String defaultValue ) {
        if ( value == null ) {
           return defaultValue;
        }
        return value;
    }	
    
}
