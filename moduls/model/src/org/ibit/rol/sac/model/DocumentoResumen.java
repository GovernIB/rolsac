/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.Comparator;


/**
 * @deprecated  u92770 - aquesta clase s'hauria d'eliminar quan les dades del Procediment i Fitxa s'hagin mogut
 * a les taules noves. 
 */

public class DocumentoResumen extends Traducible implements Indexable, Comparator {

/* TODO u02770 - Disseny actual no sembla idoni, pq hi ha poca cohesio. 
 * Potser seria millor crear relacions d'herencia: 
 * Document 						(archivo, orden)
 * Document > DocInformatiuTramite  (tramite)
 * Document > DocPrentarTramite 	(tramite)
 * Document > DocFicha				(ficha)
 * Document > DocProcedimientoLocal (procedimiento) 
 * 
 */
    private Long id;
    private Ficha ficha;
    private ProcedimientoLocal procedimiento;
    private ArchivoResumen archivoResumen;
    
    private long orden;

    
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public ProcedimientoLocal getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoLocal procedimiento) {
        this.procedimiento = procedimiento;
    }

    public ArchivoResumen getArchivoResumen() {
        return archivoResumen;
    }

    public void setArchivoResumen(ArchivoResumen archivoResumen) {
        this.archivoResumen = archivoResumen;
    }

     public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public int compare(Object o1, Object o2) {
    	if (o2 == null) return -1;
    	if (o1 == null) return 1;
	    DocumentoResumen u1 = (DocumentoResumen) o1;
	    DocumentoResumen u2 = (DocumentoResumen) o2;
	    return Long.valueOf(u1.getOrden()).intValue() - Long.valueOf(u2.getOrden()).intValue();
	}
       

	@Override
	public String toString() {

		String nomDoc = obtenirNomDocument();

		String idActuacio=null;
		
		if(null!= ficha) {
			idActuacio="ficha=" + ficha.getId();
		} else 
		if (null!=procedimiento) {
			idActuacio="procedimiento=" + procedimiento.getId() ;
		}
		
		return "Documento [nom=" + nomDoc +", id="+ id + ", orden=" + orden + 
		", "+idActuacio + "]";
	}

	private String obtenirNomDocument() {
		TraduccionDocumentoResumen tradoc = (TraduccionDocumentoResumen)getTraduccion("ca");
		if(null==tradoc) 
			return null;
		return tradoc.getTitulo();
		
	}

}
