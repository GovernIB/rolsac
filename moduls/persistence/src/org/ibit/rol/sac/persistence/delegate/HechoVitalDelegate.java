package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.persistence.intf.HechoVitalFacade;
import org.ibit.rol.sac.persistence.intf.HechoVitalFacadeHome;
import org.ibit.rol.sac.persistence.util.HechoVitalFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Hechos Vitales.
 */
public class HechoVitalDelegate implements StatelessDelegate {

	private static final long serialVersionUID = 3704892105290675900L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	public Long grabarHechoVital(HechoVital hechov) throws DelegateException {
		try {
			return getFacade().grabarHechoVital(hechov);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public void subirOrden(Long id) throws DelegateException {
		try {
			getFacade().subirOrden(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public ResultadoBusqueda listarHechosVitales(int pagina, int resultados, String idioma) throws DelegateException {
		try {
			return getFacade().listarHechosVitales(pagina, resultados, idioma);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public List<HechoVital> listarHechosVitales() throws DelegateException {
		try {
			return getFacade().listarHechosVitales();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public List<HechoVital> listarHechosVitalesProcedimientos() throws DelegateException {
		try {
			return getFacade().listarHechosVitalesProcedimientos();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	@SuppressWarnings("unchecked")
	public List<HechoVital> listarHechoVitalAgrupacionHV(Long agru_id) throws DelegateException {
		try {
			return getFacade().listarHechosVitalesAgrupacionHV(agru_id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public HechoVital obtenerHechoVital(Long id) throws DelegateException {
		try {
			return getFacade().obtenerHechoVital(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public HechoVital obtenerHechoVitalPorNombre(String nombre) throws DelegateException {
		try {
			return getFacade().obtenerHechoVitalPorNombre(nombre);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public Archivo obtenerFoto(Long id) throws DelegateException {
		try {
			return getFacade().obtenerFoto(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public Archivo obtenerIcono(Long id) throws DelegateException {
		try {
			return getFacade().obtenerIcono(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public Archivo obtenerIconoGrande(Long id) throws DelegateException {
		try {
			return getFacade().obtenerIconoGrande(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public void borrarHechoVital(Long id) throws DelegateException {
		try {
			getFacade().borrarHechoVital(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public Archivo obtenerDistribComp(Long id, String lang, boolean useDefault) throws DelegateException {
		try {
			return getFacade().obtenerDistribComp(id, lang, useDefault);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public Archivo obtenerNormativa(Long id, String lang, boolean useDefault) throws DelegateException {
		try {
			return getFacade().obtenerNormativa(id, lang, useDefault);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public Archivo obtenerContenido(Long id, String lang, boolean useDefault) throws DelegateException {
		try {
			return getFacade().obtenerContenido(id, lang, useDefault);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	@SuppressWarnings("unchecked")
	public List<HechoVital> obtenerHechosVitalesCE(final String[] codigosEstandar) throws DelegateException{
		try {
			return getFacade().obtenerHechosVitalesCE(codigosEstandar);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public HechoVital obtenerHechoVitalCE(final String codigosEstandar) throws DelegateException{
		try {
			return getFacade().obtenerHechoVitalCE(codigosEstandar);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public Set<HechoVitalAgrupacionHV> obtenerGruposHechoVital(Long id_hechoVital) throws DelegateException{
		try {
			return getFacade().obtenerGruposHechoVital(id_hechoVital);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public List<HechoVital> buscar(final String busqueda, final String idioma) throws DelegateException {
		try {
			return getFacade().buscar(busqueda,idioma);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	
	public List buscarPorIds(List<Long> ids) throws DelegateException {
    	try {
            return getFacade().buscarPorIds(ids);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	
	public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior ) throws DelegateException {
		try {
			getFacade().reordenar(id, ordenNuevo, ordenAnterior);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	public List<HechoVital> listarHechosVitales(Set<?> publicosObjetivo, String idioma) throws DelegateException {

		try {

			return getFacade().listarHechosVitales(publicosObjetivo, idioma);

		} catch (RemoteException e) {

			throw new DelegateException(e);
		}

	}

	/* ========================================================= */
			/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private HechoVitalFacade getFacade() throws RemoteException {
		return (HechoVitalFacade) facadeHandle.getEJBObject();
	}

	protected HechoVitalDelegate() throws DelegateException {
		try {
			HechoVitalFacadeHome home = HechoVitalFacadeUtil.getHome();
			HechoVitalFacade remote = home.create();
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
