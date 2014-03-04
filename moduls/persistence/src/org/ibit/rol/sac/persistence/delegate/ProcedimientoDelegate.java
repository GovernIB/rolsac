package org.ibit.rol.sac.persistence.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;

import es.caib.rolsac.lucene.model.ModelFilterObject;
import es.caib.rolsac.utils.ResultadoBusqueda;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class ProcedimientoDelegate {
	
	ProcedimientoDelegateI impl;
	
	
	public ProcedimientoDelegateI getImpl() {
		return impl;
	}
	
	public void setImpl(ProcedimientoDelegateI impl) {
		this.impl = impl;
	}
	
    public boolean existeOtroTramiteInicioProcedimiento(Long procId, Long tramiteId) throws DelegateException {
        return impl.existeOtroTramiteInicioProcedimiento(procId, tramiteId);
    }

	public void anyadirTramite(Long tramiteId, Long procId) throws DelegateException {
		impl.anyadirTramite(tramiteId, procId);
	}
	
	public void borrarProcedimiento(Long id) throws DelegateException {
		impl.borrarProcedimiento(id);
	}
	
	public List buscarProcedimientos(Map param, Map trad) throws DelegateException {
		return impl.buscarProcedimientos(param, trad);
	}
	
	/** @deprecated No se usa */
	public ResultadoBusqueda buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible, String en_plazo, String telematico) 
			throws DelegateException {
		return impl.buscadorProcedimientos(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital, publicObjectiu, pagina, resultats, visible, en_plazo, telematico);
	}

	/** @deprecated Se usa desde la API v1*/
	public List buscarProcedimientosMateria(Long id) throws DelegateException {
		return impl.buscarProcedimientosMateria(id);
	}
	
	public List buscarProcedimientosTexto(String texto) throws DelegateException {
		return impl.buscarProcedimientosTexto(texto);
	}

	public ProcedimientoLocal consultarProcedimiento(Long id) throws DelegateException {
		return impl.consultarProcedimiento(id);
	}
	
	public void eliminarTramite(Long tramiteId, Long procId) throws DelegateException {
		impl.eliminarTramite(tramiteId, procId);
	}
	
	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) throws DelegateException {
		return impl.grabarProcedimiento(procedimiento, idUA);
	}
	
	public void indexBorraProcedimiento(ProcedimientoLocal pro) throws DelegateException {
		impl.indexBorraProcedimiento(pro);
	}
	
	public void indexInsertaProcedimiento(ProcedimientoLocal proc, ModelFilterObject filter) throws DelegateException {
		impl.indexInsertaProcedimiento(proc, filter);
	}
	
	/** @deprecated Se usa desde API v1 */
	public List listarProcedimientosPublicos() throws DelegateException {
		return impl.listarProcedimientosPublicos();
    }
	
	/** @deprecated Se usa desde el back antiguo */
	public List listarProcedimientos() throws DelegateException {
		return impl.listarProcedimientos();
	}
	
	public List listarProcedimientosPublicosHechoVital(Long id) throws DelegateException {
		return impl.listarProcedimientosPublicosHechoVital(id);
	}
	
	public List listarProcedimientosPublicosUA(Long id) throws DelegateException {
		return impl.listarProcedimientosPublicosUA(id);
	}
	
	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
		return impl.listarIdsProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
	}
	
	/** @deprecated Se usa desde el back antiguo */
	public List listarProcedimientosUA(Long id) throws DelegateException {
		return impl.listarProcedimientosUA(id);
	}
	
	public List listarProcedimientosUO(Long id, Integer conse) throws DelegateException {
		return impl.listarProcedimientosUO(id, conse);
	}
	
	public ModelFilterObject obtenerFilterObject(ProcedimientoLocal proc) throws DelegateException {
		return impl.obtenerFilterObject(proc);
	}
	
	public ProcedimientoLocal obtenerProcedimiento(Long id) throws DelegateException {
		return impl.obtenerProcedimiento(id);
	}
	
	public ProcedimientoLocal obtenerProcedimientoNewBack(Long id) throws DelegateException {
		return impl.obtenerProcedimientoNewBack(id);
	}
	
	public void actualizarOrdenTramites(ArrayList<Long> tramitesId) throws DelegateException {
		 impl.actualizarOrdenTramites(tramitesId);	
	}
    
    public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
    	return impl.buscarProcedimientosActivos(listaUnidadAdministrativaId, fechaCaducidad);
    }
    
    public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
    	return impl.buscarProcedimientosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
    }
    
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(Long idUA, String[] codEstMat, String[] codEstHV) throws DelegateException {
		return impl.listarProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
	}
	
	public ResultadoBusqueda buscadorProcedimientos(BuscadorProcedimientoCriteria bc) throws DelegateException {
		return impl.buscadorProcedimientos(bc);
	}
}
