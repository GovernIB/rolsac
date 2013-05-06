
/**
 * @author u92770
 */
package org.ibit.rol.sac.model;

import java.util.Iterator;


	
public abstract class Document extends Ordenable {
	public static final int DOCINFORMATIU=0; 
	public static final int FORMULARI=1;
	//public static final int DOCPRESENTAR=2;
	public static final int REQUERIT=2;
	public static final int MODELCOMU=3;
	
	
	private Archivo archivo;
	int tipus;


	public IndexObject indexObject() {
        final IndexObject io = new IndexObject();
    //    io.setId(id);
    //    io.setClassName(Documento.class.getName());
          io.addArchivo(archivo);
        for (Iterator iterator = getTraducciones().values().iterator(); iterator.hasNext();) {
            TraduccionDocumentTramit tr = (TraduccionDocumentTramit) iterator.next();
            if (tr != null) {
                io.addTextLine(tr.getTitulo());
                io.addTextLine(tr.getDescripcion());
                io.addArchivo(tr.getArchivo());
            }
        }

        return io;
    }


    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }


	public int getTipus() {
		return tipus;
	}


	public void setTipus(int tipus) {
		this.tipus = tipus;
	}


	@Override
	public String toString() { 
		return "Document[id="+ getId() +" tipus="+ tipusToString(tipus)+ " archivo=..." +  
		", orden=" + getOrden() +"]"; 
	}

	private String tipusToString(int tipus) {
		String s=null;
		switch(tipus) {
		case DOCINFORMATIU:return "docInformatiu";
		case FORMULARI:return "formulari";
		//case DOCPRESENTAR:return "docPresentar";
		case REQUERIT: return "docRequerit";

		}
		return null;
	}

}
