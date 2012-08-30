/**
 * ProcedimentCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.procediment;

public class ProcedimentCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.Boolean actiu;

    private java.util.Calendar fechaActualizacion;

    private java.util.Calendar fechaCaducidad;

    private java.util.Calendar fechaPublicacion;

    private java.lang.Boolean indicador;

    private java.lang.String info;

    private java.lang.String orden;

    private java.lang.String orden2;

    private java.lang.String orden3;

    private java.lang.String responsable;

    private java.lang.String signatura;

    private java.lang.String t_destinatarios;

    private java.lang.String t_lugar;

    private java.lang.String t_nombre;

    private java.lang.String t_notificacion;

    private java.lang.String t_observaciones;

    private java.lang.String t_plazos;

    private java.lang.String t_recursos;

    private java.lang.String t_requisitos;

    private java.lang.String t_resolucion;

    private java.lang.String t_resultat;

    private java.lang.String t_resumen;

    private java.lang.String t_silencio;

    private java.lang.Boolean taxa;

    private java.lang.String tramite;

    private java.lang.String url;

    private java.lang.String validacion;

    private java.lang.Boolean ventanillaUnica;

    private java.lang.String version;

    public ProcedimentCriteria() {
    }

    public ProcedimentCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.Boolean actiu,
           java.util.Calendar fechaActualizacion,
           java.util.Calendar fechaCaducidad,
           java.util.Calendar fechaPublicacion,
           java.lang.Boolean indicador,
           java.lang.String info,
           java.lang.String orden,
           java.lang.String orden2,
           java.lang.String orden3,
           java.lang.String responsable,
           java.lang.String signatura,
           java.lang.String t_destinatarios,
           java.lang.String t_lugar,
           java.lang.String t_nombre,
           java.lang.String t_notificacion,
           java.lang.String t_observaciones,
           java.lang.String t_plazos,
           java.lang.String t_recursos,
           java.lang.String t_requisitos,
           java.lang.String t_resolucion,
           java.lang.String t_resultat,
           java.lang.String t_resumen,
           java.lang.String t_silencio,
           java.lang.Boolean taxa,
           java.lang.String tramite,
           java.lang.String url,
           java.lang.String validacion,
           java.lang.Boolean ventanillaUnica,
           java.lang.String version) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.actiu = actiu;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaPublicacion = fechaPublicacion;
        this.indicador = indicador;
        this.info = info;
        this.orden = orden;
        this.orden2 = orden2;
        this.orden3 = orden3;
        this.responsable = responsable;
        this.signatura = signatura;
        this.t_destinatarios = t_destinatarios;
        this.t_lugar = t_lugar;
        this.t_nombre = t_nombre;
        this.t_notificacion = t_notificacion;
        this.t_observaciones = t_observaciones;
        this.t_plazos = t_plazos;
        this.t_recursos = t_recursos;
        this.t_requisitos = t_requisitos;
        this.t_resolucion = t_resolucion;
        this.t_resultat = t_resultat;
        this.t_resumen = t_resumen;
        this.t_silencio = t_silencio;
        this.taxa = taxa;
        this.tramite = tramite;
        this.url = url;
        this.validacion = validacion;
        this.ventanillaUnica = ventanillaUnica;
        this.version = version;
    }


    /**
     * Gets the actiu value for this ProcedimentCriteria.
     * 
     * @return actiu
     */
    public java.lang.Boolean getActiu() {
        return actiu;
    }


    /**
     * Sets the actiu value for this ProcedimentCriteria.
     * 
     * @param actiu
     */
    public void setActiu(java.lang.Boolean actiu) {
        this.actiu = actiu;
    }


    /**
     * Gets the fechaActualizacion value for this ProcedimentCriteria.
     * 
     * @return fechaActualizacion
     */
    public java.util.Calendar getFechaActualizacion() {
        return fechaActualizacion;
    }


    /**
     * Sets the fechaActualizacion value for this ProcedimentCriteria.
     * 
     * @param fechaActualizacion
     */
    public void setFechaActualizacion(java.util.Calendar fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    /**
     * Gets the fechaCaducidad value for this ProcedimentCriteria.
     * 
     * @return fechaCaducidad
     */
    public java.util.Calendar getFechaCaducidad() {
        return fechaCaducidad;
    }


    /**
     * Sets the fechaCaducidad value for this ProcedimentCriteria.
     * 
     * @param fechaCaducidad
     */
    public void setFechaCaducidad(java.util.Calendar fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }


    /**
     * Gets the fechaPublicacion value for this ProcedimentCriteria.
     * 
     * @return fechaPublicacion
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ProcedimentCriteria.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the indicador value for this ProcedimentCriteria.
     * 
     * @return indicador
     */
    public java.lang.Boolean getIndicador() {
        return indicador;
    }


    /**
     * Sets the indicador value for this ProcedimentCriteria.
     * 
     * @param indicador
     */
    public void setIndicador(java.lang.Boolean indicador) {
        this.indicador = indicador;
    }


    /**
     * Gets the info value for this ProcedimentCriteria.
     * 
     * @return info
     */
    public java.lang.String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this ProcedimentCriteria.
     * 
     * @param info
     */
    public void setInfo(java.lang.String info) {
        this.info = info;
    }


    /**
     * Gets the orden value for this ProcedimentCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this ProcedimentCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the orden2 value for this ProcedimentCriteria.
     * 
     * @return orden2
     */
    public java.lang.String getOrden2() {
        return orden2;
    }


    /**
     * Sets the orden2 value for this ProcedimentCriteria.
     * 
     * @param orden2
     */
    public void setOrden2(java.lang.String orden2) {
        this.orden2 = orden2;
    }


    /**
     * Gets the orden3 value for this ProcedimentCriteria.
     * 
     * @return orden3
     */
    public java.lang.String getOrden3() {
        return orden3;
    }


    /**
     * Sets the orden3 value for this ProcedimentCriteria.
     * 
     * @param orden3
     */
    public void setOrden3(java.lang.String orden3) {
        this.orden3 = orden3;
    }


    /**
     * Gets the responsable value for this ProcedimentCriteria.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this ProcedimentCriteria.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the signatura value for this ProcedimentCriteria.
     * 
     * @return signatura
     */
    public java.lang.String getSignatura() {
        return signatura;
    }


    /**
     * Sets the signatura value for this ProcedimentCriteria.
     * 
     * @param signatura
     */
    public void setSignatura(java.lang.String signatura) {
        this.signatura = signatura;
    }


    /**
     * Gets the t_destinatarios value for this ProcedimentCriteria.
     * 
     * @return t_destinatarios
     */
    public java.lang.String getT_destinatarios() {
        return t_destinatarios;
    }


    /**
     * Sets the t_destinatarios value for this ProcedimentCriteria.
     * 
     * @param t_destinatarios
     */
    public void setT_destinatarios(java.lang.String t_destinatarios) {
        this.t_destinatarios = t_destinatarios;
    }


    /**
     * Gets the t_lugar value for this ProcedimentCriteria.
     * 
     * @return t_lugar
     */
    public java.lang.String getT_lugar() {
        return t_lugar;
    }


    /**
     * Sets the t_lugar value for this ProcedimentCriteria.
     * 
     * @param t_lugar
     */
    public void setT_lugar(java.lang.String t_lugar) {
        this.t_lugar = t_lugar;
    }


    /**
     * Gets the t_nombre value for this ProcedimentCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this ProcedimentCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the t_notificacion value for this ProcedimentCriteria.
     * 
     * @return t_notificacion
     */
    public java.lang.String getT_notificacion() {
        return t_notificacion;
    }


    /**
     * Sets the t_notificacion value for this ProcedimentCriteria.
     * 
     * @param t_notificacion
     */
    public void setT_notificacion(java.lang.String t_notificacion) {
        this.t_notificacion = t_notificacion;
    }


    /**
     * Gets the t_observaciones value for this ProcedimentCriteria.
     * 
     * @return t_observaciones
     */
    public java.lang.String getT_observaciones() {
        return t_observaciones;
    }


    /**
     * Sets the t_observaciones value for this ProcedimentCriteria.
     * 
     * @param t_observaciones
     */
    public void setT_observaciones(java.lang.String t_observaciones) {
        this.t_observaciones = t_observaciones;
    }


    /**
     * Gets the t_plazos value for this ProcedimentCriteria.
     * 
     * @return t_plazos
     */
    public java.lang.String getT_plazos() {
        return t_plazos;
    }


    /**
     * Sets the t_plazos value for this ProcedimentCriteria.
     * 
     * @param t_plazos
     */
    public void setT_plazos(java.lang.String t_plazos) {
        this.t_plazos = t_plazos;
    }


    /**
     * Gets the t_recursos value for this ProcedimentCriteria.
     * 
     * @return t_recursos
     */
    public java.lang.String getT_recursos() {
        return t_recursos;
    }


    /**
     * Sets the t_recursos value for this ProcedimentCriteria.
     * 
     * @param t_recursos
     */
    public void setT_recursos(java.lang.String t_recursos) {
        this.t_recursos = t_recursos;
    }


    /**
     * Gets the t_requisitos value for this ProcedimentCriteria.
     * 
     * @return t_requisitos
     */
    public java.lang.String getT_requisitos() {
        return t_requisitos;
    }


    /**
     * Sets the t_requisitos value for this ProcedimentCriteria.
     * 
     * @param t_requisitos
     */
    public void setT_requisitos(java.lang.String t_requisitos) {
        this.t_requisitos = t_requisitos;
    }


    /**
     * Gets the t_resolucion value for this ProcedimentCriteria.
     * 
     * @return t_resolucion
     */
    public java.lang.String getT_resolucion() {
        return t_resolucion;
    }


    /**
     * Sets the t_resolucion value for this ProcedimentCriteria.
     * 
     * @param t_resolucion
     */
    public void setT_resolucion(java.lang.String t_resolucion) {
        this.t_resolucion = t_resolucion;
    }


    /**
     * Gets the t_resultat value for this ProcedimentCriteria.
     * 
     * @return t_resultat
     */
    public java.lang.String getT_resultat() {
        return t_resultat;
    }


    /**
     * Sets the t_resultat value for this ProcedimentCriteria.
     * 
     * @param t_resultat
     */
    public void setT_resultat(java.lang.String t_resultat) {
        this.t_resultat = t_resultat;
    }


    /**
     * Gets the t_resumen value for this ProcedimentCriteria.
     * 
     * @return t_resumen
     */
    public java.lang.String getT_resumen() {
        return t_resumen;
    }


    /**
     * Sets the t_resumen value for this ProcedimentCriteria.
     * 
     * @param t_resumen
     */
    public void setT_resumen(java.lang.String t_resumen) {
        this.t_resumen = t_resumen;
    }


    /**
     * Gets the t_silencio value for this ProcedimentCriteria.
     * 
     * @return t_silencio
     */
    public java.lang.String getT_silencio() {
        return t_silencio;
    }


    /**
     * Sets the t_silencio value for this ProcedimentCriteria.
     * 
     * @param t_silencio
     */
    public void setT_silencio(java.lang.String t_silencio) {
        this.t_silencio = t_silencio;
    }


    /**
     * Gets the taxa value for this ProcedimentCriteria.
     * 
     * @return taxa
     */
    public java.lang.Boolean getTaxa() {
        return taxa;
    }


    /**
     * Sets the taxa value for this ProcedimentCriteria.
     * 
     * @param taxa
     */
    public void setTaxa(java.lang.Boolean taxa) {
        this.taxa = taxa;
    }


    /**
     * Gets the tramite value for this ProcedimentCriteria.
     * 
     * @return tramite
     */
    public java.lang.String getTramite() {
        return tramite;
    }


    /**
     * Sets the tramite value for this ProcedimentCriteria.
     * 
     * @param tramite
     */
    public void setTramite(java.lang.String tramite) {
        this.tramite = tramite;
    }


    /**
     * Gets the url value for this ProcedimentCriteria.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this ProcedimentCriteria.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the validacion value for this ProcedimentCriteria.
     * 
     * @return validacion
     */
    public java.lang.String getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this ProcedimentCriteria.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.String validacion) {
        this.validacion = validacion;
    }


    /**
     * Gets the ventanillaUnica value for this ProcedimentCriteria.
     * 
     * @return ventanillaUnica
     */
    public java.lang.Boolean getVentanillaUnica() {
        return ventanillaUnica;
    }


    /**
     * Sets the ventanillaUnica value for this ProcedimentCriteria.
     * 
     * @param ventanillaUnica
     */
    public void setVentanillaUnica(java.lang.Boolean ventanillaUnica) {
        this.ventanillaUnica = ventanillaUnica;
    }


    /**
     * Gets the version value for this ProcedimentCriteria.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this ProcedimentCriteria.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcedimentCriteria)) return false;
        ProcedimentCriteria other = (ProcedimentCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.actiu==null && other.getActiu()==null) || 
             (this.actiu!=null &&
              this.actiu.equals(other.getActiu()))) &&
            ((this.fechaActualizacion==null && other.getFechaActualizacion()==null) || 
             (this.fechaActualizacion!=null &&
              this.fechaActualizacion.equals(other.getFechaActualizacion()))) &&
            ((this.fechaCaducidad==null && other.getFechaCaducidad()==null) || 
             (this.fechaCaducidad!=null &&
              this.fechaCaducidad.equals(other.getFechaCaducidad()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.indicador==null && other.getIndicador()==null) || 
             (this.indicador!=null &&
              this.indicador.equals(other.getIndicador()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.orden2==null && other.getOrden2()==null) || 
             (this.orden2!=null &&
              this.orden2.equals(other.getOrden2()))) &&
            ((this.orden3==null && other.getOrden3()==null) || 
             (this.orden3!=null &&
              this.orden3.equals(other.getOrden3()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.signatura==null && other.getSignatura()==null) || 
             (this.signatura!=null &&
              this.signatura.equals(other.getSignatura()))) &&
            ((this.t_destinatarios==null && other.getT_destinatarios()==null) || 
             (this.t_destinatarios!=null &&
              this.t_destinatarios.equals(other.getT_destinatarios()))) &&
            ((this.t_lugar==null && other.getT_lugar()==null) || 
             (this.t_lugar!=null &&
              this.t_lugar.equals(other.getT_lugar()))) &&
            ((this.t_nombre==null && other.getT_nombre()==null) || 
             (this.t_nombre!=null &&
              this.t_nombre.equals(other.getT_nombre()))) &&
            ((this.t_notificacion==null && other.getT_notificacion()==null) || 
             (this.t_notificacion!=null &&
              this.t_notificacion.equals(other.getT_notificacion()))) &&
            ((this.t_observaciones==null && other.getT_observaciones()==null) || 
             (this.t_observaciones!=null &&
              this.t_observaciones.equals(other.getT_observaciones()))) &&
            ((this.t_plazos==null && other.getT_plazos()==null) || 
             (this.t_plazos!=null &&
              this.t_plazos.equals(other.getT_plazos()))) &&
            ((this.t_recursos==null && other.getT_recursos()==null) || 
             (this.t_recursos!=null &&
              this.t_recursos.equals(other.getT_recursos()))) &&
            ((this.t_requisitos==null && other.getT_requisitos()==null) || 
             (this.t_requisitos!=null &&
              this.t_requisitos.equals(other.getT_requisitos()))) &&
            ((this.t_resolucion==null && other.getT_resolucion()==null) || 
             (this.t_resolucion!=null &&
              this.t_resolucion.equals(other.getT_resolucion()))) &&
            ((this.t_resultat==null && other.getT_resultat()==null) || 
             (this.t_resultat!=null &&
              this.t_resultat.equals(other.getT_resultat()))) &&
            ((this.t_resumen==null && other.getT_resumen()==null) || 
             (this.t_resumen!=null &&
              this.t_resumen.equals(other.getT_resumen()))) &&
            ((this.t_silencio==null && other.getT_silencio()==null) || 
             (this.t_silencio!=null &&
              this.t_silencio.equals(other.getT_silencio()))) &&
            ((this.taxa==null && other.getTaxa()==null) || 
             (this.taxa!=null &&
              this.taxa.equals(other.getTaxa()))) &&
            ((this.tramite==null && other.getTramite()==null) || 
             (this.tramite!=null &&
              this.tramite.equals(other.getTramite()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.validacion==null && other.getValidacion()==null) || 
             (this.validacion!=null &&
              this.validacion.equals(other.getValidacion()))) &&
            ((this.ventanillaUnica==null && other.getVentanillaUnica()==null) || 
             (this.ventanillaUnica!=null &&
              this.ventanillaUnica.equals(other.getVentanillaUnica()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
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
        if (getActiu() != null) {
            _hashCode += getActiu().hashCode();
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
        if (getIndicador() != null) {
            _hashCode += getIndicador().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getOrden2() != null) {
            _hashCode += getOrden2().hashCode();
        }
        if (getOrden3() != null) {
            _hashCode += getOrden3().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getSignatura() != null) {
            _hashCode += getSignatura().hashCode();
        }
        if (getT_destinatarios() != null) {
            _hashCode += getT_destinatarios().hashCode();
        }
        if (getT_lugar() != null) {
            _hashCode += getT_lugar().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        if (getT_notificacion() != null) {
            _hashCode += getT_notificacion().hashCode();
        }
        if (getT_observaciones() != null) {
            _hashCode += getT_observaciones().hashCode();
        }
        if (getT_plazos() != null) {
            _hashCode += getT_plazos().hashCode();
        }
        if (getT_recursos() != null) {
            _hashCode += getT_recursos().hashCode();
        }
        if (getT_requisitos() != null) {
            _hashCode += getT_requisitos().hashCode();
        }
        if (getT_resolucion() != null) {
            _hashCode += getT_resolucion().hashCode();
        }
        if (getT_resultat() != null) {
            _hashCode += getT_resultat().hashCode();
        }
        if (getT_resumen() != null) {
            _hashCode += getT_resumen().hashCode();
        }
        if (getT_silencio() != null) {
            _hashCode += getT_silencio().hashCode();
        }
        if (getTaxa() != null) {
            _hashCode += getTaxa().hashCode();
        }
        if (getTramite() != null) {
            _hashCode += getTramite().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        if (getVentanillaUnica() != null) {
            _hashCode += getVentanillaUnica().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcedimentCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actiu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actiu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("indicador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "info"));
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
        elemField.setFieldName("orden2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden3"));
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
        elemField.setFieldName("signatura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_lugar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_lugar"));
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
        elemField.setFieldName("t_notificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_notificacion"));
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
        elemField.setFieldName("t_recursos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_recursos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_requisitos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_requisitos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_resolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_resolucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_resultat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_resultat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_resumen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_resumen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_silencio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_silencio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramite"));
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
        elemField.setFieldName("validacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ventanillaUnica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ventanillaUnica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
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
