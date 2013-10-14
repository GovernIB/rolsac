package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;


@Controller
@RequestMapping("/documents/")
public class DocumentBackController extends ArchivoController {

	private static Log log = LogFactory.getLog(DocumentBackController.class);

	private MessageSource messageSource = null;

	@Autowired
	public void setMessageSource(MessageSource messageSource){
		this.messageSource = messageSource;
	}	


	@RequestMapping(value = "/guardarDocument.do", method = POST)
	public ResponseEntity<String> guardarDocument(HttpServletRequest request, HttpSession session)  {
		/* Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		Locale locale = request.getLocale();
		String jsonResult = null;

		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

		try {

			//Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
			//Iremos recopilando los parámetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
			List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);
			for ( FileItem item : items ) {

				if ( item.isFormField() ) {

					valoresForm.put( item.getFieldName(), item.getString("UTF-8") );

				} else {

					ficherosForm.put( item.getFieldName(), item );

				}

			}

			DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
			Documento doc = new Documento();
			Documento docOld = null;
			Long docId = null;
			if ( valoresForm.get("docId") != null  &&  !"".equals(valoresForm.get("docId") ) ) {

				docId = Long.parseLong( valoresForm.get("docId") );
				docOld = docDelegate.obtenerDocumento(docId);

				doc.setId( docOld.getId() );
				doc.setArchivo( docOld.getArchivo() );  // Este atributo parece que ya no se usa. Se mantiene por si acaso.
				doc.setFicha( docOld.getFicha() );
				doc.setProcedimiento( docOld.getProcedimiento() );
				doc.setOrden( docOld.getOrden() );
				
			}

			// Idiomas
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			List<Long> archivosAborrar = new Vector<Long>();
			TraduccionDocumento tradDoc;

			for ( String lang : langs ) {

				tradDoc = new TraduccionDocumento();
				tradDoc.setTitulo( RolUtil.limpiaCadena( valoresForm.get("doc_titol_" + lang) ) );
				tradDoc.setDescripcion( RolUtil.limpiaCadena( valoresForm.get("doc_descripcio_" + lang) ) );
				
				// Archivo
				FileItem fileItem = ficherosForm.get("doc_arxiu_" + lang);
				if ( fileItem != null  &&  fileItem.getSize() > 0 ) {

					// Nuevo archivo.
					tradDoc.setArchivo(UploadUtil.obtenerArchivo(tradDoc.getArchivo(), fileItem));

				} else if ( valoresForm.get("doc_arxiu_" + lang + "_delete") != null  &&  !"".equals( valoresForm.get("doc_arxiu_" + lang + "_delete") ) ) {

					// Indicamos a la traducción del documento que no va a tener asignado el archivo.
					TraduccionDocumento traDocOld = (TraduccionDocumento) docOld.getTraduccion(lang);
					archivosAborrar.add( traDocOld.getArchivo().getId() );
					tradDoc.setArchivo(null);

				} else if ( docOld != null ) {

					// mantener el fichero anterior
					TraduccionDocumento traDocOld = (TraduccionDocumento) docOld.getTraduccion(lang);
					if ( traDocOld != null )
						tradDoc.setArchivo( traDocOld.getArchivo() );

				}

				doc.setTraduccion(lang, tradDoc);

			}

			if ( valoresForm.get("procId") != null  &&  !"".equals( valoresForm.get("procId") ) ) {

				Long procId = Long.parseLong( valoresForm.get("procId") );
				docId = docDelegate.grabarDocumento( doc, procId, null );
				jsonResult = new IdNomDTO( docId,  messageSource.getMessage( "document.guardat.correcte", null, locale ) ).getJson();

			} else if ( valoresForm.get("fitxaId") != null  &&  !"".equals(valoresForm.get("fitxaId") ) ) {

				Long fitxaId = Long.parseLong( valoresForm.get("fitxaId") );
				docId = docDelegate.grabarDocumento( doc, null, fitxaId );

				for ( Long idArchivo : archivosAborrar )
					DelegateUtil.getArchivoDelegate().borrarArchivo(idArchivo);

				jsonResult = new IdNomDTO( docId,  messageSource.getMessage( "document.guardat.correcte", null, locale ) ).getJson();

			} else {

				String error = messageSource.getMessage( "error.altres", null, locale );
				jsonResult = new IdNomDTO(-2l, error).getJson();
				log.error("Error guardant document: No s'ha especificat id de procediment o de fitxer.");

			}

		} catch (FileUploadException fue) {

			String error = messageSource.getMessage( "error.fitxer.tamany", null, locale );
			jsonResult = new IdNomDTO(-3l, error).getJson();
			log.error( error + ": " + fue.toString() );

		} catch (UnsupportedEncodingException uee) {

			String error = messageSource.getMessage( "error.altres", null, locale );
			jsonResult = new IdNomDTO(-2l, error).getJson();
			log.error( error + ": " + uee.toString() );

		} catch (NumberFormatException nfe) {

			String error = messageSource.getMessage( "error.altres", null, locale );
			jsonResult = new IdNomDTO(-2l, error).getJson();
			log.error( error + ": " + nfe.toString() );

		} catch (DelegateException de) {

			String error = null;
			if ( de.isSecurityException() ) {

				error = messageSource.getMessage( "error.permisos", null, locale );
				jsonResult = new IdNomDTO(-1l, error).getJson();

			} else {

				error = messageSource.getMessage( "error.altres", null, locale );
				jsonResult = new IdNomDTO(-2l, error).getJson();

			}

			log.error( error + ": " + de.toString() );

		}

		return new ResponseEntity<String>(jsonResult, responseHeaders, HttpStatus.CREATED);

	}


	@RequestMapping(value = "/carregarDocument.do")
	public @ResponseBody Map<String, Object> carregarDocument(HttpServletRequest request)  {
		Map<String, Object> resultats = new HashMap<String, Object>();
		String lang = request.getLocale().getLanguage();

		try {
			Long id = new Long(request.getParameter("id"));
			DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
			Documento doc = docDelegate.obtenerDocumento(id);

			Map<String, Object> mapDoc = new HashMap<String, Object>();
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> idiomas = idiomaDelegate.listarLenguajes();
			TraduccionDocumento traDoc;

			for (String idioma: idiomas) {
				traDoc = (TraduccionDocumento) doc.getTraduccion(idioma);
				if (traDoc != null) {
					if (traDoc.getTitulo() != null) {
						mapDoc.put("idioma_titol_" + idioma, traDoc.getTitulo());
					} else {
						mapDoc.put("idioma_titol_" + idioma, "");
					}

					if (traDoc.getDescripcion() != null) {
						mapDoc.put("idioma_descripcio_" + idioma, traDoc.getDescripcion());
					} else {
						mapDoc.put("idioma_descripcio_" + idioma, "");
					}

					// archivo
					if (traDoc.getArchivo() != null) {
						mapDoc.put("idioma_enllas_arxiu_" + idioma, "documents/archivo.do?id=" + doc.getId() + "&lang=" + idioma);
						mapDoc.put("idioma_nom_arxiu_" + idioma, traDoc.getArchivo().getNombre());
					} else {
						mapDoc.put("idioma_enllas_arxiu_" + idioma, "");
						mapDoc.put("idioma_nom_arxiu_" + idioma, "");
					}
				}
			}
			mapDoc.put("item_id", doc.getId());

			resultats.put("document", mapDoc);
			resultats.put("id", doc.getId());

		} catch (NumberFormatException nfe) {
			log.error("El id del document no es n�meric: " + nfe.toString());
			resultats.put("id", -3);
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + dEx.toString());
				resultats.put("id", -1);
			} else {
				log.error("Error: " + dEx.toString());
				resultats.put("id", -2);
			}
		}

		return resultats;
	}


	@RequestMapping(value = "/archivo.do")
	public void devolverArchivoDocumento(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.devolverArchivo(request, response);   
	}

	@Override
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {		
		//obtener archivo concreto con el delegate
		Long id = new Long(request.getParameter("id"));
		String lang = request.getParameter("lang");
		DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
		return docDelegate.obtenerArchivoDocumento(id, lang, false);
	}

}
