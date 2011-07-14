package org.ibit.rol.sac.persistence.remote.vuds;

//FIXME import es.caib.persistence.vuds.GestorWebserviceBeanServiceStub;
import es.map.vuds.si.service.webservice.CanalTramitacion;
import es.map.vuds.si.service.webservice.CargarTramitesVuds;
import es.map.vuds.si.service.webservice.CargarTramitesVudsE;
import es.map.vuds.si.service.webservice.CargarTramitesVudsResponse;
import es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE;
import es.map.vuds.si.service.webservice.FormaIniciacion;
import es.map.vuds.si.service.webservice.Formulario;
import es.map.vuds.si.service.webservice.GuardarTramites;
import es.map.vuds.si.service.webservice.GuardarTramitesE;
//FIXME import es.map.vuds.si.service.webservice.Idioma;
import es.map.vuds.si.service.webservice.OrganismoCompetente;
import es.map.vuds.si.service.webservice.Tasa;
import es.map.vuds.si.service.webservice.TipoRegistro;
import es.map.vuds.si.service.webservice.TipologiaTramite;
import es.map.vuds.si.service.webservice.Tramite;
import es.map.vuds.si.service.webservice.TramiteVuds;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionDocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;


/**
 * @author u92770
 * He escollit el patró Adapter perque cal adaptar un tramit rolsac a un tramit vuds
 * (patró Facade no seria adecuat perque cal adaptar el tramit)  
 *
 */
public class VentanillaUnicaSender {
	protected static Log log = LogFactory.getLog(VentanillaUnicaSender.class);
	//private static String endpoint = "http://epreinf45:18080/axis2/services/GestorWebserviceBeanService";
	String endpoint = "http://89.140.20.218:65003/ServiciosExternos/sistemaInformacionWS";  //TODO parametritzar 

	public static final String CATALA="ca";
	public static final String CASTELLA="es";
	
	
	//private final String endpoint_webcaib = "http://www.caib.es";
	private final static String endpoint_webcaib = "https://proves.caib.es";

	private HashMap<String,String> idiomes=new HashMap<String, String>();

	{
		idiomes.put(CATALA, "ca_ES Catala");
		idiomes.put(CASTELLA, "es_ES Castellano");

		/*	altres idiomes:
		  		eu_ES Euskera	
				gl_ES Galego
				va_ES Valencià
				en_US English
				fr_FR Français
				de_DE Deutsch
				
		*/
	}
	
	
	public VentanillaUnicaSender(){}
	
	public VentanillaUnicaSender(String ep){ endpoint=ep;	}


	public int enviarTramit(String endpoint, org.ibit.rol.sac.model.Tramite tramit, String idioma) throws WSInvocatorException {
		log.debug("enviant tramit.."+" ep="+endpoint+" tramit="+tramit+" idioma="+idioma);
		this.endpoint = endpoint;
		enviarTramit(tramit);
		return 0;
	}
	
	public int enviarTramit(org.ibit.rol.sac.model.Tramite tramit, String idiomaStr) throws WSInvocatorException {
		enviarTramit(tramit);
		return 0;
	}

	public void enviarTramit(org.ibit.rol.sac.model.Tramite tramit) throws WSInvocatorException {
		throw new WSInvocatorException(null);
	}



	public List<TramiteVuds> cargarCodisVuds(String idiomaStr) 	throws WSInvocatorException {
		return null;
		/*
 		Idioma idioma=setIdioma(idiomaStr);
 		CargarTramitesVuds param=new CargarTramitesVuds();
		param.setIdioma(idioma);
		CargarTramitesVudsE paramE=new CargarTramitesVudsE();
		paramE.setCargarTramitesVuds(param);

		CargarTramitesVudsResponseE respE;
		try {
			GestorWebserviceBeanServiceStub stub = createServiceInstance(endpoint);
			respE = stub.cargarTramitesVuds(paramE);
			CargarTramitesVudsResponse resp = respE.getCargarTramitesVudsResponse();
			TramiteVuds[] vuds = resp.get_return();
			return Arrays.asList(vuds);
			
		} catch (RemoteException e) {
			log.error(e);
			throw new WSInvocatorException(e);
		}
*/
	}
	
	

	
}
