package es.caib.rolsac.api.v2.destinatari;

public class DestinatariQueryServiceAdapter extends DestinatariDTO implements DestinatariQueryService {

    public DestinatariQueryServiceAdapter(DestinatariDTO dto) {
        id = dto.id;
        // ...
    }

}
