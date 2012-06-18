package es.caib.rolsac.api.v2.destinatari;

public class DestinatariDTO {

    protected long id;
    private String nom;
    private String idRemot;
    private String endPoint;
    private String email;

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getIdRemot() {
        return idRemot;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getEmail() {
        return email;
    }

}
