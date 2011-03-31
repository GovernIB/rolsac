package org.ibit.rol.sac.integracion.ws.sicronizacion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.integracion.ws.UnidadAdminCENoEncontradaException;
import org.ibit.rol.sac.model.AdministracionRemota;

/**
 * Singleton que controla el proceso de Alta o Baja de una Administración Remota.
 * TODO: Aquesta implementació té el problema de doble-checked locking.
 * @author arodrigo
 *
 */
public class SincronizadorSingleton {
	
	public static enum Estado { Ejecutando, Parado, Error}
	public static enum Tipo { Alta, Baja}
	
	private transient SincronizadorThreadAbstract sinc;
	private transient Exception exception;
	private static Log log = LogFactory.getLog(SincronizadorSingleton.class);
	
	/**
	 * Inicia el proceso de Alta de una Administracion Remota.
	 * 
	 * @param adminRemota
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 * @throws SincronizacionTrabajadoException
	 * @throws UnidadAdminCENoEncontradaException
	 */
	public synchronized void alta(AdministracionRemota adminRemota) throws CapaDeDatosException, ComunicacionException, SincronizacionTrabajadoException, UnidadAdminCENoEncontradaException{
		if(sinc==null){
            log.info("Inicio proceso de alta con la versión del sincronizador: "+adminRemota.getVersion());

            if(adminRemota.getVersion()==null || adminRemota.getVersion()==1){
                sinc = new SincronizadorAltaThread(adminRemota);
            }
            else if(adminRemota.getVersion()==2){
                sinc = new SincronizadorAltaThreadV2(adminRemota);
            }
            else if(adminRemota.getVersion()==3){
                sinc = new SincronizadorAltaThreadV3(adminRemota);
            }
            sinc.start();
		} else {
			throw new SincronizacionTrabajadoException("Ya se esta procesando");
		}
	}
	
	/**
	 * Inicia el proceso de baja de una Administración Remota 
	 * 
	 * @param adminRemota
	 * @throws CapaDeDatosException
	 * @throws SincronizacionTrabajadoException
	 */
	public synchronized void baja(AdministracionRemota adminRemota) throws CapaDeDatosException, SincronizacionTrabajadoException{
		if(sinc==null) {
            sinc = new SincronizadorBajaThread(adminRemota);
            sinc.start();
		} else {
			throw new SincronizacionTrabajadoException("Ya se esta procesando");
		}
	}
	
	/**
	 * Devuelve el {@link Estado}  de el proceso que se esta ejecutando
	 * @return {@link Estado}
	 */
	public static Estado running() {
		if (instance != null) {
			synchronized(instance){
				if(instance.sinc!=null){
					if(!Thread.State.TERMINATED.equals(instance.sinc.getState())){
						return Estado.Ejecutando;
					}else if (instance.sinc.getException()!=null){
						instance.exception = instance.sinc.getException();
						return Estado.Error;
					}else {
						instance.sinc=null;
					}
				}
			}
		}
		return Estado.Parado;
	}
	
	/**
	 * Si hay un proceso en marcha, devuelve si es de Alta o Baja
	 * @return {@link Tipo}
	 */
	public static Tipo getTipo(){
		if (instance != null) {
			if(instance.sinc!=null){
				if (instance.sinc instanceof SincronizadorAltaThread) {
					return Tipo.Alta;
				}
				return Tipo.Baja;
			}
		}
		return null;
	}
	
	/**
	 * Devuelve la Administración Remota sobre la que se esta realizando el proceso.
	 * @return {@link AdministracionRemota}
	 */
	public static AdministracionRemota getAdminRemota() {
		AdministracionRemota resultado = null;
		if (instance != null) {
			if(instance.sinc!=null){
				resultado = instance.sinc.getAdminRemota();
			}
		}
		return resultado;
	}
	
	/**
	 * Si hay alguna excepcion en el proceso la devuelve 
	 * @return {@link Exception}
	 */
	public static Exception getException() {
		return instance.exception;
	}
	
	/**
	 * Limpia los datos de la excepcion ocurrida en el proceso. Su uso es obligatorio para
	 * eliminar el {@link Estado} Error 
	 */
	public static void cleanException(){
		if (instance != null) {
			synchronized(instance){
				if(instance.sinc!=null){
					instance.sinc=null;
					instance.exception=null;
				}
				instance.newEstado();
			}
		}
	}
	
	/**
	 * Detiene un hilo de ejecución a la espera de que el estado del proceso varie
	 * @param timeout
	 * @throws InterruptedException
	 */
	public synchronized void makeMeWaitForEstado(long timeout) throws InterruptedException{
		wait(timeout);
	}
	
	/**
	 * Al llamar a este metodo, todos los hilos parados en el metodo makeMeWaitForEstado volveran
	 * a ponerse en marcha.
	 */
	public synchronized void newEstado(){
		notifyAll();
	}
	
	
	//---------------------------------Singleton--------------------------------------------
	
    private static SincronizadorSingleton instance; 
 
    public static SincronizadorSingleton getInstance(){
       if (instance == null) {
            synchronized(SincronizadorSingleton.class) {
                  if (instance == null) { // to be completely thread safe
                         instance = new SincronizadorSingleton();
                   }   
              }
         }
 
       return instance;
    }

 }
