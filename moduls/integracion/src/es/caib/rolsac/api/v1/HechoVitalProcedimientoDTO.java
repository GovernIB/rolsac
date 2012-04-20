package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.TraduccionHechoVital;

public class HechoVitalProcedimientoDTO implements Serializable, Comparable<HechoVitalProcedimientoDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoEstandarHechoVital;
    private Long idProcedimiento;
    private Integer orden;

    public String getCodigoEstandarHechoVital() {
		return codigoEstandarHechoVital;
	}
	public void setCodigoEstandarHechoVital(String codigoEstandarHechoVital) {
		this.codigoEstandarHechoVital = codigoEstandarHechoVital;
	}
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

    public HechoVitalProcedimientoDTO( HechoVitalProcedimiento hechoVitalProcedimiento )
    {
    	this.codigoEstandarHechoVital = hechoVitalProcedimiento.getHechoVital().getCodigoEstandar();
    	this.idProcedimiento = hechoVitalProcedimiento.getProcedimiento().getId();
    	this.orden = hechoVitalProcedimiento.getOrden();
    }
	public int compareTo(HechoVitalProcedimientoDTO o) {
		return this.codigoEstandarHechoVital.compareToIgnoreCase( o.getCodigoEstandarHechoVital() );
	}
}
