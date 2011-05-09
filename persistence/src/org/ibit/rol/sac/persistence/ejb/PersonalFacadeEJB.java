package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SessionBean para mantener y consultar Personal.
 *
 * @ejb.bean
 *  name="sac/persistence/PersonalFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.PersonalFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class PersonalFacadeEJB extends HibernateEJB {

    /**
     * Obtiene referència al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

     /**
     * Crea o actualiza un Personal
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public Long grabarPersonal(Personal personal, Long idUA) {
        Session session = getSession();
        try {
            if (personal.getId() == null) {
                if (!getAccesoManager().tieneAccesoUnidad(idUA, true)) {
                    throw new SecurityException("No tiene acceso a la unidad");
                }
            } else {
            	if (!getAccesoManager().tieneAccesoPersonal(personal.getId())) {
                    throw new SecurityException("No tiene acceso al personal");
                }
            }

            //UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
            //personal.setUnidadAdministrativa(unidad);
            //Hibernate.initialize(unidad.getPersonal());

            session.saveOrUpdate(personal);
            session.flush();
            
            return personal.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }

 
    }

    /*
    Session session = getSession();
    try {
    	if (personal.getId() == null) {
            if (!getAccesoManager().tieneAccesoUnidad(unidad_id, true)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidad_id);
            ua.addPersonal(personal);
        } else {
            if (!getAccesoManager().tieneAccesoPersonal(personal.getId())) {
                throw new SecurityException("No tiene acceso al personal");
            }
            session.update(personal);
        }
        session.flush();
        return personal.getId();
    } catch (HibernateException he) {
        throw new EJBException(he);
    } finally {
        close(session);
    }
    */
    
    
    /**
     * Obtiene un Personal
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Personal obtenerPersonal(Long id) {
        Session session = getSession();
        try {
        	Personal per = (Personal)session.load(Personal.class, id);
        	Hibernate.initialize(per.getUnidadAdministrativa());
            return per;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista el personal
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public List listarPersonal(){
         Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Personal.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    /**
     * Lista el personal
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */ 
    
    public List listarPersonalFiltro(Map parametros){
    	 
    	        Session session = getSession();
    	        try {
    	            if (!userIsOper()) {
    	                parametros.put("validacion", Validacion.PUBLICA);
    	            }

    	            List params = new ArrayList();
    	            String sQuery = populateQuery(parametros, params);
    	            String sql="from Personal perso ";
    	            if (params.size()>0) sql+=" where " + sQuery;
    	            sql+=" order by ltrim(perso.nombre) ASC ";
    	            Query query = session.createQuery(sql);
    	            for (int i = 0; i < params.size(); i++) {
    	                Object o = params.get(i);
    	                query.setParameter(i, o);
    	            }

    	            return query.list();
    	        } catch (HibernateException he) {
    	            throw new EJBException(he);
    	        } finally {
    	            close(session);
    	        }
    	    }

    /**
     * Construye el query de bï¿½squeda segun los parametros
     */
    private String populateQuery(Map parametros,  List params) {
        String aux = "";

        // Tratamiento de parametros
        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (value != null&&(!value.equals(""))) {
                if (aux.length() > 0) aux = aux + " and ";
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( perso." + key + " ) like upper(?) " ;
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( perso." + key + " ) like upper(?) ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + "perso." + key + " =  ? ";
                    params.add(value);
                }
            }
        } 
 
        return aux;
    }
    /**
     * Lista el personal de la Unidad Administrativa
     * @throws DelegateException 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Set listarPersonalUA(Long unidadAdmin_id) throws DelegateException{
        Session session = getSession();
        try {
        	
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, unidadAdmin_id);
            Hibernate.initialize(ua.getPersonal());
            return ua.getPersonal();
            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borrar un Personal
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void borrarPersonal(Long id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoPersonal(id)) {
                throw new SecurityException("No tiene acceso al personal");
            }
            Personal persona = (Personal) session.load(Personal.class, id);
            persona.getUnidadAdministrativa().removePersonal(persona);
            session.delete(persona);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
}
