package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.TraduccionPlataforma;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.PlataformaDelegateI;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

/**
 * SessionBean para mantener y consultar Publico Objetivo.(PORMAD)
 *
 * @ejb.bean name="sac/persistence/PlataformaFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.PlataformaFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class PlataformaFacadeEJB extends HibernateEJB implements PlataformaDelegateI {

	private static final long serialVersionUID = 1069547135957871563L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Asigna a una plataforma un nuevo orden y reordena los elementos afectados.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 *
	 * @param id
	 *            Identificador de una plataforma.
	 * @param ordenNuevo
	 *            Número que indica el nuevo orden de la plataforma.
	 * @param ordenAnterior
	 *            Número que indica el anterior orden de la plataforma.
	 */
	@Override
	public void reordenar(final Long id, final Integer ordenNuevo, final Integer ordenAnterior) {

		final Session session = getSession();
		try {
			final Criteria criteria = session.createCriteria(Plataforma.class);
			criteria.addOrder(Order.asc("orden"));
			final List<Plataforma> listaPlataformas = castList(Plataforma.class, criteria.list());

			// Modificar sólo los elementos entre la posición del elemento que cambia
			// de orden y su nueva posición
			final int ordenMayor = (ordenNuevo > ordenAnterior) ? ordenNuevo : ordenAnterior;
			final int ordenMenor = (ordenMayor == ordenNuevo) ? ordenAnterior : ordenNuevo;

			// Si el nuevo orden es mayor que el anterior, desplazar los elementos
			// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
			final int incremento = (ordenNuevo > ordenAnterior) ? -1 : 1;

			for (final Plataforma plataforma : listaPlataformas) {
				final int orden = plataforma.getOrden();
				if (orden >= ordenMenor && orden <= ordenMayor) {
					if (id.compareTo(plataforma.getId()) == 0) {
						plataforma.setOrden(ordenNuevo);

					} else {
						plataforma.setOrden(orden + incremento);

					}
				}

				// Actualizar todo para asegurar que no hay duplicados ni huecos
				session.saveOrUpdate(plataforma);
			}

			session.flush();

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista de plataformas.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param filtro
	 * @return
	 */
	@Override
	public ResultadoBusqueda consultaPlataformas(final FiltroGenerico filtro) {

		final Session session = getSession();
		final Integer pageSize = filtro.getPageSize();
		final Integer pageNumber = filtro.getPage();
		final Long id = filtro.getId();
		final String lang = filtro.getLang();
		final Map<String, String> parametros = new HashMap<String, String>();

		final String codigo = filtro.getValor(FiltroGenerico.FILTRO_PLATAFORMAS_CODIGO);
		final String identificador = filtro.getValor(FiltroGenerico.FILTRO_PLATAFORMAS_IDENTIFICADOR);
		final String urlAcceso = filtro.getValor(FiltroGenerico.FILTRO_PLATAFORMAS_URL_ACCESO);
		final String descripcion = filtro.getValor(FiltroGenerico.FILTRO_PLATAFORMAS_DESCRIPCION);

		final StringBuilder select = new StringBuilder("SELECT p ");
		final StringBuilder selectCount = new StringBuilder("SELECT count(p) ");
		final StringBuilder from = new StringBuilder(" FROM Plataforma as p, p.traducciones as trad ");
		final StringBuilder where = new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang", lang);
		final StringBuilder order = new StringBuilder("");

		try {

			if (id != null && id > 0) {
				where.append(" AND p.id = :id");
				parametros.put("id", id.toString());
			}

			if (codigo != null && !codigo.isEmpty()) {
				where.append(" AND p.id = :codigo ");
				parametros.put("codigo", codigo);
			}

			if (identificador != null && !identificador.isEmpty()) {
				where.append(" AND p.identificador = :identificador ");
				parametros.put("identificador", identificador);
			}

			if (urlAcceso != null && !urlAcceso.isEmpty()) {
				where.append(" AND trad.urlAcceso = :urlAcceso ");
				parametros.put("urlAcceso", urlAcceso);
			}

			if (descripcion != null && !descripcion.isEmpty()) {
				where.append(" AND trad.descripcion = :descripcion ");
				parametros.put("descripcion", descripcion);
			}

			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(),
					selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Lista el personal (buscador del nuevo backoffice)
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 */
	@Override
	public ResultadoBusqueda buscadorListarPlataforma(final Map parametros, final int pagina, final int resultats,
			final boolean uaFilles, final boolean uaMeves) {

		final int resultadosMax = resultats;
		final int primerResultado = pagina * resultats;
		final ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		final Session session = getSession();

		try {

			String campoOrden = null;
			String tipoOrden = null;

			if (parametros.get("ordreCamp") != null) {
				campoOrden = parametros.get("ordreCamp").toString();
			}

			if (parametros.get("ordreTipus") != null) {
				tipoOrden = parametros.get("ordreTipus").toString();
			}

			String sql = "select plat from Plataforma plat ";

			final List params = new ArrayList();

			if (campoOrden == null || "orden".equals(campoOrden)) {
				sql += " order by plat.orden ";
				if (tipoOrden == null) {
					sql += " ASC ";
				} else {
					sql += tipoOrden;
				}
				sql += " , plat.identificador ASC ";
			} else {
				sql += " order by plat." + campoOrden + " ";
				if (tipoOrden == null) {
					sql += " ASC ";
				} else {
					sql += tipoOrden;
				}
				sql += " , plat.orden ASC ";
			}
			final Query query = session.createQuery(sql);
			for (int i = 0; i < params.size(); i++) {
				final Object o = params.get(i);
				query.setParameter(i, o);
			}

			resultadoBusqueda.setTotalResultados(query.list().size());
			if (resultadosMax != RESULTATS_CERCA_TOTS) {

				query.setFirstResult(primerResultado);
				query.setMaxResults(resultadosMax);
			}

			resultadoBusqueda.setListaResultados(query.list());
			return resultadoBusqueda;

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Crea o actualiza una plataforma.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param plataforma
	 *            Indica la plataforma a guardar.
	 *
	 * @return Devuelve el identificador de la plataforma objetivo guardado.
	 */
	@Override
	public Long grabarPlataforma(final Plataforma plataforma) throws DelegateException {
		final Session session = getSession();
		try {

			if (plataforma.getTraduccionMap() != null) {
				final List<String> borrarIdiomas = new ArrayList<String>();
				for (final String idioma : plataforma.getTraduccionMap().keySet()) {
					final TraduccionPlataforma trad = (TraduccionPlataforma) plataforma.getTraduccion(idioma);
					if ((trad.getDescripcion() == null || trad.getDescripcion().isEmpty())
							&& (trad.getUrlAcceso() == null || trad.getUrlAcceso().isEmpty())) {
						borrarIdiomas.add(idioma);
					}
				}

				if (!borrarIdiomas.isEmpty()) {
					for (final String idioma : borrarIdiomas) {
						plataforma.getTraducciones().remove(idioma);
					}
				}
			}

			if (plataforma.getOrden() == null) {
				final Integer orden = getOrdenMax();
				plataforma.setOrden(orden);
			}
			session.saveOrUpdate(plataforma);
			session.flush();
			return plataforma.getId();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
	}

	/**
	 * Obtener el orden.
	 *
	 * @return
	 */
	private Integer getOrdenMax() {
		try {
			final Session session = getSession();
			final String sql = "select max(plat.orden) from Plataforma plat ";
			final Query query = session.createQuery(sql);
			Integer orden = (Integer) query.uniqueResult();
			if (orden == null) {
				orden = 1;
			} else {
				orden++;
			}
			return orden;
		} catch (final Exception e) {
			return 1;
		}
	}

	/**
	 * Obtiene un plataforma
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de un plataforma
	 *
	 * @return Devuelve <code>Plataforma</code> solicitado.
	 */
	@Override
	public Plataforma obtenerPlataforma(final Long id) {

		final Session session = getSession();
		try {

			return (Plataforma) session.load(Plataforma.class, id);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Borrar plataforma.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 */
	@Override
	public void borrarPlataforma(final Long id) {
		final Session session = getSession();
		try {

			final Plataforma plataforma = (Plataforma) session.load(Plataforma.class, id);
			session.delete(plataforma);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
	}

	/**
	 * Comprobar plataforma con asociaciones proc/serveis.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @ejb.permission unchecked="true"
	 *
	 * @param idPlataforma
	 */
	@Override
	public boolean plataformaConAsociaciones(final Long idPlataforma) {
		final Session session = getSession();
		boolean conAsociaciones;
		try {
			final String sqlProc = "select count(*) from Tramite tram where tram.plataforma.id = :plataforma  ";
			final Query queryProc = session.createQuery(sqlProc);
			queryProc.setParameter("plataforma", idPlataforma);
			final Integer totalProc = (Integer) queryProc.uniqueResult();

			final String sqlServ = "select count(*) from Servicio serv where serv.plataforma.id = :plataforma  ";
			final Query queryServ = session.createQuery(sqlServ);
			queryServ.setParameter("plataforma", idPlataforma);
			final Integer totalServ = (Integer) queryServ.uniqueResult();

			if (totalServ > 0 || totalProc > 0) {
				conAsociaciones = true;
			} else {
				conAsociaciones = false;
			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
		return conAsociaciones;
	}

}