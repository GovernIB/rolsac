package org.ibit.rol.sac.back.subscripcions.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.Traducible;
import org.ibit.rol.sac.model.ValueObject;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.back.subscripcions.actionform.TraDynaActionForm;
import org.ibit.rol.sac.back.subscripcions.plugins.HibernateDelegateConverter;

/**
 * Utilidades para pasar datos entre <code>ValueObject</code> i
 * <code>ActionForm</code>.
 */
public class VOUtils {

	public static void populate(ValueObject vo, DynaActionForm form) throws Exception {
        //BeanUtils.populate(vo, form.getMap());
    }

    public static void populate(Traducible vo, TraDynaActionForm form) throws Exception {
    	//BeanUtils.populate(vo, form.getMap());

    	List llista = (List) form.get("traducciones");
        List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

        
        Map traduccions = new HashMap(langs.size());
        for (int i = 0; i < langs.size(); i++) {
            String lang = (String) langs.get(i);
            traduccions.put(lang, llista.get(i));
        }
        vo.setTraduccionMap(traduccions);
        
    }

    public static void describe(DynaActionForm form, ValueObject vo) throws Exception {
        testBeanUtils();
        //BeanUtils.populate(form, BeanUtils.describe(vo));
    }

    public static void describe(TraDynaActionForm form, Traducible vo) throws Exception {
        testBeanUtils();
        
        //BeanUtils.populate(form, BeanUtils.describe(vo));
        List traducciones = (List) form.get("traducciones");
        List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        for (int i = 0; i < langs.size(); i++) {
            String lang = (String) langs.get(i);
            Traduccion traduccion = vo.getTraduccion(lang);
            if (traduccion != null) {
                traducciones.set(i, traduccion);
            }
        }
    }

    private static void testBeanUtils() {
        Converter currentConverter = ConvertUtils.lookup(String.class);
        if (!(currentConverter instanceof HibernateDelegateConverter)) {
            Converter newConverter = new HibernateDelegateConverter(currentConverter);
            ConvertUtils.register(newConverter, String.class);
        }
    }
}
