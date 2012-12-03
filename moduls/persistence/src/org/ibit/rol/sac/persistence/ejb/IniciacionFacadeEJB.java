package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegateI;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Iniciacion
 *
 * @ejb.bean
 *  name="sac/persistence/IniciacionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IniciacionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IniciacionFacadeEJB extends HibernateEJB implements IniciacionDelegateI {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

  
     /**
     * Obtiene una  iniciacion.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Iniciacion obtenerIniciacion(Long id) {
        Session session = getSession();
        try {
            Iniciacion iniciacion = (Iniciacion) session.load(Iniciacion.class, id);
            
            return iniciacion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista todas las iniciaciones
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ResultadoBusqueda listarIniciacion(int pagina, int resultats, String idioma) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTMIniciacion(idioma));
    }
    
    private List listarTMIniciacion(String idioma) {
    	Session session = getSession();
    	
    	try {
    		Query query = session.createQuery("select ini.id, ini.codigoEstandar, trad.nombre " +
    														"from Iniciacion as ini, ini.traducciones as trad " +
    														"where index(trad) = :idioma " +
    														"order by ini.codigoEstandar asc");
    		
    		query.setParameter("idioma", idioma);
    		return query.list();    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }
    
    /**
     * Lista todas las iniciaciones
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarIniciacion() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Iniciacion.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Crea o actualiza un tipo Iniciacion.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarIniciacion(Iniciacion tipo) {
        Session session = getSession();
        try {
            session.saveOrUpdate(tipo);
            session.flush();
            return tipo.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
 
      
     

    /**
         * Borra un Tipo Iniciacion.
         * @ejb.interface-method
         * @ejb.permission role-name="${role.system},${role.admin}"
         */
        public void borrarIniciacion(Long id) {
            Session session = getSession();
            try {
            	Iniciacion tipo  = (Iniciacion) session.load(Iniciacion.class, id);
                 
                session.delete(tipo);
                session.flush();
            } catch (HibernateException he) {
                throw new EJBException(he);
            } finally {
                close(session);
            }
        }

}
