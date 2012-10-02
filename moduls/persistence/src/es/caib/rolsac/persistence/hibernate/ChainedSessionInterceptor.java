package es.caib.rolsac.persistence.hibernate;

import javax.ejb.SessionContext;

import net.sf.hibernate.Interceptor;
import net.sf.hibernate.Session;

public class ChainedSessionInterceptor extends ChainedInterceptor 
									implements SessionInterceptor {

	public void setSession(Session session) {
		for(Interceptor interceptor: interceptors) {
			if(interceptor instanceof SessionInterceptor) {
				SessionInterceptor sessionInterceptor = (SessionInterceptor)interceptor;
				sessionInterceptor.setSession(session);
			}
		}
		
	}
	
	public void setSessionContext(SessionContext ctx) {
		for(Interceptor interceptor: interceptors) {
			if(interceptor instanceof SessionInterceptor) {
				SessionInterceptor sessionInterceptor = (SessionInterceptor)interceptor;
				sessionInterceptor.setSessionContext(ctx);
			}
		}
		
	}

}
