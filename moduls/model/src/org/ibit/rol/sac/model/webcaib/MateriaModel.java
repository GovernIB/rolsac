package org.ibit.rol.sac.model.webcaib;


public class MateriaModel implements java.io.Serializable {
        
    int codi = 0;
    String nom = null;
    String descripcio = null;
    int icona =0;
    int perfil = 0;
    
    public void setCodi (int codi) { this.codi = codi; }
    public int getCodi () { return codi; }
    
    public void setNom (String nom) { this.nom = nom; }
    public String getNom () { return nom; }
    
	public void setDescripcio (String descripcio) { this.descripcio = descripcio; }
	public String getDescripcio () { return descripcio; }

    public void setIcona (int icona) { this.icona = icona; }
	public int getIcona () { return icona; }

	public void setPerfil (int perfil) { this.perfil = perfil; }
	public int getPerfil () { return perfil; }

    public String toString() {
        return "[ codi : " + codi +
               "\n nom : " + nom + "]";
    }
}