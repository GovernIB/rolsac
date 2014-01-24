/**
 * ButlletiCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.butlleti;

public class ButlletiCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String enlace;

    private java.lang.String nombre;

    private es.caib.rolsac.api.v2.butlleti.ButlletiOrdenacio[] ordenar;

    public ButlletiCriteria() {
    }

    public ButlletiCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String enlace,
           java.lang.String nombre,
           es.caib.rolsac.api.v2.butlleti.ButlletiOrdenacio[] ordenar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.enlace = enlace;
        this.nombre = nombre;
        this.ordenar = ordenar;
    }


    /**
     * Gets the enlace value for this ButlletiCriteria.
     * 
     * @return enlace
     */
    public java.lang.String getEnlace() {
        return enlace;
    }


    /**
     * Sets the enlace value for this ButlletiCriteria.
     * 
     * @param enlace
     */
    public void setEnlace(java.lang.String enlace) {
        this.enlace = enlace;
    }


    /**
     * Gets the nombre value for this ButlletiCriteria.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ButlletiCriteria.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the ordenar value for this ButlletiCriteria.
     * 
     * @return ordenar
     */
    public es.caib.rolsac.api.v2.butlleti.ButlletiOrdenacio[] getOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this ButlletiCriteria.
     * 
     * @param ordenar
     */
    public void setOrdenar(es.caib.rolsac.api.v2.butlleti.ButlletiOrdenacio[] ordenar) {
        this.ordenar = ordenar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ButlletiCriteria)) return false;
        ButlletiCriteria other = (ButlletiCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.enlace==null && other.getEnlace()==null) || 
             (this.enlace!=null &&
              this.enlace.equals(other.getEnlace()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.ordenar==null && other.getOrdenar()==null) || 
             (this.ordenar!=null &&
              java.util.Arrays.equals(this.ordenar, other.getOrdenar())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getEnlace() != null) {
            _hashCode += getEnlace().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getOrdenar() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrdenar());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrdenar(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ButlletiCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://butlleti.v2.api.rolsac.caib.es", "ButlletiOrdenacio"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "listaOrdenaciones"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
