package org.ibit.rol.sac.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.ibit.rol.sac.model.Historico;

public class FechaHistoricoDTO implements Serializable {

	private static final long serialVersionUID = -1227937193155485933L;

	private Timestamp fecha;
	private Historico historico;
	
	public FechaHistoricoDTO() {
		super();
	}
	
	public FechaHistoricoDTO(Timestamp fecha, Historico historico) {
		super();
		this.fecha = fecha;
		this.historico = historico;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
}
