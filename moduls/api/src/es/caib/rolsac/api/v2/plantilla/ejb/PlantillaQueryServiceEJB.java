package es.caib.rolsac.api.v2.plantilla.ejb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.plantilla.PlantillaQueryService;

/**
 * SessionBean para consultas de plantillaes.
 *
 * @ejb.bean name="sac/api/PlantillaQueryServiceEJB"
 *           jndi-name="es.caib.rolsac.api.v2.plantilla.ejb.PlantillaQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class PlantillaQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = -7461793014109413857L;

	private static Log log = LogFactory.getLog(PlantillaQueryService.class);

	private static final String HQL_PLATAFORMA_CLASS = "Plantilla";
	private static final String HQL_PLATAFORMA_ALIAS = "plat";

}
