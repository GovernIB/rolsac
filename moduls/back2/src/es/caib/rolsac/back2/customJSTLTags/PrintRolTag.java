package es.caib.rolsac.back2.customJSTLTags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.back2.util.RolUtil;


public class PrintRolTag extends TagSupport {

    private static final long serialVersionUID = 2637018853437330695L;
    private static Log log = LogFactory.getLog(PrintRolTag.class);

	@Override
	public int doStartTag() {
		printRol();
		return SKIP_BODY;
	}
	
	/** Devuelve el rol del usuario logueado.*/ 
 	private void printRol() {
 		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			RolUtil rolUtil = new RolUtil(request);
        	pageContext.getOut().print(rolUtil.getUserRol());
        } catch (IOException ioe) {
        	log.error(ioe);
        }
    }
}
