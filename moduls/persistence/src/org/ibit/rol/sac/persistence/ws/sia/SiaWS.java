package org.ibit.rol.sac.persistence.ws.sia;

import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.WsSIAActualizarActuaciones_PortType;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.ERRORESERROR;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.enviaSIA.EnviaSIAACTUACIONESACTUACION;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.DESTINATARIOSDESTINATARIO;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.MATERIASMATERIA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.NORMATIVASNORMATIVA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ORGANISMORESPONSABLE;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACION;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE;

/**
 * Representación  Sia.
 * Envio SIA
 */
public class SiaWS {
	
	public static SiaResultado enviarSIA(Sia sia) throws Exception {
		
		SiaResultado siaResultado = new SiaResultado();
		String usuario = SiaUtils.getUsuarioEnvio();
		String password = SiaUtils.getPasswordEnvio();
		WsSIAActualizarActuaciones_PortType client = SiaClient.createClient(SiaUtils.getUrlEnvio());
		
		ParamSIAACTUACIONESACTUACION[] actuaciones = cargarDatosSia(sia);
		
		ParamSIA parameters = new ParamSIA(usuario, password, null, actuaciones);
		
		EnviaSIA resultado = client.actualizarSIA_v3_1(parameters);
		
		EnviaSIAACTUACIONESACTUACION[] res = resultado.getACTUACIONES();
		
		int correctos = 0;
		int incorrectos = 0;
		
		if(res != null){
			
			for (EnviaSIAACTUACIONESACTUACION envia : res) {
				if(envia.getCORRECTO().equals(SiaUtils.SI)){
					correctos++;
					siaResultado.setCodSIA(envia.getCODIGOACTUACION());
				} else {
					incorrectos++;
					ERRORESERROR[] arrayErrores = envia.getERRORES();
					for (ERRORESERROR error : arrayErrores) {
						siaResultado.setMensaje(error.getDESCERROR() + " "+ siaResultado.getMensaje());
					}
				}
				siaResultado.setEstadoSIA(envia.getOPERACION());
			}
		}
	
		siaResultado.setCorrectos(correctos);
		siaResultado.setIncorrectos(incorrectos);
		
		
		return siaResultado;
	}

	/**
	 * @param sia
	 * @return
	 */
	private static ParamSIAACTUACIONESACTUACION[] cargarDatosSia(Sia sia) {
		ParamSIAACTUACIONESACTUACION paramSia = new ParamSIAACTUACIONESACTUACION();
		
		paramSia.setCODIGOACTUACION(sia.getIdSIA());
		paramSia.setCODIGOORIGEN(sia.getIdProc());
		
		paramSia.setDENOMINACION(sia.getTitulo());
		paramSia.setDESCRIPCION(sia.getDescripcion());
		ORGANISMORESPONSABLE organismoResponsable = new ORGANISMORESPONSABLE();
		organismoResponsable.setCODORGANISMORESPONSABLEN1(sia.getIdCent());
		organismoResponsable.setCODORGANISMORESPONSABLEN2(sia.getUaGest());
		paramSia.setORGANISMORESPONSABLE(organismoResponsable);
		
		DESTINATARIOSDESTINATARIO[] destinatarios = new DESTINATARIOSDESTINATARIO[sia.getIdDest().length];		
		int i = 0;
		for (String pObj : sia.getIdDest()) {
			destinatarios[i]= new DESTINATARIOSDESTINATARIO(pObj);
			i++;
		}
		paramSia.setDESTINATARIOS(destinatarios);
		paramSia.setCODNIVELADMINISTRACIONELECTRONICA(sia.getNivAdm().toString());
		
		
		NORMATIVASNORMATIVA[] normativas = new NORMATIVASNORMATIVA[sia.getNormativas().size()];
		i=0;
		for (Normativa norm : sia.getNormativas()) {
			NORMATIVASNORMATIVA nor = new NORMATIVASNORMATIVA();
			nor.setCODRANGO(norm.getTipo().getTipoSia().toString());
			nor.setTITULO(norm.getTraduccionTitulo());
			normativas[i]= nor;
			i++;
		}
		paramSia.setNORMATIVAS(normativas);
		
		MATERIASMATERIA[] materias = new MATERIASMATERIA[sia.getMaterias().length];
		i = 0;
		for (String mat : sia.getMaterias()) {
			materias[i]  = new MATERIASMATERIA(mat);
			i++;
		}
		paramSia.setMATERIAS(materias);
		
		ParamSIAACTUACIONESACTUACIONFINVIA finVia = new ParamSIAACTUACIONESACTUACIONFINVIA(sia.getFiVia());
		paramSia.setFINVIA(finVia);
		
		ParamSIAACTUACIONESACTUACIONINTERNO interno = new ParamSIAACTUACIONESACTUACIONINTERNO();
		ParamSIAACTUACIONESACTUACIONESCOMUN comun = new ParamSIAACTUACIONESACTUACIONESCOMUN();
		
		if(sia.getTipologia() == SiaUtils.TIPOLOGIA_INTERNO_COMUN){
			interno.setBooleanoValue(SiaUtils.SI);
			comun.setBooleanoValue(SiaUtils.SI);
		}else if(sia.getTipologia() == SiaUtils.TIPOLOGIA_INTERNO_ESPECIFICO){
			interno.setBooleanoValue(SiaUtils.SI);
			comun.setBooleanoValue(SiaUtils.NO);
		}else if(sia.getTipologia() == SiaUtils.TIPOLOGIA_EXTERNO_COMUN){
			interno.setBooleanoValue(SiaUtils.NO);
			comun.setBooleanoValue(SiaUtils.SI);
		}else if(sia.getTipologia() == SiaUtils.TIPOLOGIA_EXTERNO_ESPECIFICO){
			interno.setBooleanoValue(SiaUtils.NO);
			comun.setBooleanoValue(SiaUtils.NO);
		}
		
		paramSia.setINTERNO(interno);
		paramSia.setESCOMUN(comun);
		
		ParamSIAACTUACIONESACTUACIONTIPOTRAMITE tipoTramite= new ParamSIAACTUACIONESACTUACIONTIPOTRAMITE();
		tipoTramite.setTIPOTRAMITEValue(sia.getTipoTramite());
		paramSia.setTIPOTRAMITE(tipoTramite);
		
		paramSia.setUNIDADGESTORATRAMITE(sia.getUaGest());
		
		paramSia.setENLACEWEB(sia.getEnlaceWeb());
		
		ParamSIAACTUACIONESACTUACIONACTIVO activo = new ParamSIAACTUACIONESACTUACIONACTIVO("S");
		paramSia.setACTIVO(activo);
		
		paramSia.setOPERACION(sia.getOperacion());
		
		ParamSIAACTUACIONESACTUACION[] actuaciones = new ParamSIAACTUACIONESACTUACION[1];
		actuaciones[0] = paramSia;
		
		return actuaciones;
	}

	
}