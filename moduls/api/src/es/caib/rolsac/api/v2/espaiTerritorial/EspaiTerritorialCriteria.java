/**
 * EspaiTerritorialCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.espaiTerritorial;

public class EspaiTerritorialCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String nivel;

    private java.lang.String coordenadas;

    private java.lang.String t_nombre;

    public EspaiTerritorialCriteria() {
    }

    public EspaiTerritorialCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String nivel,
           java.lang.String coordenadas,
           java.lang.String t_nombre) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.nivel = nivel;
        this.coordenadas = coordenadas;
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the nivel value for this EspaiTerritorialCriteria.
     * 
     * @return nivel
     */
    public java.lang.String getNivel() {
        return nivel;
    }


    /**
     * Sets the nivel value for this EspaiTerritorialCriteria.
     * 
     * @param nivel
     */
    public void setNivel(java.lang.String nivel) {
        this.nivel = nivel;
    }


    /**
     * Gets the coordenadas value for this EspaiTerritorialCriteria.
     * 
     * @return coordenadas
     */
    public java.lang.String getCoordenadas() {
        return coordenadas;
    }


    /**
     * Sets the coordenadas value for this EspaiTerritorialCriteria.
     * 
     * @param coordenadas
     */
    public void setCoordenadas(java.lang.String coordenadas) {
        this.coordenadas = coordenadas;
    }


    /**
     * Gets the t_nombre value for this EspaiTerritorialCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this EspaiTerritorialCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EspaiTerritorialCriteria)) return false;
        EspaiTerritorialCriteria other = (EspaiTerritorialCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.nivel==null && other.getNivel()==null) || 
             (this.nivel!=null &&
              this.nivel.equals(other.getNivel()))) &&
            ((this.coordenadas==null && other.getCoordenadas()==null) || 
             (this.coordenadas!=null &&
              this.coordenadas.equals(other.getCoordenadas()))) &&
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
        if (getNivel() != null) {
            _hashCode += getNivel().hashCode();
        }
        if (getCoordenadas() != null) {
            _hashCode += getCoordenadas().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EspaiTerritorialCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://espaiTerritorial.v2.api.rolsac.caib.es", "EspaiTerritorialCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coordenadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coordenadas"));
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
