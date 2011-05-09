package es.caib.vuds;

import es.map.vuds.si.service.webservice.Tramite;

public class TramiteValidado {
	Tramite tramite;
	String[] sinTraducir;
	
	
	public Tramite getTramite() {
		return tramite;
	}
	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}
	public String[] getSinTraducir() {
		return sinTraducir;
	}
	public void setSinTraducir(String[] sinTraducir) {
		this.sinTraducir = sinTraducir;
	}
}
