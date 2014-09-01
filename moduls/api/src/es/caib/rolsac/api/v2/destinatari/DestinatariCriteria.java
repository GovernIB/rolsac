package es.caib.rolsac.api.v2.destinatari;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class DestinatariCriteria extends BasicCriteria { 
    
    private String nom;
    private String idRemot;
    private String endPoint;
    private String email;
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getIdRemot() {
        return idRemot;
    }
    public void setIdRemot(String idRemot) {
        this.idRemot = idRemot;
    }
    public String getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
