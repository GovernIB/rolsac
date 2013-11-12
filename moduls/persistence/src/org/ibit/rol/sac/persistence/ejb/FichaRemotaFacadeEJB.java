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
public abstract class FichaRemotaFacadeEJB extends HibernateEJB
{
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException
	{
		super.ejbCreate();
	}
	
	
	/**
	 * Crea o actualiza una FichaRemota y genera la relacion con la UA
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idRemoto	Identificador de la ficha remota.
	 * @param fichaRemota	Indica la ficha remota a guardar.
	 * @param fichasUAT	Listado de fichas asociadas a la unidad administrativa transferible.
	 * @param codEstandarMaterias Listado de códigos estándar de materias asignadas.
	 * @param codEstandarHechosVitales Listado de códigos estándar de hechos vitales asignados.
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public Long grabarFichaRemota(final String idRemoto,final FichaRemota fichaRemota, final FichaUATransferible[] fichasUAT, final String[] codEstandarMaterias, final String[] codEstandarHechosVitales)
	{
		final Session session = getSession();
		try {
			AdministracionRemota adminRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if (adminRemota == null) {
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			}
			
			fichaRemota.setAdministracionRemota(adminRemota);
			if (fichaRemota.getId() != null) {
				session.update(fichaRemota);
			} else {
				session.save(fichaRemota);
				adminRemota.addFichaRemota(fichaRemota);
				fichaRemota.setAdministracionRemota(adminRemota);
			}
			
			final Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, codEstandarMaterias);
			if (materias != null) {
				fichaRemota.setMaterias(materias);
			} else {
				fichaRemota.setMaterias(Collections.EMPTY_SET);
			}
			
			final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session, codEstandarHechosVitales );
			if (hechos != null) {
				fichaRemota.setHechosVitales(hechos);
			} else {
				fichaRemota.setHechosVitales(Collections.EMPTY_SET);
			}
			
			// A continuacion miro si he de crear la FichaUA
			if (fichasUAT != null && fichasUAT.length > 0) {
				for (final FichaUATransferible fichaUAT : fichasUAT) {
					final Seccion seccion = RemotoUtils.recogerSeccionCE(session, fichaUAT.getCodigoEstandarSeccion());
					final UnidadAdministrativa ua = RemotoUtils.recogerUnidad(session, fichaUAT.getIdUnidadAdministrativa(), adminRemota.getId());
					if (seccion != null && ua != null) {
						crearFichaUA(session, fichaRemota, ua.getId(), seccion.getId());
					}
				}
			}
			session.flush();
			return fichaRemota.getId();
			
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza una FichaRemota y genera la relacion con la unidad administrativa.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param fichaRemota	Indica la ficha remota a guardar.
	 * @param idUnidad Identificador de la unidad administrativa a la que está asignada la ficha remnota.
	 * @param idSeccion	Identificador de la sección asignada a la ficha remota.
	 * @param codEstandarMaterias Listado de códigos estándar de materias asignadas a la ficha.
	 * @param codEstandarHechosVitales Listado de códigos estándar de hechos vitales asignados a la ficha.
	 * @return Devuelve el identificador de la ficha guardada.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarFichaRemota(final FichaRemota fichaRemota, final Long idUnidad, final Long idSeccion, final String[] codEstandarMaterias, final String[] codEstandarHechosVitales)
	{
		final Session session = getSession();
		try {
			final Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, codEstandarMaterias);
			if (materias != null) {
				fichaRemota.setMaterias(materias);
			} else {
				fichaRemota.setMaterias(Collections.EMPTY_SET);
			}
			
			final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session, codEstandarHechosVitales);
			if (hechos != null) {
				fichaRemota.setHechosVitales(hechos);
			} else {
				fichaRemota.setHechosVitales(Collections.EMPTY_SET);
			}
			
			AdministracionRemota adminRemota = fichaRemota.getAdministracionRemota();
			if (fichaRemota.getAdministracionRemota() != null) {
				adminRemota = (AdministracionRemota) session.load(AdministracionRemota.class, fichaRemota.getAdministracionRemota().getId());
			}
			
			if (adminRemota == null) {
				throw new EJBException("No existe ninguna Administracion Remota asociada");
			}
			
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
	
	
	/**
	 * Método que se encarga de crear la ficha de una unidad administrativa.
	 * 
	 * @param session	Indica la sesión de hibernate.
	 * @param fichaRemota	Indica la ficha remota a guardar.
	 * @param idUnidad	Identificador de la unidad administrativa asignada a la ficha.
	 * @param idSeccion	Identificador de la sección asignada a la ficha.
	 * @throws HibernateException
	 */
	private void crearFichaUA(final Session session, final FichaRemota fichaRemota, final Long idUnidad, final Long idSeccion) throws HibernateException
	{
		if (idSeccion != null) {
			boolean crearFichaUA = (fichaRemota.getId() == null);
			final UnidadAdministrativaRemota unidad = (UnidadAdministrativaRemota) session.load(UnidadAdministrativaRemota.class, idUnidad);
			final Seccion seccion = (Seccion) session.load(Seccion.class, idSeccion);
			// Si la ficha existía miro si la FichaUA ya estaba creada. si lo
			// está, no se creará
			if (!crearFichaUA) {
				crearFichaUA = ( RemotoUtils.recogerFichaUA(session, idUnidad, fichaRemota.getId(), idSeccion) == null);
			}
			// Si la ficha o la realcion no existía la creo
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
	 * Obtiene una Ficha Remota segun su idExterno y el IdRemoto actualmente de la AdministracionRemota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idRemoto	Identificador de la ficha remota.
	 * @param idExtFicha	Identificador externo de la ficha.
	 * @return	Devuelve <code>FichaRemota</code> solicitada.
	 */
	public FichaRemota obtenerFichaRemota(final String idRemoto, final Long idExtFicha)
	{
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if (admin == null) {
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			}
			
			return RemotoUtils.recogerFicha(session, idExtFicha, admin.getId());
			
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene una Ficha Remota segun su idExterno y su AdministracionRemota.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param	idExtFicha	Identificador externo de la ficha.
	 * @param	idAdmin	Identificador de la administración remota.
	 * @return	Devuelve <code>FichaRemota</code> solicitada.
	 */
	public FichaRemota obtenerFichaRemota(final Long idExtFicha, final Long idAdmin)
	{
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
	 * Borra una Ficha Remota segun su idExterno.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param	idRemoto	Identificador de la ficha remota.
	 * @param	idExt	Identificador externo de la ficha.
	 */
	public void borrarFichaRemota(final String idRemoto ,final Long idExt)
	{
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if (admin == null) {
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			}
			
			final FichaRemota ficha = RemotoUtils.recogerFicha(session, idExt, admin.getId());
			if (ficha != null) {
				// Debemos anular en todos los registros del historico de esa ficha, la materia y la ua.
				Query consultaHistorico = session.createQuery("from HistoricoFicha as hficha where hficha.ficha.id = :ficha_id ");
	            consultaHistorico.setParameter("ficha_id", ficha.getId(), Hibernate.LONG);
	            consultaHistorico.setCacheable(true);
	            
	            List hfichas = consultaHistorico.list();
	            HistoricoFicha historico;
	            for (int j = 0; j < hfichas.size(); j++) {
	            	historico = (HistoricoFicha) hfichas.get(j);
	            	historico.setFicha(null);
	            	historico.setMateria(null);
	            	historico.setUa(null);	            	
	            }
	            admin.removeFichaRemota(ficha);
	            
	            for (final Materia mat : ficha.getMaterias()) {
	            	mat.getFichas().remove(ficha);
	            }
	            for (final HechoVital hecho : ficha.getHechosVitales()) {
	            	hecho.getFichas().remove(ficha);
	            }
	            for (final FichaUA fichaUA : ficha.getFichasua()) {
	            	fichaUA.getSeccion().removeFichaUA(fichaUA);
	            	final UnidadAdministrativa ua = fichaUA.getUnidadAdministrativa();
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
	 * @param	idRemoto	Identificador de la ficha remota.
	 * @param	idFicha	Identificador de la ficha.
	 * @param	idUA	Identificador de la unidad administrativa.
	 * @param	codEstandar	Código estándar de la sección asociada.
	 */
	public void borrarFichaUA(final String idRemoto ,final Long idFicha, final Long idUA, final String codEstandar)
	{
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if (admin == null) {
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
			}
			
			final Seccion seccion = RemotoUtils.recogerSeccionCE(session, codEstandar);
			final FichaRemota fichaRemota = RemotoUtils.recogerFicha(session, idFicha, admin.getId());
			final UnidadAdministrativaRemota uaRemota = RemotoUtils.recogerUnidad(session, idUA, admin.getId());
			if (seccion != null && uaRemota != null && fichaRemota != null) {
				final FichaUA fichaUA = RemotoUtils.recogerFichaUA(session, uaRemota.getId(), fichaRemota.getId(), seccion.getId());
				if (fichaUA != null) {
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
	
}
