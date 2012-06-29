package es.caib.rolsac.api.v2.enllac;

import java.io.Serializable;

public class EnllacDTO implements Serializable {

    private static final long serialVersionUID = -2599255082804121567L;

    protected Long id;
    private Long ficha;
    private Long procedimiento;
    private long orden;

    // traducciones
    protected String titulo;
    protected String enlace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFicha() {
        return ficha;
    }

    public void setFicha(Long ficha) {
        this.ficha = ficha;
    }

    public Long getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Long procedimiento) {
        this.procedimiento = procedimiento;
    }

    public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

}
