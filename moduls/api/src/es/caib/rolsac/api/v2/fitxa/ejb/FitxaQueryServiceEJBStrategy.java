package es.caib.rolsac.api.v2.fitxa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceEJBStrategy implements FitxaQueryServiceStrategy {

    private FitxaQueryServiceDelegate fitxaQueryServiceDelegate;
    
    public void setFitxaQueryServiceDelegate(FitxaQueryServiceDelegate fitxaQueryServiceDelegate) {
        this.fitxaQueryServiceDelegate = fitxaQueryServiceDelegate;
    }
    
    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarEnllacos(id, enllacCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarDocuments(id, documentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarFetsVitals(id, fetVitalCritera);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarSeccions(id, seccioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<FitxaUADTO> llistarFitxesUA(long id, FitxaUACriteria fitxaUACriteria) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarFitxesUA(id, fitxaUACriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public int getNumDocuments(long id) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.getNumDocuments(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumEnllacos(long id) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.getNumEnllacos(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFetsVitals(long id) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.getNumFetsVitals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.getNumUnitatsAdministratives(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumSeccions(long id) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.getNumSeccions(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirIcona(Long icono) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.obtenirIcona(icono);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirImatge(Long imagen) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.obtenirImatge(imagen);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirBaner(Long baner) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.obtenirBaner(baner);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws StrategyException {
        try {
            return fitxaQueryServiceDelegate.llistarPublicsObjectius(id, poCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public int getNumMateries(long id) throws StrategyException {
		try {
            return fitxaQueryServiceDelegate.getNumMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
	}

	public List<MateriaDTO> llistarMateries(long id,
			MateriaCriteria materiaCriteria) throws StrategyException {
		try {
            return fitxaQueryServiceDelegate.llistarMateries(id, materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
	}

	public void setUrl(String url) {
		//No es necesario
	}
	
}
