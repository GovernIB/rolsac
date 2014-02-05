package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.IdiomaDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/idiomes/")
public class TMIdiomesController extends PantallaBaseController {

    private static Log log = LogFactory.getLog(TMIdiomesController.class);


    @RequestMapping(value = "/idiomes.do")
    public String pantallaIdiomes(Map<String, Object> model, HttpServletRequest request) {

        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMIdiomes.jsp");

        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
            model.put("escriptori", "pantalles/taulesMestres/tmIdiomes.jsp");
        } else {
            model.put("error", "permisos");
        }

        loadIndexModel(model, request);
        return "index";
    }


    @RequestMapping(value = "/llistat.do")
    public @ResponseBody Map<String, Object> llistatIdiomes(HttpServletRequest request) {

        List<IdiomaDTO> llistaIdiomesDTO = new ArrayList<IdiomaDTO>();
        Map<String, Object> resultats = new HashMap<String, Object>();

        try {
            List<Idioma> idiomas = DelegateUtil.getIdiomaDelegate().listarIdiomas();
            llistaIdiomesDTO.addAll(convertIdiomaToIdiomaDTO(idiomas));

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                log.error("Error: " + dEx.getMessage());
            }
        }

        resultats.put("total", llistaIdiomesDTO.size());
        resultats.put("nodes", llistaIdiomesDTO);

        return resultats;
    }


    private List<IdiomaDTO> convertIdiomaToIdiomaDTO(List<Idioma> idiomas) {

        List<IdiomaDTO> idiomasDTO = new ArrayList<IdiomaDTO>();
        for (Idioma idioma : idiomas) {
            idiomasDTO.add(new IdiomaDTO(
                idioma.getLang(),
                idioma.getCodigoEstandar(),
                idioma.getOrden(),
                idioma.getNombre(),
                idioma.getLangTraductor())
            );
        }

        return idiomasDTO;
    }


    @RequestMapping(value = "/pagDetall.do")
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        try {
            String lang = request.getParameter("id");

            Idioma idioma = DelegateUtil.getIdiomaDelegate().obtenerIdioma(lang);
            resultats.put("item_codi_estandard", idioma.getCodigoEstandar());
            resultats.put("item_lang", idioma.getLang());
            resultats.put("item_lang_traductor", idioma.getLangTraductor());
            resultats.put("item_nom", idioma.getNombre());

        } catch (DelegateException dEx) {
            log.error(ExceptionUtils.getStackTrace(dEx));
            if (dEx.isSecurityException()) {
                resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
            } else {
                resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
            }
        }

        return resultats;
    }


    @RequestMapping(value = "/guardar.do", method = POST)
    public @ResponseBody IdNomDTO guardarIdioma(HttpServletRequest request) {

        IdNomDTO result = null;
        String error = null;

        try {
            IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

            String lang = request.getParameter("item_lang");
            Idioma idioma = idiomaDelegate.obtenerIdioma(lang);
            if (idioma == null) {
                idioma = new Idioma();
                idioma.setLang(lang);
                idioma.setOrden(0);
            }

            idioma.setCodigoEstandar(request.getParameter("item_codi_estandard"));
            idioma.setLangTraductor(request.getParameter("item_lang_traductor"));
            idioma.setNombre(request.getParameter("item_nom"));

            idiomaDelegate.grabarIdioma(idioma);

            String ok = messageSource.getMessage("idioma.guardat.correcte", null, request.getLocale());
            result = new IdNomDTO(null, ok);

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomDTO(-1l, error);
            } else {
                error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomDTO(-2l, error);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }

        return result;
    }


    @RequestMapping(value = "/esborrarIdioma.do", method = POST)
    public @ResponseBody IdNomDTO esborrarPublicObjectiu(HttpServletRequest request) {

        IdNomDTO resultatStatus = new IdNomDTO();
        try {
            String lang = request.getParameter("id");
            DelegateUtil.getIdiomaDelegate().borrarIdioma(lang);

            resultatStatus.setId(1l);
            resultatStatus.setNom("correcte");

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultatStatus.setId(-1l);
            } else {
                resultatStatus.setId(-2l);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }

        return resultatStatus;
    }


    @RequestMapping(value = "/reordenarIdiomes.do", method = POST)
    public @ResponseBody IdNomDTO reordenarPublicObjectiu(HttpServletRequest request) {

        IdNomDTO resultatStatus = new IdNomDTO();

        try {
            String lang = request.getParameter("id");
            Integer nuevoOrden = new Integer(request.getParameter("orden")) + 1;
            Integer ordenOld = new Integer(request.getParameter("ordenAnterior")) + 1;

            DelegateUtil.getIdiomaDelegate().reordenar(lang, nuevoOrden, ordenOld);

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultatStatus.setId(-1l);
            } else {
                resultatStatus.setId(-2l);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
        } catch (NumberFormatException nfEx) {
            resultatStatus.setId(-3l);
            log.error("Error: Id de public objectiu no numèric: " + ExceptionUtils.getStackTrace(nfEx));
        }

        return resultatStatus;
    }

}
