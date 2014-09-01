package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.MBeanNotificationInfoDeser;

/**
 * Clase para sobreescribir MBeanConstructorInfoDeserFactory de forma que 
 * implemente org.apache.axis.encoding.DeserializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanNotificationInfoDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {
    
    private static final long serialVersionUID = 1379751641874940765L;

    public AxisMBeanNotificationInfoDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanNotificationInfoDeser.class, xmlType, javaType);
    }
    
}