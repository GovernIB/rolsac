/**
 * PersonalCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.personal;

public class PersonalCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String nombre;

    private java.lang.String funciones;

    private java.lang.String cargo;

    private java.lang.String email;

    private java.lang.String extensionPublica;

    private java.lang.String numeroLargoPublico;

    private java.lang.String extensionPrivada;

    private java.lang.String numeroLargoPrivado;

    private java.lang.String extensionMovil;

    private java.lang.String numeroLargoMovil;

    private es.caib.rolsac.api.v2.personal.PersonalOrdenacio[] ordenar;

    public PersonalCriteria() {
    }

    public PersonalCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String username,
           java.lang.String nombre,
           java.lang.String funciones,
           java.lang.String cargo,
           java.lang.String email,
           java.lang.String extensionPublica,
           java.lang.String numeroLargoPublico,
           java.lang.String extensionPrivada,
           java.lang.String numeroLargoPrivado,
           java.lang.String extensionMovil,
           java.lang.String numeroLargoMovil,
           es.caib.rolsac.api.v2.personal.PersonalOrdenacio[] ordenar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.username = username;
        this.nombre = nombre;
        this.funciones = funciones;
        this.cargo = cargo;
        this.email = email;
        this.extensionPublica = extensionPublica;
        this.numeroLargoPublico = numeroLargoPublico;
        this.extensionPrivada = extensionPrivada;
        this.numeroLargoPrivado = numeroLargoPrivado;
        this.extensionMovil = extensionMovil;
        this.numeroLargoMovil = numeroLargoMovil;
        this.ordenar = ordenar;
    }


    /**
     * Gets the username value for this PersonalCriteria.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this PersonalCriteria.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the nombre value for this PersonalCriteria.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PersonalCriteria.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the funciones value for this PersonalCriteria.
     * 
     * @return funciones
     */
    public java.lang.String getFunciones() {
        return funciones;
    }


    /**
     * Sets the funciones value for this PersonalCriteria.
     * 
     * @param funciones
     */
    public void setFunciones(java.lang.String funciones) {
        this.funciones = funciones;
    }


    /**
     * Gets the cargo value for this PersonalCriteria.
     * 
     * @return cargo
     */
    public java.lang.String getCargo() {
        return cargo;
    }


    /**
     * Sets the cargo value for this PersonalCriteria.
     * 
     * @param cargo
     */
    public void setCargo(java.lang.String cargo) {
        this.cargo = cargo;
    }


    /**
     * Gets the email value for this PersonalCriteria.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this PersonalCriteria.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the extensionPublica value for this PersonalCriteria.
     * 
     * @return extensionPublica
     */
    public java.lang.String getExtensionPublica() {
        return extensionPublica;
    }


    /**
     * Sets the extensionPublica value for this PersonalCriteria.
     * 
     * @param extensionPublica
     */
    public void setExtensionPublica(java.lang.String extensionPublica) {
        this.extensionPublica = extensionPublica;
    }


    /**
     * Gets the numeroLargoPublico value for this PersonalCriteria.
     * 
     * @return numeroLargoPublico
     */
    public java.lang.String getNumeroLargoPublico() {
        return numeroLargoPublico;
    }


    /**
     * Sets the numeroLargoPublico value for this PersonalCriteria.
     * 
     * @param numeroLargoPublico
     */
    public void setNumeroLargoPublico(java.lang.String numeroLargoPublico) {
        this.numeroLargoPublico = numeroLargoPublico;
    }


    /**
     * Gets the extensionPrivada value for this PersonalCriteria.
     * 
     * @return extensionPrivada
     */
    public java.lang.String getExtensionPrivada() {
        return extensionPrivada;
    }


    /**
     * Sets the extensionPrivada value for this PersonalCriteria.
     * 
     * @param extensionPrivada
     */
    public void setExtensionPrivada(java.lang.String extensionPrivada) {
        this.extensionPrivada = extensionPrivada;
    }


    /**
     * Gets the numeroLargoPrivado value for this PersonalCriteria.
     * 
     * @return numeroLargoPrivado
     */
    public java.lang.String getNumeroLargoPrivado() {
        return numeroLargoPrivado;
    }


    /**
     * Sets the numeroLargoPrivado value for this PersonalCriteria.
     * 
     * @param numeroLargoPrivado
     */
    public void setNumeroLargoPrivado(java.lang.String numeroLargoPrivado) {
        this.numeroLargoPrivado = numeroLargoPrivado;
    }


    /**
     * Gets the extensionMovil value for this PersonalCriteria.
     * 
     * @return extensionMovil
     */
    public java.lang.String getExtensionMovil() {
        return extensionMovil;
    }


    /**
     * Sets the extensionMovil value for this PersonalCriteria.
     * 
     * @param extensionMovil
     */
    public void setExtensionMovil(java.lang.String extensionMovil) {
        this.extensionMovil = extensionMovil;
    }


    /**
     * Gets the numeroLargoMovil value for this PersonalCriteria.
     * 
     * @return numeroLargoMovil
     */
    public java.lang.String getNumeroLargoMovil() {
        return numeroLargoMovil;
    }


    /**
     * Sets the numeroLargoMovil value for this PersonalCriteria.
     * 
     * @param numeroLargoMovil
     */
    public void setNumeroLargoMovil(java.lang.String numeroLargoMovil) {
        this.numeroLargoMovil = numeroLargoMovil;
    }


    /**
     * Gets the ordenar value for this PersonalCriteria.
     * 
     * @return ordenar
     */
    public es.caib.rolsac.api.v2.personal.PersonalOrdenacio[] getOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this PersonalCriteria.
     * 
     * @param ordenar
     */
    public void setOrdenar(es.caib.rolsac.api.v2.personal.PersonalOrdenacio[] ordenar) {
        this.ordenar = ordenar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PersonalCriteria)) return false;
        PersonalCriteria other = (PersonalCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.funciones==null && other.getFunciones()==null) || 
             (this.funciones!=null &&
              this.funciones.equals(other.getFunciones()))) &&
            ((this.cargo==null && other.getCargo()==null) || 
             (this.cargo!=null &&
              this.cargo.equals(other.getCargo()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.extensionPublica==null && other.getExtensionPublica()==null) || 
             (this.extensionPublica!=null &&
              this.extensionPublica.equals(other.getExtensionPublica()))) &&
            ((this.numeroLargoPublico==null && other.getNumeroLargoPublico()==null) || 
             (this.numeroLargoPublico!=null &&
              this.numeroLargoPublico.equals(other.getNumeroLargoPublico()))) &&
            ((this.extensionPrivada==null && other.getExtensionPrivada()==null) || 
             (this.extensionPrivada!=null &&
              this.extensionPrivada.equals(other.getExtensionPrivada()))) &&
            ((this.numeroLargoPrivado==null && other.getNumeroLargoPrivado()==null) || 
             (this.numeroLargoPrivado!=null &&
              this.numeroLargoPrivado.equals(other.getNumeroLargoPrivado()))) &&
            ((this.extensionMovil==null && other.getExtensionMovil()==null) || 
             (this.extensionMovil!=null &&
              this.extensionMovil.equals(other.getExtensionMovil()))) &&
            ((this.numeroLargoMovil==null && other.getNumeroLargoMovil()==null) || 
             (this.numeroLargoMovil!=null &&
              this.numeroLargoMovil.equals(other.getNumeroLargoMovil()))) &&
            ((this.ordenar==null && other.getOrdenar()==null) || 
             (this.ordenar!=null &&
              java.util.Arrays.equals(this.ordenar, other.getOrdenar())));
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
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getFunciones() != null) {
            _hashCode += getFunciones().hashCode();
        }
        if (getCargo() != null) {
            _hashCode += getCargo().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getExtensionPublica() != null) {
            _hashCode += getExtensionPublica().hashCode();
        }
        if (getNumeroLargoPublico() != null) {
            _hashCode += getNumeroLargoPublico().hashCode();
        }
        if (getExtensionPrivada() != null) {
            _hashCode += getExtensionPrivada().hashCode();
        }
        if (getNumeroLargoPrivado() != null) {
            _hashCode += getNumeroLargoPrivado().hashCode();
        }
        if (getExtensionMovil() != null) {
            _hashCode += getExtensionMovil().hashCode();
        }
        if (getNumeroLargoMovil() != null) {
            _hashCode += getNumeroLargoMovil().hashCode();
        }
        if (getOrdenar() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrdenar());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrdenar(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonalCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
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
        elemField.setFieldName("funciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "funciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cargo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cargo"));
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
        elemField.setFieldName("extensionPublica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extensionPublica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLargoPublico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroLargoPublico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionPrivada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extensionPrivada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLargoPrivado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroLargoPrivado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionMovil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extensionMovil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLargoMovil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroLargoMovil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://personal.v2.api.rolsac.caib.es", "PersonalOrdenacio"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "listaOrdenaciones"));
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
