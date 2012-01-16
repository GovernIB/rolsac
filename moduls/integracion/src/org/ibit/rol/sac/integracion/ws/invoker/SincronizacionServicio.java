package org.ibit.rol.sac.integracion.ws.invoker;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.ws.*;

import javax.xml.namespace.QName;

public class SincronizacionServicio extends StandardInvoker{

    //private static final Log log = LogFactory.getLog(SincronizacionServicio.class);

    private static final String NAMESPACEURI = "http://ws.model.sac.rol.ibit.org/beans";
    //private static final String NAMESPACEURI = "http://ws.model.sac.rol.ibit.org";
    private static final String WEB_SERVICE = "http://org.ibit.rol.sac.integracion.ws";
    private static final Log log = LogFactory.getLog(SincronizacionServicio.class);
    public SincronizacionServicio(String endPoint) throws WSInvocatorException {
    	super(endPoint,WEB_SERVICE,NAMESPACEURI);

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
        registerTypeMapping(TraduccionFichaTransferible.class);
        registerTypeMapping(FichaTransferible.class);

        registerTypeMapping(TraduccionProcedimientoTransferible.class);
        registerTypeMapping(ProcedimientoTransferible.class);
        registerTypeMapping(EdificioTransferible.class);

        //VUDS
        registerTypeMapping(TramiteTransferible.class);
        registerTypeMapping(TraduccionTramiteTransferible.class);
        registerTypeMapping(TaxaTransferible.class);
        registerTypeMapping(TraduccionTaxaTransferible.class);
        registerTypeMapping(DocumentTramitTransferible.class);
        registerTypeMapping(TraduccionDocumentoTransferible.class);
        registerTypeMapping(NormativaTransferible.class);
        registerTypeMapping(TraduccionNormativaTransferible.class);
        registerTypeMapping(DocumentoTransferible.class);

    }

    public UnidadAdministrativaTransferible recogerUnidadAdministrativaByCodigoEstandar(String codEstUA) throws WSInvocatorException {
        try {
            setOperationName("recogerUnidadAdministrativaByCodigoEstandar");

            addParameter("codEstUA",
			                  org.apache.axis.Constants.XSD_STRING,
					          javax.xml.rpc.ParameterMode.IN);
            
            setReturnType(NAMESPACEURI, UnidadAdministrativaTransferible.class);
            setReturnQName(new QName("", "recogerUnidadAdministrativaByCodigoEstandarReturn"));
            
            return (UnidadAdministrativaTransferible) invoke(codEstUA);
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


    public FichaTransferible[] recogerFichasUASeccion(String codEstSecc, Long idUA, String[] codEstHV, String[] codEstMat) throws WSInvocatorException {
        try {
            setOperationName("recogerFichasUASeccion");

            addParameter("codEstSecc",
			                  org.apache.axis.Constants.XSD_STRING,
					          javax.xml.rpc.ParameterMode.IN);
            addParameter("idUA",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstHV",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstMat",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);

            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_FichaTransferible"));
            setReturnClass(FichaTransferible[].class);
            setReturnQName(new QName("", "recogerFichasUASeccionReturn"));

            return (FichaTransferible[]) invoke(codEstSecc, idUA, codEstHV, codEstMat);
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


    public Long[] recogerIdsFichasUASeccion(String codEstSecc, Long idUA, String[] codEstHV, String[] codEstMat) throws WSInvocatorException {
        try {
            setOperationName("recogerIdsFichasUASeccion");

            addParameter("codEstSecc",
			                  org.apache.axis.Constants.XSD_STRING,
					          javax.xml.rpc.ParameterMode.IN);
            addParameter("idUA",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstHV",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstMat",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);

            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_Long"));
            setReturnClass(Long[].class);
            setReturnQName(new QName("", "recogerIdsFichasUASeccionReturn"));

            return (Long[]) invoke(codEstSecc, idUA, codEstHV, codEstMat);
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


     public FichaTransferible recogerFicha(Long idFicha) throws WSInvocatorException {
        try {
            setOperationName("recogerFicha");

            addParameter("idFicha",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);


            setReturnType(NAMESPACEURI, FichaTransferible.class);
            setReturnQName(new QName("", "recogerFichaReturn"));

            return (FichaTransferible) invoke(idFicha);

        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


    public UnidadAdministrativaTransferible recogerUnidadAdministrativa(Long idUA) throws WSInvocatorException {
        try {
        	
            setOperationName("recogerUnidadAdministrativa");

            addParameter("idUA",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);

            setReturnType(NAMESPACEURI, UnidadAdministrativaTransferible.class);
            setReturnQName(new QName("", "recogerUnidadAdministrativaReturn"));

            return (UnidadAdministrativaTransferible) invoke(idUA);
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


    public ProcedimientoTransferible[] recogerProcedimientosRelacionados(Long idUA, String[] codEstHV, String[] codEstMap) throws WSInvocatorException {
        try {
            setOperationName("recogerProcedimientosRelacionados");
            addParameter("idUA",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstHV",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstMat",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);
            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_ProcedimientoTransferible"));
            setReturnClass(ProcedimientoTransferible[].class);
            setReturnQName(new QName("", "recogerProcedimientosRelacionadosReturn"));
            return (ProcedimientoTransferible[]) invoke(idUA, codEstHV, codEstMap);
            
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


     public Long[] recogerIdsProcedimientosRelacionados(Long idUA, String[] codEstHV, String[] codEstMap) throws WSInvocatorException {
        try {
            setOperationName("recogerIdsProcedimientosRelacionados");
            addParameter("idUA",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstHV",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);
            addParameter("codEstMat",
							   org.apache.axis.Constants.SOAP_ARRAY,
			                   javax.xml.rpc.ParameterMode.IN);
            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_Long"));
            setReturnClass(Long[].class);
            setReturnQName(new QName("", "recogerIdsProcedimientosRelacionadosReturn"));
            return (Long[]) invoke(idUA, codEstHV, codEstMap);

        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }

    public ProcedimientoTransferible recogerProcedimiento(Long idProc) throws WSInvocatorException {
        try {
            setOperationName("recogerProcedimiento");

            addParameter("idProc",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);


            setReturnType(NAMESPACEURI, ProcedimientoTransferible.class);
            setReturnQName(new QName("", "recogerProcedimientoReturn"));

            return (ProcedimientoTransferible) invoke(idProc);

        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }
    
    public EdificioTransferible[] recogerEdificiosRelacionados(Long idUA) throws WSInvocatorException {
        try {
            setOperationName("recogerEdificiosRelacionados");
            
            addParameter("idUA",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);


            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_EdificioTransferible"));
            setReturnClass(EdificioTransferible[].class);
            setReturnQName(new QName("", "recogerEdificiosRelacionadosReturn"));

            return (EdificioTransferible[]) invoke(idUA);
            
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }
    
    //VUDS
    
    public TramiteTransferible[] recogerTramitesRelacionados(Long idProcedimiento) throws WSInvocatorException {
        try {
            setOperationName("recogerTramitesRelacionados");
            
            addParameter("idProcedimiento",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);


            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_TramiteTransferible"));
            setReturnClass(TramiteTransferible[].class);
            setReturnQName(new QName("", "recogerTramitesRelacionadosReturn"));

            return (TramiteTransferible[]) invoke(idProcedimiento);
            
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }
    
    public NormativaTransferible[] recogerNormativasRelacionadas(Long idProcedimiento) throws WSInvocatorException {
        try {
            setOperationName("recogerNormativasRelacionadas");
            
            addParameter("idProcedimiento",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);


            setReturnType(new QName("http://org.ibit.rol.sac.integracion.ws", "ArrayOf_NormativaTransferible"));
            setReturnClass(NormativaTransferible[].class);
            setReturnQName(new QName("", "recogerNormativasRelacionadasReturn"));

            return (NormativaTransferible[]) invoke(idProcedimiento);
            
        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }


     public DocumentoTransferible recogerDocumento(Long idDoc) throws WSInvocatorException {
        try {
            setOperationName("recogerDocumento");

            addParameter("idDoc",
            		  		  org.apache.axis.Constants.XSD_LONG,
            		  		  javax.xml.rpc.ParameterMode.IN);


            setReturnType(NAMESPACEURI, DocumentoTransferible.class);
            setReturnQName(new QName("", "recogerDocumentoReturn"));

            return (DocumentoTransferible) invoke(idDoc);

        } catch(RemoteException e) {
            throw new WSInvocatorException(e);
        }
    }
    
}
