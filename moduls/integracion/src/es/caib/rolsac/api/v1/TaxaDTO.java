package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTaxa;

public class TaxaDTO implements Serializable, Comparable<TaxaDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    private String descripcio;
    private String formaPagament;

	public TaxaDTO( Taxa taxa, String idioma )
	{
		this.id = taxa.getId();
		TraduccionTaxa traduccionTaxa = ( TraduccionTaxa )taxa.getTraduccion( idioma );
    	if (traduccionTaxa == null) {
    		traduccionTaxa = (TraduccionTaxa)taxa.getTraduccion();
    	}
		this.descripcio = traduccionTaxa.getDescripcio();
		this.formaPagament = traduccionTaxa.getFormaPagament();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getFormaPagament() {
		return formaPagament;
	}

	public void setFormaPagament(String formaPagament) {
		this.formaPagament = formaPagament;
	}

	public int compareTo( TaxaDTO o ) 
	{
		return this.formaPagament.compareToIgnoreCase( o.formaPagament );
	}
	
}
