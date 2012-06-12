package es.caib.rolsac.back2.customJSTLTags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.back2.util.RolUtil;


public class UserIsOperTag extends TagSupport {

    private static final long serialVersionUID = -8616141551539106816L;
	private static Log log = LogFactory.getLog(UserIsOperTag.class);

	@Override
	public int doStartTag() {
		userIsOper();
		return SKIP_BODY;
	}
	
	/** Determina si el usuario tiene el rol "operador" o superior.*/ 
 	private void userIsOper() {
 		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			RolUtil rolUtil = new RolUtil(request);
        	pageContext.getOut().print(rolUtil.userIsOper());
        } catch (IOException ioe) {
        	log.error(ioe);
        }
    }
}
