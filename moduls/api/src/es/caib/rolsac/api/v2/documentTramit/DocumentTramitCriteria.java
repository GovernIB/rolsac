/**
 * DocumentTramitCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.documentTramit;

public class DocumentTramitCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String tramit;

    private java.lang.String tipus;

    private java.lang.String orden;

    private java.lang.String t_titulo;

    private java.lang.String t_descripcion;

    public DocumentTramitCriteria() {
    }

    public DocumentTramitCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String tramit,
           java.lang.String tipus,
           java.lang.String orden,
           java.lang.String t_titulo,
           java.lang.String t_descripcion) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.tramit = tramit;
        this.tipus = tipus;
        this.orden = orden;
        this.t_titulo = t_titulo;
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the tramit value for this DocumentTramitCriteria.
     * 
     * @return tramit
     */
    public java.lang.String getTramit() {
        return tramit;
    }


    /**
     * Sets the tramit value for this DocumentTramitCriteria.
     * 
     * @param tramit
     */
    public void setTramit(java.lang.String tramit) {
        this.tramit = tramit;
    }


    /**
     * Gets the tipus value for this DocumentTramitCriteria.
     * 
     * @return tipus
     */
    public java.lang.String getTipus() {
        return tipus;
    }


    /**
     * Sets the tipus value for this DocumentTramitCriteria.
     * 
     * @param tipus
     */
    public void setTipus(java.lang.String tipus) {
        this.tipus = tipus;
    }


    /**
     * Gets the orden value for this DocumentTramitCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this DocumentTramitCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the t_titulo value for this DocumentTramitCriteria.
     * 
     * @return t_titulo
     */
    public java.lang.String getT_titulo() {
        return t_titulo;
    }


    /**
     * Sets the t_titulo value for this DocumentTramitCriteria.
     * 
     * @param t_titulo
     */
    public void setT_titulo(java.lang.String t_titulo) {
        this.t_titulo = t_titulo;
    }


    /**
     * Gets the t_descripcion value for this DocumentTramitCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this DocumentTramitCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentTramitCriteria)) return false;
        DocumentTramitCriteria other = (DocumentTramitCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.tramit==null && other.getTramit()==null) || 
             (this.tramit!=null &&
              this.tramit.equals(other.getTramit()))) &&
            ((this.tipus==null && other.getTipus()==null) || 
             (this.tipus!=null &&
              this.tipus.equals(other.getTipus()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.t_titulo==null && other.getT_titulo()==null) || 
             (this.t_titulo!=null &&
              this.t_titulo.equals(other.getT_titulo()))) &&
            ((this.t_descripcion==null && other.getT_descripcion()==null) || 
             (this.t_descripcion!=null &&
              this.t_descripcion.equals(other.getT_descripcion())));
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
        if (getTramit() != null) {
            _hashCode += getTramit().hashCode();
        }
        if (getTipus() != null) {
            _hashCode += getTipus().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getT_titulo() != null) {
            _hashCode += getT_titulo().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentTramitCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_descripcion"));
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
