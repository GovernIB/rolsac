package org.ibit.rol.sac.model.webcaib;


public class TemaModel implements java.io.Serializable {
        
    int codi;
    String nom;
    
    public void setCodi (int codi) { this.codi = codi; }
    public int getCodi () { return codi; }
    
    public void setNom (String nom) { this.nom = nom; }
    public String getNom () { return nom; }
    
    public String toString() {
        return "[ codi : " + codi +
               "\n nom : " + nom + "]";
    }
}