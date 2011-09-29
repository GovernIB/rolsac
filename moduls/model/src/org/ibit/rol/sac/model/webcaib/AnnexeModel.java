package org.ibit.rol.sac.model.webcaib;

public class AnnexeModel implements java.io.Serializable {

	   private int codi;
	   private String nomarx;
	   private String arxiu;
	   private int codcom;
	   private String descar;	   
	   
	   public void setCodi (int codi) { this.codi = codi; }
	   public int getCodi () { return codi; }

	   public void setNomarx (String nomarx) { this.nomarx = nomarx; }
	   public String getNomarx () { return nomarx; }

	   public void setArxiu (String arxiu) { this.arxiu = arxiu; }
	   public String getArxiu () { return arxiu; }

	   public void setCodcom (int codcom) { this.codcom = codcom; }
	   public int getCodcom () { return codcom; }

	   public void setDescar (String descar) { this.descar = descar; }
	   public String getDescar () { return descar; }
	   
	   public String toString() {
		   return  "[codi: " + codi +
		   		   "arxiu: " + arxiu +
		   		   "]";
	   }
}       