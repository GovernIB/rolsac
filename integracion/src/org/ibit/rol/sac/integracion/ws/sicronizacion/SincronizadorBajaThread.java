package org.ibit.rol.sac.integracion.ws.sicronizacion;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ibit.rol.sac.model.*;

import org.ibit.rol.sac.persistence.delegate.*;

/**
 * Realiza el proceso de baja de la informacion de una {@link AdministracionRemota}.
 * 
 * @author arodrigo
 *
 */
public class SincronizadorBajaThread extends SincronizadorThreadAbstract {
	
	private static Log log = LogFactory.getLog(SincronizadorBajaThread.class);
	
	//AdminstracionRemota con la que estamos trabajando
	private final transient AdministracionRemota adminRemota;
	
	
	//Delegate's
	private final transient  ProcedimientoDelegate procDelegate;
	private final transient  UnidadAdministrativaDelegate uaDelegate;
	private final transient  UARemotaDelegate uaRemotaDelegate;
	private final transient  FichaDelegate fichaDelegate;
	private final transient  AdministracionRemotaDelegate adminDelegate;
	private final transient  TramiteRemotoDelegate tramiteRemotoDelegate;
	private final transient NormativaExternaRemotaDelegate normativaRemotaDelegate;
	private final transient EdificioDelegate edificioDelegate;

	//Excepcion
	private Exception exception;

	/**
	 * Almacena los datos de la {@link AdministracionRemota} y prepara los Delegates que se
	 * van a usar durante el Proceso.
	 * 
	 * @param adminRemota
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	@SuppressWarnings("unchecked")
	public SincronizadorBajaThread(final AdministracionRemota adminRemota) throws CapaDeDatosException {
		this.adminRemota=adminRemota;
		
		//Recoge los Delegate's
		procDelegate =  DelegateUtil.getProcedimientoDelegate();
		uaDelegate = DelegateUtil.getUADelegate();
		fichaDelegate = DelegateUtil.getFichaDelegate();
		adminDelegate = DelegateUtil.getAdministracionRemotaDelegate();
		uaRemotaDelegate = DelegateUtil.getUARemotaDelegate();
		tramiteRemotoDelegate = DelegateUtil.getTramiteRemotoDelegate();
		normativaRemotaDelegate = DelegateUtil.getNormativaExternaRemotaDelegate();
		edificioDelegate = DelegateUtil.getEdificioDelegate();

		log.info("Constructor completado");
	}
	
	//------------------------Funciones Principales-----------------------------------

	@Override
	public void run() {
		SincronizadorSingleton.getInstance().newEstado();
		try {
			baja();
			SincronizadorSingleton.getInstance().newEstado();
		} catch (CapaDeDatosException e) {
			exception = e;
			SincronizadorSingleton.getInstance().newEstado();
		}
	}
		
	/**
	 * Elimina todos los elementos {@link Remoto} contenidos
	 * en la {@link AdministracionRemota}
	 * 
	 * @throws CapaDeDatosException
	 */
	private void baja() throws CapaDeDatosException{
		log.info("Comenzando la funcion de BAJA");
		
		try {
			log.info("Borrando Fichas");
			borrarFichas();
			log.info("Borrando Tramites");
			borrarTramites();
			log.info("Borrando Procedimientos");
			borrarProcedimientos();
			log.info("Borrando Edificios");
			borrarEdificios();
            log.info("Borrando Normativas");
			borrarNormativas();
			log.info("Borrando UAs");
			borrarUA();

		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		}
		log.info("BAJA realizada con exito");
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
	//	uaRemota = (UnidadAdministrativaRemota)uaDelegate.consultarUnidadAdministrativa(uaRemota.getId());
        uaRemota = uaRemotaDelegate.obtenerUARemota(uaRemota.getId());
		
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
                log.info("FICHAS UA " + uaRemota.getFichasUA().size());
                if(fichaua !=null){
				    fichaDelegate.borrarFichaUA(fichaua.getId());
                }
			}
		}
        /*
		if(uaRemota.getEdificios()!=null){
			for (Object object : uaRemota.getEdificios()) {
				Edificio edificio=(Edificio) object;
				//uaRemotaDelegate.borrarEdificioRemoto(edificio.getId());
				edificioDelegate.borrarEdificio(edificio.getId());
			}
		}
		*/
		
		if(uaRemota.getPadre()==null)
			uaRemotaDelegate.borrarUnidadAdministrativaRaizRemota(uaRemota.getId());
		else
			uaRemotaDelegate.borrarUnidadAdministrativaRemota(uaRemota.getId());
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
	
	/**
	 * Borra todos los edificios de la administracion remota de la AR
	 * @throws DelegateException
	 */
	private void borrarEdificios() throws DelegateException{
		Set<EdificioRemoto> edifs = adminDelegate.listarEdificiosRemotos(adminRemota.getId());
		for(EdificioRemoto edif : edifs){
			uaRemotaDelegate.borrarEdificioRemoto(edif.getId());
		}
	}
	
	/**
	 * Borra todos los tramites de la administracion remota de la AR
	 * @throws DelegateException
	 */
	private void borrarTramites() throws DelegateException{
		Set<TramiteRemoto> tramites = adminDelegate.listarTramitesRemotos(adminRemota.getId());
		for(TramiteRemoto tram : tramites){
			tramiteRemotoDelegate.borrarTramiteRemoto(tram.getId());
		}
	}



    /**
	 * Borra todos los tramites de la administracion remota de la AR
	 * @throws DelegateException
	 */
	private void borrarNormativas() throws DelegateException{
		Set<NormativaExternaRemota> normats = adminDelegate.listarNormativasExternasRemotas(adminRemota.getId());		
        for(NormativaExternaRemota norm : normats){
			normativaRemotaDelegate.borrarNormativaRemota(norm.getId());
        }
	}
}
