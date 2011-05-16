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
	public
	ActuacionTransferible generarActuacionTransferible() {
		return DocumentoTransferible.generar(doc);
	}

	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarDocumentoProcedimiento((DocumentoTransferible) elemTransf,idProc);

	}

	@Override
	Object getActuacion() {
		return doc; 
	}

	
	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		actualizacionSvc.borrarDocumentoProcedimiento(doc.getId());
		
	}
	

	Documento doc;
	Long idProc;
}
