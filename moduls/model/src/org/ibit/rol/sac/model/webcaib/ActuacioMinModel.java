package org.ibit.rol.sac.model.webcaib;

import java.sql.Date;

/**
 * Classe amb la informaci� m�nima d'una actuaci�
 */
public class ActuacioMinModel implements java.io.Serializable {

   // els valors s'inicialitzen a cadenes buides per evitar que surti
   // el missatge null al imprimir-ho
   
   /** codi de l'actuaci� */
   String codi = "";
   
   /** nom de l'actuaci� */
   String nom = "";
   
   /** resum de l'actuaci� */
   String resum = "";
   
   /** destinataris de l'actuaci� */
   String destinataris = "";
   
   /** beneficiaris de l'actuaci� */
   String beneficiaris = "";
   
   /** terminis de l'actuaci� */
   String terminis = "";
   
   /** resolucion de l'actuaci� */
   String resolucion = "";
   
   /** notificacion de l'actuaci� */
   String notificacion = "";
   
   /** terminis de l'actuaci� */
   String organismeGenerador = "";
   

   /** Identificador del tr�mite telem�tico */
   String idTramite = "";

   /** Version del tr�mite telem�tico */
   int versionTramite = 0;
   
   /** Url externa del tr�mite telem�tico */
   String urlExternaTramite = "";     
   
   /** data actualitzaci� */
   Date data = null;
   
   /**
    * Constructor buid. No fica ninguna informaci�.
    */
   public ActuacioMinModel () {}
   
   /** Fitxa el valor del camp  */
   public void setCodi (String codi) { this.codi = codi;}

   /** Retorna el valor del camp  */
   public String getCodi () { return codi; }
   
   /** Fitxa el valor del camp nom */
   public void setNom (String nom) { this.nom = nom; }

   /** Retorna el valor del camp nom */
   public String getNom () { return nom; }
   
   /** Fitxa el valor del camp resum */
   public void setResum (String resum) { this.resum = resum;} 

   /** Retorna el valor del camp resum */
   public String getResum () { return resum; }
   
   /** Fitxa el valor del camp destinataris */
   public void setDestinataris (String destinataris) { this.destinataris = destinataris;}

   /** Retorna el valor del camp destinataris */
   public String getDestinataris () { return destinataris; }
   
   /** Fitxa el valor del camp beneficiaris */
   public void setBeneficiaris (String beneficiaris) { this.beneficiaris = beneficiaris;}

   /** Retorna el valor del camp beneficiaris */
   public String getBeneficiaris () { return beneficiaris; }

   /** Fitxa el valor del camp terminis */
   public void setTerminis (String terminis) { this.terminis = terminis;} 

   /** Retorna el valor del camp terminis */
   public String getTerminis () { return terminis; }

   /** Fitxa el valor del camp resolucion*/
   public void setResolucion (String resolucion) { this.resolucion = resolucion;} 
   
   /** Retorna el valor del camp resolucion */
   public String getResolucion() { return resolucion; }

   /** Fitxa el valor del camp notificacion*/
   public void setNotificacion (String notificacion) { this.notificacion = notificacion;} 
   
   /** Retorna el valor del camp notificacion */
   public String getNotificacion() { return notificacion; }

   
   /** Fitxa el valor del camp terminis */
   public void setOrganismeGenerador (String organismeGenerador) { this.organismeGenerador = organismeGenerador;} 

   /** Retorna el valor del camp terminis */
   public String getOrganismeGenerador () { return organismeGenerador; }

   
   /** Fitxa el valor del camp data */
   public void setData (Date data) { this.data = data;}
   
   /** Retorna el valor del camp data */
   public Date getData() { return data; }

   /** retorna el identificador del tràmit */
   public String getIdTramite() {	return idTramite;}
  /** Fitxa el identificador del tràmit */
	public void setIdTramite(String idTramite) { this.idTramite = idTramite;}

	/** retorna la versio del tràmit */
	public int getVersionTramite() {	return versionTramite;}

	/** Fitxa la versio del tràmit */
	public void setVersionTramite(int versionTramite) {	this.versionTramite = versionTramite;}   
	
	/** retorna la url externa del tràmit */
	public String getUrlExternaTramite() {	return urlExternaTramite;}
   	
	/** Fitxa la url externa del tràmit */
	public void setUrlExternaTramite(String urlExternaTramite) { this.urlExternaTramite = urlExternaTramite;}   	


	/**
    * retorna una representació adequada en forma d'string dels valors d'aquest objecte.
    *
    * @return cadena representant l'estat d'aquest objecte
    */
   public String toString () { return "[codi : " + codi + 
                                      "\n nom : " + nom + 
                                      "\n resum : " + resum +
                                      "\n destinataris : " + destinataris +
                                      "\n beneficiaris : " + beneficiaris +
                                      "\n id : " + idTramite  + 
                                      "\n versio : " + versionTramite  + 
                                      "\n resolucion : " + resolucion  +
                                      "\n notificacion: " + notificacion  +
                                      "\n terminis : " + terminis  + 
                                      "\n uo : " + organismeGenerador +
                                      "\n data : " + data +
                                      "\n url : " + urlExternaTramite +"\n]";
    }


}