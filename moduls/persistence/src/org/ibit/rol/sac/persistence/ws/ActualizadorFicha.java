package org.ibit.rol.sac.persistence.ws;


import java.util.ArrayList;
import java.util.List;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.FichaUATransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import org.ibit.rol.sac.model.Auditoria;

import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;


public class ActualizadorFicha extends ActualizadorBase {

	public ActualizadorFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	
	
	
	@Override
	ActuacionTransferible generarActuacionTransferible() {
		final FichaTransferible fichaT = FichaTransferible.generar(ficha);
		fichaT.setFichasUA(FichaUATransferible.generar(ficha.getFichasua()));
		if(fichaT.getResponsable() == null || fichaT.getResponsable().trim().length()<= 0){
			String responsables = obtenerResponsableHistorico(ficha.getId());
			if (responsables!=null && responsables.length()>0)fichaT.setResponsable(responsables);
			log.info("responsables="+responsables);
		}
		return fichaT;
	}
	
	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarFicha((FichaTransferible) elemTransf);
		
	}
	
	
	@Override
	Object getActuacion() {
		return ficha;
	}
	

	@Override
	void borrar() {
		for (final Destinatario destinatario : destinatarios) {
			try{
				if(calActualizarElDestinatari()) {
					log.info("Al Destinatario: "+destinatario.getNombre());
					final ActualizacionServicio actualizacion = ActualizacionServicio.createActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarFicha(ficha.getId());
		        }
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(ficha, true, destinatario, e);
				log.error(e);
			}
		}


	}

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
						
							listaAuditorias = auditoriaDelegate.listarAuditoriasFichaPMA(id);
						
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

	  
	  Ficha ficha;

	
}
