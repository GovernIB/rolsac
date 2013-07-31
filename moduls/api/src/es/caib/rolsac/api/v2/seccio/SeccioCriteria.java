/**
 * SeccioCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.seccio;

public class SeccioCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String codigoEstandard;

    private java.lang.String perfil;

    private java.lang.String orden;

    private java.lang.Boolean arrel;

    private java.lang.String t_descripcion;

    private java.lang.String t_nombre;

    public SeccioCriteria() {
    }

    public SeccioCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String codigoEstandard,
           java.lang.String perfil,
           java.lang.String orden,
           java.lang.Boolean arrel,
           java.lang.String t_descripcion,
           java.lang.String t_nombre) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.codigoEstandard = codigoEstandard;
        this.perfil = perfil;
        this.orden = orden;
        this.arrel = arrel;
        this.t_descripcion = t_descripcion;
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the codigoEstandard value for this SeccioCriteria.
     * 
     * @return codigoEstandard
     */
    public java.lang.String getCodigoEstandard() {
        return codigoEstandard;
    }


    /**
     * Sets the codigoEstandard value for this SeccioCriteria.
     * 
     * @param codigoEstandard
     */
    public void setCodigoEstandard(java.lang.String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }


    /**
     * Gets the perfil value for this SeccioCriteria.
     * 
     * @return perfil
     */
    public java.lang.String getPerfil() {
        return perfil;
    }


    /**
     * Sets the perfil value for this SeccioCriteria.
     * 
     * @param perfil
     */
    public void setPerfil(java.lang.String perfil) {
        this.perfil = perfil;
    }


    /**
     * Gets the orden value for this SeccioCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this SeccioCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the arrel value for this SeccioCriteria.
     * 
     * @return arrel
     */
    public java.lang.Boolean getArrel() {
        return arrel;
    }


    /**
     * Sets the arrel value for this SeccioCriteria.
     * 
     * @param arrel
     */
    public void setArrel(java.lang.Boolean arrel) {
        this.arrel = arrel;
    }


    /**
     * Gets the t_descripcion value for this SeccioCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this SeccioCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the t_nombre value for this SeccioCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this SeccioCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SeccioCriteria)) return false;
        SeccioCriteria other = (SeccioCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoEstandard==null && other.getCodigoEstandard()==null) || 
             (this.codigoEstandard!=null &&
              this.codigoEstandard.equals(other.getCodigoEstandard()))) &&
            ((this.perfil==null && other.getPerfil()==null) || 
             (this.perfil!=null &&
              this.perfil.equals(other.getPerfil()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.arrel==null && other.getArrel()==null) || 
             (this.arrel!=null &&
              this.arrel.equals(other.getArrel()))) &&
            ((this.t_descripcion==null && other.getT_descripcion()==null) || 
             (this.t_descripcion!=null &&
              this.t_descripcion.equals(other.getT_descripcion()))) &&
            ((this.t_nombre==null && other.getT_nombre()==null) || 
             (this.t_nombre!=null &&
              this.t_nombre.equals(other.getT_nombre())));
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
        if (getCodigoEstandard() != null) {
            _hashCode += getCodigoEstandard().hashCode();
        }
        if (getPerfil() != null) {
            _hashCode += getPerfil().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getArrel() != null) {
            _hashCode += getArrel().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SeccioCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://seccio.v2.api.rolsac.caib.es", "SeccioCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstandard");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEstandard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "perfil"));
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
        elemField.setFieldName("arrel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_nombre"));
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
