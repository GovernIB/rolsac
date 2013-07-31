/**
 * EnllacDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.enllac;

public class EnllacDTO  implements java.io.Serializable {
    private java.lang.String enlace;

    private java.lang.Long ficha;

    private java.lang.Long id;

    private long orden;

    private java.lang.Long procedimiento;

    private java.lang.String titulo;

    public EnllacDTO() {
    }

    public EnllacDTO(
           java.lang.String enlace,
           java.lang.Long ficha,
           java.lang.Long id,
           long orden,
           java.lang.Long procedimiento,
           java.lang.String titulo) {
           this.enlace = enlace;
           this.ficha = ficha;
           this.id = id;
           this.orden = orden;
           this.procedimiento = procedimiento;
           this.titulo = titulo;
    }


    /**
     * Gets the enlace value for this EnllacDTO.
     * 
     * @return enlace
     */
    public java.lang.String getEnlace() {
        return enlace;
    }


    /**
     * Sets the enlace value for this EnllacDTO.
     * 
     * @param enlace
     */
    public void setEnlace(java.lang.String enlace) {
        this.enlace = enlace;
    }


    /**
     * Gets the ficha value for this EnllacDTO.
     * 
     * @return ficha
     */
    public java.lang.Long getFicha() {
        return ficha;
    }


    /**
     * Sets the ficha value for this EnllacDTO.
     * 
     * @param ficha
     */
    public void setFicha(java.lang.Long ficha) {
        this.ficha = ficha;
    }


    /**
     * Gets the id value for this EnllacDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this EnllacDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the orden value for this EnllacDTO.
     * 
     * @return orden
     */
    public long getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this EnllacDTO.
     * 
     * @param orden
     */
    public void setOrden(long orden) {
        this.orden = orden;
    }


    /**
     * Gets the procedimiento value for this EnllacDTO.
     * 
     * @return procedimiento
     */
    public java.lang.Long getProcedimiento() {
        return procedimiento;
    }


    /**
     * Sets the procedimiento value for this EnllacDTO.
     * 
     * @param procedimiento
     */
    public void setProcedimiento(java.lang.Long procedimiento) {
        this.procedimiento = procedimiento;
    }


    /**
     * Gets the titulo value for this EnllacDTO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this EnllacDTO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnllacDTO)) return false;
        EnllacDTO other = (EnllacDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enlace==null && other.getEnlace()==null) || 
             (this.enlace!=null &&
              this.enlace.equals(other.getEnlace()))) &&
            ((this.ficha==null && other.getFicha()==null) || 
             (this.ficha!=null &&
              this.ficha.equals(other.getFicha()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            this.orden == other.getOrden() &&
            ((this.procedimiento==null && other.getProcedimiento()==null) || 
             (this.procedimiento!=null &&
              this.procedimiento.equals(other.getProcedimiento()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo())));
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
        if (getEnlace() != null) {
            _hashCode += getEnlace().hashCode();
        }
        if (getFicha() != null) {
            _hashCode += getFicha().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        _hashCode += new Long(getOrden()).hashCode();
        if (getProcedimiento() != null) {
            _hashCode += getProcedimiento().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnllacDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enllac.v2.api.rolsac.caib.es", "EnllacDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ficha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ficha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procedimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "procedimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulo"));
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
