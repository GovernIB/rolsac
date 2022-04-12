package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.PlataformaFacade;
import org.ibit.rol.sac.persistence.intf.PlataformaFacadeHome;
import org.ibit.rol.sac.persistence.util.PlataformaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para plataforma.
 */
public class PlataformaDelegateImpl extends PlataformaDelegate implements StatelessDelegate, PlataformaDelegateI {

	/** Serial Version UID. **/
	private static final long serialVersionUID = 1L;

	private Handle facadeHandle;

	private PlataformaFacade getFacade() throws RemoteException {
		return (PlataformaFacade) facadeHandle.getEJBObject();
	}

	protected PlataformaDelegateImpl() throws DelegateException {
		try {
			final PlataformaFacadeHome home = PlataformaFacadeUtil.getHome();
			final PlataformaFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (final NamingException e) {
			throw new DelegateException(e);
		} catch (final CreateException e) {
			throw new DelegateException(e);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** Reordenar plataforma. **/
	@Override
	public void reordenar(final Long id, final Integer nuevoOrden, final Integer ordenOld) throws DelegateException {
		try {
			getFacade().reordenar(id, nuevoOrden, ordenOld);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Grabar plataforma. **/
	@Override
	public Long grabarPlataforma(final Plataforma plataforma) throws DelegateException {

		try {
			return getFacade().grabarPlataforma(plataforma);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Obtener plataforma. **/
	@Override
	public Plataforma obtenerPlataforma(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerPlataforma(id);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Borrar plataforma. **/
	@Override
	public void borrarPlataforma(final Long id) throws DelegateException {
		try {
			getFacade().borrarPlataforma(id);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Plataforma con asociaciones proc/serv. **/
	@Override
	public boolean plataformaConAsociaciones(final Long idPlataforma) throws DelegateException {
		try {
			return getFacade().plataformaConAsociaciones(idPlataforma);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Buscar plataforma. **/
	@Override
	public ResultadoBusqueda buscadorListarPlataforma(final Map parametros, final int pagina, final int resultats,
			final boolean uaFilles, final boolean uaMeves) throws DelegateException {
		try {
			return getFacade().buscadorListarPlataforma(parametros, pagina, resultats, uaFilles, uaMeves);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Busca plataformas para el restapi. **/
	@Override
	public ResultadoBusqueda consultaPlataformas(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaPlataformas(filtro);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	/** Lista plataforma. **/
	@Override
	public List<Plataforma> listarPlataforma() throws DelegateException {
		try {
			return getFacade().listarPlataforma();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
