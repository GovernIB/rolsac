package es.caib.rolsac.back2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.DocumentoResumen;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoResumenDelegate;
import org.ibit.rol.sac.persistence.delegate.IconoFamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IconoMateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

/**
 * Métodos de guardado y soporte para el guardado AJAX de módulos laterales.
 * 
 * @author e43155798r
 */
public class GuardadoAjaxUtil {
	
	/**
	 * Obtiene las materias especificadas mediante su ID en el array de elementos y las devuelve en un Set.
	 * 
	 * @param elementos
	 * @return
	 * @throws DelegateException
	 */
	public static Set<Materia> obtenerMateriasRelacionadas(Long[] elementos) throws DelegateException {
		
		Set<Materia> materias = new HashSet<Materia>();
		
		if (elementos != null && elementos.length > 0) {

			List<Long> materiasList = new Vector<Long>();
			
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			
			for (int i = 0; i < elementos.length; i++)
				materiasList.add(elementos[i]);
			
			materias.addAll(materiaDelegate.obtenerMateriasPorIDs(materiasList, DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));
			
		}
		
		return materias;
		
	}
	
	/**
	 * Se obtienen los iconos que hay que borrar en función de los que han llegado para ser procesados
	 * (las adiciones se hacen en IconaMateriaBackController).
	 * 
	 * @param elementos
	 * @param familia
	 * @param materia
	 * @return
	 * @throws DelegateException 
	 */
	public static void actualizarIconos(Long[] elementos, Familia familia, Materia materia) 
			throws DelegateException {
				
		Set<Long> iconosABorrar = new HashSet<Long>();
		Set iconosAnteriores = null;
		
		// De este modo, si ambos valores son nulos, devolvemos una lista vacía.
		if (familia != null || materia != null) {
		
			if (familia != null)
				iconosAnteriores = familia.getIconos();
			else if (materia != null)
				iconosAnteriores = materia.getIconos();
			
			if (iconosAnteriores != null) {
			
				Boolean iconoEncontrado = null;
				Long idIcono = null;
				Iterator it = iconosAnteriores.iterator();
				
				while ( it.hasNext() ) {
					
					iconoEncontrado = Boolean.FALSE;
					
					if (familia != null)
						idIcono = ((IconoFamilia)it.next()).getId();
					else if (materia != null)
						idIcono = ((IconoMateria)it.next()).getId();
					
					for (Long iconaId : elementos) {
						if (idIcono.equals(iconaId)) {
							iconoEncontrado = Boolean.TRUE;
							break;
						}
					}
					
					if (!iconoEncontrado) {
						iconosABorrar.add(idIcono);
					}
					
				}
			
			}
			
			if (familia != null) {
				IconoMateriaDelegate iconaMateriaDelegate = DelegateUtil.getIconoMateriaDelegate();
				iconaMateriaDelegate.borrarIconosMateria(iconosABorrar);
			}
			
			else if (materia != null) {
				IconoFamiliaDelegate iconaFamiliaDelegate = DelegateUtil.getIconoFamiliaDelegate();
				iconaFamiliaDelegate.borrarIconosFamilia(iconosABorrar);
			}
		
		}
		
	}
	
	/**
	 * Borra los documentos relacionados con un procedimiento o una ficha que ya no se especifican vía id en el array de elementos.
	 * Además, los ordena según su orden en el array de elementos (orden de colocación en el mantenimiento afectado).
	 * 
	 * @param elementos
	 * @param procedimiento
	 * @param ficha
	 * @return
	 * @throws DelegateException
	 */
	public static List<Documento> actualizarYOrdenarDocumentosRelacionados(Long[] elementos, ProcedimientoLocal procedimiento, Ficha ficha) 
			throws DelegateException {
		
		List<Documento> documentos = new ArrayList<Documento>();
		Map <String, String[]> actualizadorMap = new HashMap<String, String[]>();
		
		List<Documento> documentosABorrar = null;
		if (procedimiento != null)
			documentosABorrar = procedimiento.getDocumentos();
		else if (ficha != null)
			documentosABorrar = ficha.getDocumentos();
		
		if ( elementos != null ) {
			
			int orden = 1;
			
			for (int i = 0; i < elementos.length; i++) {
				
				// Buscamos el id del documento. Si está en los documentos anteriores, no hay que borrarlo.
				Iterator<Documento> it = documentosABorrar.iterator();
				boolean borrarDocumento = true;
				
				while ( it.hasNext() ) {
					
					Documento d = it.next();
					
					// Si encontramos el documento, lo quitamos de la lista y salimos del while.
					if ( d != null && d.getId().equals(elementos[i]) ) {
						it.remove();
						borrarDocumento = false;
						break;
					}
					
				}
				
				// Si no hay que borrar el documento, lo procesamos.
				if ( !borrarDocumento ) {
					
					DocumentoResumen docResumen = DelegateUtil.getDocumentoResumenDelegate().obtenerDocumentoResumen(elementos[i]);
					
					Documento doc = new Documento();
					doc.setId(docResumen.getId());
					doc.setFicha(docResumen.getFicha());
					
					// Aquí no ponemos el orden en función de la variable i, ya que es posible que no se entre en
					// este bloque de código en cada iteración.
					doc.setOrden( orden );
					
					String[] ordenMap = {String.valueOf(orden)};
					actualizadorMap.put("orden_doc" + doc.getId(), ordenMap);
					orden++;
					
					doc.setProcedimiento(docResumen.getProcedimiento());
					doc.setTraduccionMap(docResumen.getTraduccionMap());
					
					documentos.add(doc);
											
				}
				
			}
			
		}
		
		// Borramos documentos que no hayamos encontrado en el envío.
		for (Documento d : documentosABorrar) {
			if (d != null)
				DelegateUtil.getDocumentoDelegate().borrarDocumento(d.getId());
		}
		
		// FIXME amartin: actualmente no funciona el tema de la ordenación con los documentos asociados a un procedimiento.
		// Con los documentos asociados a fichas funciona perfectamente y se sigue el mismo algoritmo. ¿Problema del modelo de datos?
		// Ver comentario en método DocumentoResumenFacadeEJB.actualizarOrdenDocs().
		DocumentoResumenDelegate documentoResumenDelegate = DelegateUtil.getDocumentoResumenDelegate();
		// Guardamos documentos actuales (actualizar orden).
		documentoResumenDelegate.actualizarOrdenDocs(actualizadorMap);
		
		return documentos;
		
	}
	
}
