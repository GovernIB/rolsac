package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.ws.SiaResultado;


/**
 * Interfaz SIA Delegate.
 * @author slromero
 *
 */
public interface SiaDelegateI {

	public void enviarTodos(SiaJob siaJob) throws DelegateException;

	public void enviarPendientes(SiaJob siaJob) throws DelegateException;

	public void info(SiaJob siaJob) throws DelegateException;

	public void revisarProcedimientosPorTiempo(SiaJob siaJob) throws DelegateException;

	/**
	 * Envia el procedimiento directamente hacia SIA.
	 * @param proc
	 * @return
	 * @throws Exception
	 */
	public SiaResultado enviarProcedimientoNoActivo(ProcedimientoLocal proc) throws DelegateException;
		
	/**
	 * Envia el servicio directamente hacia SIA.
	 * @param servicio
	 * @return
	 * @throws DelegateException
	 */
	public SiaResultado enviarServicioNoActivo(Servicio servicio) throws DelegateException;

}
