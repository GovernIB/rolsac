package org.ibit.rol.sac.persistence.ws.sia;

import java.net.URL;

import org.ibit.rol.sac.persistence.ws.sia.actualizar.WsSIAActualizarActuaciones_PortType;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.WsSIAActualizarActuaciones_ServiceLocator;
import org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_PortType;
import org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_ServiceLocator;

/**
 * SiaClient.
 * 
 * @author Indra
 * 
 */
public class SiaClient {

    /**
     * Devuelve cliente SIA.
     * 
     * @param url
     *            Url
     * @return cliente
     * @throws Exception
     */
    public static WsSIAActualizarActuaciones_PortType createClient(final String url)
            throws Exception {
        final WsSIAActualizarActuaciones_ServiceLocator sl = new WsSIAActualizarActuaciones_ServiceLocator();
        final URL portAddress = new URL(url);
        WsSIAActualizarActuaciones_PortType port = sl.getwsSIAActualizarActuacionesSOAP(portAddress);
        return port;
    }

    
    /**
     * Devuelve cliente info SIA.
     * 
     * @param url
     *            Url
     * @return cliente
     * @throws Exception
     */
    public static WsSIAConsultarActuacionesIdentificacion_PortType createInfoClient(final String url)
            throws Exception {
        final WsSIAConsultarActuacionesIdentificacion_ServiceLocator sl = new WsSIAConsultarActuacionesIdentificacion_ServiceLocator();
        final URL portAddress = new URL(url);
        final WsSIAConsultarActuacionesIdentificacion_PortType port = sl.getwsSIAConsultarActuacionesIdentificacionSOAP(portAddress);
        return port;
    }

}
