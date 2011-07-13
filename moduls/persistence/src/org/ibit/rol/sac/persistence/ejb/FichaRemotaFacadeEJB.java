package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Session;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Hibernate;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.FichaUATransferible;

import org.ibit.rol.sac.persistence.util.RemotoUtils;
import org.ibit.rol.sac.persistence.util.DateUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * SessionBean para mantener y consultar Fichas Remotas(PORMAD)
 * 
 * @ejb.bean name="sac/persistence/FichaRemotaFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.FichaRemotaFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class FichaRemotaFacadeEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}
	
	/**
	 * Crea o actualiza una FichaRemota y genera la relacion con la UA
	 * @throws HibernateException 
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Long grabarFichaRemota(final String idRemoto,final FichaRemota fichaRemota,
			final FichaUATransferible[] fichasUAT, final String[] ceMaterias,
			final String[] ceHechos){
		final Session session = getSession();
		try {
			AdministracionRemota adminRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			
			if(adminRemota==null)
	    		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			
			fichaRemota.setAdministracionRemota(adminRemota);
			
			if (fichaRemota.getId() != null) {
				session.update(fichaRemota);
			} else {
				session.save(fichaRemota);
				adminRemota.addFichaRemota(fichaRemota);
				fichaRemota.setAdministracionRemota(adminRemota);
			}
			
			final Set<Materia> materias = RemotoUtils.recogerMateriasCE(
					session, ceMaterias);
			if (materias != null) {
				
				fichaRemota.setMaterias(materias);
			} else {
				fichaRemota.setMaterias(Collections.EMPTY_SET);
			}
	
			final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session,
					ceHechos);
			if (hechos != null) {
				fichaRemota.setHechosVitales(hechos);
			} else {
				fichaRemota.setHechosVitales(Collections.EMPTY_SET);
			}
	
			// A continuacion miro si he de crear la FichaUA
			if (fichasUAT != null && fichasUAT.length > 0) {
				for (final FichaUATransferible fichaUAT : fichasUAT) {
					final Seccion seccion = RemotoUtils.recogerSeccionCE(
							session, fichaUAT.getCodigoEstandarSeccion());
					final UnidadAdministrativa ua = RemotoUtils.recogerUnidad(
							session, fichaUAT.getIdUnidadAdministrativa(),
							adminRemota.getId());
					if (seccion != null && ua != null) {
						crearFichaUA(session, fichaRemota, ua.getId(), seccion
								.getId());
					}
				}
			}
		
			session.flush();
			
			return fichaRemota.getId();
		}catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Crea o actualiza una FichaRemota y genera la relacion con la UA
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Long grabarFichaRemota(final FichaRemota fichaRemota,
			final Long idUnidad, final Long idSeccion,
			final String[] ceMaterias, final String[] ceHechos) {
		final Session session = getSession();
		try {
			final Set<Materia> materias = RemotoUtils.recogerMateriasCE(
					session, ceMaterias);
			if (materias != null) {
				fichaRemota.setMaterias(materias);
			} else {
				fichaRemota.setMaterias(Collections.EMPTY_SET);
			}
			

			final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session,
					ceHechos);
			if (hechos != null) {
				fichaRemota.setHechosVitales(hechos);
			} else {
				fichaRemota.setHechosVitales(Collections.EMPTY_SET);
			}
			
			AdministracionRemota adminRemota = fichaRemota
					.getAdministracionRemota();

			if (fichaRemota.getAdministracionRemota() != null) {
				adminRemota = (AdministracionRemota) session.load(
						AdministracionRemota.class, fichaRemota
								.getAdministracionRemota().getId());
			}
			
			if(adminRemota==null)
	    		throw new EJBException("No existe ninguna Administracion Remota asociada");

			if (fichaRemota.getId() != null) {
				session.update(fichaRemota);
			} else {
				session.save(fichaRemota);
				adminRemota.addFichaRemota(fichaRemota);
				fichaRemota.setAdministracionRemota(adminRemota);
			}

			// A continuacion miro si he de crear la FichaUA
			crearFichaUA(session, fichaRemota, idUnidad, idSeccion);
			session.flush();
			return fichaRemota.getId();
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	private void crearFichaUA(final Session session,
			final FichaRemota fichaRemota, final Long idUnidad,
			final Long idSeccion) throws HibernateException {
		if (idSeccion != null) {
			boolean crearFichaUA = fichaRemota.getId() == null;

			final UnidadAdministrativaRemota unidad = (UnidadAdministrativaRemota) session
					.load(UnidadAdministrativaRemota.class, idUnidad);
			final Seccion seccion = (Seccion) session.load(Seccion.class,
					idSeccion);

			// Si la ficha existia miro si la FichaUA ya estaba creada. si lo
			// esta, no se creara
			if (!crearFichaUA) {
				crearFichaUA = RemotoUtils.recogerFichaUA(session, idUnidad,
						fichaRemota.getId(), idSeccion) == null;
			}

			// Si la ficha o la realcion no existian la creo
			if (crearFichaUA) {
				final FichaUA fichaUA = new FichaUA();

				unidad.addFichaUA(fichaUA);
				seccion.addFichaUA(fichaUA);

				if (fichaRemota.getFichasua() == null) {
					fichaRemota.setFichasua(new HashSet<FichaUA>());
				}

				fichaRemota.addFichaUA(fichaUA);
			}
		}
	}

	/**
	 * Obtiene una Ficha Remota segun su idExterno y el IdRemoto actualmente
	 * de la AdministracionRemota
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FichaRemota obtenerFichaRemota(final String idRemoto, final Long idExtFicha) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			
			if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
        	
			return RemotoUtils.recogerFicha(session, idExtFicha, admin.getId());
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene una Ficha Remota segun su idExterno y su AdministracionRemota
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public FichaRemota obtenerFichaRemota(final Long idExtFicha,
			final Long idAdmin) {
		final Session session = getSession();
		try {
			return RemotoUtils.recogerFicha(session, idExtFicha, idAdmin);
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista las fichas remotas
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Set<FichaRemota> listarFichasRemotas(final String idRemoto) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			
			if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			
			return admin.getFichasRemotas();
		} catch (final HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

	/**
	 * Borra una Ficha Remota segun su idExterno
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarFichaRemota(final String idRemoto ,final Long idExt) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	
			if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			
			final FichaRemota ficha = RemotoUtils.recogerFicha(session, idExt, admin.getId());
			
			if(ficha!=null){
				
	            // Debemos anular en todos los registros del historico de esa ficha, la materia y la ua.  
	            Query query1 = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :ficha_id ");
	            query1.setParameter("ficha_id", ficha.getId(), Hibernate.LONG);
	            query1.setCacheable(true);
	            List hfichas = query1.list();
	            HistoricoFicha historico;
	            for (int j=0;j<hfichas.size();j++) {
	            	historico = (HistoricoFicha)hfichas.get(j);
	            	historico.setFicha(null);
	            	historico.setMateria(null);
	            	historico.setUa(null);
	            }
				
				//final Historico historico = getHistorico(session, ficha);
				//((HistoricoFicha) historico).setFicha(null);
	
				admin.removeFichaRemota(ficha);
	
				for (final Materia mat : ficha.getMaterias()) {
					mat.getFichas().remove(ficha);
				}
				for (final HechoVital hecho : ficha.getHechosVitales()) {
					hecho.getFichas().remove(ficha);
				}
				for (final FichaUA fichaUA : ficha.getFichasua()) {
					fichaUA.getSeccion().removeFichaUA(fichaUA);
					final UnidadAdministrativa ua = fichaUA
							.getUnidadAdministrativa();
					if (ua != null) {
						ua.removeFichaUA(fichaUA);
					}
				}
	
				session.delete(ficha);
				session.flush();
			}
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Borra una FichaUA
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarFichaUA(final String idRemoto ,final Long idFicha, final Long idUA,
			final String codEst) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	
        	if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			final Seccion seccion = RemotoUtils.recogerSeccionCE(session,
					codEst);
			final FichaRemota fichaRemota = RemotoUtils.recogerFicha(session,
					idFicha, admin.getId());
			final UnidadAdministrativaRemota uaRemota = RemotoUtils
					.recogerUnidad(session, idUA, admin.getId());

			if (seccion != null && uaRemota != null && fichaRemota != null) {
				final FichaUA fichaUA = RemotoUtils.recogerFichaUA(session,
						uaRemota.getId(), fichaRemota.getId(), seccion.getId());
                if(fichaUA!=null){
				    seccion.removeFichaUA(fichaUA);
				    uaRemota.removeFichaUA(fichaUA);
				    fichaRemota.removeFichaUA(fichaUA);
				    session.delete(fichaUA);
                }
			}
			session.flush();
		} catch (final HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

    /**  (Pensado para los banners del principio)
     * Obtiene las fichas(remotas) de una Unidad Administrativa relacionada con la seccion
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaRemota> listarFichasRemotasSeccionUA(Long ua_id, String codEstSecc) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from FichaRemota as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and fua.seccion.codigoEstandard = :codEstSecc  " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("codEstSecc", codEstSecc, Hibernate.STRING);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**  
     * Obtiene las fichas(remotas) de una Unidad Administrativa relacionada con la seccion y la materia que son publicas
     * y no estan caducadas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaRemota> listarFichasRemotasSeccionUAMateria(Long ua_id, String codEstSecc, String codEstMat) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from FichaRemota as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and fua.seccion.codigoEstandard = :codEstSecc  " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "and :codEstMateria in (select mat.codigoEstandar from f.materias as mat ) " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("codEstSecc", codEstSecc, Hibernate.STRING);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            query.setParameter("codEstMateria", codEstMat);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

     /**
     * Obtiene las fichas(remotas) de una Unidad Administrativa relacionada con la seccion y el hecho vital que son publicas
     * y no estan caducadas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaRemota> listarFichasRemotasSeccionUAHechoVital(Long ua_id, String codEstSecc, String codEstHV) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from FichaRemota as f, fua in f.fichasua " +
                    "where fua.unidadAdministrativa.id = :ua_id " +
                    "and fua.seccion.codigoEstandard = :codEstSecc  " +
                    "and f.validacion = :validacion " +
                    "and ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " +
                    "and ( f.fechaPublicacion is null or f.fechaPublicacion <= :fechap ) " +
                    "and :codEstHV in (select hv.codigoEstandar from f.hechosVitales as hv) " +
                    "order by fua.orden");
            query.setParameter("ua_id", ua_id, Hibernate.LONG);
            query.setParameter("codEstSecc", codEstSecc, Hibernate.STRING);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.HoyHora());
            query.setParameter("fechap", DateUtils.HoyHora());
            query.setParameter("codEstHV", codEstHV);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de fichas remotas mas recientes
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaRemota> listarFichasRecientes(int length,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from FichaRemota as f " +
                    "where f.validacion = :validacion " +
                    "and f.fechaActualizacion is not null " +
                    (!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
                    "order by f.fechaActualizacion desc");
            query.setParameter("validacion", Validacion.PUBLICA);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setMaxResults(length);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de fichas mas comentadas
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaRemota> listarFichasMasComentadas(int length,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from FichaRemota as f, ComentarioFicha as cf " +
                    "where cf.ficha = f " +
                    "and f.validacion = :validacion " +
                    (!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
                     // "group by" ha de posar explicitament tots els camps, veure:
                     // http://opensource.atlassian.com/projects/hibernate/browse/HB-1003
                    "group by f.id, f.idExterno, f.urlRemota, f.administracionRemota, f.fechaCaducidad, " +
                            "f.icono, f.imagen, f.baner, f.validacion, f.info, f.fechaPublicacion, f.fechaActualizacion, f.urlVideo, f.urlForo, f.foro_tema, f.responsable  " +
                    "order by count(cf) desc");
            query.setParameter("validacion", Validacion.PUBLICA);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setMaxResults(length);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de fichas remotas mas visitadas.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public List<FichaRemota> listarFichasMasVisitadas(int length,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select f from FichaRemota f, HistoricoFicha hf, Estadistica e " +
                    "where hf.ficha = f and e.historico = hf " +
                    "and f.validacion = :validacion " +
                    (!caducados ? "AND ( f.fechaCaducidad is null or f.fechaCaducidad >= :fecha ) " : "") +
                    // "group by" ha de posar explicitament tots els camps, veure:
                    // http://opensource.atlassian.com/projects/hibernate/browse/HB-1003
                    "group by f.id, f.idExterno, f.urlRemota, f.administracionRemota, f.fechaCaducidad, " +
                            "f.icono, f.imagen, f.baner, f.validacion, f.info, f.fechaPublicacion, f.fechaActualizacion, f.urlVideo, f.urlForo, f.foro_tema, f.responsable " +
                    "order by sum(e.contador) desc");
            query.setParameter("validacion", Validacion.PUBLICA);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setMaxResults(length);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

}
