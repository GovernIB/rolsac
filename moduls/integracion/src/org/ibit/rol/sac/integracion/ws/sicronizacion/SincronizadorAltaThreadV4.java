package org.ibit.rol.sac.integracion.ws.sicronizacion;

import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;
import org.ibit.rol.sac.model.ws.DocumentoTransferible;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.integracion.ws.invoker.WSInvocatorException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 19-sep-2011
 * Time: 9:36:53
 * Esta versión coincide con la V3 donde lo que se ha cambiado es la manera de obtener las fichas
 * y los procedimientos para evitar problemas de memoria con el mule. Ahora se van cogiendo de uno en uno.
 */
public class SincronizadorAltaThreadV4 extends SincronizadorAltaThreadV3{


    public SincronizadorAltaThreadV4(final AdministracionRemota adminRemota) throws CapaDeDatosException, ComunicacionException {
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
	 * Recoge y almacena todas las {@link org.ibit.rol.sac.model.Ficha} relacionadas con la
	 * {@link org.ibit.rol.sac.model.UnidadAdministrativa}.
	 *
	 * @param ua
	 * @throws CapaDeDatosException
	 * @throws ComunicacionException
	 */
	@SuppressWarnings("unchecked")
	protected void recogerFichas(final UnidadAdministrativaRemota ua) throws CapaDeDatosException, ComunicacionException{
		try {
			log.debug("Recogiendo fichas relacionadas");
			//TODO: Se han de listar todas las secciones que tengan codigoEstandar
			final List<Seccion> secciones = DelegateUtil.getSeccionDelegate().listarSecciones();

			for(Seccion seccion : secciones){
				if(seccion.getCodigoEstandard()!=null && !"".equals(seccion.getCodigoEstandard().trim())){
					log.debug("Recoginedo fichas de la seccion: "+ ((TraduccionSeccion)seccion.getTraduccion()).getNombre());
					final Long[] idsFichaTrans = sincInvoker.recogerIdsFichasUASeccion(seccion.getCodigoEstandard(), ua.getIdExterno(), hechosCE, materiasCE);
					if(idsFichaTrans!=null){
						log.debug("Fichas recogidas");
                        boolean grabarFicha= false;
						for(Long idFichaTrans : idsFichaTrans){
                            // Condicions afegides per controllar que no es donin d'alta fitxes sense matèries ni fets vitals.
                            // Controllam que si la llista ens ve amb elements nulls, se descarti la fitxa.
                            FichaTransferible fichaTrans = sincInvoker.recogerFicha(idFichaTrans);
                            if ((fichaTrans.getCodigoEstandarMaterias() != null && fichaTrans.getCodigoEstandarMaterias().length > 0)
				                || (fichaTrans.getCodigoEstandarHV() != null && fichaTrans.getCodigoEstandarHV().length > 0)) {


                                  if(fichaTrans.getCodigoEstandarMaterias()!=null){
                                    for(String ceMat : fichaTrans.getCodigoEstandarMaterias()){
                                       if(ceMat != null){
                                           log.debug("entro");
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
                                            log.debug("La ficha no existe la creo");
                                            fichaRemota = new FichaRemota();
                                        }

                                        fichaRemota.rellenar(fichaTrans);
                                        fichaRemota.setAdministracionRemota(adminRemota);
                                        fichaRemotaDelegate.grabarFichaRemota(fichaRemota,ua.getId(),seccion.getId(),fichaTrans.getCodigoEstandarMaterias(),fichaTrans.getCodigoEstandarHV());
                                        log.debug("FichaUA guardada idExt"+fichaRemota.getIdExterno());
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
			log.debug("Recogiendo Procedimientos relacionados");
			final Long[] idsProcsTransferibles = sincInvoker.recogerIdsProcedimientosRelacionados(ua.getIdExterno(), hechosCE, materiasCE);
			if(idsProcsTransferibles!=null){
				log.debug("Procedimientos recogidos tamaño: "+idsProcsTransferibles.length);

				for(Long idProcTransferible : idsProcsTransferibles){
                    if(null != idProcTransferible){
                        ProcedimientoTransferible procTransferible = sincInvoker.recogerProcedimiento(idProcTransferible);
                        if (procTransferible.getId() != null) {
                            ProcedimientoRemoto procRemoto = procRemotoDelegate.obtenerProcedimientoRemoto(procTransferible.getId(),adminRemota.getId());
                            if(procRemoto==null){
                                procRemoto= new ProcedimientoRemoto();
                            }
                            procRemoto.rellenear(procTransferible);

                            if(procTransferible.getIdOrganResolutori()!=null){
                                procRemoto.setOrganResolutori(uaRemotaDelegate.obtenerUARemota(adminRemota.getIdRemoto(),procTransferible.getIdOrganResolutori()));
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
                    }
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
