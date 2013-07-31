/**
 * PerfilCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.perfil;

public class PerfilCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String codigoEstandard;

    private java.lang.String pathIconografia;

    private java.lang.String t_nombre;

    private java.lang.String t_descripcion;

    public PerfilCriteria() {
    }

    public PerfilCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String codigoEstandard,
           java.lang.String pathIconografia,
           java.lang.String t_nombre,
           java.lang.String t_descripcion) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.codigoEstandard = codigoEstandard;
        this.pathIconografia = pathIconografia;
        this.t_nombre = t_nombre;
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the codigoEstandard value for this PerfilCriteria.
     * 
     * @return codigoEstandard
     */
    public java.lang.String getCodigoEstandard() {
        return codigoEstandard;
    }


    /**
     * Sets the codigoEstandard value for this PerfilCriteria.
     * 
     * @param codigoEstandard
     */
    public void setCodigoEstandard(java.lang.String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }


    /**
     * Gets the pathIconografia value for this PerfilCriteria.
     * 
     * @return pathIconografia
     */
    public java.lang.String getPathIconografia() {
        return pathIconografia;
    }


    /**
     * Sets the pathIconografia value for this PerfilCriteria.
     * 
     * @param pathIconografia
     */
    public void setPathIconografia(java.lang.String pathIconografia) {
        this.pathIconografia = pathIconografia;
    }


    /**
     * Gets the t_nombre value for this PerfilCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this PerfilCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the t_descripcion value for this PerfilCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this PerfilCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PerfilCriteria)) return false;
        PerfilCriteria other = (PerfilCriteria) obj;
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
            ((this.pathIconografia==null && other.getPathIconografia()==null) || 
             (this.pathIconografia!=null &&
              this.pathIconografia.equals(other.getPathIconografia()))) &&
            ((this.t_nombre==null && other.getT_nombre()==null) || 
             (this.t_nombre!=null &&
              this.t_nombre.equals(other.getT_nombre()))) &&
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
        if (getCodigoEstandard() != null) {
            _hashCode += getCodigoEstandard().hashCode();
        }
        if (getPathIconografia() != null) {
            _hashCode += getPathIconografia().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PerfilCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://perfil.v2.api.rolsac.caib.es", "PerfilCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEstandard");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEstandard"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathIconografia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathIconografia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_nombre"));
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
