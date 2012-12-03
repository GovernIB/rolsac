package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Iniciacion;

import es.caib.rolsac.utils.ResultadoBusqueda;

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
 
	public ResultadoBusqueda listarIniciacion(int pagina, int resultats, String idioma) throws DelegateException {
		return impl.listarIniciacion(pagina, resultats, idioma);
	}
	
	public List listarIniciacion() throws DelegateException {
		return impl.listarIniciacion();
        }

	public Iniciacion obtenerIniciacion(Long id) throws DelegateException {
		return impl.obtenerIniciacion(id);
    }
    
    
}
