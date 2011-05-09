package org.ibit.rol.sac.integracion.ws;

import org.ibit.rol.sac.model.ProcedimientoRemotoAntiguo;
import org.ibit.rol.sac.model.Tramite;

/**
 * Interfaz del servicio de consulta de procedimientos.
 */
public interface ProcedimientoService {

    public static Class[] types = new Class[]{ ProcedimientoRemotoAntiguo.class };

    public ProcedimientoRemotoAntiguo consultarProcedimento(Long id);

    public ProcedimientoRemotoAntiguo[] obtenerProcedimentosHechoVital(String nombre);

    public ProcedimientoRemotoAntiguo[] buscarProcedimientos(String text);

}
