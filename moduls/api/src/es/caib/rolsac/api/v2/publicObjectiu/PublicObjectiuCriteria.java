/**
 * PublicObjectiuCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.publicObjectiu;

public class PublicObjectiuCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String orden;

    private java.lang.String codigoEstandar;

    private java.lang.String t_titulo;

    private java.lang.String t_descripcion;

    private java.lang.String t_palabrasclave;

    private java.lang.String procedimiento;

    private java.lang.String ficha;

    public PublicObjectiuCriteria() {
    }

    public PublicObjectiuCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String orden,
           java.lang.String codigoEstandar,
           java.lang.String t_titulo,
           java.lang.String t_descripcion,
           java.lang.String t_palabrasclave,
           java.lang.String procedimiento,
           java.lang.String ficha) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.orden = orden;
        this.codigoEstandar = codigoEstandar;
        this.t_titulo = t_titulo;
        this.t_descripcion = t_descripcion;
        this.t_palabrasclave = t_palabrasclave;
        this.procedimiento = procedimiento;
        this.ficha = ficha;
    }


    /**
     * Gets the orden value for this PublicObjectiuCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this PublicObjectiuCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the codigoEstandar value for this PublicObjectiuCriteria.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this PublicObjectiuCriteria.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the t_titulo value for this PublicObjectiuCriteria.
     * 
     * @return t_titulo
     */
    public java.lang.String getT_titulo() {
        return t_titulo;
    }


    /**
     * Sets the t_titulo value for this PublicObjectiuCriteria.
     * 
     * @param t_titulo
     */
    public void setT_titulo(java.lang.String t_titulo) {
        this.t_titulo = t_titulo;
    }


    /**
     * Gets the t_descripcion value for this PublicObjectiuCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this PublicObjectiuCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the t_palabrasclave value for this PublicObjectiuCriteria.
     * 
     * @return t_palabrasclave
     */
    public java.lang.String getT_palabrasclave() {
        return t_palabrasclave;
    }


    /**
     * Sets the t_palabrasclave value for this PublicObjectiuCriteria.
     * 
     * @param t_palabrasclave
     */
    public void setT_palabrasclave(java.lang.String t_palabrasclave) {
        this.t_palabrasclave = t_palabrasclave;
    }


    /**
     * Gets the procedimiento value for this PublicObjectiuCriteria.
     * 
     * @return procedimiento
     */
    public java.lang.String getProcedimiento() {
        return procedimiento;
    }


    /**
     * Sets the procedimiento value for this PublicObjectiuCriteria.
     * 
     * @param procedimiento
     */
    public void setProcedimiento(java.lang.String procedimiento) {
        this.procedimiento = procedimiento;
    }


    /**
     * Gets the ficha value for this PublicObjectiuCriteria.
     * 
     * @return ficha
     */
    public java.lang.String getFicha() {
        return ficha;
    }


    /**
     * Sets the ficha value for this PublicObjectiuCriteria.
     * 
     * @param ficha
     */
    public void setFicha(java.lang.String ficha) {
        this.ficha = ficha;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PublicObjectiuCriteria)) return false;
        PublicObjectiuCriteria other = (PublicObjectiuCriteria) obj;
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
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.t_titulo==null && other.getT_titulo()==null) || 
             (this.t_titulo!=null &&
              this.t_titulo.equals(other.getT_titulo()))) &&
            ((this.t_descripcion==null && other.getT_descripcion()==null) || 
             (this.t_descripcion!=null &&
              this.t_descripcion.equals(other.getT_descripcion()))) &&
            ((this.t_palabrasclave==null && other.getT_palabrasclave()==null) || 
             (this.t_palabrasclave!=null &&
              this.t_palabrasclave.equals(other.getT_palabrasclave()))) &&
            ((this.procedimiento==null && other.getProcedimiento()==null) || 
             (this.procedimiento!=null &&
              this.procedimiento.equals(other.getProcedimiento()))) &&
            ((this.ficha==null && other.getFicha()==null) || 
             (this.ficha!=null &&
              this.ficha.equals(other.getFicha())));
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
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getT_titulo() != null) {
            _hashCode += getT_titulo().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        if (getT_palabrasclave() != null) {
            _hashCode += getT_palabrasclave().hashCode();
        }
        if (getProcedimiento() != null) {
            _hashCode += getProcedimiento().hashCode();
        }
        if (getFicha() != null) {
            _hashCode += getFicha().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PublicObjectiuCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publicObjectiu.v2.api.rolsac.caib.es", "PublicObjectiuCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_palabrasclave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_palabrasclave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procedimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "procedimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ficha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ficha"));
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
