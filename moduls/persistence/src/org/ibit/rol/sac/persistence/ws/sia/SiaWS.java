package org.ibit.rol.sac.persistence.ws.sia;

import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.TraduccionNormativa;
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

	/**
	 * Enviar dato a sia para actualizar.
	 * @param sia
	 * @return
	 * @throws Exception
	 */
	public static SiaResultado enviarSIA(Sia sia, boolean borrado) throws Exception {
		
		SiaResultado siaResultado = new SiaResultado();
		if (SiaUtils.isActivoEnvio()) {
			String usuario = SiaUtils.getUsuarioEnvio();
			String password = SiaUtils.getPasswordEnvio();
			WsSIAActualizarActuaciones_PortType client = SiaClient.createClient(SiaUtils.getUrlEnvio());
			
			ParamSIAACTUACIONESACTUACION[] actuaciones;
			if (borrado) {
				actuaciones = cargarDatosSiaBorrado(sia);
			} else {
				actuaciones = cargarDatosSia(sia);
			}
			
			ParamSIA parameters = new ParamSIA(usuario, password, null, actuaciones);
			
			EnviaSIA resultado = client.actualizarSIA_v3_1(parameters);
			
			EnviaSIAACTUACIONESACTUACION[] res = resultado.getACTUACIONES();
			
			int correctos = 0;
			int incorrectos = 0;
			
			if(res != null) {
				siaResultado.setResultado(SiaResultado.RESULTADO_OK);
				for (EnviaSIAACTUACIONESACTUACION envia : res) {
					if(envia.getCORRECTO().equals(SiaUtils.SI)) {
						correctos++;
						siaResultado.setCodSIA(envia.getCODIGOACTUACION());
					} else {
						siaResultado.setResultado(SiaResultado.RESULTADO_ERROR);
						incorrectos++;
						ERRORESERROR[] arrayErrores = envia.getERRORES();
						siaResultado.setMensaje("");
						for (ERRORESERROR error : arrayErrores) {
							siaResultado.setMensaje(siaResultado.getMensaje()+error.getERROR()+":"+error.getDESCERROR() + " "+ siaResultado.getMensaje()+"<br />");
						}
					}
					if (SiaUtils.ESTADO_BAJA.compareTo(envia.getOPERACION()) == 0) {
						siaResultado.setEstadoSIA(SiaUtils.ESTADO_BAJA);
					} else {
						siaResultado.setEstadoSIA(SiaUtils.ESTADO_ALTA);
					}					
				}
			}
		
			siaResultado.setCorrectos(correctos);
			siaResultado.setIncorrectos(incorrectos);
			
		} else {
			int aleatorio = (int)(Math.random()*(100));
			if (aleatorio % 8 == 0) {
				siaResultado.setMensaje("Error aleatorio, estás en modo prueba!!");
				siaResultado.setCorrectos(0); 
				siaResultado.setIncorrectos(1);
				siaResultado.setResultado(SiaResultado.RESULTADO_ERROR);
			} else {
				siaResultado.setCodSIA(String.valueOf((int)(Math.random()*(10000000))));
				siaResultado.setCorrectos(1);
				siaResultado.setIncorrectos(0);
				siaResultado.setResultado(SiaResultado.RESULTADO_OK);
				if (aleatorio % 8 == 2 || aleatorio % 8 == 4) {
					siaResultado.setEstadoSIA(SiaUtils.ESTADO_BAJA);
				} else {
					siaResultado.setEstadoSIA(SiaUtils.ESTADO_ALTA);
				} 
			}
		}
		return siaResultado;
	}
	
	/**
	 * @param sia
	 * @return
	 * @throws Exception 
	 */
	private static ParamSIAACTUACIONESACTUACION[] cargarDatosSiaBorrado(Sia sia) throws Exception {
		ParamSIAACTUACIONESACTUACION paramSia = new ParamSIAACTUACIONESACTUACION();
		
		paramSia.setCODIGOACTUACION(sia.getIdSIA());
		paramSia.setCODIGOORIGEN(sia.getIdProc());
		paramSia.setOPERACION(SiaUtils.ESTADO_BAJA);
		
		ParamSIAACTUACIONESACTUACION[] actuaciones = new ParamSIAACTUACIONESACTUACION[1];
		actuaciones[0] = paramSia;
		
		return actuaciones;
	}
	
	
	/**
	 * @param sia
	 * @return
	 * @throws Exception 
	 */
	private static ParamSIAACTUACIONESACTUACION[] cargarDatosSia(Sia sia) throws Exception {
		ParamSIAACTUACIONESACTUACION paramSia = new ParamSIAACTUACIONESACTUACION();
		
		if (sia.getOperacion() != null && SiaUtils.ESTADO_ALTA.equals(sia.getOperacion())) {
			paramSia.setCODIGOACTUACION("inventadoCAIB"); //Obligan a que se introduzca, en caso de alta, lo generan ellos en la respuesta.
		} else {
			paramSia.setCODIGOACTUACION(sia.getIdSIA());
		}
		paramSia.setCODIGOORIGEN(sia.getIdProc());
		
		paramSia.setDENOMINACION(sia.getTitulo());
		//paramSia.setTITULOCIUDADANO(sia.getTitulo());
		paramSia.setDESCRIPCION(sia.getDescripcion());
		ORGANISMORESPONSABLE organismoResponsable = new ORGANISMORESPONSABLE();
		//Fix 17/02. Pasado el id del centro a nivel 2 e incluido como nivel1 el departamento que viene por propiedades.
		organismoResponsable.setCODORGANISMORESPONSABLEN1(sia.getIdDepartamento());
		organismoResponsable.setCODORGANISMORESPONSABLEN2(sia.getIdCent());
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
			if (norm.getTipo() == null) { throw new Exception("No tiene tipo sia la normativa.");}
			nor.setCODRANGO(norm.getTipo().getTipoSia().toString()); 
			if (((TraduccionNormativa) norm.getTraduccion("es")) == null) {
				nor.setTITULO(((TraduccionNormativa) norm.getTraduccion("ca")).getTitulo());
			} else {
				nor.setTITULO(((TraduccionNormativa) norm.getTraduccion("es")).getTitulo());
			}
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
