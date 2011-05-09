package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

/**
 * SessionBean para mantener y consultar Formularios.
 *
 * @ejb.bean
 *  name="sac/persistence/FormularioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.FormularioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
public abstract class FormularioFacadeEJB extends HibernateEJB {

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
     * Crea o actualiza un Formulario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarFormulario(Formulario formulario) {
        Session session = getSession();
        try {
            if (formulario.getId() != null) {
                if (!getAccesoManager().tieneAccesoFormulario(formulario.getId())) {
                    throw new SecurityException("No tiene acceso al formulario");
                }
            }
            session.saveOrUpdate(formulario);
            session.flush();
            return formulario.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un formulario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Formulario obtenerFormulario(Long id) {
        Session session = getSession();
        try {
            Formulario formulario = (Formulario) session.load(Formulario.class, id);
            Hibernate.initialize(formulario.getArchivo());
            Hibernate.initialize(formulario.getManual());
            Hibernate.initialize(formulario.getTramite());
            Tramite tramite=formulario.getTramite();
            Hibernate.initialize(tramite.getFormularios()); 
            Hibernate.initialize(tramite.getDocsInformatius());
            //Hibernate.initialize(tramite.getDocsPresentar());
            //Hibernate.initialize(tramite.getRequisits());
            return formulario;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene el archivo de un formulario.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerArchivoFormulario(Long id) {
        Session session = getSession();
        try {
            Formulario formulario = (Formulario) session.load(Formulario.class, id);
            Hibernate.initialize(formulario.getArchivo());
            return formulario.getArchivo();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene el manual de un formulario.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerManualFormulario(Long id) {
        Session session = getSession();
        try {
            Formulario formulario = (Formulario) session.load(Formulario.class, id);
            Hibernate.initialize(formulario.getManual());
            return formulario.getManual();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borrar un formulario
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarFormulario(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoFormulario(id)) {
                throw new SecurityException("No tiene acceso al formulario");
            }
            Formulario formulario = (Formulario) session.load(Formulario.class, id);
            session.delete(formulario);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
