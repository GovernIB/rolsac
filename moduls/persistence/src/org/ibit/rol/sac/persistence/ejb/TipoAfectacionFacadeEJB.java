package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.TipoAfectacion;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Tipo Afectacion.
 *
 * @ejb.bean
 *  name="sac/persistence/TipoAfectacionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TipoAfectacionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TipoAfectacionFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 5246339899571321776L;

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un tipo Afectacion.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarTipoAfectacion(TipoAfectacion tipo) {
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
     * Lista todos los tipos afectaciones.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public ResultadoBusqueda listarTiposAfectaciones(int pagina, int resultats) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTiposAfectaciones());
    }
    
    /**
     * Lista todos los tipos afectaciones.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List<TipoAfectacion> listarTiposAfectaciones() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(TipoAfectacion.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
        * Obtiene un tipo afectacion.
        * @ejb.interface-method
        * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
        */
       public TipoAfectacion obtenerTipoAfectacion(Long id) {
           Session session = getSession();
           try {
               TipoAfectacion tipoAfec = (TipoAfectacion) session.load(TipoAfectacion.class, id);
               return tipoAfec;
           } catch (HibernateException he) {
               throw new EJBException(he);
           } finally {
               close(session);
           }
       }

    /**
     * Nos dice si un tipo Afectacion tiene afectaciones.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean tieneAfectaciones(Long id){
        Session session = getSession();
        try {
            List result = session.find("select normativa from Normativa as normativa, afectacion in normativa.afectadas where afectacion.tipoAfectacion.id = ?", id, Hibernate.LONG);
            if(!result.isEmpty()){
               return true;
            } else {
               return false;
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
         * Borra un Tipo Afectacion.
         * @ejb.interface-method
         * @ejb.permission role-name="${role.system},${role.admin}"
         */
        public void borrarTipoAfectacion(Long id) {
            Session session = getSession();
            try {
                TipoAfectacion tipoAfec = (TipoAfectacion) session.load(TipoAfectacion.class, id);
                if(tieneAfectaciones(id)){
                     throw new EJBException("El tipo tiene afectaciones asociadas");
                }
                session.delete(tipoAfec);
                session.flush();
            } catch (HibernateException he) {
                throw new EJBException(he);
            } finally {
                close(session);
            }
        }

}
