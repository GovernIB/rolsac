package test.integracion.persistence.mock;

import javax.ejb.EJBException;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;

import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

public class MockAccesoManagerLocal implements AccesoManagerLocal {

	public boolean tieneAccesoComentario(Long idComentario) {

		return true;
	}

	public boolean tieneAccesoDocumento(Long idDoc) {

		return true;
	}

	public boolean tieneAccesoEdificio(Long idEdi) {

		return true;
	}

	public boolean tieneAccesoFicha(Long idFicha) {

		return true;
	}

	public boolean tieneAccesoFichaUnidad(Long idFichaUA) {

		return true;
	}

	public boolean tieneAccesoFormulario(Long idForm) {

		return true;
	}

	public boolean tieneAccesoNormativa(Long idNorma) {

		return true;
	}

	public boolean tieneAccesoPersonal(Long idPers) {

		return true;
	}

	public boolean tieneAccesoProcedimiento(Long idProc) {

		return true;
	}

	public boolean tieneAccesoSeccion(Long idSec) {

		return true;
	}

	public boolean tieneAccesoTramite(Long idTram) {

		return true;
	}

	public boolean tieneAccesoUnidad(Long idUA, boolean modificar) {

		return true;
	}

	public EJBLocalHome getEJBLocalHome() throws EJBException {

		return null;
	}

	public Object getPrimaryKey() throws EJBException {

		return null;
	}

	public boolean isIdentical(EJBLocalObject ejblocalobject_0)
			throws EJBException {

		return true;
	}

	public void remove() throws RemoveException, EJBException {


	}

	public boolean tieneAccesoMoverOrganigrama(Long oldParent, Long newParent) {
		return true;
	}
	
}
