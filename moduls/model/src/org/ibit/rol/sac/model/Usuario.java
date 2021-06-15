package org.ibit.rol.sac.model;

import java.util.Iterator;
import java.util.Set;

public class Usuario implements ValueObject {

	private static final long serialVersionUID = 1L;

	public static String PERMISO_MODIFICACION_NORMATIVA = "P_MOD_NOR";
	public static String PERMISO_GESTION_COMUNES = "P_MOD_COM";
	public static String PERMISO_PUBLICAR_INVENTARIO = "P_PUB_INV";
	public static String PERMISO_SEPARADOR = ",";

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(final String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(final String perfil) {
		this.perfil = perfil;
	}

	public Set getUnidadesAdministrativas() {
		return unidadesAdministrativas;
	}

	public void setUnidadesAdministrativas(final Set unidadesAdministrativas) {
		this.unidadesAdministrativas = unidadesAdministrativas;
	}

	public boolean hasAccess(final UnidadAdministrativa ua) {
		return unidadesAdministrativas.contains(ua) || (!ua.isRaiz() && hasAccess(ua.getPadre()));
	}

	public boolean hasAccess(final PerfilGestor perfilGestor) {
		return perfilsGestor.contains(perfilGestor);
	}

	public boolean hasRaizAccess() {
		for (final Iterator iter = unidadesAdministrativas.iterator(); iter.hasNext();) {
			final UnidadAdministrativa ua = (UnidadAdministrativa) iter.next();
			if (ua.isRaiz()) {
				return true;
			}
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Set<PerfilGestor> getPerfilsGestor() {
		return perfilsGestor;
	}

	public void setPerfilsGestor(final Set perfilsGestor) {
		this.perfilsGestor = perfilsGestor;
	}

	public void addPerfilsGestor(final PerfilGestor perfilGestor) {
		this.perfilsGestor.add(perfilGestor);
	}

	public void removePerfilsGestor(final PerfilGestor perfilGestor) {
		this.perfilsGestor.remove(perfilGestor);
	}

	private Long id;
	private String username;
	private String password;
	private String nombre;
	private String observaciones;
	private String perfil;
	private Set unidadesAdministrativas;
	private String email;
	private Set perfilsGestor;
	// permisos separados por coma(PERMISO_SEPARADOR), por si desean anyadir mas
	// Contiene los permisos que tiene el usuario
	private String permisos;

	/**
	 * Recupera true si un usuario tiene un permiso determinado definido a nivel de
	 * aplicacion
	 *
	 * @param permiso
	 * @return true si tiene el permiso
	 */
	public boolean tienePermiso(final String permiso) {
		return tienePermiso(this.permisos, permiso);
	}

	public void setPermiso(final String permiso, final boolean tienePermiso) {
		this.permisos = setPermiso(this.permisos, permiso, tienePermiso);
	}

	public static boolean tienePermiso(final String permisos, final String permiso) {
		return permisos != null && permisos.contains(permiso);
	}

	/**
	 * actualiza el permiso indicado en la lista de permisos facilitada.
	 *
	 * @param permisos
	 *            lista de permisos separada por comas
	 * @param permiso
	 * @param tienePermiso
	 */
	public static String setPermiso(final String permisos, final String permiso, final boolean tienePermiso) {
		// usamos un separador para que quede claro que permisos existen si se consulta
		// directamente la BBDD
		String[] lperm;
		String res = "";
		if (tienePermiso(permisos, permiso)) {
			if (!tienePermiso) {
				// tiene el permiso y se lo hemos de quitar (this.permisos no puede ser nulo)
				lperm = permisos.split(PERMISO_SEPARADOR);
				boolean primero = true;
				for (final String p : lperm) {
					if (primero) {
						primero = false;
					} else {
						res += PERMISO_SEPARADOR;
					}
					if (!p.equals(permiso)) {
						res += p;
					}
				}
			} else {
				res = permisos;
			}
		} else {
			res = permisos;
			if (tienePermiso) {
				// hay que anyadir el permiso
				if (permisos != null && permisos.length() > 0) {
					res += PERMISO_SEPARADOR + permiso;
				} else {
					res = permiso;
				}
			} // si no hay que anyadirlo no hacemos nada
		}
		return res;
	}

	/**
	 * @return the permisos
	 */
	public String getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos
	 *            the permisos to set
	 */
	public void setPermisos(final String permisos) {
		this.permisos = permisos;
	}
}
