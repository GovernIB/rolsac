

/**
 * CCAAWebServiceServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 * 
 * u92770 - adaptació
 *  
 */

package org.ibit.rol.sac.integracion.ws.vuds; 

import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub;
import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramite;
import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramiteResponse;
import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.InfoDS_Input;
import org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.Tramite;


    public class CCAAWebServiceServiceTest extends junit.framework.TestCase{

    	String endpoint = "http://epreinf45:8080/sacws/services/CCAAWebService";
     

    	public  void testgetTramite() throws java.lang.Exception{

        	CCAAWebServiceServiceStub stub =
                    new CCAAWebServiceServiceStub(endpoint);

        	CCAAWebServiceServiceStub.GetTramite input =
                             (org.ibit.rol.sac.integracion.ws.vuds.CCAAWebServiceServiceStub.GetTramite)getTestObject(GetTramite.class);
        	
        	InfoDS_Input infoInput= new InfoDS_Input();
    		infoInput.setStrCodtramite("1111");        	
        	input.setInput(infoInput);
    		
        	GetTramiteResponse tramite = stub.getTramite(input);
            assertNotNull(tramite);
            
        	Tramite t=tramite.getGetTramiteReturn();
        	int cod=t.getTramite().getControl().getCod_Retorno1();
            assertEquals(1234,cod);
    	}

    	//Create an ADBBean and provide it as the test object
    	public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
    		return (org.apache.axis2.databinding.ADBBean) type.newInstance();
    	}

        
        

    }
    