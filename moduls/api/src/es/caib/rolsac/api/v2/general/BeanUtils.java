package es.caib.rolsac.api.v2.general;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUtils {

    public static enum STRATEGY {EJB, WS};
    
    private static final String INSERT_INFIX = "InsertService";
    private static final String ADAPTER_INFIX = "QueryService";
    private static final String ADAPTER_SUFIX = "Adapter";
    private static final String SPRING_CONTEXT_CLASSPATH = "classpath*:es/caib/rolsac/api/v2/resources/sac-api-context.xml";
    
    // Uncomment for JUnit tests.
    // private static final String AXIS_CLIENT_CONFIG = "es/caib/rolsac/api/v2/resources/sac-api-client-config.wsdd";
    
    private static ApplicationContext ctx;
    
    private BeanUtils() {
    }
    
    public static ApplicationContext getApplicationContextInstance() {
        if (ctx == null) {
            
            // Uncomment for JUnit tests.
            // System.setProperty("axis.ClientConfigFile", AXIS_CLIENT_CONFIG);
            
            ctx = new ClassPathXmlApplicationContext(SPRING_CONTEXT_CLASSPATH);
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
        if (adapterPrefix.equals("estadistica")) {
        	//El nombre del adapter de estadistica, sigue un patrón diferente
            return getBean(adapterPrefix + INSERT_INFIX + strategy + ADAPTER_SUFIX, params);
        } else {
            return getBean(adapterPrefix + ADAPTER_INFIX + strategy + ADAPTER_SUFIX, params);
        }
    }

    
}
