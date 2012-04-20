package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.TraduccionDocumento;

public class DocumentoDTO implements Serializable, Comparable<DocumentoDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
    private String descripcion;
    private ArchivoDTO archivo;

    public DocumentoDTO( Documento documento, String idioma )
	{
		this.id = documento.getId();

		TraduccionDocumento traduccionDocumento = ( TraduccionDocumento )documento.getTraduccion( idioma );
		if (traduccionDocumento == null) {
			traduccionDocumento = ( TraduccionDocumento )documento.getTraduccion( );
		}
		rellenarCampos( idioma, traduccionDocumento );
	}

    public DocumentoDTO( DocumentTramit documento, String idioma )
	{
		this.id = documento.getId();

		TraduccionDocumento traduccionDocumento = ( TraduccionDocumento )documento.getTraduccion( idioma );
		if (traduccionDocumento == null) {
			traduccionDocumento = ( TraduccionDocumento )documento.getTraduccion( );
		}
		rellenarCampos( idioma, traduccionDocumento );
	}

	private void rellenarCampos( String idioma, TraduccionDocumento traduccionDocumento ) 
	{
		if( traduccionDocumento.getArchivo() != null )
		{
			this.archivo = new ArchivoDTO( traduccionDocumento.getArchivo(), idioma );
		}
		this.titulo = traduccionDocumento.getTitulo();
		this.descripcion = traduccionDocumento.getDescripcion();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArchivoDTO getArchivo() {
		return archivo;
	}

	public void setArchivo(ArchivoDTO archivo) {
		this.archivo = archivo;
	}

	public int compareTo(DocumentoDTO o) {
		return this.titulo.compareToIgnoreCase( o.getTitulo() );
	}
}
