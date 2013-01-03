package es.caib.rolsac.api.v2.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import net.sf.hibernate.FlushMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoFicha;
import org.ibit.rol.sac.model.HistoricoMateria;
import org.ibit.rol.sac.model.HistoricoNormativa;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.HistoricoUA;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.query.HibernateUtils;

/**
 * Bean con la funcionalidad basica para interactuar con sesiones de Hibernate y algunas utilidades mas.
 * 
 * @ejb.bean view-type="remote" generate="false"
 */
public abstract class HibernateEJB implements SessionBean {

    private static final long serialVersionUID = -425438791097314020L;
    private static Log log = LogFactory.getLog(HibernateEJB.class);

    
    // Session utils //
    
    private SessionFactory sessionFactory = null;

    protected SessionContext ctx = null;

    public void setSessionContext(SessionContext ctx) {
        this.ctx = ctx;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        sessionFactory = HibernateUtils.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.COMMIT);
            return session;
        } catch (HibernateException e) {
            throw new EJBException(e);
        }
    }

    public void close(Session session) {
        if (session != null && session.isOpen()) {
            try {
                if (session.isDirty()) {
                    log.warn("Closing dirty session!!");
                }
                session.close();
            } catch (HibernateException e) {
                log.error(e);
            }
        }
    }
    
    
    // EJB utils //
    
    public void ejbCreate() throws CreateException {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    public void ejbRemove() {
        sessionFactory = null;
    }
    
    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }
    

    // Query utils // 
    
    protected ArxiuDTO getArxiuDTO(long idFile) {
        Session session = null;
        ArxiuDTO arxiuDTO = null;
        try {
            session = getSession();
            Query query = session.createQuery("select distinct new org.ibit.rol.sac.model.Archivo(a.id, a.mime, a.nombre, a.peso) from Archivo as a where a.id = :code ");
            query.setParameter("code", idFile);
            Archivo archivo = (Archivo) query.uniqueResult();
            arxiuDTO = (ArxiuDTO) BasicUtils.entityToDTO(ArxiuDTO.class, archivo, null);
            String url = System.getProperty("es.caib.rolsac.api.v2.urlArxius");
            if (StringUtils.isBlank(url)) {
                log.error(ExceptionMessages.CONFIG_ARXIU_URL);
            }
            arxiuDTO.setUrl(url + idFile);
        } catch (HibernateException e) {
            log.error(e);
        } finally {
            close(session);
        }
        return arxiuDTO;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoric(Session session, UnidadAdministrativa ua) throws HibernateException {
        HistoricoUA hua;
        Query query = session.createQuery("from HistoricoUA as hua where hua.ua.id = :ua_id");
        query.setParameter("ua_id", ua.getId(), Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoUA> huas = (List<HistoricoUA>) query.list();
        if (huas.isEmpty()) {
            hua = new HistoricoUA();
            hua.setUa(ua);
            if (ua.getTraduccion() != null) {
                hua.setNombre(((TraduccionUA) ua.getTraduccion()).getNombre());
            } else {
                hua.setNombre(ua.getCodigoEstandar());
            }
            session.save(hua);
            session.flush();
        } else {
            hua = huas.get(0);
        }
        return hua;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoric(Session session, ProcedimientoLocal pr) throws HibernateException {
        HistoricoProcedimiento hp;
        Query query = session.createQuery("from HistoricoProcedimiento as hp where hp.procedimiento.id = :proc_id");
        query.setParameter("proc_id", pr.getId(), Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoProcedimiento> hprs = (List<HistoricoProcedimiento>) query.list();
        if (hprs.isEmpty()) {
            hp = new HistoricoProcedimiento();
            hp.setProcedimiento(pr);
            TraduccionProcedimientoLocal traduccionProcedimientoLocal = (TraduccionProcedimientoLocal) pr
                    .getTraduccion();
            if (traduccionProcedimientoLocal != null) {
                hp.setNombre(traduccionProcedimientoLocal.getNombre());
            } else {
                hp.setNombre("-");
            }
            session.save(hp);
            session.flush();
        } else {
            hp = hprs.get(0);
        }
        return hp;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoric(Session session, Normativa norm) throws HibernateException {
        HistoricoNormativa hnorm;
        Query query = session.createQuery("from HistoricoNormativa as hnorm where hnorm.normativa.id = :nor_id");
        query.setParameter("nor_id", norm.getId(), Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoNormativa> hnorms = (List<HistoricoNormativa>) query.list();
        if (hnorms.isEmpty()) {
            hnorm = new HistoricoNormativa();
            hnorm.setNormativa(norm);
            if (norm.getTraduccion() != null) {
                hnorm.setNombre(((TraduccionNormativa) norm.getTraduccion()).getTitulo());
            }
            session.save(hnorm);
            session.flush();
        } else {
            hnorm = hnorms.get(0);
        }
        return hnorm;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoric(Session session, Ficha fitxa) throws HibernateException {
        HistoricoFicha hficha;
        Query query = session
                .createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :ficha_id and hficha.materia.id is null and hficha.ua.id is null ");
        query.setParameter("ficha_id", fitxa.getId(), Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoFicha> hfichas = (List<HistoricoFicha>) query.list();
        if (hfichas.isEmpty()) {
            hficha = new HistoricoFicha();
            hficha.setFicha(fitxa);
            if (fitxa.getTraduccion() != null) {
                hficha.setNombre(((TraduccionFicha) fitxa.getTraduccion()).getTitulo());
            }
            session.save(hficha);
            session.flush();
        } else {
            hficha = hfichas.get(0);
        }
        return hficha;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoric(Session session, Materia mat) throws HibernateException {
        HistoricoMateria hmat;
        Query query = session.createQuery("from HistoricoMateria as hmat where hmat.materia.id = :materia_id");
        query.setParameter("materia_id", mat.getId(), Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoMateria> hmats = (List<HistoricoMateria>) query.list();
        if (hmats.isEmpty()) {
            hmat = new HistoricoMateria();
            hmat.setMateria(mat);
            if (mat.getTraduccion() != null) {
                hmat.setNombre(((TraduccionMateria) mat.getTraduccion()).getNombre());
            }
            session.save(hmat);
            session.flush();
        } else {
            hmat = hmats.get(0);
        }
        return hmat;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoricFitxaPerMateria(Session session, Ficha fitxa, long idmat) throws HibernateException {
        HistoricoFicha hficha;
        Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id=:ficha_id and hficha.materia.id=:mat_id");
        query.setParameter("ficha_id", fitxa.getId(), Hibernate.LONG);
        query.setParameter("mat_id", idmat, Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoFicha> hfichas = (List<HistoricoFicha>) query.list();
        if (hfichas.isEmpty()) {
            hficha = new HistoricoFicha();
            hficha.setFicha(fitxa);
            Materia mat = new Materia();
            mat.setId(idmat);
            hficha.setMateria(mat);
            if (fitxa.getTraduccion() != null) {
                hficha.setNombre(((TraduccionFicha) fitxa.getTraduccion()).getTitulo());
            }
            session.save(hficha);
            session.flush();
        } else {
            hficha = hfichas.get(0);
        }
        
        return hficha;
    }

    @SuppressWarnings("unchecked")
    protected Historico getHistoricFitxaPerUA(Session session, Ficha fitxa, long idUA) throws HibernateException {
        HistoricoFicha hficha;
        Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id=:ficha_id and hficha.ua.id=:ua_id");
        query.setParameter("ficha_id", fitxa.getId(), Hibernate.LONG);
        query.setParameter("ua_id", idUA, Hibernate.LONG);
        query.setCacheable(true);
        query.setMaxResults(1);
        List<HistoricoFicha> hfichas = (List<HistoricoFicha>) query.list();
        if (hfichas.isEmpty()) {
            hficha = new HistoricoFicha();
            hficha.setFicha(fitxa);
            UnidadAdministrativa ua = new UnidadAdministrativa();
            ua.setId(idUA);
            hficha.setUa(ua);
            if (fitxa.getTraduccion() != null) {
                hficha.setNombre(((TraduccionFicha) fitxa.getTraduccion()).getTitulo());
            }
            session.save(hficha);
            session.flush();
        } else {
            hficha = hfichas.get(0);
        }
        return hficha;
    }

    protected int getNumberResults(Query query) throws HibernateException {
        Object result = query.uniqueResult();
        if (result == null) return 0;
        return (Integer) result;
    }

    
    /**
     * Método encargado de realizar el casting de listas no tipadas a listas
     * tipadas
     * 
     * @param <T>
     * @param clazz Clase del tipo de objeto contenido en la lista
     * @param c Colección a ser tipada
     * @return Lista tipada
     */
    protected <T>List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>();
        if (c != null) {     
            r = new ArrayList<T>(c.size());            
            for (Object o : c) {
            	r.add(clazz.cast(o));
            }
        }
        return r;
    }    
    
}
