package org.ibit.rol.sac.persistence.ejb;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * SessionBean para mantener y consultar Enlaces.
 *
 * @ejb.bean
 *  name="sac/persistence/EnlaceFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EnlaceFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */

public abstract class EnlaceFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

	/**
     * Obtiene referencia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
	public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    /**
     * Crea o actualiza un Enlace.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param enlace	Indica una entidad de tipo Enlace a guardar.
     * @param idProcedimiento	Identificador del procedimiento. "<code>Este parámetro no se usa</code>"
     * @param idFicha	Identificador de una ficha.
     * @return Devuelve el identificador del enlace guardado.
     */
    public Long grabarEnlace(Enlace enlace, Long idProcedimiento, Long idFicha) {
    	
    	Session session = getSession();
    	
    	try {
    		
    		Ficha ficha = null;
    		
    		if (enlace.getId() == null) {
    			
    			if (idFicha != null) {
    				
    				if (!getAccesoManager().tieneAccesoFicha(idFicha)) {
    					throw new SecurityException("No tiene acceso a la ficha.");
    				}
    				
    				ficha = (Ficha) session.load(Ficha.class, idFicha);
    				ficha.addEnlace(enlace);
    				
    			}
    			
    			session.save(enlace);
    			
    		} else {
    			
    			session.update(enlace);
    			
    		}
    		
    		session.flush();
    		
    		return enlace.getId();
    		
    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}
    	
    }
    
    /**
     * Borra un enlace.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * @param id	Identificador del enlace a borrar.
     */
    public void borrarEnlace(Long id)
    {
    	Session session = getSession();
    	try {
    		Enlace enlace = (Enlace) session.load(Enlace.class, id);
    		if (enlace.getFicha() != null) {
    			enlace.getFicha().removeEnlace(enlace);
    		}
    		session.delete(enlace);
    		session.flush();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    class EnlsFichaComparator implements Comparator
    {
    	public int compare(Object o1, Object o2) {
    		
    		Long x1 = new Long(((Enlace) o1).getOrden());
    		Long x2 = new Long(((Enlace) o2).getOrden());
    		
    		return x1.compareTo(x2);
    	}
    }
    
    
	
	 /**
	 * Consulta los edificios en funcion del filtro generico
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda consultaEnlaces(FiltroGenerico filtro){
	
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		Long id = filtro.getId();
		String lang = filtro.getLang();
		Map <String,String> parametros = new HashMap<String,String>();
				
		String codigoFicha = filtro.getValor(FiltroGenerico.FILTRO_ENLACES_FICHA);
		
		StringBuilder select = new StringBuilder("SELECT e ");
		StringBuilder selectCount = new StringBuilder("SELECT count(e) ");
		StringBuilder from = new StringBuilder(" FROM Enlace as e, e.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");

				
		try {
									
			if(!StringUtils.isEmpty(codigoFicha)) {
				from.append(" , e.ficha as f");
				where.append(" AND f.id = :codigoFicha");
				parametros.put("codigoFicha", codigoFicha);												
			}
			
			if(id!=null && id>0) {
				where.append(" AND e.id = :id");
				parametros.put("id", id.toString());					
			}
			
			
			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
			
	
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}
    
}
