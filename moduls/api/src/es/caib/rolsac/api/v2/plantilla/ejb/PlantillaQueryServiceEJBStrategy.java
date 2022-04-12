package es.caib.rolsac.api.v2.plantilla.ejb;

import es.caib.rolsac.api.v2.plantilla.PlantillaQueryServiceStrategy;

public class PlantillaQueryServiceEJBStrategy implements PlantillaQueryServiceStrategy {

	private PlantillaQueryServiceDelegate plantillaQueryServiceDelegate;

	public void setPlantillaQueryServiceDelegate(final PlantillaQueryServiceDelegate plantillaQueryServiceDelegate) {
		this.plantillaQueryServiceDelegate = plantillaQueryServiceDelegate;
	}

	@Override
	public void setUrl(final String url) {
		// No es necesario en EJB
	}

}
