package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar Tipo Normativa.
 *
 * @ejb.bean
 *  name="sac/persistence/TipoNormativaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TipoNormativaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TipoNormativaFacadeEJB extends HibernateEJB {
	
   /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
	public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    /**
     * Crea o actualiza un tipo Normativa.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	tipo	Indica el tipo de normativa
     * 
     * @return	Devuelve el identificador del tipo de normativa a guardar.
     */
    public Long grabarTipoNormativa(Tipo tipo) {
    	
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
    * Lista todos los tipos normativas (nuevo backoffice).
    * 
    * @ejb.interface-method
    * 
    * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
    * 
    * @param	pagina	Indica la última página visitada.
    * 
    * @param	resultats	Indica el número de resultados por página.
    * 
    * @param	idioma	Indica el idioma en que se realiza la búsqueda.
    */
    public ResultadoBusqueda listarTiposNormativas(int pagina, int resultats, String idioma) {
    	
    	return listarTablaMaestraPaginada( pagina, resultats, listarTMTiposNormativas(idioma) );
    	
    }
    
    
     /**
     * Lista todos los tipos normativas.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @return	Devuelve <code>List</code> de todos los tipos de normativas.
     */
    public List<Tipo> listarTiposNormativas() {
    	
        final Session session = getSession();
        try {
        	
        	final Criteria criteri = session.createCriteria(Tipo.class);
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     * Lista todos los tipos de normativas (menú Administración) 
     * 
     * @param	idioma	Indica el idioma en que se realiza la búsqueda.
     */
    private List listarTMTiposNormativas(String idioma) {
    	
    	Session session = getSession();
    	try {
    		
    		StringBuilder consulta = new StringBuilder("select tipo.id, trad.nombre ");
    		consulta.append("from Tipo as tipo, tipo.traducciones as trad ");
    		consulta.append("where index(trad) = :idioma ");
    		consulta.append("order by trad.nombre asc");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setParameter("idioma", idioma);
    		
    		return query.list(); 
    		
    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}    	    	
    	
    }
    
    
    /**
     * Obtiene un tipo normativa.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param	id	Identificador del tipo de normativa.
     * 
     * @return	Devuelve <code>Tipo</code> solicitado.
     */
    public Tipo obtenerTipoNormativa(Long id) {
    	
        Session session = getSession();
        try {
        	
            Tipo tipoNorm = (Tipo) session.load(Tipo.class, id);
            
            return tipoNorm;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     * Obtiene un tipo normativa segun el id de boib.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param	idBoib	Identificador del tipo de normativa en BOIB.
     * 
     * @return	Devuelve <code>Tipo</code> solicitado.
     */
    public Tipo obtenerTipoNormativaByBOIB(String idBoib) {
    	
    	Session session = getSession();
    	try {
    		
    		StringBuilder consulta = new StringBuilder("select tipo ");
    		consulta.append("from Tipo as tipo ");
    		consulta.append("where tipo.idBoib = " + idBoib); 
    		
    		Query query = session.createQuery( consulta.toString() );
    		return (Tipo) query.uniqueResult(); 
    		
    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}    	    	
    	
    }
    
    
    /**
     * Borra un Tipo Normativa.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	id	Identificador del tipo de normativa a borrar.
     */
    public void borrarTipoNormativa(Long id) {
    	
        Session session = getSession();
        try {
        	
            Tipo tiponorm = (Tipo) session.load(Tipo.class, id);
            Set normativas = tiponorm.getNormativas();
            
            if ( !normativas.isEmpty() )
                throw new EJBException("El tipo tiene normativas asociadas");

            session.delete(tiponorm);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
   
	
	/**
	 *  Metodo para consultar los Tipos de normativas. 
	 * @param filtro generico
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
	 * @return
	 */
    public ResultadoBusqueda consultaTipo(FiltroGenerico filtro)  {
		
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		String lang = filtro.getLang();
		Long id = filtro.getId();
		Map <String,String> parametros = new HashMap<String,String>();
		
		
		StringBuilder select = new StringBuilder("SELECT t ");
		StringBuilder selectCount = new StringBuilder("SELECT count(t) ");
		StringBuilder from = new StringBuilder(" FROM Tipo as t, t.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");			
				
		try {
				
			if(id!=null && id>0) {
				where.append(" AND t.id = :id");
				parametros.put("id", id.toString());					
			}
				 
			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
	
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
    

}
