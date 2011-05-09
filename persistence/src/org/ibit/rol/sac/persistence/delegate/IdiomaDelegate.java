package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.persistence.intf.IdiomaFacade;
import org.ibit.rol.sac.persistence.intf.IdiomaFacadeHome;
import org.ibit.rol.sac.persistence.util.IdiomaFacadeUtil;
import org.ibit.rol.sac.model.Idioma;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */
public class IdiomaDelegate implements StatelessDelegate {

    // Cache de lengaujes
    private List lenguajes = null;
    private List lenguajesTraductor = null;
    private long timeLen = 0L;

    // Cache de lenguaje por defecto
    private String porDefecto = null;
    private long timeDef = 0L;
	IdiomaDelegateI impl;

    private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {
        return ((System.currentTimeMillis() - time) > maxtime);
    }

	public IdiomaDelegateI getImpl() {
		return impl;
	}
	public void setImpl(IdiomaDelegateI impl) {
		this.impl = impl;
	}

	
    public List listarLenguajesTraductor() throws DelegateException {
            if (lenguajesTraductor == null || timeout(timeLen)) {
                lenguajesTraductor = impl.listarLenguajesTraductor();
                timeLen = System.currentTimeMillis();
            }
            return lenguajesTraductor;
    }

    public String lenguajePorDefecto() throws DelegateException {
		return impl.lenguajePorDefecto();
    }

     public List<Idioma> listarIdiomas() throws DelegateException {
		return impl.listarIdiomas();
    }

	public List listarLenguajes() throws DelegateException {
            if (lenguajes == null || timeout(timeLen)) {
                lenguajes = impl.listarLenguajes();
                timeLen = System.currentTimeMillis();
     }
            return lenguajes;
     
    }

}
