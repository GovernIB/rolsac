/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.Iterator;


public class NormativaLocal extends Normativa implements Indexable {

    public UnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    private UnidadAdministrativa unidadAdministrativa;

    /*
    public IndexObject indexObject() {
        final IndexObject io = super.indexObject();
        for (Iterator iterator = getTraducciones().values().iterator(); iterator.hasNext();) {
            TraduccionNormativa tr = (TraduccionNormativa) iterator.next();
            if (tr != null) {
                io.addTextLine(tr.getApartado());
                io.addTextLine(tr.getObservaciones());
                io.addTextLine(tr.getSeccion());
                io.addTextLine(tr.getTitulo());
                io.addArchivo(tr.getArchivo());
            }
        }
        return io;
    }
    */
}
