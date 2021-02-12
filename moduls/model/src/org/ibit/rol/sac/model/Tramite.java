
package org.ibit.rol.sac.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tramite extends Ordenable implements Comparator {

	public final static int INICIACION = 1;
	public final static int INSTRUCCION = 2;
	public final static int FINALIZACION = 3;

	// inst vars han de ser instanceof Objects pq el b

	Long id;
	int fase;
	ProcedimientoLocal procedimiento;

	Long validacio;
	Date dataCaducitat; // DATE
	Date dataPublicacio; // DATE
	Date dataActualitzacio; // DATE

	Date dataInici; // DATE
	Date dataTancament; // DATE

	// private String idOrganCompetent; //NUMBER(19)
	UnidadAdministrativa organCompetent;

	boolean presencial;
	boolean telematico;

	String idTraTel;
	Integer versio; // NUMBER(2)
//	String urlExterna; // VARCHAR2(1024 CHAR)
	/** Parametros si es un tramtie telematico y necesita parametros. **/
	private String parametros;
	/** Plataforma. **/
	private Plataforma plataforma;

	// campos para la ventanilla unica

	String codiVuds;
	String descCodiVuds;

	public enum Operativa {
		CREA, MODIFICA, BORRA, CONSULTA
	};

	private Operativa operativa;

	transient boolean tramiteVudsValido = true; // aquest camp no cal que sigui persistent.

	Set<DocumentTramit> docsInformatius = new HashSet<DocumentTramit>(); // set of documents
	Set<DocumentTramit> formularios = new HashSet<DocumentTramit>(); // set of documents
	Set<DocumentTramit> docsRequerits = new HashSet<DocumentTramit>(); // set of documents
	Set<Taxa> taxes = new HashSet<Taxa>(); // set of taxa
	String dataActualitzacioVuds; // String: "no enviat" "data hora"

	public boolean isTramiteVudsValido() {
		return tramiteVudsValido;
	}

	public void setTramiteVudsValido(final boolean tramiteVudsValido) {
		this.tramiteVudsValido = tramiteVudsValido;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(final int fase) {
		this.fase = fase;
	}

	public ProcedimientoLocal getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoLocal procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Set<DocumentTramit> getFormularios() {
		return formularios;
	}

	public void setFormularios(final Set<DocumentTramit> formularios) {
		this.formularios = formularios;
	}

	public void addFormulario(final DocumentTramit doc) {
		doc.setTramit(this);
		doc.setOrden((long) formularios.size() + 1);
		formularios.add(doc);
	}

	/**
	 * @deprecated usar removeDocument
	 * @param form
	 */
	@Deprecated
	public void removeFormulario(final Formulario form) {
		form.setTramite(null);
		formularios.remove(form);
	}

	public Date getDataCaducitat() {
		return dataCaducitat;
	}

	public void setDataCaducitat(final Date dataCaducitat) {
		this.dataCaducitat = dataCaducitat;
	}

	public Date getDataPublicacio() {
		return dataPublicacio;
	}

	public void setDataPublicacio(final Date dataPublicacio) {
		this.dataPublicacio = dataPublicacio;
	}

	public Date getDataActualitzacio() {
		return dataActualitzacio;
	}

	public void setDataActualitzacio(final Date dataActualitzacio) {
		this.dataActualitzacio = dataActualitzacio;
	}

	public Date getDataInici() {
		return dataInici;
	}

	public void setDataInici(final Date dataInici) {
		this.dataInici = dataInici;
	}

	public Date getDataTancament() {
		return dataTancament;
	}

	public void setDataTancament(final Date dataTancament) {
		this.dataTancament = dataTancament;
	}

	public UnidadAdministrativa getOrganCompetent() {
		return organCompetent;
	}

	public void setOrganCompetent(final UnidadAdministrativa organCompetent) {
		this.organCompetent = organCompetent;
	}

	public Set<DocumentTramit> getDocsInformatius() {
		return docsInformatius;
	}

	public void setDocsInformatius(final Set<DocumentTramit> docsInformatius) {
		this.docsInformatius = docsInformatius;
	}

	public void addDocInformatiu(final DocumentTramit doc) {
		doc.setTramit(this);
		doc.setOrden((long) docsInformatius.size() + 1);
		docsInformatius.add(doc);
	}

	public void addDocument(final DocumentTramit doc) {
		switch (doc.getTipus()) {
		case DocumentTramit.DOCINFORMATIU:
			addDocInformatiu(doc);
			break;
		case DocumentTramit.FORMULARI:
			addFormulario(doc);
			break;
		case DocumentTramit.REQUERIT:
			addDocRequerits(doc);
			break;
		}
	}

	/**
	 * L'ordre es refren el TramiteFacadebEJB
	 * 
	 * @param doc
	 */
	public void removeDocument(final Document doc) {
		switch (doc.getTipus()) {
		case DocumentTramit.DOCINFORMATIU:
			docsInformatius.remove(doc);
			break;
		case DocumentTramit.FORMULARI:
			formularios.remove(doc);
			break;
		case DocumentTramit.REQUERIT:
			docsRequerits.remove(doc);
			break;

		}
	}

	public Set<DocumentTramit> getDocsRequerits() {
		return docsRequerits;
	}

	public void setDocsRequerits(final Set<DocumentTramit> docsRequerits) {
		this.docsRequerits = docsRequerits;
	}

	public void addDocRequerits(final DocumentTramit doc) {
		doc.setTramit(this);
		doc.setOrden((long) docsRequerits.size() + 1);
		docsRequerits.add(doc);
	}

	public void addTaxa(final Taxa taxa) {
		taxa.setTramit(this);
		taxes.add(taxa);
		taxa.setOrden((long) taxes.size());
	}

	public void removeTaxa(final Taxa taxa) {
		taxa.setTramit(null);
		taxes.remove(taxa);
	}

	public String getCodiVuds() {
		return codiVuds;
	}

	public void setCodiVuds(final String codiVuds) {
		this.codiVuds = codiVuds;
	}

	public String getDescCodiVuds() {
		return descCodiVuds;
	}

	public void setDescCodiVuds(final String descCodiVuds) {
		this.descCodiVuds = descCodiVuds;
	}

	public Integer getVersio() {
		return versio;
	}

	public void setVersio(final Integer versio) {
		this.versio = versio;
	}

	public String getUrlExterna() {
		//return urlExterna;
			
		final TraduccionTramite traduccion = (TraduccionTramite) getTraduccion();
		if (null == traduccion) {
			return null;
		} else {
			return traduccion.getUrlTramiteExterno();
		}		
	}

	/*public void setUrlExterna(final String urlExterna) {
		this.urlExterna = urlExterna;
	}*/

	public Operativa getOperativa() {
		return operativa;
	}

	public void setOperativa(final Operativa operativa) {
		this.operativa = operativa;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	public Long getValidacio() {
		return validacio;
	}

	public void setValidacio(final Long validacio) {
		this.validacio = validacio;
	}

	public String getIdTraTel() {
		return idTraTel;
	}

	public void setIdTraTel(final String idTramTele) {
		this.idTraTel = idTramTele;
	}

	public Set<Taxa> getTaxes() {
		return taxes;
	}

	public void setTaxes(final Set<Taxa> taxes) {
		this.taxes = taxes;
	}

	public String getDataActualitzacioVuds() {
		return dataActualitzacioVuds;
	}

	public void setDataActualitzacioVuds(final String dataActualitzacioVuds) {
		this.dataActualitzacioVuds = dataActualitzacioVuds;
	}

	// u92770[enric] a�adido equals para que procedimiento pueda ser testeable con
	// easyMock.
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Tramite))
			return false;
		return id.equals(((Tramite) obj).id);
	}

	@Override
	public String toString() {
		final String proc = procedimiento == null ? null : procedimiento.getId().toString();
		return "Tramite [id=" + id + ", codiVuds=" + codiVuds + ", descCodiVuds=" + descCodiVuds + ", validacio="
				+ validacio + ", dataActualitzaci�=" + dataActualitzacio + ", dataCaducitat=" + dataCaducitat +
				// ", taxes=" + taxes +
				", dataPublicacio=" + dataPublicacio +
				// ", docsInformatius=" + docsInformatius +
				", fase=" + fase +
				// ", formularios=" + formularios +
				", organCompetent=" + organCompetent + ", procedimiento=" + proc + ", idTraTel=" + idTraTel
				+ ", versio=" + versio + ", traduccion=" + getTraduccion() + "]";
	}

	public String getNombreOrganCompetent(final String idioma) {
		if (!estaTraduitOrganCompetent(idioma))
			return null;
		return organCompetent.getNombreUnidadAdministrativa(idioma);
	}

	public boolean estaTraduitOrganCompetent(final String idioma) {
		return null != (TraduccionUA) getOrganCompetent().getTraduccion(idioma);
	}

	public Long obtenerIdUnidadAdministrativa() {
		return getProcedimiento().getUnidadAdministrativa().getId();
	}

	public String getNombreUnidadAdministrativa(final String idioma) {
		if (!estaTraduitUnidadAdministrativa(idioma))
			return null;
		return procedimiento.getNombreUnidadAdministrativa(idioma);
	}

	private boolean estaTraduitUnidadAdministrativa(final String idioma) {
		return null != getProcedimiento().getUnidadAdministrativa().getTraduccion(idioma);
	}

	public boolean esVentanillaUnica() {
		return getProcedimiento().esVentanillaUnica();
	}

	public boolean esPublico() {
		final Date now = new Date();
		final boolean noCaducado = (getDataCaducitat() == null || getDataCaducitat().after(now));
		final boolean publicado = (getDataPublicacio() == null || getDataPublicacio().before(now));
		// INDRA : La visibilidad depende del procedimiento
		// boolean visible = (getValidacio() == null ||
		// Validacion.PUBLICA.equals(Integer.valueOf(getValidacio().toString())));
		final boolean visible = procedimiento != null && (procedimiento.getValidacion() == null
				|| Validacion.PUBLICA.equals(Integer.valueOf(procedimiento.getValidacion().toString())));
		return visible && noCaducado && publicado;
	}

	@Override
	public int compare(final Object o1, final Object o2) {
		final Tramite u1 = (Tramite) o1;
		final Tramite u2 = (Tramite) o2;
		return u1.getId().intValue() - u2.getId().intValue();
	}

	/**
	 * @return the presencial
	 */
	public boolean isPresencial() {
		return presencial;
	}

	/**
	 * @param presencial
	 *            the presencial to set
	 */
	public void setPresencial(final boolean presencial) {
		this.presencial = presencial;
	}

	/**
	 * @return the telematico
	 */
	public boolean isTelematico() {
		return telematico;
	}

	/**
	 * @param telematico
	 *            the telematico to set
	 */
	public void setTelematico(final boolean telematico) {
		this.telematico = telematico;
	}

	/**
	 * @return the parametros
	 */
	public String getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the plataforma
	 */
	public Plataforma getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma the plataforma to set
	 */
	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
}
