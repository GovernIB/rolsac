package es.caib.rolsac.api.v1;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.ArchivoDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

/**
 * Implementación del webservice.
 */
public final class RolsacQueryServiceImpl implements RolsacQueryService {

    protected static Log log = LogFactory.getLog(RolsacQueryServiceImpl.class);

    public UnidadAdministrativaDTO obtenerUnidadAdministrativaPorCodigoEstandar(String codigo_estandar, String idioma) {
        try {
            UnidadAdministrativaDelegate unidadAdmDelegate = DelegateUtil.getUADelegate();
            UnidadAdministrativa ua = unidadAdmDelegate.obtenerUnidadAdministrativaPorCodEstandar(codigo_estandar);

            UnidadAdministrativaDTO uae = new UnidadAdministrativaDTO(ua, idioma);

            return uae;
        } catch (DelegateException e) {
            log.error("obtenerUnidadAdministrativaPorCodigoEstandar(" + codigo_estandar + ")", e);
            return null;
        }
    }

    public UnidadAdministrativaDTO obtenerUnidadAdministrativa(Long id, String idioma) {
        try {
            UnidadAdministrativaDelegate unidadAdmDelegate = DelegateUtil.getUADelegate();
            UnidadAdministrativa ua = unidadAdmDelegate.consultarUnidadAdministrativa(id);

            UnidadAdministrativaDTO uae = new UnidadAdministrativaDTO(ua, idioma);

            return uae;
        } catch (DelegateException e) {
            log.error("obtenerUnidadAdministrativa(" + id + ")", e);
            return null;
        }
    }

	@SuppressWarnings("unchecked")
	public EdificioDTO[] listarEdificiosByUnidadAdm(Long id, String idioma) {
		try {
			EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
			Set edificios = edificioDelegate.listarEdificiosUnidad(id);
			int i = 0;
			EdificioDTO[] result = new EdificioDTO[edificios.size()];
			for (Object edificio:edificios) {
				EdificioDTO ed = new EdificioDTO((Edificio)edificio, idioma, false);
				result[i] = ed;
				i++;
			}
			return result;
		} catch (DelegateException e) {
	        log.error("listarEdificiosByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}

	public EdificioDTO obtenerEdificio(Long id, String idioma) {
		try {
            EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
            return new EdificioDTO(edificioDelegate.obtenerEdificio(id), idioma);
        } catch (DelegateException e) {
            log.error("obtenerEdificio(" + id + ")", e);
            return null;
        }
	}

	/* agarcia: nueva implementación del método
	public MateriaDTO[] listarMateriasByUnidadAdm(Long id, String idioma) {
		try {
			log.debug("#@#2###############################################################################################################################################################################################");

			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			List<Materia> materias = materiaDelegate.listarMateriasbyUA(id);
			int i = 0;
			MateriaDTO[] result = new MateriaDTO[materias.size()];
			log.debug("Materias encontradas para UA" + id.intValue() + ":" + materias.size());
			for (Object materia:materias) {
				MateriaDTO mat = new MateriaDTO((Materia)materia, idioma);
				result[i] = mat;
				i++;
				Materia ma = (Materia) materia;
				log.debug("#################################################################################################################################################################################################");
				log.debug("-EXPORTId: " + mat.getId() + "-codEstandat: " + mat.getCodigoEstandar() + "-Procedimientos: " + mat.getNumProcedimientos());
				log.debug("-Id: " + ma.getId() + "-codEstandat: " + ma.getCodigoEstandar() + "-Procedimientos: " + ma.getProcedimientosLocales().size());
				log.debug("#################################################################################################################################################################################################");
			}
			return result;
		} catch (DelegateException e) {
	        log.error("listarMateriasByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}

	public MateriaDTO[] listarMateriasByUnidadAdm_new(Long id, String idioma) {
		try {
			log.debug("#@#2###############################################################################################################################################################################################");

			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			//List<Materia> materias = materiaDelegate.listarMateriasbyUA(id);
			List<Materia> materias = materiaDelegate.listarMaterias();
			int i = 0;
			MateriaDTO[] result = new MateriaDTO[materias.size()];
			log.debug("Materias encontradas para UA" + id.intValue() + ":" + materias.size());
			for (Object materia:materias) {
				MateriaDTO mat = new MateriaDTO((Materia)materia, idioma);
				result[i] = mat;
				i++;
				Materia ma = (Materia) materia;
				log.debug("#################################################################################################################################################################################################");
				log.debug("-EXPORTId: " + mat.getId() + "-codEstandat: " + mat.getCodigoEstandar() + "-Procedimientos: " + mat.getNumProcedimientos());
				log.debug("-Id: " + ma.getId() + "-codEstandat: " + ma.getCodigoEstandar() + "-Procedimientos: " + ma.getProcedimientosLocales().size());
				log.debug("#################################################################################################################################################################################################");
			}


			return result;




		} catch (DelegateException e) {
	        log.error("listarMateriasByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}
	*/


	public MateriaDTO[] listarMateriasByUnidadAdm(Long id, String idioma) {
		try {
			log.debug("#@#2###############################################################################################################################################################################################");
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			List<Materia> materias = materiaDelegate.listarMaterias();
			int i = 0;
			log.debug("Materias encontradas para UA" + id.intValue() + ":" + materias.size());
			Map<Long, MateriaDTO> indiceMaterias = new Hashtable<Long, MateriaDTO>();
			MateriaDTO[] result = new MateriaDTO[materias.size()];
			for (Object materia:materias) {
				MateriaDTO mat = new MateriaDTO((Materia)materia, idioma);
				result[i] = mat;
				indiceMaterias.put(mat.getId(), mat);
				i++;
				Materia ma = (Materia) materia;
//				log.debug("#################################################################################################################################################################################################");
//				log.debug("-EXPORTId: " + mat.getId() + "-codEstandat: " + mat.getCodigoEstandar() + "-Procedimientos: " + mat.getNumProcedimientos());
//				log.debug("-Id: " + ma.getId() + "-codEstandat: " + ma.getCodigoEstandar() + "-Procedimientos: " + ma.getProcedimientosLocales().size());
//				log.debug("#################################################################################################################################################################################################");
			}

            ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();

			/* agarcia: listarProcedimientos no va bien, no busca en todo el árbol, sino sólo en 3 niveles
			 * no queda más remedio que buscarlos todos y filtrar los que no pertenecen al árbol
            List procedimientos = procedimientoDelegate.listarProcedimientosUO(id, 1);
             */
			List<Long> descendientes = DelegateUtil.getUADelegate().cargarArbolUnidadId(id);
            List procedimientos = procedimientoDelegate.listarProcedimientosPublicos();

			for (Object proc:procedimientos) {
				ProcedimientoLocal pl = ( ProcedimientoLocal )proc;
				if ( !descendientes.contains( pl.getUnidadAdministrativa().getId() ) )  {
					continue;
				}
				Set<Materia> mats = ((ProcedimientoLocal)proc).getMaterias();
				for (Object materia:mats) {
					Long idMat = ((Materia)materia).getId();
					indiceMaterias.get(idMat).setNumProcedimientos( indiceMaterias.get(idMat).getNumProcedimientos()+1 );
				}
			}

			return result;
		} catch (DelegateException e) {
	        log.error("listarMateriasByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}

	public MateriaDTO obtenerMateria(Long id, String idioma) {
		try {
            MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
            return new MateriaDTO (materiaDelegate.obtenerMateria(id), idioma);
        } catch (DelegateException e) {
            log.error("obtenerMateria(" + id + ")", e);
            return null;
        }
	}

    @SuppressWarnings("unchecked")
	public PersonalDTO[] listarPersonasByUnidadAdm(Long id) {
		try {
			PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
			Set personas = personalDelegate.listarPersonalUA(id);
			int i = 0;
			PersonalDTO[] result = new PersonalDTO[personas.size()];
			for (Object persona:personas) {
				PersonalDTO pe = new PersonalDTO((Personal)persona);
				result[i] = pe;
				i++;
			}
			return result;
		} catch (DelegateException e) {
	        log.error("listarPersonasByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}

    @SuppressWarnings("unchecked")
	public Long[] listarIdsPersonasByUnidadAdm(Long id) {
		try {
			PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
			Set personas = personalDelegate.listarPersonalUA(id);
			int i = 0;
			Long[] result = new Long[personas.size()];
			for (Object persona:personas) {
				result[i] = ((Personal)persona).getId();
				i++;
			}
			return result;
		} catch (DelegateException e) {
	        log.error("listarIdsPersonasByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}

	public PersonalDTO obtenerPersona(Long id) {
		try {
			PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
            PersonalDTO pe = new PersonalDTO(personalDelegate.obtenerPersonal(id));
            return pe;
        } catch (DelegateException e) {
            log.error("obtenerPersona(" + id + ")", e);
            return null;
        }
	}

	public ProcedimientoDTO obtenerProcedimiento(Long id, String idioma) {
		try {
            ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
            return new ProcedimientoDTO(procedimientoDelegate.obtenerProcedimiento(id), idioma);
        } catch (DelegateException e) {
            log.error("obtenerProcedimiento(" + id + ")", e);
            return null;
        }
	}

	public ProcedimientoCompletoDTO obtenerProcedimientoCompleto(Long id, String idioma) {
		ProcedimientoCompletoDTO procedimientoCompleto = null;
		try{
            ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
            procedimientoCompleto = new ProcedimientoCompletoDTO(procedimientoDelegate.obtenerProcedimiento(id), idioma);
        	log.debug("##################################################################################################");
    	  	log.debug("WEBSERVICE-ObtenerProcedimientoCompleto: " + id.intValue());
    	  	log.debug("Id Unidad Administrativa: " + procedimientoCompleto.getOrganoCompetente().getId().intValue());
    	  	log.debug("Tramites: " + procedimientoCompleto.getTramites().size());
    	  	log.debug("Documentos: " + procedimientoCompleto.getDocumentos().size());
    	  	log.debug("Id Familia: " + procedimientoCompleto.getFamilia().getId().intValue());
    	  	log.debug("Ventana: " + procedimientoCompleto.getVentana());
    	  	log.debug("Materias: " + procedimientoCompleto.getMaterias().size());
    	  	log.debug("Normativas: " + procedimientoCompleto.getNormativas().size());
    	  	log.debug("Id Iniciacion: " + procedimientoCompleto.getIniciacion().getId());
    	  	log.debug("Responsable: " + procedimientoCompleto.getResponsable());
    	  	log.debug("Indicador: " + procedimientoCompleto.getIndicador());
    	  	log.debug("Info: " + procedimientoCompleto.getInfo());
    	  	log.debug("Signatura: " + procedimientoCompleto.getSignatura());
    	  	log.debug("Url: " + procedimientoCompleto.getUrl());
    	  	log.debug("Hechos Vitales: " + procedimientoCompleto.getHechosVitalesProcedimientos().size());
    	  	log.debug("##################################################################################################");

            return procedimientoCompleto;
        } catch (DelegateException e) {
            log.error("obtenerProcedimientoCompleto(" + id + ")", e);
            return null;
        }
        catch( Throwable t )
        {
        	t.printStackTrace();
        	return procedimientoCompleto;
        }

	}

	@SuppressWarnings("unchecked")
	public ProcedimientoDTO[] listarProcedimientosByMateria(Long materia_id, Long ua_id, String idioma) {
		try {
			List<Long> descendientes = DelegateUtil.getUADelegate().cargarArbolUnidadId(ua_id);

			ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
			//agarcia: deben ser procedimientos de cualquier descendiente

			List procedimientos = procDelegate.buscarProcedimientosMateria(materia_id);
			List<ProcedimientoDTO> procedimientosUnidad = new ArrayList<ProcedimientoDTO>();
			for (Object procedimiento:procedimientos) {
				Long idUnidad = ((ProcedimientoLocal)procedimiento).getUnidadAdministrativa().getId();
				if ( descendientes.contains(idUnidad) ) {
					procedimientosUnidad.add(new ProcedimientoDTO((ProcedimientoLocal)procedimiento, idioma));
				}
			}

			/*
			List procedimientos = procDelegate.listarProcedimientos();
			List<ProcedimientoDTO> procedimientosUnidad = new ArrayList<ProcedimientoDTO>();
			for (Object procedimiento:procedimientos) {
				Long idUnidad = ((ProcedimientoLocal)procedimiento).getUnidadAdministrativa().getId();
				if ( descendientes.contains(idUnidad) ) {
					Set<Materia> mats = ((ProcedimientoLocal)procedimiento).getMaterias();
					//agarcia: esto es ineficiente, pero buscarProcedimientosMateria NO FUNCIONA
					for (Object materia:mats) {
						Long idMat = ((Materia)materia).getId();
						if (idMat.equals(materia_id)) {
							procedimientosUnidad.add(new ProcedimientoDTO((ProcedimientoLocal)procedimiento, idioma));
							continue;
						}
					}
				}

			}
			*/

			ProcedimientoDTO[] result = new ProcedimientoDTO[procedimientosUnidad.size()];
			result = procedimientosUnidad.toArray(result);
			return result;
		} catch (DelegateException e) {
	        log.error("listarProcedimientosByMateria(" + materia_id + ", " + ua_id + ")", e);
	        return null;
	    }
	}

	@SuppressWarnings("unchecked")
	public ProcedimientoDTO[] listarProcedimientosByUnidadAdm(Long id, String idioma) {
		try {
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			List procedimientos = procedimientoDelegate.listarProcedimientosPublicosUA(id);
			int i = 0;
			ProcedimientoDTO[] result = new ProcedimientoDTO[procedimientos.size()];
			for (Object procedimiento:procedimientos) {
				result[i] = new ProcedimientoDTO((ProcedimientoLocal)procedimiento, idioma);
				i++;
			}
			return result;
		} catch (DelegateException e) {
	        log.error("listarProcedimientosByUnidadAdm(" + id + ")", e);
	        return null;
	    }
	}

	@SuppressWarnings("unchecked")
	public ProcedimientoDTO[] listarProcedimientosByTexto(String codigo_estandar, String texto, String idioma) {
		try {
            UnidadAdministrativaDelegate unidadAdmDelegate = DelegateUtil.getUADelegate();
            ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();

            UnidadAdministrativa ua = unidadAdmDelegate.obtenerUnidadAdministrativaPorCodEstandar(codigo_estandar);
            List procedimientos = procDelegate.buscarProcedimientosUATexto(ua.getId(), texto, idioma);
            //List procedimientos = procDelegate.buscarProcedimientosUATexto(ua.getId(), texto);
			List<ProcedimientoDTO> procsExport = new ArrayList<ProcedimientoDTO>();
			for (Object procedimiento:procedimientos) {
				procsExport.add(new ProcedimientoDTO((ProcedimientoLocal)procedimiento, idioma));
			}
			ProcedimientoDTO[] result = new ProcedimientoDTO[procsExport.size()];
			result = procsExport.toArray(result);
			return result;
		} catch (DelegateException e) {
	        log.error("listarProcedimientosByTexto(" + texto + ")", e);
            return null;
        }
	}

//	public ProcedimientoDTO[] listarProcedimientosByTexto__ (String codigo_estandar, String texto, String idioma) {
//		try {
//            ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
//            IndexerDelegate indDelegate = DelegateUtil.getIndexerDelegate();
//
//            IndexResultados indexResultados;
//			List<ProcedimientoDTO> procsExport = new ArrayList<ProcedimientoDTO>();
//
//			indexResultados = indDelegate.buscaravanzado(texto, "", "", "", "PRC", codigo_estandar, "", null, null, "", "ca", true, true);
//			List list = indexResultados.getLista();
//			log.debug("###################################### buscarProcedimientosUATexto\n RESULTADOS DE LA BUSQUEDA: " + list.size() + "\n####################################################################");
//			for (Object obj : list) {
//				if (obj instanceof IndexEncontrado) {
//					IndexEncontrado indexEncontrado = (IndexEncontrado) obj;
//					String str = indexEncontrado.getId();
//					log.debug("@@IdProcedimiento:" + str); //El id que devuelve no es de tipo Long //Ejemplo: PRC.EXTERNO.DCMTS.39
//
//					String strr = str.substring(str.lastIndexOf('.'));
//
//					log.debug("#~#~#~#~#~#~#~#~#~#~#~#~#~#~#~#"+strr);
//					Long n = Long.valueOf(strr).longValue();
//
//					procsExport.add( new ProcedimientoDTO( procDelegate.obtenerProcedimiento(n), idioma));
//				}
//			}
//
//			ProcedimientoDTO[] result = new ProcedimientoDTO[procsExport.size()];
//			result = procsExport.toArray(result);
//			return result;
//    		//Recorrer la lista de ids y obtener los procedimientos a devolver
//
//
//		} catch (DelegateException e) {
//	        log.error("listarProcedimientosByTexto(" + texto + ")", e);
//            return null;
//        }
//	}


	public Archivo obtenerArchivo(Long id) {
		try {
            ArchivoDelegate archivoDelegate = DelegateUtil.getArchivoDelegate();
            Archivo archivo = archivoDelegate.obtenerArchivo(id);
            return archivo;
        } catch (DelegateException e) {
            log.error("obtenerArchivo(" + id + ")", e);
            return null;
        }
	}
}
