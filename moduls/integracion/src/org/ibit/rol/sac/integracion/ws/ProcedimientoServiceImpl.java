package org.ibit.rol.sac.integracion.ws;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemotoAntiguo;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;

/**
 * Implementación del webservice.
 */
public final class ProcedimientoServiceImpl implements ProcedimientoService {

    protected static Log log = LogFactory.getLog(ProcedimientoServiceImpl.class);

    public ProcedimientoRemotoAntiguo consultarProcedimento(Long id) {

        try {
            ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
            return procDelegate.consultarProcedimiento(id).crearRemoto();
        } catch (DelegateException e) {
            log.error("consultarProcedimiento(" + id + ")", e);
            return null;
        }
    }

    public ProcedimientoRemotoAntiguo[] obtenerProcedimentosHechoVital(String nombre) {
        try {
            HechoVitalDelegate hechoDelegate = DelegateUtil.getHechoVitalDelegate();
            ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();

            HechoVital hechoVital = hechoDelegate.obtenerHechoVitalPorNombre(nombre);
            if (hechoVital == null) {
                return new ProcedimientoRemotoAntiguo[0];
            }

            List procedimientos = procDelegate.listarProcedimientosPublicosHechoVital(hechoVital.getId());
            ProcedimientoRemotoAntiguo[] result = new ProcedimientoRemotoAntiguo[procedimientos.size()];
            for (int i = 0; i < procedimientos.size(); i++) {
                result[i] = ((ProcedimientoLocal) procedimientos.get(i)).crearRemoto();
            }
            return result;
        } catch (DelegateException e) {
            log.error("obtenerProcedimentosHechoVital(" + nombre + ")", e);
            return null;
        }
    }

    public ProcedimientoRemotoAntiguo[] buscarProcedimientos(String text) {
        try {
            ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
            List procedimientos = procDelegate.buscarProcedimientosTexto(text);
            ProcedimientoRemotoAntiguo[] result = new ProcedimientoRemotoAntiguo[procedimientos.size()];
            for (int i = 0; i < procedimientos.size(); i++) {
                result[i] = ((ProcedimientoLocal) procedimientos.get(i)).crearRemoto();
            }
            return result;
        } catch (DelegateException e) {
            log.error("buscarProcedimientos(" + text + ")", e);
            return null;
        }
    }
}
