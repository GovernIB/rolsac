package es.caib.rolsac.back2.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.easymock.EasyMock;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import test.unitario.persistence.mock.MockFamiliaDelegate;
import test.unitario.persistence.mock.MockIniciacionDelegate;
import test.unitario.persistence.mock.MockMateriaDelegate;


@RunWith(PowerMockRunner.class)
@PrepareForTest({DelegateUtil.class})

public class CatalegProcedimentsBackControllerTest {
	

	CatalegProcedimentsBackController procedimentsController;
	
	
	@Before
	public void setup() {
		procedimentsController = new CatalegProcedimentsBackController();
	}

	/**
	 * ESCENARI: consultar el model de la pantalla de procediments.
	 * 
	 * DONAT QUE: no hi ha cap UnidadAdministrativa en la sessio
     * I QUE: els altres parametres estan inicialitzats
     * I QUE: els delegate mock estan creats
	 * QUAN: s'executa el metode pantallaProcediment
	 * ALESHORES: es retorna un model sencill de 4 elements
	 * 	menu
	 *  submenu
	 *  submenu_seleccionado
	 *  escriptori
 
	 * I TAMBE: es direcciona a index
	 * 
	 */
	@Test
	public void pantallaProcedimentsRetornaModelSencill() {
		
		//DONAT QUE: no hi ha cap UnidadAdministrativa en la sessio
		MockHttpSession session = new MockHttpSession();
		
		//I QUE: els altres parametres estan inicialitzats
		MockHttpServletRequest request = new MockHttpServletRequest();
		Map<String, Object> model = new HashMap<String,Object>();

		//QUAN 
		String jsp = procedimentsController.pantallaProcediment(model , session, request);
		
		//ALESHORES: es retorna un model 4 elements
		assertEquals(4,model.size());
		assertTrue(model.containsKey("menu"));
		assertTrue(model.containsKey("submenu"));
		assertTrue(model.containsKey("submenu_seleccionado"));
		assertTrue(model.containsKey("escriptori"));
		
		
		//I TAMBE: es direcciona a index
		assertEquals("index",jsp);

	}
	

	/**
	 * ESCENARI: consultar el model de la pantalla de procediments.
	 * 
	 * DONAT QUE: estem en una UnidadAdministrativa en la sessio
     * I QUE: els altres parametres estan inicialitzats
     * I QUE: el idioma es catala
	 * QUAN: s'executa el metode pantallaProcediment
	 * ALESHORES: es retorna un model complet de 9 elements
	 * 	menu
	 *  submenu
	 *  submenu_seleccionado
	 *  escriptori
	 *  idUA
	 *  nomUA
	 *  llistaMateries
	 *  families
	 *  iniciacions
 
	 * I TAMBE: es direcciona a index
	 * 
	 */
	
	@Test
	public void pantallaProcedimentsRetornaModelComplet() {
		// DONAT QUE: estem en una UnidadAdministrativa en la sessio
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("unidadAdministrativa", new UnidadAdministrativa());
		
		// I QUE: el idioma es catala
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(new Locale("ca"));
		
		//I QUE: els altres parametres estan inicialitzats
		Map<String, Object> model = new HashMap<String,Object>();

		//I QUE: els delegate mock estan creats
		
		MockMateriaDelegate materiaDelegate = new MockMateriaDelegate();
		MockFamiliaDelegate familiaDelegate = new MockFamiliaDelegate();
		MockIniciacionDelegate iniciacionDelegate = new MockIniciacionDelegate();
		
		PowerMock.mockStatic(DelegateUtil.class);
		EasyMock.expect(DelegateUtil.getMateriaDelegate()).andReturn(materiaDelegate);
		EasyMock.expect(DelegateUtil.getFamiliaDelegate()).andReturn(familiaDelegate);
		EasyMock.expect(DelegateUtil.getIniciacionDelegate()).andReturn(iniciacionDelegate);
		PowerMock.replay(DelegateUtil.class);
		 

		
		//QUAN 
		String jsp = procedimentsController.pantallaProcediment(model , session, request);
		
		//ALESHORES: es retorna un model 9 elements
		assertEquals(9,model.size());
		assertTrue(model.containsKey("menu"));
		assertTrue(model.containsKey("submenu"));
		assertTrue(model.containsKey("submenu_seleccionado"));
		assertTrue(model.containsKey("escriptori"));
		assertTrue(model.containsKey("idUA"));
		assertTrue(model.containsKey("nomUA"));
		assertTrue(model.containsKey("llistaMateries"));
		assertTrue(model.containsKey("families"));
		assertTrue(model.containsKey("iniciacions"));

		
		//I TAMBE: es direcciona a index
		assertEquals("index",jsp);

	}

	
	


	/**
	 * ESCENARI: consultar el model de la pantalla de procediments.
	 * 
	 * DONAT QUE: estem en una UnidadAdministrativa en la sessio
     * I QUE: els altres parametres estan inicialitzats
     * I QUE: el idioma es catala
     * I QUE: no tinc permis d'accedir al llistat
	 * QUAN: s'executa el metode pantallaProcediment
	 * ALESHORES: retorna un model de 7 elements 
	 * I TAMBE: es retorna un error que no es de permis  
	 * 
	 *  model:
	 *  
	 *  menu
	 *  submenu
	 *  submenu_seleccionado
	 *  escriptori
	 *  idUA
	 *  nomUA
	 *  error
	 * 
	 */
	@Test
	public void pantallaProcediments_sensePermis() {
		// DONAT QUE: estem en una UnidadAdministrativa en la sessio
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("unidadAdministrativa", new UnidadAdministrativa());
		
		// I QUE: el idioma es catala
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(new Locale("ca"));
		
		//I QUE: els altres parametres estan inicialitzats
		Map<String, Object> model = new HashMap<String,Object>();

	    // I QUE: no tinc permis d'accedir al llistat
		MockMateriaDelegate materiaDelegate = new MockMateriaDelegate() {
			public java.util.List listarMaterias() throws DelegateException {
						throw new DelegateException(null);
			}
		};
		
		PowerMock.mockStatic(DelegateUtil.class);
		EasyMock.expect(DelegateUtil.getMateriaDelegate()).andReturn(materiaDelegate);
		PowerMock.replay(DelegateUtil.class);
		 

		
		//QUAN 
		String jsp = procedimentsController.pantallaProcediment(model , session, request);
		
		//ALESHORES: es retorna un model 7 elements
		assertEquals(7,model.size());
		assertTrue(model.containsKey("menu"));
		assertTrue(model.containsKey("submenu"));
		assertTrue(model.containsKey("submenu_seleccionado"));
		assertTrue(model.containsKey("escriptori"));
		assertTrue(model.containsKey("idUA"));
		assertTrue(model.containsKey("nomUA"));
		assertTrue(model.containsKey("error"));
		
		// I TAMBE: es retorna un error de permis
		assertEquals("altres",model.get("error"));

		//I TAMBE: es direcciona a index
		assertEquals("index",jsp);
	}

	
	/**
	 * ESCENARI: consultar el model de la pantalla de procediments.
	 * 
	 * DONAT QUE: estem en una UnidadAdministrativa en la sessio
     * I QUE: els altres parametres estan inicialitzats
     * I QUE: el idioma es catala
     * I QUE: no tinc el role per accedir al llistat
	 * QUAN: s'executa el metode pantallaProcediment
	 * ALESHORES: retorna un model de 7 elements 
	 * I TAMBE: es retorna un error de permis 
	 * 
	 * 
	 */
	@Test
	public void pantallaProcediments_altresProblemes() {
		// DONAT QUE: estem en una UnidadAdministrativa en la sessio
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("unidadAdministrativa", new UnidadAdministrativa());
		
		// I QUE: el idioma es catala
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addPreferredLocale(new Locale("ca"));
		
		//I QUE: els altres parametres estan inicialitzats
		Map<String, Object> model = new HashMap<String,Object>();

	    // I QUE: no tinc el role per accedir al llistat
		MockMateriaDelegate materiaDelegate = new MockMateriaDelegate() {
			public java.util.List listarMaterias() throws DelegateException {
						DelegateException dEx = new DelegateException(null);
						dEx.setSecurityException(new SecurityException());
						throw dEx;
			}
		};
		
		PowerMock.mockStatic(DelegateUtil.class);
		EasyMock.expect(DelegateUtil.getMateriaDelegate()).andReturn(materiaDelegate);
		PowerMock.replay(DelegateUtil.class);
		 

		
		//QUAN 
		String jsp = procedimentsController.pantallaProcediment(model , session, request);
		
		//ALESHORES: es retorna un model 7 elements
		assertEquals(7,model.size());
		assertTrue(model.containsKey("menu"));
		assertTrue(model.containsKey("submenu"));
		assertTrue(model.containsKey("submenu_seleccionado"));
		assertTrue(model.containsKey("escriptori"));
		assertTrue(model.containsKey("idUA"));
		assertTrue(model.containsKey("nomUA"));
		assertTrue(model.containsKey("error"));
		
		// I TAMBE: es retorna un error de permis
		assertEquals("permisos",model.get("error"));

		//I TAMBE: es direcciona a index
		assertEquals("index",jsp);

	}
	
}
