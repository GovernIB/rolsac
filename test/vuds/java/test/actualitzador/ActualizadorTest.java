package test.actualitzador;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.easymock.EasyMock;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.ws.Actualizador;
import org.ibit.rol.sac.persistence.ws.ReportarFallo;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import test.dao.TramiteFacadeEJBTest_Spring;

import es.caib.vuds.VentanillaUnica;


/**
 * Requisits:
 * 	- tenir el local Axis2 web service de VUDS en marxa:
 * 		C:\j2ee-developer\webservices\axis2-1.5\bin\axis2-1.5\bin\axis2server.bat
 * 
 * @author u92770
 *
 */

public class ActualizadorTest extends junit.framework.TestCase {

	DestinatarioDelegate destDelegateMock = EasyMock.createMock(DestinatarioDelegate.class);
	Actualizador actualitzador=new Actualizador();

	VentanillaUnica vuds = EasyMock.createMock(VentanillaUnica.class);
	
	List<Destinatario> destinatarios=new ArrayList<Destinatario>();
	Destinatario dest;
	
	@Override
	protected void setUp() throws Exception {
		
		String vudsEP = "http://epreinf45:18080/axis2/services/sistemaInformacionWS";
		dest=new Destinatario();
		dest.setNombre("VUDS");
		dest.setEndpoint(vudsEP);
		dest.setEmail("bustia@rolsac");
		destinatarios.add(dest);
		
		EasyMock.expect(destDelegateMock.listarDestinatarios()).andReturn(destinatarios);
		EasyMock.replay(destDelegateMock);
		Actualizador.setDestDelegate(destDelegateMock);
		Actualizador.setVuds(vuds);		
	}
	

	public  void _testActualitzarAltaTramitLocalOK() throws InterruptedException, WSInvocatorException {
		Tramite t=rellenaTramite(true);
		t.setOperativa(Operativa.MODIFICA);
		EasyMock.expect(vuds.enviarTramit(t,"es")).andReturn(0);
		EasyMock.replay(vuds);
		//Actualizador.setVuds(vuds);  //Descomentar cuando servidor esta caido. 
		List<String> destsOK = Actualizador.actualizarSync(t);
		assertTrue(destsOK.contains("VUDS"));
	}

	//TODO crear mock per EmailUtils. Pero pel passar el test no cal.
	public  void _testEnviarAltaTramitLocalFalla() throws InterruptedException, WSInvocatorException {
		Tramite t=rellenaTramite();
		t.setOperativa(Operativa.CREA);
		Throwable ex=new ConnectException(" org.apache.axis2.AxisFault: Connection refused: connect");
		EasyMock.expect(vuds.enviarTramit(t)).andThrow(new WSInvocatorException(ex));
		EasyMock.replay(vuds);
		Actualizador.setVuds(vuds);
		Actualizador.setTramitsFallats(new HashSet<Long>());
		Actualizador.actualizar(t);
		while(null==Actualizador.getActualizador());
		Actualizador.getActualizador().join();
		Set<Long> fallats= Actualizador.getTramitsFallats();
		assertTrue(fallats.contains(t.getId()));
	}
	
	public void _testReportarFallo_AlCrearTramite() {
		Tramite t=rellenaTramite();
		t.setOperativa(Operativa.CREA);
		Throwable ex=new ConnectException(" org.apache.axis2.AxisFault: Connection refused: connect");
		WSInvocatorException wex= new WSInvocatorException(ex); 
		ReportarFallo.reportar(t, false, dest, wex);
	}
	
	
	public  void _testActualitzarAltaTramitLocalOK_NoDestinatari() throws  InterruptedException, WSInvocatorException {
		Tramite t=rellenaTramite(true);
		dest.setNombre(null);
		t.setOperativa(Operativa.CREA);
		EasyMock.expect(vuds.enviarTramit(t)).andReturn(0);
		EasyMock.replay(vuds);
		Actualizador.setVuds(null);
		Actualizador.setTramitsFallats(new HashSet<Long>());
		//Actualizador.setVuds(vuds);  //Descomentar cuando servidor esta caido. 
		
		Actualizador.actualizar(t);
		while(null==Actualizador.getActualizador());
		Actualizador.getActualizador().join();
		Set<Long> fallats= Actualizador.getTramitsFallats();
		assertEquals(0, fallats.size());
	}

	/*
	public void _testDestinatarioVUDSSoloActualizaTramites() throws InterruptedException {
		Tramite t=rellenaTramite();
		dest.setNombre("VUDS");
		t.setOperativa(Operativa.CREA);
		Actualizador.setVuds(null);
		Actualizador.actualizar(t);
		while(null==Actualizador.getActualizador());
		Actualizador.getActualizador().join();
		Set<Long> fallats= Actualizador.getTramitsFallats();
		assertEquals(0, fallats.size());
	}
	*/
	
	public void testCalActualitzar() {
		 Actualizador a = new Actualizador();
		 Destinatario d=new Destinatario();
		 
		 Object elem=rellenaTramite(true);
		 
		 ((Tramite)elem).setOperativa(Operativa.MODIFICA);
		 d.setNombre("VUDS");
		 assertTrue(a.calActualizar(d, elem));
		 
		 d.setNombre("");
		 assertFalse(a.calActualizar(d, elem));  
		 
		 d.setNombre(null);
		 assertFalse(a.calActualizar(d, elem));  
		 
		 elem=new ProcedimientoLocal();
		 d.setNombre("VUDS");
		 assertFalse(a.calActualizar(d, elem));
		 
		 d.setNombre("");
		 assertTrue(a.calActualizar(d, elem));
		 
		 d.setNombre(null);
		 assertFalse(a.calActualizar(d, elem));
		 
		 System.setProperty("es.indra.caib.rolsac.oficina", "S");
		 assertFalse(a.calActualizar(d, elem));
		 
		 System.setProperty("es.indra.caib.rolsac.oficina", "N");
		 d.setNombre("VUDS");
		 assertFalse(a.calActualizar(d, elem));
		 
		 Tramite t=rellenaTramite(true);
		 t.setOperativa(Operativa.CREA);
		 assertFalse(a.calActualizar(d, t));
		 
		 t.setOperativa(Operativa.MODIFICA);
		 assertTrue(a.calActualizar(d, t));
	}
	
	private Tramite rellenaTramite(boolean vuds) {
		
		TramiteFacadeEJBTest_Spring daoTest =new TramiteFacadeEJBTest_Spring();
		Tramite t=daoTest.creaTramite("tramit test "+new Date(),1,0,0,vuds);
		t.setId(111L);
		return t;
	}
}
