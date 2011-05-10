package es.caib.vuds;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.xml.rpc.ServiceException;

import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

import es.caib.persistence.vuds.SistemaInformacionWSStub;
import es.map.vuds.si.service.webservice.CanalTramitacion;
import es.map.vuds.si.service.webservice.CargarCanalesTramitacion;
import es.map.vuds.si.service.webservice.CargarCanalesTramitacionE;
import es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponse;
import es.map.vuds.si.service.webservice.CargarCanalesTramitacionResponseE;
import es.map.vuds.si.service.webservice.CargarFormaIniciacion;
import es.map.vuds.si.service.webservice.CargarFormaIniciacionE;
import es.map.vuds.si.service.webservice.CargarFormaIniciacionResponse;
import es.map.vuds.si.service.webservice.CargarFormaIniciacionResponseE;
import es.map.vuds.si.service.webservice.CargarTramitesVuds;
import es.map.vuds.si.service.webservice.CargarTramitesVudsE;
import es.map.vuds.si.service.webservice.CargarTramitesVudsResponse;
import es.map.vuds.si.service.webservice.CargarTramitesVudsResponseE;
import es.map.vuds.si.service.webservice.ConsultarRechazosTramites;
import es.map.vuds.si.service.webservice.ConsultarRechazosTramitesE;
import es.map.vuds.si.service.webservice.ConsultarRechazosTramitesResponse;
import es.map.vuds.si.service.webservice.ConsultarRechazosTramitesResponseE;
import es.map.vuds.si.service.webservice.FormaIniciacion;
import es.map.vuds.si.service.webservice.Formulario;
import es.map.vuds.si.service.webservice.GuardarTramites;
import es.map.vuds.si.service.webservice.GuardarTramitesE;
import es.map.vuds.si.service.webservice.OrganismoCompetente;
import es.map.vuds.si.service.webservice.Rechazo;
import es.map.vuds.si.service.webservice.Tasa;
import es.map.vuds.si.service.webservice.TipoRegistro;
import es.map.vuds.si.service.webservice.TipologiaTramite;
import es.map.vuds.si.service.webservice.Tramite;
import es.map.vuds.si.service.webservice.TramiteVuds;

    public class WebserviceVUDS_SignatureAxis2Test extends junit.framework.TestCase{

    	
    	//String endpoint = "http://epreinf45:18080/axis2/services/sistemaInformacionWS";
    	//String endpoint = "http://epreinf45:18080/axis2/services/GestorWebserviceBeanService";
    	//String endpoint = "http://89.140.20.218:65003/ServiciosExternos/sistemaInformacionWS";
    	//String endpoint = "http://89.140.20.218:65003/sistemaInformacion-authbasic";
    	String endpoint = "http://89.140.20.218:65003/sistemaInformacion-authwsse";
    	//String endpoint = "http://localhost:9876/sistemaInformacion-authwsse";
    	
    	SistemaInformacionWSStub service;

    	private void _(Object o){ System.out.println(o); }

    	@Override
    	protected void setUp() throws Exception {
    		super.setUp();


    		//HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
    		//auth.setUsername("ibaleares");
    		//auth.setPassword("ibaleares");
    		//options.setProperty(HTTPConstants.AUTHENTICATE, auth);

    		
    		ClassLoader classLoader =
        		Thread.currentThread().getContextClassLoader();
    		
    	  	URL axis2xml  = classLoader.getResource("vuds.client.axis2.xml");
        	URL repo  = classLoader.getResource("axis2repo");

        	ConfigurationContext ctx  = ConfigurationContextFactory.createConfigurationContextFromURIs(axis2xml, repo);

    		service = new SistemaInformacionWSStub(ctx, endpoint);


    		Options options=service._getServiceClient().getOptions();
    		//options.setProperty(HTTPConstants.SO_TIMEOUT, 60000);
    		//options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, 60000);
    		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);

    		service._getServiceClient().setOptions(options);

    	}
  
       	
       	//OK
    	public void test02CargaCodigosVuds() throws MalformedURLException, ServiceException, RemoteException, FileNotFoundException {
    		CargarTramitesVuds param=new CargarTramitesVuds();
    	
    		CargarTramitesVudsE paramE=new CargarTramitesVudsE();
    		paramE.setCargarTramitesVuds(param);
    
    		CargarTramitesVudsResponseE respE = service.cargarTramitesVuds(paramE);
    		CargarTramitesVudsResponse resp = respE.getCargarTramitesVudsResponse();
    		TramiteVuds[] vuds = resp.get_return();
    		//PrintWriter w=new PrintWriter(new File("codis.txt"));
    		
    		for(TramiteVuds t:vuds) {
    			String l=t.getIdTramiteVuds()+ " "+ t.getDescripcionTramiteVuds();
    			//w.println(l);
    			_(l); 
    		}
    		_(vuds.length);
    		//w.close();
    		
    	}
    	
    	//OK 15/12/2009 local
    	//FALLA en remoto:
    	// 'Autorizaci[0xc3][0x83][0xc2][0xb3]n' is not facet-valid with respect to enumeration '[Autorizaci[0xc3][0xb3]n
    	/*
    	 * 
    	  Codigos de rechazo:
    	   
0101010001	El organismo introducido no existe en el sistema.
0101010002	El usuario actual no permite introducir información para el organismo competente introducido.
0101020001	El canal de tramitación introducido no existe en el sistema.
0101070001	La forma de iniciacion introducida no existe en el sistema.
0101090001	La normativa introducida no existe en el sistema.
0101150001	Se intenta dar de alta un registro que ya existe.
0101150002	Se intenta modificar un registro que no existe.
0101150003	Se intenta dar de baja un registro que no existe.
0101170001	El tramite VUDS introducido no existe en el sistema.
0101170002	El usuario actual no permite actualizar la informacion del tramite introducido.
0101210001	El idioma introducido no existe en el sistema
0101210002	No se permite la carga dicho idioma en el sistema si no se ha realizado la carga en el idioma por defecto(Español).

    	 */
    	/*
    	public void _test03GuardarTramite() throws MalformedURLException, ServiceException, RemoteException {
       		_(getName());
    		Tramite t=new Tramite();
    		//Idioma idioma=new Idioma();
    		//idioma.setDescripcionIdioma("");
    		//idioma.setIdIdioma("ca");

    		
    		CanalTramitacion canalTramitacion = new CanalTramitacion();
    		
    		canalTramitacion.setDescripcionCanal("");
    		canalTramitacion.setIdCanal(0);
    		
    		
    		String codigoIdentificador = "00000";
    		String denominacionTramite ="";
    		String descripcionTramite="";
    		
    		FormaIniciacion formaIniciacion=new FormaIniciacion();
    		formaIniciacion.setDescripcionFormaIniciacion("");
    		formaIniciacion.setIdFormaIniciacion(0);
    		
    		String observaciones="";
    		String plazosLegales="";
    		Tasa tasa=new Tasa();
    		tasa.setDescripcionTasa("dd");
    		tasa.setModoPago("mm");
    		
    		
    		String tiempoResolucion="";
    		TipoRegistro tipoRegistro= TipoRegistro.Alta;
    		TipologiaTramite tipologia=TipologiaTramite.value1;  //FIXME he tenido que ajustar los acentos de los campos a mano!!
    		
    		String[] documentos = {"DOC1","DOC2","DOC3"};
    		String[] requisitos= {"REQ1","REQ2","REQ3"};
    		
    		Formulario form1=new Formulario();
    		form1.setDescripcionFormulario("");
    		form1.setIdCodificacion("formID1");
    		form1.setUrlDescarga("URL1");
    		
    		TramiteVuds tramiteVuds =new TramiteVuds();
    		tramiteVuds.setIdTramiteVuds("0001");
    		tramiteVuds.setDescripcionTramiteVuds("");
    		
    		t.setAreaTramitadora("Mi area tramitadora");
    		t.setCanalTramitacion(canalTramitacion);
    		t.setCodigoIdentificador(codigoIdentificador);
    		t.setDenominacionTramite(denominacionTramite);
    		t.setDescripcionTramite(descripcionTramite);
    		t.setFormaIniciacion(formaIniciacion);
    		t.setObservaciones(observaciones);
    		t.setPlazosLegales(plazosLegales);
    		t.setTasa(tasa);
    		t.setTiempoResolucion(tiempoResolucion);
    		t.setTipoRegistro(tipoRegistro);
    		t.setTipologia(tipologia);
    		t.setDocumento(documentos);
    		t.setFormulario(new Formulario[]{form1});
    		//   		t.setNormativa(new Normativa[]{norm1});
    		t.setRequisitosPrevios(requisitos);
    		t.setTramiteVuds(tramiteVuds);
    		t.setEnlaceConsulta("");
    		
    		OrganismoCompetente organComp=new OrganismoCompetente();
    		organComp.setIdOrganismo("94");
    		organComp.setDescripcionOrganismo("");
			t.setOrganismoCompetente(organComp);
    		
    		t.setResultado(new String[]{"mi resultado"});


    		GuardarTramites tramites = new GuardarTramites();
    		tramites.addTramite(t);

    		GuardarTramitesE tramitesE = new GuardarTramitesE();
    		tramitesE.setGuardarTramites(tramites);

    		service.guardarTramites(tramitesE); 
    	}
    	*/
    	
/*    	
    	public void _test0301ConsultarRechazosTramites() throws MalformedURLException, ServiceException, RemoteException {
    		ConsultarRechazosTramitesE crtE=new ConsultarRechazosTramitesE();
    		ConsultarRechazosTramites crt = new ConsultarRechazosTramites();
    		crtE.setConsultarRechazosTramites(crt);
    		ConsultarRechazosTramitesResponseE respE = service.consultarRechazosTramites(crtE);
    		ConsultarRechazosTramitesResponse resp = respE.getConsultarRechazosTramitesResponse();
    		Rechazo[] rechazos = resp.getRechazo();
    		_(Arrays.toString(rechazos));
    	}
  */  	
    	/*
    	 * 1   A instancia del solicitante
    	 * 2	Oficio3
Ambas (Interesado y de oficio)

    	 * 
    	 */
    	//OK 26gen2010
       	public void test04CargarFormasIniciacion() throws MalformedURLException, ServiceException, RemoteException {
    		CargarFormaIniciacion param=new CargarFormaIniciacion();
    		CargarFormaIniciacionE paramE=new CargarFormaIniciacionE();
    		paramE.setCargarFormaIniciacion(param);
    		
    		CargarFormaIniciacionResponseE respE = service.cargarFormaIniciacion(paramE);
    		CargarFormaIniciacionResponse resp = respE.getCargarFormaIniciacionResponse();
    		FormaIniciacion[] fi_arr = resp.get_return();

    		assertNotSame(0,fi_arr.length);
    		
    		for(FormaIniciacion fi:fi_arr) {
    			_(fi.getIdFormaIniciacion());
    			_(fi.getDescripcionFormaIniciacion());
    		}
    		
    	}

    	
       	public void test06CargarCanalesTramitacion() throws MalformedURLException, ServiceException, RemoteException {
    		CargarCanalesTramitacion param=new CargarCanalesTramitacion();
    		CargarCanalesTramitacionE paramE=new CargarCanalesTramitacionE();
    		paramE.setCargarCanalesTramitacion(param);
    		
    		CargarCanalesTramitacionResponseE respE = service.cargarCanalesTramitacion(paramE);
    		CargarCanalesTramitacionResponse resp = respE.getCargarCanalesTramitacionResponse();
    		CanalTramitacion[] fi_arr = resp.get_return();

    		assertNotSame(0,fi_arr.length);
    		
    		for(CanalTramitacion fi:fi_arr) {
    			_(fi.getIdCanal());
    			_(fi.getDescripcionCanal());
    		}
    		
    	}
    	

    }
    