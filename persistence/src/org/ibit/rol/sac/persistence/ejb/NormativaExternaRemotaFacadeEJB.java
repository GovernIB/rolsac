package org.ibit.rol.sac.persistence.ejb;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.util.RemotoUtils;

import javax.ejb.CreateException;
import javax.ejb.EJBException;


/**
 * SessionBean para mantener y consultar Normativa Remota.
 *
 * @ejb.bean
 *  name="sac/persistence/NormativaExternaRemotaFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.NormativaExternaRemotaFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class NormativaExternaRemotaFacadeEJB extends HibernateEJB {

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    
	/**
	 * Crea una Normativa Remota
	 * @throws HibernateException 
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public Long grabarNormativaExternaRemota(final NormativaExternaRemota normativaExtRemota) {
    	 Session session = getSession();
	        try {     	
	        	if( normativaExtRemota!=null){
	        		if (normativaExtRemota.getId() != null) {
			            session.update(normativaExtRemota);
		            }
		            else{
			            session.save(normativaExtRemota);
		            }
		            session.flush();
	        	}
	            return normativaExtRemota.getId();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
    }
    
    
    /**
     * Obtiene un Normativa Remota apartir de su id externo y su id de la UARemota
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public NormativaExternaRemota obtenerNormativaExternaRemota(Long idExterno,Long idUaRemota) {
        Session session = getSession();
        try {

			Query query = session.createQuery("from NormativaExternaRemota as nr where nr.idExterno="+idExterno+" and nr.administracionRemota.id="+idUaRemota);
			NormativaExternaRemota normativa = (NormativaExternaRemota)query.uniqueResult();

			if (normativa != null) {
				Hibernate.initialize(normativa.getProcedimientos());
			}

			return normativa;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }


    /**
		 * Obtiene una Normativa Remota segun su idExterno y el IdRemoto actualmente
		 * de la AdministracionRemota
		 *
		 * @ejb.interface-method
		 * @ejb.permission unchecked="true"
		 */
		public NormativaExternaRemota obtenerNormativaExternaRemota(final String idRemoto, final Long idExtNorm) {
			final Session session = getSession();
			try {
				AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

				if(admin==null)
	        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

				return RemotoUtils.recogerNormativa(session, idExtNorm, admin.getId());
			} catch (final HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}
    

    
    /**
     * Obtiene las Normativas de una procedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Normativa> obtenerNormativasProcedimiento(Long idProcedimiento) {
        Session session = getSession();
        try {
			Query query = session.createQuery("select elements(proc.normativas) from ProcedimientoLocal as proc where proc.id="+idProcedimiento);

			return (List<Normativa>)query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }



    /**
        * Aï¿½ade un nuevo procedimiento a la normativa
        * @ejb.interface-method
        * @ejb.permission unchecked="true"
        */
       public void anyadirProcedimiento( Long proc_id, Long norm_id) {

           Session session = getSession();
           try {
              
               NormativaExternaRemota normativa = (NormativaExternaRemota) session.load(NormativaExternaRemota.class, norm_id);
               ProcedimientoRemoto procedimiento = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, proc_id);
               procedimiento.getNormativas().add(normativa);
               normativa.getProcedimientos().add(procedimiento);
               session.flush();
           } catch (HibernateException e) {
               throw new EJBException(e);
           } finally {
               close(session);
           }
       }

      /**
     * borra una normativa remota. Se duplica paro no tener roles.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
     public void borrarNormativaRemota(Long id) {
         Session session = getSession();
	        try {
	            Normativa normativa = (Normativa) session.load(Normativa.class, id);
	            for (Iterator iterator = normativa.getProcedimientos().iterator(); iterator.hasNext();) {
	                ProcedimientoLocal procedimiento = (ProcedimientoLocal) iterator.next();
	                procedimiento.getNormativas().remove(normativa);
	            }
	            if(normativa instanceof NormativaExternaRemota){
	                AdministracionRemota admin = ((NormativaExternaRemota)normativa).getAdministracionRemota();
	                if(admin!=null)
	                	admin.removeNormativaExternaRemota((NormativaExternaRemota)normativa);
	            }
	            session.delete(normativa);
	            session.flush();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
      }


     /**
		 * Borra una Normativa Remota segun su idExterno
		 *
		 * @ejb.interface-method
		 * @ejb.permission unchecked="true"
		 */
		public void borrarNormativaRemota(final String idRemoto ,final Long idExt) {

			final Session session = getSession();
			try {
				AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

	        	if(admin==null)
	        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

				final NormativaExternaRemota externaRemota = obtenerNormativaExternaRemota(idExt, admin.getId());

				if(externaRemota!=null){
					admin.removeNormativaExternaRemota(externaRemota);
					session.delete(externaRemota);
					session.flush();
				}
			} catch (final HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}


         /**
	     * elimina un procedimiento de la  normativa
	     * @ejb.interface-method
	     * @ejb.permission unchecked="true"
	     * */
	    public void eliminarProcNormativa(String idRemoto,Long idNorm, Long idProc) {
	        Session session = getSession();
	        try {
				AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
				if(admin==null)
	        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

				ProcedimientoRemoto procedimiento = RemotoUtils.recogerProcedimiento(session, idProc, admin.getId());
				NormativaExternaRemota ner = RemotoUtils.recogerNormativa(session, idNorm, admin.getId());

                procedimiento.getNormativas().remove(ner);
                ner.getProcedimientos().remove(procedimiento);
	            session.flush();

	        } catch (HibernateException e) {
	            throw new EJBException(e);
	        } finally {
	            close(session);
	        }
	    }	
}
