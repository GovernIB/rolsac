package es.caib.rolsac.api.v2.servicio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.servicio.ServicioQueryServiceStrategy;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public class ServicioQueryServiceEJBStrategy implements ServicioQueryServiceStrategy {

    private ServicioQueryServiceDelegate servicioQueryServiceDelegate;

    public void setServicioQueryServiceDelegate(ServicioQueryServiceDelegate servicioQueryServiceDelegate) {
        this.servicioQueryServiceDelegate = servicioQueryServiceDelegate;
    }

    
    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.getNumNormatives(id, tipus);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumMateries(long id) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.getNumMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumDocuments(long id) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.getNumDocuments(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFetsVitals(long id) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.getNumFetsVitals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.llistarMateries(id, materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetsVitalsCriteria) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.llistarFetsVitals(id, fetsVitalsCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<DocumentoServicioDTO> llistarDocuments(long id, DocumentoServicioCriteria documentCriteria) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.llistarDocuments(id, documentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws StrategyException {
        try {
            return servicioQueryServiceDelegate.llistarPublicsObjectius(id, poCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJBs.
	}

}
