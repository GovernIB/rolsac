package es.caib.rolsac.api.v2.estadistica;

public interface EstadisticaQueryService {

    // public EstadisticaQueryService estadistica(EstadisticaCriteria estadisticaCriteria);

    // estadisticaCriteria ha de contenir un membre "consulta"
    public void addAcces(EstadisticaDTO dades, EstadisticaCriteria estadisticaCriteria);

}
