package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.persistence.intf.MateriaFacade;
import org.ibit.rol.sac.persistence.intf.MateriaFacadeHome;
import org.ibit.rol.sac.persistence.util.MateriaFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

/**
 * Business delegate para manipular materias.
 */
public class MateriaDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarMateria(Materia materia) throws DelegateException {
        try {
            return getFacade().grabarMateria(materia);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarMaterias() throws DelegateException {
        try {
            return getFacade().listarMaterias();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarMateriasFront() throws DelegateException {
        try {
            return getFacade().listarMateriasFront();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarMateriasFrontDestacadas(String lang) throws DelegateException {
        try {
            return getFacade().listarMateriasFrontDestacadas(lang);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Materia obtenerMateria(Long id) throws DelegateException {
        try {
            return getFacade().obtenerMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public boolean tieneProcedimientosOFichas(Long id) throws DelegateException {
        try {
            return getFacade().tieneProcedimientosOFichas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List<Materia> listarMateriasbyUA (Long ua) throws DelegateException {
        try {
            return getFacade().listarMateriasbyUA(ua);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Set<MateriaAgrupacionM> obtenerGruposMateria(Long idmateria) throws DelegateException{
        try {
            return getFacade().obtenerGruposMateria(idmateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    
    
    public void borrarMateria(Long id) throws DelegateException {
        try {
            getFacade().borrarMateria(id);
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
    
    @SuppressWarnings("unchecked")
	public Set<Materia> obtenerMateriasCE(final String[] codigosEstandarMateria) throws DelegateException{
    	try {
            return getFacade().obtenerMateriasCE(codigosEstandarMateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Materia obtenerMateriaCE(final String codigosEstandarMateria) throws DelegateException{
    	try {
            return getFacade().obtenerMateriaCE(codigosEstandarMateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Materia> buscar(final String busqueda, final String idioma) throws DelegateException {
    	try {
            return getFacade().buscar(busqueda,idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Materia obtenerMateriaFichasProced (Long id) throws DelegateException{
    	try {
            return getFacade().obtenerMateriaFichasProced(id);
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

    protected MateriaDelegate() throws DelegateException {
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
