package org.ibit.rol.sac.persistence.ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.ExcepcioDocumentacio;

import es.caib.rolsac.utils.ResultadoBusqueda;


/**
 * SessionBean per mantenir i consultar Excepcions de Doumentaci�.
 *
 * @ejb.bean
 *  name="sac/persistence/ExcepcioDocumentacioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ExcepcioDocumentacioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class ExcepcioDocumentacioFacadeEJB extends HibernateEJB {
	
	private static final long serialVersionUID = -4181888881071845868L;
	
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza una excepci� de documentaci�
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long gravarExcepcioDocumentacio(ExcepcioDocumentacio excepcio) {
        Session session = getSession();
        try {
            session.saveOrUpdate(excepcio);
            session.flush();
            return excepcio.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     *  Llista totes les excepcions de documentaci� paginadament
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
     public ResultadoBusqueda llistarExcepcioDocumentacio(int pagina, int resultats) {
       return listarTablaMaestraPaginada(pagina, resultats, llistarExcepcioDocumentacio());
     }
     
     /**
     * Llista totes les excepcions de documentaci�.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List<ExcepcioDocumentacio> llistarExcepcioDocumentacio() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(ExcepcioDocumentacio.class);
            return castList(ExcepcioDocumentacio.class, criteri.list());
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtienir una excepcio de documentacio.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public ExcepcioDocumentacio obtenirExcepcioDocumentacio(Long id) {
        Session session = getSession();
        try {
            ExcepcioDocumentacio excepcio = (ExcepcioDocumentacio) session.load(ExcepcioDocumentacio.class, id);
            return excepcio;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Indica si l'excepci� est� relacionada amb algun document del cat�leg documental
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean teRelacioCatalegDocuments(Long id){
        Session session = getSession();
        try {
            ExcepcioDocumentacio excepcio = (ExcepcioDocumentacio) session.load(ExcepcioDocumentacio.class, id);
            Set catalegDocuments = excepcio.getCatalegDocuments();
            if(!catalegDocuments.isEmpty()){
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
     * Indica si l'excepci� est� relacionada amb algun document especific requerit en el tramit
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean teRelacioDocumentTramit(Long id){
        Session session = getSession();
        try {
            ExcepcioDocumentacio excepcio = (ExcepcioDocumentacio) session.load(ExcepcioDocumentacio.class, id);
            Set documentTramit = excepcio.getDocsRequerits();
            if(!documentTramit.isEmpty()){
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
     * Esborra una excepcion de documentaci�
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void esborrarExcepcioDocumentacio(Long id) {
        Session session = getSession();
        try {
            ExcepcioDocumentacio excepcio = (ExcepcioDocumentacio) session.load(ExcepcioDocumentacio.class, id);
            Set catalegDocuments = excepcio.getCatalegDocuments();
            if(!catalegDocuments.isEmpty()){
                throw new EJBException("L'excepcio de documentaci� t� relacions definides amb el cataleg de documents");
            }
            Set documentTramit = excepcio.getDocsRequerits();
            if(!documentTramit.isEmpty()){
              throw new EJBException("L'excepcio de documentaci� t� relacions definides amb algun document espec�fic requerit en el tr�mit");
            }
            session.delete(excepcio);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
