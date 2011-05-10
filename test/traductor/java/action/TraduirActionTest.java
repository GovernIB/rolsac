package action;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.back.action.contenido.procedimiento.EditarProcedimientoAction;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
//import org.junit.Test;


public class TraduirActionTest extends TestCase {

	EditarProcedimientoAction action = new EditarProcedimientoAction ();
	
	ActionMapping mapping =EasyMock.createMock(ActionMapping.class);
	ProcedimientoForm form=	EasyMock.createMock(ProcedimientoForm.class);

	TraduccionProcedimientoLocal cat=new TraduccionProcedimientoLocal();
	TraduccionProcedimientoLocal cast = new TraduccionProcedimientoLocal(); 
	TraduccionProcedimientoLocal ang = new TraduccionProcedimientoLocal(); 
	TraduccionProcedimientoLocal ale = new TraduccionProcedimientoLocal();

		
	@Override
	protected void setUp() throws Exception {
		EasyMock.expect(mapping.findForward("success")).andReturn(new ActionForward());
		EasyMock.replay(mapping);
		
		EasyMock.expect(form.get("traducciones", 0)).andReturn(cat);
		EasyMock.expect(form.get("traducciones", 1)).andReturn(cast);
		EasyMock.expect(form.get("traducciones", 2)).andReturn(ang);
		EasyMock.expect(form.get("traducciones", 3)).andReturn(ale);
		EasyMock.replay(form);

	}
	
	public void testTradueixProcedimentCatala_Castella() throws Exception {
		cat.setNombre("sol.licitud");
		action.traduir(mapping, form, null, null);
		assertEquals("solicitud",cast.getNombre());
	}
	
	
	public void testTradueixProcedimentCatala_Castella2() throws Exception {
		cat.setNombre("ajuda");
		action.traduir(mapping, form, null, null);
		assertEquals("ayuda",cast.getNombre());
	}

	public void testTradueixProcedimentCatala_Castella3() throws Exception {
		cat.setNombre("convocatoria");
		action.traduir(mapping, form, null, null);
		//assertEquals("convocatoria",cast.getNombre());
		assertEquals("<U[convocatoria]>",cast.getNombre());
	}

	public void testTradueixProcedimentCatala_Castella4() throws Exception {
		cat.setPlazos("6 mesos");
		action.traduir(mapping, form, null, null);
		assertEquals("6 meses",cast.getPlazos());
	}

	public void testTradueixProcedimentCatala_Castella5() throws Exception {
		cat.setNombre("sol.licitud");
		cat.setPlazos("6 mesos");
		cat.setDestinatarios("funcionaris");
		cat.setLugar("");
		cat.setNotificacion("");
		cat.setObservaciones("");
		cat.setRecursos("");
		cat.setRequisitos("");
		cat.setResolucion("");
		cat.setResumen("");
		cat.setSilencio("");
		
		action.traduir(mapping, form, null, null);
		assertEquals("solicitud",cast.getNombre());
		assertEquals("6 meses",cast.getPlazos());
		assertEquals("funcionarios",cast.getDestinatarios());
	}

	
	public void testTradueixProcedimentCatala_Angles() throws Exception {
		cat.setNombre("sol.licitud");
		action.traduir(mapping, form, null, null);
		assertEquals("request",ang.getNombre());

	}
	
	public void _testTradueixProcedimentCastella_Catala() {
		
	}
}
