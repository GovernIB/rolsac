package test.webservice;



import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.ibit.rol.sac.model.ProcedimientoLocal;


import es.caib.back.vuds.VentanillaUnica;
import es.map.vuds.si.service.webservice.AreaTramitadora;
import es.map.vuds.si.service.webservice.CanalTramitacion;
import es.map.vuds.si.service.webservice.FormaIniciacion;
import es.map.vuds.si.service.webservice.GestorWebserviceBean;
import es.map.vuds.si.service.webservice.Idioma;
import es.map.vuds.si.service.webservice.SistemaInformacionWSLocator;
import es.map.vuds.si.service.webservice.Tasa;
import es.map.vuds.si.service.webservice.TipoRegistro;
import es.map.vuds.si.service.webservice.TipologiaTramite;
import es.map.vuds.si.service.webservice.Tramite;

    public class WebserviceVUDS_AxisTest extends junit.framework.TestCase{

    	
    	String endpoint = "http://epreinf45:8080/sacws/services/GestorWebserviceBeanPort";


    	public void testGuardarTramite() throws MalformedURLException, ServiceException, RemoteException {
    		Tramite t=new Tramite();
    		Idioma idioma=new Idioma("", "cat");

    		AreaTramitadora areaTramitadora=new AreaTramitadora("","0");
    		CanalTramitacion canalTramitacion = new CanalTramitacion("",0);
    		String codigoIdentificador = "00000";
    		String denominacionTramite ="";
    		String descripcionTramite="";
    		FormaIniciacion formaIniciacion=new FormaIniciacion("", 0);
    		String observaciones="";
    		String plazosLegales="";
    		Tasa tasa=new Tasa("","","");
    		String tiempoResolucion="";
    		TipoRegistro tipoRegistro= TipoRegistro.Alta;
    		TipologiaTramite tipologia=TipologiaTramite.value1;
    		
    		t.setAreaTramitadora(areaTramitadora);
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


    		GestorWebserviceBean service=null;
    		service = (GestorWebserviceBean)new SistemaInformacionWSLocator().
    		getGestorWebserviceBeanPort(new URL(endpoint));
    		service.guardarTramites(new Tramite[]{t}, idioma);


    	}

    }
    