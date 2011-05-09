package org.ibit.rol.sac.integracion.uddi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.datatype.business.BusinessEntity;
import org.apache.juddi.datatype.service.BusinessService;
import org.apache.juddi.datatype.tmodel.TModel;

/**
 *
 */
public class UDDICache {

    /* ================= */
    /* Cache De Business */
    /* ================= */

    public static void putBusiness(BusinessEntity b) {
        putObject(b.getBusinessKey(), b);
    }

    public static BusinessEntity getBusiness(String key) {
        return (BusinessEntity) getObject(key);
    }

    public static void putBusiness(List keys, BusinessEntity[] bs) {
        putObjects(keys, bs);
    }

    public static BusinessEntity[] getBusiness(List keys) {
        List business = getObjects(keys);
        if (business == null) return null;

        return (BusinessEntity[]) business.toArray(new BusinessEntity[keys.size()]);
    }

    /* ================= */
    /* Cache De Services */
    /* ================= */

    public static void putService(BusinessService s) {
        putObject(s.getServiceKey(), s);
    }

    public static BusinessService getService(String key) {
        return (BusinessService) getObject(key);
    }

    public static void putServices(List keys, BusinessService[] ss) {
        putObjects(keys, ss);
    }

    public static BusinessService[] getServices(List keys) {
        List services = getObjects(keys);
        if (services == null) return null;

        return (BusinessService[]) services.toArray(new BusinessService[keys.size()]);
    }

    /* ================ */
    /* Cache De TModels */
    /* ================ */

    public static void putTModel(TModel t) {
        putObject(t.getTModelKey(), t);
    }

    public static TModel getTModel(String key) {
        return (TModel) getObject(key);
    }

    public static void putTModels(List keys, TModel[] ts) {
        putObjects(keys, ts);
    }

    public static TModel[] getTModels(List keys) {
        List tModels = getObjects(keys);
        if (tModels == null) return null;

        return (TModel[]) tModels.toArray(new TModel[keys.size()]);
    }

    /* =============== */
    /* Cache de claus  */
    /* =============== */

    public static void putKeyForName(String name, String key) {
        putObject(name, key);
    }

    public static String getKeyForName(String name) {
        return (String) getObject(name);
    }

    /* ********************************************** */
    /* ** Metodos privados para manipular el cache ** */
    /* ********************************************** */

    private static final Log log = LogFactory.getLog(UDDICache.class);

    private static final long maxTime = 2 * 60 * 1000; // 2 minuts

    private static Map cacheMap = new HashMap();
    private static Map timerMap = new HashMap();

    private static List getObjects(List keys) {
        log.info("Cercant llista " + keys);
        List objects = new ArrayList(keys.size());
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            Object object = getObject(key);
            if (object == null) {
                log.info("Ha fallat llista per " + key);
                return null;
            }

            objects.add(object);
        }

        log.info("Hit de la llista " + keys);

        return objects;
    }

    private static void putObjects(List keys, Object[] objects) {
        log.info("Cacheant llista " + keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            putObject(key, objects[i]);
        }
    }

    private static Object getObject(String key) {
        if (!timerMap.containsKey(key)) {
            log.info("No cache per " + key);
            return null;
        }

        long cachedTime = ((Long) timerMap.get(key)).longValue();
        if (maxTime < System.currentTimeMillis() - cachedTime) {
            log.info("Cache expirat per " + key);
            timerMap.remove(key);
            cacheMap.remove(key);
            return null;
        }

        Object result = cacheMap.get(key);
        log.info("Hit per " + key);
        return result;
    }

    private static void putObject(String key, Object object) {
        log.info("Cacheant " + key);
        cacheMap.put(key, object);
        timerMap.put(key, new Long(System.currentTimeMillis()));
    }
}
