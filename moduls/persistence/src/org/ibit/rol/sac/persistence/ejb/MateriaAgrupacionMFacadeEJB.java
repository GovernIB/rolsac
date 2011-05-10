package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.AgrupacionMateria;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;

/**
 * SessionBean para mantener y consultar Materia AgrupacionMateria. 
 *
 * @ejb.bean
 *  name="sac/persistence/MateriaAgrupacionMFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.MateriaAgrupacionMFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class MateriaAgrupacionMFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza una MateriaAgrupacionM.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarMateriaAgrupacionM (MateriaAgrupacionM mata, Long mat_id, Long agrupacion_id) {
        Session session = getSession();
        try {
            if (mata.getId() == null) {
                Materia mat = (Materia) session.load(Materia.class, mat_id);
                AgrupacionMateria amat = (AgrupacionMateria) session.load(AgrupacionMateria.class, agrupacion_id);
                mat.addMateriaAgrupacionM(mata);
                amat.addMateriaAgrupacionM(mata);
            } else {
                session.update(mata);
            }
            session.flush();
            return mata.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una MateriaAgrupacionM.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public MateriaAgrupacionM obtenerMateriaAgrupacionM(Long id) {
        Session session = getSession();
        try {
            return (MateriaAgrupacionM) session.load(MateriaAgrupacionM.class, id);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Incrementa el orden de una materia - agrupacion.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void subirOrden(Long id) {
        Session session = getSession();
        try {
        	MateriaAgrupacionM materiava1 = (MateriaAgrupacionM) session.load(MateriaAgrupacionM.class, id);
            int orden = materiava1.getOrden();
            if (orden > 0) {
                AgrupacionMateria agru = materiava1.getAgrupacion();
                List<MateriaAgrupacionM> listaMA = agru.getMateriasAgrupacionM();

                MateriaAgrupacionM materiamp2 = (MateriaAgrupacionM) listaMA.get(orden - 1);

                materiamp2.setOrden(orden);
                listaMA.set(orden, materiamp2);

                materiava1.setOrden(orden - 1);
                listaMA.set(orden - 1, materiava1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un MateriaAgrupacionM.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarMateriaAgrupacionM(Long id) {
        Session session = getSession();
        try {
        	MateriaAgrupacionM materiaagr = (MateriaAgrupacionM) session.load(MateriaAgrupacionM.class, id);
        	materiaagr.getMateria().removeMateriaAgrupacionM(materiaagr);
        	materiaagr.getAgrupacion().removeMateriaAgrupacionM(materiaagr);
            session.delete(materiaagr);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
