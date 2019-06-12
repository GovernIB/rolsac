package org.ibit.rol.sac.persistence.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;

public class POUtils {
	protected static Log log = LogFactory.getLog(POUtils.class);

	
	/**
	 * lista los públicos objetivos y permite ocultar el público objetivo interno.
	 * @param lang
	 * @param mostrarInterno (si es true se muestran todos los publicos objetovo, si es false no se muestran los internos)
	 * @return
	 * @throws DelegateException
	 */
	public static List<IdNomDTO> llistarPublicObjectius(String lang, boolean mostrarInterno ) throws DelegateException {
		PublicoObjetivoDelegate publicoObjetivoDelegate = DelegateUtil.getPublicoObjetivoDelegate();
		List<IdNomDTO> publicObjDTOList = new ArrayList<IdNomDTO>();
		List<PublicoObjetivo> llistaPublicObjetius = publicoObjetivoDelegate.listarPublicoObjetivo();
		TraduccionPublicoObjetivo tpo;
		for (PublicoObjetivo publicoObjetivo : llistaPublicObjetius) {
			tpo = (TraduccionPublicoObjetivo) publicoObjetivo.getTraduccion(lang);
			if(mostrarInterno || !publicoObjetivo.isInterno()){
				publicObjDTOList.add(new IdNomDTO(publicoObjetivo.getId(), tpo.getTitulo()));
			}
		}
		return publicObjDTOList;
	}
	
/*	public static boolean esPublicoObjetivoInterno(String codigoPO) {
		boolean res = false;
		
		try {
			res = codigoPO.equals(getPublicoObjetivoInterno());
		}catch(Exception e) {
			res=false;			
		}
		return res;		
	}	*/
	
	
	/**
	 * Retorna el PO interno (solo puede haber uno)
	 * @return
	 */
	public static String  getPublicoObjetivoInterno() {
		String res = "";	
		PublicoObjetivoDelegate publicoObjetivoDelegate = DelegateUtil.getPublicoObjetivoDelegate();
		List<PublicoObjetivo> llistaPublicObjetius;
		try {
			llistaPublicObjetius = publicoObjetivoDelegate.listarPublicoObjetivo();		
			for (PublicoObjetivo publicoObjetivo : llistaPublicObjetius) {
				if(publicoObjetivo.isInterno()) {
					res = publicoObjetivo.getId().toString();
					break;
				}
			}		
		} catch (DelegateException e) {			
			log.error("Error al obtener el código del publico objetivo interno" , e);
		}
		//res = RolsacPropertiesUtil.getPropiedadPOInterno();
		return res;		
	}	
	
	
	
	
	public static boolean contienePOInterno(Set<PublicoObjetivo> publicosObjetivo) {
		boolean res = false;
		try {
			for (final PublicoObjetivo publicoObjectivo : publicosObjetivo) {				
				if(publicoObjectivo.isInterno()) {
					res = true;
					break;
				}			
			}	
		} catch (Exception e) {
			log.error("Error al verificar si un procedimiento o un servicio contienen un publico objetivo interno" , e);
		}	
		return res;
	}
	
	
	

	
	/**
	 * Verifica si la lista de publicos objetivos es válida. Si contiene un PO interno no puede contener nada mas
	 * @param listaPO lista de id's de los públicos objetivos separados por coma.
	 * @return
	 */
	public static boolean validaPublicosObjetivos(String listaPO) {
		String procInterno = getPublicoObjetivoInterno();
		Boolean res = true;
		if(!StringUtils.isEmpty(procInterno)) {
			//si la propiedad no existe retornamos true
			
			String[] lPO = listaPO.split(",");
			boolean encontrado = false;
			int numElementos = 0;
			for (String po : lPO) {
				if(po.length()>=1) {
					numElementos++;
				}
				if(po.equals(procInterno)) {
					encontrado=true;
				}
			}
			
			if (encontrado && numElementos>1) {
				//el publico objetivo interno es excluyente, si está seleccionado no puede haber otros seleccionados.
				res=false;
			}
		}		
		return res;			
	}
	
}
