package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import es.caib.rolsac.utils.ResultadoBusqueda;

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
     * Obtiene refer�ncia al ejb de control de Acceso.
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
            session.saveOrUpdate(personal);
            session.flush();
            
            return personal.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }

 
    }
    
    
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
     * Lista el personal (buscador del nuevo backoffice)
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */ 
    public ResultadoBusqueda buscadorListarPersonal(Map parametros, int pagina, int resultats, boolean uaFilles, boolean uaMeves) {
    	
    	int resultadosMax = new Integer(resultats).intValue();
    	int primerResultado = new Integer(pagina).intValue() * resultadosMax;
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	Session session = getSession();
    	try {
    		if (!userIsOper()) {
    			parametros.put("validacion", Validacion.PUBLICA);
    		}
    		
    		String sql="from Personal perso ";
    		
    		Long idUA = (Long) parametros.get("unidadAdministrativa");
    		String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaFilles, uaMeves);
    		if (!StringUtils.isEmpty(uaQuery)) {
    			uaQuery = "where perso.unidadAdministrativa.id in (" + uaQuery + ") ";
    			sql += uaQuery;
    			// Se elimina para que no interfiera con los otros
    			parametros.remove("unidadAdministrativa");
    		}
    		
    		Long id = (Long) parametros.get("id");
    		if (id != null && !id.equals("")) {
    			sql += "and perso.id = " + id;
    			// Se elimina para que no interfiera con los otros
    			parametros.remove("id");
    		}
    		
    		List params = new ArrayList();
    		String sQuery = populateQuery(parametros, params, "or");
    		if (params.size() > 0) {
        		sql += " and (" + sQuery + ") ";
    		}
    		
    		sql += " order by ltrim(perso.nombre) ASC ";
    		Query query = session.createQuery(sql);
    		for (int i = 0; i < params.size(); i++) {
    			Object o = params.get(i);
    			query.setParameter(i, o);
    		}
    		
    		resultadoBusqueda.setTotalResultados(query.list().size());
    		if (resultadosMax != RESULTATS_CERCA_TOTS) {
    			query.setFirstResult(primerResultado);
    			query.setMaxResults(resultadosMax);
    		}
    		
    		resultadoBusqueda.setListaResultados(query.list());
    		return resultadoBusqueda;
    		
    	} catch (DelegateException de) {
    		throw new EJBException(de);
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
    
    /**
     * Construye el query de b�squeda segun los parametros
     */
    private String populateQuery(Map parametros,  List params, String conector) {
        String aux = "";	// Debe ser una AND o una OR

        // Tratamiento de parametros
        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (value != null&&(!value.equals(""))) {
                if (aux.length() > 0) aux = aux + " " + conector;
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
