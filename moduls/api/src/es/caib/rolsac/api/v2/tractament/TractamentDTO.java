/**
 * TractamentDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.tractament;

public class TractamentDTO  implements java.io.Serializable {
    private java.lang.String cargoF;

    private java.lang.String cargoM;

    private java.lang.String codigoEstandar;

    private java.lang.Long id;

    private java.lang.String tipo;

    private java.lang.String tratamientoF;

    private java.lang.String tratamientoM;

    public TractamentDTO() {
    }

    public TractamentDTO(
           java.lang.String cargoF,
           java.lang.String cargoM,
           java.lang.String codigoEstandar,
           java.lang.Long id,
           java.lang.String tipo,
           java.lang.String tratamientoF,
           java.lang.String tratamientoM) {
           this.cargoF = cargoF;
           this.cargoM = cargoM;
           this.codigoEstandar = codigoEstandar;
           this.id = id;
           this.tipo = tipo;
           this.tratamientoF = tratamientoF;
           this.tratamientoM = tratamientoM;
    }


    /**
     * Gets the cargoF value for this TractamentDTO.
     * 
     * @return cargoF
     */
    public java.lang.String getCargoF() {
        return cargoF;
    }


    /**
     * Sets the cargoF value for this TractamentDTO.
     * 
     * @param cargoF
     */
    public void setCargoF(java.lang.String cargoF) {
        this.cargoF = cargoF;
    }


    /**
     * Gets the cargoM value for this TractamentDTO.
     * 
     * @return cargoM
     */
    public java.lang.String getCargoM() {
        return cargoM;
    }


    /**
     * Sets the cargoM value for this TractamentDTO.
     * 
     * @param cargoM
     */
    public void setCargoM(java.lang.String cargoM) {
        this.cargoM = cargoM;
    }


    /**
     * Gets the codigoEstandar value for this TractamentDTO.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this TractamentDTO.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the id value for this TractamentDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TractamentDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the tipo value for this TractamentDTO.
     * 
     * @return tipo
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this TractamentDTO.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the tratamientoF value for this TractamentDTO.
     * 
     * @return tratamientoF
     */
    public java.lang.String getTratamientoF() {
        return tratamientoF;
    }


    /**
     * Sets the tratamientoF value for this TractamentDTO.
     * 
     * @param tratamientoF
     */
    public void setTratamientoF(java.lang.String tratamientoF) {
        this.tratamientoF = tratamientoF;
    }


    /**
     * Gets the tratamientoM value for this TractamentDTO.
     * 
     * @return tratamientoM
     */
    public java.lang.String getTratamientoM() {
        return tratamientoM;
    }


    /**
     * Sets the tratamientoM value for this TractamentDTO.
     * 
     * @param tratamientoM
     */
    public void setTratamientoM(java.lang.String tratamientoM) {
        this.tratamientoM = tratamientoM;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TractamentDTO)) return false;
        TractamentDTO other = (TractamentDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cargoF==null && other.getCargoF()==null) || 
             (this.cargoF!=null &&
              this.cargoF.equals(other.getCargoF()))) &&
            ((this.cargoM==null && other.getCargoM()==null) || 
             (this.cargoM!=null &&
              this.cargoM.equals(other.getCargoM()))) &&
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.tratamientoF==null && other.getTratamientoF()==null) || 
             (this.tratamientoF!=null &&
              this.tratamientoF.equals(other.getTratamientoF()))) &&
            ((this.tratamientoM==null && other.getTratamientoM()==null) || 
             (this.tratamientoM!=null &&
              this.tratamientoM.equals(other.getTratamientoM())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCargoF() != null) {
            _hashCode += getCargoF().hashCode();
        }
        if (getCargoM() != null) {
            _hashCode += getCargoM().hashCode();
        }
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTratamientoF() != null) {
            _hashCode += getTratamientoF().hashCode();
        }
        if (getTratamientoM() != null) {
            _hashCode += getTratamientoM().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TractamentDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tractament.v2.api.rolsac.caib.es", "TractamentDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargoF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargoF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargoM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargoM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstandar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEstandar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tratamientoF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tratamientoF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tratamientoM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tratamientoM"));
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
