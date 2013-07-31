/**
 * BasicCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.general;

public abstract class BasicCriteria  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String idioma;

    private java.lang.String inici;

    private java.lang.String ordenacio;

    private java.lang.String tamany;

    public BasicCriteria() {
    }

    public BasicCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany) {
           this.id = id;
           this.idioma = idioma;
           this.inici = inici;
           this.ordenacio = ordenacio;
           this.tamany = tamany;
    }


    /**
     * Gets the id value for this BasicCriteria.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this BasicCriteria.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the idioma value for this BasicCriteria.
     * 
     * @return idioma
     */
    public java.lang.String getIdioma() {
        return idioma;
    }


    /**
     * Sets the idioma value for this BasicCriteria.
     * 
     * @param idioma
     */
    public void setIdioma(java.lang.String idioma) {
        this.idioma = idioma;
    }


    /**
     * Gets the inici value for this BasicCriteria.
     * 
     * @return inici
     */
    public java.lang.String getInici() {
        return inici;
    }


    /**
     * Sets the inici value for this BasicCriteria.
     * 
     * @param inici
     */
    public void setInici(java.lang.String inici) {
        this.inici = inici;
    }


    /**
     * Gets the ordenacio value for this BasicCriteria.
     * 
     * @return ordenacio
     */
    public java.lang.String getOrdenacio() {
        return ordenacio;
    }


    /**
     * Sets the ordenacio value for this BasicCriteria.
     * 
     * @param ordenacio
     */
    public void setOrdenacio(java.lang.String ordenacio) {
        this.ordenacio = ordenacio;
    }


    /**
     * Gets the tamany value for this BasicCriteria.
     * 
     * @return tamany
     */
    public java.lang.String getTamany() {
        return tamany;
    }


    /**
     * Sets the tamany value for this BasicCriteria.
     * 
     * @param tamany
     */
    public void setTamany(java.lang.String tamany) {
        this.tamany = tamany;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BasicCriteria)) return false;
        BasicCriteria other = (BasicCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idioma==null && other.getIdioma()==null) || 
             (this.idioma!=null &&
              this.idioma.equals(other.getIdioma()))) &&
            ((this.inici==null && other.getInici()==null) || 
             (this.inici!=null &&
              this.inici.equals(other.getInici()))) &&
            ((this.ordenacio==null && other.getOrdenacio()==null) || 
             (this.ordenacio!=null &&
              this.ordenacio.equals(other.getOrdenacio()))) &&
            ((this.tamany==null && other.getTamany()==null) || 
             (this.tamany!=null &&
              this.tamany.equals(other.getTamany())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdioma() != null) {
            _hashCode += getIdioma().hashCode();
        }
        if (getInici() != null) {
            _hashCode += getInici().hashCode();
        }
        if (getOrdenacio() != null) {
            _hashCode += getOrdenacio().hashCode();
        }
        if (getTamany() != null) {
            _hashCode += getTamany().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BasicCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://general.v2.api.rolsac.caib.es", "BasicCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idioma");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idioma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inici");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inici"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tamany");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tamany"));
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
