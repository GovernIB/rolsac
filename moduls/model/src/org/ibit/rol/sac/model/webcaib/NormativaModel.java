package org.ibit.rol.sac.model.webcaib;

import java.util.Date;

/**
 * <i>Value object</i> amb la informació corresponent a una normativa.
 * <p>
 * No te cap funcionalitat a part de contenir aquesta informació (amb métodes get/set) i 
 * una sobre-escritura adequada pel métode toString
 */
public class NormativaModel implements java.io.Serializable {
    
   private int codi;
   private Date data;
   private String origen;
   private String sumari;
   private String abr;
   private String errades;
   private Date dataActualitzacio;
   private String numero; /*cdi_num*/
   private String numeroBoib; /*cdi_diari*/
   private int numeroPagina; /*dis_pag_xx*/
   private int pagini;
   private int pagfin;
   private int arxiu;
   
   public NormativaModel () {}
   
   public void setNumero(String numero) { this.numero = numero; }
   public String getNumero() { return numero; }
   
   public void setAbr(String abr) { this.abr = abr; }
   public String getAbr() { return abr; }
   
   public void setOrigen(String origen) { this.origen = origen; }
   public String getOrigen() { return origen; }
   
   
   public void setNumeroBoib(String numeroBoib) { this.numeroBoib = numeroBoib; }
   public String getNumeroBoib() { return numeroBoib; }
   
   public void setNumeroPagina(int numeroPagina) { this.numeroPagina = numeroPagina; }
   public int getNumeroPagina() { return numeroPagina; }
   
   public void setCodi(int codi) { this.codi = codi; }
   public int getCodi () { return codi; }

   public void setData ( Date data ) { this.data = data; }
   public Date getData () { return data; }
   
   public void setSumari (String sumari) { this.sumari = sumari; }
   public String getSumari () { return sumari; }
   
   public void setErrades (String errades) { this.errades = errades; }
   public String getErrades () { return errades; }

   public void setDataActualitzacio (java.util.Date dataActualitzacio ) { this.dataActualitzacio = dataActualitzacio; }
   public java.util.Date getDataActualitzacio () { return dataActualitzacio; }
   
   public void setArxiu(int arxiu) { this.arxiu = arxiu; }
   public int getArxiu() { return arxiu; }   
      
   public String toString () { return "[codi : " + codi +
                                       "\ndata : " + data +
                                       "\nsumari : " + sumari +
                                       "\nerrades : " + errades +
                                       "\ndata actualitzacio : " + dataActualitzacio +  "]";
   }

/**
 * @return
 */
public int getPagfin() {
	return pagfin;
}

/**
 * @return
 */
public int getPagini() {
	return pagini;
}

/**
 * @param i
 */
public void setPagfin(int i) {
	pagfin = i;
}

/**
 * @param i
 */
public void setPagini(int i) {
	pagini = i;
}

}