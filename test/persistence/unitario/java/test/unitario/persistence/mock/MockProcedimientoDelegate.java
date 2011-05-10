package test.unitario.persistence.mock;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.easymock.classextension.EasyMock;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.ejb.ProcedimientoFacadeEJB;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;


/**
 * aquest Mock es fa extendre del EJB per poder fer tests d'integracio.
 * @author u92770
 *
 */
public class MockProcedimientoDelegate extends ProcedimientoDelegate {

}
