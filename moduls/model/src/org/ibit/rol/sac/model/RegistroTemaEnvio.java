/* Generated by Together */

package org.ibit.rol.sac.model;


public class RegistroTemaEnvio implements ValueObject {
	
	public static final Integer ACTIVO = new Integer(1);
	public static final Integer NO_ACTIVO = new Integer(0);
	
	private Long idMateria;
	private String html;
	private Integer activo;
	
	private HistoricoEnvio envio;
	
	
	public HistoricoEnvio getEnvio() {
		return envio;
	}
	public void setEnvio(HistoricoEnvio envio) {
		this.envio = envio;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}



}
