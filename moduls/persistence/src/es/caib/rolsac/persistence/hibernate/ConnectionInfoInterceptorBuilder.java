package es.caib.rolsac.persistence.hibernate;

import net.sf.hibernate.Interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.persistence.utils.ConnectionInfo;
import es.caib.rolsac.persistence.utils.JBossUtils;
import static es.caib.rolsac.utils.StringUtils.*;


/**
 * Esta clase crea un interceptor para que muestre informacion de la conexion, 
 * independientemente de la base de datos.
 * Para ello se debe definir una propiedad de sistema con el nombre de la clase
 * que obtiene esta informacion.
 * @author u92770
 *
 */

public class ConnectionInfoInterceptorBuilder implements InterceptorBuilder {

	static ConnectionInfo connInfo; 
    protected static Log log = LogFactory.getLog(ConnectionInfoInterceptorBuilder.class);

	
	public Interceptor build() {
		
		//Si está definida la propiedad, se crea una instancia de la clase que devuelve la informacion de
		//la conexion. Sino se devuelve un interceptor nulo.
    	String connInfoClassName = System.getProperty("es.caib.rolsac.persistence.hibernate.databaseConnectionInfoClass");

    	if(vacio(connInfoClassName)) {
    		return null;
    	}
    	
		try {
			if(null==connInfo) {
				//Se crea la instancia de la clase definida segun la propiedad
				Class clazz = Class.forName(connInfoClassName);
				connInfo = (ConnectionInfo)clazz.newInstance();
			}
			
	    	//Se devuelve el connection interceptor
			return  new ConnectionInfoInterceptor(connInfo);
			
		} catch (InstantiationException e) {
			log.error("",e);
		} catch (IllegalAccessException e) {
			log.error("",e);
		} catch (ClassNotFoundException e) {
			log.error("",e);
		}
    
    	return  null;

	}

}
