package  es.caib.vuds;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import test.integracion.ejb.TramiteFacadeEJBTest_Spring;

import es.caib.vuds.TramiteValidado;
import es.caib.vuds.VentanillaUnica;
import es.map.vuds.si.service.webservice.TramiteVuds;


/**
 * Requisits:
 * 	- tenir en web service en marxa:
 * 		C:\j2ee-developer\webservices\axis2-1.5\bin\axis2-1.5\bin\axis2server.bat
 * 
 * @author u92770
 *
 */

public class VentanillaUnicaTest extends junit.framework.TestCase {

	Tramite t=new Tramite();
	VentanillaUnica vu=new VentanillaUnica();
	test.integracion.ejb.TramiteFacadeEJBTest_Spring daoTest=new test.integracion.ejb.TramiteFacadeEJBTest_Spring();

	@Override
	protected void setUp() throws Exception {

		
	}

	public void _test01GenerarTramitVudsSenseDocuments() throws IOException {
		
		_("test amb 1 taxa");
		t=daoTest.creaTramite("tramit test "+new Date(),1,0,0,true);
		t.setId(12345L);
		t.getProcedimiento().setId(247L);
		
		
		t.setOperativa(Operativa.CREA);
		es.map.vuds.si.service.webservice.Tramite tramite= vu.tramitRolsacl2TramiteVUDS(t,"ca");
		
		_(tramite.getCodigoIdentificador());
		_(tramite.getDenominacionTramite());
		_(tramite.getEnlaceConsulta());
		_(tramite.getFormulario()[0].getUrlDescarga());
		_(tramite.getTasa().getCodificacion());
		_(tramite.getTasa().getDescripcionTasa());
		_(tramite.getTasa().getModoPago());
		_(tramite.getResultado());
		_(tramite.getFormaIniciacion().getIdFormaIniciacion());

		
		_("\ntest amb 2 taxes");
		t=daoTest.creaTramite("tramit test "+new Date(),2,0,0,true);
		t.setId(12345L);
		t.getProcedimiento().setId(246L);
		
		
		t.setOperativa(Operativa.CREA);
		tramite= vu.tramitRolsacl2TramiteVUDS(t,"ca");
		
		_(tramite.getCodigoIdentificador());
		_(tramite.getDenominacionTramite());
		_(tramite.getEnlaceConsulta());
		_(tramite.getFormulario()[0].getUrlDescarga());
		_(tramite.getTasa().getCodificacion());
		_(tramite.getTasa().getDescripcionTasa());
		_(tramite.getTasa().getModoPago());
		
	}

	public void _test01GenerarTramitVudsAmbDocuments() throws IOException {
		//afegeix un formulari
		String fname="spy.properties";
		Archivo archivo=daoTest.creaArchivo(fname);
		DocumentTramit d = daoTest.creaDocument("doc111", archivo);
		d.setId(2323L);
		d.setTipus(DocumentTramit.FORMULARI);
		d.setArchivo(daoTest.creaArchivo("spy.properties"));
		d.getArchivo().setId(1414L);
		Set<DocumentTramit> formularios = new HashSet<DocumentTramit>();
		formularios.add(d);
		t.setFormularios(formularios);
		
		t.setOperativa(Operativa.CREA);
		es.map.vuds.si.service.webservice.Tramite tramite= vu.tramitRolsacl2TramiteVUDS(t,"ca");
		
		_(tramite.getCodigoIdentificador());
		_(tramite.getDenominacionTramite());
		_(tramite.getEnlaceConsulta());
		_(tramite.getFormulario()[0].getUrlDescarga());
	}

	
	//OK 19/01/2010
	public  void _test02EnviarAltaTramitOK() throws WSInvocatorException   {
		t=daoTest.creaTramite("tramit test "+new Date(),1,0,0,true);
		t.setId(12345L);
		t.getProcedimiento().setId(247L);
		t.setOperativa(Operativa.CREA);
		int codi=vu.enviarTramit(t,"ca");
		assertEquals(0,codi);
	}

	public  void _test03EnviarAltaProcedimientoLocalFalla() throws WSInvocatorException  {
		t.setOperativa(Operativa.CREA);
		int codi=vu.enviarTramit(t,"ca");
		assertEquals(-2,codi);  //-2 = servidor no actiu
	}
	
	
	
	public  void _test04EnviarTramiteOK() {
				Tramite tra=new Tramite();
		//int codi=ClientVentanillaUnica.enviarTramite(tra);
		//assertEquals(-2,codi);  //servidor no actiu
	}

	//OK 19/01/2010
	public void _test04CargarCodigosVuds_castella()  throws WSInvocatorException {
		List<TramiteVuds> codis = vu.cargarCodisVuds("es");
		assertNotSame(0,codis.size());
		_(codis.size());
		_(codis.get(0).getIdTramiteVuds());
		_(codis.get(0).getDescripcionTramiteVuds());
	}
	
	public void _test04CargarCodigosVuds_catala()  throws WSInvocatorException {
		List<TramiteVuds> codis = vu.cargarCodisVuds("ca");
		assertNotSame(0,codis.size());
		_(codis.size());
		_(codis.get(0).getIdTramiteVuds());
		_(codis.get(0).getDescripcionTramiteVuds());
	}

	public void test05ValidarTramite_castella() {
		
		_("test amb 1 taxa");
		t=daoTest.creaTramite("tramit test "+new Date(),1,0,0,true);
		t.setId(12345L);
		t.getProcedimiento().setId(247L);
		t.setOperativa(Operativa.CREA);
		
		TramiteValidado traval = vu.validarTramiteVuds(t,"es");
		
		_(Arrays.toString(traval.getSinTraducir()));
		assertSame(10,traval.getSinTraducir().length);
		
		//ara traduim el nom, la descripcio i el plaç
		TraduccionTramite tt = new TraduccionTramite();

		tt.setNombre("nombre del tramite");
		tt.setDescripcion("mi descr del tramite");
		tt.setPlazos("plazo maximo de presentacion");
		tt.setDocumentacion("documentacion del tramite");
		tt.setRequisits("requisitos del tramite");
		
		t.setTraduccion("es", tt);
				
		traval = vu.validarTramiteVuds(t,"es");
		
		_(Arrays.toString(traval.getSinTraducir()));
		assertSame(5,traval.getSinTraducir().length);

		//ara traduim el resultat
		TraduccionProcedimientoLocal trapro = new TraduccionProcedimientoLocal();
		trapro.setResultat("un resultado");
		t.getProcedimiento().setTraduccion("es", trapro);

		traval = vu.validarTramiteVuds(t,"es");

		_(Arrays.toString(traval.getSinTraducir()));
		assertSame(4,traval.getSinTraducir().length);


		
		

	}
	
	private void _(Object o) { System.out.println(o); }
}
