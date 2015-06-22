package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.expression.Expression;

import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.List;
import java.util.Set;

/**
 * SessionBean para matener y consultar perfiles de usuario gestor.
 *
 * @ejb.bean
 *  name="sac/persistence/PerfilGestorFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.PerfilGestorFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class PerfilGestorFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un perfil de gestor.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarPerfilGestor(PerfilGestor perfilGestor) {
        Session session = getSession();
        try {
            session.saveOrUpdate(perfilGestor);
            session.flush();
            return perfilGestor.getId();
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
    public ResultadoBusqueda listarPerfilesGestor(int pagina, int resultats, String idioma) {
    	
    	return listarTablaMaestraPaginada( pagina, resultats, listarTMPerfilesGestor(idioma) );
    	
    }    
    /**
     * Lista todos los perfiles de gesti�
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarPerfilesGestor() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(PerfilGestor.class);
            criteri.setCacheable(true);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un perfil.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PerfilGestor obtenerPerfilGestor(Long id) {
        Session session = getSession();
        try {
            PerfilGestor perfilGestor = (PerfilGestor) session.load(PerfilGestor.class, id);
            Hibernate.initialize(perfilGestor.getSeccions());
            Hibernate.initialize(perfilGestor.getUsuaris());
            
            return perfilGestor;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un perfil de gesti�n.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PerfilGestor obtenerPerfilGestor(String codigo) {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(PerfilGestor.class);
            criteri.add(Expression.eq("codigoEstandard", codigo));
            criteri.setCacheable(true);
            List result = criteri.list();

            if (result.isEmpty()) {
                return null;
            }

            PerfilGestor perfilGestor = (PerfilGestor) result.get(0);
            Hibernate.initialize(perfilGestor.getSeccions());
            Hibernate.initialize(perfilGestor.getUsuaris());

            return perfilGestor;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un perfil de usuario gestor.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarPerfilGestor(Long id) {
        Session session = getSession();
        try {
        	PerfilGestor perfilGestor = (PerfilGestor) session.load(PerfilGestor.class, id);
           Hibernate.initialize(perfilGestor.getSeccions());
           Hibernate.initialize(perfilGestor.getUsuaris());
        	Set seccions= perfilGestor.getSeccions();
            if(seccions.size()!= 0){
                throw new EJBException("El Perfil Gestor conté registres associats amb seccions");
            }
        	Set usuaris = perfilGestor.getUsuaris();
            if(usuaris.size()!= 0){
                throw new EJBException("El Perfil Gestor conté registres associats amb usuaris");
            }
            session.delete(perfilGestor);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
	 * Lista todos los perfiles (menú administración) 
	 */
    private List listarTMPerfilesGestor(String idioma) {
    	
    	Session session = getSession();
    	
    	try {
    		
    		StringBuilder consulta = new StringBuilder(" select p.id, p.codigoEstandar, p.duplica, trad.nombre, trad.descripcion ");
    		consulta.append(" from PerfilGestor as p, p.traducciones as trad ");
    		consulta.append(" where index(trad) = :idioma ");
    		consulta.append(" order by p.codigoEstandar asc ");
    		
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
	 * Elimina una seccion de associada a un Perfil Gestor
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public void eliminarSeccionPerfilGestor(Long idSeccio, Long idPerfilGestor)
	{
		Session session = getSession();
		try {
			Seccion seccio = (Seccion) session.load(Seccion.class, idSeccio);
			PerfilGestor perfil = (PerfilGestor) session.load(PerfilGestor.class, idPerfilGestor);
			perfil.getSeccions().remove(seccio);
			seccio.getPerfilsGestor().remove(perfil);
			session.flush();
			Actualizador.borrar(seccio, perfil.getId());
			
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Añade una seccion associada a un Perfil Gestor
	 * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */	public void anyadirSeccionPerfilGestor(Long idSeccio, Long idPerfilGestor)
	{
		Session session = getSession();
		try {
			Seccion seccio = (Seccion) session.load(Seccion.class, idSeccio);
			PerfilGestor perfil = (PerfilGestor) session.load(PerfilGestor.class, idPerfilGestor);
			perfil.getSeccions().add(seccio);
			seccio.getPerfilsGestor().add(perfil);
			session.flush();
			Actualizador.actualizar(seccio, perfil.getId());
			
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

}
