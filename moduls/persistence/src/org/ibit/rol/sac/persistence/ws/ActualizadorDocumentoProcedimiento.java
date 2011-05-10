package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.DocumentoTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorDocumentoProcedimiento extends ActualizadorBase {

	
	
	public ActualizadorDocumentoProcedimiento(Documento doc, Long idProc) {
		super();
		this.doc = doc;
		this.idProc = idProc;
	}
	

	@Override
	ActuacionTransferible generarActuacionTransferible() {
		return DocumentoTransferible.generar(doc);
	}

	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarDocumentoProcedimiento((DocumentoTransferible) elemTransf,idProc);

	}

	@Override
	Object getActuacion() {
		return doc; 
	}

	@Override
	void borrar() {
		for (final Destinatario destinatario : destinatarios) {
			try{
		        if (calActualizarElDestinatari()) {
					final ActualizacionServicio actualizacion = ActualizacionServicio.createActualizacionServicio(
						destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarDocumentoProcedimiento(doc.getId());
		        }
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(doc, true, destinatario, e);
				log.error(e);
			}
		}

	}

	

	Documento doc;
	Long idProc;
}
