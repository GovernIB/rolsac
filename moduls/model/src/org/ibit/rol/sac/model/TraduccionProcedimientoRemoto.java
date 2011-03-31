package org.ibit.rol.sac.model;

/* de momento no se emplea */
public class TraduccionProcedimientoRemoto extends TraduccionProcedimientoLocal implements TraduccionRemota {

    private String urlRemota;
    
    public String getUrlRemota() {
		return urlRemota;
	}

	public void setUrlRemota(String urlRemota) {
		this.urlRemota = urlRemota;
	}

}