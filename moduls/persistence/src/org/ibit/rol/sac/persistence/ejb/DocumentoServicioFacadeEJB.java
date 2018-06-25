package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.DocumentoServicio;
import org.ibit.rol.sac.model.HechoVitalServicio;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionDocumentoServicio;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionServicio;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ServicioDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.IndexFile;
import es.caib.solr.api.model.MultilangLiteral;
import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;


/**
 * SessionBean para mantener y consultar DocumentoServicio. (DOCSER)
 *
 * @ejb.bean
 * name="sac/persistence/DocumentoServicioFacade"
 * jndi-name="org.ibit.rol.sac.persistence.DocumentoServicioFacade"
 * type="Stateless"
 * view-type="remote"
 * transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class DocumentoServicioFacadeEJB extends HibernateEJB
{
	/** Serial version UID. **/
	private static final long serialVersionUID = 3203877720788468776L;
	
	
	/**
	 * Obtiene referencia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();
	
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
	@Override
	public void ejbCreate() throws CreateException
	{
        super.ejbCreate();
    }
	
	
	
	/**
     * Borra una DocumentoServicio.
     * @param id	Identificador del documento servicio a borrar.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public void borrarDocumentoServicio(Long id)
	{
		Session session = getSession();
		try {
			DocumentoServicio documentoServicio = (DocumentoServicio) session.load(DocumentoServicio.class, id);
			session.delete(documentoServicio);
            session.flush();            
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }
	}
	

	/**
     * Obtener una DocumentoServicio.
     * @param id	Identificador del documento servicio a obtener.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public DocumentoServicio obtenerDocumentoServicio(Long id)
	{
		Session session = getSession();
		DocumentoServicio documentoServicio = null;
		try {
			documentoServicio = (DocumentoServicio) session.get(DocumentoServicio.class, id);
			Hibernate.initialize(documentoServicio.getTraducciones());
			if (documentoServicio != null && documentoServicio.getTraducciones() != null) {
				for(String idioma : documentoServicio.getTraducciones().keySet()) {
					TraduccionDocumentoServicio tradNor = (TraduccionDocumentoServicio) documentoServicio.getTraduccion(idioma);
					if (tradNor != null && tradNor.getArchivo() != null) {
						Hibernate.initialize(tradNor.getArchivo());
					}
				}
			}
			return documentoServicio;
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }
	}
	
	/**
     * Obtener una lista con las ids de todos los documentos de servicio asociado al idServicio.
     * @param idServicio	Identificador del servicio
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public List<Long> obtenerDocumentosServiciosSolr(Long idServicio) {
		final Session session = getSession();
    	try {
    		return this.castList(Long.class, session.createQuery("select doc.id from DocumentoServicio doc where doc.servicio.id = "+idServicio).list());
    	} catch(HibernateException he) {
    		log.error("Error obteniendo los documentos asociados a la idServicio:"+idServicio, he);
    		return new ArrayList<Long>();
    	} finally{
    		try {
				session.close();
			} catch (HibernateException e) {
				log.error("Error cerrando en obteniendo los documentos asociados a la idServicio:"+idServicio, e);
			}
    	}
	}
	
	/**
	 * Guarda un documento
	 * 
	 * @param doc	Indica un documento asociado a un determinado trámite.
	 * 
	 * @param idServicio	Identificador de un trámite.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return Devuelve el identificador del documento guardado.
	 * @throws DelegateException 
	 */
	public Long grabarDocument(DocumentoServicio doc, Long idServicio) throws DelegateException {

		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoServicio(idServicio) ) {
				throw new SecurityException("No tiene acceso a al servicio.");
			}
			
			Servicio servicio = null;
			boolean actualizar = false;

			if ( doc.getId() == null ) {

				servicio = cargaServicio(session, idServicio);
				servicio.addDocumentoServicio(doc);
				if (doc.getServicio() == null) {
					doc.setServicio(servicio);
				}
				if (doc.getOrden() == null) {
					Long orden = getOrden(servicio.getId(), session);
					doc.setOrden(orden);
				}
				session.save(doc);

			} else {

				session.saveOrUpdate(doc);
				actualizar = true;
			}

			session.flush();
			if (actualizar) {
				servicio = cargaServicio(session, idServicio);
			}

			log.debug("Grabar Documento: Lanzo el actualizador");
			Actualizador.actualizar(servicio, true);
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_SERVICIO, servicio.getId(), false);
			return doc.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	/**
	 * Obtiene el orden máximo
	 * @param idServicio
	 * @param session
	 * @return
	 */
	private Long getOrden(Long idServicio, final Session session) {
		Long orden;
		try {
			final Query query = session.createQuery("Select max(documentoServicio.orden) from DocumentoServicio documentoServicio where documentoServicio.servicio.id = " + idServicio);
			//Incrementa el orden en uno
			Object resultadoMax = query.uniqueResult();
			if (resultadoMax == null) {
				orden = 0l;
			} else {
				orden = Long.valueOf(resultadoMax.toString() ) + 1l;
			}
		} catch(Exception e) {
			orden = 0l;
		}
		return orden;
	}

	/**
	 * Se hace pública para que se pueda acceder desde servicioFacadeEJB
	 * @param session
	 * @param idServicio
	 * @return
	 * @throws HibernateException
	 */
	private Servicio cargaServicio(Session session, Long idServicio) throws HibernateException {

		Servicio servicio = (Servicio) session.load(Servicio.class, idServicio);
		Hibernate.initialize(servicio.getDocumentos());
		if (servicio.getDocumentos() != null) {
			for(DocumentoServicio documentoServicio : servicio.getDocumentos()) {
				if (documentoServicio != null) {
					for(String idioma : documentoServicio.getTraducciones().keySet()) {
						TraduccionDocumentoServicio tradNor = (TraduccionDocumentoServicio) documentoServicio.getTraduccion(idioma);
						if (tradNor != null && tradNor.getArchivo() != null) {
							Hibernate.initialize(tradNor.getArchivo());
						}
					}
				}
			}
		}

		return servicio;

	}
	
	
	/**
	 * Metodo para indexar un solrPendiente.
	 * 
	 * @param solrIndexer
	 * @param idElemento
	 * @param categoria
	 * 
	 * @ejb.interface-method
     * @ejb.permission unchecked="true"
	 */
	public SolrPendienteResultado indexarSolrServicioDoc(SolrIndexer solrIndexer, Long idElemento, EnumCategoria categoria) {
		log.debug("DocumentoServicioFacadeEJB.indexarSolrServicioDoc. idElemento:" + idElemento+" categoria:"+ categoria);
		boolean indexacion = false;
		try {
			//Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final DocumentoServicio documento = obtenerDocumentoServicio(idElemento);
			if (documento == null) {
				return new SolrPendienteResultado(false, "Da problema al cargar la info del documento servicio.");
			}
			
			boolean isIndexable = IndexacionUtil.isIndexable(documento.getServicio());
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}
						
			//Obtenemos el servicio por separado porque daba un error de lazy hibernate
			ServicioDelegate servDelegate = DelegateUtil.getServicioDelegate();
			Servicio servicio = servDelegate.obtenerServicioParaSolr(documento.getServicio().getId(), null);
			
			//Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la categoria de tipo ficha.
			final IndexFile indexData = new IndexFile();
			indexData.setCategoria(categoria);
			indexData.setCategoriaPadre(EnumCategoria.ROLSAC_SERVICIO);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoIdPadre(servicio.getId().toString());
			indexData.setCategoriaRaiz(EnumCategoria.ROLSAC_SERVICIO);
			indexData.setElementoIdRaiz(servicio.getId().toString());

			//materia
			final List<String> materiasId = new ArrayList<String>();		
			for(Materia materia : servicio.getMaterias()) {
	    		materiasId.add(materia.getId().toString());
			}
			indexData.setMateriaId(materiasId);
			
			//Publico Objetivo
			final List<String> publicoObjetivoId = new ArrayList<String>();		
			for( PublicoObjetivo publicoObjectivo :  servicio.getPublicosObjetivo()) {
	    		publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);
			
			//Fechas
			indexData.setFechaActualizacion(servicio.getFechaActualizacion());
			indexData.setFechaPublicacion(servicio.getFechaPublicacion());
			indexData.setFechaCaducidad(servicio.getFechaDespublicacion());
			
			//UA
			PathUO pathUO = IndexacionUtil.calcularPathUO(servicio.getOrganoInstructor());
			if (pathUO == null) {
				return new SolrPendienteResultado(true, "No se puede indexar: no cuelga de UA visible");
			}
			indexData.getUos().add(pathUO);
			
			//Traducciones
			final Map<String, Traduccion> traducciones = documento.getTraduccionMap();
			
			//Recorremos las traducciones
			for (String keyIdioma : traducciones.keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionDocumento traduccion = (TraduccionDocumento)traducciones.get(keyIdioma);
				final TraduccionServicio traduccionServicio = (TraduccionServicio)servicio.getTraduccion(keyIdioma);
		    	
				if (traduccion != null && enumIdioma != null && traduccion.getArchivo() != null) {
					
					//Para saltarse los idiomas sin titulo.
					if ((traduccion.getTitulo() == null || traduccion.getTitulo().isEmpty())  && enumIdioma != EnumIdiomas.CATALA) {
						continue;
					}
					
					if (IndexacionUtil.isIndexableSolr(traduccion.getArchivo())) {
						log.debug("Es indexable con mime:" + traduccion.getArchivo().getMime()+" y tamanyo:" + traduccion.getArchivo().getPeso());
					} else {
						if (traduccion.getArchivo() == null) {
							log.debug("NO Es indexable doc servicio " + servicio.getId()+ " porque el archivo es nulo. ");
						} else {
							log.debug("NO Es indexable con mime:" + traduccion.getArchivo().getMime()+" y tamanyo:" + traduccion.getArchivo().getPeso());
						}
						continue;
					}
					
					try {
						
						indexData.setElementoId(traduccion.getArchivo().getId().toString());

						//Anyadimos idioma al enumerado.
						indexData.setIdioma(enumIdioma);
						
						final MultilangLiteral titulo = new MultilangLiteral();
						final MultilangLiteral descripcion = new MultilangLiteral();
						final MultilangLiteral descripcionPadre = new MultilangLiteral();
						final MultilangLiteral urls = new MultilangLiteral();
						final MultilangLiteral urlsPadres = new MultilangLiteral();		
						final MultilangLiteral searchTextOptional = new MultilangLiteral();
						
						final MultilangLiteral extension = new MultilangLiteral();
						
						//Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search text.
						titulo.addIdioma(enumIdioma, traduccion.getTitulo());
						descripcion.addIdioma(enumIdioma, solrIndexer.htmlToText(traduccion.getDescripcion()));
						if (traduccionServicio != null) {
							descripcionPadre.addIdioma(enumIdioma, traduccionServicio.getNombre());
						}
					    
				    	extension.addIdioma(enumIdioma, FilenameUtils.getExtension(StringUtils.trim(traduccion.getArchivo().getNombre()).trim()));
				    	
				    	final StringBuffer textoOptional = new StringBuffer();
						
				    	//materia
				    	for(Materia materia : servicio.getMaterias()) {
				    		final TraduccionMateria traduccionMateria = (TraduccionMateria) materia.getTraduccion(keyIdioma);
				    		if (traduccionMateria != null) {
								textoOptional.append(" ");
								textoOptional.append(traduccionMateria.getNombre());
								textoOptional.append(" ");
								textoOptional.append(traduccionMateria.getDescripcion());
								textoOptional.append(" ");
								textoOptional.append(traduccionMateria.getPalabrasclave());
				    		}
						}
				    	
				    	//hechos vitales
						for(HechoVitalServicio hecho : servicio.getHechosVitalesServicios()) {
							final TraduccionHechoVital traduccionHechoVital =  (TraduccionHechoVital) hecho.getHechoVital().getTraduccionFront(keyIdioma);
							if (traduccionHechoVital != null) {
								textoOptional.append(" ");
								textoOptional.append(traduccionHechoVital.getNombre());
								textoOptional.append(" ");
								textoOptional.append(traduccionHechoVital.getDescripcion());
								textoOptional.append(" ");
								textoOptional.append(traduccionHechoVital.getPalabrasclave());
							}
						}
						
						//Publico objetivo, para añadirlo como id y para extraer el titulo para la url
						String nombrePubObjetivo = "persones";
						String idPubObjetivo = "200";
						for( PublicoObjetivo publicoObjectivo :  servicio.getPublicosObjetivo()) {
							final TraduccionPublicoObjetivo traduccionPO = (TraduccionPublicoObjetivo) publicoObjectivo.getTraduccion(keyIdioma);
							if (traduccionPO != null) {
								nombrePubObjetivo = traduccionPO.getTitulo();
								idPubObjetivo = publicoObjectivo.getId().toString(); 
								break;
							}
						}
												
						//UO
						TraduccionUA traduccionUA = ((TraduccionUA) servicio.getOrganoInstructor().getTraduccion(keyIdioma));
						if (traduccionUA != null && traduccionUA.getNombre() != null) {
							textoOptional.append(" ");
							textoOptional.append(traduccionUA.getNombre());							
						}							
						
						
						//Normativa asociadas
						for(Normativa normativa : servicio.getNormativas()) {
							final TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativa.getTraduccion(keyIdioma);
							if (traduccionNormativa != null) {
								textoOptional.append(traduccionNormativa.getTitulo());
								textoOptional.append(" ");
							}
						}
						
						// Urls
				    	urlsPadres.addIdioma(enumIdioma, "/seucaib/"+keyIdioma+"/"+idPubObjetivo+"/"+nombrePubObjetivo+"/tramites/tramite/"+servicio.getId());
				    	urls.addIdioma(enumIdioma, "govern/rest/arxiu/" + traduccion.getArchivo().getId());
				    	
				    	//Seteamos datos multidioma.
						indexData.setTitulo(titulo);
						indexData.setDescripcion(descripcion);
						indexData.setDescripcionPadre(descripcionPadre);
						indexData.setUrl(urls);
						indexData.setUrlPadre(urlsPadres);
						
						searchTextOptional.addIdioma(enumIdioma, traduccion.getTitulo()+ " "+ traduccion.getDescripcion() + " "+ traduccion.getArchivo().getNombre());
						indexData.setSearchTextOptional(searchTextOptional);
						indexData.setFileContent(traduccion.getArchivo().getDatos());
						indexData.setExtension(extension);
						solrIndexer.indexarFichero(indexData);
						indexacion = true;	
						
					} catch(Exception exceptionSolr) {
						log.error("Error indexando un documento de servicio. DocID:" + documento.getId()+" Idioma:" + enumIdioma, exceptionSolr);
					}
				}
			}
			
			return new SolrPendienteResultado(indexacion);
		} catch(Exception exception) {
			log.error("Error en documentoServicioFacade intentando indexar.  idElemento:" + idElemento+" categoria:"+ categoria, exception);			
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}
}
