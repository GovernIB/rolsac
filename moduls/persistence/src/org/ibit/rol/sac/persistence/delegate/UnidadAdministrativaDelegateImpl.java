package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadAdministrativaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Unidades Administrativas.
 */
public class UnidadAdministrativaDelegateImpl implements StatelessDelegate, UnidadAdministrativaDelegateI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 234459285681808474L;

	/* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarHijosUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarHijosUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarDescendientesConse(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarDescendientesConse(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarDescendientesConse(java.lang.String)
	 */
    public List listarDescendientesConse(String id) throws DelegateException {
        try {
            return getFacade().listarDescendientesConse(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz()
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarTodasUnidadesAdministrativasRaiz()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarTodasUnidadesAdministrativasRaiz()
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerPrimeraUnidadAdministrativaRaiz()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerPrimeraUnidadAdministrativaRaiz()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerPrimeraUnidadAdministrativaRaiz()
	 */
    public UnidadAdministrativa obtenerPrimeraUnidadAdministrativaRaiz() throws DelegateException {
        try {
            return getFacade().obtenerPrimeraUnidadAdministrativaRaiz();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz(boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz(boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarUnidadesAdministrativasRaiz(boolean)
	 */
    public List listarUnidadesAdministrativasRaiz(boolean publicadas) throws DelegateException {
        try {
            return getFacade().listarUnidadesAdministrativasRaiz(publicadas);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativa(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativa(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativaAcceso(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativaAcceso(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarPadresUnidadAdministrativaAcceso(java.lang.Long)
	 */
    public List listarPadresUnidadAdministrativaAcceso(Long id) throws DelegateException {
        try {
            return getFacade().listarPadresUnidadAdministrativaAcceso(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarBusinessKey()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarBusinessKey()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarBusinessKey()
	 */
    public List listarBusinessKey() throws DelegateException {
        try {
            return getFacade().listarBusinessKey();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativas(java.util.Map, java.util.Map)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativas(java.util.Map, java.util.Map)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#buscarUnidadesAdministrativas(java.util.Map, java.util.Map)
	 */
    public List buscarUnidadesAdministrativas(Map parametros, Map traduccion) throws DelegateException {
        try {
            return getFacade().buscarUnidadesAdministrativas(parametros, traduccion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativa(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativa(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#informacionGeneral(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#informacionGeneral(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#informacionGeneral(java.lang.Long)
	 */
    public UnidadAdministrativa informacionGeneral(Long id) throws DelegateException {
        try {
            return getFacade().informacionGeneral(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUAPormad(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUAPormad(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#consultarUAPormad(java.lang.Long)
	 */
    public UnidadAdministrativa consultarUAPormad(Long id) throws DelegateException {
        try {
            return getFacade().consultarUAPormad(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativa(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativa(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPORMAD(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPORMAD(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#consultarUnidadAdministrativaPORMAD(java.lang.Long)
	 */
    public UnidadAdministrativa consultarUnidadAdministrativaPORMAD(Long id) throws DelegateException {
        try {
            return getFacade().consultarUnidadAdministrativaPORMAD(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorNombre(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorNombre(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerUnidadAdministrativaPorNombre(java.lang.String)
	 */
    public UnidadAdministrativa obtenerUnidadAdministrativaPorNombre(String nombre) throws DelegateException {
        try {
            return getFacade().obtenerUnidadAdministrativaPorNombre(nombre);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoPequenyaUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoPequenyaUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoGrandeUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoGrandeUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoHorizontalUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoHorizontalUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoVerticalUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoVerticalUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cambiarOrden(java.lang.Long, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cambiarOrden(java.lang.Long, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#cambiarOrden(java.lang.Long, java.lang.Long)
	 */
    public void cambiarOrden(Long ua1_id, Long ua2_id) throws DelegateException {
        try {
            getFacade().cambiarOrden(ua1_id, ua2_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#anyadirEdificio(java.lang.Long, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#anyadirEdificio(java.lang.Long, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#anyadirEdificio(java.lang.Long, java.lang.Long)
	 */
    public void anyadirEdificio(Long edificio_id, Long ua_id) throws DelegateException {
        try {
            getFacade().anyadirEdificio(edificio_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#eliminarEdificio(java.lang.Long, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#eliminarEdificio(java.lang.Long, java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#eliminarEdificio(java.lang.Long, java.lang.Long)
	 */
    public void eliminarEdificio(Long edificio_id, Long ua_id) throws DelegateException {
        try {
            getFacade().eliminarEdificio(edificio_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

   /* public void anyadirMateria(Long materia_id, Long ua_id) throws DelegateException {
        try {
            getFacade().anyadirMateria(materia_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }*/

    /*public void eliminarMateria(Long materia_id, Long ua_id) throws DelegateException {
        try {
            getFacade().eliminarMateria(materia_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }*/

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadPorBusinessKey(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadPorBusinessKey(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerUnidadPorBusinessKey(java.lang.String)
	 */
    public UnidadAdministrativa obtenerUnidadPorBusinessKey(String bk) throws DelegateException {
        try {
            return getFacade().obtenerUnidadPorBusinessKey(bk);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarUnidadAdministrativa(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadAdministrativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }

    public void borrarUnidadAdministrativaRaiz(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadAdministrativaRaiz(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#fijarBusinessKey(java.lang.Long, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#fijarBusinessKey(java.lang.Long, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#fijarBusinessKey(java.lang.Long, java.lang.String)
	 */
    public void fijarBusinessKey(Long id, String businessKey) throws DelegateException {
        try {
            getFacade().fijarBusinessKey(id, businessKey);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarBusinessKey(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarBusinessKey(java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#borrarBusinessKey(java.lang.String)
	 */
    public boolean borrarBusinessKey(String businessKey) throws DelegateException {
        try {
            return getFacade().borrarBusinessKey(businessKey);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidad(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidad(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#cargarArbolUnidad(java.lang.Long)
	 */
    public UnidadAdministrativa cargarArbolUnidad(Long id) throws DelegateException {
        try {
            return getFacade().cargarArbolUnidad(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

     /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidadId(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidadId(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#repararOrdenFichasUA()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#repararOrdenFichasUA()
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#repararOrdenFichasUA()
	 */
    public void repararOrdenFichasUA() throws DelegateException {
    	 try {
             getFacade().repararOrdenFichasUA();
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
     }

     /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasPorAmbito(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasPorAmbito(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarUnidadesAdministrativasPorAmbito(java.lang.Long)
	 */
    public List<UnidadAdministrativa> listarUnidadesAdministrativasPorAmbito(Long ambito) throws DelegateException {
         try {
             return getFacade().listarUnidadesAdministrativasPorAmbito(ambito);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
    }
     
     /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativasPorAmbito(java.lang.Long, java.lang.String, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativasPorAmbito(java.lang.Long, java.lang.String, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#buscarUnidadesAdministrativasPorAmbito(java.lang.Long, java.lang.String, java.lang.String)
	 */
    public List<UnidadAdministrativa> buscarUnidadesAdministrativasPorAmbito(Long ambito, final String busqueda, final String idioma) throws DelegateException {
    	 try {
    		 return getFacade().buscarUnidadesAdministrativasPorAmbito(ambito,busqueda,idioma);
    	 } catch (RemoteException e) {
    		 throw new DelegateException(e);
    	 }
     }


    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUAEspacioTerritorial(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUAEspacioTerritorial(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarUAEspacioTerritorial(java.lang.Long)
	 */
    public List<UnidadAdministrativa> listarUAEspacioTerritorial(Long idEspacio) throws DelegateException {
        try {
            return getFacade().listarUAEspacioTerritorial(idEspacio);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String, java.util.List)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String, java.util.List)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String, java.util.List)
	 */
    public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(Long idEspacio, String tipo, List UAOpcionales) throws DelegateException {
         try {
             return getFacade().obtenerUnidadesAdministrativasArbreTerritorial(idEspacio,tipo, UAOpcionales);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
    }
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String)
	 */
    public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(Long idEspacio, String tipo)throws DelegateException{
         try {
             return getFacade().obtenerUnidadesAdministrativasArbreTerritorial(idEspacio,tipo);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscar(java.lang.String, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscar(java.lang.String, java.lang.String)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#buscar(java.lang.String, java.lang.String)
	 */
    public List<UnidadAdministrativa> buscar(final String busqueda, final String idioma) throws DelegateException {
    	try {
            return getFacade().buscar(busqueda,idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecMat(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecMat(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarProcFichSecMat(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public Map<String,List<?>> listarProcFichSecMat(final Long idUA, final Long idMat, final String ceSec) throws DelegateException {
    	try {
            return getFacade().listarProcFichSecMat(idUA,idMat,ceSec);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecMat(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecMat(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarFichSecMat(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	public List<Ficha> listarFichSecMat(final Long idUA, final Long idMat, final String ceSec,boolean caducados) throws DelegateException {
    	try {
            return getFacade().listarFichSecMat(idUA,idMat,ceSec,caducados);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcMat(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcMat(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarProcMat(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
    public List<ProcedimientoLocal> listarProcMat(final Long idUA, final Long idMat,final String[] codMat, boolean include,boolean caducados) throws DelegateException {
    	try {
            return getFacade().listarProcMat(idUA,idMat,codMat, include,caducados);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }


	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecHV(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecHV(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarProcFichSecHV(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public Map<String,List<?>> listarProcFichSecHV(final Long idUA, final Long idHV, final String ceSec) throws DelegateException {
    	try {
            return getFacade().listarProcFichSecHV(idUA,idHV,ceSec);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecHV(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecHV(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarFichSecHV(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
    public List<Ficha> listarFichSecHV(final Long idUA, final Long idHV, final String ceSec,boolean caducados) throws DelegateException {
    	try {
            return getFacade().listarFichSecHV(idUA,idHV,ceSec,caducados);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcHV(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcHV(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#listarProcHV(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
    public List<ProcedimientoLocal> listarProcHV(final Long idUA, final Long idHV,final String[] codMat, boolean include,boolean caducados) throws DelegateException {
    	try {
            return getFacade().listarProcHV(idUA,idHV,codMat, include,caducados);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarProcedimientosUA(java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarProcedimientosUA(java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#buscarProcedimientosUA(java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
    public List<ProcedimientoLocal> buscarProcedimientosUA(final Long idUA, final String busqueda, final String idioma,final Date dataInici, final Date dataFi ) throws DelegateException {
    	try {
    		return getFacade().buscarProcedimientosUA(idUA,busqueda,idioma,dataInici,dataFi);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /**
     * * Busca todos los {@link ficha} que contengan la palabra de la busqueda que
	 * esten relacionados con una {@link UnidadAdministrativa}
     */
    public Set<Ficha> buscarFichasUA(final String busqueda, final String idioma,final Date dataInici, final Date dataFi ) throws DelegateException {
    	try {
    		return getFacade().buscarFichasUA(busqueda, idioma, dataInici, dataFi);
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#indexBorraUA(java.lang.Long)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#indexBorraUA(java.lang.Long)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFilterObject(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFilterObject(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
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
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMolla(java.lang.Long, java.lang.String)
	 */    
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMolla(java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#getUaMolla(java.lang.Long, java.lang.String)
	 */
	public StringBuffer getUaMolla(Long idua, String _idioma) throws DelegateException {
	       try {
	            return getFacade().getUaMolla(idua, _idioma);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}	
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMollaBack2java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */    
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMollaBack2(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
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

     public void reordenar( Long id, Integer ordenNuevo, Integer ordenAnterior, Long idPadre) 
    		 throws DelegateException {
     	try {
     		getFacade().reordenar(id, ordenNuevo, ordenAnterior, idPadre);
     	} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
     }
     
	 /**
	 * Obtiene una Unidad Administrativa {PORMAD}
	 * */
    public UnidadAdministrativa obtenerUnidadAdministrativaPM(Long id) throws DelegateException {
        try {
            return getFacade().obtenerUnidadAdministrativaPM(id);
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

	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(Long id)
			throws DelegateException {
		
		try {
     		return getFacade().consultarUnidadAdministrativaSinFichas(id);
     	} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
		
	}

	public List<Seccion> listarSeccionesUA(Long idUA) 
			throws DelegateException {
		
		try {
     		return getFacade().listarSeccionesUA(idUA);
     	} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
		
	}

	public Long cuentaFichasSeccionUA(Long idUA, Long idSeccion)
			throws DelegateException {
		
		try {
     		return getFacade().cuentaFichasSeccionUA(idUA, idSeccion);
     	} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
		
	}

	public List<FichaDTO> listarFichasSeccionUA(Long idUA, Long idSeccion)
			throws DelegateException {
		
		try {
			return getFacade().listarFichasSeccionUA(idUA, idSeccion);
		} catch (RemoteException e) {
     		throw new DelegateException(e);
     	}
		
	}

	public void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<Long> fichasParaActualizar) throws DelegateException {
		
		try {
			getFacade().actualizaFichasSeccionUA(idUA, idSeccion, fichasParaActualizar);
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
	
}
