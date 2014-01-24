/**
 * MateriaAgrupacioCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.materiaAgrupacio;

public class MateriaAgrupacioCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String orden;

    private es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioOrdenacio[] ordenar;

    public MateriaAgrupacioCriteria() {
    }

    public MateriaAgrupacioCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String orden,
           es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioOrdenacio[] ordenar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.orden = orden;
        this.ordenar = ordenar;
    }


    /**
     * Gets the orden value for this MateriaAgrupacioCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this MateriaAgrupacioCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the ordenar value for this MateriaAgrupacioCriteria.
     * 
     * @return ordenar
     */
    public es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioOrdenacio[] getOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this MateriaAgrupacioCriteria.
     * 
     * @param ordenar
     */
    public void setOrdenar(es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioOrdenacio[] ordenar) {
        this.ordenar = ordenar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MateriaAgrupacioCriteria)) return false;
        MateriaAgrupacioCriteria other = (MateriaAgrupacioCriteria) obj;
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
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
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
        new org.apache.axis.description.TypeDesc(MateriaAgrupacioCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://materiaAgrupacio.v2.api.rolsac.caib.es", "MateriaAgrupacioOrdenacio"));
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
