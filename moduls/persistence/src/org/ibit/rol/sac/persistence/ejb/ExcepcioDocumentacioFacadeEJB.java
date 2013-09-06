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
 * SessionBean per mantenir i consultar Excepcions de Doumentació.
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
     * Crea o actualiza una excepción de documentación.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	excepcio	Indica la excepción a guardar.
     * 
     * @return Devuelve el identificador de la excepción guardada.
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
     * Lista todas las excepciones de documentación paginadamente.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param pagina	Indica la última página consultada del listado.
     * 
     * @param resultats	Indica el número de resultados por página.
     * 
     * @return	Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todas las excepciones  de documentación.
     */
     public ResultadoBusqueda llistarExcepcioDocumentacio(int pagina, int resultats) {
    	 
       return listarTablaMaestraPaginada( pagina, resultats, llistarExcepcioDocumentacio() );
       
     }
     
     
     /**
      * Lista todas las excepciones de documentación.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     *
     * @return Devuelve <code>List<ExcepcioDocumentacio></code> de todas las excepciones  de documentación.
     */
    public List<ExcepcioDocumentacio> llistarExcepcioDocumentacio() {
    	
        Session session = getSession();
        
        try {
        	
            Criteria criteri = session.createCriteria(ExcepcioDocumentacio.class);
            
            return castList( ExcepcioDocumentacio.class, criteri.list() );
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     * Obtener una excepción de documentación
     * .
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param id	Identificador de la excepción de documentación.
     * 
     * @return Devuelve <code>ExcepcioDocumentacio</code> solicitada.
     */
    public ExcepcioDocumentacio obtenirExcepcioDocumentacio(Long id) {
    	
        Session session = getSession();
        
        try {
        	
            ExcepcioDocumentacio excepcio = (ExcepcioDocumentacio) session.load( ExcepcioDocumentacio.class, id );
            
            return excepcio;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     * @deprecated No se usa
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
     * @deprecated No se usa
     * Indica si la excepción está relacionada con algún documento específico requerido en el trámite.
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
     * Borra una excepción de documentación.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param id	Identificador de la excepción de documentación a borrar.
     */
    public void esborrarExcepcioDocumentacio(Long id) {

        Session session = getSession();
        
        try {
        	
            ExcepcioDocumentacio excepcio = (ExcepcioDocumentacio) session.load( ExcepcioDocumentacio.class, id );
            Set catalegDocuments = excepcio.getCatalegDocuments();
            
            if( !catalegDocuments.isEmpty() )
                throw new EJBException("L'excepcio de documentació té relacions definides amb el cataleg de documents");
            
            
            Set documentTramit = excepcio.getDocsRequerits();
            
            if( !documentTramit.isEmpty() )
              throw new EJBException("L'excepcio de documentació té relacions definides amb algun document específic requerit en el tràmit");
            
            session.delete(excepcio);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

}
