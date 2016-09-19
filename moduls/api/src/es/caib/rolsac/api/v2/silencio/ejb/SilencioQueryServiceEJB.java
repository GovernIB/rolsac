package es.caib.rolsac.api.v2.silencio.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SilencioAdm;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;

/**
 * SessionBean para consultas de ROLSAC.
 *
 * @ejb.bean
 *  name="sac/api/SilencioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.silencio.ejb.SilencioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class SilencioQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = 1320206905650174683L;
	
	private static Log log = LogFactory.getLog(RolsacQueryServiceEJB.class);
	
//	private static final String HQL_TRADUCCIONES_ALIAS = "trad";
//	private static final String HQL_SILENCIO_CLASS = "SilencioAdm";
//    private static final String HQL_SILENCIO_ALIAS = "sil";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene un tipo de iniciación.
     * @param codSilencio
     * @param idioma
     * @return SilencioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public SilencioDTO obtenirSilenci(String codSilencio, String idioma) {
    	
        SilencioDTO silencioDTO = null;
        Session session = null;
      
        try {
        
            session = getSession();
            StringBuilder consulta = new StringBuilder("select sil ");
			consulta.append("from SilencioAdm as sil, sil.traducciones as trad ");
			consulta.append("where index(trad) = :idioma ");
			consulta.append(" and sil.id = :codSilencio");
			consulta.append(" order by trad.nombre asc");
			
            Query query = session.createQuery(consulta.toString());
            query.setParameter("idioma", idioma);
            query.setParameter("codSilencio", codSilencio);
    		
            
            SilencioAdm silencio = (SilencioAdm)query.uniqueResult();
            
            if (silencio != null) {
            	
				silencioDTO = (SilencioDTO)BasicUtils.entityToDTO(
						SilencioDTO.class, 
						silencio,
						"ca"//silencioCriteria.getIdioma()
				);
            	
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } /*catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } */ finally {
        	
            close(session);
            
        }

        return silencioDTO;
        
    }

}
