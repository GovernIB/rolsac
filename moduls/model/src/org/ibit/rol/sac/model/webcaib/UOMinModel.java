package org.ibit.rol.sac.model.webcaib;

public class UOMinModel implements java.io.Serializable {

	protected String codi;
	protected String nom;
	protected String dep_abr;
	protected String abreviatura;
	protected String presentacion;
   
	public UOMinModel() {}
	
	public UOMinModel (UOMinModel uommodel) {
		
		this.codi=uommodel.getCodi();
		this.nom=uommodel.getNom();
		this.dep_abr=uommodel.getDep_Abr();
		this.abreviatura=uommodel.getAbreviatura();
		this.presentacion=uommodel.getPresentacion();
		
	}
    
   public void setCodi(String codi) { this.codi = codi; }
   public String getCodi() { return codi; }
   
   public void setNom (String nom) { this.nom = nom; }
   public String getNom () { return nom; }
   
   public void setDep_Abr (String dep_abr) { this.dep_abr = dep_abr; }
   public String getDep_Abr () { return dep_abr; }

   public void setAbreviatura (String abreviatura) { this.abreviatura = abreviatura; }
   public String getAbreviatura () { return abreviatura; }
   
   public String getPresentacion() { return presentacion; }
   public void setPresentacion(String presentacion) { this.presentacion = presentacion;  }

   
   public String toString () {
      return "[codi : " + codi + 
             "\n nom : " + nom + 
             "\n present : " + presentacion +
             "\n abreviatura : " + abreviatura + "]";
   }
}