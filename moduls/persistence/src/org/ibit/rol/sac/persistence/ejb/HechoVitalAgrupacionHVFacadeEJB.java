package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.model.AgrupacionHechoVital;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;

/**
 * @deprecated Clase que se usa únicamente desde el back antiguo.
 * SessionBean para mantener y consultar Hecho Vital AgrupacionHechoVital. (PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/HechoVitalAgrupacionHVFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HechoVitalAgrupacionHVFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HechoVitalAgrupacionHVFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = -5074196397464022659L;

	/**
     * @deprecated Usado desde el back antiguo
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    /**
     * @deprecated Usado desde el back antiguo
     * Crea o actualiza un HechoVitalAgrupacionHV.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarHechoVitalAgrupacionHV(HechoVitalAgrupacionHV hva, Long hecho_id, Long agrupacion_id) {
        Session session = getSession();
        try {
            if (hva.getId() == null) {
                HechoVital hecho = (HechoVital) session.load(HechoVital.class, hecho_id);
                AgrupacionHechoVital ahecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, agrupacion_id);
                hecho.addHechoVitalAgrupacionHV(hva);
                ahecho.addHechoVitalAgrupacionHV(hva);
            } else {
                session.update(hva);
            }
            session.flush();
            return hva.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * @deprecated No se usa 
     * Obtiene un HechoVitalAgrupacionHV.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public HechoVitalAgrupacionHV obtenerHechoVitalAgrupacionHV(Long id) {
        Session session = getSession();
        try {
            return (HechoVitalAgrupacionHV) session.load(HechoVitalAgrupacionHV.class, id);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * @deprecated Usado desde el back antiguo
     * Incrementa el orden de un hecho vital - agrupacion.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void subirOrden(Long id) {
        Session session = getSession();
        try {
            HechoVitalAgrupacionHV hechova1 = (HechoVitalAgrupacionHV) session.load(HechoVitalAgrupacionHV.class, id);
            int orden = hechova1.getOrden();
            if (orden > 0) {
                AgrupacionHechoVital agru = hechova1.getAgrupacion();
                List<HechoVitalAgrupacionHV> listaHVA = agru.getHechosVitalesAgrupacionHV();

                HechoVitalAgrupacionHV hechovp2 = (HechoVitalAgrupacionHV) listaHVA.get(orden - 1);

                hechovp2.setOrden(orden);
                listaHVA.set(orden, hechovp2);

                hechova1.setOrden(orden - 1);
                listaHVA.set(orden - 1, hechova1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * @deprecated Usado desde el back antiguo
     * Borra un HechoVitalAgrupacionHV.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarHechoVitalAgrupacionHV(Long id) {
        Session session = getSession();
        try {
            HechoVitalAgrupacionHV hechova = (HechoVitalAgrupacionHV) session.load(HechoVitalAgrupacionHV.class, id);
            hechova.getHechoVital().removeHechoVitalAgrupacionHV(hechova);
            hechova.getAgrupacion().removeHechoVitalAgrupacionHV(hechova);
            session.delete(hechova);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
