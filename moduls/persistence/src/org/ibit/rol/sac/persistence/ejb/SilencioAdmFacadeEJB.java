package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.model.TraduccionSilencio;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar silencio.
 *
 * @ejb.bean
 *  name="sac/persistence/SilencioAdmFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SilencioAdmFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SilencioAdmFacadeEJB extends HibernateEJB implements SilencioAdmDelegateI
{
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException
    {
    	super.ejbCreate();
    }
    
    
    /**
     * Crea o actualiza una silencio.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param silencio	Indica una instancia de <code>SilencioAdm</code> a guardar.
     * @return Devuelve el identificador del silencio guardado.
     * @throws DelegateException 
     */
    public String grabarSilencioAdm(SilencioAdm silencio, boolean editar) throws DelegateException
    {
    	Session session = getSession();
    	try {
    		//TODO revisar porque no funciona al crear el saveorupdate
    		if(editar){
    			session.saveOrUpdate(silencio);
    		}else{    			
    			session.save(silencio);
    		}
 
    		session.flush();
    		return silencio.getId();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Lista todos los silencios.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @param pagina	Indica la última página del listado consultada.
     * @param resultats	Indica el número de registros a mostrar por página.
     * @param idioma	Indica el idioma en que se realiza la búsqueda.
     * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado paginado con todos los silencios.
     */
    public ResultadoBusqueda listarSilencioAdm(int pagina, int resultats, String idioma)
    {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTMSilencioAdm(idioma));
    }
    
    
    /**
     * Lista todos los silencios.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @return Devuelve <code>List</code> de todas los silencios.
     */
    public List listarSilencioAdm()
    {
    	Session session = getSession();
    	try {
    		Criteria criteri = session.createCriteria(SilencioAdm.class);
    		return criteri.list();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     *  Lista todos los silencios (menú Administración).
     *  
     *  @param idioma Indica el idioma en que se realiza la búsqueda.
     *  @return Devuelve <code>List</code> de todos los silencios.
     */
    private List listarTMSilencioAdm(String idioma)
    {
    	Session session = getSession();
    	try {
    		    		
    		StringBuilder consulta = new StringBuilder("select sil.id, trad.nombre, trad.descripcion ");
			consulta.append("from SilencioAdm as sil, sil.traducciones as trad ");
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
     * Obtiene un silencio.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param codigo	Identificador del silencio.
     * @return Devuelve <code>SilencioAdm</code> solicitada.
     */
    public SilencioAdm obtenerSilencioAdm(String codigo)
    {
    	Session session = getSession();
    	try {
    		SilencioAdm silencio = (SilencioAdm) session.load(SilencioAdm.class, codigo);
    		return silencio;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Borra un silencio.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param codigo	Identificador del silencio a borrar.
     */
    public void borrarSilencioAdm(String codigo)
    {
    	Session session = getSession();
    	try {
    		SilencioAdm silencio = (SilencioAdm) session.load(SilencioAdm.class, codigo);
           
           //TODO controlar asociacion 
//            if (procedimientos.size() != 0) {
//            	throw new EJBException("El silencio tiene procedimientos asociados");
//            }
            session.delete(silencio);
            session.flush();
            
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }
    }
    
}
