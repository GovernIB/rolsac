package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.intf.FichaFacade;
import org.ibit.rol.sac.persistence.intf.FichaFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaFacadeUtil;

import es.caib.rolsac.lucene.model.ModelFilterObject;
import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular fichas.
 */
public class FichaDelegateImpl implements StatelessDelegate, FichaDelegateI {

	private static final long serialVersionUID = 1L;

	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public void actualizaEnlacesFicha(Long id, List<Enlace> enlacesNuevos, List<Enlace> enlacesAEliminar) throws DelegateException {
		try {
            getFacade().actualizaEnlacesFicha(id, enlacesNuevos, enlacesAEliminar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#autorizaCrearFicha(java.lang.Integer)
	 */
    public boolean autorizaCrearFicha(Integer validacionNormativa) throws DelegateException {
        try {
            return getFacade().autorizaCrearFicha(validacionNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#autorizaModificarFicha(java.lang.Long)
	 */
    public boolean autorizaModificarFicha(Long idFicha) throws DelegateException {
        try {
            return getFacade().autorizaModificarFicha(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    
    
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#grabarFicha(org.ibit.rol.sac.model.Ficha)
	 */
	public Long grabarFicha(Ficha ficha) throws DelegateException {
        try {
            return getFacade().grabarFicha(ficha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    /* (non-Javadoc)
     * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#buscarFichas(java.util.Map, java.util.Map, org.ibit.rol.sac.model.UnidadAdministrativa, boolean, boolean)
     */
    public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible) throws DelegateException {
        try {
            return getFacade().buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles, uaMeves, campoOrdenacion, orden, pagina, resultats, campoVisible);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
     * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#buscarFichas(java.util.Map, java.util.String, org.ibit.rol.sac.model.UnidadAdministrativa, boolean)
     */
    public List buscarFichas(Map parametros, String traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic,  boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden) throws DelegateException {
        try {
            return getFacade().buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles, uaMeves, campoOrdenacion, orden);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerFicha(java.lang.Long)
	 */
    public Ficha obtenerFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerIconoFicha(java.lang.Long)
	 */
    public Archivo obtenerIconoFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIconoFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerImagenFicha(java.lang.Long)
	 */
    public Archivo obtenerImagenFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerImagenFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerBanerFicha(java.lang.Long)
	 */
    public Archivo obtenerBanerFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerBanerFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#borrarFicha(java.lang.Long)
	 */
    public void borrarFicha(Long id) throws DelegateException {
        try {
            getFacade().borrarFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#listarFichasSeccionUA(java.lang.Long, java.lang.String, java.lang.String[], java.lang.String[])
	 */
    @SuppressWarnings("unchecked")
	public List<Ficha> listarFichasSeccionUA(Long ua_id, String codEstSecc, String[] codEstHV, String[] codEstMat) throws DelegateException {
        try {
            return getFacade().listarFichasSeccionUA(ua_id, codEstSecc, codEstHV, codEstMat);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#listarFichasSeccionTodas(java.lang.Long)
	 */
    public List listarFichasSeccionTodas(Long id) throws DelegateException {
        try {
            return getFacade().listarFichasSeccionTodas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#crearFichaUA2(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
    public Long crearFichaUA2(Long unidad_id, Long seccion_id, Long ficha_id)
            throws DelegateException {
        try {
            return getFacade().crearFichaUA2(unidad_id, seccion_id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#borrarFichaUA(java.lang.Long)
	 */
    public void borrarFichaUA(Long id) throws DelegateException {
        try {
            getFacade().borrarFichaUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#indexInsertaFicha(org.ibit.rol.sac.model.Ficha, es.caib.rolsac.lucene.model.ModelFilterObject)
	 */
    public void indexInsertaFicha(Ficha fic, ModelFilterObject filter) throws DelegateException {
        try {
            getFacade().indexInsertaFicha(fic, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#indexBorraFicha(java.lang.Long)
	 */
    public void indexBorraFicha(Long id) throws DelegateException {
        try {
            getFacade().indexBorraFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerFilterObject(org.ibit.rol.sac.model.Ficha)
	 */
    public ModelFilterObject obtenerFilterObject(Ficha ficha) throws DelegateException {
        try {
            return getFacade().obtenerFilterObject(ficha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#getContenidos_web()
	 */
    public Hashtable getContenidos_web() throws DelegateException {
    	 try {
             return getFacade().getContenidos_web();
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
	}

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#setContenidos_web(java.util.Hashtable)
	 */
	public void setContenidos_web(Hashtable contenidos_web) throws DelegateException {
		 try {
             getFacade().setContenidos_web(contenidos_web);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#listFichasUA(java.lang.Long)
	 */
	public  List<FichaUA> listFichasUA(Long idFicha) throws DelegateException {
		 try {
           return getFacade().listFichasUA(idFicha);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	
	public Ficha obtenerFichaPMA(Long id)  throws DelegateException {
		 try {
	           return getFacade().obtenerFichaPMA(id);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}
	
	public int buscarFichasActivas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
		try {
	           return getFacade().buscarFichasActivas(listaUnidadAdministrativaId, fechaCaducidad);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}
	
	public int buscarFichasCaducadas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad )throws DelegateException {
		try {
	           return getFacade().buscarFichasCaducadas(listaUnidadAdministrativaId,fechaCaducidad);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}
	
	public void borrarFichasUAdeFicha(List<FichaUA> fichasUA) throws DelegateException {
		try {
			getFacade().borrarFichasUAdeFicha(fichasUA);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public Ficha obtenerFichaDeFichaUA(Long idFichaUA) throws DelegateException {
		try {
			return getFacade().obtenerFichaDeFichaUA(idFichaUA);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public Integer comprobarRelacionFicha(Long idFitxa) throws DelegateException {
		try {
			return getFacade().comprobarRelacionFicha(idFitxa);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private FichaFacade getFacade() throws RemoteException {
        return (FichaFacade) facadeHandle.getEJBObject();
    }

    protected FichaDelegateImpl() throws DelegateException {
        try {
            FichaFacadeHome home = FichaFacadeUtil.getHome();
            FichaFacade remote = home.create();
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
