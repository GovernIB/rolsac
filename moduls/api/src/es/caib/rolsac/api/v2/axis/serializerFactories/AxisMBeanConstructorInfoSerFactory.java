package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializerFactory;
import org.jboss.axis.encoding.ser.BaseSerializerFactory;
import org.jboss.net.jmx.adaptor.MBeanConstructorInfoSer;

/**
 * Clase para sobreescribir MBeanConstructorInfoSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanConstructorInfoSerFactory extends BaseSerializerFactory implements SerializerFactory {

    private static final long serialVersionUID = 6174558705705076010L;
    
    public AxisMBeanConstructorInfoSerFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanConstructorInfoSer.class, xmlType, javaType);
    }
    
}