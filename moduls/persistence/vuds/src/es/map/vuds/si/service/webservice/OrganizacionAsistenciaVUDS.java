
/**
 * OrganizacionAsistenciaVUDS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package es.map.vuds.si.service.webservice;
            

            /**
            *  OrganizacionAsistenciaVUDS bean class
            */
        
        public  class OrganizacionAsistenciaVUDS
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = organizacionAsistenciaVUDS
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
                        * field for IdOrganizacion
                        */

                        
                                    protected long localIdOrganizacion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdOrganizacionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getIdOrganizacion(){
                               return localIdOrganizacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IdOrganizacion
                               */
                               public void setIdOrganizacion(long param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (param==java.lang.Long.MIN_VALUE) {
                                           localIdOrganizacionTracker = false;
                                              
                                       } else {
                                          localIdOrganizacionTracker = true;
                                       }
                                   
                                            this.localIdOrganizacion=param;
                                    

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
                        * field for Tipologia
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TipologiaOrganizaciones localTipologia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTipologiaTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.TipologiaOrganizaciones
                           */
                           public  es.map.vuds.si.service.webservice.TipologiaOrganizaciones getTipologia(){
                               return localTipologia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Tipologia
                               */
                               public void setTipologia(es.map.vuds.si.service.webservice.TipologiaOrganizaciones param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTipologiaTracker = true;
                                       } else {
                                          localTipologiaTracker = false;
                                              
                                       }
                                   
                                            this.localTipologia=param;
                                    

                               }
                            

                        /**
                        * field for Ambito
                        */

                        
                                    protected es.map.vuds.si.service.webservice.AmbitoEjercicio localAmbito ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAmbitoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.AmbitoEjercicio
                           */
                           public  es.map.vuds.si.service.webservice.AmbitoEjercicio getAmbito(){
                               return localAmbito;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Ambito
                               */
                               public void setAmbito(es.map.vuds.si.service.webservice.AmbitoEjercicio param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAmbitoTracker = true;
                                       } else {
                                          localAmbitoTracker = false;
                                              
                                       }
                                   
                                            this.localAmbito=param;
                                    

                               }
                            

                        /**
                        * field for TipoSolicitante
                        */

                        
                                    protected es.map.vuds.si.service.webservice.TipoSolicitante localTipoSolicitante ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTipoSolicitanteTracker = false ;
                           

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
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTipoSolicitanteTracker = true;
                                       } else {
                                          localTipoSolicitanteTracker = false;
                                              
                                       }
                                   
                                            this.localTipoSolicitante=param;
                                    

                               }
                            

                        /**
                        * field for EnlaceWeb
                        */

                        
                                    protected java.lang.String localEnlaceWeb ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEnlaceWebTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEnlaceWeb(){
                               return localEnlaceWeb;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EnlaceWeb
                               */
                               public void setEnlaceWeb(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEnlaceWebTracker = true;
                                       } else {
                                          localEnlaceWebTracker = false;
                                              
                                       }
                                   
                                            this.localEnlaceWeb=param;
                                    

                               }
                            

                        /**
                        * field for Telefono
                        */

                        
                                    protected java.lang.String localTelefono ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTelefonoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTelefono(){
                               return localTelefono;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Telefono
                               */
                               public void setTelefono(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTelefonoTracker = true;
                                       } else {
                                          localTelefonoTracker = false;
                                              
                                       }
                                   
                                            this.localTelefono=param;
                                    

                               }
                            

                        /**
                        * field for DescripcionOrganizacion
                        */

                        
                                    protected java.lang.String localDescripcionOrganizacion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDescripcionOrganizacionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDescripcionOrganizacion(){
                               return localDescripcionOrganizacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DescripcionOrganizacion
                               */
                               public void setDescripcionOrganizacion(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDescripcionOrganizacionTracker = true;
                                       } else {
                                          localDescripcionOrganizacionTracker = false;
                                              
                                       }
                                   
                                            this.localDescripcionOrganizacion=param;
                                    

                               }
                            

                        /**
                        * field for Localizacion
                        */

                        
                                    protected es.map.vuds.si.service.webservice.Localizacion localLocalizacion ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLocalizacionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return es.map.vuds.si.service.webservice.Localizacion
                           */
                           public  es.map.vuds.si.service.webservice.Localizacion getLocalizacion(){
                               return localLocalizacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Localizacion
                               */
                               public void setLocalizacion(es.map.vuds.si.service.webservice.Localizacion param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localLocalizacionTracker = true;
                                       } else {
                                          localLocalizacionTracker = false;
                                              
                                       }
                                   
                                            this.localLocalizacion=param;
                                    

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
                       OrganizacionAsistenciaVUDS.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":organizacionAsistenciaVUDS",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "organizacionAsistenciaVUDS",
                           xmlWriter);
                   }

               
                   }
                if (localIdOrganizacionTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"idOrganizacion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"idOrganizacion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("idOrganizacion");
                                    }
                                
                                               if (localIdOrganizacion==java.lang.Long.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("idOrganizacion cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdOrganizacion));
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
                             } if (localTipologiaTracker){
                                            if (localTipologia==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipologia cannot be null!!");
                                            }
                                           localTipologia.serialize(new javax.xml.namespace.QName("","tipologia"),
                                               factory,xmlWriter);
                                        } if (localAmbitoTracker){
                                            if (localAmbito==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ambito cannot be null!!");
                                            }
                                           localAmbito.serialize(new javax.xml.namespace.QName("","ambito"),
                                               factory,xmlWriter);
                                        } if (localTipoSolicitanteTracker){
                                            if (localTipoSolicitante==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipoSolicitante cannot be null!!");
                                            }
                                           localTipoSolicitante.serialize(new javax.xml.namespace.QName("","tipoSolicitante"),
                                               factory,xmlWriter);
                                        } if (localEnlaceWebTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"enlaceWeb", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"enlaceWeb");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("enlaceWeb");
                                    }
                                

                                          if (localEnlaceWeb==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("enlaceWeb cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEnlaceWeb);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTelefonoTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"telefono", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"telefono");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("telefono");
                                    }
                                

                                          if (localTelefono==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("telefono cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTelefono);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDescripcionOrganizacionTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"descripcionOrganizacion", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"descripcionOrganizacion");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("descripcionOrganizacion");
                                    }
                                

                                          if (localDescripcionOrganizacion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("descripcionOrganizacion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDescripcionOrganizacion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLocalizacionTracker){
                                            if (localLocalizacion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("localizacion cannot be null!!");
                                            }
                                           localLocalizacion.serialize(new javax.xml.namespace.QName("","localizacion"),
                                               factory,xmlWriter);
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

                 if (localIdOrganizacionTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "idOrganizacion"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdOrganizacion));
                            } if (localNombreTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "nombre"));
                                 
                                        if (localNombre != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNombre));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("nombre cannot be null!!");
                                        }
                                    } if (localTipologiaTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tipologia"));
                            
                            
                                    if (localTipologia==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipologia cannot be null!!");
                                    }
                                    elementList.add(localTipologia);
                                } if (localAmbitoTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ambito"));
                            
                            
                                    if (localAmbito==null){
                                         throw new org.apache.axis2.databinding.ADBException("ambito cannot be null!!");
                                    }
                                    elementList.add(localAmbito);
                                } if (localTipoSolicitanteTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "tipoSolicitante"));
                            
                            
                                    if (localTipoSolicitante==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipoSolicitante cannot be null!!");
                                    }
                                    elementList.add(localTipoSolicitante);
                                } if (localEnlaceWebTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "enlaceWeb"));
                                 
                                        if (localEnlaceWeb != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnlaceWeb));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("enlaceWeb cannot be null!!");
                                        }
                                    } if (localTelefonoTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "telefono"));
                                 
                                        if (localTelefono != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTelefono));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("telefono cannot be null!!");
                                        }
                                    } if (localDescripcionOrganizacionTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "descripcionOrganizacion"));
                                 
                                        if (localDescripcionOrganizacion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescripcionOrganizacion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("descripcionOrganizacion cannot be null!!");
                                        }
                                    } if (localLocalizacionTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "localizacion"));
                            
                            
                                    if (localLocalizacion==null){
                                         throw new org.apache.axis2.databinding.ADBException("localizacion cannot be null!!");
                                    }
                                    elementList.add(localLocalizacion);
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
        public static OrganizacionAsistenciaVUDS parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            OrganizacionAsistenciaVUDS object =
                new OrganizacionAsistenciaVUDS();

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
                    
                            if (!"organizacionAsistenciaVUDS".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (OrganizacionAsistenciaVUDS)es.map.vuds.si.service.webservice.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","idOrganizacion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdOrganizacion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setIdOrganizacion(java.lang.Long.MIN_VALUE);
                                           
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
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tipologia").equals(reader.getName())){
                                
                                                object.setTipologia(es.map.vuds.si.service.webservice.TipologiaOrganizaciones.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ambito").equals(reader.getName())){
                                
                                                object.setAmbito(es.map.vuds.si.service.webservice.AmbitoEjercicio.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tipoSolicitante").equals(reader.getName())){
                                
                                                object.setTipoSolicitante(es.map.vuds.si.service.webservice.TipoSolicitante.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","enlaceWeb").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEnlaceWeb(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","telefono").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTelefono(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","descripcionOrganizacion").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDescripcionOrganizacion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","localizacion").equals(reader.getName())){
                                
                                                object.setLocalizacion(es.map.vuds.si.service.webservice.Localizacion.Factory.parse(reader));
                                              
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
           
          