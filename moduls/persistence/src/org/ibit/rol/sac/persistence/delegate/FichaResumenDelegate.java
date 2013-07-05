package org.ibit.rol.sac.persistence.delegate;

import java.util.Map;

import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.utils.ResultadoBusqueda;

public class FichaResumenDelegate implements FichaResumenDelegateI {
	
	FichaResumenDelegateI impl;
	
	
	public FichaResumenDelegateI getImpl() {
		return impl;
	}
	
	public void setImpl(FichaResumenDelegateI impl) {
		this.impl = impl;
	}
	
	public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible) throws DelegateException {
		return impl.buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles, uaMeves, campoOrdenacion, orden, pagina, resultats, campoVisible);
	}
	
}
