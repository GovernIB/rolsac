

/**
 * CCAAWebServiceServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package org.ibit.rol.sac.integracion.ws;

import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub;
import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramite;

    /*
     *  CCAAWebServiceServiceTest Junit test case
    */

    public class CCAAWebServiceServiceTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testgetTramite() throws java.lang.Exception{

        org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub stub =
                    new org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub();//the default implementation should point to the right endpoint

           org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramite getTramite2=
                                                        (org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramite)getTestObject(org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramite.class);
                    // TODO : Fill in the getTramite2 here
                
                        assertNotNull(stub.getTramite(
                        getTramite2));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    