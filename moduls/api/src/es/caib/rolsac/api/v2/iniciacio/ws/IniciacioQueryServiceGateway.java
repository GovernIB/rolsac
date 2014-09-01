package es.caib.rolsac.api.v2.iniciacio.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;

public class IniciacioQueryServiceGateway
{
//	IniciacioWSSoapBindingStub stub;
	
	public IniciacioQueryServiceGateway()
	{
/*		try {
			stub = new IniciacioWSSoapBindingStub(
					new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_INICIACIO)),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}*/
		
		/*
		 * QUEDA PENDIENTE TERMINAR DE IMPLEMENTARLO EN CASO DE QUE SEA NECESSARIO
		 * AHORA MISMO NO HACE FALTA YA QUE LA CONSULTA LA HACE EL ROLSACWS
		 * SI SE HA CREADO EL WS VACIO ES PARA QUE NO DE UN PETE
		 */
		
		System.out.println("");
	}
	
    public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws RemoteException {
//    	return stub.List<IniciacioDTO> llistarTipusIniciacions(iniciacioCriteria);
    	return null;
    }

    public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws RemoteException {
//    	return stub.IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria);
    	return null;
    }
    
}
