package org.ibit.rol.sac.integracion.ws.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.model.ws.*;

import org.ibit.rol.sac.persistence.delegate.*;

import java.util.List;
import java.util.ArrayList;


public class SincronizacionServicio {


    private static final Log log = LogFactory.getLog(SincronizacionServicio.class);

    public UnidadAdministrativaTransferible recogerUnidadAdministrativaByCodigoEstandar(final String codEstUA) throws Exception {
    	log.debug("recogerUnidadAdministrativaByCodigoEstandar");
        try{
            final UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
            final UnidadAdministrativa unidad = unidadAdministrativaDelegate.obtenerUnidadAdministrativaPorCodEstandar(codEstUA);

            UnidadAdministrativaTransferible unidadTransferible = null;
            if(unidad!=null){
                //transformar a transferible
                unidadTransferible = UnidadAdministrativaTransferible.generar(unidad);
            }
            log.debug("recogerUnidadAdministrativaByCodigoEstandar fin");
            return unidadTransferible;
        } catch (Exception e) {
			log.error("error", e);            
            throw e;
		}
    }



    @SuppressWarnings("unchecked")
	public FichaTransferible[] recogerFichasUASeccion(final String codEstSecc, final Long idUA, final String[] codEstHV, final String[] codEstMat) throws Exception {
    	log.debug("recogerFichasUASeccion");
        try{
            final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();

            final List<Ficha> fichas = fichaDelegate.listarFichasSeccionUA(idUA, codEstSecc, codEstHV, codEstMat);
            FichaTransferible[] fichasTransArray = null;
            if(fichas!=null && !fichas.isEmpty()){
               List<FichaTransferible> fichasTrans = new ArrayList<FichaTransferible>();
               for(final Ficha ficha: fichas){
                   final FichaTransferible fichaTrans = new FichaTransferible();
                 /** Obtengo el responsable del histórico**/
        			if(fichaTrans.getResponsable() == null || fichaTrans.getResponsable().trim().length()<= 0){
        				String responsables = obtenerResponsableHistorico(ficha.getId(),"ficha");
        				if (responsables!=null && responsables.length()>0)fichaTrans.setResponsable(responsables);
        			}
                    fichasTrans.add(fichaTrans);
                }
                fichasTransArray = fichasTrans.toArray(new FichaTransferible[0]);
            }
            log.debug("recogerFichasUASeccion fin");
            return fichasTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }

    

    @SuppressWarnings("unchecked")
	public Long[] recogerIdsFichasUASeccion(final String codEstSecc, final Long idUA, final String[] codEstHV, final String[] codEstMat) throws Exception {
    	log.debug("recogerIdsFichasUASeccion");
        try{
            final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();

            final List<Ficha> fichas = fichaDelegate.listarFichasSeccionUA(idUA, codEstSecc, codEstHV, codEstMat);
            Long[] fichasTransArray = null;
            if(fichas!=null && !fichas.isEmpty()){
                List<Long> fichasTrans = new ArrayList<Long>();
                for(final Ficha ficha: fichas){
                    fichasTrans.add(ficha.getId());
                }
                fichasTransArray = fichasTrans.toArray(new Long[0]);
            }
            log.debug("recogerIdsFichasUASeccion fin");
            return fichasTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }


    public FichaTransferible recogerFicha(final Long idFicha) throws Exception {
        try{
            log.debug("recogerFicha");
            final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
            final Ficha ficha = fichaDelegate.obtenerFicha(idFicha);


            FichaTransferible fichaTransferible = null;
            if(ficha!=null){
                //transformar a transferible
                fichaTransferible = FichaTransferible.generar(ficha);
                //obtenemos responsable
                if(fichaTransferible.getResponsable() == null || fichaTransferible.getResponsable().trim().length()<= 0){
                    String responsables = obtenerResponsableHistorico(ficha.getId(),"ficha");
                    if (responsables!=null && responsables.length()>0)fichaTransferible.setResponsable(responsables);
                }

            }
            log.debug("recogerFicha fin");
            return fichaTransferible;


        }catch(Exception e){
           log.error("error", e);
           throw e;
        }


    }

    public UnidadAdministrativaTransferible recogerUnidadAdministrativa(final Long idUA) throws Exception{
    	log.debug("recogerUnidadAdministrativa");
    	try {
	    	final UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
	        UnidadAdministrativa unidad = unidadAdministrativaDelegate.consultarUnidadAdministrativa(idUA);
			
	        //transformar a transferible
	        UnidadAdministrativaTransferible unidadTransferible = null;
	        if(unidad!=null){
		        unidadTransferible = UnidadAdministrativaTransferible.generar(unidad);
	        }
            log.debug("recogerUnidadAdministrativa fin");
            return unidadTransferible;
    	} catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }


    @SuppressWarnings("unchecked")
	public ProcedimientoTransferible[] recogerProcedimientosRelacionados(final Long idUA, final String[] codEstHV, final String[] codEstMat) throws Exception {
    	log.debug("recogerProcedimientosRelacionados");
    	try {
            final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
            final List<ProcedimientoLocal> procs = procedimientoDelegate.listarProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
            ProcedimientoTransferible[] procsTransArray = null;

             if(procs!=null && !procs.isEmpty()){

                final List<ProcedimientoTransferible> procsTrans = new ArrayList<ProcedimientoTransferible>();
                for(final ProcedimientoLocal proc: procs){
                    final ProcedimientoTransferible procTrans = ProcedimientoTransferible.generar(proc);
                    /** Obtengo el responsable del histórico**/
                    if(procTrans.getResponsable() == null || procTrans.getResponsable().trim().length()<= 0){
    					String responsables = obtenerResponsableHistorico(proc.getId(),"procedimiento");
    					if (responsables!=null && responsables.length()>0)procTrans.setResponsable(responsables);
    				}
                    procsTrans.add(procTrans);
                }

                procsTransArray = procsTrans.toArray(new ProcedimientoTransferible[0]);
            }
            log.debug("recogerProcedimientosRelacionados fin");
            return  procsTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }

     @SuppressWarnings("unchecked")
	public Long[] recogerIdsProcedimientosRelacionados(final Long idUA, final String[] codEstHV, final String[] codEstMat) throws Exception {
    	log.debug("recogerIdsProcedimientosRelacionados");
    	try {
            final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
            final List<Long> procs = procedimientoDelegate.listarIdsProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
            Long[] procsTransArray = null;

            if(procs!=null && !procs.isEmpty()){

                final List<Long> procsTrans = new ArrayList<Long>();
                /*for(final ProcedimientoLocal proc: procs){
                   final ProcedimientoTransferible procTrans = ProcedimientoTransferible.generar(proc);
                    *//** Obtengo el responsable del histórico**//*
                    if(procTrans.getResponsable() == null || procTrans.getResponsable().trim().length()<= 0){
    					String responsables = obtenerResponsableHistorico(proc.getId(),"procedimiento");
    					if (responsables!=null && responsables.length()>0)procTrans.setResponsable(responsables);
    				}
                    procsTrans.add(proc.getId());
                }*/

                procsTransArray = procs.toArray(new Long[0]);
            }
            log.debug("recogerIdsProcedimientosRelacionados fin");
            return  procsTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }

    public ProcedimientoTransferible recogerProcedimiento(final Long idProc) throws Exception {
           try{
               log.debug("recogerProcedimiento");
               final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
               final ProcedimientoLocal procedimiento = procedimientoDelegate.obtenerProcedimiento(idProc);


              ProcedimientoTransferible procedimientoTransferible = null;
               if(procedimiento !=null){
                   //transformar a transferible
                   procedimientoTransferible = ProcedimientoTransferible.generar(procedimiento);
                   //obtenemos responsable
                   if(procedimientoTransferible.getResponsable() == null || procedimientoTransferible.getResponsable().trim().length()<= 0){
                       String responsables = obtenerResponsableHistorico(procedimiento.getId(),"procedimiento");
                       if (responsables!=null && responsables.length()>0)procedimientoTransferible.setResponsable(responsables);
                   }

               }
               log.debug("recogerProcedimiento fin");
               return procedimientoTransferible;
           }catch(Exception e){
              log.error("error", e);
              throw e;
           }
       }

    
    @SuppressWarnings("unchecked")
	public EdificioTransferible[] recogerEdificiosRelacionados(final Long idUA) throws Exception {
    	log.debug("recogerEdificiosRelacionados");
    	try {
            final UARemotaDelegate uaRemotaDelegate = DelegateUtil.getUARemotaDelegate();

            final List<Edificio> edificios = uaRemotaDelegate.obtenerEdificiosUA(idUA);
            EdificioTransferible[] edificiosTransArray = null;
            if(edificios!=null && !edificios.isEmpty()){
                final List<EdificioTransferible> edificiosTrans = new ArrayList<EdificioTransferible>();
                for(final Edificio edif: edificios){
                    final EdificioTransferible edifTrans = EdificioTransferible.generar(edif);
                    edificiosTrans.add(edifTrans);
                }
                edificiosTransArray = edificiosTrans.toArray(new EdificioTransferible[0]);
            }
            log.debug("recogerEdificiosRelacionados fin");
            return  edificiosTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }
    
    @SuppressWarnings("unchecked")
	public NormativaTransferible[] recogerNormativasRelacionadas(final Long idProcedimiento) throws Exception {
    	log.debug("recogerNormativasRelacionados");
    	try {
            final NormativaExternaRemotaDelegate normativaExternaRemotaDelegate = DelegateUtil.getNormativaExternaRemotaDelegate();

            final List<Normativa> normativas = normativaExternaRemotaDelegate.obtenerNormativasProcedimiento(idProcedimiento);
            NormativaTransferible[] normativasTransArray = null;
            if(normativas!=null && !normativas.isEmpty()){
                final List<NormativaTransferible> normativasTrans = new ArrayList<NormativaTransferible>();
                for(final Normativa norm: normativas){
                    final NormativaTransferible normTrans = NormativaTransferible.generar(norm);
                    normativasTrans.add(normTrans);
                }
                normativasTransArray = normativasTrans.toArray(new NormativaTransferible[0]);
            }
            log.debug("recogerNormaticasRelacionadas fin");
            return  normativasTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }
    
    //VUDS
    
    @SuppressWarnings("unchecked")
	public TramiteTransferible[] recogerTramitesRelacionados(final Long idProcedimiento) throws Exception {
    	log.debug("recogerTramitesRelacionados");
    	try {
            final TramiteRemotoDelegate tramiteRemotoDelegate = DelegateUtil.getTramiteRemotoDelegate();
            final List<Tramite> tramites = tramiteRemotoDelegate.obtenerTramitesProcedimiento(idProcedimiento);
            TramiteTransferible[] tramitesTransArray = null;

            if(tramites!=null && !tramites.isEmpty()){
                final List<TramiteTransferible> tramitesTrans = new ArrayList<TramiteTransferible>();
                for(final Tramite tram: tramites){
                    final TramiteTransferible tramTrans = TramiteTransferible.generar(tram);
                    tramitesTrans.add(tramTrans);
                }
                tramitesTransArray = tramitesTrans.toArray(new TramiteTransferible[0]);
            }
            log.debug("recogerTramitesRelacionados fin");
            return  tramitesTransArray;
        } catch (Exception e) {
			log.error("error", e);
			throw e;
		}
    }
    
    /**
	 * Obtiene los mails de las ultimas personas que han modificado un procedimiento o ficha
	 * @param id
	 * @param tipo
	 */
    private String obtenerResponsableHistorico(Long id,String tipo){
    	String numResponsables =System.getProperty("es.caib.rolsac.numResponsables");
    	StringBuffer responsables = new StringBuffer();

    	if(numResponsables!=null){
    		int contador = Integer.parseInt(numResponsables);
    		AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
	    	UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
	    	ArrayList<String> aResponsables=new ArrayList<String>();
	    	List listaAuditorias=null;
				try {
					
					if(tipo.equals("procedimiento")){
						listaAuditorias = auditoriaDelegate.listarAuditoriasProcedimientoPMA(id);
					}
					else if(tipo.equals("ficha")){
						listaAuditorias = auditoriaDelegate.listarAuditoriasFichaPMA(id);
					}
					
					for (Object object : listaAuditorias) {
						Auditoria auditoria = (Auditoria) object;
						if(aResponsables.size()< contador){
							if(auditoria.getUsuario()!=null){
								Usuario usuario=usuarioDelegate.obtenerUsuariobyUsernamePMA(auditoria.getUsuario());
								if(usuario!=null && usuario.getEmail()!=null){
									if(!aResponsables.contains(usuario.getEmail())){
										aResponsables.add(usuario.getEmail());
									}
								}
							}
						}
						else break;
					}
				} catch (DelegateException e) {
					log.error(e);
				}
				
				for (int i = 0; i < aResponsables.size(); i++) {
					if(i==aResponsables.size()-1){
						responsables.append(aResponsables.get(i));
					}
					else{
						responsables.append(aResponsables.get(i)+",");
					}
				}
    	}
    	
    	return responsables.toString();
    }

     public DocumentoTransferible recogerDocumento(final Long idDoc) throws Exception {
        try{
            log.debug("recogerDocumento");
            final DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
            final Documento doc = docDelegate.obtenerDocumento(idDoc);


            DocumentoTransferible documentoTransferible = null;
            if(doc!=null){
                //transformar a transferible
                documentoTransferible = DocumentoTransferible.generar(doc);

            }
            log.debug("recogerDocumento fin");
            return documentoTransferible;
        } catch (Exception e) {
			log.error("error", e);
            throw e;
		}
    }



}
