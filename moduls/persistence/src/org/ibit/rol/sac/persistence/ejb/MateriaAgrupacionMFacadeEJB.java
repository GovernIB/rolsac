package org.ibit.rol.sac.persistence.ejb;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean para mantener y consultar Materia AgrupacionMateria. 
 *
 * @ejb.bean
 *  name="sac/persistence/MateriaAgrupacionMFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.MateriaAgrupacionMFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class MateriaAgrupacionMFacadeEJB extends HibernateEJB
{
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
	public void ejbCreate() throws CreateException
	{
        super.ejbCreate();
    }
    
    
    /**
     * Lista todas las materias agrupadas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @param pagina	Número de página actual.
     * @param resultats	Número de resultados por página.
     * @return Devuelve <code>ResultadoBusqueda</code> que contiene un listado con todas las materias agrupadas.
     */
	public ResultadoBusqueda listarAgrupacionMaterias(int pagina, int resultats)
	{
		return listarTablaMaestraPaginada( pagina, resultats, listarAgrupacionMaterias() );   
        
    }
    
    
    /**
     * Lista todas las materias agrupadas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * @return Devuelve una lista con todas las materias agrupadas.
     */
    public List listarAgrupacionMaterias()
    {
    	Session session = getSession();
    	try {
    		Criteria criteri = session.createCriteria(AgrupacionMateria.class);
    		return criteri.addOrder(Order.asc("codigoEstandar")).list();
    		
    	} catch (HibernateException he) {
    		throw new EJBException(he);
    	} finally {
    		close(session);
    	}
    }
    
}
