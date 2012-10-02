package es.caib.rolsac.oracle.hibernate;

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
@PrepareForTest({es.caib.rolsac.oracle.hibernate.StringClobType.class}) // classes que PowerMock necessita manipular el seu codi

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
	public void testNullSafeGet_Oracle_ClobEsNull() throws Exception {
		
		//creem el mock de Statement
		Statement statement = PowerMock.createMock(Statement.class);
		
		//creem el mock de resultSet: getClob, getStatement
		String[] names = {"test"};
		ResultSet resultSet = PowerMock.createMock(ResultSet.class);
		EasyMock.expect(resultSet.getClob(names[0])).andReturn(null);
		EasyMock.expect(resultSet.getStatement()).andReturn(statement);
		PowerMock.replay(resultSet);

		//creem el mock parcial de StringClobType
		es.caib.rolsac.oracle.hibernate.StringClobType lobType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.StringClobType.class, "getDatabaseProductName");
		PowerMock.expectPrivate(lobType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.replay(lobType);
		
		
		Object o= null;
		String texteReturned = (String) lobType.nullSafeGet(resultSet, names, o);
		
		assertEquals(null, texteReturned);
		
	}

	@Test
	public void testNullSafeGet_Oracle_ClobNoEsNull() throws Exception {
		
		String texteExpected = "hola";

		
		//creem el mock de Statement
		Statement statement = PowerMock.createMock(Statement.class);
		
		//creem el mock de Blob: getClob, length, getSubString
		Clob clob = PowerMock.createMock(Clob.class);
		EasyMock.expect(clob.length()).andReturn(4L).anyTimes();
		EasyMock.expect(clob.getSubString(1, 4)).andReturn(texteExpected);
		PowerMock.replay(clob);
		
		//creem el mock de resultSet: getBlob, getStatement
		String[] names = {"test"};
		ResultSet resultSet = PowerMock.createMock(ResultSet.class);
		EasyMock.expect(resultSet.getClob(names[0])).andReturn(clob);
		EasyMock.expect(resultSet.getStatement()).andReturn(statement);
		PowerMock.replay(resultSet);

		//creem el mock parcial de StringClobType: getDatabaseProductName
		es.caib.rolsac.oracle.hibernate.StringClobType lobType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.StringClobType.class, "getDatabaseProductName");
		PowerMock.expectPrivate(lobType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.replay(lobType);
		
		
		Object o= null;
		String  texteReturned = (String) lobType.nullSafeGet(resultSet, names, o);
		
		assertEquals(texteExpected, texteReturned);
		
	}

	
	@Test
	public void testNullSafeSet_Oracle_ClobNoEsNull() throws Exception {

		int pos_i=1;
		Object value= "pepe";

		
		//creem el mock de Blob
		Clob clob = PowerMock.createMock(Clob.class);

		//creem el mock de PreparedStatement: setBlob
		PreparedStatement statement = PowerMock.createMock(PreparedStatement.class);
		statement.setClob(pos_i, clob);
		PowerMock.replay(statement);

		//creem el mock parcial de StringClobType: getDatabaseProductName, createAndRegisterTemporalClob
		es.caib.rolsac.oracle.hibernate.StringClobType lobType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.StringClobType.class, "getDatabaseProductName", "createAndRegisterTemporalClob");
		PowerMock.expectPrivate(lobType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.expectPrivate(lobType, "createAndRegisterTemporalClob",value,statement).andReturn(clob);		PowerMock.replay(lobType);
		

		
		lobType.nullSafeSet(statement, value, pos_i);
		
		//aquest test passa si arriba aqui i no s'ha generat un 
		// AssertionError unexpected setBlob
	}

	@Test
	public void testNullSafeSet_Oracle_ValueEsNull() throws Exception {

		int pos_i=1;
		Object value= null;

		
		//creem el mock de PreparedStatement: setNull
		PreparedStatement statement = PowerMock.createMock(PreparedStatement.class);
		statement.setNull(pos_i, Types.CLOB);
		PowerMock.replay(statement);

		//creem el mock parcial de StringClobType: getDatabaseProductName
		es.caib.rolsac.oracle.hibernate.StringClobType lobType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.StringClobType.class, "getDatabaseProductName", "createAndRegisterTemporalBlob");
		PowerMock.expectPrivate(lobType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.replay(lobType);
		

		
		lobType.nullSafeSet(statement, value, pos_i);
		
		//aquest test passa si arriba aqui i no s'ha generat un 
		// AssertionError unexpected setNull
	}

		
	public void testDeepCopy() {
		fail("Not yet implemented"); // TODO
	}

	
	public void testIsMutable() {
		fail("Not yet implemented"); // TODO
	}

}
