package org.ibit.rol.sac.model.ws;

/**
 * Campos basicos de una bean Transferible que contirne una traduccion
 * @author arodrigo
 *
 */
public abstract class AbstractTraduccion {
	private String codigoEstandarIdioma;
    /*private String urlRemota;*/
    
	public String getCodigoEstandarIdioma() {
		return codigoEstandarIdioma;
	}
	public void setCodigoEstandarIdioma(String codigoEstandarIdioma) {
		this.codigoEstandarIdioma = codigoEstandarIdioma;
	}
/*	public String getUrlRemota() {
		return urlRemota;
	}
	public void setUrlRemota(String urlRemota) {
		this.urlRemota = urlRemota;
	}*/
    
}
