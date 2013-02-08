package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface ProcedimientoDelegateI {

	public abstract Long grabarProcedimiento(ProcedimientoLocal procedimiento,
			Long idUA) throws DelegateException;

	public abstract List listarProcedimientos() throws DelegateException;

	public abstract List listarProcedimientosPublicos() throws DelegateException;

	public abstract ProcedimientoLocal obtenerProcedimiento(Long id)
			throws DelegateException;

	public abstract List buscarProcedimientos(Map param, Map trad)
			throws DelegateException;

	public abstract List buscarProcedimientosFamilia(Long id)
			throws DelegateException;

	public abstract List buscarProcedimientosMateria(Long id)
			throws DelegateException;

	public abstract List buscarProcedimientosTexto(String texto)
			throws DelegateException;

	public abstract List buscarProcedimientosUATexto(Long idUnidad, String texto)
			throws DelegateException;

	public abstract List buscarProcedimientosUATexto(Long idUnidad, String texto, String idioma)
			throws DelegateException;
	
	public ResultadoBusqueda buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats)
			throws DelegateException;
		
	public abstract void anyadirNormativa(Long norm_id, Long proc)
			throws DelegateException;

	public abstract void eliminarNormativa(Long norm_id, Long proc_id)
			throws DelegateException;

	public abstract void anyadirMateria(Long materia_id, Long proc_id)
			throws DelegateException;

	public abstract void eliminarMateria(Long materia_id, Long proc_id)
			throws DelegateException;

	public abstract void anyadirTramite(Long tramite_id, Long proc_id)
			throws DelegateException;

	public abstract void eliminarTramite(Long tramite_id, Long proc_id)
			throws DelegateException;

	public abstract void borrarProcedimiento(Long id) throws DelegateException;

	public abstract List listarProcedimientosUA(Long id)
			throws DelegateException;

	public abstract List listarProcedimientosUO(Long id, Integer conse)
			throws DelegateException;

	public abstract void actualizarOrdenPros3(Map map) throws DelegateException;

	public abstract void actualizarOrdenPros2(Map map) throws DelegateException;

	public abstract void actualizarOrdenPros(Map map) throws DelegateException;

	public abstract List listarProcedimientosPublicosUA(Long id)
			throws DelegateException;

	public abstract List listarProcedimientosHechoVital(Long hecho_id)
			throws DelegateException;

	public abstract List listarProcedimientosPublicosHechoVital(Long id)
			throws DelegateException;

	public abstract boolean existeOtroTramiteInicioProcedimiento(Long procId, Long tramiteId)
	        throws DelegateException;
	
	/* PORMAD */
	@SuppressWarnings("unchecked")
	public abstract List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(
			Long idUA, String[] codEstMat, String[] codEstHV)
			throws DelegateException;

    /* PORMAD */
    @SuppressWarnings("unchecked")
    public abstract List<Long> listarIdsProcedimientosPublicosUAHVMateria(
            Long idUA, String[] codEstMat, String[] codEstHV)
            throws DelegateException;


	/* PORMAD*/
	public abstract List listarProcedimientosMateriaUA(Long materia_id,
			Long ua_id) throws DelegateException;

	public abstract ProcedimientoLocal consultarProcedimiento(Long id)
			throws DelegateException;

	public abstract List<ProcedimientoLocal> buscar(final String busqueda,
			final String idioma) throws DelegateException;

	public abstract ModelFilterObject obtenerFilterObject(
			ProcedimientoLocal proc) throws DelegateException;

	public abstract void indexInsertaProcedimiento(ProcedimientoLocal proc,
			ModelFilterObject filter) throws DelegateException;

	public abstract void indexBorraProcedimiento(ProcedimientoLocal pro)
			throws DelegateException;

	public void  actualizarOrdenTramites(Map map) throws DelegateException;

	public abstract boolean autorizaCrearProcedimiento(
			Integer validacionProcedimiento) throws DelegateException;

	public abstract boolean autorizaModificarProcedimiento(Long idProcedimiento) throws DelegateException;
	
	public abstract ProcedimientoLocal obtenerProcedimientoPM(Long id) throws DelegateException;

	public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException; 
    
	public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException;
	
}