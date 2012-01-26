package org.ibit.rol.sac.model;

public class HechoVitalProcedimiento implements ValueObject, Comparable<HechoVitalProcedimiento> {

	private static final long serialVersionUID = -8443729848093068327L;

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HechoVital getHechoVital() {
        return hechoVital;
    }

    public void setHechoVital(HechoVital hechoVital) {
        this.hechoVital = hechoVital;
    }

    public ProcedimientoLocal getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoLocal procedimiento) {
        this.procedimiento = procedimiento;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    

	public int compareTo(HechoVitalProcedimiento hvp) {
    	if (hvp == null || this.orden > hvp.getOrden()){
    		return 1;
    	} else if (this.orden < hvp.getOrden()) {
    		return -1;
		} else {
    		return 0;
    	}
	}


    private Long id;
    private HechoVital hechoVital = null;
    private ProcedimientoLocal procedimiento = null;
    private int orden;    
	
}
