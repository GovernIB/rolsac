package org.ibit.rol.sac.integracion.ws;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Remoto;

/**
 * Interceptor para llamadas axis.
 */
public final class AxisInterceptor implements MethodInterceptor {

    static {
        System.setProperty(
                "axis.socketFactory",
                "org.ibit.rol.sac.integracion.ws.AxisSocketFactory");
    }


    protected static Log log = LogFactory.getLog(AxisInterceptor.class);

    private String endPoint;
    private Class[] beanTypes;

    private static String unqualifiedClassName(Class clazz) {
        String className = clazz.getName();
        return className.substring(className.lastIndexOf('.') + 1, className.length());
    }

    private static void registerTypeMapping(Call call, Class clazz) {
        String name = unqualifiedClassName(clazz);
        //String ns = name + "Serv";
        String ns = "http://rol.ibit.org/beans";
        QName qn = new QName(ns, name);
        call.registerTypeMapping(clazz, qn,
                new BeanSerializerFactory(clazz, qn),
                new BeanDeserializerFactory(clazz, qn));
    }

    public AxisInterceptor(String endPoint, Class[] beanTypes) {
        this.endPoint = endPoint;
        this.beanTypes = beanTypes;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object ret = null;
        if (!method.getDeclaringClass().equals(Object.class)) {
            try {
                Service service = new Service();
                Call call = (Call) service.createCall();

                if (beanTypes != null) {
                    for (int i = 0; i < beanTypes.length; i++) {
                        registerTypeMapping(call, beanTypes[i]);
                    }
                }

                call.setTargetEndpointAddress(new java.net.URL(endPoint));
                QName qnOperation = new QName(unqualifiedClassName(method.getDeclaringClass()), method.getName());
                call.setOperationName(qnOperation);
                call.setTimeout(new Integer(30000));

                ret = call.invoke(objects);

                if (ret == null) {
                    log.info("Ha retornat null");
                } else {
                    log.info(ret.getClass().getName());
                }

            } catch (ServiceException e) {
                log.error("intercept", e);
            } catch (MalformedURLException e) {
                log.error("intercept", e);
            } catch (RemoteException e) {
                log.error("intercept", e);
            }
        }

        return ret;
    }
}
