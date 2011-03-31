package org.ibit.rol.sac.persistence.crawler;

import java.io.Serializable;
import java.util.Map;

public class OperacionCrawler implements Serializable {

	private String tipo;
	private Long idObjeto;
	private String tipoObjeto;
	private Map<String, String> urls;
	public OperacionCrawler(){}
	
	public OperacionCrawler(String tipo, Long idObjeto,String tipoObjeto , Map<String, String> urls){
		this.tipo=tipo;
		this.idObjeto=idObjeto;
		this.tipoObjeto=tipoObjeto;
		this.urls=urls;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(Long idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public Map<String, String> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}
	
	
	
}
