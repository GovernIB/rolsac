package es.caib.rolsac.back2.customJSTLTags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.back2.util.RolUtil;


public class UserIsSystemTag extends TagSupport {

    private static final long serialVersionUID = 1398851467859363114L;
	private static Log log = LogFactory.getLog(PrintRolTag.class);

	@Override
	public int doStartTag() {
		userIsSystem();
		return SKIP_BODY;
	}
	
	/** Determina si el usuario tiene el rol "system" o superior.*/ 
 	private void userIsSystem() {
 		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			RolUtil rolUtil = new RolUtil(request);
        	pageContext.getOut().print(rolUtil.userIsSystem());
        } catch (IOException ioe) {
        	log.error(ioe);
        }
    }
}
