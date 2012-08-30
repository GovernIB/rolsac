/**
 * IconaMateriaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.iconaMateria;

public class IconaMateriaDTO  implements java.io.Serializable {
    private java.lang.Long icono;

    private java.lang.Long id;

    private java.lang.Long materia;

    private java.lang.Long perfil;

    public IconaMateriaDTO() {
    }

    public IconaMateriaDTO(
           java.lang.Long icono,
           java.lang.Long id,
           java.lang.Long materia,
           java.lang.Long perfil) {
           this.icono = icono;
           this.id = id;
           this.materia = materia;
           this.perfil = perfil;
    }


    /**
     * Gets the icono value for this IconaMateriaDTO.
     * 
     * @return icono
     */
    public java.lang.Long getIcono() {
        return icono;
    }


    /**
     * Sets the icono value for this IconaMateriaDTO.
     * 
     * @param icono
     */
    public void setIcono(java.lang.Long icono) {
        this.icono = icono;
    }


    /**
     * Gets the id value for this IconaMateriaDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this IconaMateriaDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the materia value for this IconaMateriaDTO.
     * 
     * @return materia
     */
    public java.lang.Long getMateria() {
        return materia;
    }


    /**
     * Sets the materia value for this IconaMateriaDTO.
     * 
     * @param materia
     */
    public void setMateria(java.lang.Long materia) {
        this.materia = materia;
    }


    /**
     * Gets the perfil value for this IconaMateriaDTO.
     * 
     * @return perfil
     */
    public java.lang.Long getPerfil() {
        return perfil;
    }


    /**
     * Sets the perfil value for this IconaMateriaDTO.
     * 
     * @param perfil
     */
    public void setPerfil(java.lang.Long perfil) {
        this.perfil = perfil;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IconaMateriaDTO)) return false;
        IconaMateriaDTO other = (IconaMateriaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.icono==null && other.getIcono()==null) || 
             (this.icono!=null &&
              this.icono.equals(other.getIcono()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.materia==null && other.getMateria()==null) || 
             (this.materia!=null &&
              this.materia.equals(other.getMateria()))) &&
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
        if (getIcono() != null) {
            _hashCode += getIcono().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMateria() != null) {
            _hashCode += getMateria().hashCode();
        }
        if (getPerfil() != null) {
            _hashCode += getPerfil().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IconaMateriaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://iconaMateria.v2.api.rolsac.caib.es", "IconaMateriaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("materia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "materia"));
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
