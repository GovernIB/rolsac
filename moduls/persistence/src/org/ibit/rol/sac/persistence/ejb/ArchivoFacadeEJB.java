package org.ibit.rol.sac.persistence.ejb;

import java.io.ByteArrayOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Archivo;

/**
 * SessionBean para obtener archivos.
 *
 * @ejb.bean
 *  name="sac/persistence/ArchivoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.ArchivoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class ArchivoFacadeEJB extends HibernateEJB {
	
	private static final String nombrePorDefecto = "sin_nombre";

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    
    /**
     * Obtiene una instancia de tipo Archivo
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param id	Identificador del archivo a obtener.
     * 
     * @return <code>Archivo</code> con el identificador especificado.
     */
    public Archivo obtenerArchivo(Long id) {
    	
        Session session = getSession();
        
        try {
        	
        	Archivo archivo = new Archivo();
        	
        	Query query = session.createQuery("from Archivo archivo where archivo.id = :id");
        	query.setParameter( "idArchivo" , id );
        	
        	if ( query.list().size() == 1 )	
        		archivo = (Archivo) query.list().get(0);
        	
            Hibernate.initialize(archivo);
        	
        	return archivo;

        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     *  @deprecated No se usa
     * Obtiene los datos del archivo.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param id	Identificador del archivo a obtener.
     * 
     * @return Devuelve <code>ByteArrayOutputStream</code> del fichero solicitado si éste no es nulo, en caso contrario devuelve null.
     * 
     */
    public ByteArrayOutputStream getFitxer(Long id) {
    	Archivo archivo = obtenerArchivo(id);
    	
    	ByteArrayOutputStream baos = null;
    	if ( archivo.getDatos() != null ) {
    		
    		baos = new ByteArrayOutputStream();
    		baos.write( archivo.getDatos() , 0 , archivo.getDatos().length );

    	}
    	
		return baos;
    		
    }
    
    
    /**
     *  @deprecated No se usa
     * Obtiene el mime del archivo.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param id	Identificador del archivo a obtener.
     * 
     * @return Devuelve el mime del archivo solicitado si no es nulo, en caso contrario devuelve <code>text/plain</code>.
     * 
     */
    public String getMime(Long id) {   
    	Archivo archivo = obtenerArchivo(id);
    	
    	return ( archivo.getMime() != null ) ? archivo.getMime() : "text/plain";
    	
    }
    
    
    /**
     *  @deprecated No se usa
     * Obtiene el peso del archivo.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param id	Identificador del archivo a obtener.
     * 
     * @return Devuelve el tamaño del archivo mediante un <code>long</code>.
     * 
     */
    public long getPes(Long id) {  
    	return obtenerArchivo(id).getPeso() / 1024;
    	
    }    
    
    
    /**
     *  @deprecated No se usa
     * Obtiene el nombre del archivo.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param id	Identificador del archivo a obtener.
     * 
     * @return Devuelve <code>String</code> con el nombre del archivo solicitado, si éste es nulo devuelve valor por defecto.
     * 
     */
    public String getNombre(Long id) {
    	Archivo archivo = obtenerArchivo(id);
    	
    	return ( archivo.getNombre() != null ) ? archivo.getNombre() : nombrePorDefecto;  
    	
    }   
    
}
