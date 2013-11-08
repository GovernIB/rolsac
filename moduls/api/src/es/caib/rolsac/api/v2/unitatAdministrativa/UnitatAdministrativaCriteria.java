/**
 * UnitatAdministrativaCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.unitatAdministrativa;

public class UnitatAdministrativaCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String businessKey;

    private java.lang.String claveHita;

    private java.lang.String dominio;

    private java.lang.String orden;

    private java.lang.String validacion;

    private java.lang.String responsable;

    private java.lang.String telefono;

    private java.lang.String fax;

    private java.lang.String email;

    private java.lang.String sexoResponsable;

    private java.lang.String codigoEstandar;

    private java.lang.String t_nombre;

    private java.lang.String t_presentacion;

    private java.lang.String t_abreviatura;

    private java.lang.String seccion;

    public UnitatAdministrativaCriteria() {
    }

    public UnitatAdministrativaCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String businessKey,
           java.lang.String claveHita,
           java.lang.String dominio,
           java.lang.String orden,
           java.lang.String validacion,
           java.lang.String responsable,
           java.lang.String telefono,
           java.lang.String fax,
           java.lang.String email,
           java.lang.String sexoResponsable,
           java.lang.String codigoEstandar,
           java.lang.String t_nombre,
           java.lang.String t_presentacion,
           java.lang.String t_abreviatura,
           java.lang.String seccion) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.businessKey = businessKey;
        this.claveHita = claveHita;
        this.dominio = dominio;
        this.orden = orden;
        this.validacion = validacion;
        this.responsable = responsable;
        this.telefono = telefono;
        this.fax = fax;
        this.email = email;
        this.sexoResponsable = sexoResponsable;
        this.codigoEstandar = codigoEstandar;
        this.t_nombre = t_nombre;
        this.t_presentacion = t_presentacion;
        this.t_abreviatura = t_abreviatura;
        this.seccion = seccion;
    }


    /**
     * Gets the businessKey value for this UnitatAdministrativaCriteria.
     * 
     * @return businessKey
     */
    public java.lang.String getBusinessKey() {
        return businessKey;
    }


    /**
     * Sets the businessKey value for this UnitatAdministrativaCriteria.
     * 
     * @param businessKey
     */
    public void setBusinessKey(java.lang.String businessKey) {
        this.businessKey = businessKey;
    }


    /**
     * Gets the claveHita value for this UnitatAdministrativaCriteria.
     * 
     * @return claveHita
     */
    public java.lang.String getClaveHita() {
        return claveHita;
    }


    /**
     * Sets the claveHita value for this UnitatAdministrativaCriteria.
     * 
     * @param claveHita
     */
    public void setClaveHita(java.lang.String claveHita) {
        this.claveHita = claveHita;
    }


    /**
     * Gets the dominio value for this UnitatAdministrativaCriteria.
     * 
     * @return dominio
     */
    public java.lang.String getDominio() {
        return dominio;
    }


    /**
     * Sets the dominio value for this UnitatAdministrativaCriteria.
     * 
     * @param dominio
     */
    public void setDominio(java.lang.String dominio) {
        this.dominio = dominio;
    }


    /**
     * Gets the orden value for this UnitatAdministrativaCriteria.
     * 
     * @return orden
     */
    public java.lang.String getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this UnitatAdministrativaCriteria.
     * 
     * @param orden
     */
    public void setOrden(java.lang.String orden) {
        this.orden = orden;
    }


    /**
     * Gets the validacion value for this UnitatAdministrativaCriteria.
     * 
     * @return validacion
     */
    public java.lang.String getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this UnitatAdministrativaCriteria.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.String validacion) {
        this.validacion = validacion;
    }


    /**
     * Gets the responsable value for this UnitatAdministrativaCriteria.
     * 
     * @return responsable
     */
    public java.lang.String getResponsable() {
        return responsable;
    }


    /**
     * Sets the responsable value for this UnitatAdministrativaCriteria.
     * 
     * @param responsable
     */
    public void setResponsable(java.lang.String responsable) {
        this.responsable = responsable;
    }


    /**
     * Gets the telefono value for this UnitatAdministrativaCriteria.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this UnitatAdministrativaCriteria.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the fax value for this UnitatAdministrativaCriteria.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this UnitatAdministrativaCriteria.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the email value for this UnitatAdministrativaCriteria.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this UnitatAdministrativaCriteria.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the sexoResponsable value for this UnitatAdministrativaCriteria.
     * 
     * @return sexoResponsable
     */
    public java.lang.String getSexoResponsable() {
        return sexoResponsable;
    }


    /**
     * Sets the sexoResponsable value for this UnitatAdministrativaCriteria.
     * 
     * @param sexoResponsable
     */
    public void setSexoResponsable(java.lang.String sexoResponsable) {
        this.sexoResponsable = sexoResponsable;
    }


    /**
     * Gets the codigoEstandar value for this UnitatAdministrativaCriteria.
     * 
     * @return codigoEstandar
     */
    public java.lang.String getCodigoEstandar() {
        return codigoEstandar;
    }


    /**
     * Sets the codigoEstandar value for this UnitatAdministrativaCriteria.
     * 
     * @param codigoEstandar
     */
    public void setCodigoEstandar(java.lang.String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    /**
     * Gets the t_nombre value for this UnitatAdministrativaCriteria.
     * 
     * @return t_nombre
     */
    public java.lang.String getT_nombre() {
        return t_nombre;
    }


    /**
     * Sets the t_nombre value for this UnitatAdministrativaCriteria.
     * 
     * @param t_nombre
     */
    public void setT_nombre(java.lang.String t_nombre) {
        this.t_nombre = t_nombre;
    }


    /**
     * Gets the t_presentacion value for this UnitatAdministrativaCriteria.
     * 
     * @return t_presentacion
     */
    public java.lang.String getT_presentacion() {
        return t_presentacion;
    }


    /**
     * Sets the t_presentacion value for this UnitatAdministrativaCriteria.
     * 
     * @param t_presentacion
     */
    public void setT_presentacion(java.lang.String t_presentacion) {
        this.t_presentacion = t_presentacion;
    }


    /**
     * Gets the t_abreviatura value for this UnitatAdministrativaCriteria.
     * 
     * @return t_abreviatura
     */
    public java.lang.String getT_abreviatura() {
        return t_abreviatura;
    }


    /**
     * Sets the t_abreviatura value for this UnitatAdministrativaCriteria.
     * 
     * @param t_abreviatura
     */
    public void setT_abreviatura(java.lang.String t_abreviatura) {
        this.t_abreviatura = t_abreviatura;
    }


    /**
     * Gets the seccion value for this UnitatAdministrativaCriteria.
     * 
     * @return seccion
     */
    public java.lang.String getSeccion() {
        return seccion;
    }


    /**
     * Sets the seccion value for this UnitatAdministrativaCriteria.
     * 
     * @param seccion
     */
    public void setSeccion(java.lang.String seccion) {
        this.seccion = seccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnitatAdministrativaCriteria)) return false;
        UnitatAdministrativaCriteria other = (UnitatAdministrativaCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.businessKey==null && other.getBusinessKey()==null) || 
             (this.businessKey!=null &&
              this.businessKey.equals(other.getBusinessKey()))) &&
            ((this.claveHita==null && other.getClaveHita()==null) || 
             (this.claveHita!=null &&
              this.claveHita.equals(other.getClaveHita()))) &&
            ((this.dominio==null && other.getDominio()==null) || 
             (this.dominio!=null &&
              this.dominio.equals(other.getDominio()))) &&
            ((this.orden==null && other.getOrden()==null) || 
             (this.orden!=null &&
              this.orden.equals(other.getOrden()))) &&
            ((this.validacion==null && other.getValidacion()==null) || 
             (this.validacion!=null &&
              this.validacion.equals(other.getValidacion()))) &&
            ((this.responsable==null && other.getResponsable()==null) || 
             (this.responsable!=null &&
              this.responsable.equals(other.getResponsable()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.sexoResponsable==null && other.getSexoResponsable()==null) || 
             (this.sexoResponsable!=null &&
              this.sexoResponsable.equals(other.getSexoResponsable()))) &&
            ((this.codigoEstandar==null && other.getCodigoEstandar()==null) || 
             (this.codigoEstandar!=null &&
              this.codigoEstandar.equals(other.getCodigoEstandar()))) &&
            ((this.t_nombre==null && other.getT_nombre()==null) || 
             (this.t_nombre!=null &&
              this.t_nombre.equals(other.getT_nombre()))) &&
            ((this.t_presentacion==null && other.getT_presentacion()==null) || 
             (this.t_presentacion!=null &&
              this.t_presentacion.equals(other.getT_presentacion()))) &&
            ((this.t_abreviatura==null && other.getT_abreviatura()==null) || 
             (this.t_abreviatura!=null &&
              this.t_abreviatura.equals(other.getT_abreviatura()))) &&
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
        if (getBusinessKey() != null) {
            _hashCode += getBusinessKey().hashCode();
        }
        if (getClaveHita() != null) {
            _hashCode += getClaveHita().hashCode();
        }
        if (getDominio() != null) {
            _hashCode += getDominio().hashCode();
        }
        if (getOrden() != null) {
            _hashCode += getOrden().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        if (getResponsable() != null) {
            _hashCode += getResponsable().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getSexoResponsable() != null) {
            _hashCode += getSexoResponsable().hashCode();
        }
        if (getCodigoEstandar() != null) {
            _hashCode += getCodigoEstandar().hashCode();
        }
        if (getT_nombre() != null) {
            _hashCode += getT_nombre().hashCode();
        }
        if (getT_presentacion() != null) {
            _hashCode += getT_presentacion().hashCode();
        }
        if (getT_abreviatura() != null) {
            _hashCode += getT_abreviatura().hashCode();
        }
        if (getSeccion() != null) {
            _hashCode += getSeccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnitatAdministrativaCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://unitatAdministrativa.v2.api.rolsac.caib.es", "UnitatAdministrativaCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("dominio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dominio"));
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
        elemField.setFieldName("validacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validacion"));
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
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fax"));
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
        elemField.setFieldName("sexoResponsable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sexoResponsable"));
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
        elemField.setFieldName("t_nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_presentacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_presentacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_abreviatura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_abreviatura"));
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
