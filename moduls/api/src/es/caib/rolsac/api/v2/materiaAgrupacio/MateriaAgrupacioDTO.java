/**
 * MateriaAgrupacioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.materiaAgrupacio;

public class MateriaAgrupacioDTO  implements java.io.Serializable {
    private java.lang.Long agrupacion;

    private java.lang.Long id;

    private java.lang.Long materia;

    private java.lang.Integer orden;

    public MateriaAgrupacioDTO() {
    }

    public MateriaAgrupacioDTO(
           java.lang.Long agrupacion,
           java.lang.Long id,
           java.lang.Long materia,
           java.lang.Integer orden) {
           this.agrupacion = agrupacion;
           this.id = id;
           this.materia = materia;
           this.orden = orden;
    }


    /**
     * Gets the agrupacion value for this MateriaAgrupacioDTO.
     * 
     * @return agrupacion
     */
    public java.lang.Long getAgrupacion() {
        return agrupacion;
    }


    /**
     * Sets the agrupacion value for this MateriaAgrupacioDTO.
     * 
     * @param agrupacion
     */
    public void setAgrupacion(java.lang.Long agrupacion) {
        this.agrupacion = agrupacion;
    }


    /**
     * Gets the id value for this MateriaAgrupacioDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this MateriaAgrupacioDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the materia value for this MateriaAgrupacioDTO.
     * 
     * @return materia
     */
    public java.lang.Long getMateria() {
        return materia;
    }


    /**
     * Sets the materia value for this MateriaAgrupacioDTO.
     * 
     * @param materia
     */
    public void setMateria(java.lang.Long materia) {
        this.materia = materia;
    }


    /**
     * Gets the orden value for this MateriaAgrupacioDTO.
     * 
     * @return orden
     */
    public java.lang.Integer getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this MateriaAgrupacioDTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Integer orden) {
        this.orden = orden;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MateriaAgrupacioDTO)) return false;
        MateriaAgrupacioDTO other = (MateriaAgrupacioDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.agrupacion==null && other.getAgrupacion()==null) || 
             (this.agrupacion!=null &&
              this.agrupacion.equals(other.getAgrupacion()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.materia==null && other.getMateria()==null) || 
             (this.materia!=null &&
              this.materia.equals(other.getMateria()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden())));
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
        if (getAgrupacion() != null) {
            _hashCode += getAgrupacion().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMateria() != null) {
            _hashCode += getMateria().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MateriaAgrupacioDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agrupacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agrupacion"));
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
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
