/**
 * MateriaCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.materia;

public class MateriaCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String codiHita;

    private java.lang.String codigoEstandar;

    private java.lang.Boolean destacada;

    private java.lang.String t_nombre;

    private java.lang.String t_descripcion;

    private java.lang.String t_palabrasclave;

    private es.caib.rolsac.api.v2.materia.MateriaOrdenacio[] ordenar;

    public MateriaCriteria() {
    }

    public MateriaCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String codiHita,
           java.lang.String codigoEstandar,
           java.lang.Boolean destacada,
           java.lang.String t_nombre,
           java.lang.String t_descripcion,
           java.lang.String t_palabrasclave,
           es.caib.rolsac.api.v2.materia.MateriaOrdenacio[] ordenar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.codiHita = codiHita;
        this.codigoEstandar = codigoEstandar;
        this.destacada = destacada;
        this.t_nombre = t_nombre;
        this.t_descripcion = t_descripcion;
        this.t_palabrasclave = t_palabrasclave;
        this.ordenar = ordenar;
    }


    /**
     * Gets the codiHita value for this MateriaCriteria.
     * 
     * @return codiHita
     */
    public java.lang.String getCodiHita() {
        return codiHita;
    }


    /**
     * Sets the codiHita value for this MateriaCriteria.
     * 
     * @param codiHita
     */
    public void setCodiHita(java.lang.String codiHita) {
        this.codiHita = codiHita;
    }


    /**
     * Gets the codigoEstandar value for this MateriaCriteria.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this MateriaCriteria.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the destacada value for this MateriaCriteria.
     * 
     * @return destacada
     */
    public java.lang.Boolean getDestacada() {
        return destacada;
    }


    /**
     * Sets the destacada value for this MateriaCriteria.
     * 
     * @param destacada
     */
    public void setDestacada(java.lang.Boolean destacada) {
        this.destacada = destacada;
    }


    /**
     * Gets the t_nombre value for this MateriaCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this MateriaCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the t_descripcion value for this MateriaCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this MateriaCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the t_palabrasclave value for this MateriaCriteria.
     * 
     * @return t_palabrasclave
     */
    public java.lang.String getT_palabrasclave() {
        return t_palabrasclave;
    }


    /**
     * Sets the t_palabrasclave value for this MateriaCriteria.
     * 
     * @param t_palabrasclave
     */
    public void setT_palabrasclave(java.lang.String t_palabrasclave) {
        this.t_palabrasclave = t_palabrasclave;
    }


    /**
     * Gets the ordenar value for this MateriaCriteria.
     * 
     * @return ordenar
     */
    public es.caib.rolsac.api.v2.materia.MateriaOrdenacio[] getOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this MateriaCriteria.
     * 
     * @param ordenar
     */
    public void setOrdenar(es.caib.rolsac.api.v2.materia.MateriaOrdenacio[] ordenar) {
        this.ordenar = ordenar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MateriaCriteria)) return false;
        MateriaCriteria other = (MateriaCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codiHita==null && other.getCodiHita()==null) || 
             (this.codiHita!=null &&
              this.codiHita.equals(other.getCodiHita()))) &&
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.destacada==null && other.getDestacada()==null) || 
             (this.destacada!=null &&
              this.destacada.equals(other.getDestacada()))) &&
            ((this.t_nombre==null && other.getT_nombre()==null) || 
             (this.t_nombre!=null &&
              this.t_nombre.equals(other.getT_nombre()))) &&
            ((this.t_descripcion==null && other.getT_descripcion()==null) || 
             (this.t_descripcion!=null &&
              this.t_descripcion.equals(other.getT_descripcion()))) &&
            ((this.t_palabrasclave==null && other.getT_palabrasclave()==null) || 
             (this.t_palabrasclave!=null &&
              this.t_palabrasclave.equals(other.getT_palabrasclave()))) &&
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
        if (getCodiHita() != null) {
            _hashCode += getCodiHita().hashCode();
        }
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getDestacada() != null) {
            _hashCode += getDestacada().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        if (getT_palabrasclave() != null) {
            _hashCode += getT_palabrasclave().hashCode();
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
        new org.apache.axis.description.TypeDesc(MateriaCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiHita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiHita"));
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
        elemField.setFieldName("destacada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destacada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_palabrasclave");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_palabrasclave"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://materia.v2.api.rolsac.caib.es", "MateriaOrdenacio"));
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
