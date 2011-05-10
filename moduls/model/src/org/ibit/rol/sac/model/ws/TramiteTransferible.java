package org.ibit.rol.sac.model.ws;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Marilen
 * Date: 16-jul-2010
 * Time: 13:43:57
 * To change this template use File | Settings | File Templates.
 */
public class TramiteTransferible  extends ActuacionTransferible implements Serializable {

    private Long id;
    private String codiVuds;
    private String descCodiVuds;
    private int fase;
    private Long idProcedimiento;


    private Long validacio;
    private Date dataCaducitat;
    private Date dataPublicacio;
    private Date dataActualitzacio;

    private Long idOrganCompetent;
    private String	idTraTel;
    private Integer versio;
    private String urlExterna;
    
    private TaxaTransferible[] taxes;
    
    private DocumentTramitTransferible[] documentInformatiu;

    private DocumentTramitTransferible[] formularios;
    
    private TraduccionTramiteTransferible[] traducciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public Long getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Long getValidacio() {
        return validacio;
    }

    public void setValidacio(Long validacio) {
        this.validacio = validacio;
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

    public Long getIdOrganCompetent() {
        return idOrganCompetent;
    }

    public void setIdOrganCompetent(Long idOrganCompetent) {
        this.idOrganCompetent = idOrganCompetent;
    }

    public String getIdTraTel() {
        return idTraTel;
    }

    public void setIdTraTel(String idTraTel) {
        this.idTraTel = idTraTel;
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


    public TraduccionTramiteTransferible[] getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(TraduccionTramiteTransferible[] traducciones) {
        this.traducciones = traducciones;
    }

	public TaxaTransferible[] getTaxes() {
		return taxes;
	}

	public void setTaxes(TaxaTransferible[] taxes) {
		this.taxes = taxes;
	}
	

	public DocumentTramitTransferible[] getDocumentInformatiu() {
		return documentInformatiu;
	}

	public void setDocumentInformatiu(
			DocumentTramitTransferible[] documentInformatiu) {
		this.documentInformatiu = documentInformatiu;
	}
	
	public DocumentTramitTransferible[] getFormularios() {
		return formularios;
	}

	public void setFormularios(DocumentTramitTransferible[] formularios) {
		this.formularios = formularios;
	}

	@SuppressWarnings("unchecked")
	public void rellenar(Tramite tramite){
        this.setId(tramite.getId());
        this.setCodiVuds(tramite.getCodiVuds());
        this.setDescCodiVuds(tramite.getDescCodiVuds());
        this.setFase(tramite.getFase());
        this.setIdProcedimiento(tramite.getProcedimiento().getId());
        this.setValidacio(tramite.getValidacio());
        this.setDataCaducitat(tramite.getDataCaducitat());
        this.setDataPublicacio(tramite.getDataPublicacio());
        this.setDataActualitzacio(tramite.getDataActualitzacio());
        if(tramite.getOrganCompetent()!=null){
            this.setIdOrganCompetent(tramite.getOrganCompetent().getId());
        }
        this.setIdTraTel(tramite.getIdTraTel());
        this.setVersio(tramite.getVersio());
        this.setUrlExterna(tramite.getUrlExterna());
        
        //taxes
        
		final List<TaxaTransferible> taxesTemp = new ArrayList<TaxaTransferible>();
		for(final Taxa taxa : (Collection<Taxa>)tramite.getTaxes()){
				if(taxa!=null){
					final TaxaTransferible temp =  new TaxaTransferible();
					taxesTemp.add(temp.generar(taxa));
				}
		}
		this.setTaxes(taxesTemp.toArray(new TaxaTransferible[0]));
		
		
        //Document informatiu
        
		final List<DocumentTramitTransferible> documentInformatiuTemp = new ArrayList<DocumentTramitTransferible>();
		for(final DocumentTramit documentTramit : (Collection<DocumentTramit>)tramite.getDocsInformatius()){
				if(documentTramit!=null){
					final DocumentTramitTransferible temp =  new DocumentTramitTransferible();
					documentInformatiuTemp.add(temp.generar(documentTramit));
				}
		}
		this.setDocumentInformatiu(documentInformatiuTemp.toArray(new DocumentTramitTransferible[0]));
		
		//Formularios
        
		final List<DocumentTramitTransferible> formulariosTemp = new ArrayList<DocumentTramitTransferible>();
		for(final DocumentTramit formularioTramit : (Collection<DocumentTramit>)tramite.getFormularios()){
				if(formularioTramit!=null){
					final DocumentTramitTransferible temp =  new DocumentTramitTransferible();
					formulariosTemp.add(temp.generar(formularioTramit));
				}
		}
		this.setFormularios(formulariosTemp.toArray(new DocumentTramitTransferible[0]));
        
		//todo: urlRemota-bundle????
        
         //Relleno las traducciones
		final List<TraduccionTramiteTransferible> traducciones = new ArrayList<TraduccionTramiteTransferible>(); 
		for (final String idioma : (Collection<String>)tramite.getLangs()){
            final TraduccionTramite traduccion = (TraduccionTramite)tramite.getTraduccion(idioma);
            if(traduccion!=null){
                final TraduccionTramiteTransferible temp =  new TraduccionTramiteTransferible();
                temp.setNombre(traduccion.getNombre());
                temp.setDescripcion(traduccion.getDescripcion());
                temp.setDocumentacion(traduccion.getDocumentacion());
                temp.setRequisits(traduccion.getRequisits());
                temp.setPlazos(traduccion.getPlazos());
                temp.setObservaciones(traduccion.getObservaciones());
                temp.setLugar(traduccion.getLugar());
                temp.setCodigoEstandarIdioma(idioma);
                traducciones.add(temp);
            }
        }
        
        this.setTraducciones(traducciones.toArray(new TraduccionTramiteTransferible[0]));
    }
    
   
    public static TramiteTransferible generar(Tramite tramite){
    	TramiteTransferible tramT = new TramiteTransferible();
    	if(tramite!=null){
    		tramT.rellenar(tramite);
    	}
    	return tramT;
    }
}
