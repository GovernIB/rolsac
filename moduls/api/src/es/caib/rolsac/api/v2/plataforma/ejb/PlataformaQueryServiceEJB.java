package es.caib.rolsac.api.v2.plataforma.ejb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.plataforma.PlataformaQueryService;

/**
 * SessionBean para consultas de plataformaes.
 *
 * @ejb.bean name="sac/api/PlataformaQueryServiceEJB"
 *           jndi-name="es.caib.rolsac.api.v2.plataforma.ejb.PlataformaQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class PlataformaQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = -7461793014109413857L;

	private static Log log = LogFactory.getLog(PlataformaQueryService.class);

	private static final String HQL_PLATAFORMA_CLASS = "Plataforma";
	private static final String HQL_PLATAFORMA_ALIAS = "plat";

}
