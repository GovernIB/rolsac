package test.unitario.model.testsuite.ws;


import java.util.HashSet;

import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;


public class ProcedimientoTransferibleTest extends junit.framework.TestCase {
	
	ProcedimientoTransferible procTransf;
	
	@Override
	protected void setUp() throws Exception {
		procTransf=new ProcedimientoTransferible();
		System.setProperty(ProcedimientoTransferible.URL_PROC, "https://proves.caib.es/govern/sac/visor_proc.do?codi=%id%&lang=" );
	}
	
	public void testRellenar() {
		
		long procId=123L;
		ProcedimientoLocal procLocal = crearMockProcedimientoLocal(procId);

				
		procTransf.rellenar(procLocal);
		
		String expectedUrl="https://proves.caib.es/govern/sac/visor_proc.do?codi="+procId+"&lang=";
		assertEquals(expectedUrl, procTransf.getUrlRemota());
		
	}
	

	private ProcedimientoLocal crearMockProcedimientoLocal(long procId) {
		
		ProcedimientoLocal mock = new ProcedimientoLocal() ; //EasyMock.createMock(ProcedimientoLocal.class);
		mock.setId(procId);
		mock.setUnidadAdministrativa(new UnidadAdministrativa(1234L));
		mock.setMaterias(new HashSet<Materia>());
		mock.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());
				
		return mock;
	}
	
	
	
}
