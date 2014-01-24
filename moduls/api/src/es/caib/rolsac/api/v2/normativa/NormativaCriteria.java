/**
 * NormativaCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.normativa;

public class NormativaCriteria  extends es.caib.rolsac.api.v2.general.BasicCriteria  implements java.io.Serializable {
    private java.lang.String numero;

    private java.lang.String registro;

    private java.lang.String ley;

    private java.lang.String fecha;

    private java.lang.String fechaBoletin;

    private java.lang.String validacion;

    private java.lang.String codiVuds;

    private java.lang.String descCodiVuds;

    private java.lang.String t_seccion;

    private java.lang.String t_apartado;

    private java.lang.String t_paginaInicial;

    private java.lang.String t_paginaFinal;

    private java.lang.String t_titulo;

    private java.lang.String t_enlace;

    private java.lang.String t_observaciones;

    private java.lang.String t_responsable;

    private java.lang.Boolean incluirExternas;

    private es.caib.rolsac.api.v2.normativa.NormativaOrdenacio[] ordenar;

    public NormativaCriteria() {
    }

    public NormativaCriteria(
           java.lang.String id,
           java.lang.String idioma,
           java.lang.String inici,
           java.lang.String ordenacio,
           java.lang.String tamany,
           java.lang.String numero,
           java.lang.String registro,
           java.lang.String ley,
           java.lang.String fecha,
           java.lang.String fechaBoletin,
           java.lang.String validacion,
           java.lang.String codiVuds,
           java.lang.String descCodiVuds,
           java.lang.String t_seccion,
           java.lang.String t_apartado,
           java.lang.String t_paginaInicial,
           java.lang.String t_paginaFinal,
           java.lang.String t_titulo,
           java.lang.String t_enlace,
           java.lang.String t_observaciones,
           java.lang.String t_responsable,
           java.lang.Boolean incluirExternas,
           es.caib.rolsac.api.v2.normativa.NormativaOrdenacio[] ordenar) {
        super(
            id,
            idioma,
            inici,
            ordenacio,
            tamany);
        this.numero = numero;
        this.registro = registro;
        this.ley = ley;
        this.fecha = fecha;
        this.fechaBoletin = fechaBoletin;
        this.validacion = validacion;
        this.codiVuds = codiVuds;
        this.descCodiVuds = descCodiVuds;
        this.t_seccion = t_seccion;
        this.t_apartado = t_apartado;
        this.t_paginaInicial = t_paginaInicial;
        this.t_paginaFinal = t_paginaFinal;
        this.t_titulo = t_titulo;
        this.t_enlace = t_enlace;
        this.t_observaciones = t_observaciones;
        this.t_responsable = t_responsable;
        this.incluirExternas = incluirExternas;
        this.ordenar = ordenar;
    }


    /**
     * Gets the numero value for this NormativaCriteria.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this NormativaCriteria.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the registro value for this NormativaCriteria.
     * 
     * @return registro
     */
    public java.lang.String getRegistro() {
        return registro;
    }


    /**
     * Sets the registro value for this NormativaCriteria.
     * 
     * @param registro
     */
    public void setRegistro(java.lang.String registro) {
        this.registro = registro;
    }


    /**
     * Gets the ley value for this NormativaCriteria.
     * 
     * @return ley
     */
    public java.lang.String getLey() {
        return ley;
    }


    /**
     * Sets the ley value for this NormativaCriteria.
     * 
     * @param ley
     */
    public void setLey(java.lang.String ley) {
        this.ley = ley;
    }


    /**
     * Gets the fecha value for this NormativaCriteria.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this NormativaCriteria.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the fechaBoletin value for this NormativaCriteria.
     * 
     * @return fechaBoletin
     */
    public java.lang.String getFechaBoletin() {
        return fechaBoletin;
    }


    /**
     * Sets the fechaBoletin value for this NormativaCriteria.
     * 
     * @param fechaBoletin
     */
    public void setFechaBoletin(java.lang.String fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }


    /**
     * Gets the validacion value for this NormativaCriteria.
     * 
     * @return validacion
     */
    public java.lang.String getValidacion() {
        return validacion;
    }


    /**
     * Sets the validacion value for this NormativaCriteria.
     * 
     * @param validacion
     */
    public void setValidacion(java.lang.String validacion) {
        this.validacion = validacion;
    }


    /**
     * Gets the codiVuds value for this NormativaCriteria.
     * 
     * @return codiVuds
     */
    public java.lang.String getCodiVuds() {
        return codiVuds;
    }


    /**
     * Sets the codiVuds value for this NormativaCriteria.
     * 
     * @param codiVuds
     */
    public void setCodiVuds(java.lang.String codiVuds) {
        this.codiVuds = codiVuds;
    }


    /**
     * Gets the descCodiVuds value for this NormativaCriteria.
     * 
     * @return descCodiVuds
     */
    public java.lang.String getDescCodiVuds() {
        return descCodiVuds;
    }


    /**
     * Sets the descCodiVuds value for this NormativaCriteria.
     * 
     * @param descCodiVuds
     */
    public void setDescCodiVuds(java.lang.String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }


    /**
     * Gets the t_seccion value for this NormativaCriteria.
     * 
     * @return t_seccion
     */
    public java.lang.String getT_seccion() {
        return t_seccion;
    }


    /**
     * Sets the t_seccion value for this NormativaCriteria.
     * 
     * @param t_seccion
     */
    public void setT_seccion(java.lang.String t_seccion) {
        this.t_seccion = t_seccion;
    }


    /**
     * Gets the t_apartado value for this NormativaCriteria.
     * 
     * @return t_apartado
     */
    public java.lang.String getT_apartado() {
        return t_apartado;
    }


    /**
     * Sets the t_apartado value for this NormativaCriteria.
     * 
     * @param t_apartado
     */
    public void setT_apartado(java.lang.String t_apartado) {
        this.t_apartado = t_apartado;
    }


    /**
     * Gets the t_paginaInicial value for this NormativaCriteria.
     * 
     * @return t_paginaInicial
     */
    public java.lang.String getT_paginaInicial() {
        return t_paginaInicial;
    }


    /**
     * Sets the t_paginaInicial value for this NormativaCriteria.
     * 
     * @param t_paginaInicial
     */
    public void setT_paginaInicial(java.lang.String t_paginaInicial) {
        this.t_paginaInicial = t_paginaInicial;
    }


    /**
     * Gets the t_paginaFinal value for this NormativaCriteria.
     * 
     * @return t_paginaFinal
     */
    public java.lang.String getT_paginaFinal() {
        return t_paginaFinal;
    }


    /**
     * Sets the t_paginaFinal value for this NormativaCriteria.
     * 
     * @param t_paginaFinal
     */
    public void setT_paginaFinal(java.lang.String t_paginaFinal) {
        this.t_paginaFinal = t_paginaFinal;
    }


    /**
     * Gets the t_titulo value for this NormativaCriteria.
     * 
     * @return t_titulo
     */
    public java.lang.String getT_titulo() {
        return t_titulo;
    }


    /**
     * Sets the t_titulo value for this NormativaCriteria.
     * 
     * @param t_titulo
     */
    public void setT_titulo(java.lang.String t_titulo) {
        this.t_titulo = t_titulo;
    }


    /**
     * Gets the t_enlace value for this NormativaCriteria.
     * 
     * @return t_enlace
     */
    public java.lang.String getT_enlace() {
        return t_enlace;
    }


    /**
     * Sets the t_enlace value for this NormativaCriteria.
     * 
     * @param t_enlace
     */
    public void setT_enlace(java.lang.String t_enlace) {
        this.t_enlace = t_enlace;
    }


    /**
     * Gets the t_observaciones value for this NormativaCriteria.
     * 
     * @return t_observaciones
     */
    public java.lang.String getT_observaciones() {
        return t_observaciones;
    }


    /**
     * Sets the t_observaciones value for this NormativaCriteria.
     * 
     * @param t_observaciones
     */
    public void setT_observaciones(java.lang.String t_observaciones) {
        this.t_observaciones = t_observaciones;
    }


    /**
     * Gets the t_responsable value for this NormativaCriteria.
     * 
     * @return t_responsable
     */
    public java.lang.String getT_responsable() {
        return t_responsable;
    }


    /**
     * Sets the t_responsable value for this NormativaCriteria.
     * 
     * @param t_responsable
     */
    public void setT_responsable(java.lang.String t_responsable) {
        this.t_responsable = t_responsable;
    }


    /**
     * Gets the incluirExternas value for this NormativaCriteria.
     * 
     * @return incluirExternas
     */
    public java.lang.Boolean getIncluirExternas() {
        return incluirExternas;
    }


    /**
     * Sets the incluirExternas value for this NormativaCriteria.
     * 
     * @param incluirExternas
     */
    public void setIncluirExternas(java.lang.Boolean incluirExternas) {
        this.incluirExternas = incluirExternas;
    }


    /**
     * Gets the ordenar value for this NormativaCriteria.
     * 
     * @return ordenar
     */
    public es.caib.rolsac.api.v2.normativa.NormativaOrdenacio[] getOrdenar() {
        return ordenar;
    }


    /**
     * Sets the ordenar value for this NormativaCriteria.
     * 
     * @param ordenar
     */
    public void setOrdenar(es.caib.rolsac.api.v2.normativa.NormativaOrdenacio[] ordenar) {
        this.ordenar = ordenar;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NormativaCriteria)) return false;
        NormativaCriteria other = (NormativaCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.registro==null && other.getRegistro()==null) || 
             (this.registro!=null &&
              this.registro.equals(other.getRegistro()))) &&
            ((this.ley==null && other.getLey()==null) || 
             (this.ley!=null &&
              this.ley.equals(other.getLey()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.fechaBoletin==null && other.getFechaBoletin()==null) || 
             (this.fechaBoletin!=null &&
              this.fechaBoletin.equals(other.getFechaBoletin()))) &&
            ((this.validacion==null && other.getValidacion()==null) || 
             (this.validacion!=null &&
              this.validacion.equals(other.getValidacion()))) &&
            ((this.codiVuds==null && other.getCodiVuds()==null) || 
             (this.codiVuds!=null &&
              this.codiVuds.equals(other.getCodiVuds()))) &&
            ((this.descCodiVuds==null && other.getDescCodiVuds()==null) || 
             (this.descCodiVuds!=null &&
              this.descCodiVuds.equals(other.getDescCodiVuds()))) &&
            ((this.t_seccion==null && other.getT_seccion()==null) || 
             (this.t_seccion!=null &&
              this.t_seccion.equals(other.getT_seccion()))) &&
            ((this.t_apartado==null && other.getT_apartado()==null) || 
             (this.t_apartado!=null &&
              this.t_apartado.equals(other.getT_apartado()))) &&
            ((this.t_paginaInicial==null && other.getT_paginaInicial()==null) || 
             (this.t_paginaInicial!=null &&
              this.t_paginaInicial.equals(other.getT_paginaInicial()))) &&
            ((this.t_paginaFinal==null && other.getT_paginaFinal()==null) || 
             (this.t_paginaFinal!=null &&
              this.t_paginaFinal.equals(other.getT_paginaFinal()))) &&
            ((this.t_titulo==null && other.getT_titulo()==null) || 
             (this.t_titulo!=null &&
              this.t_titulo.equals(other.getT_titulo()))) &&
            ((this.t_enlace==null && other.getT_enlace()==null) || 
             (this.t_enlace!=null &&
              this.t_enlace.equals(other.getT_enlace()))) &&
            ((this.t_observaciones==null && other.getT_observaciones()==null) || 
             (this.t_observaciones!=null &&
              this.t_observaciones.equals(other.getT_observaciones()))) &&
            ((this.t_responsable==null && other.getT_responsable()==null) || 
             (this.t_responsable!=null &&
              this.t_responsable.equals(other.getT_responsable()))) &&
            ((this.incluirExternas==null && other.getIncluirExternas()==null) || 
             (this.incluirExternas!=null &&
              this.incluirExternas.equals(other.getIncluirExternas()))) &&
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
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getRegistro() != null) {
            _hashCode += getRegistro().hashCode();
        }
        if (getLey() != null) {
            _hashCode += getLey().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getFechaBoletin() != null) {
            _hashCode += getFechaBoletin().hashCode();
        }
        if (getValidacion() != null) {
            _hashCode += getValidacion().hashCode();
        }
        if (getCodiVuds() != null) {
            _hashCode += getCodiVuds().hashCode();
        }
        if (getDescCodiVuds() != null) {
            _hashCode += getDescCodiVuds().hashCode();
        }
        if (getT_seccion() != null) {
            _hashCode += getT_seccion().hashCode();
        }
        if (getT_apartado() != null) {
            _hashCode += getT_apartado().hashCode();
        }
        if (getT_paginaInicial() != null) {
            _hashCode += getT_paginaInicial().hashCode();
        }
        if (getT_paginaFinal() != null) {
            _hashCode += getT_paginaFinal().hashCode();
        }
        if (getT_titulo() != null) {
            _hashCode += getT_titulo().hashCode();
        }
        if (getT_enlace() != null) {
            _hashCode += getT_enlace().hashCode();
        }
        if (getT_observaciones() != null) {
            _hashCode += getT_observaciones().hashCode();
        }
        if (getT_responsable() != null) {
            _hashCode += getT_responsable().hashCode();
        }
        if (getIncluirExternas() != null) {
            _hashCode += getIncluirExternas().hashCode();
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
        new org.apache.axis.description.TypeDesc(NormativaCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ley");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ley"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaBoletin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaBoletin"));
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
        elemField.setFieldName("t_seccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_seccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_apartado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_apartado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_paginaInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_paginaInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("t_paginaFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_paginaFinal"));
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
        elemField.setFieldName("t_enlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_enlace"));
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
        elemField.setFieldName("t_responsable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "t_responsable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incluirExternas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "incluirExternas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://normativa.v2.api.rolsac.caib.es", "NormativaOrdenacio"));
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
