package es.caib.rolsac.api.v2.general;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanUtils {

    public static enum STRATEGY {EJB, WS};
    
    private static final String ADAPTER_INFIX = "QueryService";
    private static final String ADAPTER_SUFIX = "Adapter";
    private static final String SPRING_XML_CONFIG = "moduls\\api\\etc\\spring.cfg.xml";
    private static ApplicationContext ctx;
    
    private BeanUtils() {
    }
    
    public static ApplicationContext getApplicationContextInstance() {
        if (ctx == null) {
            ctx = new FileSystemXmlApplicationContext(SPRING_XML_CONFIG);
        }
        return ctx;
    }

    public static Object getBean(String beanId, Object... params) {
        if (params.length > 0) {
            return getApplicationContextInstance().getBean(beanId, params);
        } else {
            return getApplicationContextInstance().getBean(beanId);
        }
    }
    
    public static Object getAdapter(String adapterPrefix, STRATEGY strategy, Object... params) {
        // return null if dto is null
        if (params.length > 0 && params[0] == null) {return null;}
        return getBean(adapterPrefix + ADAPTER_INFIX + strategy + ADAPTER_SUFIX, params);
    }

}
