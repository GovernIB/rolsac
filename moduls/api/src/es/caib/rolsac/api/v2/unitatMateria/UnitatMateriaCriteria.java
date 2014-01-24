/**
 * UnitatMateriaCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.unitatMateria;

public class UnitatMateriaCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String unidadPrincipal;

    private java.lang.String t_urlUnidadMateria;

    private es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaOrdenacio[] ordenar;

    public UnitatMateriaCriteria() {
    }

    public UnitatMateriaCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String unidadPrincipal,
           java.lang.String t_urlUnidadMateria,
           es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaOrdenacio[] ordenar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.unidadPrincipal = unidadPrincipal;
        this.t_urlUnidadMateria = t_urlUnidadMateria;
        this.ordenar = ordenar;
    }


    /**
     * Gets the unidadPrincipal value for this UnitatMateriaCriteria.
     * 
     * @return unidadPrincipal
     */
    public java.lang.String getUnidadPrincipal() {
        return unidadPrincipal;
    }


    /**
     * Sets the unidadPrincipal value for this UnitatMateriaCriteria.
     * 
     * @param unidadPrincipal
     */
    public void setUnidadPrincipal(java.lang.String unidadPrincipal) {
        this.unidadPrincipal = unidadPrincipal;
    }


    /**
     * Gets the t_urlUnidadMateria value for this UnitatMateriaCriteria.
     * 
     * @return t_urlUnidadMateria
     */
    public java.lang.String getT_urlUnidadMateria() {
        return t_urlUnidadMateria;
    }


    /**
     * Sets the t_urlUnidadMateria value for this UnitatMateriaCriteria.
     * 
     * @param t_urlUnidadMateria
     */
    public void setT_urlUnidadMateria(java.lang.String t_urlUnidadMateria) {
        this.t_urlUnidadMateria = t_urlUnidadMateria;
    }


    /**
     * Gets the ordenar value for this UnitatMateriaCriteria.
     * 
     * @return ordenar
     */
    public es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaOrdenacio[] getOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this UnitatMateriaCriteria.
     * 
     * @param ordenar
     */
    public void setOrdenar(es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaOrdenacio[] ordenar) {
        this.ordenar = ordenar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnitatMateriaCriteria)) return false;
        UnitatMateriaCriteria other = (UnitatMateriaCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.unidadPrincipal==null && other.getUnidadPrincipal()==null) || 
             (this.unidadPrincipal!=null &&
              this.unidadPrincipal.equals(other.getUnidadPrincipal()))) &&
            ((this.t_urlUnidadMateria==null && other.getT_urlUnidadMateria()==null) || 
             (this.t_urlUnidadMateria!=null &&
              this.t_urlUnidadMateria.equals(other.getT_urlUnidadMateria()))) &&
            ((this.ordenar==null && other.getOrdenar()==null) || 
             (this.ordenar!=null &&
              java.util.Arrays.equals(this.ordenar, other.getOrdenar())));
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
        if (getUnidadPrincipal() != null) {
            _hashCode += getUnidadPrincipal().hashCode();
        }
        if (getT_urlUnidadMateria() != null) {
            _hashCode += getT_urlUnidadMateria().hashCode();
        }
        if (getOrdenar() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrdenar());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrdenar(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnitatMateriaCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_urlUnidadMateria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_urlUnidadMateria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://unitatMateria.v2.api.rolsac.caib.es", "UnitatMateriaOrdenacio"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "listaOrdenaciones"));
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
