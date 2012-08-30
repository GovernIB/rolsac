package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.ObjectNameDeser;

/**
 * Clase para sobreescribir ObjectInstanceDeserFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisObjectNameDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {

    private static final long serialVersionUID = -544733623057352864L;

    public AxisObjectNameDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(ObjectNameDeser.class, xmlType, javaType);
    }

}