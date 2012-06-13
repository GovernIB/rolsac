package es.caib.rolsac.api.v2.destinatari;

public class DestinatariDTO {

    long id;
    String nom;
    String idRemot;
    String endPoint;
    String email;

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
