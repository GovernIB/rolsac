package org.ibit.rol.sac.integracion.ws.sicronizacion;

import org.ibit.rol.sac.integracion.ws.invoker.WSInvocatorException;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.EdificioRemoto;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.ws.EdificioTransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;
import org.ibit.rol.sac.persistence.delegate.DelegateException;

/**
 * Realiza el proceso de alta de la informacion de una {@link AdministracionRemota}.
 * 
 * @author arodrigo
 *
 */
public class SincronizadorAltaThreadV2 extends SincronizadorAltaThread{
	
	public SincronizadorAltaThreadV2(final AdministracionRemota adminRemota) throws CapaDeDatosException, ComunicacionException {
		super(adminRemota);
	}
	
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

			recogerEdificios(uaRemota);

			
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
	 * Recoge y almacena todos los {@link Edificios} relacionados con la
	 * {@link UnidadAdministrativa}.
	 * 
	 * @param ua
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	private void recogerEdificios(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
		try {
			log.info("Recogiendo Edificios relacionados para la UA: "+ua);
			final EdificioTransferible[] edificioTransferibles = sincInvoker.recogerEdificiosRelacionados(ua.getIdExterno());
			
			if(edificioTransferibles!=null){
				log.info("Edificios recogidos tamaño: "+edificioTransferibles.length);
				
				for(EdificioTransferible edificioTransferible : edificioTransferibles){
					log.info("getId: "+edificioTransferible.getId());
					log.info("getDireccion: "+edificioTransferible.getDireccion());
					log.info("getPoblacion: "+edificioTransferible.getPoblacion());
					if (edificioTransferible.getId() != null) {
						EdificioRemoto edificioRemoto = uaRemotaDelegate.obtenerEdificioRemoto(edificioTransferible.getId(),adminRemota.getId());
                        if(edificioRemoto==null){
                        	edificioRemoto= new EdificioRemoto();
                        }
                        edificioRemoto.rellenar(edificioTransferible); 
                        edificioRemoto.getUnidadesAdministrativas().add(ua);
                        edificioRemoto.setAdministracionRemota(adminRemota);
                        uaRemotaDelegate.grabarEdificioRemoto(edificioRemoto);
                        log.info("Edificio guardado idExt "+ edificioTransferible.getId());
                    } else {
                        log.warn("Edificio transferible con 'id' null, ignorando!!!!");
                    }
                }
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerEdificiosRelacionados",e);
		}
	}
}
