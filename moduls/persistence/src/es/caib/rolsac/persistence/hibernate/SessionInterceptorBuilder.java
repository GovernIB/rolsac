package es.caib.rolsac.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static es.caib.rolsac.utils.StringUtils.*;


import net.sf.hibernate.Interceptor;

public class SessionInterceptorBuilder {

	protected static Log log = LogFactory.getLog(SessionInterceptorBuilder.class);

	public static ChainedSessionInterceptor build() {

		//Primero se crean los interceptores espeficicos de sesion, si los hubiese.
		List<Interceptor> interceptorsList = buildSessionInterceptorList();

    	
    	//Si no hay ningun interceptor de sesion, se devuelve chain nulo.
    	if(!haySessionInterceptors(interceptorsList)) {
    		return null;
    	}

    	
    	//Finalmente se añade el interceptor global, si lo hubiese.
    	
		Interceptor globalInterceptor= HibernateLocator.getGlobalInterceptor();
		
		if(null!=globalInterceptor) {
			interceptorsList.add(globalInterceptor);
		}
		

		//Se crea y devuelve el interceptor encadenado de sesion.
		
		ChainedSessionInterceptor chainedInterceptor = new ChainedSessionInterceptor();
		
		Interceptor[] interceptors = toArray(interceptorsList);
		chainedInterceptor.setInterceptors(interceptors);

		return chainedInterceptor;
	}


	private static List<Interceptor> buildSessionInterceptorList() {
		/**
		 * Lee de una propiedad la lista de interceptores (comma) , y por cada uno
		 * crea una instance con un builder acabado en nomclaseBuilder.build()
		 * 
		 */
		
		List<Interceptor> interceptorsList = new ArrayList<Interceptor>();

		String interceptorStr = (String)System.getProperty("es.caib.rolsac.persistence.hibernate.sessionInterceptors");
		
		if(vacio(interceptorStr))
			return null;
		
		String[] interceptors = interceptorStr.split(",");
		
		for(String interceptorClassname : interceptors) {

			try {
				Class clazz=null;
				clazz = Class.forName(interceptorClassname+"Builder");
				InterceptorBuilder builder = (InterceptorBuilder)clazz.newInstance();
				Interceptor interceptor = builder.build();    	
				if(null!=interceptor) {
		    		interceptorsList.add(interceptor);
		    	}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.error("",e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				log.error("",e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				log.error("",e);
			}
			

		}
		
		
		//ConnectionInfoInterceptor connInfoInterceptor = ConnectionInfoInterceptorBuilder.build();

		return interceptorsList;
	}
	

	private static Interceptor[] toArray(List<Interceptor> l) {
		return (Interceptor[]) l.toArray(new Interceptor[l.size()]);
		
	}

	private static boolean haySessionInterceptors(List<Interceptor> interceptorsList) {
		if(null==interceptorsList) 
			return false;
		return 0 < interceptorsList.size();
	}
	
	

}
