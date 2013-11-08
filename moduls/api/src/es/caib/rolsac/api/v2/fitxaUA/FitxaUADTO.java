/**
 * FitxaUADTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.fitxaUA;

public class FitxaUADTO  implements java.io.Serializable {
    private java.lang.Long ficha;

    private java.lang.Long id;

    private java.lang.Integer orden;

    private java.lang.Integer ordenseccion;

    private java.lang.Long seccion;

    private java.lang.Long unidadAdministrativa;

    public FitxaUADTO() {
    }

    public FitxaUADTO(
           java.lang.Long ficha,
           java.lang.Long id,
           java.lang.Integer orden,
           java.lang.Integer ordenseccion,
           java.lang.Long seccion,
           java.lang.Long unidadAdministrativa) {
           this.ficha = ficha;
           this.id = id;
           this.orden = orden;
           this.ordenseccion = ordenseccion;
           this.seccion = seccion;
           this.unidadAdministrativa = unidadAdministrativa;
    }


    /**
     * Gets the ficha value for this FitxaUADTO.
     * 
     * @return ficha
     */
    public java.lang.Long getFicha() {
        return ficha;
    }


    /**
     * Sets the ficha value for this FitxaUADTO.
     * 
     * @param ficha
     */
    public void setFicha(java.lang.Long ficha) {
        this.ficha = ficha;
    }


    /**
     * Gets the id value for this FitxaUADTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this FitxaUADTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the orden value for this FitxaUADTO.
     * 
     * @return orden
     */
    public java.lang.Integer getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this FitxaUADTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Integer orden) {
        this.orden = orden;
    }


    /**
     * Gets the ordenseccion value for this FitxaUADTO.
     * 
     * @return ordenseccion
     */
    public java.lang.Integer getOrdenseccion() {
        return ordenseccion;
    }


    /**
     * Sets the ordenseccion value for this FitxaUADTO.
     * 
     * @param ordenseccion
     */
    public void setOrdenseccion(java.lang.Integer ordenseccion) {
        this.ordenseccion = ordenseccion;
    }


    /**
     * Gets the seccion value for this FitxaUADTO.
     * 
     * @return seccion
     */
    public java.lang.Long getSeccion() {
        return seccion;
    }


    /**
     * Sets the seccion value for this FitxaUADTO.
     * 
     * @param seccion
     */
    public void setSeccion(java.lang.Long seccion) {
        this.seccion = seccion;
    }


    /**
     * Gets the unidadAdministrativa value for this FitxaUADTO.
     * 
     * @return unidadAdministrativa
     */
    public java.lang.Long getUnidadAdministrativa() {
        return unidadAdministrativa;
    }


    /**
     * Sets the unidadAdministrativa value for this FitxaUADTO.
     * 
     * @param unidadAdministrativa
     */
    public void setUnidadAdministrativa(java.lang.Long unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FitxaUADTO)) return false;
        FitxaUADTO other = (FitxaUADTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ficha==null && other.getFicha()==null) || 
             (this.ficha!=null &&
              this.ficha.equals(other.getFicha()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.ordenseccion==null && other.getOrdenseccion()==null) || 
             (this.ordenseccion!=null &&
              this.ordenseccion.equals(other.getOrdenseccion()))) &&
            ((this.seccion==null && other.getSeccion()==null) || 
             (this.seccion!=null &&
              this.seccion.equals(other.getSeccion()))) &&
            ((this.unidadAdministrativa==null && other.getUnidadAdministrativa()==null) || 
             (this.unidadAdministrativa!=null &&
              this.unidadAdministrativa.equals(other.getUnidadAdministrativa())));
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
        if (getFicha() != null) {
            _hashCode += getFicha().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getOrdenseccion() != null) {
            _hashCode += getOrdenseccion().hashCode();
        }
        if (getSeccion() != null) {
            _hashCode += getSeccion().hashCode();
        }
        if (getUnidadAdministrativa() != null) {
            _hashCode += getUnidadAdministrativa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FitxaUADTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fitxaUA.v2.api.rolsac.caib.es", "FitxaUADTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ficha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ficha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenseccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenseccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadAdministrativa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadAdministrativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
