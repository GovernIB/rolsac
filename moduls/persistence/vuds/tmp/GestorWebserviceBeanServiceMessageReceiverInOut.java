
/**
 * GestorWebserviceBeanServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
        package es.caib.persistence.vuds;

        /**
        *  GestorWebserviceBeanServiceMessageReceiverInOut message receiver
        */

        public class GestorWebserviceBeanServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        GestorWebserviceBeanServiceSkeleton skel = (GestorWebserviceBeanServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("cargarFormaIniciacion".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE cargarFormaIniciacionResponse33 = null;
	                        es.map.vuds.si.service.webservice.CargarFormaIniciacionE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarFormaIniciacionE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarFormaIniciacionE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarFormaIniciacionResponse33 =
                                                   
                                                   
                                                         skel.cargarFormaIniciacion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarFormaIniciacionResponse33, false);
                                    } else 

            if("cargarCanalesTramitacion".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE cargarCanalesTramitacionResponse35 = null;
	                        es.map.vuds.si.service.webservice.CargarCanalesTramitacionE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarCanalesTramitacionE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarCanalesTramitacionE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarCanalesTramitacionResponse35 =
                                                   
                                                   
                                                         skel.cargarCanalesTramitacion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarCanalesTramitacionResponse35, false);
                                    } else 

            if("cargarOrganizacionesAsistencia".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE cargarOrganizacionesAsistenciaResponse37 = null;
	                        es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarOrganizacionesAsistenciaResponse37 =
                                                   
                                                   
                                                         skel.cargarOrganizacionesAsistencia(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarOrganizacionesAsistenciaResponse37, false);
                                    } else 

            if("guardarViasReclamacion".equals(methodName)){
                
                es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE guardarViasReclamacionResponse39 = null;
	                        es.map.vuds.si.service.webservice.GuardarViasReclamacionE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.GuardarViasReclamacionE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.GuardarViasReclamacionE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               guardarViasReclamacionResponse39 =
                                                   
                                                   
                                                         skel.guardarViasReclamacion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), guardarViasReclamacionResponse39, false);
                                    } else 

            if("guardarTramites".equals(methodName)){
                
                es.map.vuds.si.service.webservice.GuardarTramitesResponseE guardarTramitesResponse41 = null;
	                        es.map.vuds.si.service.webservice.GuardarTramitesE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.GuardarTramitesE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.GuardarTramitesE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               guardarTramitesResponse41 =
                                                   
                                                   
                                                         skel.guardarTramites(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), guardarTramitesResponse41, false);
                                    } else 

            if("cargarResultadosProceso".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE cargarResultadosProcesoResponse43 = null;
	                        es.map.vuds.si.service.webservice.CargarResultadosProcesoE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarResultadosProcesoE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarResultadosProcesoE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarResultadosProcesoResponse43 =
                                                   
                                                   
                                                         skel.cargarResultadosProceso(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarResultadosProcesoResponse43, false);
                                    } else 

            if("cargarViasReclamacion".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE cargarViasReclamacionResponse45 = null;
	                        es.map.vuds.si.service.webservice.CargarViasReclamacionE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarViasReclamacionE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarViasReclamacionE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarViasReclamacionResponse45 =
                                                   
                                                   
                                                         skel.cargarViasReclamacion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarViasReclamacionResponse45, false);
                                    } else 

            if("cargarTramitesVuds".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE cargarTramitesVudsResponse47 = null;
	                        es.map.vuds.si.service.webservice.CargarTramitesVudsE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarTramitesVudsE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarTramitesVudsE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarTramitesVudsResponse47 =
                                                   
                                                   
                                                         skel.cargarTramitesVuds(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarTramitesVudsResponse47, false);
                                    } else 

            if("cargarIdiomas".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarIdiomasResponseE cargarIdiomasResponse49 = null;
	                        es.map.vuds.si.service.webservice.CargarIdiomasE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarIdiomasE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarIdiomasE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarIdiomasResponse49 =
                                                   
                                                   
                                                         skel.cargarIdiomas(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarIdiomasResponse49, false);
                                    } else 

            if("cargarTramites".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarTramitesResponseE cargarTramitesResponse51 = null;
	                        es.map.vuds.si.service.webservice.CargarTramitesE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarTramitesE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarTramitesE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarTramitesResponse51 =
                                                   
                                                   
                                                         skel.cargarTramites(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarTramitesResponse51, false);
                                    } else 

            if("cargarNormativas".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarNormativasResponseE cargarNormativasResponse53 = null;
	                        es.map.vuds.si.service.webservice.CargarNormativasE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarNormativasE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarNormativasE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarNormativasResponse53 =
                                                   
                                                   
                                                         skel.cargarNormativas(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarNormativasResponse53, false);
                                    } else 

            if("cargarInstrumentosReclamacion".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE cargarInstrumentosReclamacionResponse55 = null;
	                        es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarInstrumentosReclamacionResponse55 =
                                                   
                                                   
                                                         skel.cargarInstrumentosReclamacion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarInstrumentosReclamacionResponse55, false);
                                    } else 

            if("cargarRecursosResolucion".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE cargarRecursosResolucionResponse57 = null;
	                        es.map.vuds.si.service.webservice.CargarRecursosResolucionE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarRecursosResolucionE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarRecursosResolucionE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarRecursosResolucionResponse57 =
                                                   
                                                   
                                                         skel.cargarRecursosResolucion(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarRecursosResolucionResponse57, false);
                                    } else 

            if("cargarAreasTramitadora".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE cargarAreasTramitadoraResponse59 = null;
	                        es.map.vuds.si.service.webservice.CargarAreasTramitadoraE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarAreasTramitadoraE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarAreasTramitadoraE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarAreasTramitadoraResponse59 =
                                                   
                                                   
                                                         skel.cargarAreasTramitadora(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarAreasTramitadoraResponse59, false);
                                    } else 

            if("cargarActividadesEconomicas".equals(methodName)){
                
                es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE cargarActividadesEconomicasResponse61 = null;
	                        es.map.vuds.si.service.webservice.CargarActividadesEconomicasE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.CargarActividadesEconomicasE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.CargarActividadesEconomicasE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cargarActividadesEconomicasResponse61 =
                                                   
                                                   
                                                         skel.cargarActividadesEconomicas(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cargarActividadesEconomicasResponse61, false);
                                    } else 

            if("guardarOrganizacionesAsistencia".equals(methodName)){
                
                es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE guardarOrganizacionesAsistenciaResponse63 = null;
	                        es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE wrappedParam =
                                                             (es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               guardarOrganizacionesAsistenciaResponse63 =
                                                   
                                                   
                                                         skel.guardarOrganizacionesAsistencia(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), guardarOrganizacionesAsistenciaResponse63, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarFormaIniciacionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarFormaIniciacionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarCanalesTramitacionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarCanalesTramitacionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.GuardarViasReclamacionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.GuardarViasReclamacionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.GuardarTramitesE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.GuardarTramitesE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.GuardarTramitesResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.GuardarTramitesResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarResultadosProcesoE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarResultadosProcesoE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarViasReclamacionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarViasReclamacionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarTramitesVudsE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarTramitesVudsE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarIdiomasE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarIdiomasE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarIdiomasResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarIdiomasResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarTramitesE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarTramitesE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarTramitesResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarTramitesResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarNormativasE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarNormativasE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarNormativasResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarNormativasResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarRecursosResolucionE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarRecursosResolucionE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarAreasTramitadoraE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarAreasTramitadoraE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarActividadesEconomicasE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarActividadesEconomicasE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE wrapcargarCanalesTramitacion(){
                                es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE wrapcargarFormaIniciacion(){
                                es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE wrapcargarOrganizacionesAsistencia(){
                                es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE wrapguardarViasReclamacion(){
                                es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE wrappedElement = new es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE wrapcargarResultadosProceso(){
                                es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.GuardarTramitesResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.GuardarTramitesResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.GuardarTramitesResponseE wrapguardarTramites(){
                                es.map.vuds.si.service.webservice.GuardarTramitesResponseE wrappedElement = new es.map.vuds.si.service.webservice.GuardarTramitesResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE wrapcargarViasReclamacion(){
                                es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarIdiomasResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarIdiomasResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarIdiomasResponseE wrapcargarIdiomas(){
                                es.map.vuds.si.service.webservice.CargarIdiomasResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarIdiomasResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE wrapcargarTramitesVuds(){
                                es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarTramitesResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarTramitesResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarTramitesResponseE wrapcargarTramites(){
                                es.map.vuds.si.service.webservice.CargarTramitesResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarTramitesResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarNormativasResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarNormativasResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarNormativasResponseE wrapcargarNormativas(){
                                es.map.vuds.si.service.webservice.CargarNormativasResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarNormativasResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE wrapcargarInstrumentosReclamacion(){
                                es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE wrapcargarActividadesEconomicas(){
                                es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE wrapcargarAreasTramitadora(){
                                es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE wrapcargarRecursosResolucion(){
                                es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE wrappedElement = new es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE wrapguardarOrganizacionesAsistencia(){
                                es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE wrappedElement = new es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (es.map.vuds.si.service.webservice.CargarFormaIniciacionE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarFormaIniciacionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarCanalesTramitacionE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarCanalesTramitacionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.GuardarViasReclamacionE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.GuardarViasReclamacionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.GuardarTramitesE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.GuardarTramitesE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.GuardarTramitesResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.GuardarTramitesResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarResultadosProcesoE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarResultadosProcesoE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarViasReclamacionE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarViasReclamacionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarTramitesVudsE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarTramitesVudsE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarIdiomasE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarIdiomasE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarIdiomasResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarIdiomasResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarTramitesE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarTramitesE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarTramitesResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarTramitesResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarNormativasE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarNormativasE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarNormativasResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarNormativasResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarRecursosResolucionE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarRecursosResolucionE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarAreasTramitadoraE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarAreasTramitadoraE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarActividadesEconomicasE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarActividadesEconomicasE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE.class.equals(type)){
                
                           return es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    