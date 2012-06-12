
/**
 * SistemaInformacionWSSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package test.webservice;

import es.caib.persistence.vuds.GestorWebserviceBeanServiceSkeleton;
import es.map.vuds.si.service.webservice.CargarTramitesVudsResponse;
import es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE;
import es.map.vuds.si.service.webservice.GuardarTramitesResponse;
import es.map.vuds.si.service.webservice.GuardarTramitesResponseE;
import es.map.vuds.si.service.webservice.TramiteVuds;
    /**
     *  SistemaInformacionWSSkeleton java skeleton for the axisService
     */
    public class VUDS_ServiceImplAxis2 extends GestorWebserviceBeanServiceSkeleton{
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarCanalesTramitacion
         */
        
                 public es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE cargarCanalesTramitacion
                  (
                  es.map.vuds.si.service.webservice.CargarCanalesTramitacionE cargarCanalesTramitacion
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarCanalesTramitacion");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarFormaIniciacion
         */
        
                 public es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE cargarFormaIniciacion
                  (
                  es.map.vuds.si.service.webservice.CargarFormaIniciacionE cargarFormaIniciacion
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarFormaIniciacion");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarOrganizacionesAsistencia
         */
        
                 public es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaResponseE cargarOrganizacionesAsistencia
                  (
                  es.map.vuds.si.service.webservice.CargarOrganizacionesAsistenciaE cargarOrganizacionesAsistencia
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarOrganizacionesAsistencia");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param guardarViasReclamacion
         */
        
                 public es.map.vuds.si.service.webservice.GuardarViasReclamacionResponseE guardarViasReclamacion
                  (
                  es.map.vuds.si.service.webservice.GuardarViasReclamacionE guardarViasReclamacion
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#guardarViasReclamacion");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarResultadosProceso
         */
        
                 public es.map.vuds.si.service.webservice.CargarResultadosProcesoResponseE cargarResultadosProceso
                  (
                  es.map.vuds.si.service.webservice.CargarResultadosProcesoE cargarResultadosProceso
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarResultadosProceso");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param guardarTramites
         */
        
                 public es.map.vuds.si.service.webservice.GuardarTramitesResponseE guardarTramites
                  (
                  es.map.vuds.si.service.webservice.GuardarTramitesE guardarTramites
                  )
            {
                	 System.out.println("inside guadarTramites");
                	 GuardarTramitesResponseE respE=new GuardarTramitesResponseE();
                	 GuardarTramitesResponse resp=new GuardarTramitesResponse();
                	 respE.setGuardarTramitesResponse(resp);
                	 return respE;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarViasReclamacion
         */
        
                 public es.map.vuds.si.service.webservice.CargarViasReclamacionResponseE cargarViasReclamacion
                  (
                  es.map.vuds.si.service.webservice.CargarViasReclamacionE cargarViasReclamacion
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarViasReclamacion");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarIdiomas
         */
        
                 public es.map.vuds.si.service.webservice.CargarIdiomasResponseE cargarIdiomas
                  (
                  es.map.vuds.si.service.webservice.CargarIdiomasE cargarIdiomas
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarIdiomas");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarTramitesVuds
         */
        
                 public es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE cargarTramitesVuds
                  (
                  es.map.vuds.si.service.webservice.CargarTramitesVudsE cargarTramitesVuds
                  )
            {
                	 System.out.println("inside cargarTramitesVuds");
                	CargarTramitesVudsResponseE respE = new CargarTramitesVudsResponseE();
                	CargarTramitesVudsResponse resp = new CargarTramitesVudsResponse();
                	
                	TramiteVuds codiVuds=new TramiteVuds();
                	codiVuds.setIdTramiteVuds("C0001");
                	codiVuds.setDescripcionTramiteVuds("desc tramit 0001");
                	resp.add_return(codiVuds);
                	
                	codiVuds=new TramiteVuds();
                	codiVuds.setIdTramiteVuds("C0002");
                	codiVuds.setDescripcionTramiteVuds("desc tramit 0002");
                	resp.add_return(codiVuds);

                	codiVuds=new TramiteVuds();
                	codiVuds.setIdTramiteVuds("C0003");
                	codiVuds.setDescripcionTramiteVuds("desc tramit 0003");
                	resp.add_return(codiVuds);

                	respE.setCargarTramitesVudsResponse(resp);
             		return respE;
            }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarTramites
         */
        
                 public es.map.vuds.si.service.webservice.CargarTramitesResponseE cargarTramites
                  (
                  es.map.vuds.si.service.webservice.CargarTramitesE cargarTramites
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarTramites");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarNormativas
         */
        
                 public es.map.vuds.si.service.webservice.CargarNormativasResponseE cargarNormativas
                  (
                  es.map.vuds.si.service.webservice.CargarNormativasE cargarNormativas
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarNormativas");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarInstrumentosReclamacion
         */
        
                 public es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionResponseE cargarInstrumentosReclamacion
                  (
                  es.map.vuds.si.service.webservice.CargarInstrumentosReclamacionE cargarInstrumentosReclamacion
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarInstrumentosReclamacion");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarRecursosResolucion
         */
        
                 public es.map.vuds.si.service.webservice.CargarRecursosResolucionResponseE cargarRecursosResolucion
                  (
                  es.map.vuds.si.service.webservice.CargarRecursosResolucionE cargarRecursosResolucion
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarRecursosResolucion");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarActividadesEconomicas
         */
        
                 public es.map.vuds.si.service.webservice.CargarActividadesEconomicasResponseE cargarActividadesEconomicas
                  (
                  es.map.vuds.si.service.webservice.CargarActividadesEconomicasE cargarActividadesEconomicas
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarActividadesEconomicas");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param cargarAreasTramitadora
         */
        
                 public es.map.vuds.si.service.webservice.CargarAreasTramitadoraResponseE cargarAreasTramitadora
                  (
                  es.map.vuds.si.service.webservice.CargarAreasTramitadoraE cargarAreasTramitadora
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cargarAreasTramitadora");
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param guardarOrganizacionesAsistencia
         */
        
                 public es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaResponseE guardarOrganizacionesAsistencia
                  (
                  es.map.vuds.si.service.webservice.GuardarOrganizacionesAsistenciaE guardarOrganizacionesAsistencia
                  )
            {
                //TODO : fill this with the necessary business logic
                throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#guardarOrganizacionesAsistencia");
        }
     
    }
    