package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.ibit.rol.sac.model.RegistroTemaSuscriptor;
import org.ibit.rol.sac.model.Suscriptor;




/**
 * @deprecated	Se usa únicamente desde el back antiguo
 * SessionBean para mantener y consultar envios de suscripcion.
 *
 * @ejb.bean
 *  name="sac/persistence/SuscriptorFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SuscriptorFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SuscriptorFacadeEJB extends PaginatedHibernateEJB {

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * @deprecated No se usa
     * Inicializo los par�metros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init(Long id, String estado, String grupo) {
    	super.tampagina=10;
    	super.pagina=0;
    	super.select="";
    	super.from=" from Suscriptor suscrip ";
    	super.where=" where ( suscrip.grupo.id = TO_NUMBER('" + grupo + "') or '-1' = '"+grupo+"' ) and ( suscrip.estado = '" + estado + "' or 'ALL' = '"+estado+"' ) and suscrip.tipoSuscripcion.id="+id.toString() + " and suscrip.id = (select max(a.id) from Suscriptor a where a.email = suscrip.email and a.tipoSuscripcion.id="+id.toString()+")";
    	super.whereini=" ";
    	super.orderby=" order by suscrip.email, suscrip.fechaAlta";

    	super.camposfiltro= new String[] {"suscrip.email", "suscrip.apellido1"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }

    /**
     *  @deprecated Usado desde el back antiguo
     * Inicializo los par�metros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init() {
    	super.tampagina=10;
    	super.pagina=0;
    	//super.select="";
    	super.select="";
    	super.from=" from Suscriptor suscrip ";
    	super.where=" suscrip.id = (select max(a.id) from Suscriptor a where a.email = suscrip.email)";
    	super.whereini=" ";
    	super.orderby=" order by suscrip.email, suscrip.fechaAlta";

    	super.camposfiltro= new String[] {"suscrip.email", "suscrip.apellido1"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }    

    /**
     * @deprecated Usado desde el back antiguo
     * Crea o actualiza un envio.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Long grabarSuscriptor(Suscriptor suscriptor) {
        Session session = getSession();
        try {
            session.saveOrUpdate(suscriptor);
            session.flush();
            return suscriptor.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
      * @deprecated Usado desde el back antiguo
     * Lista todos los suscriptores.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listar() {
        Session session = getSession();
        try {
        	parametrosCons(); // Establecemos los par�metros de la paginaci�n
           	
        	Query query = session.createQuery(select+from+where+orderby);
            query.setFirstResult(cursor-1);
            query.setMaxResults(tampagina);
        	return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * @deprecated No se usa
     * Lista todas los suscriptores segun estado y grupo
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List listarPorEstado(String estado, Long idGrupo) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Suscriptor as s where s.estado = :estado and s.grupo.id = :idGrupo");
            query.setParameter("estado", estado);
            query.setParameter("idGrupo", idGrupo);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }




    /**
     *  @deprecated Usado desde el back antiguo
     * Lista todas los suscriptores segun estado,grupo y tipo de suscripcion
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List listarPorEstadoTipo(String idtipo, String estado, Long idGrupo) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Suscriptor as s where s.estado = :estado and s.grupo.id = :idGrupo and ((s.boletin = 'S' and :idTipo = 'B')or(s.alertas = 'S' and :idTipo = 'A')or(s.estudios = 'S' and :idTipo = 'E'))");
            query.setParameter("estado", estado);
            query.setParameter("idGrupo", idGrupo);
            query.setParameter("idTipo", idtipo);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     *  @deprecated No se usa
     * Lista todas los suscriptores segun mail
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List listarPorMail(String mail) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Suscriptor as s where s.estado in ('A','P')  and s.email=:mail and s.tipoSuscripcion = '2'");
            query.setParameter("mail", mail);
            
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * @deprecated Usado desde el back antiguo
     * Lista todas los suscriptores segun estado y combinacion de temas
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List listarPorEstadoCombinacion(String estado, String combinacion) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Suscriptor as s where s.estado = :estado and s.resumenTemas = :combinacion");
            query.setParameter("estado", estado);
            query.setParameter("combinacion", combinacion);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }



    /**
     * @deprecated Usado desde el back antiguo
     * Obtiene un suscriptor. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Suscriptor obtenerSuscriptor(Long id) {
        Session session = getSession();
        try {
        	
        	Query query = session.createQuery("from Suscriptor s where s.id="+id.toString());
        	query.setMaxResults(1);
    		List result = query.list();
            if(result.size() != 0)
            	return (Suscriptor) result.get(0);
        	return null;
        	
  //          Suscriptor suscriptor = (Suscriptor) session.load(Suscriptor.class, id);
//            return suscriptor;
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     *  @deprecated No se usa
     * Obtiene un suscriptor a partir del correo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Suscriptor obtenerSuscriptorbyMail(String correo) {
        Session session = getSession();
        try {
        	
        	Query query = session.createQuery("from Suscriptor s where s.email='"+correo+"'");
        	query.setMaxResults(1);
    		List result = query.list();
            if(result.size() != 0)
            	return (Suscriptor) result.get(0);
        	return null;
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * @deprecated No se usa
     * Obtiene un suscriptor a partir del correo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Suscriptor obtenerSuscriptorbyMailEstado(String correo,String estado) { 
        Session session = getSession();
        try {
        	
        	Query query = session.createQuery("from Suscriptor s where s.email='"+correo+"' and s.tipoSuscripcion='2' and s.estado='"+estado+"'");
        	query.setMaxResults(1);
    		List result = query.list();
            if(result.size() != 0)
            	return (Suscriptor) result.get(0);
        	return null;
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    /**
     * Obtiene el suscriptor activo a partir del correo y tipo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Suscriptor obtenerSuscriptor(Long tipo, String correo) {
        Session session = getSession();
        try {
        	
        	Query query = session.createQuery(" from Suscriptor s where s.email='"+correo+"' and s.tipoSuscripcion='"+tipo+"' and s.estado='A'");
        	query.setMaxResults(1);
    		List result = query.list();
            if(result.size() != 0)
            	return (Suscriptor) result.get(0);
        	return null;
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
        
    /**
     *  @deprecated Usado desde el back antiguo
     * Descripci�n: Devuelve una lista con todos los suscriptores de un determinado tipo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public List<Suscriptor> getSuscriptoresByTipo(Long idTipo) {
    	 Session session = getSession();
         try {
        	   Query query = session.createQuery("from Suscriptor as s where s.tipoSuscripcion.id = :idTipo and s.estado = 'A'");
               query.setParameter("idTipo", idTipo);
    
               List<Suscriptor> suscriptores =  query.list();
               
               return suscriptores;
       } catch (HibernateException he) {
             throw new EJBException(he);
         } finally {
             close(session);
         }
    }
    
    /**
     * @deprecated No se usa
     * Actualiza resumen temas 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Set actualizaResumenTemas(Long idTipo) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Suscriptor as s where s.tipoSuscripcion.id = :idTipo and s.estado = 'A'");
            query.setParameter("idTipo", idTipo);
           
            HashSet temasSet = new HashSet();

            
            List suscriptores =  query.list();
            
            for(Iterator it = suscriptores.iterator(); it.hasNext();)
            {
            	Suscriptor sus = (Suscriptor) it.next();
            	Set temas = sus.getTemas();
            	List<Long> temasList = new ArrayList<Long>();
            	String resumen = "";
            	for(Iterator itTemas = temas.iterator(); itTemas.hasNext();)
            	{
            		RegistroTemaSuscriptor reg = (RegistroTemaSuscriptor) itTemas.next();
            		temasSet.add(reg.getIdMateria().toString());
            		temasList.add(reg.getIdMateria());
            	}
			  	RegistroTemaComparator comp = new RegistroTemaComparator();
            	Collections.sort(temasList,comp);
            	for(Iterator itTemasList = temasList.iterator(); itTemasList.hasNext();)
            	{
            		Long reg = (Long) itTemasList.next();
               		resumen += reg.toString() + "#";
            	}
            	sus.setResumenTemas(resumen);
            	session.saveOrUpdate(sus);
                session.flush();
            }
            return temasSet;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * @deprecated Usado desde el back antiguo
     * Recupera combinaciones 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Set recuperaCombinaciones(Long idTipo) {
        Session session = getSession();
        try {
            Query query = session.createQuery("select distinct s.resumenTemas from Suscriptor as s where s.tipoSuscripcion.id = :idTipo and s.estado = 'A' and s.resumenTemas is not null");
            query.setParameter("idTipo", idTipo);
            List suscriptores = query.list();
            HashSet combinacionesSet = new HashSet();
            for(Iterator it = suscriptores.iterator(); it.hasNext();)
            {
            	String comb = (String)it.next();
            	if((comb != null) && (!comb.equals("")))
            	{
            		combinacionesSet.add(comb);
            	}
            }
            return combinacionesSet;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    private static class RegistroTemaComparator implements Comparator {
    	public int compare(Object element1, Object element2) {
	    	Long lower2 = (Long)element2;
	    	Long lower1 = (Long)element1;
	      return lower1.compareTo(lower2);
    	}
    }
    

    /**
     * @deprecated Usado desde el back antiguo 
     * borra un Suscriptor
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public void borrarSuscriptor(Long id) {
    	// Primero actualizamos el estado y luego borramos la clave
        Session session = getSession();
        try {
        	Transaction tx = session.beginTransaction();
        	
        	Suscriptor suscriptor = (Suscriptor) session.load(Suscriptor.class, id);
        	
        	String mail = suscriptor.getEmail();
        	
        	suscriptor.setEstado(Suscriptor.TIPO_BAJA);
        	suscriptor.setFechaBaja(new Date());
            session.saveOrUpdate(suscriptor);
            
            session.flush();
            
            tx.commit();
            //return suscriptor.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }

    }
    
    /**
     * Lista todos los suscriptores.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean existeSuscriptor(String email, Long idTipo) {
        Session session = getSession();
        try {
        	
        	Query query = session.createQuery("from Suscriptor as s where s.tipoSuscripcion.id = :idTipo and s.email = :email and s.estado = :estado" );
            query.setParameter("email", email);
            query.setParameter("idTipo", idTipo);
            query.setParameter("estado", "A");
            return query.list().size()!=0;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
        
    }
 }
