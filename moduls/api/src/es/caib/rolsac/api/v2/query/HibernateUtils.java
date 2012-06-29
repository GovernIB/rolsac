package es.caib.rolsac.api.v2.query;

import java.io.File;
import java.net.URL;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HibernateUtils {

    private static Log log = LogFactory.getLog(HibernateUtils.class);

    private static final String HB_MAPPING = "moduls\\api\\etc\\hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    private HibernateUtils() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            URL url = cl.getResource("hibernate.cfg.xml");
            File f = new File(HB_MAPPING);
            try {
                // TODO: HibernateLocator configura un interceptor y una property. Revisarlo mas tarde.
                if (url == null) {
                    sessionFactory = new Configuration().configure(f).buildSessionFactory();
                } else {
                    sessionFactory = new Configuration().configure(url).buildSessionFactory();
                }
            } catch (HibernateException e) {
                log.error("Error obteniendo SessionFactory de Hibernate.", e);
            }
        }
        return sessionFactory;
    }

}
