package org.ibit.rol.sac.model;

/**
 * Comentario de un procedimiento.
 *
 * @author areus
 */
public class ComentarioProcedimiento extends Comentario {

    /*
     * Lo normal seria que aquest camp fes referència a un Procedimiento, en lloc de a un
     * ProcedimientoLocal. Però el problema és que Procedimiento no està mapejat com a tal, sinó
     * que només està mapejat ProcedimientoLocal, i ProcedimientoRemoto com a subclasse de
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