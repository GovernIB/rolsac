package org.ibit.rol.sac.persistence.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

/**
 * SessionBean para mantener y consultar Hecho Vital Procedimiento.
 *
 * @ejb.bean
 *  name="sac/persistence/HechoVitalProcedimientoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HechoVitalProcedimientoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HechoVitalProcedimientoFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un HechoVitalProcedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarHechoVitalProcedimiento(HechoVitalProcedimiento hvp, Long hecho_id, Long procedimiento_id) {
        Session session = getSession();
        try {
            if (hvp.getId() == null) {
                HechoVital hecho = (HechoVital) session.load(HechoVital.class, hecho_id);
                ProcedimientoLocal proc = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, procedimiento_id);
                Hibernate.initialize(proc.getMaterias());
                Hibernate.initialize(proc.getHechosVitalesProcedimientos());
                hecho.addHechoVitalProcedimiento(hvp);
                proc.addHechoVitalProcedimiento(hvp);
                session.flush();
                Actualizador.actualizar(proc);
            } else {
                session.update(hvp);
                session.flush();
            }
            return hvp.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Crea o actualiza los HechoVitalProcedimiento de una coleccion.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void grabarHechoVitalProcedimientos(Collection<HechoVitalProcedimiento> hvpsAGrabar) {
        Session session = getSession();
        try {
        	for(HechoVitalProcedimiento hvp: hvpsAGrabar) {
	            if (hvp.getId() == null) {
	                HechoVital hecho = (HechoVital) session.load(HechoVital.class, hvp.getHechoVital().getId());
	                ProcedimientoLocal proc = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, hvp.getProcedimiento().getId());
	                Hibernate.initialize(proc.getMaterias());
	                Hibernate.initialize(proc.getHechosVitalesProcedimientos());
	                hecho.addHechoVitalProcedimientoRespetandoOrden(hvp);
	                proc.addHechoVitalProcedimiento(hvp);  // siempre respeta orden
	                Actualizador.actualizar(proc);
	            } else {
	                session.update(hvp);
	            }
        	}
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un HechoVitalProcedimiento.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public HechoVitalProcedimiento obtenerHechoVitalProcedimiento(Long id) {
        Session session = getSession();
        try {
            return (HechoVitalProcedimiento) session.load(HechoVitalProcedimiento.class, id);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Incrementa el orden de un hecho vital - procedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void subirOrden(Long id) {
        Session session = getSession();
        try {
            HechoVitalProcedimiento hechovp1 = (HechoVitalProcedimiento) session.load(HechoVitalProcedimiento.class, id);
            int orden = hechovp1.getOrden();
            if (orden > 0) {
                HechoVital hecho = hechovp1.getHechoVital();
                List listaHVP = hecho.getHechosVitalesProcedimientos();

                HechoVitalProcedimiento hechovp2 = (HechoVitalProcedimiento) listaHVP.get(orden - 1);

                hechovp2.setOrden(orden);
                listaHVP.set(orden, hechovp2);

                hechovp1.setOrden(orden - 1);
                listaHVP.set(orden - 1, hechovp1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un HechoVitalProcedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarHechoVitalProcedimiento(Long id) {
        Session session = getSession();
        try {
            HechoVitalProcedimiento hechovp = (HechoVitalProcedimiento) session.load(HechoVitalProcedimiento.class, id);
            hechovp.getHechoVital().removeHechoVitalProcedimiento(hechovp);
            ProcedimientoLocal procedimiento = hechovp.getProcedimiento();
            Hibernate.initialize(procedimiento.getMaterias());
            Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
            procedimiento.removeHechoVitalProcedimiento(hechovp);
            session.delete(hechovp);
            Actualizador.actualizar(procedimiento);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Borra una coleccion de HechoVitalProcedimiento.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarHechoVitalProcedimientos(Collection<Long> hvpsABorrar) {
        Session session = getSession();
        HechoVitalProcedimiento hechovp;
        try {
        	for (Long hvpId: hvpsABorrar) {
	            hechovp = (HechoVitalProcedimiento) session.load(HechoVitalProcedimiento.class, hvpId);
	            hechovp.getHechoVital().removeHechoVitalProcedimiento(hechovp);
	            ProcedimientoLocal procedimiento = hechovp.getProcedimiento();
	            Hibernate.initialize(procedimiento.getMaterias());
	            Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
	            procedimiento.removeHechoVitalProcedimiento(hechovp);
	            session.delete(hechovp);
	            Actualizador.actualizar(procedimiento);
        	}
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
}
