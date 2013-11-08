/**
 * EdificiDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.edifici;

public class EdificiDTO  extends es.caib.rolsac.api.v2.general.EntitatRemota  implements java.io.Serializable {
    private java.lang.String codigoPostal;

    private java.lang.String descripcion;

    private java.lang.String direccion;

    private java.lang.String email;

    private java.lang.String fax;

    private java.lang.Long fotoGrande;

    private java.lang.Long fotoPequenya;

    private long id;

    private java.lang.String latitud;

    private java.lang.String longitud;

    private java.lang.Long plano;

    private java.lang.String poblacion;

    private java.lang.String telefono;

    public EdificiDTO() {
    }

    public EdificiDTO(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota,
           java.lang.String codigoPostal,
           java.lang.String descripcion,
           java.lang.String direccion,
           java.lang.String email,
           java.lang.String fax,
           java.lang.Long fotoGrande,
           java.lang.Long fotoPequenya,
           long id,
           java.lang.String latitud,
           java.lang.String longitud,
           java.lang.Long plano,
           java.lang.String poblacion,
           java.lang.String telefono) {
        super(
            administracionRemota,
            idExterno,
            urlRemota);
        this.codigoPostal = codigoPostal;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.email = email;
        this.fax = fax;
        this.fotoGrande = fotoGrande;
        this.fotoPequenya = fotoPequenya;
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.plano = plano;
        this.poblacion = poblacion;
        this.telefono = telefono;
    }


    /**
     * Gets the codigoPostal value for this EdificiDTO.
     * 
     * @return codigoPostal
     */
    public java.lang.String getCodigoPostal() {
        return codigoPostal;
    }


    /**
     * Sets the codigoPostal value for this EdificiDTO.
     * 
     * @param codigoPostal
     */
    public void setCodigoPostal(java.lang.String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }


    /**
     * Gets the descripcion value for this EdificiDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this EdificiDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the direccion value for this EdificiDTO.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this EdificiDTO.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the email value for this EdificiDTO.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this EdificiDTO.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the fax value for this EdificiDTO.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this EdificiDTO.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the fotoGrande value for this EdificiDTO.
     * 
     * @return fotoGrande
     */
    public java.lang.Long getFotoGrande() {
        return fotoGrande;
    }


    /**
     * Sets the fotoGrande value for this EdificiDTO.
     * 
     * @param fotoGrande
     */
    public void setFotoGrande(java.lang.Long fotoGrande) {
        this.fotoGrande = fotoGrande;
    }


    /**
     * Gets the fotoPequenya value for this EdificiDTO.
     * 
     * @return fotoPequenya
     */
    public java.lang.Long getFotoPequenya() {
        return fotoPequenya;
    }


    /**
     * Sets the fotoPequenya value for this EdificiDTO.
     * 
     * @param fotoPequenya
     */
    public void setFotoPequenya(java.lang.Long fotoPequenya) {
        this.fotoPequenya = fotoPequenya;
    }


    /**
     * Gets the id value for this EdificiDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this EdificiDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the latitud value for this EdificiDTO.
     * 
     * @return latitud
     */
    public java.lang.String getLatitud() {
        return latitud;
    }


    /**
     * Sets the latitud value for this EdificiDTO.
     * 
     * @param latitud
     */
    public void setLatitud(java.lang.String latitud) {
        this.latitud = latitud;
    }


    /**
     * Gets the longitud value for this EdificiDTO.
     * 
     * @return longitud
     */
    public java.lang.String getLongitud() {
        return longitud;
    }


    /**
     * Sets the longitud value for this EdificiDTO.
     * 
     * @param longitud
     */
    public void setLongitud(java.lang.String longitud) {
        this.longitud = longitud;
    }


    /**
     * Gets the plano value for this EdificiDTO.
     * 
     * @return plano
     */
    public java.lang.Long getPlano() {
        return plano;
    }


    /**
     * Sets the plano value for this EdificiDTO.
     * 
     * @param plano
     */
    public void setPlano(java.lang.Long plano) {
        this.plano = plano;
    }


    /**
     * Gets the poblacion value for this EdificiDTO.
     * 
     * @return poblacion
     */
    public java.lang.String getPoblacion() {
        return poblacion;
    }


    /**
     * Sets the poblacion value for this EdificiDTO.
     * 
     * @param poblacion
     */
    public void setPoblacion(java.lang.String poblacion) {
        this.poblacion = poblacion;
    }


    /**
     * Gets the telefono value for this EdificiDTO.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this EdificiDTO.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EdificiDTO)) return false;
        EdificiDTO other = (EdificiDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoPostal==null && other.getCodigoPostal()==null) || 
             (this.codigoPostal!=null &&
              this.codigoPostal.equals(other.getCodigoPostal()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.fotoGrande==null && other.getFotoGrande()==null) || 
             (this.fotoGrande!=null &&
              this.fotoGrande.equals(other.getFotoGrande()))) &&
            ((this.fotoPequenya==null && other.getFotoPequenya()==null) || 
             (this.fotoPequenya!=null &&
              this.fotoPequenya.equals(other.getFotoPequenya()))) &&
            this.id == other.getId() &&
            ((this.latitud==null && other.getLatitud()==null) || 
             (this.latitud!=null &&
              this.latitud.equals(other.getLatitud()))) &&
            ((this.longitud==null && other.getLongitud()==null) || 
             (this.longitud!=null &&
              this.longitud.equals(other.getLongitud()))) &&
            ((this.plano==null && other.getPlano()==null) || 
             (this.plano!=null &&
              this.plano.equals(other.getPlano()))) &&
            ((this.poblacion==null && other.getPoblacion()==null) || 
             (this.poblacion!=null &&
              this.poblacion.equals(other.getPoblacion()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getCodigoPostal() != null) {
            _hashCode += getCodigoPostal().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getFotoGrande() != null) {
            _hashCode += getFotoGrande().hashCode();
        }
        if (getFotoPequenya() != null) {
            _hashCode += getFotoPequenya().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        if (getLatitud() != null) {
            _hashCode += getLatitud().hashCode();
        }
        if (getLongitud() != null) {
            _hashCode += getLongitud().hashCode();
        }
        if (getPlano() != null) {
            _hashCode += getPlano().hashCode();
        }
        if (getPoblacion() != null) {
            _hashCode += getPoblacion().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EdificiDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://edifici.v2.api.rolsac.caib.es", "EdificiDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPostal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoPostal"));
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
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fotoGrande");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fotoGrande"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fotoPequenya");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fotoPequenya"));
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
        elemField.setFieldName("latitud");
        elemField.setXmlName(new javax.xml.namespace.QName("", "latitud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longitud");
        elemField.setXmlName(new javax.xml.namespace.QName("", "longitud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "plano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poblacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "poblacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefono"));
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
