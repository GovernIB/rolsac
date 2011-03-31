
/**
 * Tramite.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package es.map.vuds.si.service.webservice;
            

            /**
            *  Tramite bean class
            */
        
        public  class Tramite
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tramite
                Namespace URI = http://webservice.service.si.vuds.map.es/
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://webservice.service.si.vuds.map.es/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for OrganismoCompetente
                        */

                        
                                    protected es.map.vuds.si.service.webservice.OrganismoCompetente localOrganismoCompetente ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.OrganismoCompetente
                           */
                           public  es.map.vuds.si.service.webservice.OrganismoCompetente getOrganismoCompetente(){
                               return localOrganismoCompetente;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OrganismoCompetente
                               */
                               public void setOrganismoCompetente(es.map.vuds.si.service.webservice.OrganismoCompetente param){
                            
                                            this.localOrganismoCompetente=param;
                                    

                               }
                            

                        /**
                        * field for CanalTramitacion
                        */

                        
                                    protected es.map.vuds.si.service.webservice.CanalTramitacion localCanalTramitacion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCanalTramitacionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.CanalTramitacion
                           */
                           public  es.map.vuds.si.service.webservice.CanalTramitacion getCanalTramitacion(){
                               return localCanalTramitacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CanalTramitacion
                               */
                               public void setCanalTramitacion(es.map.vuds.si.service.webservice.CanalTramitacion param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCanalTramitacionTracker = true;
                                       } else {
                                          localCanalTramitacionTracker = false;
                                              
                                       }
                                   
                                            this.localCanalTramitacion=param;
                                    

                               }
                            

                        /**
                        * field for CodigoIdentificador
                        */

                        
                                    protected java.lang.String localCodigoIdentificador ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodigoIdentificador(){
                               return localCodigoIdentificador;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CodigoIdentificador
                               */
                               public void setCodigoIdentificador(java.lang.String param){
                            
                                            this.localCodigoIdentificador=param;
                                    

                               }
                            

                        /**
                        * field for DenominacionTramite
                        */

                        
                                    protected java.lang.String localDenominacionTramite ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDenominacionTramite(){
                               return localDenominacionTramite;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DenominacionTramite
                               */
                               public void setDenominacionTramite(java.lang.String param){
                            
                                            this.localDenominacionTramite=param;
                                    

                               }
                            

                        /**
                        * field for DescripcionTramite
                        */

                        
                                    protected java.lang.String localDescripcionTramite ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDescripcionTramite(){
                               return localDescripcionTramite;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DescripcionTramite
                               */
                               public void setDescripcionTramite(java.lang.String param){
                            
                                            this.localDescripcionTramite=param;
                                    

                               }
                            

                        /**
                        * field for Documento
                        * This was an Array!
                        */

                        
                                    protected java.lang.String[] localDocumento ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDocumentoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String[]
                           */
                           public  java.lang.String[] getDocumento(){
                               return localDocumento;
                           }

                           
                        


                               
                              /**
                               * validate the array for Documento
                               */
                              protected void validateDocumento(java.lang.String[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Documento
                              */
                              public void setDocumento(java.lang.String[] param){
                              
                                   validateDocumento(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localDocumentoTracker = true;
                                          } else {
                                             localDocumentoTracker = true;
                                                 
                                          }
                                      
                                      this.localDocumento=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param java.lang.String
                             */
                             public void addDocumento(java.lang.String param){
                                   if (localDocumento == null){
                                   localDocumento = new java.lang.String[]{};
                                   }

                            
                                 //update the setting tracker
                                localDocumentoTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localDocumento);
                               list.add(param);
                               this.localDocumento =
                             (java.lang.String[])list.toArray(
                            new java.lang.String[list.size()]);

                             }
                             

                        /**
                        * field for FormaIniciacion
                        */

                        
                                    protected es.map.vuds.si.service.webservice.FormaIniciacion localFormaIniciacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.FormaIniciacion
                           */
                           public  es.map.vuds.si.service.webservice.FormaIniciacion getFormaIniciacion(){
                               return localFormaIniciacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FormaIniciacion
                               */
                               public void setFormaIniciacion(es.map.vuds.si.service.webservice.FormaIniciacion param){
                            
                                            this.localFormaIniciacion=param;
                                    

                               }
                            

                        /**
                        * field for Formulario
                        * This was an Array!
                        */

                        
                                    protected es.map.vuds.si.service.webservice.Formulario[] localFormulario ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFormularioTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.Formulario[]
                           */
                           public  es.map.vuds.si.service.webservice.Formulario[] getFormulario(){
                               return localFormulario;
                           }

                           
                        


                               
                              /**
                               * validate the array for Formulario
                               */
                              protected void validateFormulario(es.map.vuds.si.service.webservice.Formulario[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Formulario
                              */
                              public void setFormulario(es.map.vuds.si.service.webservice.Formulario[] param){
                              
                                   validateFormulario(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localFormularioTracker = true;
                                          } else {
                                             localFormularioTracker = true;
                                                 
                                          }
                                      
                                      this.localFormulario=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param es.map.vuds.si.service.webservice.Formulario
                             */
                             public void addFormulario(es.map.vuds.si.service.webservice.Formulario param){
                                   if (localFormulario == null){
                                   localFormulario = new es.map.vuds.si.service.webservice.Formulario[]{};
                                   }

                            
                                 //update the setting tracker
                                localFormularioTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localFormulario);
                               list.add(param);
                               this.localFormulario =
                             (es.map.vuds.si.service.webservice.Formulario[])list.toArray(
                            new es.map.vuds.si.service.webservice.Formulario[list.size()]);

                             }
                             

                        /**
                        * field for Normativa
                        * This was an Array!
                        */

                        
                                    protected java.lang.String[] localNormativa ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNormativaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String[]
                           */
                           public  java.lang.String[] getNormativa(){
                               return localNormativa;
                           }

                           
                        


                               
                              /**
                               * validate the array for Normativa
                               */
                              protected void validateNormativa(java.lang.String[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Normativa
                              */
                              public void setNormativa(java.lang.String[] param){
                              
                                   validateNormativa(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localNormativaTracker = true;
                                          } else {
                                             localNormativaTracker = true;
                                                 
                                          }
                                      
                                      this.localNormativa=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param java.lang.String
                             */
                             public void addNormativa(java.lang.String param){
                                   if (localNormativa == null){
                                   localNormativa = new java.lang.String[]{};
                                   }

                            
                                 //update the setting tracker
                                localNormativaTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localNormativa);
                               list.add(param);
                               this.localNormativa =
                             (java.lang.String[])list.toArray(
                            new java.lang.String[list.size()]);

                             }
                             

                        /**
                        * field for Observaciones
                        */

                        
                                    protected java.lang.String localObservaciones ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localObservacionesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getObservaciones(){
                               return localObservaciones;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Observaciones
                               */
                               public void setObservaciones(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localObservacionesTracker = true;
                                       } else {
                                          localObservacionesTracker = false;
                                              
                                       }
                                   
                                            this.localObservaciones=param;
                                    

                               }
                            

                        /**
                        * field for PlazosLegales
                        */

                        
                                    protected java.lang.String localPlazosLegales ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPlazosLegalesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPlazosLegales(){
                               return localPlazosLegales;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PlazosLegales
                               */
                               public void setPlazosLegales(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPlazosLegalesTracker = true;
                                       } else {
                                          localPlazosLegalesTracker = false;
                                              
                                       }
                                   
                                            this.localPlazosLegales=param;
                                    

                               }
                            

                        /**
                        * field for RequisitosPrevios
                        * This was an Array!
                        */

                        
                                    protected java.lang.String[] localRequisitosPrevios ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRequisitosPreviosTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String[]
                           */
                           public  java.lang.String[] getRequisitosPrevios(){
                               return localRequisitosPrevios;
                           }

                           
                        


                               
                              /**
                               * validate the array for RequisitosPrevios
                               */
                              protected void validateRequisitosPrevios(java.lang.String[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param RequisitosPrevios
                              */
                              public void setRequisitosPrevios(java.lang.String[] param){
                              
                                   validateRequisitosPrevios(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localRequisitosPreviosTracker = true;
                                          } else {
                                             localRequisitosPreviosTracker = true;
                                                 
                                          }
                                      
                                      this.localRequisitosPrevios=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param java.lang.String
                             */
                             public void addRequisitosPrevios(java.lang.String param){
                                   if (localRequisitosPrevios == null){
                                   localRequisitosPrevios = new java.lang.String[]{};
                                   }

                            
                                 //update the setting tracker
                                localRequisitosPreviosTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localRequisitosPrevios);
                               list.add(param);
                               this.localRequisitosPrevios =
                             (java.lang.String[])list.toArray(
                            new java.lang.String[list.size()]);

                             }
                             

                        /**
                        * field for Tasa
                        */

                        
                                    protected es.map.vuds.si.service.webservice.Tasa localTasa ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTasaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.Tasa
                           */
                           public  es.map.vuds.si.service.webservice.Tasa getTasa(){
                               return localTasa;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tasa
                               */
                               public void setTasa(es.map.vuds.si.service.webservice.Tasa param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTasaTracker = true;
                                       } else {
                                          localTasaTracker = false;
                                              
                                       }
                                   
                                            this.localTasa=param;
                                    

                               }
                            

                        /**
                        * field for TiempoResolucion
                        */

                        
                                    protected java.lang.String localTiempoResolucion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTiempoResolucionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTiempoResolucion(){
                               return localTiempoResolucion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TiempoResolucion
                               */
                               public void setTiempoResolucion(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTiempoResolucionTracker = true;
                                       } else {
                                          localTiempoResolucionTracker = false;
                                              
                                       }
                                   
                                            this.localTiempoResolucion=param;
                                    

                               }
                            

                        /**
                        * field for TipoRegistro
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TipoRegistro localTipoRegistro ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.TipoRegistro
                           */
                           public  es.map.vuds.si.service.webservice.TipoRegistro getTipoRegistro(){
                               return localTipoRegistro;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TipoRegistro
                               */
                               public void setTipoRegistro(es.map.vuds.si.service.webservice.TipoRegistro param){
                            
                                            this.localTipoRegistro=param;
                                    

                               }
                            

                        /**
                        * field for Tipologia
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TipologiaTramite localTipologia ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.TipologiaTramite
                           */
                           public  es.map.vuds.si.service.webservice.TipologiaTramite getTipologia(){
                               return localTipologia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tipologia
                               */
                               public void setTipologia(es.map.vuds.si.service.webservice.TipologiaTramite param){
                            
                                            this.localTipologia=param;
                                    

                               }
                            

                        /**
                        * field for TramiteVuds
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TramiteVuds localTramiteVuds ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.TramiteVuds
                           */
                           public  es.map.vuds.si.service.webservice.TramiteVuds getTramiteVuds(){
                               return localTramiteVuds;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TramiteVuds
                               */
                               public void setTramiteVuds(es.map.vuds.si.service.webservice.TramiteVuds param){
                            
                                            this.localTramiteVuds=param;
                                    

                               }
                            

                        /**
                        * field for EnlaceConsulta
                        */

                        
                                    protected java.lang.String localEnlaceConsulta ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEnlaceConsultaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEnlaceConsulta(){
                               return localEnlaceConsulta;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EnlaceConsulta
                               */
                               public void setEnlaceConsulta(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEnlaceConsultaTracker = true;
                                       } else {
                                          localEnlaceConsultaTracker = false;
                                              
                                       }
                                   
                                            this.localEnlaceConsulta=param;
                                    

                               }
                            

                        /**
                        * field for AreaTramitadora
                        */

                        
                                    protected java.lang.String localAreaTramitadora ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAreaTramitadoraTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAreaTramitadora(){
                               return localAreaTramitadora;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AreaTramitadora
                               */
                               public void setAreaTramitadora(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAreaTramitadoraTracker = true;
                                       } else {
                                          localAreaTramitadoraTracker = false;
                                              
                                       }
                                   
                                            this.localAreaTramitadora=param;
                                    

                               }
                            

                        /**
                        * field for Resultado
                        * This was an Array!
                        */

                        
                                    protected java.lang.String[] localResultado ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localResultadoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String[]
                           */
                           public  java.lang.String[] getResultado(){
                               return localResultado;
                           }

                           
                        


                               
                              /**
                               * validate the array for Resultado
                               */
                              protected void validateResultado(java.lang.String[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Resultado
                              */
                              public void setResultado(java.lang.String[] param){
                              
                                   validateResultado(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localResultadoTracker = true;
                                          } else {
                                             localResultadoTracker = true;
                                                 
                                          }
                                      
                                      this.localResultado=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param java.lang.String
                             */
                             public void addResultado(java.lang.String param){
                                   if (localResultado == null){
                                   localResultado = new java.lang.String[]{};
                                   }

                            
                                 //update the setting tracker
                                localResultadoTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localResultado);
                               list.add(param);
                               this.localResultado =
                             (java.lang.String[])list.toArray(
                            new java.lang.String[list.size()]);

                             }
                             

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       Tramite.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://webservice.service.si.vuds.map.es/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tramite",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tramite",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localOrganismoCompetente==null){
                                                 throw new org.apache.axis2.databinding.ADBException("organismoCompetente cannot be null!!");
                                            }
                                           localOrganismoCompetente.serialize(new javax.xml.namespace.QName("","organismoCompetente"),
                                               factory,xmlWriter);
                                         if (localCanalTramitacionTracker){
                                            if (localCanalTramitacion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("canalTramitacion cannot be null!!");
                                            }
                                           localCanalTramitacion.serialize(new javax.xml.namespace.QName("","canalTramitacion"),
                                               factory,xmlWriter);
                                        }
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"codigoIdentificador", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"codigoIdentificador");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("codigoIdentificador");
                                    }
                                

                                          if (localCodigoIdentificador==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("codigoIdentificador cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCodigoIdentificador);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"denominacionTramite", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"denominacionTramite");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("denominacionTramite");
                                    }
                                

                                          if (localDenominacionTramite==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("denominacionTramite cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDenominacionTramite);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"descripcionTramite", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"descripcionTramite");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("descripcionTramite");
                                    }
                                

                                          if (localDescripcionTramite==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("descripcionTramite cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDescripcionTramite);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localDocumentoTracker){
                             if (localDocumento!=null) {
                                   namespace = "";
                                   boolean emptyNamespace = namespace == null || namespace.length() == 0;
                                   prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                                   for (int i = 0;i < localDocumento.length;i++){
                                        
                                            if (localDocumento[i] != null){
                                        
                                                if (!emptyNamespace) {
                                                    if (prefix == null) {
                                                        java.lang.String prefix2 = generatePrefix(namespace);

                                                        xmlWriter.writeStartElement(prefix2,"documento", namespace);
                                                        xmlWriter.writeNamespace(prefix2, namespace);
                                                        xmlWriter.setPrefix(prefix2, namespace);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace,"documento");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("documento");
                                                }

                                            
                                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDocumento[i]));
                                                    
                                                xmlWriter.writeEndElement();
                                              
                                                } else {
                                                   
                                                           // write null attribute
                                                            namespace = "";
                                                            if (! namespace.equals("")) {
                                                                prefix = xmlWriter.getPrefix(namespace);

                                                                if (prefix == null) {
                                                                    prefix = generatePrefix(namespace);

                                                                    xmlWriter.writeStartElement(prefix,"documento", namespace);
                                                                    xmlWriter.writeNamespace(prefix, namespace);
                                                                    xmlWriter.setPrefix(prefix, namespace);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace,"documento");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("documento");
                                                            }
                                                            writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                            xmlWriter.writeEndElement();
                                                       
                                                }

                                   }
                             } else {
                                 
                                         // write the null attribute
                                        // write null attribute
                                            java.lang.String namespace2 = "";
                                            if (! namespace2.equals("")) {
                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                if (prefix2 == null) {
                                                    prefix2 = generatePrefix(namespace2);

                                                    xmlWriter.writeStartElement(prefix2,"documento", namespace2);
                                                    xmlWriter.writeNamespace(prefix2, namespace2);
                                                    xmlWriter.setPrefix(prefix2, namespace2);

                                                } else {
                                                    xmlWriter.writeStartElement(namespace2,"documento");
                                                }

                                            } else {
                                                xmlWriter.writeStartElement("documento");
                                            }

                                           // write the nil attribute
                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                           xmlWriter.writeEndElement();
                                    
                             }

                        }
                                            if (localFormaIniciacion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("formaIniciacion cannot be null!!");
                                            }
                                           localFormaIniciacion.serialize(new javax.xml.namespace.QName("","formaIniciacion"),
                                               factory,xmlWriter);
                                         if (localFormularioTracker){
                                       if (localFormulario!=null){
                                            for (int i = 0;i < localFormulario.length;i++){
                                                if (localFormulario[i] != null){
                                                 localFormulario[i].serialize(new javax.xml.namespace.QName("","formulario"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                            // write null attribute
                                                            java.lang.String namespace2 = "";
                                                            if (! namespace2.equals("")) {
                                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                                if (prefix2 == null) {
                                                                    prefix2 = generatePrefix(namespace2);

                                                                    xmlWriter.writeStartElement(prefix2,"formulario", namespace2);
                                                                    xmlWriter.writeNamespace(prefix2, namespace2);
                                                                    xmlWriter.setPrefix(prefix2, namespace2);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace2,"formulario");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("formulario");
                                                            }

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                // write null attribute
                                                java.lang.String namespace2 = "";
                                                if (! namespace2.equals("")) {
                                                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                    if (prefix2 == null) {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2,"formulario", namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace2,"formulario");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("formulario");
                                                }

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 } if (localNormativaTracker){
                             if (localNormativa!=null) {
                                   namespace = "";
                                   boolean emptyNamespace = namespace == null || namespace.length() == 0;
                                   prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                                            for (int i = 0;i < localNormativa.length;i++){
                                        
                                                if (localNormativa[i] != null){
                                        
                                                if (!emptyNamespace) {
                                                    if (prefix == null) {
                                                        java.lang.String prefix2 = generatePrefix(namespace);

                                                        xmlWriter.writeStartElement(prefix2,"normativa", namespace);
                                                        xmlWriter.writeNamespace(prefix2, namespace);
                                                        xmlWriter.setPrefix(prefix2, namespace);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace,"normativa");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("normativa");
                                                }

                                            
                                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNormativa[i]));
                                                    
                                                xmlWriter.writeEndElement();
                                              
                                                } else {
                                                   
                                                            // write null attribute
                                                            namespace = "";
                                                            if (! namespace.equals("")) {
                                                                prefix = xmlWriter.getPrefix(namespace);

                                                                if (prefix == null) {
                                                                    prefix = generatePrefix(namespace);

                                                                    xmlWriter.writeStartElement(prefix,"normativa", namespace);
                                                                    xmlWriter.writeNamespace(prefix, namespace);
                                                                    xmlWriter.setPrefix(prefix, namespace);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace,"normativa");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("normativa");
                                                            }
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                         // write the null attribute
                                                // write null attribute
                                                java.lang.String namespace2 = "";
                                                if (! namespace2.equals("")) {
                                                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                    if (prefix2 == null) {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2,"normativa", namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace2,"normativa");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("normativa");
                                                }

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }

                                 } if (localObservacionesTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"observaciones", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"observaciones");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("observaciones");
                                    }
                                

                                          if (localObservaciones==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("observaciones cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localObservaciones);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPlazosLegalesTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"plazosLegales", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"plazosLegales");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("plazosLegales");
                                    }
                                

                                          if (localPlazosLegales==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("plazosLegales cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPlazosLegales);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localRequisitosPreviosTracker){
                             if (localRequisitosPrevios!=null) {
                                   namespace = "";
                                   boolean emptyNamespace = namespace == null || namespace.length() == 0;
                                   prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                                   for (int i = 0;i < localRequisitosPrevios.length;i++){
                                        
                                            if (localRequisitosPrevios[i] != null){
                                        
                                                if (!emptyNamespace) {
                                                    if (prefix == null) {
                                                        java.lang.String prefix2 = generatePrefix(namespace);

                                                        xmlWriter.writeStartElement(prefix2,"requisitosPrevios", namespace);
                                                        xmlWriter.writeNamespace(prefix2, namespace);
                                                        xmlWriter.setPrefix(prefix2, namespace);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace,"requisitosPrevios");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("requisitosPrevios");
                                                }

                                            
                                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequisitosPrevios[i]));
                                                    
                                                xmlWriter.writeEndElement();
                                              
                                                } else {
                                                   
                                                           // write null attribute
                                                            namespace = "";
                                                            if (! namespace.equals("")) {
                                                                prefix = xmlWriter.getPrefix(namespace);

                                                                if (prefix == null) {
                                                                    prefix = generatePrefix(namespace);

                                                                    xmlWriter.writeStartElement(prefix,"requisitosPrevios", namespace);
                                                                    xmlWriter.writeNamespace(prefix, namespace);
                                                                    xmlWriter.setPrefix(prefix, namespace);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace,"requisitosPrevios");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("requisitosPrevios");
                                                            }
                                                            writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                            xmlWriter.writeEndElement();
                                                       
                                                }

                                   }
                             } else {
                                 
                                         // write the null attribute
                                        // write null attribute
                                            java.lang.String namespace2 = "";
                                            if (! namespace2.equals("")) {
                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                if (prefix2 == null) {
                                                    prefix2 = generatePrefix(namespace2);

                                                    xmlWriter.writeStartElement(prefix2,"requisitosPrevios", namespace2);
                                                    xmlWriter.writeNamespace(prefix2, namespace2);
                                                    xmlWriter.setPrefix(prefix2, namespace2);

                                                } else {
                                                    xmlWriter.writeStartElement(namespace2,"requisitosPrevios");
                                                }

                                            } else {
                                                xmlWriter.writeStartElement("requisitosPrevios");
                                            }

                                           // write the nil attribute
                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                           xmlWriter.writeEndElement();
                                    
                             }

                        } if (localTasaTracker){
                                            if (localTasa==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tasa cannot be null!!");
                                            }
                                           localTasa.serialize(new javax.xml.namespace.QName("","tasa"),
                                               factory,xmlWriter);
                                        } if (localTiempoResolucionTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"tiempoResolucion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"tiempoResolucion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("tiempoResolucion");
                                    }
                                

                                          if (localTiempoResolucion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("tiempoResolucion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTiempoResolucion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                            if (localTipoRegistro==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipoRegistro cannot be null!!");
                                            }
                                           localTipoRegistro.serialize(new javax.xml.namespace.QName("","tipoRegistro"),
                                               factory,xmlWriter);
                                        
                                            if (localTipologia==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipologia cannot be null!!");
                                            }
                                           localTipologia.serialize(new javax.xml.namespace.QName("","tipologia"),
                                               factory,xmlWriter);
                                        
                                            if (localTramiteVuds==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tramiteVuds cannot be null!!");
                                            }
                                           localTramiteVuds.serialize(new javax.xml.namespace.QName("","tramiteVuds"),
                                               factory,xmlWriter);
                                         if (localEnlaceConsultaTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"enlaceConsulta", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"enlaceConsulta");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("enlaceConsulta");
                                    }
                                

                                          if (localEnlaceConsulta==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("enlaceConsulta cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEnlaceConsulta);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAreaTramitadoraTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"areaTramitadora", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"areaTramitadora");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("areaTramitadora");
                                    }
                                

                                          if (localAreaTramitadora==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("areaTramitadora cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAreaTramitadora);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localResultadoTracker){
                             if (localResultado!=null) {
                                   namespace = "";
                                   boolean emptyNamespace = namespace == null || namespace.length() == 0;
                                   prefix =  emptyNamespace ? null : xmlWriter.getPrefix(namespace);
                                   for (int i = 0;i < localResultado.length;i++){
                                        
                                            if (localResultado[i] != null){
                                        
                                                if (!emptyNamespace) {
                                                    if (prefix == null) {
                                                        java.lang.String prefix2 = generatePrefix(namespace);

                                                        xmlWriter.writeStartElement(prefix2,"resultado", namespace);
                                                        xmlWriter.writeNamespace(prefix2, namespace);
                                                        xmlWriter.setPrefix(prefix2, namespace);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace,"resultado");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("resultado");
                                                }

                                            
                                                        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultado[i]));
                                                    
                                                xmlWriter.writeEndElement();
                                              
                                                } else {
                                                   
                                                           // write null attribute
                                                            namespace = "";
                                                            if (! namespace.equals("")) {
                                                                prefix = xmlWriter.getPrefix(namespace);

                                                                if (prefix == null) {
                                                                    prefix = generatePrefix(namespace);

                                                                    xmlWriter.writeStartElement(prefix,"resultado", namespace);
                                                                    xmlWriter.writeNamespace(prefix, namespace);
                                                                    xmlWriter.setPrefix(prefix, namespace);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace,"resultado");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("resultado");
                                                            }
                                                            writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                            xmlWriter.writeEndElement();
                                                       
                                                }

                                   }
                             } else {
                                 
                                         // write the null attribute
                                        // write null attribute
                                            java.lang.String namespace2 = "";
                                            if (! namespace2.equals("")) {
                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                if (prefix2 == null) {
                                                    prefix2 = generatePrefix(namespace2);

                                                    xmlWriter.writeStartElement(prefix2,"resultado", namespace2);
                                                    xmlWriter.writeNamespace(prefix2, namespace2);
                                                    xmlWriter.setPrefix(prefix2, namespace2);

                                                } else {
                                                    xmlWriter.writeStartElement(namespace2,"resultado");
                                                }

                                            } else {
                                                xmlWriter.writeStartElement("resultado");
                                            }

                                           // write the nil attribute
                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                           xmlWriter.writeEndElement();
                                    
                             }

                        }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "organismoCompetente"));
                            
                            
                                    if (localOrganismoCompetente==null){
                                         throw new org.apache.axis2.databinding.ADBException("organismoCompetente cannot be null!!");
                                    }
                                    elementList.add(localOrganismoCompetente);
                                 if (localCanalTramitacionTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "canalTramitacion"));
                            
                            
                                    if (localCanalTramitacion==null){
                                         throw new org.apache.axis2.databinding.ADBException("canalTramitacion cannot be null!!");
                                    }
                                    elementList.add(localCanalTramitacion);
                                }
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "codigoIdentificador"));
                                 
                                        if (localCodigoIdentificador != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodigoIdentificador));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("codigoIdentificador cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "denominacionTramite"));
                                 
                                        if (localDenominacionTramite != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDenominacionTramite));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("denominacionTramite cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "descripcionTramite"));
                                 
                                        if (localDescripcionTramite != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescripcionTramite));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("descripcionTramite cannot be null!!");
                                        }
                                     if (localDocumentoTracker){
                            if (localDocumento!=null){
                                  for (int i = 0;i < localDocumento.length;i++){
                                      
                                         if (localDocumento[i] != null){
                                          elementList.add(new javax.xml.namespace.QName("",
                                                                              "documento"));
                                          elementList.add(
                                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDocumento[i]));
                                          } else {
                                             
                                                    elementList.add(new javax.xml.namespace.QName("",
                                                                              "documento"));
                                                    elementList.add(null);
                                                
                                          }
                                      

                                  }
                            } else {
                              
                                    elementList.add(new javax.xml.namespace.QName("",
                                                                              "documento"));
                                    elementList.add(null);
                                
                            }

                        }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "formaIniciacion"));
                            
                            
                                    if (localFormaIniciacion==null){
                                         throw new org.apache.axis2.databinding.ADBException("formaIniciacion cannot be null!!");
                                    }
                                    elementList.add(localFormaIniciacion);
                                 if (localFormularioTracker){
                             if (localFormulario!=null) {
                                 for (int i = 0;i < localFormulario.length;i++){

                                    if (localFormulario[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "formulario"));
                                         elementList.add(localFormulario[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("",
                                                                          "formulario"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("",
                                                                          "formulario"));
                                        elementList.add(localFormulario);
                                    
                             }

                        } if (localNormativaTracker){
                            if (localNormativa!=null){
                                 for (int i = 0;i < localNormativa.length;i++){

                                    if (localNormativa[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "normativa"));
                                          elementList.add(
                                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNormativa[i]));
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("",
                                                                          "normativa"));
                                                elementList.add(null);
                                            
                                    }


                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("",
                                                                          "normativa"));
                                    elementList.add(null);
                                    
                             }

                        } if (localObservacionesTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "observaciones"));
                                 
                                        if (localObservaciones != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localObservaciones));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("observaciones cannot be null!!");
                                        }
                                    } if (localPlazosLegalesTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "plazosLegales"));
                                 
                                        if (localPlazosLegales != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPlazosLegales));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("plazosLegales cannot be null!!");
                                        }
                                    } if (localRequisitosPreviosTracker){
                            if (localRequisitosPrevios!=null){
                                  for (int i = 0;i < localRequisitosPrevios.length;i++){
                                      
                                         if (localRequisitosPrevios[i] != null){
                                          elementList.add(new javax.xml.namespace.QName("",
                                                                              "requisitosPrevios"));
                                          elementList.add(
                                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRequisitosPrevios[i]));
                                          } else {
                                             
                                                    elementList.add(new javax.xml.namespace.QName("",
                                                                              "requisitosPrevios"));
                                                    elementList.add(null);
                                                
                                          }
                                      

                                  }
                            } else {
                              
                                    elementList.add(new javax.xml.namespace.QName("",
                                                                              "requisitosPrevios"));
                                    elementList.add(null);
                                
                            }

                        } if (localTasaTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tasa"));
                            
                            
                                    if (localTasa==null){
                                         throw new org.apache.axis2.databinding.ADBException("tasa cannot be null!!");
                                    }
                                    elementList.add(localTasa);
                                } if (localTiempoResolucionTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "tiempoResolucion"));
                                 
                                        if (localTiempoResolucion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTiempoResolucion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("tiempoResolucion cannot be null!!");
                                        }
                                    }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tipoRegistro"));
                            
                            
                                    if (localTipoRegistro==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipoRegistro cannot be null!!");
                                    }
                                    elementList.add(localTipoRegistro);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tipologia"));
                            
                            
                                    if (localTipologia==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipologia cannot be null!!");
                                    }
                                    elementList.add(localTipologia);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tramiteVuds"));
                            
                            
                                    if (localTramiteVuds==null){
                                         throw new org.apache.axis2.databinding.ADBException("tramiteVuds cannot be null!!");
                                    }
                                    elementList.add(localTramiteVuds);
                                 if (localEnlaceConsultaTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "enlaceConsulta"));
                                 
                                        if (localEnlaceConsulta != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnlaceConsulta));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("enlaceConsulta cannot be null!!");
                                        }
                                    } if (localAreaTramitadoraTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "areaTramitadora"));
                                 
                                        if (localAreaTramitadora != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAreaTramitadora));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("areaTramitadora cannot be null!!");
                                        }
                                    } if (localResultadoTracker){
                            if (localResultado!=null){
                                  for (int i = 0;i < localResultado.length;i++){
                                      
                                         if (localResultado[i] != null){
                                          elementList.add(new javax.xml.namespace.QName("",
                                                                              "resultado"));
                                          elementList.add(
                                          org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultado[i]));
                                          } else {
                                             
                                                    elementList.add(new javax.xml.namespace.QName("",
                                                                              "resultado"));
                                                    elementList.add(null);
                                                
                                          }
                                      

                                  }
                            } else {
                              
                                    elementList.add(new javax.xml.namespace.QName("",
                                                                              "resultado"));
                                    elementList.add(null);
                                
                            }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Tramite parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Tramite object =
                new Tramite();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"tramite".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Tramite)es.map.vuds.si.service.webservice.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list6 = new java.util.ArrayList();
                    
                        java.util.ArrayList list8 = new java.util.ArrayList();
                    
                        java.util.ArrayList list9 = new java.util.ArrayList();
                    
                        java.util.ArrayList list12 = new java.util.ArrayList();
                    
                        java.util.ArrayList list20 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","organismoCompetente").equals(reader.getName())){
                                
                                                object.setOrganismoCompetente(es.map.vuds.si.service.webservice.OrganismoCompetente.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","canalTramitacion").equals(reader.getName())){
                                
                                                object.setCanalTramitacion(es.map.vuds.si.service.webservice.CanalTramitacion.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","codigoIdentificador").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCodigoIdentificador(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","denominacionTramite").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDenominacionTramite(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","descripcionTramite").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDescripcionTramite(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","documento").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                              nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                              if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                  list6.add(null);
                                                       
                                                  reader.next();
                                              } else {
                                            list6.add(reader.getElementText());
                                            }
                                            //loop until we find a start element that is not part of this array
                                            boolean loopDone6 = false;
                                            while(!loopDone6){
                                                // Ensure we are at the EndElement
                                                while (!reader.isEndElement()){
                                                    reader.next();
                                                }
                                                // Step out of this element
                                                reader.next();
                                                // Step to next element event.
                                                while (!reader.isStartElement() && !reader.isEndElement())
                                                    reader.next();
                                                if (reader.isEndElement()){
                                                    //two continuous end elements means we are exiting the xml structure
                                                    loopDone6 = true;
                                                } else {
                                                    if (new javax.xml.namespace.QName("","documento").equals(reader.getName())){
                                                         
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list6.add(null);
                                                                   
                                                              reader.next();
                                                          } else {
                                                        list6.add(reader.getElementText());
                                                        }
                                                    }else{
                                                        loopDone6 = true;
                                                    }
                                                }
                                            }
                                            // call the converter utility  to convert and set the array
                                            
                                                    object.setDocumento((java.lang.String[])
                                                        list6.toArray(new java.lang.String[list6.size()]));
                                                
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","formaIniciacion").equals(reader.getName())){
                                
                                                object.setFormaIniciacion(es.map.vuds.si.service.webservice.FormaIniciacion.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","formulario").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list8.add(null);
                                                              reader.next();
                                                          } else {
                                                        list8.add(es.map.vuds.si.service.webservice.Formulario.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone8 = false;
                                                        while(!loopDone8){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone8 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","formulario").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list8.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list8.add(es.map.vuds.si.service.webservice.Formulario.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone8 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setFormulario((es.map.vuds.si.service.webservice.Formulario[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                es.map.vuds.si.service.webservice.Formulario.class,
                                                                list8));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","normativa").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list9.add(null);
                                                       
                                                              reader.next();
                                                          } else {
                                            list9.add(reader.getElementText());
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone9 = false;
                                                        while(!loopDone9){
                                                // Ensure we are at the EndElement
                                                while (!reader.isEndElement()){
                                                                reader.next();
                                                }
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone9 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","normativa").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list9.add(null);
                                                                   
                                                                          reader.next();
                                                                      } else {
                                                        list9.add(reader.getElementText());
                                                                        }
                                                                }else{
                                                                    loopDone9 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                    object.setNormativa((java.lang.String[])
                                                        list9.toArray(new java.lang.String[list9.size()]));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","observaciones").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setObservaciones(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","plazosLegales").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPlazosLegales(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","requisitosPrevios").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                              nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                              if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                  list12.add(null);
                                                       
                                                  reader.next();
                                              } else {
                                            list12.add(reader.getElementText());
                                            }
                                            //loop until we find a start element that is not part of this array
                                            boolean loopDone12 = false;
                                            while(!loopDone12){
                                                // Ensure we are at the EndElement
                                                while (!reader.isEndElement()){
                                                    reader.next();
                                                }
                                                // Step out of this element
                                                reader.next();
                                                // Step to next element event.
                                                while (!reader.isStartElement() && !reader.isEndElement())
                                                    reader.next();
                                                if (reader.isEndElement()){
                                                    //two continuous end elements means we are exiting the xml structure
                                                    loopDone12 = true;
                                                } else {
                                                    if (new javax.xml.namespace.QName("","requisitosPrevios").equals(reader.getName())){
                                                         
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list12.add(null);
                                                                   
                                                              reader.next();
                                                          } else {
                                                        list12.add(reader.getElementText());
                                                        }
                                                    }else{
                                                        loopDone12 = true;
                                                    }
                                                }
                                            }
                                            // call the converter utility  to convert and set the array
                                            
                                                    object.setRequisitosPrevios((java.lang.String[])
                                                        list12.toArray(new java.lang.String[list12.size()]));
                                                
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tasa").equals(reader.getName())){
                                
                                                object.setTasa(es.map.vuds.si.service.webservice.Tasa.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tiempoResolucion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTiempoResolucion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tipoRegistro").equals(reader.getName())){
                                
                                                object.setTipoRegistro(es.map.vuds.si.service.webservice.TipoRegistro.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tipologia").equals(reader.getName())){
                                
                                                object.setTipologia(es.map.vuds.si.service.webservice.TipologiaTramite.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tramiteVuds").equals(reader.getName())){
                                
                                                object.setTramiteVuds(es.map.vuds.si.service.webservice.TramiteVuds.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","enlaceConsulta").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEnlaceConsulta(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","areaTramitadora").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAreaTramitadora(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","resultado").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                              nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                              if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                  list20.add(null);
                                                       
                                                  reader.next();
                                              } else {
                                            list20.add(reader.getElementText());
                                            }
                                            //loop until we find a start element that is not part of this array
                                            boolean loopDone20 = false;
                                            while(!loopDone20){
                                                // Ensure we are at the EndElement
                                                while (!reader.isEndElement()){
                                                    reader.next();
                                                }
                                                // Step out of this element
                                                reader.next();
                                                // Step to next element event.
                                                while (!reader.isStartElement() && !reader.isEndElement())
                                                    reader.next();
                                                if (reader.isEndElement()){
                                                    //two continuous end elements means we are exiting the xml structure
                                                    loopDone20 = true;
                                                } else {
                                                    if (new javax.xml.namespace.QName("","resultado").equals(reader.getName())){
                                                         
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list20.add(null);
                                                                   
                                                              reader.next();
                                                          } else {
                                                        list20.add(reader.getElementText());
                                                        }
                                                    }else{
                                                        loopDone20 = true;
                                                    }
                                                }
                                            }
                                            // call the converter utility  to convert and set the array
                                            
                                                    object.setResultado((java.lang.String[])
                                                        list20.toArray(new java.lang.String[list20.size()]));
                                                
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          