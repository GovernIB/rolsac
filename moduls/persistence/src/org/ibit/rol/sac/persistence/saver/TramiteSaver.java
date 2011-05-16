package org.ibit.rol.sac.persistence.saver;

import java.util.Date;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.lang.time.DateUtils;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.persistence.vuds.ActualizacionVudsException;
import es.caib.persistence.vuds.ValidateVudsException;

public class TramiteSaver {

	TramiteFacadeEJB tramiteFacadeEJB;
	
	public TramiteSaver(TramiteFacadeEJB tramiteFacadeEJB) {
		this.tramiteFacadeEJB = tramiteFacadeEJB;
	}

	public Long grabarTramite(Tramite tramite, Long idOC, Session session) throws ValidateVudsException, ActualizacionVudsException, HibernateException {


		if (!tramiteFacadeEJB.tieneAccesoTramite(tramite)) 
			throw new SecurityException("No tiene acceso al tr�mite");

		alimentarFechaActualizacionSiNecesario(tramite);
		

		UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idOC);
		tramite.setOrganCompetent(unidad);

		//guardem canvis
		session.saveOrUpdate(tramite);
		session.flush();

		if(tramite.esPublico()) 
				Actualizador.actualizar(tramite,true);

		return tramite.getId();

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


}
