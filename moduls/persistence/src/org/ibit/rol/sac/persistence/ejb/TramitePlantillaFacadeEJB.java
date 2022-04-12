package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.TramitePlantilla;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegateI;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar tramitePlantilla.
 *
 * @ejb.bean name="sac/persistence/TramitePlantillaFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.TramitePlantillaFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TramitePlantillaFacadeEJB extends HibernateEJB implements TramitePlantillaDelegateI {
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Crea o actualiza una tramitePlantilla.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param tramitePlantilla
	 *            Indica una instancia de <code>TramitePlantilla</code> a guardar.
	 * @return Devuelve el identificador del tramitePlantilla guardado.
	 * @throws DelegateException
	 */
	@Override
	public Long grabarTramitePlantilla(final TramitePlantilla tramitePlantilla, final boolean editar)
			throws DelegateException {
		final Session session = getSession();
		try {
			// TODO revisar porque no funciona al crear el saveorupdate
			if (editar) {
				session.saveOrUpdate(tramitePlantilla);
			} else {
				session.save(tramitePlantilla);
			}

			session.flush();
			return tramitePlantilla.getId();

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista todos los tramitePlantillas.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param pagina
	 *            Indica la última página del listado consultada.
	 * @param resultats
	 *            Indica el número de registros a mostrar por página.
	 * @param idioma
	 *            Indica el idioma en que se realiza la búsqueda.
	 * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado
	 *         paginado con todos los tramitePlantillas.
	 */
	@Override
	public ResultadoBusqueda listarTramitePlantilla(final int pagina, final int resultats, final String idioma) {
		return listarTablaMaestraPaginada(pagina, resultats, listarTMTramitePlantilla(idioma));
	}

	/**
	 * Lista todos los tramitePlantillas.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @return Devuelve <code>List</code> de todas los tramitePlantillas.
	 */
	@Override
	public List listarTramitePlantilla() {
		final Session session = getSession();
		try {
			final Criteria criteri = session.createCriteria(TramitePlantilla.class);
			return criteri.list();

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve el total de procedimientos con dicho tramitePlantilla
	 * administrativo.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Devuelve <code>List</code> de todas los tramitePlantillas.
	 */
	@Override
	public int cuantosProcedimientosConTramitePlantilla(final Long id) {
		final Session session = getSession();
		int cuantos = 0;
		try {
			final StringBuilder consulta = new StringBuilder("select count(proc) ");
			consulta.append("from Tramite proc ");
			consulta.append("where proc.tramitePlantilla.id = ");
			consulta.append(id);

			final Query query = session.createQuery(consulta.toString());
			cuantos = Integer.valueOf(query.uniqueResult().toString());
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		return cuantos;
	}

	/**
	 * Lista todos los tramitePlantillas (menú Administración).
	 *
	 * @param idioma
	 *            Indica el idioma en que se realiza la búsqueda.
	 * @return Devuelve <code>List</code> de todos los tramitePlantillas.
	 */
	private List listarTMTramitePlantilla(final String idioma) {
		final Session session = getSession();
		try {

			final StringBuilder consulta = new StringBuilder("select tramPlant.id, trad.nombre ");
			consulta.append("from TramitePlantilla as tramPlant, tramPlant.traducciones as trad ");
			consulta.append("where index(trad) = :idioma ");
			consulta.append("order by trad.nombre asc");

			final Query query = session.createQuery(consulta.toString());

			query.setParameter("idioma", idioma);
			return query.list();

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene un tramitePlantilla.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * @param codigo
	 *            Identificador del tramitePlantilla.
	 * @return Devuelve <code>TramitePlantilla</code> solicitada.
	 */
	@Override
	public TramitePlantilla obtenerTramitePlantilla(final Long codigo) {
		final Session session = getSession();
		try {
			final TramitePlantilla tramitePlantilla = (TramitePlantilla) session.load(TramitePlantilla.class, codigo);
			return tramitePlantilla;

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Borra un tramitePlantilla.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 * @param codigo
	 *            Identificador del tramitePlantilla a borrar.
	 */
	@Override
	public void borrarTramitePlantilla(final Long codigo) {
		final Session session = getSession();
		try {
			final TramitePlantilla tramitePlantilla = (TramitePlantilla) session.load(TramitePlantilla.class, codigo);

			// TODO controlar asociacion
			// if (procedimientos.size() != 0) {
			// throw new EJBException("El tramitePlantilla tiene procedimientos asociados");
			// }
			session.delete(tramitePlantilla);
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
	public ResultadoBusqueda consultaPlantillas(final FiltroGenerico filtro) {

		final Session session = getSession();
		final Integer pageSize = filtro.getPageSize();
		final Integer pageNumber = filtro.getPage();
		final Long id = filtro.getId();
		final String lang = filtro.getLang();
		final Map<String, String> parametros = new HashMap<String, String>();

		final String codigo = filtro.getValor(FiltroGenerico.FILTRO_PLANTILLAS_CODIGO);
		final String identificador = filtro.getValor(FiltroGenerico.FILTRO_PLANTILLAS_IDENTIFICADOR);
		final String urlAcceso = filtro.getValor(FiltroGenerico.FILTRO_PLANTILLAS_URL_ACCESO);
		final String nombre = filtro.getValor(FiltroGenerico.FILTRO_PLANTILLAS_NOMBRE);
		final String version = filtro.getValor(FiltroGenerico.FILTRO_PLANTILLAS_VERSION);
		final String plataforma = filtro.getValor(FiltroGenerico.FILTRO_PLANTILLAS_PLATAFORMA);

		final StringBuilder select = new StringBuilder("SELECT p ");
		final StringBuilder selectCount = new StringBuilder("SELECT count(p) ");
		final StringBuilder from = new StringBuilder(" FROM TramitePlantilla as p, p.traducciones as trad ");
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
				where.append(" AND p.plataforma.urlAcceso = :urlAcceso ");
				parametros.put("urlAcceso", urlAcceso);
			}

			if (plataforma != null && !plataforma.isEmpty()) {
				where.append(" AND p.plataforma.id = :plataforma ");
				parametros.put("plataforma", plataforma);
			}

			if (version != null && !version.isEmpty()) {
				where.append(" AND p.version = :version ");
				parametros.put("version", version);
			}

			if (nombre != null && !nombre.isEmpty()) {
				where.append(" AND trad.nombre = :nombre ");
				parametros.put("nombre", nombre);
			}

			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(),
					selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

}
