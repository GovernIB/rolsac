package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Idioma;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */
public class IdiomaDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -4377464442759993342L;
	
	// Cache de lengaujes
    private List<String> lenguajes = null;
    private List<String> lenguajesTraductor = null;
    private long timeLen = 0L;

    // Cache de lenguaje por defecto
//    private String porDefecto = null;
//    private long timeDef = 0L;
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
	
    public List<String> listarLenguajesTraductor() throws DelegateException {
            if (lenguajesTraductor == null || timeout(timeLen)) {
                lenguajesTraductor = impl.listarLenguajesTraductor();
                timeLen = System.currentTimeMillis();
            }
            return lenguajesTraductor;
    }    

	public String lenguajePorDefecto() throws DelegateException {
		return impl.lenguajePorDefecto();
	}

	/** @deprecated No se usa */
	public List<Idioma> listarIdiomas() throws DelegateException {
		return impl.listarIdiomas();
	}

	public List<String> listarLenguajes() throws DelegateException {
            if (lenguajes == null || timeout(timeLen)) {
                lenguajes = impl.listarLenguajes();
                timeLen = System.currentTimeMillis();
            }
            return lenguajes;
	}

}
