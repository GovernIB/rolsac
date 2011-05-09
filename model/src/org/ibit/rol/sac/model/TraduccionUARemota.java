package org.ibit.rol.sac.model;

/* de momento no se emplea */
public class TraduccionUARemota extends TraduccionUA implements TraduccionRemota {

    public String getUrlRemota() {
		return urlRemota;
	}

	public void setUrlRemota(String urlRemota) {
		this.urlRemota = urlRemota;
	}
	
    private String urlRemota;
	
}
