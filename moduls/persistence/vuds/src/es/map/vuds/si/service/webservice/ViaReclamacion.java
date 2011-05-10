
/**
 * ViaReclamacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package es.map.vuds.si.service.webservice;
            

            /**
            *  ViaReclamacion bean class
            */
        
        public  class ViaReclamacion
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = viaReclamacion
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
                        * field for Descripcion
                        */

                        
                                    protected java.lang.String localDescripcion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDescripcion(){
                               return localDescripcion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Descripcion
                               */
                               public void setDescripcion(java.lang.String param){
                            
                                            this.localDescripcion=param;
                                    

                               }
                            

                        /**
                        * field for TipoSolicitante
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TipoSolicitante localTipoSolicitante ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.TipoSolicitante
                           */
                           public  es.map.vuds.si.service.webservice.TipoSolicitante getTipoSolicitante(){
                               return localTipoSolicitante;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TipoSolicitante
                               */
                               public void setTipoSolicitante(es.map.vuds.si.service.webservice.TipoSolicitante param){
                            
                                            this.localTipoSolicitante=param;
                                    

                               }
                            

                        /**
                        * field for TipoViaReclamacion
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TipoViaReclamacion localTipoViaReclamacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.TipoViaReclamacion
                           */
                           public  es.map.vuds.si.service.webservice.TipoViaReclamacion getTipoViaReclamacion(){
                               return localTipoViaReclamacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TipoViaReclamacion
                               */
                               public void setTipoViaReclamacion(es.map.vuds.si.service.webservice.TipoViaReclamacion param){
                            
                                            this.localTipoViaReclamacion=param;
                                    

                               }
                            

                        /**
                        * field for Plazo
                        */

                        
                                    protected java.lang.String localPlazo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPlazoTracker = false ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPlazo(){
                               return localPlazo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Plazo
                               */
                               public void setPlazo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPlazoTracker = true;
                                       } else {
                                          localPlazoTracker = false;
                            
                                       }
                                   
                                            this.localPlazo=param;
                                    

                               }
                            

                        /**
                        * field for Donde
                        */

                        
                                    protected java.lang.String localDonde ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDondeTracker = false ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDonde(){
                               return localDonde;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Donde
                               */
                               public void setDonde(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDondeTracker = true;
                                       } else {
                                          localDondeTracker = false;
                                              
                                       }
                                   
                                            this.localDonde=param;
                                    

                               }
                            

                        /**
                        * field for Documentacion
                        */

                        
                                    protected java.lang.String localDocumentacion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDocumentacionTracker = false ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDocumentacion(){
                               return localDocumentacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Documentacion
                               */
                               public void setDocumentacion(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDocumentacionTracker = true;
                                       } else {
                                          localDocumentacionTracker = false;
                                              
                                       }
                            
                                            this.localDocumentacion=param;
                                    

                               }
                            

                        /**
                        * field for FormaIniciacion
                        */

                        
                                    protected java.lang.String localFormaIniciacion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFormaIniciacionTracker = false ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFormaIniciacion(){
                               return localFormaIniciacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FormaIniciacion
                               */
                               public void setFormaIniciacion(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localFormaIniciacionTracker = true;
                                       } else {
                                          localFormaIniciacionTracker = false;
                                              
                                       }
                                   
                                            this.localFormaIniciacion=param;
                                    

                               }
                            

                        /**
                        * field for OrganismoTramita
                        */

                        
                                    protected java.lang.String localOrganismoTramita ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOrganismoTramitaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOrganismoTramita(){
                               return localOrganismoTramita;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OrganismoTramita
                               */
                               public void setOrganismoTramita(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOrganismoTramitaTracker = true;
                                       } else {
                                          localOrganismoTramitaTracker = false;
                                              
                                       }
                                   
                                            this.localOrganismoTramita=param;
                                    

                               }
                            

                        /**
                        * field for Notificaciones
                        */

                        
                                    protected java.lang.String localNotificaciones ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNotificacionesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNotificaciones(){
                               return localNotificaciones;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Notificaciones
                               */
                               public void setNotificaciones(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localNotificacionesTracker = true;
                                       } else {
                                          localNotificacionesTracker = false;
                                              
                                       }
                                   
                                            this.localNotificaciones=param;
                                    

                               }
                            

                        /**
                        * field for Recursos
                        */

                        
                                    protected java.lang.String localRecursos ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRecursosTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRecursos(){
                               return localRecursos;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Recursos
                               */
                               public void setRecursos(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localRecursosTracker = true;
                                       } else {
                                          localRecursosTracker = false;
                                              
                                       }
                                   
                                            this.localRecursos=param;
                                    

                               }
                            

                        /**
                        * field for OtrosDatosInteres
                        */

                        
                                    protected java.lang.String localOtrosDatosInteres ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOtrosDatosInteresTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOtrosDatosInteres(){
                               return localOtrosDatosInteres;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OtrosDatosInteres
                               */
                               public void setOtrosDatosInteres(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOtrosDatosInteresTracker = true;
                                       } else {
                                          localOtrosDatosInteresTracker = false;
                                              
                                       }
                                   
                                            this.localOtrosDatosInteres=param;
                                    

                               }
                            

                        /**
                        * field for Nombre
                        */

                        
                                    protected java.lang.String localNombre ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNombreTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNombre(){
                               return localNombre;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nombre
                               */
                               public void setNombre(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localNombreTracker = true;
                                       } else {
                                          localNombreTracker = false;
                                              
                                       }
                                   
                                            this.localNombre=param;
                                    

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
                       ViaReclamacion.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":viaReclamacion",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "viaReclamacion",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"descripcion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"descripcion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("descripcion");
                                    }
                                

                                          if (localDescripcion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("descripcion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDescripcion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localTipoSolicitante==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipoSolicitante cannot be null!!");
                                            }
                                           localTipoSolicitante.serialize(new javax.xml.namespace.QName("","tipoSolicitante"),
                                               factory,xmlWriter);
                                        
                                            if (localTipoViaReclamacion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipoViaReclamacion cannot be null!!");
                                            }
                                           localTipoViaReclamacion.serialize(new javax.xml.namespace.QName("","tipoViaReclamacion"),
                                               factory,xmlWriter);
                                         if (localPlazoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"plazo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"plazo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("plazo");
                                    }
                                

                                          if (localPlazo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("plazo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPlazo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDondeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"donde", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"donde");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("donde");
                                    }
                                

                                          if (localDonde==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("donde cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDonde);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDocumentacionTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"documentacion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"documentacion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("documentacion");
                                    }
                                

                                          if (localDocumentacion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("documentacion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDocumentacion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFormaIniciacionTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"formaIniciacion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"formaIniciacion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("formaIniciacion");
                                    }
                                

                                          if (localFormaIniciacion==null){
                                              // write the nil attribute

                                                     throw new org.apache.axis2.databinding.ADBException("formaIniciacion cannot be null!!");

                                          }else{
        

                                                   xmlWriter.writeCharacters(localFormaIniciacion);
                                            
                 }
            
                                   xmlWriter.writeEndElement();
                             } if (localOrganismoTramitaTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"organismoTramita", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"organismoTramita");
       }

                                    } else {
                                        xmlWriter.writeStartElement("organismoTramita");
         }

            
                                          if (localOrganismoTramita==null){
                                              // write the nil attribute
                
                                                     throw new org.apache.axis2.databinding.ADBException("organismoTramita cannot be null!!");

                                          }else{

                
                                                   xmlWriter.writeCharacters(localOrganismoTramita);

                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNotificacionesTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                            if (prefix == null) {
                                prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"notificaciones", namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"notificaciones");
                        }

                    } else {
                                        xmlWriter.writeStartElement("notificaciones");
                    }
                
               
                                          if (localNotificaciones==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("notificaciones cannot be null!!");
                                                  
                                          }else{


                                                   xmlWriter.writeCharacters(localNotificaciones);
               
                   }
               
                                   xmlWriter.writeEndElement();
                             } if (localRecursosTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"recursos", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"recursos");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("recursos");
                                    }
                                

                                          if (localRecursos==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("recursos cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRecursos);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localOtrosDatosInteresTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"otrosDatosInteres", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"otrosDatosInteres");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("otrosDatosInteres");
                                    }
                                

                                          if (localOtrosDatosInteres==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("otrosDatosInteres cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOtrosDatosInteres);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNombreTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);
                             
                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"nombre", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"nombre");
                                            }
                                        
                                    } else {
                                        xmlWriter.writeStartElement("nombre");
                                            }
                                        

                                          if (localNombre==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("nombre cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNombre);
                                            
                                            }
                                        
                    xmlWriter.writeEndElement();
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
                                                                      "descripcion"));
                                 
                                        if (localDescripcion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescripcion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("descripcion cannot be null!!");
                                        }
                                    
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tipoSolicitante"));
                            
                            
                                    if (localTipoSolicitante==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipoSolicitante cannot be null!!");
                                    }
                                    elementList.add(localTipoSolicitante);
                                
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "tipoViaReclamacion"));
                                 
                                    
                                    if (localTipoViaReclamacion==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipoViaReclamacion cannot be null!!");
                                    }
                                    elementList.add(localTipoViaReclamacion);
                                 if (localPlazoTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "plazo"));
                            
                                        if (localPlazo != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPlazo));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("plazo cannot be null!!");
                                        }
                                    } if (localDondeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "donde"));
                            
                                        if (localDonde != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDonde));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("donde cannot be null!!");
                                    }
                                    } if (localDocumentacionTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "documentacion"));
                                
                                        if (localDocumentacion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDocumentacion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("documentacion cannot be null!!");
                                        }
                                    } if (localFormaIniciacionTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "formaIniciacion"));
                            
                                        if (localFormaIniciacion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFormaIniciacion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("formaIniciacion cannot be null!!");
                                        }
                                    } if (localOrganismoTramitaTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "organismoTramita"));
                            
                                        if (localOrganismoTramita != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrganismoTramita));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("organismoTramita cannot be null!!");
                                    }
                                    } if (localNotificacionesTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "notificaciones"));
                                
                                        if (localNotificaciones != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNotificaciones));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("notificaciones cannot be null!!");
                                        }
                                    } if (localRecursosTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "recursos"));
                            
                                        if (localRecursos != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecursos));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("recursos cannot be null!!");
                                        }
                                    } if (localOtrosDatosInteresTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "otrosDatosInteres"));
                            
                                        if (localOtrosDatosInteres != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOtrosDatosInteres));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("otrosDatosInteres cannot be null!!");
                                    }
                                    } if (localNombreTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "nombre"));
                                
                                        if (localNombre != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNombre));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("nombre cannot be null!!");
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
        public static ViaReclamacion parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ViaReclamacion object =
                new ViaReclamacion();

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
                    
                            if (!"viaReclamacion".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ViaReclamacion)es.map.vuds.si.service.webservice.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","descripcion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDescripcion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tipoSolicitante").equals(reader.getName())){
                                
                                                object.setTipoSolicitante(es.map.vuds.si.service.webservice.TipoSolicitante.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tipoViaReclamacion").equals(reader.getName())){
                                    
                                                object.setTipoViaReclamacion(es.map.vuds.si.service.webservice.TipoViaReclamacion.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","plazo").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                
                                              object.setPlazo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","donde").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDonde(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","documentacion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDocumentacion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","formaIniciacion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                
                                              object.setFormaIniciacion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","organismoTramita").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOrganismoTramita(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","notificaciones").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNotificaciones(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","recursos").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRecursos(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","otrosDatosInteres").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOtrosDatosInteres(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","nombre").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNombre(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
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
           
          