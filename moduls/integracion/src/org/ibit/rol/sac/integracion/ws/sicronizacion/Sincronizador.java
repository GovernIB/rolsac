package org.ibit.rol.sac.integracion.ws.sicronizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ibit.rol.sac.integracion.ws.UnidadAdminCENoEncontradaException;
import org.ibit.rol.sac.integracion.ws.invoker.SincronizacionServicio;
import org.ibit.rol.sac.integracion.ws.invoker.WSInvocatorException;

import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.Remoto;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;

import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoRemotoDelegate;
import org.ibit.rol.sac.persistence.delegate.UARemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

/**
 * OBSOLETO
 * @deprecated 
 * Realiza el proceso de alta y baja la informacion de una {@link AdministracionRemota}.
 * 
 * @author arodrigo
 *
 */
public class Sincronizador {
	
	private static Log log = LogFactory.getLog(Sincronizador.class);
	
	//AdminstracionRemota con la que estamos trabajando
	private final transient AdministracionRemota adminRemota;
	
	//Cliente WebService con las funciones de sincronizacion
	private final transient SincronizacionServicio sincInvoker;
	
	//Delegate's
	private final transient  ProcedimientoRemotoDelegate procRemotoDelegate;
	private final transient  ProcedimientoDelegate procDelegate;
	private final transient  UARemotaDelegate uaRemotaDelegate;
	private final transient  UnidadAdministrativaDelegate uaDelegate;
	private final transient  FichaRemotaDelegate fichaRemotaDelegate;
	private final transient  FichaDelegate fichaDelegate;
	private final transient  AdministracionRemotaDelegate adminDelegate;
	
	//Array con los codigos estandar de las HechosVitales de nuestra DB
	private final transient String[] hechosCE ;
	//Array con los codigos estandar de las materias de nuestra DB
	private final transient String[] materiasCE;
	
	//Nivel de profundidad en el que se esta trabajando
	private transient int profundidad=0; 
	
	/**
	 * Constructor con los parametros minimos.
	 * 
	 * @param adminRemota
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	@SuppressWarnings("unchecked")
	public Sincronizador(final AdministracionRemota adminRemota) throws CapaDeDatosException, ComunicacionException {
		this.adminRemota=adminRemota;
		try {
			sincInvoker = new SincronizacionServicio(
					adminRemota.getEndpoint());
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Error al construir el conector",e);
		}
		
		//Recoge los Delegate's
		procRemotoDelegate =  DelegateUtil.getProcedimientoRemotoDelegate();
		procDelegate =  DelegateUtil.getProcedimientoDelegate();
		uaRemotaDelegate = DelegateUtil.getUARemotaDelegate();
		uaDelegate = DelegateUtil.getUADelegate();
		fichaRemotaDelegate = DelegateUtil.getFichaRemotaDelegate();
		fichaDelegate = DelegateUtil.getFichaDelegate();
		adminDelegate = DelegateUtil.getAdministracionRemotaDelegate();
		
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
	
	/**
	 * Recoge toda la informacion de la {@link AdministracionRemota}. Comienza desde la
	 * {@link UnidadAdministrativa} cuyo codigo estandar esta señalado en la {@link AdministracionRemota}
	 * y a la profindidad señalada.
	 * 
	 * Todas las {@link Ficha} y {@link ProcedimientoLocal} relacionados con las {@link UnidadAdministrativa}
	 * tambien son recogidos.
	 */
	public void alta() throws UnidadAdminCENoEncontradaException, ComunicacionException, CapaDeDatosException{
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
	 * Elimina todos los elementos {@link Remoto} contenidos
	 * en la {@link AdministracionRemota}
	 * 
	 * @throws CapaDeDatosException
	 */
	public void baja() throws CapaDeDatosException{
		log.info("Comenzando la funcion de BAJA");
		
		try {
			log.info("Borrando Fichas");
			borrarFichas();
			log.info("Borrando Procedimientos");
			borrarProcedimientos();
			log.info("Borrando UAs");
			borrarUA();
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		}
		log.info("BAJA realizada con exito");
	}
	
	//------------------------Funciones Privadas de Recogida-----------------------------------
	
	/**
	 * Funcion recursiva que recorre todo el arbol de las {@link UnidadAdministrativa} asta la
	 * profundidad definida.
	 * 
	 * Durante el proceso almacena tanto las {@link UnidadAdministrativa} como sus {@link Ficha}
	 * y {@link ProcedimientoLocal} relacionados.
	 * 
	 */
	private void recogerUnidadesAdministrativas(final UnidadAdministrativaTransferible uaTrans, UnidadAdministrativaRemota padre) throws CapaDeDatosException, ComunicacionException {
		profundidad++;
		
		try{
			log.info("rellenando la UARemota a partir de la transferible");
			final UnidadAdministrativaRemota uaRemota = UnidadAdministrativaRemota.generar(uaTrans);
			uaRemota.setAdministracionRemota(adminRemota);
			log.info("guardo la UARemota de nombre "+((TraduccionUA)uaRemota.getTraduccion()).getNombre()+" e idExterno "+uaRemota.getIdExterno());
			
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
	private void recogerFichas(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
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
						for(FichaTransferible fichaTrans : fichasTrans){
							
							FichaRemota fichaRemota = fichaRemotaDelegate.obtenerFichaRemota(fichaTrans.getId(), adminRemota.getId()); 
							if(fichaRemota==null){
								log.info("La ficha no existe la creo");
								fichaRemota = new FichaRemota();
							}
							
							fichaRemota.rellenar(fichaTrans);
							fichaRemota.setAdministracionRemota(adminRemota);
							fichaRemotaDelegate.grabarFichaRemota(fichaRemota,ua.getId(),seccion.getId(),fichaTrans.getCodigoEstandarMaterias(),fichaTrans.getCodigoEstandarHV());
							log.info("FichaUA guardada idExt"+fichaRemota.getIdExterno());
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
	private void recogerProcedimientos(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
		try {
			log.info("Recogiendo Procedimientos relacionados");
			final ProcedimientoTransferible[] procsTransferibles = sincInvoker.recogerProcedimientosRelacionados(ua.getIdExterno(), hechosCE, materiasCE);
			
			if(procsTransferibles!=null){
				log.info("Procedimientos recogidos");
				
				for(ProcedimientoTransferible procTransferible : procsTransferibles){
					
					ProcedimientoRemoto procRemoto = procRemotoDelegate.obtenerProcedimientoRemoto(procTransferible.getId(),adminRemota.getId());
					if(procRemoto==null){
						procRemoto= new ProcedimientoRemoto();
					}
					
					procRemoto.rellenear(procTransferible);
					
					procRemoto.setUnidadAdministrativa(ua);
                    ua.addProcedimientoLocal(procRemoto);
					procRemoto.setAdministracionRemota(adminRemota);
					
					procRemotoDelegate.grabarProcedimientoRemoto(procRemoto,procTransferible.getCodigoEstandarMaterias(),procTransferible.getCodigoEstandarHV());
					log.info("Procedimiento guardado idExt "+ procTransferible.getId());
				}
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerProcedimientosRelacionados",e);
		}
	}
	
	
	//------------------------Funciones Privadas de Borrado-----------------------------------
	
	/**
	 * Borra todas las UA remotas de la AR.
	 * 
	 * Esta funcion concretamente busca todas las UA Raiz contenidas en la AR y las manda a la
	 * Funcion recursiva de borrarUA(UnidadAdministrativaRemota uaRemota)
	 * @throws DelegateException
	 */
	private void borrarUA() throws DelegateException{
		Set<UnidadAdministrativaRemota> unidades = adminDelegate.listarUARemotas(adminRemota.getId());
		
		if(unidades!=null && !unidades.isEmpty()){
			for (UnidadAdministrativaRemota unidad : unidades) {
				if(unidad.getPadre()==null){
					borrarUA(unidad);
				}
			}
		}
	}
	
	/**
	 * Borra recursivamente los hijos contenidos dentro de una UA, asi como la propia
	 * UA padre.
	 * 
	 * @throws DelegateException
	 */
	@SuppressWarnings("unchecked")
	private void borrarUA(UnidadAdministrativaRemota uaRemota) throws DelegateException {
		uaRemota = (UnidadAdministrativaRemota)uaDelegate.consultarUnidadAdministrativa(uaRemota.getId());
		
		if(uaRemota.getHijos()!=null && !uaRemota.getHijos().isEmpty()){
			for(UnidadAdministrativa uaHija : uaRemota.getHijos()){
				if (uaHija instanceof UnidadAdministrativaRemota) {	
					UnidadAdministrativaRemota uaRemotaHija = (UnidadAdministrativaRemota) uaHija;
					borrarUA(uaRemotaHija);
				}
			}
		}
		
		if(uaRemota.getProcedimientos()!=null){
			List<ProcedimientoLocal> procs = procDelegate.listarProcedimientosUA(uaRemota.getId());
			for (ProcedimientoLocal proc : procs) {
				procDelegate.borrarProcedimiento(proc.getId());
			}
		}
		if(uaRemota.getFichasUA()!=null){
			for (FichaUA fichaua : uaRemota.getFichasUA()) {
				fichaDelegate.borrarFicha(fichaua.getFicha().getId());
			}
		}
		
    	try{
    		uaDelegate.eliminarUaSinRelaciones(uaRemota.getId());
    	}catch(SecurityException e){
    		throw new SecurityException("No tiene suficientes permisos para eliminar la UA.");
    	}
		
	
	}
	
	/**
	 * Borra todas las fichas remotas de la AR
	 * @throws DelegateException
	 */
	private void borrarFichas() throws DelegateException{
		Set<FichaRemota> fichas = adminDelegate.listarFichasRemotas(adminRemota.getId());
		for(FichaRemota ficha : fichas){
			fichaDelegate.borrarFicha(ficha.getId());
		}
	}
	
	
	/**
	 * Borra todos los procedimientos de la administracion remota de la AR
	 * @throws DelegateException
	 */
	private void borrarProcedimientos() throws DelegateException{
		Set<ProcedimientoRemoto> procs = adminDelegate.listarProcedimientosRemotos(adminRemota.getId());
		for(ProcedimientoRemoto proc : procs){
			procDelegate.borrarProcedimiento(proc.getId());
		}
	}
}
