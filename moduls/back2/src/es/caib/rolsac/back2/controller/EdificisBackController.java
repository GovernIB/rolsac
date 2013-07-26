package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.dto.EdificioDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/edificis/")
public class EdificisBackController extends PantallaBaseController{

	private static Log log = LogFactory.getLog(EdificisBackController.class);

	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistatEdificis(HttpServletRequest request) {

		List<EdificioDTO> llistaEdificiDTO = new ArrayList<EdificioDTO>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> tradMap = new HashMap<String, Object>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Long idUABase=null;
		Boolean uaFilles=false;
		Boolean uaMeves=false;

		if (!"".equals(request.getParameter("adresa"))){
			paramMap.put("direccion", request.getParameter("adresa").toUpperCase());
		}
		if (!"".equals(request.getParameter("cp"))){
			paramMap.put("codigoPostal", request.getParameter("cp").toUpperCase());
		}
		if (!"".equals(request.getParameter("poblacio"))){
			paramMap.put("poblacion", request.getParameter("poblacio").toUpperCase());
		}

		String idUA = request.getParameter("idUA");
		if (idUA != null && !"".equals(idUA)) {
			idUABase=Long.valueOf(idUA);
			uaFilles=true;
		} 

		//Información de paginación
		String pagPag = request.getParameter("pagPagina");		
		String pagRes = String.valueOf(10);

		if (pagPag == null) pagPag = String.valueOf(0); 

		//Requerimiento del metodo original de rolsac
		tradMap.put("idioma", request.getLocale().getLanguage());

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();	

		try {                               

			EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();

			resultadoBusqueda = edificiDelegate.buscarEdificios(paramMap, tradMap, idUABase, uaFilles, uaMeves, pagPag, pagRes);

			for (Edificio edf : castList(Edificio.class, resultadoBusqueda.getListaResultados() ) ) {

				llistaEdificiDTO.add(new EdificioDTO(edf.getId(), edf.getDireccion(), edf.getCodigoPostal(),edf.getPoblacion()));  

			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + dEx.toString());
				resultats.put("id", -1);
			} else {
				log.error("Error: " + dEx.toString());
				resultats.put("id", -2);
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaEdificiDTO);      

		return resultats;
	}
}
