package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializerFactory;
import org.jboss.axis.encoding.ser.BaseSerializerFactory;
import org.jboss.net.jmx.adaptor.ObjectNameSer;

/**
 * Clase para sobreescribir ObjectInstanceSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisObjectNameSerFactory extends BaseSerializerFactory implements SerializerFactory {

    private static final long serialVersionUID = 4026090030403522354L;

    public AxisObjectNameSerFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(ObjectNameSer.class, xmlType, javaType);
    }
    
}