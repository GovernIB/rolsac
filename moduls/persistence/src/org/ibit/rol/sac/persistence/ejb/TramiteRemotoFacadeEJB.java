package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.*;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.TramiteRemoto;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.RemotoUtils;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.util.*;

/**
 * SessionBean para mantener y consultar Tramites Remotos.
 *
 * @ejb.bean
 *  name="sac/persistence/TramiteRemotoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TramiteRemotoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TramiteRemotoFacadeEJB extends HibernateEJB {

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
	 * Crea una Tramite Remoto
	 * @throws HibernateException 
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
    public Long grabarTramiteRemoto(final TramiteRemoto tramiteRemoto, final Long idProc,Long idOC) {
    	 Session session = getSession();

	        try {
	        	
	        	if( idProc!=null){
	        		ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, idProc);
	        		procRemoto.addTramite(tramiteRemoto);
	        		tramiteRemoto.setProcedimiento(procRemoto);
	        		//obtenemos el órgano competente y lo añadimos
	        		if(idOC!=null){
		        		UnidadAdministrativaRemota organoCompentente = RemotoUtils.recogerUnidad(session, idOC, tramiteRemoto.getAdministracionRemota().getId());
		        		if(organoCompentente!=null){
			        		tramiteRemoto.setOrganCompetent(organoCompentente);
			        		organoCompentente.getTramites().add(tramiteRemoto);
		        		}

	        		}

	        		if (tramiteRemoto.getId() != null) {
			            session.update(tramiteRemoto);
		            }
		            else{
			            session.save(tramiteRemoto);

		            }
		            session.flush();
	        	}

	            return tramiteRemoto.getId();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
    }
    
    /**
     * Actualiza un Tramite en la importacion de una UA. Se duplica paro no tener roles.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
     */
    public Long grabarTramiteRemoto(TramiteRemoto tramiteRemoto,Long idOC) {

        Session session = getSession();
        try {
    		if(idOC!=null){
        		UnidadAdministrativaRemota organoCompententeTrans = RemotoUtils.recogerUnidad(session, idOC, tramiteRemoto.getAdministracionRemota().getId());
        		if(organoCompententeTrans!=null){
        			//En el caso que sea el mismo no hacemos nada si es diferente lo cambiamos por el nuevo
	        		if(tramiteRemoto.getOrganCompetent()!=null){
	        			if(!organoCompententeTrans.getId().equals(tramiteRemoto.getOrganCompetent().getId())){
	        				tramiteRemoto.setOrganCompetent(organoCompententeTrans);
	        				organoCompententeTrans.getTramites().add(tramiteRemoto);
	        			}

	        		}
	        		//En el caso que no haya lo añadimos como nuevo
	        		else{
        				tramiteRemoto.setOrganCompetent(organoCompententeTrans);
        				organoCompententeTrans.getTramites().add(tramiteRemoto);
	        		}
    			}
        		//Si no existe el organo competente transferido lo ponemos a null
        		else{
        			tramiteRemoto.setOrganCompetent(null);
        		}
    		}

            if (tramiteRemoto != null) {
	            session.saveOrUpdate(tramiteRemoto);
	            session.flush();
            }
            return tramiteRemoto.getId();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Obtiene los tramites de una procedimiento
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<Tramite> obtenerTramitesProcedimiento(Long idProcedimiento) {
        Session session = getSession();
        try {
			Query query = session.createQuery("select elements(proc.tramites) from ProcedimientoLocal as proc where proc.id="+idProcedimiento);

			List<Tramite> tramites=(List<Tramite>)query.list();
			List<Tramite> tramFinales = new ArrayList<Tramite>();
			for (Tramite tramite : tramites) {
				if(publico(tramite)){
					Hibernate.initialize(tramite.getFormularios());
					for (DocumentTramit formulario : tramite.getFormularios()) {
						for (final String idioma : (Collection<String>)tramite.getLangs()){
				            final TraduccionDocumento traduccion = (TraduccionDocumento)formulario.getTraduccion(idioma);
				            if(traduccion!=null){					
	
							Hibernate.initialize(traduccion.getArchivo());
				            }
				        }
					}
					Hibernate.initialize(tramite.getDocsInformatius());
					for (DocumentTramit docInformatiu : tramite.getDocsInformatius()) {
						for (final String idioma : (Collection<String>)tramite.getLangs()){
				            final TraduccionDocumento traduccion = (TraduccionDocumento)docInformatiu.getTraduccion(idioma);
				            if(traduccion!=null){					
							Hibernate.initialize(traduccion.getArchivo());
				            }
				        }
					}
					Hibernate.initialize(tramite.getTaxes());
					tramFinales.add(tramite);
					}
			}	
			
			return tramFinales;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    protected boolean publico(Tramite tram) {
        final Date now = new Date();
        boolean noCaducado = (tram.getDataCaducitat() == null || tram.getDataCaducitat().after(now));
        boolean publicado = (tram.getDataPublicacio() == null || tram.getDataPublicacio().before(now));
        boolean visible = (tram.getValidacio() == null || Validacion.PUBLICA.equals(Integer.valueOf(tram.getValidacio().toString())));
        return visible && noCaducado && publicado;
    }
    
    /**
     * Obtiene un Tramite Remoto apartir de su id externo y su id de la UARemota
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TramiteRemoto obtenerTramiteRemoto(Long idExterno,Long idUaRemota) {
        Session session = getSession();
        try {

			Query query = session.createQuery("from TramiteRemoto as tr where tr.idExterno="+idExterno+" and tr.administracionRemota.id="+idUaRemota);
			TramiteRemoto tramite = (TramiteRemoto)query.uniqueResult();

			if (tramite != null) {
				Hibernate.initialize(tramite.getProcedimiento());
			}

			return tramite;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
	/**
	 * Obtiene un Tramite Remoto segun su idExterno y el IdRemoto actualmente
	 * de la AdministracionRemota
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public TramiteRemoto obtenerTramiteRemoto(final String idRemoto, final Long idExtTramite) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			
			if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
        	
			return RemotoUtils.recogerTramite(session, idExtTramite, admin.getId());
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	

    
    /**
     * borra un tramite remoto. Se duplica paro no tener roles.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void borrarTramiteRemoto(Long id) {
        Session session = getSession();
        try {

            Tramite tramite = (Tramite) session.load(Tramite.class, id);

    		ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, tramite.getProcedimiento().getId());
    		if( tramite.getOrganCompetent()!=null){
        		UnidadAdministrativaRemota organoCompetente = (UnidadAdministrativaRemota) session.load(UnidadAdministrativaRemota.class, tramite.getOrganCompetent().getId());
        		organoCompetente.getTramites().remove(tramite);
    		}
    		
    		procRemoto.removeTramite(tramite);

    		if(tramite instanceof TramiteRemoto){
                AdministracionRemota admin = ((TramiteRemoto)tramite).getAdministracionRemota();
                if(admin!=null)
                	admin.removeTramiteRemoto((TramiteRemoto)tramite);
            }
            session.delete(tramite);
            session.flush();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
	/**
	 * Borra un trámite Remoto segun su idExterno
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarTramiteRemoto(final String idRemoto ,final Long idExt) {

		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			Query query = session.createQuery("from TramiteRemoto as tr where tr.idExterno="+idExt+" and tr.administracionRemota.id="+ admin.getId());
			TramiteRemoto tramiteRemoto = (TramiteRemoto)query.uniqueResult();

			if(tramiteRemoto!=null){
				Hibernate.initialize(tramiteRemoto.getProcedimiento());
				ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, tramiteRemoto.getProcedimiento().getId());
	    		procRemoto.removeTramite(tramiteRemoto);
				admin.removeTramiteRemoto(tramiteRemoto);
		 		if( tramiteRemoto.getOrganCompetent()!=null){
	        		UnidadAdministrativaRemota organoCompetente = (UnidadAdministrativaRemota) session.load(UnidadAdministrativaRemota.class, tramiteRemoto.getOrganCompetent().getId());
	        		organoCompetente.getTramites().remove(tramiteRemoto);
	    		}
				session.delete(tramiteRemoto);
				session.flush();
			}
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

    

    
}
