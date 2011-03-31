/**
 *
 */
package org.ibit.rol.sac.model;

public class HechoVitalProcedimiento implements ValueObject{

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

    private Long id;
    private HechoVital hechoVital = null;
    private ProcedimientoLocal procedimiento = null;
    private int orden;
}
