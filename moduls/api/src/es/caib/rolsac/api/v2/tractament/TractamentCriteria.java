/**
 * TractamentCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.tractament;

public class TractamentCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String codigoEstandar;

    private java.lang.String t_tipo;

    private java.lang.String t_cargoM;

    private java.lang.String t_tratamientoM;

    private java.lang.String t_cargoF;

    private java.lang.String t_tratamientoF;

    public TractamentCriteria() {
    }

    public TractamentCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String codigoEstandar,
           java.lang.String t_tipo,
           java.lang.String t_cargoM,
           java.lang.String t_tratamientoM,
           java.lang.String t_cargoF,
           java.lang.String t_tratamientoF) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.codigoEstandar = codigoEstandar;
        this.t_tipo = t_tipo;
        this.t_cargoM = t_cargoM;
        this.t_tratamientoM = t_tratamientoM;
        this.t_cargoF = t_cargoF;
        this.t_tratamientoF = t_tratamientoF;
    }


    /**
     * Gets the codigoEstandar value for this TractamentCriteria.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this TractamentCriteria.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the t_tipo value for this TractamentCriteria.
     * 
     * @return t_tipo
     */
    public java.lang.String getT_tipo() {
        return t_tipo;
    }


    /**
     * Sets the t_tipo value for this TractamentCriteria.
     * 
     * @param t_tipo
     */
    public void setT_tipo(java.lang.String t_tipo) {
        this.t_tipo = t_tipo;
    }


    /**
     * Gets the t_cargoM value for this TractamentCriteria.
     * 
     * @return t_cargoM
     */
    public java.lang.String getT_cargoM() {
        return t_cargoM;
    }


    /**
     * Sets the t_cargoM value for this TractamentCriteria.
     * 
     * @param t_cargoM
     */
    public void setT_cargoM(java.lang.String t_cargoM) {
        this.t_cargoM = t_cargoM;
    }


    /**
     * Gets the t_tratamientoM value for this TractamentCriteria.
     * 
     * @return t_tratamientoM
     */
    public java.lang.String getT_tratamientoM() {
        return t_tratamientoM;
    }


    /**
     * Sets the t_tratamientoM value for this TractamentCriteria.
     * 
     * @param t_tratamientoM
     */
    public void setT_tratamientoM(java.lang.String t_tratamientoM) {
        this.t_tratamientoM = t_tratamientoM;
    }


    /**
     * Gets the t_cargoF value for this TractamentCriteria.
     * 
     * @return t_cargoF
     */
    public java.lang.String getT_cargoF() {
        return t_cargoF;
    }


    /**
     * Sets the t_cargoF value for this TractamentCriteria.
     * 
     * @param t_cargoF
     */
    public void setT_cargoF(java.lang.String t_cargoF) {
        this.t_cargoF = t_cargoF;
    }


    /**
     * Gets the t_tratamientoF value for this TractamentCriteria.
     * 
     * @return t_tratamientoF
     */
    public java.lang.String getT_tratamientoF() {
        return t_tratamientoF;
    }


    /**
     * Sets the t_tratamientoF value for this TractamentCriteria.
     * 
     * @param t_tratamientoF
     */
    public void setT_tratamientoF(java.lang.String t_tratamientoF) {
        this.t_tratamientoF = t_tratamientoF;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TractamentCriteria)) return false;
        TractamentCriteria other = (TractamentCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.t_tipo==null && other.getT_tipo()==null) || 
             (this.t_tipo!=null &&
              this.t_tipo.equals(other.getT_tipo()))) &&
            ((this.t_cargoM==null && other.getT_cargoM()==null) || 
             (this.t_cargoM!=null &&
              this.t_cargoM.equals(other.getT_cargoM()))) &&
            ((this.t_tratamientoM==null && other.getT_tratamientoM()==null) || 
             (this.t_tratamientoM!=null &&
              this.t_tratamientoM.equals(other.getT_tratamientoM()))) &&
            ((this.t_cargoF==null && other.getT_cargoF()==null) || 
             (this.t_cargoF!=null &&
              this.t_cargoF.equals(other.getT_cargoF()))) &&
            ((this.t_tratamientoF==null && other.getT_tratamientoF()==null) || 
             (this.t_tratamientoF!=null &&
              this.t_tratamientoF.equals(other.getT_tratamientoF())));
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
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getT_tipo() != null) {
            _hashCode += getT_tipo().hashCode();
        }
        if (getT_cargoM() != null) {
            _hashCode += getT_cargoM().hashCode();
        }
        if (getT_tratamientoM() != null) {
            _hashCode += getT_tratamientoM().hashCode();
        }
        if (getT_cargoF() != null) {
            _hashCode += getT_cargoF().hashCode();
        }
        if (getT_tratamientoF() != null) {
            _hashCode += getT_tratamientoF().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TractamentCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tractament.v2.api.rolsac.caib.es", "TractamentCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstandar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEstandar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_cargoM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_cargoM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_tratamientoM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_tratamientoM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_cargoF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_cargoF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_tratamientoF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_tratamientoF"));
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
