package org.ibit.rol.sac.model;

/**
 * Documento de normativa. 
 * @author slromero
 *
 */
public class DocumentoNormativa extends Document  {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	/** Normativa. **/
	private Normativa normativa;
	/** URL.  **/
	private String url;
	
	/**
	 * @return the normativa
	 */
	public final Normativa getNormativa() {
		return normativa;
	}

	/**
	 * @param normativa the normativa to set
	 */
	public final void setNormativa(Normativa normativa) {
		this.normativa = normativa;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public final String toString() {
		final Long normativaId=null==normativa?null:normativa.getId();
		return "DocumentoNormativa ["+super.toString()+" normativa="+normativaId+" orden="+this.getOrden()+" ]";
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DocumentoNormativa)) {
			return false;
		}
		final DocumentoNormativa dt2 = (DocumentoNormativa) obj;
		return getId() == dt2.getId();
	}
}
