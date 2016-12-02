/**
 * TRAMITE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA;

public class TRAMITE  implements java.io.Serializable {
    private java.lang.String INTERNO;

    private java.lang.String ESCOMUN;

    private java.lang.String DENOMINACION;

    private java.lang.String DESCRIPCION;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS;

    private java.lang.String CODNIVELADMINISTRACIONELECTRONICA;

    private java.lang.String ENLACEWEB;

    private org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.MATERIASMATERIA[] MATERIAS;

    private java.lang.String SUJETOATASAS;

    /* 1 - continuo; 2 sujeto a plazos; 3 sujeto a convocatoria. */
    private java.lang.String PERIODICIDAD;

    public TRAMITE() {
    }

    public TRAMITE(
           java.lang.String INTERNO,
           java.lang.String ESCOMUN,
           java.lang.String DENOMINACION,
           java.lang.String DESCRIPCION,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS,
           java.lang.String CODNIVELADMINISTRACIONELECTRONICA,
           java.lang.String ENLACEWEB,
           org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.MATERIASMATERIA[] MATERIAS,
           java.lang.String SUJETOATASAS,
           java.lang.String PERIODICIDAD) {
           this.INTERNO = INTERNO;
           this.ESCOMUN = ESCOMUN;
           this.DENOMINACION = DENOMINACION;
           this.DESCRIPCION = DESCRIPCION;
           this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
           this.DESTINATARIOS = DESTINATARIOS;
           this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
           this.ENLACEWEB = ENLACEWEB;
           this.MATERIAS = MATERIAS;
           this.SUJETOATASAS = SUJETOATASAS;
           this.PERIODICIDAD = PERIODICIDAD;
    }


    /**
     * Gets the INTERNO value for this TRAMITE.
     * 
     * @return INTERNO
     */
    public java.lang.String getINTERNO() {
        return INTERNO;
    }


    /**
     * Sets the INTERNO value for this TRAMITE.
     * 
     * @param INTERNO
     */
    public void setINTERNO(java.lang.String INTERNO) {
        this.INTERNO = INTERNO;
    }


    /**
     * Gets the ESCOMUN value for this TRAMITE.
     * 
     * @return ESCOMUN
     */
    public java.lang.String getESCOMUN() {
        return ESCOMUN;
    }


    /**
     * Sets the ESCOMUN value for this TRAMITE.
     * 
     * @param ESCOMUN
     */
    public void setESCOMUN(java.lang.String ESCOMUN) {
        this.ESCOMUN = ESCOMUN;
    }


    /**
     * Gets the DENOMINACION value for this TRAMITE.
     * 
     * @return DENOMINACION
     */
    public java.lang.String getDENOMINACION() {
        return DENOMINACION;
    }


    /**
     * Sets the DENOMINACION value for this TRAMITE.
     * 
     * @param DENOMINACION
     */
    public void setDENOMINACION(java.lang.String DENOMINACION) {
        this.DENOMINACION = DENOMINACION;
    }


    /**
     * Gets the DESCRIPCION value for this TRAMITE.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this TRAMITE.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the ORGANISMORESPONSABLE value for this TRAMITE.
     * 
     * @return ORGANISMORESPONSABLE
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ORGANISMORESPONSABLE getORGANISMORESPONSABLE() {
        return ORGANISMORESPONSABLE;
    }


    /**
     * Sets the ORGANISMORESPONSABLE value for this TRAMITE.
     * 
     * @param ORGANISMORESPONSABLE
     */
    public void setORGANISMORESPONSABLE(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ORGANISMORESPONSABLE ORGANISMORESPONSABLE) {
        this.ORGANISMORESPONSABLE = ORGANISMORESPONSABLE;
    }


    /**
     * Gets the DESTINATARIOS value for this TRAMITE.
     * 
     * @return DESTINATARIOS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[] getDESTINATARIOS() {
        return DESTINATARIOS;
    }


    /**
     * Sets the DESTINATARIOS value for this TRAMITE.
     * 
     * @param DESTINATARIOS
     */
    public void setDESTINATARIOS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[] DESTINATARIOS) {
        this.DESTINATARIOS = DESTINATARIOS;
    }


    /**
     * Gets the CODNIVELADMINISTRACIONELECTRONICA value for this TRAMITE.
     * 
     * @return CODNIVELADMINISTRACIONELECTRONICA
     */
    public java.lang.String getCODNIVELADMINISTRACIONELECTRONICA() {
        return CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Sets the CODNIVELADMINISTRACIONELECTRONICA value for this TRAMITE.
     * 
     * @param CODNIVELADMINISTRACIONELECTRONICA
     */
    public void setCODNIVELADMINISTRACIONELECTRONICA(java.lang.String CODNIVELADMINISTRACIONELECTRONICA) {
        this.CODNIVELADMINISTRACIONELECTRONICA = CODNIVELADMINISTRACIONELECTRONICA;
    }


    /**
     * Gets the ENLACEWEB value for this TRAMITE.
     * 
     * @return ENLACEWEB
     */
    public java.lang.String getENLACEWEB() {
        return ENLACEWEB;
    }


    /**
     * Sets the ENLACEWEB value for this TRAMITE.
     * 
     * @param ENLACEWEB
     */
    public void setENLACEWEB(java.lang.String ENLACEWEB) {
        this.ENLACEWEB = ENLACEWEB;
    }


    /**
     * Gets the MATERIAS value for this TRAMITE.
     * 
     * @return MATERIAS
     */
    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.MATERIASMATERIA[] getMATERIAS() {
        return MATERIAS;
    }


    /**
     * Sets the MATERIAS value for this TRAMITE.
     * 
     * @param MATERIAS
     */
    public void setMATERIAS(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.MATERIASMATERIA[] MATERIAS) {
        this.MATERIAS = MATERIAS;
    }


    /**
     * Gets the SUJETOATASAS value for this TRAMITE.
     * 
     * @return SUJETOATASAS
     */
    public java.lang.String getSUJETOATASAS() {
        return SUJETOATASAS;
    }


    /**
     * Sets the SUJETOATASAS value for this TRAMITE.
     * 
     * @param SUJETOATASAS
     */
    public void setSUJETOATASAS(java.lang.String SUJETOATASAS) {
        this.SUJETOATASAS = SUJETOATASAS;
    }


    /**
     * Gets the PERIODICIDAD value for this TRAMITE.
     * 
     * @return PERIODICIDAD   * 1 - continuo; 2 sujeto a plazos; 3 sujeto a convocatoria.
     */
    public java.lang.String getPERIODICIDAD() {
        return PERIODICIDAD;
    }


    /**
     * Sets the PERIODICIDAD value for this TRAMITE.
     * 
     * @param PERIODICIDAD   * 1 - continuo; 2 sujeto a plazos; 3 sujeto a convocatoria.
     */
    public void setPERIODICIDAD(java.lang.String PERIODICIDAD) {
        this.PERIODICIDAD = PERIODICIDAD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TRAMITE)) return false;
        TRAMITE other = (TRAMITE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INTERNO==null && other.getINTERNO()==null) || 
             (this.INTERNO!=null &&
              this.INTERNO.equals(other.getINTERNO()))) &&
            ((this.ESCOMUN==null && other.getESCOMUN()==null) || 
             (this.ESCOMUN!=null &&
              this.ESCOMUN.equals(other.getESCOMUN()))) &&
            ((this.DENOMINACION==null && other.getDENOMINACION()==null) || 
             (this.DENOMINACION!=null &&
              this.DENOMINACION.equals(other.getDENOMINACION()))) &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.ORGANISMORESPONSABLE==null && other.getORGANISMORESPONSABLE()==null) || 
             (this.ORGANISMORESPONSABLE!=null &&
              this.ORGANISMORESPONSABLE.equals(other.getORGANISMORESPONSABLE()))) &&
            ((this.DESTINATARIOS==null && other.getDESTINATARIOS()==null) || 
             (this.DESTINATARIOS!=null &&
              java.util.Arrays.equals(this.DESTINATARIOS, other.getDESTINATARIOS()))) &&
            ((this.CODNIVELADMINISTRACIONELECTRONICA==null && other.getCODNIVELADMINISTRACIONELECTRONICA()==null) || 
             (this.CODNIVELADMINISTRACIONELECTRONICA!=null &&
              this.CODNIVELADMINISTRACIONELECTRONICA.equals(other.getCODNIVELADMINISTRACIONELECTRONICA()))) &&
            ((this.ENLACEWEB==null && other.getENLACEWEB()==null) || 
             (this.ENLACEWEB!=null &&
              this.ENLACEWEB.equals(other.getENLACEWEB()))) &&
            ((this.MATERIAS==null && other.getMATERIAS()==null) || 
             (this.MATERIAS!=null &&
              java.util.Arrays.equals(this.MATERIAS, other.getMATERIAS()))) &&
            ((this.SUJETOATASAS==null && other.getSUJETOATASAS()==null) || 
             (this.SUJETOATASAS!=null &&
              this.SUJETOATASAS.equals(other.getSUJETOATASAS()))) &&
            ((this.PERIODICIDAD==null && other.getPERIODICIDAD()==null) || 
             (this.PERIODICIDAD!=null &&
              this.PERIODICIDAD.equals(other.getPERIODICIDAD())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getINTERNO() != null) {
            _hashCode += getINTERNO().hashCode();
        }
        if (getESCOMUN() != null) {
            _hashCode += getESCOMUN().hashCode();
        }
        if (getDENOMINACION() != null) {
            _hashCode += getDENOMINACION().hashCode();
        }
        if (getDESCRIPCION() != null) {
            _hashCode += getDESCRIPCION().hashCode();
        }
        if (getORGANISMORESPONSABLE() != null) {
            _hashCode += getORGANISMORESPONSABLE().hashCode();
        }
        if (getDESTINATARIOS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDESTINATARIOS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDESTINATARIOS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODNIVELADMINISTRACIONELECTRONICA() != null) {
            _hashCode += getCODNIVELADMINISTRACIONELECTRONICA().hashCode();
        }
        if (getENLACEWEB() != null) {
            _hashCode += getENLACEWEB().hashCode();
        }
        if (getMATERIAS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMATERIAS());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMATERIAS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSUJETOATASAS() != null) {
            _hashCode += getSUJETOATASAS().hashCode();
        }
        if (getPERIODICIDAD() != null) {
            _hashCode += getPERIODICIDAD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TRAMITE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TRAMITE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "INTERNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESCOMUN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ESCOMUN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DENOMINACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DENOMINACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DESCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGANISMORESPONSABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ORGANISMORESPONSABLE"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESTINATARIOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DESTINATARIOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">DESTINATARIOS>DESTINATARIO"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DESTINATARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODNIVELADMINISTRACIONELECTRONICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "CODNIVELADMINISTRACIONELECTRONICA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENLACEWEB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ENLACEWEB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MATERIAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "MATERIAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">MATERIAS>MATERIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "MATERIA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUJETOATASAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "SUJETOATASAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIODICIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "PERIODICIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
