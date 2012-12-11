package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.util.FichaUAFichaIds;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Secciones.
 * 
 * @ejb.bean name="sac/persistence/SeccionFacade" jndi-name="org.ibit.rol.sac.persistence.SeccionFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class SeccionFacadeEJB extends HibernateEJB {

    private static String idioma_per_defecte = "ca";

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea una Secci�n.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long crearSeccion(Seccion seccion, Long padre_id) {
        Session session = getSession();
        try {
            if (padre_id == null) {
                seccion.setOrden(numSeccionesRaiz(session));
                session.save(seccion);
            } else {
                Seccion padre = (Seccion) session.load(Seccion.class, padre_id);
                padre.addHijo(seccion);
            }

            session.flush();
            return seccion.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Actualiza una Secci�n.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void actualizarSeccion(Seccion seccion, Long padre_id) {
        Session session = getSession();
        try {
            session.update(seccion);
            Long padreOld_id = (seccion.getPadre() != null ? seccion.getPadre().getId() : null);
            Seccion padreOld = null;
            if (padreOld_id != null)
                padreOld = this.obtenerSeccion(padreOld_id);

            /* Comprova si el pare antic �s diferent del nou. Tots dos valors poden ser null. */
            if ( (padre_id == null && padreOld_id != null) || (padre_id != null && padreOld_id == null) || (padre_id != null && padreOld_id != null && !padre_id.equals(padreOld_id))) {
                if (padre_id == null) { //Quitamos de jerarquia i metemos en raiz.
                    if (padreOld != null)
                        padreOld.removeHijo(seccion);
                    seccion.setOrden(numSeccionesRaiz(session));
                    seccion.setPadre(null);
                } else { // Pasamos a otra jerarquia
                    Seccion padreNew = this.obtenerSeccion(padre_id);
                    if (padreOld != null)
                        padreOld.removeHijo(seccion);
                    seccion.setPadre(padreNew);
                    int orden = 0;
                    if (padreNew.getHijos() != null)
                        orden = padreNew.getHijos().size();
                    seccion.setOrden(orden);
                    actualizarListaRaiz(session);
                }
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Actualiza los ordenes de las fichas de una secci�n seg�n el orden de los campos del form
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenFichasSeccion(Long id, Enumeration params, Map valores) {

        Session session = getSession();
        try {
            Seccion seccion = obtenerSeccion(id);
            int valor_orden = 0;
            Set fichas = seccion.getFichasUA();
            Set fichas_ordenadas = new HashSet();
            while (params.hasMoreElements()) {
                String paramName = (String) params.nextElement();
                if (paramName.startsWith("orden_fic")) {
                    Long id1 = new Long(paramName.substring(9));
                    String[] parametros = (String[]) valores.get(paramName);
                    valor_orden = Integer.parseInt(parametros[0]);

                    Iterator itfic = fichas.iterator();
                    FichaUA fic = null;
                    while (itfic.hasNext()) {
                        fic = (FichaUA) itfic.next();
                        if (fic.getId().longValue() == id1.longValue()) {
                            fic.setOrdenseccion(valor_orden);
                            fichas_ordenadas.add(fic);
                        }
                    }
                }
            }
            seccion.setFichasUA(fichas_ordenadas);
            session.update(seccion);
            session.flush();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Abre huecos en los ordenes de las fichas de una secci�n reordeno las fichas de 5 en 5 para dejar huecos para
     * moverlas
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenFichasSeccionHuecos(Long id) {

        Session session = getSession();
        try {
            Seccion seccion = obtenerSeccion(id);

            // Abro huecos q permitira luego intercalar fichas
            Set fichas = seccion.getFichasUA();
            Set fichas_ordenadas = new HashSet();

            int contador = 5;

            Iterator itfic = fichas.iterator();
            FichaUA fic = null;
            while (itfic.hasNext()) {
                fic = (FichaUA) itfic.next();
                fic.setOrdenseccion(contador);
                contador += 5;
                fichas_ordenadas.add(fic);
            }
            seccion.setFichasUA(fichas_ordenadas);
            session.update(seccion);
            session.flush();

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Incrementa el orden de una seccion.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void subirOrden(Long idSeccion) {
        Session session = getSession();
        try {
            Seccion sec1 = (Seccion) session.load(Seccion.class, idSeccion);
            int orden = sec1.getOrden();
            if (orden > 0) {

                List hermanos;
                if (sec1.getPadre() != null) {
                    hermanos = sec1.getPadre().getHijos();
                } else {
                    hermanos = session.getNamedQuery("secciones.root").list();
                }

                Seccion sec2 = (Seccion) hermanos.get(orden - 1);

                sec2.setOrden(orden);
                hermanos.set(orden, sec2);

                sec1.setOrden(orden - 1);
                hermanos.set(orden - 1, sec1);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
        
    /**
     * Obtiene los ids de FichaUA y Ficha dada la UA y la seccion.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaUAFichaIds> obtenerFichaUAFichaIds(long idUA, long idSeccion) {
        Session session = getSession();
        try {
            String select = "select new org.ibit.rol.sac.persistence.util.FichaUAFichaIds(fua.id, fua.ficha.id) " +
            		"from FichaUA fua where fua.seccion.id = :idSeccion and fua.unidadAdministrativa.id = :idUA";
            Query query = session.createQuery(select);
            query.setLong("idSeccion", idSeccion);
            query.setLong("idUA", idUA);
            List<FichaUAFichaIds> ids = (List<FichaUAFichaIds>) query.list();
            return ids;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una secci�n determinada.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Seccion obtenerSeccion(Long id) {
        Session session = getSession();
        try {
            Seccion seccion = (Seccion) session.load(Seccion.class, id);
            session.refresh(seccion);
            Hibernate.initialize(seccion.getHijos());
            Hibernate.initialize(seccion.getPadre());
            Hibernate.initialize(seccion.getFichasUA());
            return seccion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una secci�n determinada.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Seccion obtenerSeccion(String codigo) {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Seccion.class);
            criteri.add(Expression.eq("codigoEstandard", codigo));
            List result = criteri.list();
            if (result.isEmpty()) {
                return null;
            }
            return (Seccion) result.get(0);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una secci�n determinada segun el nombre.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Seccion obtenerSeccionPorNombre(String nombre) {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("secciones.byname");
            query.setParameter("nombre", nombre);
            query.setMaxResults(1);
            query.setCacheable(true);
            List result = query.list();
            if (result.isEmpty()) {
                return null;
            }
            Seccion seccion = (Seccion) result.get(0);
            // Hibernate.initialize(seccion.getFichasUA());
            return seccion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de secciones raiz (nuevo backoffice).
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public ResultadoBusqueda listarSeccionesRaiz(int pagina, int resultats, String idioma) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTMSeccionesRaiz(idioma));
    }
    
    /**
     * Lista de secciones raiz.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarSeccionesRaiz() {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("secciones.root");
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de secciones raíz (menú Administración) 
     */
    private List listarTMSeccionesRaiz(String idioma) {
    	Session session = getSession();
    	
    	try {
    		Query query = session.createQuery("select sec.id, sec.orden, trad.nombre " +
    														"from Seccion as sec, sec.traducciones as trad " +
    														"where index(trad) = :idioma and sec.padre is null " +
    														"order by sec.orden asc");
    		
    		query.setParameter("idioma", idioma);
    		return query.list();    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	    	
    }
    
    /**
     * Lista todas las secciones.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarSecciones() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Seccion.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de secciones raiz filtrado por rol de usuario.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarSeccionesRaizPerfil() {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("secciones.root");
            query.setCacheable(true);
            List result = query.list();
            List secciones = new ArrayList();
            for (int i = 0; i < result.size(); i++) {
                Seccion seccion = (Seccion) result.get(i);
                if (userIs(seccion.getPerfil())) {
                    secciones.add(seccion);
                }
            }
            return secciones;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de la raiz hasta la secci�n indicada por el id.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarAntecesoresSeccion(Long id) {
        Session session = getSession();
        try {
            List result = new ArrayList();
            Seccion seccion = (Seccion) session.load(Seccion.class, id);

            result.add(seccion);
            while (seccion.getPadre() != null) {
                result.add(0, seccion.getPadre());
                seccion = seccion.getPadre();
            }

            return result;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de los hijos de una secci�n determinada.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarHijosSeccion(Long id) {
        Session session = getSession();
        try {
            Seccion seccion = (Seccion) session.load(Seccion.class, id);
            Hibernate.initialize(seccion.getHijos());

            return seccion.getHijos();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borra una secci�n determinada.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarSeccion(Long id) {
        Session session = getSession();
        try {
            Seccion seccion = (Seccion) session.load(Seccion.class, id);
            Set fichasUA = seccion.getFichasUA();
            for (Iterator iter = fichasUA.iterator(); iter.hasNext();) {
                FichaUA ficha = (FichaUA) iter.next();
                UnidadAdministrativa ua = ficha.getUnidadAdministrativa();
                Ficha fic = ficha.getFicha();
                if (ua != null) {
                    ua.removeFichaUA(ficha);
                }
                if (fic != null) {
                    fic.removeFichaUA(ficha);
                }
                // La seccion borrara las fichaUA en cascada.
                // seccion.removeFichaUA(ficha);
            }
            if (seccion.getPadre() != null) {
                seccion.getPadre().removeHijo(seccion);
                session.delete(seccion);
            } else {
                session.delete(seccion);
                actualizarListaRaiz(session);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista las secciones padre de una Unidad Administrativa. TODO Fer la query cacheable.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarSeccionesPadreUA(Long id_unidad) {
        Session session = getSession();
        try {
            List secciones = session
                    .find("select distinct seccion from Seccion as seccion, seccion.fichasUA as fichas where fichas.unidadAdministrativa.id=? and seccion.padre is null order by seccion.orden",
                            id_unidad, Hibernate.LONG);
            return secciones;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * A partir de un String con el codigo estandar de una Seccion recojo la {@link Seccion} correspondiente
     * 
     * @param codigoEstandar
     * @return {@link Seccion}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Seccion obtenerSeccionCE(final String codigosEstandar) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Seccion as sec where sec.codigoEstandard=:codigo");
            query.setString("codigo", codigosEstandar);
            return (Seccion) query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Asigna a una sección un nuevo orden y reordena los elementos afectados.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */	
    public void reordenar( Long id, Integer ordenNuevo, Integer ordenAnterior ) {
        Session session = getSession();
        
        try {
        	
        	Criteria criteria = session.createCriteria(Seccion.class);
        	criteria.addOrder(Order.asc("orden"));
        	List<Seccion> listaSecciones = castList(Seccion.class, criteria.list());
        	
        	// Modificar sólo los elementos entre la posición del elemento que cambia 
        	// de orden y su nueva posición 
        	int ordenMayor = ordenNuevo > ordenAnterior ? ordenNuevo : ordenAnterior;
        	int ordenMenor = ordenMayor == ordenNuevo ? ordenAnterior : ordenNuevo;
        	
        	// Si el nuevo orden es mayor que el anterior, desplazar los elementos 
        	// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
        	int incremento = ordenNuevo > ordenAnterior ? -1 : 1;        			
        	
        	// Usar un "for" en lugar de un "while" acotado porque los números de orden 
        	// podrían no ser consecutivos o incluso estar duplicados.
        	for (Seccion seccion: listaSecciones ) {        		    
        		
        		int orden = seccion.getOrden();
        		
        		if (orden >= ordenMenor && orden <= ordenMayor) {
        			
        			if ( id.equals(seccion.getId() ) ) {
        				seccion.setOrden( ordenNuevo );
        			} else {
        				seccion.setOrden( orden + incremento );
        			}
        			
        			session.saveOrUpdate(seccion);
        		}
        		
        		// No es necesario procesar el resto de registros a partir del último cambio.
        		if (orden > ordenMayor) break; 
        	}
        	
        	session.flush();
        	
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }    	
    }    
    
    private void actualizarListaRaiz(Session session) throws HibernateException {
        session.flush();
        Query query = session.getNamedQuery("secciones.root");
        List lista = query.list();
        for (int i = 0; i < lista.size(); i++) {
            Seccion sec = (Seccion) lista.get(i);
            sec.setOrden(i);
        }
    }

    private int numSeccionesRaiz(Session session) throws HibernateException {
        Query query = session.getNamedQuery("secciones.root.count");
        return ((Integer) query.list().get(0)).intValue();
    }
}
