package org.ibit.rol.sac.model.webcaib;

import java.util.Collection;
import java.util.Date;

public class LinkModel implements java.io.Serializable {

    int codi;
    int imatge;
    int banner;
    int icono;
    
    Foto gran;
    Foto petita;
    String nom ;
    String desc;
    String descabr;
    String url;
    Date data;
    Date dataAct;    
    String uo;
    String urlVideo;
    String urlForo;

    
    
    Collection documents = new java.util.Vector();

    
    public LinkModel() {}
    
    public LinkModel(LinkModel lmodel) {
    	this.codi=lmodel.getCodi();
    	this.imatge=lmodel.getImatge();
    	this.banner=lmodel.getBanner();
    	this.icono=lmodel.getIcono();
    	this.gran=lmodel.getFotoGran();
    	this.petita=lmodel.getFotoPetita();
    	this.nom=lmodel.getNom();
    	this.desc=lmodel.getDesc();
    	this.descabr=lmodel.getDescabr();
    	this.url=lmodel.getUrl();
    	this.data=lmodel.getData();
    	this.dataAct=lmodel.getDataAct();
    	this.uo=lmodel.getUo();
    	this.urlVideo=lmodel.getUrlVideo();
    	this.urlForo=lmodel.getUrlForo();
    	this.documents=lmodel.getDocuments();
    }
 
    public void setCodi (int codi) { this.codi = codi; }
    public int getCodi () { return codi; }
    
	public void setImatge (int imatge) { this.imatge = imatge; }
    public int getImatge () { return imatge; }
    
	public void setBanner (int banner) { this.banner = banner; }
	public int getBanner () { return banner; }

	public void setIcono (int icono) { this.icono = icono; }
	public int getIcono () { return icono; }
    
	public void setUrlVideo (String urlVideo) { this.urlVideo = urlVideo; }
	public String getUrlVideo () { return urlVideo; }
	
	public void setUrlForo (String urlForo) { this.urlForo = urlForo; }
	public String getUrlForo () { return urlForo; }		

    public void setNom (String nom) { this.nom = nom; }
    public String getNom () { return nom; }

    public void setDesc (String desc) { this.desc = desc; }
    public String getDesc () { return desc; }
    
	public void setDescabr (String descabr) { this.descabr = descabr; }
	public String getDescabr () { return descabr; }

    public void setUrl (String url) { this.url = url; }
    public String getUrl () { return url; }

    public void setData (Date data) { this.data = data; }
    public Date getData () { return data; }
    
    public void setDataAct (Date data) { this.dataAct = data; }
    public Date getDataAct () { return dataAct; }    

    public void setFotoGran (Foto gran) { this.gran = gran; }
    public Foto getFotoGran() { return gran; }

    public void setFotoPetita (Foto petita) { this.petita = petita; }
    public Foto getFotoPetita() { 
      if (petita == null ) return gran;
      return petita; 
    }

    public void setUo (String uo) { this.uo = uo; }
    public String getUo () { return uo; }

    public Collection getDocuments () { return documents; }
    
 	public void addDocuments (DocumentModel doc) {
 	   if (doc != null) {
 		 documents.add(doc); 
 	   }
 	}


    public String toString() {
       return "[codi : " + codi +
             "\n nom : " + nom +
             "\n desc : " + desc +
             "\n url : " + gran +
             "\n uo : " + uo +
             "\n foto gran : " + gran +
             "\n foto petita : " + petita +
             "\n url video : " + urlVideo +
             "\n url foro : " + urlForo +             
             "\n data : " + data +
             "]";
    }
}