package es.caib.rolsac.api.v2.publicObjectiu.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class PublicObjectiuQueryServiceGateway {

	PublicObjectiuWSSoapBindingStub stub;
	
	public PublicObjectiuQueryServiceGateway() {

		try {
			stub = new PublicObjectiuWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_PUBLIC_OBJECTIU)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumAgrupacions(long id) throws RemoteException {
		return stub.getNumAgrupacions(id);
	}

	public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id,
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
            throws RemoteException, APIException {

        Object[] tmpLlista = null;
        List<AgrupacioFetVitalDTO> llistaAgrupacionsFetVitals = null;
        
        tmpLlista = stub.llistarAgrupacions(id, agrupacioFetVitalCriteria);
        llistaAgrupacionsFetVitals = new ArrayList<AgrupacioFetVitalDTO>( Arrays.asList(tmpLlista).size() );
        
        for (Object o : tmpLlista) {
            AgrupacioFetVitalDTO afvdto = (AgrupacioFetVitalDTO) DTOUtil.object2DTO(o, AgrupacioFetVitalDTO.class);
            llistaAgrupacionsFetVitals.add(afvdto);
        }
        
        return llistaAgrupacionsFetVitals;      
        
    }
	
	public List<ProcedimentDTO> llistarProcediments(long id,
			ProcedimentCriteria procedimentCriteria) throws RemoteException,
			APIException {
		
        Object[] tmpLlista = null;
        List<ProcedimentDTO> llistaProcediments = null;
        
        tmpLlista = stub.llistarProcediments(id, procedimentCriteria);
        llistaProcediments = new ArrayList<ProcedimentDTO>( Arrays.asList(tmpLlista).size() );
        
        for (Object o : tmpLlista) {
            ProcedimentDTO pdto = (ProcedimentDTO) DTOUtil.object2DTO(o, ProcedimentDTO.class);
            llistaProcediments.add(pdto);
        }
        
        return llistaProcediments;      
    }
	
	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws RemoteException, APIException {
        Object[] tmpLlista = null;
        List<FitxaDTO> llistaFitxes = null;
        
        tmpLlista = stub.llistarFitxes(id, fitxaCriteria);
        llistaFitxes = new ArrayList<FitxaDTO>( Arrays.asList(tmpLlista).size() );
        
        for (Object o : tmpLlista) {
            FitxaDTO fdto = (FitxaDTO) DTOUtil.object2DTO(o, FitxaDTO.class);
            llistaFitxes.add(fdto);
        }
        
        return llistaFitxes;      
    }
	
}
