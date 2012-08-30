/**
 * FitxaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.fitxa;

public class FitxaDTO  extends es.caib.rolsac.api.v2.general.EntitatRemota  implements java.io.Serializable {
    private java.lang.Long baner;

    private java.lang.String descAbr;

    private java.lang.String descripcion;

    private java.util.Calendar fechaActualizacion;

    private java.util.Calendar fechaCaducidad;

    private java.util.Calendar fechaPublicacion;

    private java.lang.String foro_tema;

    private java.lang.Long icono;

    private java.lang.Long id;

    private java.lang.Long imagen;

    private java.lang.String info;

    private java.lang.String responsable;

    private java.lang.String titulo;

    private java.lang.String url;

    private java.lang.String urlForo;

    private java.lang.String urlVideo;

    private java.lang.Integer validacion;

    public FitxaDTO() {
    }

    public FitxaDTO(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota,
           java.lang.Long baner,
           java.lang.String descAbr,
           java.lang.String descripcion,
           java.util.Calendar fechaActualizacion,
           java.util.Calendar fechaCaducidad,
           java.util.Calendar fechaPublicacion,
           java.lang.String foro_tema,
           java.lang.Long icono,
           java.lang.Long id,
           java.lang.Long imagen,
           java.lang.String info,
           java.lang.String responsable,
           java.lang.String titulo,
           java.lang.String url,
           java.lang.String urlForo,
           java.lang.String urlVideo,
           java.lang.Integer validacion) {
        super(
            administracionRemota,
            idExterno,
            urlRemota);
        this.baner = baner;
        this.descAbr = descAbr;
        this.descripcion = descripcion;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaPublicacion = fechaPublicacion;
        this.foro_tema = foro_tema;
        this.icono = icono;
        this.id = id;
        this.imagen = imagen;
        this.info = info;
        this.responsable = responsable;
        this.titulo = titulo;
        this.url = url;
        this.urlForo = urlForo;
        this.urlVideo = urlVideo;
        this.validacion = validacion;
    }


    /**
     * Gets the baner value for this FitxaDTO.
     * 
     * @return baner
     */
    public java.lang.Long getBaner() {
        return baner;
    }


    /**
     * Sets the baner value for this FitxaDTO.
     * 
     * @param baner
     */
    public void setBaner(java.lang.Long baner) {
        this.baner = baner;
    }


    /**
     * Gets the descAbr value for this FitxaDTO.
     * 
     * @return descAbr
     */
    public java.lang.String getDescAbr() {
        return descAbr;
    }


    /**
     * Sets the descAbr value for this FitxaDTO.
     * 
     * @param descAbr
     */
    public void setDescAbr(java.lang.String descAbr) {
        this.descAbr = descAbr;
    }


    /**
     * Gets the descripcion value for this FitxaDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this FitxaDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the fechaActualizacion value for this FitxaDTO.
     * 
     * @return fechaActualizacion
     */
    public java.util.Calendar getFechaActualizacion() {
        return fechaActualizacion;
    }


    /**
     * Sets the fechaActualizacion value for this FitxaDTO.
     * 
     * @param fechaActualizacion
     */
    public void setFechaActualizacion(java.util.Calendar fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    /**
     * Gets the fechaCaducidad value for this FitxaDTO.
     * 
     * @return fechaCaducidad
     */
    public java.util.Calendar getFechaCaducidad() {
        return fechaCaducidad;
    }


    /**
     * Sets the fechaCaducidad value for this FitxaDTO.
     * 
     * @param fechaCaducidad
     */
    public void setFechaCaducidad(java.util.Calendar fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }


    /**
     * Gets the fechaPublicacion value for this FitxaDTO.
     * 
     * @return fechaPublicacion
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this FitxaDTO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the foro_tema value for this FitxaDTO.
     * 
     * @return foro_tema
     */
    public java.lang.String getForo_tema() {
        return foro_tema;
    }


    /**
     * Sets the foro_tema value for this FitxaDTO.
     * 
     * @param foro_tema
     */
    public void setForo_tema(java.lang.String foro_tema) {
        this.foro_tema = foro_tema;
    }


    /**
     * Gets the icono value for this FitxaDTO.
     * 
     * @return icono
     */
    public java.lang.Long getIcono() {
        return icono;
    }


    /**
     * Sets the icono value for this FitxaDTO.
     * 
     * @param icono
     */
    public void setIcono(java.lang.Long icono) {
        this.icono = icono;
    }


    /**
     * Gets the id value for this FitxaDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this FitxaDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the imagen value for this FitxaDTO.
     * 
     * @return imagen
     */
    public java.lang.Long getImagen() {
        return imagen;
    }


    /**
     * Sets the imagen value for this FitxaDTO.
     * 
     * @param imagen
     */
    public void setImagen(java.lang.Long imagen) {
        this.imagen = imagen;
    }


    /**
     * Gets the info value for this FitxaDTO.
     * 
     * @return info
     */
    public java.lang.String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this FitxaDTO.
     * 
     * @param info
     */
    public void setInfo(java.lang.String info) {
        this.info = info;
    }


    /**
     * Gets the responsable value for this FitxaDTO.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this FitxaDTO.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the titulo value for this FitxaDTO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this FitxaDTO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the url value for this FitxaDTO.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this FitxaDTO.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the urlForo value for this FitxaDTO.
     * 
     * @return urlForo
     */
    public java.lang.String getUrlForo() {
        return urlForo;
    }


    /**
     * Sets the urlForo value for this FitxaDTO.
     * 
     * @param urlForo
     */
    public void setUrlForo(java.lang.String urlForo) {
        this.urlForo = urlForo;
    }


    /**
     * Gets the urlVideo value for this FitxaDTO.
     * 
     * @return urlVideo
     */
    public java.lang.String getUrlVideo() {
        return urlVideo;
    }


    /**
     * Sets the urlVideo value for this FitxaDTO.
     * 
     * @param urlVideo
     */
    public void setUrlVideo(java.lang.String urlVideo) {
        this.urlVideo = urlVideo;
    }


    /**
     * Gets the validacion value for this FitxaDTO.
     * 
     * @return validacion
     */
    public java.lang.Integer getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this FitxaDTO.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.Integer validacion) {
        this.validacion = validacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FitxaDTO)) return false;
        FitxaDTO other = (FitxaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.baner==null && other.getBaner()==null) || 
             (this.baner!=null &&
              this.baner.equals(other.getBaner()))) &&
            ((this.descAbr==null && other.getDescAbr()==null) || 
             (this.descAbr!=null &&
              this.descAbr.equals(other.getDescAbr()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
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
            ((this.icono==null && other.getIcono()==null) || 
             (this.icono!=null &&
              this.icono.equals(other.getIcono()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.imagen==null && other.getImagen()==null) || 
             (this.imagen!=null &&
              this.imagen.equals(other.getImagen()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.urlForo==null && other.getUrlForo()==null) || 
             (this.urlForo!=null &&
              this.urlForo.equals(other.getUrlForo()))) &&
            ((this.urlVideo==null && other.getUrlVideo()==null) || 
             (this.urlVideo!=null &&
              this.urlVideo.equals(other.getUrlVideo()))) &&
            ((this.validacion==null && other.getValidacion()==null) || 
             (this.validacion!=null &&
              this.validacion.equals(other.getValidacion())));
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
        if (getBaner() != null) {
            _hashCode += getBaner().hashCode();
        }
        if (getDescAbr() != null) {
            _hashCode += getDescAbr().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
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
        if (getIcono() != null) {
            _hashCode += getIcono().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getImagen() != null) {
            _hashCode += getImagen().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FitxaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fitxa.v2.api.rolsac.caib.es", "FitxaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baner");
        elemField.setXmlName(new javax.xml.namespace.QName("", "baner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descAbr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descAbr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaActualizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaActualizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCaducidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaCaducidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPublicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaPublicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foro_tema");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foro_tema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "icono"));
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
        elemField.setFieldName("imagen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imagen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
