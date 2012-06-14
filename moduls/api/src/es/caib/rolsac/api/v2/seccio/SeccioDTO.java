package es.caib.rolsac.api.v2.seccio;


public class SeccioDTO {

    protected long id;
    private Long padre;
    private String codigoEstandard;
    private String perfil;
    private int orden;

    // traducciones
    private String nombre;
    private String descripcion;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Long getPadre() {
        return padre;
    }
    public void setPadre(Long padre) {
        this.padre = padre;
    }
    public String getCodigoEstandard() {
        return codigoEstandard;
    }
    public void setCodigoEstandard(String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public int getOrden() {
        return orden;
    }
    public void setOrden(int orden) {
        this.orden = orden;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SeccioDTO other = (SeccioDTO) obj;
        if (id != other.id)
            return false;
        return true;
    }    
}
