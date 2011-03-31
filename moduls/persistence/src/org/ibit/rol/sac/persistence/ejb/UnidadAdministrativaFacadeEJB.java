package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.FichaUATransferible;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

//import es.caib.moduls.indra.Parametros;
//import es.caib.sac.indra.moduls.Modulos;
//import es.caib.sac.unitatOrganica.model.LlocModel;
//import es.caib.sac.unitatOrganica.model.UOModel;
import org.ibit.rol.sac.persistence.util.Cadenas;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.*;

/**
 * SessionBean para mantener y consultar Unidades Administrativas.
 *
 * @ejb.bean
 *  name="sac/persistence/UnidadAdministrativaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.UnidadAdministrativaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UnidadAdministrativaFacadeEJB extends HibernateEJB implements UnidadAdministrativaDelegateI {

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
     * Crea una Unidad Administrativa RAIZ
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system}"
     */
    public Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad) {
        Session session = getSession();
        try {
            int orden = numUnidadesRaiz(session);
            unidad.setOrden(orden);
            session.save(unidad);
            addOperacion(session, unidad, Auditoria.INSERTAR);
            session.flush();
            Actualizador.actualizar(unidad);
            return unidad.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Crea una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.organigrama}"
     */
    public Long crearUnidadAdministrativa(UnidadAdministrativa unidad, Long padre_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoUnidad(padre_id, true)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }

            UnidadAdministrativa padre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, padre_id);
            padre.addHijo(unidad);
            session.save(unidad);
            addOperacion(session, unidad, Auditoria.INSERTAR);
            session.flush();
            Actualizador.actualizar(unidad);
            return unidad.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Actualiza una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad, Long padre_id) {
        Session session;

        Long old_padre_id = (unidad.getPadre() == null ? null : unidad.getPadre().getId());

        boolean newIsNull = (padre_id == null);
        boolean oldIsNull = (old_padre_id == null);

        if (newIsNull && !userIsSystem()) {
            throw new SecurityException("Solo el usuario de sistema puede crear raices.");
        }

        boolean change = (newIsNull ? !oldIsNull : !padre_id.equals(old_padre_id));
        
        if (change) {
        	if (!getAccesoManager().tieneAccesoMoverOrganigrama(old_padre_id, padre_id)) {
        		throw new SecurityException("No tiene acceso al nodo superior anterior o al actual.");
        	}
        }

        if (!getAccesoManager().tieneAccesoUnidad(unidad.getId(), true)) {
            throw new SecurityException("No tiene acceso a la unidad");
        }

        session = getSession();
        try {
            session.update(unidad);
            if (change) {
                if (!oldIsNull) {
                    UnidadAdministrativa oldPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, old_padre_id);
                    oldPadre.removeHijo(unidad);
                }
                if (!newIsNull) {
                    UnidadAdministrativa newPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, padre_id);
                    newPadre.addHijo(unidad);
                }
                if (newIsNull || oldIsNull) {
                    session.flush();
                    Query query = session.getNamedQuery("unidades.root");
                    List lista = query.list();
                    for (int i = 0; i < lista.size(); i++) {
                        UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
                        uni.setOrden(i);
                    }
                }
            }
            addOperacion(session, unidad, Auditoria.MODIFICAR);
            session.flush();
            Actualizador.actualizar(unidad);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    
    
    /**
     * Lista los hijos de una unidad Administrativa.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarDescendientesConse(String id) {
        Session session = getSession();
        try {
        	 Query query = session.createQuery("");
             query = session.createQuery("select ua.id from UnidadAdministrativa as ua " +
             "WHERE ua.padre="+id+" OR  ua.padre IN (SELECT ua2.padre from UnidadAdministrativa as ua2 WHERE ua2.padre="+id+")");
             query.setCacheable(true);
             return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    /**
     * Lista los hijos de una unidad Administrativa.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarHijosUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            Hibernate.initialize(ua.getHijos());

            List result = new ArrayList();
            for (int i = 0; i < ua.getHijos().size(); i++) {
                UnidadAdministrativa uaHijo = (UnidadAdministrativa) ua.getHijos().get(i);
                if (uaHijo != null && visible(uaHijo)) {
                    result.add(uaHijo);
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
     * Lista las unidades Administrativas raiz de un usuario.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarUnidadesAdministrativasRaiz() {
        Session session = getSession();
        try {
            Usuario usu = getUsuario(session);
            if (userIsSystem()) {
                return session.createCriteria(UnidadAdministrativa.class).add(Expression.isNull("padre")).list();
            } else {
                //List uas = new ArrayList(session.filter(usu.getUnidadesAdministrativas(), "where this.padre is null"));
                // Les arrel no tenen perque tenir el pare null ja que un usuari pot tenir assignat un node qualsevol.
                List uas = new ArrayList(usu.getUnidadesAdministrativas());

                // Eliminamos unidades duplicadas, por haber ya un antecesor.
                Set duplicadas = new HashSet();
                for (int i = 0; i < uas.size(); i++) {
                    UnidadAdministrativa unidad = (UnidadAdministrativa) uas.get(i);
                    UnidadAdministrativa padre = unidad.getPadre();
                    boolean duplicada = false;
                    while (!duplicada && padre != null) {
                        if (uas.contains(padre)) {
                            duplicada = true;
                            duplicadas.add(unidad);
                        }
                        padre = padre.getPadre();
                    }
                }
                uas.removeAll(duplicadas);
                return uas;
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la primera unidad Administrativas raiz de un usuario.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa obtenerPrimeraUnidadAdministrativaRaiz() {
        Session session = getSession();
        try {
            List result;
            Criteria criterio = session.createCriteria(UnidadAdministrativa.class);
            criterio.add(Expression.isNull("padre"));
            criterio.addOrder(Order.asc("orden"));
            result = criterio.list();
            return (UnidadAdministrativa) result.get(0);

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista las unidades Administrativas raiz de un usuario que estan publicadas o no.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarUnidadesAdministrativasRaiz(boolean publicadas) {
        Session session = getSession();
        try {
            Usuario usu = getUsuario(session);

            Criteria criteria = session.createCriteria(UnidadAdministrativa.class);
            criteria.add(Expression.isNull("padre"));
            criteria.add(publicadas ? Expression.isNotNull("businessKey") : Expression.isNull("businessKey"));

            if (!userIsSystem()) {
                criteria.createAlias("usuarios", "usu");
                criteria.add(Expression.eq("usu.username", usu.getUsername()));
            }

            return criteria.list();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista los padres de unidad Administrativa.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarPadresUnidadAdministrativa(Long id) {
        Session session = getSession();
        List padres = new Vector();
        try {
            UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            padres.add(unidadAdministrativa);

            UnidadAdministrativa padre = unidadAdministrativa.getPadre();
            while (padre != null) {
                padres.add(padre);
                padre = padre.getPadre();
            }
            Collections.reverse(padres);
            return padres;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    
    /**
     * Lista los padres de unidad Administrativa, comprobando el acceso.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarPadresUnidadAdministrativaAcceso(Long id) {

        List padres = listarPadresUnidadAdministrativa(id);
        List ret = new Vector();
        	
    	for (int i=0;i<padres.size();i++) {
    		UnidadAdministrativa uni = (UnidadAdministrativa)padres.get(i);
    		if (getAccesoManager().tieneAccesoUnidad( uni.getId(), false)) 
    			ret.add(uni);
    	}
        	
       	return ret;

    }
    
    /**
     * Listar BusinessKeys de la unidades administrativas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarBusinessKey() {
        Session session = getSession();
        try {
            Query query = session.createQuery("select ua.businessKey from UnidadAdministrativa ua where ua.businessKey is not null");
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Busca todas las Unidades Administrativas que cumplen los criterios de búsqueda
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List buscarUnidadesAdministrativas(Map parametros, Map traduccion) {
        Session session = getSession();
        try {
            Usuario usuario = getUsuario(session);
            List params = new ArrayList();

            String sQuery = populateQuery(parametros, traduccion, params);
            Query query = session.createQuery("from UnidadAdministrativa as unidad, unidad.traducciones as trad " + sQuery);
            for (int i = 0; i < params.size(); i++) {
                String o = (String)params.get(i);
                query.setString(i, o);
            }
            List uas = query.list();

            List uasAux = new ArrayList();
            for (Iterator iterUas = uas.iterator(); iterUas.hasNext();) {
                UnidadAdministrativa ua = (UnidadAdministrativa) iterUas.next();
                if (tieneAcceso(usuario, ua, false)) {
                    uasAux.add(ua);
                }
            }

            return uasAux;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa obtenerUnidadAdministrativa(Long id) {
        Session session = getSession();
        try {
            return (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
	 * Obtiene una Unidad Administrativa {PORMAD}
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public UnidadAdministrativa obtenerUnidadAdministrativaPM(Long id) {
		Session session = getSession();
		UnidadAdministrativa ua = null;
		try {
			ua = (UnidadAdministrativa) session.load(
					UnidadAdministrativa.class, id);
			 Hibernate.initialize(ua.getEdificios()); 
			 
			 return ua;

		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
    

    /**
     * Dice si existe una unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean existeUnidadAdministrativa(Long id) {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(UnidadAdministrativa.class);
            criteri.add(Expression.eq("id", id));
            UnidadAdministrativa unidad = (UnidadAdministrativa)criteri.uniqueResult();
            return unidad != null;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la información general de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa informacionGeneral(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getTratamiento());
                Hibernate.initialize(ua.getHijos());
                return ua;
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Obtiene informacion para el front de una Unidad Administrativa (PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa consultarUAPormad(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (ua.getValidacion().equals(Validacion.PUBLICA)) {
                Hibernate.initialize(ua.getTratamiento());
                Hibernate.initialize(ua.getEdificios());
                Hibernate.initialize(ua.getHijos());
                return ua;
            } else {
                throw new SecurityException("La unidad administrativa no es Publica");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }



    /**
     * Obtiene informacion para el front de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa consultarUnidadAdministrativa(Long id) {
        Session session = getSession();
        
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            
            if (visible(ua)) {
                Hibernate.initialize(ua.getFotop());
                Hibernate.initialize(ua.getFotog());
                Hibernate.initialize(ua.getLogoh());
                Hibernate.initialize(ua.getLogov());
                Hibernate.initialize(ua.getLogos());
                Hibernate.initialize(ua.getLogot());
                Hibernate.initialize(ua.getTratamiento());
                Hibernate.initialize(ua.getEspacioTerrit());
                Hibernate.initialize(ua.getEdificios());
                Hibernate.initialize(ua.getUnidadesMaterias());
                Hibernate.initialize(ua.getPadre());
                Hibernate.initialize(ua.getHijos());
                Hibernate.initialize(ua.getFichasUA());
                Hibernate.initialize(ua.getTodasfichas());
                Hibernate.initialize(ua.getProcedimientos());
                Hibernate.initialize(ua.getPersonal());
                Hibernate.initialize(ua.getNormativas());
                
                if (userIsAdmin()) {
                    Hibernate.initialize(ua.getUsuarios());
                }
                return ua;
            } else {
                throw new SecurityException("La unitat administrativa no és publica con id "+ua.getId() );
            }

        } catch (HibernateException he) {
            throw new EJBException("No s' ha trobat la unitat administrativa sol·licitada",he);
        } finally {
            close(session);
        }
    }


      /**
     * Obtiene informacion para el front de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id) {
        Session session = getSession();

        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

            if (visible(ua)) {
                Hibernate.initialize(ua.getFotop());
                Hibernate.initialize(ua.getFotog());
                Hibernate.initialize(ua.getLogoh());
                Hibernate.initialize(ua.getLogov());
                Hibernate.initialize(ua.getLogos());
                Hibernate.initialize(ua.getLogot());
                Hibernate.initialize(ua.getTratamiento());
                Hibernate.initialize(ua.getEspacioTerrit());
                Hibernate.initialize(ua.getEdificios());
                Hibernate.initialize(ua.getUnidadesMaterias());
                Hibernate.initialize(ua.getPadre());
                Hibernate.initialize(ua.getHijos());
                Hibernate.initialize(ua.getFichasUA());
                Hibernate.initialize(ua.getTodasfichas());
                Hibernate.initialize(ua.getProcedimientos());
                Hibernate.initialize(ua.getPersonal());
                Hibernate.initialize(ua.getNormativas());

                if (userIsAdmin()) {
                    Hibernate.initialize(ua.getUsuarios());
                }
                return ua;
            } else {
            	log.error("L'unidad administrativa no es visible;");
                return null;
            }

        } catch (HibernateException he) {
            throw new EJBException("No s' ha trobat la unitat administrativa sol·licitada",he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene informacion para el front del pormad (PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa consultarUnidadAdministrativaPORMAD(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getFotop());
                Hibernate.initialize(ua.getFotog());
                Hibernate.initialize(ua.getTratamiento());
                Hibernate.initialize(ua.getEdificios());
                Hibernate.initialize(ua.getHijos());

                return ua;
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Repara el orden de todas las unidades administrativas(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked = "true"
     */
    @SuppressWarnings("unchecked")
	public void repararOrdenFichasUA() {
        Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(UnidadAdministrativa.class);

            List<UnidadAdministrativa> unidades= criteria.list();
            for(UnidadAdministrativa unidad : unidades){
            	unidad.setFichasUA(resetOrden(unidad.getFichasUA()));
            }
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    private List<FichaUA> resetOrden(List<FichaUA> fichas){
    	List<FichaUA> resultado = new ArrayList<FichaUA>();
    	int temp = 0;
    	for(FichaUA ficha : fichas){
    		if(ficha != null){
    			ficha.setOrden(temp);
    			temp++;
    			resultado.add(ficha);
    		}
    	}
    	return resultado;
    }

    /**
     * Obtiene una Unidad Administrativa por el nombre.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa obtenerUnidadAdministrativaPorNombre(String nombre) {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("unidades.byname");
            query.setParameter("nombre", nombre);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            UnidadAdministrativa ua = (UnidadAdministrativa) result.get(0);
            if (visible(ua)) {
                return ua;
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene una Unidad Administrativa por el codigo Estandar(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(String codEst) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from UnidadAdministrativa as ua where ua.codigoEstandar=:codEst");
            query.setParameter("codEst", codEst);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            UnidadAdministrativa ua = (UnidadAdministrativa) result.get(0);
            if (visible(ua)) {
                Hibernate.initialize(ua.getFotop());
                Hibernate.initialize(ua.getHijos());
                Hibernate.initialize(ua.getFotog());
                Hibernate.initialize(ua.getLogoh());
                Hibernate.initialize(ua.getLogov());
                Hibernate.initialize(ua.getLogos());
                Hibernate.initialize(ua.getLogot());
                Hibernate.initialize(ua.getTratamiento());
                Hibernate.initialize(ua.getUnidadesMaterias());
                Hibernate.initialize(ua.getEdificios());
                return ua;
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la foto pequenya de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerFotoPequenyaUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getFotop());
                return ua.getFotop();
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la foto grande de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerFotoGrandeUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getFotog());
                return ua.getFotog();
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    // CODI NOU PELS LOGOS
    /**
     * Obtiene la logo horitzontal de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerLogoHorizontalUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getLogoh());
                return ua.getLogoh();
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    // CODI NOU PELS LOGOS
    /**
     * Obtiene la logo vertical de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerLogoVerticalUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getLogov());
                return ua.getLogov();
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    // CODI NOU PELS LOGOS
    /**
     * Obtiene la logo saludo de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerLogoSaludoUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getLogos());
                return ua.getLogos();
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

//  CODI NOU PELS LOGOS
    /**
     * Obtiene la logo saludo vertical de una Unidad Administrativa
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerLogoSaludoVerticalUA(Long id) {
        Session session = getSession();
        try {
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            if (visible(ua)) {
                Hibernate.initialize(ua.getLogot());
                return ua.getLogot();
            } else {
                throw new SecurityException("El usuario no tiene el rol operador");
            }

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
     * Intercambia el orden de las UA
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void cambiarOrden(Long ua1_id, Long ua2_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoUnidad(ua1_id, true)) {
                throw new SecurityException("No tiene acceso a la unidad anterior");
            }
            if (!getAccesoManager().tieneAccesoUnidad(ua2_id, true)) {
                throw new SecurityException("No tiene acceso a la unidad posterior");
            }
            UnidadAdministrativa p1 = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua1_id);
            UnidadAdministrativa p2 = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua2_id);
            long aux = p1.getOrden();
            p1.setOrden(p2.getOrden());
            p2.setOrden(aux);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * añade un nuevo edificio a la unidad administrativa
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void anyadirEdificio(Long edificio_id, Long ua_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoUnidad(ua_id, true)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }
            Edificio edificio = (Edificio) session.load(Edificio.class, edificio_id);
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua_id);
            Hibernate.initialize(ua.getHijos());
            Hibernate.initialize(ua.getUnidadesMaterias());

            ua.getEdificios().add(edificio);  				//sin inverse=true 
            //edificio.getUnidadesAdministrativas().add(ua);  	//con inverse=true 

            session.flush();
            Actualizador.actualizar(edificio,ua.getId());
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * elimina un edificio del al unidad administrativa
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
     */
    public void eliminarEdificio(Long edificio_id, Long ua_id) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoUnidad(ua_id, true)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }
            Edificio edificio = (Edificio) session.load(Edificio.class, edificio_id);
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, ua_id);
            Hibernate.initialize(ua.getHijos());
            Hibernate.initialize(ua.getUnidadesMaterias());
            ua.getEdificios().remove(edificio);
            edificio.getUnidadesAdministrativas().remove(ua);
            session.flush();
            Actualizador.borrar(edificio,ua.getId());

        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }


    /**
     * Descripción: Autorización eliminar Unidad Administrativa.Devuleve true si tiene acceso.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.organigrama}"
     */
    public boolean autorizarEliminarUA(Long idUa){
    	return (getAccesoManager().tieneAccesoUnidad(idUa, true));  
        }

    /**
     * Descripción: Eliminar una unidad administrativa. Se podra eliminar la UA, 
     * si no tiene elementos relacionados (Procedimientos,Normativas,etc.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.organigrama}"
     * 
     */
    public void eliminarUaSinRelaciones(Long idUA) {
        Session session = getSession();

        try {

        	UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
        	Boolean isRaiz = ua.isRaiz();

        	if (!isRaiz) {
        		// Si no és arrel, el pot borrar només si té accés a l'antecesor. 
        		if (!autorizarEliminarUA(ua.getPadre().getId()))
        			throw new SecurityException("No tiene acceso al antecesor de la unidad.");
        	} else {
        		// Si és arrel, només si és ADMIN o SYSTEM i té accés a l'unitat.
    			if (!userIsAdmin() || !autorizarEliminarUA(ua.getId()))
    				throw new SecurityException("No tiene acceso a la unidad.");
        	}

            addOperacion(session, ua, Auditoria.BORRAR);
        	getHistorico(session, ua);

            session = deleteCodUaHistorico(session,ua);   

            ua.getEdificios().clear();
            ua.getUsuarios().clear();
            if (!isRaiz) {
	            ua.getPadre().removeHijo(ua);
            }else{
            Usuario usuario = getUsuario(session);
            usuario.getUnidadesAdministrativas().remove(ua);
			}

            Long id = ua.getId();
            if(ua instanceof UnidadAdministrativaRemota){
            	AdministracionRemota admin = ((UnidadAdministrativaRemota)ua).getAdministracionRemota();
            	if(admin!=null)
            		admin.removeUnidadAdministrativaRemota((UnidadAdministrativaRemota)ua);
            }else{
            	Actualizador.borrar(new UnidadAdministrativa(id));
            }

            session.delete(ua);
            session.flush();

            if (isRaiz) {
            Query query = session.getNamedQuery("unidades.root");
	            List<?> lista = query.list();
            for (int i = 0; i < lista.size(); i++) {
                UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
                uni.setOrden(i);
            }
            session.flush();
            }
        } catch (HibernateException he) {
            throw new EJBException("El sistema está actualizando los datos, espere unos minutos.");
        } finally {
            close(session);
        }
    }
    
    
    /**
	 * Borra una unidad administrativa.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	 public void borrarUnidadAdministrativa(Long id) {
	        
	        Session session = getSession();

	        try {
	            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
	            if (ua.isRaiz()) {
	                throw new SecurityException("No puede eliminar una unidad raiz.");
	            } else {
	                if (!getAccesoManager().tieneAccesoUnidad(ua.getPadre().getId(), true)) {
	                    throw new SecurityException("No tiene acceso al padre de la unidad");
	                }
	            }

	        Long padre_id = ua.getPadre().getId();  

	        session.update(ua);

	        UnidadAdministrativa uaPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, padre_id);

	        uaPadre.darDeBajaUA(ua);
	        ua.darDeBajaHijosUA(ua);

	            addOperacion(session, ua, Auditoria.BORRAR);
	            session.flush();

	            if(ua instanceof UnidadAdministrativaRemota){
	            	AdministracionRemota admin = ((UnidadAdministrativaRemota)ua).getAdministracionRemota();
	            	if(admin!=null)
	            		admin.removeUnidadAdministrativaRemota((UnidadAdministrativaRemota)ua);
	            }else{
	            	Actualizador.borrar(ua);
	            }

	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
	   
	    }

	 
	/**
	 * Borra una unidad administrativa raiz.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 */
	public void borrarUnidadAdministrativaRaiz(Long id) {
		Session session = getSession();

		try {
			if (!getAccesoManager().tieneAccesoUnidad(id, true)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}

			UnidadAdministrativa ua = (UnidadAdministrativa) session.load(
					UnidadAdministrativa.class, id);
			if (!ua.isRaiz()) {
				throw new SecurityException(
						"No puede eliminar una unidad que no sea raiz.");
			}

			addOperacion(session, ua, Auditoria.BORRAR);
			Historico historico = getHistorico(session, ua);
			((HistoricoUA) historico).setUa(null);

			ua.getEdificios().clear();
			ua.getUsuarios().clear();
			Usuario usuario = getUsuario(session);
			usuario.getUnidadesAdministrativas().remove(ua);

			List fichasUA = ua.getFichasUA();
			for (Iterator iter = fichasUA.iterator(); iter.hasNext();) {
				FichaUA ficha = (FichaUA) iter.next();
				if (ficha != null) {
					Seccion seccion = ficha.getSeccion();
					seccion.removeFichaUA(ficha);
				}
			}

			for (UnidadMateria uniMat : (Set<UnidadMateria>) ua
					.getUnidadesMaterias()) {
				uniMat.getMateria().removeUnidadMateria(uniMat);
				session.delete(uniMat);
			}

			if (ua instanceof UnidadAdministrativaRemota) {
				AdministracionRemota admin = ((UnidadAdministrativaRemota) ua)
						.getAdministracionRemota();
				if (admin != null)
					admin
							.removeUnidadAdministrativaRemota((UnidadAdministrativaRemota) ua);
			} else {
				Actualizador.borrar(new UnidadAdministrativa(id));
			}

			session.delete(ua);
			session.flush();

			Query query = session.getNamedQuery("unidades.root");
			List lista = query.list();
			for (int i = 0; i < lista.size(); i++) {
				UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
				uni.setOrden(i);
			}
			session.flush();
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

    /**
     * Obtiene una unidad administrativa por BusinessKey.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa obtenerUnidadPorBusinessKey(String bk) {
        Session session = getSession();
        try {
            Criteria criterio = session.createCriteria(UnidadAdministrativa.class);
            criterio.setMaxResults(1);
            criterio.add(Expression.eq("businessKey", bk));
            List result = criterio.list();
            if (result.isEmpty()) {
                return null;
            }

            return (UnidadAdministrativa) result.get(0);
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }

    }

    /**
     * Fija el BusinessKey de una Unidad Administrativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void fijarBusinessKey(Long id, String businessKey) {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoUnidad(id, true)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }
            UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
            ua.setBusinessKey(businessKey);
            Actualizador.actualizar(ua);
            session.flush();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Borrar el BusinessKey de una Unidad Administrativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public boolean borrarBusinessKey(String businessKey) {
        Session session = getSession();
        try {
            Criteria criterio = session.createCriteria(UnidadAdministrativa.class);
            criterio.setMaxResults(1);
            criterio.add(Expression.eq("businessKey", businessKey));
            List result = criterio.list();
            if (result.isEmpty()) {
                return false;
            }

            UnidadAdministrativa ua = (UnidadAdministrativa) result.get(0);
            if (!getAccesoManager().tieneAccesoUnidad(ua.getId(), true)) {
                throw new SecurityException("No tiene acceso a la unidad");
            }

            ua.setBusinessKey(null);
            Actualizador.actualizar(ua);
            session.flush();
            return true;
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /* Metode fet per el portal de salut, fet de na Marilen */
    /**
     * carga el arbol de unidades Administrativas a partir de un id
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa cargarArbolUnidad(Long id) {
        Session session = getSession();
        try {
            return cargarHijosUnidad(id,session);
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }
     /* Metode fet pel portal de salut, fet de na Marilen */
     /**
     * Carga los identificadores de una unidad y de sus hijos de manera recursiva
     * @ejb.interface-method
     * @ejb.permission  unchecked="true"
     */
    public List cargarArbolUnidadId(Long id) {
        Session sessio = getSession();
        try{
            List ids = new ArrayList();
            ids.add(id);
            cargarHijosUnidadId(id,ids,sessio);
            return ids;
        }catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(sessio);
        }
    }


    /**
     * Lista las unidades Administrativas por Ambito(0:Autonomico, 1:Insular)(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<UnidadAdministrativa> listarUnidadesAdministrativasPorAmbito(Long ambito) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from UnidadAdministrativa as ua where"+
                                              " ua.validacion = :validacion and " +
                                              " ua.espacioTerrit.nivel= :ambito and" +
                                              " ua.padre is null");
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("ambito", ambito);
            query.setCacheable(true);

            List<UnidadAdministrativa> unidades = query.list();
            for(UnidadAdministrativa unidad: unidades){
                Hibernate.initialize(unidad.getFotop());
            }
            return unidades;

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista las unidades Administrativas por Ambito(0:Autonomico, 1:Insular)(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<UnidadAdministrativa> buscarUnidadesAdministrativasPorAmbito(Long ambito, final String busqueda, final String idioma) {
    	Session session = getSession();
    	try {

            /*Query query = session.createQuery("from UnidadAdministrativa as ua, ua.traducciones as trad where index(trad) = :idioma and "+
                                              " ua.validacion = :validacion and " +
                                              " ua.espacioTerrit.nivel= :ambito and " +
                                              " upper(trad.nombre) like :busqueda");*/

            Query query = session.createQuery(" from UnidadAdministrativa as ua, " +
                                              "      ua.traducciones as trad " +
                                              "where index(trad) = :idioma " +
                                              "  and upper(trad.nombre) like :busqueda " +
                                              "  and ua.espacioTerrit.nivel = :ambito " +     
                                              "  and ua.validacion = :validacion");
    		query.setInteger("validacion", Validacion.PUBLICA);
    		query.setString("idioma", idioma);
        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
    		query.setLong("ambito", ambito);
    		query.setCacheable(true);
    		
    		List<UnidadAdministrativa> unidades = query.list();
    		for(UnidadAdministrativa unidad: unidades){
    			Hibernate.initialize(unidad.getFotop());
    		}
    		return unidades;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }


    /**
     * Lista las unidades Administrativas de un determiando espacio territorial (PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<UnidadAdministrativa> listarUAEspacioTerritorial(Long idEspacio) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from UnidadAdministrativa as ua " +
                                             "where ua.validacion = :validacion " +
                                             "  and ua.espacioTerrit.id= :idEspacio");
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("idEspacio", idEspacio);
            query.setCacheable(true);

            List<UnidadAdministrativa> unidades = query.list();
            return unidades;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }






    /**
     * Busca todos los {@link UnidadAdministrativa} cuyo nombre contenga el String de entrada(PORMAD)
     *
     * @param busqueda
     * @param idioma
     * @return lista de {@link UnidadAdministrativa}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<UnidadAdministrativa> buscar(final String busqueda, final String idioma){
		List<UnidadAdministrativa> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("from UnidadAdministrativa as uni, uni.traducciones as trad " +
                        "                         where index(trad) = :idioma " +
                        "                           and upper(trad.nombre) like :busqueda" +
                        "                           and uni.validacion = :validacion" );
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	query.setInteger("validacion", Validacion.PUBLICA);
	        	resultado = (List<UnidadAdministrativa>)query.list();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			resultado = Collections.emptyList();
		}

		return resultado;
	}


    /**
     * Obtiene la unidad asociada al espacio territorial y del tipo indicado(PORMAD).
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(Long idEspacio, String tipo) {
        Session session = getSession();
        try {
            Query query = session.createQuery("");
            if("AJU".equals(tipo)) {
                query = session.createQuery("from UnidadAdministrativa as ua " +
                                           "where ua.espacioTerrit.id = :idEspacio" +
                                           "  and ua.validacion = :validacion");
                query.setParameter("idEspacio", idEspacio);
                query.setParameter("validacion", Validacion.PUBLICA);
                query.setCacheable(true);

            } else if("CONSELL".equals(tipo)) {
                query = session.createQuery("select ua " +
                                            "  from UnidadAdministrativa as ua, " +
                                            "       EspacioTerritorial as espte" +
                                            " where ua.espacioTerrit = espte.padre" +
                                            "   and espte.id = :idEspacio " +
                                            "   and ua.codigoEstandar = :codEstUA" +
                                            "   and ua.validacion = :validacion");
                query.setParameter("idEspacio", idEspacio);
                query.setParameter("codEstUA", "CONSELL");
                query.setParameter("validacion", Validacion.PUBLICA);
                query.setCacheable(true);
            } else if( "GOV".equals(tipo)) {
                query = session.createQuery("select ua " +
                                            "  from UnidadAdministrativa as ua, " +
                                            "       EspacioTerritorial as espte " +
                                            " where ua.espacioTerrit = espte.padre.padre " +
                                            "   and espte.id = :idEspacio " +
                                            "   and ua.codigoEstandar = :codEstUA" +
                                            "   and ua.validacion = :validacion");
                query.setParameter("idEspacio", idEspacio);
                query.setParameter("codEstUA", "GOV");
                query.setParameter("validacion", Validacion.PUBLICA);
                query.setCacheable(true);
            }
            UnidadAdministrativa ua =  (UnidadAdministrativa)query.uniqueResult();
            if (ua != null) {
                Hibernate.initialize(ua.getUnidadesMaterias());
            }
            return  ua;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la unidad asociada al espacio territorial y del tipo indicado(PORMAD).
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     *
     * @param idEspacio id del espacio territorial del que queremos obtener una determinada UA
     * @para tipo tipo de búsqueda que queremos generar (AJU - AYUNTAMIENTO, CONSELL -  CONSELLS INSULARS, GOV - GOVERN)
     * @para UAOpcionales. En el caso de tipo = CONSELL o GOV, puede haber múltipes UA para un determinado espacio territorial,
     *                     en esta lista estan las opciones a filtrar.
     * @return la {@link UnidadAdministrativa} deseada
     */
    @SuppressWarnings("unchecked")
    public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(Long idEspacio, String tipo, List UAOpcionales) {
        Session session = getSession();
        try {
            Query query = session.createQuery("");
            if("AJU".equals(tipo)) {

                query = session.createQuery(" from UnidadAdministrativa as ua" +
                                            " where ua.espacioTerrit.id = :idEspacio" +
                                            "   and ua.padre is null" +
                                            "   and ua.validacion = :validacion");

                query.setParameter("idEspacio", idEspacio);
                query.setParameter("validacion", Validacion.PUBLICA);
                query.setCacheable(true);

            } else if("CONSELL".equals(tipo)) {
 
                query = session.createQuery(" select ua " +
                                            "   from UnidadAdministrativa as ua," +
                                            "        EspacioTerritorial as espte" +
                                            "  where espte.id = :idEspacio" +
                                            "    and espte.padre.id = ua.espacioTerrit.id" +
                                            "    and ua.padre is null" +
                                            "    and ua.validacion = :validacion");

                query.setParameter("idEspacio", idEspacio);
                query.setParameter("validacion", Validacion.PUBLICA);
                query.setCacheable(true);
            } else if("GOV".equals(tipo)) {

                query = session.createQuery(" select ua " +
                                            "   from UnidadAdministrativa as ua, " +
                                            "        EspacioTerritorial as espte " +
                                            "  where espte.id = :idEspacio" +
                                            "    and espte.padre.padre.id = ua.espacioTerrit.id" +
                                            "    and ua.padre is null" +
                                            "    and ua.validacion = :validacion");
                query.setParameter("idEspacio", idEspacio);
                query.setParameter("validacion", Validacion.PUBLICA);
                query.setCacheable(true);
            }

            if ("AJU".equals(tipo)) {
                UnidadAdministrativa ua =  (UnidadAdministrativa) query.uniqueResult();
                if (ua != null) {
                    Hibernate.initialize(ua.getUnidadesMaterias());
                }
                return  ua;
            } else {
                List unidadesAdministrativas = query.list();
                for (Iterator i = unidadesAdministrativas.iterator(); i.hasNext();) {
                    UnidadAdministrativa ua = (UnidadAdministrativa) i.next();
                    if (ua.getCodigoEstandar() != null && ua.getCodigoEstandar().trim().length() != 0) {
                        for(Iterator j = UAOpcionales.iterator(); j.hasNext();) {
                            String codEstUA = (String) j.next();
                            if (ua.getCodigoEstandar().equals(codEstUA)) {
                                Hibernate.initialize(ua.getUnidadesMaterias());
                                return ua;
                            }
                        }
                    }
                }
            }
            return null;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
	 * Devuelve un {@link Map} que contiene 2 {@link List}, uno de {@link ProcedimientoLocal}
	 * y el otro de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 *
	 * Los key del {@link Map} son los String "PROCEDIMIENTOS" y "FICHAS"  (PORMAD)
     *
     * @param idUA
	 * @param idMat
	 * @param ceSec
	 * @return
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public Map<String,List<?>> listarProcFichSecMat(final Long idUA, final Long idMat, final String ceSec ){
		Map<String,List<?>> resultado = new HashMap<String,List<?>>();
		if(idUA!=null && idMat!=null){
			Session session = getSession();
	        try {
	        	List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();
	        	List<Ficha> fichas = new ArrayList<Ficha>();

	        	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	        	if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcFichSecMat(session, unidadRemota.getAdministracionRemota(), idMat, ceSec, procs, fichas);
				}else{
					listarProcFichSecMatRecur(session, unidad, idMat, ceSec, procs, fichas);
				}
	        	resultado.put("PROCEDIMIENTOS", procs);
				resultado.put("FICHAS", fichas);
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			resultado.put("PROCEDIMIENTOS", Collections.emptyList());
			resultado.put("FICHAS", Collections.emptyList());
		}

		return resultado;
	}

	/**
	 * Devuelve un {@link Map} que contiene 2 {@link List}, uno de {@link ProcedimientoLocal}
	 * y el otro de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
	 *
	 * Los key del {@link Map} son los String "PROCEDIMIENTOS" y "FICHAS" (PORMAD)
     *
     * @param idUA
	 * @param idHV
	 * @param ceSec
	 * @return
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public Map<String,List<?>> listarProcFichSecHV(final Long idUA, final Long idHV, final String ceSec ){
		Map<String,List<?>> resultado = new HashMap<String,List<?>>();
		if(idUA!=null && idHV!=null){
			Session session = getSession();
	        try {
	        	List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();
	        	List<Ficha> fichas = new ArrayList<Ficha>();

	        	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	        	if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcFichSecHV(session, unidadRemota.getAdministracionRemota(), idHV, ceSec, procs, fichas);
				}else{
					listarProcFichSecHVRecur(session, unidad, idHV, ceSec, procs, fichas);
				}

	        	resultado.put("PROCEDIMIENTOS", procs);
				resultado.put("FICHAS", fichas);
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			resultado.put("PROCEDIMIENTOS", Collections.emptyList());
			resultado.put("FICHAS", Collections.emptyList());
		}

		return resultado;
	}

	/**
	 * Busca todos los {@link ProcedimientoLocal} que contengan la palabra de la busqueda que
	 * esten relacionados con una {@link UnidadAdministrativa} (PORMAD)
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> buscarProcedimientosUA(final Long idUA, final String busqueda, final String idioma,final Date dataInici, final Date dataFi ){
		List<ProcedimientoLocal> resultado;
		if(idUA!=null && busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	resultado =  new ArrayList<ProcedimientoLocal>();

	        	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	        	if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					buscarProcedimientosUA(session, busqueda, idioma, dataInici,dataFi,unidadRemota.getAdministracionRemota(), resultado );
				}else{
					buscarProcedimientosUARecur(session, busqueda, idioma,dataInici,dataFi, unidad, resultado);
				}

	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			resultado = Collections.emptyList();
		}

		return resultado;
	}

	
	/**
	 * Metodo que obtiene un bean con el filtro para la indexacion
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true" 
	 */
	public ModelFilterObject obtenerFilterObject(UnidadAdministrativa ua) {
		ModelFilterObject filter = new ModelFilterObject();
		
			
    		//de momento, familia y microsites a null
    		
    		filter.setFamilia_id(null);    	
    		filter.setMicrosite_id(null);
    		filter.setSeccion_id(null);
    		
    		
    		Iterator iterlang = ua.getLangs().iterator();
    		while (iterlang.hasNext()){
    			
    			String idioma = (String) iterlang.next();
				String txids=Catalogo.KEY_SEPARADOR;
	    		String txtexto=" ";//espacio en blanco, que es para tokenizar
	    		Iterator iter;
	    		
				TraModelFilterObject trafilter = new TraModelFilterObject();
				
				//titulo		
				trafilter.setMaintitle( ((TraduccionUA)ua.getTraduccion(idioma)).getNombre() );
	        	

	    		txids=Catalogo.KEY_SEPARADOR;
	    		txtexto=" ";
	    		txids += ua.getId()+Catalogo.KEY_SEPARADOR;
	    		txtexto += ((TraduccionUA)ua.getTraduccion(idioma)).getNombre()+" ";
	    		txtexto += ((TraduccionUA)ua.getTraduccion(idioma)).getAbreviatura()+" ";
	    		txtexto += ((TraduccionUA)ua.getTraduccion(idioma)).getPresentacion()+" ";

	    		
	    		//OBTENER DIRECCIONES
	    		if (ua.getEdificios()!=null) {
		    		iter = ua.getEdificios().iterator();
		    		while (iter.hasNext()) {
		    			Edificio edificio = (Edificio)iter.next();
		    			txtexto += edificio.getDireccion()+" ";
		    			txtexto += edificio.getTelefono()+" ";
		    		}
	    		}
	    		
	    		filter.setUo_id( (txids.length()==1) ? null: txids);
	    		trafilter.setUo_text( (txtexto.length()==1) ? null: txtexto);

	    		//OBTENER LAS MATERIAS (ademas de las materias se ponen los textos de los HECHOS VITALES)
	    		if (ua.getUnidadesMaterias()!=null) {
		    		txids=Catalogo.KEY_SEPARADOR;
		    		txtexto=" ";
					iter = ua.getUnidadesMaterias().iterator();
					while (iter.hasNext()) {
						UnidadMateria uamat = (UnidadMateria)iter.next();
						
			        	
						txids+=uamat.getMateria().getId()+Catalogo.KEY_SEPARADOR; 
			        	if (uamat.getMateria().getTraduccion(idioma)!=null) {
			        		txtexto+=((TraduccionMateria)uamat.getMateria().getTraduccion(idioma)).getNombre() + " ";
			        		txtexto+=((TraduccionMateria)uamat.getMateria().getTraduccion(idioma)).getDescripcion() + " ";
			        		txtexto+=((TraduccionMateria)uamat.getMateria().getTraduccion(idioma)).getPalabrasclave() + " ";
			        	}
			        		
					}
		    		
		    		filter.setMateria_id( (txids.length()==1) ? null: txids);
		    		trafilter.setMateria_text( (txtexto.length()==1) ? null: txtexto);
	    		}
	    		
	    		filter.addTraduccion(idioma, trafilter);
 
			}
        	


		return filter;
	}  	
	
	
    /**
     * Añade la ua al indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaUA(UnidadAdministrativa ua,  ModelFilterObject filter)  {
		
    	try {

	    	if (filter==null) filter = obtenerFilterObject(ua);
			
			for (Iterator iterator = ua.getLangs().iterator(); iterator.hasNext();) {
				String idi = (String) iterator.next();
				IndexObject io= new IndexObject();
				
	            io.setId(Catalogo.SRVC_UO + "." + ua.getId());
	            io.setClasificacion(Catalogo.SRVC_UO);
	            
	            io.setMicro( filter.getMicrosite_id() ); 
	            io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );
				
				io.setCaducidad("");
				io.setPublicacion(""); 
				io.setDescripcion("");

				io.addTextLine(ua.getResponsable());
				
	            TraduccionUA trad=((TraduccionUA)ua.getTraduccion(idi));
	            if (trad!=null) {
	            
	            	io.setUrl("/govern/organigrama/area.do?coduo="+ua.getId()+"&lang="+idi);
	            	io.setTituloserviciomain(filter.getTraduccion(idi).getMaintitle());
	            	
	            	
	            	if (trad.getNombre()!=null) {
	            		io.setTitulo(trad.getNombre());
	            		//para dar mas peso al titulo
	            		for (int i=0;i<5;i++) {
		            		io.addTextLine(trad.getNombre());
	            		}
	            	}
	            	
	            	if (trad.getPresentacion()!=null)  {
	            		if (trad.getPresentacion().length()>200) io.setDescripcion(trad.getPresentacion().substring(0,199)+"...");
	                	else io.setDescripcion(trad.getPresentacion());
	            	}
	            	
					io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
					io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
					io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());
						    
	            	
	            }


	            
	            if (io.getText().length()>0)
	            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);
			}
		}
		catch (Exception ex) {
			log.warn("[indexInsertaUA:" + ua.getId() + "] No se ha podido indexar UA. " + ex.getMessage());
		}
        
	}
	
	 /**
     * Elimina la ua en el indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public void indexBorraUA(Long id)  {
		
		
		try {

			List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			for (int i = 0; i < langs.size(); i++) {
				DelegateUtil.getIndexerDelegate().borrarObjeto(Catalogo.SRVC_UO + "." + id, ""+langs.get(i));
			}

		}
		catch (DelegateException ex) {
			log.warn("[indexBorraFicha:" + id + "] No se ha podido borrar del indice la ficha. " + ex.getMessage());
		}
		
        
	}
	
	
	
    /* ================================================ */
    /* ==  MÉTODOS PRIVADOS  ========================== */
    /* ================================================ */

    private int numUnidadesRaiz(Session session) throws HibernateException {
        Query query = session.getNamedQuery("unidades.root.count");
        return ((Integer) query.list().get(0)).intValue();
    }

    /**
     * Construye el query de búsqueda segun los parámetros
     */
    private String populateQuery(Map parametros, Map traduccion, List params) {
        String aux = "";

        for (Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
            String key = (String) iter1.next();
            Object value = parametros.get(key);
            if (value != null) {
                if (value instanceof String) {
                    String sValue = (String) value;
                    if (sValue.length() > 0) {
                        if (aux.length() > 0) aux = aux + " and ";
                        if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
                            sValue = sValue.substring(1, (sValue.length() - 1));
                            aux = aux + " upper( unidad." + key + " ) like ? ";
                            params.add(sValue);
                        } else {
                            aux = aux + " upper( unidad." + key + " ) like ? ";
                            params.add("%"+sValue+"%");
                        }
                    }
                } else if (value instanceof Date) {
                    if (aux.length() > 0) aux = aux + " and ";
                    aux = aux + "unidad." + key + " = '" + value + "'";
                } else {
                    if (aux.length() > 0) aux = aux + " and ";
                    aux = aux + "unidad." + key + " = " + value;
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
    * Carga las unidades hijas de una unidad de manera recursiva
    * @throws HibernateException
    */
    private UnidadAdministrativa cargarHijosUnidad(Long id, Session session) throws HibernateException {
           UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, id);
           Hibernate.initialize(unidad.getHijos());
           List hijos = unidad.getHijos();
           for(int i=0; i<hijos.size(); i++){
               UnidadAdministrativa uni = (UnidadAdministrativa)hijos.get(i);
               cargarHijosUnidad(uni.getId(), session);
           }
           return unidad;
    }

    /**
    * Carga todos los ids de las unidades hijas de una unidad de manera recursiva
    * @throws HibernateException
    */
    private void cargarHijosUnidadId(Long id, List ids, Session session) throws HibernateException {
        UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, id);
        List hijos = unidad.getHijos();

        for(int i=0; i<hijos.size(); i++){

            UnidadAdministrativa uni = (UnidadAdministrativa)hijos.get(i);
            ids.add(uni.getId());
            cargarHijosUnidadId(uni.getId(), ids, session);
        }

    }

    /**
     * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
     * un listado de {@link ProcedimientoLocal} asociados a las unidades una {@link Materia} y
     * rellenado un listado de {@link Ficha} asociadas a las unidades, a una {@link Materia} y al
     * CodigoEstandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param unidad sobre la que recorrer su arbol de hijos
     * @param idMat,  Materia relacionada con las fichas y procedimientos
     * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @param fichas, Lista de {@link Ficha} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	private void listarProcFichSecMatRecur(Session session ,final UnidadAdministrativa unidad, final Long idMat, final String ceSec, final List<ProcedimientoLocal> procs, final List<Ficha> fichas) throws HibernateException{
    	//Navegacion por el arbol de hijos
    	for (UnidadAdministrativa hijo : unidad.getHijos()) {
    		Hibernate.initialize(hijo.getHijos());
    		if(hijo.getHijos()!=null){
    			listarProcFichSecMatRecur(session, hijo, idMat, ceSec, procs, fichas);
    		}
		}

    	//Query que retorna los procedimientos relacionados
    	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoLocal as proc, Materia as mat " +
			"where proc.unidadAdministrativa.id=:idUA AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
			"AND mat.id=:idMat AND mat in elements(proc.materias) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idMat", idMat);
		query.setParameter("validacion", Validacion.PUBLICA);
        query.setParameter("fecha", DateUtils.today());
		procs.addAll(query.list());

		//Query que retorna las fichas relacionadas
		query = session.createQuery("Select DISTINCT f From FichaUA as fua, fua.ficha as f, Materia as mat " +
				"where fua.unidadAdministrativa.id=:idUA " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				"AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
				"AND mat.id=:idMat AND mat in elements(f.materias) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idMat", idMat);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
        query.setParameter("fecha", DateUtils.today());
		fichas.addAll(query.list());
    }

    /**
     * Rellena un listado de {@link ProcedimientoLocal} y {@link Ficha} relacionados con
     * una {@link AdministracionRemota}, una {@link Materia} y el codigo
     * estandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param admin AdministracionRemota sobre la que buscar
     * @param idMat,  Materia relacionada con las fichas y procedimientos
     * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @param fichas, Lista de {@link Ficha} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    private void listarProcFichSecMat(Session session ,final AdministracionRemota admin, final Long idMat, final String ceSec, final List<ProcedimientoLocal> procs, final List<Ficha> fichas) throws HibernateException{

    	//Query que retorna los procedimientos relacionados
    	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoRemoto as proc, Materia as mat " +
    			"where proc.administracionRemota.id=:idAdmin AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
    			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
    	"AND mat.id=:idMat AND mat in elements(proc.materias) ");
    	query.setLong("idAdmin",  admin.getId());
    	query.setLong("idMat", idMat);
    	query.setParameter("validacion", Validacion.PUBLICA);
    	query.setParameter("fecha", DateUtils.today());
    	procs.addAll(query.list());

    	//Query que retorna las fichas relacionadas
    	query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, Materia as mat " +
    			"where f.administracionRemota.id=:idAdmin AND fua.ficha = f " +
    			"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
    			"AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    	"AND mat.id=:idMat AND mat in elements(f.materias) ");
    	query.setLong("idAdmin",  admin.getId());
    	query.setLong("idMat", idMat);
    	query.setString("ceSec", ceSec);
    	query.setParameter("validacion", Validacion.PUBLICA);
    	query.setParameter("fecha", DateUtils.today());
    	fichas.addAll(query.list());
    }


    /**
     * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
     * un listado de {@link ProcedimientoLocal} asociados a las unidades un {@link HechoVital} y
     * rellenado un listado de {@link Ficha} asociadas a las unidades, a un {@link HechoVital} y al
     * CodigoEstandar de una {@link Seccion} (PORMAD)
     *
     * @param session
     * @param unidad sobre la que recorrer su arbol de hijos
     * @param idHV,  HechoVital relacionada con las fichas y procedimientos
     * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @param fichas, Lista de {@link Ficha} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	private void listarProcFichSecHVRecur(Session session ,final UnidadAdministrativa unidad, final Long idHV, final String ceSec, final List<ProcedimientoLocal> procs, final List<Ficha> fichas) throws HibernateException{
    	//Navegacion por el arbol de hijos
    	for (UnidadAdministrativa hijo : unidad.getHijos()) {
    		Hibernate.initialize(hijo.getHijos());
    		if(hijo.getHijos()!=null){
    			listarProcFichSecHVRecur(session, hijo, idHV, ceSec, procs, fichas);
    		}
		}

    	//Query que retorna los procedimientos relacionados
    	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoLocal as proc, HechoVitalProcedimiento as hvproc, hvproc.hechoVital as hecho " +
			"where proc.unidadAdministrativa.id=:idUA AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
			"AND hecho.id=:idHV AND hvproc in elements(proc.hechosVitalesProcedimientos) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idHV", idHV);
		query.setParameter("validacion", Validacion.PUBLICA);
        query.setParameter("fecha", DateUtils.today());
		procs.addAll(query.list());

		//Query que retorna las fichas relacionadas
		query = session.createQuery("Select DISTINCT f From FichaUA as fua, fua.ficha as f, HechoVital as hecho " +
				"where fua.unidadAdministrativa.id=:idUA " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
				"AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
				"AND hecho.id=:idHV AND hecho in elements(f.hechosVitales) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idHV", idHV);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
        query.setParameter("fecha", DateUtils.today());
		fichas.addAll(query.list());
    }

    /**
     * Rellena un listado de {@link ProcedimientoLocal} y {@link Ficha} relacionados con
     * una {@link AdministracionRemota}, un {@link HechoVital} y el codigo
     * estandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param admin AdministracionRemota
     * @param idHV,  HechoVital relacionada con las fichas y procedimientos
     * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @param fichas, Lista de {@link Ficha} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    private void listarProcFichSecHV(Session session ,final AdministracionRemota admin, final Long idHV, final String ceSec, final List<ProcedimientoLocal> procs, final List<Ficha> fichas) throws HibernateException{

    	//Query que retorna los procedimientos relacionados
    	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoRemoto as proc, HechoVitalProcedimiento as hvproc, hvproc.hechoVital as hecho " +
    			"where proc.administracionRemota.id=:idAdmin AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
    			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
    	"AND hecho.id=:idHV AND hvproc in elements(proc.hechosVitalesProcedimientos) ");
    	query.setLong("idAdmin",  admin.getId());
    	query.setLong("idHV", idHV);
    	query.setParameter("validacion", Validacion.PUBLICA);
    	query.setParameter("fecha", DateUtils.today());
    	procs.addAll(query.list());

    	//Query que retorna las fichas relacionadas
    	query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, HechoVital as hecho " +
    			"where f.administracionRemota.id=:idAdmin AND fua.ficha=f " +
    			"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
    			"AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    	"AND hecho.id=:idHV AND hecho in elements(f.hechosVitales) ");
    	query.setLong("idAdmin",  admin.getId());
    	query.setLong("idHV", idHV);
    	query.setString("ceSec", ceSec);
    	query.setParameter("validacion", Validacion.PUBLICA);
    	query.setParameter("fecha", DateUtils.today());
    	fichas.addAll(query.list());
    }


    /**
     * Funcion recursiva que Busca {@link ProcedimientoLocal} relacionados con la busqueda que esten en
     * el arbol de una {@link UnidadAdministrativa} (PORMAD)
     *
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	private void buscarProcedimientosUARecur(Session session, final String busqueda, final String idioma ,final Date dataInici, final Date dataFi,final UnidadAdministrativa unidad, final List<ProcedimientoLocal> procs) throws HibernateException{
        System.out.println("1ejb unidad administrativa fecha Inicio: "+dataInici);
    	System.out.println("1ejb unidad administrativa fecha Fin: "+dataFi);
    	//Navegacion por el arbol de hijos
    	for (UnidadAdministrativa hijo : unidad.getHijos()) {
    		Hibernate.initialize(hijo.getHijos());
    		if(hijo.getHijos()!=null){
    			buscarProcedimientosUARecur(session, busqueda, idioma,dataInici, dataFi, hijo, procs);
    		}
		}

    	String sQuery="";
    	//Query que retorna los procedimientos relacionados
    	if(dataInici!=null && dataFi!=null ){
    		sQuery="Select DISTINCT proc From ProcedimientoLocal as proc, proc.traducciones as trad " +
			"where proc.unidadAdministrativa.id=:idUA AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND (proc.fechaPublicacion between :dataInici and :dataFi) AND proc.validacion = :validacion " +
			"AND index(trad) = :idioma and upper(trad.nombre) like :busqueda ";
    	}
    	else{
    		sQuery="Select DISTINCT proc From ProcedimientoLocal as proc, proc.traducciones as trad " +
			"where proc.unidadAdministrativa.id=:idUA AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
			"AND index(trad) = :idioma and upper(trad.nombre) like :busqueda ";
    	}

    	Query query = session.createQuery(sQuery);
		query.setLong("idUA",  unidad.getId());
		query.setParameter("validacion", Validacion.PUBLICA);
        query.setParameter("fecha", DateUtils.today());
        query.setString("idioma", idioma);
        if(dataInici!=null && dataFi!=null ){
            query.setParameter("dataInici", null);
            query.setParameter("dataFi", null);
        }

    	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
		procs.addAll(query.list());
    }

    /**
     * Busca {@link ProcedimientoLocal} relacionados con la busqueda que esten en una
     * {@link AdministracionRemota} (PORMAD)
     *
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    private void buscarProcedimientosUA(Session session , final String busqueda, final String idioma,final Date dataInici,final Date dataFi, final AdministracionRemota admin, final List<ProcedimientoLocal> procs) throws HibernateException{
        System.out.println("2ejb unidad administrativa fecha Inicio: "+dataInici);
    	System.out.println("2ejb unidad administrativa fecha Fin: "+dataFi);
    	String sQuery="";
    	if(dataInici!=null && dataFi!=null ){
    		sQuery="Select DISTINCT proc From ProcedimientoRemoto as proc, proc.traducciones as trad " +
			"where proc.administracionRemota.id=:idAdmin AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND (proc.fechaPublicacion between :dataInici and :dataFi) AND proc.validacion = :validacion " +
			"AND index(trad) = :idioma and upper(trad.nombre) like :busqueda ";
    	}
    	else{
    		sQuery="Select DISTINCT proc From ProcedimientoRemoto as proc, proc.traducciones as trad " +
			"where proc.administracionRemota.id=:idAdmin AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
			"AND index(trad) = :idioma and upper(trad.nombre) like :busqueda ";
    	}


    		Query query = session.createQuery(sQuery);
        	query.setLong("idAdmin",  admin.getId());
        	query.setParameter("validacion", Validacion.PUBLICA);
        	query.setParameter("fecha", DateUtils.today());
        	query.setString("idioma", idioma);
        	if(dataInici!=null && dataFi!=null ){
                query.setParameter("dataInici", dataInici);
                query.setParameter("dataFi", dataFi);
        	}

        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
        	procs.addAll(query.list());
    }

	/**
	 * Busca todos los {@link FichaLocal} que contengan la palabra de la
	 * busqueda que esten relacionados con una {@link UnidadAdministrativa}
	 * (PORMAD)
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Set<Ficha> buscarFichasUA(final String busqueda,
			final String idioma, final Date dataInici, final Date dataFi) {
		Set<Ficha> resultado;
		if (busqueda != null && !"".equals(busqueda.trim())) {
			Session session = getSession();
			try {
				resultado = new HashSet<Ficha>();

				String sQuery = "";
				if (dataInici != null && dataFi != null) {
					sQuery = "Select DISTINCT fic From Ficha as fic, fic.traducciones as trad "
							+ "where ( fic.fechaCaducidad is null or fic.fechaCaducidad >= :fecha ) "
							+ "AND ( fic.fechaPublicacion is null or fic.fechaPublicacion < :fecha ) AND (fic.fechaPublicacion between :dataInici and :dataFi) AND fic.validacion = :validacion "
							+ "AND index(trad) = :idioma and upper(trad.titulo) like :busqueda ";
				} else {
					sQuery = "Select DISTINCT fic From Ficha as fic, fic.traducciones as trad "
							+ "where ( fic.fechaCaducidad is null or fic.fechaCaducidad >= :fecha ) "
							+ "AND ( fic.fechaPublicacion is null or fic.fechaPublicacion < :fecha ) AND fic.validacion = :validacion "
							+ "AND index(trad) = :idioma and upper(trad.titulo) like :busqueda ";
				}

				Query query = session.createQuery(sQuery);
				query.setParameter("validacion", Validacion.PUBLICA);
				query.setParameter("fecha", DateUtils.today());
				query.setString("idioma", idioma);
				if (dataInici != null && dataFi != null) {
					query.setParameter("dataInici", dataInici);
					query.setParameter("dataFi", dataFi);
				}

				query.setString("busqueda", "%" + busqueda.trim().toUpperCase()
						+ "%");
				resultado.addAll(query.list());

			} catch (HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		} else {
			resultado = Collections.emptySet();
		}

		return resultado;
	}

	/**
	 * MODIFICACIONS MARILEN PER ADAPTAR EL PORINF FA2
	 */

    /*********FICHASMATERIA*********/

	  /**
	   * Rellena un listado {@link Ficha} relacionados con
	   * una {@link AdministracionRemota}, una {@link Materia} y el codigo
	   * estandar de una {@link Seccion}  (PORINF)
	   *
	   * @param session
	   * @param admin AdministracionRemota sobre la que buscar
	   * @param idMat,  Materia relacionada con las fichas y procedimientos
	   * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	   * @param fichas, Lista de {@link Ficha} a rellenar
	   * @throws HibernateException
	   */
	  @SuppressWarnings("unchecked")
	  private void listarFichSecMat(Session session ,final AdministracionRemota admin, final Long idMat, final String ceSec, final List<Ficha> fichas,boolean caducados) throws HibernateException{
	
	      //Query que retorna las fichas relacionadas
	      Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, Materia as mat " +
	              "where f.administracionRemota.id=:idAdmin AND fua.ficha = f " +
	              "AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
	              (!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "")+
	      "AND mat.id=:idMat AND mat in elements(f.materias) ");
	      query.setLong("idAdmin",  admin.getId());
	      query.setLong("idMat", idMat);
	      query.setString("ceSec", ceSec);
	      query.setParameter("validacion", Validacion.PUBLICA);
	      if(!caducados){query.setParameter("fecha", DateUtils.today());}
	      fichas.addAll(query.list());
	  }
	
	
	
	
	/**
	  * Devuelve un {@link List} de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	  * una {@link Materia} y una {@link Seccion}.
	  *
	  * @param idUA
	  * @param idMat
	  * @param ceSec
	  * @return
	  *
	  * @ejb.interface-method
	  * @ejb.permission unchecked="true"
	  */
	 public List<Ficha> listarFichSecMat(final Long idUA, final Long idMat, final String ceSec,boolean caducados){
		  
	     //if (!userIsInfo() && caducados) throws SecurityException("Errror");
		  
		  List<Ficha> fichas = new ArrayList<Ficha>();
	     if(idUA!=null && idMat!=null){
	         Session session = getSession();
	         try {
	             UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	             if (unidad instanceof UnidadAdministrativaRemota) {
	                 UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
	                 listarFichSecMat(session, unidadRemota.getAdministracionRemota(), idMat, ceSec, fichas,caducados);
	             }else{
	                 listarFichSecMatRecur(session, unidad, idMat, ceSec, fichas,caducados);
	             }
	         } catch (HibernateException he) {
	             throw new EJBException(he);
	         } finally {
	             close(session);
	         }
	     }else{
	         return Collections.emptyList();
	     }
	
	     return fichas;
	 }
	
	
	/**
	   * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
	   * listado de {@link Ficha} asociadas a las unidades, a una {@link Materia} y al
	   * CodigoEstandar de una {@link Seccion}  (PORMAD)
	   *
	   * @param session
	   * @param unidad sobre la que recorrer su arbol de hijos
	   * @param idMat,  Materia relacionada con las fichas y procedimientos
	   * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
	   * @param fichas, Lista de {@link Ficha} a rellenar
	   * @throws HibernateException
	   */
	  @SuppressWarnings("unchecked")
	  private void listarFichSecMatRecur(Session session ,final UnidadAdministrativa unidad, final Long idMat, final String ceSec, final List<Ficha> fichas,boolean caducados) throws HibernateException{
	      //Navegacion por el arbol de hijos
	      for (UnidadAdministrativa hijo : unidad.getHijos()) {
	          Hibernate.initialize(hijo.getHijos());
	          if(hijo.getHijos()!=null){
	              listarFichSecMatRecur(session, hijo, idMat, ceSec, fichas,caducados);
	          }
	      }
	
	
	      //Query que retorna las fichas relacionadas
	      Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, fua.ficha as f, Materia as mat " +
	              "where fua.unidadAdministrativa.id=:idUA " +
	              "AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
	              (!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
	              "AND mat.id=:idMat AND mat in elements(f.materias) ");
	      query.setLong("idUA",  unidad.getId());
	      query.setLong("idMat", idMat);
	      query.setString("ceSec", ceSec);
	      query.setParameter("validacion", Validacion.PUBLICA);
	      if(!caducados){query.setParameter("fecha", DateUtils.today());}
	      fichas.addAll(query.list());
	  }


    /*********PROCEDIMIENTOSMATERIA*********/


	  /**
	 * Devuelve un {@link List} de {@link ProcedimientoLocal}
	 * Relacionados con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link Materia} y una {@link Seccion}.
     *
     * @param idUA
	 * @param idMat
	 * @return
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> listarProcMat(final Long idUA, final Long idMat, final String[] codMat, boolean include,boolean caducados){
        List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();
		if(idUA!=null && idMat!=null){
			Session session = getSession();
	        try {
	        	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	        	if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcMat(session, unidadRemota.getAdministracionRemota(), idMat, codMat, procs,include,caducados);
				}else{
					listarProcMatRecur(session, unidad, idMat, codMat, procs, include,caducados);
				}
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			return Collections.emptyList();
		}

		return procs;
	}

    /**
     * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
     * un listado de {@link ProcedimientoLocal} asociados a las unidades una {@link Materia} y al
     * CodigoEstandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param unidad sobre la que recorrer su arbol de hijos
     * @param idMat,  Materia relacionada con las fichas y procedimientos
     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	private void listarProcMatRecur(Session session ,final UnidadAdministrativa unidad, final Long idMat, final String[] codMat, final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException{
    	//Navegacion por el arbol de hijos
    	for (UnidadAdministrativa hijo : unidad.getHijos()) {
    		Hibernate.initialize(hijo.getHijos());
    		if(hijo.getHijos()!=null){
    			listarProcMatRecur(session, hijo, idMat, codMat, procs, include,caducados);
    		}
		}

    	//Query que retorna los procedimientos relacionados
    	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoLocal as proc, Materia as mat " +
			"where proc.unidadAdministrativa.id=:idUA " +
			(!caducados ? "AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " : "") +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
			"AND mat.id=:idMat AND mat in elements(proc.materias) "+
            " and " + (include?"exists":"not exists") + " (select m from proc.materias m where m.codigoEstandar in (:codigos)) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idMat", idMat);
		query.setParameter("validacion", Validacion.PUBLICA);
		query.setParameter("fecha", DateUtils.today());
        query.setParameterList("codigos", codMat);
		procs.addAll(query.list());		
    }
    

     /**
     * Rellena un listado de {@link ProcedimientoLocal} relacionados con
     * una {@link AdministracionRemota}, una {@link Materia} y el codigo
     * estandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param admin AdministracionRemota sobre la que buscar
     * @param idMat,  Materia relacionada con las fichas y procedimientos

     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    private void listarProcMat(Session session ,final AdministracionRemota admin, final Long idMat,final String[] codMat, final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException{

    	//Query que retorna los procedimientos relacionados
    	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoRemoto as proc, Materia as mat " +
    			"where proc.administracionRemota.id=:idAdmin " +
    			(!caducados ? "AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " : "") +
    			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
    	"AND mat.id=:idMat AND mat in elements(proc.materias) "+
        " and " + (include?"exists":"not exists") + " (select m from proc.materias m where m.codigoEstandar in (:codigos)) ");
    	query.setLong("idAdmin",  admin.getId());
    	query.setLong("idMat", idMat);
    	query.setParameter("validacion", Validacion.PUBLICA);
    	query.setParameter("fecha", DateUtils.today());
        query.setParameterList("codigos", codMat);
    	procs.addAll(query.list());
    }


   /****FICHASHECHOS VITALES***/


   /**
	 * Devuelve un {@link List}  de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * un {@link HechoVital} y una {@link Seccion}.
	 *
    * @param idUA
	 * @param idHV
	 * @param ceSec
	 * @return
    *
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
	 */
	public List<Ficha> listarFichSecHV(final Long idUA, final Long idHV, final String ceSec,boolean caducados){

		if(idUA!=null && idHV!=null){
			Session session = getSession();
	        try {

	        	List<Ficha> fichas = new ArrayList<Ficha>();

	        	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	        	if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarFichSecHV(session, unidadRemota.getAdministracionRemota(), idHV, ceSec, fichas,caducados);
				}else{
					listarFichSecHVRecur(session, unidad, idHV, ceSec, fichas,caducados);
				}

	            return fichas;
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			return Collections.emptyList();
		}

	}

	 /**
     * Rellena un listado {@link Ficha} relacionados con
     * una {@link AdministracionRemota}, un {@link HechoVital} y el codigo
     * estandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param admin AdministracionRemota
     * @param idHV,  HechoVital relacionada con las fichas y procedimientos
     * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
     * @param fichas, Lista de {@link Ficha} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    private void listarFichSecHV(Session session ,final AdministracionRemota admin, final Long idHV, final String ceSec, final List<Ficha> fichas,boolean caducados) throws HibernateException{


    	//Query que retorna las fichas relacionadas
    	Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, FichaRemota as f, HechoVital as hecho " +
    			"where f.administracionRemota.id=:idAdmin AND fua.ficha=f " +
    			"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
    			(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
    	"AND hecho.id=:idHV AND hecho in elements(f.hechosVitales) ");
    	query.setLong("idAdmin",  admin.getId());
    	query.setLong("idHV", idHV);
    	query.setString("ceSec", ceSec);
    	query.setParameter("validacion", Validacion.PUBLICA);
        if(!caducados){query.setParameter("fecha", DateUtils.today());}
    	fichas.addAll(query.list());
    }


    /**
     * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
     * un listado de {@link Ficha} asociadas a las unidades, a un {@link HechoVital} y al
     * CodigoEstandar de una {@link Seccion} (PORMAD)
     *
     * @param session
     * @param unidad sobre la que recorrer su arbol de hijos
     * @param idHV,  HechoVital relacionada con las fichas y procedimientos
     * @param ceSec, CodigoEstandar de una {@link Seccion} relacionada con las Fichas
     * @param fichas, Lista de {@link Ficha} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	private void listarFichSecHVRecur(Session session ,final UnidadAdministrativa unidad, final Long idHV, final String ceSec,  final List<Ficha> fichas,boolean caducados) throws HibernateException{
    	//Navegacion por el arbol de hijos
    	for (UnidadAdministrativa hijo : unidad.getHijos()) {
    		Hibernate.initialize(hijo.getHijos());
    		if(hijo.getHijos()!=null){
    			listarFichSecHVRecur(session, hijo, idHV, ceSec, fichas,caducados);
    		}
		}



		//Query que retorna las fichas relacionadas
		Query query = session.createQuery("Select DISTINCT f From FichaUA as fua, fua.ficha as f, HechoVital as hecho " +
				"where fua.unidadAdministrativa.id=:idUA " +
				"AND fua.seccion.codigoEstandard=:ceSec AND f.validacion = :validacion " +
    			(!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
				"AND hecho.id=:idHV AND hecho in elements(f.hechosVitales) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idHV", idHV);
		query.setString("ceSec", ceSec);
		query.setParameter("validacion", Validacion.PUBLICA);
		if(!caducados){query.setParameter("fecha", DateUtils.today());}
		fichas.addAll(query.list());
    }


   /***PROCEDIMIENTOSHECHOSVITALES ***/

    /**
	 * Devuelve un {@link List} de {@link Ficha}, Relacionadas con el arbol de relaciones de una {@link UnidadAdministrativa},
	 * una {@link HechoVital} y una {@link Seccion}.
	 *

     *
     * @param idUA
	 * @param idHV
	 * @return
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoLocal> listarProcHV(final Long idUA, final Long idHV, final String[] codMat, boolean include,boolean caducados){

		if(idUA!=null && idHV!=null){
			Session session = getSession();
	        try {
	        	List<ProcedimientoLocal> procs =  new ArrayList<ProcedimientoLocal>();


	        	UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, idUA);
	        	if (unidad instanceof UnidadAdministrativaRemota) {
					UnidadAdministrativaRemota unidadRemota = (UnidadAdministrativaRemota) unidad;
					listarProcHV(session, unidadRemota.getAdministracionRemota(), idHV, codMat, procs, include,caducados);
				}else{
					listarProcHVRecur(session, unidad, idHV, codMat, procs, include,caducados);
				}

	        	return procs;
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		}else{
			return Collections.emptyList();
		}
		
	}
	
	 /**
     * Rellena un listado de {@link ProcedimientoLocal} relacionados con
     * una {@link AdministracionRemota}, un {@link HechoVital} y el codigo
     * estandar de una {@link Seccion}  (PORMAD)
     *
     * @param session
     * @param admin AdministracionRemota
     * @param idHV,  HechoVital relacionada con las fichas y procedimientos
     * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    private void listarProcHV(Session session ,final AdministracionRemota admin, final Long idHV, final String[] codMat, final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException{

        //Query que retorna los procedimientos relacionados
        Query query = session.createQuery("Select DISTINCT proc From ProcedimientoRemoto as proc, HechoVitalProcedimiento as hvproc, hvproc.hechoVital as hecho " +
                "where proc.administracionRemota.id=:idAdmin " +
    			  (!caducados ? "AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " : "") +
                "AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
        "AND hecho.id=:idHV AND hvproc in elements(proc.hechosVitalesProcedimientos) " +
        " and " + (include?"exists":"not exists") + " (select m from proc.materias m where m.codigoEstandar in (:codigos)) ");
        query.setLong("idAdmin",  admin.getId());
        query.setLong("idHV", idHV);
        query.setParameter("validacion", Validacion.PUBLICA);
        query.setParameter("fecha", DateUtils.today());
        query.setParameterList("codigos", codMat);
        procs.addAll(query.list());

    }


   /**
   * Funcion recursiva que recorre todo el arbol de una {@link UnidadAdministrativa} rellenando
   * un listado de {@link ProcedimientoLocal} asociados a las unidades un {@link HechoVital} a un {@link HechoVital} y al
   * CodigoEstandar de una {@link Seccion} (PORMAD)
   *
   * @param session
   * @param unidad sobre la que recorrer su arbol de hijos
   * @param idHV,  HechoVital relacionada con las fichas y procedimientos
   * @param procs, Lista de {@link ProcedimientoLocal} a rellenar
   * @throws HibernateException
   */
  @SuppressWarnings("unchecked")
	private void listarProcHVRecur(Session session ,final UnidadAdministrativa unidad, final Long idHV, final String[] codMat, final List<ProcedimientoLocal> procs, boolean include,boolean caducados) throws HibernateException{
  	//Navegacion por el arbol de hijos
	  	for (UnidadAdministrativa hijo : unidad.getHijos()) {
	  		Hibernate.initialize(hijo.getHijos());
	  		if(hijo.getHijos()!=null){
	  			listarProcHVRecur(session, hijo, idHV,codMat ,procs, include,caducados);
	  		}
	  	}

	  	//Query que retorna los procedimientos relacionados
	  	Query query = session.createQuery("Select DISTINCT proc From ProcedimientoLocal as proc, HechoVitalProcedimiento as hvproc, hvproc.hechoVital as hecho " +
			"where proc.unidadAdministrativa.id=:idUA " +
			(!caducados ? "AND ( proc.fechaCaducidad is null or proc.fechaCaducidad >= :fecha ) " : "") +
			"AND ( proc.fechaPublicacion is null or proc.fechaPublicacion < :fecha ) AND proc.validacion = :validacion " +
			"AND hecho.id=:idHV AND hvproc in elements(proc.hechosVitalesProcedimientos) " +
          " and " + (include?"exists":"not exists") + " (select m from proc.materias m where m.codigoEstandar in (:codigos)) ");
		query.setLong("idUA",  unidad.getId());
		query.setLong("idHV", idHV);
		query.setParameter("validacion", Validacion.PUBLICA);
		query.setParameter("fecha", DateUtils.today());
		query.setParameterList("codigos", codMat);
		procs.addAll(query.list());
  	}

    
	/**
	 * Compone la miga de pan de la unidad administrativa
	 * @param idua
	 * @param idioma 
     * @return String 
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */

	
	public StringBuffer getUaMolla(Long idua, String _idioma) {
	
		StringBuffer tmp = new StringBuffer(" ");
		try {
				UnidadAdministrativaDelegate uadel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate();
			    UnidadAdministrativa uniadm=uadel.obtenerUnidadAdministrativa(idua);
				while ((uniadm!=null) && (uniadm.getId().longValue()!=1)) {
					StringBuffer ua_sbuf = new StringBuffer( ((TraduccionUA)uniadm.getTraduccion(_idioma)).getNombre() );
					Cadenas.initAllTab(ua_sbuf); //primera letra en mayusculas
					String ua_texto=Cadenas.initTab(ua_sbuf.toString()); // articulos a minusculas
					String ua_url="/govern/organigrama/area.do?lang=" + _idioma + "&coduo="+uniadm.getId();
					tmp.insert(0, "<li>&gt; <a href=\"" + ua_url + "\">" + ua_texto + "</a>" + "</li>" );
					uniadm = uniadm.getPadre();
				}
				if (tmp.length()>3) tmp.delete(tmp.length()-3, tmp.length());
				else tmp.append("&nbsp;");
		} catch (DelegateException e) {
			tmp = new StringBuffer("&nbsp;");			
		} 
		return tmp;
	}		

	  
    
}
