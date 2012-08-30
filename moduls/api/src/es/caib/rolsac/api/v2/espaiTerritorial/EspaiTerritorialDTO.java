/**
 * EspaiTerritorialDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.espaiTerritorial;

public class EspaiTerritorialDTO  implements java.io.Serializable {
    private java.lang.String coordenadas;

    private java.lang.Long id;

    private java.lang.Long logo;

    private java.lang.Long mapa;

    private java.lang.Integer nivel;

    private java.lang.String nombre;

    private java.lang.Long padre;

    public EspaiTerritorialDTO() {
    }

    public EspaiTerritorialDTO(
           java.lang.String coordenadas,
           java.lang.Long id,
           java.lang.Long logo,
           java.lang.Long mapa,
           java.lang.Integer nivel,
           java.lang.String nombre,
           java.lang.Long padre) {
           this.coordenadas = coordenadas;
           this.id = id;
           this.logo = logo;
           this.mapa = mapa;
           this.nivel = nivel;
           this.nombre = nombre;
           this.padre = padre;
    }


    /**
     * Gets the coordenadas value for this EspaiTerritorialDTO.
     * 
     * @return coordenadas
     */
    public java.lang.String getCoordenadas() {
        return coordenadas;
    }


    /**
     * Sets the coordenadas value for this EspaiTerritorialDTO.
     * 
     * @param coordenadas
     */
    public void setCoordenadas(java.lang.String coordenadas) {
        this.coordenadas = coordenadas;
    }


    /**
     * Gets the id value for this EspaiTerritorialDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this EspaiTerritorialDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the logo value for this EspaiTerritorialDTO.
     * 
     * @return logo
     */
    public java.lang.Long getLogo() {
        return logo;
    }


    /**
     * Sets the logo value for this EspaiTerritorialDTO.
     * 
     * @param logo
     */
    public void setLogo(java.lang.Long logo) {
        this.logo = logo;
    }


    /**
     * Gets the mapa value for this EspaiTerritorialDTO.
     * 
     * @return mapa
     */
    public java.lang.Long getMapa() {
        return mapa;
    }


    /**
     * Sets the mapa value for this EspaiTerritorialDTO.
     * 
     * @param mapa
     */
    public void setMapa(java.lang.Long mapa) {
        this.mapa = mapa;
    }


    /**
     * Gets the nivel value for this EspaiTerritorialDTO.
     * 
     * @return nivel
     */
    public java.lang.Integer getNivel() {
        return nivel;
    }


    /**
     * Sets the nivel value for this EspaiTerritorialDTO.
     * 
     * @param nivel
     */
    public void setNivel(java.lang.Integer nivel) {
        this.nivel = nivel;
    }


    /**
     * Gets the nombre value for this EspaiTerritorialDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this EspaiTerritorialDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the padre value for this EspaiTerritorialDTO.
     * 
     * @return padre
     */
    public java.lang.Long getPadre() {
        return padre;
    }


    /**
     * Sets the padre value for this EspaiTerritorialDTO.
     * 
     * @param padre
     */
    public void setPadre(java.lang.Long padre) {
        this.padre = padre;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EspaiTerritorialDTO)) return false;
        EspaiTerritorialDTO other = (EspaiTerritorialDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.coordenadas==null && other.getCoordenadas()==null) || 
             (this.coordenadas!=null &&
              this.coordenadas.equals(other.getCoordenadas()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.logo==null && other.getLogo()==null) || 
             (this.logo!=null &&
              this.logo.equals(other.getLogo()))) &&
            ((this.mapa==null && other.getMapa()==null) || 
             (this.mapa!=null &&
              this.mapa.equals(other.getMapa()))) &&
            ((this.nivel==null && other.getNivel()==null) || 
             (this.nivel!=null &&
              this.nivel.equals(other.getNivel()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.padre==null && other.getPadre()==null) || 
             (this.padre!=null &&
              this.padre.equals(other.getPadre())));
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
        if (getCoordenadas() != null) {
            _hashCode += getCoordenadas().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLogo() != null) {
            _hashCode += getLogo().hashCode();
        }
        if (getMapa() != null) {
            _hashCode += getMapa().hashCode();
        }
        if (getNivel() != null) {
            _hashCode += getNivel().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getPadre() != null) {
            _hashCode += getPadre().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EspaiTerritorialDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coordenadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coordenadas"));
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
        elemField.setFieldName("logo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mapa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mapa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nivel"));
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
        elemField.setFieldName("padre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "padre"));
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
