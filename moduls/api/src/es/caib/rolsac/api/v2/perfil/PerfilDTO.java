/**
 * PerfilDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.perfil;

public class PerfilDTO  implements java.io.Serializable {
    private java.lang.String codigoEstandard;

    private java.lang.String descripcion;

    private java.lang.Long id;

    private java.lang.String nombre;

    private java.lang.String pathIconografia;

    public PerfilDTO() {
    }

    public PerfilDTO(
           java.lang.String codigoEstandard,
           java.lang.String descripcion,
           java.lang.Long id,
           java.lang.String nombre,
           java.lang.String pathIconografia) {
           this.codigoEstandard = codigoEstandard;
           this.descripcion = descripcion;
           this.id = id;
           this.nombre = nombre;
           this.pathIconografia = pathIconografia;
    }


    /**
     * Gets the codigoEstandard value for this PerfilDTO.
     * 
     * @return codigoEstandard
     */
    public java.lang.String getCodigoEstandard() {
        return codigoEstandard;
    }


    /**
     * Sets the codigoEstandard value for this PerfilDTO.
     * 
     * @param codigoEstandard
     */
    public void setCodigoEstandard(java.lang.String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }


    /**
     * Gets the descripcion value for this PerfilDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this PerfilDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the id value for this PerfilDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this PerfilDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this PerfilDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PerfilDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the pathIconografia value for this PerfilDTO.
     * 
     * @return pathIconografia
     */
    public java.lang.String getPathIconografia() {
        return pathIconografia;
    }


    /**
     * Sets the pathIconografia value for this PerfilDTO.
     * 
     * @param pathIconografia
     */
    public void setPathIconografia(java.lang.String pathIconografia) {
        this.pathIconografia = pathIconografia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PerfilDTO)) return false;
        PerfilDTO other = (PerfilDTO) obj;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.pathIconografia==null && other.getPathIconografia()==null) || 
             (this.pathIconografia!=null &&
              this.pathIconografia.equals(other.getPathIconografia())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getPathIconografia() != null) {
            _hashCode += getPathIconografia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PerfilDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilDTO"));
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
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathIconografia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathIconografia"));
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
