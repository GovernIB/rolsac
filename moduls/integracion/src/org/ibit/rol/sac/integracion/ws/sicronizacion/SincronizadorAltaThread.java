package org.ibit.rol.sac.integracion.ws.sicronizacion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ibit.rol.sac.integracion.ws.UnidadAdminCENoEncontradaException;
import org.ibit.rol.sac.integracion.ws.invoker.SincronizacionServicio;
import org.ibit.rol.sac.integracion.ws.invoker.WSInvocatorException;

import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaExternaRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoRemotoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteRemotoDelegate;
import org.ibit.rol.sac.persistence.delegate.UARemotaDelegate;

/**
 * Realiza el proceso de alta de la informacion de una {@link AdministracionRemota}.
 * 
 * @author arodrigo
 *
 */
public class SincronizadorAltaThread extends SincronizadorThreadAbstract{
	
	protected static Log log = LogFactory.getLog(SincronizadorAltaThread.class);
	
	//AdminstracionRemota con la que estamos trabajando
	protected final transient AdministracionRemota adminRemota;
	
	//Cliente WebService con las funciones de sincronizacion
	protected final transient SincronizacionServicio sincInvoker;
	
	//Delegate's
	protected final transient  ProcedimientoRemotoDelegate procRemotoDelegate;
	protected final transient  UARemotaDelegate uaRemotaDelegate;
	protected final transient  FichaRemotaDelegate fichaRemotaDelegate;
	protected final transient  TramiteRemotoDelegate tramiteRemotoDelegate;
	protected final transient NormativaExternaRemotaDelegate normativaExternaRemotaDelegate;

	//Array con los codigos estandar de las HechosVitales de nuestra DB
	protected final transient String[] hechosCE ;
	//Array con los codigos estandar de las materias de nuestra DB
	protected final transient String[] materiasCE;
	
	//Nivel de profundidad en el que se esta trabajando
	protected transient int profundidad=0; 
	
	//Excepcion
	protected Exception exception;
	
	/**
	 * Almacena los datos de la {@link AdministracionRemota} y prepara los Delegates que se
	 * van a usar durante el Proceso. Tambien Genera 2 Array con los codigos estandar de 
	 * {@link Materia} y {@link HechoVital}.
	 * 
	 * @param adminRemota
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	@SuppressWarnings("unchecked")
	public SincronizadorAltaThread(final AdministracionRemota adminRemota) throws CapaDeDatosException, ComunicacionException {
		this.adminRemota=adminRemota;
		try {
			sincInvoker = new SincronizacionServicio(
					adminRemota.getEndpoint());
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Error al construir el conector",e);
		}
		
		//Recoge los Delegate's
		procRemotoDelegate =  DelegateUtil.getProcedimientoRemotoDelegate();
		uaRemotaDelegate = DelegateUtil.getUARemotaDelegate();
		fichaRemotaDelegate = DelegateUtil.getFichaRemotaDelegate();
		tramiteRemotoDelegate = DelegateUtil.getTramiteRemotoDelegate();
		normativaExternaRemotaDelegate = DelegateUtil.getNormativaExternaRemotaDelegate();
		
		try {
			//Genero un Array con los CE de las materia de la BD
			final List<String> materiasTemp = new ArrayList<String>();
			for( final Materia materia : (List<Materia>)DelegateUtil.getMateriaDelegate().listarMaterias() ){
				if(materia.getCodigoEstandar()!=null  && !"".equals(materia.getCodigoEstandar().trim())){
					materiasTemp.add(materia.getCodigoEstandar());
				}
			}
			materiasCE = materiasTemp.toArray(new String[0]);
	
			//Genero un Array con los CE de los HechosVitales de la BD
			final List<String> hechosTemp = new ArrayList<String>();
			for( final HechoVital hecho : (List<HechoVital>)DelegateUtil.getHechoVitalDelegate().listarHechosVitales() ){
				if(hecho.getCodigoEstandar()!=null  && !"".equals(hecho.getCodigoEstandar().trim())){
					hechosTemp.add(hecho.getCodigoEstandar());
				}
			}
			hechosCE = hechosTemp.toArray(new String[0]);
		}catch (DelegateException e) {
			throw new CapaDeDatosException("Fallo al construir los arrays de materias y hechosvitales",e);
		}
		
		
		log.info("Constructor completado");
	}
	
	//------------------------Funciones Publicas-----------------------------------

	@Override
	public void run() {
		SincronizadorSingleton.getInstance().newEstado();
		try {
			alta();
			SincronizadorSingleton.getInstance().newEstado();
		} catch (Exception e) {
			log.error("Error en el proceso de alta",e);
			exception=e;
			SincronizadorSingleton.getInstance().newEstado();
		}
	}
	
	/**
	 * Recoge toda la informacion de la {@link AdministracionRemota}. Comienza desde la
	 * {@link UnidadAdministrativa} cuyo codigo estandar esta señalado en la {@link AdministracionRemota}
	 * y a la profindidad señalada.
	 * 
	 * Todas las {@link Ficha} y {@link ProcedimientoLocal} relacionados con las {@link UnidadAdministrativa}
	 * tambien son recogidos.
	 */
	protected void alta() throws UnidadAdminCENoEncontradaException, ComunicacionException, CapaDeDatosException{
		log.info("Comienza la transferencia");
		UnidadAdministrativaTransferible uaTrans;
		try {
			log.info("Recogiendo la UARaiz con CE: "+adminRemota.getCodigoEstandarUA());
			//Recojo la UARaiz
			uaTrans = sincInvoker.recogerUnidadAdministrativaByCodigoEstandar(adminRemota.getCodigoEstandarUA());
			if(uaTrans!=null){
				log.info("La UARaiz ha sido recogida con exito");
				//Si existe comienzo el proceso de adquisicion de datos
				recogerUnidadesAdministrativas(uaTrans,null);
			}else{
				log.error("Fallo al recoger la UARaiz!!!!!!!!!");
				//Si no termina el proceso con un error
				throw new UnidadAdminCENoEncontradaException("No existe ninguna administracion con ese codigo estandar");
			}
		} catch (WSInvocatorException e) {
			throw new ComunicacionException(e);
		}
		log.info("Transferencia finalizada con exito");
	}
	
	/**
	 * Si ha ocurrido una {@link Exception} durante el proceso la devuelve. En caso
	 * contrario devuelve null. 
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Devuelve la {@link AdministracionRemota} sobre la que se esta realizando el Proceso.
	 */
	public AdministracionRemota getAdminRemota() {
		return adminRemota;
	}
	
	//------------------------Funciones Privadas de Recogida-----------------------------------
	
	/**
	 * Funcion recursiva que recorre todo el arbol de las {@link UnidadAdministrativa} hasta la
	 * profundidad definida.
	 * 
	 * Durante el proceso almacena tanto las {@link UnidadAdministrativa} como sus {@link Ficha}
	 * y {@link ProcedimientoLocal} relacionados.
	 * 
	 */
	protected void recogerUnidadesAdministrativas(final UnidadAdministrativaTransferible uaTrans, UnidadAdministrativaRemota padre) throws CapaDeDatosException, ComunicacionException {
		profundidad++;
		try{
			log.info("rellenando la UARemota a partir de la transferible");
			final UnidadAdministrativaRemota uaRemota = UnidadAdministrativaRemota.generar(uaTrans);
			uaRemota.setAdministracionRemota(adminRemota);
			log.info("guardo la UARemota de codigo "+ uaRemota.getCodigoEstandar() +" e idExterno "+uaRemota.getIdExterno());
			
			Long idPadre = null;
			if(padre!=null){
				idPadre = padre.getId();
			}else{
				uaRemota.setCodigoEstandar(adminRemota.getCodigoEstandarUA());
			}

			uaRemotaDelegate.grabarUARemota(uaRemota, idPadre, uaTrans.getCodigoEstandarTratamiento(),uaTrans.getCodigoEstandarMaterias());

			recogerFichas(uaRemota);

			recogerProcedimientos(uaRemota);
	
			if(profundidad<adminRemota.getProfundidad()){
				log.info("no se alcanzo la profundidad deseada, recogiendo hijos");
				final int temp = profundidad;
				if(uaTrans.getIdHijos()!=null){
					for(Long idUA : uaTrans.getIdHijos()){
						log.info("Recogido Hijo");
						final UnidadAdministrativaTransferible uahTrans = sincInvoker.recogerUnidadAdministrativa(idUA);
						if(uahTrans!=null)
							recogerUnidadesAdministrativas(uahTrans ,uaRemota);
						profundidad=temp;
					}
				}
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerUnidadAdministrativa",e);
		}
	}
	
	/**
	 * Recoge y almacena todas las {@link Ficha} relacionadas con la
	 * {@link UnidadAdministrativa}.
	 * 
	 * @param ua
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	@SuppressWarnings("unchecked")
	protected void recogerFichas(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
		try {
			log.info("Recogiendo fichas relacionadas");
			//TODO: Se han de listar todas las secciones que tengan codigoEstandar
			final List<Seccion> secciones = DelegateUtil.getSeccionDelegate().listarSecciones();
			
			for(Seccion seccion : secciones){
				if(seccion.getCodigoEstandard()!=null && !"".equals(seccion.getCodigoEstandard().trim())){
					log.info("Recoginedo fichas de la seccion: "+ ((TraduccionSeccion)seccion.getTraduccion()).getNombre());
					final FichaTransferible[] fichasTrans = sincInvoker.recogerFichasUASeccion(seccion.getCodigoEstandard(), ua.getIdExterno(), hechosCE, materiasCE);
					if(fichasTrans!=null){
						log.info("Fichas recogidas");
                        boolean grabarFicha= false;
						for(FichaTransferible fichaTrans : fichasTrans){
                            // Condicions afegides per controllar que no es donin d'alta fitxes sense matèries ni fets vitals.
                            // Controllam que si la llista ens ve amb elements nulls, se descarti la fitxa.
                            if ((fichaTrans.getCodigoEstandarMaterias() != null && fichaTrans.getCodigoEstandarMaterias().length > 0)
				                || (fichaTrans.getCodigoEstandarHV() != null && fichaTrans.getCodigoEstandarHV().length > 0)) {


                                  if(fichaTrans.getCodigoEstandarMaterias()!=null){
                                    for(String ceMat : fichaTrans.getCodigoEstandarMaterias()){
                                       if(ceMat != null){
                                           log.info("entro");
                                           grabarFicha=true;
                                       }
                                    }
                                  }

                                  if(fichaTrans.getCodigoEstandarHV()!=null){
                                    for(String ceHV : fichaTrans.getCodigoEstandarHV()){
                                       if(ceHV!=null){
                                           grabarFicha=true;
                                       }
                                    }
                                  }

                                  if(grabarFicha){
                                    if (fichaTrans.getId() != null) {
                                        FichaRemota fichaRemota = fichaRemotaDelegate.obtenerFichaRemota(fichaTrans.getId(), adminRemota.getId());
                                        if(fichaRemota==null){
                                            log.info("La ficha no existe la creo");
                                            fichaRemota = new FichaRemota();
                                        }

                                        fichaRemota.rellenar(fichaTrans);
                                        fichaRemota.setAdministracionRemota(adminRemota);
                                        fichaRemotaDelegate.grabarFichaRemota(fichaRemota,ua.getId(),seccion.getId(),fichaTrans.getCodigoEstandarMaterias(),fichaTrans.getCodigoEstandarHV());
                                        log.info("FichaUA guardada idExt"+fichaRemota.getIdExterno());
                                    } else {
                                        log.warn("Ficha transferible con 'id' null, ignorando!!!!");
                                    }
                                  }
                            }
                        }
					}
				}
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerFichasUASeccion",e);
		}
	}
	
	/**
	 * Recoge y almacena todos los {@link ProcedimientoLocal} relacionados con la
	 * {@link UnidadAdministrativa}.
	 * 
	 * @param ua
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	protected void recogerProcedimientos(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
		try {
			log.info("Recogiendo Procedimientos relacionados");
			final ProcedimientoTransferible[] procsTransferibles = sincInvoker.recogerProcedimientosRelacionados(ua.getIdExterno(), hechosCE, materiasCE);
			
			if(procsTransferibles!=null){
				log.info("Procedimientos recogidos");
				
				for(ProcedimientoTransferible procTransferible : procsTransferibles){
					
					if (procTransferible.getId() != null) {
                        ProcedimientoRemoto procRemoto = procRemotoDelegate.obtenerProcedimientoRemoto(procTransferible.getId(),adminRemota.getId());
                        if(procRemoto==null){
                            procRemoto= new ProcedimientoRemoto();
                        }

                        procRemoto.rellenear(procTransferible);

                        procRemoto.setUnidadAdministrativa(ua);
                        procRemoto.setAdministracionRemota(adminRemota);

                        procRemotoDelegate.grabarProcedimientoRemoto(procRemoto,procTransferible.getCodigoEstandarMaterias(),procTransferible.getCodigoEstandarHV());
                        log.info("Procedimiento guardado idExt "+ procTransferible.getId());
                    } else {
                        log.warn("Procedimiento transferible con 'id' null, ignorando!!!!");
                    }
                }
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerProcedimientosRelacionados",e);
		}
	}
	
}
