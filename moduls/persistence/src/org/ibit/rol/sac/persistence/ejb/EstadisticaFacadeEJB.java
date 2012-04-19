package org.ibit.rol.sac.persistence.ejb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import net.sf.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Estadistica;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoFicha;
import org.ibit.rol.sac.model.HistoricoNormativa;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
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
public abstract class EstadisticaFacadeEJB extends HibernateEJB {

    protected static Log log = LogFactory.getLog(EstadisticaFacadeEJB.class);

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza una Estadistica para una unidadAdministrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaUnidadAdministrativa(Long unidad_id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaNormativa(Long norm_id) {
        Session session = getSession();
        try {
            Normativa normativa = (Normativa) session.load(Normativa.class, norm_id);
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaProcedimiento(Long proc_id) {
        Session session = getSession();
        try {
            ProcedimientoLocal proc = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
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
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaFicha(Long ficha_id) {
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
     * Crea o actualiza una Estadistica para una Materia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaMateria(Long materia_id) {
        Session session = getSession();
        try {
            Materia mat = (Materia) session.load(Materia.class, materia_id);
            Periodo periodo = PeriodoUtil.crearPeriodoMes();
            Historico historico = getHistorico(session, mat);
            grabarEstadistica(session, historico, periodo);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    private void grabarEstadistica(Session session, Historico historico, Periodo periodo) throws HibernateException {
        Query query = session.createQuery("from Estadistica as est where est.historico = :historico " +
                "and est.fecha between :fecha_inicio and :fecha_fin");
        query.setEntity("historico", historico);
        query.setDate("fecha_inicio", periodo.getFechaInicio());
        query.setDate("fecha_fin", periodo.getFechaFin());
        query.setMaxResults(1);
        query.setCacheable(true);
        List result = query.list();
        /*
        Criteria criterio = session.createCriteria(Estadistica.class);
        criterio.add(Expression.eq("historico", historico));
        criterio.add(Expression.between("fecha", periodo.getFechaInicio(), periodo.getFechaFin()));
        List result = criterio.list();
        */

        if (result.isEmpty()) {
            //log.debug("Creant nova estadistica per " + historico.getId());
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
     * Resumen en un periodo de una unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEstadisticasUnidad(Long unidad_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
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
     * Resumen en un periodo del arbol de una unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */    
    public List<Estadistica> listarEstadisticasListaUnidadAdministrativaId(List<Long> listaUnidadAdministrativaId, Date fechaInicio, Date fechaFin) {
    	Session session = getSession();
    	List<Estadistica> list;
		try {
        	Query query = null;
        	query = session.createQuery("select est from Estadistica est, HistoricoUA hua where " +
        			" est.historico.id = hua.id " +
        			" and est.fecha between :fechaIni and :fechaFin" +
        			" and hua.ua.id in (:lId)" +
        			"order by est.fecha asc ");
        	query.setDate("fechaIni", fechaInicio);
        	query.setDate("fechaFin", fechaFin);
        	query.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        	
        	list = query.list();	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
            
       return completarEstadisticaMesesListaUA(list, fechaInicio, fechaFin);
        
    }
    

    /**
     * Resumen en un periodo de una Normativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEstadisticasNormativa(Long norm_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
            Normativa normativa = (Normativa) session.load(Normativa.class, norm_id);
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
     * Resumen en un periodo de un Procedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEstadisticasProcedimiento(Long proc_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
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
     * Resumen en un periodo de una Ficha
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEstadisticasFicha(Long ficha_id, Date fechaInicio, Date fechaFin, Long idMat, Long idUA) {
        Session session = getSession();
        try {
            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
            Historico historico = null;
            
            if (idMat!=null) 		historico = getHistoricoFichaPorMateria(session, ficha, idMat);
            else if (idUA!=null) 	historico = getHistoricoFichaPorUA(session, ficha, idUA);
            else 				 	historico = getHistorico(session, ficha);
            
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
     * Resumen en un periodo de una Ficha
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarEstadisticasMateria(Long mat_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
        	Materia mat = (Materia) session.load(Materia.class, mat_id);
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
    
    /**
     * Normativas más visitadas en un periodo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarNormativasMasVisitadas(Long unidad_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
            Object[] params = {fechaInicio, fechaFin};
            Type[] tipos = {Hibernate.DATE, Hibernate.DATE};
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
            Hibernate.initialize(unidad.getNormativas());
            Set normativas = unidad.getNormativas();
            List normativasFin = new Vector();
            List result = session.find("select h.id from Estadistica as e, e.historico  as h where h.class = HistoricoNormativa and e.fecha between ? and ? group by h.id order by sum(e.contador)", params, tipos);
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                Long id_historico = (Long) iter.next();
                HistoricoNormativa historico = (HistoricoNormativa) session.load(HistoricoNormativa.class, id_historico);
                if (normativas.contains(historico.getNormativa())) {
                    normativasFin.add(historico.getNormativa());
                }
            }
            return normativasFin;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Procedimientos más visitados en un periodo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarProcedimientosMasVisitados(Long unidad_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
            Object[] params = {fechaInicio, fechaFin};
            Type[] tipos = {Hibernate.DATE, Hibernate.DATE};
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
            Hibernate.initialize(unidad.getProcedimientos());
            Set procedimientos = unidad.getProcedimientos();
            List procedimientosFin = new Vector();
            List result = session.find("select h.id from Estadistica as e, e.historico  as h where h.class = HistoricoProcedimiento and e.fecha between ? and ? group by h.id order by sum(e.contador)", params, tipos);
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                Long id_historico = (Long) iter.next();
                HistoricoProcedimiento historico = (HistoricoProcedimiento) session.load(HistoricoProcedimiento.class, id_historico);
                if (procedimientos.contains(historico.getProcedimiento())) {
                    procedimientosFin.add(historico.getProcedimiento());
                }
            }
            return procedimientosFin;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Fichas más visitadas en un periodo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarFichasMasVisitadas(Long unidad_id, Date fechaInicio, Date fechaFin) {
        Session session = getSession();
        try {
            List fichas = new Vector();
            List fichasFin = new Vector();
            Object[] params = {fechaInicio, fechaFin};
            Type[] tipos = {Hibernate.DATE, Hibernate.DATE};
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
            Hibernate.initialize(unidad.getFichasUA());
            for (Iterator iter = unidad.getFichasUA().iterator(); iter.hasNext();) {
                FichaUA fichaUa = (FichaUA) iter.next();
                fichas.add(fichaUa.getFicha());
            }
            List result = session.find("select h.id from Estadistica as e, e.historico  as h where h.class = HistoricoFicha and e.fecha between ? and ? group by h.id order by sum(e.contador)", params, tipos);
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                Long id_historico = (Long) iter.next();
                HistoricoFicha historico = (HistoricoFicha) session.load(HistoricoFicha.class, id_historico);
                if (fichas.contains(historico.getFicha())) {
                    fichasFin.add(historico.getFicha());
                }
            }
            return fichasFin;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    private List completarEstadisticaMeses(List original, Date fechaInicio, Date fechaFin) {
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
    
    
    private List<Estadistica> completarEstadisticaMesesListaUA(List<Estadistica> listaEstadisticas, Date fechaInicio, Date fechaFin) {
        List<Periodo> periodos = PeriodoUtil.crearListaMeses(fechaInicio, fechaFin);
        List<Estadistica> result = new ArrayList<Estadistica>(periodos.size());
        
        for (int i = 0; i < periodos.size(); i++) {
            Periodo periodo = (Periodo) periodos.get(i);
            Estadistica est = new Estadistica();
            for (int j = 0; j < listaEstadisticas.size(); j++) {
                Estadistica estadistica = (Estadistica) listaEstadisticas.get(j);
                if (periodo.contains(estadistica.getFecha())) {
                	est.setContador(est.getContador()+ estadistica.getContador());
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
     * Crea o actualiza una Estadistica para una Ficha segun la materia donde
     * estaba.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaFichaPorMateria(Long ficha_id, Long idMat) {
        Session session = getSession();
        try {
        	if (ficha_id!=null && ficha_id.longValue()!=0 && idMat!=null && idMat.longValue()!=0) {
        		Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
	            Periodo periodo = PeriodoUtil.crearPeriodoMes();
	            //Historico historico = getHistorico(session, ficha);
	            Historico historico = getHistoricoFichaPorMateria(session, ficha, idMat);
	            grabarEstadistica(session, historico, periodo);
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Crea o actualiza una Estadistica para una Ficha segun la UA donde
     * estaba.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void grabarEstadisticaFichaPorUA(Long ficha_id, Long idUA) {
        Session session = getSession();
        try {
        	if (ficha_id!=null && ficha_id.longValue()!=0 && idUA!=null && idUA.longValue()!=0) {
	            Ficha ficha = (Ficha) session.load(Ficha.class, ficha_id);
	            
	            boolean UA_ok = true;
	            
	            try {
	            	UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);	            	
	            } catch (Exception e) {
	            	UA_ok = false;
	            }
	            
	            if (UA_ok) {
		            Periodo periodo = PeriodoUtil.crearPeriodoMes();
		            //Historico historico = getHistorico(session, ficha);
		            Historico historico = getHistoricoFichaPorUA(session, ficha, idUA);
		            grabarEstadistica(session, historico, periodo);
		        }
        	}
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    private Historico getHistoricoFichaPorMateria(Session session, Ficha ficha, Long idmat) throws HibernateException {
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

    private Historico getHistoricoFichaPorUA(Session session, Ficha ficha, Long idUA) throws HibernateException {
		HistoricoFicha hficha;
		Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id=:ficha_id and hficha.ua.id=:ua_id");
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
     * Lista las ultimas modificaciones para Procedimientos, Normativas y Fichas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Map<Timestamp, Object> listarUltimasModificaciones(Date fechaInicio, Date fechaFin, Integer numeroRegistros, List<Long> listaUnidadAdministrativaId) {
        Session session = getSession();
        try {
        	
        	UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        	if (listaUnidadAdministrativaId.size() > 0) {
        		
        		// Lanzamos 3 query porque una solo hacia que el tiempo de respuesta fuese exponencial
        		Query queryProcedimiento = null;
            	Query queryNormativa = null;
            	Query queryFicha = null;
        		
            	
        		queryProcedimiento = session.createQuery("select a.fecha, h from Historico as h, Auditoria as a, ProcedimientoLocal as plo " +
            			"where h.id=a.historico.id and h.class = HistoricoProcedimiento " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion=" + Auditoria.MODIFICAR +
            			" and plo.unidadAdministrativa.id in (:lId) " +
            			" order by a.fecha desc");
        		queryProcedimiento.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryProcedimiento.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryProcedimiento.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        		queryProcedimiento.setMaxResults(numeroRegistros);
        		
        		queryNormativa = session.createQuery("select a.fecha, h from Historico as h, Auditoria as a, NormativaLocal as nlo " +
        			"where h.id=a.historico.id and h.class = HistoricoNormativa " +
        			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion=" + Auditoria.MODIFICAR +
        			" and nlo.unidadAdministrativa.id in (:lId) " +
        			" order by a.fecha desc");
        		queryNormativa.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryNormativa.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryNormativa.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        		queryNormativa.setMaxResults(numeroRegistros);
        		
        		queryFicha = session.createQuery("select a.fecha, h from Historico as h, Auditoria as a, Ficha as fic, FichaUA as fua " +
            			"where h.id=a.historico.id and h.class = HistoricoFicha " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion=" + Auditoria.MODIFICAR +
            			" and fua.ficha.id = fic.id and fua.unidadAdministrativa.id in (:lId) "  +
            			" order by a.fecha desc");
        		queryFicha.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryFicha.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryFicha.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        		queryFicha.setMaxResults(numeroRegistros);
        		
        		
        		Map<Timestamp, Object> historicoOrdenado = ordenarLista(queryProcedimiento, queryNormativa, queryFicha, numeroRegistros);
        		
        		return historicoOrdenado;
        		
        	} else {
        		Query query = null;
        		query = session.createQuery("select a.fecha, h from Historico as h, Auditoria as a " +
            			"where h.id=a.historico.id and h.class in (HistoricoProcedimiento, HistoricoNormativa, HistoricoFicha ) " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion=" + Auditoria.MODIFICAR + 
            			" order by a.fecha desc");
        		query.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
            	query.setParameter("fechaFin", fechaFin, Hibernate.DATE);
            	query.setMaxResults(numeroRegistros);
            	//query.setCacheable(true);
            	
            	List<Object[]> lQuery = query.list();
            	Map<Timestamp, Object> historico = new HashMap<Timestamp, Object>();
        		
        		for (Object[] obj : lQuery) {
        			historico.put((Timestamp)obj[0], obj[1]);
        		}
        		
        		 return historico;
        	}
           
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Lista las ultimas modificaciones para Procedimientos, Normativas y Fichas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Integer> resumenOperativa(Date fechaInicio, Date fechaFin, Integer tipoOperacion, List<Long> listaUnidadAdministrativaId) {
        Session session = getSession();
        try {
        	List<Integer> valores = new ArrayList<Integer>();
        	
        	Query queryProcedimiento = null;
        	Query queryNormativa = null;
        	Query queryFicha = null;
        	
        	if (listaUnidadAdministrativaId.size() > 0) {
        		
        		queryProcedimiento = session.createQuery("select count(h) from Historico as h, Auditoria as a, ProcedimientoLocal as plo " +
            			"where h.id=a.historico.id and h.class = HistoricoProcedimiento " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion= :tipoOperacion " +
            			" and plo.unidadAdministrativa.id in (:lId) ");
        		queryProcedimiento.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryProcedimiento.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryProcedimiento.setInteger("tipoOperacion", tipoOperacion);
        		queryProcedimiento.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        		
        		queryNormativa = session.createQuery("select count(h) from Historico as h, Auditoria as a, NormativaLocal as nlo " +
        			"where h.id=a.historico.id and h.class = HistoricoNormativa " +
        			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion= :tipoOperacion " +
        			" and nlo.unidadAdministrativa.id in (:lId) ");
        		queryNormativa.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryNormativa.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryNormativa.setInteger("tipoOperacion", tipoOperacion);
        		queryNormativa.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        		
        		queryFicha = session.createQuery("select count(h) from Historico as h, Auditoria as a, Ficha as fic, FichaUA as fua " +
            			" where h.id=a.historico.id and h.class = HistoricoFicha " +
            			" and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion= :tipoOperacion " +
            			" and fua.ficha.id = fic.id " +
            			" and fua.unidadAdministrativa.id in (:lId) ");
        		queryFicha.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryFicha.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryFicha.setInteger("tipoOperacion", tipoOperacion);
        		queryFicha.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);
        		
        	} else {
        		queryProcedimiento = session.createQuery("select count(h) from Historico as h, Auditoria as a " +
            			"where h.id=a.historico.id and h.class in (HistoricoProcedimiento) " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion= :tipoOperacion ");
        		queryProcedimiento.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryProcedimiento.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryProcedimiento.setInteger("tipoOperacion", tipoOperacion);
            	
        		queryNormativa = session.createQuery("select count(h) from Historico as h, Auditoria as a " +
            			"where h.id=a.historico.id and h.class in (HistoricoNormativa) " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion= :tipoOperacion ");
        		queryNormativa.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryNormativa.setParameter("fechaFin", fechaFin, Hibernate.DATE);
        		queryNormativa.setInteger("tipoOperacion", tipoOperacion);
            	
        		queryFicha = session.createQuery("select count(h) from Historico as h, Auditoria as a " +
            			"where h.id=a.historico.id and h.class in (HistoricoFicha) " +
            			"and a.fecha between :fechaInicio and :fechaFin and a.codigoOperacion= :tipoOperacion ");
        		queryFicha.setParameter("fechaInicio", fechaInicio, Hibernate.DATE);
        		queryFicha.setParameter("fechaFin", fechaFin, Hibernate.DATE);
            	queryFicha.setInteger("tipoOperacion", tipoOperacion);

        	}

        	valores.add((Integer)queryProcedimiento.uniqueResult());
        	valores.add((Integer)queryNormativa.uniqueResult());
        	valores.add((Integer)queryFicha.uniqueResult());

        	return valores;
           
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    
	/**
	 * @param queryProcedimiento
	 * @param queryNormativa
	 * @param queryFicha
	 * @return
	 * @throws HibernateException
	 */
	private Map<Timestamp, Object> ordenarLista(Query queryProcedimiento, Query queryNormativa, Query queryFicha, Integer numeroRegistros) throws HibernateException {
		//Tratamiento de la query
		List<Object[]> lQueryProcedimiento = queryProcedimiento.list();
		List<Object[]> lQueryNormativa = queryNormativa.list();
		List<Object[]> lQueryFicha = queryFicha.list();
		
		Map<Date, Object> historico = new HashMap<Date, Object>();
		
		for (Object[] obj : lQueryProcedimiento) {
			historico.put((Timestamp)obj[0], obj[1]);
		}
		
		for (Object[] obj : lQueryNormativa) {
			historico.put((Timestamp)obj[0], obj[1]);
		}
		
		for (Object[] obj : lQueryFicha) {
			historico.put((Timestamp)obj[0], obj[1]);
		}
		
		Object[] key = historico.keySet().toArray();
		Arrays.sort(key);
		
		Map<Timestamp, Object> historicoOrdenado = new HashMap<Timestamp, Object>();
		
		int numReg = numeroRegistros;
		if (key.length < numeroRegistros) {
			numReg = key.length;
		}
		
		for (int i = 1; i <= numReg; i++) {
			historicoOrdenado.put((Timestamp)key[key.length-i], historico.get(key[key.length-i]));  			
		}	
		
		return historicoOrdenado;
	}

    
}
