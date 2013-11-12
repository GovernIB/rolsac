package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.List;

import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegateI;

/**
 * SessionBean para consultar idiomas.
 *
 * @ejb.bean
 *  name="sac/persistence/IdiomaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.IdiomaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class IdiomaFacadeEJB extends HibernateEJB implements IdiomaDelegateI {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve una lista de {@link java.lang.String} con el codigo ISO los idiomas.
	 */
	public List<String> listarLenguajes() {

		Session session = getSession();

		try {

			Query query = session.createQuery("select idi.lang from Idioma as idi order by idi.orden");
			query.setCacheable(true);

			return castList( String.class, query.list() );

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve una lista de {@link java.lang.String} con el los Ids de Idiomas del traductor.
	 */
	public List<String> listarLenguajesTraductor() {

		Session session = getSession();

		try {

			Query query = session.createQuery("select idi.langTraductor from Idioma as idi order by idi.orden");
			query.setCacheable(true);

			return castList( String.class, query.list() );

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}  


	/**
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve el código ISO del lenguaje por defecto.
	 */
	public String lenguajePorDefecto() {

		try {

			return System.getProperty("es.caib.rolsac.idiomaDefault");

		} catch (java.lang.SecurityException es) {

			throw new java.lang.SecurityException(es);
			
		} catch (NullPointerException ne) {

			throw new NullPointerException();
			
		} catch (IllegalArgumentException i) {

			throw new IllegalArgumentException(i);

		}

	}
	
}
