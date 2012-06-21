package org.ibit.rol.sac.model.types;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;


import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("org.apache.commons.logging")  // para q no salga duplicate visibility error
@PrepareForTest({org.ibit.rol.sac.model.types.StringClobType.class}) // classes que PowerMock necessita manipular el seu codi

public class StringClobTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	
	public void testSqlTypes() {
		fail("Not yet implemented"); // TODO
	}

	
	public void testReturnedClass() {
		fail("Not yet implemented"); // TODO
	}

	
	public void testEqualsObjectObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testNullSafeGet_Postgressql() throws Exception {
		
		
		//creem el mock de Statement
		Statement statement = PowerMock.createMock(Statement.class);
		
		//creem el mock de resultSet: getCharacterStream
		
		String texteExpected = "hola";
		Reader reader = new StringReader(texteExpected);  
		String[] names = {"test"};
		ResultSet resultSet = PowerMock.createMock(ResultSet.class);
		EasyMock.expect(resultSet.getCharacterStream(names[0])).andReturn(reader);
		EasyMock.expect(resultSet.getStatement()).andReturn(statement);
		PowerMock.replay(resultSet);

		//creem el mock parcial de StringClobType
		org.ibit.rol.sac.model.types.StringClobType lobType = PowerMock.createPartialMock(
				org.ibit.rol.sac.model.types.StringClobType.class, "getDatabaseProductName");
		PowerMock.expectPrivate(lobType, "getDatabaseProductName",statement).andReturn("PostgreSQL");
		PowerMock.replay(lobType);
		
		
		Object o= null;
		String  texteReturned = (String) lobType.nullSafeGet(resultSet, names, o);
		
		assertEquals(texteExpected, texteReturned);
		
	}

	
		
	@Test
	public void testNullSafeSet_PostgreSQL_ValueNoEsNull() throws Exception {

		int pos_i=1;
		String texteExpected = "hola";
		Reader reader = new StringReader(texteExpected);  
		
		//creem el mock de PreparedStatement: setBytes
		PreparedStatement statement = PowerMock.createMock(PreparedStatement.class);
		statement.setCharacterStream(EasyMock.anyInt(),(Reader)EasyMock.anyObject(),EasyMock.anyInt());
		PowerMock.replay(statement);

		//creem el mock parcial de StringClobType: getDatabaseProductName
		org.ibit.rol.sac.model.types.StringClobType lobType = PowerMock.createPartialMock(
				org.ibit.rol.sac.model.types.StringClobType.class, "getDatabaseProductName", "createAndRegisterTemporalBlob");
		PowerMock.expectPrivate(lobType, "getDatabaseProductName",statement).andReturn("PostgreSQL");
		PowerMock.replay(lobType);
		

		
		lobType.nullSafeSet(statement, texteExpected, pos_i);
		
		//aquest test passa si arriba aqui i no s'ha generat un 
		// AssertionError unexpected setCharacterStream
	}

	
	public void testDeepCopy() {
		fail("Not yet implemented"); // TODO
	}

	
	public void testIsMutable() {
		fail("Not yet implemented"); // TODO
	}

}
