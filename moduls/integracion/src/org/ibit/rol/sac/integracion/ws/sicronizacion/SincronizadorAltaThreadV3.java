package org.ibit.rol.sac.integracion.ws.sicronizacion;


import org.ibit.rol.sac.integracion.ws.invoker.WSInvocatorException;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.*;
import org.ibit.rol.sac.persistence.delegate.DelegateException;


/**
 * Realiza el proceso de alta de la informacion de una {@link AdministracionRemota}.
 * 
 * @author arodrigo
 *
 * Esta versión implementa toda la funcionalidad necesaria para adaptarse a los requerimientos de la Ventanilla Unica
 * de Directiva de Servicios
 */
public class SincronizadorAltaThreadV3 extends SincronizadorAltaThread{
	
	
	public SincronizadorAltaThreadV3(final AdministracionRemota adminRemota) throws CapaDeDatosException, ComunicacionException {
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


            //TODO: Descomentar al acabar pruebas
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
	protected void recogerEdificios(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
		try {
			log.debug("Recogiendo Edificios relacionados para la UA: "+ua);
			final EdificioTransferible[] edificioTransferibles = sincInvoker.recogerEdificiosRelacionados(ua.getIdExterno());
			
			if(edificioTransferibles!=null){
				log.debug("Edificios recogidos tamaño: "+edificioTransferibles.length);
				
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
			log.debug("Recogiendo Procedimientos relacionados");
			final ProcedimientoTransferible[] procsTransferibles = sincInvoker.recogerProcedimientosRelacionados(ua.getIdExterno(), hechosCE, materiasCE);
			if(procsTransferibles!=null){
				log.debug("Procedimientos recogidos tamaño: "+procsTransferibles.length);
				for(ProcedimientoTransferible procTransferible : procsTransferibles){
					if (procTransferible.getId() != null) {
                        ProcedimientoRemoto procRemoto = procRemotoDelegate.obtenerProcedimientoRemoto(procTransferible.getId(),adminRemota.getId());
                        if(procRemoto==null){
                            procRemoto= new ProcedimientoRemoto();
                        }
                        procRemoto.rellenear(procTransferible);
                        
                    	if(procTransferible.getIdOrganResolutori()!=null){
                            procRemoto.setOrganResolutori(uaRemotaDelegate.obtenerUARemota(adminRemota.getIdRemoto(), procTransferible.getIdOrganResolutori()));	
                        }
                        procRemoto.setUnidadAdministrativa(ua);
                        procRemoto.setAdministracionRemota(adminRemota);
                        
                        //INICIACION
                        boolean actualizarIniciacion=false;
                        if(procTransferible.getCodigoEstandarIniciacion()!=null){
                        	Iniciacion iniciacion = procRemotoDelegate.obtenerIniciacion(procTransferible.getCodigoEstandarIniciacion());
                        	if (iniciacion != null) {
                    			procRemoto.setIniciacion(iniciacion);
                    			actualizarIniciacion=true;
                    		} 
                        }
                    	if(!actualizarIniciacion){
                    		procRemoto.setIniciacion(null);
                    	}

                        
                        procRemotoDelegate.grabarProcedimientoRemoto(procRemoto,procTransferible.getCodigoEstandarMaterias(),procTransferible.getCodigoEstandarHV());
                        log.debug("Procedimiento guardado idExt "+ procTransferible.getId());    

                        //recogemos documentos Informativos (uno a uno)
                        if(procTransferible.getIdsDocsInfor()!=null){
                            for(Long idDoc : procTransferible.getIdsDocsInfor()){
                                log.debug("recogiendo documento remoto");
                                DocumentoTransferible docTrans = sincInvoker.recogerDocumento(idDoc);
                                //Convertir a Remoto y relacionarlo con el procedimiento

                                if (docTrans.getId() != null) {
						            DocumentoRemoto documentoRemoto = procRemotoDelegate.obtenerDocumentoRemoto(docTrans.getId(),adminRemota.getId());
                                    if(documentoRemoto==null){
                        	            documentoRemoto= new DocumentoRemoto();
                                    }
                                    documentoRemoto.rellenar(docTrans);


                                    documentoRemoto.setAdministracionRemota(adminRemota);
                                    procRemotoDelegate.grabarDocumentoRemoto(documentoRemoto, procRemoto.getId(), null);

                                }
                            }
                        }


                        //Recogemos sus tramites                        
            			recogerTramites(procRemoto);
                        //Recogemos sus normativas                        
            			recogerNormativas(procRemoto);
                        
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
	
	
	private UnidadAdministrativa recogerOrdanResolutori (Long  idOrganResolutori,Long idAdminRemota,ProcedimientoRemoto procRemoto){
		//obtenemos el Órgano Resolutori y lo añadimos
		UnidadAdministrativaRemota organResolutori=null;
		if(idOrganResolutori!=null){
			try {
				organResolutori = uaRemotaDelegate.obtenerUARemota(idAdminRemota.toString(), idOrganResolutori);
			} catch (DelegateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(organResolutori!=null){
    			procRemoto.setOrganResolutori(organResolutori);
    			//organResolutori.setproc;
    		}

		}
		return null;
	}
	/**
	 * Recoge y almacena todos los {@link Tramites} relacionados con la
	 * {@link ProcedimientoLocal}.
	 * 
	 * @param proc
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	protected void recogerTramites(final ProcedimientoRemoto proc) throws CapaDeDatosException, ComunicacionException{
		try {
			log.debug("Recogiendo Tramites relacionados para el procedimiento: "+proc.getIdExterno());
			final TramiteTransferible[] tramiteTransferibles = sincInvoker.recogerTramitesRelacionados(proc.getIdExterno());

			if(tramiteTransferibles!=null){
				for(TramiteTransferible tramiteTransferible : tramiteTransferibles){

					if (tramiteTransferible.getId() != null) {
						TramiteRemoto tramiteRemoto = tramiteRemotoDelegate.obtenerTramiteRemoto(tramiteTransferible.getId(),adminRemota.getId());
                        if(tramiteRemoto==null){
                        	tramiteRemoto= new TramiteRemoto();
                        }
                        tramiteRemoto.rellenar(tramiteTransferible); 
                        
                        tramiteRemoto.setAdministracionRemota(adminRemota);
                        tramiteRemotoDelegate.grabarTramiteRemoto(tramiteRemoto,proc.getId(),tramiteTransferible.getIdOrganCompetent());
                        log.debug("Tramite guardado idExt "+ tramiteTransferible.getId());
                    } else {
                        log.warn("Tramite transferible con 'id' null, ignorando!!!!");
                    }
                }
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerTramites",e);
		}
	}
	
	
	/**
	 * Recoge y almacena todos l2s {@link Normativas} relacionados con la
	 * {@link ProcedimientoLocal}.
	 * 
	 * @param proc
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	protected void recogerNormativas(final ProcedimientoRemoto proc) throws CapaDeDatosException, ComunicacionException{
		try {
			log.debug("Recogiendo Normativas relacionados para el procedimiento: "+proc.getIdExterno());
			final NormativaTransferible[] normativaTransferibles = sincInvoker.recogerNormativasRelacionadas(proc.getIdExterno());

			if(normativaTransferibles!=null){
				for(NormativaTransferible normativaTransferible : normativaTransferibles){

					if (normativaTransferible.getId() != null) {
						NormativaExternaRemota normativaExternaRemota = normativaExternaRemotaDelegate.obtenerNormativaExternaRemota(normativaTransferible.getId(),adminRemota.getId());
                        if(normativaExternaRemota==null){
                            log.debug("Entro aqui");
                        	normativaExternaRemota= new NormativaExternaRemota();
                        }
                        normativaExternaRemota.rellenar(normativaTransferible);

                        log.debug("Normativa que vamos a guardar con idExt "+ normativaTransferible.getId());
                        normativaExternaRemota.setAdministracionRemota(adminRemota);
                        normativaExternaRemotaDelegate.grabarNormativaExternaRemota(normativaExternaRemota);
                        normativaExternaRemotaDelegate.anyadirProcedimiento(proc.getId(), normativaExternaRemota.getId());
                        log.debug("Normativa guardada idExt "+ normativaTransferible.getId());
                    } else {
                        log.warn("Normativa transferible con 'id' null, ignorando!!!!");
                    }
                }
			}
		} catch (DelegateException e) {
			throw new CapaDeDatosException(e);
		} catch (WSInvocatorException e) {
			throw new ComunicacionException("Fallo en la invocacion del webservice recogerNormativas",e);
		}
	}
}
