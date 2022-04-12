package es.caib.rolsac.api.v2.plantilla;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.plantilla.ejb.PlantillaQueryServiceEJBStrategy;

public class PlantillaQueryServiceAdapter extends PlantillaDTO implements PlantillaQueryService {

	private static final long serialVersionUID = -7266758574463322225L;

	private PlantillaQueryServiceStrategy plantillaQueryServiceStrategy;

	public void setPlantillaQueryServiceStrategy(final PlantillaQueryServiceStrategy plantillaQueryServiceStrategy) {
		this.plantillaQueryServiceStrategy = plantillaQueryServiceStrategy;
	}

	private String rolsacUrl;

	public void setRolsacUrl(final String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (this.plantillaQueryServiceStrategy != null) {
			this.plantillaQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

	public PlantillaQueryServiceAdapter(final PlantillaDTO dto) throws QueryServiceException {
		try {
			PropertyUtils.copyProperties(this, dto);
		} catch (final Exception e) {
			throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
		}
	}

	private STRATEGY getStrategy() {
		return plantillaQueryServiceStrategy instanceof PlantillaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
	}

}
