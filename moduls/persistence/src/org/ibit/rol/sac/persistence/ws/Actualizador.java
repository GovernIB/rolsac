package org.ibit.rol.sac.persistence.ws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.Tramite.Operativa;
import org.ibit.rol.sac.model.ws.*;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.portal.PortalDestinatario;
import org.ibit.rol.sac.persistence.portal.PortalDestinatarioFactory;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import es.caib.persistence.vuds.VentanillaUnica;

/**
 * Transefiere a todos los destinatarios los diferentes Objectos Actualizables.
 * Los elementos actualizables son {@link Ficha}, {@link UnidadAdministrativa},
 * {@link ProcedimientoLocal} y {@link FichaUATransferible}.
 * 
 * Modificacion de VRS(2008/02/01): Se añade System.getProperty("es.indra.caib.rolsac.oficina")
 * para controlar que si estas en las oficinas de indra no se lance el webservices.
 * 
 * @author arodrigo
 * 
 *
 */
public final class Actualizador {
	
	protected static Log log = LogFactory.getLog(Actualizador.class);
	public static  Log  getLog() { return log;}
	public static void setLog(org.apache.commons.logging.Log log) {Actualizador.log=log;}
	

	//u92770[enric] Objectes afegits com beans per poder fer unit testing
	static DestinatarioDelegate destDelegate;  
	static ActualizadorThread actualizador;
	@Deprecated
	static VentanillaUnica vuds;
	
	//u92770[enric] getters and setters pels tests unitaris
	public static DestinatarioDelegate Delegate() {return destDelegate;}
	public static void setDestDelegate(DestinatarioDelegate destDelegate) {Actualizador.destDelegate = destDelegate;}
	public static ActualizadorThread getThreadActualizador() {return actualizador;}
	public static void setActualizador(ActualizadorThread actualizador) {Actualizador.actualizador = actualizador;}
	
	@Deprecated
	public static VentanillaUnica getVuds() {return vuds;}
	@Deprecated
	public static void setVuds(VentanillaUnica vuds) {Actualizador.vuds = vuds;}

	static ActualizadorBase actualizadorBase; 
	
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
	public static void actualizar(final Object actuacion, final Object... params) {
		actualizadorBase = ActualizadorFactory.creaActualizador(actuacion, params);
		if(!(actuacion instanceof Remoto)){
			final ActualizadorThread act = new Actualizador().new ActualizadorThread(
					actuacion, false, params);
			actualizador=act;
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
	public static void borrar(final Object actuacion, final Object... params) {
		actualizadorBase = ActualizadorFactory.creaActualizador(actuacion, params);
		if(!(actuacion instanceof Remoto)){
			final ActualizadorThread act = new Actualizador().new ActualizadorThread(
					actuacion, true, params);
			actualizador=act;
			act.start();
		}
	}
	
	public static void borrar(final Object actualizar) {
		Actualizador.borrar(actualizar, new Object[0]);
	}
	

	public boolean calActualizarElDestinatari(Destinatario dest, Object elem) {
 		if (estemOficinaIndra()) return false;
		if (estemModificantUnTramiteVuds(dest,elem)) return true;
		if (!esTramite(elem)) return true;
		return false;
	}
	
	
	private boolean esTramite(Object elem) {
		return (elem instanceof Tramite);
	}

	
	private boolean estemModificantUnTramiteVuds(Destinatario dest, Object elem) {
		if(null!=dest.getNombre() && "VUDS".equals(dest.getNombre()) && elem instanceof Tramite) {
			Tramite t=(Tramite)elem;
			if("1".equals(t.getProcedimiento().getVentanillaUnica()) && Operativa.MODIFICA == t.getOperativa()) 
				return true;

		}
		return false;
	}
		
	boolean estemOficinaIndra() {
		String value = System.getProperty("es.indra.caib.rolsac.oficina");
		return (value != null) && value.equals("S");
	}
	
	
	/**
	 * Proceso encargado de transferir el objeto a actualizar/borrar a traves
	 * de WebService
	 * 
	 * @author arodrigo
	 * @author enricjaen (refactorizacion)
	 *
	 */
	public class ActualizadorThread extends Thread {
		
		//Elemento a Actualizar/borrar
		private final Object actualizar;
		
		//booleana que indica si el elemento a de ser borrado
		private final boolean borrar;
		
		//Destinatarios a los que se les enviara la actualizacion
		private List<Destinatario> destinatarios;

				//indentificador de la relación del elemento
		private final Object[] params;

		private List<String> destinatarisOK = new ArrayList<String>();

		

		public List<String> getDestinatarisOK() {
			return destinatarisOK;
		}

		/**
		 * El constructor recoge los parametros necesarios.
		 * 
		 * @param actualizar: Elemento a Actualizar/borrar
		 * @param borrar : booleana que indica si el elemento a de ser borrado
		 */
		private ActualizadorThread(final Object actualizar, final boolean borrar, final Object... params) {
			this.actualizar = actualizar;
			this.borrar = borrar;
			this.params = params;
			final DestinatarioDelegate destDelegate = null==Actualizador.destDelegate?
				DelegateUtil.getDestinatarioDelegate() : Actualizador.destDelegate;
			try {
				destinatarios = destDelegate.listarDestinatarios();
				
			} catch (final DelegateException e) {
				e.printStackTrace();
				destinatarios = Collections.emptyList();
			}
		}
		
		@Override
		public void run() {
			
			for (final Destinatario destinatario : destinatarios) {
				try{
					if (calActualizarElDestinatari()) {
						PortalDestinatario portal = PortalDestinatarioFactory.creaPortalDestinatario(destinatario);
						if(borrar)
							portal.borrarActuacion(actualizadorBase);
						else
							portal.actualizarActuacion(actualizadorBase);
					}
				} catch (WSInvocatorException e) {
					enviarEmailFalloDestinatario(destinatario, e);			
				}
			}	
		}
		
		public boolean calActualizarElDestinatari() {
			if (estemOficinaIndra()) return false;
			return true;
		}
		
		boolean estemOficinaIndra() {
			String value = System.getProperty("es.indra.caib.rolsac.oficina");
			return (value != null) && value.equals("S");
		}

		private void enviarEmailFalloDestinatario(
				final Destinatario destinatario, WSInvocatorException e) {
			ReportarFallo.reportar(actualizadorBase.getActuacion(), false, destinatario, e);
			log.error("error actualizando -> "+ e.getMessage());
		}
		
		}
		

}
