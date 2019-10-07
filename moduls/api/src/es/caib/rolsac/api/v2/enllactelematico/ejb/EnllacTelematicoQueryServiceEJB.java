package es.caib.rolsac.api.v2.enllactelematico.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.general.HibernateEJB;

/**
 * SessionBean para consultas de boletines.
 *
 * @ejb.bean name="sac/api/EnllacTelematicoQueryServiceEJB"
 *           jndi-name="es.caib.rolsac.api.v2.enllac.ejb.EnllacQueryServiceEJB"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class EnllacTelematicoQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = 5728626419374660173L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

}
