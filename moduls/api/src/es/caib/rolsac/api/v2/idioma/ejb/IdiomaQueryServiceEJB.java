package es.caib.rolsac.api.v2.idioma.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.general.HibernateEJB;

/**
 * SessionBean para consultas de familia.
 *
 * @ejb.bean
 *  name="sac/api/IdiomaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.idioma.ejb.IdiomaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class IdiomaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 1557458996303347630L;
    
    /* amartin: dejo este bloque de código comentado para que se haga uso de él si posteriormente es necesario.
    
    private static Log log = LogFactory.getLog(IdiomaQueryServiceEJB.class);

    private static final String HQL_IDIOMA_CLASS = "Idioma";
    private static final String HQL_IDIOMA_ALIAS = "idi";
    
    */
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

}
