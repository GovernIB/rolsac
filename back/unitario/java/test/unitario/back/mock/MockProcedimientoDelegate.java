package test.unitario.back.mock;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;


/**
 * aquest Mock es fa extendre del EJB per poder fer tests d'integracio.
 * @author u92770
 *
 */
public class MockProcedimientoDelegate extends ProcedimientoDelegate {

	
	AccesoManagerLocal mockAccesoManager;

	public static ProcedimientoLocal procedimiento;
	
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
		
		return procedimiento;  //o se establece en grabar, o mediante IoC 
	}


	@Override
	public Long grabarProcedimiento(ProcedimientoLocal procedimiento, Long idUA) throws DelegateException {
		long id=123L;
		procedimiento.setId(id);
		this.procedimiento=procedimiento;
		return id;
	}

	@Override
	public void anyadirMateria(Long materiaId, Long procId) throws DelegateException {
		
		Materia materia=new Materia();
		materia.setId(materiaId);
		if(0==materiaId)  
			materia.setCodigoEstandar(Materia.CE_SENSECLASSIFICAR);
		if(1==materiaId)  
			materia.setCodigoEstandar("Agricultura");

		procedimiento.addMateria(materia);

	}

	@Override
	public void eliminarMateria(Long materiaId, Long procId) throws DelegateException {
		procedimiento.removeMateria(materiaId);
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
