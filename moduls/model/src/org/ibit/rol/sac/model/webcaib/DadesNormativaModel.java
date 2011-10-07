package org.ibit.rol.sac.model.webcaib;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <i>Value object</i> amb la informació corresponent a una normativa.
 * <p>
 * No te cap funcionalitat a part de contenir aquesta informació (amb métodes get/set) i 
 * una sobre-escritura adequada pel mètode toString
 */
public class DadesNormativaModel implements java.io.Serializable {
    
   private String annexe;
   private Date data;
   private String sumari;
   private int numeroPagina; /*dis_pag_xx*/
   private String nomDiari;
   private String abrDiari;
   private String numDiari;
   private Date dataPublicacio;
   private String organismePropietari; 
   private String uoPropietaria;
   private String enllas;
   private String observacions;
   
   
   public DadesNormativaModel () {}
   

   public void setAnnexe(String annexe) { this.annexe = annexe; }
   public String getAnnexe() { return annexe; }

   public void setNomDiari(String nomDiari) { this.nomDiari = nomDiari; }
   public String getNomDiari() { return nomDiari; }
   
   public void setNumDiari(String numDiari) { this.numDiari = numDiari; }
   public String getNumDiari() { return numDiari; }
   
   public void setAbrDiari(String abrDiari) { this.abrDiari = abrDiari; }
   public String getAbrDiari() { return abrDiari; }
   
   
   public void setOrganismePropietari(String organismePropietari) { this.organismePropietari = organismePropietari; }
   public String getOrganismePropietari() { return organismePropietari; }
   
   public void setUoPropietaria(String uoPropietaria) { this.uoPropietaria = uoPropietaria; }
   public String getUoPropietaria() { return uoPropietaria; }
   
   public void setNumeroPagina(int numeroPagina) { this.numeroPagina = numeroPagina; }
   public int getNumeroPagina() { return numeroPagina; }
   
   public void setDataPublicacio ( Date dataPublicacio ) { this.dataPublicacio = dataPublicacio; }
   public Date getDataPublicacio () { return dataPublicacio; }

   public void setData ( Date data ) { this.data = data; }
   public Date getData () { return data; }
   
   public void setSumari (String sumari) { this.sumari = sumari; }
   public String getSumari () { return sumari; }

   public void setEnllas (String enllas) { this.enllas = enllas; }
   public String getEnllas () { return enllas; }
   
   public void setObservacions (String observacions) { this.observacions = observacions; }
   public String getObservacions() { return observacions; }
   
   public String toString() {
	   
	   String tmpData = data != null ? new SimpleDateFormat("dd/MM/yyyy").format(data) : "";
	   String tmpDataPublicacio = dataPublicacio != null ? new SimpleDateFormat("dd/MM/yyyy").format(dataPublicacio) : "";
	   
	   return "[ annexe: " + annexe +
	   		  "\ndata: " + tmpData +
	   		  "\nsumari: " + sumari +
	   		  "\nnumeroPagina: " + numeroPagina + 
	   		  "\nnomDiari: " + nomDiari +
	   		  "\nabrDiari: " + abrDiari +
	   		  "\nnumDiari: " + numDiari +
	   		  "\ndataPulicacio: " + tmpDataPublicacio +
	   		  "\norganismePropietari: " + organismePropietari +
	   		  "\nuoPropietaria: " + uoPropietaria +
	   		  "\nenllaç: " + enllas +
	   		  "\nobservacions: " + observacions +
	   		  "]";
	   
   }

}