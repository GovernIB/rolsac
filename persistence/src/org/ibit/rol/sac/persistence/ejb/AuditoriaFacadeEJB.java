package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import net.sf.hibernate.type.Type;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Ficha;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
     * Lista todas las Auditorias de una unidad_id Adminitrativa.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarAuditoriasUnidadAdministrativa(Long unidad_id) {
        Session session = getSession();
        try {
            UnidadAdministrativa unidad = (UnidadAdministrativa)session.load(UnidadAdministrativa.class, unidad_id);
            Historico historico = getHistorico(session, unidad);
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico.getId()));
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
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarAuditoriasProcedimiento(Long proc_id) {
        Session session = getSession();
        try {
            ProcedimientoLocal procedimiento = (ProcedimientoLocal)session.load(ProcedimientoLocal.class, proc_id);
            Historico historico = getHistorico(session, procedimiento);
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico.getId()));
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
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarAuditoriasNormativa(Long norm_id) {
        Session session = getSession();
        try {
            Normativa normativa = (Normativa)session.load(Normativa.class, norm_id);
            Historico historico = getHistorico(session, normativa);
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico.getId()));
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
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarAuditoriasFicha(Long ficha_id) {
        Session session = getSession();
        try {
            Ficha ficha = (Ficha)session.load(Ficha.class, ficha_id);
            Historico historico = getHistorico(session, ficha);
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico.getId()));
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
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarAuditoriasHistorico(Long historico_id) {
        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico_id));
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
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public List listarHistoricosAuditorias(Date fechaIni, Date fechaFin) {
        Session session = getSession();
        try {
            Object[] params={fechaIni, fechaFin};
            Type[] tipos = {Hibernate.DATE,Hibernate.DATE};
            List historicos = new Vector();
            List auditorias = session.find("from Auditoria as a where a.fecha between ? and ? order by a.historico.nombre asc",params,tipos);
            for(Iterator iter = auditorias.iterator(); iter.hasNext();){
                Auditoria auditoria = (Auditoria)iter.next();
                if(!historicos.contains(auditoria.getHistorico())){
                    historicos.add(auditoria.getHistorico());
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
     * PORMAD
     * Lista todas las Auditorias de un Procedimiento.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarAuditoriasProcedimientoPMA(Long proc_id) {
        Session session = getSession();
        try {
            ProcedimientoLocal procedimiento = (ProcedimientoLocal)session.load(ProcedimientoLocal.class, proc_id);
            Historico historico = getHistorico(session, procedimiento);
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico.getId()));
            criteri.addOrder(Order.desc("fecha"));

            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * PORMAD
     * Lista todas las Auditorias de una ficha.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List listarAuditoriasFichaPMA(Long ficha_id) {
        Session session = getSession();
        try {
            Ficha ficha = (Ficha)session.load(Ficha.class, ficha_id);
            Historico historico = getHistorico(session, ficha);
            Criteria criteri = session.createCriteria(Auditoria.class);
            criteri.add(Expression.eq("historico.id",historico.getId()));
            criteri.addOrder(Order.desc("fecha"));
            return criteri.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
