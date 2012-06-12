package es.caib.rolsac.api.v1;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;

public class TramiteDTO implements Serializable, Comparable<TramiteDTO>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private UnidadAdministrativaDTO organCompetent;

	private String nombre;
    private String descripcion;
    private String documentacion;
    private String requisits;
    private String plazos;
    private String lugar;
    private Set<TaxaDTO> taxes;  //set of taxa
    private Set<DocumentoDTO> documentInformatiu;
    private Set<DocumentoDTO> formularios;

	public TramiteDTO( Tramite tramite, String idioma )
	{
		if( tramite.getOrganCompetent() != null )
		{
			//No necesitamos los hijos del organo competente
			this.organCompetent = new UnidadAdministrativaDTO( tramite.getOrganCompetent(), idioma, false );
		}
		TraduccionTramite traduccionTramite = ( TraduccionTramite )tramite.getTraduccion( idioma );

		this.taxes = new TreeSet<TaxaDTO>();
		if( tramite.getTaxes() != null )
		{
			for( Taxa t : tramite.getTaxes() )
			{
				this.taxes.add( new TaxaDTO( t, idioma ) );
			}
		}
		this.nombre = traduccionTramite.getNombre();
		this.descripcion = traduccionTramite.getDescripcion();
		this.documentacion = traduccionTramite.getDocumentacion();
		this.requisits = traduccionTramite.getRequisits();
		this.plazos = traduccionTramite.getPlazos();
		this.lugar = traduccionTramite.getLugar();

		this.documentInformatiu = new TreeSet<DocumentoDTO>();
		if( tramite.getDocsInformatius() != null )
		{
			for( DocumentTramit d : tramite.getDocsInformatius() )
			{
				this.documentInformatiu.add( new DocumentoDTO( d, idioma ) );
			}
		}

		this.formularios = new TreeSet<DocumentoDTO>();
		if( tramite.getFormularios() != null )
		{
			for( DocumentTramit f : tramite.getFormularios() )
			{
				this.formularios.add( new DocumentoDTO( f, idioma ) );
			}
		}
	}

	public UnidadAdministrativaDTO getOrganCompetent()
	{
		return this.organCompetent;
	}


	public void setOrganCompetent( UnidadAdministrativaDTO organCompetent )
	{
		this.organCompetent = organCompetent;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDocumentacion() {
		return this.documentacion;
	}

	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}

	public String getRequisits() {
		return this.requisits;
	}

	public void setRequisits(String requisits) {
		this.requisits = requisits;
	}

	public String getPlazos() {
		return this.plazos;
	}

	public void setPlazos(String plazos) {
		this.plazos = plazos;
	}

	public int compareTo(TramiteDTO o) {
		return this.nombre.compareToIgnoreCase( o.getNombre() );
	}

	public Set<TaxaDTO> getTaxes() {
		return this.taxes;
	}

	public void setTaxes(Set<TaxaDTO> taxes) {
		this.taxes = taxes;
	}

	public Set<DocumentoDTO> getDocumentInformatiu() {
		return this.documentInformatiu;
	}

	public void setDocumentInformatiu(Set<DocumentoDTO> documentInformatiu) {
		this.documentInformatiu = documentInformatiu;
	}

	public Set<DocumentoDTO> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(Set<DocumentoDTO> formularios) {
		this.formularios = formularios;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
}
