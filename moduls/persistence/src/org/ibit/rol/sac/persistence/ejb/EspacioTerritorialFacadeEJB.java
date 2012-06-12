package org.ibit.rol.sac.persistence.ejb;

import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Archivo;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.hibernate.*;

/**
 * SessionBean para mantener y consultar Espacios Territoriales (PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/EspacioTerritorialFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.EspacioTerritorialFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class EspacioTerritorialFacadeEJB extends HibernateEJB{

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
     * Crea un EspacioTerritorial.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long crearEspacioTerritorial(EspacioTerritorial espacio, Long padre_id) {
        Session session = getSession();
        try {
            if (padre_id == null) {
            	espacio.setNivel(0);
                session.save(espacio);
            } else {
                EspacioTerritorial padre = (EspacioTerritorial) session.load(EspacioTerritorial.class, padre_id);
                padre.addHijo(espacio);
                
                int nivel = 0;
                do {
                	nivel++;
                	padre = padre.getPadre();
                } while (padre!=null);
				
                espacio.setNivel(nivel);
            }

            session.flush();
            return espacio.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Actualiza un EspacioTerritorial.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void actualizarEspacioTerritorial(EspacioTerritorial espacio, Long padre_id) {
        Session session = getSession();
        try {
            session.update(espacio);
            Long padreOld_id = (espacio.getPadre() != null ? espacio.getPadre().getId() : null);
            EspacioTerritorial padreOld = null;
            if (padreOld_id != null) padreOld = this.obtenerEspacioTerritorial(padreOld_id);

            if (padre_id != padreOld_id) {
                if (padre_id == null) { // Quitamos de jerarquia i metemos en raiz.
                    if (padreOld != null) padreOld.removeHijo(espacio);
                    espacio.setNivel(0);
                    espacio.setPadre(null);
                } else { // Passamos a otra jerarquia
                    EspacioTerritorial padreNew = this.obtenerEspacioTerritorial(padre_id);
                    if (padreOld != null) padreOld.removeHijo(espacio);
                    espacio.setPadre(padreNew);
                    
                    int nivel = 0;
                    do {
                    	nivel++;
                    	padreNew = padreNew.getPadre();
                    } while (padreNew != null);
                    ajustarNivel(nivel, espacio);
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
     * Borra un EspacioTerritorial determinado.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarEspacioTerritorial(Long id) {
        Session session = getSession();
        try {
            EspacioTerritorial espacio = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
            if (espacio.getPadre() != null) {
                espacio.getPadre().removeHijo(espacio);
                session.delete(espacio);
            } else {
                session.delete(espacio);
            }

            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista de EspacioTerritorialesraiz.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarEspacioTerritorialesRaiz() {
        Session session = getSession();
        try {
            Query query = session.getNamedQuery("espacio.root");
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Lista de la raiz hasta el EspacioTerritorial indicado por el id.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List<EspacioTerritorial> listarAntecesoresEspacioTerritorial(Long id) {
        Session session = getSession();
        try {
            List<EspacioTerritorial> result = new ArrayList<EspacioTerritorial>();
            EspacioTerritorial espacio = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);

            result.add(espacio);
            while (espacio.getPadre() != null) {
                result.add(0, espacio.getPadre());
                espacio = espacio.getPadre();
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
    public Collection<EspacioTerritorial> listarHijosEspacioTerritorial(Long id) {
        Session session = getSession();
        try {
            EspacioTerritorial espacio = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
            Hibernate.initialize(espacio.getHijos());

            return espacio.getHijos();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene un Espacio Territorial
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EspacioTerritorial obtenerEspacioTerritorial(Long id) {
        Session session = getSession();
        try {
            EspacioTerritorial espacioTerritorial = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
            session.refresh(espacioTerritorial);
            Hibernate.initialize(espacioTerritorial.getHijos());
            Hibernate.initialize(espacioTerritorial.getPadre());
            Hibernate.initialize(espacioTerritorial.getMapa());
            Hibernate.initialize(espacioTerritorial.getLogo());
            return espacioTerritorial;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


     /**
     * Lista los espacios Territoriales
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<EspacioTerritorial> listarEspaciosTerritoriales(){
        Session session = getSession();
        try {
            Query query = session.createQuery("from EspacioTerritorial");
            return query.list();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene el mapa de un Espacio Territorial
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerMapaEspacio(Long id) {
        Session session = getSession();
        try {
            EspacioTerritorial espacioTerritorial = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
            Hibernate.initialize(espacioTerritorial.getMapa());
            return espacioTerritorial.getMapa();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene el logo de un Espacio Territorial
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerLogoEspacio(Long id) {
        Session session = getSession();
        try {
            EspacioTerritorial espacioTerritorial = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
            Hibernate.initialize(espacioTerritorial.getLogo());
            return espacioTerritorial.getLogo();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }
   /**
    * Obtiene los Espacios Territoriales de un nivel
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
    public List<EspacioTerritorial> listarEspaciosTerritorialesNivel(Long nivel) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from EspacioTerritorial as esp where esp.nivel=:nivel");
            query.setParameter("nivel", nivel);
            query.setCacheable(true);

            return query.list();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }

    
    /**
    * Obtiene los municipios de una isla
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */
    public List<EspacioTerritorial> listarMunicipiosIsla(Long codIsla) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from EspacioTerritorial as esp where esp.padre.id=:codIsla");
            query.setParameter("codIsla", codIsla);
            query.setCacheable(true);

            return query.list();
        } catch (HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }
    
    /**
     * Borra un mapa de un EspacioTerritorial determinado.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarMapa(Long id) {
    	Session session = getSession();
    	try {
    		EspacioTerritorial espacioTerritorial = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
    		session.delete(espacioTerritorial.getMapa());
    		espacioTerritorial.setMapa(null);
    		session.flush();
    	} catch (HibernateException e) {
    		throw new EJBException(e);
    	} finally {
    		close(session);
    	}
    }
    
    /**
     * Borra un logo de un EspacioTerritorial determinado.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarLogo(Long id) {
    	Session session = getSession();
    	try {
    		EspacioTerritorial espacioTerritorial = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
    		session.delete(espacioTerritorial.getLogo());
    		espacioTerritorial.setLogo(null);
    		session.flush();
    	} catch (HibernateException e) {
    		throw new EJBException(e);
    	} finally {
    		close(session);
    	}
    }


    /**
     * Obtiene el padre {@link EspacioTerritorial} de un {@link EspacioTerritorial}.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EspacioTerritorial obtenerPadre(Long id) {
        Session session = getSession();
        try {
            EspacioTerritorial et = (EspacioTerritorial) session.load(EspacioTerritorial.class, id);
            return et.getPadre();            
        } catch(HibernateException e) {
            throw new EJBException(e);
        } finally {
            close(session);
        }
    }


    private void ajustarNivel(int nivel, final EspacioTerritorial espacio){
    	espacio.setNivel(nivel);
    	nivel++;
    	if(espacio.getHijos() != null) {
    		for (EspacioTerritorial hijo : espacio.getHijos()) {
				ajustarNivel(nivel, hijo);
			}
    	}
    }

}
