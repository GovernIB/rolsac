package org.ibit.rol.sac.model;

public class DocumentTramit extends Document  {
	
	private Tramite tramit;
	private CatalegDocuments docCatalogo;
	private ExcepcioDocumentacio excepcioDocumentacio;

	public Tramite getTramit() {
		return tramit;
	}

	public void setTramit(Tramite tramit) {
		this.tramit = tramit;
	}
	
  public CatalegDocuments getDocCatalogo() {
    return docCatalogo;
  }

  public void setDocCatalogo(CatalegDocuments docCatalogo) {
    this.docCatalogo = docCatalogo;
  }

  public ExcepcioDocumentacio getExcepcioDocumentacio() {
    return excepcioDocumentacio;
  }
  
  public void setExcepcioDocumentacio(ExcepcioDocumentacio excepcioDocumentacio) {
    this.excepcioDocumentacio = excepcioDocumentacio;
  }
    

	@Override
	public String toString() {
		Long tid=null==tramit?null:tramit.getId();
		Long docCatid=null==docCatalogo?null:docCatalogo.getId();
    return "DocumentTramit ["+super.toString()+" tramite="+tid+" docCataleg="+docCatid+" ]";
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DocumentTramit)) return false;
		DocumentTramit dt2=(DocumentTramit)obj;
		return getId() == dt2.getId();
	}
}
