package test.unitario.back.mock;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;


/**
 * aquest Mock es fa extendre del bean real per poder fer tests d'integracio.
 * @author u92770
 *
 */
public class MockProcedimientoDelegateFail extends ProcedimientoDelegate {

	
	AccesoManagerLocal mockAccesoManager;
	
	
	public void setAccesoManager(AccesoManagerLocal manager) { mockAccesoManager=manager;}
	
	public AccesoManagerLocal getAccesoManager() {
		return mockAccesoManager;
	}

	ProcedimientoLocal procLocal;
	
	public ProcedimientoLocal getProcedimientoLocal() {
		return procLocal;
	}

	public void setProcedimientoLocal(ProcedimientoLocal procLocal) {
		this.procLocal = procLocal;
	}

	protected boolean userIsOper() {
		return true;
	}
	
	protected boolean userIsSuper() {
			return true;
	}

	public ProcedimientoLocal obtenerProcedimiento(Long id) throws DelegateException {
		
		ProcedimientoLocal proc=new ProcedimientoLocal(123L);
		proc.setResponsable("yo mismo");
		proc.setTaxa("on");
		proc.setTramite("codigo1");
		proc.setVersion(1L);
		proc.setUrl("url1");
		return proc;
	}


	@Override
	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) throws DelegateException {
		Exception ex=new Exception("mi excepcion");
		throw new DelegateException(ex);
	}
	
	
	@Override
		public void anyadirTramite(Long tramiteId, Long procId) throws DelegateException {

		}

	@Override
		public List buscarProcedimientos(Map parametros, Map traduccion) throws DelegateException {
			return super.buscarProcedimientos(parametros, traduccion);
		}
	
	
		protected void addOperacion(Session session, ProcedimientoLocal pr,
				int operacion) throws HibernateException {

		}
	
		protected boolean tieneAcceso(Usuario usuario,
						ProcedimientoLocal procedimiento) {
			return true;
		}
	
		protected Usuario getUsuario(Session session) throws HibernateException {
			return null;
		}
}
