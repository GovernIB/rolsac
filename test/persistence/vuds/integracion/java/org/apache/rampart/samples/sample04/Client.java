package org.apache.rampart.samples.sample04;
/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.net.URL;

import junit.framework.TestCase;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;

public class Client extends TestCase {

	String endpoint = "http://localhost:9080/axis2/services/sample04";
	
    public static void main(String[] args) throws Exception {
        
        if(args.length != 2) {
            System.out.println("Usage: $java Client endpoint_address client_repo_path");
        }
        
        ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(args[1], args[1] + "/sample.axis2.xml");
        
        ServiceClient client = new ServiceClient(ctx, null);
        Options options = new Options();
        options.setAction("urn:echo");
        options.setTo(new EndpointReference(args[0]));
        client.setOptions(options);
        
        OMElement response = client.sendReceive(getPayload("Hello world"));
        
        System.out.println(response);
        
    }
    
    public void testRampart() throws Exception {
    	
    	//ConfigurationContext ctx = ConfigurationContextFactory.createBasicConfigurationContext("sample.axis2.xml");

    	ClassLoader classLoader =
    		Thread.currentThread().getContextClassLoader();

    	URL axis2xml  = classLoader.getResource("sample.axis2.xml");
    	URL repo  = classLoader.getResource("axis2repo");

    	ConfigurationContext ctx  = ConfigurationContextFactory.createConfigurationContextFromURIs(axis2xml, repo);


    	ServiceClient client = new ServiceClient(ctx, null);

    	Options options = new Options();
    	options.setAction("urn:echo");
    	options.setTo(new EndpointReference(endpoint));
    	client.setOptions(options);

        
        OMElement response = client.sendReceive(getPayload("Hello world"));
        
        System.out.println(response);
    }
    
    private static OMElement getPayload(String value) {
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace ns = factory.createOMNamespace("http://sample04.samples.rampart.apache.org","ns1");
        OMElement elem = factory.createOMElement("echo", ns);
        OMElement childElem = factory.createOMElement("param0", null);
        childElem.setText(value);
        elem.addChild(childElem);
        
        return elem;
    }
    
}
