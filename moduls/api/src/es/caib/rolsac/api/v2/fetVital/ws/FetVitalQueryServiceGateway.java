package es.caib.rolsac.api.v2.fetVital.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FetVitalQueryServiceGateway {

	FetVitalWSSoapBindingStub stub;
	
	public FetVitalQueryServiceGateway() {

		try {			
			stub = new FetVitalWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_FET_VITAL)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

	public int getNumFitxes(long id) throws RemoteException {
		
		int numFitxes = 0;		
		numFitxes = stub.getNumFitxes(id);
		
		return numFitxes;
	}
	
	public int getNumProcedimentsLocals(long id) throws RemoteException {
		
		int numProcedimentsLocals = 0;
		numProcedimentsLocals = stub.getNumProcedimentsLocals(id);
		
		return numProcedimentsLocals;
	}
	
    public int getNumFetsVitalsAgrupacionsFV(long id) throws RemoteException {
    	
    	int numFetVitalAgrupacionsFV = 0;    	    	
    	numFetVitalAgrupacionsFV = stub.getNumFetsVitalsAgrupacionsFV(id);
    	
    	return numFetVitalAgrupacionsFV;
    }
	
    public ArxiuDTO getDistribuciCompetencial(long idDistribuciCompetencial) throws RemoteException {
    	
    	ArxiuDTO adto = null;
    	adto = stub.getDistribuciCompetencial(idDistribuciCompetencial);   
    	
    	return adto;
    }    
    
    public ArxiuDTO getNormativa(long idNormativa) throws RemoteException {
    	
    	ArxiuDTO adto = null;    	
    	adto = stub.getNormativa(idNormativa);
    	
    	return adto;
    }
    
    public ArxiuDTO getContingut(long idContingut) throws RemoteException {
    	ArxiuDTO adto = null;    	
    	adto = stub.getContingut(idContingut);
    	
    	return adto;
    }
    
	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws RemoteException, APIException {
		Object[] tmpLlista = null;
		List<FitxaDTO> llistaFitxes = null;
		
		tmpLlista = stub.llistarFitxes(id, fitxaCriteria);
		llistaFitxes = new ArrayList<FitxaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FitxaDTO fdto = (FitxaDTO) DTOUtil.object2DTO(o, FitxaDTO.class);							
			llistaFitxes.add( fdto );
		}
			
		return llistaFitxes;
	}
	
	public List<ProcedimentDTO> llistarProcedimentsLocals(long id,
			ProcedimentCriteria procedimentCriteria) throws RemoteException,
			APIException {
		
		Object[] tmpLlista = null;
		List<ProcedimentDTO> llistaProcedimentsLocals =null;
		
		tmpLlista = stub.llistarProcedimentsLocals(id, procedimentCriteria);
		llistaProcedimentsLocals = new ArrayList<ProcedimentDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {				
			ProcedimentDTO pdto = (ProcedimentDTO) DTOUtil.object2DTO(o, ProcedimentDTO.class);
			llistaProcedimentsLocals.add( pdto );
		}
		
		return llistaProcedimentsLocals;
	}
	
	public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id,
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws RemoteException, APIException {
    	
    	Object[] tmpLlista = null;
    	List<AgrupacioFetVitalDTO> llistaAgrupacionsFetVitals = null;
    	
		tmpLlista = stub.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
		llistaAgrupacionsFetVitals = new ArrayList<AgrupacioFetVitalDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {
			AgrupacioFetVitalDTO afvdto = (AgrupacioFetVitalDTO) DTOUtil.object2DTO(o, AgrupacioFetVitalDTO.class);
			llistaAgrupacionsFetVitals.add(afvdto);
		}
    	
		return llistaAgrupacionsFetVitals;
	}
        
}
