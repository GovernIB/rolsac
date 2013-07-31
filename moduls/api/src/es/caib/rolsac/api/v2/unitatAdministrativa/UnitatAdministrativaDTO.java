/**
 * UnitatAdministrativaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.unitatAdministrativa;

public class UnitatAdministrativaDTO  extends es.caib.rolsac.api.v2.general.EntitatRemota  implements java.io.Serializable {
    private java.lang.String abreviatura;

    private java.lang.String businessKey;

    private java.lang.String claveHita;

    private java.lang.String codigoEstandar;

    private java.lang.String dominio;

    private java.lang.String email;

    private java.lang.Long espacioTerrit;

    private java.lang.String fax;

    private java.lang.Long fotog;

    private java.lang.Long fotop;

    private java.lang.Long id;

    private java.lang.Long logoh;

    private java.lang.Long logos;

    private java.lang.Long logot;

    private java.lang.Long logov;

    private java.lang.String nombre;

    private java.lang.Integer numfoto1;

    private java.lang.Integer numfoto2;

    private java.lang.Integer numfoto3;

    private java.lang.Integer numfoto4;

    private long orden;

    private java.lang.Long padre;

    private java.lang.String presentacion;

    private java.lang.String responsable;

    private java.lang.Integer sexoResponsable;

    private java.lang.String telefono;

    private java.lang.Long tratamiento;

    private java.lang.String url;

    private java.lang.Integer validacion;

    public UnitatAdministrativaDTO() {
    }

    public UnitatAdministrativaDTO(
           java.lang.Long administracionRemota,
           java.lang.Long idExterno,
           java.lang.String urlRemota,
           java.lang.String abreviatura,
           java.lang.String businessKey,
           java.lang.String claveHita,
           java.lang.String codigoEstandar,
           java.lang.String dominio,
           java.lang.String email,
           java.lang.Long espacioTerrit,
           java.lang.String fax,
           java.lang.Long fotog,
           java.lang.Long fotop,
           java.lang.Long id,
           java.lang.Long logoh,
           java.lang.Long logos,
           java.lang.Long logot,
           java.lang.Long logov,
           java.lang.String nombre,
           java.lang.Integer numfoto1,
           java.lang.Integer numfoto2,
           java.lang.Integer numfoto3,
           java.lang.Integer numfoto4,
           long orden,
           java.lang.Long padre,
           java.lang.String presentacion,
           java.lang.String responsable,
           java.lang.Integer sexoResponsable,
           java.lang.String telefono,
           java.lang.Long tratamiento,
           java.lang.String url,
           java.lang.Integer validacion) {
        super(
            administracionRemota,
            idExterno,
            urlRemota);
        this.abreviatura = abreviatura;
        this.businessKey = businessKey;
        this.claveHita = claveHita;
        this.codigoEstandar = codigoEstandar;
        this.dominio = dominio;
        this.email = email;
        this.espacioTerrit = espacioTerrit;
        this.fax = fax;
        this.fotog = fotog;
        this.fotop = fotop;
        this.id = id;
        this.logoh = logoh;
        this.logos = logos;
        this.logot = logot;
        this.logov = logov;
        this.nombre = nombre;
        this.numfoto1 = numfoto1;
        this.numfoto2 = numfoto2;
        this.numfoto3 = numfoto3;
        this.numfoto4 = numfoto4;
        this.orden = orden;
        this.padre = padre;
        this.presentacion = presentacion;
        this.responsable = responsable;
        this.sexoResponsable = sexoResponsable;
        this.telefono = telefono;
        this.tratamiento = tratamiento;
        this.url = url;
        this.validacion = validacion;
    }


    /**
     * Gets the abreviatura value for this UnitatAdministrativaDTO.
     * 
     * @return abreviatura
     */
    public java.lang.String getAbreviatura() {
        return abreviatura;
    }


    /**
     * Sets the abreviatura value for this UnitatAdministrativaDTO.
     * 
     * @param abreviatura
     */
    public void setAbreviatura(java.lang.String abreviatura) {
        this.abreviatura = abreviatura;
    }


    /**
     * Gets the businessKey value for this UnitatAdministrativaDTO.
     * 
     * @return businessKey
     */
    public java.lang.String getBusinessKey() {
        return businessKey;
    }


    /**
     * Sets the businessKey value for this UnitatAdministrativaDTO.
     * 
     * @param businessKey
     */
    public void setBusinessKey(java.lang.String businessKey) {
        this.businessKey = businessKey;
    }


    /**
     * Gets the claveHita value for this UnitatAdministrativaDTO.
     * 
     * @return claveHita
     */
    public java.lang.String getClaveHita() {
        return claveHita;
    }


    /**
     * Sets the claveHita value for this UnitatAdministrativaDTO.
     * 
     * @param claveHita
     */
    public void setClaveHita(java.lang.String claveHita) {
        this.claveHita = claveHita;
    }


    /**
     * Gets the codigoEstandar value for this UnitatAdministrativaDTO.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this UnitatAdministrativaDTO.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the dominio value for this UnitatAdministrativaDTO.
     * 
     * @return dominio
     */
    public java.lang.String getDominio() {
        return dominio;
    }


    /**
     * Sets the dominio value for this UnitatAdministrativaDTO.
     * 
     * @param dominio
     */
    public void setDominio(java.lang.String dominio) {
        this.dominio = dominio;
    }


    /**
     * Gets the email value for this UnitatAdministrativaDTO.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this UnitatAdministrativaDTO.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the espacioTerrit value for this UnitatAdministrativaDTO.
     * 
     * @return espacioTerrit
     */
    public java.lang.Long getEspacioTerrit() {
        return espacioTerrit;
    }


    /**
     * Sets the espacioTerrit value for this UnitatAdministrativaDTO.
     * 
     * @param espacioTerrit
     */
    public void setEspacioTerrit(java.lang.Long espacioTerrit) {
        this.espacioTerrit = espacioTerrit;
    }


    /**
     * Gets the fax value for this UnitatAdministrativaDTO.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this UnitatAdministrativaDTO.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the fotog value for this UnitatAdministrativaDTO.
     * 
     * @return fotog
     */
    public java.lang.Long getFotog() {
        return fotog;
    }


    /**
     * Sets the fotog value for this UnitatAdministrativaDTO.
     * 
     * @param fotog
     */
    public void setFotog(java.lang.Long fotog) {
        this.fotog = fotog;
    }


    /**
     * Gets the fotop value for this UnitatAdministrativaDTO.
     * 
     * @return fotop
     */
    public java.lang.Long getFotop() {
        return fotop;
    }


    /**
     * Sets the fotop value for this UnitatAdministrativaDTO.
     * 
     * @param fotop
     */
    public void setFotop(java.lang.Long fotop) {
        this.fotop = fotop;
    }


    /**
     * Gets the id value for this UnitatAdministrativaDTO.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this UnitatAdministrativaDTO.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the logoh value for this UnitatAdministrativaDTO.
     * 
     * @return logoh
     */
    public java.lang.Long getLogoh() {
        return logoh;
    }


    /**
     * Sets the logoh value for this UnitatAdministrativaDTO.
     * 
     * @param logoh
     */
    public void setLogoh(java.lang.Long logoh) {
        this.logoh = logoh;
    }


    /**
     * Gets the logos value for this UnitatAdministrativaDTO.
     * 
     * @return logos
     */
    public java.lang.Long getLogos() {
        return logos;
    }


    /**
     * Sets the logos value for this UnitatAdministrativaDTO.
     * 
     * @param logos
     */
    public void setLogos(java.lang.Long logos) {
        this.logos = logos;
    }


    /**
     * Gets the logot value for this UnitatAdministrativaDTO.
     * 
     * @return logot
     */
    public java.lang.Long getLogot() {
        return logot;
    }


    /**
     * Sets the logot value for this UnitatAdministrativaDTO.
     * 
     * @param logot
     */
    public void setLogot(java.lang.Long logot) {
        this.logot = logot;
    }


    /**
     * Gets the logov value for this UnitatAdministrativaDTO.
     * 
     * @return logov
     */
    public java.lang.Long getLogov() {
        return logov;
    }


    /**
     * Sets the logov value for this UnitatAdministrativaDTO.
     * 
     * @param logov
     */
    public void setLogov(java.lang.Long logov) {
        this.logov = logov;
    }


    /**
     * Gets the nombre value for this UnitatAdministrativaDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this UnitatAdministrativaDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the numfoto1 value for this UnitatAdministrativaDTO.
     * 
     * @return numfoto1
     */
    public java.lang.Integer getNumfoto1() {
        return numfoto1;
    }


    /**
     * Sets the numfoto1 value for this UnitatAdministrativaDTO.
     * 
     * @param numfoto1
     */
    public void setNumfoto1(java.lang.Integer numfoto1) {
        this.numfoto1 = numfoto1;
    }


    /**
     * Gets the numfoto2 value for this UnitatAdministrativaDTO.
     * 
     * @return numfoto2
     */
    public java.lang.Integer getNumfoto2() {
        return numfoto2;
    }


    /**
     * Sets the numfoto2 value for this UnitatAdministrativaDTO.
     * 
     * @param numfoto2
     */
    public void setNumfoto2(java.lang.Integer numfoto2) {
        this.numfoto2 = numfoto2;
    }


    /**
     * Gets the numfoto3 value for this UnitatAdministrativaDTO.
     * 
     * @return numfoto3
     */
    public java.lang.Integer getNumfoto3() {
        return numfoto3;
    }


    /**
     * Sets the numfoto3 value for this UnitatAdministrativaDTO.
     * 
     * @param numfoto3
     */
    public void setNumfoto3(java.lang.Integer numfoto3) {
        this.numfoto3 = numfoto3;
    }


    /**
     * Gets the numfoto4 value for this UnitatAdministrativaDTO.
     * 
     * @return numfoto4
     */
    public java.lang.Integer getNumfoto4() {
        return numfoto4;
    }


    /**
     * Sets the numfoto4 value for this UnitatAdministrativaDTO.
     * 
     * @param numfoto4
     */
    public void setNumfoto4(java.lang.Integer numfoto4) {
        this.numfoto4 = numfoto4;
    }


    /**
     * Gets the orden value for this UnitatAdministrativaDTO.
     * 
     * @return orden
     */
    public long getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this UnitatAdministrativaDTO.
     * 
     * @param orden
     */
    public void setOrden(long orden) {
        this.orden = orden;
    }


    /**
     * Gets the padre value for this UnitatAdministrativaDTO.
     * 
     * @return padre
     */
    public java.lang.Long getPadre() {
        return padre;
    }


    /**
     * Sets the padre value for this UnitatAdministrativaDTO.
     * 
     * @param padre
     */
    public void setPadre(java.lang.Long padre) {
        this.padre = padre;
    }


    /**
     * Gets the presentacion value for this UnitatAdministrativaDTO.
     * 
     * @return presentacion
     */
    public java.lang.String getPresentacion() {
        return presentacion;
    }


    /**
     * Sets the presentacion value for this UnitatAdministrativaDTO.
     * 
     * @param presentacion
     */
    public void setPresentacion(java.lang.String presentacion) {
        this.presentacion = presentacion;
    }


    /**
     * Gets the responsable value for this UnitatAdministrativaDTO.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this UnitatAdministrativaDTO.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the sexoResponsable value for this UnitatAdministrativaDTO.
     * 
     * @return sexoResponsable
     */
    public java.lang.Integer getSexoResponsable() {
        return sexoResponsable;
    }


    /**
     * Sets the sexoResponsable value for this UnitatAdministrativaDTO.
     * 
     * @param sexoResponsable
     */
    public void setSexoResponsable(java.lang.Integer sexoResponsable) {
        this.sexoResponsable = sexoResponsable;
    }


    /**
     * Gets the telefono value for this UnitatAdministrativaDTO.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this UnitatAdministrativaDTO.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the tratamiento value for this UnitatAdministrativaDTO.
     * 
     * @return tratamiento
     */
    public java.lang.Long getTratamiento() {
        return tratamiento;
    }


    /**
     * Sets the tratamiento value for this UnitatAdministrativaDTO.
     * 
     * @param tratamiento
     */
    public void setTratamiento(java.lang.Long tratamiento) {
        this.tratamiento = tratamiento;
    }


    /**
     * Gets the url value for this UnitatAdministrativaDTO.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this UnitatAdministrativaDTO.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the validacion value for this UnitatAdministrativaDTO.
     * 
     * @return validacion
     */
    public java.lang.Integer getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this UnitatAdministrativaDTO.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.Integer validacion) {
        this.validacion = validacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnitatAdministrativaDTO)) return false;
        UnitatAdministrativaDTO other = (UnitatAdministrativaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.abreviatura==null && other.getAbreviatura()==null) || 
             (this.abreviatura!=null &&
              this.abreviatura.equals(other.getAbreviatura()))) &&
            ((this.businessKey==null && other.getBusinessKey()==null) || 
             (this.businessKey!=null &&
              this.businessKey.equals(other.getBusinessKey()))) &&
            ((this.claveHita==null && other.getClaveHita()==null) || 
             (this.claveHita!=null &&
              this.claveHita.equals(other.getClaveHita()))) &&
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.dominio==null && other.getDominio()==null) || 
             (this.dominio!=null &&
              this.dominio.equals(other.getDominio()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.espacioTerrit==null && other.getEspacioTerrit()==null) || 
             (this.espacioTerrit!=null &&
              this.espacioTerrit.equals(other.getEspacioTerrit()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.fotog==null && other.getFotog()==null) || 
             (this.fotog!=null &&
              this.fotog.equals(other.getFotog()))) &&
            ((this.fotop==null && other.getFotop()==null) || 
             (this.fotop!=null &&
              this.fotop.equals(other.getFotop()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.logoh==null && other.getLogoh()==null) || 
             (this.logoh!=null &&
              this.logoh.equals(other.getLogoh()))) &&
            ((this.logos==null && other.getLogos()==null) || 
             (this.logos!=null &&
              this.logos.equals(other.getLogos()))) &&
            ((this.logot==null && other.getLogot()==null) || 
             (this.logot!=null &&
              this.logot.equals(other.getLogot()))) &&
            ((this.logov==null && other.getLogov()==null) || 
             (this.logov!=null &&
              this.logov.equals(other.getLogov()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.numfoto1==null && other.getNumfoto1()==null) || 
             (this.numfoto1!=null &&
              this.numfoto1.equals(other.getNumfoto1()))) &&
            ((this.numfoto2==null && other.getNumfoto2()==null) || 
             (this.numfoto2!=null &&
              this.numfoto2.equals(other.getNumfoto2()))) &&
            ((this.numfoto3==null && other.getNumfoto3()==null) || 
             (this.numfoto3!=null &&
              this.numfoto3.equals(other.getNumfoto3()))) &&
            ((this.numfoto4==null && other.getNumfoto4()==null) || 
             (this.numfoto4!=null &&
              this.numfoto4.equals(other.getNumfoto4()))) &&
            this.orden == other.getOrden() &&
            ((this.padre==null && other.getPadre()==null) || 
             (this.padre!=null &&
              this.padre.equals(other.getPadre()))) &&
            ((this.presentacion==null && other.getPresentacion()==null) || 
             (this.presentacion!=null &&
              this.presentacion.equals(other.getPresentacion()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.sexoResponsable==null && other.getSexoResponsable()==null) || 
             (this.sexoResponsable!=null &&
              this.sexoResponsable.equals(other.getSexoResponsable()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.tratamiento==null && other.getTratamiento()==null) || 
             (this.tratamiento!=null &&
              this.tratamiento.equals(other.getTratamiento()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
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
        if (getAbreviatura() != null) {
            _hashCode += getAbreviatura().hashCode();
        }
        if (getBusinessKey() != null) {
            _hashCode += getBusinessKey().hashCode();
        }
        if (getClaveHita() != null) {
            _hashCode += getClaveHita().hashCode();
        }
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getDominio() != null) {
            _hashCode += getDominio().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getEspacioTerrit() != null) {
            _hashCode += getEspacioTerrit().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getFotog() != null) {
            _hashCode += getFotog().hashCode();
        }
        if (getFotop() != null) {
            _hashCode += getFotop().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLogoh() != null) {
            _hashCode += getLogoh().hashCode();
        }
        if (getLogos() != null) {
            _hashCode += getLogos().hashCode();
        }
        if (getLogot() != null) {
            _hashCode += getLogot().hashCode();
        }
        if (getLogov() != null) {
            _hashCode += getLogov().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getNumfoto1() != null) {
            _hashCode += getNumfoto1().hashCode();
        }
        if (getNumfoto2() != null) {
            _hashCode += getNumfoto2().hashCode();
        }
        if (getNumfoto3() != null) {
            _hashCode += getNumfoto3().hashCode();
        }
        if (getNumfoto4() != null) {
            _hashCode += getNumfoto4().hashCode();
        }
        _hashCode += new Long(getOrden()).hashCode();
        if (getPadre() != null) {
            _hashCode += getPadre().hashCode();
        }
        if (getPresentacion() != null) {
            _hashCode += getPresentacion().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getSexoResponsable() != null) {
            _hashCode += getSexoResponsable().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getTratamiento() != null) {
            _hashCode += getTratamiento().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnitatAdministrativaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abreviatura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abreviatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "businessKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claveHita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claveHita"));
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
        elemField.setFieldName("dominio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dominio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("espacioTerrit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "espacioTerrit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fotog");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fotog"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fotop");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fotop"));
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
        elemField.setFieldName("logoh");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logoh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logot");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logot"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logov");
        elemField.setXmlName(new javax.xml.namespace.QName("", "logov"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numfoto1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numfoto1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numfoto2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numfoto2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numfoto3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numfoto3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numfoto4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numfoto4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("padre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "padre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("presentacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "presentacion"));
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
        elemField.setFieldName("sexoResponsable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sexoResponsable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tratamiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tratamiento"));
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
