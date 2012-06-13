package es.caib.rolsac.api.v2.unitatMateria;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.ejb.UnitatMateriaQueryServiceEJBStrategy;

public class UnitatMateriaQueryServiceAdapter extends UnitatMateriaDTO implements UnitatMateriaQueryService {

    UnitatMateriaQueryServiceStrategy unitatMateriaQueryServiceStrategy;

    public UnitatMateriaQueryServiceAdapter() {
        // FIXME: don't harcode the UnitatMateriaQueryServiceEJBStrategy.
        unitatMateriaQueryServiceStrategy = new UnitatMateriaQueryServiceEJBStrategy();
    }
    
    public UnitatMateriaQueryServiceAdapter(UnitatMateriaDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    public MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria) {

        MateriaDTO dto = unitatMateriaQueryServiceStrategy.obtenirMateria(id, materiaCriteria);

        return new MateriaQueryServiceAdapter(dto);
    }

    public UnitatAdministrativaQueryService obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {

        UnitatAdministrativaDTO dto = unitatMateriaQueryServiceStrategy.obtenirUnitatAdministrativa(id,
                unitatAdministrativaCriteria);

        return new UnitatAdministrativaQueryServiceAdapter(dto);
    }

}
