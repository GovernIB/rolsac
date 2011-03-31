package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;

/**
 * SessionBean para mantener y consultar UnidadMateria. (PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/UnidadMateriaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.UnidadMateriaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UnidadMateriaFacadeEJB extends HibernateEJB{

    /**
         * @ejb.create-method
         * @ejb.permission unchecked="true"
         */
        public void ejbCreate() throws CreateException {
            super.ejbCreate();
        }

        /**
         * Crea o actualiza una UnidadMateria
         * @ejb.interface-method
         * @ejb.permission role-name="${role.system},${role.admin}"
         */
        public Long grabarUnidadMateria(UnidadMateria unidadMateria, Long unidad_id, Long materia_id) {
            Session session = getSession();
            try {
            	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, unidad_id);
                if(unidadMateria.getId()==null){
                    Materia materia = (Materia)session.load(Materia.class, materia_id);
                    unidad.addUnidadMateria(unidadMateria);
                    materia.addUnidadMateria(unidadMateria);
                } else {
                    session.update(unidadMateria);
                }

                session.flush();
                Actualizador.actualizar(unidad);
                return unidadMateria.getId();
            } catch (HibernateException he) {
                throw new EJBException(he);
            } finally {
                close(session);
            }
        }

        /**
        * Obtiene una UnidadMateria.
        * @ejb.interface-method
        * @ejb.permission unchecked="true"
        */
       public UnidadMateria obtenerUnidadMateria(Long id) {
           Session session = getSession();
           try {
               return (UnidadMateria) session.load(UnidadMateria.class, id);
           } catch (HibernateException he) {
               throw new EJBException(he);
           } finally {
               close(session);
           }
       }



       /**
         * Borra una UnidadMateria.
         * @ejb.interface-method
         * @ejb.permission role-name="${role.system},${role.admin}"
         */
        public void borrarUnidadMateria(Long id) {
            Session session = getSession();
            try {
            	
                UnidadMateria unidadMateria = (UnidadMateria) session.load(UnidadMateria.class, id);
                
                final UnidadAdministrativa unidadA = unidadMateria.getUnidad();
                
                unidadMateria.getMateria().removeUnidadMateria(unidadMateria);
                unidadMateria.getUnidad().removeUnidadMateria(unidadMateria);
                session.delete(unidadMateria);
                session.flush();
                
                Actualizador.actualizar(unidadA);
            } catch (HibernateException he) {
                throw new EJBException(he);
            } finally {
                close(session);
            }
        }

}
