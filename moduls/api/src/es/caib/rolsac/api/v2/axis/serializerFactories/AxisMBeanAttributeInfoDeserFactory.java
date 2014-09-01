package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.MBeanAttributeInfoDeser;

/**
 * Clase para sobreescribir MBeanConstructorInfoSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanAttributeInfoDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {

    private static final long serialVersionUID = 1652990230336675237L;

    public AxisMBeanAttributeInfoDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanAttributeInfoDeser.class, xmlType, javaType);
    }
    
}