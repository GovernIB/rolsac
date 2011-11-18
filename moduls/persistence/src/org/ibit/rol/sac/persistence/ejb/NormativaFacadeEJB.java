package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.FetchMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoNormativa;
import org.ibit.rol.sac.model.IndexObject;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.webcaib.AnnexeModel;
import org.ibit.rol.sac.model.webcaib.DadesNormativaModel;
import org.ibit.rol.sac.model.webcaib.NormativaModel;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;


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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarNormativaLocal(NormativaLocal normativa, Long idUA) {
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
            return normativa.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Crea o actualiza una Normativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Long grabarNormativaExterna(NormativaExterna normativa) {
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
    public List buscarNormativas(Map parametros, Map traduccion, String tipo, Long idUA, boolean uaMeves, String campoOrdenacion, String orden) {
        Session session = getSession();
        try {
        	
        	UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        	
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

            String orderBy = " order by " + campoOrdenacion + " " + orden;
            
            Query query;
            if ("local".equals(tipo)) {
            	//Si la búsqueda es por normativas locales filtramos por unidades administrativas
            	Set<Long> uas = new HashSet<Long>();
        		uas.add(idUA);
            	
            	//Si se ha indicado mostrar normativas de las unidades administrativas del usuario
                if (uaMeves) {
                	
                	Set<UnidadAdministrativa> listaUAsUsuario = getUsuario(session).getUnidadesAdministrativas();
                	for (UnidadAdministrativa ua : listaUAsUsuario) {                		
                		uas.add(ua.getId());
                		// Lo siguiente se haria para buscar en las UAs hijas.
                		// List<Long> listaDescendientes = uaDelegate.cargarArbolUnidadId(ua.getId());
                		// uas.addAll(listaDescendientes);                		
                	}
                }

                if (!uas.isEmpty()) {
	                sQuery += " and normativa.unidadAdministrativa.id in (";
	                String sep = "";
	                for (Long ua : uas) {
	                	sQuery += sep + ua;
	                	sep = ", ";
	                }
	                sQuery += ")";
                }
            	
                // Eliminado "left join fetch" por problemas en el cache de traducciones.
                query = session.createQuery("from NormativaLocal as normativa, normativa.traducciones as trad where " + sQuery + orderBy);
                
            } else { // "externa".equals(tipo))
                // Eliminado "left join fetch" por problemas en el cache de traducciones.
                query = session.createQuery("from NormativaExterna as normativa, normativa.traducciones as trad where " + sQuery + orderBy);
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
            
//      } catch (DelegateException de) {
//        	throw new EJBException(de);
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
     * Busca todas las Normativas con un texto determinado.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List buscarNormativas(String texto) {
        IndexerDelegate delegate = DelegateUtil.getIndexerDelegate();
        Long[] ids;
        try {
            ids = delegate.buscarIds(Normativa.class.getName(), texto);
        } catch (DelegateException e) {
            log.error("Error buscando", e);
            ids = new Long[0];
        }

        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }

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
    public void eliminarAfectacion(Long normativaQueAfecta_id, Long tipoAfec_id, Long normativaAeliminar_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoNormativa(normativaQueAfecta_id)) {
                    throw new SecurityException("No tiene acceso a la normativa");
            }
            Normativa normativaQueAfecta = (Normativa) session.load(Normativa.class, normativaQueAfecta_id);
            Normativa normativaAeliminar = (Normativa) session.load(Normativa.class, normativaAeliminar_id);
            Set afectadas = normativaQueAfecta.getAfectadas();
            Afectacion encontrada = new Afectacion();
            for (Iterator iter = afectadas.iterator(); iter.hasNext();) {
                Afectacion afectacion = (Afectacion) iter.next();
                if (afectacion.getNormativa().equals(normativaAeliminar)) {
                    encontrada = afectacion;
                }
            }
            normativaQueAfecta.getAfectadas().remove(encontrada);
            normativaAeliminar.getAfectantes().remove(encontrada);
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
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarNormativa(Long id) {
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
                if (aux.length() > 0) aux = aux + " and ";
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
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
                    aux = aux + "normativa." + key + " = '" + org.ibit.rol.sac.persistence.util.DateUtils.formatearddMMyyyy((Date)value) + "'";
                } else {
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
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * 
	 * Debemos incluir las materias a través de los procedimientos relacionados
	 * con esa normativa y la unidad administrativa de la que depende.
	 * 
	 * Método válido para Normativas locales y externas
	 * @throws DelegateException 
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public ModelFilterObject obtenerFilterObject(Normativa norma) throws DelegateException {
		ModelFilterObject filter = new ModelFilterObject();
		
		Session session = getSession();

   		TraModelFilterObject trafilter;
   		String idioma;
   		String txids;
   		String txtexto;
		Set procs= norma.getProcedimientos();
		UnidadAdministrativa ua= ((NormativaLocal)norma).getUnidadAdministrativa();
		List listapadres = new ArrayList();
		
		if (ua!=null)
			listapadres= org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa(ua.getId());
		
		
   		filter.setFamilia_id(null);
   		filter.setMicrosite_id(null);
   		filter.setSeccion_id(null);

   		// Obtenemos las materias y hechos vitales partiendo de sus procedimientos
   		Iterator itproc=procs.iterator();
   		ProcedimientoLocal pro;
   		Materia mat;
   		HechoVitalProcedimiento hvital;
   		Hashtable lista_materias = new Hashtable(), lista_hechos = new Hashtable();
   		ProcedimientoDelegate bdProc = DelegateUtil.getProcedimientoDelegate();
   		
   		while (itproc.hasNext()) {
   			// Obtenemos el procedimiento puesto que sus colecciones estan lazy
   			pro = (ProcedimientoLocal)bdProc.obtenerProcedimiento(((ProcedimientoLocal)itproc.next()).getId());
   			
   			if (pro.getMaterias()!=null) {
   				Iterator itmat=pro.getMaterias().iterator();
   				while (itmat.hasNext()) {
   					mat=(Materia)itmat.next();
   					if (!lista_materias.containsKey(mat.getId()))
       						lista_materias.put(mat.getId(), mat);
   				}
   			}

   			if (pro.getHechosVitalesProcedimientos()!=null) {
   				Iterator itvital=pro.getHechosVitalesProcedimientos().iterator();
   				while (itvital.hasNext()) {
   					hvital=(HechoVitalProcedimiento)itvital.next();
   					if (!lista_hechos.containsKey(hvital.getHechoVital().getId()))
   						lista_hechos.put(hvital.getHechoVital().getId(), hvital.getHechoVital());
   				}
   			}
   		}

   		
   		Iterator langs= norma.getLangs().iterator();
   		while (langs.hasNext()) {
   			idioma = (String) langs.next();

   			trafilter = new TraModelFilterObject();
			trafilter.setMaintitle(null); 

			// Obtenemos la UA con sus padres excepto el raiz
			if (ua!=null) {
				txids=Catalogo.KEY_SEPARADOR;
				txtexto=" ";

				UnidadAdministrativa ua_padre=null;
				for (int x = 1; x < listapadres.size(); x++) {
					ua_padre=(UnidadAdministrativa)listapadres.get(x);
					txids+=ua_padre.getId()+Catalogo.KEY_SEPARADOR;
					if (ua_padre.getTraduccion(idioma)!=null)
						txtexto+=((TraduccionUA)ua_padre.getTraduccion(idioma)).getNombre()+" ";
				}

				filter.setUo_id( (txids.length()==1) ? null: txids);
				trafilter.setUo_text( (txtexto.length()==1) ? null: txtexto);
			}
			
			// Obtenemos las materias y hechos vitales via sus procedimientos relacionados
	    	txids=Catalogo.KEY_SEPARADOR;
	    	txtexto=" ";
	    		
	    	Enumeration i=lista_materias.keys();
	    		
	    	while (i.hasMoreElements()) {
	    		Materia materia = (Materia)lista_materias.get(i.nextElement());
		       	txids+=materia.getId()+Catalogo.KEY_SEPARADOR; //anadir los ids (los de los hechos vitales no)
		       	if (materia.getTraduccion(idioma)!=null) {
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getNombre() + " ";
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getDescripcion() + " ";
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getPalabrasclave() + " ";
		       	}
	    	}

	    	i=lista_hechos.keys();
	    	HechoVital hechovital=null;
	    	
	    	while (i.hasMoreElements()) {
	    		hechovital = (HechoVital)lista_hechos.get(i.nextElement());
		       	if (hechovital.getTraduccion(idioma)!=null) {
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getNombre() + " ";
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getDescripcion() + " ";
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getPalabrasclave() + " ";
		       	}
	    	}
	    		
	    	filter.setMateria_id( (txids.length()==1) ? null: txids);
	    	trafilter.setMateria_text( (txtexto.length()==1) ? null: txtexto);
	    		
    		filter.addTraduccion(idioma, trafilter);
 
		}
   		close(session);
		return filter;
	}        

	
    /**
     * Añade la normativa al indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaNormativa(Normativa norma, ModelFilterObject filter)  {
    	
    	try {

	    	if (filter==null) filter = obtenerFilterObject(norma);
	    	
			for (Iterator iterator = norma.getLangs().iterator(); iterator.hasNext();) {
				String idi = (String) iterator.next();
				IndexObject io= new IndexObject();
				
				if (norma instanceof NormativaLocal) {
					io.setId(Catalogo.SRVC_NORMATIVA_LOCAL + "." + norma.getId());
					io.setClasificacion(Catalogo.SRVC_NORMATIVA_LOCAL);
				}
				if (norma instanceof NormativaExterna) {
					io.setId(Catalogo.SRVC_NORMATIVA_EXTERNA + "." + norma.getId());
					io.setClasificacion(Catalogo.SRVC_NORMATIVA_EXTERNA);
				}
	            
	            io.setMicro( filter.getMicrosite_id() );
	            io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );
				
				io.setCaducidad("");	// No tiene fecha de caducidad
				io.setPublicacion(""); 	// No tiene fecha de publicacion
				io.setDescripcion(""); 

	            TraduccionNormativa trad=((TraduccionNormativa)norma.getTraduccion(idi));
	            
	            if (trad!=null) {

	            	io.setTituloserviciomain(trad.getSeccion());

	            	if ( norma.getBoletin().getNombre().equals("BOIB")) {
	            		io.setUrl("/govern/estadistica?tipus=N&codi="+norma.getId()+"&mode=view&p_numero=" + norma.getNumero() + "&p_inipag="+ trad.getPaginaInicial()+ "&p_finpag=" + trad.getPaginaFinal()+ "&lang=" + idi + "&url=0");
	            	}
	            	else {
	            		io.setUrl("/govern/sac/dadesnormativa.do?lang="+ idi +"&codi="+ norma.getId()+"&coduo="+ filter.getUo_id());	            	
	            	}
	            	
	            	if (trad.getTitulo()!=null) {
	            		io.setTitulo(trad.getTitulo());
	            		io.addTextLine(trad.getTitulo());
	            		//if (trad.getTitulo().length()>200) io.setDescripcion(trad.getTitulo().substring(0,199)+"...");
	                	//else io.setDescripcion(trad.getTitulo());
	            		io.setDescripcion(trad.getTitulo());
	            	}
	            	
	            	if (trad.getSeccion()!=null)    	io.addTextLine(trad.getSeccion());
	            	if (trad.getApartado()!=null)		io.addTextLine(trad.getApartado());
	            	if (trad.getObservaciones()!=null)	io.addTextLine(trad.getObservaciones());
					
		            if (trad.getArchivo()!=null)       	io.addArchivo((Archivo)trad.getArchivo());
					
	            }

				io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());	    

	            
	            if (io.getText().length()>0)
					org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);

			}

		}
					
		catch (Exception ex) {
			log.warn("[indexInsertaNormativa:" + norma.getId() + "] No se ha podido indexar la normativa. " + ex.getMessage());
		}
        
	}
	
	 /**
     * Elimina la normativa en el indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void indexBorraNormativa(Normativa nor)  {
		
		try {

			for (Iterator iterator = nor.getLangs().iterator(); iterator.hasNext();) {
				String idi = (String) iterator.next();
				if (nor instanceof NormativaLocal) 
					DelegateUtil.getIndexerDelegate().borrarObjeto(Catalogo.SRVC_NORMATIVA_LOCAL + "." + nor.getId(), idi);
				if (nor instanceof NormativaExterna) 
					DelegateUtil.getIndexerDelegate().borrarObjeto(Catalogo.SRVC_NORMATIVA_EXTERNA + "." + nor.getId(), idi);
			}

		}
		catch (DelegateException ex) {
			log.warn("[indexBorraNormativa:" + nor.getId() + "] No se ha podido borrar del indice la normativa. " + ex.getMessage());
		}		        
	}

	//WEBCAIB
    /**
     * WEBCAIB.
     * Obtiene los datos de la normativa
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */
	 public DadesNormativaModel getDadesNormativa(String codi, String idioma) {
		 
		 Session session = getSession();
		 		 
		 try {
			 
			 NormativaLocal normativaLocal = (NormativaLocal) session.load(NormativaLocal.class, Long.parseLong(codi) );
			 
			 Boletin boletin = normativaLocal.getBoletin();						 			 
			 
			 DadesNormativaModel dadesNormativaModel = new DadesNormativaModel();			 
			 
			 //Datos de boletín
			 dadesNormativaModel.setNomDiari(boletin.getNombre());			 
			 dadesNormativaModel.setDataPublicacio(normativaLocal.getFechaBoletin());
			 
			 //Datos de normativa local
			 dadesNormativaModel.setData(normativaLocal.getFecha());			 			 
			 dadesNormativaModel.setNumDiari( normativaLocal.getNumero().toString() );
			 dadesNormativaModel.setDataPublicacio(normativaLocal.getFechaBoletin());			 						 
			 
			 //Datos de traducción unidad administrativa
			 UnidadAdministrativa unidadAdministrativa = normativaLocal.getUnidadAdministrativa();
			 
			 TraduccionUA traduccionUADef = (TraduccionUA) unidadAdministrativa.getTraduccion(idioma_per_defecte);
			 TraduccionUA traduccionUA = (TraduccionUA) unidadAdministrativa.getTraduccion(idioma);			 
			 
			 TraduccionNormativa traduccionNormativaDef = (TraduccionNormativa) normativaLocal.getTraduccion(idioma_per_defecte);
			 TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativaLocal.getTraduccion(idioma);
			 
			 if (traduccionUA == null)
				 traduccionUA = traduccionUADef;
				 
			 if (traduccionNormativa == null)
				 traduccionNormativa = traduccionNormativaDef;				 
			 
			 dadesNormativaModel.setOrganismePropietari( traduccionUA.getNombre() != null ? traduccionUA.getNombre() : traduccionUADef.getNombre() );
			 dadesNormativaModel.setUoPropietaria( traduccionUA.getNombre() != null ? traduccionUA.getNombre() : traduccionUADef.getNombre() );
			 			 
			 //Datos de traducción normativa local
			 if (traduccionNormativa != null) {				 
				 dadesNormativaModel.setSumari( traduccionNormativa.getTitulo() != null ? traduccionNormativa.getTitulo() : traduccionNormativaDef.getTitulo() );
				 dadesNormativaModel.setEnllas( traduccionNormativa.getEnlace() != null ? traduccionNormativa.getEnlace() :  traduccionNormativaDef.getEnlace() );
				 dadesNormativaModel.setObservacions( traduccionNormativa.getObservaciones() != null ? traduccionNormativa.getObservaciones() : traduccionNormativaDef.getObservaciones() );
				 dadesNormativaModel.setNumeroPagina( traduccionNormativa.getPaginaInicial() != null ? traduccionNormativa.getPaginaInicial() : ( traduccionNormativaDef.getPaginaInicial() != null ? traduccionNormativaDef.getPaginaInicial() : 0 ) );
			 }
				 
			 return dadesNormativaModel;
			 			 			
		 } catch (HibernateException he) {
	         throw new EJBException(he);			 
		 } finally {
			 close(session);
		 }		 
	 }		 

	 /**
	 * WEBCAIB 
	 * Obtiene los anexos de una normativa
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
	 */
	 public Collection annexeByNormativa( String codi, String idioma ) {
		 
		 Session session = getSession();
		 
		 try {
			 
			NormativaLocal normativaLocal = (NormativaLocal) session.get(NormativaLocal.class, Long.parseLong(codi));			
			List<AnnexeModel> listaArchivo = new Vector();
			
			if (normativaLocal == null) 
				return listaArchivo;			
				
			TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativaLocal.getTraduccion(idioma);
			
			//Si no obtenemos el annexo con el idioma pasado por parámetro, probamos con el idioma por defecto			
			if (traduccionNormativa == null)
				traduccionNormativa = (TraduccionNormativa) normativaLocal.getTraduccion(idioma_per_defecte);
			
			Archivo archivo = traduccionNormativa != null ? traduccionNormativa.getArchivo() : null;
												
			AnnexeModel annexeModel = new AnnexeModel();			
			
			if (archivo != null) {
				annexeModel.setCodi( archivo.getId().intValue() );
				annexeModel.setArxiu(archivo.getNombre() );			
			
				listaArchivo.add(annexeModel);
		 	}
		 
			return  listaArchivo;
			
		 } catch (HibernateException he) {
			 throw new EJBException(he);
		 } finally {
			 close(session);
		 }
	 }
	 
    /**
     * WEBCAIB.
     * Obtiene las normativas por código de procedimiento, con el idioma indicado.
     * Si no existe en dicho idioma se buscarán por el idioma por defecto.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */
	 public Collection normativesByActuacio( String codiActuacio, String idioma ) {
		 
		 Session session = getSession();
			
		 try {
			 
			Query query = session.createQuery("select nor from Normativa as nor " +
					"join fetch nor.procedimientos as pro " +
					"where pro.id = :codi and nor.validacion = 1");
			
			query.setString("codi", codiActuacio);
			
			//ProcedimientoLocal procedimientoLocal = (ProcedimientoLocal) query.uniqueResult();
						
			List<Normativa> listaNormativa = query.list();
			return getListaNormativaModel(listaNormativa, idioma);			
						
	     } catch (HibernateException he) {
	    	 throw new EJBException(he);
	     } finally {
	         close(session);
	     }		
	 }
	 	 
    /**
     * WEBCAIB.
     * Obtiene las normativas por código de procedimiento, con el idioma indicado.
     * Si no existe en dicho idioma se buscarán por el idioma por defecto.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */	 
	 public Collection normativesByUO( String codiUO, String idioma, String any ) {
		 
		 Session session = getSession();
		 
		 try {

			 Query query = session.createQuery(
					"from Normativa as nor " +
			 		"where to_char(nor.fechaBoletin, 'YYYY') = :any " +
			 		"order by nor.fechaBoletin desc " );
			 			 			 
			 query.setString("any", any);			 
			 
			 List<Normativa> listaTmpNormativas = query.list();			 
			 List<Normativa> listaNormativas = new ArrayList();
			 
			 //Descartamos las normativas con id de UA distinto al pasado por parámtro			 
			 for (Normativa normativa : listaTmpNormativas ) {				 
				if ( normativa instanceof NormativaLocal &&
					( (NormativaLocal) normativa).getUnidadAdministrativa() != null && 
					( (NormativaLocal) normativa).getUnidadAdministrativa().getId().equals( Long.parseLong( codiUO) ) )

					listaNormativas.add( normativa );					 
			 }
			 			 	
			 return getListaNormativaModel(listaNormativas, idioma);
			
	     } catch (HibernateException he) {
	    	 throw new EJBException(he);
	     } finally {
	         close(session);
	     }		
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
		public int buscarNormativasActivas(UnidadAdministrativa unidadAdministrativa){
			Integer resultado = 0;
			Session session = getSession();
		
			try {
				
	        	Query query = null;
	        	if (unidadAdministrativa != null && unidadAdministrativa.getId() != null) {
	        		query = session.createQuery("select count(*) from NormativaLocal as nor where nor.unidadAdministrativa.id= :id and nor.validacion = :validacion ");
	        		query.setLong("id", unidadAdministrativa.getId());
	        		query.setInteger("validacion", Validacion.PUBLICA);
	        	} else {
	        		query = session.createQuery("select count(*) from NormativaLocal as nor where nor.validacion = :validacion ");
	        		query.setInteger("validacion", Validacion.PUBLICA);
	        	}
	        	
	        	resultado = (Integer) query.uniqueResult();
	    		
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		
			
			return resultado;
		}
	    
}
