package org.ibit.rol.sac.persistence.ejb;

import java.io.ByteArrayOutputStream;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

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

	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * obtiene el archivo
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Archivo obtenerArchivo(Long id) {
        Session session = getSession();
        try {
        	Archivo archi = new Archivo();
        	
        	Query query = session.createQuery("from Archivo archi where archi.id="+id.toString());
        	if (query.list().size()==1)	archi=(Archivo)query.list().get(0);
            Hibernate.initialize(archi);
        	
        	return archi;

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene los datos del archivo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */
    public ByteArrayOutputStream getFitxer(Long id) {
    	Archivo tmp = obtenerArchivo(id);
    	if (tmp.getDatos() != null) {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		baos.write(tmp.getDatos(), 0, tmp.getDatos().length);
    		return baos;
    	} else
    		return null;
    }
    
    /**
     * Obtiene el mime del archivo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */
    public String getMime(Long id) {    	
    	Archivo tmp = obtenerArchivo(id);
    	if (tmp.getMime() != null)
    		return tmp.getMime();
    	else
    		return "text/plain";
    }
    
    /**
     * Obtiene el peso del archivo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */
    public long getPes(Long id) {    	
    	Archivo tmp = obtenerArchivo(id);

    	return tmp.getPeso() / 1024;
    }    
    
    /**
     * Obtiene el nombre del archivo.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
     */
    public String getNombre(Long id) {
    	Archivo tmp = obtenerArchivo(id);
    	
    	if (tmp.getNombre() != null)
    		return tmp.getNombre();
    	else
    		return "sin_nombre";
    }
    
    
    /**
     * Borra un archivo.
     * 
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param	id	Identificador del archivo a borrar.
     */
    public void borrarArchivo(Long id) {
    	
        Session session = getSession();
        try {
        	
            Archivo archivo = (Archivo) session.load(Archivo.class, id);
            session.delete(archivo);
            session.flush();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);  
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
}
