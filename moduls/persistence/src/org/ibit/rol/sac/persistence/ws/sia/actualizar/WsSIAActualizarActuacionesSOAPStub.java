/**
 * WsSIAActualizarActuacionesSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.ibit.rol.sac.persistence.ws.sia.actualizar;

public class WsSIAActualizarActuacionesSOAPStub extends org.apache.axis.client.Stub implements org.ibit.rol.sac.persistence.ws.sia.actualizar.WsSIAActualizarActuaciones_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("actualizarSIA");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "paramSIA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">paramSIA"), org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIA.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">enviaSIA"));
        oper.setReturnClass(org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIA.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", "enviaSIA"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("actualizarSIA_v2_4");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "paramSIA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">paramSIA"), org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIA.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">enviaSIA"));
        oper.setReturnClass(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIA.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", "enviaSIA"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("actualizarSIA_v3_0");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "paramSIA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">paramSIA"), org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIA.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">enviaSIA"));
        oper.setReturnClass(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIA.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", "enviaSIA"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("actualizarSIA_v3_1");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "paramSIA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">paramSIA"), org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIA.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">enviaSIA"));
        oper.setReturnClass(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", "enviaSIA"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

    }

    public WsSIAActualizarActuacionesSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WsSIAActualizarActuacionesSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WsSIAActualizarActuacionesSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
        addBindings2();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">>enviaSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/EnviaSIA", ">enviaSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ACTIVACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ALTA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIAACTUACIONESACTUACIONALTA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>BAJA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>EDICION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>ACTUACIONESRELACIONADAS>ACTUACIONRELACIONADA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>AQUIENESVADIRIGIDO>AQUIENVADIRIGIDO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>FORMULARIOS>FORMULARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionFORMULARIOSFORMULARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>HECHOSVITALES>HECHOVITAL");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionHECHOSVITALESHECHOVITAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>INICIOS>INICIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionINICIOSINICIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>MULTILINGUISMOS>MULTILINGUISMO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>NORMATIVAS>NORMATIVA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionNORMATIVASNORMATIVA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>PRESTACIONES>PRESTACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionPRESTACIONESPRESTACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionSISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>TEMATICAS>TEMATICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionTEMATICASTEMATICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionVOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>paramSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>ACTUACIONESRELACIONADAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionACTUACIONESRELACIONADASACTUACIONRELACIONADA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>ACTUACIONESRELACIONADAS>ACTUACIONRELACIONADA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "ACTUACIONRELACIONADA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>AGRUPACIONSERV");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionAGRUPACIONSERV.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>AQUIENESVADIRIGIDO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionAQUIENESVADIRIGIDOAQUIENVADIRIGIDO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>AQUIENESVADIRIGIDO>AQUIENVADIRIGIDO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "AQUIENVADIRIGIDO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>FORMULARIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionFORMULARIOSFORMULARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>FORMULARIOS>FORMULARIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "FORMULARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>HECHOSVITALES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionHECHOSVITALESHECHOVITAL[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>HECHOSVITALES>HECHOVITAL");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "HECHOVITAL");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>INICIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionINICIOSINICIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>INICIOS>INICIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "INICIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>MULTILINGUISMOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionMULTILINGUISMOSMULTILINGUISMO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>MULTILINGUISMOS>MULTILINGUISMO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "MULTILINGUISMO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>NORMATIVAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionNORMATIVASNORMATIVA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>NORMATIVAS>NORMATIVA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "NORMATIVA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>PLAZORESOLUCION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionPLAZORESOLUCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>PRESTACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionPRESTACIONESPRESTACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>PRESTACIONES>PRESTACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "PRESTACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>SEGUISERVICIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionSEGUISERVICIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>SISTEMASIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionSISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "SISTEMAIDENTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>TEMATICAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionTEMATICASTEMATICA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>TEMATICAS>TEMATICA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "TEMATICA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">comunAltaEdicion>VOLUMENESTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicionVOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">>comunAltaEdicion>VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "VOLUMENTRAMITACIONES");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">NOTIFICACIONES>NOTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.NOTIFICACIONESNOTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">paramSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "booleano");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "comunAltaEdicion");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ComunAltaEdicion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "NOTIFICACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.NOTIFICACIONESNOTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", ">NOTIFICACIONES>NOTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "NOTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "organismoResponsable");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.OrganismoResponsable.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "tipoActuacion");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/ParamSIA", "tipoSeguiServicio");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">>enviaSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">enviaSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">ERRORES>ERROR");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.ERRORESERROR.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", "ERRORES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.ERRORESERROR[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", ">ERRORES>ERROR");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/EnviaSIA", "ERROR");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ACTIVACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ALTA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONALTA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>BAJA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>EDICION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>paramSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">DESTINATARIOS>DESTINATARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">MULTILINGUISMOS>MULTILINGUISMO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.MULTILINGUISMOSMULTILINGUISMO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">NORMATIVAS>NORMATIVA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">paramSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">PERFILES>PERFIL");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PERFILESPERFIL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">PRESTACIONES>PRESTACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PRESTACIONESPRESTACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">REDUCCIONES>REDUCCION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.REDUCCIONESREDUCCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">TEMATICAS>TEMATICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TEMATICASTEMATICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTADOCUMENTOSESPECIFICOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTADOCUMENTOESPECIFICO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAPROCEDIMIENTOCOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOCOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTAPROCEDIMIENTOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTAPROCEDIMIENTOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTASERVICIOCOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOCOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTASERVICIOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTASERVICIOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTATRAMITECOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTATRAMITECOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ALTATRAMITEESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ALTATRAMITEESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "booleano");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DESTINATARIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DESTINATARIOSDESTINATARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">DESTINATARIOS>DESTINATARIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DESTINATARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DOCUMENTOSCATALOGO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "DOCUMENTOCATALOGO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONCLASIFICACIONTEMATICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONCLASIFICACIONTEMATICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONDOCUMENTACIONASOCIADA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONDOCUMENTACIONASOCIADA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONOTROSDATOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONOTROSDATOS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOCOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONPROCEDIMIENTOCOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONPROCEDIMIENTOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONPROCEDIMIENTOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONREGULACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONREGULACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONSERVICIOCOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONSERVICIOCOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONSERVICIOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONSERVICIOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONTIPOSRELACIONADOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONTIPOSRELACIONADOS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONTRAMITACIONELECTRONICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONTRAMITACIONELECTRONICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONTRAMITECOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONTRAMITECOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONTRAMITEESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONTRAMITEESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "EDICIONVOLUMENTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.EDICIONVOLUMENTRAMITACIONES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "INICIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.INICIOS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "MULTILINGUISMOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.MULTILINGUISMOSMULTILINGUISMO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">MULTILINGUISMOS>MULTILINGUISMO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "MULTILINGUISMO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "NORMATIVAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.NORMATIVASNORMATIVA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">NORMATIVAS>NORMATIVA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "NORMATIVA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "ORGANISMORESPONSABLE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ORGANISMORESPONSABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PERFILES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PERFILESPERFIL[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">PERFILES>PERFIL");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PERFIL");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PLAZORESOLUCION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PLAZORESOLUCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PRESTACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.PRESTACIONESPRESTACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">PRESTACIONES>PRESTACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "PRESTACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "REDUCCIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.REDUCCIONESREDUCCION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">REDUCCIONES>REDUCCION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "REDUCCION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "SEGUIMIENTOTRAMITACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.SEGUIMIENTOTRAMITACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "SISTEMASIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "SISTEMAIDENTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TEMATICAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TEMATICASTEMATICA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">TEMATICAS>TEMATICA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TEMATICA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TIPOSEGUIMIENTOTRAMITACION");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TIPOTRAMITE");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TRAMITESRELACIONADOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "TRAMITERELACIONADO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "VOLUMENESTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v2_4/ParamSIA", "VOLUMENTRAMITACIONES");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">>enviaSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">enviaSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">ERRORES>ERROR");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.ERRORESERROR.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", "ERRORES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.ERRORESERROR[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", ">ERRORES>ERROR");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/EnviaSIA", "ERROR");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ACTIVACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ALTA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIAACTUACIONESACTUACIONALTA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>BAJA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIAACTUACIONESACTUACIONBAJA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>EDICION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIAACTUACIONESACTUACIONEDICION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO>INTERMEDIADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICOINTERMEDIADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO>OBLIGADOAPORTARLOINTERESADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICOOBLIGADOAPORTARLOINTERESADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO>OBLIGADOAPORTARLOINTERESADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGOOBLIGADOAPORTARLOINTERESADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>paramSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">ALTATRAMITACIONELECTRONICA>PRESENCIALNOADAPTABLE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTATRAMITACIONELECTRONICAPRESENCIALNOADAPTABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">DESTINATARIOS>DESTINATARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">EDICIONTRAMITACIONELECTRONICA>PRESENCIALNOADAPTABLE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONTRAMITACIONELECTRONICAPRESENCIALNOADAPTABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">FORMULARIOS>FORMULARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.FORMULARIOSFORMULARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">HECHOSVITALES>HECHOVITAL");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.HECHOSVITALESHECHOVITAL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">MATERIAS>MATERIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.MATERIASMATERIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">NORMATIVAS>NORMATIVA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.NORMATIVASNORMATIVA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">NOTIFICACIONES>NOTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.NOTIFICACIONESNOTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">paramSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">REDUCCIONES>REDUCCION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.REDUCCIONESREDUCCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTACLASIFICACIONTEMATICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTACLASIFICACIONTEMATICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTADOCUMENTOSESPECIFICOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTADOCUMENTOESPECIFICO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTAINFORMACIONTRAMITE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTAINFORMACIONTRAMITE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTAPROCEDIMIENTO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTAPROCEDIMIENTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTASERVICIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTASERVICIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ALTATRAMITACIONELECTRONICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ALTATRAMITACIONELECTRONICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "booleano");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DESTINATARIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DESTINATARIOSDESTINATARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">DESTINATARIOS>DESTINATARIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DESTINATARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DOCUMENTOSCATALOGO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "DOCUMENTOCATALOGO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONCLASIFICACIONTEMATICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONCLASIFICACIONTEMATICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONDOCUMENTACIONASOCIADA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONDOCUMENTACIONASOCIADA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONESTADISTICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONESTADISTICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONINFORMACIONTRAMITE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONINFORMACIONTRAMITE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONPROCEDIMIENTO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONPROCEDIMIENTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONSERVICIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONSERVICIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONTIPOSRELACIONADOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONTIPOSRELACIONADOS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONTRAMITACIONELECTRONICA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONTRAMITACIONELECTRONICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "EDICIONTRAMITE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EDICIONTRAMITE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "emptyString");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.EmptyString.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "FORMULARIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.FORMULARIOSFORMULARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">FORMULARIOS>FORMULARIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "FORMULARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "HECHOSVITALES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.HECHOSVITALESHECHOVITAL[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">HECHOSVITALES>HECHOVITAL");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "HECHOVITAL");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "INICIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.INICIOS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "MATERIAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.MATERIASMATERIA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">MATERIAS>MATERIA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "MATERIA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NORMATIVAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.NORMATIVASNORMATIVA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">NORMATIVAS>NORMATIVA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NORMATIVA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NOTIFICACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.NOTIFICACIONESNOTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">NOTIFICACIONES>NOTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "NOTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "ORGANISMORESPONSABLE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ORGANISMORESPONSABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "PLAZORESOLUCION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.PLAZORESOLUCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "REDUCCIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.REDUCCIONESREDUCCION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">REDUCCIONES>REDUCCION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "REDUCCION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "SISTEMASIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "SISTEMAIDENTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TIPOTRAMITE");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TRAMITE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.TRAMITE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TRAMITESRELACIONADOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "TRAMITERELACIONADO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENESTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENTRAMITACIONES");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENNOTIFICACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_0/ParamSIA", "VOLUMENNOTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">>enviaSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">>>enviaSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">enviaSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">ERRORES>ERROR");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.ERRORESERROR.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", "ERRORES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.ERRORESERROR[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", ">ERRORES>ERROR");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/EnviaSIA", "ERROR");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ACTIVO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>DISPONIBLEFUNCIONARIOHABILITADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>ESCOMUN");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>FINVIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>HABILITABLEREGISTOFUNCIONARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONHABILITABLEREGISTOFUNCIONARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>INTERNO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>NOREQUIEREDOCUMENTACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONNOREQUIEREDOCUMENTACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>PRESENCIALNOADAPTABLE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONPRESENCIALNOADAPTABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>SUJETOATASAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONSUJETOATASAS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>>paramSIA>ACTUACIONES>ACTUACION>TIPOTRAMITE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO>OBLIGADOAPORTARLOINTERESADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICOOBLIGADOAPORTARLOINTERESADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO>OBLIGADOAPORTARLOINTERESADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGOOBLIGADOAPORTARLOINTERESADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>paramSIA>ACTUACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">>>paramSIA>ACTUACIONES>ACTUACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ACTUACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">DESTINATARIOS>DESTINATARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">FORMULARIOS>FORMULARIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.FORMULARIOSFORMULARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">INICIOS>FORMAINICIACIONINTERESADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOSFORMAINICIACIONINTERESADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">INICIOS>FORMAINICIACIONOFICIO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOSFORMAINICIACIONOFICIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">MATERIAS>MATERIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">NORMATIVAS>NORMATIVA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">NOTIFICACIONES>NOTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NOTIFICACIONESNOTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">paramSIA");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings2() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ALTADOCUMENTOSESPECIFICOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ALTADOCUMENTOSESPECIFICOSALTADOCUMENTOESPECIFICO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">ALTADOCUMENTOSESPECIFICOS>ALTADOCUMENTOESPECIFICO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ALTADOCUMENTOESPECIFICO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "booleano");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DESTINATARIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">DESTINATARIOS>DESTINATARIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DESTINATARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DOCUMENTOSCATALOGO");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DOCUMENTOSCATALOGODOCUMENTOCATALOGO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">DOCUMENTOSCATALOGO>DOCUMENTOCATALOGO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "DOCUMENTOCATALOGO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "emptyString");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.EmptyString.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "FORMULARIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.FORMULARIOSFORMULARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">FORMULARIOS>FORMULARIO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "FORMULARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "INICIOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.INICIOS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "MATERIAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">MATERIAS>MATERIA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "MATERIA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NORMATIVAS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">NORMATIVAS>NORMATIVA");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NORMATIVA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NOTIFICACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NOTIFICACIONESNOTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">NOTIFICACIONES>NOTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "NOTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "ORGANISMORESPONSABLE");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ORGANISMORESPONSABLE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "PLAZORESOLUCION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.PLAZORESOLUCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SISTEMASIDENTIFICACION");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.SISTEMASIDENTIFICACIONSISTEMAIDENTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">SISTEMASIDENTIFICACION>SISTEMAIDENTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "SISTEMAIDENTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TIPOTRAMITE");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TRAMITESRELACIONADOS");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.TRAMITESRELACIONADOSTRAMITERELACIONADO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">TRAMITESRELACIONADOS>TRAMITERELACIONADO");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "TRAMITERELACIONADO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENESTRAMITACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENESTRAMITACIONESVOLUMENTRAMITACIONES[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">VOLUMENESTRAMITACIONES>VOLUMENTRAMITACIONES");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENTRAMITACIONES");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENNOTIFICACIONES");
            cachedSerQNames.add(qName);
            cls = org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.VOLUMENNOTIFICACIONESVOLUMENNOTIFICACION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", ">VOLUMENNOTIFICACIONES>VOLUMENNOTIFICACION");
            qName2 = new javax.xml.namespace.QName("http://www.map.es/sgca/actualizar/messages/v3_1/ParamSIA", "VOLUMENNOTIFICACION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIA actualizarSIA(org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.map.es/sgca/actualizar/actualizarSIA");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "actualizarSIA"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIA) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIA) org.apache.axis.utils.JavaUtils.convert(_resp, org.ibit.rol.sac.persistence.ws.sia.actualizar.messages.enviaSIA.EnviaSIA.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIA actualizarSIA_v2_4(org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.map.es/sgca/actualizar/actualizarSIA_v2_4");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "actualizarSIA_v2_4"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIA) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIA) org.apache.axis.utils.JavaUtils.convert(_resp, org.ibit.rol.sac.persistence.ws.sia.actualizar.v2_4.enviaSIA.EnviaSIA.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIA actualizarSIA_v3_0(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.map.es/sgca/actualizar/actualizarSIA_v3_0");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "actualizarSIA_v3_0"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIA) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIA) org.apache.axis.utils.JavaUtils.convert(_resp, org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_0.enviaSIA.EnviaSIA.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA actualizarSIA_v3_1(org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIA parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://www.map.es/sgca/actualizar/actualizarSIA_v3_1");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "actualizarSIA_v3_1"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA) org.apache.axis.utils.JavaUtils.convert(_resp, org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
