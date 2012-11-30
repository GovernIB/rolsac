package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Tratamiento;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Tratamientos.
 *
 * @ejb.bean
 *  name="sac/persistence/TratamientoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TratamientoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TratamientoFacadeEJB extends HibernateEJB{

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Crea o actualiza un Tratamiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public Long grabarTratamiento(Tratamiento tratamiento) {
        Session session = getSession();
        try {
            session.saveOrUpdate(tratamiento);
            session.flush();
            return tratamiento.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene un Tratamiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public Tratamiento obtenerTratamiento(Long id) {
        Session session = getSession();
        try {
            return (Tratamiento) session.load(Tratamiento.class, id);
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista todos los Tratamientos
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public ResultadoBusqueda listarTratamientos(int pagina, int resultats) {
    	return listarTablaMaestraPaginada(pagina, resultats, listarTratamientos());
    }    
    
    /**
     * Lista todos los Tratamientos
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarTratamientos(){
         Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Tratamiento.class);
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Nos dice si un tratamiento tiene unidades asociadas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public boolean tieneUnidades(Long id){
        Session session = getSession();
        try {
            List uas = session.find("from UnidadAdministrativa as ua where ua.tratamiento.id=?", id, Hibernate.LONG);
            return (uas.size()) != 0;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * borra un tratamiento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
    public void borrarTratamiento(Long id) {
        Session session = getSession();
        try {
            List uas = session.find("from UnidadAdministrativa as ua where ua.tratamiento.id=?", id, Hibernate.LONG);
            if((uas.size())!=0){
                 throw new EJBException("El tratamiento contiene Unidades Administrativas");
            }
            Tratamiento tratamiento = (Tratamiento) session.load(Tratamiento.class, id);
            session.delete(tratamiento);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * A partir de un String con el codigo estandar de un tratamiento recojo
     * un {@link Tratamiento} correspondiente
     * 
     * @param codigoEstandar
     * @return {@link Tratamiento}
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public Tratamiento obtenerTratamientoCE(final String codigosEstandar){
		Session session = getSession();
        try {
        	Query query = session.createQuery("from Tratamiento as trat where trat.codigoEstandar=:codigo");
        	query.setString("codigo", codigosEstandar);
            return (Tratamiento)query.uniqueResult();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
	}

}
