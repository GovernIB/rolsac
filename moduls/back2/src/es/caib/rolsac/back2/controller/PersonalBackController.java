package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.PersonalDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.CSVUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/personal/")
public class PersonalBackController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(PersonalBackController.class);

	@RequestMapping(value = "/personal.do", method = GET)
	public String pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request)
	{
		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 5);

		RolUtil rolUtil= new RolUtil(request);
		if (rolUtil.userIsSuper()) {
			model.put("escriptori", "pantalles/personal.jsp");
			if (session.getAttribute("unidadAdministrativa") != null) {
				model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
				try {
					model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));
				} catch (DelegateException e) {
					log.error("Error: " + e.getMessage());
				}
			}
		} else {
			model.put("error", "permisos");
		}

		loadIndexModel (model, request);
		return "index";
	}

	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request, HttpSession session) {

		List<PersonalDTO> llistaPersonalDTO = new ArrayList<PersonalDTO>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
        
        paramMap.put("username", request.getParameter("cerca_codi"));
        paramMap.put("ordreCamp",  request.getParameter("ordreCamp"));
        paramMap.put("ordreTipus",  request.getParameter("ordreTipus"));
        
        Long idUA = (session.getAttribute("unidadAdministrativa") != null) ? ((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId() : null;
        paramMap.put("unidadAdministrativa", idUA);

        String textes = request.getParameter("cerca_text");
        if (textes != null && !"".equals(textes)) {
            textes = textes.toUpperCase();

            paramMap.put("nombre", textes);
            paramMap.put("funciones", textes);
            paramMap.put("cargo", textes);
            paramMap.put("email", textes);
            paramMap.put("extensionPublica", textes);
            paramMap.put("numeroLargoPublico", textes);
            paramMap.put("extensionPrivada", textes);
            paramMap.put("numeroLargoPrivado", textes);
            paramMap.put("extensionMovil", textes);
            paramMap.put("numeroLargoMovil", textes);
        }

        boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
        boolean uaMeves = "1".equals(request.getParameter("uaMeves"));

        // Información de paginación
        String pagPag = request.getParameter("pagPag");
        String pagRes = request.getParameter("pagRes");

        if (pagPag == null) {
		    pagPag = String.valueOf(0);
		}
		if (pagRes == null) {
		    pagRes = String.valueOf(10);
		}

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {
		    PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
		    resultadoBusqueda = personalDelegate.buscadorListarPersonal(paramMap, Integer.parseInt(pagPag), Integer.parseInt(pagRes), uaFilles, uaMeves, false);

		    for (Personal persona : (List<Personal>) resultadoBusqueda.getListaResultados()) {
		        llistaPersonalDTO.add(new PersonalDTO(
		            persona.getId(),
		            persona.getNombre(),
		            persona.getUsername(),
		            persona.getUnidadAdministrativa().getNombreUnidadAdministrativa(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()),
		            persona.getEmail(),
		            persona.getExtensionPublica()
		        ));
		    }

		} catch (DelegateException dEx) {
		    if ( dEx.isSecurityException() ) {
		        log.error("Permisos insuficients: " + dEx.getMessage());
		    } else {
		        log.error("Error: " + dEx.getMessage());
		    }
		}
		
		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaPersonalDTO);
		return resultats;
	}

	@RequestMapping(value = "/exportar.do", method = POST)
	public void exportar(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
       
        paramMap.put("username", request.getParameter("cerca_codi"));
        paramMap.put("ordreCamp",  request.getParameter("ordreCamp"));
        paramMap.put("ordreTipus",  request.getParameter("ordreTipus"));
        Long idUA = (session.getAttribute("unidadAdministrativa") != null) ? ((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId() : null;
        paramMap.put("unidadAdministrativa", idUA);

        String textes = request.getParameter("cerca_text");
        if (textes != null && !"".equals(textes)) {
            textes = textes.toUpperCase();

            paramMap.put("nombre", textes);
            paramMap.put("funciones", textes);
            paramMap.put("cargo", textes);
            paramMap.put("email", textes);
            paramMap.put("extensionPublica", textes);
            paramMap.put("numeroLargoPublico", textes);
            paramMap.put("extensionPrivada", textes);
            paramMap.put("numeroLargoPrivado", textes);
            paramMap.put("extensionMovil", textes);
            paramMap.put("numeroLargoMovil", textes);
        }

        boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
        boolean uaMeves = "1".equals(request.getParameter("uaMeves"));

        // Información de paginación
        String pagPag = request.getParameter("pagPag");
        String pagRes = request.getParameter("pagRes");

        if (pagPag == null) {
		    pagPag = String.valueOf(0);
		}
		if (pagRes == null) {
		    pagRes = String.valueOf(10);
		}

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {
		    PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
		    resultadoBusqueda = personalDelegate.buscadorListarPersonal(paramMap, Integer.parseInt(pagPag), Integer.parseInt(pagRes), uaFilles, uaMeves, true);

		    CSVUtil.mostrarCSV(response, convertirPersonalToCSV((List<Object[]>) resultadoBusqueda.getListaResultados()));


		} catch (DelegateException dEx) {
		    if ( dEx.isSecurityException() ) {
		        log.error("Permisos insuficients: " + dEx.getMessage());
		    } else {
		        log.error("Error: " + dEx.getMessage());
		    }
		}
		
	}

	/**
	 * Genera el CSV a partir de los ids.
	 * @param listaResultados
	 * @return
	 */
	private String convertirPersonalToCSV(List<Object[]> listaResultados) {
		StringBuffer retorno = new StringBuffer();
		
		//cabecera!
		retorno.append("CODI_PERSONA;");
		retorno.append("NOM_PERSONA;");
		retorno.append("CARREC;");
		retorno.append("NOM_UA;");
		retorno.append("CORREU ELECTRONIC;");
		retorno.append("TELEFON EXTERIOR;");
		retorno.append("EXTENSIO FIXA;");
		retorno.append("TELEFON MOBIL;");
		retorno.append("EXTENSIO MOBIL;");
		retorno.append("\n");
		
		
		PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
		for ( Object[] resultado : listaResultados ) {
			final Long idPersonal = Long.valueOf(resultado[0].toString());
			Personal personal;
			try {
				personal = personalDelegate.obtenerPersonal(idPersonal);
			} catch (Exception exception) {
				log.error("Error obteniendo el personal con id : " + idPersonal , exception);
				retorno.append(CSVUtil.limpiar(idPersonal));
				retorno.append(ExceptionUtils.getCause(exception));
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}
			
			retorno.append(CSVUtil.limpiar(personal.getId())); 					//CODI_PERSONA,
			retorno.append(CSVUtil.limpiar(personal.getNombre())); 				//NOM_PERSONA,
			retorno.append(CSVUtil.limpiar(personal.getCargo())); 				//CARREC,
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(personal.getUnidadAdministrativa()))); 	//NOM_UA
			retorno.append(CSVUtil.limpiar(personal.getEmail())); 	//CORREU ELECTRÒNIC
			retorno.append(CSVUtil.limpiar(personal.getNumeroLargoPublico())); 	//TELEFON EXTERIOR (PER_NUMPUB)
			retorno.append(CSVUtil.limpiar(personal.getExtensionPublica()));	//EXTENSIÓ FIXA (PER_EXTPUB)
			retorno.append(CSVUtil.limpiar(personal.getNumeroLargoMovil())); 	//TELEFON MÒBIL (PER_NUMMOV)
			retorno.append(CSVUtil.limpiar(personal.getExtensionMovil())); 		//EXTENSIÓ MÒBIL (PER_EXTMOV)
			retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
		}
		
		return retorno.toString();		
	}

	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request)
	{
		Map<String, Object> personaDetall = new HashMap<String, Object>();

		try {
			Long id = new Long(request.getParameter("id"));

			PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
			Personal persona = personalDelegate.obtenerPersonal(id);

			personaDetall.put("id", persona.getId());
			personaDetall.put("nom", persona.getNombre());
			personaDetall.put("codi", persona.getUsername());
			personaDetall.put("funcions", persona.getFunciones());
			personaDetall.put("carrec", persona.getCargo());
			personaDetall.put("email", persona.getEmail());
			personaDetall.put("ua", persona.getUnidadAdministrativa().getNombreUnidadAdministrativa(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));
			personaDetall.put("uaId", persona.getUnidadAdministrativa().getId());
			personaDetall.put("extensioPublicaIntranet", persona.getExtensionPublica());
			personaDetall.put("numeroLlargPublicIntranet", persona.getNumeroLargoPublico());
			personaDetall.put("extensioPrivadaIntranet", persona.getExtensionPrivada());
			personaDetall.put("numeroLlargPrivatIntranet", persona.getNumeroLargoPrivado());
			personaDetall.put("extensioMobil", persona.getExtensionMovil());
			personaDetall.put("extensioLlargMobil", persona.getNumeroLargoMovil());

		} catch (DelegateException dEx) {
			log.error("Error: " + dEx.getMessage());
			if (dEx.isSecurityException())
				personaDetall.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				personaDetall.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
		}

		return personaDetall;
	}

	@RequestMapping(value = "/esborrarPersonal.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {

		IdNomDTO resultatStatus = new IdNomDTO(); 

		try {

			Long id = new Long(request.getParameter("id"));

			PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
			personalDelegate.borrarPersonal(id);                                    

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				resultatStatus.setId(-2l);
				log.error("Error: " + dEx.getMessage());
			}
		}

		return resultatStatus;
		
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpSession session, HttpServletRequest request) {

		IdNomDTO result = null;

		try {
			
			UnidadAdministrativa ua;
			try {
				Long uaId = Long.parseLong(request.getParameter("item_ua_id"));
				UnidadAdministrativaDelegate udelegate = DelegateUtil.getUADelegate();
				ua = udelegate.obtenerUnidadAdministrativa(uaId);
			} catch (NumberFormatException nfe) {
				ua = null;
			}

			String nom = request.getParameter("item_nom");
			String username = request.getParameter("item_codi");

			if (ua == null || nom == null || username == null || "".equals(nom) || "".equals(username)) {
				String error = messageSource.getMessage("persona.error.falten.camps", null, request.getLocale());
				result = new IdNomDTO(-3l, error);
			} else {
				PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
				Personal persona = null;

				try {
					Long id = Long.parseLong(request.getParameter("item_id"));
					persona = personalDelegate.obtenerPersonal(id); 
				} catch (NumberFormatException nfe) {
					persona = new Personal();
				}

				persona.setNombre(nom);
				persona.setUsername(username);
				persona.setUnidadAdministrativa(ua);
				persona.setFunciones(request.getParameter("item_funcions"));
				persona.setCargo(request.getParameter("item_carrec"));
				persona.setEmail(request.getParameter("item_email"));
				persona.setExtensionPublica(request.getParameter("item_epui"));
				persona.setNumeroLargoPublico(request.getParameter("item_nlpui"));
				persona.setExtensionPrivada(request.getParameter("item_epri"));
				persona.setNumeroLargoPrivado(request.getParameter("item_nlpri"));
				persona.setExtensionMovil(request.getParameter("item_em"));
				persona.setNumeroLargoMovil(request.getParameter("item_nlm"));                

				personalDelegate.grabarPersonal(persona, ua.getId());

				String ok = messageSource.getMessage("personal.guardat.correcte", null, request.getLocale());
				result = new IdNomDTO(persona.getId(), ok);
			}

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error("Error: " + dEx.getMessage());
			}
		}

		return result;
		
	}

}
