package org.ibit.rol.sac.model;

import java.util.Comparator;

/**
 * Documento de servicio. 
 * @author slromero
 *
 */
public final class DocumentoServicio extends Document implements Comparator<DocumentoServicio> {
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	/** Servicio. **/
	private Servicio servicio;
	/** Orden. **/
	private Long orden;
	
	
	/**
	 * @return the servicio
	 */
	public final Servicio getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(final Servicio servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the orden
	 */
	public Long getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
	}

	@Override
	public final String toString() {
		final Long servicioId=null==servicio?null:servicio.getId();
		return "DocumentoServicio ["+super.toString()+" servicio="+servicioId+" orden="+this.getOrden()+" ]";
	}

	@Override
	public boolean equals(final Object obj) {
		if(!(obj instanceof DocumentoServicio)) {
			return false;
		}
		final DocumentoServicio dt2 = (DocumentoServicio) obj;
		return getId() == dt2.getId();
	}

	@Override
	public int compare(DocumentoServicio o1, DocumentoServicio o2) {
		if (o1 == null || o1.getId() == null) { return -1;}
		if (o2 == null || o2.getId() == null) { return 1;}
		return o1.getId().compareTo(o2.getId());
		
	}
}
