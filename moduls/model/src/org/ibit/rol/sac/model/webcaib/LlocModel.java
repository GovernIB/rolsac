package org.ibit.rol.sac.model.webcaib;

public class LlocModel implements java.io.Serializable {

   private String nom = "";
   private String codiUO = "";
   private String longitud = "";
   private String latitud = "";
   private String adreca = "";
   private String poblacio = "";
   private String codiPostal = "";
   private String comunitat = "";
   private String telefon = "";
   private String fax = "";
   private String email = "";
   private String html = "";
   private String descripcio ="";
   private int    fotoEdificiPetita;
   private int    fotoEdifici;
   private int    mapa;
//   private Foto   fotoEdificiPetita;
//   private Foto   fotoEdifici;
//   private Foto   mapa;
   
   public void setNom (String nom)  {this.nom = nom; }
   public String getNom () { return nom;}
   
   public void setCodiUO (String codiUO)  {this.codiUO = codiUO; }
   public String getCodiUO () { return codiUO;}
   
   public void setLongitud (String longitud)  {this.longitud = longitud; }
   public String getLongitud () { return longitud;}   
   
   public void setLatitud (String latitud)  {this.latitud = latitud; }
   public String getLatitud () { return latitud;}    
   
   public void setAdreca (String adreca)  {this.adreca = adreca; }
   public String getAdreca () { return adreca;}
   
   public void setPoblacio (String poblacio)  {this.poblacio = poblacio; }
   public String getPoblacio () { return poblacio;}
   
   public void setCodiPostal (String codiPostal)  {this.codiPostal = codiPostal; }
   public String getCodiPostal () { return codiPostal;}
   
   public void setComunitat (String comunitat)  {this.comunitat = comunitat; }
   public String getComunitat () { return comunitat;}
   
   public void setTelefon (String telefon)  {this.telefon = telefon; }
   public String getTelefon () { return telefon;}
   
   public void setFax (String fax)  {this.fax = fax; }
   public String getFax () { return fax;}
   
   public void setEmail (String email)  {this.email = email; }
   public String getEmail () { return email;}
   
   public void setHtml (String html) { this.html = html; }
   public String getHtml () { return html; }
   
   public void setDescripcio (String descripcio)  {this.descripcio = descripcio; }
   public String getDescripcio () { return descripcio;}
 
   
   public void setFotoEdifici ( int foto ) { this.fotoEdifici = foto; }
   public int getFotoEdifici ( ) { return fotoEdifici; }
   
   public void setFotoEdificiPetita ( int foto ) { this.fotoEdificiPetita = foto; }
   public int getFotoEdificiPetita ( ) { return fotoEdificiPetita; }
   
   public void setMapa ( int mapa ) { this.mapa = mapa; }
   public int getMapa ( ) { return mapa; }


/* public void setFotoEdifici ( Foto foto ) { this.fotoEdifici = foto; }
   public Foto getFotoEdifici ( ) { return fotoEdifici; }
   
   public void setFotoEdificiPetita ( Foto foto ) { this.fotoEdificiPetita = foto; }
   public Foto getFotoEdificiPetita ( ) { return fotoEdificiPetita; }
   
   public void setMapa ( Foto mapa ) { this.mapa = mapa; }
   public Foto getMapa ( ) { return mapa; }
*/   
   
   public LlocModel () {}

   public String toString () {
      return "[adreca : " + adreca +
             "\n poblacio : " + poblacio +
             "\n codi postal : " + codiPostal +
             "\n comunitat : " + comunitat +
             "\n telefon : " + telefon +
             "\n fax : " + fax +
             "\n email : " + email +
             "\n lng :" + longitud +
             "\n lat :" + latitud +             
             "\n html : " + html + "]";
   }
}