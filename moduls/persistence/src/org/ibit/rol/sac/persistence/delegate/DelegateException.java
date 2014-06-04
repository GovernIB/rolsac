package org.ibit.rol.sac.persistence.delegate;

import javax.ejb.EJBException;

/**
 * Excepción producida en la capa delegate.
 */
public class DelegateException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private SecurityException se = null;
	private IllegalStateException ie = null;

    public DelegateException(Throwable cause) {
    	
        super(cause);

        while (cause != null) {
        	
            if (cause instanceof SecurityException) {
                setSecurityException((SecurityException) cause);
                break;
            }
            
            if (cause instanceof IllegalStateException) {
                setIllegalStateException((IllegalStateException) cause);
                break;
            }

            if (cause instanceof EJBException) {
                cause = ((EJBException) cause).getCausedByException();
            } else {
                cause = cause.getCause();
            }
            
        }
        
    }

    public boolean isSecurityException() {
        return (se != null);
    }

    public SecurityException getSecurityException() {
        return se;
    }

    public void setSecurityException(SecurityException se) {
        this.se = se;
    }

	public IllegalStateException getIllegalStateException() {
		return ie;
	}

	public void setIllegalStateException(IllegalStateException ie) {
		this.ie = ie;
	}

	public boolean isIllegalStateException() {
    	return (ie != null);
    }
    
}
