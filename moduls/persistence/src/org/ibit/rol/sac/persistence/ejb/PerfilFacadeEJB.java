package org.ibit.rol.sac.persistence.ejb;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para matener y consultar perfiles de usuario.
 *
 * @ejb.bean
 *  name="sac/persistence/PerfilFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.PerfilFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class PerfilFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    /**
     * Crea o actualiza un perfil de usuario.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	perfil	Indica el perfil a guardar.
     * 
     * @return	Devuelve el identificador del perfil guardado.
     */
    public Long grabarPerfil(PerfilCiudadano perfil) {
    	
        Session session = getSession();
        try {
        	
            session.saveOrUpdate(perfil);
            session.flush();
            
            return perfil.getId();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     * Lista todos los perfiles (nuevo backoffice).
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param	pagina	Indica el número de la última página visitada.
     * 
     * @param	resultats	Indica el número de resultados de cada página
     * 
     * @param idioma	Indica el idioma en que se realiza la búsqueda.
     * 
     * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todos los perfiles.
     */
    public ResultadoBusqueda listarPerfiles(int pagina, int resultats, String idioma) {
    	
    	return listarTablaMaestraPaginada( pagina, resultats, listarTMPerfiles(idioma) );
    	
    }    
    
    
    /**
     * Lista todos los perfiles.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @return	Devuelve un listado de todos los perfiles.
     */
    public List listarPerfiles() {
    	
        Session session = getSession();
        
        try {
        	
            Criteria criteri = session.createCriteria(PerfilCiudadano.class);
            criteri.setCacheable(true);
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
	/**
	 * Lista todos los perfiles (menú administración) 
	 */
    private List listarTMPerfiles(String idioma) {
    	
    	Session session = getSession();
    	
    	try {
    		
    		StringBuilder consulta = new StringBuilder(" select p.id, p.codigoEstandard, p.pathIconografia, trad.nombre, trad.descripcion ");
    		consulta.append(" from PerfilCiudadano as p, p.traducciones as trad ");
    		consulta.append(" where index(trad) = :idioma ");
    		consulta.append(" order by p.codigoEstandard asc ");
    		
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
	/** @deprecated No se usa 
     * Obtiene un perfil.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PerfilCiudadano obtenerPerfil(Long id) {
        Session session = getSession();
        try {
            PerfilCiudadano perfil = (PerfilCiudadano) session.load(PerfilCiudadano.class, id);
            Hibernate.initialize(perfil.getIconosFamilia());
            Hibernate.initialize(perfil.getIconosMateria());
            for (Iterator iter = perfil.getIconosMateria().iterator(); iter.hasNext();) {
				((IconoMateria) iter.next()).getIcono().getNombre();
			}
            
            for (Iterator iter = perfil.getIconosFamilia().iterator(); iter.hasNext();) {
				((IconoFamilia) iter.next()).getIcono().getNombre();
			}
            
            return perfil;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Obtiene un perfil.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param	codigo	Indica el código estándar del perfil.
     * 
     * @return Devuelve <code>PerfilCiudadano</code> solicitado.
     */
    public PerfilCiudadano obtenerPerfil(String codigo) {
    	
        Session session = getSession();
        try {
        	
            Criteria criteri = session.createCriteria(PerfilCiudadano.class);
            criteri.add( Expression.eq("codigoEstandard", codigo) );
            criteri.setCacheable(true);
            List result = criteri.list();

            if ( result.isEmpty() )
                return null;

            PerfilCiudadano perfil = (PerfilCiudadano) result.get(0);
            Hibernate.initialize( perfil.getIconosFamilia() );
            Hibernate.initialize( perfil.getIconosMateria() );

            return perfil;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     * Borra un perfil de usuario.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	id	Identificador de un perfil.
     */
    public void borrarPerfil(Long id) {
    	
        Session session = getSession();
        try {
        	
            PerfilCiudadano perfil = (PerfilCiudadano) session.load(PerfilCiudadano.class, id);
            Set iconosMateria = perfil.getIconosMateria();
            Iterator iteradorMateria = iconosMateria.iterator(); 
            while ( iteradorMateria.hasNext() ) {
            	
                IconoMateria iconoMat = (IconoMateria) iteradorMateria.next();
                Materia materia = iconoMat.getMateria();
                materia.removeIcono(iconoMat);
                
            }
            
            Set iconosFamilia = perfil.getIconosFamilia();
            Iterator iteradorFamilia = iconosFamilia.iterator();
            while ( iteradorFamilia.hasNext() ) {
            	
                IconoFamilia iconoFam = (IconoFamilia) iteradorFamilia.next();
                Familia familia = iconoFam.getFamilia();
                familia.removeIcono(iconoFam);
                
            }
            
            session.delete(perfil);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

}
