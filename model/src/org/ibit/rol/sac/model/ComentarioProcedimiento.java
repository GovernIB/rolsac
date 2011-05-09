package org.ibit.rol.sac.model;

/**
 * Comentario de un procedimiento.
 *
 * @author areus
 */
public class ComentarioProcedimiento extends Comentario {

    /*
     * Lo normal seria que aquest camp fes refer�ncia a un Procedimiento, en lloc de a un
     * ProcedimientoLocal. Per� el problema �s que Procedimiento no est� mapejat com a tal, sin�
     * que nom�s est� mapejat ProcedimientoLocal, i ProcedimientoRemoto com a subclasse de
     * ProcedimientoLocal
     */
    private ProcedimientoLocal procedimiento;

    public ProcedimientoLocal getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoLocal procedimiento) {
        this.procedimiento = procedimiento;
    }
}