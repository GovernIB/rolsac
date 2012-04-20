package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;

public class NormativaDTO implements Serializable, Comparable<NormativaDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private TipoDTO tipo;

    private String titulo;
    private String enlace;
    private ArchivoDTO archivo;

    public NormativaDTO( Normativa normativa, String idioma )
    {
    	TraduccionNormativa traduccionNormativa = ( TraduccionNormativa )normativa.getTraduccion( idioma );
    	if (traduccionNormativa == null) {
    		traduccionNormativa = (TraduccionNormativa)normativa.getTraduccion();
    	}
    	this.id = normativa.getId();

    	this.tipo = new TipoDTO( normativa.getTipo(), idioma );

    	if( traduccionNormativa.getArchivo() != null )
    	{
    		this.archivo = new ArchivoDTO( traduccionNormativa.getArchivo(), idioma );
    	}
    	this.titulo = traduccionNormativa.getTitulo();
    	this.enlace = traduccionNormativa.getEnlace();
    }

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}

    public TipoDTO getTipo() {
		return tipo;
	}

    public void setTipo(TipoDTO tipo) {
		this.tipo = tipo;
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

	public int compareTo(NormativaDTO o) {
		return this.titulo.compareToIgnoreCase( o.getTitulo() );
	}

	public ArchivoDTO getArchivo() {
		return archivo;
	}

	public void setArchivo(ArchivoDTO archivo) {
		this.archivo = archivo;
	}
}
