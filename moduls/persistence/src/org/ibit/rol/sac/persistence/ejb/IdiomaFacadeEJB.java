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
     * Devuelve una lista de {@link java.lang.String} con el codigo ISO los idiomas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<String> /* String */ listarLenguajes() {
        Session session = getSession();
        try {
            Query query = session.createQuery("select idi.lang from Idioma as idi order by idi.orden");
            query.setCacheable(true);
            return castList(String.class, query.list());
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Devuelve una lista de {@link java.lang.String} con el los Ids de Idiomas del traductor.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<String> listarLenguajesTraductor() {
        Session session = getSession();
        try {
            Query query = session.createQuery("select idi.langTraductor from Idioma as idi order by idi.orden");
            query.setCacheable(true);
            return castList(String.class, query.list());
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }    

    /**
     * Obtiene el codigo ISO del lenguaje por defecto.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public String lenguajePorDefecto() {
        try {
        	return System.getProperty("es.caib.rolsac.idiomaDefault");
        } catch (java.lang.SecurityException es) {
            throw new java.lang.SecurityException(es);}
          catch (NullPointerException ne) {
            throw new NullPointerException();}
          catch (IllegalArgumentException i) {
            throw new IllegalArgumentException(i);
        }
    }


    /**
     * Devuelve una lista de idiomas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Idioma> listarIdiomas() {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Idioma");
            query.setCacheable(true);
            return castList(Idioma.class, query.list());
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Devuelve el tiempo de ejecutar una select de los idiomas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public long testeoHql() {
        Session session = getSession();
        try {
        	int _repes = 5;
        	int _acumula = 0;
        	for (int i=0;i<_repes;i++) {
	            Query query = session.createQuery("Select idi.lang from Idioma as idi");
	            query.setCacheable(false);
	            long ini = System.currentTimeMillis();
	            List<String> lista =  castList(String.class, query.list());
	            long fin = System.currentTimeMillis();
	            _acumula += (fin-ini); 
        	}
            return _acumula/_repes;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    
}
