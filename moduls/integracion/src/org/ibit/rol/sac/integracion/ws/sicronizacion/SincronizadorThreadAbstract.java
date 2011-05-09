package org.ibit.rol.sac.integracion.ws.sicronizacion;

import org.ibit.rol.sac.model.AdministracionRemota;

/**
 * Definicion adstracta de un proceso de sincronizacion.
 * @author arodrigo
 *
 */
public abstract class SincronizadorThreadAbstract extends Thread{
	public abstract Exception getException();
	public abstract AdministracionRemota getAdminRemota();
}
