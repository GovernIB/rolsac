package es.caib.rolsac.persistence.hibernate;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SessionInterceptorBuilder.class)

public class SessionInterceptorBuilderTest {

	SessionInterceptorBuilder builder;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void teardown() {
		
	}
	
	@Test
	public void testBuild_sinInterceptoresSesion_noPropiedad_DevuelveNull() {
		
		ChainedSessionInterceptor chained = builder.build();
		assertNull(chained);
	}

	
	@Test
	public void testBuild_sinInterceptoresSesion_conPropiedad_DevuelveNull() {
		
		System.setProperty("es.caib.rolsac.persistence.hibernate.sessionInterceptors","");
		ChainedSessionInterceptor chained = builder.build();
		assertNull(chained);
	}


	@Test
	public void testBuild_con_1_InterceptorDeSesionDevuelve_1_ChainedCon_1_Interceptor() {

		
		//builder = new SessionInterceptorBuilder();

		System.setProperty("es.caib.rolsac.persistence.hibernate.sessionInterceptors","es.caib.rolsac.persistence.hibernate.ConnectionInfoInterceptor");
		System.setProperty("es.caib.rolsac.persistence.hibernate.databaseConnectionInfoClass","test.unitario.persistence.mock.MockConnectionInfo");
		
		ChainedSessionInterceptor chained = builder.build();
		assertNotNull(chained);
		assertEquals(1, chained.interceptors.length);

	}
	
	// TODO test 2: con 1 session interceptor + 1 global int. devuelve 2 interceptors, el de sesion i el global
	// TODO test 3: con 1 session interceptor + 0 global int. devuelve 1 interceptors, el de sesion 
	// TODO test 4: repetir test 2 varias veces, con el mismo builder

}
