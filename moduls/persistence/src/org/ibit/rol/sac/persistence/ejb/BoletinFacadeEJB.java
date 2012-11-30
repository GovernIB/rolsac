package org.ibit.rol.sac.persistence.ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Boletin;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Boletin.
 *
 * @ejb.bean
 *  name="sac/persistence/BoletinFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.BoletinFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class BoletinFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 364556289440568520L;

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un Boletin.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarBoletin(Boletin boletin) {
        Session session = getSession();
        try {
            session.saveOrUpdate(boletin);
            session.flush();
            return boletin.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todos los boletines.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public ResultadoBusqueda listarBoletines(int pagina, int resultats) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarBoletines());
    }
    
    /**
     * Lista todos los boletines.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarBoletines() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Boletin.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un boletin.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Boletin obtenerBoletin(Long id) {
        Session session = getSession();
        try {
            return (Boletin) session.load(Boletin.class, id);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Nos dice si un boletin tiene normativas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean tieneNormativas(Long id){
        Session session = getSession();
        try {
            Boletin boletin = (Boletin) session.load(Boletin.class, id);
            Set normativas = boletin.getNormativas();
            return !normativas.isEmpty();
            //return normativas != null;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un Boletin.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarBoletin(Long id) {
        Session session = getSession();
        try {
            Boletin boletin = (Boletin) session.load(Boletin.class, id);
            Set normativas = boletin.getNormativas();
            if(!normativas.isEmpty()){
                throw new EJBException("El Boletin tiene normativas asociadas");
            }
            session.delete(boletin);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
