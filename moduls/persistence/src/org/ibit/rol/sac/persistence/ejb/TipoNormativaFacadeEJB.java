package org.ibit.rol.sac.persistence.ejb;

import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Tipo;

import es.caib.rolsac.utils.ResultadoBusqueda;

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
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un tipo Normativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
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
    * @ejb.interface-method
    * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
    */
    public ResultadoBusqueda listarTiposNormativas(int pagina, int resultats, String idioma) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTMTiposNormativas(idioma));
    }
    
     /**
     * Lista todos los tipos normativas.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarTiposNormativas() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Tipo.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todos los tipos de normativas (menú Administración) 
     */
    private List listarTMTiposNormativas(String idioma) {
    	Session session = getSession();
    	//session.createQuery("select tipo.id, trad.nombre from Tipo as tipo, tipo.traducciones as trad").list()
    	try {
    		Query query = session.createQuery("select tipo.id, trad.nombre " +
    														"from Tipo as tipo, tipo.traducciones as trad " +
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
     * Obtiene un tipo normativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
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
     * Nos dice si un tipo Normativa tiene normativas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean tieneNormativas(Long id){
        Session session = getSession();
        try {
            Tipo tiponorm = (Tipo) session.load(Tipo.class, id);
            Set normativas = tiponorm.getNormativas();
            if(!normativas.isEmpty()){
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
     * Borra un Tipo Normativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarTipoNormativa(Long id) {
        Session session = getSession();
        try {
            Tipo tiponorm = (Tipo) session.load(Tipo.class, id);
            Set normativas = tiponorm.getNormativas();
            if(!normativas.isEmpty()){
                throw new EJBException("El tipo tiene normativas asociadas");
            }
            session.delete(tiponorm);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
