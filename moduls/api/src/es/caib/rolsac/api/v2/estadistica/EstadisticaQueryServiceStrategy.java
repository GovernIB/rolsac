package es.caib.rolsac.api.v2.estadistica;

public interface EstadisticaQueryServiceStrategy {
    public void addAccess(long id, EstadisticaCriteria estadisticaCriteria);
}
