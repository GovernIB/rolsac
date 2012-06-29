package es.caib.rolsac.api.v2.estadistica.ejb;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Estadistica;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.PeriodoUtil;
import es.caib.rolsac.api.v2.query.HibernateUtils;

public class EstadisticaInsertServiceEJB {

    private static Log log = LogFactory.getLog(EstadisticaInsertServiceEJB.class);

    @SuppressWarnings("unchecked")
    private void grabarEstadistica(Session session, Historico historico, Periodo periodo) throws HibernateException {
        Query query = session.createQuery("from Estadistica as est where est.historico = :historico "
                + "and est.fecha between :fecha_inicio and :fecha_fin");
        query.setEntity("historico", historico);
        query.setDate("fecha_inicio", periodo.getFechaInicio());
        query.setDate("fecha_fin", periodo.getFechaFin());
        query.setMaxResults(1);
        query.setCacheable(true);
        
        List<Estadistica> result = (List<Estadistica>) query.list();
        if (result.isEmpty()) {
            Estadistica estadistica = new Estadistica();
            estadistica.setFecha(periodo.getFechaInicio());
            estadistica.setContador(1);
            estadistica.setHistorico(historico);
            session.saveOrUpdate(estadistica);
        } else {
            Estadistica estadistica = result.get(0);
            estadistica.setContador(estadistica.getContador() + 1);
        }
        session.flush();
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (fitxaId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Ficha ficha = (Ficha) session.load(Ficha.class, fitxaId);
                Historico historico = EJBUtils.getHistoric(session, ficha);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (fitxaId > 0 && materiaId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Ficha ficha = (Ficha) session.load(Ficha.class, fitxaId);
                Historico historico = EJBUtils.getHistoricFitxaPerMateria(session, ficha, materiaId);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (fitxaId > 0 && uaId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Ficha ficha = (Ficha) session.load(Ficha.class, fitxaId);
                // Provocar excepcion si no existe la UA.
                session.load(UnidadAdministrativa.class, uaId);
                Historico historico = EJBUtils.getHistoricFitxaPerUA(session, ficha, uaId);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

    public boolean gravarEstadisticaMateria(long materiaId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (materiaId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Materia materia = (Materia) session.load(Materia.class, materiaId);
                Historico historico = EJBUtils.getHistoric(session, materia);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

    public boolean gravarEstadisticaNormativa(long normativaId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (normativaId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Normativa normativa = (Normativa) session.load(Normativa.class, normativaId);
                Historico historico = EJBUtils.getHistoric(session, normativa);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

    public boolean gravarEstadisticaProcediment(Long procedimentId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (procedimentId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                ProcedimientoLocal procediment = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, procedimentId);
                Historico historico = EJBUtils.getHistoric(session, procediment);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) {
        boolean estadisticaGravada = false;
        Session session = null;
        Transaction tx = null;
        try {
            if (uaId > 0) {
                Periodo periodo = PeriodoUtil.crearPeriodoMes();
                session = HibernateUtils.getSessionFactory().openSession();
                tx = session.beginTransaction();
                UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, uaId);
                Historico historico = EJBUtils.getHistoric(session, ua);
                grabarEstadistica(session, historico, periodo);
                tx.commit();
                estadisticaGravada = true;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    log.error(e);
                    e1.printStackTrace();
                }
            }
            log.error(e);
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return estadisticaGravada;
    }

}
