
/**
 * GestorWebserviceBeanServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.ibit.rol.sac.persistence.remote.vuds;

    /**
     *  GestorWebserviceBeanServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GestorWebserviceBeanServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GestorWebserviceBeanServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GestorWebserviceBeanServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for consultarMapaTramite method
            * override this method for handling normal response from consultarMapaTramite operation
            */
           public void receiveResultconsultarMapaTramite(
                    es.map.vuds.si.service.webservice.ConsultarMapaTramiteResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarMapaTramite operation
           */
            public void receiveErrorconsultarMapaTramite(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarIdiomas method
            * override this method for handling normal response from cargarIdiomas operation
            */
           public void receiveResultcargarIdiomas(
                    es.map.vuds.si.service.webservice.CargarIdiomasResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarIdiomas operation
           */
            public void receiveErrorcargarIdiomas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarAreasActividad method
            * override this method for handling normal response from cargarAreasActividad operation
            */
           public void receiveResultcargarAreasActividad(
                    es.map.vuds.si.service.webservice.CargarAreasActividadResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarAreasActividad operation
           */
            public void receiveErrorcargarAreasActividad(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarOrganizacionesAsistenciaAACC method
            * override this method for handling normal response from cargarOrganizacionesAsistenciaAACC operation
            */
           public void receiveResultcargarOrganizacionesAsistenciaAACC(
                    es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaAACCResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarOrganizacionesAsistenciaAACC operation
           */
            public void receiveErrorcargarOrganizacionesAsistenciaAACC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarViasReclamacion method
            * override this method for handling normal response from guardarViasReclamacion operation
            */
           public void receiveResultguardarViasReclamacion(
                    es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarViasReclamacion operation
           */
            public void receiveErrorguardarViasReclamacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarOrganizacionesAsistencia method
            * override this method for handling normal response from guardarOrganizacionesAsistencia operation
            */
           public void receiveResultguardarOrganizacionesAsistencia(
                    es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarOrganizacionesAsistencia operation
           */
            public void receiveErrorguardarOrganizacionesAsistencia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarResultadosProceso method
            * override this method for handling normal response from cargarResultadosProceso operation
            */
           public void receiveResultcargarResultadosProceso(
                    es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarResultadosProceso operation
           */
            public void receiveErrorcargarResultadosProceso(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarTramitesAACC method
            * override this method for handling normal response from cargarTramitesAACC operation
            */
           public void receiveResultcargarTramitesAACC(
                    es.map.vuds.si.service.webservice.CargarTramitesAACCResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarTramitesAACC operation
           */
            public void receiveErrorcargarTramitesAACC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarEstadoTramites method
            * override this method for handling normal response from consultarEstadoTramites operation
            */
           public void receiveResultconsultarEstadoTramites(
                    es.map.vuds.si.service.webservice.ConsultarEstadoTramitesResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarEstadoTramites operation
           */
            public void receiveErrorconsultarEstadoTramites(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarFormaJuridica method
            * override this method for handling normal response from cargarFormaJuridica operation
            */
           public void receiveResultcargarFormaJuridica(
                    es.map.vuds.si.service.webservice.CargarFormaJuridicaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarFormaJuridica operation
           */
            public void receiveErrorcargarFormaJuridica(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarActividadesEconomicas method
            * override this method for handling normal response from cargarActividadesEconomicas operation
            */
           public void receiveResultcargarActividadesEconomicas(
                    es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarActividadesEconomicas operation
           */
            public void receiveErrorcargarActividadesEconomicas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarTramitesVuds method
            * override this method for handling normal response from cargarTramitesVuds operation
            */
           public void receiveResultcargarTramitesVuds(
                    es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarTramitesVuds operation
           */
            public void receiveErrorcargarTramitesVuds(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarTipoViaReclamacion method
            * override this method for handling normal response from cargarTipoViaReclamacion operation
            */
           public void receiveResultcargarTipoViaReclamacion(
                    es.map.vuds.si.service.webservice.CargarTipoViaReclamacionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarTipoViaReclamacion operation
           */
            public void receiveErrorcargarTipoViaReclamacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarAmbitoEjercicio method
            * override this method for handling normal response from cargarAmbitoEjercicio operation
            */
           public void receiveResultcargarAmbitoEjercicio(
                    es.map.vuds.si.service.webservice.CargarAmbitoEjercicioResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarAmbitoEjercicio operation
           */
            public void receiveErrorcargarAmbitoEjercicio(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarViaReclamacionVUDS method
            * override this method for handling normal response from cargarViaReclamacionVUDS operation
            */
           public void receiveResultcargarViaReclamacionVUDS(
                    es.map.vuds.si.service.webservice.CargarViaReclamacionVUDSResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarViaReclamacionVUDS operation
           */
            public void receiveErrorcargarViaReclamacionVUDS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarViasReclamacionAACC method
            * override this method for handling normal response from cargarViasReclamacionAACC operation
            */
           public void receiveResultcargarViasReclamacionAACC(
                    es.map.vuds.si.service.webservice.CargarViasReclamacionAACCResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarViasReclamacionAACC operation
           */
            public void receiveErrorcargarViasReclamacionAACC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarDetalleTramite method
            * override this method for handling normal response from consultarDetalleTramite operation
            */
           public void receiveResultconsultarDetalleTramite(
                    es.map.vuds.si.service.webservice.ConsultarDetalleTramiteResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarDetalleTramite operation
           */
            public void receiveErrorconsultarDetalleTramite(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarViaPrestacion method
            * override this method for handling normal response from cargarViaPrestacion operation
            */
           public void receiveResultcargarViaPrestacion(
                    es.map.vuds.si.service.webservice.CargarViaPrestacionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarViaPrestacion operation
           */
            public void receiveErrorcargarViaPrestacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarRechazosTramites method
            * override this method for handling normal response from consultarRechazosTramites operation
            */
           public void receiveResultconsultarRechazosTramites(
                    es.map.vuds.si.service.webservice.ConsultarRechazosTramitesResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarRechazosTramites operation
           */
            public void receiveErrorconsultarRechazosTramites(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarAreasTramitadora method
            * override this method for handling normal response from cargarAreasTramitadora operation
            */
           public void receiveResultcargarAreasTramitadora(
                    es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarAreasTramitadora operation
           */
            public void receiveErrorcargarAreasTramitadora(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarTramitePorAmbito method
            * override this method for handling normal response from consultarTramitePorAmbito operation
            */
           public void receiveResultconsultarTramitePorAmbito(
                    es.map.vuds.si.service.webservice.ConsultarTramitePorAmbitoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarTramitePorAmbito operation
           */
            public void receiveErrorconsultarTramitePorAmbito(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarTipoSolicitante method
            * override this method for handling normal response from cargarTipoSolicitante operation
            */
           public void receiveResultcargarTipoSolicitante(
                    es.map.vuds.si.service.webservice.CargarTipoSolicitanteResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarTipoSolicitante operation
           */
            public void receiveErrorcargarTipoSolicitante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarTramites method
            * override this method for handling normal response from guardarTramites operation
            */
           public void receiveResultguardarTramites(
                    es.map.vuds.si.service.webservice.GuardarTramitesResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarTramites operation
           */
            public void receiveErrorguardarTramites(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarTipoEstablecimiento method
            * override this method for handling normal response from cargarTipoEstablecimiento operation
            */
           public void receiveResultcargarTipoEstablecimiento(
                    es.map.vuds.si.service.webservice.CargarTipoEstablecimientoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarTipoEstablecimiento operation
           */
            public void receiveErrorcargarTipoEstablecimiento(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarFormaIniciacion method
            * override this method for handling normal response from cargarFormaIniciacion operation
            */
           public void receiveResultcargarFormaIniciacion(
                    es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarFormaIniciacion operation
           */
            public void receiveErrorcargarFormaIniciacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarCanalesTramitacion method
            * override this method for handling normal response from cargarCanalesTramitacion operation
            */
           public void receiveResultcargarCanalesTramitacion(
                    es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarCanalesTramitacion operation
           */
            public void receiveErrorcargarCanalesTramitacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarOrganizacionAsistenciaVUDS method
            * override this method for handling normal response from cargarOrganizacionAsistenciaVUDS operation
            */
           public void receiveResultcargarOrganizacionAsistenciaVUDS(
                    es.map.vuds.si.service.webservice.CargarOrganizacionAsistenciaVUDSResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarOrganizacionAsistenciaVUDS operation
           */
            public void receiveErrorcargarOrganizacionAsistenciaVUDS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarActividadesByAreaActividad method
            * override this method for handling normal response from cargarActividadesByAreaActividad operation
            */
           public void receiveResultcargarActividadesByAreaActividad(
                    es.map.vuds.si.service.webservice.CargarActividadesByAreaActividadResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarActividadesByAreaActividad operation
           */
            public void receiveErrorcargarActividadesByAreaActividad(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarLocalizacion method
            * override this method for handling normal response from consultarLocalizacion operation
            */
           public void receiveResultconsultarLocalizacion(
                    es.map.vuds.si.service.webservice.ConsultarLocalizacionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarLocalizacion operation
           */
            public void receiveErrorconsultarLocalizacion(java.lang.Exception e) {
            }
                


    }
    