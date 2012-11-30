package org.ibit.rol.sac.persistence.ejb;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.persistence.util.RemotoUtils;

import es.caib.rolsac.utils.ResultadoBusqueda;
 

/**
 * SessionBean para mantener y consultar materias.
 *
 * @ejb.bean
 *  name="sac/persistence/MateriaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.MateriaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class MateriaFacadeEJB extends HibernateEJB {

	private static String idioma_per_defecte ="ca";
	
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza una materia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarMateria(Materia materia) {
        Session session = getSession();
        try {
            session.saveOrUpdate(materia);
            session.flush();
            return materia.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
    * Lista todas las materias (nuevo backoffice).
    * 
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */    
    public ResultadoBusqueda listarMaterias(int pagina, int resultados) {
    	return listarTablaMaestraPaginada(pagina, resultados, listarMaterias());
    }
    
    /**
    * Lista todas las materias.
    * @ejb.interface-method
    * @ejb.permission unchecked="true"
    */        
    public List listarMaterias() {
    	Session session = getSession();
    	
    	try {
    		Criteria criteri = session.createCriteria(Materia.class);
    		
    		return criteri.addOrder(Order.asc("codigoEstandar")).list();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }

     /**
     * Lista todas las materias para el front.
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List<Materia> listarMateriasFront() {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Materia.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todas las materias para el front destacadas. (PORMAD) TODO: ver si hay que eliminarlo
     * @ejb.interface-method
     * @ejb.permission unchecked= "true"
     */
    public List<Materia> listarMateriasFrontDestacadas(String lang) {
        Session session = getSession();
        try {
            Query query = session.createQuery("from Materia as mat, mat.traducciones as trad where index(trad) = :idioma and mat.destacada=true order by trad.nombre asc");
            query.setString("idioma", lang);
            List<Materia> materias = query.list();
            return materias;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene una materia. (metode que s'emplea per el backoffice i el frontoffice.
     * Hem llevat els rols (PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Materia obtenerMateria(Long id) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            Hibernate.initialize(materia.getFoto());
            Hibernate.initialize(materia.getIcono());
            Hibernate.initialize(materia.getIconoGrande());
            Hibernate.initialize(materia.getProcedimientosLocales());
            for (Iterator iterator = materia.getIconos().iterator(); iterator.hasNext();) {
                IconoMateria icono = (IconoMateria) iterator.next();
                Hibernate.initialize(icono.getIcono());
            }
            for (Iterator iterator = materia.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionMateria traduccion = (TraduccionMateria) materia.getTraduccion(lang);
                if(traduccion!= null){
                    Hibernate.initialize(traduccion.getDistribComp());
                    Hibernate.initialize(traduccion.getNormativa());
                    Hibernate.initialize(traduccion.getContenido());
                }
            }
            return materia;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }



    /**
     * Nos dice si una materia tiene procedimientos o fichas
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean tieneProcedimientosOFichas(Long id){
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            Set procedimientos = materia.getProcedimientosLocales();
            Set fichas = materia.getFichas();
            return procedimientos.size() != 0 || fichas.size() != 0;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
	/**
     * Obtiene el listado de materias de una UA
     * Se toma la Unidad administrativa principal de la materia
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public List<Materia> listarMateriasbyUA (Long ua){
        Session session = getSession();
        try {
            Query query = session.createQuery("from UnidadMateria unimat where unimat.unidadPrincipal='S' and unimat.unidad.id=:ua");
        	//Query query = session.createQuery("from UnidadMateria unimat where unimat.unidadPrincipal='S' and unimat.unidad.id:=ua");
            query.setLong("ua", ua);
            query.setCacheable(true);
            return (List<Materia>)query.list();
            //if (result.isEmpty()) {
              //  return null;
            //}
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    

    /**
     * Borra una Materia.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarMateria(Long id) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);

            Set procedimientos = materia.getProcedimientosLocales();
            Set fichas = materia.getFichas();
            Set iconos = materia.getIconos();
            for(Iterator iter = iconos.iterator();iter.hasNext();){
                IconoMateria icono =(IconoMateria)iter.next();
                PerfilCiudadano perfil = icono.getPerfil();
                perfil.removeIconoMateria(icono);
            }
            if(procedimientos.size()!= 0|| fichas.size()!= 0){
                throw new EJBException("La materia contiene registros asociados");
            }
            session.delete(materia);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene todos los grupos {@link MateriaAgrupacionM} a los que pertenece una determinada materia
     * @return lista de {@link MateriaAgrupacionM}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
    public Set<MateriaAgrupacionM> obtenerGruposMateria(Long idmateria) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, idmateria);
            Hibernate.initialize(materia.getMateriasAgrupacionM());
            return materia.getMateriasAgrupacionM();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }    
    
    /**
     * Obtiene el archivo distribuci�n competencial de una Materia.(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerDistribComp(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            TraduccionMateria tradMateria = (TraduccionMateria) materia.getTraduccion(lang);
            if (tradMateria == null || tradMateria.getDistribComp() == null) {
                if (useDefault) {
                	tradMateria = (TraduccionMateria) materia.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradMateria.getDistribComp());
            return tradMateria.getDistribComp();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene el archivo de normativa de una Materia.(PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerNormativa(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            TraduccionMateria tradMateria = (TraduccionMateria) materia.getTraduccion(lang);
            if (tradMateria == null || tradMateria.getNormativa() == null) {
                if (useDefault) {
                	tradMateria = (TraduccionMateria) materia.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradMateria.getNormativa());
            return tradMateria.getNormativa();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene el archivo de contenido de una Materia. (PORMAD)
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerContenido(Long id, String lang, boolean useDefault) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            TraduccionMateria tradMateria = (TraduccionMateria) materia.getTraduccion(lang);
            if (tradMateria == null || tradMateria.getContenido() == null) {
                if (useDefault) {
                	tradMateria = (TraduccionMateria) materia.getTraduccion();
                } else {
                    return null;
                }
            }
            Hibernate.initialize(tradMateria.getContenido());
            return tradMateria.getContenido();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Obtiene la foto de una Materia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerFoto(Long id) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            Hibernate.initialize(materia.getFoto());
            return materia.getFoto();            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene el icono de una Materia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerIcono(Long id) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            Hibernate.initialize(materia.getIcono());
            return materia.getIcono();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene el icono grande de una Materia
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerIconoGrande(Long id) {
        Session session = getSession();
        try {
            Materia materia = (Materia) session.load(Materia.class, id);
            Hibernate.initialize(materia.getIconoGrande());
            return materia.getIconoGrande();            
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * A partir de un Array de Strings con los codigos
     * estandar de las materias recojo un {@link Set} de {@link Materia} con las materias
     * contenidas cuyo codigo Estandar este en el Array de Strings.(PORMAD)
     * 
     * @param ceMaterias
     * @return Un {@link Set} de {@link Materia}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public Set<Materia> obtenerMateriasCE(final String[] ceMaterias){
		Session session = getSession();
        try {
        	Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, ceMaterias);
    		return materias;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
	}
	
	/**
     * A partir de un Strings con el codigo estandar de las materia recojo
     * la {@link Materia} correspondiente (PORMAD)
     * 
     * @param codigosEstandarMateria
     * @return {@link Materia}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public Materia obtenerMateriaCE(final String codigosEstandarMateria){
		Session session = getSession();
        try {
        	Query query = session.createQuery("from Materia materia where materia.codigoEstandar=:codigo");
        	query.setString("codigo", codigosEstandarMateria);
            return (Materia)query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
	}
	
	/**
     * Busca todos los {@link Materia} cuyo nombre contenga el String de entrada(PORMAD)
     * 
     * @param busqueda
     * @param idioma
     * @return lista de {@link Materia}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<Materia> buscar(final String busqueda, final String idioma){
		List<Materia> resultado;
		if(busqueda!=null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("from Materia as mat, mat.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	resultado = (List<Materia>)query.list();
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
     * Obtiene el listado de fichas y procedimientos de la materia
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public Materia obtenerMateriaFichasProced (Long id) {
		
        Session session = getSession();
        try {
    		Materia materia = (Materia) session.load(Materia.class, id);
    		Hibernate.initialize(materia.getFichas());
    		Hibernate.initialize(materia.getProcedimientosLocales());
            return materia;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
        
    }
	
}
