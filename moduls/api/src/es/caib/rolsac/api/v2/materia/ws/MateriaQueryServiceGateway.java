package es.caib.rolsac.api.v2.materia.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQueryServiceGateway {
	
	MateriaWSSoapBindingStub stub;
	
 	public MateriaQueryServiceGateway() {

		try {
			stub = new MateriaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_MATERIA)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
 	
 	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new MateriaWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_MATERIA),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumFitxes(long id) throws RemoteException {
		return stub.getNumFitxes(id);
	}

	public int getNumAgrupacioMateries(long id) throws RemoteException {
		return stub.getNumAgrupacioMateries(id);
	}

	public int getNumProcedimentsLocals(long id) throws RemoteException {
		return stub.getNumProcedimentsLocals(id);
	}

	public int getNumUnitatsMateries(long id) throws RemoteException {
		return stub.getNumUnitatsMateries(id);
	}

	public int getNumIcones(long id) throws RemoteException {
		return stub.getNumIcones(id);
	}

	public ArxiuDTO getFotografia(long idFoto) throws RemoteException {
		return stub.getFotografia(idFoto);
	}

	public ArxiuDTO getIcona(long idIcona) throws RemoteException {
		return stub.getIcona(idIcona);
	}

	public ArxiuDTO getIconaGran(long idIconaGran) throws RemoteException {
		return stub.getIconaGran(idIconaGran);
	}	
	
//	public ArxiuDTO obtenirDistribucioCompetencial(long id,
//			ArxiuCriteria arxiuCriteria) throws RemoteException {		
//		return null;
//	}

	public List<ProcedimentDTO> llistarProcedimentsLocals(long id,
			ProcedimentCriteria procedimentCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<ProcedimentDTO> llistarProcedimentsLocals = null;
		
		tmpLlista = stub.llistarProcedimentsLocals(id, procedimentCriteria);
		llistarProcedimentsLocals = new ArrayList<ProcedimentDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			ProcedimentDTO fdto = (ProcedimentDTO) DTOUtil.object2DTO(o, ProcedimentDTO.class);
			llistarProcedimentsLocals.add(fdto);
		}
		
		return llistarProcedimentsLocals;							
	}

	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<FitxaDTO> llistaFitxes = null;
		
		tmpLlista = stub.llistarFitxes(id, fitxaCriteria);
		llistaFitxes = new ArrayList<FitxaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			FitxaDTO fdto = (FitxaDTO) DTOUtil.object2DTO(o, FitxaDTO.class);
			llistaFitxes.add(fdto);
		}
		
		return llistaFitxes;					
	}

	public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id,
			AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<AgrupacioMateriaDTO> llistaAgrupacionsMateria = null;
		
		tmpLlista = stub.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
		llistaAgrupacionsMateria = new ArrayList<AgrupacioMateriaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			AgrupacioMateriaDTO imdto = (AgrupacioMateriaDTO) DTOUtil.object2DTO(o, AgrupacioMateriaDTO.class);
			llistaAgrupacionsMateria.add(imdto);
		}
		
		return llistaAgrupacionsMateria;		
	}

	public List<IconaMateriaDTO> llistarIconesMateries(long id,
			IconaMateriaCriteria iconaMateriaCriteria) throws RemoteException, APIException {
	
		Object[] tmpLlista = null;
		List<IconaMateriaDTO> llistaIconesMateries = null;
		
		tmpLlista = stub.llistarIconesMateries(id, iconaMateriaCriteria);
		llistaIconesMateries = new ArrayList<IconaMateriaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			IconaMateriaDTO imdto = (IconaMateriaDTO) DTOUtil.object2DTO(o, IconaMateriaDTO.class);
			llistaIconesMateries.add(imdto);
		}
		
		return llistaIconesMateries;		
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<UnitatAdministrativaDTO> llistaUnitatsAdministratives = null;
		
		tmpLlista = stub.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		llistaUnitatsAdministratives = new ArrayList<UnitatAdministrativaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			UnitatAdministrativaDTO uadto = (UnitatAdministrativaDTO) DTOUtil.object2DTO(o, UnitatAdministrativaDTO.class);
			llistaUnitatsAdministratives.add(uadto);
		}
		
		return llistaUnitatsAdministratives;		
	}

	public List<UnitatMateriaDTO> llistarUnitatsMateria(long id,
			UnitatMateriaCriteria unitatMateriaCriteria)
			throws RemoteException, APIException {
	
		Object[] tmpLlista = null;
		List<UnitatMateriaDTO> llistaUnitatsMateria = null;
		
		tmpLlista = stub.llistarUnitatsMateria(id, unitatMateriaCriteria);
		llistaUnitatsMateria = new ArrayList<UnitatMateriaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			UnitatMateriaDTO umdto = (UnitatMateriaDTO) DTOUtil.object2DTO(o, UnitatMateriaDTO.class);
			llistaUnitatsMateria.add(umdto);
		}
		
		return llistaUnitatsMateria;
	}

	
}