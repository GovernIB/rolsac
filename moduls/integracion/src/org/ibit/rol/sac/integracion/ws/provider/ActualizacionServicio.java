package org.ibit.rol.sac.integracion.ws.provider;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.*;
import org.ibit.rol.sac.persistence.delegate.*;


public class ActualizacionServicio{
	
	protected static Log log = LogFactory.getLog(ActualizacionServicio.class);

	public Boolean actualizarFicha(final String idRemoto,
			FichaTransferible fichaT) throws DelegateException {

		
		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto) && (fichaT.getCodigoEstandarMaterias() != null && fichaT
				.getCodigoEstandarMaterias().length > 0)
				|| (fichaT.getCodigoEstandarHV() != null && fichaT
						.getCodigoEstandarHV().length > 0)) {
			
			log.debug("Recibida ficha para actualizar");
			
			FichaRemotaDelegate delegate = DelegateUtil
					.getFichaRemotaDelegate();
			
			FichaRemota fichaRemota = delegate.obtenerFichaRemota(idRemoto,fichaT
					.getId());

			if (fichaRemota == null){
				fichaRemota = new FichaRemota();
				log.debug("La ficha no Existia");
			}
			fichaRemota.rellenar(fichaT);
			delegate.grabarFichaRemota(idRemoto,fichaRemota, fichaT.getFichasUA(), fichaT
					.getCodigoEstandarMaterias(), fichaT.getCodigoEstandarHV());
		}

		return null;
	}

	public Boolean actualizarUnidadAdministrativa(final String idRemoto,
			UnidadAdministrativaTransferible uaT)
			throws DelegateException {
		if(!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)){
			log.debug("Recibida UA para actualizar");
			
			UARemotaDelegate delegate = DelegateUtil.getUARemotaDelegate();

            AdministracionRemota adminRemota = delegate.obtenerAdministracionRemota(idRemoto);
			UnidadAdministrativaRemota unidad = delegate.obtenerUARemota(idRemoto,uaT
					.getId());

            if(uaT.getNivel()<=adminRemota.getProfundidad()){
                Long idPadre= null;
                if(uaT.getIdPadre()!=null)
                    idPadre = delegate.obtenerUARemota(idRemoto,uaT.getIdPadre()).getId();

                if (unidad == null){
                    if(idPadre==null){
                        return null;
                    }

                    unidad = new UnidadAdministrativaRemota();
                }
                unidad.rellenar(uaT);
                delegate.grabarUARemota(idRemoto,unidad, idPadre, uaT.getCodigoEstandarTratamiento() ,uaT.getCodigoEstandarMaterias());
            }
		}
		return null;
	}

	public Boolean actualizarProcedimiento(final String idRemoto,
			ProcedimientoTransferible procT)
			throws DelegateException {

		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto) && (procT.getCodigoEstandarMaterias() != null && procT
				.getCodigoEstandarMaterias().length > 0)
				|| (procT.getCodigoEstandarHV() != null && procT
						.getCodigoEstandarHV().length > 0)) {
			
			log.debug("Recibido Procedimiento para actualizar");
			
			UARemotaDelegate auDelegate = DelegateUtil
			.getUARemotaDelegate();
			
			UnidadAdministrativaRemota unidad = auDelegate.obtenerUARemota(idRemoto,procT.getIdUnidadAdministrativa());
			if(unidad==null)
				return false;
			
			ProcedimientoRemotoDelegate delegate = DelegateUtil
					.getProcedimientoRemotoDelegate();

			ProcedimientoRemoto proc = delegate
					.obtenerProcedimientoRemoto(idRemoto,procT.getId());
			if (proc == null){
				proc = new ProcedimientoRemoto();
			}
			proc.rellenear(procT);
			proc.setUnidadAdministrativa(unidad);
			
        	if(procT.getIdOrganResolutori()!=null){
        		proc.setOrganResolutori(auDelegate.obtenerUARemota(idRemoto, procT.getIdOrganResolutori()));	
            }
        	//INICIACION
            boolean actualizarIniciacion=false;
        	if(procT.getCodigoEstandarIniciacion()!=null){ 
            	Iniciacion iniciacion = delegate.obtenerIniciacion(procT.getCodigoEstandarIniciacion());
            	if (iniciacion != null) {
            		proc.setIniciacion(iniciacion);
            		actualizarIniciacion=true;
        		} 
            }
        	if(!actualizarIniciacion){
        		proc.setIniciacion(null);
        	}
			delegate.grabarProcedimientoRemoto(idRemoto, proc, procT
					.getCodigoEstandarMaterias(), procT.getCodigoEstandarHV());
		}

		return null;
	}
	
	public Boolean actualizarEdificio(final String idRemoto,
			EdificioTransferible edifT) throws DelegateException {

		
		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)) {
			
			log.debug("Recibido edificio para actualizar");
			
			UARemotaDelegate delegate = DelegateUtil.getUARemotaDelegate();

			EdificioRemoto edificioRemoto = delegate.obtenerEdificioRemoto(idRemoto,edifT.getId());

			if (edificioRemoto == null){
				edificioRemoto = new EdificioRemoto();
			}
			edificioRemoto.rellenar(edifT);
			delegate.grabarEdificioRemoto(edificioRemoto);
		}

		return null;
	}
	
	
	public Boolean actualizarTramite(final String idRemoto,
			TramiteTransferible tramT) throws DelegateException {

		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)) {
			
			log.debug("Recibido tramite para actualizar");
			
			TramiteRemotoDelegate delegate = DelegateUtil.getTramiteRemotoDelegate();

			TramiteRemoto tramiteRemoto = delegate.obtenerTramiteRemoto(idRemoto,tramT.getId());
			
			if (tramiteRemoto == null){
				UARemotaDelegate uaRemotaDelegate = DelegateUtil.getUARemotaDelegate();
				ProcedimientoRemotoDelegate procedimientoRemotoDelegate = DelegateUtil.getProcedimientoRemotoDelegate();

				AdministracionRemota administracionRemota = uaRemotaDelegate.obtenerAdministracionRemota(idRemoto);
				ProcedimientoRemoto procedimientoRemoto =procedimientoRemotoDelegate.obtenerProcedimientoRemoto(idRemoto,tramT.getIdProcedimiento());
				tramiteRemoto = new TramiteRemoto();
				tramiteRemoto.setAdministracionRemota(administracionRemota);
				tramiteRemoto.rellenar(tramT);
				delegate.grabarTramiteRemoto(tramiteRemoto, procedimientoRemoto.getId(),tramT.getIdOrganCompetent());
			}
			else{
				tramiteRemoto.rellenar(tramT);
				delegate.grabarTramiteRemoto(tramiteRemoto,tramT.getIdOrganCompetent());
			}

		}

		return null;
	}
	
	public Boolean actualizarEdificioUA(final String idRemoto,
			EdificioTransferible edifT,final Long idUA) throws DelegateException {
		
		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)) {

			
			UARemotaDelegate delegate = DelegateUtil.getUARemotaDelegate();


			EdificioRemoto edificioRemoto = delegate.obtenerEdificioRemoto(idRemoto,edifT.getId());
			UnidadAdministrativaRemota unidadAdministrativaRemota = delegate.obtenerUARemota(idRemoto,idUA);
			
			if(unidadAdministrativaRemota!=null){

				if (edificioRemoto == null){
					AdministracionRemota administracionRemota = delegate.obtenerAdministracionRemota(idRemoto);
					edificioRemoto = new EdificioRemoto();
					edificioRemoto.setAdministracionRemota(administracionRemota);
                }
				edificioRemoto.rellenar(edifT);
				edificioRemoto.getUnidadesAdministrativas().add(unidadAdministrativaRemota);
				delegate.grabarEdificioRemoto(edificioRemoto);	
			}
		}
		return null;
	}


    public Boolean actualizarNormativa(final String idRemoto,
			NormativaTransferible normT) throws DelegateException {


		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)) {

			log.debug("Recibido normativa para actualizar");

			NormativaExternaRemotaDelegate delegate = DelegateUtil.getNormativaExternaRemotaDelegate();
			NormativaExternaRemota normExtRemota = delegate.obtenerNormativaExternaRemota(idRemoto,normT.getId());

			if (normExtRemota == null){
				normExtRemota = new NormativaExternaRemota();
			}
			normExtRemota.rellenar(normT);
            delegate.grabarNormativaExternaRemota(normExtRemota);
		}

		return null;
	}

     public Boolean actualizarNormativaProcedimiento(final String idRemoto,
			NormativaTransferible normT,final Long idProc) throws DelegateException {

		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)) {

			log.debug("Recibido Normativa para actualizar");

			NormativaExternaRemotaDelegate normDelegate = DelegateUtil.getNormativaExternaRemotaDelegate();
			ProcedimientoRemotoDelegate procDelegate = DelegateUtil.getProcedimientoRemotoDelegate();
            UARemotaDelegate uaRemDelegate = DelegateUtil.getUARemotaDelegate();

			NormativaExternaRemota normRemota = normDelegate.obtenerNormativaExternaRemota(idRemoto,normT.getId());
			ProcedimientoRemoto procRemoto = procDelegate.obtenerProcedimientoRemoto(idRemoto,idProc);

			if(procRemoto!=null){

				if (normRemota == null){
					AdministracionRemota administracionRemota = uaRemDelegate.obtenerAdministracionRemota(idRemoto);
					normRemota = new NormativaExternaRemota();
					normRemota.setAdministracionRemota(administracionRemota);
				}
				normRemota.rellenar(normT);
				normDelegate.grabarNormativaExternaRemota(normRemota);
                normDelegate.anyadirProcedimiento(procRemoto.getId(),normRemota.getId());
			}

		}

		return null;
	}

      public Boolean actualizarDocumentoProcedimiento(final String idRemoto,
			DocumentoTransferible docT,final Long idProc) throws DelegateException {

		if (!DelegateUtil.getAdministracionRemotaDelegate().isEmpty(idRemoto)) {

			log.debug("Recibido Documento para actualizar");


			ProcedimientoRemotoDelegate procDelegate = DelegateUtil.getProcedimientoRemotoDelegate();
            UARemotaDelegate uaRemDelegate = DelegateUtil.getUARemotaDelegate();

			
			ProcedimientoRemoto procRemoto = procDelegate.obtenerProcedimientoRemoto(idRemoto,idProc);
			DocumentoRemoto documentoRemoto = procDelegate.obtenerDocumentoRemoto(idRemoto,docT.getId());

			if(procRemoto!=null){

				if (documentoRemoto == null){
					AdministracionRemota administracionRemota = uaRemDelegate.obtenerAdministracionRemota(idRemoto);
					documentoRemoto = new DocumentoRemoto();
					documentoRemoto.setAdministracionRemota(administracionRemota);
				}
				documentoRemoto.rellenar(docT);
				procDelegate.grabarDocumentoRemoto(documentoRemoto, procRemoto.getId(), null);

			}

		}

		return null;
	}
	

	public void borrarFicha(final String idRemoto,
			Long idExt) throws DelegateException {
		log.debug("Borrando Ficha");

		DelegateUtil.getFichaRemotaDelegate().borrarFichaRemota(idRemoto,idExt);

	}

	public void borrarProcedimiento(final String idRemoto,
			Long idExt) throws DelegateException {

		log.debug("Borrando Procedimiento");

		DelegateUtil.getProcedimientoRemotoDelegate().borrarProcedimientoRemoto(idRemoto,idExt);

	}

	public void borrarUnidadAdministrativa(final String idRemoto,
			Long idExt) throws DelegateException {

		log.debug("Borrando Unidad");

		DelegateUtil.getUARemotaDelegate().borrarUARemota(idRemoto,idExt);

	}

	public void borrarFichaUA(final String idRemoto,
			Long idFicha, Long idUA, String codEst) throws DelegateException {
		log.debug("Borrando FichaUA");
		DelegateUtil.getFichaRemotaDelegate().borrarFichaUA(idRemoto,idFicha, idUA, codEst);
	}
	
	public void borrarEdificio(final String idRemoto,Long idExt) throws DelegateException {
		log.debug("Borrando Edificio");
		DelegateUtil.getUARemotaDelegate().borrarEdificioRemoto(idRemoto,idExt);
	}
	
	public void borrarEdificioUA(final String idRemoto, Long idExt, Long idUA) throws DelegateException {
		log.debug("Borrando EdificioUA");
		DelegateUtil.getUARemotaDelegate().eliminarUnidad(idRemoto,idExt,idUA);
	}
	
	public void borrarTramite(final String idRemoto,Long idExt) throws DelegateException {
		log.debug("Borrando Edificio");
		DelegateUtil.getTramiteRemotoDelegate().borrarTramiteRemoto(idRemoto,idExt);
	}
	

	public void borrarNormativa(final String idRemoto,Long idExt) throws DelegateException {
		log.debug("Borrando Normativa");
		DelegateUtil.getNormativaExternaRemotaDelegate().borrarNormativaRemota(idRemoto,idExt);
	}

	public void borrarNormativaProcedimiento(final String idRemoto, Long idExt, Long idProc) throws DelegateException {
		log.debug("Borrando NormativaProc");
		DelegateUtil.getNormativaExternaRemotaDelegate().eliminarProcNormativa(idRemoto,idExt,idProc);
	}


	public void borrarDocumentoProcedimiento(final String idRemoto, Long idExt) throws DelegateException {
		log.debug("Borrando DocumentoProc");
		DelegateUtil.getProcedimientoRemotoDelegate().borrarDocumentoRemoto(idRemoto,idExt);
	}

	

}
