/**
 * AutomaticTranslationServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.indra.rol.sac.integracion.ws.traductor;

public class AutomaticTranslationServiceTestCase extends junit.framework.TestCase {
    public AutomaticTranslationServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testAutomaticTranslationPortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceLocator().getAutomaticTranslationPortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1AutomaticTranslationPortTranslate() throws Exception {
        es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationPortBindingStub binding;
        try {
            binding = (es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationPortBindingStub)
                          new es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceLocator().getAutomaticTranslationPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.translate(new es.indra.rol.sac.integracion.ws.traductor.holders.TaskHolder(new es.indra.rol.sac.integracion.ws.traductor.Task()));
        // TBD - validate results
    }

    public void test2AutomaticTranslationPortTest() throws Exception {
        es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationPortBindingStub binding;
        try {
            binding = (es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationPortBindingStub)
                          new es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceLocator().getAutomaticTranslationPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.test();
        // TBD - validate results
    }

}
