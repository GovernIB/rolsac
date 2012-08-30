/**
 * PublicObjectiuDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.publicObjectiu;

public class PublicObjectiuDTO  implements java.io.Serializable {
    private java.lang.String codigoEstandar;

    private java.lang.String descripcion;

    private long id;

    private int orden;

    private java.lang.String palabrasclave;

    private java.lang.String titulo;

    public PublicObjectiuDTO() {
    }

    public PublicObjectiuDTO(
           java.lang.String codigoEstandar,
           java.lang.String descripcion,
           long id,
           int orden,
           java.lang.String palabrasclave,
           java.lang.String titulo) {
           this.codigoEstandar = codigoEstandar;
           this.descripcion = descripcion;
           this.id = id;
           this.orden = orden;
           this.palabrasclave = palabrasclave;
           this.titulo = titulo;
    }


    /**
     * Gets the codigoEstandar value for this PublicObjectiuDTO.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this PublicObjectiuDTO.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the descripcion value for this PublicObjectiuDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this PublicObjectiuDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the id value for this PublicObjectiuDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this PublicObjectiuDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the orden value for this PublicObjectiuDTO.
     * 
     * @return orden
     */
    public int getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this PublicObjectiuDTO.
     * 
     * @param orden
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }


    /**
     * Gets the palabrasclave value for this PublicObjectiuDTO.
     * 
     * @return palabrasclave
     */
    public java.lang.String getPalabrasclave() {
        return palabrasclave;
    }


    /**
     * Sets the palabrasclave value for this PublicObjectiuDTO.
     * 
     * @param palabrasclave
     */
    public void setPalabrasclave(java.lang.String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }


    /**
     * Gets the titulo value for this PublicObjectiuDTO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this PublicObjectiuDTO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PublicObjectiuDTO)) return false;
        PublicObjectiuDTO other = (PublicObjectiuDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            this.id == other.getId() &&
            this.orden == other.getOrden() &&
            ((this.palabrasclave==null && other.getPalabrasclave()==null) || 
             (this.palabrasclave!=null &&
              this.palabrasclave.equals(other.getPalabrasclave()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo())));
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
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        _hashCode += getOrden();
        if (getPalabrasclave() != null) {
            _hashCode += getPalabrasclave().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PublicObjectiuDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstandar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEstandar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("palabrasclave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "palabrasclave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulo"));
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
