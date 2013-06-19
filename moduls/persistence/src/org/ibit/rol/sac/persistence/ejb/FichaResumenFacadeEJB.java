package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.FichaResumen;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Fichas con la información justa(PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/FichaResumenFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.FichaResumenFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class FichaResumenFacadeEJB extends HibernateEJB {

	protected Hashtable contenidos_web; // contiene url y su contenido para agilizar el proceso de indexacion de fichas
	
    /**
     * Obtiene referéncia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * Ubicació del directori de Lucene a emprar.
     * @ejb.env-entry value="${index.crawler.location}"
     */
    protected String indexCrawlerLocation;
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Busca todas las fichas que cumplen los criterios de busqueda del nuevo back (sacback2). 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion,
			UnidadAdministrativa ua, Long idFetVital, Long idMateria,
			Long idPublic, boolean uaFilles, boolean uaMeves,
			String campoOrdenacion, String orden, String pagina,
			String resultats, int campoVisible) {
		
        Session session = getSession();
        
        try {
            if (!userIsOper()) {
                parametros.put("validacion", Validacion.PUBLICA);
            }
            
            List params = new ArrayList();
            String i18nQuery = "";
            String fetVitalQuery = "";            
            String materiaQuery = "";
            String publicQuery = "";
            String mainQuery = "select distinct ficha from FichaResumen as ficha, ficha.traducciones as trad, ficha.fichasua as fsua ";
            
            if (traduccion.get("idioma") != null) {
                i18nQuery = populateQuery(parametros, traduccion, params);
            } else {
				String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if (paramsQuery.length() == 0) {
					i18nQuery += " where ";
				} else {
					i18nQuery += paramsQuery + " and ";
				}
				i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ") ";
            }
            
            if (campoVisible == 1) {
            	i18nQuery += " and (sysdate < ficha.fechaCaducidad or ficha.fechaCaducidad is null) ";
				i18nQuery += " and (sysdate > ficha.fechaPublicacion or ficha.fechaPublicacion is null) ";
			} else if (campoVisible == 2) {
				i18nQuery += " and (sysdate > ficha.fechaCaducidad or sysdate < ficha.fechaPublicacion or ficha.validacion = 2 or ficha.validacion = 3) ";
			}
            
            String orderBy = "";
            if (campoOrdenacion != null && orden != null) orderBy = " order by ficha." + campoOrdenacion + " " + orden;
            
			Long idUA = (ua != null) ? ua.getId() : null;
            String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA( idUA, uaFilles, uaMeves );
            
            if ( !StringUtils.isEmpty(uaQuery) )
            	uaQuery = " and fsua.idUa in (" + uaQuery + ") ";
            
            if ( idFetVital != null ) {
            	mainQuery += ",ficha.hechosVitales as hec ";
            	fetVitalQuery = " and hec.id = ? ";
            	params.add( idFetVital );
            }
            
            if ( idMateria != null ) {
            	mainQuery += ",ficha.materias as mat ";
            	materiaQuery = " and mat.id = ? ";
            	params.add( idMateria );
            }
            
            if ( idPublic != null ) {
            	mainQuery += ",ficha.publicosObjetivo as pob ";  
                publicQuery = " and pob.id = ? ";
                params.add(idPublic);
            }
            
            Query query = session.createQuery(mainQuery + i18nQuery + uaQuery + fetVitalQuery + materiaQuery + publicQuery + orderBy);
//            Query query = session.createQuery(mainQuery + i18nQuery + fetVitalQuery + materiaQuery + publicQuery + orderBy);
            
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                query.setParameter(i, o);
            }
            
            int resultadosMax = new Integer(resultats).intValue();
			int primerResultado = new Integer(pagina).intValue() * resultadosMax;
			
			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
			
            if (!userIsOper()) {
            	
                resultadoBusqueda.setTotalResultados( query.list().size() );
                
    			if ( resultadosMax != RESULTATS_CERCA_TOTS) {
    				query.setFirstResult(primerResultado);
    				query.setMaxResults(resultadosMax);
    			}
            	
    			resultadoBusqueda.setListaResultados(query.list());
                
            } else {
            	
    			List<FichaResumen> fichasResumen = query.list();
    			
                List<FichaResumen> fichasAcceso = new ArrayList<FichaResumen>();
                Usuario usuario = getUsuario(session);
                
                // Procesar todas las fichas para saber el total y 
                // aprovechar el bucle para ir guardando el número 
                // de fichas solicitadas según los parámetros de paginación.                
                int contadorTotales = 0;
                int fichasInsertadas = 0;
                int iteraciones = 0;
                
                for ( FichaResumen fichaResumen : fichasResumen ) {
                	if ( tieneAcceso(usuario, fichaResumen) ) {
                		if ( fichasInsertadas != resultadosMax && iteraciones >= primerResultado ) { 
                			fichasAcceso.add(fichaResumen);
                			fichasInsertadas++;
                		}
                		contadorTotales++;
                	}
                	iteraciones++;
                }
                
                resultadoBusqueda.setTotalResultados( contadorTotales );
                resultadoBusqueda.setListaResultados( fichasAcceso );
                
            }
            
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
     * Construye el query de búsqueda segun los parametros
     */
    private String populateQuery(Map parametros, Map traduccion, List params) {
        String aux = "";

        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (!key.startsWith("ordre") && value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (aux.length() > 0) aux = aux + " and ";
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( ficha." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( ficha." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else if (value instanceof Date) {
                    if (aux.length() > 0) aux = aux + " and ";
                    aux = aux + "ficha." + key + " = '" + value + "'";
                } else {
                    if (aux.length() > 0) aux = aux + " and ";
                    aux = aux + "ficha." + key + " = " + value;
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
                    aux = aux + " and trad." + key + " = ? ";
                    params.add(value);
                }
            }
        }

        if (aux.length() > 0) {
            aux = "where " + aux;
        }
        return aux;
    }
    
	 /**
     * Construye el query de búsqueda multiidioma en todos los campos
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
