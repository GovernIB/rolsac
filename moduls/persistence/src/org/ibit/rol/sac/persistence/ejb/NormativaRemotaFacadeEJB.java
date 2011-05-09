package org.ibit.rol.sac.persistence.ejb;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaRemota;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.TramiteRemoto;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.RemotoUtils;

import javax.ejb.CreateException;
import javax.ejb.EJBException;


/**
 * SessionBean para mantener y consultar Normativa Remota.
 *
 * @ejb.bean
 *  name="sac/persistence/NormativaRemotaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.NormativaRemotaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class NormativaRemotaFacadeEJB extends HibernateEJB {

    /**
     * Obtiene referéncia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
	/**
	 * Crea una Normativa Remota
	 * @throws HibernateException 
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public Long grabarNormativaRemota(final NormativaRemota normativaRemota) {
    	 Session session = getSession();
	        try {     	
	        	if( normativaRemota!=null){
	        		if (normativaRemota.getId() != null) {
			            session.update(normativaRemota);
		            }
		            else{
			            session.save(normativaRemota);
		            }
		            session.flush();
	        	}
	            return normativaRemota.getId();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
    }
    
    
    /**
     * Obtiene un Normativa Remota apartir de su id externo y su id de la UARemota
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public NormativaRemota obtenerNormativaRemota(Long idExterno,Long idUaRemota) {
        Session session = getSession();
        try {

			Query query = session.createQuery("from NormativaRemota as nr where nr.idExterno="+idExterno+" and nr.administracionRemota.id="+idUaRemota);
			NormativaRemota normativa = (NormativaRemota)query.uniqueResult();

			if (normativa != null) {
				Hibernate.initialize(normativa.getProcedimientos());
			}

			return normativa;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    

    
    /**
     * Obtiene las Normativas de una procedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Normativa> obtenerNormativasProcedimiento(Long idProcedimiento) {
        Session session = getSession();
        try {
			Query query = session.createQuery("select elements(proc.normativas) from ProcedimientoLocal as proc where proc.id="+idProcedimiento);

			return (List<Normativa>)query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
 
    
	
	
}
