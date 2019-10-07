package es.caib.rolsac.api.v2.enllactelematico;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.enllac.ejb.EnllacQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;

public class EnllacTelematicoQueryServiceAdapter extends EnllacTelematicoDTO implements EnllacTelematicoQueryService {

	private static final long serialVersionUID = 6411086726548025434L;

	private EnllacTelematicoQueryServiceStrategy enllacQueryServiceStrategy;

	public void setEnllacTelematicoQueryServiceStrategy(
			final EnllacTelematicoQueryServiceStrategy enllacQueryServiceStrategy) {
		this.enllacQueryServiceStrategy = enllacQueryServiceStrategy;
	}

	private STRATEGY getStrategy() {
		return enllacQueryServiceStrategy instanceof EnllacQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
	}

	private String rolsacUrl;

	public void setRolsacUrl(final String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (this.enllacQueryServiceStrategy != null) {
			this.enllacQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

	public EnllacTelematicoQueryServiceAdapter(final EnllacTelematicoDTO dto) throws QueryServiceException {
		try {
			PropertyUtils.copyProperties(this, dto);
		} catch (final Exception e) {
			throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
		}
	}

	/**
	 * Obtiene el enlace telematico.
	 *
	 * @return
	 */
	public String getEnlaceTelematico() {
		return this.getEnlace();
	}
}
