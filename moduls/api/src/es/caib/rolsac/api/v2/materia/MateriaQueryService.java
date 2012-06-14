package es.caib.rolsac.api.v2.materia;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;

public interface MateriaQueryService {

    int getNumFitxes();

    int getNumAgrupacioMateries();

    int getNumProcedimentsLocals();

    int getNumUnitatsMateries();

    int getNumIcones();
    
    ArxiuQueryServiceAdapter getFotografia();
    
    ArxiuQueryServiceAdapter getIcona();
    
    ArxiuQueryServiceAdapter getIconaGran();

    List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria);

    List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria);

    List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacioMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria);

    List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateria(UnitatMateriaCriteria unitatMateriaCriteria);
    
    List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria);

}
