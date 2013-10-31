package org.ibit.rol.sac.persistence.ejb;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.model.Usuario;

/**
 * @deprecated Clase que únicamente se usa desde el back antiguo.
 * SessionBean para consultar Microsite.
 *
 * @ejb.bean
 *  name="sac/persistence/TipoSuscripcionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TipoSuscripcionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */

public abstract class TipoSuscripcionFacadeEJB extends PaginatedHibernateEJB
{

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Inicializo los par�metros de la consulta de Tipo de Suscripcion....
     * No est� bien hecho deber�a ser Statefull
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public void init() {
    	super.tampagina=10;
    	super.pagina=0;
    	super.select="";
    	super.from=" from TipoSuscripcion";
    	super.where="";
    	super.whereini=" ";
    	super.orderby="";

    	super.camposfiltro= new String[] {"nombre", "identificador"};
    	super.cursor=0;
    	super.nreg=0;
    	super.npags=0;	
    }        
    
    /**
     * Crea o actualiza un TipoSuscripcion
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public Long grabarTipoSuscripcion(TipoSuscripcion tipo) {
        Session session = getSession();
        try {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(tipo);
            session.flush();
            tx.commit();
            return tipo.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un TipoSuscripcion
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TipoSuscripcion obtenerTipoSuscripcion(Long id) {
        Session session = getSession();
        try {
        	
        	TipoSuscripcion tipo = (TipoSuscripcion) session.load(TipoSuscripcion.class, id);
        	//session.refresh(site);
            return tipo;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Lista todos los TiposSuscripcion
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public List listarTiposSuscripcion() {
        Session session = getSession();
        try {
        	Query query = session.createQuery(" FROM TipoSuscripcion tipo ");
        	return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Lista todos los Microsites a los que el usuario puede acceder
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
     */
    public List listarTiposSuscripcionFiltro(Usuario usu, Map param) {
        Session session = getSession();
        try {

        	Criteria criteri = session.createCriteria(TipoSuscripcion.class);
        	populateCriteria(criteri, param);
        	List lista = criteri.list();

        	return lista;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

 

    /**
     * borra un TipoSuscripcion
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarTipoSuscripcion(Long id) {
        Session session = getSession();
        try {
        	Transaction tx = session.beginTransaction();
        	TipoSuscripcion tipo = (TipoSuscripcion) session.load(TipoSuscripcion.class, id);
            session.delete(tipo);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    private void populateCriteria(Criteria criteri, Map parametros) {
        parametros.remove("id");
        for (Iterator iterator = parametros.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = parametros.get(key);
            if (value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        criteri.add(Expression.ilike(key, value));
                    }
                } else {
                    criteri.add(Expression.eq(key, value));
                }
            }
        }
    }

    
	
}