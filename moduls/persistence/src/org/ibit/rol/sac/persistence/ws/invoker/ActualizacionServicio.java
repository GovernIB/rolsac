package org.ibit.rol.sac.persistence.ws.invoker;

import org.apache.axis.Constants;

import org.ibit.rol.sac.model.ws.*;

import javax.xml.namespace.QName;
import java.rmi.RemoteException;

public class ActualizacionServicio extends IdInvoker {

	//private static final Log log = LogFactory.getLog(ActualizacionServicio.class);

	private static final String NAMESPACEURI = "http://ws.model.sac.rol.ibit.org/beans";
    //private static final String NAMESPACEURI = "http://ws.model.sac.rol.ibit.org";
    private static final String WEB_SERVICE = "http://org.ibit.rol.sac.integracion.ws";


    public static ActualizacionServicio createActualizacionServicio(
			String endPoint, String idRemoto) throws WSInvocatorException {
		return new ActualizacionServicio(endPoint, idRemoto);
	}
    public ActualizacionServicio(String endPoint, String idRemoto) throws WSInvocatorException {

		super(endPoint, WEB_SERVICE, NAMESPACEURI, idRemoto);

        registerArrayTypeMapping(new QName(WEB_SERVICE, "ArrayOf_xsd_long"),
                org.apache.axis.Constants.XSD_LONG, long[].class);
        /* registerArrayTypeMapping(new QName(WEB_SERVICE, "ArrayOf_xsd_string"),
                org.apache.axis.Constants.XSD_STRING, String[].class); */

        registerTypeMapping("http://ws.model.sac.rol.ibit.org", AbstractTraduccion.class);
        registerTypeMapping("http://ws.model.sac.rol.ibit.org", TraduccionUnidadMateriaTransferible.class);
        registerTypeMapping(TraduccionUATransferible.class);
        registerTypeMapping(ArchivoTransferible.class);
        registerTypeMapping(UnidadMateriaTransferible.class);
        registerTypeMapping(UnidadAdministrativaTransferible.class);
        registerTypeMapping(TraduccionEdificioTransferible.class);
        registerTypeMapping(TraduccionTramiteTransferible.class);
        registerTypeMapping(TraduccionFichaTransferible.class);
        registerTypeMapping(FichaTransferible.class);
        registerTypeMapping(FichaUATransferible.class);


        //VUDS
        registerTypeMapping(TraduccionProcedimientoTransferible.class);
        registerTypeMapping(ProcedimientoTransferible.class);
        registerTypeMapping(EdificioTransferible.class);
        registerTypeMapping(TramiteTransferible.class);
        registerTypeMapping(TaxaTransferible.class);
        registerTypeMapping(TraduccionTaxaTransferible.class);
        registerTypeMapping(DocumentTramitTransferible.class);
        registerTypeMapping(TraduccionDocumentoTransferible.class);
        registerTypeMapping(NormativaTransferible.class);
        registerTypeMapping(TraduccionNormativaTransferible.class);
        registerTypeMapping(DocumentoTransferible.class);
	}

	public Boolean actualizarFicha(FichaTransferible fichaT)
			throws WSInvocatorException {
		try {
			setOperationName("actualizarFicha");

			addParameter("fichaT", new QName(NAMESPACEURI,
					FichaTransferible.class.getSimpleName()),
					javax.xml.rpc.ParameterMode.IN);

			setReturnType(Constants.XSD_BOOLEAN);
            setReturnQName(new QName("", "actualizarFichaReturn"));

			return (Boolean) invoke(fichaT);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

	public Boolean actualizarUnidadAdministrativa(
			UnidadAdministrativaTransferible UAT) throws WSInvocatorException {
		try {
			setOperationName("actualizarUnidadAdministrativa");

			addParameter("UAT", new QName(NAMESPACEURI,
					UnidadAdministrativaTransferible.class.getSimpleName()),
					javax.xml.rpc.ParameterMode.IN);

			setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);
            setReturnQName(new QName("", "actualizarUnidadAdministrativaReturn"));

			return (Boolean) invoke(UAT);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

	public Boolean actualizarProcedimiento(ProcedimientoTransferible procT)
			throws WSInvocatorException {
		try {
			setOperationName("actualizarProcedimiento");

			addParameter("procT", new QName(NAMESPACEURI,
					ProcedimientoTransferible.class.getSimpleName()),
					javax.xml.rpc.ParameterMode.IN);

			setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);
            setReturnQName(new QName("", "actualizarProcedimientoReturn"));

			return (Boolean) invoke(procT);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

	public Boolean actualizarEdificio(EdificioTransferible edifT)throws WSInvocatorException {
		try {
			
		setOperationName("actualizarEdificio");

		addParameter("edifT", new QName(NAMESPACEURI,
				EdificioTransferible.class.getSimpleName()),
				javax.xml.rpc.ParameterMode.IN);

		setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);

	    setReturnQName(new QName("", "actualizarEdificioReturn"));

		return (Boolean) invoke(edifT);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}
	
	public Boolean actualizarTramite(TramiteTransferible tramT)throws WSInvocatorException {
		try {
			
		setOperationName("actualizarTramite");

		addParameter("tramT", new QName(NAMESPACEURI,
				TramiteTransferible.class.getSimpleName()),
				javax.xml.rpc.ParameterMode.IN);

		setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);

	    setReturnQName(new QName("", "actualizarTramiteReturn"));

		return (Boolean) invoke(tramT);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}
	
	public Boolean actualizarEdificioUA(EdificioTransferible edifT,Long idUA)throws WSInvocatorException {
		try {

		setOperationName("actualizarEdificioUA");
		
		addParameter("edifT", new QName(NAMESPACEURI,
				EdificioTransferible.class.getSimpleName()),
				javax.xml.rpc.ParameterMode.IN);
		addParameter("idUA", org.apache.axis.Constants.XSD_LONG,
				javax.xml.rpc.ParameterMode.IN);
			
		setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);
		
		
	    setReturnQName(new QName("", "actualizarEdificioUAReturn"));
	    
		return (Boolean) invoke(edifT,idUA);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}
	
	
    public Boolean actualizarNormativa(NormativaTransferible normT)throws WSInvocatorException {
		try {

		setOperationName("actualizarNormativa");

		addParameter("normT", new QName(NAMESPACEURI,
				NormativaTransferible.class.getSimpleName()),
				javax.xml.rpc.ParameterMode.IN);

		setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);

	    setReturnQName(new QName("", "actualizarNormativaReturn"));

		return (Boolean) invoke(normT);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

    public Boolean actualizarNormativaProcedimiento(NormativaTransferible normT,Long idProc)throws WSInvocatorException {
		try {
        setOperationName("actualizarNormativaProcedimiento");
        addParameter("normT", new QName(NAMESPACEURI,
				NormativaTransferible.class.getSimpleName()),
				javax.xml.rpc.ParameterMode.IN);
		addParameter("idProc", org.apache.axis.Constants.XSD_LONG,
				javax.xml.rpc.ParameterMode.IN);        
		setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);
	    setReturnQName(new QName("", "actualizarNormativaProcedimientoReturn"));
		return (Boolean) invoke(normT, idProc);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}


       public Boolean actualizarDocumentoProcedimiento(DocumentoTransferible docT,Long idProc)throws WSInvocatorException {
           try {
           setOperationName("actualizarDocumentoProcedimiento");

           addParameter("docT", new QName(NAMESPACEURI,
                   DocumentoTransferible.class.getSimpleName()),
                   javax.xml.rpc.ParameterMode.IN);
           addParameter("idProc", org.apache.axis.Constants.XSD_LONG,
                   javax.xml.rpc.ParameterMode.IN);
           setReturnType(org.apache.axis.Constants.XSD_BOOLEAN);

           setReturnQName(new QName("", "actualizarDocumentoProcedimientoReturn"));
           return (Boolean) invoke(docT, idProc);
           } catch (RemoteException e) {
               throw new WSInvocatorException(e);
           }
       }


	

	public void borrarFicha(Long idExt) throws WSInvocatorException {
		try {
			setOperationName("borrarFicha");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
            setReturnType(org.apache.axis.Constants.XSD_ANY);
			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

	public void borrarProcedimiento(Long idExt) throws WSInvocatorException {
		try {
			setOperationName("borrarProcedimiento");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
            setReturnType(org.apache.axis.Constants.XSD_ANY);
			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

	public void borrarUnidadAdministrativa(Long idExt)
			throws WSInvocatorException {
		try {
			setOperationName("borrarUnidadAdministrativa");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
            setReturnType(org.apache.axis.Constants.XSD_ANY);
			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

	public void borrarFichaUA(Long idFicha, Long idUA, String codEst)
			throws WSInvocatorException {
		try {
			setOperationName("borrarFichaUA");

			addParameter("idFicha", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			addParameter("idUA", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			addParameter("codEst", org.apache.axis.Constants.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);

            setReturnType(org.apache.axis.Constants.XSD_ANY);
			invoke(idFicha, idUA, codEst);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}
	
	public void borrarEdificio(Long idExt) throws WSInvocatorException {
		try {
			setOperationName("borrarEdificio");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			
			setReturnType(org.apache.axis.Constants.XSD_ANY);

			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}
	
	public void borrarEdificioUA(Long idExt, Long idUA) throws WSInvocatorException {
		try {
			setOperationName("borrarEdificioUA");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			addParameter("idUA", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			
			setReturnType(org.apache.axis.Constants.XSD_ANY);

			invoke(idExt,idUA);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}
	
	public void borrarTramite(Long idExt) throws WSInvocatorException {
		try {
			setOperationName("borrarTramite");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			
			setReturnType(org.apache.axis.Constants.XSD_ANY);

			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}


    public void borrarNormativa(Long idExt) throws WSInvocatorException {
		try {
			setOperationName("borrarNormativa");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);

			setReturnType(org.apache.axis.Constants.XSD_ANY);

			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}

    public void borrarNormativaProcedimiento(Long idExt, Long idProc) throws WSInvocatorException {
		try {
			setOperationName("borrarNormativaProcedimiento");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);
			addParameter("idProc", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);

			setReturnType(org.apache.axis.Constants.XSD_ANY);

			invoke(idExt, idProc);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	}


    public void borrarDocumentoProcedimiento(Long idExt) throws WSInvocatorException {
		try {
			setOperationName("borrarDocumentoProcedimiento");

			addParameter("idExt", org.apache.axis.Constants.XSD_LONG,
					javax.xml.rpc.ParameterMode.IN);

			setReturnType(org.apache.axis.Constants.XSD_ANY);

			invoke(idExt);
		} catch (RemoteException e) {
			throw new WSInvocatorException(e);
		}
	} 
}
