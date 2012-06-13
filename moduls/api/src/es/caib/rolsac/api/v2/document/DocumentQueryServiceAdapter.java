package es.caib.rolsac.api.v2.document;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class DocumentQueryServiceAdapter extends DocumentDTO implements DocumentQueryService {

    private DocumentQueryServiceStrategy documentQueryServiceStrategy;

    public void setDocumentQueryServiceStrategy(DocumentQueryServiceStrategy documentQueryServiceStrategy) {
        this.documentQueryServiceStrategy = documentQueryServiceStrategy;
    }

    public DocumentQueryServiceAdapter(DocumentDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return documentQueryServiceStrategy instanceof DocumentQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public FitxaQueryService obtenirFitxa() {
        if (this.getFicha() == null) {return null;}
        FitxaDTO dto = documentQueryServiceStrategy.obtenirFitxa(this.getFicha());
        return (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), dto);
    }

    public ProcedimentQueryService obtenirProcediment() {
        if (this.getProcedimiento() == null) {return null;}
        ProcedimentDTO dto = documentQueryServiceStrategy.obtenirProcediment(this.getProcedimiento());
        return (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
    }

    public ArxiuQueryService obtenirArxiu() {
        if (this.getArchivo() == null) {return null;}
        ArxiuDTO dto = documentQueryServiceStrategy.obtenirArxiu(this.getArchivo());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }

}
