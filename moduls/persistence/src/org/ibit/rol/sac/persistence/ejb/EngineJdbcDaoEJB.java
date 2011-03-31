package org.ibit.rol.sac.persistence.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * SessionBean para consultar a traves de JDBC
 *
 * @ejb.bean
 *  name="sac/persistence/EngineJdbcDaoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EngineJdbcDaoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class EngineJdbcDaoEJB extends DaoEJB {

	protected static Log log = LogFactory.getLog(EngineJdbcDaoEJB.class);
	
	protected String _name="";
	
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Inicializa el EJB con un datasource determinado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void init(String jndi) {
        try {
        	_name=jndi;
        	//super.init(jndi);
        	//dbConnection = getDBConnection();
        } catch (Exception he) {
            throw new EJBException(he);
        }
    }
	
    
    /**
     * Método que devuelve un resultset tras realizar una operación de consulta
     * o de modificación en BD
     * 
     * Si no es una consulta retorna null.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ResultSet ejecuta(String sql) throws EJBException {
      
    	long milisegundos=System.currentTimeMillis();
    	ResultSet rs=null;
    	java.sql.Statement st = null;
    	try {
    		super.init(_name);
        	dbConnection = getDBConnection();
    		st = dbConnection.createStatement();
    		if (dbConnection!=null) {
    			if (sql.toUpperCase().substring(0,6).equals("SELECT")) {
    				rs= st.executeQuery(sql);
    			}
    			else {
    				int i=st.executeUpdate(sql);
    				return null;
    			}
    		}
    		close(st);
    		return rs;
    	} catch (SQLException sqle) {
    		throw new EJBException(errorOracle(sqle) + ">>>" + sqle);
    	} finally {
        	long milisegundos2=System.currentTimeMillis();
        	if ((milisegundos2-milisegundos)>_timewarning) log.warn("Sentencia lenta ["+ (milisegundos2-milisegundos) + "]: "+ sql);
    	}



    }


  	
  	/**
  	 * Método para ejecutar SQL de inserción, actualización o borrado.  
     * Devuelve el número de registros modificados o 0 en caso de error.
  	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
  	 */
  	public int ejecuta(String sql, String[] datos) throws EJBException {
      PreparedStatement pStmt = null;
      int nRegsAct = 0;

      try {
	  		super.init(_name);
	    	dbConnection = getDBConnection();

            if (dbConnection != null) {
  
                  pStmt = dbConnection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    //se cargan los datos en el Statement
                  if (datos != null) {
                      //cargamos los datos para la consulta
                        for (int i = 0; i < datos.length; i++) {
                            pStmt.setString(i+1, datos[i]);
                        }
                  }
                  nRegsAct = pStmt.executeUpdate();
            }
            close(pStmt);
            close(dbConnection);
            return nRegsAct;          
        } catch (SQLException sqle) {
        	throw new EJBException(errorOracle(sqle) + ">>>" + sqle);
        }
    }

  	/**
  	 * Método para ejecutar SQL de inserción, actualización o borrado.  
     * Devuelve el número de registros modificados o 0 en caso de error.
  	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
  	 */
  	public String ejecutaCount(String sql) throws EJBException {
        
    	long milisegundos=System.currentTimeMillis();
    	ResultSet rs=null;
    	java.sql.Statement st = null;
    	String devolver="-1";
    	try {
    		super.init(_name);
	    	dbConnection = getDBConnection();
    		st = dbConnection.createStatement();
    		if (dbConnection!=null) {
    			if (sql.toUpperCase().substring(0,6).equals("SELECT")) {
    				rs= st.executeQuery(sql);
    				if (rs.next())
    					devolver=rs.getString(1);
    			}
    		}
    		close(st);
    		close(dbConnection);
    		return devolver;
    	} catch (SQLException sqle) {
    		throw new EJBException(errorOracle(sqle) + ">>>" + sqle);
    	} finally {
        	long milisegundos2=System.currentTimeMillis();
        	if ((milisegundos2-milisegundos)>_timewarning) log.warn("Sentencia lenta ["+ (milisegundos2-milisegundos) + "]: "+ sql);
    	}

    }
    
}
