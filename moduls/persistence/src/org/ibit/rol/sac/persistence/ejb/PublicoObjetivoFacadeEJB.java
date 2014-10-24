package org.ibit.rol.sac.persistence.ejb;

import java.util.List;
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

import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.PublicoObjetivo;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Publico Objetivo.(PORMAD)
 *
 * @ejb.bean
 *  name="sac/persistence/PublicoObjetivoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.PublicoObjetivoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class PublicoObjetivoFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1069547135957871563L;

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
    /**
     * Crea o actualiza un Publico Objetivo.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	publico	Indica el publico objetivo a guardar.
     * 
     * @return Devuelve el identificador del público objetivo guardado.
     */
    public Long grabarPublicoObjetivo(PublicoObjetivo publico) {
    	
        Session session = getSession();
        try {
        	
            if ( publico.getId() == null ) {
            	
                Criteria criteria = session.createCriteria(PublicoObjetivo.class);
                List<PublicoObjetivo> result = castList( PublicoObjetivo.class, criteria.list() );
                if ( result.isEmpty() ) {
                	
                    publico.setOrden(0);
                    
                } else {
                	
                    publico.setOrden( result.size() );
                    
                }
                
            }
            
            session.saveOrUpdate(publico);
            session.flush();
            
            return publico.getId();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     * Lista todos los Publico Objetivo.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param 	pagina	Indica la última página visitada.
     * 
     * @param	resultats	Indica el número de resultados por página.
     * 
     * @return	Devuelve <code>ResultadoBusqueda</code> que contiene un listado de todos los públicos objetivos.
     */
    public ResultadoBusqueda listarPublicoObjetivo(int pagina, int resultats) {
    	
    	return listarTablaMaestraPaginada( pagina, resultats, listarTMPublicoObjetivo() );
    	
    }
    
    
    /**
     * Lista todos los Publico Objetivo.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     *
     * @return Devuelve un listado de todos los públicos objetivos.
     */
	public List<PublicoObjetivo> listarPublicoObjetivo() {
    	
        Session session = getSession();
        try {
        	
            Criteria criteri = session.createCriteria(PublicoObjetivo.class);
            criteri.addOrder( Order.asc("orden") );
            List<PublicoObjetivo> publicos = castList( PublicoObjetivo.class, criteri.list() );
            
            for ( PublicoObjetivo publico : publicos ) {
            	
                Hibernate.initialize( publico.getAgrupaciones() );
                Set<AgrupacionHechoVital> agrupaciones = publico.getAgrupaciones();
                
                for ( AgrupacionHechoVital agrupacion : agrupaciones )
                    Hibernate.initialize( agrupacion.getHechosVitalesAgrupacionHV() );
                
            }
            
            return publicos;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
        }
    }

	
    /**
     * Listar público objetivo (menú administración) 
     */
    private List<?> listarTMPublicoObjetivo() {
    	
    	corregirOrdenacion();
    	Session session = getSession();
    	try {
    		
    		StringBuilder  consulta = new StringBuilder("select pubObj.id, pubObj.orden, pubObj.codigoEstandar ");
    		consulta.append(" from PublicoObjetivo as pubObj ");
    		consulta.append(" order by pubObj.orden asc ");
    		Query query = session.createQuery( consulta.toString() );
    		
    		return query.list();
    		
    	} catch (HibernateException he) {
    		
    		throw new EJBException(he);
    		
    	} finally {
    		
    		close(session);
    		
    	}    	

    }
    
    
    /**
     * Obtiene un Publico Objetivo
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param	id	Identificador de un público objetivo
     * 
     * @return	Devuelve <code>PublicoObjetivo</code> solicitado.
     */
    public PublicoObjetivo obtenerPublicoObjetivo(Long id) {
    	
        Session session = getSession();
        try {
        	
            PublicoObjetivo publico = (PublicoObjetivo) session.load(PublicoObjetivo.class, id);
            
            return publico;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     * Borra un Publico Objetivo.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	id	Identificador de un público objetivo.
     */
    public void borrarPublicoObjetivo(Long id) {
    	
        Session session = getSession();
        try {
        	
            PublicoObjetivo publico = (PublicoObjetivo) session.load(PublicoObjetivo.class, id);

            Criteria criteria = session.createCriteria(PublicoObjetivo.class);
            criteria.add( Expression.gt( "orden", new Integer( publico.getOrden() ) ) );
            List<PublicoObjetivo> publicos = castList( PublicoObjetivo.class, criteria.list() );
            
            for ( int i = 0 ; i < publicos.size() ; i++ ) {
            	
                PublicoObjetivo pub = (PublicoObjetivo) publicos.get(i);
                pub.setOrden(i);
                
            }

            session.delete(publico);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     * Incrementa el orden de un Público Objetivo.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	id	Identificador de un público objetivo
     */
	public void subirOrden(Long id) {
		
        Session session = getSession();
        try {
        	
            PublicoObjetivo poActual = (PublicoObjetivo) session.load(PublicoObjetivo.class, id);
            Integer orden = poActual.getOrden();
            
            if ( orden > 0 ) {
            	
                Criteria criteri = session.createCriteria(PublicoObjetivo.class);
                criteri.addOrder( Order.asc("orden") );
                List<PublicoObjetivo> result = castList( PublicoObjetivo.class, criteri.list() );

                PublicoObjetivo poModificado = (PublicoObjetivo) result.get( orden - 1 );

                poModificado.setOrden(orden);
                result.set(orden, poModificado);
                poActual.setOrden(orden - 1);
                result.set(orden - 1, poActual);
                
            }

            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
	
           
    /**
     * Asigna a un público objetivo un nuevo orden y reordena los elementos afectados.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin}"
     * 
     * @param	id	Identificador de un público objetivo.
     * 
     * @param	ordenNuevo	Número que indica el nuevo orden del público objetivo.
     * 
     * @param	ordenAnterior	Número que indica el anterior orden del público objetivo.
     */	
    public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior) {
    	    	
        Session session = getSession();
        try {

        	Criteria criteria = session.createCriteria(PublicoObjetivo.class);
        	criteria.addOrder( Order.asc("orden") );
        	List<PublicoObjetivo> listaPublicosObjetivo = castList( PublicoObjetivo.class, criteria.list() );
        	
        	// Modificar sólo los elementos entre la posición del elemento que cambia 
        	// de orden y su nueva posición 
        	int ordenMayor = ( ordenNuevo > ordenAnterior ) ? ordenNuevo : ordenAnterior;
        	int ordenMenor = ( ordenMayor == ordenNuevo ) ? ordenAnterior : ordenNuevo;
        	
        	// Si el nuevo orden es mayor que el anterior, desplazar los elementos 
        	// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
        	int incremento = ( ordenNuevo > ordenAnterior ) ? -1 : 1;        			
        	
        	for ( PublicoObjetivo publicoObjetivo : listaPublicosObjetivo ) {        		    
        		
        		int orden = publicoObjetivo.getOrden();
        		if ( orden >= ordenMenor && orden <= ordenMayor ) {
        			
        			if ( id.equals( publicoObjetivo.getId() ) ) {
        				
        				publicoObjetivo.setOrden( ordenNuevo );
        				
        			} else {
        				
        				publicoObjetivo.setOrden( orden + incremento );
        				
        			}
        			
        		}

        		// Actualizar todo para asegurar que no hay duplicados ni huecos        		
        		session.saveOrUpdate(publicoObjetivo);
        		
        	}
        	
        	session.flush();
        	
        } catch (HibernateException he) {
        	
        	throw new EJBException(he);
        	
        } finally {
        	
        	close(session);
        	
        }
        
    }

    
    /**
     * Busca registros con el campo "orden" repetido y en caso afirmativo corrige
     * toda la tabla ordenando los registros convenientemente. 
     */
    private void corregirOrdenacion() {
    	
    	if ( !isOrdenesRepetidos() ) 
    		return;

    	Session session = getSession();
    	try {
    		
        	Criteria criteria = session.createCriteria(PublicoObjetivo.class);
        	criteria.addOrder( Order.asc("orden") );
        	List<PublicoObjetivo> listaPublicosObjetivo = castList( PublicoObjetivo.class, criteria.list() );

           	// Asegurar que la secuencia de orden incrementa de uno en uno        	
        	for ( int i = 0 ; i < listaPublicosObjetivo.size() ; i++ ) {
        		
        		PublicoObjetivo publicoObjetivo = listaPublicosObjetivo.get(i);
        		publicoObjetivo.setOrden(i);
        		session.saveOrUpdate(publicoObjetivo);
        		
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
     * @return Devuelve <code>true</code> si existen registros con el campo orden repetido
     */
    private boolean isOrdenesRepetidos() {
    	
    	Session session = getSession();
    	try {
    		
    		StringBuilder consulta = new StringBuilder("select pubObj.orden from PublicoObjetivo as pubObj ");
    		consulta.append("group by pubObj.orden ");
    		consulta.append("having count(pubObj.orden) > 1");
    		
    		Query query = session.createQuery( consulta.toString() );

    		return !query.list().isEmpty();
    		
    	} catch (HibernateException e) {
    		
    		throw new EJBException(e);
    		
    	} finally {
    		
    		close(session);
    		
    	}   
    	
    }
    
    
    /**
     * Obtener listado de públicos objetivo según un listado de IDs
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List<PublicoObjetivo> obtenerPublicosObjetivoPorIDs(String ids, String idioma) {
        
        List<PublicoObjetivo> resultado;
        Session session = getSession();
        try {
            Query query = session.createQuery("from PublicoObjetivo as po, po.traducciones as trad where index(trad) = :idioma and po.id in (" + ids + ") ");
            query.setString("idioma", idioma);
            resultado = (List<PublicoObjetivo>)query.list();
            return resultado;
        } catch (HibernateException he){
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
}