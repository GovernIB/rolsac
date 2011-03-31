package org.ibit.rol.sac.persistence.util;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;

import javax.xml.namespace.QName;


public class WSUtils {


    public static void registerTypeMapping(Call call, String namespaceuri, Class clazz) {
        QName qn = new QName(namespaceuri, clazz.getSimpleName());
        call.registerTypeMapping(clazz,
                                 qn,
                                 new BeanSerializerFactory(clazz, qn),
                                 new BeanDeserializerFactory(clazz, qn));
    }
}

