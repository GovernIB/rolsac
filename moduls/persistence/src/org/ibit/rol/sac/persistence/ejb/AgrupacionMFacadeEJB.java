package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Order;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.ejb.HibernateEJB;
import org.ibit.rol.sac.model.AgrupacionMateria;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;
import java.util.Collections;

/**
 * SessionBean para mantener y consultar Agrupaciones Materia.
 *
 * @ejb.bean
 *  name="sac/persistence/AgrupacionMFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.AgrupacionMFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class AgrupacionMFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza una Agrupacion Materia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarAgrupacionM(AgrupacionMateria matagr) {
        Session session = getSession();
        try {
            session.saveOrUpdate(matagr);
            session.flush();
            return matagr.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todas las Agrupaciones Materias.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<AgrupacionMateria> listarAgrupacionM() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(AgrupacionMateria.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una Agrupacion materia.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacionMateria obtenerAgrupacionM(Long id) {
        Session session = getSession();
        try {
            AgrupacionMateria mata = (AgrupacionMateria) session.load(AgrupacionMateria.class, id);
            Hibernate.initialize(mata.getMateriasAgrupacionM());
            return mata;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una Agrupacion Materia segun el nombre.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacionMateria obtenerAgrupacionMPorNombre(String nombre) {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("agrupacionm.byname");
            query.setParameter("nombre", nombre);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            AgrupacionMateria mata = (AgrupacionMateria) result.get(0);
            Hibernate.initialize(mata.getMateriasAgrupacionM());
            return mata;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Borra una Agrupacion Materia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarAgrupacionM(Long id) {
        Session session = getSession();
        try {
            AgrupacionMateria agrupacion = (AgrupacionMateria) session.load(AgrupacionMateria.class, id);

            List<MateriaAgrupacionM> mata = agrupacion.getMateriasAgrupacionM();
            for (MateriaAgrupacionM matagr : mata) {
            	Materia mat = matagr.getMateria();
                mat.removeMateriaAgrupacionM(matagr);
            }

            session.delete(agrupacion);
            session.flush();
            
            session.delete(agrupacion);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Lista todas las Materias y sus AgrupacionesM.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
	public List<MateriaAgrupacionM> listarAgrupacionesMMaterias() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(MateriaAgrupacionM.class);
            criteri.addOrder(Order.asc("orden"));
            criteri.setCacheable(true);

            List<MateriaAgrupacionM> result = criteri.list();
            for (MateriaAgrupacionM agrupacion : result) {
                Hibernate.initialize(agrupacion.getMateria());
            }

            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Busca todos los {@link AgrupacionMateria} cuyo nombre contenga el String de entrada
     *
     * @param busqueda
     * @param idioma
     * @return lista de {@link AgrupacionMateria}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<AgrupacionMateria> buscar(final String busqueda, final String idioma){
		List<AgrupacionMateria> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("  from AgrupacionMateria as agrM," +
                                                  "       agrM.traducciones as trad " +
                                                  "  where index(trad) = :idioma " +
                                                  "    and upper(trad.nombre) like :busqueda");
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	resultado = (List<AgrupacionMateria>)query.list();
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
