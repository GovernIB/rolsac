/**
 * MateriaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.materia;

public class MateriaDTO  implements java.io.Serializable {
    private java.lang.String codiHita;

    private java.lang.String codigoEstandar;

    private java.lang.String descripcion;

    private boolean destacada;

    private java.lang.Long foto;

    private java.lang.Long icono;

    private java.lang.Long iconoGrande;

    private long id;

    private java.lang.String nombre;

    private java.lang.String palabrasclave;

    public MateriaDTO() {
    }

    public MateriaDTO(
           java.lang.String codiHita,
           java.lang.String codigoEstandar,
           java.lang.String descripcion,
           boolean destacada,
           java.lang.Long foto,
           java.lang.Long icono,
           java.lang.Long iconoGrande,
           long id,
           java.lang.String nombre,
           java.lang.String palabrasclave) {
           this.codiHita = codiHita;
           this.codigoEstandar = codigoEstandar;
           this.descripcion = descripcion;
           this.destacada = destacada;
           this.foto = foto;
           this.icono = icono;
           this.iconoGrande = iconoGrande;
           this.id = id;
           this.nombre = nombre;
           this.palabrasclave = palabrasclave;
    }


    /**
     * Gets the codiHita value for this MateriaDTO.
     * 
     * @return codiHita
     */
    public java.lang.String getCodiHita() {
        return codiHita;
    }


    /**
     * Sets the codiHita value for this MateriaDTO.
     * 
     * @param codiHita
     */
    public void setCodiHita(java.lang.String codiHita) {
        this.codiHita = codiHita;
    }


    /**
     * Gets the codigoEstandar value for this MateriaDTO.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this MateriaDTO.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the descripcion value for this MateriaDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this MateriaDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the destacada value for this MateriaDTO.
     * 
     * @return destacada
     */
    public boolean isDestacada() {
        return destacada;
    }


    /**
     * Sets the destacada value for this MateriaDTO.
     * 
     * @param destacada
     */
    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }


    /**
     * Gets the foto value for this MateriaDTO.
     * 
     * @return foto
     */
    public java.lang.Long getFoto() {
        return foto;
    }


    /**
     * Sets the foto value for this MateriaDTO.
     * 
     * @param foto
     */
    public void setFoto(java.lang.Long foto) {
        this.foto = foto;
    }


    /**
     * Gets the icono value for this MateriaDTO.
     * 
     * @return icono
     */
    public java.lang.Long getIcono() {
        return icono;
    }


    /**
     * Sets the icono value for this MateriaDTO.
     * 
     * @param icono
     */
    public void setIcono(java.lang.Long icono) {
        this.icono = icono;
    }


    /**
     * Gets the iconoGrande value for this MateriaDTO.
     * 
     * @return iconoGrande
     */
    public java.lang.Long getIconoGrande() {
        return iconoGrande;
    }


    /**
     * Sets the iconoGrande value for this MateriaDTO.
     * 
     * @param iconoGrande
     */
    public void setIconoGrande(java.lang.Long iconoGrande) {
        this.iconoGrande = iconoGrande;
    }


    /**
     * Gets the id value for this MateriaDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this MateriaDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this MateriaDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this MateriaDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the palabrasclave value for this MateriaDTO.
     * 
     * @return palabrasclave
     */
    public java.lang.String getPalabrasclave() {
        return palabrasclave;
    }


    /**
     * Sets the palabrasclave value for this MateriaDTO.
     * 
     * @param palabrasclave
     */
    public void setPalabrasclave(java.lang.String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MateriaDTO)) return false;
        MateriaDTO other = (MateriaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiHita==null && other.getCodiHita()==null) || 
             (this.codiHita!=null &&
              this.codiHita.equals(other.getCodiHita()))) &&
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            this.destacada == other.isDestacada() &&
            ((this.foto==null && other.getFoto()==null) || 
             (this.foto!=null &&
              this.foto.equals(other.getFoto()))) &&
            ((this.icono==null && other.getIcono()==null) || 
             (this.icono!=null &&
              this.icono.equals(other.getIcono()))) &&
            ((this.iconoGrande==null && other.getIconoGrande()==null) || 
             (this.iconoGrande!=null &&
              this.iconoGrande.equals(other.getIconoGrande()))) &&
            this.id == other.getId() &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
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
        if (getCodiHita() != null) {
            _hashCode += getCodiHita().hashCode();
        }
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        _hashCode += (isDestacada() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFoto() != null) {
            _hashCode += getFoto().hashCode();
        }
        if (getIcono() != null) {
            _hashCode += getIcono().hashCode();
        }
        if (getIconoGrande() != null) {
            _hashCode += getIconoGrande().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getPalabrasclave() != null) {
            _hashCode += getPalabrasclave().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MateriaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiHita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiHita"));
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
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destacada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destacada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
