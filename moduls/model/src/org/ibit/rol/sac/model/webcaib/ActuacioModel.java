package org.ibit.rol.sac.model.webcaib;

import java.util.Date;
import java.util.Collection;


/**
 * Classe que conte informacio detallada d'una actuacio
 */
public class ActuacioModel extends ActuacioMinModel implements java.io.Serializable {

   // els valors s'inicialitzen a cadenes buides per evitar que surti
   // el missatge null al imprimir-ho
   
   /** observacions de l'actuació */
   String observacions = "";

   String resultat ="";
   
   /** tramits de l'actuació */
   @Deprecated
   String tramits = "";
   
   /** requisits de l'actuació */
   @Deprecated
   String requisits = ""; 
   
   /** documents de l'actuació */
   Collection<DocumentModel> documents = new java.util.ArrayList<DocumentModel>();
   
   /** normatives de l'actuació */
   Collection<NormativaModel> normatives = new java.util.ArrayList<NormativaModel>();
   
   /** organisme generador de l'actuació */
   String organismeGenerador = "";
   
   /** codi de l'organisme generador de l'actuació */
   String codiOrganismeGenerador = "";
   
   /** organismes gestors de l'actuació */
   Collection<String> organismesGestors = new java.util.ArrayList<String>();
   
   /** organismes resolutoris de l'actuació */
   Collection<String> organismesResolutoris = new java.util.ArrayList<String>();
   
   /** tramits  de l'actuació */
	Collection<TramitModel> tramit = new java.util.ArrayList<TramitModel>();
  
   /** data de la darrera actualitzacio de l'actuacio */
   Date dataActualitzacio; 
   
   /** silenci administratiu de l'actuacio */
   String silenci = "";
   
   /** familia a la que pertany l'actuacio */
   String familia = "";
   
   /** iniciacion de  l'actuacio */
   String iniciacion = "";
   
   /** indicador agotament */
   String indicador = "";
   
   /** rescursos de l'actuacio */
   String recursos = "";
   
   /** data de caducitat del procediment */
   Date data_caducitat = null;

   /** data de publicacio del procediment */
   Date data_publicacio = null;
   
   /** rescursos de l'actuacio */
   @Deprecated
   String lloc = "";
   
   
   boolean taxa;

   /** origens de l'actuacio */
   Collection<String> origens = new java.util.ArrayList<String>();
   
   /**
    * <p>
    * N�mero d'expedients tramitats anualment.
    * </p>
    * <p>
    * Est� com a tipus Integer en lloc del primitiu int per permetre valors nulls.
    * </p>
    */
   Integer volumAnual ; /* num_exp */
   
   /**
    * <p>
    * Centres relacionats amb l'actuaci� de l'actuaci� ( col.lecci� de LlocModel )
    * </p>
    * <p>
    * A la base de dades es guarda en les relacions : ACT - ACT_PRES - CENTRE
    * </p>
    * @see es.caib.sac.unitatOrganica.model.LlocModel
    */
   Collection<LlocModel> llocs = new java.util.ArrayList<LlocModel>();
   
      
   /**
    * Constructor buid. No fica ninguna informaci�.
    */
   public ActuacioModel () {}
   
   public void setObservacions (String observacions) { this.observacions = observacions;}
   public String getObservacions () { return observacions; }

   @Deprecated
   public void setTramits (String tramits) { this.tramits = tramits;}
   @Deprecated
   public String getTramits () { return tramits; }

   @Deprecated
   public void setRequisits (String requisits) { this.requisits = requisits;}
   @Deprecated
   public String getRequisits () { return requisits; }



   public void addNormativa (NormativaModel normativa) {
      if (normativa != null) {
        normatives.add(normativa); 
      }
   }
   
   public Collection<DocumentModel> getDocuments () { return documents; }
   
	public void addDocuments (DocumentModel doc) {
	   if (doc != null) {
		 documents.add(doc); 
	   }
	}
 
   public Collection<TramitModel> getTramit () { return tramit; }
   
   public void addTramit (TramitModel tram) {
	  if (tram != null) {
		tramit.add(tram); 
	  }
   }
   public Collection<NormativaModel> getNormatives () { return normatives; }

   
   
   public void addOrganismeGestor (String organisme) { organismesGestors.add(organisme); }
   public Collection<String> getOrganismesGestors () { return organismesGestors; }
   
   public void addOrganismeResolutori (String organisme) { organismesResolutoris.add(organisme); }
   public Collection<String> getOrganismesResolutoris () { return organismesResolutoris; }

   public void setOrganismeGenerador (String organismeGenerador) { 
        this.organismeGenerador = organismeGenerador;
   }
   public String getOrganismeGenerador () { return organismeGenerador; }
   
   public void setCodiOrganismeGenerador (String codiOrganismeGenerador) { 
        this.codiOrganismeGenerador = codiOrganismeGenerador;
   }
   public String getCodiOrganismeGenerador () { return codiOrganismeGenerador; }
   

   public void setSilenci(String silenci) { this.silenci = silenci; }
   public String getSilenci () { return silenci; }
   
   public void setRecursos (String recursos) { this.recursos = recursos; }
   public String getRecursos () { return recursos; }
   
   public void setFamilia (String familia) { this.familia = familia; }
   public String getFamilia () { return familia; }
   
   public void setIniciacion (String iniciacion) { this.iniciacion = iniciacion; }
   public String getIniciacion () { return iniciacion; }
   
   public void setIndicador (String indicador) { this.indicador = indicador; }
   public String getIndicador () { return indicador; }
   
   public void setData_caducitat (Date data_caducitat) { this.data_caducitat = data_caducitat; }
   public Date getData_caducitat () { return data_caducitat; }
   
   public void setData_publicacio (Date data_publicacio) { this.data_publicacio = data_publicacio; }
   public Date getData_publicacio () { return data_publicacio; }
   
   public void addOrigen (String origen) { origens.add(origen); }
   public Collection<String> getOrigens () { return origens; }
   
   @Deprecated
   public void setLloc (String lloc) { this.lloc = lloc; }
   @Deprecated
   public String getLloc () { return lloc; }

   
   public void addLloc (LlocModel lloc) { llocs.add(lloc); }
   public Collection<LlocModel> getLlocs () { return llocs; }
      
   /**
    * El camp NUM_EXP ( camp que guarda el volum anual per una actuacio ) pot esser null. L'acces
    * amb JDBC a una columna que te un valor null retorna l'enter 0. En el codi, feim que si el 
    * valor enviat es 0, la referencia this.volumAnual queda a null.
    *
    * Evidentment, aix� fallaria en cas de que volumActual fos efectivament 0 a la base de dades ( i no null )
    * Suposam que aquest darrer cas NO es possible
    */
   public void setVolumAnual (int volumAnual) {
        this.volumAnual = (volumAnual > 0? new Integer(volumAnual): null); 
   }
   public Integer getVolumAnual () { return volumAnual; }

   public void setDataActualitzacio (Date dataActualitzacio ) { this.dataActualitzacio = dataActualitzacio; }
   public Date getDataActualitzacio () { return dataActualitzacio; }
   
   private <T> String itera (Collection<T> c) {
      String result = "";
      java.util.Iterator<T> i = c.iterator();
      while (i.hasNext()) {
         result += "\n" + i.next();
      }    
      result += "\n----------------------------------------------------";
      return result;
   }
   

   public String getResultat() {
	return resultat;
}

public void setResultat(String resultat) {
	this.resultat = resultat;
}

public boolean isTaxa() {
	return taxa;
}

public void setTaxa(boolean taxa) {
	this.taxa = taxa;
}

/**
    * retorna una representaci� adequada en forma d'string dels valors d'aquest objecte.
    *
    * @return cadena representant l'estat d'aquest objecte
    */
   // inclou camps que son heretats d'ActuacioMinModel
   public String toString () { return "[codi : " + codi + 
                                      "\n nom : " + nom + 
                                      "\n resum : " + resum +
                                      "\n destinataris : " + destinataris +
                                      "\n beneficiaris : " + beneficiaris +
                                      "\n observacions : " + observacions + 
                                      "\n terminis : " + terminis +
                                      "\n documents : " + documents +
                                      "\n normatives : " + itera(normatives) +
                                      "\n organisme generador : " + organismeGenerador +
                                      "\n organismes gestors : " + itera(organismesGestors) +
                                      "\n organismes resolutoris : " + itera(organismesResolutoris) +
                                      "\n sileci : " + silenci +
                                      "\n familia : " + familia + 
                                      "\n iniciacion : " + iniciacion + 
                                      "\n indicador: " + indicador +
                                      "\n recursos : " + recursos +
                                      "\n data actualitzacio : " + dataActualitzacio + "\n]";
    }


}