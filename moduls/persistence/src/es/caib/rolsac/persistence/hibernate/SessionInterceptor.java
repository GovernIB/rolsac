package es.caib.rolsac.persistence.hibernate;

import javax.ejb.SessionContext;

import net.sf.hibernate.Interceptor;
import net.sf.hibernate.Session;

public interface SessionInterceptor extends Interceptor {

	public void setSession(Session session);
	public void setSessionContext(SessionContext ctx);

}
