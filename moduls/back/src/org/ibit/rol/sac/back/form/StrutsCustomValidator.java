package org.ibit.rol.sac.back.form;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;

public class StrutsCustomValidator {
	
	public static boolean validateTwoFieldsNotEqual(
										Object bean,
										ValidatorAction va, 
										Field field,
										ActionErrors errors,
										HttpServletRequest request) {
		
		   String value = obtenerValorPropiedad1(bean, field);
		   
		   if (noTieneValor(value))
			   return true;
			   
		   String value2 = obtenerValorPropiedad2(bean, field);
	
		   if (noTieneValor(value2)) 
			   return true;
	   
		   if (!value.equals(value2))  
			   return true;
		    
		   anadirError(va, field, errors, request);
	       
	        return false;
 
	}



	

	private static String obtenerValorPropiedad1(Object bean, Field field) {
		return obtenerValorPropiedad(bean, field.getProperty());
	}


	
	private static String obtenerValorPropiedad2(Object bean, Field field) {
		String sProperty2 = field.getVarValue("secondProperty");
		return obtenerValorPropiedad(bean, sProperty2);
	}

	

	private static String obtenerValorPropiedad(Object bean, String field) {
		return ValidatorUtil.getValueAsString(bean, field);
	}

	private static boolean noTieneValor(String value) {
		return GenericValidator.isBlankOrNull(value);
	}
	
	
	private static void anadirError(ValidatorAction va, Field field,
			ActionErrors errors, HttpServletRequest request) {
		errors.add(
				obtenerNombreCampo(field),
		        obtenerMensajeError(va, field, request));
	}


	private static String obtenerNombreCampo(Field field) {
		return field.getKey();
	}



	protected static ActionError obtenerMensajeError(ValidatorAction va,
			Field field, HttpServletRequest request) {
		return Resources.getActionError(request,va,field);
	}




}
