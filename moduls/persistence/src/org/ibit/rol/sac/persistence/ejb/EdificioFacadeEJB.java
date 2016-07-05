package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Edificios.
 *
 * @ejb.bean
 *  name="sac/persistence/EdificioFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EdificioFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class EdificioFacadeEJB extends HibernateEJB
{
	/**
	 * Obtiene referencia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();
	
	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException
	{
		super.ejbCreate();
	}
	
	
	/**
	 * Crea o actualiza un Edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param edificio	Indica el edificio que se quiere guardar.
	 * @return Devuelve el identificador del edificio guardado.
	 */
	public Long grabarEdificio(Edificio edificio)
	{
		Session session = getSession();
		try {
			if (edificio.getId() != null) {
				if (!getAccesoManager().tieneAccesoEdificio(edificio.getId())) {
					throw new SecurityException("No tiene acceso al edificio.");
				}
			}
			session.saveOrUpdate(edificio);
			session.flush();
			if (!edificio.getUnidadesAdministrativas().isEmpty()) {
				Actualizador.actualizar(edificio);
			}
			return edificio.getId();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene un Edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador
	 * @return Devuelve <code>Edificio</code> solititado.
	 */
	public Edificio obtenerEdificio(Long id)
	{
		Session session = getSession();
		try {
			Edificio edificio = (Edificio) session.load(Edificio.class, id);
			Hibernate.initialize(edificio.getFotoGrande());
			Hibernate.initialize(edificio.getFotoPequenya());
			Hibernate.initialize(edificio.getPlano());
			Hibernate.initialize(edificio.getUnidadesAdministrativas());
			
			return edificio;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Busca todos los edificios que cumplen los criterios de busqueda del nuevo back (rolsacback).
	 *  
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda buscarEdificios(Map parametros, Map traduccion, Long idUA, boolean uaFilles, boolean uaMeves, String pagina,String resultats)
	{
		Session session = getSession();
		try {
			List params = new ArrayList();
			String i18nQuery = "";
			String mainQuery = "select distinct edificio from Edificio as edificio , edificio.traducciones as trad left outer join edificio.unidadesAdministrativas edua where ";
			
			if (traduccion.get("idioma") != null) {
				i18nQuery = populateQuery(parametros, traduccion, params);
			} else {
				String paramsQuery = populateQuery(parametros, new HashMap(), params);
				i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ") ";
			}
			
			String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaFilles, uaMeves);
			
			if (!StringUtils.isEmpty(uaQuery)) {
				uaQuery = " and edua.id in (" + uaQuery + ") ";
			}
			
			Query query = session.createQuery(mainQuery + i18nQuery + uaQuery);
			for (int i = 0; i < params.size(); i++) {
				Object o = params.get(i);
				query.setParameter(i, o);
			}
			
			int resultadosMax = new Integer(resultats).intValue();
			int primerResultado = new Integer(pagina).intValue() * resultadosMax;
			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
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
	 * Obtiene la foto pequenya del edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del edificio.
	 * @return Devuelve <code>Archivo</code> con la foto del edificio solicitado.
	 */
	public Archivo obtenerFotoPequenyaEdificio(Long id)
	{
		Session session = getSession();
		try {
			Edificio edificio = (Edificio) session.load(Edificio.class, id);
			Hibernate.initialize(edificio.getFotoPequenya());
			return edificio.getFotoPequenya();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene la foto grande del edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del edificio.
	 * @return Devuelve <code>Archivo</code> de la foto del edificio en formato grande.
	 */
	public Archivo obtenerFotoGrandeEdificio(Long id)
	{
		Session session = getSession();
		try {
			Edificio edificio = (Edificio) session.load(Edificio.class, id);
			Hibernate.initialize(edificio.getFotoGrande());
			return edificio.getFotoGrande();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Obtiene el plano del edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del edificio.
	 * @return Devuelve <code>Archivo</code> con el plano del edificio solicitado.
	 */
	public Archivo obtenerPlanoEdificio(Long id)
	{
		Session session = getSession();
		try {
			Edificio edificio = (Edificio) session.load(Edificio.class, id);
			Hibernate.initialize(edificio.getPlano());
			return edificio.getPlano();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Borra un edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param id	Identificador del edificio a borrar.
	 */
	public void borrarEdificio(Long id)
	{
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoEdificio(id)) {
				throw new SecurityException("No tiene acceso al edificio.");
			}
			
			Edificio edificio = (Edificio) session.load(Edificio.class, id);
			Iterator iterator = edificio.getUnidadesAdministrativas().iterator();
			while (iterator.hasNext()) {
				UnidadAdministrativa ua = (UnidadAdministrativa) iterator.next();
				if (!getAccesoManager().tieneAccesoUnidad(ua.getId(), true)) {
					throw new SecurityException("No tiene acceso a la unidad relacionada con el edificio.");
				}
				ua.getEdificios().remove(edificio);
			}
			
			session.delete(edificio);
			session.flush();
			Actualizador.borrar(edificio);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene los edificios de una Unidad.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador de la unidad administrativa.
	 * @return Devuelve <code>Set</code> con todos los edificios de la unidad administrativa solicitada.
	 */
	public Set listarEdificiosUnidad(Long id)
	{
		Session session = getSession();
		try {
			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			Hibernate.initialize(unidadAdministrativa.getEdificios());
			return unidadAdministrativa.getEdificios();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Construye el query de búsqueda segun los parametros
	 */
	private String populateQuery(Map parametros, Map traduccion, List params)
	{
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
							aux = aux + " upper( edificio." + key + " ) like ? " ;
							params.add(sValue);
						} else {
							aux = aux + " upper( edificio." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else {
					aux = aux + "edificio." + key + " =  ? ";
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
	 * Añade una nueva unidad.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * @param idUA	Identificador de la unidad administrativa.
	 * @param idEdificio	Identificador del edificio.
	 */
	public void anyadirUnidad(Long idUA, Long idEdificio)
	{
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoEdificio(idEdificio)) {
				throw new SecurityException("No tiene acceso al edificio");
			}
			
			Edificio edi = (Edificio) session.load(Edificio.class, idEdificio);
			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			edi.getUnidadesAdministrativas().add(unidad);
			unidad.getEdificios().add(edi);
			session.flush();
			Actualizador.actualizar(edi, unidad.getId());
			
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Elimina una unidad del edificio
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * @param idUA	Identificador de la unidad administrativa.
	 * @param idEdificio	Identificador del edificio.
	 */
	public void eliminarUnidad(Long idUA, Long idEdificio)
	{
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoEdificio(idEdificio)) {
				throw new SecurityException("No tiene acceso al edificio");
			}
			
			UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			Edificio edi = (Edificio) session.load(Edificio.class, idEdificio);
			edi.getUnidadesAdministrativas().remove(unidad);
			unidad.getEdificios().remove(edi);
			session.flush();
			Actualizador.borrar(edi, unidad.getId());
			
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
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
