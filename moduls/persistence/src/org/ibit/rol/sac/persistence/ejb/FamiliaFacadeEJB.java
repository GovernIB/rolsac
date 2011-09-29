package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * SessionBean para mantener y consultar familia.
 *
 * @ejb.bean
 *  name="sac/persistence/FamiliaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.FamiliaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class FamiliaFacadeEJB extends HibernateEJB implements FamiliaDelegateI {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

     /**
     * Crea o actualiza una familia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarFamilia(Familia familia) {
        Session session = getSession();
        try {
            session.saveOrUpdate(familia);
            session.flush();
            return familia.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Lista todas las familias.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFamilias() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Familia.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene una familia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Familia obtenerFamilia(Long id) {
        Session session = getSession();
        try {
            Familia familia = (Familia) session.load(Familia.class, id);
            for (Iterator iterator = familia.getIconos().iterator(); iterator.hasNext();) {
                IconoFamilia icono = (IconoFamilia) iterator.next();
                Hibernate.initialize(icono.getIcono());
            }
            return familia;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Nos dice si una familia tiene procedimientos.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean tieneProcedimientos(Long id){
        Session session = getSession();
        try {
            Familia familia = (Familia) session.load(Familia.class, id);
            Set procedimientos = familia.getProcedimientosLocales();
            return procedimientos.size() != 0;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra una familia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarFamilia(Long id) {
        Session session = getSession();
        try {
            Familia familia = (Familia) session.load(Familia.class, id);
            Hibernate.initialize(familia.getProcedimientosLocales());
            Set procedimientos = familia.getProcedimientosLocales();
            //familia.getIconos().clear();
            Set iconos = familia.getIconos();
            for(Iterator iter = iconos.iterator(); iter.hasNext();){
                IconoFamilia icono = (IconoFamilia)iter.next();
                PerfilCiudadano perfil =icono.getPerfil();
                perfil.removeIconoFamilia(icono);
            }
            if(procedimientos.size()!=0){
                throw new EJBException("La familia tiene procedimientos asociados");
            }
            session.delete(familia);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
}
