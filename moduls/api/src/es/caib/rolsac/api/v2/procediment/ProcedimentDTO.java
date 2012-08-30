/**
 * ProcedimentDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.procediment;

public class ProcedimentDTO  extends es.caib.rolsac.api.v2.general.EntitatRemota  implements java.io.Serializable {
    private java.lang.String destinatarios;

    private java.lang.Long familia;

    private java.util.Calendar fechaActualizacion;

    private java.util.Calendar fechaCaducidad;

    private java.util.Calendar fechaPublicacion;

    private java.lang.Long id;

    private boolean indicador;

    private java.lang.String info;

    private java.lang.Long iniciacion;

    private java.lang.String lugar;

    private java.lang.String nombre;

    private java.lang.String notificacion;

    private java.lang.String observaciones;

    private java.lang.Long orden;

    private java.lang.Long orden2;

    private java.lang.Long orden3;

    private java.lang.Long organResolutori;

    private java.lang.String plazos;

    private java.lang.String recursos;

    private java.lang.String requisitos;

    private java.lang.String resolucion;

    private java.lang.String responsable;

    private java.lang.String resultat;

    private java.lang.String resumen;

    private java.lang.String signatura;

    private java.lang.String silencio;

    private boolean taxa;

    private java.lang.String tramite;

    private java.lang.Long unidadAdministrativa;

    private java.lang.String url;

    private java.lang.Integer validacion;

    private boolean ventanillaUnica;

    private java.lang.Long version;

    public ProcedimentDTO() {
    }

    public ProcedimentDTO(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota,
           java.lang.String destinatarios,
           java.lang.Long familia,
           java.util.Calendar fechaActualizacion,
           java.util.Calendar fechaCaducidad,
           java.util.Calendar fechaPublicacion,
           java.lang.Long id,
           boolean indicador,
           java.lang.String info,
           java.lang.Long iniciacion,
           java.lang.String lugar,
           java.lang.String nombre,
           java.lang.String notificacion,
           java.lang.String observaciones,
           java.lang.Long orden,
           java.lang.Long orden2,
           java.lang.Long orden3,
           java.lang.Long organResolutori,
           java.lang.String plazos,
           java.lang.String recursos,
           java.lang.String requisitos,
           java.lang.String resolucion,
           java.lang.String responsable,
           java.lang.String resultat,
           java.lang.String resumen,
           java.lang.String signatura,
           java.lang.String silencio,
           boolean taxa,
           java.lang.String tramite,
           java.lang.Long unidadAdministrativa,
           java.lang.String url,
           java.lang.Integer validacion,
           boolean ventanillaUnica,
           java.lang.Long version) {
        super(
            administracionRemota,
            idExterno,
            urlRemota);
        this.destinatarios = destinatarios;
        this.familia = familia;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaPublicacion = fechaPublicacion;
        this.id = id;
        this.indicador = indicador;
        this.info = info;
        this.iniciacion = iniciacion;
        this.lugar = lugar;
        this.nombre = nombre;
        this.notificacion = notificacion;
        this.observaciones = observaciones;
        this.orden = orden;
        this.orden2 = orden2;
        this.orden3 = orden3;
        this.organResolutori = organResolutori;
        this.plazos = plazos;
        this.recursos = recursos;
        this.requisitos = requisitos;
        this.resolucion = resolucion;
        this.responsable = responsable;
        this.resultat = resultat;
        this.resumen = resumen;
        this.signatura = signatura;
        this.silencio = silencio;
        this.taxa = taxa;
        this.tramite = tramite;
        this.unidadAdministrativa = unidadAdministrativa;
        this.url = url;
        this.validacion = validacion;
        this.ventanillaUnica = ventanillaUnica;
        this.version = version;
    }


    /**
     * Gets the destinatarios value for this ProcedimentDTO.
     * 
     * @return destinatarios
     */
    public java.lang.String getDestinatarios() {
        return destinatarios;
    }


    /**
     * Sets the destinatarios value for this ProcedimentDTO.
     * 
     * @param destinatarios
     */
    public void setDestinatarios(java.lang.String destinatarios) {
        this.destinatarios = destinatarios;
    }


    /**
     * Gets the familia value for this ProcedimentDTO.
     * 
     * @return familia
     */
    public java.lang.Long getFamilia() {
        return familia;
    }


    /**
     * Sets the familia value for this ProcedimentDTO.
     * 
     * @param familia
     */
    public void setFamilia(java.lang.Long familia) {
        this.familia = familia;
    }


    /**
     * Gets the fechaActualizacion value for this ProcedimentDTO.
     * 
     * @return fechaActualizacion
     */
    public java.util.Calendar getFechaActualizacion() {
        return fechaActualizacion;
    }


    /**
     * Sets the fechaActualizacion value for this ProcedimentDTO.
     * 
     * @param fechaActualizacion
     */
    public void setFechaActualizacion(java.util.Calendar fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    /**
     * Gets the fechaCaducidad value for this ProcedimentDTO.
     * 
     * @return fechaCaducidad
     */
    public java.util.Calendar getFechaCaducidad() {
        return fechaCaducidad;
    }


    /**
     * Sets the fechaCaducidad value for this ProcedimentDTO.
     * 
     * @param fechaCaducidad
     */
    public void setFechaCaducidad(java.util.Calendar fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }


    /**
     * Gets the fechaPublicacion value for this ProcedimentDTO.
     * 
     * @return fechaPublicacion
     */
    public java.util.Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }


    /**
     * Sets the fechaPublicacion value for this ProcedimentDTO.
     * 
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(java.util.Calendar fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    /**
     * Gets the id value for this ProcedimentDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ProcedimentDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the indicador value for this ProcedimentDTO.
     * 
     * @return indicador
     */
    public boolean isIndicador() {
        return indicador;
    }


    /**
     * Sets the indicador value for this ProcedimentDTO.
     * 
     * @param indicador
     */
    public void setIndicador(boolean indicador) {
        this.indicador = indicador;
    }


    /**
     * Gets the info value for this ProcedimentDTO.
     * 
     * @return info
     */
    public java.lang.String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this ProcedimentDTO.
     * 
     * @param info
     */
    public void setInfo(java.lang.String info) {
        this.info = info;
    }


    /**
     * Gets the iniciacion value for this ProcedimentDTO.
     * 
     * @return iniciacion
     */
    public java.lang.Long getIniciacion() {
        return iniciacion;
    }


    /**
     * Sets the iniciacion value for this ProcedimentDTO.
     * 
     * @param iniciacion
     */
    public void setIniciacion(java.lang.Long iniciacion) {
        this.iniciacion = iniciacion;
    }


    /**
     * Gets the lugar value for this ProcedimentDTO.
     * 
     * @return lugar
     */
    public java.lang.String getLugar() {
        return lugar;
    }


    /**
     * Sets the lugar value for this ProcedimentDTO.
     * 
     * @param lugar
     */
    public void setLugar(java.lang.String lugar) {
        this.lugar = lugar;
    }


    /**
     * Gets the nombre value for this ProcedimentDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ProcedimentDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the notificacion value for this ProcedimentDTO.
     * 
     * @return notificacion
     */
    public java.lang.String getNotificacion() {
        return notificacion;
    }


    /**
     * Sets the notificacion value for this ProcedimentDTO.
     * 
     * @param notificacion
     */
    public void setNotificacion(java.lang.String notificacion) {
        this.notificacion = notificacion;
    }


    /**
     * Gets the observaciones value for this ProcedimentDTO.
     * 
     * @return observaciones
     */
    public java.lang.String getObservaciones() {
        return observaciones;
    }


    /**
     * Sets the observaciones value for this ProcedimentDTO.
     * 
     * @param observaciones
     */
    public void setObservaciones(java.lang.String observaciones) {
        this.observaciones = observaciones;
    }


    /**
     * Gets the orden value for this ProcedimentDTO.
     * 
     * @return orden
     */
    public java.lang.Long getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this ProcedimentDTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Long orden) {
        this.orden = orden;
    }


    /**
     * Gets the orden2 value for this ProcedimentDTO.
     * 
     * @return orden2
     */
    public java.lang.Long getOrden2() {
        return orden2;
    }


    /**
     * Sets the orden2 value for this ProcedimentDTO.
     * 
     * @param orden2
     */
    public void setOrden2(java.lang.Long orden2) {
        this.orden2 = orden2;
    }


    /**
     * Gets the orden3 value for this ProcedimentDTO.
     * 
     * @return orden3
     */
    public java.lang.Long getOrden3() {
        return orden3;
    }


    /**
     * Sets the orden3 value for this ProcedimentDTO.
     * 
     * @param orden3
     */
    public void setOrden3(java.lang.Long orden3) {
        this.orden3 = orden3;
    }


    /**
     * Gets the organResolutori value for this ProcedimentDTO.
     * 
     * @return organResolutori
     */
    public java.lang.Long getOrganResolutori() {
        return organResolutori;
    }


    /**
     * Sets the organResolutori value for this ProcedimentDTO.
     * 
     * @param organResolutori
     */
    public void setOrganResolutori(java.lang.Long organResolutori) {
        this.organResolutori = organResolutori;
    }


    /**
     * Gets the plazos value for this ProcedimentDTO.
     * 
     * @return plazos
     */
    public java.lang.String getPlazos() {
        return plazos;
    }


    /**
     * Sets the plazos value for this ProcedimentDTO.
     * 
     * @param plazos
     */
    public void setPlazos(java.lang.String plazos) {
        this.plazos = plazos;
    }


    /**
     * Gets the recursos value for this ProcedimentDTO.
     * 
     * @return recursos
     */
    public java.lang.String getRecursos() {
        return recursos;
    }


    /**
     * Sets the recursos value for this ProcedimentDTO.
     * 
     * @param recursos
     */
    public void setRecursos(java.lang.String recursos) {
        this.recursos = recursos;
    }


    /**
     * Gets the requisitos value for this ProcedimentDTO.
     * 
     * @return requisitos
     */
    public java.lang.String getRequisitos() {
        return requisitos;
    }


    /**
     * Sets the requisitos value for this ProcedimentDTO.
     * 
     * @param requisitos
     */
    public void setRequisitos(java.lang.String requisitos) {
        this.requisitos = requisitos;
    }


    /**
     * Gets the resolucion value for this ProcedimentDTO.
     * 
     * @return resolucion
     */
    public java.lang.String getResolucion() {
        return resolucion;
    }


    /**
     * Sets the resolucion value for this ProcedimentDTO.
     * 
     * @param resolucion
     */
    public void setResolucion(java.lang.String resolucion) {
        this.resolucion = resolucion;
    }


    /**
     * Gets the responsable value for this ProcedimentDTO.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this ProcedimentDTO.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the resultat value for this ProcedimentDTO.
     * 
     * @return resultat
     */
    public java.lang.String getResultat() {
        return resultat;
    }


    /**
     * Sets the resultat value for this ProcedimentDTO.
     * 
     * @param resultat
     */
    public void setResultat(java.lang.String resultat) {
        this.resultat = resultat;
    }


    /**
     * Gets the resumen value for this ProcedimentDTO.
     * 
     * @return resumen
     */
    public java.lang.String getResumen() {
        return resumen;
    }


    /**
     * Sets the resumen value for this ProcedimentDTO.
     * 
     * @param resumen
     */
    public void setResumen(java.lang.String resumen) {
        this.resumen = resumen;
    }


    /**
     * Gets the signatura value for this ProcedimentDTO.
     * 
     * @return signatura
     */
    public java.lang.String getSignatura() {
        return signatura;
    }


    /**
     * Sets the signatura value for this ProcedimentDTO.
     * 
     * @param signatura
     */
    public void setSignatura(java.lang.String signatura) {
        this.signatura = signatura;
    }


    /**
     * Gets the silencio value for this ProcedimentDTO.
     * 
     * @return silencio
     */
    public java.lang.String getSilencio() {
        return silencio;
    }


    /**
     * Sets the silencio value for this ProcedimentDTO.
     * 
     * @param silencio
     */
    public void setSilencio(java.lang.String silencio) {
        this.silencio = silencio;
    }


    /**
     * Gets the taxa value for this ProcedimentDTO.
     * 
     * @return taxa
     */
    public boolean isTaxa() {
        return taxa;
    }


    /**
     * Sets the taxa value for this ProcedimentDTO.
     * 
     * @param taxa
     */
    public void setTaxa(boolean taxa) {
        this.taxa = taxa;
    }


    /**
     * Gets the tramite value for this ProcedimentDTO.
     * 
     * @return tramite
     */
    public java.lang.String getTramite() {
        return tramite;
    }


    /**
     * Sets the tramite value for this ProcedimentDTO.
     * 
     * @param tramite
     */
    public void setTramite(java.lang.String tramite) {
        this.tramite = tramite;
    }


    /**
     * Gets the unidadAdministrativa value for this ProcedimentDTO.
     * 
     * @return unidadAdministrativa
     */
    public java.lang.Long getUnidadAdministrativa() {
        return unidadAdministrativa;
    }


    /**
     * Sets the unidadAdministrativa value for this ProcedimentDTO.
     * 
     * @param unidadAdministrativa
     */
    public void setUnidadAdministrativa(java.lang.Long unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }


    /**
     * Gets the url value for this ProcedimentDTO.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this ProcedimentDTO.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the validacion value for this ProcedimentDTO.
     * 
     * @return validacion
     */
    public java.lang.Integer getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this ProcedimentDTO.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.Integer validacion) {
        this.validacion = validacion;
    }


    /**
     * Gets the ventanillaUnica value for this ProcedimentDTO.
     * 
     * @return ventanillaUnica
     */
    public boolean isVentanillaUnica() {
        return ventanillaUnica;
    }


    /**
     * Sets the ventanillaUnica value for this ProcedimentDTO.
     * 
     * @param ventanillaUnica
     */
    public void setVentanillaUnica(boolean ventanillaUnica) {
        this.ventanillaUnica = ventanillaUnica;
    }


    /**
     * Gets the version value for this ProcedimentDTO.
     * 
     * @return version
     */
    public java.lang.Long getVersion() {
        return version;
    }


    /**
     * Sets the version value for this ProcedimentDTO.
     * 
     * @param version
     */
    public void setVersion(java.lang.Long version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcedimentDTO)) return false;
        ProcedimentDTO other = (ProcedimentDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.destinatarios==null && other.getDestinatarios()==null) || 
             (this.destinatarios!=null &&
              this.destinatarios.equals(other.getDestinatarios()))) &&
            ((this.familia==null && other.getFamilia()==null) || 
             (this.familia!=null &&
              this.familia.equals(other.getFamilia()))) &&
            ((this.fechaActualizacion==null && other.getFechaActualizacion()==null) || 
             (this.fechaActualizacion!=null &&
              this.fechaActualizacion.equals(other.getFechaActualizacion()))) &&
            ((this.fechaCaducidad==null && other.getFechaCaducidad()==null) || 
             (this.fechaCaducidad!=null &&
              this.fechaCaducidad.equals(other.getFechaCaducidad()))) &&
            ((this.fechaPublicacion==null && other.getFechaPublicacion()==null) || 
             (this.fechaPublicacion!=null &&
              this.fechaPublicacion.equals(other.getFechaPublicacion()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            this.indicador == other.isIndicador() &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.iniciacion==null && other.getIniciacion()==null) || 
             (this.iniciacion!=null &&
              this.iniciacion.equals(other.getIniciacion()))) &&
            ((this.lugar==null && other.getLugar()==null) || 
             (this.lugar!=null &&
              this.lugar.equals(other.getLugar()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.notificacion==null && other.getNotificacion()==null) || 
             (this.notificacion!=null &&
              this.notificacion.equals(other.getNotificacion()))) &&
            ((this.observaciones==null && other.getObservaciones()==null) || 
             (this.observaciones!=null &&
              this.observaciones.equals(other.getObservaciones()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.orden2==null && other.getOrden2()==null) || 
             (this.orden2!=null &&
              this.orden2.equals(other.getOrden2()))) &&
            ((this.orden3==null && other.getOrden3()==null) || 
             (this.orden3!=null &&
              this.orden3.equals(other.getOrden3()))) &&
            ((this.organResolutori==null && other.getOrganResolutori()==null) || 
             (this.organResolutori!=null &&
              this.organResolutori.equals(other.getOrganResolutori()))) &&
            ((this.plazos==null && other.getPlazos()==null) || 
             (this.plazos!=null &&
              this.plazos.equals(other.getPlazos()))) &&
            ((this.recursos==null && other.getRecursos()==null) || 
             (this.recursos!=null &&
              this.recursos.equals(other.getRecursos()))) &&
            ((this.requisitos==null && other.getRequisitos()==null) || 
             (this.requisitos!=null &&
              this.requisitos.equals(other.getRequisitos()))) &&
            ((this.resolucion==null && other.getResolucion()==null) || 
             (this.resolucion!=null &&
              this.resolucion.equals(other.getResolucion()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.resultat==null && other.getResultat()==null) || 
             (this.resultat!=null &&
              this.resultat.equals(other.getResultat()))) &&
            ((this.resumen==null && other.getResumen()==null) || 
             (this.resumen!=null &&
              this.resumen.equals(other.getResumen()))) &&
            ((this.signatura==null && other.getSignatura()==null) || 
             (this.signatura!=null &&
              this.signatura.equals(other.getSignatura()))) &&
            ((this.silencio==null && other.getSilencio()==null) || 
             (this.silencio!=null &&
              this.silencio.equals(other.getSilencio()))) &&
            this.taxa == other.isTaxa() &&
            ((this.tramite==null && other.getTramite()==null) || 
             (this.tramite!=null &&
              this.tramite.equals(other.getTramite()))) &&
            ((this.unidadAdministrativa==null && other.getUnidadAdministrativa()==null) || 
             (this.unidadAdministrativa!=null &&
              this.unidadAdministrativa.equals(other.getUnidadAdministrativa()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.validacion==null && other.getValidacion()==null) || 
             (this.validacion!=null &&
              this.validacion.equals(other.getValidacion()))) &&
            this.ventanillaUnica == other.isVentanillaUnica() &&
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
        if (getDestinatarios() != null) {
            _hashCode += getDestinatarios().hashCode();
        }
        if (getFamilia() != null) {
            _hashCode += getFamilia().hashCode();
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        _hashCode += (isIndicador() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getIniciacion() != null) {
            _hashCode += getIniciacion().hashCode();
        }
        if (getLugar() != null) {
            _hashCode += getLugar().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getNotificacion() != null) {
            _hashCode += getNotificacion().hashCode();
        }
        if (getObservaciones() != null) {
            _hashCode += getObservaciones().hashCode();
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
        if (getOrganResolutori() != null) {
            _hashCode += getOrganResolutori().hashCode();
        }
        if (getPlazos() != null) {
            _hashCode += getPlazos().hashCode();
        }
        if (getRecursos() != null) {
            _hashCode += getRecursos().hashCode();
        }
        if (getRequisitos() != null) {
            _hashCode += getRequisitos().hashCode();
        }
        if (getResolucion() != null) {
            _hashCode += getResolucion().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getResultat() != null) {
            _hashCode += getResultat().hashCode();
        }
        if (getResumen() != null) {
            _hashCode += getResumen().hashCode();
        }
        if (getSignatura() != null) {
            _hashCode += getSignatura().hashCode();
        }
        if (getSilencio() != null) {
            _hashCode += getSilencio().hashCode();
        }
        _hashCode += (isTaxa() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTramite() != null) {
            _hashCode += getTramite().hashCode();
        }
        if (getUnidadAdministrativa() != null) {
            _hashCode += getUnidadAdministrativa().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        _hashCode += (isVentanillaUnica() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcedimentDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://procediment.v2.api.rolsac.caib.es", "ProcedimentDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "familia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iniciacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iniciacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lugar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lugar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organResolutori");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organResolutori"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plazos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "plazos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recursos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recursos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requisitos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requisitos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolucion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resolucion"));
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
        elemField.setFieldName("resultat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resumen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resumen"));
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
        elemField.setFieldName("silencio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "silencio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taxa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadAdministrativa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadAdministrativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ventanillaUnica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ventanillaUnica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
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
