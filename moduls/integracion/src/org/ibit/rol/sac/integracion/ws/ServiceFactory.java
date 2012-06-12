package org.ibit.rol.sac.integracion.ws;

import net.sf.cglib.proxy.Enhancer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v1.RolsacQueryService;

/**
 * Factoria de servicios.
 */
public class ServiceFactory {

    protected static Log log = LogFactory.getLog(ServiceFactory.class);

    public static ProcedimientoService getProcedimientoService(String endPoint) {
        log.debug("Creando ProcedimientoService para " + endPoint);

        AxisInterceptor interceptor = new AxisInterceptor(endPoint, ProcedimientoService.types);
        ProcedimientoService client = (ProcedimientoService) Enhancer.create(ProcedimientoService.class, interceptor);

        return client;
    }
    

    public static RolsacQueryService getExportService(String endPoint) {
        log.info("Creando RolsacQueryService para " + endPoint);

        AxisInterceptor interceptor = new AxisInterceptor(endPoint, RolsacQueryService.types);
        RolsacQueryService client = (RolsacQueryService) Enhancer.create(RolsacQueryService.class, interceptor);

        return client;
    }
    
}
