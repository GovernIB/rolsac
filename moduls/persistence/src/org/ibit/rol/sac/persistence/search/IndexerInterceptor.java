package org.ibit.rol.sac.persistence.search;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Indexable;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;


/**
 * User: areus
 * Date: 02/03/2004
 * Time: 13:18:34
 */
public class IndexerInterceptor implements Interceptor {

    protected static final Log log = LogFactory.getLog(IndexerInterceptor.class);
    private final IndexerDelegate delegate;

    public IndexerInterceptor() {
        delegate = DelegateUtil.getIndexerDelegate();
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) throws CallbackException {
        return false;
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) throws CallbackException {
    	if (entity instanceof Indexable) {
	    	try {
				delegate.indexarObjeto(entity);
			} catch (DelegateException e) {
				log.error("onFlushDirty: " + e.getMessage());
			}
    	}
		/*
    	if (entity instanceof Indexable) {
            try {
                IndexObject indexObject = ((Indexable) entity).indexObject();
                delegate.actualizarObjeto(indexObject);
            } catch (DelegateException e) {
                log.error("onFlushDirty: ", e);
            }
        }*/
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) throws CallbackException {
    	/*
        if (entity instanceof Indexable) {
            try {
                IndexObject indexObject = ((Indexable) entity).indexObject();
                //indexObject.setId((Long) id);
                delegate.insertaObjeto(indexObject,"ca");
            } catch (DelegateException e) {
                log.error("onSave: ", e);
            }
        }*/
    	try {
			delegate.indexarObjeto(entity);
		} catch (DelegateException e) {
			log.error("onSave: " + e.getMessage());
		}
        return false;
    }

    public void onDelete(Object entity,
                         Serializable id,
                         Object[] state,
                         String[] propertyNames,
                         Type[] types) throws CallbackException {
    	try {
			delegate.desindexarObjeto(entity);
		} catch (DelegateException e) {
			log.error("onDelete: " + e.getMessage());
		}
		/*
        if (entity instanceof Indexable) {
            try {
                IndexObject indexObject = ((Indexable) entity).indexObject();
                delegate.borrarObjeto(indexObject.getId(),"ca");
            } catch (DelegateException e) {
                log.error("onDelete: ", e);
            }
        }
        */
    }

    public void preFlush(Iterator entities) throws CallbackException {
    }

    public void postFlush(Iterator entities) throws CallbackException {
    }

    public Boolean isUnsaved(Object entity) {
        return null;
    }

    public int[] findDirty(Object entity,
                           Serializable id,
                           Object[] currentState,
                           Object[] previousState,
                           String[] propertyNames,
                           Type[] types) {
        return null;
    }

    public Object instantiate(Class clazz,
                              Serializable id) throws CallbackException {
        return null;
    }
}
