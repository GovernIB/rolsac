package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

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
public abstract class FamiliaFacadeEJB extends HibernateEJB implements FamiliaDelegateI
{
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
	public void ejbCreate() throws CreateException
    {
    	super.ejbCreate();
    }
    
    
    /**
     * Crea o actualiza una familia.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param familia	Indica una instancia de <code>Familia</code> a guardar.
     * @return Devuelve el identificador de la familia guardada.
     */
    public Long grabarFamilia(Familia familia)
    {
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
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @param pagina	Indica la última página del listado consultada.
     * @param resultats	Indica el número de registros a mostrar por página.
     * @param idioma	Indica el idioma en que se realiza la búsqueda.
     * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado paginado con todas las familias.
     */
    public ResultadoBusqueda listarFamilias(int pagina, int resultats, String idioma)
    {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTMFamilias(idioma));
    }
    
    
    /**
     * Lista todas las familias.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @return Devuelve <code>List</code> de todas las familias.
     */
    public List listarFamilias()
    {
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
     *  Lista todas las familias (menú Administración).
     *  
     *  @param idioma Indica el idioma en que se realiza la búsqueda.
     *  @return Devuelve <code>List</code> de todas las familias.
     */
    private List listarTMFamilias(String idioma)
    {
    	Session session = getSession();
    	try {
    		Query query = session.createQuery(
    				"select familia.id, " +
    				"		trad.nombre, " +
    				"		trad.descripcion " +
    				"from Familia as familia, " +
    				"	  familia.traducciones as trad " +
    				"where index(trad) = :idioma " +
    				"order by trad.nombre asc");
    		
    		query.setParameter("idioma", idioma);
    		return query.list();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Obtiene una familia.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param id	Identificador de la familia.
     * @return Devuelve <code>Familia</code> solicitada.
     */
    public Familia obtenerFamilia(Long id)
    {
    	Session session = getSession();
    	try {
    		Familia familia = (Familia) session.load(Familia.class, id);
    		Iterator iterator = familia.getIconos().iterator();
    		while (iterator.hasNext()) {
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
     * Borra una familia.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * @param id	Identificado de la familia a borrar.
     */
    public void borrarFamilia(Long id)
    {
    	Session session = getSession();
    	try {
    		Familia familia = (Familia) session.load(Familia.class, id);
            Hibernate.initialize(familia.getProcedimientosLocales());
            Set procedimientos = familia.getProcedimientosLocales();
            Set iconos = familia.getIconos();
            Iterator iter = iconos.iterator(); 
            while (iter.hasNext()) {
            	IconoFamilia icono = (IconoFamilia) iter.next();
                PerfilCiudadano perfil = icono.getPerfil();
                perfil.removeIconoFamilia(icono);
            }
            
            if (procedimientos.size() != 0) {
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
    
    
    
    
    
    
    /**
	 * Consulta las familias en funcion del filtro generico
	 * 
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda consultaFamilias(FiltroGenerico filtro){
	
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		String lang = filtro.getLang();
		Long id = filtro.getId();
		Map <String,String> parametros = new HashMap<String,String>();
		
		StringBuilder select = new StringBuilder("SELECT f ");
		StringBuilder selectCount = new StringBuilder("SELECT count(f) ");
		StringBuilder from = new StringBuilder(" FROM Familia as f, f.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder(" ORDER BY trad.nombre ASC");			
				
		try {
				
			if(id!=null && id>0) {
				where.append(" AND f.id = :id");
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
