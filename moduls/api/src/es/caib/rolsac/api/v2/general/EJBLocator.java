package es.caib.rolsac.api.v2.general;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

public abstract class EJBLocator {

    protected static final String JNDI_NAME_PREFIX = "es.caib.rolsac.api.v2.";
    
    protected Object getRemoteReference(String jndiName) throws NamingException {
        // TODO: InitialContext properties will be provided by JBoss.
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.PROVIDER_URL, "jnp://localhost:11099");
        p.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");

        Context jndiContext = new javax.naming.InitialContext(p);
        return jndiContext.lookup(jndiName);
    }
    
}
