package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.ObjectInstanceDeser;

/**
 * Clase para sobreescribir ObjectInstanceDeserFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisObjectInstanceDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {

    private static final long serialVersionUID = -7483372111154247081L;

    public AxisObjectInstanceDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(ObjectInstanceDeser.class, xmlType, javaType);
    }

}