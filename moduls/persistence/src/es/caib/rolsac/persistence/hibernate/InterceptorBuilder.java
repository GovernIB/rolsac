package es.caib.rolsac.persistence.hibernate;

import net.sf.hibernate.Interceptor;

public interface InterceptorBuilder {

	public abstract Interceptor build();

}
