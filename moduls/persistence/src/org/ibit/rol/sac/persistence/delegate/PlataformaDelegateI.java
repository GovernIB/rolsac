package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Interfaz Plataforma Delegate.
 *
 * @author slromero
 *
 */
public interface PlataformaDelegateI {

	/** Grabar plataforma. **/
	public Long grabarPlataforma(final Plataforma plataforma) throws DelegateException;

	/** Obtener plataforma. **/
	public Plataforma obtenerPlataforma(final Long id) throws DelegateException;

	/** Borrar plataforma. **/
	public void borrarPlataforma(final Long id) throws DelegateException;

	/** Buscar plataforma. **/
	public ResultadoBusqueda buscadorListarPlataforma(Map parametros, int pagina, int resultats, boolean uaFilles,
			boolean uaMeves) throws DelegateException;

	/** Reordenar. **/
	public void reordenar(Long id, Integer nuevoOrden, Integer ordenOld) throws DelegateException;

	/** Comprueba si tiene asociaciones antes de borrar. **/
	public boolean plataformaConAsociaciones(Long idPlataforma) throws DelegateException;

	/** Obtiene la consutla por restapi. **/
	public ResultadoBusqueda consultaPlataformas(FiltroGenerico filtro) throws DelegateException;

	/** Obtiene las plataformas para el combobox. **/
	public List<Plataforma> listarPlataforma() throws DelegateException;

}
