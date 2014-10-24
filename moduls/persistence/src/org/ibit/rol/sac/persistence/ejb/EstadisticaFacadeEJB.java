package org.ibit.rol.sac.persistence.ejb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Estadistica;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoFicha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.FechaHistoricoDTO;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;

/**
 * SessionBean para manejar y consultar Estadisticas.
 *
 * @ejb.bean
 *  name="sac/persistence/EstadisticaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EstadisticaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class EstadisticaFacadeEJB extends HibernateEJB
{
	protected static Log log = LogFactory.getLog(EstadisticaFacadeEJB.class);
	
	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException
	{
		super.ejbCreate();
	}
	
	
	/**
	 * Crea o actualiza una Estadistica para una unidadAdministrativa
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idUnidad	Identificador de una unidad administrativa.
	 */
	public void grabarEstadisticaUnidadAdministrativa(Long idUnidad)
	{
		Session session = getSession();
		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUnidad);
			Periodo periodo = PeriodoUtil.crearPeriodoMes();
			Historico historico = getHistorico(session, ua);
			grabarEstadistica(session, historico, periodo);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza una Estadistica para una Normativa
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idNormativa	Identificador de una normativa.
	 */
	public void grabarEstadisticaNormativa(Long idNormativa)
	{
		Session session = getSession();
		try {
			Normativa normativa = (Normativa) session.load(Normativa.class, idNormativa);
			Periodo periodo = PeriodoUtil.crearPeriodoMes();
			Historico historico = getHistorico(session, normativa);
			grabarEstadistica(session, historico, periodo);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza una Estadistica para una Procedimiento Local
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idProcedimiento	Identificador de un procedimiento.
	 */
	public void grabarEstadisticaProcedimiento(Long idProcedimiento)
	{
		Session session = getSession();
		try {
			ProcedimientoLocal proc = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, idProcedimiento);
			Periodo periodo = PeriodoUtil.crearPeriodoMes();
			Historico historico = getHistorico(session, proc);
			grabarEstadistica(session, historico, periodo);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza una Estadistica para una Ficha
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void grabarEstadisticaFicha(Long ficha_id)
	{
		Session session = getSession();
		try {
			if (ficha_id!=null && ficha_id.longValue()!=0) {
				Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
				Periodo periodo = PeriodoUtil.crearPeriodoMes();
				Historico historico = getHistorico(session, ficha);
				grabarEstadistica(session, historico, periodo);
			}
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Graba estadísticas.
	 * 
	 * @param session	Indica la sesión de hibernate.
	 * @param historico	Indica el historico asignado a la estadística a guardar.
	 * @param periodo	Indica el rango de fechas en las que se encuentra la estadística a guardar.
	 * @throws HibernateException
	 */
	private void grabarEstadistica(Session session, Historico historico, Periodo periodo) throws HibernateException
	{
		Query query = session.createQuery(
				"from Estadistica as est " +
				"where est.historico = :historico " +
				"	and est.fecha between :fecha_inicio " +
				"	and :fecha_fin");
		
		query.setEntity("historico", historico);
		query.setDate("fecha_inicio", periodo.getFechaInicio());
		query.setDate("fecha_fin", periodo.getFechaFin());
		query.setMaxResults(1);
		query.setCacheable(true);
		
		List result = query.list();
		if (result.isEmpty()) {
			Estadistica estadistica = new Estadistica();
			estadistica.setFecha(periodo.getFechaInicio());
			estadistica.setContador(1);
			estadistica.setHistorico(historico);
			session.saveOrUpdate(estadistica);
			
		} else {
			Estadistica estadistica = (Estadistica) result.get(0);
			estadistica.setContador(estadistica.getContador() + 1);
		}
		session.flush();
	}
	
	
	/**
	 * Resumen en un periodo de una unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idUA	Identificador de la unidad administrativa.
	 * @param fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @return Devuelve <code>List</code> de unas estadísticas en un periodo de tiempo de una unidad administrativa.
	 */
	public List listarEstadisticasUnidad(Long idUA, Date fechaInicio, Date fechaFin)
	{
		Session session = getSession();
		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			Historico historico = getHistorico(session, ua);
			Criteria criterio = session.createCriteria(Estadistica.class);
			criterio.add(Expression.eq("historico.id", historico.getId()));
			criterio.add(Expression.between("fecha", fechaInicio, fechaFin));
			criterio.addOrder(Order.asc("fecha"));
			List list = criterio.list();
			
			return completarEstadisticaMeses(list, fechaInicio, fechaFin);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Resumen en un periodo del arbol de una unidad Administrativa.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas.
	 * @param	fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @return Devuelve <code>List<Estadistica></code> de unas determinadas unidades administrativas en un rango de fecha.
	 */
	public List<Estadistica> listarEstadisticasListaUnidadAdministrativaId(List<Long> listaUnidadAdministrativaId, Date fechaInicio, Date fechaFin)
	{
		Session session = getSession();
		List<Estadistica> list;
		try {
			Query query = null;
			query = session.createQuery(
					" select est from Estadistica est, HistoricoUA hua " +
					" where est.historico.id = hua.id " +
					" 	and est.fecha between :fechaIni and :fechaFin " +
					" 	and hua.ua.id in (:lId) " +
					" order by est.fecha asc ");
			
			query.setDate("fechaIni", fechaInicio);
			query.setDate("fechaFin", fechaFin);
			query.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
			list = query.list();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		
		return completarEstadisticaMesesListaUA( list, fechaInicio, fechaFin );
	}
	
	
	/**
	 * Resumen en un periodo de una Normativa.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idNormativa	Identificador de la unidad administrativa.
	 * @param	fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @return Devuelve <code>List</code> de estadísticas en un periodo de tiempo.
	 */
	public List listarEstadisticasNormativa(Long idNormativa, Date fechaInicio, Date fechaFin)
	{
		Session session = getSession();
		try {
			Normativa normativa = (Normativa) session.load(Normativa.class, idNormativa);
			Historico historico = getHistorico(session, normativa);
			Criteria criterio = session.createCriteria(Estadistica.class);
			criterio.add(Expression.eq("historico.id", historico.getId()));
			criterio.add(Expression.between("fecha", fechaInicio, fechaFin));
			criterio.addOrder(Order.asc("fecha"));
			List list = criterio.list();
			
			return completarEstadisticaMeses(list, fechaInicio, fechaFin);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Resumen en un periodo de un Procedimiento.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param	idProcedimiento	Identificador de un procedimiento.
	 * @param	fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @return Devuelve <code>List</code> de estadísticas en un rango de fechas de un procedimiento.
	 */
	public List listarEstadisticasProcedimiento(Long idProcedimiento, Date fechaInicio, Date fechaFin)
	{
		Session session = getSession();
		try {
			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, idProcedimiento);
			Historico historico = getHistorico(session, procedimiento);
			Criteria criterio = session.createCriteria(Estadistica.class);
			criterio.add(Expression.eq("historico.id", historico.getId()));
			criterio.add(Expression.between("fecha", fechaInicio, fechaFin));
			criterio.addOrder(Order.asc("fecha"));
			List list = criterio.list();
			
			return completarEstadisticaMeses(list, fechaInicio, fechaFin);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Resumen en un periodo de una Ficha.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idFicha	Identificador de una ficha
	 * @param fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @param idMat	Indica el identificador de la materia.
	 * @param idUA	Identificador de una unidad administrativa.
	 * @return Devuelve <code>List</code> de las estadísticas en un rango de fechas para una ficha.
	 */
	public List listarEstadisticasFicha(Long idFicha, Date fechaInicio, Date fechaFin, Long idMat, Long idUA)
	{
		Session session = getSession();
		try {
			Ficha ficha = (Ficha) session.load(Ficha.class, idFicha);
			Historico historico = null;
			if (idMat != null) {
				historico = getHistoricoFichaPorMateria(session, ficha, idMat);
			} else if (idUA != null) {
				historico = getHistoricoFichaPorUA(session, ficha, idUA);
			} else {
				historico = getHistorico(session, ficha);
			}
			
			Criteria criterio = session.createCriteria(Estadistica.class);
			criterio.add(Expression.eq("historico.id", historico.getId()));
			criterio.add(Expression.between("fecha", fechaInicio, fechaFin));
			criterio.addOrder(Order.asc("fecha"));
			List list = criterio.list();
			
			return completarEstadisticaMeses(list, fechaInicio, fechaFin);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Resumen en un periodo de una Materia.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idMateria	Identificador de una materia.
	 * @param fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @return Devuelve <code>List</code> de estadísticas en un rango de fechas para una materia
	 */
	public List listarEstadisticasMateria(Long idMateria, Date fechaInicio, Date fechaFin)
	{
		Session session = getSession();
		try {
			Materia mat = (Materia) session.load(Materia.class, idMateria);
			Historico historico = getHistorico(session, mat);
			Criteria criterio = session.createCriteria(Estadistica.class);
			criterio.add(Expression.eq("historico.id", historico.getId()));
			criterio.add(Expression.between("fecha", fechaInicio, fechaFin));
			criterio.addOrder(Order.asc("fecha"));
			List list = criterio.list();
			
			return completarEstadisticaMeses(list, fechaInicio, fechaFin);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	private List completarEstadisticaMeses(List original, Date fechaInicio, Date fechaFin)
	{
		List periodos = PeriodoUtil.crearListaMeses(fechaInicio, fechaFin);
		List result = new ArrayList(periodos.size());
		for (int i = 0; i < periodos.size(); i++) {
			Periodo periodo = (Periodo) periodos.get(i);
			for (int j = 0; j < original.size(); j++) {
				Estadistica estadistica = (Estadistica) original.get(j);
				if (periodo.contains(estadistica.getFecha())) {
					result.add(estadistica);
				}
			}
			
			if (result.size() <= i) {
				Estadistica estadistica = new Estadistica();
				estadistica.setFecha(periodo.getFechaInicio());
				estadistica.setContador(0);
				result.add(estadistica);
			}
		}
		
		return result;
	}
	
	
	private List<Estadistica> completarEstadisticaMesesListaUA(List<Estadistica> listaEstadisticas, Date fechaInicio, Date fechaFin)
	{
		List<Periodo> periodos = PeriodoUtil.crearListaMeses(fechaInicio, fechaFin);
		List<Estadistica> result = new ArrayList<Estadistica>(periodos.size());
		for (int i = 0; i < periodos.size(); i++) {
			Periodo periodo = (Periodo) periodos.get(i);
			Estadistica est = new Estadistica();
			for (int j = 0; j < listaEstadisticas.size(); j++) {
				Estadistica estadistica = (Estadistica) listaEstadisticas.get(j);
				if (periodo.contains( estadistica.getFecha())) {
					est.setContador(est.getContador() + estadistica.getContador());
				}
			}
			est.setFecha(periodo.getFechaInicio());
			result.add(est);
			
			if (result.size() <= i) {
				Estadistica estadistica = new Estadistica();
				estadistica.setFecha(periodo.getFechaInicio());
				estadistica.setContador(0);
				result.add(estadistica);
			}
		}
		
		return result;
	}
	
	
	/** 
	 * Obtiene el historico de una ficha con una determinada materia
	 * 
	 * @param session	Indica una sesión de hibernate.
	 * @param ficha	Indica la ficha relacionada al histórico solicitado.
	 * @param idmat	Identificador de una materia.
	 * @return Devuelve <code>Historico</code> solicitado.
	 * @throws HibernateException
	 */
	private Historico getHistoricoFichaPorMateria(Session session, Ficha ficha, Long idmat) throws HibernateException
	{
		HistoricoFicha hficha;
		Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id=:ficha_id and hficha.materia.id=:mat_id");
		query.setParameter("ficha_id", ficha.getId(), Hibernate.LONG);
		query.setParameter("mat_id", idmat, Hibernate.LONG);
		query.setCacheable(true);
		
		List hfichas = query.list();
		if (hfichas.isEmpty()) {
			hficha = new HistoricoFicha();
			hficha.setFicha(ficha);
			Materia mat = new Materia();
			mat.setId(idmat);
			hficha.setMateria(mat);
			hficha.setNombre(((TraduccionFicha) ficha.getTraduccion()).getTitulo());
			session.save(hficha);
			session.flush();
			
		} else {
			hficha = (HistoricoFicha) hfichas.get(0);
		}
		
		return hficha;
	}
	
	
	/**
	 * Obtiene el histórico de una ficha asignada a una determinada unidad administrativa.
	 * 
	 * @param session	Indica una sesión de hibernate.
	 * @param ficha	Indica la ficha del histórico.
	 * @param idUA	Identificador de una unidad administrativa.
	 * @return Devuelve <code>Historico</code> solicitado.
	 * @throws HibernateException
	 */
	private Historico getHistoricoFichaPorUA(Session session, Ficha ficha, Long idUA) throws HibernateException
	{
		HistoricoFicha hficha;
		Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :ficha_id and hficha.ua.id = :ua_id");
		query.setParameter("ficha_id", ficha.getId(), Hibernate.LONG);
		query.setParameter("ua_id", idUA, Hibernate.LONG);
		query.setCacheable(true);
		
		List hfichas = query.list();
		if (hfichas.isEmpty()) {
			hficha = new HistoricoFicha();
			hficha.setFicha(ficha);
			UnidadAdministrativa ua = new UnidadAdministrativa();
			ua.setId(idUA);
			hficha.setUa(ua);
			hficha.setNombre(((TraduccionFicha) ficha.getTraduccion()).getTitulo());
			session.save(hficha);
			session.flush();
			
		} else {
			hficha = (HistoricoFicha) hfichas.get(0);
		}
		
		return hficha;
	}
	
	
	/**
	 * Lista las ultimas modificaciones para Procedimientos, Normativas y Fichas.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param fechaInicio	Indica la fecha de inicio del resumen.
	 * @param fechaFin	Indica la fecha final del resumen.
	 * @param numeroRegistros	Indica el número máximo de resultados que puede devolver la consulta.
	 * @param listaUnidadAdministrativaId	Listado de identificadores de unidades administrativas.
	 * @return Devuelve <code>List<FechaHistoricoDTO></code> de las últimas modificaciones de procedimientos, normativas, y fichas.
	 */
	public List<FechaHistoricoDTO> listarUltimasModificaciones(Date fechaInicio, Date fechaFin, Integer numeroRegistros, List<Long> listaUnidadAdministrativaId)
	{
		Session session = getSession();
		try {
			if (listaUnidadAdministrativaId.isEmpty()) {
				return new Vector<FechaHistoricoDTO>();
			}
			
			String userName = getUsuario(session).getUsername();
			String clausulaUsuari = " and ( a.usuario like :usuari ) ";
			
			if (userIsSuper()) {
				clausulaUsuari = "";
			}
			
			// Lanzamos 3 query porque una solo hacia que el tiempo de respuesta fuese exponencial
			Query queryProcedimiento = null;
			Query queryNormativa = null;
			Query queryFicha = null;
			
			/* Consulta de auditorías de procedimientos */
			queryProcedimiento = session.createQuery("select a.fecha, hp " +
													"from HistoricoProcedimiento as hp, " +
													"	   Auditoria as a, " +
													"	   ProcedimientoLocal as pl " +
													"where ( hp.id = a.historico.id ) " +
													"	and ( hp.procedimiento.id = pl.id )" +
													"	and ( a.codigoOperacion = :codOperacion ) " +
													"	and ( pl.unidadAdministrativa.id in (:idUA) ) " +
													"	and ( a.fecha between :fechaInicio and :fechaFin ) " +
													clausulaUsuari +
													"order by a.fecha desc");
			
			queryProcedimiento.setParameter("codOperacion", Auditoria.MODIFICAR);
			queryProcedimiento.setParameterList("idUA", listaUnidadAdministrativaId);
			queryProcedimiento.setParameter("fechaInicio", fechaInicio);
			queryProcedimiento.setParameter("fechaFin", fechaFin);
			
			if (!"".equals(clausulaUsuari)) {
				queryProcedimiento.setParameter("usuari", userName);
			}
			
			queryProcedimiento.setMaxResults(numeroRegistros);
			/* Consulta de auditorías de normativas */
			queryNormativa = session.createQuery("select a.fecha, hn " +
												"from HistoricoNormativa as hn, " +
												"	   Auditoria as a, " +
												"	   NormativaLocal as nl " +
												"where ( hn.id = a.historico.id ) " +
												"	and ( hn.normativa.id = nl.id )" +
												"	and ( a.codigoOperacion = :codOperacion ) " +
												"	and ( nl.unidadAdministrativa.id in (:idUA) ) " +
												"	and ( a.fecha between :fechaInicio and :fechaFin ) " +
												clausulaUsuari +
												"order by a.fecha desc");
			
			queryNormativa.setParameter("codOperacion", Auditoria.MODIFICAR);
			queryNormativa.setParameterList("idUA", listaUnidadAdministrativaId);
			queryNormativa.setParameter("fechaInicio", fechaInicio);
			queryNormativa.setParameter("fechaFin", fechaFin);
			
			if (!"".equals(clausulaUsuari)) {
				queryNormativa.setParameter("usuari", userName);
			}
			
			queryNormativa.setMaxResults(numeroRegistros);
			/* Consulta de auditorías de fichas informativas */
			queryFicha = session.createQuery("select a.fecha, hf " +
											"from HistoricoFicha as hf, " +
											"	   Auditoria as a, " +
											"	   Ficha as f," +
											"     FichaUA as fua " +
											"where ( hf.id = a.historico.id ) " +
											"	and ( hf.ficha.id = f.id ) " +
											"	and ( fua.ficha.id = f.id ) " +
											"	and ( a.codigoOperacion = :codOperacion ) " +
											"	and ( fua.unidadAdministrativa.id in (:idUA) ) " +
											"	and ( a.fecha between :fechaInicio and :fechaFin ) " +
											clausulaUsuari +
											"order by a.fecha desc");
			
			queryFicha.setParameter("codOperacion", Auditoria.MODIFICAR);
			queryFicha.setParameterList("idUA", listaUnidadAdministrativaId);
			queryFicha.setParameter("fechaInicio", fechaInicio);
			queryFicha.setParameter("fechaFin", fechaFin);
			
			if (!"".equals(clausulaUsuari)) {
				queryFicha.setParameter("usuari", userName);
			}
			queryFicha.setMaxResults(numeroRegistros);
			List<FechaHistoricoDTO> historicoOrdenado = ordenarLista(queryProcedimiento, queryNormativa, queryFicha, numeroRegistros);
			return historicoOrdenado;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista las ultimas modificaciones para Procedimientos, Normativas y Fichas.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param	fechaInicio	Indica la fecha de inicio del resumen de las últimas modificaciones.
	 * @param	fechaFin	Indica la fecha de finalización del resumen de las últimas modificaciones.
	 * @param	tipoOperacion	Filtro que indica el tipo de operación de la auditoría.
	 * @param	listaUnidadAdministrativaId	Listado de idetnficadores de unidades administrativas.
	 * @return Devuelve <code>List<Integer></code> de las últimas modificaciones para procedimientos, normativas y fichas.
	 */
	public List<Integer> resumenOperativa(Date fechaInicio, Date fechaFin, Integer tipoOperacion, List<Long> listaUnidadAdministrativaId)
	{
		Session session = getSession();
		try {
			String userName = getUsuario(session).getUsername();
			String clausulaUsuari = " and a.usuario=:usuari ";
			
			if (userIsSuper()) {
				clausulaUsuari = "";
			}
			
			List<Integer> valores = new ArrayList<Integer>();
			
			Query queryProcedimiento = null;
			Query queryNormativa = null;
			Query queryFicha = null;
			
			if (listaUnidadAdministrativaId.size() > 0) {
				queryProcedimiento = session.createQuery(
						"select count(distinct h) from Historico as h, " +
						"					  Auditoria as a, " +
						"					  ProcedimientoLocal as plo " +
						"where h.id = a.historico.id " +
						"	and h.class = HistoricoProcedimiento " +
						"	and a.fecha between :fechaInicio and :fechaFin " +
						"	and a.codigoOperacion = :tipoOperacion " +
						clausulaUsuari +
						" 	and plo.unidadAdministrativa.id in (:lId) ");
				
				queryProcedimiento.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
				queryProcedimiento.setParameter("fechaFin", fechaFin, Hibernate.DATE);
				queryProcedimiento.setInteger("tipoOperacion", tipoOperacion);
				
				if (!"".equals(clausulaUsuari)) {
					queryProcedimiento.setParameter("usuari", userName);
				}
				
				queryProcedimiento.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
				
				queryNormativa = session.createQuery(
						"select count(distinct h) from Historico as h, " +
						"					  Auditoria as a, " +
						"					  NormativaLocal as nlo " +
						"where h.id = a.historico.id " +
						"	and h.class = HistoricoNormativa " +
						"	and a.fecha between :fechaInicio and :fechaFin " +
						"	and a.codigoOperacion = :tipoOperacion " +
						clausulaUsuari +
						"	and nlo.unidadAdministrativa.id in (:lId) ");
				
				queryNormativa.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
				queryNormativa.setParameter("fechaFin", fechaFin, Hibernate.DATE);
				queryNormativa.setInteger("tipoOperacion", tipoOperacion);
				
				if (!"".equals(clausulaUsuari)) {
					queryNormativa.setParameter("usuari", userName);
				}
				
				queryNormativa.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
				
				queryFicha = session.createQuery(
						"select count(distinct h) " +
						"from Historico as h, " +
						"	  Auditoria as a, " +
						"	  Ficha as fic, " +
						"	  FichaUA as fua " +
						" where h.id = a.historico.id " +
						"	and h.class = HistoricoFicha " +						
						" 	and fua.ficha.id = fic.id " +
						" 	and a.fecha between :fechaInicio and :fechaFin " +
						"	and a.codigoOperacion = :tipoOperacion " +
						clausulaUsuari +
						" 	and fua.unidadAdministrativa.id in (:lId) ");
				
				queryFicha.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
				queryFicha.setParameter("fechaFin", fechaFin, Hibernate.DATE);
				queryFicha.setInteger("tipoOperacion", tipoOperacion);
				
				if (!"".equals(clausulaUsuari)) {
					queryFicha.setParameter("usuari", userName);
				}
				queryFicha.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
				
			} else {
				queryProcedimiento = session.createQuery(
						"select count(distinct h) " +
						"from Historico as h, " +
						"	  Auditoria as a " +
						"where h.id = a.historico.id " +
						"	and h.class in (HistoricoProcedimiento) " +
						clausulaUsuari +
						"	and a.fecha between :fechaInicio and :fechaFin " +
						"	and a.codigoOperacion = :tipoOperacion ");
				
				if (!"".equals(clausulaUsuari)) {
					queryProcedimiento.setParameter("usuari", userName);
				}
				
				queryProcedimiento.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
				queryProcedimiento.setParameter("fechaFin", fechaFin, Hibernate.DATE);
				queryProcedimiento.setInteger("tipoOperacion", tipoOperacion);
				queryNormativa = session.createQuery(
						"select count(distinct h) " +
						"from Historico as h, " +
						"	  Auditoria as a " +
						"where h.id = a.historico.id " +
						"	and h.class in (HistoricoNormativa) " +
						clausulaUsuari +
						"	and a.fecha between :fechaInicio and :fechaFin " +
						"	and a.codigoOperacion = :tipoOperacion ");
				
				if (!"".equals(clausulaUsuari)) {
					queryNormativa.setParameter("usuari", userName);
				}
				queryNormativa.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
				queryNormativa.setParameter("fechaFin", fechaFin, Hibernate.DATE);
				queryNormativa.setInteger("tipoOperacion", tipoOperacion);
				
				queryFicha = session.createQuery(
						"select count(distinct h) " +
						"from Historico as h, " +
						"	  Auditoria as a " +
						"where h.id = a.historico.id " +
						"	and h.class in (HistoricoFicha) " +
						clausulaUsuari +
						"	and a.fecha between :fechaInicio and :fechaFin " +
						"	and a.codigoOperacion = :tipoOperacion ");
				
				if (!"".equals(clausulaUsuari)) {
					queryFicha.setParameter("usuari", userName);
				}
				
				queryFicha.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
				queryFicha.setParameter("fechaFin", fechaFin, Hibernate.DATE);
				queryFicha.setInteger("tipoOperacion", tipoOperacion);
			}
			
			valores.add((Integer) queryProcedimiento.uniqueResult());
			valores.add((Integer) queryNormativa.uniqueResult());
			valores.add((Integer) queryFicha.uniqueResult());
			
			return valores;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Ordena la lista.
	 * 
	 * @param queryProcedimiento	Indica la consulta de procedimientos.
	 * @param queryNormativa	Indica la consulta de normativas.
	 * @param queryFicha	Indica la consulta de fichas.
	 * @return	Devuelve <code>List<FechaHistoricoDTO></code> solicitada.
	 * @throws HibernateException
	 */
	private List<FechaHistoricoDTO> ordenarLista(Query queryProcedimiento, Query queryNormativa, Query queryFicha, Integer numeroRegistros) throws HibernateException
	{
		//Tratamiento de la query
		//TODO  02/08/2013: Revisar la ordenación
		List<Object[]> lQueryProcedimiento = queryProcedimiento.list();
		List<Object[]> lQueryNormativa = queryNormativa.list();
		List<Object[]> lQueryFicha = queryFicha.list();
		
		Map<Date, Object> historico = new HashMap<Date, Object>();
		
		for (Object[] obj : lQueryProcedimiento) {
			historico.put((Timestamp) obj[0], obj[1]);
		}
		
		for (Object[] obj : lQueryNormativa) {
			historico.put((Timestamp) obj[0], obj[1]);
		}
		
		for (Object[] obj : lQueryFicha) {
			historico.put((Timestamp) obj[0], obj[1]);
		}
		
		Object[] key = historico.keySet().toArray();
		Arrays.sort(key, new Comparator() {
			public int compare(Object o1, Object o2) {
				
				Timestamp primeraFecha = (Timestamp) o1;
				Timestamp segundaFecha = (Timestamp) o2;
				return segundaFecha.compareTo(primeraFecha);
			}
		});
		
		Vector<FechaHistoricoDTO> historicoOrdenado = new Vector<FechaHistoricoDTO>();
		int numReg = numeroRegistros;
		if (key.length < numeroRegistros) {
			numReg = key.length;
		}
		
		for (int i = 0 ; i < numReg ; i++) {
			historicoOrdenado.add(new FechaHistoricoDTO((Timestamp) key[i] , (Historico) historico.get(key[i])));
		}
		
		return historicoOrdenado;
	}
	
}
