package org.ibit.rol.sac.back.action;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Manté el nombre de sessions actives en tot moment.
 * @author areus
 */
public class SimpleSessionListener implements HttpSessionListener {

	protected static Log log = LogFactory.getLog(SimpleSessionListener.class);
	
	private static AtomicInteger sessions = new AtomicInteger(0);	
	
	public void sessionCreated(HttpSessionEvent event) {
		sessions.incrementAndGet();
		log.info("\nCreated session: " + event.getSession().getId() +
				"\nActive sessions: " + sessions.get() );		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		sessions.decrementAndGet();
		log.info("\nDestroy session: " + event.getSession().getId() +
				"\nActive sessions: " + sessions.get() );				
	}
}
