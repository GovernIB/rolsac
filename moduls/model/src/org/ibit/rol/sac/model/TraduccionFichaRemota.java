package org.ibit.rol.sac.model;

/* de momento no se emplea*/
public class TraduccionFichaRemota extends TraduccionFicha implements TraduccionRemota {
    
    public String getUrlRemota() {
		return urlRemota;
	}

	public void setUrlRemota(String urlRemota) {
		this.urlRemota = urlRemota;
	}
    private String urlRemota;
}
