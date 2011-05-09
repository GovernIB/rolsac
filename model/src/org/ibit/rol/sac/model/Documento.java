/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.Iterator;


/**
 * @deprecated  u92770 - aquesta clase s'hauria d'eliminar quan les dades del Procediment i Fitxa s'hagin mogut
 * a les taules noves. 
 */

public class Documento extends Traducible implements Indexable {

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
    private Archivo archivo;
    
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

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

     public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    
    
    public IndexObject indexObject() {
        final IndexObject io = new IndexObject();
    //    io.setId(id);
    //    io.setClassName(Documento.class.getName());
          io.addArchivo(archivo);
        for (Iterator iterator = getTraducciones().values().iterator(); iterator.hasNext();) {
            TraduccionDocumento tr = (TraduccionDocumento) iterator.next();
            if (tr != null) {
                io.addTextLine(tr.getTitulo());
                io.addTextLine(tr.getDescripcion());
                io.addArchivo(tr.getArchivo());
            }
        }

        return io;
    }




	@Override
	public String toString() {

		long fichaid=ficha==null?-1:ficha.getId();
		long procid=procedimiento==null?-1:procedimiento.getId();

		return "Documento [archivo=..." +", ficha=" + fichaid + 
		", id="+ id + ", orden=" + orden + ", " +
		"procedimiento=" + procid + "]";
	}


}
