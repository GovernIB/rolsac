/**
 * FetVitalDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.fetVital;

public class FetVitalDTO  implements java.io.Serializable {
    private java.lang.String codigoEstandar;

    private java.lang.String descripcion;

    private java.lang.Long foto;

    private java.lang.Long icono;

    private java.lang.Long iconoGrande;

    private java.lang.Long id;

    private java.lang.String nombre;

    private int orden;

    private java.lang.String palabrasclave;

    public FetVitalDTO() {
    }

    public FetVitalDTO(
           java.lang.String codigoEstandar,
           java.lang.String descripcion,
           java.lang.Long foto,
           java.lang.Long icono,
           java.lang.Long iconoGrande,
           java.lang.Long id,
           java.lang.String nombre,
           int orden,
           java.lang.String palabrasclave) {
           this.codigoEstandar = codigoEstandar;
           this.descripcion = descripcion;
           this.foto = foto;
           this.icono = icono;
           this.iconoGrande = iconoGrande;
           this.id = id;
           this.nombre = nombre;
           this.orden = orden;
           this.palabrasclave = palabrasclave;
    }


    /**
     * Gets the codigoEstandar value for this FetVitalDTO.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this FetVitalDTO.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the descripcion value for this FetVitalDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this FetVitalDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the foto value for this FetVitalDTO.
     * 
     * @return foto
     */
    public java.lang.Long getFoto() {
        return foto;
    }


    /**
     * Sets the foto value for this FetVitalDTO.
     * 
     * @param foto
     */
    public void setFoto(java.lang.Long foto) {
        this.foto = foto;
    }


    /**
     * Gets the icono value for this FetVitalDTO.
     * 
     * @return icono
     */
    public java.lang.Long getIcono() {
        return icono;
    }


    /**
     * Sets the icono value for this FetVitalDTO.
     * 
     * @param icono
     */
    public void setIcono(java.lang.Long icono) {
        this.icono = icono;
    }


    /**
     * Gets the iconoGrande value for this FetVitalDTO.
     * 
     * @return iconoGrande
     */
    public java.lang.Long getIconoGrande() {
        return iconoGrande;
    }


    /**
     * Sets the iconoGrande value for this FetVitalDTO.
     * 
     * @param iconoGrande
     */
    public void setIconoGrande(java.lang.Long iconoGrande) {
        this.iconoGrande = iconoGrande;
    }


    /**
     * Gets the id value for this FetVitalDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this FetVitalDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this FetVitalDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this FetVitalDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the orden value for this FetVitalDTO.
     * 
     * @return orden
     */
    public int getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this FetVitalDTO.
     * 
     * @param orden
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }


    /**
     * Gets the palabrasclave value for this FetVitalDTO.
     * 
     * @return palabrasclave
     */
    public java.lang.String getPalabrasclave() {
        return palabrasclave;
    }


    /**
     * Sets the palabrasclave value for this FetVitalDTO.
     * 
     * @param palabrasclave
     */
    public void setPalabrasclave(java.lang.String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FetVitalDTO)) return false;
        FetVitalDTO other = (FetVitalDTO) obj;
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
            ((this.foto==null && other.getFoto()==null) || 
             (this.foto!=null &&
              this.foto.equals(other.getFoto()))) &&
            ((this.icono==null && other.getIcono()==null) || 
             (this.icono!=null &&
              this.icono.equals(other.getIcono()))) &&
            ((this.iconoGrande==null && other.getIconoGrande()==null) || 
             (this.iconoGrande!=null &&
              this.iconoGrande.equals(other.getIconoGrande()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            this.orden == other.getOrden() &&
            ((this.palabrasclave==null && other.getPalabrasclave()==null) || 
             (this.palabrasclave!=null &&
              this.palabrasclave.equals(other.getPalabrasclave())));
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
        if (getFoto() != null) {
            _hashCode += getFoto().hashCode();
        }
        if (getIcono() != null) {
            _hashCode += getIcono().hashCode();
        }
        if (getIconoGrande() != null) {
            _hashCode += getIconoGrande().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        _hashCode += getOrden();
        if (getPalabrasclave() != null) {
            _hashCode += getPalabrasclave().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FetVitalDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fetVital.v2.api.rolsac.caib.es", "FetVitalDTO"));
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
        elemField.setFieldName("foto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foto"));
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
        elemField.setFieldName("iconoGrande");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iconoGrande"));
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
        elemField.setFieldName("palabrasclave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "palabrasclave"));
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
