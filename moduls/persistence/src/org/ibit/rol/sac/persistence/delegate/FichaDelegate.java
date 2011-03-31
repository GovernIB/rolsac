package org.ibit.rol.sac.persistence.delegate;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.persistence.intf.FichaFacade;
import org.ibit.rol.sac.persistence.intf.FichaFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaFacadeUtil;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular fichas.
 */
public class FichaDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public boolean autorizaCrearFicha(Integer validacionNormativa) throws DelegateException {
        try {
            return getFacade().autorizaCrearFicha(validacionNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public boolean autorizaModificarFicha(Long idFicha) throws DelegateException {
        try {
            return getFacade().autorizaModificarFicha(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    
    
    public Long grabarFicha(Ficha ficha) throws DelegateException {
        try {
            return getFacade().grabarFicha(ficha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarFichas(Map parametros, Map traduccion) throws DelegateException {
        try {
            return getFacade().buscarFichas(parametros, traduccion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List buscarFichas(String texto) throws DelegateException {
        try {
            return getFacade().buscarFichas(texto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }  

    public List listarFichas() throws DelegateException {
        try {
            return getFacade().listarFichas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarFichasCrawler() throws DelegateException {
        try {
            return getFacade().listarFichasCrawler();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /**
     * Lista todas las fichas y solo rellena el idioma por defecto. Los unicos atributos que se rellenan son id, titulo y url.
     * @return List de fichas
     * @throws DelegateException
     */
    public List listarFichasThin() throws DelegateException {
        try {
            return getFacade().listarFichasThin();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Ficha obtenerFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarFichasMateria(Long id) throws DelegateException {
        try {
            return getFacade().buscarFichasMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarFichasHuerfanas() throws DelegateException {
        try {
            return getFacade().buscarFichasHuerfanas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void anyadirMateria(Long materia_id, Long ficha_id) throws DelegateException {
        try {
            getFacade().anyadirMateria(materia_id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void eliminarMateria(Long materia_id, Long ficha_id) throws DelegateException {
        try {
            getFacade().eliminarMateria(materia_id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerIconoFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIconoFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerImagenFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerImagenFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerBanerFicha(Long id) throws DelegateException {
        try {
            return getFacade().obtenerBanerFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarFichasUnidad(Long unidad_id) throws DelegateException {
        try {
            return getFacade().listarFichasUnidad(unidad_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarFicha(Long id) throws DelegateException {
        try {
            getFacade().borrarFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarFichasSeccionUA(Long ua_id, Long seccion_id) throws DelegateException {
        try {
            return getFacade().listarFichasSeccionUA(ua_id, seccion_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<Ficha> listarFichasSeccionUA(Long ua_id, String codEstSecc, String[] codEstHV, String[] codEstMat) throws DelegateException {
        try {
            return getFacade().listarFichasSeccionUA(ua_id, codEstSecc, codEstHV, codEstMat);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarFichasSeccionUA(Long ua_id, String codEstSecc) throws DelegateException {
        try {
            return getFacade().listarFichasSeccionUA(ua_id, codEstSecc);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarFichasSeccion(Long id) throws DelegateException {
        try {
            return getFacade().listarFichasSeccion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public List listarFichasSeccionTodas(Long id) throws DelegateException {
        try {
            return getFacade().listarFichasSeccionTodas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /*PORMAD*/
    public List listarFichasSeccion(String codEstSecc,boolean caducados) throws DelegateException {
        try {
            return getFacade().listarFichasSeccion(codEstSecc,caducados);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Long crearFichaUA(Long unidad_id, Long seccion_id, Long ficha_id)
            throws DelegateException {
        try {
            return getFacade().crearFichaUA(unidad_id, seccion_id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void subirFichaUA(Long id) throws DelegateException {
        try {
            getFacade().subirFichaUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void actualizarOrdenFichasUA(Enumeration params, Map valores) throws DelegateException {
        try {
            getFacade().actualizarOrdenFichasUA(params, valores);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarFichaUA(Long id) throws DelegateException {
        try {
            getFacade().borrarFichaUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List buscarFichasHechoVital(Long id) throws DelegateException {
        try {
            return getFacade().buscarFichasHechoVital(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void anyadirHechoVital(Long id, Long ficha_id) throws DelegateException {
        try {
            getFacade().anyadirHechoVital(id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void eliminarHechoVital(Long id, Long ficha_id) throws DelegateException {
        try {
            getFacade().eliminarHechoVital(id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void indexInsertaFicha(Ficha fic, ModelFilterObject filter) throws DelegateException {
        try {
            getFacade().indexInsertaFicha(fic, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void indexBorraFicha(Long id) throws DelegateException {
        try {
            getFacade().indexBorraFicha(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List getFichasMicrosite(String idsite) throws DelegateException {
        try {
            return getFacade().getFichasMicrosite(idsite);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }		
		
    /**
     * Devuelve las posibles fichas relacionadas con un foro.<br/>
     * 
     * @param idforo, String identificador del foro.
     * @return List, lista de beans "Ficha".
     * @throws DelegateException
     */
    public List getFichasForo(String idforo) throws DelegateException {
        try {
            return getFacade().getFichasForo(idforo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Devuelve las posibles fichas relacionadas con un tema.<br/>
     * 
     * @param idtema, String identificador del tema.
     * @return List, lista de beans "Ficha".
     * @throws DelegateException
     */
    public List getFichasTema(String idtema) throws DelegateException {
        try {
            return getFacade().getFichasTema(idtema);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ModelFilterObject obtenerFilterObject(Ficha ficha) throws DelegateException {
        try {
            return getFacade().obtenerFilterObject(ficha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    
    
    public Hashtable getContenidos_web() throws DelegateException {
    	 try {
             return getFacade().getContenidos_web();
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
	}

	public void setContenidos_web(Hashtable contenidos_web) throws DelegateException {
		 try {
             getFacade().setContenidos_web(contenidos_web);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
	}
	
    /**
     * * Busca todos los {@link Ficha} que contengan la palabra de la busqueda que
	 * contengan una URL indexada en el crawler
     */
	public List<Ficha>  buscarFichasCrawler(final String busqueda, final String idioma,final Date dataInici, final Date dataFi) throws DelegateException {
		 try {
            return getFacade().buscarFichasCrawler(busqueda, idioma, dataInici, dataFi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public  List<FichaUA> listFichasUA(Long idFicha) throws DelegateException {
		 try {
           return getFacade().listFichasUA(idFicha);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}

    public Ficha obtenerFichaPMA(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFichaPMA(id);
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

    protected FichaDelegate() throws DelegateException {
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
