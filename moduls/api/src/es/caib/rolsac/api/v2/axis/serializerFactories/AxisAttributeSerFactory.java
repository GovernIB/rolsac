package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializerFactory;
import org.jboss.axis.encoding.ser.BaseSerializerFactory;
import org.jboss.net.jmx.adaptor.AttributeSer;

/**
 * Clase para sobreescribir ObjectInstanceSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisAttributeSerFactory extends BaseSerializerFactory implements SerializerFactory {

    private static final long serialVersionUID = -7984204056238787037L;

    public AxisAttributeSerFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(AttributeSer.class, xmlType, javaType);
    }
    
}