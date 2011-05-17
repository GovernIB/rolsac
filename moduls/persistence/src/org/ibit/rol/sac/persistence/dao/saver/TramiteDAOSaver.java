package org.ibit.rol.sac.persistence.dao.saver;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;


public class TramiteDAOSaver {

	public TramiteDAOSaver() {
	}
	
	TramiteFacadeEJB tramiteFacadeEJB;
	
	public TramiteDAOSaver(TramiteFacadeEJB tramiteFacadeEJB) {
		this.tramiteFacadeEJB = tramiteFacadeEJB;
	}

	public Long grabarTramite(Tramite tramite, Long idOC, Session session) throws HibernateException {

		UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idOC);
		tramite.setOrganCompetent(unidad);

		//guardem canvis
		session.saveOrUpdate(tramite);
		session.flush();

		return tramite.getId();

	}
	

}
