/**
 * FitxaUACriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.fitxaUA;

public class FitxaUACriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String orden;

    private java.lang.String ordenSeccion;

    public FitxaUACriteria() {
    }

    public FitxaUACriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String orden,
           java.lang.String ordenSeccion) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.orden = orden;
        this.ordenSeccion = ordenSeccion;
    }


    /**
     * Gets the orden value for this FitxaUACriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this FitxaUACriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the ordenSeccion value for this FitxaUACriteria.
     * 
     * @return ordenSeccion
     */
    public java.lang.String getOrdenSeccion() {
        return ordenSeccion;
    }


    /**
     * Sets the ordenSeccion value for this FitxaUACriteria.
     * 
     * @param ordenSeccion
     */
    public void setOrdenSeccion(java.lang.String ordenSeccion) {
        this.ordenSeccion = ordenSeccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FitxaUACriteria)) return false;
        FitxaUACriteria other = (FitxaUACriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.ordenSeccion==null && other.getOrdenSeccion()==null) || 
             (this.ordenSeccion!=null &&
              this.ordenSeccion.equals(other.getOrdenSeccion())));
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
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getOrdenSeccion() != null) {
            _hashCode += getOrdenSeccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FitxaUACriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUACriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenSeccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenSeccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
