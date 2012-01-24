package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.webcaib.DocumentModel;
import org.ibit.rol.sac.model.webcaib.LinkModel;
import org.ibit.rol.sac.model.webcaib.TemaModel;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.Parametros;

/**
 * SessionBean para mantener y consultar Secciones.
 *
 * @ejb.bean
 *  name="sac/persistence/SeccionFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SeccionFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
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
     * Crea una Sección.
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
     * Actualiza una Sección.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void actualizarSeccion(Seccion seccion, Long padre_id) {
        Session session = getSession();
        try {
            session.update(seccion);
            Seccion padreOld = seccion.getPadre();
            Long padreOld_id = (padreOld != null ? padreOld.getId() : null);

            if (padre_id != padreOld_id) {
                if (padre_id == null) { // Quitamos de jerarquia i metemos en raiz.
                    padreOld.removeHijo(seccion);
                    seccion.setOrden(numSeccionesRaiz(session));
                    seccion.setPadre(null);
                } else { // Passamos a otra jerarquia
                    Seccion padreNew = (Seccion) session.load(Seccion.class, padre_id);
                    padreNew.addHijo(seccion);
                    if (padreOld == null) {
                        actualizarListaRaiz(session);
                    }
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
     * Actualiza los ordenes de las fichas de una sección
     * según el orden de los campos del form
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void actualizarOrdenFichasSeccion(Long id, Enumeration params, Map valores) {
    	
    	Session session = getSession();
        try {
        	Seccion seccion = obtenerSeccion(id);
        	int valor_orden=0;
        	Set fichas= seccion.getFichasUA();
        	Set fichas_ordenadas=new HashSet();
            while(params.hasMoreElements()) {
            	String paramName = (String)params.nextElement();
            	if (paramName.startsWith("orden_fic")) {
            		Long id1=new Long(paramName.substring(9));
            		String[] parametros=(String[])valores.get(paramName);
            		valor_orden= Integer.parseInt(parametros[0]);
            		
            		Iterator itfic=fichas.iterator();
            		FichaUA fic=null;
            		while (itfic.hasNext()) {
            			fic=(FichaUA)itfic.next();
            			if (fic.getId().longValue()==id1.longValue()) {
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
     * Abre huecos en los ordenes de las fichas de una sección
     * reordeno las fichas de 5 en 5 para dejar huecos para moverlas
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
        	Set fichas_ordenadas=new HashSet();
            
        	int contador=5;
        	
        	Iterator itfic=fichas.iterator();
    		FichaUA fic=null;
    		while (itfic.hasNext()) {
    			fic=(FichaUA)itfic.next();
    			fic.setOrdenseccion(contador);
    			contador+=5;
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
     * Obtiene una sección determinada.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Seccion obtenerSeccion(Long id) {
        Session session = getSession();
        try {
            Seccion seccion = (Seccion) session.load(Seccion.class, id);
            Hibernate.initialize(seccion.getFichasUA());
            return seccion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene una sección determinada.
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
     * Obtiene una sección determinada segun el nombre.
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
            //Hibernate.initialize(seccion.getFichasUA());
            return seccion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de secciones raiz.
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
     * Lista todas las secciones.
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
     * Lista de la raiz hasta la sección indicada por el id.
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
     * Lista de los hijos de una sección determinada.
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
     * Borra una sección determinada.
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
                if(fic != null) {
                    fic.removeFichaUA(ficha);
                }
                // La seccion borrara las fichaUA en cascada.
                //seccion.removeFichaUA(ficha);
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
     * Lista las secciones padre de una Unidad Administrativa.
     * TODO Fer la query cacheable.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarSeccionesPadreUA(Long id_unidad) {
        Session session = getSession();
        try {
            List secciones = session.find("select distinct seccion from Seccion as seccion, seccion.fichasUA as fichas where fichas.unidadAdministrativa.id=? and seccion.padre is null order by seccion.orden", id_unidad, Hibernate.LONG);
            return secciones;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * A partir de un String con el codigo estandar de una Seccion recojo
     * la {@link Seccion} correspondiente
     * 
     * @param codigoEstandar
     * @return {@link Seccion}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public Seccion obtenerSeccionCE(final String codigosEstandar){
		Session session = getSession();
        try {
        	Query query = session.createQuery("from Seccion as sec where sec.codigoEstandard=:codigo");
        	query.setString("codigo", codigosEstandar);
            return (Seccion)query.uniqueResult();
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
    
    
    //WEBCAIB//
    
    /**
     * WEBCAIB
     * Colección ordenada de padres ( de root a codi, incluido )
     * retorna TemaModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */ 
    public Collection getPares ( Long codi, String idioma ) {
    	
    	List<Seccion> listaSecciones = listarAntecesoresSeccion(codi);
    	
    	List<TemaModel> listaTemas = new Vector();
    	
    	for(Seccion sec : listaSecciones) {
    		TemaModel tema = new TemaModel();
    		tema.setCodi(sec.getId().intValue());
    		TraduccionSeccion traSec = (TraduccionSeccion)sec.getTraduccion(idioma);
    		if (traSec != null) {
    			tema.setNom(traSec.getNombre());
    		}
    		
    		if (tema.getNom() == null) {
    			traSec = (TraduccionSeccion)sec.getTraduccion(idioma_per_defecte);
    			tema.setNom(traSec.getNombre());
    		}
    		
    		listaTemas.add(tema);    		
    	}
    	return listaTemas;
    	
    }
    
    
    
    /**
     * WEBCAIB
     * Colección de hijos de una sección/tema.
     * retorna TemaModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */    
    public Collection getFills ( Long codi, String idioma ) {
    	
    	List<Seccion> listarHijosSeccion = listarHijosSeccion(codi);

    	List<TemaModel> listaTemas = new Vector();
    	
    	for(Seccion sec : listarHijosSeccion) {
    		TemaModel tema = new TemaModel();
    		tema.setCodi(sec.getId().intValue());
    		TraduccionSeccion traSec = (TraduccionSeccion)sec.getTraduccion(idioma);
    		if (traSec != null) {
    			tema.setNom(traSec.getNombre());
    		}
    		
    		if (tema.getNom() == null) {
    			traSec = (TraduccionSeccion)sec.getTraduccion(idioma_per_defecte);
    			tema.setNom(traSec.getNombre());
    		}
    		
    		listaTemas.add(tema);    		
    	}
    	return listaTemas;    	
    	
    }
    
    
    /**
     * WEBCAIB
     * Colección de temas principales (raices).
     * retorna TemaModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getArrels ( String idioma ) {
    	List<Seccion> listaSeccionesRaiz = listarSeccionesRaiz();

    	List<TemaModel> listaTemas = new Vector();
    	
    	for(Seccion sec : listaSeccionesRaiz) {
    		TemaModel tema = new TemaModel();
    		tema.setCodi(sec.getId().intValue());
    		TraduccionSeccion traSec = (TraduccionSeccion)sec.getTraduccion(idioma);
    		if (traSec != null) {
    			tema.setNom(traSec.getNombre());
    		}
    		
    		if (tema.getNom() == null) {
    			traSec = (TraduccionSeccion)sec.getTraduccion(idioma_per_defecte);
    			tema.setNom(traSec.getNombre());
    		}
    		
    		listaTemas.add(tema);    		
    	}
    	return listaTemas;     	
    }
    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la sección indicada.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinks ( Long codi, int inicial, int tamany, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    			"select fua " +
    			"from FichaUA as fua " +
    				"inner join fetch fua.ficha as f " +
    				"where fua.seccion.id = :seccion_id " +
    				"and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    				"and f.validacion = :validacion " +
    				"order by fua.orden asc, f.fechaActualizacion desc"); 
    		
    		query.setParameter("seccion_id", codi, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);
    		query.setCacheable(true);
    		query.setFirstResult(inicial );
    		query.setMaxResults(tamany);

    		Collection<FichaUA> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (FichaUA fichaUA : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			
    			Ficha ficha = fichaUA.getFicha();
    			String uo = fichaUA.getUnidadAdministrativa().getId().toString();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la sección indicada dentro del rango de orden.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByOrderRange ( Long codi, int ordre_i, int ordre_f, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    			"select fua " +
    			"from FichaUA as fua " +
    				"inner join fetch fua.ficha as f " +
    				"where fua.seccion.id = :seccion_id " +
    				"and fua.orden between :ordre_i and :ordre_f " +
    				"and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    				"and f.validacion = :validacion " +
    				"order by fua.orden asc, f.fechaActualizacion desc"); 
    		
    		query.setParameter("seccion_id", codi, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);
    		query.setParameter("ordre_i", ordre_i);
    		query.setParameter("ordre_f", ordre_f);
    		
    		query.setCacheable(true);
    		

    		Collection<FichaUA> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (FichaUA fichaUA : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			
    			Ficha ficha = fichaUA.getFicha();
    			String uo = fichaUA.getUnidadAdministrativa().getId().toString();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la sección indicada dentro del rango de orden y la UO.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByOrderRangeAndUO ( Long codi, int ordre_i, int ordre_f, String coduo, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    			"select fua " +
    			"from FichaUA as fua " +
    				"inner join fetch fua.ficha as f " +
    				"where fua.seccion.id = :seccion_id " +
    				"and fua.orden between :ordre_i and :ordre_f " +
    				"and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    				"and f.validacion = :validacion " +
    				"and fua.unidadAdministrativa = :coduo " +
    				"order by fua.orden asc, f.fechaActualizacion desc"); 
    		
    		query.setParameter("seccion_id", codi, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);
    		query.setParameter("ordre_i", ordre_i);
    		query.setParameter("ordre_f", ordre_f);
    		query.setParameter("coduo", coduo);
    		
    		
    		query.setCacheable(true);
    		

    		Collection<FichaUA> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (FichaUA fichaUA : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			
    			Ficha ficha = fichaUA.getFicha();
    			String uo = fichaUA.getUnidadAdministrativa().getId().toString();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }

    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la sección indicada y la UO.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByUO ( Long codi, Long coduo, int inicial, int tamany, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    			"select fua " +
    			"from FichaUA as fua " +
    				"inner join fetch fua.ficha as f " +
    				"where fua.seccion.id = :seccion_id " +
    				"and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    				"and ( f.fechaPublicacion is null or f.fechaPublicacion < :fecha ) " +
    				"and f.validacion = :validacion " +
    				"and ( fua.unidadAdministrativa = :coduo or 0 = :coduo ) " +
    				"order by fua.orden asc, f.fechaActualizacion desc"); 
    		
    		query.setParameter("seccion_id", codi, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);
    		query.setParameter("coduo", coduo, Hibernate.LONG);
    		query.setCacheable(true);
    		query.setFirstResult(inicial );
    		query.setMaxResults(tamany);

    		Collection<FichaUA> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (FichaUA fichaUA : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			
    			Ficha ficha = fichaUA.getFicha();
    			String uo = fichaUA.getUnidadAdministrativa().getId().toString();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la sección indicada y la UO.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByUORSS ( Long codi, Long coduo, int inicial, int tamany, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    			"select fua " +
    			"from FichaUA as fua " +
    				"inner join fetch fua.ficha as f " +
    				"where fua.seccion.id = :seccion_id " +
    				"and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    				"and ( f.fechaPublicacion is null or ( f.fechaPublicacion < :fecha) and f.fechaPublicacion > sysdate-30 ) " +
    				"and f.validacion = :validacion " +
    				"and ( fua.unidadAdministrativa = :coduo or 0 = :coduo ) " +
    				"order by fua.orden asc, f.fechaActualizacion desc"); 
    		
    		query.setParameter("seccion_id", codi, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);
    		query.setParameter("coduo", coduo, Hibernate.LONG);
    		query.setCacheable(true);
    		query.setFirstResult(inicial );
    		query.setMaxResults(tamany);

    		Collection<FichaUA> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (FichaUA fichaUA : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			
    			Ficha ficha = fichaUA.getFicha();
    			String uo = fichaUA.getUnidadAdministrativa().getId().toString();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }        
    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la sección indicada y la materia.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByMat ( Long codi, Long codmat, int inicial, int tamany, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {

    		String q = "select distinct ficha " +
			"from Ficha as ficha, ficha.materias as mat, ficha.fichasua as fua " +
			"where mat.id=:id_materia ";
    		
    		// Si es la sección actualidad UA , anyadimos las fichas de la portada de UA
    		if (codi== new Long(Parametros.ESDEVENIMENTS).intValue())
    			q += "and (fua.seccion=:id_seccion or fua.seccion= " + Parametros.PORTADAS_UA + ") ";
    		else
    			q += "and fua.seccion=:id_seccion ";
			
			q += "and ( ficha.fechaCaducidad is null or ficha.fechaCaducidad >= :fecha ) " +
			"and ( ficha.fechaPublicacion is null or ficha.fechaPublicacion < :fecha ) " +
			"and ficha.validacion = :validacion " +
			"order by ficha.fechaActualizacion desc";
    		
    		Query query = session.createQuery(q); 
    		
    		query.setParameter("id_seccion", codi, Hibernate.LONG);
    		query.setParameter("id_materia", codmat, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);    		
    		query.setCacheable(true);
    		query.setFirstResult(inicial );
    		query.setMaxResults(tamany);

    		Collection<Ficha> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (Ficha ficha : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			String uo = "1";
    			//Ficha ficha = fichaUA.getFicha();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }
    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que pertenecen a la materia indicada.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByMat (Long codmat, int inicial, int tamany, String idioma, int valida, Date fultenv ) {
    	
    	Session session = getSession();
    	try {

    		String q = "select distinct ficha " +
			"from Ficha as ficha, ficha.materias as mat " +
			"where mat.id=:id_materia " +    					
			"and ( ficha.fechaPublicacion is null or ficha.fechaPublicacion < :fecha ) " +
			"and ( ficha.fechaCaducidad is null or ficha.fechaCaducidad >= :fecha ) " +
			
			"and ( " +
			"trunc(coalesce(ficha.fechaPublicacion, :fecha_minima)) <= trunc(sysdate) " +
			"and trunc(coalesce(ficha.fechaActualizacion, :fecha_minima)) <= trunc(sysdate) " +
			
			"and (trunc(coalesce(ficha.fechaPublicacion, :fecha_minima)) >= :fultenv " +
			"  or trunc(coalesce(ficha.fechaActualizacion, :fecha_minima)) >= :fultenv) " +
			") " +
			
			"and ficha.validacion = :validacion " +
			"order by ficha.fechaActualizacion desc";
    		
    		Query query = session.createQuery(q); 
    		    		
    		query.setParameter("id_materia", codmat, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		Calendar cal = Calendar.getInstance();
    		cal.set(2000, 0, 1);
    		query.setParameter("fecha_minima", cal.getTime());
    		query.setParameter("validacion", valida);    	
    		query.setParameter("fultenv", fultenv);
    		query.setCacheable(true);
    		query.setFirstResult(inicial);
    		query.setMaxResults(tamany);

    		Collection<Ficha> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (Ficha ficha : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			String uo = "1";
    		    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    			
    			//Determinar si la ficha también está en la materia Parametros.MATERIA_AJUDES_SUBVENCIONS y en tal caso
    			//añadir al título " - Ajudes i subvencions".    			
    			Query query2 = session.createQuery("select ficha from Ficha as ficha, ficha.materias as mat " +
    				"where ficha.id = :id_ficha and mat.id = :id_materia");
    			
    			query2.setParameter("id_ficha", ficha.getId(), Hibernate.LONG);
    			query2.setParameter("id_materia", Parametros.MATERIA_AJUDES_SUBVENCIONS);
    			if (query2.uniqueResult() != null) {
    				linkModel.setNom( linkModel.getNom() + " - Ajudes i subvencions");
    			}
    			    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
    
    /**
     * WEBCAIB
     * Devuelve una colección de LinkModel que tienen foro y pertenecen a la sección indicada y la materia.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Collection getLinksByMatForo ( Long codi, Long codmat, int inicial, int tamany, String idioma, int valida ) {
    	
    	Session session = getSession();
    	try {

    		String q = "select distinct ficha " +
			"from Ficha as ficha, ficha.materias as mat, ficha.fichasua as fua " +
			"where mat.id=:id_materia " +    		
    		"and fua.seccion=:id_seccion " +			
			"and ( ficha.fechaCaducidad is null or ficha.fechaCaducidad >= :fecha ) " +
			"and ( ficha.fechaPublicacion is null or ficha.fechaPublicacion < :fecha ) " +
			"and ficha.validacion = :validacion " +
			"and ficha.urlForo is not null " +
			"order by ficha.fechaActualizacion desc";
    		
    		Query query = session.createQuery(q); 
    		
    		query.setParameter("id_seccion", codi, Hibernate.LONG);
    		query.setParameter("id_materia", codmat, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		query.setParameter("validacion", valida);    		
    		query.setCacheable(true);
    		query.setFirstResult(inicial );
    		query.setMaxResults(tamany);

    		Collection<Ficha> listaFichas = query.list();
    		
    		List<LinkModel> listaResultados = new Vector();

    		for (Ficha ficha : listaFichas) {

    			LinkModel linkModel = new LinkModel();
    			String uo = "1";
    			//Ficha ficha = fichaUA.getFicha();
    			
    			pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    				
    			listaResultados.add(linkModel);
    		}
    		
    		return listaResultados;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
    
    
    /**
     * WEBCAIB
     * Devuelve un LinkModel.
     * retorna LinkModel  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public LinkModel getLink ( Long codi, String idioma, int valida, String previ ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    			"select fua " +
    			"from FichaUA as fua " +
    				"inner join fetch fua.ficha as f " +
    				"where f.id = :id_ficha " + 
    				("n".equals(previ) ? " and f.validacion = :validacion " : "") + 
    				"and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
    				"and f.validacion = :validacion "); 
    		
    		query.setParameter("id_ficha", codi, Hibernate.LONG);
    		query.setParameter("fecha", DateUtils.HoyHora());
    		if ("n".equals(previ))
    			query.setParameter("validacion", valida);  		    		
    		
    		//FichaUA fichaUA = (FichaUA)query.uniqueResult();
    		FichaUA fichaUA = null;
    		List<FichaUA> listaFichas = query.list();
    		if (listaFichas.size() > 0 )
    			fichaUA = listaFichas.get(0);
			
    		if (fichaUA != null) {
				Ficha ficha = fichaUA.getFicha();
				String uo = fichaUA.getUnidadAdministrativa().getId().toString();
				
				LinkModel linkModel = new LinkModel();
				pasarFichaALinkModel(ficha, linkModel, idioma, uo);
    	    
				return linkModel;
				
    		} else {
    			
    			LinkModel lm = new LinkModel();
    			lm.setNom("Aquesta informació no està disponible");
    			
    			return lm;
    		}

    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }
    
    /**
     * WEBCAIB
     * Devuelve el número de fichas de la sección especificada.
     *  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Integer getNumLinks ( Long codi ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    				"select count(fua) " +
    				"from FichaUA as fua " +
    					"inner join fua.ficha as f " +
    					"where fua.seccion = :id_seccion " +
    					"and f.validacion = 1 "); 
    		
    		query.setParameter("id_seccion", codi, Hibernate.LONG);
	    		
    		return (Integer)query.uniqueResult();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
    

    /**
     * WEBCAIB
     * Devuelve el número de fichas de la sección especificada y unidad administrativa.
     *  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Integer getNumLinksByUO ( Long codi, String coduo ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    				"select count(fua) " +
    				"from FichaUA as fua " +    					
    					"where fua.seccion = :id_seccion and fua.unidadAdministrativa = :id_uo "); 
    		
    		query.setParameter("id_seccion", codi, Hibernate.LONG);
    		query.setParameter("id_uo", coduo);
	    		
    		return (Integer)query.uniqueResult();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }    
        
    
    /**
     * WEBCAIB
     * Devuelve el número de fichas de la sección especificada y materia.
     *  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public Integer getNumLinksByMat ( Long codi, String materia ) {
    	
    	Session session = getSession();
    	try {
    		
    		Query query = session.createQuery(
    				"select count(fua) " +
    				"from FichaUA as fua " +
    					"inner join fua.ficha as f " +
    					"inner join f.materias as mat " +
    					"where fua.seccion = :id_seccion and mat.id = :id_materia ");
    		
    		query.setParameter("id_seccion", codi, Hibernate.LONG);
    		query.setParameter("id_materia", materia);
	    		
    		return (Integer)query.uniqueResult();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }     
    

    /**
     * WEBCAIB
     * Devuelve la fecha de auditoria de la ficha especificada.
     *  
     * @ejb.interface-method
     * @ejb.permission unchecked="true"   
     */     
    public String getData ( Long codiFitxa ) {
    	
    	Session session = getSession();
    	try {
    		String data = "";
    		Query query = session.createQuery(
    				"select aud.fecha " +
    				"from Auditoria as aud, HistoricoFicha his " + 					
    					"where aud.historico = his.id " +
    					"and his.ficha = :id_ficha " +
    					"order by aud.fecha desc ");
    		
    		query.setParameter("id_ficha", codiFitxa, Hibernate.LONG);
    		
    		List<Date> listaResultados = (List)query.list();
    		
    		if (listaResultados.size() > 0 ) {
    			Date fecha= listaResultados.get(0);
    			data = DateUtils.formatearddMMyyyy(fecha);
    		}
    		
    			
    		return data;
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}    	
    }     
    
    

	/**
	 * WEBCAIB
	 * Pasa los datos de Ficha (hibernate) a LinkModel (WEBCAIB) obteniendo datos adicionales.
	 * 
	 * @param ficha Ficha.
	 * @param linkModel LinkModel.
	 * @param idioma Idioma preferido.
	 * @param idUO Id de unidad administrativa.
	 */
	private void pasarFichaALinkModel(Ficha ficha, LinkModel linkModel, String idioma, String idUO) {
		linkModel.setCodi(ficha.getId().intValue());
		    				
		if (ficha.getBaner() != null)
			linkModel.setBanner(ficha.getBaner().getId().intValue());
		if (ficha.getIcono() != null)
			linkModel.setIcono(ficha.getIcono().getId().intValue());
		if (ficha.getImagen() != null)
			linkModel.setImatge(ficha.getImagen().getId().intValue());
		linkModel.setUrlVideo(ficha.getUrlVideo());
		linkModel.setUrlForo(ficha.getUrlForo());

		linkModel.setUo( idUO );
		    				
		linkModel.setData(ficha.getFechaCaducidad());
		linkModel.setDataAct(ficha.getFechaActualizacion());
		    				
		TraduccionFicha traFicha = (TraduccionFicha)ficha.getTraduccion(idioma);
		if (traFicha != null) {
			linkModel.setNom( traFicha.getTitulo() );
			linkModel.setDescabr(traFicha.getDescAbr());
			linkModel.setDesc(traFicha.getDescripcion());
			linkModel.setUrl(traFicha.getUrl());
		}
		
		if ( linkModel.getNom() == null || linkModel.getDescabr() == null || linkModel.getDesc() == null || linkModel.getUrl() == null )
			traFicha = (TraduccionFicha)ficha.getTraduccion(idioma_per_defecte);
		
		if (linkModel.getNom() == null)     					
			linkModel.setNom( traFicha.getTitulo() );    				
		
		if (linkModel.getDescabr() == null)     					
			linkModel.setDescabr( traFicha.getDescAbr() );    				
		
		if (linkModel.getDesc() == null)     				
			linkModel.setDesc( traFicha.getDescripcion() );    		     		
		
		if (linkModel.getUrl() == null) 
			linkModel.setUrl( traFicha.getUrl() );
		     		
		List<Documento> listaDocumentos = ficha.getDocumentos();
		
		if (listaDocumentos != null) 
		for (Documento doc : listaDocumentos) {
			if (doc != null) {
				DocumentModel docModel = new DocumentModel();
				Archivo archivo = doc.getArchivo();
				if (archivo != null)
					docModel.setArxiu( archivo.getId().intValue() );
				TraduccionDocumento traDoc = (TraduccionDocumento)doc.getTraduccion(idioma);
				if (traDoc != null) {
					docModel.setTitol(traDoc.getTitulo());
					docModel.setDescripcio(traDoc.getDescripcion());
				}
				
				if (docModel.getTitol() == null || docModel.getDescripcio() == null) 
					traDoc = (TraduccionDocumento)doc.getTraduccion(idioma_per_defecte);
				
				if (docModel.getTitol() == null)
					docModel.setTitol(traDoc.getTitulo());
				
				if (docModel.getDescripcio() == null)
					docModel.setDescripcio(traDoc.getDescripcion());
			}
		}
	}    
    
}
