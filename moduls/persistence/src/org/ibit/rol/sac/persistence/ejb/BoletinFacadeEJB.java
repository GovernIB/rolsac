package org.ibit.rol.sac.persistence.ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Boletin;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Boletin.
 *
 * @ejb.bean
 *  name="sac/persistence/BoletinFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.BoletinFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class BoletinFacadeEJB extends HibernateEJB
{
	private static final long serialVersionUID = 364556289440568520L;
	
	private static final String excepcionBorradoBoletin = "El Boletin tiene normativas asociadas";
	
	
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException
    {
    	super.ejbCreate();
    }
    
    
    /**
     * Crea o actualiza un Boletin.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param boletin	Boletín a guardar.
     * @return	Devuelve el iodentificador del boletín.
     */
    public Long grabarBoletin(Boletin boletin)
    {
    	Session session = getSession();
    	try {
    		session.saveOrUpdate(boletin);
    		session.flush();
    		return boletin.getId();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Crea o actualiza un Boletin.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param boletin	Boletín a guardar.
     * @return	Devuelve el iodentificador del boletín.
     */
    public Long listarAuditoriasFichaPMA(Boletin boletin)
    {
    	Session session = getSession();
    	try {
    		session.saveOrUpdate(boletin);
    		session.flush();
    		
    		return boletin.getId();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Lista todos los boletines.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param pagina Número de página actual.
     * @param resultats	Número de resultados por página.
     * @return Devuelve <code>ResultadoBusqueda</code> que contiene una lista de todos los boletines.
     */
    public ResultadoBusqueda listarBoletines(int pagina, int resultats)
    {
    	return listarTablaMaestraPaginada(pagina, resultats, this.listarTablaMaestraBoletines());
    }
    
    
    /**
     * Lista todos los boletines.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @return Devuelve <code>List</code> de todos los boletines.
     */
    public  List<Boletin> listarBoletines()
    {
    	Session session = getSession();
    	try {
    		Criteria criteri = session.createCriteria(Boletin.class);
    		return criteri.list();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Lista todos los boletines (menú Administración)
     * 
     * @return Devuelve <code>List</code> de todos los boletines.
     */
    private List listarTablaMaestraBoletines()
    {
    	Session session = getSession();
    	try {
    		Query query = session.createQuery(
    				"select boletin.id, " +
    				"		boletin.nombre, " +
    				"		boletin.enlace " +
    				"from Boletin as boletin " +
    				"order by boletin.nombre asc");
    		
    		return query.list();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Obtiene un boletin.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param id	Identificador de un boletín.
     * @return Devuelve <code>Boletin</code> solicitado.
     */
    public Boletin obtenerBoletin(Long id)
    {
    	Session session = getSession();
    	try {
    		return (Boletin) session.load(Boletin.class, id);
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }

    
    /**
     * Borra un Boletin.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param id	Identificador de un boletín.
     */
    public void borrarBoletin(Long id)
    {
    	Session session = getSession();
    	try {
    		Boletin boletin = (Boletin) session.load(Boletin.class, id);
    		Set normativas = boletin.getNormativas();
    		if (!normativas.isEmpty()) {
    			throw new EJBException(excepcionBorradoBoletin);
    		}
    		session.delete(boletin);
    		session.flush();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
}
