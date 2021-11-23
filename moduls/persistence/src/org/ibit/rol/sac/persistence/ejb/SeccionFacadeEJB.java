package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PerfilGestorDelegate;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

/**
 * SessionBean para mantener y consultar Secciones.
 * 
 * @ejb.bean name="sac/persistence/SeccionFacade" jndi-name="org.ibit.rol.sac.persistence.SeccionFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class SeccionFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 7533847125462776332L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
	public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea una Seccion.
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
     * Actualiza una Seccion.
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

            /* Comprova si el pare antic es diferent del nou. Tots dos valors poden ser null. */
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

                List<Seccion> hermanos;
                if (sec1.getPadre() != null) {
                    hermanos = castList(Seccion.class, sec1.getPadre().getHijos());
                } else {
                    hermanos = castList(Seccion.class, session.getNamedQuery("secciones.root").list());
                }

                Seccion sec2 = hermanos.get(orden - 1);

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
     * Obtiene una seccion determinada.
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
            Hibernate.initialize(seccion.getPerfilsGestor());
            return seccion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Obtiene una secci�n determinada sin fichasUA.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Seccion obtenerSeccionSinFichasUA(Long id) {
        Session session = getSession();
        try {
            Seccion seccion = (Seccion) session.load(Seccion.class, id);
            session.refresh(seccion);
            Hibernate.initialize(seccion.getHijos());
            Hibernate.initialize(seccion.getPadre());
            Hibernate.initialize(seccion.getPerfilsGestor());
            return seccion;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista de secciones filtrado por perfil gestor.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public List<Seccion> listarSeccionesRaizPerfilGestor(Long idSeccio) {
 		Session session = getSession();
 		List<Seccion> secciones = new ArrayList<Seccion>();
 		
 		try {
 		     if (userIsSystem()) {
 		    	// SI L'USUARI TÉ ROL SYSTEM ES MOSTREN TOTES LES SECCIONS
 		    	secciones  = (idSeccio!=null)? listarHijosSeccion(idSeccio) : listarSeccionesRaiz();
             }else {
            	// EN CAS CONTRARI CERCAM SECCIONS PARES D'ENTRE LES SECCIONS GESTIONABLES PER L'USUARI 
	 			Usuario usuario = getUsuario(session);
	 			if (usuario != null) {
	 				Query query = session
	 						.createQuery("select distinct sec from Seccion as sec inner join sec.perfilsGestor as peg inner join peg.usuaris usu "
	 						+"where usu.id = :usuari_id ");
	 				query.setParameter("usuari_id", usuario.getId(), Hibernate.LONG);
	 				query.setCacheable(false);
	 				List<Seccion> result = castList(Seccion.class, query.list());
	 				Set<Seccion> seccionesRaizPG = new HashSet<Seccion>();
	 				for (Seccion sec: result){	 				
	 					Boolean trobat = false;
	 					while (!trobat && sec!=null) { 
	 						if ((sec.getPadre()==null && idSeccio==null) || (sec.getPadre()!=null && sec.getPadre().getId().equals(idSeccio))) {
	 							seccionesRaizPG.add(sec);
	 							trobat = true;
	 						} else{
	 							sec=sec.getPadre();
	 						}
		 				}
	 				}
	 				secciones = new ArrayList<Seccion>(seccionesRaizPG);
	 			}
             }
 		} catch (HibernateException he) {
 			throw new EJBException(he);
 		} finally {
 			close(session);
 		}
 		return secciones;
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
    public List<Seccion> listarSeccionesRaiz() {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("secciones.root");
            query.setCacheable(true);
            return castList(Seccion.class, query.list());
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de secciones raíz (menú Administración) 
     */
    private List<?> listarTMSeccionesRaiz(String idioma) {
    	
    	corregirOrdenacion();
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
    public List<Seccion> listarSecciones() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Seccion.class);
            return castList(Seccion.class, criteri.list());
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
    public List<Seccion> listarSeccionesRaizPerfil() {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("secciones.root");
            query.setCacheable(true);
            List<Seccion> result = castList(Seccion.class, query.list());
            List<Seccion> secciones = new ArrayList<Seccion>();
            
            for (int i = 0; i < result.size(); i++) {
                Seccion seccion = result.get(i);
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
	 * Metodo que devuelve una cadena csv de ids de secciones a las que el usuario tienen acceso
	 * 	 
	 * @return
	 * @throws HibernateException
	 * @throws DelegateException
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public String obtenerCadenaFiltroSeccion()
			throws DelegateException {
		
		Session session = getSession();		
       
        try {
        	Usuario user = this.getUsuario(session);
    		StringBuilder consulta = new StringBuilder("select s.id from Seccion as s, pg in s.perfilsGestor, u in pg.usuaris ");
    		consulta.append("where u.id = :userId");
            Query query = session.createQuery(consulta.toString());
            query.setParameter("userId",user.getId(),Hibernate.LONG);
            List ids = query.list();
            return StringUtils.join(ids.toArray(), ",");
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }

	}	
	   

    /**
     * Lista de la raiz hasta la seccion indicada por el id.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List<Seccion> listarAntecesoresSeccion(Long id) {
        Session session = getSession();
        try {
            List<Seccion> result = new ArrayList<Seccion>();
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
     * Lista de los hijos de una seccion determinada.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Seccion> listarHijosSeccion(Long id) {
    	
        Session session = getSession();
        try {
        	Seccion seccion = (Seccion)session.load(Seccion.class, id);
        	Hibernate.initialize(seccion.getHijos());
            return seccion.getHijos();

        } catch (HibernateException he) {
            throw new EJBException(he);
		} finally {
            close(session);
        }
    }
    
    /**
     * Llista les seccions depenedents d'una secció donada de manera recursiva
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Seccion> listarDescendienteSeccion(Long id) {
        Session session = getSession();
        List<Seccion> descendientes = new ArrayList<Seccion>();
        try {
        	cargarDescendientesSeccion(id, descendientes, session);
        } 
        catch (HibernateException he) {	
            throw new EJBException(he);
		} 
        finally {
            close(session);
        }
        return descendientes;
    }

    private void cargarDescendientesSeccion(Long id, List<Seccion> descendientes, Session session) throws HibernateException  {
    	Seccion seccion = (Seccion)session.load(Seccion.class, id);
    	Hibernate.initialize(seccion.getHijos());
    	for (int i = 0; i < seccion.getHijos().size(); i++ ) {
    		Seccion secHija = seccion.getHijos().get(i);
    		if (secHija != null){
    			descendientes.add(secHija);
    			cargarDescendientesSeccion(secHija.getId(), descendientes, session);
    		}     		
    	}
    }
    /**
     * Borra una seccion determinada.
     * 
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarSeccion(Long id) {
        Session session = getSession();
        try {
            Seccion seccion = (Seccion) session.load(Seccion.class, id);
            Set<FichaUA> fichasUA = castSet(FichaUA.class, seccion.getFichasUA());
            for (Iterator<FichaUA> iter = fichasUA.iterator(); iter.hasNext();) {
                FichaUA ficha = iter.next();
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
     * A partir de un String con el codigo estandar de una Seccion recojo la {@link Seccion} correspondiente
     * 
     * @param codigosEstandar
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
        	criteria.add(  Expression.isNull("padre"));        	
        	criteria.addOrder(Order.asc("orden"));
        	List<Seccion> listaSecciones = castList(Seccion.class, criteria.list());

        	// Modificar sólo los elementos entre la posición del elemento que cambia 
        	// de orden y su nueva posición 
        	int ordenMayor = ordenNuevo > ordenAnterior ? ordenNuevo : ordenAnterior;
        	int ordenMenor = ordenMayor == ordenNuevo ? ordenAnterior : ordenNuevo;
        	
        	// Si el nuevo orden es mayor que el anterior, desplazar los elementos 
        	// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
        	int incremento = ordenNuevo > ordenAnterior ? -1 : 1;        			
        	
        	// Usar un "for" en lugar de un "while" acotado porque en entornos de prueba, 
        	// los números de orden podrían no ser consecutivos o incluso estar duplicados.
        	for (Seccion seccion: listaSecciones ) {        		    
        		
        		int orden = seccion.getOrden();
        		
        		if (orden >= ordenMenor && orden <= ordenMayor) {
        			
        			if ( id.equals(seccion.getId() ) ) {
        				seccion.setOrden( ordenNuevo );
        			} else {
        				seccion.setOrden( orden + incremento );
        			}
        		}
        		// Actualizar todo para asegurar que no hay duplicados ni huecos
        		session.saveOrUpdate(seccion);        		
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
        List<Seccion> lista = castList(Seccion.class, query.list());
        for (int i = 0; i < lista.size(); i++) {
            Seccion sec = lista.get(i);
            sec.setOrden(i);
        }
    }

    private int numSeccionesRaiz(Session session) throws HibernateException {
        Query query = session.getNamedQuery("secciones.root.count");
        return ((Integer) query.list().get(0)).intValue();
    }
    
    /**
     * Busca registros con el campo "orden" repetido y en caso afirmativo corrige
     * toda la tabla ordenando los registros convenientemente. 
     */
    private void corregirOrdenacion() {
    	
    	if ( !isOrdenesRepetidos() ) return;

    	Session session = getSession();
    	
    	try {
        	Criteria criteria = session.createCriteria(Seccion.class);
        	criteria.addOrder(Order.asc("orden"));
        	criteria.add(Expression.isNull("padre"));
        	List<Seccion> listaSecciones = castList(Seccion.class, criteria.list());

           	// Asegurar que la secuencia de orden incrementa de uno en uno        	
        	for (int i = 0; i < listaSecciones.size(); i++) {
        		Seccion seccion = listaSecciones.get(i);
        		seccion.setOrden(i);
        		
        		session.saveOrUpdate(seccion);        		
        	}
        	
        	session.flush();
        	
    	} catch (HibernateException he) {
    		throw new EJBException(he); 
    	} finally {
    		close(session);
    	}
    }
    
    /**
     * Comprueba si hay registros con el campo "orden" repetido
     * 
     * @return true Si existen registros con el campo orden repetido
     */
    private boolean isOrdenesRepetidos() {
    	Session session = getSession();
    	
    	try {
    		return !( session.createQuery("select seccion.orden from Seccion as seccion " +
    												 "where seccion.padre is null " +
    												 "group by seccion.orden " +
    												 "having count(seccion.orden) > 1").list().isEmpty() );
    	} catch (HibernateException e) {
    		throw new EJBException(e);    		
    	} finally {
    		close(session);
    	}    	
    } 
    
    /** Actualitza les seccions associades a un Perfil Gestor
    * @ejb.interface-method
    * @ejb.permission role-name="${role.system},${role.admin}"
    */	
    
    public Seccion actualizarPerfilesGestorSeccion(Seccion seccion, List<Long> idsNuevosPerfiles) {
		
		try {
		
			PerfilGestorDelegate perfilDelegate = DelegateUtil.getPerfilGestorDelegate();
	
			// Borrar los edificios actuales.
			for (PerfilGestor perfilActual : seccion.getPerfilsGestor())
				perfilDelegate.eliminarSeccionPerfilGestor(seccion.getId(), perfilActual.getId());
			
			// Insertar los nuevos.
			for ( Long id : idsNuevosPerfiles ) {
				if ( id != null ) {
					perfilDelegate.anyadirSeccionPerfilGestor(seccion.getId(), id);
				}
			}
			
			return seccion;
		
		} catch (DelegateException e) {
			
			throw new EJBException(e);
			
		}
		
	}
    
    
	
	 /**
	 * Consulta las secciones en funcion del filtro generico
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public ResultadoBusqueda consultaSecciones(FiltroGenerico filtro){
	
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		Long id = filtro.getId();
		String lang = filtro.getLang();
		Map <String,Object> parametros = new HashMap<String,Object>();
		
		//PARAMETROS
		String codigoUA = filtro.getValor(FiltroGenerico.FILTRO_SECCIONES_UA);
		String codigoEstandard = filtro.getValor(FiltroGenerico.FILTRO_SECCIONES_CODIGO_ESTANDAR);		
		
		
		StringBuilder select = new StringBuilder("SELECT s ");
		StringBuilder selectCount = new StringBuilder("SELECT count(s) ");
		StringBuilder from = new StringBuilder(" FROM Seccion as s, s.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder("");		
				
		try {
			
			if(id!=null && id>0) {
				where.append(" AND s.id = :id");
				parametros.put("id", id);					
			}
			
			
			if(!StringUtils.isEmpty(codigoUA)) {
				where.append(" AND s.id in (SELECT fua.seccion.id FROM  FichaUA AS fua WHERE fua.unidadAdministrativa.id = :codigoUA ) ");
				parametros.put("codigoUA", Long.parseLong(codigoUA));					
			}				
							
			if(!StringUtils.isEmpty(codigoEstandard)) {
				where.append(" AND s.codigoEstandard = :codigoEstandard ");
				
				parametros.put("codigoEstandard", codigoEstandard);					
			}	
	
			return ApiRestUtils.ejecutaConsultaGenerica_new(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
			
	
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	
	}
    
	
}
