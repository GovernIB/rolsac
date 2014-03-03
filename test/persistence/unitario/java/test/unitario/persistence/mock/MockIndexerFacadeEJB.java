package test.unitario.persistence.mock;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import org.ibit.rol.sac.persistence.ejb.IndexerFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;

public class MockIndexerFacadeEJB extends IndexerFacadeEJB {

    private static final long serialVersionUID = -819161804683118597L;

    AccesoManagerLocal accesoManager;

    public void ejbActivate() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void ejbPassivate() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public void setIndexLocation(String indexLocation) {
        this.indexLocation = indexLocation;
    }

}
