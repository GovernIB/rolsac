package org.ibit.rol.sac.persistence.saver;

import java.util.Date;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.lang.time.DateUtils;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.dao.saver.TramiteDAOSaver;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;
import org.ibit.rol.sac.persistence.remote.ActualizadorPortal;


public class TramiteSaver {

	
	public TramiteSaver(TramiteFacadeEJB tramiteFacadeEJB) {
		this.tramiteFacadeEJB = tramiteFacadeEJB;
	}

	public Long grabarTramite(Tramite tramite, Long idOC, Session session) throws HibernateException {

		if (!tramiteFacadeEJB.tieneAccesoTramite(tramite)) 
			throw new SecurityException("No tiene acceso al tr�mite");

		alimentarFechaActualizacionSiNecesario(tramite);

		Long tramId = getTramiteDAOSaver().grabarTramite(tramite,idOC,session);
		getActualizadorPortal().actualizar(tramite);

		return tramId;

	}
	
	

	private void alimentarFechaActualizacionSiNecesario(Tramite tramite) {
		
		if(null==tramite.getId()) return;
		
		Date fechaActualizacionBD = new Date(); 
		
		Tramite tramiteBD = tramiteFacadeEJB.obtenerTramite(tramite.getId());
		fechaActualizacionBD = tramiteBD.getDataActualitzacio();
		//FIXME ejaen@dgtic this.indexBorraProcedimiento(procedimientoBD);

		//Se alimenta la fecha de actualizaci�n de forma autom�tica si  no se ha introducido dato 
		if (tramite.getDataActualitzacio() == null || 
				DateUtils.isSameDay(fechaActualizacionBD,tramite.getDataActualitzacio())) {
			tramite.setDataActualitzacio( new Date()); 
		}		
	}

	
	TramiteFacadeEJB tramiteFacadeEJB;

	ActualizadorPortal actualizadorPortal;
	
	TramiteDAOSaver tramiteDAOSaver;
	
	
	public TramiteFacadeEJB getTramiteFacadeEJB() {
		return tramiteFacadeEJB;
	}

	public void setTramiteFacadeEJB(TramiteFacadeEJB tramiteFacadeEJB) {
		this.tramiteFacadeEJB = tramiteFacadeEJB;
	}

	public void setActualizadorPortal(ActualizadorPortal actualizadorPortal) {
		this.actualizadorPortal = actualizadorPortal;
	}

	public void setTramiteDAOSaver(TramiteDAOSaver tramiteDAOSaver) {
		this.tramiteDAOSaver = tramiteDAOSaver;
	}

	public TramiteDAOSaver getTramiteDAOSaver() {
		if(null==tramiteDAOSaver) tramiteDAOSaver=new TramiteDAOSaver(tramiteFacadeEJB);
		return tramiteDAOSaver;
	}

	public ActualizadorPortal getActualizadorPortal() {
		if(null==actualizadorPortal) actualizadorPortal=new ActualizadorPortal();
		return actualizadorPortal;
	}

}
