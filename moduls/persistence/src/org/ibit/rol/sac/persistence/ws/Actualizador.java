package org.ibit.rol.sac.persistence.ws;

import java.beans.DesignMode;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.model.ws.*;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;


/**
 * Transefiere a todos los destinatarios los diferentes Objectos Actualizables.
 * Los elementos actualizables son {@link Ficha}, {@link UnidadAdministrativa},
 * {@link ProcedimientoLocal} y {@link FichaUATransferible}.
 * 
 * Modificacion de VRS(2008/02/01): Se anyade System.getProperty("es.caib.rolsac.oficina")
 * para controlar que si estas en las oficinas de indra no se lance el webservices.
 * 
 * @author arodrigo
 * 
 *
 */
final public class Actualizador {
	
	protected static Log log = LogFactory.getLog(Actualizador.class);
	
	/**
	 * Lanza un Proceso encargado de actualizar el Objeto
	 * Los elementos actualizables son {@link Ficha}, {@link UnidadAdministrativa},
	 * {@link ProcedimientoLocal},{@link Edificio}.
	 * 
	 * @param actualizar: objeto a actualizar
	 * @param params: multiples objetos
	 * @note
	 * En el caso de modificar/borrar la UA asociada a un edificio, utilizamos el primer param para la id de la UA
	 */
	public static void actualizar(final Object actualizar, final Object... params) {
		if(!(actualizar instanceof Remoto)){
			final ActualizadorThread act = new Actualizador().new ActualizadorThread(
					actualizar, false, params);
			act.start();
		}
	}
	
	public static void actualizar(final Object actualizar) {
		Actualizador.actualizar(actualizar, new Object[0]);
	}

	
	/**
	 * Lanza un Proceso encargado de borrar el Objeto.
	 * Los elementos borrables son {@link Ficha}, {@link UnidadAdministrativa},
	 * {@link ProcedimientoLocal} , {@link FichaUATransferible} y {@link Edificio}.
	 * 
	 * @param actualizar: objeto a borrar
	 */
	public static void borrar(final Object actualizar, final Object... params) {
		if(!(actualizar instanceof Remoto)){
			final ActualizadorThread act = new Actualizador().new ActualizadorThread(
					actualizar, true, params);
			act.start();
		}
	}
	
	public static void borrar(final Object actualizar) {
		Actualizador.borrar(actualizar, new Object[0]);
	}
	
	
	public boolean calActualizar(Destinatario dest, Object elem) {
		//si indra no diu res i
		//altres casos -> false

    	String value = System.getProperty("es.caib.rolsac.oficina");
        if ((value == null) || value.equals("N")){
        	return true;
        }
        return false;
	}
	
	/**
	 * Proceso encargado de transferir el objeto a actualizar/borrar a traves
	 * de WebService
	 * 
	 * @author arodrigo
	 *
	 */
	protected class ActualizadorThread extends Thread {
		
		//Elemento a Actualizar/borrar
		private final Object actualizar;
		
		//booleana que indica si el elemento a de ser borrado
		private final boolean borrar;
		
		//Destinatarios a los que se les enviara la actualizacion
		private List<Destinatario> destinatarios;
		
				//indentificador de la relación del elemento
		private final Object[] params;

		

		/**
		 * El constructor recoge los parametros necesarios.
		 * 
		 * @param actualizar: Elemento a Actualizar/borrar
		 * @param borrar : booleana que indica si el elemento a de ser borrado
		 */
		protected ActualizadorThread(final Object actualizar, final boolean borrar, final Object... params) {
			this.actualizar = actualizar;
			this.borrar = borrar;
			this.params = params;
			final DestinatarioDelegate destDelegate = DelegateUtil.getDestinatarioDelegate();
			try {
				destinatarios = destDelegate.listarDestinatarios();
			} catch (final DelegateException e) {
				e.printStackTrace();
				destinatarios = Collections.emptyList();
			}
		}
		
		@Override
		public void run() {
			//Miro de que tipo es el objeto a actualizar e lanzo el metodo oportuno
			if (actualizar instanceof Ficha) {
				final Ficha ficha = (Ficha) actualizar;
				if (borrar) {
					borrarFicha(ficha);
				} else {
					actualizarFicha(ficha);
				}
			} else if (actualizar instanceof ProcedimientoLocal) {
				final ProcedimientoLocal proc = (ProcedimientoLocal) actualizar;
				if (borrar) {
					borrarProcedimiento(proc);
				} else {
					actualizarProcedimiento(proc);
				}
			} else if (actualizar instanceof UnidadAdministrativa) {
				final UnidadAdministrativa unidad = (UnidadAdministrativa) actualizar;
				if (borrar) {
					borrarUnidadAdministrativa(unidad);
				} else {
					actualizarUnidadAdministrativa(unidad);
				}
			} else if (actualizar instanceof FichaUATransferible) {
				final FichaUATransferible fichaUA = (FichaUATransferible) actualizar;
				if (borrar) {
					borrarFichaUA(fichaUA);
				}
			} else if (actualizar instanceof Tramite) {
				final Tramite tramit = (Tramite) actualizar;
				if(params.length == 1){
					if (borrar) {
						borrarTramite(tramit);
					} else {
						actualizarTramite(tramit);
					}
				} else if(params.length == 0){
					if (borrar) {
						borrarTramit(tramit);
					} else {
						actualizarTramite(tramit);
					}
				}
			} else if (actualizar instanceof Edificio) {
				
				final Edificio edificio = (Edificio) actualizar;
				
				if(params.length == 1){
					final Long idUA = (Long) params[0];
					if (borrar) {
						borrarEdificioUA(edificio,idUA);
					} else {
						actualizarEdificioUA(edificio,idUA);
					}
				}
				else if(params.length == 0){
					if (borrar) {
						borrarEdificio(edificio);
					} else {
						actualizarEdificio(edificio);
					}
				}
			} else if (actualizar instanceof Normativa) {

				final Normativa normativa = (Normativa) actualizar;
				if(params.length == 1){
					final Long idProc = (Long) params[0];
					if (borrar) {
						borrarNormativaProcedimiento(normativa,idProc);
					} else {
						actualizarNormativaProcedimiento(normativa,idProc);
					}
				}
				else if(params.length == 0){
					if (borrar) {
						borrarNormativa(normativa);
					} else {
						actualizarNormativa(normativa);
					}
				}
			} else if (actualizar instanceof Documento) {
				final Documento documento = (Documento) actualizar;
				if(params.length == 1){
					final Long idProc = (Long) params[0];
					if (borrar) {
						borrarDocumentoProcedimiento(documento,idProc);
					} else {
						actualizarDocumentoProcedimiento(documento,idProc);
					}
				}
			}
		}

		
		
		
		/**
		 * Actualiza una {@link Ficha}
		 * @param ficha
		 */
		private void actualizarFicha(final Ficha ficha){
			
			log.debug("Actualizando una ficha");
			
			//La transformo en transferible
			final FichaTransferible fichaT = FichaTransferible.generar(ficha);
			fichaT.setFichasUA(FichaUATransferible.generar(ficha.getFichasua()));
			
			if(fichaT.getResponsable() == null || fichaT.getResponsable().trim().length()<= 0){
				String responsables = obtenerResponsableHistorico(ficha.getId(),"ficha");
				if (responsables!=null && responsables.length()>0)fichaT.setResponsable(responsables);
			}
			
			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try {
					if(calActualizar(destinatario, ficha)) {
						log.debug("Al Destinatario: "+destinatario.getNombre());
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarFicha(fichaT);
		        	}
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(ficha, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Actualiza un {@link ProcedimientoLocal}
		 * @param proc
		 */
		private void actualizarProcedimiento(final ProcedimientoLocal proc){
			log.debug("Actualizando un Procedimiento");
			
			//La transformo en transferible
			ProcedimientoTransferible procT =null;
			if(0<destinatarios.size()) {
				procT = ProcedimientoTransferible.generar(proc);
				if(procT.getResponsable() == null || procT.getResponsable().trim().length()<= 0){
					String responsables = obtenerResponsableHistorico(proc.getId(),"procedimiento");
					if (responsables!=null && responsables.length()>0)procT.setResponsable(responsables);
				}
			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
						if(calActualizar(destinatario, proc)) {
					log.debug("Al Destinatario: "+destinatario.getNombre());
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarProcedimiento(procT);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(proc, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		}
		
		
		
		/**
		 * Actualiza un {@link ProcedimientoLocal}
		 * @param proc
		 * @throws ActualizadorException 
		 */
		private void actualizarTramite(final Tramite tramit) {
			log.debug("Actualizando un TramitePMA");
			
			//La transformo en transferible
			final TramiteTransferible tramT = TramiteTransferible.generar(tramit);
			String value = System.getProperty("es.caib.rolsac.oficina");
			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
		        	
			        if ((value == null) || value.equals("N")) {
			        	//SOLO DE PRUEBAS // QUITAR!!!!
							final ActualizacionServicio actualizacion = new ActualizacionServicio(
									destinatario.getEndpoint(), destinatario.getIdRemoto());
							actualizacion.actualizarTramite(tramT);
			        	}
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(tramit, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
	
		/**
		 * Actualiza una {@link UnidadAdministrativa}
		 * @param unidad
		 */
		private void actualizarUnidadAdministrativa(
				final UnidadAdministrativa unidad){
			log.debug("Actualizando una Unidad");
			
			//La transformo en transferible
			final UnidadAdministrativaTransferible uaT = UnidadAdministrativaTransferible.generar(unidad);
			
			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
					if(calActualizar(destinatario, unidad)) {
					log.debug("Al Destinatario: "+destinatario.getNombre());
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarUnidadAdministrativa(uaT);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(unidad, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Actualiza un {@link Edificio}
		 * @param edif
		 */
		private void actualizarEdificio(final Edificio edif){
			log.debug("Actualizando un Edificio");
			
			//La transformo en transferible
			final EdificioTransferible edifT = EdificioTransferible.generar(edif);
			
			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
					log.debug("Al Destinatario: "+destinatario.getNombre());
			        if (calActualizar(destinatario, edif)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarEdificio(edifT);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(edif, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Actualiza  {@link Unidad Administrativa @link Edificio}
		 * @param edif
		 * @param id Unidad Administrativa
		 */
		private void actualizarEdificioUA(final Edificio edif, final Long idUA){
			log.debug("Actualizando la UA de un Edificio");
			
			//La transformo en transferible
			final EdificioTransferible edifT = EdificioTransferible.generar(edif);
			
			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
					log.debug("Al Destinatario: "+destinatario.getNombre());
			        if (calActualizar(destinatario, edif)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarEdificioUA(edifT,idUA);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(edif, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Borra una {@link Ficha}
		 * @param ficha
		 */
		private void borrarFicha(final Ficha ficha){
			for (final Destinatario destinatario : destinatarios) {
				try{
					if(calActualizar(destinatario, ficha)) {
						log.debug("Al Destinatario: "+destinatario.getNombre());
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarFicha(ficha.getId());
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(ficha, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Borra una {@link FichaUA} a traves de una {@link FichaUATransferible}
		 * @param fichaUA
		 */
		private void borrarFichaUA(final FichaUATransferible fichaUA){
			final long idFicha = fichaUA.getIdFicha();
			final long idUA = fichaUA.getIdUnidadAdministrativa();
			final String codEs = fichaUA.getCodigoEstandarSeccion();
			for (final Destinatario destinatario : destinatarios) {
				try{
					if(calActualizar(destinatario, fichaUA)) {
						log.debug("Al Destinatario: "+destinatario.getNombre());
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
							actualizacion.borrarFichaUA(idFicha, idUA, codEs);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(fichaUA, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Borra un {@link ProcedimientoLocal}
		 * @param proc
		 */
		private void borrarProcedimiento(final ProcedimientoLocal proc){
			for (final Destinatario destinatario : destinatarios) {
				try{
					if(calActualizar(destinatario, proc)) {
						log.debug("Al Destinatario: "+destinatario.getNombre());	
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarProcedimiento(proc.getId());
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(proc, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
			/**
		 * Borra un {@link Edificio}
		 * @param edif
		 */
		private void borrarEdificio(final Edificio edif){
			for (final Destinatario destinatario : destinatarios) {
				try{
			        if (calActualizar(destinatario, edif)) {					
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarEdificio(edif.getId());
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(edif, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Borra un {@link Edificio}
		 * @param edif
		 */
		private void borrarTramite(final Tramite tram){
			String value = System.getProperty("es.caib.rolsac.oficina");
			for (final Destinatario destinatario : destinatarios) {
				try{
					if ((value == null) || value.equals("N")) {		
				        	log.debug("Actualizo para el destinario : "+destinatario.getIdRemoto());
				        	log.debug("Actualizo para end point : "+destinatario.getEndpoint());
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarTramite(tram.getId());
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(tram, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Borra un {@link Edificio}
		 * @param edif
		 */
		private void borrarEdificioUA(final Edificio edif, final Long idUA){
			for (final Destinatario destinatario : destinatarios) {
				try{
			        if (calActualizar(destinatario, edif)) {					
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarEdificioUA(edif.getId(),idUA);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(edif, borrar, destinatario, e);
					log.error(e);
				}
			}
		}
		
		/**
		 * Borra una {@link UnidadAdministrativa}
		 * @param unidad
		 */
		private void borrarUnidadAdministrativa(
				final UnidadAdministrativa unidad){
			for (final Destinatario destinatario : destinatarios) {
				try{
					if(calActualizar(destinatario, unidad)) {
						log.debug("Al Destinatario: "+destinatario.getNombre());
					final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarUnidadAdministrativa(unidad.getId());
					}
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo
					ReportarFallo.reportar(unidad, borrar, destinatario, e);
					log.error(e);
				}
			}
		}

		//TODO
		private void borrarTramit(Tramite tramit) {
		
		}
		
		/**
		 * Obtiene los mails de las ultimas personas que han modificado un procedimiento o ficha
		 * @param id
		 * @param tipo
		 */
	    private String obtenerResponsableHistorico(Long id,String tipo){
	    	String numResponsables =System.getProperty("es.caib.rolsac.numResponsables");
	    	StringBuffer responsables = new StringBuffer();
	    	if(numResponsables!=null){
	    		int contador = Integer.parseInt(numResponsables);
	    		AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
		    	UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
		    	ArrayList<String> aResponsables=new ArrayList<String>();
		    	List listaAuditorias=null;
					try {
						
						if(tipo.equals("procedimiento")){
							listaAuditorias = auditoriaDelegate.listarAuditoriasProcedimientoPMA(id);
						}
						else if(tipo.equals("ficha")){
							listaAuditorias = auditoriaDelegate.listarAuditoriasFichaPMA(id);
						}
						
						for (Object object : listaAuditorias) {
							Auditoria auditoria = (Auditoria) object;
							if(aResponsables.size()< contador){
								if(auditoria.getUsuario()!=null){
									Usuario usuario=usuarioDelegate.obtenerUsuariobyUsernamePMA(auditoria.getUsuario());
									if(usuario!=null && usuario.getEmail()!=null){
										if(!aResponsables.contains(usuario.getEmail())){
											aResponsables.add(usuario.getEmail());
										}
									}
								}
							}
							else break;
						}
					} catch (DelegateException e) {
						log.error(e);
					}
					
					for (int i = 0; i < aResponsables.size(); i++) {
						if(i==aResponsables.size()-1){
							responsables.append(aResponsables.get(i));
						}
						else{
							responsables.append(aResponsables.get(i)+",");
						}
					}
	    	}
	    	
	    	return responsables.toString();
	    }


         /**
		 * Borra un {@link Normativa}
		 * @param norm
		 */
		private void borrarNormativa(final Normativa norm){
			for (final Destinatario destinatario : destinatarios) {
				try{
			        if (calActualizar(destinatario, norm)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarNormativa(norm.getId());
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(norm, borrar, destinatario, e);
					log.error(e);
				}
			}
		}



        /**
		 * Actualiza un {@link Normativa}
		 * @param norm
		 */
		private void actualizarNormativa(final Normativa norm){
			log.debug("Actualizando una Normativa");

			//La transformo en transferible
			final NormativaTransferible normT = NormativaTransferible.generar(norm);

			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
					log.debug("Al Destinatario: "+destinatario.getNombre());
			        if (calActualizar(destinatario, norm)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarNormativa(normT);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(norm, borrar, destinatario, e);
					log.error(e);
				}
			}
		}

        /**
		 * Borra un la normativa de un procedimiento
		 * @param norm
		 */
		private void borrarNormativaProcedimiento(final Normativa norm, final Long idProc){
			for (final Destinatario destinatario : destinatarios) {
				try{
			        if (calActualizar(destinatario, norm)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarNormativaProcedimiento(norm.getId(),idProc);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(norm, borrar, destinatario, e);
					log.error(e);
				}
			}
		}


		/**
		 * Actualiza  {@link Normativa @link Procedimiento}
		 * @param norm
		 * @param idProc
		 */
		private void actualizarNormativaProcedimiento(final Normativa norm, final Long idProc){
			log.debug("Actualizando el proc de una Normativa");

			//La transformo en transferible
			final NormativaTransferible normT = NormativaTransferible.generar(norm);

			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
					log.debug("Al Destinatario: "+destinatario.getNombre());
			        if (calActualizar(destinatario, norm)) {
                        log.debug("3");
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarNormativaProcedimiento(normT,idProc);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(norm, borrar, destinatario, e);
					log.error(e);
				}
			}
		}

        /**
		 * Borra el documento de un procedimiento
		 * @param doc
		 */
		private void borrarDocumentoProcedimiento(final Documento doc, final Long idProc){
			for (final Destinatario destinatario : destinatarios) {
				try{
			        if (calActualizar(destinatario, doc)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarDocumentoProcedimiento(doc.getId());
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(doc, borrar, destinatario, e);
					log.error(e);
				}
			}
		}


		/**
		 * Actualiza  {@link Documento @link Procedimiento}
		 * @param doc
		 * @param idProc
		 */
		private void actualizarDocumentoProcedimiento(final Documento doc, final Long idProc){
			log.debug("Actualizando el documento de un procedimiento");

			//La transformo en transferible
			final DocumentoTransferible docT = DocumentoTransferible.generar(doc);

			//Y voy destinatario a destinatario mandando la actualizacion
			for (final Destinatario destinatario : destinatarios) {
				try{
					log.debug("Al Destinatario: "+destinatario.getNombre());
			        if (calActualizar(destinatario, doc)) {
						final ActualizacionServicio actualizacion = new ActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.actualizarDocumentoProcedimiento(docT,idProc);
			        }
				} catch (WSInvocatorException e) {
					//Si falla mando un Email informando del fallo al destinatario
					ReportarFallo.reportar(doc, borrar, destinatario, e);
					log.error(e);
				}
			}
		}



		
	}

}
