package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Clase que representa la información a transferir de la relacion HechoVitalProcedimiento(PORMAD)
 */
public class HechoVitalProcedimientoTransferible implements Serializable{


	
    private String codigoEstandarHechoVital = null;
    private Long idProcedimiento = null;
    private Integer orden;
    
	public Long getIdProcedimiento() {
		return idProcedimiento;
	}
	public void setIdProcedimiento(Long idProcedimiento) {
		this.idProcedimiento = idProcedimiento;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getCodigoEstandarHechoVital() {
		return codigoEstandarHechoVital;
	}
	public void setCodigoEstandarHechoVital(String codigoEstandarHechoVital) {
		this.codigoEstandarHechoVital = codigoEstandarHechoVital;
	}
}
