package es.caib.rolsac.api.v2.query;

import java.io.File;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HibernateUtils {

    private static Log log = LogFactory.getLog(HibernateUtils.class);
    
    private static final String HB_MAPPING = "moduls\\api\\etc\\hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            File f = new File(HB_MAPPING);
            try {
                sessionFactory = new Configuration().configure(f).buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println(e); // TODO: delete me later.
                log.error(e);
            }
        }
        return sessionFactory;
    }

}
