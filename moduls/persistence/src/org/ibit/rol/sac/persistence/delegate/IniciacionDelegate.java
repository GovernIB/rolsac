package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.IniciacionFacade;
import org.ibit.rol.sac.persistence.intf.IniciacionFacadeHome;
import org.ibit.rol.sac.persistence.util.IniciacionFacadeUtil;
import org.ibit.rol.sac.model.Iniciacion;
 
import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class IniciacionDelegate  {

	IniciacionDelegateI impl;
    
	public IniciacionDelegateI getImpl() {
		return impl;
    }

	public void setImpl(IniciacionDelegateI impl) {
		this.impl = impl;
        }

	public void borrarIniciacion(Long id) throws DelegateException {
		impl.borrarIniciacion(id);
    }

	public Long grabarIniciacion(Iniciacion tipo) throws DelegateException {
		return impl.grabarIniciacion(tipo);
    }
 
	public List listarIniciacion() throws DelegateException {
		return impl.listarIniciacion();
        }

	public Iniciacion obtenerIniciacion(Long id) throws DelegateException {
		return impl.obtenerIniciacion(id);
    }
    
    
}
