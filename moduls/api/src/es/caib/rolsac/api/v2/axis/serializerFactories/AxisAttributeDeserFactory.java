package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.AttributeDeser;

/**
 * Clase para sobreescribir MBeanConstructorInfoSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisAttributeDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {

    private static final long serialVersionUID = -3115154111376862094L;

    public AxisAttributeDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(AttributeDeser.class, xmlType, javaType);
    }
    
}