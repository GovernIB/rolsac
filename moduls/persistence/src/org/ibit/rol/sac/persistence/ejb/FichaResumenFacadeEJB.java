package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

	private static final long serialVersionUID = 1L;
		
    /**
     * Obtiene referéncia al ejb de control de Acceso.
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
     * Busca todas las fichas que cumplen los criterios de busqueda del nuevo back (rolsacback). 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria,
			Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, 
			int campoVisible, boolean isSoloIds) {
		
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
            String camposVariosQuery = "";
            String selectResults;
            if (isSoloIds) {
            	selectResults = "select distinct ficha.id, ficha."+campoOrdenacion+" ";
            } else {
            	selectResults = "select distinct ficha ";
            }
            String selectCount = "select count (distinct ficha) ";
            String mainQuery = "from FichaResumen as ficha, ficha.traducciones as trad, ficha.fichasua as fsua ";
            
            // Contemplamos los campos urlVideo, urlForo, foro_tema, info, responsable junto a los campos traducibles.
            String[] camposVarios = {"urlVideo", "urlForo", "foro_tema", "info", "responsable"};
            
            // Guardamos valor del texto de búsqueda si éste existe. Lo borramos del mapa de parámetros para que la lógica
            // de construcción de i18nQuery siga funcionando sin problemas.
            String textoBusqueda = null;
            if (parametros.get("textes") != null) {
            	
            	textoBusqueda = (String)parametros.get("textes");
            	parametros.remove("textes");
            	
            	if (textoBusqueda != null) {
                	
    	            camposVariosQuery = " (";
    	            String or = "";
    	            
    	            for (String campo : camposVarios) {
    	            
    	            	camposVariosQuery += or + " upper(ficha." + campo + ") like upper('%" + textoBusqueda + "%') ";
    	            	
    	            	if ("".equals(or))
    	            		or = " or ";
    	
    	            }
    	            
    	            camposVariosQuery += ") ";
    	                            
                }
            	
            }
            
			if (traduccion.get("idioma") != null) {
				i18nQuery = populateQuery(parametros, traduccion, params);
			} else {
				String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if (paramsQuery.length() == 0) {
					i18nQuery += " where ";
				} else {
					i18nQuery += paramsQuery + " and ";
				}
				i18nQuery += "(" + i18nPopulateQuery(traduccion, params, camposVariosQuery) + ") ";
			}
            
            if (campoVisible == 1) {
            	i18nQuery += " and (sysdate < ficha.fechaCaducidad or ficha.fechaCaducidad is null) ";
				i18nQuery += " and (sysdate > ficha.fechaPublicacion or ficha.fechaPublicacion is null) ";
			} else if (campoVisible == 2) {
				i18nQuery += " and (sysdate > ficha.fechaCaducidad or sysdate < ficha.fechaPublicacion or ficha.validacion = 2 or ficha.validacion = 3) ";
			}
            
            String orderBy = "";
            if (campoOrdenacion != null && orden != null) { 
            	orderBy = " order by ficha." + campoOrdenacion + " " + orden + " ";
             	if (campoOrdenacion != null && !"id".equals(campoOrdenacion)) { 
             		orderBy += " , ficha.id ASC ";	
             	}
             	
             }
            
			Long idUA = (ua != null) ? ua.getId() : null;
            String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA( idUA, uaFilles, uaMeves );
            
            if ( !StringUtils.isEmpty(uaQuery) ) {
            	uaQuery = " and fsua.idUa in (" + uaQuery + ") ";
            }
            
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
            
            String accesoQuery = "";
            if (userIsOper()) {
            	
				//Filtrar por las unidades a que el usuario tiene acceso:

				//tieneAccesoValidable
				if (!userIsSuper()) {
					accesoQuery += " and ficha.validacion = " + Validacion.INTERNA;
				}

				// tieneAcceso a fichaUA
				if (!userIsSystem()) {
					//debe tener acceso a la UA y a la seccion
					
		            if ( StringUtils.isEmpty(uaQuery) ) { //Se está buscando en todas las unidades orgánicas            	
						uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(null, true, true);
			            if ( !StringUtils.isEmpty(uaQuery) ) {        	
			            	uaQuery = " and fsua.idUa in (" + uaQuery + ") ";
			            } else {
			            	//Esto significa que el usuario no tiene ninguna unidad administrativa configurada, y 
			            	//no es system. Por lo que en realidad no hace falta ejecutar nada, sino más bien devolver
			            	//un resultado vacío
			    			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();			
			    			resultadoBusqueda.setTotalResultados(0);
			    			resultadoBusqueda.setListaResultados(new ArrayList<FichaResumen>());
			    			return resultadoBusqueda;			            	
			            }
					}
		            
		            String seccionList = DelegateUtil.getSeccionDelegate().obtenerCadenaFiltroSeccion();
		            if ( !StringUtils.isEmpty(seccionList) ) {
		            	accesoQuery = " and fsua.idSeccio in (" + seccionList + ") ";
		            } else {
		            	//Si el usuario no tiene acceso a ninguna sección, entonces no hace falta ejecutar nada, sino más bien 
		            	//devolver un resultado vacío
		    			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();			
		    			resultadoBusqueda.setTotalResultados(0);
		    			resultadoBusqueda.setListaResultados(new ArrayList<FichaResumen>());
		    			return resultadoBusqueda;	            	
		            }
		            
				}
				
            }
            
            Query query = session.createQuery(selectResults + mainQuery + i18nQuery + uaQuery + accesoQuery + fetVitalQuery + materiaQuery + publicQuery + orderBy);
            Query countQuery = session.createQuery(selectCount + mainQuery + i18nQuery + uaQuery + accesoQuery + fetVitalQuery + materiaQuery + publicQuery);
            
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                query.setParameter(i, o);
                countQuery.setParameter(i, o);
            }
            
            int resultadosMax = new Integer(resultats).intValue();
			int primerResultado = new Integer(pagina).intValue() * resultadosMax;
			
			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
			
			resultadoBusqueda.setTotalResultados( (Integer) countQuery.uniqueResult() );
            	
			if ( resultadosMax != RESULTATS_CERCA_TOTS) {
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
     * Construye el query de búsqueda segun los parametros
     */
	private String populateQuery(Map parametros, Map traduccion, List params) {
		
		String aux = "";

		Iterator iter1 = parametros.keySet().iterator();
		
		while ( iter1.hasNext() ) {
			
			String key = (String) iter1.next();
			Object value = parametros.get(key);
			
			if (!key.startsWith("ordre") && value != null) {
				
				if (value instanceof String) {
					
					String sValue = (String)value;
					
					if (sValue.length() > 0) {
						if (aux.length() > 0)
							aux = aux + " and ";
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( ficha." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( ficha." + key + " ) like ? ";
							params.add("%" + sValue + "%");
						}
					}
					
				} else if (value instanceof Date) {
					
					if (aux.length() > 0)
						aux = aux + " and ";
					
					aux = aux + "ficha." + key + " = '" + value + "'";
					
				} else {
					
					if (aux.length() > 0)
						aux = aux + " and ";
					
					aux = aux + "ficha." + key + " = " + value;
					
				}
				
			}
			
		}

		// Tratamiento de traducciones
		if (!traduccion.isEmpty()) {
			
			if (aux.length() > 0)
				aux = aux + " and ";
			
			aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
			traduccion.remove("idioma");
			
		}
		
		Iterator iter2 = traduccion.keySet().iterator();
		
		while ( iter2.hasNext() ) {
			
			String key = (String) iter2.next();
			Object value = traduccion.get(key);
			
			if (value != null) {
				
				if (value instanceof String) {
					
					String sValue = (String)value;
					
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " and upper( trad." + key
									+ " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " and upper( trad." + key
									+ " ) like ? ";
							params.add("%" + sValue + "%");
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
    private String i18nPopulateQuery(Map traducciones, List params, String camposVariosQuery) {
    	
        String aux = "";
        
        // amartin: Añadimos estas condiciones a la query de idiomas, ya que por cómo se construye, es lo más rápido y
        // limpio que podemos concatenar.
		if (camposVariosQuery != null && !"".equals(camposVariosQuery)) {
			
			aux += camposVariosQuery;
			
		}

		Iterator iterTraducciones = traducciones.keySet().iterator(); 
		
		while ( iterTraducciones.hasNext() ) {
			
			String key = (String) iterTraducciones.next();
			Object value = traducciones.get(key);
			
			if (value != null) {
				
				if (aux.length() > 0)
					aux = aux + " or ";
				
				if (value instanceof String) {
					
					String sValue = (String) value;
					
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( trad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( trad." + key + " ) like ? ";
							params.add("%" + sValue + "%");
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
