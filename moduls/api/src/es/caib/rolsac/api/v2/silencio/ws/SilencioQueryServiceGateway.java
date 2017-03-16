package es.caib.rolsac.api.v2.silencio.ws;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;

public class SilencioQueryServiceGateway
{
	SilencioWSSoapBindingStub stub;
	
	public SilencioQueryServiceGateway()
	{
		try {
			stub = new SilencioWSSoapBindingStub(
					new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_SILENCI)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} catch (APIException e) {
			e.printStackTrace();
		}
		
		System.out.println(" silencio getway");
	}
	
  

    public SilencioDTO obtenirSilenci(Long codSilencio, String idioma) throws RemoteException {
    	return stub.obtenirSilenci(codSilencio, idioma);
    }



	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new SilencioWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_SILENCI),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
    
}
