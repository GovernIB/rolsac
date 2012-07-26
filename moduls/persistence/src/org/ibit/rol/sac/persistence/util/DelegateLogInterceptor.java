package org.ibit.rol.sac.persistence.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traducible;
import org.jboss.security.SecurityAssociation;

/**
 * Intercepta las llamadas a métodos y añade logs.
 */
public class DelegateLogInterceptor implements MethodInterceptor, Serializable {

    private transient Log log;
    private final String name;

    public DelegateLogInterceptor(String name) {
        this.name = name;
    }

    private Log getLog() {
        if (log == null) {
            log = LogFactory.getLog(name);
        }
        return log;
    }

    private boolean invokeSuper(Object obj, Method method, Object[] params, int count) {
        String className = method.getDeclaringClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1, className.length());
        //getLog().info("-> " + className + "." + method.getName() + Arrays.asList(params));
        getLog().debug("#"+count + " -> " + className + "." + method.getName() + Arrays.asList(params));
        Principal princ = SecurityAssociation.getPrincipal();
        return true;
    }

    private Object afterReturn(Object obj, Method method, Object[] params, boolean b, Object result, Throwable excepcion, int count) throws Throwable {
        if (excepcion != null) {
            throw excepcion.fillInStackTrace();
        }

        String logText = "<- "+"#"+count+" ";
        if (result != null) {
            if (result instanceof Collection) {
                logText += " (" + ((Collection) result).size() + ") " + result;
            } else if (result.getClass().isArray()) {
                Object[] objects = (Object[]) result;
                logText += " (" + objects.length + ") " + Arrays.asList(objects);
            } else {
                logText += result;
/*
                if (result instanceof Traducible) {
                    Traducible traducible = ((Traducible) result);
                    logText += "[traduccions:" + traducible.getTraduccionMap() + "]";
                }
*/
            }
        }


        //getLog().info(logText);
        getLog().debug(logText);


        return result;
    }

    static Integer requestCounter=1;

    public Object intercept(Object obj, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        
    	int count = 0;
    	
    	synchronized (requestCounter) {
    		count = requestCounter++;
		}
    	
        boolean llamarSuper = invokeSuper(obj, method, params, count);
        Object result = null;
        Throwable exception = null;

        if (llamarSuper) {
            try {
                result = methodProxy.invokeSuper(obj, params);
            } catch (Throwable throwable) {
                exception = throwable;
            }
        }

        return afterReturn(obj, method, params, llamarSuper, result, exception, count);
    }

}
