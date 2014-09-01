package es.caib.rolsac.api.v2.estadistica;

import es.caib.rolsac.api.v2.exception.InsertServiceException;

public interface EstadisticaInsertService {

    /**
     * Crea o actualiza una Estadistica para una Ficha.
     * @param fitxaId
     */
    public boolean gravarEstadisticaFitxa(long fitxaId) throws InsertServiceException;
    
    /**
     * Crea o actualiza una Estadistica para una Ficha segun la materia donde estaba.
     * @param fichaId
     */
    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) throws InsertServiceException;
    
    /**
     * Crea o actualiza una Estadistica para una Ficha segun la UA donde estaba.
     * @param fitxaId
     * @param uaId
     */
    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) throws InsertServiceException;
    
    /**
     * Crea o actualiza una Estadistica para una Materia.
     * @param materiaId
     */
    public boolean gravarEstadisticaMateria(long materiaId) throws InsertServiceException;
    
    /**
     * Crea o actualiza una Estadistica para una Normativa
     * @param normativaId
     */
    public boolean gravarEstadisticaNormativa(long normativaId) throws InsertServiceException;
    
    /**
     * Crea o actualiza una Estadistica para una Procedimiento Local
     * @param
     */
    public boolean gravarEstadisticaProcediment(long procedimentId) throws InsertServiceException;
    
    /**
     * Crea o actualiza una Estadistica para una unitatAdministrativa
     * @param uaId
     */
    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) throws InsertServiceException;

}
