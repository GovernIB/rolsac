package org.ibit.rol.sac.model.ws;


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
	}
	
	/**
	 * se comprueba que urlremota esta completa cuando si existe la propietat url_ficha
	 */
	public void testUrlRemotaSeCompletaCuandoPropiedadExiste() {
		long id=123L;
		Ficha procLocal = crearMockFicha(id);
		System.setProperty(FichaTransferible.URL_FICHA, "https://proves.caib.es/govern/sac/visor_proc.do?codi=%id%&lang=" );
				
		fichaTransf.rellenar(procLocal);
		
		String expectedUrl="https://proves.caib.es/govern/sac/visor_proc.do?codi="+id+"&lang=";
		assertEquals(expectedUrl, fichaTransf.getUrlRemota());
	}
	

	/**
	 * se comprueba que urlremota es null cuando no existe la propietat url_ficha
	 */

	public void testUrlRemotaIsNullCuandoPropiedadNoExiste() {
		long id=123L;
		Ficha procLocal = crearMockFicha(id);
		System.clearProperty(FichaTransferible.URL_FICHA);
				
		fichaTransf.rellenar(procLocal);
		
		assertNull(fichaTransf.getUrlRemota());
	}

	
	
	private Ficha crearMockFicha(long id) {
		Ficha mock = new Ficha(id);
		return mock;
	}
	
	
}
