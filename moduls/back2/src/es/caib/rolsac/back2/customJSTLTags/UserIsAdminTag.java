package es.caib.rolsac.back2.customJSTLTags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.back2.util.RolUtil;


public class UserIsAdminTag extends TagSupport {

    private static final long serialVersionUID = -3669076772714938021L;
	private static Log log = LogFactory.getLog(UserIsAdminTag.class);

	@Override
	public int doStartTag() {
		userIsAdmin();
		return SKIP_BODY;
	}
	
	/** Determina si el usuario tiene el rol "admin" o superior.*/ 
 	private void userIsAdmin() {
 		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			RolUtil rolUtil = new RolUtil(request);
        	pageContext.getOut().print(rolUtil.userIsAdmin());
        } catch (IOException ioe) {
        	log.error(ioe);
        }
    }
}
