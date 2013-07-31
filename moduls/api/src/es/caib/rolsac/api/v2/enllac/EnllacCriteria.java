/**
 * EnllacCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.enllac;

public class EnllacCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String orden;

    private java.lang.String t_titulo;

    private java.lang.String t_enlace;

    public EnllacCriteria() {
    }

    public EnllacCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String orden,
           java.lang.String t_titulo,
           java.lang.String t_enlace) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.orden = orden;
        this.t_titulo = t_titulo;
        this.t_enlace = t_enlace;
    }


    /**
     * Gets the orden value for this EnllacCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this EnllacCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the t_titulo value for this EnllacCriteria.
     * 
     * @return t_titulo
     */
    public java.lang.String getT_titulo() {
        return t_titulo;
    }


    /**
     * Sets the t_titulo value for this EnllacCriteria.
     * 
     * @param t_titulo
     */
    public void setT_titulo(java.lang.String t_titulo) {
        this.t_titulo = t_titulo;
    }


    /**
     * Gets the t_enlace value for this EnllacCriteria.
     * 
     * @return t_enlace
     */
    public java.lang.String getT_enlace() {
        return t_enlace;
    }


    /**
     * Sets the t_enlace value for this EnllacCriteria.
     * 
     * @param t_enlace
     */
    public void setT_enlace(java.lang.String t_enlace) {
        this.t_enlace = t_enlace;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnllacCriteria)) return false;
        EnllacCriteria other = (EnllacCriteria) obj;
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
            ((this.t_titulo==null && other.getT_titulo()==null) || 
             (this.t_titulo!=null &&
              this.t_titulo.equals(other.getT_titulo()))) &&
            ((this.t_enlace==null && other.getT_enlace()==null) || 
             (this.t_enlace!=null &&
              this.t_enlace.equals(other.getT_enlace())));
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
        if (getT_titulo() != null) {
            _hashCode += getT_titulo().hashCode();
        }
        if (getT_enlace() != null) {
            _hashCode += getT_enlace().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnllacCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_enlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_enlace"));
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
