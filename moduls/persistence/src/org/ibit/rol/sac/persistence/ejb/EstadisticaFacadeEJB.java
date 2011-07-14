package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import net.sf.hibernate.type.Type;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.*;

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

    
}
