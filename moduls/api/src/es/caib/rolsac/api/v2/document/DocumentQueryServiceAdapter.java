package es.caib.rolsac.api.v2.document;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
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

    public DocumentQueryServiceAdapter(DocumentDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return documentQueryServiceStrategy instanceof DocumentQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public FitxaQueryService obtenirFitxa() throws QueryServiceException {
        if (this.getFicha() == null) {return null;}
        try {
            FitxaDTO dto = documentQueryServiceStrategy.obtenirFitxa(this.getFicha());
            return (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "ficha.", e);
        }
    }

    public ProcedimentQueryService obtenirProcediment() throws QueryServiceException {
        if (this.getProcedimiento() == null) {return null;}
        try {
            ProcedimentDTO dto = documentQueryServiceStrategy.obtenirProcediment(this.getProcedimiento());
            return (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "procedimiento.", e);
        }
    }

    public ArxiuQueryService obtenirArxiu() throws QueryServiceException {
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = documentQueryServiceStrategy.obtenirArxiu(this.getArchivo());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "archivo.", e);
        }
    }

}
