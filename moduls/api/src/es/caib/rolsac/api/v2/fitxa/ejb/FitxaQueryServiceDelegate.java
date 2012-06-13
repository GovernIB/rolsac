package es.caib.rolsac.api.v2.fitxa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceDelegate {

 // @Injected
    private FitxaQueryServiceEJBLocator fitxaQueryServiceLocator;        
    
    public void setFitxaQueryServiceLocator(FitxaQueryServiceEJBLocator fitxaQueryServiceLocator) {
        this.fitxaQueryServiceLocator = fitxaQueryServiceLocator;
    }

    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.llistarEnllacos(id, enllacCriteria);
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.llistarDocuments(id, documentCriteria);
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.llistarFetsVitals(id, fetVitalCritera);
    }

    public int getNumDocuments(long id) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.getNumDocuments(id);
    }

    public int getNumEnllacos(long id) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.getNumEnllacos(id);
    }

    public int getNumFetsVitals(long id) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.getNumFetsVitals(id);
    }

    public int getNumUnitatsAdministratives(long id) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.getNumUnitatsAdministratives(id);
    }

    public int getNumSeccions(long id) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.getNumSeccions(id);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.llistarSeccions(id, seccioCriteria);
    }

    public ArxiuDTO obtenirIcona(Long icono) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.obtenirIcona(icono);
    }

    public ArxiuDTO obtenirImatge(Long imagen) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.obtenirImatge(imagen);
    }

    public ArxiuDTO obtenirBaner(Long baner) {
        FitxaQueryServiceEJB ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
        return ejb.obtenirBaner(baner);
    }
    
}
