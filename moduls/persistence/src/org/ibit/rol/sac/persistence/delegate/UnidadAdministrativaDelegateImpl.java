package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadAdministrativaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/**
 * Business delegate para manipular Unidades Administrativas.
 */
public class UnidadAdministrativaDelegateImpl implements StatelessDelegate, UnidadAdministrativaDelegateI {
	
	private static final long serialVersionUID = 234459285681808474L;
	
	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	public Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad) throws DelegateException {
		try {
			return getFacade().crearUnidadAdministrativaRaiz(unidad);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	public Long crearUnidadAdministrativa(UnidadAdministrativa unidad, Long padre_id) throws DelegateException {
		try {
			return getFacade().crearUnidadAdministrativa(unidad, padre_id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad, Long padre_id) throws DelegateException {
		try {
			getFacade().actualizarUnidadAdministrativa(unidad, padre_id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#actualizarEdificiosUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.List<java.lang.Long>)
	 */
	public void actualizarEdificiosUnidadAdministrativa(UnidadAdministrativa unidad, List<Long> idsNuevosEdificios) throws DelegateException {
		try {
			getFacade().actualizarEdificiosUnidadAdministrativa(unidad, idsNuevosEdificios);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#actualizarUsuariosUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.List<java.lang.Long>)
	 */
	public void actualizarUsuariosUnidadAdministrativa(UnidadAdministrativa unidad, List<Long> idsNuevosUsuarios) throws DelegateException {
		try {
			getFacade().actualizarUsuariosUnidadAdministrativa(unidad, idsNuevosUsuarios);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarHijosUA(java.lang.Long)
	 */
	public List listarHijosUA(Long id) throws DelegateException {
		try {
			return getFacade().listarHijosUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarUnidadesAdministrativasRaiz()
	 */
	public List listarUnidadesAdministrativasRaiz() throws DelegateException {
		try {
			return getFacade().listarUnidadesAdministrativasRaiz();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarTodasUnidadesAdministrativasRaiz()
	 */
	public List listarTodasUnidadesAdministrativasRaiz() throws DelegateException {
		try {
			return getFacade().listarTodasUnidadesAdministrativasRaiz();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarPadresUnidadAdministrativa(java.lang.Long)
	 */
	public List listarPadresUnidadAdministrativa(Long id) throws DelegateException {
		try {
			return getFacade().listarPadresUnidadAdministrativa(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerUnidadAdministrativa(java.lang.Long)
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativa(Long id) throws DelegateException {
		try {
			return getFacade().obtenerUnidadAdministrativa(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#consultarUnidadAdministrativa(java.lang.Long)
	 */
	public UnidadAdministrativa consultarUnidadAdministrativa(Long id) throws DelegateException {
		try {
			return getFacade().consultarUnidadAdministrativa(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
	public UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id) throws DelegateException {
		try {
			return getFacade().consultarUnidadAdministrativaPMA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(String codEst) throws DelegateException {
		try {
			return getFacade().obtenerUnidadAdministrativaPorCodEstandar(codEst);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodDir3(String codDir3,boolean inicializar) throws DelegateException {
		try {
			return getFacade().obtenerUnidadAdministrativaPorCodDir3(codDir3,inicializar);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
		
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerFotoPequenyaUA(java.lang.Long)
	 */
	public Archivo obtenerFotoPequenyaUA(Long id) throws DelegateException {
		try {
			return getFacade().obtenerFotoPequenyaUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerFotoGrandeUA(java.lang.Long)
	 */
	public Archivo obtenerFotoGrandeUA(Long id) throws DelegateException {
		try {
			return getFacade().obtenerFotoGrandeUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerLogoHorizontalUA(java.lang.Long)
	 */
	public Archivo obtenerLogoHorizontalUA(Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoHorizontalUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerLogoVerticalUA(java.lang.Long)
	 */
	public Archivo obtenerLogoVerticalUA(Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoVerticalUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerLogoSaludoUA(java.lang.Long)
	 */
	public Archivo obtenerLogoSaludoUA(Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoSaludoUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
	public Archivo obtenerLogoSaludoVerticalUA(Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoSaludoVerticalUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#cargarArbolUnidadId(java.lang.Long)
	 */
	public List cargarArbolUnidadId(Long id) throws DelegateException {
		try {
			return getFacade().cargarArbolUnidadId(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	

	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#getUaMollaBack2(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	public StringBuffer getUaMollaBack2(Long idua, String _idioma, String url, String uaIdPlaceholder) throws DelegateException {
		try {
			return getFacade().getUaMollaBack2(idua, _idioma, url, uaIdPlaceholder);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public boolean autorizarEliminarUA(Long idUa) throws DelegateException {
		try {
			return getFacade().autorizarEliminarUA(idUa);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * Descripcion: Comprobar si el usuario tiene privilegios para crear una UA.
	 */
	public Boolean autorizarCrearUA() throws DelegateException {
		try {
			return getFacade().autorizarCrearUA();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public String obtenerCadenaFiltroUA(Long ua, boolean uaFilles, boolean uaMeves) throws DelegateException {
		try {
			return getFacade().obtenerCadenaFiltroUA( ua, uaFilles, uaMeves );
		}  catch (RemoteException e) {
			throw new DelegateException(e);
		}  	
	}
	
	
	public String obtenerCadenaFiltroUAPorDir3(String codigoDir3UA, boolean uaFilles, boolean uaMeves) throws DelegateException {
		try {
			return getFacade().obtenerCadenaFiltroUAPorDir3( codigoDir3UA, uaFilles, uaMeves );
		}  catch (RemoteException e) {
			throw new DelegateException(e);
		}  	
	}
	
	public void eliminarUaSinRelaciones(Long idUA) throws DelegateException {
		try {
			getFacade().eliminarUaSinRelaciones(idUA);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior, Long idPadre) throws DelegateException {
		try {
			getFacade().reordenar(id, ordenNuevo, ordenAnterior, idPadre);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public ResultadoBusqueda buscadorUnidadesAdministrativas(Map<String, Object> parametros, Map<String, String> traduccion, Long id, String idioma, boolean uaFilles, boolean uaMeves, Long materia, String pagina, String resultats) throws DelegateException {
		try {
			return getFacade().buscadorUnidadesAdministrativas(parametros, traduccion, id, idioma, uaFilles, uaMeves, materia, pagina, resultats);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(Long id) throws DelegateException {
		try {
			return getFacade().consultarUnidadAdministrativaSinFichas(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public List<Seccion> listarSeccionesUA(Long idUA) throws DelegateException {
		try {
			return getFacade().listarSeccionesUA(idUA);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public int cuentaFichasSeccionUA(Long idUA, Long idSeccion) throws DelegateException {
		try {
			return getFacade().cuentaFichasSeccionUA(idUA, idSeccion);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public List<FichaDTO> listarFichasSeccionUA(Long idUA, Long idSeccion, String idioma, PaginacionCriteria paginacion) throws DelegateException {
		try {
			return getFacade().listarFichasSeccionUA(idUA, idSeccion, idioma, paginacion);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public List<FichaDTO> listarFichasSeccionUASinPaginacion(Long idUA, Long idSeccion, String idioma) throws DelegateException {
		try {
			return getFacade().listarFichasSeccionUASinPaginacion(idUA, idSeccion, idioma);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<FichaDTO> fichas) throws DelegateException {
		try {
			getFacade().actualizaFichasSeccionUA(idUA, idSeccion, fichas);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void eliminarFotoGrande(Long idUA) throws DelegateException
	{
		try {
			getFacade().eliminarFotoGrande(idUA);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	
	public void eliminarFotoPetita(Long idUA) throws DelegateException
	{
		try {
			getFacade().eliminarFotoPetita(idUA);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	
	public void eliminarLogoHorizontal(Long idUA) throws DelegateException
	{
		try {
			getFacade().eliminarLogoHorizontal(idUA);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	
	public void eliminarLogoVertical(Long idUA) throws DelegateException
	{
		try {
			getFacade().eliminarLogoVertical(idUA);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	
	public void eliminarLogoSalutacio(Long idUA) throws DelegateException
	{
		try {
			getFacade().eliminarLogoSalutacio(idUA);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	
	public void eliminarLogoTipos(Long idUA) throws DelegateException
	{
		try {
			getFacade().eliminarLogoTipos(idUA);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	
	public void eliminarSeccionUA(Long idUA, Long idSeccion) throws DelegateException
	{
		try {
			getFacade().eliminarSeccionUA(idUA, idSeccion);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
	}
	

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().indexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException {
    	try {
    		return getFacade().indexarSolr(solrIndexer , idElemento, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
    		return getFacade().desindexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public List<Long> obtenerHijosUnidadAdministrativa(Long idUA) throws DelegateException {
    	try {
    		return getFacade().obtenerHijosUnidadAdministrativa(idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ResultadoBusqueda consultaUnidadesAdministrativas(FiltroGenerico filtro) throws DelegateException {
    	try {
    		return getFacade().consultaUnidadesAdministrativas(filtro);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    } 
   
    
    public String consultaCodigoDir3(Long id) throws DelegateException {
    	try {
    		return getFacade().consultaCodigoDir3(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	} 
    
    
	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */
	
	private Handle facadeHandle;
	
	private UnidadAdministrativaFacade getFacade() throws RemoteException {
		return (UnidadAdministrativaFacade) facadeHandle.getEJBObject();
	}
	
	protected UnidadAdministrativaDelegateImpl() throws DelegateException {
		try {
			UnidadAdministrativaFacadeHome home = UnidadAdministrativaFacadeUtil.getHome();
			UnidadAdministrativaFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (NamingException e) {
			throw new DelegateException(e);
		} catch (CreateException e) {
			throw new DelegateException(e);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public List<Long> buscarIdsUas() throws DelegateException {
		try {
            return getFacade().buscarIdsUas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

	public String checkProcedimientosUA(Long id) throws DelegateException {
		try {
            return getFacade().checkProcedimientosUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
}
