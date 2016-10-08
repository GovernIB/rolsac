package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.FetchMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoNormativa;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.webcaib.NormativaModel;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.IndexData;
import es.caib.solr.api.model.IndexFile;
import es.caib.solr.api.model.MultilangLiteral;
import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;
import es.caib.solr.api.util.Utilidades;


/**
 * SessionBean para mantener y consultar Normativa.
 *
 * @ejb.bean
 *  name="sac/persistence/NormativaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.NormativaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class NormativaFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = -1519489071805481782L;

	private static String idioma_per_defecte ="ca";

	/**
	 * Obtiene referéncia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Autoriza la creación de una normativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaCrearNormativa(Integer validacionNormativa) throws SecurityException  {
		return !(validacionNormativa.equals(Validacion.PUBLICA) && !userIsSuper()); 
	}


	/**
	 * Autoriza la modificación de una normativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaModificarNormativa(Long idNormativa) throws SecurityException {
		return (getAccesoManager().tieneAccesoNormativa(idNormativa)); 
	}  

	/**
	 * Crea o actualiza una Normativa local.
	 * @throws DelegateException 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public Long grabarNormativaLocal(NormativaLocal normativa, Long idUA) throws DelegateException {
		Session session = getSession();
		try {
			if (normativa.getId() == null) {
				if (normativa.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
					throw new SecurityException("No puede crear una normativa pï¿½blica");
				}
			} else {
				if (!getAccesoManager().tieneAccesoNormativa(normativa.getId())) {
					throw new SecurityException("No tiene acceso a la normativa");
				}
			}

			if (idUA != null) {
				if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
					throw new SecurityException("No tiene acceso a la unidad");
				}
				UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
				normativa.setUnidadAdministrativa(unidad);
			}

			if (normativa.getId() == null) {
				session.save(normativa);
				addOperacion(session, normativa, Auditoria.INSERTAR);
			} else {
				session.update(normativa);
				addOperacion(session, normativa, Auditoria.MODIFICAR);
			}
			session.flush();
			
			//SOLR Indexar normativa
			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
		    solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_NORMATIVA.toString(), normativa.getId(), 1l);
		    session.flush();
		    
			return normativa.getId();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Crea o actualiza una Normativa.
	 * @throws DelegateException 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public Long grabarNormativaExterna(NormativaExterna normativa) throws DelegateException {
		Session session = getSession();
		try {
			if (normativa.getId() == null) {
				if (normativa.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
					throw new SecurityException("No puede crear una normativa pï¿½blica");
				}
				session.save(normativa);
				addOperacion(session, normativa, Auditoria.INSERTAR);
			} else {
				if (!getAccesoManager().tieneAccesoNormativa(normativa.getId())) {
					throw new SecurityException("No tiene acceso a la normativa");
				}
				session.update(normativa);
				addOperacion(session, normativa, Auditoria.MODIFICAR);
			}
			session.flush();
			
			//SOLR Indexar normativa
			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
		    solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_NORMATIVA.toString(), normativa.getId(), 1l);
		    session.flush();
		    
			return normativa.getId();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista todas las normativas.
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List listarNormativas() {
		Session session = getSession();
		try {
			Criteria criteri1 = session.createCriteria(NormativaLocal.class);
			criteri1.setFetchMode("traducciones", FetchMode.EAGER);
			Criteria criteri2 = session.createCriteria(NormativaExterna.class);
			criteri2.setFetchMode("traducciones", FetchMode.EAGER);
			List aux = criteri1.list();
			aux.addAll(criteri2.list());
			return aux;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una normativa.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Normativa obtenerNormativa(Long id) {
		Session session = getSession();
		try {
			Normativa normativa = (Normativa) session.load(Normativa.class, id);
			for (Iterator iterator = normativa.getLangs().iterator(); iterator.hasNext();) {
				String lang = (String) iterator.next();
				TraduccionNormativa traduccion = (TraduccionNormativa) normativa.getTraduccion(lang);
				if (traduccion!=null)  Hibernate.initialize(traduccion.getArchivo());
			}
			Hibernate.initialize(normativa.getAfectadas());
			Hibernate.initialize(normativa.getAfectantes());
			Hibernate.initialize(normativa.getProcedimientos());
			return normativa;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Busca todas las Normativas que cumplen los criterios de bï¿½squeda
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List buscarNormativas(Map parametros, Map traduccion, String tipo) {
		Session session = getSession();
		try {
			List params = new ArrayList();
			String sQuery = populateQuery(parametros, traduccion, params);

			Query query;
			if ("local".equals(tipo)) {
				// Eliminado "left join fetch" por problemas en el cache de traducciones.
				query = session.createQuery("from NormativaLocal as normativa, normativa.traducciones as trad where " + sQuery);                
			} else { // "externa".equals(tipo))
				// Eliminado "left join fetch" por problemas en el cache de traducciones.
				query = session.createQuery("from NormativaExterna as normativa, normativa.traducciones as trad where " + sQuery);
			}
			for (int i = 0; i < params.size(); i++) {
				Object o = params.get(i);
				query.setParameter(i, o);
			}

			List normativas = query.list();

			List normativasAcceso = new ArrayList();
			Usuario usuario = getUsuario(session);
			for (int i = 0; i < normativas.size(); i++) {
				if("local".equals(tipo)){
					NormativaLocal normativa =  (NormativaLocal)normativas.get(i);
					if(tieneAcceso(usuario, normativa)){
						normativasAcceso.add(normativa);
					}
				} else{
					NormativaExterna normativa =  (NormativaExterna)normativas.get(i);
					if(tieneAcceso(usuario, normativa)){
						normativasAcceso.add(normativa);
					}
				}
			}

			return normativasAcceso;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Busca todas las Normativas que cumplen los criterios de búsqueda aplicando el orden indicado.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ResultadoBusqueda buscarNormativas(Map parametros, Map traduccion, String tipo,
			Long idUA, boolean uaMeves, boolean uaFilles,
			String campoOrdenacion, String orden, String pagina,
			String resultats) {

		Session session = getSession();

		try {

			List params = new ArrayList();            
			String sQuery = "";

			if (traduccion.get("idioma") != null) {
				sQuery = populateQuery(parametros, traduccion, params);
			} else {
				String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if (paramsQuery.length() > 0) {   
					sQuery += paramsQuery + " and ";
				}
				sQuery += "(" + populateQuery(traduccion, params) + ")";
			}            

			String orderBy = " order by normativa." + campoOrdenacion + " " + orden;

			Query query;
			Query queryCount;
			String select = "Select distinct normativa";
			String selectCount = "Select count(distinct normativa)";
			String from;
			if ("local".equals(tipo)) {
				from = " from NormativaLocal as normativa, normativa.traducciones as trad ";
			} else if ("todas".equals(tipo)) {
				from = " from Normativa as normativa, normativa.traduccionesCombinadas as trad ";
			} else {
				from = " from NormativaExterna as normativa, normativa.traducciones as trad ";
			}

			String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaFilles, uaMeves);
			if ( !StringUtils.isEmpty(uaQuery) ) {
				uaQuery = " and  ( 	normativa.unidadAdministrativa.id in (" + uaQuery + ") " +
						"		or normativa.unidadAdministrativa.id is null )"; //las externas no tienen ua
			}

			//Filtrar por el acceso del usuario

			String accessQuery = "";
			//tieneAccesoValidable
			if (!userIsSuper()) {
				accessQuery += " and normativa.validacion = " + Validacion.INTERNA;
			}

			// tieneAcceso
			if (!userIsSystem()) {
				if ( StringUtils.isEmpty(uaQuery) ) { //Se está buscando en todas las unidades orgánicas            	
					uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(null, true, true);
					if ( !StringUtils.isEmpty(uaQuery) ) {        	
						uaQuery = " and ( normativa.unidadAdministrativa.id in (" + uaQuery + ") " +
								"		or normativa.unidadAdministrativa.id is null )"; //las externas no tienen ua
					} else {
						//Esto significa que el usuario no tiene ninguna unidad administrativa configurada, y //no es system. 
						uaQuery = " and normativa.unidadAdministrativa.id is null"; //las externas no tienen ua
					}
				}
			}
			ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
			queryCount = session.createQuery(selectCount + from + " where " + sQuery + uaQuery + accessQuery);
			query = session.createQuery(select + from + " where " + sQuery + uaQuery + accessQuery + orderBy);

			for ( int i = 0; i < params.size(); i++ ) {
				Object o = params.get(i);
				query.setParameter(i, o);
				queryCount.setParameter(i, o);
			}

			int resultadosMax = new Integer(resultats).intValue();
			int primerResultado = new Integer(pagina).intValue() * resultadosMax;
			if ( resultadosMax != RESULTATS_CERCA_TOTS) {
				query.setFirstResult(primerResultado);
				query.setMaxResults(resultadosMax);
			}

			resultadoBusqueda.setTotalResultados( (Integer) queryCount.uniqueResult() );
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
	 * Construye el query de búsqueda multiidioma en todos los campos
	 */
	private String populateQuery(Map traducciones, List params) {
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

	/**
	 * Busca todas las Normativas por ids
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List buscarNormativas(List<Long> ids)
	{
		if (ids == null || ids.size() == 0)
			return Collections.EMPTY_LIST;

		Session session = getSession();
		try {
			Criteria criteria = session.createCriteria(Normativa.class);
			criteria.setFetchMode("traducciones", FetchMode.EAGER);
			criteria.add(Expression.in("id", ids));
			return criteria.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una lista de normativas del mismo tipo
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List buscarNormativasTipo(Long id, String tipo) {
		Session session = getSession();
		try {
			String sQuery = "normativa.tipo.id = " + id;
			Query query;
			if ("local".equals(tipo)) {
				query = session.createQuery("from NormativaLocal as normativa where " + sQuery);
			} else { //("externa".equals(tipo))
				query = session.createQuery("from NormativaExterna as normativa where " + sQuery);
			}
			return query.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una lista de normativas del mismo boletin
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List buscarNormativasBoletin(Long id, String tipo) {
		Session session = getSession();
		try {
			String sQuery = "normativa.boletin.id = " + id;
			Query query;
			if ("local".equals(tipo)) {
				query = session.createQuery("from NormativaLocal as normativa where " + sQuery);
			} else { //("externa".equals(tipo))
				query = session.createQuery("from NormativaExterna as normativa where " + sQuery);
			}
			return query.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una lista de normativas de la misma UA
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List buscarNormativasUA(Long id, String tipo) {
		Session session = getSession();
		try {
			String sQuery = "normativa.unidadAdministrativa.id = " + id;
			Query query;
			if ("local".equals(tipo)) {
				query = session.createQuery("from NormativaLocal as normativa where " + sQuery);
			} else { //("externa".equals(tipo))
				query = session.createQuery("from NormativaExterna as normativa where " + sQuery);
			}

			return query.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una lista de normativas no relacionadas con procedimientos
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public List buscarNormativasNoRelacionadas(String tipo) {
		Session session = getSession();
		try {
			String sQuery = "normativa.procedimientos.size = 0";
			Query query;
			if ("local".equals(tipo)) {
				query = session.createQuery("from NormativaLocal as normativa where " + sQuery);
			} else { //("externa".equals(tipo))
				query = session.createQuery("from NormativaExterna as normativa where " + sQuery);
			}

			return query.list();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Aï¿½ade una nueva afectacion a la normativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void anyadirAfectacion(Long normativaAfectada_id, Long tipafec_id, Long normativaQueAfecta_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoNormativa(normativaQueAfecta_id)) {
				throw new SecurityException("No tiene acceso a la normativa");
			}
			Normativa normativaAfectada = (Normativa) session.load(Normativa.class, normativaAfectada_id);
			Normativa normativaQueAfecta = (Normativa) session.load(Normativa.class, normativaQueAfecta_id);
			TipoAfectacion tipAfec = (TipoAfectacion) session.load(TipoAfectacion.class, tipafec_id);
			Afectacion afectacion = new Afectacion();
			afectacion.setAfectante(normativaQueAfecta);
			afectacion.setNormativa(normativaAfectada);
			afectacion.setTipoAfectacion(tipAfec);
			normativaQueAfecta.getAfectadas().add(afectacion);
			normativaAfectada.getAfectantes().add(afectacion);
			session.flush();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

	/**
	 * elimina una afectacion de la normativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void eliminarAfectacion(Long idNormativaQueAfecta, Long idTipoAfectacion, Long idNormativaAEliminar) {
		
		Session session = getSession();
		
		try {
			
			if (!getAccesoManager().tieneAccesoNormativa(idNormativaQueAfecta)) {
				throw new SecurityException("No tiene acceso a la normativa");
			}
			
			Normativa normativaQueAfecta = (Normativa)session.load(Normativa.class, idNormativaQueAfecta);
			Normativa normativaAEliminar = (Normativa)session.load(Normativa.class, idNormativaAEliminar);
			Set afectadas = normativaQueAfecta.getAfectadas();
			Afectacion encontrada = new Afectacion();
			
			Iterator iter = afectadas.iterator();
			
			while ( iter.hasNext() ) {
				
				Afectacion afectacion = (Afectacion) iter.next();
				
				if (afectacion.getNormativa().equals(normativaAEliminar)) {
					encontrada = afectacion;
				}
				
			}
			
			normativaQueAfecta.getAfectadas().remove(encontrada);
			normativaAEliminar.getAfectantes().remove(encontrada);
			
			session.flush();
			
		} catch (HibernateException e) {
			
			throw new EJBException(e);
			
		} finally {
			
			close(session);
			
		}
		
	}

	/**
	 * Aï¿½ade un nuevo procedimiento a la normativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void anyadirProcedimiento(Long proc_id, Long norm_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
				throw new SecurityException("No tiene acceso al procedimiento");
			}
			Normativa normativa = (Normativa) session.load(Normativa.class, norm_id);
			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
			procedimiento.getNormativas().add(normativa);
			session.flush();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

	/**
	 * elimina un procedimiento de la normativa
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void eliminarProcedimiento(Long proc_id, Long norm_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoProcedimiento(proc_id)) {
				throw new SecurityException("No tiene acceso al procedimiento");
			}
			Normativa normativa = (Normativa) session.load(Normativa.class, norm_id);
			ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, proc_id);
			procedimiento.getNormativas().remove(normativa);
			session.flush();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene el archivo de una Normativa.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Archivo obtenerArchivoNormativa(Long id, String lang, boolean useDefault) {
		Session session = getSession();
		try {
			Normativa normativa = (Normativa) session.load(Normativa.class, id);
			TraduccionNormativa tradNormativa = (TraduccionNormativa) normativa.getTraduccion(lang);
			if (tradNormativa == null || tradNormativa.getArchivo() == null) {
				if (useDefault) {
					tradNormativa = (TraduccionNormativa) normativa.getTraduccion();
				} else {
					return null;
				}
			}
			Hibernate.initialize(tradNormativa.getArchivo());
			return tradNormativa.getArchivo();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Borra una Normativa.
	 * @throws DelegateException 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void borrarNormativa(Long id) throws DelegateException {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoNormativa(id)) {
				throw new SecurityException("No tiene acceso a la normativa");
			}
			Normativa normativa = (Normativa) session.load(Normativa.class, id);
			addOperacion(session, normativa, Auditoria.BORRAR);
			Historico historico = getHistorico(session, normativa);
			((HistoricoNormativa) historico).setNormativa(null);
			for (Iterator iterator = normativa.getProcedimientos().iterator(); iterator.hasNext();) {
				ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
				proc.getNormativas().remove(normativa);
			}
			normativa.getAfectadas().clear();

			for (Iterator iterator = normativa.getAfectantes().iterator(); iterator.hasNext();) {
				Afectacion afectacion = (Afectacion) iterator.next();
				Normativa afectante = afectacion.getAfectante();
				afectante.getAfectadas().remove(afectacion);
			}
			normativa.getAfectantes().clear();
			
			//SOLR Desndexar normativa
			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
		    solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_NORMATIVA.toString(), normativa.getId(), 2l);
		    
			Actualizador.borrar(normativa);
			session.delete(normativa);
			session.flush();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene las normativas de una unidad administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List listarNormativasUA(Long id) {
		Session session = getSession();
		try {
			UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);            
			Hibernate.initialize(unidadAdministrativa.getNormativas());            

			List result = new ArrayList();
			for (Iterator iterator = unidadAdministrativa.getNormativas().iterator(); iterator.hasNext();) {
				Normativa norm = (Normativa) iterator.next();
				if (visible(norm)) {
					result.add(norm);
				}
			}
			return result;
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


	/**
	 * Construye el query de bï¿½squeda segun los parï¿½metros
	 */
	private String populateQuery(Map parametros, Map trad, List params) {

		String aux = "";

		Map traduccion = new HashMap(trad);

		// Tratamiento de parametros
		for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {

			String key = (String) iter1.next();
			Object value = parametros.get(key);

			if (value != null) {

				if (value instanceof String) {

					String sValue = (String) value;

					if (sValue.length() > 0) {

						if (aux.length() > 0) 
							aux = aux + " and ";

						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {

							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( normativa." + key + " )  like ? ";
							params.add(sValue);

						} else {

							aux = aux + " upper( normativa." + key + " )  like ? ";
							params.add("%"+sValue+"%");

						}

					}

				} else if (value instanceof Date) {

					if (aux.length() > 0) 
						aux = aux + " and ";

					Calendar cal = Calendar.getInstance();
					cal.setTime((Date)value);
					aux = aux + "normativa." + key + " = TO_DATE('" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + "', 'YYYY-MM-DD')";

				} else {

					if (aux.length() > 0) aux = aux + " and ";
					aux = aux + "normativa." + key + " = " + value;

				}

			}

		}


		// Tratamiento de traducciones
		if (traduccion.containsKey("idioma")) {
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
							aux = aux + " and  upper( trad." + key + " )  like ? ";
							params.add(sValue);
						} else {
							aux = aux + " and  upper( trad." + key + " )  like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else {
					aux = aux + " and trad." + key + " = ? ";
					params.add(value);
				}
			}
		}
		return aux;
	}


	/**
	 * A partir de una lista de objetos Normativa, devuelve su correspondiente lista de 
	 * objetos NormativaModel, teniendo en cuenta el idioma de traducción.
	 * 
	 * @param listaNormativa
	 * @param idioma
	 * @return Collection Lista de normativaModel
	 */
	private Collection getListaNormativaModel( List<Normativa> listaNormativa, String idioma ) {
		
		List<NormativaModel> listaNormativaModel = new ArrayList();		

		for (Normativa normativa: listaNormativa) {

			NormativaModel normativaModel = new NormativaModel();
			Boletin boletin = normativa.getBoletin();

			//Campos sin traducción
			normativaModel.setCodi( normativa.getId().intValue() );
			normativaModel.setData( normativa.getFecha() != null ? normativa.getFecha() : null );
			normativaModel.setNumero( normativa.getNumero() != null ? normativa.getNumero().toString() : null );
			normativaModel.setNumeroBoib( boletin.getId() != null ? boletin.getId().toString() : null);
			normativaModel.setOrigen( boletin.getNombre() != null ? boletin.getNombre() : null);

			TraduccionNormativa traduccion = (TraduccionNormativa) normativa.getTraduccion(idioma);
			TraduccionNormativa traduccionDef = (TraduccionNormativa) normativa.getTraduccion(idioma_per_defecte);

			if  ("externa".equals(normativa.getTipo()) )
				traduccion = (TraduccionNormativa) normativa.getTraduccionFront(idioma);

			if (traduccion == null)
				traduccion = traduccionDef;

			//Campos con traducción
			normativaModel.setSumari( traduccion.getTitulo() != null ? traduccion.getTitulo() : traduccionDef.getTitulo() );

			Archivo archivo = traduccion.getArchivo();
			Long idArchivo = null;

			if (archivo != null && archivo.getId() != null) { 
				idArchivo = archivo.getId();			
				normativaModel.setArxiu( idArchivo.intValue() );
			}			

			Integer paginaInicial = traduccion.getPaginaInicial() != null ? traduccion.getPaginaInicial() : null;
			Integer paginaFinal = traduccion.getPaginaInicial() != null ? traduccion.getPaginaInicial() : null;

			Integer paginaInicialDef = traduccionDef.getPaginaInicial() != null ? traduccionDef.getPaginaInicial() : null;
			Integer paginaFinalDef = traduccionDef.getPaginaFinal() != null ? traduccionDef.getPaginaFinal() : null;

			normativaModel.setPagini( paginaInicial != null ? paginaInicial : ( paginaInicialDef != null ? paginaInicialDef : 0)  );
			normativaModel.setPagfin( paginaFinal != null ? paginaFinal : ( paginaFinalDef != null ? paginaFinalDef : 0) );

			listaNormativaModel.add(normativaModel);			
		}

		return listaNormativaModel;		
		
	} 	 

	/**
	 * Buscamos el numero de normvativas activas
	 * 
	 * @param unidadAdministrativa
	 * @return numero de normativas activas 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public int buscarNormativasActivas(List<Long> listaUnitatAdministrativaId){
		Integer resultado = 0;
		Session session = getSession();

		try {

			Query query = null;
			if (listaUnitatAdministrativaId.size() > 0) {
				query = session.createQuery("select count(*) from NormativaLocal as nor where nor.validacion = :validacion " +
						"and nor.unidadAdministrativa.id in (:lId)");
				query.setInteger("validacion", Validacion.PUBLICA);
				query.setParameterList("lId", listaUnitatAdministrativaId, Hibernate.LONG);

				resultado = (Integer) query.uniqueResult();

			}/* else {

	        		query = session.createQuery("select count(*) from NormativaLocal as nor where nor.validacion = :validacion ");
	        		query.setInteger("validacion", Validacion.PUBLICA);

	        	}

	        	resultado = (Integer) query.uniqueResult();
			 */	        		        	

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

		return resultado;
	}

	 /**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
	 * @param solrIndexer
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		EnumCategoria categoria = EnumCategoria.fromString(solrPendiente.getTipo());
		if (categoria == EnumCategoria.ROLSAC_NORMATIVA) {
			return indexarSolrNormativa(solrIndexer, solrPendiente.getId(), EnumCategoria.ROLSAC_NORMATIVA);
		} else if (categoria == EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO) {
			return indexarSolrNormativaDocumento(solrIndexer, solrPendiente.getId(), EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO);
		} else {
			return null;  
		}
	}
	
	/**
	 * Obtiene una normativa segun id para solr.
	 * @param id
	 * @return
	 */
	 
	public Normativa obtenerNormativaParaSolr(Long id, Long idArchivo) {
		Session session = getSession();
		Normativa normativa = null;
		try {
			try {
				normativa = (Normativa) session.load(Normativa.class, id);
			} catch (Exception e) { //No ponemos error para poder depurar.
			}
			
			if (normativa == null) {
				normativa = (NormativaLocal) session.load(NormativaLocal.class, id);
			} 
			
			Hibernate.initialize(normativa.getAfectadas());
			Hibernate.initialize(normativa.getAfectantes());
			Hibernate.initialize(normativa.getProcedimientos());
			
			Map<String, Traduccion> traduccionCorrecta = null;
			for (Iterator iterator = normativa.getLangs().iterator(); iterator.hasNext();) {
				
				String lang = (String) iterator.next();
				TraduccionNormativa traduccion = (TraduccionNormativa) normativa.getTraduccion(lang);
				if (traduccion!=null)  Hibernate.initialize(traduccion.getArchivo());
				
				//Si es distinto de null, entonces habrá que extraer los documentos de aquellos que no 
				//   concuerden con la id.
				if (idArchivo != null) {
					if (traduccion != null && traduccion.getArchivo() != null && idArchivo.compareTo(traduccion.getArchivo().getId()) == 0) {
						//normativa.setTraduccion(lang, null);
						traduccionCorrecta = new HashMap<String, Traduccion>();
						traduccionCorrecta.put(lang,  traduccion);
						break;
					}
				}
			}
			
			//Para que sólo tenga una traducción.
			if (idArchivo != null) {
				normativa.setTraduccionMap(traduccionCorrecta);
			}
			
		} catch (HibernateException he) {
			log.error("Error obteniendo la normativa con id " + id, he);
		} catch (Exception e) {
			log.error("Error obteniendo la normativa con id " + id, e);
		} finally {
			close(session);
		}
		return normativa;
	}
	
	 /**
	  * Metodo para indexar un solrPendiente.
	  * @param solrIndexer
	  * @param idElemento
	  * @param categoria
	  * @ejb.interface-method
      * @ejb.permission unchecked="true"
	  */	 
	public SolrPendienteResultado indexarSolrNormativa(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("NormativaFacadeEJB.indexarNormativaSolr. idElemento:" + idElemento +" categoria:"+categoria);
		
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final Normativa normativa = obtenerNormativaParaSolr(idElemento, null);
			if (normativa == null) {
				return new SolrPendienteResultado(false, "Error obteniendo la normativa");
			}
			
			boolean isIndexable = IndexacionUtil.isIndexable(normativa);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria de tipo normativa.
			final IndexData indexData = new IndexData();
			indexData.setCategoria(categoria);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());			
			indexData.setFechaPublicacion(normativa.getFechaBoletin());
			indexData.getUos().add(IndexacionUtil.calcularPathUO(normativa.getUnidadAdministrativa()));
			
			//Iteramos las traducciones
			final Map<String, Traduccion> traducciones = normativa.getTraduccionMap();
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral searchText = new MultilangLiteral();
			final MultilangLiteral searchTextOptional = new MultilangLiteral();
			final List<EnumIdiomas> idiomas = new ArrayList<EnumIdiomas>();
			
			//Recorremos las traducciones
			for (String keyIdioma : normativa.getTraduccionMap().keySet()) {
				final TraduccionNormativa traduccion = (TraduccionNormativa)traducciones.get(keyIdioma);
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				
				if (traduccion != null && enumIdioma != null) {
					
					//Para saltarse los idiomas sin titulo
					if ((traduccion.getTitulo() == null || traduccion.getTitulo().isEmpty())) {
						continue;
					}
					
					//Anyadimos idioma al enumerado.
					idiomas.add(enumIdioma);
					
					//Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search text.
					titulo.addIdioma(enumIdioma, traduccion.getTitulo());
					descripcion.addIdioma(enumIdioma, traduccion.getTitulo());
			    	String tipoNormativaNombre = "";
			    	if ( normativa.getTipo() != null && normativa.getTipo().getTraduccion(keyIdioma) != null) {
			    		 TraduccionTipo tipo = (TraduccionTipo) normativa.getTipo().getTraduccion(keyIdioma);
			    		 tipoNormativaNombre = tipo.getNombre();
			    	}
			    	searchText.addIdioma(enumIdioma, traduccion.getTitulo()+ " " + traduccion.getApartado() + " " + tipoNormativaNombre);
			    	
			    	final StringBuffer textoOptional = new StringBuffer();
			    	textoOptional.append(" " + traduccion.getObservaciones());
			    	textoOptional.append(IndexacionUtil.calcularPathTextUO(normativa.getUnidadAdministrativa(), keyIdioma));
			    	
					searchTextOptional.addIdioma(enumIdioma, textoOptional.toString());
			    	if (normativa.getBoletin() == null && normativa.getFecha() != null && (traduccion.getEnlace() == null || traduccion.getEnlace().isEmpty())) {
			    		Calendar calendar = Calendar.getInstance();
			    		calendar.setTime(normativa.getFecha());
			    		urls.addIdioma(enumIdioma,"/eboibfront/VisPdf?action=VisHistoric&p_any="+calendar.get(Calendar.YEAR)+"&p_numero="+normativa.getNumero()+"&p_finpag="+traduccion.getPaginaFinal()+"&p_inipag="+traduccion.getPaginaInicial()+"&idDocument="+normativa.getId()+"&lang="+keyIdioma);
			    	} else if (normativa.getBoletin() == null && traduccion.getEnlace() != null && traduccion.getEnlace().isEmpty()) {
			    		urls.addIdioma(enumIdioma, traduccion.getEnlace());
			    	} else {
			    		urls.addIdioma(enumIdioma, "/govern/dadesNormativa.do?lang="+keyIdioma+"&codi="+normativa.getId()+"&coduo="+normativa.getUnidadAdministrativa().getId());
			    	}
			    	
				}
		    	
			}
			
			//Seteamos datos multidioma.
			indexData.setTitulo(titulo);
			indexData.setDescripcion(descripcion);
			indexData.setUrl(urls);
			indexData.setSearchText(searchText);
			indexData.setSearchTextOptional(searchTextOptional);
			indexData.setIdiomas(idiomas);
						
			solrIndexer.indexarContenido(indexData);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en normativafacade intentando indexar. idElemento:" + idElemento +" categoria:"+categoria, exception);
			String mensajeError;
			if (exception.getMessage() == null) {
				mensajeError = exception.toString();
			} else {
				mensajeError = exception.getMessage();
			}
			return new SolrPendienteResultado(false, mensajeError);
		}

	}
	
	 /** 
	  * Metodo para indexar un solrPendiente.
	  * @param solrIndexer
	  * @param idElemento
	  * @param categoria
	  * 
	  * @ejb.interface-method
      * @ejb.permission unchecked="true"
	  */
	 
	public SolrPendienteResultado indexarSolrNormativaDocumento(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) {
		log.debug("NormativaFacadeEJB.indexarNormativaDocumentoSolr. idElemento:" + idElemento +" categoria:"+categoria);
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final Normativa normativa = obtenerNormativaSegunArchivo(idElemento);
			if (normativa == null) {
				return new SolrPendienteResultado(false, "Error obteniendo la normativa");
			}
			
			boolean isIndexable = IndexacionUtil.isIndexable(normativa);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
												
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria ( y padre) de tipo documento normativa.
			final IndexFile indexData = new IndexFile();
			indexData.setCategoria(categoria);
			indexData.setCategoriaPadre(EnumCategoria.ROLSAC_NORMATIVA);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.getUos().add(IndexacionUtil.calcularPathUO(normativa.getUnidadAdministrativa()));
			indexData.setElementoIdPadre(normativa.getId().toString());

			//Iteramos las traducciones
			final Map<String, Traduccion> traducciones = normativa.getTraduccionMap();
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral descripcionPadre = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral urlsPadre = new MultilangLiteral();
			final MultilangLiteral extension = new MultilangLiteral();
			
			//Recorremos las traducciones
			boolean encArchivo = false;
			for (String keyIdioma : normativa.getTraduccionMap().keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionNormativa traduccion = (TraduccionNormativa)traducciones.get(keyIdioma);
				
				if (traduccion != null && enumIdioma != null) {
					
					// Verificamos si se ha encontrado el archivo asociado
					if (traduccion.getArchivo() == null || traduccion.getArchivo().getId().compareTo(idElemento) != 0) {
						continue;
					}
					
					encArchivo = true;
					
					if (IndexacionUtil.isIndexableSolr(traduccion.getArchivo())) {
						log.debug("Es indexable con mime:" + traduccion.getArchivo().getMime()+" y tamanyo:" + traduccion.getArchivo().getPeso());
					} else {
						if (traduccion.getArchivo() == null) {
							log.debug("NO Es indexable doc de normativa "+ normativa.getId()+" porque el archivo es nulo");
						} else {
							log.debug("NO Es indexable con mime:" + traduccion.getArchivo().getMime()+" y tamanyo:" + traduccion.getArchivo().getPeso());
						}
						return new SolrPendienteResultado(true, "El documento no cumple los requisitos.");
					}
					
					indexData.setElementoId(idElemento+"."+traduccion.getArchivo().getId().toString());
					
					
					//Seteamos los primeros campos multiidiomas: Titulo y Descripción (y padre).
					titulo.addIdioma(enumIdioma, traduccion.getArchivo().getNombre());
					descripcion.addIdioma(enumIdioma, traduccion.getArchivo().getMime());
					descripcionPadre.addIdioma(enumIdioma, Utilidades.sanitizarTexto(traduccion.getTitulo()));
					extension.addIdioma(enumIdioma, IndexacionUtil.calcularExtensionArchivo(traduccion.getArchivo().getNombre()));
					
					urls.addIdioma(enumIdioma, "/normativa/archivo.do?id=" + traduccion.getArchivo().getId() + "&lang=" + keyIdioma);
					if (normativa.getBoletin() == null && normativa.getFechaBoletin() != null && (traduccion.getEnlace() == null || traduccion.getEnlace().isEmpty())) {
			    		Calendar calendar = Calendar.getInstance();
			    		calendar.setTime(normativa.getFechaBoletin());
			    		urlsPadre.addIdioma(enumIdioma,"/eboibfront/VisPdf?action=VisHistoric&p_any="+calendar.get(Calendar.YEAR)+"&p_numero="+normativa.getNumero()+"&p_finpag="+traduccion.getPaginaFinal()+"&p_inipag="+traduccion.getPaginaInicial()+"&idDocument="+normativa.getId()+"&lang="+keyIdioma);
			    	} else if (normativa.getBoletin() == null && traduccion.getEnlace() != null && !traduccion.getEnlace().isEmpty()) {
			    		urlsPadre.addIdioma(enumIdioma, traduccion.getEnlace());
			    	} else {
			    		urlsPadre.addIdioma(enumIdioma, "/govern/dadesNormativa.do?lang="+keyIdioma+"&codi="+normativa.getId()+"&coduo="+normativa.getUnidadAdministrativa().getId());
			    	}
					indexData.setFileContent(traduccion.getArchivo().getDatos());
					indexData.setExtension(extension);
					indexData.setIdioma(enumIdioma);
					break;
				}
			}
			
			if (!encArchivo) {
				return new SolrPendienteResultado(true, "No se ha encontrado normativa asociada");
			}
			
			//Seteamos datos multidioma.
			indexData.setTitulo(titulo);
			indexData.setDescripcion(descripcion);
			indexData.setDescripcionPadre(descripcionPadre);
			indexData.setUrl(urls);
			indexData.setUrlPadre(urlsPadre);
			
			//Fechas
			indexData.setFechaPublicacion(normativa.getFechaBoletin());
			solrIndexer.indexarFichero(indexData);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en normativafacade intentando indexar. idElemento:" + idElemento +" categoria:"+categoria, exception);
			String mensajeError; 
			if (exception.getMessage() == null) {
				mensajeError = exception.toString();
			} else {
				mensajeError = exception.getMessage();
			}
			return new SolrPendienteResultado(false, mensajeError);
		}

	}
	
	/**
	 * Devuelve la normativa, con sólo una traducción y su archivo.
	 * @param idElemento
	 * @return
	 */
	private Normativa obtenerNormativaSegunArchivo(Long idElemento) {
		Session session = getSession(); 
		Normativa normativa = null;
		try {
			Query sqlQuery = null;
			List result = null;
			
			Long idNormativa = null;
			
			// Buscamos en normativa
			sqlQuery = session.createQuery("select distinct normativa.id from Normativa normativa inner join normativa.traduccionesCombinadas trad inner join trad.archivo arc where arc.id = " + idElemento);					
			result = sqlQuery.list();
			if (result.size() <= 0) {
				// Si no encuentra, buscamos en normativa local
				sqlQuery = session.createQuery("select distinct normativa.id from NormativaLocal normativa inner join normativa.traduccionesCombinadas trad inner join trad.archivo arc where arc.id = " + idElemento);
				result = sqlQuery.list();
			}
			
			if (result.size() > 0) {
				idNormativa = Long.valueOf(result.get(0).toString());
				normativa = this.obtenerNormativaParaSolr(idNormativa, idElemento);
			}
						
						
		} catch (HibernateException he) {
			log.error("Error obteniendo normativa según archivo con id " + idElemento, he);
		} catch (Exception e) {
			log.error("Error e obteniendo normativa según archivo con id " + idElemento, e);
		}finally {
			close(session);
		}
		return normativa;
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 * @param solrPendiente
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */ 
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)  {
		try {
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(), EnumCategoria.ROLSAC_NORMATIVA);
			return new SolrPendienteResultado(true);
		} catch(Exception exception) {
			log.error("Error en normativafacade intentando desindexar.", exception);
			String mensajeError;
			if (exception.getMessage() == null) {
				mensajeError = exception.toString();
			} else {
				mensajeError = exception.getMessage();
			}
			return new SolrPendienteResultado(false, mensajeError);
		}
	} 

	/**
	 * Devuelve los ids de las normativas.
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<Long> buscarIdsNormativas()  {
		final Session session = getSession();
		
		try {

    		final StringBuilder consulta = new StringBuilder("select normativa.id from Normativa as normativa ");
    		
    		final Query query = session.createQuery( consulta.toString() );
    		query.setCacheable(true);

    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);

    	}
		
	}
	
}