package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegateI;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

/**
 * SessionBean para mantener y consultar Destinatarios (PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/DestinatarioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.DestinatarioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class DestinatarioFacadeEJB extends HibernateEJB implements DestinatarioDelegateI {

     /**
     * Obtiene referència al ejb de control de Acceso.
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
     * Crea o actualiza un Destinatario
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public Long grabarDestinatario(Destinatario destinatario) {
        Session session = getSession();
        try {
            session.saveOrUpdate(destinatario);
            session.flush();
            return destinatario.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un Destinatario
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Destinatario obtenerDestinatario(Long id) {
        Session session = getSession();
        try {
            Destinatario destinatario = (Destinatario) session.load(Destinatario.class, id);
            return destinatario;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


     /**
     * Lista los destinatarios
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<Destinatario> listarDestinatarios(){
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Destinatario.class);
            return criteri.list();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarDestinatario(Long id) {
        Session session = getSession();
        try {

            Destinatario destinatario = (Destinatario) session.load(Destinatario.class, id);
            session.delete(destinatario);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
}
