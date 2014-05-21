/**
 * IdiomaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.idioma;

public class IdiomaDTO  implements java.io.Serializable {
    private java.lang.String lang;

    private java.lang.String codigoEstandar;

    private java.lang.Integer orden;

    private java.lang.String nombre;

    private java.lang.String langTraductor;

    public IdiomaDTO() {
    }

    public IdiomaDTO(
           java.lang.String lang,
           java.lang.String codigoEstandar,
           java.lang.Integer orden,
           java.lang.String nombre,
           java.lang.String langTraductor) {
           this.lang = lang;
           this.codigoEstandar = codigoEstandar;
           this.orden = orden;
           this.nombre = nombre;
           this.langTraductor = langTraductor;
    }


    /**
     * Gets the lang value for this IdiomaDTO.
     * 
     * @return lang
     */
    public java.lang.String getLang() {
        return lang;
    }


    /**
     * Sets the lang value for this IdiomaDTO.
     * 
     * @param lang
     */
    public void setLang(java.lang.String lang) {
        this.lang = lang;
    }


    /**
     * Gets the codigoEstandar value for this IdiomaDTO.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this IdiomaDTO.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the orden value for this IdiomaDTO.
     * 
     * @return orden
     */
    public java.lang.Integer getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this IdiomaDTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Integer orden) {
        this.orden = orden;
    }


    /**
     * Gets the nombre value for this IdiomaDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this IdiomaDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the langTraductor value for this IdiomaDTO.
     * 
     * @return langTraductor
     */
    public java.lang.String getLangTraductor() {
        return langTraductor;
    }


    /**
     * Sets the langTraductor value for this IdiomaDTO.
     * 
     * @param langTraductor
     */
    public void setLangTraductor(java.lang.String langTraductor) {
        this.langTraductor = langTraductor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdiomaDTO)) return false;
        IdiomaDTO other = (IdiomaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lang==null && other.getLang()==null) || 
             (this.lang!=null &&
              this.lang.equals(other.getLang()))) &&
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.langTraductor==null && other.getLangTraductor()==null) || 
             (this.langTraductor!=null &&
              this.langTraductor.equals(other.getLangTraductor())));
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
        if (getLang() != null) {
            _hashCode += getLang().hashCode();
        }
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getLangTraductor() != null) {
            _hashCode += getLangTraductor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdiomaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://idioma.v2.api.rolsac.caib.es", "IdiomaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lang");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lang"));
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
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("langTraductor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "langTraductor"));
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
