package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.LopdLegitimacion;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para operar lopd legitimacion.
 *
 * @ejb.bean name="sac/persistence/LopdLegitimacionFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.LopdLegitimacionFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="RequiresNew"
 */
public abstract class LopdLegitimacionFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}

	/**
	 * /** Obtener SIA UAs.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param pagina
	 *            Pagina actual donde nos encontramos.
	 * @param cuantos
	 *            Cuantos elementos a obtener
	 * @param ordenCampo
	 *            Marca el parámetro de orden
	 * @param ordenAsc
	 *            Marca si es ASC o DESC (ascendente o descendente)
	 * @return
	 */
	public ResultadoBusqueda getLopdLegitimacion(final int pagina, final int cuantos, final String ordenCampo,
			final String ordenAsc) {
		log.debug("Obtiene las Lopd Legitimacion.");

		final Session session = getSession();
		final ResultadoBusqueda resultado = new ResultadoBusqueda();

		try {

			final StringBuilder consultaSelect = new StringBuilder("select lopdLeg from LopdLegitimacion as lopdLeg ");
			final StringBuilder consultaTotal = new StringBuilder(
					"select count(lopdLeg) from LopdLegitimacion as lopdLeg ");

			final StringBuilder consulta = new StringBuilder();
			// Indicamos el orden, por defecto es por la id descendente
			if (ordenCampo == null || ordenCampo.isEmpty()) {
				consulta.append(" order by lopdLeg.id desc");
			} else {
				consulta.append(" order by lopdLeg.");
				consulta.append(ordenCampo);
				if (ordenAsc == null || ordenAsc.isEmpty()) {
					consulta.append(" asc ");
				} else {
					consulta.append(" ");
					consulta.append(ordenAsc);
				}
			}

			// La select.
			consultaSelect.append(consulta);
			final Query query = session.createQuery(consultaSelect.toString());
			// Calculamos a partir de cuando hay que calcular.
			final int primerResultado = new Integer(pagina).intValue() * cuantos;
			query.setFirstResult(primerResultado);
			query.setMaxResults(cuantos);
			final List<LopdLegitimacion> lista = query.list();
			resultado.setListaResultados(lista);

			// El total.
			// consultaTotal.append(consulta); //No hace falta el order by en los counts
			final Query queryTotal = session.createQuery(consultaTotal.toString());
			final Integer totalResultados = Integer.valueOf(queryTotal.uniqueResult().toString());
			resultado.setTotalResultados(totalResultados);

			return resultado;

		} catch (final HibernateException he) {

			log.error("Error obteniendo LopdLegitimacion. Pagina:" + pagina + " Cuantos:" + cuantos + " Orden:"
					+ ordenCampo + " OrdenAsc:" + ordenAsc, he);
			throw new EJBException(he);

		} finally {

			close(session);
			log.debug("Obtener SIA pendientes OK");

		}

	}

	/**
	 * Borra lopd Legimitacion
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param lopdLegitimacion
	 */
	public void borrarLopdLegitimacion(final Long id) {
		log.debug("Borrar SIA pendiente");

		Session session = null;

		try {

			session = getSession();
			session.delete("from LopdLegitimacion as lopdLeg where lopdLeg.id = ?", id, Hibernate.LONG);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);
			log.debug("Borrar lopdLegimitacion OK");
		}
	}

	/**
	 * Graba Lopd Legimitacion.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param lopdLegitimacion
	 *            lopd Legimitacion
	 * @throws Exception
	 */
	public void grabarLopdLegitimacion(final LopdLegitimacion lopdLegitimacion) {

		final Session session = getSession();
		try {

			if (lopdLegitimacion.isPorDefecto()) {
				quitarPorDefecto(lopdLegitimacion.getId());
			}

			session.saveOrUpdate(lopdLegitimacion);
			session.flush();
		} catch (final HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}

	}

	/**
	 * Buscamos lo que haya por defecto que no sea el propio elemento (de normal,
	 * debería haber solo uno)
	 *
	 * @param id
	 * @throws HibernateException
	 */
	private void quitarPorDefecto(final Long id) throws HibernateException {
		// Primero hay que marcar el resto como no por defecto

		String sql = "select lopd from LopdLegitimacion lopd where lopd.porDefecto = true ";
		if (id != null) {
			sql += " and lopd.id != " + id;
		}
		final Session session = getSession();

		final Query query = session.createQuery(sql);
		final List<LopdLegitimacion> lopds = query.list();
		if (lopds != null && lopds.size() > 0) {
			for (final LopdLegitimacion lopd : lopds) {
				lopd.setPorDefecto(false);
				session.saveOrUpdate(lopd);
				session.flush();
			}
		}

	}

	/**
	 * Devuelve Lopd Legitimacion.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            id Lopd Legitimacion
	 */
	public LopdLegitimacion obtenerLopdLegitimacion(final Long id) {
		Session session = null;
		try {
			session = getSession();
			final LopdLegitimacion lopdLegitimacion = (LopdLegitimacion) session.get(LopdLegitimacion.class, id);
			return lopdLegitimacion;
		} catch (final Exception exception) {
			throw new EJBException(exception);
		} finally {
			close(session);
		}
	}

}
