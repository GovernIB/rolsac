package es.caib.rolsac.back2.controller;

import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({DelegateUtil.class})

public class NormativaBackControllerTest {

	
	
	
	
	NormativaBackController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new NormativaBackController();
	}

	/*
	 * Escenari: treure un llistat de normatives ...
	 * DONAT QUE: 
	 * I QUE:
	 * QUAN: s'executa el metode llistatNormatives
	 * ALESHORES: es retorna
	 * I: 
	 */
//	@Test
//	public void llistatNormatives_run1() throws DelegateException {

		//PARA testear el listado de normativas
		
		//se crean los parametros del caso 1
		//cercaExternes, totesUnitats, uaFilles, ordreCamp, ordreTipus, idUA, text
		
		//se crea el resultado esperado del caso 1
		//se crea un mock del NormativasDelegate.buscarNormativas(...)
		//se crea un mock del DelegateUtil.getNormativaDelegate
		//se llama al metodo
		//se compara el resultado
		
/*		
		String[][] parametres = {
				{"id","id_1"}, 
				{"data","01/01/2020"},
				{"data_butlleti","01/01/2020"},
				{"numero","numero_1"},
				{"tipus","tipus_1"},
				{"butlleti","butlleti_1"},
				{"llei","llei_1"},
				{"cercaExternes","true"}
		};
		
		
		String[][] normativesTrobades = {
				{"externa","101"},
				{"externa","102"}
		};
		
		
		testLlistarNormatives(parametres, normativesTrobades);
		
	}

	@Test
	public void llistatNormatives_run2() throws DelegateException {

				
		String[][] parametres = {
				{"id","id_1"}, 
				{"data","01/01/2020"},
				{"data_butlleti","01/01/2020"},
				{"numero","numero_1"},
				{"tipus","tipus_1"},
				{"butlleti","butlleti_1"},
				{"llei","llei_1"},
				{"cercaExternes","false"}
		};
		
		
		String[][] normativesTrobades = {
				{"interna","101"},
				{"interna","102"}
		};
		
		
		testLlistarNormatives(parametres, normativesTrobades);
		
	}
	
	
	private void testLlistarNormatives(String[][] parametres,
									   String[][] norms) throws DelegateException {

		//se crean los parametros
		MockHttpServletRequest request = new MockHttpServletRequest();
		for(String[] param : parametres) {
			request.setParameter(param[0], param[1]);	
		}
		
		//se crea el resultado esperado
		List<Normativa> normativesTrobades = new ArrayList<Normativa>();
		for(String[] normArr : norms) {
			Normativa norm=null;
			if("externa".equals(normArr[0])) {
				norm=new NormativaExterna();
			}
			if("interna".equals(normArr[0])) {
				norm=new NormativaLocal();
			}
			norm.setId(Long.valueOf(normArr[1]));
			normativesTrobades.add(norm);
		}
			
		
		//se crea un mock del NormativasDelegate.buscarNormativas(...)
		NormativaDelegate normativaDelegate = PowerMock.createMock(NormativaDelegate.class);
		EasyMock.expect(normativaDelegate.buscarNormativas(
				EasyMock.anyObject(Map.class), 
				EasyMock.anyObject(Map.class),
				EasyMock.anyObject(String.class),
				EasyMock.anyObject(Long.class),
				EasyMock.anyBoolean(),
				EasyMock.anyBoolean(),
				EasyMock.anyObject(String.class),
				EasyMock.anyObject(String.class))).andReturn(normativesTrobades);
				
		PowerMock.replay(DelegateUtil.class);
		
		
		//se crea el mock del metodo DelegateUtil.getNormativaDelegate
		PowerMock.mockStatic(DelegateUtil.class);
		EasyMock.expect(DelegateUtil.getNormativaDelegate()).andReturn(normativaDelegate);
		PowerMock.replay(DelegateUtil.class);
		
		//se llama al metodo testeado
		Map<String, Object> llistat = controller.llistatNormatives(request, null);
		
		//se comprueba el resultado
		assertEquals(norms[0].length,llistat.get("total"));
		
	}
*/
	
	
}
