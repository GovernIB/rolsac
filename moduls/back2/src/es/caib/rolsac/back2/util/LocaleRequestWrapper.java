package es.caib.rolsac.back2.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {
	
	private static Log log = LogFactory.getLog(LocaleRequestWrapper.class);
	
    public LocaleRequestWrapper(HttpServletRequest req) {
        super(req);
    }

    public Enumeration getLocales() {
        Vector v = new Vector(1);
        v.add(getLocale());
        return v.elements();
    }

    public Locale getLocale() {
		IdiomaDelegate id = DelegateUtil.getIdiomaDelegate();
		String lang;
		
		try {
			lang = super.getLocale().getLanguage();
			
			if (lang == null)
				lang = System.getProperty("es.caib.rolsac.idiomaDefault");
			
			if (lang == null) {
				lang = id.lenguajePorDefecto();
				if (lang == null) lang = "ca";
			}
			
		} catch (DelegateException e) {
			lang = "ca";
			log.error(ExceptionUtils.getStackTrace(e));
		}
		
		return new Locale(lang);
    }
}