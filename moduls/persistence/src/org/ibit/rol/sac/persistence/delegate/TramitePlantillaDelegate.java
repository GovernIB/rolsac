package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.TramitePlantilla;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import es.caib.rolsac.utils.ResultadoBusqueda;

/*
 * TramitePlantillaDelegate
 */

public class TramitePlantillaDelegate {
	TramitePlantillaDelegateI impl;

	public TramitePlantillaDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final TramitePlantillaDelegateI impl) {
		this.impl = impl;
	}

	public void borrarTramitePlantilla(final Long codigo) throws DelegateException {
		impl.borrarTramitePlantilla(codigo);
	}

	public Long grabarTramitePlantilla(final TramitePlantilla tramitePlantilla, final boolean edicion)
			throws DelegateException {
		return impl.grabarTramitePlantilla(tramitePlantilla, edicion);
	}

	public ResultadoBusqueda listarTramitePlantilla(final int pagina, final int resultats, final String idioma)
			throws DelegateException {
		return impl.listarTramitePlantilla(pagina, resultats, idioma);
	}

	public List<TramitePlantilla> listarTramitePlantilla() throws DelegateException {
		return impl.listarTramitePlantilla();
	}

	public TramitePlantilla obtenerTramitePlantilla(final Long codigo) throws DelegateException {
		return impl.obtenerTramitePlantilla(codigo);
	}

	public int cuantosProcedimientosConTramitePlantilla(final Long id) throws DelegateException {
		return impl.cuantosProcedimientosConTramitePlantilla(id);
	}

	public ResultadoBusqueda consultaPlantillas(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaPlantillas(filtro);
	}

}
