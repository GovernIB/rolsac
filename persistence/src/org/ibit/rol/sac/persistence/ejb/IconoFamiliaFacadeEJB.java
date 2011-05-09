package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.expression.Expression;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Archivo;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;

/**
 * SessionBean para mantener y consultar IconoFamilia.
 *
 * @ejb.bean
 *  name="sac/persistence/IconoFamiliaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IconoFamiliaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IconoFamiliaFacadeEJB extends HibernateEJB {

     /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un Iconofamilia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarIconoFamilia(IconoFamilia icono, Long familia_id, Long perfil_id) {
        Session session = getSession();
        try {
            if(icono.getId()==null){
                Familia familia = (Familia)session.load(Familia.class, familia_id);
                PerfilCiudadano perfil = (PerfilCiudadano)session.load(PerfilCiudadano.class, perfil_id);
                familia.addIcono(icono);
                perfil.addIconoFamilia(icono);
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
    * Obtiene un iconofamilia.
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
   public IconoFamilia obtenerIconoFamilia(Long id) {
       Session session = getSession();
       try {
           IconoFamilia iconoFamilia = (IconoFamilia) session.load(IconoFamilia.class, id);
           Hibernate.initialize(iconoFamilia.getIcono());
           return iconoFamilia;
       } catch (HibernateException he) {
           throw new EJBException(he);
       } finally {
           close(session);
       }
   }

   /**
    * Obtiene un iconofamilia.
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
   public IconoFamilia obtenerIconoFamilia(Long id_perfil,Long id_fam) {
       Session session = getSession();
       try {
           Criteria criterio = session.createCriteria(IconoFamilia.class);
           criterio.add(Expression.eq("familia.id",id_fam));
           criterio.add(Expression.eq("perfil.id",id_perfil));
           List list = criterio.list();
           if (list.isEmpty()) {
               return null;
           }
           IconoFamilia iconoFamilia = (IconoFamilia) list.get(0);
           Hibernate.initialize(iconoFamilia.getIcono());
           return iconoFamilia;
       } catch (HibernateException he) {
           throw new EJBException(he);
       } finally {
           close(session);
       }
   }

    /**
    * Obtiene el icono de la familia.
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
   public Archivo obtenerIcono(Long id) {
       Session session = getSession();
       try {
            IconoFamilia iconoFamilia = (IconoFamilia) session.load(IconoFamilia.class, id);
            Hibernate.initialize(iconoFamilia.getIcono());
            return iconoFamilia.getIcono();
       } catch (HibernateException he) {
           throw new EJBException(he);
       } finally {
           close(session);
       }
   }



   /**
     * Borra un IconoFamilia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarIconoFamilia(Long id) {
        Session session = getSession();
        try {
            IconoFamilia icono = (IconoFamilia) session.load(IconoFamilia.class, id);
            icono.getFamilia().removeIcono(icono);
            icono.getPerfil().removeIconoFamilia(icono);
            session.delete(icono);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
