/**
 * IconaFamiliaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.iconaFamilia;

public class IconaFamiliaDTO  implements java.io.Serializable {
    private java.lang.Long familia;

    private java.lang.Long icono;

    private java.lang.Long id;

    private java.lang.Long perfil;

    public IconaFamiliaDTO() {
    }

    public IconaFamiliaDTO(
           java.lang.Long familia,
           java.lang.Long icono,
           java.lang.Long id,
           java.lang.Long perfil) {
           this.familia = familia;
           this.icono = icono;
           this.id = id;
           this.perfil = perfil;
    }


    /**
     * Gets the familia value for this IconaFamiliaDTO.
     * 
     * @return familia
     */
    public java.lang.Long getFamilia() {
        return familia;
    }


    /**
     * Sets the familia value for this IconaFamiliaDTO.
     * 
     * @param familia
     */
    public void setFamilia(java.lang.Long familia) {
        this.familia = familia;
    }


    /**
     * Gets the icono value for this IconaFamiliaDTO.
     * 
     * @return icono
     */
    public java.lang.Long getIcono() {
        return icono;
    }


    /**
     * Sets the icono value for this IconaFamiliaDTO.
     * 
     * @param icono
     */
    public void setIcono(java.lang.Long icono) {
        this.icono = icono;
    }


    /**
     * Gets the id value for this IconaFamiliaDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this IconaFamiliaDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the perfil value for this IconaFamiliaDTO.
     * 
     * @return perfil
     */
    public java.lang.Long getPerfil() {
        return perfil;
    }


    /**
     * Sets the perfil value for this IconaFamiliaDTO.
     * 
     * @param perfil
     */
    public void setPerfil(java.lang.Long perfil) {
        this.perfil = perfil;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IconaFamiliaDTO)) return false;
        IconaFamiliaDTO other = (IconaFamiliaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.familia==null && other.getFamilia()==null) || 
             (this.familia!=null &&
              this.familia.equals(other.getFamilia()))) &&
            ((this.icono==null && other.getIcono()==null) || 
             (this.icono!=null &&
              this.icono.equals(other.getIcono()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.perfil==null && other.getPerfil()==null) || 
             (this.perfil!=null &&
              this.perfil.equals(other.getPerfil())));
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
        if (getFamilia() != null) {
            _hashCode += getFamilia().hashCode();
        }
        if (getIcono() != null) {
            _hashCode += getIcono().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getPerfil() != null) {
            _hashCode += getPerfil().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IconaFamiliaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iconaFamilia.v2.api.rolsac.caib.es", "IconaFamiliaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "familia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "icono"));
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
        elemField.setFieldName("perfil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "perfil"));
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
