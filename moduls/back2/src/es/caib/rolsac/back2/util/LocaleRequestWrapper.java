package es.caib.rolsac.back2.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {
    public LocaleRequestWrapper(HttpServletRequest req) {
        super(req);
    }

    public Enumeration getLocales() {
        Vector v = new Vector(1);
        v.add(getLocale());
        return v.elements();
    }

    public Locale getLocale() {
    	// TODO: cogerlo de alguna variable de sesion o request y sino coger el por defecto.
		IdiomaDelegate id = DelegateUtil.getIdiomaDelegate();
		String idioma;
		try {
			idioma = id.lenguajePorDefecto();
			if (idioma == null) idioma = "ca";
		} catch (DelegateException e) {
			idioma = "ca";
			e.printStackTrace();
		}
		return new Locale(idioma);
    }
}

