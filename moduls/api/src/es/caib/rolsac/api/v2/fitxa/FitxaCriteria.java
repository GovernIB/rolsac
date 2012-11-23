/**
 * FitxaCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.fitxa;

public class FitxaCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String fechaActualizacion;

    private java.lang.String fechaCaducidad;

    private java.lang.String fechaPublicacion;

    private java.lang.String foro_tema;

    private java.lang.String info;

    private java.lang.String responsable;

    private java.lang.String t_descAbr;

    private java.lang.String t_descripcion;

    private java.lang.String t_titulo;

    private java.lang.String t_url;

    private java.lang.String urlForo;

    private java.lang.String urlVideo;

    private java.lang.String validacion;

    private java.lang.String publicoObjetivo;

    private java.lang.String seccion;

    public FitxaCriteria() {
    }

    public FitxaCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String fechaActualizacion,
           java.lang.String fechaCaducidad,
           java.lang.String fechaPublicacion,
           java.lang.String foro_tema,
           java.lang.String info,
           java.lang.String responsable,
           java.lang.String t_descAbr,
           java.lang.String t_descripcion,
           java.lang.String t_titulo,
           java.lang.String t_url,
           java.lang.String urlForo,
           java.lang.String urlVideo,
           java.lang.String validacion,
           java.lang.String publicoObjetivo,
           java.lang.String seccion) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaPublicacion = fechaPublicacion;
        this.foro_tema = foro_tema;
        this.info = info;
        this.responsable = responsable;
        this.t_descAbr = t_descAbr;
        this.t_descripcion = t_descripcion;
        this.t_titulo = t_titulo;
        this.t_url = t_url;
        this.urlForo = urlForo;
        this.urlVideo = urlVideo;
        this.validacion = validacion;
        this.publicoObjetivo = publicoObjetivo;
        this.seccion = seccion;
    }


    /**
     * Gets the fechaActualizacion value for this FitxaCriteria.
     * 
     * @return fechaActualizacion
     */
    public java.lang.String getFechaActualizacion() {
        return fechaActualizacion;
    }


    /**
     * Sets the fechaActualizacion value for this FitxaCriteria.
     * 
     * @param fechaActualizacion
     */
    public void setFechaActualizacion(java.lang.String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    /**
     * Gets the fechaCaducidad value for this FitxaCriteria.
     * 
     * @return fechaCaducidad
     */
    public java.lang.String getFechaCaducidad() {
        return fechaCaducidad;
    }


    /**
     * Sets the fechaCaducidad value for this FitxaCriteria.
     * 
     * @param fechaCaducidad
     */
    public void setFechaCaducidad(java.lang.String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }


    /**
     * Gets the fechaPublicacion value for this FitxaCriteria.
     * 
     * @return fechaPublicacion
     */
    public java.lang.String getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this FitxaCriteria.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.lang.String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the foro_tema value for this FitxaCriteria.
     * 
     * @return foro_tema
     */
    public java.lang.String getForo_tema() {
        return foro_tema;
    }


    /**
     * Sets the foro_tema value for this FitxaCriteria.
     * 
     * @param foro_tema
     */
    public void setForo_tema(java.lang.String foro_tema) {
        this.foro_tema = foro_tema;
    }


    /**
     * Gets the info value for this FitxaCriteria.
     * 
     * @return info
     */
    public java.lang.String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this FitxaCriteria.
     * 
     * @param info
     */
    public void setInfo(java.lang.String info) {
        this.info = info;
    }


    /**
     * Gets the responsable value for this FitxaCriteria.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this FitxaCriteria.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the t_descAbr value for this FitxaCriteria.
     * 
     * @return t_descAbr
     */
    public java.lang.String getT_descAbr() {
        return t_descAbr;
    }


    /**
     * Sets the t_descAbr value for this FitxaCriteria.
     * 
     * @param t_descAbr
     */
    public void setT_descAbr(java.lang.String t_descAbr) {
        this.t_descAbr = t_descAbr;
    }


    /**
     * Gets the t_descripcion value for this FitxaCriteria.
     * 
     * @return t_descripcion
     */
    public java.lang.String getT_descripcion() {
        return t_descripcion;
    }


    /**
     * Sets the t_descripcion value for this FitxaCriteria.
     * 
     * @param t_descripcion
     */
    public void setT_descripcion(java.lang.String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }


    /**
     * Gets the t_titulo value for this FitxaCriteria.
     * 
     * @return t_titulo
     */
    public java.lang.String getT_titulo() {
        return t_titulo;
    }


    /**
     * Sets the t_titulo value for this FitxaCriteria.
     * 
     * @param t_titulo
     */
    public void setT_titulo(java.lang.String t_titulo) {
        this.t_titulo = t_titulo;
    }


    /**
     * Gets the t_url value for this FitxaCriteria.
     * 
     * @return t_url
     */
    public java.lang.String getT_url() {
        return t_url;
    }


    /**
     * Sets the t_url value for this FitxaCriteria.
     * 
     * @param t_url
     */
    public void setT_url(java.lang.String t_url) {
        this.t_url = t_url;
    }


    /**
     * Gets the urlForo value for this FitxaCriteria.
     * 
     * @return urlForo
     */
    public java.lang.String getUrlForo() {
        return urlForo;
    }


    /**
     * Sets the urlForo value for this FitxaCriteria.
     * 
     * @param urlForo
     */
    public void setUrlForo(java.lang.String urlForo) {
        this.urlForo = urlForo;
    }


    /**
     * Gets the urlVideo value for this FitxaCriteria.
     * 
     * @return urlVideo
     */
    public java.lang.String getUrlVideo() {
        return urlVideo;
    }


    /**
     * Sets the urlVideo value for this FitxaCriteria.
     * 
     * @param urlVideo
     */
    public void setUrlVideo(java.lang.String urlVideo) {
        this.urlVideo = urlVideo;
    }


    /**
     * Gets the validacion value for this FitxaCriteria.
     * 
     * @return validacion
     */
    public java.lang.String getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this FitxaCriteria.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.String validacion) {
        this.validacion = validacion;
    }


    /**
     * Gets the publicoObjetivo value for this FitxaCriteria.
     * 
     * @return publicoObjetivo
     */
    public java.lang.String getPublicoObjetivo() {
        return publicoObjetivo;
    }


    /**
     * Sets the publicoObjetivo value for this FitxaCriteria.
     * 
     * @param publicoObjetivo
     */
    public void setPublicoObjetivo(java.lang.String publicoObjetivo) {
        this.publicoObjetivo = publicoObjetivo;
    }


    /**
     * Gets the seccion value for this FitxaCriteria.
     * 
     * @return seccion
     */
    public java.lang.String getSeccion() {
        return seccion;
    }


    /**
     * Sets the seccion value for this FitxaCriteria.
     * 
     * @param seccion
     */
    public void setSeccion(java.lang.String seccion) {
        this.seccion = seccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FitxaCriteria)) return false;
        FitxaCriteria other = (FitxaCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.fechaActualizacion==null && other.getFechaActualizacion()==null) || 
             (this.fechaActualizacion!=null &&
              this.fechaActualizacion.equals(other.getFechaActualizacion()))) &&
            ((this.fechaCaducidad==null && other.getFechaCaducidad()==null) || 
             (this.fechaCaducidad!=null &&
              this.fechaCaducidad.equals(other.getFechaCaducidad()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.foro_tema==null && other.getForo_tema()==null) || 
             (this.foro_tema!=null &&
              this.foro_tema.equals(other.getForo_tema()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.t_descAbr==null && other.getT_descAbr()==null) || 
             (this.t_descAbr!=null &&
              this.t_descAbr.equals(other.getT_descAbr()))) &&
            ((this.t_descripcion==null && other.getT_descripcion()==null) || 
             (this.t_descripcion!=null &&
              this.t_descripcion.equals(other.getT_descripcion()))) &&
            ((this.t_titulo==null && other.getT_titulo()==null) || 
             (this.t_titulo!=null &&
              this.t_titulo.equals(other.getT_titulo()))) &&
            ((this.t_url==null && other.getT_url()==null) || 
             (this.t_url!=null &&
              this.t_url.equals(other.getT_url()))) &&
            ((this.urlForo==null && other.getUrlForo()==null) || 
             (this.urlForo!=null &&
              this.urlForo.equals(other.getUrlForo()))) &&
            ((this.urlVideo==null && other.getUrlVideo()==null) || 
             (this.urlVideo!=null &&
              this.urlVideo.equals(other.getUrlVideo()))) &&
            ((this.validacion==null && other.getValidacion()==null) || 
             (this.validacion!=null &&
              this.validacion.equals(other.getValidacion()))) &&
            ((this.publicoObjetivo==null && other.getPublicoObjetivo()==null) || 
             (this.publicoObjetivo!=null &&
              this.publicoObjetivo.equals(other.getPublicoObjetivo()))) &&
            ((this.seccion==null && other.getSeccion()==null) || 
             (this.seccion!=null &&
              this.seccion.equals(other.getSeccion())));
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
        if (getFechaActualizacion() != null) {
            _hashCode += getFechaActualizacion().hashCode();
        }
        if (getFechaCaducidad() != null) {
            _hashCode += getFechaCaducidad().hashCode();
        }
        if (getFechaPublicacion() != null) {
            _hashCode += getFechaPublicacion().hashCode();
        }
        if (getForo_tema() != null) {
            _hashCode += getForo_tema().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getT_descAbr() != null) {
            _hashCode += getT_descAbr().hashCode();
        }
        if (getT_descripcion() != null) {
            _hashCode += getT_descripcion().hashCode();
        }
        if (getT_titulo() != null) {
            _hashCode += getT_titulo().hashCode();
        }
        if (getT_url() != null) {
            _hashCode += getT_url().hashCode();
        }
        if (getUrlForo() != null) {
            _hashCode += getUrlForo().hashCode();
        }
        if (getUrlVideo() != null) {
            _hashCode += getUrlVideo().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        if (getPublicoObjetivo() != null) {
            _hashCode += getPublicoObjetivo().hashCode();
        }
        if (getSeccion() != null) {
            _hashCode += getSeccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FitxaCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaActualizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaActualizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCaducidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaCaducidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foro_tema");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foro_tema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responsable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_descAbr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_descAbr"));
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
        elemField.setFieldName("t_titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlForo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlForo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("urlVideo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "urlVideo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicoObjetivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publicoObjetivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seccion"));
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
