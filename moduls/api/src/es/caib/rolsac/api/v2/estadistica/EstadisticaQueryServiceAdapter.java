package es.caib.rolsac.api.v2.estadistica;

public class EstadisticaQueryServiceAdapter extends EstadisticaDTO implements EstadisticaQueryService {

    public EstadisticaQueryServiceAdapter(EstadisticaDTO dto) {
        abr = dto.abr;
        // ...
    }

    public void addAcces(EstadisticaDTO dades, EstadisticaCriteria estadisticaCriteria) {
        // TODO Auto-generated method stub
    }

}
