package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Familia;

import es.caib.rolsac.utils.ResultadoBusqueda;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class FamiliaDelegate  {

	FamiliaDelegateI impl;

	public FamiliaDelegateI getImpl() {
		return impl;
	}


	public void setImpl(FamiliaDelegateI impl) {
		this.impl = impl;
	}


	public void borrarFamilia(Long id) throws DelegateException {
		impl.borrarFamilia(id);
	}


	public Long grabarFamilia(Familia familia) throws DelegateException {
		return impl.grabarFamilia(familia);
	}


	public ResultadoBusqueda listarFamilias(int pagina, int resultats, String idioma) throws DelegateException {
		return impl.listarFamilias(pagina, resultats, idioma);
	}


	public List listarFamilias() throws DelegateException {
		return impl.listarFamilias();
	}


	public Familia obtenerFamilia(Long id) throws DelegateException {
		return impl.obtenerFamilia(id);
	}


	/** @deprecated No se usa */
	public boolean tieneProcedimientos(Long id) throws DelegateException {
		return impl.tieneProcedimientos(id);
	}

}
