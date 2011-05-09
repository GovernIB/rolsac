package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.model.Idioma;


/**
 * SessionBean para mantener y consultar envios de suscripcion.
 *
 * @ejb.bean
 *  name="sac/persistence/GrupoSuscripcionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.GrupoSuscripcionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class GrupoSuscripcionFacadeEJB extends PaginatedHibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Inicializo los parámetros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init(Long id) {
    	super.tampagina=10;
    	super.pagina=0;
    	super.select="";
    	//super.select="select gru.id, trad.nombre ";
    	super.from=" from GrupoSuscripcion gru join gru.traducciones trad ";
    	super.where=" where index(trad)='"+System.getProperty("es.caib.rolsac.idiomaDefault")+"' and gru.tipoSuscripcion.id="+id.toString();
    	super.whereini="";
    	super.orderby=" order by trad.nombre";    	

    	super.camposfiltro= new String[] {"trad.nombre"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }

    /**
     * Inicializo los parámetros de la consulta....
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init() {
    	super.tampagina=10;
    	super.pagina=0;
    	super.select="";
    	//super.select="select gru.id, trad.nombre ";
    	super.from=" from GrupoSuscripcion gru join gru.traducciones trad ";
    	super.where=" where index(trad)='"+System.getProperty("es.caib.rolsac.idiomaDefault")+"'";
    	super.whereini="";
    	super.orderby=" order by trad.nombre";    	

    	super.camposfiltro= new String[] {"trad.nombre"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	

    }    
    
    
    /**
     * Crea o actualiza un envio.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public Long grabar(GrupoSuscripcion grupo) {
        Session session = getSession();
        try {
            session.saveOrUpdate(grupo);
            session.flush();
            return grupo.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Lista todos los grupos.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarGrupos() {
        Session session = getSession();
        try {
        	parametrosCons(); // Establecemos los parámetros de la paginación
           	
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
     * Lista todas los grupos de suscripcion para usar en Combos
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public List listarCombo(Long idTipo) {
        Session session = getSession();
        try {
        	Query query = session.createQuery("from GrupoSuscripcion grupo join grupo.traducciones trad where index(trad)='"+System.getProperty("es.caib.rolsac.idiomaDefault")+"' and grupo.tipoSuscripcion.id=" + idTipo.toString()+ " order by trad.nombre");
        	return query.list();
        	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Obtiene un grupo. 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public GrupoSuscripcion obtenerGrupo(Long id) {
        Session session = getSession();
        try {
        	GrupoSuscripcion grupo = (GrupoSuscripcion) session.load(GrupoSuscripcion.class, id);
           Hibernate.initialize(grupo.getSuscriptores());
            return grupo;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * borra un Grupo
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */    
    public void borrarGrupo(Long id) {
        Session session = getSession();
        try {
        	Transaction tx = session.beginTransaction();
        	GrupoSuscripcion grupo = (GrupoSuscripcion) session.load(GrupoSuscripcion.class, id);
            
        	if (id.intValue()!=0) 	session.delete(grupo);
            
            session.flush();
            
            tx.commit();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    

}
