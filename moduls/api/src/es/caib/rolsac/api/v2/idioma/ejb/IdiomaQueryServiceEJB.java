package es.caib.rolsac.api.v2.idioma.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Idioma;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

/**
 * SessionBean para consultas de familia.
 *
 * @ejb.bean
 *  name="sac/api/IdiomaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.idioma.ejb.IdiomaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class IdiomaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 1557458996303347630L;
    
    private static Log log = LogFactory.getLog(IdiomaQueryServiceEJB.class);

    private static final String HQL_IDIOMA_CLASS = "Idioma";
    private static final String HQL_IDIOMA_ALIAS = "idi";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
	 * Obtiene idiomas.
	 * @param idiomaCriteria
	 * @return List<IdiomaDTO>
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) {
		
		List<CriteriaObject> criteris;
		List<IdiomaDTO> idiomaDTOList = new ArrayList<IdiomaDTO>();
		Session session = null;

		try {
			
			// TODO amartin quitar debug
			System.out.println("Funciona!");
			
			criteris = BasicUtils.parseCriterias(
				IdiomaCriteria.class,
				HQL_IDIOMA_ALIAS,
				idiomaCriteria
			);

			List<FromClause> entities = new ArrayList<FromClause>();
			entities.add(new FromClause(HQL_IDIOMA_CLASS, HQL_IDIOMA_ALIAS));

			QueryBuilder qb = new QueryBuilder(
				HQL_IDIOMA_ALIAS, 
				entities, 
				"",
				""
			);
			qb.extendCriteriaObjects(criteris);

			session = getSession();
			Query query = qb.createQuery(session);
			List<Idioma> idiomaResult = (List<Idioma>) query.list();
			for (Idioma f: idiomaResult) {
				idiomaDTOList.add((IdiomaDTO) BasicUtils.entityToDTO(IdiomaDTO.class, f, idiomaCriteria.getIdioma()));
			}
			
		} catch (HibernateException e) {
			
			log.error(e);
			throw new EJBException(e);
			
		} catch (CriteriaObjectParseException e) {
			
			log.error(e);
			throw new EJBException(e);
			
		} catch (QueryBuilderException e) {
			
			log.error(e);
			throw new EJBException(e);
			
		} finally {
			
			close(session);
			
		}

		return idiomaDTOList;
		
	}

}
