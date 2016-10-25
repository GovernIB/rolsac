package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Archivo;


/**
 * SessionBean para obtener archivos.
 *
 * @ejb.bean
 *  name="sac/persistence/ArchivoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ArchivoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class ArchivoFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = -33267938120534732L;
	private static final String nombrePorDefecto = "sin_nombre";

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Obtiene una instancia de tipo Archivo
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param id	Identificador del archivo a obtener.
	 * 
	 * @return <code>Archivo</code> con el identificador especificado.
	 */
	public Archivo obtenerArchivo(Long id) {

		Session session = getSession();

		try {

			Archivo archivo = new Archivo();

			Query query = session.createQuery("from Archivo archivo where archivo.id = :id");
			query.setParameter( "idArchivo" , id );

			if ( query.list().size() == 1 )	
				archivo = (Archivo) query.list().get(0);

			Hibernate.initialize(archivo);

			return archivo;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Borra un archivo.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @param	id	Identificador del archivo a borrar.
	 */
	public void borrarArchivo(Long id) {

		Session session = getSession();
		try {

			Archivo archivo = (Archivo) session.load(Archivo.class, id);
			session.delete(archivo);
			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);  

		} finally {

			close(session);

		}

	}  

	
}
