package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

/**
 * SessionBean para consultar Auditoria.
 *
 * @ejb.bean
 *  name="sac/persistence/AuditoriaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.AuditoriaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class AuditoriaFacadeEJB extends HibernateEJB {
	
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }


    /**
     * Lista todas las Auditorias de un identificador de una unidad administrativa.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param idUA	Identificador de una unidad administrtiva.
     * 
     * @return Devuelve <code>List</code> de todas las auditorias filtradas por una unidad administrativa.
     */
    public List listarAuditoriasUnidadAdministrativa(Long idUA) {

    	Session session = getSession();
        
        try {
        	
            UnidadAdministrativa unidad = (UnidadAdministrativa) session.load( UnidadAdministrativa.class , idUA );
            Historico historico = getHistorico( session , unidad );
            
            Criteria criteri = session.createCriteria( Auditoria.class );
            criteri.add( Expression.eq( "historico.id" , historico.getId() ) );
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     * Lista todas las Auditorias de un Procedimiento.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param idProcedimiento	Identificador de un procedimiento.
     * 
     * @return Devuelve <code>List</code> de todas las auditorías de un procedimiento.
     */
    public List listarAuditoriasProcedimiento(Long idProcedimiento) {

    	Session session = getSession();
        
        try {
        	
            ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load( ProcedimientoLocal.class , idProcedimiento );
            Historico historico = getHistorico( session , procedimiento );
            
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add( Expression.eq( "historico.id" , historico.getId() ) );
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    

    /**
     * Lista todas las Auditorias de un Procedimiento.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param idNoramtiva Identificador de una normativa.
     * 
     * @return Devuelve <code>List</code> de todas las auditorías de un procedimiento.
     */
    public List listarAuditoriasNormativa(Long idNoramtiva) {

    	Session session = getSession();
        
        try {
        	
            Normativa normativa = (Normativa) session.load( Normativa.class , idNoramtiva );
            Historico historico = getHistorico( session , normativa );
            
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add( Expression.eq( "historico.id" , historico.getId() ) );
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     * Lista todas las Auditorias de una ficha.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param idFicha	Identificador de una ficha informativa.
     * 
     * @return Devuelve <code>List</code> de todas las auditorías de una ficha informativa.
     */
    public List listarAuditoriasFicha(Long idFicha) {

    	Session session = getSession();
        
        try {
        	
            Ficha ficha = (Ficha) session.load( Ficha.class , idFicha );
            Historico historico = getHistorico( session , ficha );
            
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add( Expression.eq( "historico.id" , historico.getId() ) );
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     * Lista todas las Auditorias de un Historico.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param idHistorico	Identificador de histórico.
     * 
     * @return Devuelve <code>List</code> de todas las auditorías de un histórico.
     */
    public List listarAuditoriasHistorico(Long idHistorico) {

    	Session session = getSession();
        
        try {
        	
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add( Expression.eq( "historico.id" , idHistorico ) );
            
            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

    
    /**
     * Lista todos los Historicos entre dos Fechas.
     * @ejb.interface-method
     * 
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     * 
     * @param fechaIni	Fecha de inicio de una auditoría.
     * 
     * @param fechaFin	Fecha de finalización de una auditoría.
     * 
     * @return Devuelve <code>List</code> de todos los históricos filtrados por la fecha de inicio y la fecha de finalización.
     */
    public List listarHistoricosAuditorias(Date fechaIni, Date fechaFin) {

    	Session session = getSession();
        
        try {

            Query query = session.createQuery("from Auditoria as a where a.fecha between :fechaIni and :fechaFin order by a.historico.nombre asc");
            query.setParameter( "fechaIni" , fechaIni );
            query.setParameter( "fechaFin" , fechaFin );
            
            List auditorias = query.list();
            
            List historicos = new Vector();
            Iterator it = auditorias.iterator();
            while ( it.hasNext() ) {
            	
                Auditoria auditoria = (Auditoria) it.next();
                if ( !historicos.contains( auditoria.getHistorico() ) ) {
                	
                    historicos.add( auditoria.getHistorico() );
                    
                }
            	
            }
            
            return historicos;
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     * Lista todas las Auditorias de un Procedimiento.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param idProcedimiento	Identificador de un procedimiento.
     * 
     * @return Devuelve <code>List</code> de todas las auditorías de un procedimiento.
     */
   public List listarAuditoriasProcedimientoPMA(Long idProcedimiento) {
    	
        Session session = getSession();
        
        try {
        	
        	ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load( ProcedimientoLocal.class , idProcedimiento );
            Historico historico = getHistorico( session , procedimiento );
            
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add( Expression.eq( "historico.id" , historico.getId() ) );
            criteri.addOrder( Order.desc("fecha") );

            return criteri.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }
    
    
    /**
     * Lista todas las Auditorias de una ficha.
     * @ejb.interface-method
     * 
     * @ejb.permission unchecked="true"
     * 
     * @param idFicha	Identificador de una ficha informativa.
     * 
     * @return Devuelve <code>List</code> de todas las auditorías de una ficha informativa.
     */
    public List listarAuditoriasFichaPMA(Long idFicha) {
    	
        Session session = getSession();
        
        try {
        	
            Ficha ficha = (Ficha) session.load( Ficha.class , idFicha );
            Historico historico = getHistorico( session , ficha );
            
            Criteria criteria = session.createCriteria(Auditoria.class);
            criteria.add( Expression.eq( "historico.id" , historico.getId() ) );
            criteria.addOrder(Order.desc("fecha"));
            
            return criteria.list();
            
        } catch (HibernateException he) {
        	
            throw new EJBException(he);
            
        } finally {
        	
            close(session);
            
        }
        
    }

}
