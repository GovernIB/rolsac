package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Estadistica;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.rolsac.back2.util.Graficas;
import es.caib.rolsac.back2.util.Parametros;

@Controller
@RequestMapping(value = "/quadreControl/")
public class QuadreControlController extends ArchivoController {

	private MessageSource messageSource = null;

	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/quadreControl.do")
	public String quadreControl(HttpSession session,
			HttpServletRequest request, Map<String, Object> model)
			throws ServletException, IOException {

		GregorianCalendar dataActual = new GregorianCalendar();
		model.put("dataActual", dataActual.getTime());
		UnidadAdministrativa unitatAdministrativa = new UnidadAdministrativa();

		if (session.getAttribute("unidadAdministrativa") != null) {
			unitatAdministrativa = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
			model.put("idUA", unitatAdministrativa.getId());
			model.put("nomUA",unitatAdministrativa.getNombreUnidadAdministrativa());
		}

		model.put("menu", 0);
		model.put("submenu", "layout/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 0);
		model.put("titol_escriptori", messageSource.getMessage("submenu.quadre_control", null, request.getLocale()));
		model.put("escriptori", "pantalles/quadreControl.jsp");

		// Darreres Modificacions
		try {
			EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
			
			GregorianCalendar dataActualFi = dataActual;
			dataActualFi.add(Calendar.DATE, -7);

			Map<Timestamp, Object> llistaCanvis = eDelegate.listarUltimasModificaciones(dataActual.getTime(),dataActualFi.getTime(), Parametros.NUMERO_REGISTROS,unitatAdministrativa);

			model.put("darreresModificacions", llistaCanvis);

		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				String error = messageSource.getMessage("error.permisos", null,request.getLocale());
			} else {
				String error = messageSource.getMessage("error.altres", null,request.getLocale());
				dEx.printStackTrace();
			}
		}
		
		// Nombre de continguts
		try {
			// Procediment
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			int procedimentActiu = procedimientoDelegate.buscarProcedimientosActivos(unitatAdministrativa,dataActual.getTime());
			int procedimentCaducat = procedimientoDelegate.buscarProcedimientosCaducados(unitatAdministrativa,dataActual.getTime());
			model.put("procedimentActiu", procedimentActiu);
			model.put("procedimentCaducat", procedimentCaducat);
			model.put("procedimentTotal", procedimentActiu + procedimentCaducat);

			// Normativa
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			int normativaActiva = normativaDelegate.buscarProcedimientosActivos(unitatAdministrativa);
			model.put("normativaActiva", normativaActiva);

			// Fitxa
			FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
			int fitxaActiva = fichaDelegate.buscarFichasActivas(unitatAdministrativa, dataActual.getTime());
			int fitxaCaducada = fichaDelegate.buscarFichasCaducadas(unitatAdministrativa, dataActual.getTime());

			model.put("fitxaActiva", fitxaActiva);
			model.put("fitxaCaducada", fitxaCaducada);
			model.put("fitxaTotal", fitxaActiva + fitxaCaducada);

		} catch (DelegateException e) {
			e.printStackTrace();
		}

		return "index";
	}
	

    @RequestMapping(value = "/estadisticaUA.do", method = GET)
    public void mostrarImagen(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }
    
    
	@Override
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {		
        //obtener archivo concreto con el delegate
        Long idUA = new Long(request.getParameter("id"));
                
        Periodo periodo = PeriodoUtil.crearPeriodoAnual();
		EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
		List<Estadistica> datosEstadistica;
		
		// Obtenim les dades 
		datosEstadistica = eDelegate.listarEstadisticasUnidad(idUA, periodo.getFechaInicio(),periodo.getFechaFin());
		
		// Generam la grafica
		JFreeChart chart = Graficas.pintarGrafica(datosEstadistica);
		
		// Transformacions de la grafica
		BufferedImage image = chart.createBufferedImage(Parametros.WIDTH_ESTADISTICA_QUADRE_CONTROL, Parametros.HEIGHT_ESTADISTICA_QUADRE_CONTROL);
        				
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		
		Archivo archivo = new Archivo();
		
		archivo.setDatos(imageInByte);
		archivo.setId(idUA);
		archivo.setMime("image/png");
		archivo.setNombre("estadisticaUA");
		
        return archivo; 
	}
	
}
