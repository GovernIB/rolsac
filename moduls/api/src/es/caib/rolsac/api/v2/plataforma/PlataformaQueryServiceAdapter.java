package es.caib.rolsac.api.v2.plataforma;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.plataforma.ejb.PlataformaQueryServiceEJBStrategy;

public class PlataformaQueryServiceAdapter extends PlataformaDTO implements PlataformaQueryService {

	private static final long serialVersionUID = -7266758574463322225L;

	private PlataformaQueryServiceStrategy plataformaQueryServiceStrategy;

	public void setPlataformaQueryServiceStrategy(final PlataformaQueryServiceStrategy plataformaQueryServiceStrategy) {
		this.plataformaQueryServiceStrategy = plataformaQueryServiceStrategy;
	}

	private String rolsacUrl;

	public void setRolsacUrl(final String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (this.plataformaQueryServiceStrategy != null) {
			this.plataformaQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

	public PlataformaQueryServiceAdapter(final PlataformaDTO dto) throws QueryServiceException {
		try {
			PropertyUtils.copyProperties(this, dto);
		} catch (final Exception e) {
			throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
		}
	}

	private STRATEGY getStrategy() {
		return plataformaQueryServiceStrategy instanceof PlataformaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
	}

}
