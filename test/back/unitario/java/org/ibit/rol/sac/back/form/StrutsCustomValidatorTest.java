package org.ibit.rol.sac.back.form;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Var;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;
import org.easymock.EasyMock;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("org.apache.commons.logging")
@PrepareForTest({StrutsCustomValidator.class})
public class StrutsCustomValidatorTest {


	@Before
	public void setup() {
		crearMockObtenerMensajeError();
	}
	
	private void crearMockObtenerMensajeError() {
		PowerMock.mockStaticPartial(StrutsCustomValidator.class,"obtenerMensajeError");

		
		EasyMock.expect(StrutsCustomValidator.obtenerMensajeError( 
				(ValidatorAction)EasyMock.anyObject(),
				(Field)EasyMock.anyObject(),
				(HttpServletRequest)EasyMock.anyObject())).andReturn(error);

    	PowerMock.replay(StrutsCustomValidator.class);
    	
    	
    	
	}

	@Test
	public void testValidateTwoFieldsNotEqual() {
		UnidadAdministrativaForm bean = new UnidadAdministrativaForm();
		bean.id=12L;
		bean.idPadre=14L;
		Field field =new Field();
		field.setProperty("idPadre");
		Var v=new Var("secondProperty","id","long");
		field.addVar(v);
		ActionErrors errors=new ActionErrors();
		ValidatorAction va=new ValidatorAction();
		
		boolean result = validator.validateTwoFieldsNotEqual(bean, va, field, errors, null);
		assertTrue(result);
		assertEquals(0,errors.size());
	}

	
	@Test
	public void testValidateWhenIdNull() {
		UnidadAdministrativaForm bean = new UnidadAdministrativaForm();
		bean.id=null;
		bean.idPadre=14L;
		Field field =new Field();
		field.setProperty("idPadre");
		Var v=new Var("secondProperty","id","long");
		field.addVar(v);
		ActionErrors errors=new ActionErrors();
		ValidatorAction va=new ValidatorAction();
		
		boolean result = validator.validateTwoFieldsNotEqual(bean, va, field, errors, null);
		assertTrue(result);
		assertEquals(0,errors.size());
	
	}
	
	
	@Test
	public void testValidateWhenIdPadreNull() {
		UnidadAdministrativaForm bean = new UnidadAdministrativaForm();
		bean.id=12L;
		bean.idPadre=null;
		Field field =new Field();
		field.setProperty("idPadre");
		Var v=new Var("secondProperty","id","long");
		field.addVar(v);
		ActionErrors errors=new ActionErrors();
		ValidatorAction va=new ValidatorAction();
		
		boolean result = validator.validateTwoFieldsNotEqual(bean, va, field, errors, null);
		assertTrue(result);
		assertEquals(0,errors.size());
	}
	
	@Test
	public void testValidateTwoFieldsAreEqual() {
		UnidadAdministrativaForm bean = new UnidadAdministrativaForm();
		bean.id=12L;
		bean.idPadre=12L;
		Field field =new Field();
		field.setProperty("idPadre");
		Var v=new Var("secondProperty","id","long");
		field.addVar(v);
		ActionErrors errors=new ActionErrors();
		ValidatorAction va=new ValidatorAction();
		
		boolean result = validator.validateTwoFieldsNotEqual(bean, va, field, errors, null);
		assertFalse(result);
		assertEquals(1,errors.size());
	
	}
	
	
	public class UnidadAdministrativaForm {
		public Long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Long getIdPadre() {
			return idPadre;
		}
		public void setIdPadre(long idPadre) {
			this.idPadre = idPadre;
		}
		Long id;
		Long idPadre;
	}
	
	StrutsCustomValidator validator=new StrutsCustomValidator();
	
	ActionError error= new ActionError("ua.padre.recursivo");
}
