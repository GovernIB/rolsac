/**
 * TramitCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.tramit;

public class TramitCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String fase;

    private java.lang.String orden;

    private java.lang.String versio;

    private java.lang.String validacio;

    private java.util.Calendar dataCaducitat;

    private java.util.Calendar dataPublicacio;

    private java.util.Calendar dataActualitzacio;

    private java.lang.String codiVuds;

    private java.lang.String descCodiVuds;

    private java.lang.String idTraTel;

    private java.lang.String urlExterna;

    private java.lang.String dataActualitzacioVuds;

    private java.lang.String t_nombre;

    private java.lang.String t_descripcion;

    private java.lang.String t_documentacion;

    private java.lang.String t_requisits;

    private java.lang.String t_observaciones;

    private java.lang.String t_plazos;

    private java.lang.String t_lugar;

    public TramitCriteria() {
    }

    public TramitCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String fase,
           java.lang.String orden,
           java.lang.String versio,
           java.lang.String validacio,
           java.util.Calendar dataCaducitat,
           java.util.Calendar dataPublicacio,
           java.util.Calendar dataActualitzacio,
           java.lang.String codiVuds,
           java.lang.String descCodiVuds,
           java.lang.String idTraTel,
           java.lang.String urlExterna,
           java.lang.String dataActualitzacioVuds,
           java.lang.String t_nombre,
           java.lang.String t_descripcion,
           java.lang.String t_documentacion,
           java.lang.String t_requisits,
           java.lang.String t_observaciones,
           java.lang.String t_plazos,
           java.lang.String t_lugar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.fase = fase;
        this.orden = orden;
        this.versio = versio;
        this.validacio = validacio;
        this.dataCaducitat = dataCaducitat;
        this.dataPublicacio = dataPublicacio;
        this.dataActualitzacio = dataActualitzacio;
        this.codiVuds = codiVuds;
        this.descCodiVuds = descCodiVuds;
        this.idTraTel = idTraTel;
        this.urlExterna = urlExterna;
        this.dataActualitzacioVuds = dataActualitzacioVuds;
        this.t_nombre = t_nombre;
        this.t_descripcion = t_descripcion;
        this.t_documentacion = t_documentacion;
        this.t_requisits = t_requisits;
        this.t_observaciones = t_observaciones;
        this.t_plazos = t_plazos;
        this.t_lugar = t_lugar;
    }


    /**
     * Gets the fase value for this TramitCriteria.
     * 
     * @return fase
     */
    public java.lang.String getFase() {
        return fase;
    }


    /**
     * Sets the fase value for this TramitCriteria.
     * 
     * @param fase
     */
    public void setFase(java.lang.String fase) {
        this.fase = fase;
    }


    /**
     * Gets the orden value for this TramitCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this TramitCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the versio value for this TramitCriteria.
     * 
     * @return versio
     */
    public java.lang.String getVersio() {
        return versio;
    }


    /**
     * Sets the versio value for this TramitCriteria.
     * 
     * @param versio
     */
    public void setVersio(java.lang.String versio) {
        this.versio = versio;
    }


    /**
     * Gets the validacio value for this TramitCriteria.
     * 
     * @return validacio
     */
    public java.lang.String getValidacio() {
        return validacio;
    }


    /**
     * Sets the validacio value for this TramitCriteria.
     * 
     * @param validacio
     */
    public void setValidacio(java.lang.String validacio) {
        this.validacio = validacio;
    }


    /**
     * Gets the dataCaducitat value for this TramitCriteria.
     * 
     * @return dataCaducitat
     */
    public java.util.Calendar getDataCaducitat() {
        return dataCaducitat;
    }


    /**
     * Sets the dataCaducitat value for this TramitCriteria.
     * 
     * @param dataCaducitat
     */
    public void setDataCaducitat(java.util.Calendar dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }


    /**
     * Gets the dataPublicacio value for this TramitCriteria.
     * 
     * @return dataPublicacio
     */
    public java.util.Calendar getDataPublicacio() {
        return dataPublicacio;
    }


    /**
     * Sets the dataPublicacio value for this TramitCriteria.
     * 
     * @param dataPublicacio
     */
    public void setDataPublicacio(java.util.Calendar dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
    }


    /**
     * Gets the dataActualitzacio value for this TramitCriteria.
     * 
     * @return dataActualitzacio
     */
    public java.util.Calendar getDataActualitzacio() {
        return dataActualitzacio;
    }


    /**
     * Sets the dataActualitzacio value for this TramitCriteria.
     * 
     * @param dataActualitzacio
     */
    public void setDataActualitzacio(java.util.Calendar dataActualitzacio) {
        this.dataActualitzacio = dataActualitzacio;
    }


    /**
     * Gets the codiVuds value for this TramitCriteria.
     * 
     * @return codiVuds
     */
    public java.lang.String getCodiVuds() {
        return codiVuds;
    }


    /**
     * Sets the codiVuds value for this TramitCriteria.
     * 
     * @param codiVuds
     */
    public void setCodiVuds(java.lang.String codiVuds) {
        this.codiVuds = codiVuds;
    }


    /**
     * Gets the descCodiVuds value for this TramitCriteria.
     * 
     * @return descCodiVuds
     */
    public java.lang.String getDescCodiVuds() {
        return descCodiVuds;
    }


    /**
     * Sets the descCodiVuds value for this TramitCriteria.
     * 
     * @param descCodiVuds
     */
    public void setDescCodiVuds(java.lang.String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }


    /**
     * Gets the idTraTel value for this TramitCriteria.
     * 
     * @return idTraTel
     */
    public java.lang.String getIdTraTel() {
        return idTraTel;
    }


    /**
     * Sets the idTraTel value for this TramitCriteria.
     * 
     * @param idTraTel
     */
    public void setIdTraTel(java.lang.String idTraTel) {
        this.idTraTel = idTraTel;
    }


    /**
     * Gets the urlExterna value for this TramitCriteria.
     * 
     * @return urlExterna
     */
    public java.lang.String getUrlExterna() {
        return urlExterna;
    }


    /**
     * Sets the urlExterna value for this TramitCriteria.
     * 
     * @param urlExterna
     */
    public void setUrlExterna(java.lang.String urlExterna) {
        this.urlExterna = urlExterna;
    }


    /**
     * Gets the dataActualitzacioVuds value for this TramitCriteria.
     * 
     * @return dataActualitzacioVuds
     */
    public java.lang.String getDataActualitzacioVuds() {
        return dataActualitzacioVuds;
    }


    /**
     * Sets the dataActualitzacioVuds value for this TramitCriteria.
     * 
     * @param dataActualitzacioVuds
     */
    public void setDataActualitzacioVuds(java.lang.String dataActualitzacioVuds) {
        this.dataActualitzacioVuds = dataActualitzacioVuds;
    }


    /**
     * Gets the t_nombre value for this TramitCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this TramitCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the t_descripcion value for this TramitCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this TramitCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the t_documentacion value for this TramitCriteria.
     * 
     * @return t_documentacion
     */
    public java.lang.String getT_documentacion() {
        return t_documentacion;
    }


    /**
     * Sets the t_documentacion value for this TramitCriteria.
     * 
     * @param t_documentacion
     */
    public void setT_documentacion(java.lang.String t_documentacion) {
        this.t_documentacion = t_documentacion;
    }


    /**
     * Gets the t_requisits value for this TramitCriteria.
     * 
     * @return t_requisits
     */
    public java.lang.String getT_requisits() {
        return t_requisits;
    }


    /**
     * Sets the t_requisits value for this TramitCriteria.
     * 
     * @param t_requisits
     */
    public void setT_requisits(java.lang.String t_requisits) {
        this.t_requisits = t_requisits;
    }


    /**
     * Gets the t_observaciones value for this TramitCriteria.
     * 
     * @return t_observaciones
     */
    public java.lang.String getT_observaciones() {
        return t_observaciones;
    }


    /**
     * Sets the t_observaciones value for this TramitCriteria.
     * 
     * @param t_observaciones
     */
    public void setT_observaciones(java.lang.String t_observaciones) {
        this.t_observaciones = t_observaciones;
    }


    /**
     * Gets the t_plazos value for this TramitCriteria.
     * 
     * @return t_plazos
     */
    public java.lang.String getT_plazos() {
        return t_plazos;
    }


    /**
     * Sets the t_plazos value for this TramitCriteria.
     * 
     * @param t_plazos
     */
    public void setT_plazos(java.lang.String t_plazos) {
        this.t_plazos = t_plazos;
    }


    /**
     * Gets the t_lugar value for this TramitCriteria.
     * 
     * @return t_lugar
     */
    public java.lang.String getT_lugar() {
        return t_lugar;
    }


    /**
     * Sets the t_lugar value for this TramitCriteria.
     * 
     * @param t_lugar
     */
    public void setT_lugar(java.lang.String t_lugar) {
        this.t_lugar = t_lugar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TramitCriteria)) return false;
        TramitCriteria other = (TramitCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.fase==null && other.getFase()==null) || 
             (this.fase!=null &&
              this.fase.equals(other.getFase()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.versio==null && other.getVersio()==null) || 
             (this.versio!=null &&
              this.versio.equals(other.getVersio()))) &&
            ((this.validacio==null && other.getValidacio()==null) || 
             (this.validacio!=null &&
              this.validacio.equals(other.getValidacio()))) &&
            ((this.dataCaducitat==null && other.getDataCaducitat()==null) || 
             (this.dataCaducitat!=null &&
              this.dataCaducitat.equals(other.getDataCaducitat()))) &&
            ((this.dataPublicacio==null && other.getDataPublicacio()==null) || 
             (this.dataPublicacio!=null &&
              this.dataPublicacio.equals(other.getDataPublicacio()))) &&
            ((this.dataActualitzacio==null && other.getDataActualitzacio()==null) || 
             (this.dataActualitzacio!=null &&
              this.dataActualitzacio.equals(other.getDataActualitzacio()))) &&
            ((this.codiVuds==null && other.getCodiVuds()==null) || 
             (this.codiVuds!=null &&
              this.codiVuds.equals(other.getCodiVuds()))) &&
            ((this.descCodiVuds==null && other.getDescCodiVuds()==null) || 
             (this.descCodiVuds!=null &&
              this.descCodiVuds.equals(other.getDescCodiVuds()))) &&
            ((this.idTraTel==null && other.getIdTraTel()==null) || 
             (this.idTraTel!=null &&
              this.idTraTel.equals(other.getIdTraTel()))) &&
            ((this.urlExterna==null && other.getUrlExterna()==null) || 
             (this.urlExterna!=null &&
              this.urlExterna.equals(other.getUrlExterna()))) &&
            ((this.dataActualitzacioVuds==null && other.getDataActualitzacioVuds()==null) || 
             (this.dataActualitzacioVuds!=null &&
              this.dataActualitzacioVuds.equals(other.getDataActualitzacioVuds()))) &&
            ((this.t_nombre==null && other.getT_nombre()==null) || 
             (this.t_nombre!=null &&
              this.t_nombre.equals(other.getT_nombre()))) &&
            ((this.t_descripcion==null && other.getT_descripcion()==null) || 
             (this.t_descripcion!=null &&
              this.t_descripcion.equals(other.getT_descripcion()))) &&
            ((this.t_documentacion==null && other.getT_documentacion()==null) || 
             (this.t_documentacion!=null &&
              this.t_documentacion.equals(other.getT_documentacion()))) &&
            ((this.t_requisits==null && other.getT_requisits()==null) || 
             (this.t_requisits!=null &&
              this.t_requisits.equals(other.getT_requisits()))) &&
            ((this.t_observaciones==null && other.getT_observaciones()==null) || 
             (this.t_observaciones!=null &&
              this.t_observaciones.equals(other.getT_observaciones()))) &&
            ((this.t_plazos==null && other.getT_plazos()==null) || 
             (this.t_plazos!=null &&
              this.t_plazos.equals(other.getT_plazos()))) &&
            ((this.t_lugar==null && other.getT_lugar()==null) || 
             (this.t_lugar!=null &&
              this.t_lugar.equals(other.getT_lugar())));
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
        if (getFase() != null) {
            _hashCode += getFase().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getVersio() != null) {
            _hashCode += getVersio().hashCode();
        }
        if (getValidacio() != null) {
            _hashCode += getValidacio().hashCode();
        }
        if (getDataCaducitat() != null) {
            _hashCode += getDataCaducitat().hashCode();
        }
        if (getDataPublicacio() != null) {
            _hashCode += getDataPublicacio().hashCode();
        }
        if (getDataActualitzacio() != null) {
            _hashCode += getDataActualitzacio().hashCode();
        }
        if (getCodiVuds() != null) {
            _hashCode += getCodiVuds().hashCode();
        }
        if (getDescCodiVuds() != null) {
            _hashCode += getDescCodiVuds().hashCode();
        }
        if (getIdTraTel() != null) {
            _hashCode += getIdTraTel().hashCode();
        }
        if (getUrlExterna() != null) {
            _hashCode += getUrlExterna().hashCode();
        }
        if (getDataActualitzacioVuds() != null) {
            _hashCode += getDataActualitzacioVuds().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        if (getT_documentacion() != null) {
            _hashCode += getT_documentacion().hashCode();
        }
        if (getT_requisits() != null) {
            _hashCode += getT_requisits().hashCode();
        }
        if (getT_observaciones() != null) {
            _hashCode += getT_observaciones().hashCode();
        }
        if (getT_plazos() != null) {
            _hashCode += getT_plazos().hashCode();
        }
        if (getT_lugar() != null) {
            _hashCode += getT_lugar().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TramitCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fase"));
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
        elemField.setFieldName("versio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "versio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCaducitat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataCaducitat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPublicacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPublicacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataActualitzacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataActualitzacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiVuds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiVuds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descCodiVuds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descCodiVuds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTraTel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTraTel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataActualitzacioVuds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataActualitzacioVuds"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_documentacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_documentacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_requisits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_requisits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_observaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_observaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_plazos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_plazos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_lugar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_lugar"));
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
