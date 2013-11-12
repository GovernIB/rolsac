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
 * @deprecated No se usa
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
public abstract class NormativaRemotaFacadeEJB extends HibernateEJB
{
	/**
     * Obtiene refer�ncia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
	protected abstract AccesoManagerLocal getAccesoManager();
	
	
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException
    {
        super.ejbCreate();
    }
    
    
    /**
     * Obtiene las Normativas de una procedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Normativa> obtenerNormativasProcedimiento(Long idProcedimiento)
    {
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
