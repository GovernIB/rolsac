package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializerFactory;
import org.jboss.axis.encoding.ser.BaseDeserializerFactory;
import org.jboss.net.jmx.adaptor.MBeanParameterInfoDeser;

/**
 * Clase para sobreescribir MBeanConstructorInfoSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanParameterInfoDeserFactory extends BaseDeserializerFactory implements DeserializerFactory {

    private static final long serialVersionUID = -4871709239317142569L;

    public AxisMBeanParameterInfoDeserFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanParameterInfoDeser.class, xmlType, javaType);
    }
    
}