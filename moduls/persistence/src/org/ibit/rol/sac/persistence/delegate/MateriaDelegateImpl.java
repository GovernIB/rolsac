package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.intf.MateriaFacade;
import org.ibit.rol.sac.persistence.intf.MateriaFacadeHome;
import org.ibit.rol.sac.persistence.util.MateriaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular materias.
 * 
 * 
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

 public class MateriaDelegateImpl implements StatelessDelegate, MateriaDelegateI {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    /**
	 * 
	 */
	private static final long serialVersionUID = 8339842386814116044L;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#grabarMateria(org.ibit.rol.sac.model.Materia)
	 */
    public Long grabarMateria(Materia materia) throws DelegateException {
        try {
            return getFacade().grabarMateria(materia);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda listarMaterias(int pagina, int resultados, String lang) throws DelegateException {
    	try {
    		return getFacade().listarMaterias(pagina, resultados, lang);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);    		
    	} 
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#listarMaterias()
	 */
    public List listarMaterias() throws DelegateException {
        try {
            return getFacade().listarMaterias();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#listarMateriasFront()
	 */
    public List listarMateriasFront() throws DelegateException {
        try {
            return getFacade().listarMateriasFront();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#listarMateriasFrontDestacadas(java.lang.String)
	 */
    public List listarMateriasFrontDestacadas(String lang) throws DelegateException {
        try {
            return getFacade().listarMateriasFrontDestacadas(lang);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerMateria(java.lang.Long)
	 */
    public Materia obtenerMateria(Long id) throws DelegateException {
        try {
            return getFacade().obtenerMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#tieneProcedimientosOFichas(java.lang.Long)
	 */
    public boolean tieneProcedimientosOFichas(Long id) throws DelegateException {
        try {
            return getFacade().tieneProcedimientosOFichas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#listarMateriasbyUA(java.lang.Long)
	 */
    public List<Materia> listarMateriasbyUA (Long ua) throws DelegateException {
        try {
            return getFacade().listarMateriasbyUA(ua);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerGruposMateria(java.lang.Long)
	 */
    public Set<MateriaAgrupacionM> obtenerGruposMateria(Long idmateria) throws DelegateException{
        try {
            return getFacade().obtenerGruposMateria(idmateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#borrarMateria(java.lang.Long)
	 */
    public void borrarMateria(Long id) throws DelegateException {
        try {
            getFacade().borrarMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerDistribComp(java.lang.Long, java.lang.String, boolean)
	 */
    public Archivo obtenerDistribComp(Long id, String lang, boolean useDefault) throws DelegateException {
        try {
            return getFacade().obtenerDistribComp(id, lang, useDefault);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerNormativa(java.lang.Long, java.lang.String, boolean)
	 */
    public Archivo obtenerNormativa(Long id, String lang, boolean useDefault) throws DelegateException {
        try {
            return getFacade().obtenerNormativa(id, lang, useDefault);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerContenido(java.lang.Long, java.lang.String, boolean)
	 */
    public Archivo obtenerContenido(Long id, String lang, boolean useDefault) throws DelegateException {
        try {
            return getFacade().obtenerContenido(id, lang, useDefault);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerFoto(java.lang.Long)
	 */
    public Archivo obtenerFoto(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFoto(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerIcono(java.lang.Long)
	 */
    public Archivo obtenerIcono(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIcono(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerIconoGrande(java.lang.Long)
	 */
    public Archivo obtenerIconoGrande(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIconoGrande(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerMateriasCE(java.lang.String[])
	 */
    @SuppressWarnings("unchecked")
	public Set<Materia> obtenerMateriasCE(final String[] codigosEstandarMateria) throws DelegateException{
    	try {
            return getFacade().obtenerMateriasCE(codigosEstandarMateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerMateriaCE(java.lang.String)
	 */
    public Materia obtenerMateriaCE(final String codigosEstandarMateria) throws DelegateException{
    	try {
            return getFacade().obtenerMateriaCE(codigosEstandarMateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#buscar(java.lang.String, java.lang.String)
	 */
    public List<Materia> buscar(final String busqueda, final String idioma) throws DelegateException {
    	try {
            return getFacade().buscar(busqueda,idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerMateriaFichasProced(java.lang.Long)
	 */
    public Materia obtenerMateriaFichasProced (Long id) throws DelegateException{
    	try {
            return getFacade().obtenerMateriaFichasProced(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.MateriaDelegateI#obtenerMateriasPorIDs(java.lang.Long)
	 */
    public List<Materia> obtenerMateriasPorIDs (String ids, String idioma) throws DelegateException {
    	try {
            return getFacade().obtenerMateriasPorIDs(ids, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<UnidadAdministrativa> listarUAsMateria(Long id)
			throws DelegateException {
    	try {
            return getFacade().listarUAsMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private MateriaFacade getFacade() throws RemoteException {
        return (MateriaFacade) facadeHandle.getEJBObject();
    }

    protected MateriaDelegateImpl() throws DelegateException {
        try {
            MateriaFacadeHome home = MateriaFacadeUtil.getHome();
            MateriaFacade remote = home.create();
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
