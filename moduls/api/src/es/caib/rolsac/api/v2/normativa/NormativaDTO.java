/**
 * NormativaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.normativa;

public class NormativaDTO  extends es.caib.rolsac.api.v2.general.EntitatRemota  implements java.io.Serializable {
    private java.lang.String apartado;

    private java.lang.Long archivo;

    private java.lang.Long boletin;

    private java.lang.String codiVuds;

    private java.lang.String descCodiVuds;

    private java.lang.String enlace;

    private java.util.Calendar fecha;

    private java.util.Calendar fechaBoletin;

    private java.lang.Long id;

    private boolean local;

    private java.lang.Long numero;

    private java.lang.String observaciones;

    private java.lang.Integer paginaFinal;

    private java.lang.Integer paginaInicial;

    private java.lang.Long registro;

    private java.lang.String responsable;

    private java.lang.String seccion;

    private java.lang.Long tipo;

    private java.lang.String titulo;

    private java.lang.Long unidadAdministrativa;

    private java.lang.Integer validacion;

    public NormativaDTO() {
    }

    public NormativaDTO(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota,
           java.lang.String apartado,
           java.lang.Long archivo,
           java.lang.Long boletin,
           java.lang.String codiVuds,
           java.lang.String descCodiVuds,
           java.lang.String enlace,
           java.util.Calendar fecha,
           java.util.Calendar fechaBoletin,
           java.lang.Long id,
           boolean local,
           java.lang.Long numero,
           java.lang.String observaciones,
           java.lang.Integer paginaFinal,
           java.lang.Integer paginaInicial,
           java.lang.Long registro,
           java.lang.String responsable,
           java.lang.String seccion,
           java.lang.Long tipo,
           java.lang.String titulo,
           java.lang.Long unidadAdministrativa,
           java.lang.Integer validacion) {
        super(
            administracionRemota,
            idExterno,
            urlRemota);
        this.apartado = apartado;
        this.archivo = archivo;
        this.boletin = boletin;
        this.codiVuds = codiVuds;
        this.descCodiVuds = descCodiVuds;
        this.enlace = enlace;
        this.fecha = fecha;
        this.fechaBoletin = fechaBoletin;
        this.id = id;
        this.local = local;
        this.numero = numero;
        this.observaciones = observaciones;
        this.paginaFinal = paginaFinal;
        this.paginaInicial = paginaInicial;
        this.registro = registro;
        this.responsable = responsable;
        this.seccion = seccion;
        this.tipo = tipo;
        this.titulo = titulo;
        this.unidadAdministrativa = unidadAdministrativa;
        this.validacion = validacion;
    }


    /**
     * Gets the apartado value for this NormativaDTO.
     * 
     * @return apartado
     */
    public java.lang.String getApartado() {
        return apartado;
    }


    /**
     * Sets the apartado value for this NormativaDTO.
     * 
     * @param apartado
     */
    public void setApartado(java.lang.String apartado) {
        this.apartado = apartado;
    }


    /**
     * Gets the archivo value for this NormativaDTO.
     * 
     * @return archivo
     */
    public java.lang.Long getArchivo() {
        return archivo;
    }


    /**
     * Sets the archivo value for this NormativaDTO.
     * 
     * @param archivo
     */
    public void setArchivo(java.lang.Long archivo) {
        this.archivo = archivo;
    }


    /**
     * Gets the boletin value for this NormativaDTO.
     * 
     * @return boletin
     */
    public java.lang.Long getBoletin() {
        return boletin;
    }


    /**
     * Sets the boletin value for this NormativaDTO.
     * 
     * @param boletin
     */
    public void setBoletin(java.lang.Long boletin) {
        this.boletin = boletin;
    }


    /**
     * Gets the codiVuds value for this NormativaDTO.
     * 
     * @return codiVuds
     */
    public java.lang.String getCodiVuds() {
        return codiVuds;
    }


    /**
     * Sets the codiVuds value for this NormativaDTO.
     * 
     * @param codiVuds
     */
    public void setCodiVuds(java.lang.String codiVuds) {
        this.codiVuds = codiVuds;
    }


    /**
     * Gets the descCodiVuds value for this NormativaDTO.
     * 
     * @return descCodiVuds
     */
    public java.lang.String getDescCodiVuds() {
        return descCodiVuds;
    }


    /**
     * Sets the descCodiVuds value for this NormativaDTO.
     * 
     * @param descCodiVuds
     */
    public void setDescCodiVuds(java.lang.String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }


    /**
     * Gets the enlace value for this NormativaDTO.
     * 
     * @return enlace
     */
    public java.lang.String getEnlace() {
        return enlace;
    }


    /**
     * Sets the enlace value for this NormativaDTO.
     * 
     * @param enlace
     */
    public void setEnlace(java.lang.String enlace) {
        this.enlace = enlace;
    }


    /**
     * Gets the fecha value for this NormativaDTO.
     * 
     * @return fecha
     */
    public java.util.Calendar getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this NormativaDTO.
     * 
     * @param fecha
     */
    public void setFecha(java.util.Calendar fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the fechaBoletin value for this NormativaDTO.
     * 
     * @return fechaBoletin
     */
    public java.util.Calendar getFechaBoletin() {
        return fechaBoletin;
    }


    /**
     * Sets the fechaBoletin value for this NormativaDTO.
     * 
     * @param fechaBoletin
     */
    public void setFechaBoletin(java.util.Calendar fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }


    /**
     * Gets the id value for this NormativaDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this NormativaDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the local value for this NormativaDTO.
     * 
     * @return local
     */
    public boolean isLocal() {
        return local;
    }


    /**
     * Sets the local value for this NormativaDTO.
     * 
     * @param local
     */
    public void setLocal(boolean local) {
        this.local = local;
    }


    /**
     * Gets the numero value for this NormativaDTO.
     * 
     * @return numero
     */
    public java.lang.Long getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this NormativaDTO.
     * 
     * @param numero
     */
    public void setNumero(java.lang.Long numero) {
        this.numero = numero;
    }


    /**
     * Gets the observaciones value for this NormativaDTO.
     * 
     * @return observaciones
     */
    public java.lang.String getObservaciones() {
        return observaciones;
    }


    /**
     * Sets the observaciones value for this NormativaDTO.
     * 
     * @param observaciones
     */
    public void setObservaciones(java.lang.String observaciones) {
        this.observaciones = observaciones;
    }


    /**
     * Gets the paginaFinal value for this NormativaDTO.
     * 
     * @return paginaFinal
     */
    public java.lang.Integer getPaginaFinal() {
        return paginaFinal;
    }


    /**
     * Sets the paginaFinal value for this NormativaDTO.
     * 
     * @param paginaFinal
     */
    public void setPaginaFinal(java.lang.Integer paginaFinal) {
        this.paginaFinal = paginaFinal;
    }


    /**
     * Gets the paginaInicial value for this NormativaDTO.
     * 
     * @return paginaInicial
     */
    public java.lang.Integer getPaginaInicial() {
        return paginaInicial;
    }


    /**
     * Sets the paginaInicial value for this NormativaDTO.
     * 
     * @param paginaInicial
     */
    public void setPaginaInicial(java.lang.Integer paginaInicial) {
        this.paginaInicial = paginaInicial;
    }


    /**
     * Gets the registro value for this NormativaDTO.
     * 
     * @return registro
     */
    public java.lang.Long getRegistro() {
        return registro;
    }


    /**
     * Sets the registro value for this NormativaDTO.
     * 
     * @param registro
     */
    public void setRegistro(java.lang.Long registro) {
        this.registro = registro;
    }


    /**
     * Gets the responsable value for this NormativaDTO.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this NormativaDTO.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the seccion value for this NormativaDTO.
     * 
     * @return seccion
     */
    public java.lang.String getSeccion() {
        return seccion;
    }


    /**
     * Sets the seccion value for this NormativaDTO.
     * 
     * @param seccion
     */
    public void setSeccion(java.lang.String seccion) {
        this.seccion = seccion;
    }


    /**
     * Gets the tipo value for this NormativaDTO.
     * 
     * @return tipo
     */
    public java.lang.Long getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this NormativaDTO.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.Long tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the titulo value for this NormativaDTO.
     * 
     * @return titulo
     */
    public java.lang.String getTitulo() {
        return titulo;
    }


    /**
     * Sets the titulo value for this NormativaDTO.
     * 
     * @param titulo
     */
    public void setTitulo(java.lang.String titulo) {
        this.titulo = titulo;
    }


    /**
     * Gets the unidadAdministrativa value for this NormativaDTO.
     * 
     * @return unidadAdministrativa
     */
    public java.lang.Long getUnidadAdministrativa() {
        return unidadAdministrativa;
    }


    /**
     * Sets the unidadAdministrativa value for this NormativaDTO.
     * 
     * @param unidadAdministrativa
     */
    public void setUnidadAdministrativa(java.lang.Long unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }


    /**
     * Gets the validacion value for this NormativaDTO.
     * 
     * @return validacion
     */
    public java.lang.Integer getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this NormativaDTO.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.Integer validacion) {
        this.validacion = validacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NormativaDTO)) return false;
        NormativaDTO other = (NormativaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.apartado==null && other.getApartado()==null) || 
             (this.apartado!=null &&
              this.apartado.equals(other.getApartado()))) &&
            ((this.archivo==null && other.getArchivo()==null) || 
             (this.archivo!=null &&
              this.archivo.equals(other.getArchivo()))) &&
            ((this.boletin==null && other.getBoletin()==null) || 
             (this.boletin!=null &&
              this.boletin.equals(other.getBoletin()))) &&
            ((this.codiVuds==null && other.getCodiVuds()==null) || 
             (this.codiVuds!=null &&
              this.codiVuds.equals(other.getCodiVuds()))) &&
            ((this.descCodiVuds==null && other.getDescCodiVuds()==null) || 
             (this.descCodiVuds!=null &&
              this.descCodiVuds.equals(other.getDescCodiVuds()))) &&
            ((this.enlace==null && other.getEnlace()==null) || 
             (this.enlace!=null &&
              this.enlace.equals(other.getEnlace()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.fechaBoletin==null && other.getFechaBoletin()==null) || 
             (this.fechaBoletin!=null &&
              this.fechaBoletin.equals(other.getFechaBoletin()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            this.local == other.isLocal() &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.observaciones==null && other.getObservaciones()==null) || 
             (this.observaciones!=null &&
              this.observaciones.equals(other.getObservaciones()))) &&
            ((this.paginaFinal==null && other.getPaginaFinal()==null) || 
             (this.paginaFinal!=null &&
              this.paginaFinal.equals(other.getPaginaFinal()))) &&
            ((this.paginaInicial==null && other.getPaginaInicial()==null) || 
             (this.paginaInicial!=null &&
              this.paginaInicial.equals(other.getPaginaInicial()))) &&
            ((this.registro==null && other.getRegistro()==null) || 
             (this.registro!=null &&
              this.registro.equals(other.getRegistro()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.seccion==null && other.getSeccion()==null) || 
             (this.seccion!=null &&
              this.seccion.equals(other.getSeccion()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.titulo==null && other.getTitulo()==null) || 
             (this.titulo!=null &&
              this.titulo.equals(other.getTitulo()))) &&
            ((this.unidadAdministrativa==null && other.getUnidadAdministrativa()==null) || 
             (this.unidadAdministrativa!=null &&
              this.unidadAdministrativa.equals(other.getUnidadAdministrativa()))) &&
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
        if (getApartado() != null) {
            _hashCode += getApartado().hashCode();
        }
        if (getArchivo() != null) {
            _hashCode += getArchivo().hashCode();
        }
        if (getBoletin() != null) {
            _hashCode += getBoletin().hashCode();
        }
        if (getCodiVuds() != null) {
            _hashCode += getCodiVuds().hashCode();
        }
        if (getDescCodiVuds() != null) {
            _hashCode += getDescCodiVuds().hashCode();
        }
        if (getEnlace() != null) {
            _hashCode += getEnlace().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getFechaBoletin() != null) {
            _hashCode += getFechaBoletin().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        _hashCode += (isLocal() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getObservaciones() != null) {
            _hashCode += getObservaciones().hashCode();
        }
        if (getPaginaFinal() != null) {
            _hashCode += getPaginaFinal().hashCode();
        }
        if (getPaginaInicial() != null) {
            _hashCode += getPaginaInicial().hashCode();
        }
        if (getRegistro() != null) {
            _hashCode += getRegistro().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getSeccion() != null) {
            _hashCode += getSeccion().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getTitulo() != null) {
            _hashCode += getTitulo().hashCode();
        }
        if (getUnidadAdministrativa() != null) {
            _hashCode += getUnidadAdministrativa().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NormativaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apartado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apartado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "archivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("boletin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "boletin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("enlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaBoletin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaBoletin"));
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
        elemField.setFieldName("local");
        elemField.setXmlName(new javax.xml.namespace.QName("", "local"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paginaFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paginaInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responsable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titulo"));
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
