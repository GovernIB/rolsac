package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.FamiliaFacade;
import org.ibit.rol.sac.persistence.intf.FamiliaFacadeHome;
import org.ibit.rol.sac.persistence.util.FamiliaFacadeUtil;
import org.ibit.rol.sac.model.Familia;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class FamiliaDelegate  {

	FamiliaDelegateI impl;

	public FamiliaDelegateI getImpl() {
		return impl;
        }

	public void setImpl(FamiliaDelegateI impl) {
		this.impl = impl;
    }

	public void borrarFamilia(Long id) throws DelegateException {
		impl.borrarFamilia(id);
        }

	public Long grabarFamilia(Familia familia) throws DelegateException {
		return impl.grabarFamilia(familia);
    }

	public List listarFamilias() throws DelegateException {
		return impl.listarFamilias();
        }

	public Familia obtenerFamilia(Long id) throws DelegateException {
		return impl.obtenerFamilia(id);
    }

    public boolean tieneProcedimientos(Long id) throws DelegateException {
		return impl.tieneProcedimientos(id);
    }



}
