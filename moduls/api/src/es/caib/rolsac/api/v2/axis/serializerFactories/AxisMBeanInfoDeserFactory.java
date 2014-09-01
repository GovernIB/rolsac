package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.MBeanInfoDeser;

/**
 * Clase para sobreescribir MBeanConstructorInfoDeserFactory de forma que 
 * implemente org.apache.axis.encoding.DeserializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanInfoDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {
    
    private static final long serialVersionUID = 16766588158639996L;

    public AxisMBeanInfoDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanInfoDeser.class, xmlType, javaType);
    }
    
}