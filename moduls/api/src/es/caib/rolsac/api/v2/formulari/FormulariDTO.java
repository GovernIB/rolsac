/**
 * FormulariDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.formulari;

public class FormulariDTO  implements java.io.Serializable {
    private java.lang.Long archivo;

    private java.lang.Long id;

    private java.lang.Long manual;

    private java.lang.String nombre;

    private java.lang.Long tramite;

    private java.lang.String url;

    private java.lang.String urlManual;

    public FormulariDTO() {
    }

    public FormulariDTO(
           java.lang.Long archivo,
           java.lang.Long id,
           java.lang.Long manual,
           java.lang.String nombre,
           java.lang.Long tramite,
           java.lang.String url,
           java.lang.String urlManual) {
           this.archivo = archivo;
           this.id = id;
           this.manual = manual;
           this.nombre = nombre;
           this.tramite = tramite;
           this.url = url;
           this.urlManual = urlManual;
    }


    /**
     * Gets the archivo value for this FormulariDTO.
     * 
     * @return archivo
     */
    public java.lang.Long getArchivo() {
        return archivo;
    }


    /**
     * Sets the archivo value for this FormulariDTO.
     * 
     * @param archivo
     */
    public void setArchivo(java.lang.Long archivo) {
        this.archivo = archivo;
    }


    /**
     * Gets the id value for this FormulariDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this FormulariDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the manual value for this FormulariDTO.
     * 
     * @return manual
     */
    public java.lang.Long getManual() {
        return manual;
    }


    /**
     * Sets the manual value for this FormulariDTO.
     * 
     * @param manual
     */
    public void setManual(java.lang.Long manual) {
        this.manual = manual;
    }


    /**
     * Gets the nombre value for this FormulariDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this FormulariDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the tramite value for this FormulariDTO.
     * 
     * @return tramite
     */
    public java.lang.Long getTramite() {
        return tramite;
    }


    /**
     * Sets the tramite value for this FormulariDTO.
     * 
     * @param tramite
     */
    public void setTramite(java.lang.Long tramite) {
        this.tramite = tramite;
    }


    /**
     * Gets the url value for this FormulariDTO.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this FormulariDTO.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the urlManual value for this FormulariDTO.
     * 
     * @return urlManual
     */
    public java.lang.String getUrlManual() {
        return urlManual;
    }


    /**
     * Sets the urlManual value for this FormulariDTO.
     * 
     * @param urlManual
     */
    public void setUrlManual(java.lang.String urlManual) {
        this.urlManual = urlManual;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FormulariDTO)) return false;
        FormulariDTO other = (FormulariDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivo==null && other.getArchivo()==null) || 
             (this.archivo!=null &&
              this.archivo.equals(other.getArchivo()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.manual==null && other.getManual()==null) || 
             (this.manual!=null &&
              this.manual.equals(other.getManual()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.tramite==null && other.getTramite()==null) || 
             (this.tramite!=null &&
              this.tramite.equals(other.getTramite()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.urlManual==null && other.getUrlManual()==null) || 
             (this.urlManual!=null &&
              this.urlManual.equals(other.getUrlManual())));
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
        if (getArchivo() != null) {
            _hashCode += getArchivo().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getManual() != null) {
            _hashCode += getManual().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getTramite() != null) {
            _hashCode += getTramite().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getUrlManual() != null) {
            _hashCode += getUrlManual().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FormulariDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://formulari.v2.api.rolsac.caib.es", "FormulariDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "archivo"));
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
        elemField.setFieldName("manual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manual"));
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
        elemField.setFieldName("tramite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlManual");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlManual"));
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
