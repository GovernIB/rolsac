package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SilencioAdm;

import es.caib.rolsac.utils.ResultadoBusqueda;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class SilencioAdmDelegate
{
	SilencioAdmDelegateI impl;
	
	
	public SilencioAdmDelegateI getImpl() {
		return impl;
	}
	
	public void setImpl(SilencioAdmDelegateI impl) {
		this.impl = impl;
	}
	
	public void borrarSilencioAdm(String codigo) throws DelegateException {
		impl.borrarSilencioAdm(codigo);
	}
	
	public String grabarSilencioAdm(SilencioAdm silencio, boolean edicion) throws DelegateException {
		return impl.grabarSilencioAdm(silencio, edicion);
	}
	
	public ResultadoBusqueda listarSilencioAdm(int pagina, int resultats, String idioma) throws DelegateException {
		return impl.listarSilencioAdm(pagina, resultats, idioma);
	}
	
	public List listarSilencioAdm() throws DelegateException {
		return impl.listarSilencioAdm();
	}
	
	public SilencioAdm obtenerSilencioAdm(String codigo) throws DelegateException {
		return impl.obtenerSilencioAdm(codigo);
	}

	
}
