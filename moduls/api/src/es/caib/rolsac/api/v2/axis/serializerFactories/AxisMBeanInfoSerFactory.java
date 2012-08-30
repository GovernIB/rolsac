package es.caib.rolsac.api.v2.axis.serializerFactories;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializerFactory;
import org.jboss.axis.encoding.ser.BaseSerializerFactory;
import org.jboss.net.jmx.adaptor.MBeanInfoSer;

/**
 * Clase para sobreescribir MBeanConstructorInfoSerFactory de forma que 
 * implemente org.apache.axis.encoding.SerializerFactory para que no provoque
 * un ConfigurationException relacionado con typeMappingRegistry. 
 */
public class AxisMBeanInfoSerFactory extends BaseSerializerFactory implements SerializerFactory {

    private static final long serialVersionUID = 3101787426276888290L;

    public AxisMBeanInfoSerFactory(@SuppressWarnings("rawtypes") Class javaType, QName xmlType) {
        super(MBeanInfoSer.class, xmlType, javaType);
    }
    
}