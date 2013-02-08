/**
 * TramitDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.tramit;

public class TramitDTO  extends es.caib.rolsac.api.v2.general.EntitatRemota  implements java.io.Serializable {
    private java.lang.String codiVuds;

    private java.util.Calendar dataActualitzacio;

    private java.lang.String dataActualitzacioVuds;

    private java.util.Calendar dataCaducitat;

    private java.util.Calendar dataPublicacio;

    private java.util.Calendar dataInici;

    private java.util.Calendar dataTancament;

    private java.lang.String descCodiVuds;

    private java.lang.String descripcion;

    private java.lang.String documentacion;

    private int fase;

    private long id;

    private java.lang.String idTraTel;

    private java.lang.String lugar;

    private java.lang.String nombre;

    private java.lang.String observaciones;

    private java.lang.Long orden;

    private java.lang.Long organCompetent;

    private java.lang.String plazos;

    private java.lang.Long procedimiento;

    private java.lang.String requisits;

    private java.lang.String urlExterna;

    private java.lang.Long validacio;

    private java.lang.Integer versio;

    public TramitDTO() {
    }

    public TramitDTO(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota,
           java.lang.String codiVuds,
           java.util.Calendar dataActualitzacio,
           java.lang.String dataActualitzacioVuds,
           java.util.Calendar dataCaducitat,
           java.util.Calendar dataPublicacio,
           java.util.Calendar dataInici,
           java.util.Calendar dataTancament,
           java.lang.String descCodiVuds,
           java.lang.String descripcion,
           java.lang.String documentacion,
           int fase,
           long id,
           java.lang.String idTraTel,
           java.lang.String lugar,
           java.lang.String nombre,
           java.lang.String observaciones,
           java.lang.Long orden,
           java.lang.Long organCompetent,
           java.lang.String plazos,
           java.lang.Long procedimiento,
           java.lang.String requisits,
           java.lang.String urlExterna,
           java.lang.Long validacio,
           java.lang.Integer versio) {
        super(
            administracionRemota,
            idExterno,
            urlRemota);
        this.codiVuds = codiVuds;
        this.dataActualitzacio = dataActualitzacio;
        this.dataActualitzacioVuds = dataActualitzacioVuds;
        this.dataCaducitat = dataCaducitat;
        this.dataPublicacio = dataPublicacio;
        this.dataInici = dataInici;
        this.dataTancament = dataTancament;
        this.descCodiVuds = descCodiVuds;
        this.descripcion = descripcion;
        this.documentacion = documentacion;
        this.fase = fase;
        this.id = id;
        this.idTraTel = idTraTel;
        this.lugar = lugar;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.orden = orden;
        this.organCompetent = organCompetent;
        this.plazos = plazos;
        this.procedimiento = procedimiento;
        this.requisits = requisits;
        this.urlExterna = urlExterna;
        this.validacio = validacio;
        this.versio = versio;
    }


    /**
     * Gets the codiVuds value for this TramitDTO.
     * 
     * @return codiVuds
     */
    public java.lang.String getCodiVuds() {
        return codiVuds;
    }


    /**
     * Sets the codiVuds value for this TramitDTO.
     * 
     * @param codiVuds
     */
    public void setCodiVuds(java.lang.String codiVuds) {
        this.codiVuds = codiVuds;
    }


    /**
     * Gets the dataActualitzacio value for this TramitDTO.
     * 
     * @return dataActualitzacio
     */
    public java.util.Calendar getDataActualitzacio() {
        return dataActualitzacio;
    }


    /**
     * Sets the dataActualitzacio value for this TramitDTO.
     * 
     * @param dataActualitzacio
     */
    public void setDataActualitzacio(java.util.Calendar dataActualitzacio) {
        this.dataActualitzacio = dataActualitzacio;
    }


    /**
     * Gets the dataActualitzacioVuds value for this TramitDTO.
     * 
     * @return dataActualitzacioVuds
     */
    public java.lang.String getDataActualitzacioVuds() {
        return dataActualitzacioVuds;
    }


    /**
     * Sets the dataActualitzacioVuds value for this TramitDTO.
     * 
     * @param dataActualitzacioVuds
     */
    public void setDataActualitzacioVuds(java.lang.String dataActualitzacioVuds) {
        this.dataActualitzacioVuds = dataActualitzacioVuds;
    }


    /**
     * Gets the dataCaducitat value for this TramitDTO.
     * 
     * @return dataCaducitat
     */
    public java.util.Calendar getDataCaducitat() {
        return dataCaducitat;
    }


    /**
     * Sets the dataCaducitat value for this TramitDTO.
     * 
     * @param dataCaducitat
     */
    public void setDataCaducitat(java.util.Calendar dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }


    /**
     * Gets the dataPublicacio value for this TramitDTO.
     * 
     * @return dataPublicacio
     */
    public java.util.Calendar getDataPublicacio() {
        return dataPublicacio;
    }


    /**
     * Sets the dataPublicacio value for this TramitDTO.
     * 
     * @param dataPublicacio
     */
    public void setDataPublicacio(java.util.Calendar dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
    }


    /**
     * Gets the dataInici value for this TramitDTO.
     * 
     * @return dataInici
     */
    public java.util.Calendar getDataInici() {
        return dataInici;
    }


    /**
     * Sets the dataInici value for this TramitDTO.
     * 
     * @param dataInici
     */
    public void setDataInici(java.util.Calendar dataInici) {
        this.dataInici = dataInici;
    }


    /**
     * Gets the dataTancament value for this TramitDTO.
     * 
     * @return dataTancament
     */
    public java.util.Calendar getDataTancament() {
        return dataTancament;
    }


    /**
     * Sets the dataTancament value for this TramitDTO.
     * 
     * @param dataTancament
     */
    public void setDataTancament(java.util.Calendar dataTancament) {
        this.dataTancament = dataTancament;
    }


    /**
     * Gets the descCodiVuds value for this TramitDTO.
     * 
     * @return descCodiVuds
     */
    public java.lang.String getDescCodiVuds() {
        return descCodiVuds;
    }


    /**
     * Sets the descCodiVuds value for this TramitDTO.
     * 
     * @param descCodiVuds
     */
    public void setDescCodiVuds(java.lang.String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }


    /**
     * Gets the descripcion value for this TramitDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this TramitDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the documentacion value for this TramitDTO.
     * 
     * @return documentacion
     */
    public java.lang.String getDocumentacion() {
        return documentacion;
    }


    /**
     * Sets the documentacion value for this TramitDTO.
     * 
     * @param documentacion
     */
    public void setDocumentacion(java.lang.String documentacion) {
        this.documentacion = documentacion;
    }


    /**
     * Gets the fase value for this TramitDTO.
     * 
     * @return fase
     */
    public int getFase() {
        return fase;
    }


    /**
     * Sets the fase value for this TramitDTO.
     * 
     * @param fase
     */
    public void setFase(int fase) {
        this.fase = fase;
    }


    /**
     * Gets the id value for this TramitDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this TramitDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the idTraTel value for this TramitDTO.
     * 
     * @return idTraTel
     */
    public java.lang.String getIdTraTel() {
        return idTraTel;
    }


    /**
     * Sets the idTraTel value for this TramitDTO.
     * 
     * @param idTraTel
     */
    public void setIdTraTel(java.lang.String idTraTel) {
        this.idTraTel = idTraTel;
    }


    /**
     * Gets the lugar value for this TramitDTO.
     * 
     * @return lugar
     */
    public java.lang.String getLugar() {
        return lugar;
    }


    /**
     * Sets the lugar value for this TramitDTO.
     * 
     * @param lugar
     */
    public void setLugar(java.lang.String lugar) {
        this.lugar = lugar;
    }


    /**
     * Gets the nombre value for this TramitDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this TramitDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the observaciones value for this TramitDTO.
     * 
     * @return observaciones
     */
    public java.lang.String getObservaciones() {
        return observaciones;
    }


    /**
     * Sets the observaciones value for this TramitDTO.
     * 
     * @param observaciones
     */
    public void setObservaciones(java.lang.String observaciones) {
        this.observaciones = observaciones;
    }


    /**
     * Gets the orden value for this TramitDTO.
     * 
     * @return orden
     */
    public java.lang.Long getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this TramitDTO.
     * 
     * @param orden
     */
    public void setOrden(java.lang.Long orden) {
        this.orden = orden;
    }


    /**
     * Gets the organCompetent value for this TramitDTO.
     * 
     * @return organCompetent
     */
    public java.lang.Long getOrganCompetent() {
        return organCompetent;
    }


    /**
     * Sets the organCompetent value for this TramitDTO.
     * 
     * @param organCompetent
     */
    public void setOrganCompetent(java.lang.Long organCompetent) {
        this.organCompetent = organCompetent;
    }


    /**
     * Gets the plazos value for this TramitDTO.
     * 
     * @return plazos
     */
    public java.lang.String getPlazos() {
        return plazos;
    }


    /**
     * Sets the plazos value for this TramitDTO.
     * 
     * @param plazos
     */
    public void setPlazos(java.lang.String plazos) {
        this.plazos = plazos;
    }


    /**
     * Gets the procedimiento value for this TramitDTO.
     * 
     * @return procedimiento
     */
    public java.lang.Long getProcedimiento() {
        return procedimiento;
    }


    /**
     * Sets the procedimiento value for this TramitDTO.
     * 
     * @param procedimiento
     */
    public void setProcedimiento(java.lang.Long procedimiento) {
        this.procedimiento = procedimiento;
    }


    /**
     * Gets the requisits value for this TramitDTO.
     * 
     * @return requisits
     */
    public java.lang.String getRequisits() {
        return requisits;
    }


    /**
     * Sets the requisits value for this TramitDTO.
     * 
     * @param requisits
     */
    public void setRequisits(java.lang.String requisits) {
        this.requisits = requisits;
    }


    /**
     * Gets the urlExterna value for this TramitDTO.
     * 
     * @return urlExterna
     */
    public java.lang.String getUrlExterna() {
        return urlExterna;
    }


    /**
     * Sets the urlExterna value for this TramitDTO.
     * 
     * @param urlExterna
     */
    public void setUrlExterna(java.lang.String urlExterna) {
        this.urlExterna = urlExterna;
    }


    /**
     * Gets the validacio value for this TramitDTO.
     * 
     * @return validacio
     */
    public java.lang.Long getValidacio() {
        return validacio;
    }


    /**
     * Sets the validacio value for this TramitDTO.
     * 
     * @param validacio
     */
    public void setValidacio(java.lang.Long validacio) {
        this.validacio = validacio;
    }


    /**
     * Gets the versio value for this TramitDTO.
     * 
     * @return versio
     */
    public java.lang.Integer getVersio() {
        return versio;
    }


    /**
     * Sets the versio value for this TramitDTO.
     * 
     * @param versio
     */
    public void setVersio(java.lang.Integer versio) {
        this.versio = versio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TramitDTO)) return false;
        TramitDTO other = (TramitDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codiVuds==null && other.getCodiVuds()==null) || 
             (this.codiVuds!=null &&
              this.codiVuds.equals(other.getCodiVuds()))) &&
            ((this.dataActualitzacio==null && other.getDataActualitzacio()==null) || 
             (this.dataActualitzacio!=null &&
              this.dataActualitzacio.equals(other.getDataActualitzacio()))) &&
            ((this.dataActualitzacioVuds==null && other.getDataActualitzacioVuds()==null) || 
             (this.dataActualitzacioVuds!=null &&
              this.dataActualitzacioVuds.equals(other.getDataActualitzacioVuds()))) &&
            ((this.dataCaducitat==null && other.getDataCaducitat()==null) || 
             (this.dataCaducitat!=null &&
              this.dataCaducitat.equals(other.getDataCaducitat()))) &&
            ((this.dataPublicacio==null && other.getDataPublicacio()==null) || 
             (this.dataPublicacio!=null &&
              this.dataPublicacio.equals(other.getDataPublicacio()))) &&
            ((this.dataInici==null && other.getDataInici()==null) || 
             (this.dataInici!=null &&
              this.dataInici.equals(other.getDataInici()))) &&
            ((this.dataTancament==null && other.getDataTancament()==null) || 
             (this.dataTancament!=null &&
              this.dataTancament.equals(other.getDataTancament()))) &&
            ((this.descCodiVuds==null && other.getDescCodiVuds()==null) || 
             (this.descCodiVuds!=null &&
              this.descCodiVuds.equals(other.getDescCodiVuds()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.documentacion==null && other.getDocumentacion()==null) || 
             (this.documentacion!=null &&
              this.documentacion.equals(other.getDocumentacion()))) &&
            this.fase == other.getFase() &&
            this.id == other.getId() &&
            ((this.idTraTel==null && other.getIdTraTel()==null) || 
             (this.idTraTel!=null &&
              this.idTraTel.equals(other.getIdTraTel()))) &&
            ((this.lugar==null && other.getLugar()==null) || 
             (this.lugar!=null &&
              this.lugar.equals(other.getLugar()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.observaciones==null && other.getObservaciones()==null) || 
             (this.observaciones!=null &&
              this.observaciones.equals(other.getObservaciones()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.organCompetent==null && other.getOrganCompetent()==null) || 
             (this.organCompetent!=null &&
              this.organCompetent.equals(other.getOrganCompetent()))) &&
            ((this.plazos==null && other.getPlazos()==null) || 
             (this.plazos!=null &&
              this.plazos.equals(other.getPlazos()))) &&
            ((this.procedimiento==null && other.getProcedimiento()==null) || 
             (this.procedimiento!=null &&
              this.procedimiento.equals(other.getProcedimiento()))) &&
            ((this.requisits==null && other.getRequisits()==null) || 
             (this.requisits!=null &&
              this.requisits.equals(other.getRequisits()))) &&
            ((this.urlExterna==null && other.getUrlExterna()==null) || 
             (this.urlExterna!=null &&
              this.urlExterna.equals(other.getUrlExterna()))) &&
            ((this.validacio==null && other.getValidacio()==null) || 
             (this.validacio!=null &&
              this.validacio.equals(other.getValidacio()))) &&
            ((this.versio==null && other.getVersio()==null) || 
             (this.versio!=null &&
              this.versio.equals(other.getVersio())));
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
        if (getCodiVuds() != null) {
            _hashCode += getCodiVuds().hashCode();
        }
        if (getDataActualitzacio() != null) {
            _hashCode += getDataActualitzacio().hashCode();
        }
        if (getDataActualitzacioVuds() != null) {
            _hashCode += getDataActualitzacioVuds().hashCode();
        }
        if (getDataCaducitat() != null) {
            _hashCode += getDataCaducitat().hashCode();
        }
        if (getDataPublicacio() != null) {
            _hashCode += getDataPublicacio().hashCode();
        }
        if (getDataInici() != null) {
            _hashCode += getDataInici().hashCode();
        }
        if (getDataTancament() != null) {
            _hashCode += getDataTancament().hashCode();
        }
        if (getDescCodiVuds() != null) {
            _hashCode += getDescCodiVuds().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getDocumentacion() != null) {
            _hashCode += getDocumentacion().hashCode();
        }
        _hashCode += getFase();
        _hashCode += new Long(getId()).hashCode();
        if (getIdTraTel() != null) {
            _hashCode += getIdTraTel().hashCode();
        }
        if (getLugar() != null) {
            _hashCode += getLugar().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getObservaciones() != null) {
            _hashCode += getObservaciones().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getOrganCompetent() != null) {
            _hashCode += getOrganCompetent().hashCode();
        }
        if (getPlazos() != null) {
            _hashCode += getPlazos().hashCode();
        }
        if (getProcedimiento() != null) {
            _hashCode += getProcedimiento().hashCode();
        }
        if (getRequisits() != null) {
            _hashCode += getRequisits().hashCode();
        }
        if (getUrlExterna() != null) {
            _hashCode += getUrlExterna().hashCode();
        }
        if (getValidacio() != null) {
            _hashCode += getValidacio().hashCode();
        }
        if (getVersio() != null) {
            _hashCode += getVersio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TramitDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tramit.v2.api.rolsac.caib.es", "TramitDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiVuds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiVuds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataActualitzacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataActualitzacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataActualitzacioVuds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataActualitzacioVuds"));
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
        elemField.setFieldName("dataInici");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataInici"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataTancament");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataTancament"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descCodiVuds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descCodiVuds"));
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
        elemField.setFieldName("documentacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTraTel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTraTel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("organCompetent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organCompetent"));
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
        elemField.setFieldName("procedimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "procedimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requisits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requisits"));
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
        elemField.setFieldName("validacio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validacio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "versio"));
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
