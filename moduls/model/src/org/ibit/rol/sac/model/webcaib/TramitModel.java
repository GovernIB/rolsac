package org.ibit.rol.sac.model.webcaib;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Cont� informació d'un document d'una actuació
 */
public class TramitModel implements java.io.Serializable {

   /** codi del tramit al que pertany el document */
   int codi;
   
   // els valors s'inicialitzen a cadenes buides per evitar que surti
   // el missatge null al imprimir-ho
   
   boolean caducat=false;
   
   /** documentacio */
   String documentacio = "";

   /** requisits */
   String requisits = "";

   /** fase del tramit */
   String fase = "";
	  
	/** nom del tramit */
    String titol = "";
    
	/** descripcio del tramit */
	String descripcio = "";
	
	/** termini del tramit */
	String termini = "";	  
   
	String lugar="";
	
	Collection<DocumentModel> docsInformatius = new ArrayList<DocumentModel>(); 
	Collection<DocumentModel> formularis = new ArrayList<DocumentModel>();
	Collection<TaxaModel> taxes = new ArrayList<TaxaModel>();
	
   /** constructor buid */
   public TramitModel () {}
   
   /** fitxa el valor del codi de l'actuació del document */
   public void setCodi(int codi) { this.codi = codi; }
   
   /** retorna el valor del codi de l'actuació del document */
   public int getCodi () { return codi; }
   
 
	/**
	 * @return
	 */
	public String getDescripcio() {
		return descripcio;
	}

/**
 * @return
 */
public String getDocumentacio() {
	return documentacio;
}

/**
 * @return
 */
public String getFase() {
	return fase;
}

	/**
	 * @return
	 */
	public String getTermini() {
		return termini;
	}

	/**
	 * @return
	 */
	public String getTitol() {
		return titol;
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
public void setDocumentacio(String string) {
	documentacio = string;
}

/**
 * @param string
 */
public void setFase(String string) {
	fase = string;
}

	/**
	 * @param string
	 */
	public void setTermini(String string) {
		termini = string;
	}

	/**
	 * @param string
	 */
	public void setTitol(String string) {
		titol = string;
	}


	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public void addDocInformatiu(DocumentModel doc) {
		docsInformatius.add(doc);
	}

	public Collection<DocumentModel> getDocsInformatius() {
		return docsInformatius;
	}

	public void addFormulari(DocumentModel form) {
		formularis.add(form);
	}

	
	public Collection<DocumentModel> getFormularis() {
		return formularis;
	}
	

	public void addTaxa(TaxaModel tax) {
		taxes.add(tax);
	}

	
	public Collection<TaxaModel> getTaxes() {
		return taxes;
	}
	


	public String getRequisits() {
		return requisits;
	}

	public void setRequisits(String requisits) {
		this.requisits = requisits;
	}


	public boolean isCaducat() {
		return caducat;
	}

	public void setCaducat(boolean caducat) {
		this.caducat = caducat;
	}

	@Override
	public String toString() {
		return "TramitModel [codi=" + codi + ", descripcio=" + descripcio +
				", documentacio=" + documentacio + ", fase=" + fase +
				", requisits=" + requisits +
				", lugar=" + lugar +
				", lugar=" + lugar + 
				", termini=" + termini +
				", titol=" + titol +
				", taxes=" + taxes +
				", docsInformatius=" + docsInformatius +
				", formularis=" 	+ formularis +
				"]";
	}
	
	

	

	
}