package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import org.ibit.rol.sac.model.*;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

/**
 * SessionBean para controlar si los usuarios tienen acceso o no.
 * 
 * @ejb.bean name="sac/persistence/AccesoManager" jndi-name="org.ibit.rol.sac.persistence.AccesoManager"
 *           type="Stateless" view-type="local" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class AccesoManagerEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede acceder a la unidad administrativa. El par?metro
	 * modificar inidica si el acceso ?s solo a efectos de publicar contenido relacionado o a modificar los datos de la
	 * unidad.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoUnidad(Long idUA, boolean modificar) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (UnidadAdministrativa) session
			        .get(UnidadAdministrativa.class, idUA), modificar);
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Si el cliente captura una SecurityException es que no tiene permiso.
	 * 
	 * @ejb.interface-method 
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 */
	public boolean tieneAccesoCrearUnidad() {
		return true;
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede mover una unidad administrativa desde una unidad padre
	 * actual a otra nueva unidad padre.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 */
	public boolean tieneAccesoMoverOrganigrama(final Long oldParent, final Long newParent) {
		Session session = getSession();
		try {
			final Usuario usuario = getUsuario(session);
			final UnidadAdministrativa oldUA = (UnidadAdministrativa) session
			        .get(UnidadAdministrativa.class, oldParent);
			final UnidadAdministrativa newUA = (UnidadAdministrativa) session
			        .get(UnidadAdministrativa.class, newParent);
			return tieneAcceso(usuario, oldUA, true) && tieneAcceso(usuario, newUA, true);
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede usar la secci?n.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoSeccion(Long idSec) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Seccion) session.get(Seccion.class, idSec));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el procedimiento.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoProcedimiento(Long idProc) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (ProcedimientoLocal) session.get(ProcedimientoLocal.class, idProc));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar la normativa.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoNormativa(Long idNorma) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Normativa) session.get(Normativa.class, idNorma));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar la ficha.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoFicha(Long idFicha) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Ficha) session.get(Ficha.class, idFicha));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar la relaci?n ficha - unidad.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoFichaUnidad(Long idFichaUA) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (FichaUA) session.get(FichaUA.class, idFichaUA));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el documento.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoDocumento(Long idDoc) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Documento) session.get(Documento.class, idDoc));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el tramite.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoTramite(Long idTram) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Tramite) session.get(Tramite.class, idTram));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el formulario.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoFormulario(Long idForm) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Formulario) session.get(Formulario.class, idForm));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el edificio.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoEdificio(Long idEdi) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Edificio) session.get(Edificio.class, idEdi));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el personal.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean tieneAccesoPersonal(Long idPers) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Personal) session.get(Personal.class, idPers));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Devuelve <code>true</code> si y solo si el usuario puede modificar el comentario.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper},${role.info}"
	 */
	public boolean tieneAccesoComentario(Long idComentario) {
		Session session = getSession();
		try {
			return tieneAcceso(getUsuario(session), (Comentario) session.get(Comentario.class, idComentario));
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

}
