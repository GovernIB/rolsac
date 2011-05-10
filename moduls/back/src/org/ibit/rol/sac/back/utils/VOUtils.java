package org.ibit.rol.sac.back.utils;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.plugins.HibernateDelegateConverter;
import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.Traducible;
import org.ibit.rol.sac.model.ValueObject;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

/**
 * Utilidades para pasar datos entre <code>ValueObject</code> i
 * <code>ActionForm</code>.
 */
public class VOUtils {

    public static void populate(ValueObject vo, DynaActionForm form) throws Exception {
        BeanUtils.populate(vo, form.getMap());
    }

    public static void populate(Traducible vo, TraDynaValidatorForm form) throws Exception {
        BeanUtils.populate(vo, form.getMap());

        List llista = (List) form.get("traducciones");
        List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        Map traduccions = new HashMap(langs.size());
        for (int i = 0; i < langs.size(); i++) {
            String lang = (String) langs.get(i);
            traduccions.put(lang, llista.get(i));
        }
        vo.setTraduccionMap(traduccions);
    }
    
    //u92770[enric] - metode afegit per fer populate testejable (IdiomaDelegate es conecta amb Dependency Injection)
    //TODO cambiar aquest metode a no static i posar idiomadelegate com una propietat. Aixo facilitaria el testing.
    public static void populate(Traducible vo, TraDynaValidatorForm form, IdiomaDelegate idiomaDelegate) throws Exception {
        BeanUtils.populate(vo, form.getMap());
        List llista = (List) form.get("traducciones");
        List langs = idiomaDelegate.listarLenguajes();
        Map traduccions = new HashMap(langs.size());
        for (int i = 0; i < langs.size(); i++) {
            String lang = (String) langs.get(i);
            traduccions.put(lang, llista.get(i));
        }
        vo.setTraduccionMap(traduccions);
    }

	public static void describe(DynaActionForm form, ValueObject vo) throws Exception {
        testBeanUtils();
        BeanUtils.populate(form, BeanUtils.describe(vo));
    }

    public static void describe(TraDynaValidatorForm form, Traducible vo) throws Exception {
        testBeanUtils();
        BeanUtils.populate(form, BeanUtils.describe(vo));
        List traducciones = (List) form.get("traducciones");
        IdiomaDelegate idiomaDelegate=DelegateUtil.getIdiomaDelegate();
        List langs = idiomaDelegate.listarLenguajes();
        for (int i = 0; i < langs.size(); i++) {
            String lang = (String) langs.get(i);
            Traduccion traduccion = vo.getTraduccion(lang);
            if (traduccion != null) {
                traducciones.set(i, traduccion);
            }
        }
    }

	//u92770[enric] - metode afegit per carregar IdiomaDelegate mitjancant Inversion of Control
    //TODO cambiar aquest metode a no static i posar idiomadelegate com una propietat. Aixo facilitaria el testing.
    public static void describe(TraDynaValidatorForm form, Traducible vo, IdiomaDelegate idiomaDelegate) throws Exception {
        testBeanUtils();
        
        //Explicacio del populate(). form es un DynaActionForm de struts, i struts obté 
        //el mapa de camps que estan definits en struts-config.xml amb tipus de la classe de form.
        BeanUtils.populate(form, BeanUtils.describe(vo));
        
        List traducciones = (List) form.get("traducciones");
        idiomaDelegate=idiomaDelegate==null?DelegateUtil.getIdiomaDelegate(): idiomaDelegate;
        List langs = idiomaDelegate.listarLenguajes();
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
