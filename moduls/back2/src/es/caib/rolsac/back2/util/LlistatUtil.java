package es.caib.rolsac.back2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.util.POUtils;

public class LlistatUtil {

	public static List<IdNomDTO> llistarIniciacions(String lang) throws DelegateException {
		IniciacionDelegate id = DelegateUtil.getIniciacionDelegate();
		List<IdNomDTO> iniciacionDTOList = new LinkedList<IdNomDTO>();
		List<Iniciacion> iniciaciones = id.listarIniciacion();
		TraduccionIniciacion ti;
		for (Iniciacion i : iniciaciones) {
			ti = (TraduccionIniciacion) i.getTraduccion(lang);
			iniciacionDTOList.add(new IdNomDTO(i.getId(), ti.getNombre()));
		}
		return iniciacionDTOList;
	}

	public static List<IdNomDTO> llistarFamilias(String lang) throws DelegateException {
		FamiliaDelegate fd = DelegateUtil.getFamiliaDelegate();
		List<IdNomDTO> familiasDTOList = new LinkedList<IdNomDTO>();
		List<Familia> familias = fd.listarFamilias();
		TraduccionFamilia tf;
		for (Familia f : familias) {
			tf = (TraduccionFamilia) f.getTraduccion(lang);
			familiasDTOList.add(new IdNomDTO(f.getId(), tf.getNombre()));
		}
		return familiasDTOList;
	}

	public static List<IdNomDTO> llistarMaterias(String lang) throws DelegateException {
		MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
		List<IdNomDTO> materiesDTOList = new ArrayList<IdNomDTO>();
		List<Materia> llistaMateries = materiaDelegate.listarMaterias();
		for (Materia materia : llistaMateries) {
			materiesDTOList.add(new IdNomDTO(materia.getId(), materia.getNombreMateria(lang)));
		}
		//Ordenamos por nombreMateria
		Collections.sort(materiesDTOList, new ComparatorNombreMateriaASC());
		
		return materiesDTOList;
	}
	
	 private static class ComparatorNombreMateriaASC implements Comparator<IdNomDTO> {
			

			public int compare(IdNomDTO o1, IdNomDTO o2) {
				int comparacion = 0;
				if ((o1 != null) && (o2 != null)) {
					comparacion =  o1.getNom().toUpperCase().compareTo(o2.getNom().toUpperCase());
				}
				return comparacion;
			}
		
	    }

	public static List<IdNomDTO> llistarHechosVitales(String lang) throws DelegateException {
		HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();
		List<IdNomDTO> fetsDTOList = new ArrayList<IdNomDTO>();
		List<HechoVital> llistaFetsVitals = hechoVitalDelegate.listarHechosVitales();
		TraduccionHechoVital tfv;
		for (HechoVital fetVital : llistaFetsVitals) {
			tfv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
			fetsDTOList.add(new IdNomDTO(fetVital.getId(), tfv.getNombre()));
		}
		return fetsDTOList;
	}
	
	/**
	 * lista los públicos objetivos (no incluye los internos).
	 * @param lang
	 * @return
	 * @throws DelegateException
	 */
	public static List<IdNomDTO> llistarPublicObjectius(String lang) throws DelegateException {	
		//por defecto no se debe incluir este público objetivo (interno)
		return POUtils.llistarPublicObjectius(lang,false);
	}
	

	public static List<IdNomDTO> llistarHechosVitales(Set<?> publicosObjetivo, String lang) throws DelegateException {
		
		HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();
		List<IdNomDTO> fetsDTOList = new ArrayList<IdNomDTO>();
		List<HechoVital> llistaFetsVitals = hechoVitalDelegate.listarHechosVitales(publicosObjetivo, lang);
		TraduccionHechoVital tfv;
		
		for (HechoVital fetVital : llistaFetsVitals) {
			
			tfv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
			fetsDTOList.add( new IdNomDTO( fetVital.getId() , tfv.getNombre() ) );
		}
		
		return fetsDTOList;
		
	}
	
}
