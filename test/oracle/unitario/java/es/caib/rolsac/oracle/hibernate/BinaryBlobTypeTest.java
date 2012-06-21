package es.caib.rolsac.oracle.hibernate;


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
@PrepareForTest({es.caib.rolsac.oracle.hibernate.BinaryBlobType.class}) // classes que PowerMock necessita manipular el seu codi

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
	public void testNullSafeGet_Oracle_BlobEsNull() throws Exception {
		
		//creem el mock de Statement
		Statement statement = PowerMock.createMock(Statement.class);
		
		//creem el mock de resultSet: getBlob, getStatement
		String[] names = {"test"};
		ResultSet resultSet = PowerMock.createMock(ResultSet.class);
		EasyMock.expect(resultSet.getBlob(names[0])).andReturn(null);
		EasyMock.expect(resultSet.getStatement()).andReturn(statement);
		PowerMock.replay(resultSet);

		//creem el mock parcial de BinaryBlobType
		es.caib.rolsac.oracle.hibernate.BinaryBlobType binaryType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.BinaryBlobType.class, "getDatabaseProductName");
		PowerMock.expectPrivate(binaryType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.replay(binaryType);
		
		
		Object o= null;
		byte[] bytesReturned = (byte[]) binaryType.nullSafeGet(resultSet, names, o);
		
		assertEquals(null, bytesReturned);
		
	}

	@Test
	public void testNullSafeGet_Oracle_BlobNoEsNull() throws Exception {
		
		//creem el mock de Statement
		Statement statement = PowerMock.createMock(Statement.class);
		
		//creem el mock de Blob: getBytes, length
		byte[] bytesExpected = {1,2,3,4};
		Blob blob = PowerMock.createMock(Blob.class);
		EasyMock.expect(blob.length()).andReturn(4L);
		EasyMock.expect(blob.getBytes(1, 4)).andReturn(bytesExpected);
		PowerMock.replay(blob);
		
		//creem el mock de resultSet: getBlob, getStatement
		String[] names = {"test"};
		ResultSet resultSet = PowerMock.createMock(ResultSet.class);
		EasyMock.expect(resultSet.getBlob(names[0])).andReturn(blob);
		EasyMock.expect(resultSet.getStatement()).andReturn(statement);
		PowerMock.replay(resultSet);

		//creem el mock parcial de BinaryBlobType: getDatabaseProductName
		es.caib.rolsac.oracle.hibernate.BinaryBlobType binaryType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.BinaryBlobType.class, "getDatabaseProductName");
		PowerMock.expectPrivate(binaryType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.replay(binaryType);
		
		
		Object o= null;
		byte[] bytesReturned = (byte[]) binaryType.nullSafeGet(resultSet, names, o);
		
		assertEquals(bytesExpected, bytesReturned);
		
	}

	
	@Test
	public void testNullSafeSet_Oracle_BlobNoEsNull() throws Exception {

		int pos_i=1;
		Object value= "pepe";

		
		//creem el mock de Blob
		Blob blob = PowerMock.createMock(Blob.class);

		//creem el mock de PreparedStatement: setBlob
		PreparedStatement statement = PowerMock.createMock(PreparedStatement.class);
		statement.setBlob(pos_i, blob);
		PowerMock.replay(statement);

		//creem el mock parcial de BinaryBlobType: getDatabaseProductName, createAndRegisterTemporalBlob
		es.caib.rolsac.oracle.hibernate.BinaryBlobType binaryType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.BinaryBlobType.class, "getDatabaseProductName", "createAndRegisterTemporalBlob");
		PowerMock.expectPrivate(binaryType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.expectPrivate(binaryType, "createAndRegisterTemporalBlob",value,statement).andReturn(blob);
		PowerMock.replay(binaryType);
		

		
		binaryType.nullSafeSet(statement, value, pos_i);
		
		//aquest test passa si arriba aqui i no s'ha generat un 
		// AssertionError unexpected setBlob
	}

	@Test
	public void testNullSafeSet_Oracle_ValueEsNull() throws Exception {

		int pos_i=1;
		Object value= null;

		
		//creem el mock de PreparedStatement: setNull
		PreparedStatement statement = PowerMock.createMock(PreparedStatement.class);
		statement.setNull(pos_i, Types.BLOB);
		PowerMock.replay(statement);

		//creem el mock parcial de BinaryBlobType: getDatabaseProductName
		es.caib.rolsac.oracle.hibernate.BinaryBlobType binaryType = PowerMock.createPartialMock(
				es.caib.rolsac.oracle.hibernate.BinaryBlobType.class, "getDatabaseProductName", "createAndRegisterTemporalBlob");
		PowerMock.expectPrivate(binaryType, "getDatabaseProductName",statement).andReturn("Oracle");
		PowerMock.replay(binaryType);
		

		
		binaryType.nullSafeSet(statement, value, pos_i);
		
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
