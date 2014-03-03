package org.ibit.rol.sac.persistence.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traducible;

/**
 * Intercepta las llamadas a métodos y a�ade logs.
 */
public class LogInterceptor implements MethodInterceptor, Serializable {

    private transient Log log;
    private final String name;

    public LogInterceptor(String name) {
        this.name = name;
    }

    private Log getLog() {
        if (log == null) {
            log = LogFactory.getLog(name);
        }
        return log;
    }

    private boolean invokeSuper(Object obj, Method method, Object[] params) {
        String className = method.getDeclaringClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1, className.length());
        //getLog().info("-> " + className + "." + method.getName() + Arrays.asList(params));
        getLog().debug("-> " + className + "." + method.getName() + Arrays.asList(params));
        return true;
    }

    private Object afterReturn(Object obj, Method method, Object[] params, boolean b, Object result, Throwable excepcion) throws Throwable {
        if (excepcion != null) {
            throw excepcion.fillInStackTrace();
        }

        String logText = "<- ";
        if (result != null) {
            if (result instanceof Collection) {
                logText += " (" + ((Collection) result).size() + ") " + result;
            } else if (result.getClass().isArray()) {
                Object[] objects = (Object[]) result;
                logText += " (" + objects.length + ") " + Arrays.asList(objects);
            } else {
                logText += result;

                if (result instanceof Traducible) {
                    Traducible traducible = ((Traducible) result);
                    logText += "[traduccions:" + traducible.getTraduccionMap() + "]";
                }
            }
        }


        //getLog().info(logText);
        getLog().debug(logText);


        return result;
    }

    public Object intercept(Object obj, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        boolean llamarSuper = invokeSuper(obj, method, params);
        Object result = null;
        Throwable exception = null;

        if (llamarSuper) {
            try {
                result = methodProxy.invokeSuper(obj, params);
            } catch (Throwable throwable) {
                exception = throwable;
            }
        }

        return afterReturn(obj, method, params, llamarSuper, result, exception);
    }

}
