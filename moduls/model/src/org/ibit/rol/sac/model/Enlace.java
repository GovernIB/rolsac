package org.ibit.rol.sac.model;


public class Enlace extends Traducible {

    private Long id;
    private Ficha ficha;
    private ProcedimientoLocal procedimiento;
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
    //      io.addArchivo(archivo);
        /*
        for (Iterator iterator = getTraducciones().values().iterator(); iterator.hasNext();) {
            TraduccionDocumento tr = (TraduccionDocumento) iterator.next();
            if (tr != null) {
                io.addTextLine(tr.getTitulo());
                io.addTextLine(tr.getDescripcion());
                io.addArchivo(tr.getArchivo());
            }
        }
        */

        return io;
    }
}
