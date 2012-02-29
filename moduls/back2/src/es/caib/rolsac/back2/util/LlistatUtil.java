package es.caib.rolsac.back2.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;

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
		return materiesDTOList;
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
	
	public static List<IdNomDTO> llistarPublicObjectius(String lang) throws DelegateException {
		PublicoObjetivoDelegate publicoObjetivoDelegate = DelegateUtil.getPublicoObjetivoDelegate();
		List<IdNomDTO> publicObjDTOList = new ArrayList<IdNomDTO>();
		List<PublicoObjetivo> llistaPublicObjetius = publicoObjetivoDelegate.listarPublicoObjetivo();
		TraduccionPublicoObjetivo tpo;
		for (PublicoObjetivo publicoObjetivo : llistaPublicObjetius) {
			tpo = (TraduccionPublicoObjetivo) publicoObjetivo.getTraduccion(lang);
			publicObjDTOList.add(new IdNomDTO(publicoObjetivo.getId(), tpo.getTitulo()));
		}
		return publicObjDTOList;
	}
}
