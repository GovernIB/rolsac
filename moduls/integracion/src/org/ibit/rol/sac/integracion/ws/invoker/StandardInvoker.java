package org.ibit.rol.sac.integracion.ws.invoker;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class StandardInvoker {
	
	private static final Log log = LogFactory.getLog(StandardInvoker.class);
	
	private final Call call;
	
	private final String webService;
	private final String namespaceURI;
	
	public StandardInvoker(String endPoint, String webService, String namespaceURI) throws WSInvocatorException {
        try {
        	this.webService = webService;
        	this.namespaceURI = namespaceURI;

            call = (Call) new Service().createCall();

            call.setTargetEndpointAddress(new URL(endPoint));
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);

            log.info("Fin Constructor");
        } catch(ServiceException e) {
            throw new WSInvocatorException(e);
        } catch(MalformedURLException e) {
            throw new WSInvocatorException(e);
        }
    }
	
	protected void setOperationName(String method){
    	call.setOperationName(new QName(webService, method));
    }
    
	protected void registerTypeMapping(final Class<?> clase){
    	final QName qn =new QName(namespaceURI, clase.getSimpleName());
    	
    	call.registerTypeMapping(clase,
                qn,
                new BeanSerializerFactory(clase, qn),
                new BeanDeserializerFactory(clase, qn),
                false);
    }

    protected void registerTypeMapping(final String namespace, final Class<?> clase){
    	final QName qn =new QName(namespace, clase.getSimpleName());

    	call.registerTypeMapping(clase,
                qn,
                new BeanSerializerFactory(clase, qn),
                new BeanDeserializerFactory(clase, qn),
                false);
    }

    protected void registerArrayTypeMapping(final QName qn, final QName arrayQn, final Class<?> clase){
    	call.registerTypeMapping(clase,
                qn,
                new ArraySerializerFactory(arrayQn, null),
                new ArrayDeserializerFactory(arrayQn),
                false);
    }
    
    protected Object invoke(Object... objects ) throws RemoteException{
        call.setOperationStyle("rpc");
        call.setOperationUse("encoded");        
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("");
        Object result = call.invoke(objects);
    	call.removeAllParameters();
    	call.clearOperation();
    	return result;
    }
    
    protected void addParameter(String nombre, QName qname, ParameterMode parameter){
    	call.addParameter(nombre, qname, parameter);
    }
    
    protected void setReturnType(QName qname){
    	call.setReturnType(qname);
    }

    protected void setReturnClass(Class<?> clase) {
        call.setReturnClass(clase);
    }

    protected void setReturnType(final String namespace, final Class<?> clase){
        final QName qn =new QName(namespace, clase.getSimpleName());
        call.setReturnType(qn);
        call.setReturnClass(clase);
    }

    protected void setReturnQName(QName qname){
    	call.setReturnQName(qname);
    }

	
}
