package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/index/")
public class TMIndexController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMIndexController.class);
	
    @RequestMapping(value = "/index.do", method = GET)
    public String pantallaIndex(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMIndex.jsp");

        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmIndex.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    
    @RequestMapping(value = "/reiniciarUnitatOrganica.do", method = POST)
    public @ResponseBody IdNomDTO reiniciarIndexUnitatOrganica(HttpServletRequest request) {
	    
	    IdNomDTO resultatStatus = new IdNomDTO(); 
	    try {
	    	
	    	IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
	        indexerDelegate.reindexarUOs();
	    	
            resultatStatus.setId(1l);
            resultatStatus.setNom(messageSource.getMessage("index.missatge.unitat_organica.correcte", null, request.getLocale()));
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
    
    @RequestMapping(value = "/reiniciarFitxes.do", method = POST)
    public @ResponseBody IdNomDTO reiniciarIndexFitxa(HttpServletRequest request) {
	    
	    IdNomDTO resultatStatus = new IdNomDTO(); 
	    try {
	    	IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
        	Long nMonths;
        	try {
        		nMonths=new Long(System.getProperty("es.caib.rolsac.luceneIndexer.nMonths")+"");
        	}catch (NumberFormatException e){
        		nMonths=null;
        	}
	    	List fichas = indexerDelegate.getFichasReindexar(nMonths);
          
        	Iterator iter = fichas.iterator();

            while (iter.hasNext()) {
            	indexerDelegate.reindexarFichas((Long)iter.next());
            }
	    	
            resultatStatus.setId(1l);
            resultatStatus.setNom(messageSource.getMessage("index.missatge.fitxa_informativa.correcte", null, request.getLocale()));
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
    
    @RequestMapping(value = "/reiniciarNormatives.do", method = POST)
    public @ResponseBody IdNomDTO reiniciarIndexNormativa(HttpServletRequest request) {
	    
	    IdNomDTO resultatStatus = new IdNomDTO(); 
	    try {
	    	IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
	        indexerDelegate.reindexarNormativas();
	    	
            resultatStatus.setId(1l);
            resultatStatus.setNom(messageSource.getMessage("index.missatge.normativa.correcte", null, request.getLocale()));
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
    
    @RequestMapping(value = "/reiniciarProcediments.do", method = POST)
    public @ResponseBody IdNomDTO reiniciarIndexProcediment(HttpServletRequest request) {
	    
	    IdNomDTO resultatStatus = new IdNomDTO(); 
	    try {
	    	IndexerDelegate indexerDelegate = DelegateUtil.getIndexerDelegate();
	        indexerDelegate.reindexarProcedimentos();
	    	
            resultatStatus.setId(1l);
            resultatStatus.setNom(messageSource.getMessage("index.missatge.procediment.correcte", null, request.getLocale()));
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
}
