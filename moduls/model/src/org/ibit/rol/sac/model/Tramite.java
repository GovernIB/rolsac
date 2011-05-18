
package org.ibit.rol.sac.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Tramite extends Ordenable {

    public final static int INICIACION = 1;
    public final static int INSTRUCCION = 2;
    public final static int FINALIZACION = 3;
    

    //inst vars han de ser instanceof Objects pq el b 
    
    Long id;
    int fase;
    ProcedimientoLocal procedimiento;
    
    Long validacio;
    Date dataCaducitat;  //DATE
    Date dataPublicacio; //DATE 
    Date dataActualitzacio;  //DATE
    //private String idOrganCompetent;	//NUMBER(19)
    UnidadAdministrativa organCompetent;
    

    String	idTraTel;
    Integer versio;  //NUMBER(2)
    String urlExterna; //VARCHAR2(1024 CHAR)

    //campos para la ventanilla unica

    String codiVuds;  	 
    String descCodiVuds;

    public enum Operativa{CREA,MODIFICA,BORRA,CONSULTA};
    
    private Operativa operativa;

    transient boolean tramiteVudsValido=true;  //aquest camp no cal que sigui persistent.

    Set<DocumentTramit> docsInformatius = new HashSet<DocumentTramit>();	//set of documents
    Set<DocumentTramit> formularios = new HashSet<DocumentTramit>();	//set of documents
    Set<Taxa> taxes = new HashSet<Taxa>();  //set of taxa
    String dataActualitzacioVuds;  //String: "no enviat"  "data hora"


	public boolean isTramiteVudsValido() {
		return tramiteVudsValido;
	}

	public void setTramiteVudsValido(boolean tramiteVudsValido) {
		this.tramiteVudsValido = tramiteVudsValido;
	}

	public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public ProcedimientoLocal getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoLocal procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Set<DocumentTramit> getFormularios() {
        return formularios;
    }

    public void setFormularios(Set<DocumentTramit> formularios) {
        this.formularios = formularios;
    }

    public void addFormulario(DocumentTramit doc) {
		doc.setTramit(this);
		doc.setOrden((long)formularios.size()+1);
		formularios.add(doc);							
	}
    
    /**
     * @deprecated usar removeDocument
     * @param form
     */
    public void removeFormulario(Formulario form) {
        form.setTramite(null);
        formularios.remove(form);
    }

	public Date getDataCaducitat() {	
		return dataCaducitat;
	}

	public void setDataCaducitat(Date dataCaducitat) {
		this.dataCaducitat = dataCaducitat;
	}

	public Date getDataPublicacio() {
		return dataPublicacio;
	}

	public void setDataPublicacio(Date dataPublicacio) {
		this.dataPublicacio = dataPublicacio;
	}

	public Date getDataActualitzacio() {
		return dataActualitzacio;
	}

	public void setDataActualitzacio(Date dataActualitzacio) {
		this.dataActualitzacio = dataActualitzacio;
	}

	public UnidadAdministrativa getOrganCompetent() {
		return organCompetent;
	}

	public void setOrganCompetent(UnidadAdministrativa organCompetent) {
		this.organCompetent = organCompetent;
	}

	public Set<DocumentTramit> getDocsInformatius() {
		return docsInformatius;
	}

	public void setDocsInformatius(Set<DocumentTramit> docsInformatius) {
		this.docsInformatius = docsInformatius;
	}

	public void addDocInformatiu(DocumentTramit doc) {
		doc.setTramit(this);
		doc.setOrden((long)docsInformatius.size()+1);
		docsInformatius.add(doc);							
	}

	public void addDocument(DocumentTramit doc) {
		switch(doc.getTipus()) {
			case DocumentTramit.DOCINFORMATIU:
				addDocInformatiu(doc);					
				break;
			case DocumentTramit.FORMULARI:
				addFormulario(doc);
			break;
		}
	}

	
	
	/** 
	 * L'ordre es refà en el TramiteFacadebEJB
	 * @param doc
	 */
    public void removeDocument(Document doc) {
		switch(doc.getTipus()) {
		case DocumentTramit.DOCINFORMATIU: 
			docsInformatius.remove(doc);
			break;
		case DocumentTramit.FORMULARI: 
			formularios.remove(doc);
		break;
		}
    }

	
	public void addTaxa(Taxa taxa) {
		taxa.setTramit(this);
		taxes.add(taxa);
		taxa.setOrden((long)taxes.size());
	}


    public void removeTaxa(Taxa taxa) {
    		taxa.setTramit(null);
			taxes.remove(taxa);
    }

	
	public String getCodiVuds() {
		return codiVuds;
	}

	public void setCodiVuds(String codiVuds) {
		this.codiVuds = codiVuds;
	}

	public String getDescCodiVuds() {
		return descCodiVuds;
	}

	public void setDescCodiVuds(String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
	}

	public Integer getVersio() {
		return versio;
	}

	public void setVersio(Integer versio) {
		this.versio = versio;
	}

	public String getUrlExterna() {
		return urlExterna;
	}

	public void setUrlExterna(String urlExterna) {
		this.urlExterna = urlExterna;
	}

	public Operativa getOperativa() {
		return operativa;
	}

	public void setOperativa(Operativa operativa) {
		this.operativa = operativa;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValidacio() {
		return validacio;
	}

	public void setValidacio(Long validacio) {
		this.validacio = validacio;
	}

	public String getIdTraTel() {
		return idTraTel;
	}

	public void setIdTraTel(String idTramTele) {
		this.idTraTel = idTramTele;
	}

	public Set<Taxa> getTaxes() {
		return taxes;
	}

	public void setTaxes(Set<Taxa> taxes) {
		this.taxes = taxes;
	}

	public String getDataActualitzacioVuds() {
		return dataActualitzacioVuds;
	}

	public void setDataActualitzacioVuds(String dataActualitzacioVuds) {
		this.dataActualitzacioVuds = dataActualitzacioVuds;
	}

	//u92770[enric] añadido equals para que procedimiento pueda ser testeable con easyMock.
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tramite)) return false; 
		return id.equals(((Tramite)obj).id);
	}

	@Override
	public String toString() {
		String proc=procedimiento==null?null:procedimiento.getId().toString();
		return "Tramite [id="+ id +  
				", codiVuds=" + codiVuds + 
				", descCodiVuds=" + descCodiVuds +
				", validacio=" + validacio+
				", dataActualització=" + dataActualitzacio +
				", dataCaducitat=" + dataCaducitat + 
				", taxes=" + taxes +
				", dataPublicacio=" + dataPublicacio + 
				", docsInformatius=" + docsInformatius + 
				", fase=" + fase + 
				", formularios=" + formularios + 
				", organCompetent=" + organCompetent + 
				", procedimiento=" + proc +
				", idTraTel="	+ idTraTel +
				", versio=" + versio  + 
				", traduccion="+ getTraduccion() +
				"]";
	}


	public String getNombreOrganCompetent(String idioma) {
		if(!estaTraduitOrganCompetent(idioma))
			return null;
		return organCompetent.getNombreUnidadAdministrativa(idioma);
	}

	public boolean estaTraduitOrganCompetent(String idioma) {
		return null!=(TraduccionUA)getOrganCompetent().getTraduccion(idioma);
	}

	public Long obtenerIdUnidadAdministrativa() {
		return getProcedimiento().getUnidadAdministrativa().getId();
	}

	public String getNombreUnidadAdministrativa(String idioma) {
		if(!estaTraduitUnidadAdministrativa(idioma))
			return null;
		return procedimiento.getNombreUnidadAdministrativa(idioma);
	}

	private boolean estaTraduitUnidadAdministrativa(String idioma) {
		return null!=getProcedimiento().getUnidadAdministrativa().getTraduccion(idioma);
	}


	public boolean esVentanillaUnica() {
		 return "1".equals(getProcedimiento().getVentanillaUnica());
	}

	public boolean esPublico() {
	    final Date now = new Date();
	    boolean noCaducado = (getDataCaducitat() == null || getDataCaducitat().after(now));
	    boolean publicado = (getDataPublicacio() == null || getDataPublicacio().before(now));
	    boolean visible = (getValidacio() == null || Validacion.PUBLICA.equals(Integer.valueOf(getValidacio().toString())));
	    return visible && noCaducado && publicado;
	}
}
