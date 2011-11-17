package es.caib.rolsac.back2.customJSTLTags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.back2.util.RolUtil;


public class UserIsSuperTag extends TagSupport {

    private static final long serialVersionUID = 833405520192508806L;
	private static Log log = LogFactory.getLog(UserIsSuperTag.class);

	@Override
	public int doStartTag() {
		userIsSuper();
		return SKIP_BODY;
	}
	
	/** Determina si el usuario tiene el rol "super" o superior.*/ 
 	private void userIsSuper() {
 		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			RolUtil rolUtil = new RolUtil(request);
        	pageContext.getOut().print(rolUtil.userIsSuper());
        } catch (IOException ioe) {
        	log.error(ioe);
        }
    }
}
