package es.caib.rolsac.api.v2.tramit.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceGateway {

	TramitWSSoapBindingStub stub;
	TramitQueryServiceEJBRemoteServiceLocator locator;
	
	public TramitQueryServiceGateway() {

		try {
			
			locator = new TramitQueryServiceEJBRemoteServiceLocator();

			stub = new TramitWSSoapBindingStub(new URL(
					locator.getTramitWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumDocumentsInformatius(long id) throws RemoteException {
		return stub.getNumDocumentsInformatius(id);
	}

	public int getNumFormularis(long id) throws RemoteException {
		return stub.getNumFormularis(id);
	}

	public int getNumTaxes(long id) throws RemoteException {
		return stub.getNumTaxes(id);
	}

	public ProcedimentDTO obtenirProcediment(long id) throws RemoteException {
		return stub.obtenirProcediment(id);
	}

	public UnitatAdministrativaDTO obtenirOrganCompetent(long id)
			throws RemoteException {
		return stub.obtenirOrganCompetent(id);
	}	
	
//	public Date getDataCaducitat(long id) throws RemoteException {
//		return stub.getDataCaducitat(long id);
//	}

//	public String getTitol(long id) throws RemoteException {
//		return stub.getTitol(id);
//	}

	public List<DocumentTramitDTO> llistarFormularis(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws RemoteException {

		Object[] tmpLlista = null;
		List<DocumentTramitDTO> llistaFormularis = null;
		
		tmpLlista = stub.llistarFormularis(id, documentTramitCriteria);
		llistaFormularis = new ArrayList<DocumentTramitDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			DocumentTramitDTO tdto = (DocumentTramitDTO) DTOUtil.object2DTO(o, DocumentTramitDTO.class);
			llistaFormularis.add(tdto);
		}
		
		return llistaFormularis;		
	}

	public List<DocumentTramitDTO> llistatDocumentsInformatius(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws RemoteException {

		Object[] tmpLlista = null;
		List<DocumentTramitDTO> llistaDocuments = null;
		
		tmpLlista = stub.llistatDocumentsInformatius(id, documentTramitCriteria);
		llistaDocuments = new ArrayList<DocumentTramitDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			DocumentTramitDTO dtdto = (DocumentTramitDTO) DTOUtil.object2DTO(o, DocumentTramitDTO.class);
			llistaDocuments.add(dtdto);
		}
		
		return llistaDocuments;
	}

	public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria)
			throws RemoteException {
		Object[] tmpLlista = null;
		List<TaxaDTO> llistaTaxes = null;
		
		tmpLlista = stub.llistarTaxes(id, taxaCriteria);
		llistaTaxes = new ArrayList<TaxaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			TaxaDTO tdto = (TaxaDTO) DTOUtil.object2DTO(o, TaxaDTO.class);
			llistaTaxes.add(tdto);
		}
		
		return llistaTaxes;		
	}	
	
}
