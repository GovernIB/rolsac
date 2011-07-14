package org.ibit.rol.sac.persistence.ws.invoker;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
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
import java.util.ArrayList;
import java.util.List;

public class SecurityInvoker {
	
	private static final Log log = LogFactory.getLog(SecurityInvoker.class);
	
	private final Call call;
	
	private final String usuario;
    private final String password;
	
	private final String webService;
	private final String namespaceURI;
	
	public SecurityInvoker(String endPoint, String webService, String namespaceURI, String usuario, String password) throws WSInvocatorException {
        try {
        	this.webService = webService;
        	this.namespaceURI = namespaceURI;
        	this.usuario = usuario;
        	this.password = password;
        	
            call = (Call) new Service().createCall();
            call.setTargetEndpointAddress(new URL(endPoint));
            log.debug("Fin Constructor");
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
                new BeanDeserializerFactory(clase, qn));
    }
    
    protected Object invoke(Object... objects ) throws RemoteException{
    	call.addParameter("usuario",
    			org.apache.axis.Constants.XSD_STRING,
    			javax.xml.rpc.ParameterMode.IN);
    	call.addParameter("password",
    			org.apache.axis.Constants.XSD_STRING,
    			javax.xml.rpc.ParameterMode.IN);
    	List<Object> parametros = new ArrayList<Object>();
    	
    	parametros.add(usuario);
    	parametros.add(password);
    	for(Object object : objects){
    		parametros.add(object);
    	}
    	Object result = call.invoke(parametros.toArray(new Object[0]));
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
	
	
}
