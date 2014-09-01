package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializerFactory;
import org.jboss.axis.encoding.ser.BaseSerializerFactory;
import org.jboss.net.jmx.adaptor.MBeanParameterInfoSer;

/**
 * Clase para sobreescribir MBeanConstructorInfoSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanParameterInfoSerFactory extends BaseSerializerFactory implements SerializerFactory {

    private static final long serialVersionUID = -8013668281958303597L;

    public AxisMBeanParameterInfoSerFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanParameterInfoSer.class, xmlType, javaType);
    }
    
}