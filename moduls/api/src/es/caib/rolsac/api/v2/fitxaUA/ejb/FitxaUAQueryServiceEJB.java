package es.caib.rolsac.api.v2.fitxaUA.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de fichasUA.
 *
 * @ejb.bean
 *  name="sac/api/FitxaUAQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.fitxaUA.ejb.FitxaUAQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class FitxaUAQueryServiceEJB extends HibernateEJB {
    
    private static final long serialVersionUID = -7028731945181431100L;
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la ficha.
     * @param idFitxa
     * @return FitxaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(String.valueOf(idFitxa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirFitxa(fitxaCriteria);
    }

    /**
     * Obtiene la seccion.
     * @param idSeccio
     * @return SeccioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public SeccioDTO obtenirSeccio(long idSeccio) {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId(String.valueOf(idSeccio));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirSeccio(seccioCriteria);
    }

    /**
     * Obtiene la unidad administrativa.
     * @param idUnitatAdministrativa
     * @return UnitatAdministrativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId(String.valueOf(idUnitatAdministrativa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
    }

}
