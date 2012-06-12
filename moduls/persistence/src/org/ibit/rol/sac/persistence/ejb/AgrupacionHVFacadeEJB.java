package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Order;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.ejb.HibernateEJB;
import org.ibit.rol.sac.model.AgrupacionHechoVital;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;
import java.util.Collections;

/**
 * SessionBean para mantener y consultar Agrupaciones Hechos Vitales.(PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/AgrupacionHVFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.AgrupacionHVFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class AgrupacionHVFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza una Agrupacion Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarAgrupacionHV(AgrupacionHechoVital hechov) {
        Session session = getSession();
        try {
            session.saveOrUpdate(hechov);
            session.flush();
            return hechov.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Crea o actualiza una Agrupacion Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long guardarAgrupacionHV(AgrupacionHechoVital hechov, List<HechoVitalAgrupacionHV> llistaFetsVitalsOld) {
        Session session = getSession();
        try {
        	
            for (HechoVitalAgrupacionHV hechoVitalAgrupacionHV : llistaFetsVitalsOld) {
                if (hechoVitalAgrupacionHV != null){
                    session.delete(hechoVitalAgrupacionHV);   
                }                    
            }                   
        	
        	List<HechoVitalAgrupacionHV> listaHechoVitalAgrupacionHV = hechov.getHechosVitalesAgrupacionHV();
        	
        	// Seteamos el nuevo list
        	for (HechoVitalAgrupacionHV hechoVitalAgrupacionHV : listaHechoVitalAgrupacionHV) {
				session.saveOrUpdate(hechoVitalAgrupacionHV);
			}
        	
            session.saveOrUpdate(hechov);
            session.flush();
            return hechov.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista todas las Agrupaciones Hechos Vitales.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<AgrupacionHechoVital> listarAgrupacionHV() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(AgrupacionHechoVital.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una Agrupacion hecho vital.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacionHechoVital obtenerAgrupacionHV(Long id) {
        Session session = getSession();
        try {
            AgrupacionHechoVital hechov = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
            Hibernate.initialize(hechov.getHechosVitalesAgrupacionHV());
            Hibernate.initialize(hechov.getFoto());
            Hibernate.initialize(hechov.getIcono());
            Hibernate.initialize(hechov.getIconoGrande());
            return hechov;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una Agrupacion hecho vital segun el nombre.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacionHechoVital obtenerAgrupacionHVPorNombre(String nombre) {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("agrupacion.byname");
            query.setParameter("nombre", nombre);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            AgrupacionHechoVital hechov = (AgrupacionHechoVital) result.get(0);
            Hibernate.initialize(hechov.getFoto());
            Hibernate.initialize(hechov.getHechosVitalesAgrupacionHV());
            return hechov;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Borra una Agrupacion Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarAgrupacionHV(Long id) {
        Session session = getSession();
        try {
            AgrupacionHechoVital agrupacion = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);

            List<HechoVitalAgrupacionHV> hechosa = agrupacion.getHechosVitalesAgrupacionHV();
            for (HechoVitalAgrupacionHV hechoagru : hechosa) {
                HechoVital hecho = hechoagru.getHechoVital();
                hecho.removeHechoVitalAgrupacionHV(hechoagru);
            }

            PublicoObjetivo publicoObjetivo = agrupacion.getPublico();
            publicoObjetivo.removeAgrupacion(agrupacion);
            
            session.delete(agrupacion);
            session.flush();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * obtiene la Foto de una AgrupacionHechoVital
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerFoto(Long id) {
        Session session = getSession();
        try {
        	AgrupacionHechoVital hecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
            Hibernate.initialize(hecho.getFoto());
            return hecho.getFoto();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
        * obtiene el icono de una AgrupacionHechoVital
        * @ejb.interface-method
        * @ejb.permission unchecked="true"
        */
       public Archivo obtenerIcono(Long id) {
           Session session = getSession();
           try {
               AgrupacionHechoVital hecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
               Hibernate.initialize(hecho.getIcono());
               return hecho.getIcono();
           } catch (HibernateException he) {
               throw new EJBException(he);
           } finally {
               close(session);
           }
       }

     /**
        * obtiene el icono grande de una AgrupacionHechoVital
        * @ejb.interface-method
        * @ejb.permission unchecked="true"
        */
       public Archivo obtenerIconoGrande(Long id) {
           Session session = getSession();
           try {
               AgrupacionHechoVital hecho = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, id);
               Hibernate.initialize(hecho.getIconoGrande());
               return hecho.getIconoGrande();
           } catch (HibernateException he) {
               throw new EJBException(he);
           } finally {
               close(session);
           }
       }


    /**
     * Lista todas los Hechos Vitales y sus AgrupacionesHV.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<HechoVitalAgrupacionHV> listarAgrupacionesHVHechosVitales() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(HechoVitalAgrupacionHV.class);
            criteri.addOrder(Order.asc("orden"));
            criteri.setCacheable(true);

            List<HechoVitalAgrupacionHV> result = criteri.list();
            for (HechoVitalAgrupacionHV agrupacion : result) {
                Hibernate.initialize(agrupacion.getHechoVital());
            }

            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Lista las agrupaciones Hechos Vitales de un publico objetivo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<HechoVitalAgrupacionHV> listarAgrupacionesHVPorPublicoObjetivo(Long idPubObj) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from AgrupacionHV as agrup where agrup.publico.id =: idPubObj");
            query.setParameter("idPubObj", idPubObj);
            query.setCacheable(true);

            return query.list();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Busca todos los {@link AgrupacionHechoVital} cuyo nombre contenga el String de entrada(PORMAD)
     *
     * @param busqueda
     * @param idioma
     * @return lista de {@link AgrupacionHechoVital}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<AgrupacionHechoVital> buscar(final String busqueda, final String idioma){
		List<AgrupacionHechoVital> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("  from AgrupacionHechoVital as agrHV," +
                                                  "       agrHV.traducciones as trad " +
                                                  "  where index(trad) = :idioma " +
                                                  "    and upper(trad.nombre) like :busqueda");
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	resultado = (List<AgrupacionHechoVital>)query.list();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			resultado = Collections.emptyList();
		}

		return resultado;
	}


}
