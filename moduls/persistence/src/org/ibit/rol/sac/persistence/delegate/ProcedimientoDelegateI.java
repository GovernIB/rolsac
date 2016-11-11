package org.ibit.rol.sac.persistence.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

public interface ProcedimientoDelegateI {

	public abstract Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA)
			throws DelegateException;

	public abstract Long grabarProcedimientoConTramites(ProcedimientoLocal procedimiento, Long idUA, List listaTramitesParaBorrar, List listaIdsTramitesParaActualizar)
			throws DelegateException;
	
	public abstract List listarProcedimientos()
			throws DelegateException;

	public abstract List listarProcedimientosPublicos()
			throws DelegateException;

	public abstract ProcedimientoLocal obtenerProcedimiento(Long id)
			throws DelegateException;

	public abstract ProcedimientoLocal obtenerProcedimientoNewBack(Long id)
			throws DelegateException;

	public abstract List buscarProcedimientos(Map param, Map trad)
			throws DelegateException;

	public abstract List buscarProcedimientosMateria(Long id)
			throws DelegateException;

	public ResultadoBusqueda buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible, String en_plazo, String telematico)
			throws DelegateException;

	public abstract void anyadirTramite(Long tramite_id, Long proc_id)
			throws DelegateException;

	public abstract void eliminarTramite(Long tramite_id, Long proc_id)
			throws DelegateException;

	public abstract void borrarProcedimiento(Long id)
			throws DelegateException;

	public abstract List listarProcedimientosUA(Long id)
			throws DelegateException;

	public abstract List listarProcedimientosUO(Long id, Integer conse)
			throws DelegateException;

	public abstract List listarProcedimientosPublicosUA(Long id)
			throws DelegateException;

	public abstract List listarProcedimientosPublicosHechoVital(Long id)
			throws DelegateException;

	public abstract boolean existeOtroTramiteInicioProcedimiento(Long procId, Long tramiteId)
			throws DelegateException;

	/* PORMAD */
	@SuppressWarnings("unchecked")
	public abstract List<Long> listarIdsProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV)
			throws DelegateException;

	public abstract ProcedimientoLocal consultarProcedimiento(Long id)
			throws DelegateException;

	public void actualizarOrdenTramites(ArrayList<Long> tramites)
			throws DelegateException;

	public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad)
			throws DelegateException;

	public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad)
			throws DelegateException;
	
	public boolean isProcedimientoConEstadoPublicacionPublica(Long idProcedimiento)
			throws DelegateException;

	/* PORMAD */
	@SuppressWarnings("unchecked")
	public abstract List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV)
			throws DelegateException;

	public ResultadoBusqueda buscadorProcedimientos(BuscadorProcedimientoCriteria bc)
			throws DelegateException;

	public abstract SolrPendienteResultado indexarSolr(SolrIndexer solrIndexer, SolrPendiente solrPendiente) throws DelegateException;
        
	public abstract SolrPendienteResultado indexarSolr(SolrIndexer solrIndexer, Long idElemento, EnumCategoria categoria) throws DelegateException;
	
    public abstract SolrPendienteResultado desindexarSolr(SolrIndexer solrIndexer, SolrPendiente solrPendiente) throws DelegateException;

	public abstract List<Long> buscarIdsProcedimientos() throws DelegateException;

	public abstract List<ProcedimientoLocal> listarProcedimientosNormativa(Long idElemento) throws DelegateException;

}
