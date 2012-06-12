package org.ibit.rol.sac.model.webcaib;

import org.ibit.rol.sac.model.webcaib.Foto;
import org.ibit.rol.sac.model.webcaib.UOMinModel;

public class UOModel extends UOMinModel implements java.io.Serializable {

	   private String codiDepartament;
	   private String info;
	   private String titolResponsable;
	   private String tractamentResponsable;
	   private String nomResponsable;
	   private String presentacio;
	   private String tipus;
	   private String telefon;
	   private String fax;
	   private String email;
	   private String url;
	   private int foto_p;
	   private int foto_g;
	   private int sexe;

	   private Foto fotoResponsable;
	   
	   public UOModel () {}

	   public String getCodiDepartament () { return codiDepartament; }
	   public void setCodiDepartament (String codiDepartament) { this.codiDepartament = codiDepartament;}

	   public String getInfo () { return info; }
	   public void setInfo (String info) { this.info = info;}

	   public String getTitolResponsable () { return titolResponsable; }
	   public void setTitolResponsable (String titolResponsable) { this.titolResponsable = titolResponsable;}

	   public String getTractamentResponsable () { return tractamentResponsable; }
	   public void setTractamentResponsable (String tractamentResponsable) { this.tractamentResponsable = tractamentResponsable;}

	   public String getNomResponsable () { return nomResponsable; }
	   public void setNomResponsable (String nomResponsable) { this.nomResponsable = nomResponsable;}

	   public String getTipus () { return tipus; }
	   public void setTipus(String tipus) { this.tipus = tipus; }
	   
	   public String getUrl () { return url; }
	   public void setUrl(String url) { this.url = url; }

	   public void setFotoResponsable ( Foto foto ) { this.fotoResponsable = foto; }
	   public Foto getFotoResponsable ( ) { return fotoResponsable; }
	   
	   public void setFoto_p (int foto_p) { this.foto_p = foto_p; }
	   public int getFoto_p () { return foto_p; }

	   public void setFoto_g (int foto_g) { this.foto_g = foto_g; }
	   public int getFoto_g () { return foto_g; }
	   
	   public void setSexe (int sexe) {this.sexe = sexe;}
	   public int getSexe () { return sexe; }
	   
	   /**
	    * @return Returns the email.
	    */
	   public String getEmail() {
	   	return email;
	   }
	   /**
	    * @param email The email to set.
	    */
	   public void setEmail(String email) {
	   	this.email = email;
	   }
	   /**
	    * @return Returns the fax.
	    */
	   public String getFax() {
	   	return fax;
	   }
	   /**
	    * @param fax The fax to set.
	    */
	   public void setFax(String fax) {
	   	this.fax = fax;
	   }
	   /**
	    * @return Returns the telefon.
	    */
	   public String getTelefon() {
	   	return telefon;
	   }
	   /**
	    * @param telefon The telefon to set.
	    */
	   public void setTelefon(String telefon) {
	   	this.telefon = telefon;
	   }


	public String getPresentacio() {
		return presentacio;
	}

	public void setPresentacio(String presentacio) {
		this.presentacio = presentacio;
	}

	@Override
	public String toString() {
		return "UOModel ["+super.toString()+ "codiDepartament=" + codiDepartament + ", email=" + email
				+ ", fax=" + fax + ", fotoResponsable=" + fotoResponsable
				+ ", foto_g=" + foto_g + ", foto_p=" + foto_p + ", info=" + info
				+ ", nomResponsable=" + nomResponsable + ", presentacio="
				+ presentacio + ", sexe=" + sexe + ", telefon=" + telefon
				+ ", tipus=" + tipus + ", titolResponsable=" + titolResponsable
				+ ", tractamentResponsable=" + tractamentResponsable + ", url="
				+ url + "]";
	}

}
