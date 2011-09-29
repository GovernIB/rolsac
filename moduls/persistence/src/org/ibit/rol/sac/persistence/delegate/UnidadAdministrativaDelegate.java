package org.ibit.rol.sac.persistence.delegate;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.webcaib.UOMinModel;
import org.ibit.rol.sac.model.webcaib.UOModel;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class UnidadAdministrativaDelegate {

	UnidadAdministrativaDelegateI impl = null;


	public UnidadAdministrativaDelegateI getImpl() {
		return impl;
    }

	public void setImpl(UnidadAdministrativaDelegateI impl) {
		this.impl = impl;
    }


	public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad,
			Long padreId) throws DelegateException {
		impl.actualizarUnidadAdministrativa(unidad, padreId);
    }
    
	public void anyadirEdificio(Long edificioId, Long uaId)
			throws DelegateException {
		impl.anyadirEdificio(edificioId, uaId);
    }
    
	public boolean borrarBusinessKey(String businessKey)
			throws DelegateException {
		return impl.borrarBusinessKey(businessKey);
    }

	public List<UnidadAdministrativa> buscar(String busqueda, String idioma)
			throws DelegateException {
		return impl.buscar(busqueda, idioma);
    }

	public List<ProcedimientoLocal> buscarProcedimientosUA(Long idUA,
			String busqueda, String idioma, Date dataInici, Date dataFi)
			throws DelegateException {
		return impl.buscarProcedimientosUA(idUA, busqueda, idioma, dataInici,
				dataFi);
    }

	public List buscarUnidadesAdministrativas(Map parametros, Map traduccion)
			throws DelegateException {
		return impl.buscarUnidadesAdministrativas(parametros, traduccion);
    }
    
	public List<UnidadAdministrativa> buscarUnidadesAdministrativasPorAmbito(
			Long ambito, String busqueda, String idioma)
			throws DelegateException {
		return impl.buscarUnidadesAdministrativasPorAmbito(ambito, busqueda,
				idioma);
    }
    
	public void cambiarOrden(Long ua1Id, Long ua2Id) throws DelegateException {
		impl.cambiarOrden(ua1Id, ua2Id);
    }

	public UnidadAdministrativa cargarArbolUnidad(Long id)
			throws DelegateException {
		return impl.cargarArbolUnidad(id);
    }

	public List cargarArbolUnidadId(Long id) throws DelegateException {
		return impl.cargarArbolUnidadId(id);
    }

	public UnidadAdministrativa consultarUAPormad(Long id)
			throws DelegateException {
		return impl.consultarUAPormad(id);
    }

	public UnidadAdministrativa consultarUnidadAdministrativa(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativa(id);
    }

    public UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id)
	throws DelegateException {
		return impl.consultarUnidadAdministrativaPMA(id);
	}

	public UnidadAdministrativa consultarUnidadAdministrativaPORMAD(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativaPORMAD(id);
    }

	public Long crearUnidadAdministrativa(UnidadAdministrativa unidad,
			Long padreId) throws DelegateException {
		return impl.crearUnidadAdministrativa(unidad, padreId);
    }

	public Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad)
			throws DelegateException {
		return impl.crearUnidadAdministrativaRaiz(unidad);
    }

	public void eliminarEdificio(Long edificioId, Long uaId)
			throws DelegateException {
		impl.eliminarEdificio(edificioId, uaId);
    }

	public boolean equals(Object obj) {
		return impl.equals(obj);
    }

	public void fijarBusinessKey(Long id, String businessKey)
			throws DelegateException {
		impl.fijarBusinessKey(id, businessKey);
    }
  
	public StringBuffer getUaMolla(Long idua, String idioma)
			throws DelegateException {
		return impl.getUaMolla(idua, idioma);
    }
    
	public StringBuffer getUaMollaBack2(Long idua, String idioma, String url, String uaIdPlaceholder)
			throws DelegateException {
		return impl.getUaMollaBack2(idua, idioma, url, uaIdPlaceholder);
	}
		
	public int hashCode() {
		return impl.hashCode();
    }
    
	public void indexBorraUA(Long id) throws DelegateException {
		impl.indexBorraUA(id);
    }

	public void indexInsertaUA(UnidadAdministrativa ua, ModelFilterObject filter)
			throws DelegateException {
		impl.indexInsertaUA(ua, filter);
    }

	public UnidadAdministrativa informacionGeneral(Long id)
			throws DelegateException {
		return impl.informacionGeneral(id);
    }

	public List listarBusinessKey() throws DelegateException {
		return impl.listarBusinessKey();
    }

	public List listarDescendientesConse(String id) throws DelegateException {
		return impl.listarDescendientesConse(id);
        }

	public List<Ficha> listarFichSecHV(Long idUA, Long idHV, String ceSec,
			boolean caducados) throws DelegateException {
		return impl.listarFichSecHV(idUA, idHV, ceSec, caducados);
        }

	public List<Ficha> listarFichSecMat(Long idUA, Long idMat, String ceSec,
			boolean caducados) throws DelegateException {
		return impl.listarFichSecMat(idUA, idMat, ceSec, caducados);
    }

	public List listarHijosUA(Long id) throws DelegateException {
		return impl.listarHijosUA(id);
    }

	public List listarPadresUnidadAdministrativa(Long id)
			throws DelegateException {
		return impl.listarPadresUnidadAdministrativa(id);
    }

	public List listarPadresUnidadAdministrativaAcceso(Long id)
			throws DelegateException {
		return impl.listarPadresUnidadAdministrativaAcceso(id);
    }

	public Map<String, List<?>> listarProcFichSecHV(Long idUA, Long idHV,
			String ceSec) throws DelegateException {
		return impl.listarProcFichSecHV(idUA, idHV, ceSec);
    }

	public Map<String, List<?>> listarProcFichSecMat(Long idUA, Long idMat,
			String ceSec) throws DelegateException {
		return impl.listarProcFichSecMat(idUA, idMat, ceSec);
    }

	public List<ProcedimientoLocal> listarProcHV(Long idUA, Long idHV,
			String[] codMat, boolean include, boolean caducados)
			throws DelegateException {
		return impl.listarProcHV(idUA, idHV, codMat, include, caducados);
    }

	public List<ProcedimientoLocal> listarProcMat(Long idUA, Long idMat,
			String[] codMat, boolean include, boolean caducados)
			throws DelegateException {
		return impl.listarProcMat(idUA, idMat, codMat, include, caducados);
     }

	public List<UnidadAdministrativa> listarUAEspacioTerritorial(Long idEspacio)
			throws DelegateException {
		return impl.listarUAEspacioTerritorial(idEspacio);
    }
     
	public List<UnidadAdministrativa> listarUnidadesAdministrativasPorAmbito(
			Long ambito) throws DelegateException {
		return impl.listarUnidadesAdministrativasPorAmbito(ambito);
     }

	public List listarUnidadesAdministrativasRaiz() throws DelegateException {
		return impl.listarUnidadesAdministrativasRaiz();
    }

	public List listarUnidadesAdministrativasRaiz(boolean publicadas)
			throws DelegateException {
		return impl.listarUnidadesAdministrativasRaiz(publicadas);
    }
    
   /**
     * Autorización eliminar Unidad Administrativa.Devuleve true si tiene acceso.
     */
    public boolean autorizarEliminarUA(Long idUa) throws DelegateException {
    		return impl.autorizarEliminarUA(idUa);
    }

    /**
     * Eliminar una unidad administrativa. Se podra eliminar la UA, si no tiene elementos relacionados (Procedimientos,Normativas,etc
     * Antes de elminar la UA ha de validar que la UA no tenga relaciones.
     */
    public void eliminarUaSinRelaciones(Long idUA) throws DelegateException {
            impl.eliminarUaSinRelaciones(idUA);
    }

	public ModelFilterObject obtenerFilterObject(UnidadAdministrativa ua)
			throws DelegateException {
		return impl.obtenerFilterObject(ua);
    }

	public Archivo obtenerFotoGrandeUA(Long id) throws DelegateException {
		return impl.obtenerFotoGrandeUA(id);
    }

	public Archivo obtenerFotoPequenyaUA(Long id) throws DelegateException {
		return impl.obtenerFotoPequenyaUA(id);
    }

	public Archivo obtenerLogoHorizontalUA(Long id) throws DelegateException {
		return impl.obtenerLogoHorizontalUA(id);
    }

	public Archivo obtenerLogoSaludoUA(Long id) throws DelegateException {
		return impl.obtenerLogoSaludoUA(id);
    }
    
	public Archivo obtenerLogoSaludoVerticalUA(Long id)
			throws DelegateException {
		return impl.obtenerLogoSaludoVerticalUA(id);
    }

	public Archivo obtenerLogoVerticalUA(Long id) throws DelegateException {
		return impl.obtenerLogoVerticalUA(id);
    }
    
	public UnidadAdministrativa obtenerPrimeraUnidadAdministrativaRaiz()
			throws DelegateException {
		return impl.obtenerPrimeraUnidadAdministrativaRaiz();
    }
    
	public UnidadAdministrativa obtenerUnidadAdministrativa(Long id)
			throws DelegateException {
		return impl.obtenerUnidadAdministrativa(id);
        }

	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(
			String codEst) throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorCodEstandar(codEst);
    }     

	public UnidadAdministrativa obtenerUnidadAdministrativaPorNombre(
			String nombre) throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorNombre(nombre);
	        }

	public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(
			Long idEspacio, String tipo, List UAOpcionales)
			throws DelegateException {
		return impl.obtenerUnidadesAdministrativasArbreTerritorial(idEspacio,
				tipo, UAOpcionales);
	}	
	
	public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(
			Long idEspacio, String tipo) throws DelegateException {
		return impl.obtenerUnidadesAdministrativasArbreTerritorial(idEspacio,
				tipo);
	}
     
	public UnidadAdministrativa obtenerUnidadPorBusinessKey(String bk)
			throws DelegateException {
		return impl.obtenerUnidadPorBusinessKey(bk);
	}

    public UnidadAdministrativa obtenerUnidadAdministrativaPM(Long id)
			throws DelegateException {
		return impl.obtenerUnidadAdministrativaPM(id);
	}
    
	public void repararOrdenFichasUA() throws DelegateException {
		impl.repararOrdenFichasUA();
	}
    
	public String toString() {
		return impl.toString();
	}

	//WEBCAIB
	public String getCodiGovernIlles() throws DelegateException {
		return impl.getCodiGovernIlles();
	}
	
	public Collection unitatsOrganiquesFilles(String pare, String criteri, String idioma ) throws DelegateException {
		return impl.unitatsOrganiquesFilles(pare, criteri, idioma );
	}

	public UOModel getDetails(String codi, String idioma) throws DelegateException {
		return impl.getDetails(codi, idioma);
	}
	
	public UOMinModel getParent(String codi, String idioma) throws DelegateException {
		return impl.getParent(codi, idioma);
	}

	public boolean isMateriaInUo(String codi, String materia) throws DelegateException {
		return impl.isMateriaInUo(codi, materia);
	}
	
	public ByteArrayOutputStream getLogo( String coduo, String logo ) throws DelegateException {
		return impl.getLogo(coduo, logo);
	}
	
	public Collection centres( String codi, String idioma) throws DelegateException {
		return impl.centres(codi, idioma);
	}
	
	public Collection getUoByMateria( String pare, String materia, String idioma ) throws DelegateException {
		return impl.getUoByMateria(pare, materia, idioma);
	}
}
