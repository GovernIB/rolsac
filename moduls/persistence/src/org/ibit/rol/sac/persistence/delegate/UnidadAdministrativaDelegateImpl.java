package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ibit.rol.sac.model.Seccion;
import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadAdministrativaFacadeUtil;
import org.ibit.rol.sac.model.FichaResumenUA;
import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Unidades Administrativas.
 */
public class UnidadAdministrativaDelegateImpl implements StatelessDelegate, UnidadAdministrativaDelegateI
{
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
	
	/**
	 * Metodo que indexa una unidad administrativa
	 * @param ua Unidad administrativa
	 * @param filter filtro
	 * @throws DelegateException
	 */
	public void indexInsertaUA(UnidadAdministrativa ua, ModelFilterObject filter) throws DelegateException {
		try {
			getFacade().indexInsertaUA(ua, filter);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#indexBorraUA(java.lang.Long)
	 */
	public void indexBorraUA(Long id) throws DelegateException {
		try {
			getFacade().indexBorraUA(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerFilterObject(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	public ModelFilterObject obtenerFilterObject(UnidadAdministrativa ua) throws DelegateException {
		try {
			return getFacade().obtenerFilterObject(ua);
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
	
	public Long cuentaFichasSeccionUA(Long idUA, Long idSeccion) throws DelegateException {
		try {
			return getFacade().cuentaFichasSeccionUA(idUA, idSeccion);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public List<FichaResumenUA> listarFichasSeccionUA(Long idUA, Long idSeccion) throws DelegateException {
		try {
			return getFacade().listarFichasSeccionUA(idUA, idSeccion);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<Long> listaIdFichasLong) throws DelegateException {
		try {
			getFacade().actualizaFichasSeccionUA(idUA, idSeccion, listaIdFichasLong);
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
	
}
