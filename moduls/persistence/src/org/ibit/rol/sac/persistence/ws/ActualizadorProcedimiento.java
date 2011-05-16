package org.ibit.rol.sac.persistence.ws;

import java.util.ArrayList;
import java.util.List;

import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorProcedimiento extends ActualizadorBase {

	public ActualizadorProcedimiento(ProcedimientoLocal procedimiento) {
		this.proc = procedimiento;
	}

	
	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {

		log.info("actualizando procedimiento");
		actualizacionSvc.actualizarProcedimiento((ProcedimientoTransferible) elemTransf);
	}
	
	@Override
	public
	ActuacionTransferible generarActuacionTransferible() {
		ProcedimientoTransferible procT = ProcedimientoTransferible.generar(proc);
		if(procT.getResponsable() == null || procT.getResponsable().trim().length()<= 0){
			String responsables = obtenerResponsableHistorico(proc.getId());
			if (responsables!=null && responsables.length()>0)procT.setResponsable(responsables);
			log.info("responsables="+responsables);
		}
		return procT;
	}
	
	@Override
	Object getActuacion() {
		return proc;
	}
	

	/**
	 * Obtiene los mails de las ultimas personas que han modificado un procedimiento o ficha
	 * @param id
	 * @param tipo
	 * TODO es podria refactoritzar aquest metode per treure el condicional del tipo.
	 * 	1. afegir un metode Ficha.listarAuditorias()
	 *  2. canviar la signatura de obtenerResponsableHistorico(List listaAuditorias)
	 *  3. afegir la crida en actualizarFicha() a listarAuditorias, i cridar al
	 *  nou  obtenerResponsableHistorico.
	 */
    private String obtenerResponsableHistorico(Long id){
    	String numResponsables =System.getProperty("es.caib.rolsac.numResponsables");
    	StringBuffer responsables = new StringBuffer();
    	if(numResponsables!=null){
    		int contador = Integer.parseInt(numResponsables);
    		AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
	    	UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
	    	ArrayList<String> aResponsables=new ArrayList<String>();
	    	List listaAuditorias=null;
				try {
						listaAuditorias = auditoriaDelegate.listarAuditoriasProcedimientoPMA(id);
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
	
    
    @Override
    public void borrarActuacion(ActualizacionServicio actualizacionSvc)
    		throws WSInvocatorException {
    	actualizacionSvc.borrarProcedimiento(proc.getId());
    	
    }
    
	
	ProcedimientoLocal proc;
}
