/**
 * DocumentTramitDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.documentTramit;

public class DocumentTramitDTO  implements java.io.Serializable {
    private java.lang.Long archivo;

    private java.lang.String descripcion;

    private java.lang.Long id;

    private java.lang.Long orden;

    private java.lang.Integer tipus;

    private java.lang.String titulo;

    private java.lang.Long tramit;

    private java.lang.Long docCatalogo;

    private java.lang.Long excepcioDocumentacio;

    public DocumentTramitDTO() {
    }

    public DocumentTramitDTO(
           java.lang.Long archivo,
           java.lang.String descripcion,
           java.lang.Long id,
           java.lang.Long orden,
           java.lang.Integer tipus,
           java.lang.String titulo,
           java.lang.Long tramit,
           java.lang.Long docCatalogo,
           java.lang.Long excepcioDocumentacio) {
           this.archivo = archivo;
           this.descripcion = descripcion;
           this.id = id;
           this.orden = orden;
           this.tipus = tipus;
           this.titulo = titulo;
           this.tramit = tramit;
           this.docCatalogo = docCatalogo;
           this.excepcioDocumentacio = excepcioDocumentacio;
    }


    /**
     * Gets the archivo value for this DocumentTramitDTO.
     * 
     * @return archivo
     */
    public java.lang.Long getArchivo() {
        return archivo;
    }


    /**
     * Sets the archivo value for this DocumentTramitDTO.
     * 
     * @param archivo
     */
    public void setArchivo(java.lang.Long archivo) {
        this.archivo = archivo;
    }


    /**
     * Gets the descripcion value for this DocumentTramitDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this DocumentTramitDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the id value for this DocumentTramitDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this DocumentTramitDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the orden value for this DocumentTramitDTO.
     * 
     * @return orden
     */
    public java.lang.Long getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this DocumentTramitDTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Long orden) {
        this.orden = orden;
    }


    /**
     * Gets the tipus value for this DocumentTramitDTO.
     * 
     * @return tipus
     */
    public java.lang.Integer getTipus() {
        return tipus;
    }


    /**
     * Sets the tipus value for this DocumentTramitDTO.
     * 
     * @param tipus
     */
    public void setTipus(java.lang.Integer tipus) {
        this.tipus = tipus;
    }


    /**
     * Gets the titulo value for this DocumentTramitDTO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this DocumentTramitDTO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the tramit value for this DocumentTramitDTO.
     * 
     * @return tramit
     */
    public java.lang.Long getTramit() {
        return tramit;
    }


    /**
     * Sets the tramit value for this DocumentTramitDTO.
     * 
     * @param tramit
     */
    public void setTramit(java.lang.Long tramit) {
        this.tramit = tramit;
    }


    /**
     * Gets the docCatalogo value for this DocumentTramitDTO.
     * 
     * @return docCatalogo
     */
    public java.lang.Long getDocCatalogo() {
        return docCatalogo;
    }


    /**
     * Sets the docCatalogo value for this DocumentTramitDTO.
     * 
     * @param docCatalogo
     */
    public void setDocCatalogo(java.lang.Long docCatalogo) {
        this.docCatalogo = docCatalogo;
    }


    /**
     * Gets the excepcioDocumentacio value for this DocumentTramitDTO.
     * 
     * @return excepcioDocumentacio
     */
    public java.lang.Long getExcepcioDocumentacio() {
        return excepcioDocumentacio;
    }


    /**
     * Sets the excepcioDocumentacio value for this DocumentTramitDTO.
     * 
     * @param excepcioDocumentacio
     */
    public void setExcepcioDocumentacio(java.lang.Long excepcioDocumentacio) {
        this.excepcioDocumentacio = excepcioDocumentacio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentTramitDTO)) return false;
        DocumentTramitDTO other = (DocumentTramitDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archivo==null && other.getArchivo()==null) || 
             (this.archivo!=null &&
              this.archivo.equals(other.getArchivo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.tipus==null && other.getTipus()==null) || 
             (this.tipus!=null &&
              this.tipus.equals(other.getTipus()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.tramit==null && other.getTramit()==null) || 
             (this.tramit!=null &&
              this.tramit.equals(other.getTramit()))) &&
            ((this.docCatalogo==null && other.getDocCatalogo()==null) || 
             (this.docCatalogo!=null &&
              this.docCatalogo.equals(other.getDocCatalogo()))) &&
            ((this.excepcioDocumentacio==null && other.getExcepcioDocumentacio()==null) || 
             (this.excepcioDocumentacio!=null &&
              this.excepcioDocumentacio.equals(other.getExcepcioDocumentacio())));
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
        if (getArchivo() != null) {
            _hashCode += getArchivo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getTipus() != null) {
            _hashCode += getTipus().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getTramit() != null) {
            _hashCode += getTramit().hashCode();
        }
        if (getDocCatalogo() != null) {
            _hashCode += getDocCatalogo().hashCode();
        }
        if (getExcepcioDocumentacio() != null) {
            _hashCode += getExcepcioDocumentacio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentTramitDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://documentTramit.v2.api.rolsac.caib.es", "DocumentTramitDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "archivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excepcioDocumentacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excepcioDocumentacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
