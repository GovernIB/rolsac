package es.caib.rolsac.api.v2.general;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import net.sf.hibernate.FlushMode;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.query.HibernateUtils;

/**
 * Bean con la funcionalidad basica para interactuar con Hibernate.
 * 
 * @ejb.bean view-type="remote" generate="false"
 */
public abstract class HibernateEJB implements SessionBean {

    private static final long serialVersionUID = -425438791097314020L;
    private static Log log = LogFactory.getLog(HibernateEJB.class);

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

}
