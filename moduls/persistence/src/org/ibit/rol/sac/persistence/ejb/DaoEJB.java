package org.ibit.rol.sac.persistence.ejb;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 * Bean con la funcionalidad b�sica para interactuar con JDBC.
 *
 * @ejb.bean
 *  view-type="remote"
 *  generate="false"
 * @ejb.security-role-ref role-name="sacsystem" role-link="${role.system}"
 * @ejb.security-role-ref role-name="sacadmin" role-link="${role.admin}"
 * @ejb.security-role-ref role-name="sacsuper" role-link="${role.super}"
 * @ejb.security-role-ref role-name="sacoper" role-link="${role.oper}"
 */
public abstract class DaoEJB  implements SessionBean {

	   /** objecte per accedir a les conexions a base de dades */
	   protected transient DataSource datasource;

	   /** tiempo que tarda una sql en ejecutarse a partir del cual se mostrar� un warning en el log */
	   protected int _timewarning = 5000; 

	   /** Conexion con la BD */
	   protected Connection dbConnection = null;
	   
	    public void ejbCreate() throws CreateException { }

		public void ejbActivate() throws EJBException, RemoteException { }

		public void ejbPassivate() throws EJBException, RemoteException { }

		public void ejbRemove() throws EJBException, RemoteException {
			try {
				close(dbConnection);
	        } catch (Exception he) {
	            throw new EJBException(he);
	        }
		}

		public void setSessionContext(SessionContext arg0) throws EJBException,
				RemoteException { }
		
	    
	   /**
	    * Obte l'objecte per accedir a les conexions (datasource) 
	    * enregistrat en el servidor de noms
	    *
	    * @name nom JNDI del datasource que emprar� aquest DAO
	    *
	    * @throws SQLException si hi ha error accedint al servidor de noms.
	    */ 
	   public void init (String name) throws SQLException {
	      try {
	         Context ic = new InitialContext();
	         datasource = (DataSource) ic.lookup(name);
	      } catch (NamingException e) {
	    	  throw new SQLException("Error cercant nom JNDI : " + name);
	      }
	   }

	 
	    /**
	     * M�tode d'utilitat per poder evitar els valors nulls
	     * (versi� amb valor per defecte expl�cit)
	     * 
	     * @param value valor que volem comprovar que no sigui null
	     * @param defaultValue valor que emprarem si value es null
	     * @return value si no es null i defaultValue si es null
	     */
	    protected String nvl (String value, String defaultValue ) {
	        if ( value == null ) {
	           return defaultValue;
	        }
	        return value;
	    }

	    /**
	     * M�tode d'utilitat per poder evitar els valors nulls.
	     * (versi� amb valor per defecte implicit)
	     * 
	     * @param value valor que volem comprovar que no sigui null
	     * @return value si no es null i cadena buida si es null
	     */
	    protected String nvl (String value) {
	       return nvl(value, "");
	    }

	    /*=====================================================
	                  M�todes de conexions
	      =====================================================*/
	 
	   /** Obte una conexi� a la base de dades */
	    protected Connection getDBConnection() throws SQLException {
	    	
	    	Connection dbConnection = null;
	    
	        try {
	            dbConnection = datasource.getConnection();
	        } catch (Exception se) {
	        	throw new SQLException ("SQL Exception while getting " +
	                                "DB connection : \n" + se);
	        }   
	            /*
	        } catch (SQLException se) {
	        	throw new SQLException ("SQL Exception while getting " +
	                                "DB connection : \n" + se);
	        }
	        */
	        return dbConnection;
	    }
	   
	    /** allibera una conexi� a la base de dadess */
	    protected void close (Connection dbConnection) throws SQLException {
	   
	        try {
	            
	            if (dbConnection != null && !dbConnection.isClosed())     
	                            dbConnection.close();
	      
	        } catch (SQLException se) {
	            throw new SQLException("SQL Exception while closing " +
	                                        "DB connection : \n" + se);
	        }
	    }

	    /** 
	     * Tanca un result set
	     * 
	     * @ejb.interface-method
	     * @ejb.permission unchecked="true" 
	     */
	    public void close(ResultSet result) throws SQLException {
	        try {
	            if (result != null) {
	                result.close();
	            }
	        } catch (SQLException se) {
	            throw new SQLException("SQL Exception while closing " +
	                                        "Result Set : \n" + se);
	        }
	    }
	 
	    /** 
	     * Tanca un statement 
	     *
	     * @ejb.interface-method
	     * @ejb.permission unchecked="true" 
	     */
	    public void close(Statement stmt) throws SQLException {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException se) {
	            throw new SQLException("SQL Exception while closing " +
	                                        "Statement : \n" + se);
	        }
	    }
	    
	    /** 
	     * Tanca un PreparedStatement 
	    *
	    * @ejb.interface-method
	    * @ejb.permission unchecked="true" 
	    */
	    public void close(PreparedStatement stmt) throws SQLException {
	       try {
	           if (stmt != null) {
	               stmt.close();
	           }
	       } catch (SQLException se) {
	           throw new SQLException("SQL Exception while closing " +
	                                       "Statement : \n" + se);
	       }
		}

	    /**
	     * Mapea la excepcion retornada por oracle
	     * @param sqle
	     * @return String
	     */
	    protected String errorOracle(SQLException sqle) {
			
	    	  String msgExcep = sqle.toString();

	    	    	if (msgExcep.indexOf("ORA-00001") != -1) {
	    	    		return sqle.toString()+"\nNo se crear el elemento. Restricci�n de integridad �nica.";
	    	    	} else if (msgExcep.indexOf("ORA-00018") != -1) {
	    	    		return sqle.toString()+"\nSobrepasado el n�mero m�ximo de sesiones."; 
	    	        } else if (msgExcep.indexOf("ORA-00020") != -1) {
	    	    		return sqle.toString()+"\nSobrepasado el l�mite de procesos."; 
	    	        } else if (msgExcep.indexOf("ORA-00001") != -1) {
	    	    		return sqle.toString()+"\nClave duplicada."; 
	    	        } else if (msgExcep.indexOf("ORA-00910") != -1) {
	    	    		return sqle.toString()+"\nSobrepasado tama�o de campo."; 
	    	        } else if (msgExcep.indexOf("ORA-00997") != -1) {
	    	    		return sqle.toString()+"\nUso no permitido de valor LONG."; 
	    	        } else if (msgExcep.indexOf("ORA-01005") != -1) {
	    	    		return sqle.toString()+"\nPassword incorrecto."; 
	    	        } else if (msgExcep.indexOf("ORA-01014") != -1) {
	    	    		return sqle.toString()+"\nBase de Datos no operativa."; 
	    	        } else if (msgExcep.indexOf("ORA-01033") != -1) {
	    	    		return sqle.toString()+"\nBase de Datos no operativa."; 
	    	        } else if (msgExcep.indexOf("ORA-01034") != -1) {
	    	    		return sqle.toString()+"\nBase de Datos no operativa."; 
	    	        } else if (msgExcep.indexOf("ORA-01089") != -1) {
	    	    		return sqle.toString()+"\nBase de Datos no operativa."; 
	    	        } else if (msgExcep.indexOf("ORA-01090") != -1) {
	    	    		return sqle.toString()+"\nBase de Datos no operativa."; 
	    	        } else if (msgExcep.indexOf("ORA-01017") != -1) {
	    	    		return sqle.toString()+"\nUsuario o Password incorrecto."; 
	    	        } else if (msgExcep.indexOf("ORA-01400") != -1) {
	    	    		return sqle.toString()+"\nValor nulo no permitido en inserci�n."; 
	    	        } else if (msgExcep.indexOf("ORA-01401") != -1) {
	    	    		return sqle.toString()+"\nSobrepasado el tama�o m�ximo para el campo."; 
	    	        } else if (msgExcep.indexOf("ORA-01407") != -1) {
	    	    		return sqle.toString()+"\nValor nulo no permitido en actualizaci�n."; 
	    	        } else if (msgExcep.indexOf("ORA-01410") != -1) {
	    	    		return sqle.toString()+"\nRowID no v�lido."; 
	    	        } else if (msgExcep.indexOf("ORA-01438") != -1) {
	    	    		return sqle.toString()+"\nSobrepasado el tamaño máximo para el campo."; 
	    	        } else if (msgExcep.indexOf("ORA-01722") != -1) {
	    	    		return sqle.toString()+"\nFormato númerico incorrecto."; 
	    	        } else if (msgExcep.indexOf("ORA-01820") != -1) {
	    	    		return sqle.toString()+"\nFormato de fecha no válido."; 
	    	        } else if (msgExcep.indexOf("ORA-01830") != -1) {
	    	    		return sqle.toString()+"\nFormato de fecha no válido."; 
	    	        } else if (msgExcep.indexOf("ORA-01821") != -1) {
	    	    		return sqle.toString()+"\nFormato de fecha no válido."; 
	    	        } else if (msgExcep.indexOf("ORA-01843") != -1) {
	    	    		return sqle.toString()+"\nMes no válido en formato de fecha."; 
	    	        } else if (msgExcep.indexOf("ORA-01847") != -1) {
	    	    		return sqle.toString()+"\nD�a no válido en formato de fecha."; 
	    	        } else if (msgExcep.indexOf("ORA-01858") != -1) {
	    	    		return sqle.toString()+"\nFormato de fecha no válido"; 
	    	        } else if (msgExcep.indexOf("ORA-01841") != -1) {
	    	    		return sqle.toString()+"\nA�o no válido en formato fecha"; 
	    	        } else if (msgExcep.indexOf("ORA-02291") != -1) {
	    	    		return sqle.toString()+"\nClave principal no encontrada. Restricción de integridad violada.";
	    	    	} else if (msgExcep.indexOf("ORA-02292") != -1) {
	    	        	return sqle.toString()+"\nNo se puede eliminar el elemento seleccionado. Tiene subelementos asociados.";
	    	        } else {
	    	    		return sqle.toString();
	    	    	}
	    	  }
}
