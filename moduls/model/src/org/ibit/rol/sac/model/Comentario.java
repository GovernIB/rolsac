package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Bean que contiene los datos básicos de un comentario
 * a una ficha o procedimiento.(PORINF)
 *
 * @author areus
 *
 */
public class Comentario implements ValueObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
    private String autor;
    private String motivo;
    private Usuario usuario;
    private Date fecha;
    private String contenido;
    private boolean subsanado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

	public boolean isSubsanado() {
		return subsanado;
	}

	public void setSubsanado(boolean subsanado) {
		this.subsanado = subsanado;
	}
    
    
}
