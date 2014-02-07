package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

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


    /**
     * Lista todos los Idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     *
     * @return Devuelve un listado de todos los idiomas.
     */
    public List<Idioma> listarIdiomas() {

        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Idioma.class);
            criteri.addOrder(Order.asc("orden"));
            List<Idioma> idiomas = castList(Idioma.class, criteri.list());

            return idiomas;

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Obtiene un Idioma
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     * @param   id  Identificador de un idioma
     * 
     * @return  Devuelve <code>Idioma</code> solicitado.
     */
    public Idioma obtenerIdioma(String lang) {

        Session session = getSession();
        Idioma idioma;
        try {
            idioma = (Idioma) session.load(Idioma.class, lang);

        } catch (HibernateException he) {
            idioma = null;
        } finally {
            close(session);
        }

        return idioma;
    }


    /**
     * Crea o actualiza un Idioma.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param   idioma Indica el idioma a guardar.
     * 
     * @return Devuelve el identificador del idioma guardado.
     */
    public String grabarIdioma(Idioma idioma) {

        Session session = getSession();
        try {
            /* Si se cumple la condición entonces el idioma es nuevo.
             * En caso contrario no debemos realizar la consulta
             * para no perder la session de hibernate con el idioma
             */ 
            if (idioma.getOrden() == 0) {
                Criteria criteria = session.createCriteria(Idioma.class);
                List<Idioma> result = castList(Idioma.class, criteria.list());
                if (result.isEmpty()) {
                    idioma.setOrden(1);
                } else if (idioma.getOrden() < 1) {
                    idioma.setOrden(result.size() + 1);
                }
                session.save(idioma);

            } else {
                session.update(idioma);
            }

            session.flush();
            return idioma.getLang();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Borra un Idioma.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param   lang  Identificador de un idioma.
     */
    public void borrarIdioma(String lang) {

        Session session = getSession();
        try {
            Idioma idioma = (Idioma) session.load(Idioma.class, lang);

            Criteria criteria = session.createCriteria(Idioma.class);
            criteria.add(Expression.gt("orden", new Integer(idioma.getOrden())));
            criteria.addOrder(Order.asc("orden"));
            List<Idioma> idiomas = castList(Idioma.class, criteria.list());

            for (int i = 0; i < idiomas.size(); i++) {
                Idioma idi = (Idioma) idiomas.get(i);
                idi.setOrden(idi.getOrden() - 1);
            }

            session.delete(idioma);
            session.flush();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Asigna a un idioma un nuevo orden y reordena los elementos afectados.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param   id  Identificador de un idioma.
     * @param   ordenNuevo  Número que indica el nuevo orden del idioma.
     * @param   ordenAnterior   Número que indica el anterior orden del idioma.
     */ 
    public void reordenar(String lang, Integer ordenNuevo, Integer ordenAnterior) {

        Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(Idioma.class);
            criteria.addOrder(Order.asc("orden"));
            List<Idioma> listaIdiomas = castList(Idioma.class, criteria.list());

            // Modificar sólo los elementos entre la posición del elemento que cambia 
            // de orden y su nueva posición 
            int ordenMayor = ( ordenNuevo > ordenAnterior ) ? ordenNuevo : ordenAnterior;
            int ordenMenor = ( ordenMayor == ordenNuevo ) ? ordenAnterior : ordenNuevo;

            // Si el nuevo orden es mayor que el anterior, desplazar los elementos 
            // intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
            int incremento = ( ordenNuevo > ordenAnterior ) ? -1 : 1;                   

            for (Idioma idioma : listaIdiomas) {
                int orden = idioma.getOrden();
                if (orden >= ordenMenor && orden <= ordenMayor) {
                    if (lang.equals(idioma.getLang())) {
                        idioma.setOrden(ordenNuevo);

                    } else {                        
                        idioma.setOrden(orden + incremento);

                    }
                }

                // Actualizar todo para asegurar que no hay duplicados ni huecos                
                session.saveOrUpdate(idioma);
            }

            session.flush();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
