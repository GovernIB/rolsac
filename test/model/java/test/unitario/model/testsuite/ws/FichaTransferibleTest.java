package test.unitario.model.testsuite.ws;


import java.util.HashSet;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;


public class FichaTransferibleTest extends junit.framework.TestCase {
	
	FichaTransferible fichaTransf;
	
	@Override
	protected void setUp() throws Exception {
		fichaTransf=new FichaTransferible();
		System.setProperty(FichaTransferible.URL_FICHA, "https://proves.caib.es/govern/sac/visor_proc.do?codi=%id%&lang=" );
	}
	
	public void testRellenar() {
		
		long id=123L;
		Ficha procLocal = crearMockFicha(id);

				
		fichaTransf.rellenar(procLocal);
		
		String expectedUrl="https://proves.caib.es/govern/sac/visor_proc.do?codi="+id+"&lang=";
		assertEquals(expectedUrl, fichaTransf.getUrlRemota());
		
	}
	

	private Ficha crearMockFicha(long id) {
		Ficha mock = new Ficha(id);
		return mock;
	}
	
	
}
