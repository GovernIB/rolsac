package org.ibit.rol.sac.persistence.ws.sia;

import java.util.ArrayList;
import java.util.List;

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
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONESCOMUN;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONFINVIA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONINTERNO;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONTIPOTRAMITE;

/**
 * Representación Sia. Envio SIA
 */
public class SiaWS {

	/**
	 * Enviar dato a sia para actualizar.
	 *
	 * @param sia
	 * @param borrado
	 *            Indica si esta en modo borrado
	 * @param noactivo
	 *            Indica si esta en modo noactivo (se envia la minimo info)
	 *
	 * @return
	 * @throws Exception
	 */
	public static SiaResultado enviarSIA(final Sia sia, final boolean borrado, final boolean noactivo)
			throws Exception {

		final SiaResultado siaResultado = new SiaResultado();
		siaResultado.setOperacion(sia.getOperacion());
		if (SiaUtils.isActivoEnvio()) {
			final String usuario = sia.getUsuario();
			final String password = sia.getPassword();
			final WsSIAActualizarActuaciones_PortType client = SiaClient.createClient(SiaUtils.getUrlEnvio());

			ParamSIAACTUACIONESACTUACION[] actuaciones;
			if (noactivo) {
				actuaciones = cargarDatosSiaNoActivo(sia);
			} else if (borrado) {
				actuaciones = cargarDatosSiaBorrado(sia);
			} else {
				actuaciones = cargarDatosSia(sia);
			}

			final ParamSIA parameters = new ParamSIA(usuario, password, null, actuaciones);

			final EnviaSIA resultado = client.actualizarSIA_v3_1(parameters);

			final EnviaSIAACTUACIONESACTUACION[] res = resultado.getACTUACIONES();

			int correctos = 0;
			int incorrectos = 0;

			if (res != null) {
				siaResultado.setResultado(SiaResultado.RESULTADO_OK);
				for (final EnviaSIAACTUACIONESACTUACION envia : res) {
					if (envia.getCORRECTO().equals(SiaUtils.SI)) {
						correctos++;
						siaResultado.setCodSIA(envia.getCODIGOACTUACION());
					} else {
						siaResultado.setResultado(SiaResultado.RESULTADO_ERROR);
						incorrectos++;
						final ERRORESERROR[] arrayErrores = envia.getERRORES();
						siaResultado.setMensaje("");
						for (final ERRORESERROR error : arrayErrores) {
							siaResultado.setMensaje(siaResultado.getMensaje() + error.getERROR() + ":"
									+ error.getDESCERROR() + " " + siaResultado.getMensaje() + "<br />");
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
			final int aleatorio = (int) (Math.random() * (100));
			if (aleatorio % 8 == 0) {
				siaResultado.setMensaje("Error aleatorio, estás en modo prueba!!");
				siaResultado.setCorrectos(0);
				siaResultado.setIncorrectos(1);
				siaResultado.setResultado(SiaResultado.RESULTADO_ERROR);
			} else {
				siaResultado.setCodSIA(String.valueOf((int) (Math.random() * (10000000))));
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
	private static ParamSIAACTUACIONESACTUACION[] cargarDatosSiaNoActivo(final Sia sia) throws Exception {

		final ParamSIAACTUACIONESACTUACION paramSia = new ParamSIAACTUACIONESACTUACION();
		paramSia.setACTIVO(new ParamSIAACTUACIONESACTUACIONACTIVO("N"));

		if (sia.getIdSIA() == null || sia.getIdSIA().isEmpty()) {
			paramSia.setCODIGOACTUACION("inventadoCAIB"); // Obligan a que se introduzca, en caso de alta, lo generan
															// ellos en la respuesta.
		} else {
			paramSia.setCODIGOACTUACION(sia.getIdSIA());
		}
		paramSia.setCODIGOORIGEN(sia.getIdElemento());

		paramSia.setDENOMINACION(sia.getTitulo());
		paramSia.setDESCRIPCION(sia.getDescripcion());
		final ORGANISMORESPONSABLE organismoResponsable = new ORGANISMORESPONSABLE();
		// Fix 17/02. Pasado el id del centro a nivel 2 e incluido como nivel1 el
		// departamento que viene por propiedades.
		organismoResponsable.setCODORGANISMORESPONSABLEN1(sia.getIdDepartamento());
		organismoResponsable.setCODORGANISMORESPONSABLEN2(sia.getIdCent());
		paramSia.setORGANISMORESPONSABLE(organismoResponsable);

		final DESTINATARIOSDESTINATARIO[] destinatarios = new DESTINATARIOSDESTINATARIO[sia.getIdDest().length];
		int i = 0;
		for (final String pObj : sia.getIdDest()) {
			destinatarios[i] = new DESTINATARIOSDESTINATARIO(pObj);
			i++;
		}
		paramSia.setDESTINATARIOS(destinatarios);
		paramSia.setCODNIVELADMINISTRACIONELECTRONICA(sia.getNivAdm().toString());

		final List<NORMATIVASNORMATIVA> normativasCorrectas = new ArrayList<NORMATIVASNORMATIVA>();
		for (final Normativa norm : sia.getNormativas()) {
			final NORMATIVASNORMATIVA nor = new NORMATIVASNORMATIVA();
			if (norm == null || norm.getTipo() == null || !norm.isVisible() || norm.getTipo().getTipoSia() == null) {
				continue;
			}
			nor.setCODRANGO(norm.getTipo().getTipoSia().toString());
			nor.setTITULO(SiaUtils.getNombreNormativa(norm));
			normativasCorrectas.add(nor);
		}

		if (!normativasCorrectas.isEmpty()) {
			final NORMATIVASNORMATIVA[] normativas = new NORMATIVASNORMATIVA[normativasCorrectas.size()];
			for (i = 0; i < normativasCorrectas.size(); i++) {
				normativas[i] = normativasCorrectas.get(i);
			}

			paramSia.setNORMATIVAS(normativas);
		}

		if (sia.getMaterias().length > 0) {
			final MATERIASMATERIA[] materias = new MATERIASMATERIA[sia.getMaterias().length];
			i = 0;
			for (final String mat : sia.getMaterias()) {
				materias[i] = new MATERIASMATERIA(mat);
				i++;
			}
			paramSia.setMATERIAS(materias);
		}

		final ParamSIAACTUACIONESACTUACIONFINVIA finVia = new ParamSIAACTUACIONESACTUACIONFINVIA(sia.getFiVia());
		paramSia.setFINVIA(finVia);

		final ParamSIAACTUACIONESACTUACIONINTERNO interno = new ParamSIAACTUACIONESACTUACIONINTERNO();
		final ParamSIAACTUACIONESACTUACIONESCOMUN comun = new ParamSIAACTUACIONESACTUACIONESCOMUN();

		if (sia.getTipologia() == SiaUtils.TIPOLOGIA_INTERNO_COMUN) {
			interno.setBooleanoValue(SiaUtils.SI);
			comun.setBooleanoValue(SiaUtils.SI);
		} else if (sia.getTipologia() == SiaUtils.TIPOLOGIA_INTERNO_ESPECIFICO) {
			interno.setBooleanoValue(SiaUtils.SI);
			comun.setBooleanoValue(SiaUtils.NO);
		} else if (sia.getTipologia() == SiaUtils.TIPOLOGIA_EXTERNO_COMUN) {
			interno.setBooleanoValue(SiaUtils.NO);
			comun.setBooleanoValue(SiaUtils.SI);
		} else if (sia.getTipologia() == SiaUtils.TIPOLOGIA_EXTERNO_ESPECIFICO) {
			interno.setBooleanoValue(SiaUtils.NO);
			comun.setBooleanoValue(SiaUtils.NO);
		}

		paramSia.setESCOMUN(comun);
		paramSia.setINTERNO(interno);
		
		final ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO dispoApoderadoHabilitado = new ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO();
		final ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO dispoFuncionarioHabilitado = new ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO();
		if(sia.isDisponibleApoderadoHabilitado()) {
			dispoApoderadoHabilitado.setBooleanoValue(SiaUtils.SI);
		}else {
			dispoApoderadoHabilitado.setBooleanoValue(SiaUtils.NO);
		}
		
		if(sia.isDisponibleFuncionarioHabilitado()) {
			dispoFuncionarioHabilitado.setBooleanoValue(SiaUtils.SI);
		}else {
			dispoFuncionarioHabilitado.setBooleanoValue(SiaUtils.NO);
		}

		paramSia.setDISPONIBLEAPODERADOHABILITADO(dispoApoderadoHabilitado);
		paramSia.setDISPONIBLEFUNCIONARIOHABILITADO(dispoFuncionarioHabilitado);


		final ParamSIAACTUACIONESACTUACIONTIPOTRAMITE tipoTramite = new ParamSIAACTUACIONESACTUACIONTIPOTRAMITE();
		tipoTramite.setTIPOTRAMITEValue(sia.getTipoTramite());
		paramSia.setTIPOTRAMITE(tipoTramite);

		paramSia.setUNIDADGESTORATRAMITE(sia.getUaGest());

		paramSia.setENLACEWEB(sia.getEnlaceWeb());
		paramSia.setOPERACION(sia.getOperacion());

		final ParamSIAACTUACIONESACTUACION[] actuaciones = new ParamSIAACTUACIONESACTUACION[1];
		actuaciones[0] = paramSia;

		return actuaciones;
	}

	/**
	 * @param sia
	 * @return
	 * @throws Exception
	 */
	private static ParamSIAACTUACIONESACTUACION[] cargarDatosSiaBorrado(final Sia sia) throws Exception {
		final ParamSIAACTUACIONESACTUACION paramSia = new ParamSIAACTUACIONESACTUACION();

		paramSia.setCODIGOACTUACION(sia.getIdSIA());
		paramSia.setCODIGOORIGEN(sia.getIdElemento());
		paramSia.setOPERACION(SiaUtils.ESTADO_BAJA);

		final ParamSIAACTUACIONESACTUACION[] actuaciones = new ParamSIAACTUACIONESACTUACION[1];
		actuaciones[0] = paramSia;

		return actuaciones;
	}

	/**
	 * @param sia
	 * @return
	 * @throws Exception
	 */
	private static ParamSIAACTUACIONESACTUACION[] cargarDatosSia(final Sia sia) throws Exception {
		final ParamSIAACTUACIONESACTUACION paramSia = new ParamSIAACTUACIONESACTUACION();

		if (sia.getOperacion() != null && SiaUtils.ESTADO_ALTA.equals(sia.getOperacion())) {
			paramSia.setCODIGOACTUACION("inventadoCAIB"); // Obligan a que se introduzca, en caso de alta, lo generan
															// ellos en la respuesta.
		} else {
			paramSia.setCODIGOACTUACION(sia.getIdSIA());
		}
		paramSia.setCODIGOORIGEN(sia.getIdElemento());

		paramSia.setDENOMINACION(sia.getTitulo());
		// paramSia.setTITULOCIUDADANO(sia.getTitulo());
		paramSia.setDESCRIPCION(sia.getDescripcion());
		final ORGANISMORESPONSABLE organismoResponsable = new ORGANISMORESPONSABLE();
		// Fix 17/02. Pasado el id del centro a nivel 2 e incluido como nivel1 el
		// departamento que viene por propiedades.
		organismoResponsable.setCODORGANISMORESPONSABLEN1(sia.getIdDepartamento());
		organismoResponsable.setCODORGANISMORESPONSABLEN2(sia.getIdCent());
		paramSia.setORGANISMORESPONSABLE(organismoResponsable);

		final DESTINATARIOSDESTINATARIO[] destinatarios = new DESTINATARIOSDESTINATARIO[sia.getIdDest().length];
		int i = 0;
		for (final String pObj : sia.getIdDest()) {
			destinatarios[i] = new DESTINATARIOSDESTINATARIO(pObj);
			i++;
		}
		paramSia.setDESTINATARIOS(destinatarios);
		paramSia.setCODNIVELADMINISTRACIONELECTRONICA(sia.getNivAdm().toString());

		final List<NORMATIVASNORMATIVA> normativasCorrectas = new ArrayList<NORMATIVASNORMATIVA>();
		for (final Normativa norm : sia.getNormativas()) {
			final NORMATIVASNORMATIVA nor = new NORMATIVASNORMATIVA();
			if (norm == null || norm.getTipo() == null || !norm.isVisible() || norm.getTipo().getTipoSia() == null) {
				continue;
			}
			nor.setCODRANGO(norm.getTipo().getTipoSia().toString());
			nor.setTITULO(SiaUtils.getNombreNormativa(norm));
			normativasCorrectas.add(nor);
		}
		final NORMATIVASNORMATIVA[] normativas = new NORMATIVASNORMATIVA[normativasCorrectas.size()];
		for (i = 0; i < normativasCorrectas.size(); i++) {
			normativas[i] = normativasCorrectas.get(i);
		}

		paramSia.setNORMATIVAS(normativas);

		final MATERIASMATERIA[] materias = new MATERIASMATERIA[sia.getMaterias().length];
		i = 0;
		for (final String mat : sia.getMaterias()) {
			materias[i] = new MATERIASMATERIA(mat);
			i++;
		}
		paramSia.setMATERIAS(materias);

		final ParamSIAACTUACIONESACTUACIONFINVIA finVia = new ParamSIAACTUACIONESACTUACIONFINVIA(sia.getFiVia());
		paramSia.setFINVIA(finVia);

		final ParamSIAACTUACIONESACTUACIONINTERNO interno = new ParamSIAACTUACIONESACTUACIONINTERNO();
		final ParamSIAACTUACIONESACTUACIONESCOMUN comun = new ParamSIAACTUACIONESACTUACIONESCOMUN();

		if (sia.getTipologia() == SiaUtils.TIPOLOGIA_INTERNO_COMUN) {
			interno.setBooleanoValue(SiaUtils.SI);
			comun.setBooleanoValue(SiaUtils.SI);
		} else if (sia.getTipologia() == SiaUtils.TIPOLOGIA_INTERNO_ESPECIFICO) {
			interno.setBooleanoValue(SiaUtils.SI);
			comun.setBooleanoValue(SiaUtils.NO);
		} else if (sia.getTipologia() == SiaUtils.TIPOLOGIA_EXTERNO_COMUN) {
			interno.setBooleanoValue(SiaUtils.NO);
			comun.setBooleanoValue(SiaUtils.SI);
		} else if (sia.getTipologia() == SiaUtils.TIPOLOGIA_EXTERNO_ESPECIFICO) {
			interno.setBooleanoValue(SiaUtils.NO);
			comun.setBooleanoValue(SiaUtils.NO);
		}

		paramSia.setINTERNO(interno);
		paramSia.setESCOMUN(comun);
		
		final ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO dispoApoderadoHabilitado = new ParamSIAACTUACIONESACTUACIONDISPONIBLEAPODERADOHABILITADO();
		final ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO dispoFuncionarioHabilitado = new ParamSIAACTUACIONESACTUACIONDISPONIBLEFUNCIONARIOHABILITADO();
		if(sia.isDisponibleApoderadoHabilitado()) {
			dispoApoderadoHabilitado.setBooleanoValue(SiaUtils.SI);
		}else {
			dispoApoderadoHabilitado.setBooleanoValue(SiaUtils.NO);
		}
		
		if(sia.isDisponibleFuncionarioHabilitado()) {
			dispoFuncionarioHabilitado.setBooleanoValue(SiaUtils.SI);
		}else {
			dispoFuncionarioHabilitado.setBooleanoValue(SiaUtils.NO);
		}

		paramSia.setDISPONIBLEAPODERADOHABILITADO(dispoApoderadoHabilitado);
		paramSia.setDISPONIBLEFUNCIONARIOHABILITADO(dispoFuncionarioHabilitado);

		final ParamSIAACTUACIONESACTUACIONTIPOTRAMITE tipoTramite = new ParamSIAACTUACIONESACTUACIONTIPOTRAMITE();
		tipoTramite.setTIPOTRAMITEValue(sia.getTipoTramite());
		paramSia.setTIPOTRAMITE(tipoTramite);

		paramSia.setUNIDADGESTORATRAMITE(sia.getUaGest());

		paramSia.setENLACEWEB(sia.getEnlaceWeb());

		final ParamSIAACTUACIONESACTUACIONACTIVO activo = new ParamSIAACTUACIONESACTUACIONACTIVO("S");
		paramSia.setACTIVO(activo);

		paramSia.setOPERACION(sia.getOperacion());

		final ParamSIAACTUACIONESACTUACION[] actuaciones = new ParamSIAACTUACIONESACTUACION[1];
		actuaciones[0] = paramSia;

		return actuaciones;
	}

}
