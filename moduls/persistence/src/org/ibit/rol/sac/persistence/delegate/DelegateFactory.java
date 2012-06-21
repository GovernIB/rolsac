package org.ibit.rol.sac.persistence.delegate;

import net.sf.cglib.proxy.Enhancer;

import org.ibit.rol.sac.persistence.util.DelegateLogInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * Factoria de objetos delegate.
 */
class DelegateFactory {

    private static Map delegates = new HashMap();

    static synchronized Delegate getDelegate(Class clazz) {
        if (!Delegate.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz + " no es subclase de " + Delegate.class);
        }

        // Utilizamos un cache para reutilizar instancias cacheables.
        if (StatelessDelegate.class.isAssignableFrom(clazz)) {
            Object delegate = delegates.get(clazz);
            if (delegate == null) {
                delegate = getEnhancedInstance(clazz);
                delegates.put(clazz, delegate);
            }
            return (Delegate) delegate;
        } else {
            return (Delegate) getEnhancedInstance(clazz);
        }
    }

    private static Object getEnhancedInstance(Class clazz) {
        return Enhancer.create(clazz, new DelegateLogInterceptor("es.caib.rolsac.delegate.interceptor"));
        /*
        try {
            return clazz.newInstance();
        } catch (Throwable t) {
            return null;
        }
        */
    }

}