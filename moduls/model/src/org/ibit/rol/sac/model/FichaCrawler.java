package org.ibit.rol.sac.model;

import org.ibit.rol.sac.model.Ficha;

/**
 * Ficha que se compone del documento retornado de la búsqueda de lucene del crawler
 *
 */
public class FichaCrawler {

	private static final long serialVersionUID = 1L;

	private Ficha ficha;
	private String URL;
    private String tituloURL;
    
	public Ficha getFicha() {
		return ficha;
	}
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String url) {
		URL = url;
	}
	public String getTituloURL() {
		return tituloURL;
	}
	public void setTituloURL(String tituloURL) {
		this.tituloURL = tituloURL;
	}



}
