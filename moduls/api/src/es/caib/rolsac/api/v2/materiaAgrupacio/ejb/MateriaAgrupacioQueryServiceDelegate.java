package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public class MateriaAgrupacioQueryServiceDelegate {
    
    private MateriaAgrupacioQueryServiceEJBLocator materiaAgrupacioQueryServiceLocator;

    public void setMateriaAgrupacioQueryServiceLocator(MateriaAgrupacioQueryServiceEJBLocator materiaAgrupacioQueryServiceLocator) {
        this.materiaAgrupacioQueryServiceLocator = materiaAgrupacioQueryServiceLocator;
    }

    public MateriaDTO obtenirMateria(Long idMateria) {        
        MateriaAgrupacioQueryServiceEJB ejb = materiaAgrupacioQueryServiceLocator.getMateriaAgrupacioQueryServiceEJB();
        return ejb.obtenirMateria(idMateria);
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) {
        MateriaAgrupacioQueryServiceEJB ejb = materiaAgrupacioQueryServiceLocator.getMateriaAgrupacioQueryServiceEJB();
        return ejb.obtenirAgrupacioMateria(idAgrupacion);
    }

}
