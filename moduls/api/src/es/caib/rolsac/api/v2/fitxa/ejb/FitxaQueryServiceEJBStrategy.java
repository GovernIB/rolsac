package es.caib.rolsac.api.v2.fitxa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceEJBStrategy implements FitxaQueryServiceStrategy {

    private FitxaQueryServiceDelegate fitxaQueryServiceDelegate;
    
    public void setFitxaQueryServiceDelegate(FitxaQueryServiceDelegate fitxaQueryServiceDelegate) {
        this.fitxaQueryServiceDelegate = fitxaQueryServiceDelegate;
    }
    
    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) {
        return fitxaQueryServiceDelegate.llistarEnllacos(id, enllacCriteria);
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        return fitxaQueryServiceDelegate.llistarDocuments(id, documentCriteria);
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera) {
        return fitxaQueryServiceDelegate.llistarFetsVitals(id, fetVitalCritera);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return fitxaQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        return fitxaQueryServiceDelegate.llistarSeccions(id, seccioCriteria);
    }
    
    public int getNumDocuments(long id) {
        return fitxaQueryServiceDelegate.getNumDocuments(id);
    }

    public int getNumEnllacos(long id) {
        return fitxaQueryServiceDelegate.getNumEnllacos(id);
    }

    public int getNumFetsVitals(long id) {
        return fitxaQueryServiceDelegate.getNumFetsVitals(id);
    }

    public int getNumUnitatsAdministratives(long id) {
        return fitxaQueryServiceDelegate.getNumUnitatsAdministratives(id);
    }

    public int getNumSeccions(long id) {
        return fitxaQueryServiceDelegate.getNumSeccions(id);
    }

    public ArxiuDTO obtenirIcona(Long icono) {
        return fitxaQueryServiceDelegate.obtenirIcona(icono);
    }

    public ArxiuDTO obtenirImatge(Long imagen) {
        return fitxaQueryServiceDelegate.obtenirImatge(imagen);
    }

    public ArxiuDTO obtenirBaner(Long baner) {
        return fitxaQueryServiceDelegate.obtenirBaner(baner);
    }
}
