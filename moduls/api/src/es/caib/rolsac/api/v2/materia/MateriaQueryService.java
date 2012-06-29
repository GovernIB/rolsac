package es.caib.rolsac.api.v2.materia;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
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

    public int getNumFitxes() throws QueryServiceException;

    public int getNumAgrupacioMateries() throws QueryServiceException;

    public int getNumProcedimentsLocals() throws QueryServiceException;

    public int getNumUnitatsMateries() throws QueryServiceException;

    public int getNumIcones() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getFotografia() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException;

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException;

    public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacioMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException;

    public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateria(UnitatMateriaCriteria unitatMateriaCriteria) throws QueryServiceException;
    
    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException;

}
