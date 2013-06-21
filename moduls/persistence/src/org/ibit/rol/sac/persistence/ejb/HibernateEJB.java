package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.FlushMode;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.expression.Expression;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Comentario;
import org.ibit.rol.sac.model.ComentarioFicha;
import org.ibit.rol.sac.model.ComentarioProcedimiento;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaResumen;
import org.ibit.rol.sac.model.FichaResumenUA;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoFicha;
import org.ibit.rol.sac.model.HistoricoMateria;
import org.ibit.rol.sac.model.HistoricoNormativa;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.HistoricoUA;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validable;
import org.ibit.rol.sac.model.Validacion;

import es.caib.rolsac.persistence.hibernate.HibernateLocator;
import es.caib.rolsac.persistence.hibernate.SessionInterceptor;
import es.caib.rolsac.persistence.hibernate.SessionInterceptorBuilder;
import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Bean con la funcionalidad basica para interactuar con HIBERNATE.
 *
 * @ejb.bean
 *  view-type="remote"
 *  generate="false"
 * 
 * @ejb.security-role-ref role-name="sacsystem" role-link="${role.system}"
 * @ejb.security-role-ref role-name="sacadmin" role-link="${role.admin}"
 * @ejb.security-role-ref role-name="sacsuper" role-link="${role.super}"
 * @ejb.security-role-ref role-name="sacoper" role-link="${role.oper}"
 * @ejb.security-role-ref role-name="sacinfo" role-link="${role.info}"
 */
public abstract class HibernateEJB implements SessionBean {

	private static final long serialVersionUID = -988912799020601354L;

	protected static Log log = LogFactory.getLog(HibernateEJB.class);

    protected SessionFactory sessionFactory = null;
    protected SessionContext ctx = null;

    public static int RESULTATS_CERCA_TOTS = 99999;    
    
    public void setSessionContext(SessionContext ctx) {
        this.ctx = ctx;
    }

    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public void ejbCreate() throws CreateException {
        //log.debug("ejbCreate: " + this.getClass());
        sessionFactory = HibernateLocator.getSessionFactory();
    }

    public void ejbRemove() {
        //log.debug("ejbRemove: " + this.getClass());
        sessionFactory = null;
    }

    protected Session getSession() {
        try {
        	
        	Session session=null;

        	SessionInterceptor sessionInterceptor = SessionInterceptorBuilder.build();
        	

        	if(null==sessionInterceptor) {
        		session = sessionFactory.openSession();
        	}
        	else {
        		session = sessionFactory.openSession(sessionInterceptor);
        		sessionInterceptor.setSession(session);
        		sessionInterceptor.setSessionContext(ctx);
        	}
        	
            session.setFlushMode(FlushMode.COMMIT);  // (1)
            
            // (1) FIXME: ejaen@dgtic - diria que el flush mode.COMMIT  no te sentit doncs les transaccions 
            // en rolsac son CMT, i  en el moment del commit la sessio hibernate ja l'haura tancat el EJB

            log.debug("se ha abierto la sesion: "+ session);
            return session;	
        } catch (HibernateException e) {
            throw new EJBException(e);
        }
    }

    public void close(Session session) {
        if (session != null && session.isOpen()) {
            try {
                if (session.isDirty()) {
                    log.warn("Closing dirty session!!");
                }
                session.close();
            } catch (HibernateException e) {
                log.error(e);
            }
        }
    }

    /*
        Los roles de usuario son inclusivos.
        Los siguientes metodos permiten saber si un usuario tiene los permisos
        de un determinado rol, bien porque sea su rol, o porque tenga uno superior. 
    */

    protected boolean userIsSystem() {
        return ctx.isCallerInRole("sacsystem");
    }

    protected boolean userIsAdmin() {
        return userIsSystem() || ctx.isCallerInRole("sacadmin");
    }

    protected boolean userIsSuper() {
        return userIsAdmin() || ctx.isCallerInRole("sacsuper");
    }

    protected boolean userIsOper() {
        return userIsSuper() || ctx.isCallerInRole("sacoper");
    }
    
    protected boolean userIsInfo() {
        return userIsOper() || ctx.isCallerInRole("sacinfo");
    }

    protected boolean userIs(String role) {
        if ("sacinfo".equals(role)) {
            return userIsInfo();
        } else if ("sacoper".equals(role)) {
            return userIsOper();
        } else if ("sacsuper".equals(role)) {
            return userIsSuper();
        } else if ("sacadmin".equals(role)) {
            return userIsAdmin();
        } else if ("sacsystem".equals(role)) {
            return userIsSystem();
        } else {
            return false;
        }
    }

    /**
     * Devuelve la lista de roles del usuario actual 
     * @return
     */
    protected List<String> getUserRoles() {
    	List<String> ret = new ArrayList<String>();
    	if (userIsInfo()) {
    		ret.add("sacinfo");
    	}
    	if (userIsOper()) {
    		ret.add("sacoper");
    	}
    	if (userIsSuper()) {
    		ret.add("sacsuper");
    	}
    	if (userIsAdmin()) {
    		ret.add("sacadmin");
    	}
    	if (userIsSystem()) {
    		ret.add("sacsystem");
    	}
    	return ret;

    }

    
    protected boolean visible(Validable validable) {
        return (Validacion.PUBLICA.equals(validable.getValidacion()) || Validacion.RESERVA.equals(validable.getValidacion()) || userIsOper());
    }

    
    protected Usuario getUsuario(Session session) throws HibernateException {
        Criteria criteriUsu = session.createCriteria(Usuario.class);
        criteriUsu.add(Expression.eq("username", ctx.getCallerPrincipal().getName()));
        List usuaris;
        
        try {
        	usuaris = criteriUsu.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        }
        
        
        if (usuaris.isEmpty()) {
            throw new EJBException("El usuario actual no existe!");
        }
        return (Usuario) usuaris.get(0);
    }
    /**
     * Descripcion: Delete del valor del campo CodUA de la tabla Historico. El metodo devolvera una session para que el commit se  
     * realice en la funcion que llama a este metodo.
     * 
     * @param session  session
     * @param ua  Unidad Administrativa
     * @return   session
     * @throws HibernateException
     */
     
    protected Session deleteCodUaHistorico(Session session, UnidadAdministrativa ua)
	throws HibernateException {
    	//UAs
    	    Long idUA = ua.getId();
			Query query = session.createQuery("from HistoricoUA as hua where hua.ua.id = :ua_id");
			query.setParameter("ua_id", idUA, Hibernate.LONG);
			query.setCacheable(true);
			List<?> huas = query.list();
			Iterator<?> iterUA = huas.iterator();
			while(iterUA.hasNext()){
        		HistoricoUA histUA = (HistoricoUA) iterUA.next();
        		histUA.setUa(null);
				session.update(histUA);
			}
		//Ficha		
		   	Query queryFicha = session.createQuery("from HistoricoFicha as hp where hp.ua.id = :ua_id");
		   	queryFicha.setParameter("ua_id", idUA, Hibernate.LONG);
		   	queryFicha.setCacheable(true);
	    	
	    	List<?> hFicha = queryFicha.list();
			Iterator<?> iterFicha = hFicha.iterator();
			while(iterFicha.hasNext()){
				HistoricoFicha histFicha = (HistoricoFicha) iterFicha.next();
				histFicha.setUa(null);
				session.update(histFicha);
			}
			
			return session;
}

    protected Historico getHistorico(Session session, UnidadAdministrativa ua)
	    	throws HibernateException {
	HistoricoUA hua;
		Query query = session.createQuery("from HistoricoUA as hua where hua.ua.id = :ua_id");
		query.setParameter("ua_id", ua.getId(), Hibernate.LONG);
		query.setCacheable(true);
		List<?> huas = query.list();
		if (huas.isEmpty()) {
			hua = new HistoricoUA();
			hua.setUa(ua);
			if (ua.getTraduccion() != null) {
				hua.setNombre(((TraduccionUA) ua.getTraduccion()).getNombre());
			} else {
				hua.setNombre(ua.getCodigoEstandar());
			}
			session.save(hua);
			session.flush();
		} else {
			hua = (HistoricoUA) huas.get(0);
		}
	
		return hua;
	}
    
    protected Historico getHistorico(Session session, ProcedimientoLocal pr) throws HibernateException {
    	HistoricoProcedimiento hp;
    	Query query = session.createQuery("from HistoricoProcedimiento as hp where hp.procedimiento.id = :proc_id");
    	query.setParameter("proc_id", pr.getId(), Hibernate.LONG);
    	query.setCacheable(true);
    	List hprs = query.list();
    	if (hprs.isEmpty()) {
	    hp = new HistoricoProcedimiento();
	    hp.setProcedimiento(pr);
	    TraduccionProcedimientoLocal traduccionProcedimientoLocal = (TraduccionProcedimientoLocal) pr.getTraduccion();
	    if (traduccionProcedimientoLocal != null) {
	        hp.setNombre(traduccionProcedimientoLocal.getNombre());
	    } else {
	        hp.setNombre("-");
	    }
	    session.save(hp);
	    session.flush();
	} else {
	    hp = (HistoricoProcedimiento) hprs.get(0);
	}
	
	return hp;
	}
    
    protected Historico getHistorico(Session session, Normativa norm)
            throws HibernateException {
        HistoricoNormativa hnorm;
        Query query = session.createQuery("from HistoricoNormativa as hnorm where hnorm.normativa.id = :nor_id");
        query.setParameter("nor_id", norm.getId(), Hibernate.LONG);
        query.setCacheable(true);
        List hnorms = query.list();
        if (hnorms.isEmpty()) {
            hnorm = new HistoricoNormativa();
            hnorm.setNormativa(norm);
            if(norm.getTraduccion()!=null){
                hnorm.setNombre(((TraduccionNormativa) norm.getTraduccion()).getTitulo());
            }
            session.save(hnorm);
            session.flush();
        } else {
            hnorm = (HistoricoNormativa) hnorms.get(0);
        }

        return hnorm;
    }

    protected Historico getHistorico(Session session, Ficha ficha)
            throws HibernateException {
        HistoricoFicha hficha;
        Query query = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :ficha_id and hficha.materia.id is null and hficha.ua.id is null ");
        query.setParameter("ficha_id", ficha.getId(), Hibernate.LONG);
        query.setCacheable(true);
        List hfichas = query.list();
        if (hfichas.isEmpty()) {
            hficha = new HistoricoFicha();
            hficha.setFicha(ficha);
            if(ficha.getTraduccion()!=null){
                hficha.setNombre(((TraduccionFicha) ficha.getTraduccion()).getTitulo());
            }
            session.save(hficha);
            session.flush();
        } else {
            hficha = (HistoricoFicha) hfichas.get(0);
        }

        return hficha;
    }

    protected Historico getHistorico(Session session, Materia mat)
    	throws HibernateException {
    	HistoricoMateria hmat;
    	Query query = session.createQuery("from HistoricoMateria as hmat where hmat.materia.id = :materia_id");
    	query.setParameter("materia_id", mat.getId(), Hibernate.LONG);
    	query.setCacheable(true);
    	List hmats = query.list();
    	if (hmats.isEmpty()) {
    		hmat = new HistoricoMateria();
    		hmat.setMateria(mat);
            if(mat.getTraduccion()!=null){
    		    hmat.setNombre(((TraduccionMateria) mat.getTraduccion()).getNombre());
            }
    		session.save(hmat);
    		session.flush();
    	} else {
    		hmat = (HistoricoMateria) hmats.get(0);
    	}

    	return hmat;
    }


    protected void addOperacion(Session session, UnidadAdministrativa ua, int operacion) throws HibernateException {
        Auditoria aud = new Auditoria();
        aud.setUsuario(ctx.getCallerPrincipal().getName());
        aud.setFecha(new Date());
        aud.setCodigoOperacion(operacion);
        aud.setHistorico(getHistorico(session, ua));
        session.save(aud);
        session.flush();
    }

    protected void addOperacion(Session session, ProcedimientoLocal pr, int operacion) throws HibernateException {
        Auditoria aud = new Auditoria();
        aud.setUsuario(ctx.getCallerPrincipal().getName());
        aud.setFecha(new Date());
        aud.setCodigoOperacion(operacion);
        aud.setHistorico(getHistorico(session, pr));
        session.save(aud);
        session.flush();
    }

    protected void addOperacion(Session session, Normativa norm, int operacion) throws HibernateException {
        Auditoria aud = new Auditoria();
        aud.setUsuario(ctx.getCallerPrincipal().getName());
        aud.setFecha(new Date());
        aud.setCodigoOperacion(operacion);
        aud.setHistorico(getHistorico(session, norm));
        session.save(aud);
        session.flush();
    }

    protected void addOperacion(Session session, Ficha ficha, int operacion) throws HibernateException {
    	Auditoria aud = new Auditoria();
        aud.setUsuario(ctx.getCallerPrincipal().getName());
        aud.setFecha(new Date());
        aud.setCodigoOperacion(operacion);
        aud.setHistorico(getHistorico(session, ficha));
        session.save(aud);
        session.flush();
    }
    
    protected void addOperacion(Session session, Materia materia, int operacion) throws HibernateException {
    	Auditoria aud = new Auditoria();
        aud.setUsuario(ctx.getCallerPrincipal().getName());
        aud.setFecha(new Date());
        aud.setCodigoOperacion(operacion);
        aud.setHistorico(getHistorico(session, materia));
        session.save(aud);
        session.flush();
    }

    // Metodes de seguretat.

    /**
     * Comprueba si un usuario puede modificar una unidad administrativa.
     * El parametro modificacion indica el nivel de acceso, si solo a efectos de relacionar informacion (false)
     *  o tambien de modificacion (true).
     */
    protected boolean tieneAcceso(Usuario usuario, UnidadAdministrativa unidad, boolean modificacion) {
        return userIsSystem()
                || ( (!modificacion || userIsSuper()) && usuario.hasAccess(unidad));
    }

    /**
     * Comprueba si un usuario puede modificar los contenidos de una seccion.
     */
    protected boolean tieneAcceso(Usuario usuario, Seccion seccion) {
        return (userIsSystem() || userIs(seccion.getPerfil()));
    }

    /**
     * Comprueba si un usuario puede modificar un procedimiento.
     */
    protected boolean tieneAcceso(Usuario usuario, ProcedimientoLocal procedimiento) {
        return (tieneAccesoValidable(usuario, procedimiento)
                && tieneAcceso(usuario, procedimiento.getUnidadAdministrativa(), false));
    }

    /**
     * Comprueba si un usuario puede modificar una normativa.
     * Tendra acceso si tiene acceso a la validacion y la normativa no esta relacionada con ninguna unidad o
     * tiene acceso a la unidad con la que esta relacionada.
     */
    protected boolean tieneAcceso(Usuario usuario, Normativa normativa) {
        if (!tieneAccesoValidable(usuario, normativa)) {
            return false;
        }

        if (normativa instanceof NormativaLocal) {
            NormativaLocal normaLocal = ((NormativaLocal) normativa);
            if (normaLocal.getUnidadAdministrativa() != null) {
                return tieneAcceso(usuario, normaLocal.getUnidadAdministrativa(), false);
            }
        }

        return true;
    }

    /**
     * Comprueba si un usuario puede modificar una ficha.
     * Tendra acceso si tiene acceso a la validacion y no esta relacionada
     *  o tiene acceso a alguna unidad/seccion con
     * la que esta relacionada.
     */
    protected boolean tieneAcceso(Usuario usuario, Ficha ficha) {
        if (!tieneAccesoValidable(usuario, ficha)) {
            return false;
        }

        if (ficha.getFichasua().isEmpty()) {
            return true;
        }

        for (Iterator iterator = ficha.getFichasua().iterator(); iterator.hasNext();) {
            FichaUA fichaUA = (FichaUA) iterator.next();
            if (tieneAcceso(usuario, fichaUA)) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Comprueba si un usuario puede modificar una relacion ficha - unidad.
     * Tendra acceso si tiene acceso a la seccion y a la unidad.
     * Si la unidad es <code>null</code> es una relacion general y debe ser usuario de
     * sistema.
     */
    protected boolean tieneAcceso(Usuario usuario, FichaUA fichaUA) {
        UnidadAdministrativa unidad = fichaUA.getUnidadAdministrativa();
        return tieneAcceso(usuario, fichaUA.getSeccion()) &&
                (unidad == null ? userIsSystem() : tieneAcceso(usuario, unidad, false));
    }
    
   
    /**
     * Comprueba si un usuario puede modificar un documento.
     * Tendra acceso si tiene acceso a la ficha o al procedimiento a que pertenece el documento.
     */
    protected boolean tieneAcceso(Usuario usuario, Documento documento) {
        if (documento.getFicha() != null) {
            return tieneAcceso(usuario, documento.getFicha());
        }
        if (documento.getProcedimiento() != null) {
            return tieneAcceso(usuario, documento.getProcedimiento());
        }
        return true;
    }

    /**
     * Comprueba si un usuario puede modificar un tramite.
     * Tendra acceso si tiene acceso al procedimiento.
     */
    protected boolean tieneAcceso(Usuario usuario, Tramite tramite) {
        return tramite.getProcedimiento() == null || tieneAcceso(usuario, tramite.getProcedimiento());
    }

    /**
     * Comprueba si un usuario puede modificar un formulario.
     * Tendra acceso si tiene acceso al tramite.
     */
    protected boolean tieneAcceso(Usuario usuario, Formulario formulario) {
        return formulario.getTramite() == null || tieneAcceso(usuario, formulario.getTramite());
    }

    /**
     * Comprueba si un usuario puede modificar un edificio.
     * Tendra acceso si el edificio no pertenece a ninguna unidad o si tiene
     * acceso a alguna de las unidades a las que pertenece.
     */
    protected boolean tieneAcceso(Usuario usuario, Edificio edificio) {
        if (edificio.getUnidadesAdministrativas().isEmpty()) {
            return true;
        }

        for (Iterator iterator = edificio.getUnidadesAdministrativas().iterator(); iterator.hasNext();) {
            UnidadAdministrativa unidad = (UnidadAdministrativa) iterator.next();
            if (tieneAcceso(usuario, unidad, true)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si un usuario puede modificar un personal.
     * Tendra acceso si puede acceder a la unidad administrativa.
     */
    protected boolean tieneAcceso(Usuario usuario, Personal personal) {
        return tieneAcceso(usuario, personal.getUnidadAdministrativa(), true);
    }

    /**
     * Comprueba si un usuario puede modificar un comentario.
     * Tendra acceso si es el informador que lo creo o tiene acceso a la ficha
     * o al procedimiento a que pertenece el comentario.
     */
    protected boolean tieneAcceso(Usuario usuario, Comentario comentario) {
        if (ctx.isCallerInRole("sacinfo")
                && comentario.getUsuario() != null
                && comentario.getUsuario().getUsername().equals(usuario.getUsername())) {
            return true;
        }
        if (comentario instanceof ComentarioFicha) {
            return tieneAcceso(usuario, ((ComentarioFicha) comentario).getFicha());
        }
        if (comentario instanceof ComentarioProcedimiento) {
            return tieneAcceso(usuario, ((ComentarioProcedimiento) comentario).getProcedimiento());
        }
        return true;
    }
    
    /**
     * Comprueba si un usuario puede modificar un contenido.
     */
    protected boolean tieneAccesoValidable(Usuario usuario, Validable validable) {
        return (userIsSuper() || validable.getValidacion().equals(Validacion.INTERNA));
    }
        
    /**
     * Método que gestiona la paginación de las listas de entidades de tablas maestras.
     * 
     * @param pagina Número de página actual
     * @param resultados Número de resultados a mostrar
     * @return ResultadoBusqueda Objeto con la información solicitada (registros de página y total)
     */
    protected ResultadoBusqueda listarTablaMaestraPaginada(int pagina, int resultats, List<?> listaEntidades) {
    	
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	
    	int indiceDesde = pagina * resultats;
    	int indiceHasta = indiceDesde + resultats;
    	int tamanyoLista = listaEntidades.size();
    	
    	if (indiceHasta > tamanyoLista) {
    		indiceHasta = tamanyoLista;
    	}
    		
    	resultadoBusqueda.setTotalResultados(listaEntidades.size());
    	
    	if ( resultats < RESULTATS_CERCA_TOTS ) {
    		listaEntidades = listaEntidades.subList( indiceDesde, indiceHasta );    		
    	}
    	
    	resultadoBusqueda.setListaResultados(listaEntidades);
    	
    	return resultadoBusqueda;
    }
    
    /**
     * Método encargado de realizar el casting de listas no tipadas a listas
     * tipadas
     * 
     * @param <T>
     * @param clazz Clase del tipo de objeto contenido en la lista
     * @param c Colección a ser tipada
     * @return Lista tipada
     */
    protected <T>List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>();
        if (c != null) {     
            r = new ArrayList<T>(c.size());            
            for (Object o : c) {
            	r.add(clazz.cast(o));
            }
        }
        return r;
    }    	

    /**
     * Método encargado de realizar el casting de sets no tipados a sets
     * tipados
     * 
     * @param <T>
     * @param clazz Clase del tipo de objeto contenido en la lista
     * @param c Colección a ser tipada
     * @return Set tipado
     */    
    protected <T>Set<T> castSet(Class<? extends T> clazz, Collection<?> c) {
        Set<T> r = new HashSet<T>();
        if (c != null) {     
            r = new HashSet<T>(c.size());            
            for (Object o : c) {
            	r.add(clazz.cast(o));
            }
        }
        return r;    	
    }
}