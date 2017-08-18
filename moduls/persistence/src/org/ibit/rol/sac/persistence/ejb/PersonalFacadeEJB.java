package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
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
     * 
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
     /**
     * Crea o actualiza un Personal.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param personal	Indica el personal a grabar.
     * 
     * @param idUA	Identificador de la unidad administrativa.
     * 
     * @return	Devuelve el identificador del personal guardado.
     */
    public Long grabarPersonal(Personal personal, Long idUA) {
    	
        Session session = getSession();
        
        try {
        	
            if ( personal.getId() == null ) {
            	
                if ( !getAccesoManager().tieneAccesoUnidad( idUA, true ) ) {
                	
                    throw new SecurityException("No tiene acceso a la unidad");
                    
                }
                
            } else {
            	
            	if ( !getAccesoManager().tieneAccesoPersonal( personal.getId() ) ) {
            		
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
     * Obtiene un Personal.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param id	Identificador del personal.
     * 
     * @return Devuelve <code>Personal</code> solicitado.
     */
    public Personal obtenerPersonal(Long id) {
    	
        Session session = getSession();
        
        try {
        	
        	Personal personal = (Personal) session.load( Personal.class, id );
        	Hibernate.initialize( personal.getUnidadAdministrativa() );
        	
            return personal;
            
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
    public ResultadoBusqueda buscadorListarPersonal(Map parametros, int pagina, int resultats, boolean uaFilles, boolean uaMeves, boolean soloIds) {
    	
    	int resultadosMax = new Integer(resultats).intValue();
    	int primerResultado = new Integer(pagina).intValue() * resultadosMax;
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	Session session = getSession();
    	
    	try {
//    		if ( !userIsOper() )
//    			parametros.put("validacion", Validacion.PUBLICA);
//    		
    		String campoOrden = null,  tipoOrden = null ;
    		
    		if (parametros.get("ordreCamp") != null) {
    			campoOrden = parametros.get("ordreCamp").toString();
    		}
    		
    		if (parametros.get("ordreTipus") != null) {
        		tipoOrden = parametros.get("ordreTipus").toString();
    		}
            
    		String sql;
    		if (soloIds) {
    			sql = "Select perso.id, perso."+campoOrden+" from Personal perso ";
    		} else {
    			sql = "from Personal perso ";
    		}
    		Long idUA = (Long) parametros.get("unidadAdministrativa");
    		String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaFilles, uaMeves);
    		if (!StringUtils.isEmpty(uaQuery)) {
    			uaQuery = "where perso.unidadAdministrativa.id in (" + uaQuery + ") ";
    			sql += uaQuery;
    			// Se elimina para que no interfiera con los otros
    			parametros.remove("unidadAdministrativa");
    		}
    		
    		List params = new ArrayList();
    		String sQuery = populateQuery(parametros, params, "or");
    		if (params.size() > 0) {
        		sql += " and (" + sQuery + ") ";
    		}
    		
    		if (campoOrden == null || "id".equals(campoOrden)) {
    			sql += " order by perso.id ";
    			if (tipoOrden == null) {
    				sql += " ASC ";
    			} else {
    				sql += tipoOrden;	
    			}
    		} else {
    			sql += " order by perso." + campoOrden+" ";
    			if (tipoOrden == null) {
    				sql += " ASC ";
    			} else {
    				sql += tipoOrden;	
    			}
    			sql +=" , perso.id ASC ";
    		}
    		Query query = session.createQuery(sql);
    		for ( int i = 0 ; i < params.size() ; i++ ) {
    			Object o = params.get(i);
    			query.setParameter(i, o);
    		}
    		
    		resultadoBusqueda.setTotalResultados( query.list().size() );
    		if ( resultadosMax != RESULTATS_CERCA_TOTS ) {
    			
    			query.setFirstResult(primerResultado);
    			query.setMaxResults(resultadosMax);
    		}
    		
    		resultadoBusqueda.setListaResultados( query.list() );
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
     * Construye el query de busqueda segun los parametros
     */
    private String populateQuery(Map parametros,  List params, String conector) {
        String aux = "";	// Debe ser una AND o una OR

        // Tratamiento de parametros
        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            if ("ordreTipus".equals(key) || "ordreCamp".equals(key)) { continue;}
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
     *  @deprecated	Se usa desde el back antiguo.
     * Lista el personal de la Unidad Administrativa
     * @throws DelegateException 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Set listarPersonalUA(Long unidadAdmin_id) throws DelegateException {
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
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * 
     * @param id	Identificador del personal a borrar.
     */
    public void borrarPersonal(Long id) {
    	
        Session session = getSession();
        
        try {
        	
            if ( !getAccesoManager().tieneAccesoPersonal(id) ) {
            	
                throw new SecurityException("No tiene acceso al personal");
                
            }
            
            Personal persona = (Personal) session.load( Personal.class, id );
            persona.getUnidadAdministrativa().removePersonal(persona);
            session.delete(persona);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    /**
     * Lista el personal (buscador del nuevo backoffice)
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     * @param personal	Indica una instancia de tipo personal a buscar.
     * @param idUA	Identificador de la unidad administrativa consultada.
     * @param pagina	Indica la última página consultada del listado.
     * @param resultats	Indica el número de resultados por página.
     * @param uaHijas	Indica si queremos añadir al rango de búsqueda las unidades administrativas hijas de la UA consultada.
     * @param uaPropias	Indica si queremos añadir al rango de búsqueda las unidades administrativas propias de la UA consultada.
     */ 
    public ResultadoBusqueda buscadorListarPersonal(Personal personal, Long idUA, boolean uaHijas, boolean uaPropias, PaginacionCriteria paginacion) {
    	
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	Session session = getSession();
    	
    	try {
    		
    		String numeroLargoPublico = personal.getNumeroLargoPublico();
    		String numeroLargoPrivado = personal.getNumeroLargoPrivado();
    		String nombre = personal.getNombre();
    		String funciones = personal.getFunciones();
    		String extensionPublica = personal.getExtensionPublica();
    		String numeroLargoMovil = personal.getNumeroLargoMovil();
    		String username = personal.getUsername();
    		String extensionPrivada = personal.getExtensionPrivada();
    		String email = personal.getEmail();
    		String cargo = personal.getCargo();
    		String extensionMovil = personal.getExtensionMovil();
    		
    		// Contrucción de la consulta
    		StringBuilder consulta = new StringBuilder(" from Personal perso where");

    		if (numeroLargoPublico != null && !"".equals(numeroLargoPublico)) {
    		    consulta.append(" or upper( perso.numeroLargoPublico ) like upper(:numeroLargoPublico) ");
    		}
    		if (numeroLargoPrivado != null && !"".equals(numeroLargoPrivado)) {
    		    consulta.append(" or upper( perso.numeroLargoPrivado ) like upper(:numeroLargoPrivado) ");
    		}
    		if (nombre != null && !"".equals(nombre)) {
    		    consulta.append(" or upper( perso.nombre ) like upper(:nombre) ");
    		}
    		if (funciones != null && !"".equals(funciones)) {
    		    consulta.append(" or upper( perso.funciones ) like upper(:funciones) ");
    		}
    		if (extensionPublica != null && !"".equals(extensionPublica)) {
    		    consulta.append(" or upper( perso.extensionPublica ) like upper(:extensionPublica) ");
    		}
    		if (numeroLargoMovil != null && !"".equals(numeroLargoMovil)) {
    		    consulta.append(" or upper( perso.numeroLargoMovil ) like upper(:numeroLargoMovil) ");
    		}
    		if (username != null && !"".equals(username)) {
    		    consulta.append(" or upper( perso.username ) like upper(:username) ");
    		}
    		if (extensionPrivada != null && !"".equals(extensionPrivada)) {
    		    consulta.append(" or upper( perso.extensionPrivada ) like upper(:extensionPrivada ) ");
    		}
    		if (email != null && !"".equals(email)) {
    		    consulta.append(" or upper( perso.email ) like upper(:email) ");
    		}
    		if (cargo != null && !"".equals(cargo)) {
    		    consulta.append(" or upper( perso.cargo ) like upper(:cargo) ");
    		}
    		if (extensionMovil != null && !"".equals(extensionMovil)) {
    		    consulta.append(" or upper( perso.extensionMovil ) like upper(:extensionMovil) ");
    		}
    		
    		/*TODO 03/09/2013: Desactivada validación ya que se detectó en el algotritmo antiguo se añadía 
    		pero no existe este campo para la entidad Personal, se tiene que añadir una 'join' con su unidad 
    		administrativa para filtrar por el campo validación de la UA?    		
    		if ( !userIsOper() )
				consulta.append(" ");//añadir validacion??
				//parametros.put("validacion", Validacion.PUBLICA);*/

    		//Obtenemos los identificadores de las UAhijas y/o propias de la UA actual.
    		String listaUA = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaHijas, uaPropias);
    		if (!StringUtils.isEmpty(listaUA)) {
    		    consulta.append(" and ( perso.unidadAdministrativa in (:listaUA))");
    		}
    			
    		consulta.append(" order by ltrim(perso.nombre) asc ");

    		Query query = session.createQuery(consulta.toString());

    		if (numeroLargoPublico != null && !"".equals(numeroLargoPublico)) {
    		    query.setParameter("numeroLargoPublico", "%" + numeroLargoPublico + "%");
    		}
    		if (numeroLargoPrivado != null && !"".equals(numeroLargoPrivado)) {
    		    query.setParameter("numeroLargoPrivado", "%" + numeroLargoPrivado + "%");
    		}
    		if (nombre != null && !"".equals(nombre)) {
    		    query.setParameter("nombre", "%" + nombre + "%");
    		}
    		if (funciones != null && !"".equals(funciones)) {
    		    query.setParameter("funciones", "%" + funciones + "%");
    		}
    		if (extensionPublica != null && !"".equals(extensionPublica)) {
    		    query.setParameter("extensionPublica", "%" + extensionPublica + "%");
    		}
    		if (numeroLargoMovil != null && !"".equals(numeroLargoMovil)) {
    		    query.setParameter("numeroLargoMovil", "%" + numeroLargoMovil + "%");
    		}
    		if (username != null && !"".equals(username)) {
    		    query.setParameter("username", "%" + username + "%");
    		}
    		if (extensionPrivada != null && !"".equals(extensionPrivada)) {
    		    query.setParameter("extensionPrivada", "%" +  extensionPrivada + "%");
    		}
    		if (email != null && !"".equals(email)) {
    		    query.setParameter("email", "%" + email + "%");
    		}
    		if (cargo != null && !"".equals(cargo)) {
    		    query.setParameter("cargo", "%" + cargo + "%");
    		}
    		if (extensionMovil != null && !"".equals(extensionMovil)) {
    		    query.setParameter("extensionMovil", "%" + extensionMovil + "%");
    		}
    		if (!StringUtils.isEmpty(listaUA)) {query.setParameter("listaUA", listaUA);
    		
    		}

    		/*TODO 03/09/2013: Desactivada validación ya que se detectó en el algotritmo antiguo se añadía 
    		pero no existe este campo para la entidad Personal, se tiene que añadir una 'join' con su unidad 
    		administrativa para filtrar por el campo validación de la UA?  
    		if ( !userIsOper() )
    			query.setParameter("validacion", Validacion.PUBLICA);*/
    		

    		resultadoBusqueda.setTotalResultados( query.list().size() );
    		resultadoBusqueda = PaginatedHibernateEJB.obtenerListadoPaginado(query, paginacion);

    	} catch (DelegateException de) {    		
    		throw new EJBException(de);
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    	
		return resultadoBusqueda;
    }
    
}
