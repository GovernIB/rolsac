package test.unitario.model.testsuite.ws;


import java.util.HashSet;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;


public class UATransferibleTest extends junit.framework.TestCase {
	
	UnidadAdministrativaTransferible uaTransf;
	
	@Override
	protected void setUp() throws Exception {
		uaTransf=new UnidadAdministrativaTransferible();
		System.setProperty(UnidadAdministrativaTransferible.URL_UA, "https://proves.caib.es/govern/sac/visor_proc.do?codi=%id%&lang=" );
	}
	
	public void testRellenar() {
		
		long id=123L;
		UnidadAdministrativa ua = crearMockUA(id);

				
		uaTransf.rellenar(ua);
		
		String expectedUrl="https://proves.caib.es/govern/sac/visor_proc.do?codi="+id+"&lang=";
		assertEquals(expectedUrl, uaTransf.getUrlRemota());
		
	}
	

	private UnidadAdministrativa crearMockUA(long id) {
		UnidadAdministrativa mock=new UnidadAdministrativa (id);
		return mock;
	}

	
}
