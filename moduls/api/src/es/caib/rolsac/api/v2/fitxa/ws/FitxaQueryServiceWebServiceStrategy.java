package es.caib.rolsac.api.v2.fitxa.ws;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuCriteria;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceWebServiceStrategy implements FitxaQueryServiceStrategy {

    public ArxiuDTO obtenirIcona(long id, ArxiuCriteria arxiuCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirImatge(long id, ArxiuCriteria arxiuCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirBaner(long id, ArxiuCriteria arxiuCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaUADTO> llistarFitxesUA(long id, FitxaUACriteria fitxaUACriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumDocuments(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumEnllacos(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumMateries(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFetsVitals(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumUnitatsAdministratives(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumSeccions(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public ArxiuDTO obtenirIcona(Long icono) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirImatge(Long imagen) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirBaner(Long baner) {
        // TODO Auto-generated method stub
        return null;
    }

}
