package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.MateriaAgrupacionM;

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
public abstract class AgrupacionMFacadeEJB extends HibernateEJB
{
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException
	{
		super.ejbCreate();
	}
	
	
	/**
	 * Obtiene una Agrupacion materia.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de la agrupación de materias a obtener.
	 * @return Devuelve una instancia de tipo <code>AgrupacionMateria</code>.	
	 */
	public AgrupacionMateria obtenerAgrupacionMaterias(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionMateria agrMateria = (AgrupacionMateria) session.load(AgrupacionMateria.class , id);
			Hibernate.initialize(agrMateria.getMateriasAgrupacionM());
			
			return agrMateria;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Borra una Agrupacion de materias.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param id	Identificador de la agrupación de materias.
	 */
	public void borrarAgrupacionM(Long id)
	{
		Session session = getSession();
		try {
			AgrupacionMateria agrupacion = (AgrupacionMateria) session.load(AgrupacionMateria.class, id);
			if (agrupacion.getMateriasAgrupacionM() != null) {
				for (MateriaAgrupacionM materiaAgrupacionM : agrupacion.getMateriasAgrupacionM()) {
					if (materiaAgrupacionM != null) {
						session.delete(materiaAgrupacionM);
					}
				}
			}
			
			session.delete(agrupacion);
			session.flush();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza una Agrupacion de materias.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param agrMateria Entidad de tipo AgrupacionMateria a almnacenar
	 * @param listaMateriasAsignadas	Listado de materias asignadas a la agrupación de materias.
	 * @return Devuelve el identificador de la agrupación de materias guardada.
	 */
	public Long guardarAgrupacionMaterias(AgrupacionMateria agrMateria, List<MateriaAgrupacionM> listaMateriasAsignadas)
	{
		Session session = getSession();
		try {
			if (listaMateriasAsignadas != null) {
				for (MateriaAgrupacionM materiaAgrupacionMaterias : listaMateriasAsignadas) {
					if (materiaAgrupacionMaterias != null) {
						session.delete(materiaAgrupacionMaterias);
					}
				}
			}
			
			List<MateriaAgrupacionM> listaMateriaAgrupacionMaterias = agrMateria.getMateriasAgrupacionM();
			for (MateriaAgrupacionM materiaAgrupacionMaterias : listaMateriaAgrupacionMaterias) {
				session.saveOrUpdate(materiaAgrupacionMaterias);
			}
			
			session.saveOrUpdate(agrMateria);
			session.flush();
			
			return agrMateria.getId();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
}
