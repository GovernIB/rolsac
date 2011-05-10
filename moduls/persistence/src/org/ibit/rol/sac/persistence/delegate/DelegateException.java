package org.ibit.rol.sac.persistence.delegate;

import javax.ejb.EJBException;
import java.rmi.RemoteException;

/**
 * Excepción producida en la capa delegate.
 */
public class DelegateException extends Exception {

    private SecurityException se = null;

    public DelegateException(Throwable cause) {
        super(cause);

        while (cause != null) {
            if (cause instanceof SecurityException) {
                setSecurityException((SecurityException) cause);
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
}
