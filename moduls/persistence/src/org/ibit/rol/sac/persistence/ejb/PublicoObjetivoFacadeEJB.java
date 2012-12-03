package org.ibit.rol.sac.persistence.ejb;

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
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.PublicoObjetivo;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Publico Objetivo.(PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/PublicoObjetivoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.PublicoObjetivoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class PublicoObjetivoFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un Publico Objetivo.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarPublicoObjetivo(PublicoObjetivo publico) {
        Session session = getSession();
        try {
            if (publico.getId() == null) {
                Criteria criteria = session.createCriteria(PublicoObjetivo.class);
                List result = criteria.list();
                if (result.isEmpty()) {
                    publico.setOrden(0);
                } else {
                    publico.setOrden(result.size());
                }
            }
            session.saveOrUpdate(publico);
            session.flush();
            return publico.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista todos los Publico Objetivo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ResultadoBusqueda listarPublicoObjetivo(int pagina, int resultats) {
    	return listarTablaMaestraPaginada(pagina,  resultats, listarTMPublicoObjetivo());
    }
    
    /**
     * Lista todos los Publico Objetivo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<PublicoObjetivo> listarPublicoObjetivo() {
        Session session = getSession();
        try {
        	
            Criteria criteri = session.createCriteria(PublicoObjetivo.class);
            criteri.addOrder(Order.asc("orden"));
            List<PublicoObjetivo> publicos = criteri.list();
            
            for(PublicoObjetivo publico: publicos){
            	
                Hibernate.initialize(publico.getAgrupaciones());
                Set<AgrupacionHechoVital> agrupaciones = publico.getAgrupaciones();
                
                for ( AgrupacionHechoVital agrupacion: agrupaciones){
                    Hibernate.initialize(agrupacion.getHechosVitalesAgrupacionHV());
                }
                
            }
            
            return publicos;
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Listar público objetivo (menú administración) 
     */
    private List listarTMPublicoObjetivo() {
    	Session session = getSession();
    	
    	try {
    		Query query = session.createQuery("select pubObj.id, pubObj.orden, pubObj.codigoEstandar  " +
    														"from PublicoObjetivo as pubObj " +
    														"order by pubObj.orden asc");
    		
    		return query.list();    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	

    }
    
    /**
     * Obtiene un Publico Objetivo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PublicoObjetivo obtenerPublicoObjetivo(Long id) {
        Session session = getSession();
        try {
            PublicoObjetivo publico = (PublicoObjetivo) session.load(PublicoObjetivo.class, id);
            return publico;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un Publico Objetivo segun el titulo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PublicoObjetivo obtenerPublicoObjetivoPorTitulo(String titulo) {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("publico.byname");
            query.setParameter("titulo", titulo);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            PublicoObjetivo publico = (PublicoObjetivo) result.get(0);
            return publico;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Borra un Publico Objetivo.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarPublicoObjetivo(Long id) {
        Session session = getSession();
        try {
            PublicoObjetivo publico = (PublicoObjetivo) session.load(PublicoObjetivo.class, id);

            Criteria criteria = session.createCriteria(PublicoObjetivo.class);
            criteria.add(Expression.gt("orden", new Integer(publico.getOrden())));
            List publicos = criteria.list();
            for (int i = 0; i < publicos.size(); i++) {
                PublicoObjetivo pub = (PublicoObjetivo) publicos.get(i);
                pub.setOrden(i);
            }

            session.delete(publico);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Incrementa el orden de un P�blico Objetivo.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    @SuppressWarnings("unchecked")
	public void subirOrden(Long id) {
        Session session = getSession();
        try {
            PublicoObjetivo publico1 = (PublicoObjetivo) session.load(PublicoObjetivo.class, id);
            Integer orden = publico1.getOrden();
            
            if (orden > 0) {
                Criteria criteri = session.createCriteria(PublicoObjetivo.class);
                criteri.addOrder(Order.asc("orden"));
                List<PublicoObjetivo> result = criteri.list();

                PublicoObjetivo publico2 = (PublicoObjetivo) result.get(orden - 1);

                publico2.setOrden(orden);
                result.set(orden, publico2);

                publico1.setOrden(orden - 1);
                result.set(orden - 1, publico1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
   
}
