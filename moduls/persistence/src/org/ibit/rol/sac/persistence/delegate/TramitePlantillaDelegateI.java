package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.TramitePlantilla;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface TramitePlantillaDelegateI {
	public abstract Long grabarTramitePlantilla(TramitePlantilla plantillaTramite, boolean edicion)
			throws DelegateException;

	public abstract ResultadoBusqueda listarTramitePlantilla(int pagina, int resultats, String idioma)
			throws DelegateException;

	public abstract List<TramitePlantilla> listarTramitePlantilla() throws DelegateException;

	public abstract TramitePlantilla obtenerTramitePlantilla(Long codigo) throws DelegateException;

	public abstract void borrarTramitePlantilla(Long codigo) throws DelegateException;

	public abstract int cuantosProcedimientosConTramitePlantilla(Long id) throws DelegateException;

	public abstract ResultadoBusqueda consultaPlantillas(final FiltroGenerico filtro) throws DelegateException;

}
