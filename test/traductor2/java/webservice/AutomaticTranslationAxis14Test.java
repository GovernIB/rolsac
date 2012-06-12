/**
 * AutomaticTranslationServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;


import java.util.ArrayList;
import java.util.List;

import com.lucysoftware.ws.ObjectFactory;

import es.caib.integracion.ws.traductor.Param;
import es.caib.integracion.ws.traductor.Task;
import es.caib.integracion.ws.traductor.holders.TaskHolder;

public class AutomaticTranslationAxis14Test extends junit.framework.TestCase {
    public AutomaticTranslationAxis14Test(java.lang.String name) {
        super(name);
    }

    public void testAutomaticTranslationPortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new es.caib.integracion.ws.traductor.AutomaticTranslationServiceLocator().getAutomaticTranslationPortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new es.caib.integracion.ws.traductor.AutomaticTranslationServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1AutomaticTranslationPortTranslate() throws Exception {
        es.caib.integracion.ws.traductor.AutomaticTranslationPortBindingStub binding;
        try {
            binding = (es.caib.integracion.ws.traductor.AutomaticTranslationPortBindingStub)
                          new es.caib.integracion.ws.traductor.AutomaticTranslationServiceLocator().getAutomaticTranslationPort();
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
        Task task = new Task();
        task.setService("TRANSLATE-TEXT");
        task.setVerbose(true);
        List<Param> input=new ArrayList<Param>();
        
        Param p = new Param(null,null,"TRANSLATION_DIRECTION","CATALAN-SPANISH");
        input.add(p);
        p = new Param(null,null,"SUBJECT_AREAS","(GV)");
        input.add(p);
        p = new Param(null,null,"INPUT","bon dia com esteu?");
        input.add(p);
        //task.setInputParams((Param[])input.toArray());
        task.setInputParams(input.toArray(new Param[input.size()]));
        
        TaskHolder hTask=new TaskHolder();
        hTask.value = task;
        
        long start = System.currentTimeMillis();
        binding.translate(hTask);
        long finish = System.currentTimeMillis();
        Task result = (Task)hTask.value;

        for(Param pp: result.getOutputParams()) {
        	if(pp.getName().equals("OUTPUT")) {
                System.out.println(pp); //. type_pattern.new(..)) ( new StringBuilder(String.valueOf(result.id))).append(":\n").append("   Execution time: ").append(finish - start).append(" msecs\n").append("   Output: ").append(new String(pp.binValue)).toString());
                break;
            }
        } 
        // TBD - validate results
    }

    public void test2AutomaticTranslationPortTest() throws Exception {
        es.caib.integracion.ws.traductor.AutomaticTranslationPortBindingStub binding;
        try {
            binding = (es.caib.integracion.ws.traductor.AutomaticTranslationPortBindingStub)
                          new es.caib.integracion.ws.traductor.AutomaticTranslationServiceLocator().getAutomaticTranslationPort();
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
