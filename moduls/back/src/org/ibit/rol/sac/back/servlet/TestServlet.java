package org.ibit.rol.sac.back.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.ProcedimientoTransferible;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

/**
 * @web.servlet
 *   name="TestServlet"
 * @web.servlet-mapping
 *   url-pattern="/testWS"
 * @author arodrigo
 *
 */
public class TestServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(TestServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
			
		String idioma = arg0.getParameter("lang");
		String busqueda = arg0.getParameter("busqueda");
		
		try {
			String[] he = {"muerte"};
			String[] mat = {"AGR", "CAZA"};
			
			List<Materia> materias = DelegateUtil.getMateriaDelegate().buscar(busqueda, idioma);
		
			List<HechoVital> hechos = DelegateUtil.getHechoVitalDelegate().buscar(busqueda, idioma);
			log.info("inicio normal");
			Map map = DelegateUtil.getUADelegate().listarProcFichSecHV((long)3272, (long)58, "INFO");
			log.info("fin normal");
			if(map!=null){
				List<ProcedimientoLocal> procs = (List<ProcedimientoLocal>)map.get("PROCEDIMIENTOS");
				
				List<Ficha> fichas = (List<Ficha>)map.get("FICHAS");
				
				log.info("Procedimientos Encontradas: " + procs.size());
				for (ProcedimientoLocal proc : procs) {
					log.info("encontrado el Procedimiento " + ((TraduccionProcedimientoLocal)proc.getTraduccion()).getNombre());
				}
				
				log.info("Fichas Encontradas: " + fichas.size());
				for (Ficha ficha : fichas) {
					log.info("encontrado la Ficha " + ((TraduccionFicha)ficha.getTraduccion()).getTitulo());
				}
			}
			
			List<UnidadAdministrativa> unis = DelegateUtil.getUADelegate().buscar(busqueda, idioma);
			
			log.info("Hechos Encontrados: " + hechos.size());
			for (HechoVital hechoVital : hechos) {
				log.info("encontrado el hecho " + ((TraduccionHechoVital)hechoVital.getTraduccion()).getNombre());
			}
			
			log.info("Materias Encontradas: " + materias.size());
			for (Materia materia : materias) {
				log.info("encontrado la materia " + ((TraduccionMateria)materia.getTraduccion()).getNombre());
			}
			
			log.info("Unidades Encontradas: " + unis.size());
			for (UnidadAdministrativa uni : unis) {
				log.info("encontrado la Unidad " + ((TraduccionUA)uni.getTraduccion()).getNombre());
			}
		} catch (DelegateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
