package es.caib.rolsac.api.v2.document;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.ejb.DocumentQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class DocumentQueryServiceAdapter extends DocumentDTO implements DocumentQueryService {

    private static final long serialVersionUID = -1183253107387751972L;

    private DocumentQueryServiceStrategy documentQueryServiceStrategy;

    public void setDocumentQueryServiceStrategy(DocumentQueryServiceStrategy documentQueryServiceStrategy) {
        this.documentQueryServiceStrategy = documentQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
		if ( this.documentQueryServiceStrategy != null) {
			 this.documentQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    public DocumentQueryServiceAdapter(DocumentDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return documentQueryServiceStrategy instanceof DocumentQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public FitxaQueryService obtenirFitxa() throws QueryServiceException {
        if (this.getFicha() == null) {return null;}
        try {
            FitxaDTO dto = documentQueryServiceStrategy.obtenirFitxa(this.getFicha());
            FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), dto);
            if (fqsa != null && rolsacUrl != null) {
            	fqsa.setRolsacUrl(rolsacUrl);
            }
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "ficha.", e);
        }
    }

    public ProcedimentQueryService obtenirProcediment() throws QueryServiceException {
        if (this.getProcedimiento() == null) {return null;}
        try {
            ProcedimentDTO dto = documentQueryServiceStrategy.obtenirProcediment(this.getProcedimiento());
            ProcedimentQueryServiceAdapter pqsa = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
            if (pqsa != null && rolsacUrl != null) {
            	pqsa.setRolsacUrl(rolsacUrl);
            }
            return pqsa;
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "procedimiento.", e);
        }
    }

    public ArxiuQueryService obtenirArxiu() throws QueryServiceException {
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = documentQueryServiceStrategy.obtenirArxiu(this.getArchivo());
            ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (aqsa != null && rolsacUrl != null) {
            	aqsa.setRolsacUrl(rolsacUrl);
            }
            return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "archivo.", e);
        }
    }

   

}
