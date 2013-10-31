package org.ibit.rol.sac.persistence.ejb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean per mantenir i consultar Models Comuns
 *
 * @ejb.bean
 *  name="sac/persistence/ModelsComunsFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ModelsComunsFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class ModelsComunsFacadeEJB extends HibernateEJB {

	
	private static final long serialVersionUID = -2185416511499772305L;

	/**
     * Obtiene referència al ejb de control d'Accés.
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
     * Lista todos los modelos comunes de documentación.

     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param	pagina	Indica la última página visitada.
     * 
     * @param	resultados	Indica el número de resultado mostrados por página.
     * 
     * @param	idioma	Indica el idioma en que se realiza la búsqueda.
     * 
     * @return	Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todos los modelos comunes de documentación.
     */   
    public ResultadoBusqueda llistarModelsComuns(int pagina, int resultados, String idioma)  {
    	
    	return listarTablaMaestraPaginada( pagina, resultados, llistarTMModelsComuns(idioma) );
    	
    }

    
    private List llistarTMModelsComuns(String idioma)  {
    	
    	Session session = getSession();
    	
    	try {
    		
    		StringBuilder consulta = new StringBuilder("select docTra.id, trad.titulo, trad.descripcion, docTra.orden ");
    		consulta.append("from DocumentTramit as docTra, docTra.traducciones as trad ");
    		consulta.append("where index(trad) = :idioma ");
    		consulta.append("and docTra.tipus = :tipus ");
    		consulta.append("order by docTra.orden");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setParameter("idioma", idioma);
    		query.setParameter("tipus", Document.MODELCOMU);
    		
    		return query.list();
    	
    	}catch (HibernateException he) {
    		
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}   	
    	
    }
    
    
    /**
     * Obtener un modelo común de documentación.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param	id	Identificador de un modelo común.
     * 
     * @return	Devuelve <code>DocumentTramit</code> solicitado.
     */
    public DocumentTramit obtenirModelComu(Long id) {
    	
        Session session = getSession();
        try {
        	
            DocumentTramit modelComu = (DocumentTramit) session.load(DocumentTramit.class, id);
            Iterator iterator = modelComu.getLangs().iterator();
            while ( iterator.hasNext() ) {
            	
                String lang = (String) iterator.next();
                TraduccionDocumento tradModel = (TraduccionDocumento) modelComu.getTraduccion(lang);
                if ( tradModel != null )
                    Hibernate.initialize( tradModel.getArchivo() );
                
            }
            
            return modelComu;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     * Crea o actualiza un Modelo común.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param modeloComun Indica el modelo común a guardar.
     * 
     * @return Devuelve el identificador del modelo común guardado.
     */
    public Long gravarModelComu(DocumentTramit modeloComun) {
    	
    	Session session = getSession();
        try {
        	
            if ( modeloComun.getId() == null ) {
            	
                Criteria criteria = session.createCriteria(DocumentTramit.class);
                criteria.add( Expression.eq( "tipus", new Integer(Document.MODELCOMU) ) );
                List<DocumentTramit> result = castList( DocumentTramit.class, criteria.list() );
                
                modeloComun.setOrden( result.isEmpty() ? 0L : new Long( result.size() ) );
                
            }
            
            session.saveOrUpdate(modeloComun);
            session.flush();
            
            return modeloComun.getId();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
	 /**
     * Borra un modelo común.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param id	Identificador del modelo común.
     */
    public void esborrarModelComu(Long id) {
    	
        Session session = getSession();
        
        try {
        	
        	 DocumentTramit modelComu = (DocumentTramit) session.load(DocumentTramit.class, id);
	    	 Criteria criteria = session.createCriteria(DocumentTramit.class);
	    	 criteria.add( Expression.eq( "tipus", new Integer(Document.MODELCOMU) ) );
	         criteria.add( Expression.gt( "orden", modelComu.getOrden() ) );
	         List<DocumentTramit> llistaModelsComuns = castList(DocumentTramit.class, criteria.list() );
	         
	         for ( int i = 0 ; i < llistaModelsComuns.size() ; i++ ) {
	        	 
	        	 DocumentTramit model = (DocumentTramit) llistaModelsComuns.get(i);
	        	 model.setOrden( model.getOrden() - 1 );
	        	 
	         }
	
	         session.delete(modelComu);
	         session.flush();
	         
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     * Asigna un nuevo orden a un modelo común y reordena los elementos afectados.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param id	Identificador de del modelo común.
     * 
     * @param ordenNuevo	Indica el nuevo orden.
     * 
     * @param ordenAnterior	Indica el orden anterior.
     */
    public void reordenar( Long id, Long ordenNuevo, Long ordenAnterior ) {
    	
        Session session = getSession();
        
        try {
        	
        	Criteria criteria = session.createCriteria(DocumentTramit.class);
        	criteria.add( Expression.eq( "tipus", new Integer(Document.MODELCOMU) ) );
        	criteria.addOrder( Order.asc("orden") );
        	
        	List<DocumentTramit> llistaModelsComuns = castList( DocumentTramit.class, criteria.list() );
        	
        	// Modificar sólo los elementos entre la posición del elemento que cambia 
        	// de orden y su nueva posición 
        	Long ordenMayor = ( ordenNuevo > ordenAnterior ) ? ordenNuevo : ordenAnterior;
        	Long ordenMenor = ( ordenMayor == ordenNuevo ) ? ordenAnterior : ordenNuevo;
        	
        	// Si el nuevo orden es mayor que el anterior, desplazar los elementos 
        	// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
        	int incremento = ( ordenNuevo > ordenAnterior ) ? -1 : 1;        			
        	        	
        	for ( DocumentTramit model : llistaModelsComuns ) {        		    
        		
        		Long orden = model.getOrden();
        		
        		if ( orden >= ordenMenor && orden <= ordenMayor ) {
        			
        			if ( id.equals( model.getId() ) ) {
        				
        				model.setOrden( ordenNuevo );
        				
        			} else {
        				
        				model.setOrden( orden + incremento );
        				
        			}   
        			
        		}
        		
        		// Actualizar todo para asegurar que no hay duplicados ni huecos
        		session.saveOrUpdate(model);
        		
        	}
        	
        	session.flush();
        	
        } catch (HibernateException he) {
        	
        	throw new EJBException(he);
        	
        } finally {
        	
        	close(session);
        	
        }   
        
    }   
    
}
