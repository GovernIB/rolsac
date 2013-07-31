/**
 * TaxaCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.taxa;

public class TaxaCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String orden;

    private java.lang.String tramit;

    private java.lang.String t_descripcio;

    private java.lang.String t_codificacio;

    private java.lang.String t_formapagament;

    public TaxaCriteria() {
    }

    public TaxaCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String orden,
           java.lang.String tramit,
           java.lang.String t_descripcio,
           java.lang.String t_codificacio,
           java.lang.String t_formapagament) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.orden = orden;
        this.tramit = tramit;
        this.t_descripcio = t_descripcio;
        this.t_codificacio = t_codificacio;
        this.t_formapagament = t_formapagament;
    }


    /**
     * Gets the orden value for this TaxaCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this TaxaCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the tramit value for this TaxaCriteria.
     * 
     * @return tramit
     */
    public java.lang.String getTramit() {
        return tramit;
    }


    /**
     * Sets the tramit value for this TaxaCriteria.
     * 
     * @param tramit
     */
    public void setTramit(java.lang.String tramit) {
        this.tramit = tramit;
    }


    /**
     * Gets the t_descripcio value for this TaxaCriteria.
     * 
     * @return t_descripcio
     */
    public java.lang.String getT_descripcio() {
        return t_descripcio;
    }


    /**
     * Sets the t_descripcio value for this TaxaCriteria.
     * 
     * @param t_descripcio
     */
    public void setT_descripcio(java.lang.String t_descripcio) {
        this.t_descripcio = t_descripcio;
    }


    /**
     * Gets the t_codificacio value for this TaxaCriteria.
     * 
     * @return t_codificacio
     */
    public java.lang.String getT_codificacio() {
        return t_codificacio;
    }


    /**
     * Sets the t_codificacio value for this TaxaCriteria.
     * 
     * @param t_codificacio
     */
    public void setT_codificacio(java.lang.String t_codificacio) {
        this.t_codificacio = t_codificacio;
    }


    /**
     * Gets the t_formapagament value for this TaxaCriteria.
     * 
     * @return t_formapagament
     */
    public java.lang.String getT_formapagament() {
        return t_formapagament;
    }


    /**
     * Sets the t_formapagament value for this TaxaCriteria.
     * 
     * @param t_formapagament
     */
    public void setT_formapagament(java.lang.String t_formapagament) {
        this.t_formapagament = t_formapagament;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxaCriteria)) return false;
        TaxaCriteria other = (TaxaCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.tramit==null && other.getTramit()==null) || 
             (this.tramit!=null &&
              this.tramit.equals(other.getTramit()))) &&
            ((this.t_descripcio==null && other.getT_descripcio()==null) || 
             (this.t_descripcio!=null &&
              this.t_descripcio.equals(other.getT_descripcio()))) &&
            ((this.t_codificacio==null && other.getT_codificacio()==null) || 
             (this.t_codificacio!=null &&
              this.t_codificacio.equals(other.getT_codificacio()))) &&
            ((this.t_formapagament==null && other.getT_formapagament()==null) || 
             (this.t_formapagament!=null &&
              this.t_formapagament.equals(other.getT_formapagament())));
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
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getTramit() != null) {
            _hashCode += getTramit().hashCode();
        }
        if (getT_descripcio() != null) {
            _hashCode += getT_descripcio().hashCode();
        }
        if (getT_codificacio() != null) {
            _hashCode += getT_codificacio().hashCode();
        }
        if (getT_formapagament() != null) {
            _hashCode += getT_formapagament().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxaCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://taxa.v2.api.rolsac.caib.es", "TaxaCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_descripcio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_descripcio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_codificacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_codificacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_formapagament");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_formapagament"));
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
