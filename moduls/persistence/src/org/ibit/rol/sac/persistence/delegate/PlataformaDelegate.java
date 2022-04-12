package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import es.caib.rolsac.utils.ResultadoBusqueda;

public class PlataformaDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -437746444276565342L;

	PlataformaDelegateI impl;

	public PlataformaDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final PlataformaDelegateI impl) {
		this.impl = impl;
	}

	/** Grabar plataforma. **/
	public Long grabarPlataforma(final Plataforma plataforma) throws DelegateException {
		return impl.grabarPlataforma(plataforma);
	}

	/** Obtener plataforma. **/
	public Plataforma obtenerPlataforma(final Long id) throws DelegateException {
		return impl.obtenerPlataforma(id);
	}

	/** Borrar plataforma. **/
	public void borrarPlataforma(final Long id) throws DelegateException {
		impl.borrarPlataforma(id);
	}

	/** Buscar plataforma. **/
	public ResultadoBusqueda buscadorListarPlataforma(final Map parametros, final int pagina, final int resultats,
			final boolean uaFilles, final boolean uaMeves) throws DelegateException {
		return impl.buscadorListarPlataforma(parametros, pagina, resultats, uaFilles, uaMeves);
	}

	/** Reordenar plataforma. **/
	public void reordenar(final Long id, final Integer nuevoOrden, final Integer ordenOld) throws DelegateException {
		impl.reordenar(id, nuevoOrden, ordenOld);
	}

	/** Plataforma con asociaciones de proc/serveis. **/
	public boolean plataformaConAsociaciones(final Long idPlataforma) throws DelegateException {
		return impl.plataformaConAsociaciones(idPlataforma);
	}

	/** Consulta por restapi **/
	public ResultadoBusqueda consultaPlataformas(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaPlataformas(filtro);
	}

	/**
	 * Lista plataforma.
	 * 
	 * @throws DelegateException
	 **/
	public List<Plataforma> listarPlataforma() throws DelegateException {
		return impl.listarPlataforma();
	}
}
