package test.unitario.persistence.mock;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJBException;

import junit.extensions.TestDecorator;

import net.sf.hibernate.SessionFactory;

import org.easymock.classextension.EasyMock;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegateImpl;
import org.ibit.rol.sac.persistence.ejb.TramiteFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.remote.vuds.ActualizacionVudsException;
import org.ibit.rol.sac.persistence.remote.vuds.ValidateVudsException;
import org.ibit.rol.sac.persistence.ws.Actualizador;




public class MockTramiteDelegate extends TramiteDelegate {
}
