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
		String idioma;
		String lang;
		
		try {
			
			lang = super.getLocale().getLanguage();
			if (lang != null) {
				idioma = lang;
			} else {
				idioma = id.lenguajePorDefecto();
				if (idioma == null) idioma = "ca";
			}
			
		} catch (DelegateException e) {
			idioma = "ca";
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		// TODO XXX - Fins que totes les traduccions no estiguin disponibles a tots els idiomes 
		// forsam el catala perque no doni errors. Pensar a comentar o eliminar
		idioma = "ca";
		return new Locale(idioma);
    }
}