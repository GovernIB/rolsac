package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Archivo;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

/**
 * SessionBean para mantener y consultar IconoMateria.
 *
 * @ejb.bean
 *  name="sac/persistence/IconoMateriaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IconoMateriaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IconoMateriaFacadeEJB extends HibernateEJB {

     /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un IconoMateria.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarIconoMateria(IconoMateria icono, Long materia_id, Long perfil_id) {
        Session session = getSession();
        try {
            if(icono.getId()==null){
                Materia materia = (Materia)session.load(Materia.class, materia_id);
                PerfilCiudadano perfil = (PerfilCiudadano)session.load(PerfilCiudadano.class, perfil_id);
                materia.addIcono(icono);
                perfil.addIconoMateria(icono);
            } else {
                session.update(icono);
            }
            session.flush();
            return icono.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
    * Obtiene un IconoMateria.
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
   public IconoMateria obtenerIconoMateria(Long id) {
       Session session = getSession();
       try {
           IconoMateria icono = (IconoMateria) session.load(IconoMateria.class, id);
           Hibernate.initialize(icono.getIcono());
           return icono;
       } catch (HibernateException he) {
           throw new EJBException(he);
       } finally {
           close(session);
       }
   }

    /**
    * Obtiene el icono de la materia.
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
   public Archivo obtenerIcono(Long id) {
       Session session = getSession();
       try {
            IconoMateria iconoMateria = (IconoMateria) session.load(IconoMateria.class, id);
            Hibernate.initialize(iconoMateria.getIcono());
            return iconoMateria.getIcono();
       } catch (HibernateException he) {
           throw new EJBException(he);
       } finally {
           close(session);
       }
   }

   /**
     * Borra un IconoMateria.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarIconoMateria(Long id) {
        Session session = getSession();
        try {
            IconoMateria icono = (IconoMateria) session.load(IconoMateria.class, id);
            icono.getMateria().removeIcono(icono);
            icono.getPerfil().removeIconoMateria(icono);
            session.delete(icono);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
