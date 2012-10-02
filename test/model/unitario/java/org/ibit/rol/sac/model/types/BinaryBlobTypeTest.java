package org.ibit.rol.sac.model.types;

import static org.junit.Assert.*;

import java.sql.Blob;
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
@PrepareForTest({org.ibit.rol.sac.model.types.BinaryBlobType.class}) // classes que PowerMock necessita manipular el seu codi

public class BinaryBlobTypeTest {

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
		
		//creem el mock de resultSet: getBytes, getStatement 
		byte[] bytesExpected = {1,2,3,4};
		String[] names = {"test"};
		ResultSet resultSet = PowerMock.createMock(ResultSet.class);
		EasyMock.expect(resultSet.getBytes(names[0])).andReturn(bytesExpected);
		EasyMock.expect(resultSet.getStatement()).andReturn(statement);
		PowerMock.replay(resultSet);

		//creem el mock parcial de BinaryBlobType
		org.ibit.rol.sac.model.types.BinaryBlobType binaryType = PowerMock.createPartialMock(
				org.ibit.rol.sac.model.types.BinaryBlobType.class, "getDatabaseProductName");
		PowerMock.expectPrivate(binaryType, "getDatabaseProductName",statement).andReturn("PostgreSQL");
		PowerMock.replay(binaryType);
		
		
		Object o= null;
		byte[] bytesReturned = (byte[]) binaryType.nullSafeGet(resultSet, names, o);
		
		assertEquals(bytesExpected, bytesReturned);
		
	}

	

	
	@Test
	public void testNullSafeSet_PostgreSQL_ValueNoEsNull() throws Exception {

		int pos_i=1;
		byte[] value = {1,2,3,4};
		
		//creem el mock de PreparedStatement: setBytes
		PreparedStatement statement = PowerMock.createMock(PreparedStatement.class);
		statement.setBytes(pos_i,value);
		PowerMock.replay(statement);

		//creem el mock parcial de BinaryBlobType: getDatabaseProductName
		org.ibit.rol.sac.model.types.BinaryBlobType binaryType = PowerMock.createPartialMock(
				org.ibit.rol.sac.model.types.BinaryBlobType.class, "getDatabaseProductName", "createAndRegisterTemporalBlob");
		PowerMock.expectPrivate(binaryType, "getDatabaseProductName",statement).andReturn("PostgreSQL");
		PowerMock.replay(binaryType);
		

		
		binaryType.nullSafeSet(statement, value, pos_i);
		
		//aquest test passa si arriba aqui i no s'ha generat un 
		// AssertionError unexpected setBytes
	}

	
	public void testDeepCopy() {
		fail("Not yet implemented"); // TODO
	}

	
	public void testIsMutable() {
		fail("Not yet implemented"); // TODO
	}

}
