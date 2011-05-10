

/**
 * AutomaticTranslationServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.axis2.util.IOUtils;

import es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub;
import es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.Param;
import es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.ParamListType;
import es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.Task;
import es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.TaskE;


    /*
     *  AutomaticTranslationServiceTest Junit test case
    */

    public class AutomaticTranslationAxis2Test extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void _testtest() throws java.lang.Exception{

        es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub stub =
                    new es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub();//the default implementation should point to the right endpoint

           
                    
                    //There is no output to be tested!
                    stub.test(
                        );
                    



        }
        
         /**
         * Auto generated test method
         */
        public  void _testStarttest() throws java.lang.Exception{
            es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub stub = new es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub();
             

                stub.starttest(
                         
                    new tempCallbackN65548()
                );
              


        }

        private class tempCallbackN65548  extends es.caib.integracion.ws.traductor.AutomaticTranslationServiceCallbackHandler{
            public tempCallbackN65548(){ super(null);}

            public void receiveResulttest(
                         ) {
                
            }

            public void receiveErrortest(java.lang.Exception e) {
                fail();
            }

        }
      
        /**
         * Auto generated test method
         */
        public  void testtranslate() throws java.lang.Exception{

        	AutomaticTranslationServiceStub stub =
                    new AutomaticTranslationServiceStub();//the default implementation should point to the right endpoint

           TaskE task13=(TaskE)getTestObject(TaskE.class);
       
           Task task = (Task)getTestObject(Task.class);
           task.setService("TRANSLATE-TEXT");
           task.setVerbose(true);
           ParamListType params=(ParamListType)getTestObject(ParamListType.class);
           
           Param p = (Param)getTestObject(Param.class); 
           p.setName("TRANSLATION_DIRECTION");
           p.setValue("CATALAN-SPANISH");
           params.addParam(p);
           
           p=(Param)getTestObject(Param.class); 
           p.setName("SUBJECT_AREAS");
           p.setValue("(GV)");
           params.addParam(p);

           p=(Param)getTestObject(Param.class); 
           p.setName("INPUT");
           p.setValue("bon dia com esteu?");
           params.addParam(p);
           
           //task.setInputParams((Param[])input.toArray());
           
           task.setInputParams(params);
           task13.setTask(task);
           
           

           //assertNotNull(stub.translate(task13));
                  
           task13=stub.translate(task13);
           params = task13.getTask().getOutputParams();
           
           
           String s=null;
           
           for(Param pp: params.getParam()) {
           	if(pp.getName().equals("OUTPUT")) {
           		InputStream is= pp.getBinValue().getInputStream();
                   s=convertStreamToString(is);
                   break;
               }
           } 
           assertEquals("¿buen día <A[como|cómo]> estáis?", s.trim());

        }
        
         /**
         * Auto generated test method
         */
        public  void _testStarttranslate() throws java.lang.Exception{
            es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub stub = new es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub();
             es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.TaskE task13=
                                                        (es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.TaskE)getTestObject(es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.TaskE.class);
                    // TODO : Fill in the task13 here
                

                stub.starttranslate(
                         task13,
                    new tempCallbackN65583()
                );
              


        }

        private class tempCallbackN65583  extends es.caib.integracion.ws.traductor.AutomaticTranslationServiceCallbackHandler{
            public tempCallbackN65583(){ super(null);}

            public void receiveResulttranslate(
                         es.caib.integracion.ws.traductor.AutomaticTranslationServiceStub.TaskE result
                            ) {
                
            }

            public void receiveErrortranslate(java.lang.Exception e) {
                fail();
            }

        }
      
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        private String convertStreamToString(InputStream is) {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        	StringBuilder sb = new StringBuilder();
        	String line = null;
        	try {
        		while ((line = reader.readLine()) != null) {
        			sb.append(line + "\n");
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	} finally {
        		try {
        			is.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        	return sb.toString();
        }

    }
    