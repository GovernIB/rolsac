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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ResultadoBusqueda listarPerfiles(int pagina, int resultats, String idioma) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTMPerfiles(idioma));
    }    
    
    /**
     * Lista todos los perfiles.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
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
    		Query query = session.createQuery("select p.id, p.codigoEstandard, p.pathIconografia, trad.nombre, trad.descripcion  " +
    														"from PerfilCiudadano as p, p.traducciones as trad " +
    														"where index(trad) = :idioma " +
    														"order by p.codigoEstandard asc");
    		
    		query.setParameter("idioma", idioma);
    		return query.list();    		
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PerfilCiudadano obtenerPerfil(String codigo) {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(PerfilCiudadano.class);
            criteri.add(Expression.eq("codigoEstandard", codigo));
            criteri.setCacheable(true);
            List result = criteri.list();

            if (result.isEmpty()) {
                return null;
            }

            PerfilCiudadano perfil = (PerfilCiudadano) result.get(0);
            Hibernate.initialize(perfil.getIconosFamilia());
            Hibernate.initialize(perfil.getIconosMateria());

            return perfil;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra un perfil de usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarPerfil(Long id) {
        Session session = getSession();
        try {
            PerfilCiudadano perfil = (PerfilCiudadano) session.load(PerfilCiudadano.class, id);
            Set iconosMateria = perfil.getIconosMateria();
            for( Iterator iter = iconosMateria.iterator(); iter.hasNext();){
                IconoMateria iconoMat = (IconoMateria)iter.next();
                Materia materia = iconoMat.getMateria();
                materia.removeIcono(iconoMat);
            }
            Set iconosFamilia = perfil.getIconosFamilia();
            for( Iterator iter = iconosFamilia.iterator(); iter.hasNext();){
                IconoFamilia iconoFam = (IconoFamilia)iter.next();
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
