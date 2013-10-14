package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

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
		
	public boolean autorizaCrearProcedimiento(Integer validacionProcedimiento) throws DelegateException {
            return impl.autorizaCrearProcedimiento(validacionProcedimiento);
    }
	
    public boolean autorizaModificarProcedimiento(Long idProcedimiento) throws DelegateException {
            return impl.autorizaModificarProcedimiento(idProcedimiento);
    }     
    
    public boolean existeOtroTramiteInicioProcedimiento(Long procId, Long tramiteId) throws DelegateException {
        return impl.existeOtroTramiteInicioProcedimiento(procId, tramiteId);
    }
    
    public List buscarProcedimientosUATexto(Long idUnidad, String texto, String idioma) throws DelegateException {
        return impl.buscarProcedimientosUATexto(idUnidad, texto, idioma);
    }    

	public void actualizarOrdenPros(Map map) throws DelegateException {
		impl.actualizarOrdenPros(map);
	}

	public void actualizarOrdenPros2(Map map) throws DelegateException {
		impl.actualizarOrdenPros2(map);
	}

	public void actualizarOrdenPros3(Map map) throws DelegateException {
		impl.actualizarOrdenPros3(map);
	}

	public void anyadirMateria(Long materiaId, Long procId)
			throws DelegateException {
		impl.anyadirMateria(materiaId, procId);
	}

	public void anyadirNormativa(Long normId, Long proc)
			throws DelegateException {
		impl.anyadirNormativa(normId, proc);
	}

	public void anyadirTramite(Long tramiteId, Long procId)
			throws DelegateException {
		impl.anyadirTramite(tramiteId, procId);
	}

	public void borrarProcedimiento(Long id) throws DelegateException {
		impl.borrarProcedimiento(id);
	}

	public List<ProcedimientoLocal> buscar(String busqueda, String idioma)
			throws DelegateException {
		return impl.buscar(busqueda, idioma);
	}

	public List buscarProcedimientos(Map param, Map trad)
			throws DelegateException {
		return impl.buscarProcedimientos(param, trad);
	}
	
	public ResultadoBusqueda buscadorProcedimientos(Map parametros, Map traduccion, UnidadAdministrativa ua, boolean uaFilles, boolean uaMeves, Long materia, Long fetVital, Long publicObjectiu, String pagina, String resultats, int visible)
			throws DelegateException {
		return impl.buscadorProcedimientos(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital, publicObjectiu, pagina, resultats, visible);
	}

	public List buscarProcedimientosFamilia(Long id) throws DelegateException {
		return impl.buscarProcedimientosFamilia(id);
	}

	public List buscarProcedimientosMateria(Long id) throws DelegateException {
		return impl.buscarProcedimientosMateria(id);
	}

	public List buscarProcedimientosTexto(String texto)
			throws DelegateException {
		return impl.buscarProcedimientosTexto(texto);
	}

	public List buscarProcedimientosUATexto(Long idUnidad, String texto)
			throws DelegateException {
		return impl.buscarProcedimientosUATexto(idUnidad, texto);
	}

	public ProcedimientoLocal consultarProcedimiento(Long id)
			throws DelegateException {
		return impl.consultarProcedimiento(id);
	}

	public void eliminarMateria(Long materiaId, Long procId)
			throws DelegateException {
		impl.eliminarMateria(materiaId, procId);
	}

	public void eliminarNormativa(Long normId, Long procId)
			throws DelegateException {
		impl.eliminarNormativa(normId, procId);
	}

	public void eliminarTramite(Long tramiteId, Long procId)
			throws DelegateException {
		impl.eliminarTramite(tramiteId, procId);
	}

	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA)
			throws DelegateException {
		return impl.grabarProcedimiento(procedimiento, idUA);
	}

	public void indexBorraProcedimiento(ProcedimientoLocal pro)
			throws DelegateException {
		impl.indexBorraProcedimiento(pro);
	}

	public void indexInsertaProcedimiento(ProcedimientoLocal proc,
			ModelFilterObject filter) throws DelegateException {
		impl.indexInsertaProcedimiento(proc, filter);
	}

	public List listarProcedimientosPublicos() throws DelegateException {
		return impl.listarProcedimientosPublicos();
    }
	
	public List listarProcedimientos() throws DelegateException {
		return impl.listarProcedimientos();
	}

	public List listarProcedimientosHechoVital(Long hechoId)
			throws DelegateException {
		return impl.listarProcedimientosHechoVital(hechoId);
	}

	public List listarProcedimientosMateriaUA(Long materiaId, Long uaId)
			throws DelegateException {
		return impl.listarProcedimientosMateriaUA(materiaId, uaId);
	}

	public List listarProcedimientosPublicosHechoVital(Long id)
			throws DelegateException {
		return impl.listarProcedimientosPublicosHechoVital(id);
	}

	public List listarProcedimientosPublicosUA(Long id)
			throws DelegateException {
		return impl.listarProcedimientosPublicosUA(id);
	}

	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(
			Long idUA, String[] codEstMat, String[] codEstHV)
			throws DelegateException {
		return impl.listarProcedimientosPublicosUAHVMateria(idUA, codEstMat,
				codEstHV);
	}

	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(
			Long idUA, String[] codEstMat, String[] codEstHV)
			throws DelegateException {
		return impl.listarIdsProcedimientosPublicosUAHVMateria(idUA, codEstMat,
				codEstHV);
	}

	public List listarProcedimientosUA(Long id) throws DelegateException {
		return impl.listarProcedimientosUA(id);
	}

	public List listarProcedimientosUO(Long id, Integer conse)
			throws DelegateException {
		return impl.listarProcedimientosUO(id, conse);
	}

	public ModelFilterObject obtenerFilterObject(ProcedimientoLocal proc)
			throws DelegateException {
		return impl.obtenerFilterObject(proc);
	}
	
	public ProcedimientoLocal obtenerProcedimiento(Long id)
			throws DelegateException {
		return impl.obtenerProcedimiento(id);
	}
	
	public ProcedimientoLocal obtenerProcedimientoNewBack(Long id)
			throws DelegateException {
		return impl.obtenerProcedimientoNewBack(id);
	}
	
	public void actualizarOrdenTramites(HashMap params) 
			throws DelegateException {
		 impl.actualizarOrdenTramites(params);	
	}
	
    public ProcedimientoLocal obtenerProcedimientoPM(Long id) throws DelegateException {
    	return impl.obtenerProcedimientoPM(id);
    }
        
    public int buscarProcedimientosActivos(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
    	return impl.buscarProcedimientosActivos(listaUnidadAdministrativaId, fechaCaducidad);
    }
    
    public int buscarProcedimientosCaducados(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
    	return impl.buscarProcedimientosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
    }    
        
}
