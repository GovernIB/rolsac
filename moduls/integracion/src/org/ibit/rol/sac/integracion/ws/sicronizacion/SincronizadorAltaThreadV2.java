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
 * Esta version incluye lo mismos que la version 1 pero en esta ademas se recogen los edificios relacionados
 * con las diferentes unidades administrativas remotas.
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
			log.debug("rellenando la UARemota a partir de la transferible");
			final UnidadAdministrativaRemota uaRemota = UnidadAdministrativaRemota.generar(uaTrans);
			uaRemota.setAdministracionRemota(adminRemota);
			log.debug("guardo la UARemota de codigo "+ uaRemota.getCodigoEstandar() +" e idExterno "+uaRemota.getIdExterno());
			
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
				log.debug("no se alcanzo la profundidad deseada, recogiendo hijos");
				final int temp = profundidad;
				if(uaTrans.getIdHijos()!=null){
					for(Long idUA : uaTrans.getIdHijos()){
						log.debug("Recogido Hijo");
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
			log.debug("Recogiendo Edificios relacionados para la UA: "+ua);
			final EdificioTransferible[] edificioTransferibles = sincInvoker.recogerEdificiosRelacionados(ua.getIdExterno());
			
			if(edificioTransferibles!=null){
				log.debug("Edificios recogidos tama�o: "+edificioTransferibles.length);
				
				for(EdificioTransferible edificioTransferible : edificioTransferibles){
					log.debug("getId: "+edificioTransferible.getId());
					log.debug("getDireccion: "+edificioTransferible.getDireccion());
					log.debug("getPoblacion: "+edificioTransferible.getPoblacion());
					if (edificioTransferible.getId() != null) {
						EdificioRemoto edificioRemoto = uaRemotaDelegate.obtenerEdificioRemoto(edificioTransferible.getId(),adminRemota.getId());
                        if(edificioRemoto==null){
                        	edificioRemoto= new EdificioRemoto();
                        }
                        edificioRemoto.rellenar(edificioTransferible); 
                        edificioRemoto.getUnidadesAdministrativas().add(ua);
                        edificioRemoto.setAdministracionRemota(adminRemota);
                        uaRemotaDelegate.grabarEdificioRemoto(edificioRemoto);
                        log.debug("Edificio guardado idExt "+ edificioTransferible.getId());
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
