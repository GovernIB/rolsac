package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;

import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;

import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.*;

/**
 * SessionBean per mantenir i consultar el Cataleg de Documents
 *
 * @ejb.bean
 *  name="sac/persistence/CatalegDocumentsFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.catalegDocumentsFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class CatalegDocumentsFacadeEJB extends HibernateEJB {

    /**
     * Obtiene referència al ejb de control d'Accés.
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
     * Crea o actualiza un Documento del Cataleg
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public Long gravarDocumentCataleg(CatalegDocuments docCataleg) {
        Session session = getSession();
        try {
            session.saveOrUpdate(docCataleg);
            session.flush();
            return docCataleg.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtenir un Document del Cataleg
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public CatalegDocuments obtenirDocumentoCataleg(Long id) {
        Session session = getSession();
        try {
            CatalegDocuments docCataleg = (CatalegDocuments) session.load(CatalegDocuments.class, id);
            Hibernate.initialize(docCataleg.getExcepcioDocumentacio());
            return docCataleg;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    /**
     *  Llista tots els Documents del Cataleg paginadament
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
     public ResultadoBusqueda llistarCatalegDocuments(int pagina, int resultats) {
       return listarTablaMaestraPaginada(pagina, resultats, llistarCatalegDocuments());
     }
     
    /**
     * Llista tots els Documents del Cataleg
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List llistarCatalegDocuments() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(CatalegDocuments.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtenir una llista de Documents del Cataleg
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List cercarCatalegDocuments(Map parametros, Map traduccion) {
        Session session = getSession();
        try {
            List params = new ArrayList();
            String sQuery = populateQuery(parametros, traduccion, params);

            Query query = session.createQuery("from CatalegDocuments as catDoc, catDoc.traducciones as trad where " + sQuery);
            
            for (int i = 0; i < params.size(); i++) {
                String o = (String)params.get(i);
                query.setString(i, o);
            }
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtenir una llista de Documents del Cataleg amb multiidioma
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public ResultadoBusqueda cercarDocumentsCatalegAmbMultiidioma(Map parametros, Map traduccion, Long idExcepcio,String pagina, String resultats) {
        Session session = getSession();
        try {
            List params = new ArrayList();
            
            String mainQuery ="select distinct catDoc from CatalegDocuments as catDoc, catDoc.traducciones as trad";
            String i18nQuery = "";
            String excepcioQuery = "";
            
            if (traduccion.get("idioma") != null) {
                i18nQuery = populateQuery(parametros, traduccion, params);
            } else {
                String paramsQuery = populateQuery(parametros, new HashMap(), params);
                if (paramsQuery.length() != 0) {
                    i18nQuery += paramsQuery + " and ";
                }
                i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ")";
            }
            
            if(idExcepcio != null){
              mainQuery += ",catDoc.excepcioDocumentacio as ex ";  
              excepcioQuery = " and ex.id = ? ";
              params.add(idExcepcio);
            } 
                                   
            Query query = session.createQuery(mainQuery + " where " + i18nQuery +  excepcioQuery);
            for (int i = 0; i < params.size(); i++) {
              Object value = params.get(i);
              if (value instanceof String){
                query.setString(i, (String)value);
              }else {
                query.setLong(i, (Long)value);
              }
            }
            
            return listarTablaMaestraPaginada(Integer.valueOf(pagina), Integer.valueOf(resultats), query.list());
            

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * esborra un Document del Cataleg
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void esborrarDocumentCataleg(Long id) {
        Session session = getSession();
        try {
            CatalegDocuments docCat = (CatalegDocuments) session.load(CatalegDocuments.class, id);
            Set docTramit = docCat.getDoctramite();
            if (!docTramit.isEmpty()){
            	throw new EJBException("El tipus de document està relacionat amb algun tramit");
            }
            session.delete(docCat);
            session.flush();
            Actualizador.borrar(docCat);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Construeix la query de cerca segons els parametres
     */
    private String populateQuery(Map parametros, Map traduccion, List params) {
        String aux = "";

        // Tratamiento de parametros
        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (value != null) {
                if (aux.length() > 0) aux = aux + " and ";
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( catDoc." + key + " ) like ? " ;
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( catDoc." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + "catDoc." + key + " =  ? ";
                    params.add(value);
                }
            }
        }

        // Tratamiento de traducciones
        if (!traduccion.isEmpty()) {
	        if (aux.length() > 0) aux = aux + " and ";
	        aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
	        traduccion.remove("idioma");
        }
        for (Iterator iter2 = traduccion.keySet().iterator(); iter2.hasNext();) {
            String key = (String) iter2.next();
            Object value = traduccion.get(key);
            if (value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " and upper( trad." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " and upper( trad." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + " and trad." + key + " =  ? ";
                    params.add(value);
                }
            }
        }

        return aux;
    }
    
    
    /**
     * Construeix la query de cerca multiidioma amb tots els camps
     */
    private String i18nPopulateQuery(Map traducciones, List params) {
        String aux = "";

        for (Iterator iterTraducciones = traducciones.keySet().iterator(); iterTraducciones.hasNext();) {
            String key = (String) iterTraducciones.next();
            Object value = traducciones.get(key);
            if (value != null) {
                if (aux.length() > 0) aux = aux + " or ";
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( trad." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( trad." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else {
                    aux = aux + " trad." + key + " = ? ";
                    params.add(value);
                }
            }
        }

        return aux;
    }
    
}
