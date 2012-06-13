package es.caib.rolsac.api.v2.fitxaUA;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.ejb.FitxaUAQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaUAQueryServiceAdapter extends FitxaUADTO implements FitxaUAQueryService {

    FitxaUAQueryServiceStrategy fitxaUAQueryServiceStrategy;

    public FitxaUAQueryServiceAdapter() {
        // FIXME: don't harcode the ProcedimentQueryServiceEJBStrategy.
        fitxaUAQueryServiceStrategy = new FitxaUAQueryServiceEJBStrategy();
    }
    
    public FitxaUAQueryServiceAdapter(FitxaUADTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public UnitatAdministrativaQueryService obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {

        UnitatAdministrativaDTO dto = fitxaUAQueryServiceStrategy.obtenirUnitatAdministrativa(id,
                unitatAdministrativaCriteria);

        return new UnitatAdministrativaQueryServiceAdapter(dto);
    }

    public FitxaQueryService obtenirFitxa(FitxaCriteria fitxaCriteria) {

        FitxaDTO dto = fitxaUAQueryServiceStrategy.obtenirFitxa(id, fitxaCriteria);
        return new FitxaQueryServiceAdapter(dto);
    }

    public SeccioQueryService obtenirSeccio(SeccioCriteria seccioCriteria) {

        SeccioDTO dto = fitxaUAQueryServiceStrategy.obtenirSeccio(id, seccioCriteria);
        return new SeccioQueryServiceAdapter(dto);
    }

}
