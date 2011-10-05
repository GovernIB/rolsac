package es.caib.rolsac.back2.customJSTLTags;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;


public class SeleccionarUATag extends TagSupport {

    private static final long serialVersionUID = 2637018853437330695L;

	@Override
	public int doStartTag() {
		seleccionarUA();
		return SKIP_BODY;
	}
	
	/** Cargar en Session una Unidad Administrativa del usuario logueado.*/ 
 	private void seleccionarUA() {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			UsuarioDelegate uDelegate = DelegateUtil.getUsuarioDelegate();
			Usuario usuario = uDelegate.obtenerUsuariobyUsername(request.getRemoteUser());

			Set<UnidadAdministrativa> uas = usuario.getUnidadesAdministrativas();
			if (uas.size() > 0) {
				HttpSession session = pageContext.getSession();
				session.setAttribute("unidadAdministrativa", uas.iterator().next());
			}
        } catch(DelegateException de) {
        	System.out.println("Error: " + de);
        }
    }
}
