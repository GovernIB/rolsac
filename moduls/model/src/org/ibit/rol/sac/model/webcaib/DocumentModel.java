package org.ibit.rol.sac.model.webcaib;

/**
 * Conté informació d'un document d'una actuació
 */
public class DocumentModel implements java.io.Serializable {

   /** codi de l'actuaci� al que pertany el document */
   int codi;
   
   // els valors s'inicialitzen a cadenes buides per evitar que surti
   // el missatge null al imprimir-ho
   
   /** nom del document */
   String titol = "";
   
   String descripcio = "";
   
   String urlImage = "";
   
   int pes = 0;
   
   int arxiu;

   
   /** constructor buid */
   public DocumentModel () {}
   
   /** fitxa el valor del codi de l'actuació del document */
   public void setCodi(int codi) { this.codi = codi; }
   
   /** retorna el valor del codi de l'actuació del document */
   public int getCodi () { return codi; }
   
   
/**
 * @return
 */
public int getArxiu() {
	return arxiu;
}

/**
 * @return
 */
public String getDescripcio() {
	return descripcio;
}

/**
 * @return
 */
public String getTitol() {
	return titol;
}

/**
 * @param i
 */
public void setArxiu(int i) {
	arxiu = i;
}

/**
 * @param string
 */
public void setDescripcio(String string) {
	descripcio = string;
}

/**
 * @param string
 */
public void setTitol(String string) {
	titol = string;
}

public String getUrlImage() {
	return urlImage;
}

public void setUrlImage(String urlImage) {
	this.urlImage = urlImage;
}

public int getPes() {
	return pes;
}

public void setPes(int pes) {
	this.pes = pes;
}





}