package org.ibit.rol.sac.integracion.ws;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import junit.framework.TestCase;

public class ProcedimientoServiceTest extends TestCase {

	  EndpointReference targetEPR = 
	        new EndpointReference(
	              "http://epreinf45:8080/sacws/services/ProcedimientoService");
	
	  ServiceClient sender;
	  
	  @Override
	protected void setUp() throws Exception {
		super.setUp();
		Options options = new Options();
        options.setTo(targetEPR);
        options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
        sender = new ServiceClient();
        sender.setOptions(options);

	}
	public void testConsultarProcedimento() throws AxisFault {
		
		OMElement resp = sender.sendReceive(consultarProcedimientoPayload(0));
		assertNotNull(resp);
	}

	public void _testObtenerProcedimentosHechoVital() {
		fail("Not yet implemented");
	}

	public void _testBuscarProcedimientos() {
		fail("Not yet implemented");
	}

	private OMElement consultarProcedimientoPayload(int id) {
	      OMFactory fac = OMAbstractFactory.getOMFactory();
	        OMNamespace omNs = fac.createOMNamespace(
	                                                 "http://rol.ibit.org/beans", "tns");

	        OMElement method = fac.createOMElement("consultarProcedimento", omNs);
	        OMElement value = fac.createOMElement("id", omNs);
	        value.addChild(fac.createOMText(value, new Integer(id).toString()));
	        method.addChild(value);
	        return method;
	
	}
}
