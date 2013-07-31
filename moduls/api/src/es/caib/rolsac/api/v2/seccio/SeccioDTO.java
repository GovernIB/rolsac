/**
 * SeccioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.seccio;

public class SeccioDTO  implements java.io.Serializable {
    private java.lang.String codigoEstandard;

    private java.lang.String descripcion;

    private long id;

    private java.lang.String nombre;

    private int orden;

    private java.lang.Long padre;

    private java.lang.String perfil;

    public SeccioDTO() {
    }

    public SeccioDTO(
           java.lang.String codigoEstandard,
           java.lang.String descripcion,
           long id,
           java.lang.String nombre,
           int orden,
           java.lang.Long padre,
           java.lang.String perfil) {
           this.codigoEstandard = codigoEstandard;
           this.descripcion = descripcion;
           this.id = id;
           this.nombre = nombre;
           this.orden = orden;
           this.padre = padre;
           this.perfil = perfil;
    }


    /**
     * Gets the codigoEstandard value for this SeccioDTO.
     * 
     * @return codigoEstandard
     */
    public java.lang.String getCodigoEstandard() {
        return codigoEstandard;
    }


    /**
     * Sets the codigoEstandard value for this SeccioDTO.
     * 
     * @param codigoEstandard
     */
    public void setCodigoEstandard(java.lang.String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }


    /**
     * Gets the descripcion value for this SeccioDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this SeccioDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the id value for this SeccioDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this SeccioDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this SeccioDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this SeccioDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the orden value for this SeccioDTO.
     * 
     * @return orden
     */
    public int getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this SeccioDTO.
     * 
     * @param orden
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }


    /**
     * Gets the padre value for this SeccioDTO.
     * 
     * @return padre
     */
    public java.lang.Long getPadre() {
        return padre;
    }


    /**
     * Sets the padre value for this SeccioDTO.
     * 
     * @param padre
     */
    public void setPadre(java.lang.Long padre) {
        this.padre = padre;
    }


    /**
     * Gets the perfil value for this SeccioDTO.
     * 
     * @return perfil
     */
    public java.lang.String getPerfil() {
        return perfil;
    }


    /**
     * Sets the perfil value for this SeccioDTO.
     * 
     * @param perfil
     */
    public void setPerfil(java.lang.String perfil) {
        this.perfil = perfil;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SeccioDTO)) return false;
        SeccioDTO other = (SeccioDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoEstandard==null && other.getCodigoEstandard()==null) || 
             (this.codigoEstandard!=null &&
              this.codigoEstandard.equals(other.getCodigoEstandard()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            this.id == other.getId() &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            this.orden == other.getOrden() &&
            ((this.padre==null && other.getPadre()==null) || 
             (this.padre!=null &&
              this.padre.equals(other.getPadre()))) &&
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
        if (getCodigoEstandard() != null) {
            _hashCode += getCodigoEstandard().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        _hashCode += getOrden();
        if (getPadre() != null) {
            _hashCode += getPadre().hashCode();
        }
        if (getPerfil() != null) {
            _hashCode += getPerfil().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SeccioDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstandard");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEstandard"));
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
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("padre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "padre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "perfil"));
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
