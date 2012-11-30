package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import org.ibit.rol.sac.model.*;

import es.caib.rolsac.utils.ResultadoBusqueda;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * SessionBean para mantener y consultar Hechos Vitales.
 *
 * @ejb.bean
 *  name="sac/persistence/HechoVitalFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.HechoVitalFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class HechoVitalFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarHechoVital(HechoVital hechov) {
        Session session = getSession();
        try {
            if (hechov.getId() == null) {
                Criteria criteria = session.createCriteria(HechoVital.class);
                List result = criteria.list();
                if (result.isEmpty()) {
                    hechov.setOrden(0);
                } else {
                    hechov.setOrden(result.size());
                }
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
     * Incrementa el orden de un hecho vital.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    @SuppressWarnings("unchecked")
	public void subirOrden(Long id) {
        Session session = getSession();
        try {
            HechoVital hechov1 = (HechoVital) session.load(HechoVital.class, id);
            int orden = hechov1.getOrden();
            if (orden > 0) {

                Criteria criteri = session.createCriteria(HechoVital.class);
                criteri.addOrder(Order.asc("orden"));
                List<HechoVital> result = criteri.list();

                HechoVital hechov2 = (HechoVital) result.get(orden - 1);

                hechov2.setOrden(orden);
                result.set(orden, hechov2);

                hechov1.setOrden(orden - 1);
                result.set(orden - 1, hechov1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todas los Hechos Vitales (nuevo backoffice, tablas maestras).
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public ResultadoBusqueda listarHechosVitales(int pagina, int resultados) {    	
    	return listarTablaMaestraPaginada( pagina, resultados, listarHechosVitales() );    	
    }
    
    /**
     * Lista todas los Hechos Vitales.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarHechosVitales() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(HechoVital.class);
            criteri.addOrder(Order.asc("orden"));
            criteri.setCacheable(true);

            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    private List<?> listarTablaMaestra() {
    	return listarHechosVitales();
    }
    
    /**
     * Lista todas los Hechos Vitales y sus procedimientos.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarHechosVitalesProcedimientos() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(HechoVital.class);
            criteri.addOrder(Order.asc("orden"));
            criteri.setCacheable(true);

            List result = criteri.list();
            for (int i = 0; i < result.size(); i++) {
                HechoVital hechoVital = (HechoVital) result.get(i);
                Hibernate.initialize(hechoVital.getHechosVitalesProcedimientos());
            }

            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Listar Hechos Vitales AgrupacionHechoVital
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List<HechoVital> listarHechosVitalesAgrupacionHV(Long agrupacion_id) {
        Session session = getSession();
        try {
            List<HechoVital> result = new ArrayList<HechoVital>();
            AgrupacionHechoVital agrupacion = (AgrupacionHechoVital) session.load(AgrupacionHechoVital.class, agrupacion_id);
            for (HechoVitalAgrupacionHV temp : agrupacion.getHechosVitalesAgrupacionHV()) {
                HechoVital hechovital = temp.getHechoVital();
                result.add(hechovital);
            }
            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un hecho vital.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public HechoVital obtenerHechoVital(Long id) {
        Session session = getSession();
        try {
            HechoVital hechov = (HechoVital) session.load(HechoVital.class, id);
            Hibernate.initialize(hechov.getFoto());
            Hibernate.initialize(hechov.getIcono());
            Hibernate.initialize(hechov.getIconoGrande());
            Hibernate.initialize(hechov.getHechosVitalesProcedimientos());
            for (Iterator iterator = hechov.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionHechoVital traduccion = (TraduccionHechoVital) hechov.getTraduccion(lang);
                if(traduccion != null){
                    Hibernate.initialize(traduccion.getDistribComp());
                    Hibernate.initialize(traduccion.getNormativa());
                    Hibernate.initialize(traduccion.getContenido());
                }
            }
            return hechov;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene un hecho vital segun el nombre.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public HechoVital obtenerHechoVitalPorNombre(String nombre) {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("hechos.byname");
            query.setParameter("nombre", nombre);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            HechoVital hechov = (HechoVital) result.get(0);
            Hibernate.initialize(hechov.getIcono());
            Hibernate.initialize(hechov.getHechosVitalesProcedimientos());
            return hechov;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
         * obtiene la foto de un hecho vital
         * @ejb.interface-method
         * @ejb.permission unchecked="true"
         */
        public Archivo obtenerFoto(Long id) {
            Session session = getSession();
            try {
                HechoVital hecho = (HechoVital) session.load(HechoVital.class, id);
                Hibernate.initialize(hecho.getFoto());
                return hecho.getFoto();
            } catch (HibernateException he) {
                throw new EJBException(he);
            } finally {
                close(session);
            }
        }


    /**
     * obtiene el icono de un hecho vital
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerIcono(Long id) {
        Session session = getSession();
        try {
            HechoVital hecho = (HechoVital) session.load(HechoVital.class, id);
            Hibernate.initialize(hecho.getIcono());
            return hecho.getIcono();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * obtiene el icono grande de un hecho vital
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerIconoGrande(Long id) {
        Session session = getSession();
        try {
            HechoVital hecho = (HechoVital) session.load(HechoVital.class, id);
            Hibernate.initialize(hecho.getIconoGrande());
            return hecho.getIconoGrande();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }



    /**
     * Borra un Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarHechoVital(Long id) {
        Session session = getSession();
        try {
            HechoVital hechov = (HechoVital) session.load(HechoVital.class, id);
            for(Ficha ficha : (Set<Ficha>)hechov.getFichas()){
            	ficha.removeHechovital(hechov);
            }
            
            List hechosvp = hechov.getHechosVitalesProcedimientos();
            for (Iterator iter = hechosvp.iterator(); iter.hasNext();) {
                HechoVitalProcedimiento hechovp = (HechoVitalProcedimiento) iter.next();
                ProcedimientoLocal proc = hechovp.getProcedimiento();
                proc.removeHechoVitalProcedimiento(hechovp);
            }
            
            Set<HechoVitalAgrupacionHV> hvagrList = hechov.getHechosVitalesAgrupacionHV();
            for (Iterator iter = hvagrList.iterator(); iter.hasNext();) {
                HechoVitalAgrupacionHV hechova = (HechoVitalAgrupacionHV) iter.next();
                AgrupacionHechoVital agru = hechova.getAgrupacion();
                agru.removeHechoVitalAgrupacionHV(hechova);
            }
            
            
            Criteria criteria = session.createCriteria(HechoVital.class);
            criteria.add(Expression.gt("orden", new Integer(hechov.getOrden())));
            List hechos = criteria.list();
            for (int i = 0; i < hechos.size(); i++) {
                HechoVital hec = (HechoVital) hechos.get(i);
                hec.setOrden(i);
            }

            session.delete(hechov);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene la distribucion competencial de un Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerDistribComp(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            HechoVital hvital = (HechoVital) session.load(HechoVital.class, id);
            TraduccionHechoVital tradHVital = (TraduccionHechoVital) hvital.getTraduccion(lang);
            if (tradHVital == null || tradHVital.getDistribComp() == null) {
                if (useDefault) {
                	tradHVital = (TraduccionHechoVital) hvital.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradHVital.getDistribComp());
            return tradHVital.getDistribComp();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Obtiene la normativa aplicada de un Hecho Vital.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerNormativa(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            HechoVital hvital = (HechoVital) session.load(HechoVital.class, id);
            TraduccionHechoVital tradHVital = (TraduccionHechoVital) hvital.getTraduccion(lang);
            if (tradHVital == null || tradHVital.getNormativa() == null) {
                if (useDefault) {
                	tradHVital = (TraduccionHechoVital) hvital.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradHVital.getNormativa());
            return tradHVital.getNormativa();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene el contenido de un HechoVital.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerContenido(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            HechoVital hvital = (HechoVital) session.load(HechoVital.class, id);
            TraduccionHechoVital tradHVital = (TraduccionHechoVital) hvital.getTraduccion(lang);
            if (tradHVital == null || tradHVital.getContenido() == null) {
                if (useDefault) {
                	tradHVital = (TraduccionHechoVital) hvital.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradHVital.getContenido());
            return tradHVital.getContenido();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * A partir de un Array de Strings con los codigos
     * estandar de los HechosVitales recojo un {@link Set} de {@link HechoVital}
     * 
     * @param codigosEstandar
     * @return Un {@link List} de {@link HechoVital}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public List<HechoVital> obtenerHechosVitalesCE(final String[] codigosEstandar){
    	final List<HechoVital> resultado = new ArrayList<HechoVital>();
    	for(String codigoEstandar : codigosEstandar){
    		HechoVital hechoVital =  obtenerHechoVitalCE(codigoEstandar);
			if(hechoVital!=null){
				resultado.add(hechoVital);
			}
    	}
		return resultado;
	}
	
	/**
     * A partir de un Strings con el codigo estandar de el HechoVital recojo
     * la {@link HechoVital} correspondiente
     * 
     * @param codigoEstandar
     * @return {@link HechoVital}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public HechoVital obtenerHechoVitalCE(final String codigoEstandar){
		Session session = getSession();
        try {
        	Query query = session.createQuery("from HechoVital as hv where hv.codigoestandar=:codigo");
        	query.setString("codigo", codigoEstandar);
            return (HechoVital)query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
	}


    /**
     * Obtiene todos los grupos {@link HechoVitalAgrupacionHV} a los que pertenece un determinado hecho vital
     * @return lista de {@link HechoVitalAgrupacionHV}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
    public Set<HechoVitalAgrupacionHV> obtenerGruposHechoVital(Long id_hechoVital) {
        Session session = getSession();
        try {
            HechoVital hechoVital = (HechoVital) session.load(HechoVital.class, id_hechoVital);
            Hibernate.initialize(hechoVital.getHechosVitalesAgrupacionHV());
            return hechoVital.getHechosVitalesAgrupacionHV();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Busca todos los {@link HechoVital} cuyo nombre contenga el String de entrada
     * 
     * @param busqueda
     * @param idioma
     * @return lista de {@link HechoVital}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<HechoVital> buscar(final String busqueda, final String idioma){
		List<HechoVital> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("from HechoVital as hev, hev.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	resultado = (List<HechoVital>)query.list();
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
