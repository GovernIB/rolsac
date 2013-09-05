package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static es.caib.rolsac.utils.StringUtils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Estadistica;
import org.ibit.rol.sac.model.Periodo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.util.PeriodoUtil;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.rolsac.back2.util.Graficas;
import es.caib.rolsac.back2.util.Parametros;

@Controller
@RequestMapping(value = "/estadistiques/")
public class GraficasEstadisticasController extends ArchivoController {

	private static Log log = LogFactory.getLog(GraficasEstadisticasController.class);	
	
	private MessageSource messageSource = null;

	
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	@RequestMapping(value = "/grafica.do", method = GET)
    public void mostrarImagen(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        this.devolverArchivo(request, response);
        
    }
    
    
	@Override
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {	

		//TODO 05/09/2013: Internacionalizar textos.
		//obtener archivo concreto con el delegate
        String tipus = request.getParameter("tipus");
        if ( vacio(tipus) || tipus.equals( Parametros.TIPUS_GRAFICA_QUADRE_CONTROL ) ) {
        	
        	return this.obtenerArchivoQuadreControl(request);
        	
        } else if ( tipus.equals( Parametros.TIPUS_GRAFICA_MODUL ) ) {
        	
        	return this.obtenerArchivoModul(request);
        	
        } else {
        	
        	throw new IllegalArgumentException ("Tipus de gráfica desconeguda");
        	
        }
	}

	
	private Archivo obtenerArchivoModul(HttpServletRequest request) throws Exception {		

        String tipusModul = request.getParameter("tipusModul");
		Long id = new Long(request.getParameter("id"));
		Periodo periodo = PeriodoUtil.crearPeriodoAnual();

		EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
		Archivo archivo = new Archivo();
		List<Estadistica> datosEstadistica = null;

		// Obtenim les dades 
		if (tipusModul.equals(Parametros.MODUL_GRAFICA_UA) ) {

			datosEstadistica = eDelegate.listarEstadisticasUnidad(id, periodo.getFechaInicio(),periodo.getFechaFin());

		} else if (tipusModul.equals(Parametros.MODUL_GRAFICA_PROCEDIMENT) ) {
			
			datosEstadistica = eDelegate.listarEstadisticasProcedimiento(id, periodo.getFechaInicio(),periodo.getFechaFin());
			
        } else if (tipusModul.equals(Parametros.MODUL_GRAFICA_MATERIA) ) {

			datosEstadistica = eDelegate.listarEstadisticasMateria(id, periodo.getFechaInicio(),periodo.getFechaFin());

        } else if (tipusModul.equals(Parametros.MODUL_GRAFICA_NORMATIVA) ) {

			datosEstadistica = eDelegate.listarEstadisticasNormativa(id, periodo.getFechaInicio(),periodo.getFechaFin());

        } else if (tipusModul.equals(Parametros.MODUL_GRAFICA_FITXA) ) {

        	datosEstadistica = eDelegate.listarEstadisticasFicha(id, periodo.getFechaInicio(),periodo.getFechaFin(), null, null);

        } else {
        	throw new IllegalArgumentException ("Tipus de gr�fica desconeguda");
        }

       
			
			
		// Generam la grafica
		JFreeChart chart = Graficas.pintarGraficaSimple(datosEstadistica);
			
		construirArchivo(id, archivo, chart, "estadistica" + tipusModul, Parametros.WIDTH_ESTADISTICA_MODULO, Parametros.HEIGHT_ESTADISTICA_MODULO);
		        		
        return archivo; 
	}

	

	private Archivo obtenerArchivoQuadreControl(HttpServletRequest request) throws Exception {		
        //obtener archivo concreto con el delegate

        Long idUA = new Long( request.getParameter("id") );
        Integer tipoOperacion = new Integer( request.getParameter("tipoOperacion") );
        
        
        // Comprovamos si tenemos que recorrer todos los nodos
        String todoArbol = request.getParameter("allUA");
        List<Long> llistaUnitatAdministrativaId = new ArrayList<Long>();
        
        if ( todoArbol != null && !"".equals(todoArbol) ) {
        	
        	UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
			llistaUnitatAdministrativaId = unitatAdministrativaDelegate.cargarArbolUnidadId(idUA);
			
		} else {
			
			llistaUnitatAdministrativaId.add(idUA);
			
		}
        
		EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
		Archivo archivo = new Archivo();
		
		if ( Parametros.GRAFICA_ESTADISTICA.equals(tipoOperacion) ) {
			
			Periodo periodo = PeriodoUtil.crearPeriodoAnual();
			
			// Obtenim les dades 
			List<Estadistica> datosEstadistica = eDelegate.listarEstadisticasListaUnidadAdministrativaId( llistaUnitatAdministrativaId , periodo.getFechaInicio() , periodo.getFechaFin() );
			
			// Generam la grafica
			JFreeChart chart = Graficas.pintarGraficaSimple(datosEstadistica);
			
			construirArchivo( idUA , archivo , chart , "estadisticaUnitatAdministrativa", Parametros.WIDTH_ESTADISTICA_QUADRE_CONTROL , Parametros.HEIGHT_ESTADISTICA_QUADRE_CONTROL );
			
		} else {
			
			GregorianCalendar dataActual = new GregorianCalendar();
			GregorianCalendar dataActualFi = new GregorianCalendar();

			// Invertim Ordre de visualitzacio
			dataActual.add( Calendar.DATE, -Parametros.GRAFICA_RESUM_PERIODE );
			dataActualFi.add( Calendar.DATE, -Parametros.GRAFICA_RESUM_PERIODE );
			dataActualFi.add( Calendar.DATE, +1 );
			
			List<List<Integer>> datosResumen = new ArrayList();
			String titulo = "";
			
			// Obtenim les dades 
			for ( int i = 0 ; i < Parametros.GRAFICA_RESUM_PERIODE ; i++ ) {
				
				if ( Parametros.GRAFICA_RESUM_ALTA.equals(tipoOperacion) ) {
					
					datosResumen.add( eDelegate.resumenOperativa( dataActual.getTime(), dataActualFi.getTime() , Auditoria.INSERTAR , llistaUnitatAdministrativaId ) );
					titulo = "resumAlta";
					
				} else if ( Parametros.GRAFICA_RESUM_MODIFICACIO.equals(tipoOperacion) ) {
					
					datosResumen.add( eDelegate.resumenOperativa( dataActual.getTime(), dataActualFi.getTime() , Auditoria.MODIFICAR , llistaUnitatAdministrativaId ) );
					titulo = "resumModificar";
					
				} else if ( Parametros.GRAFICA_RESUM_BAIXA.equals(tipoOperacion) ) {
					
					datosResumen.add( eDelegate.resumenOperativa( dataActual.getTime(), dataActualFi.getTime(), Auditoria.BORRAR, llistaUnitatAdministrativaId ) );
					titulo = "resumBaixa";
					
				}
				
				dataActual.add( Calendar.DATE, +1 );
				dataActualFi.add( Calendar.DATE, +1 );
				
			}
			
			// Generam la grafica
			// Invertim Ordre de visualitzacio
			dataActual.add( Calendar.DATE, -Parametros.GRAFICA_RESUM_PERIODE );
			JFreeChart chart = Graficas.pintarGraficaMultiple( datosResumen, dataActual );
			
			this.construirArchivo( idUA, archivo, chart, titulo, Parametros.WIDTH_ESTADISTICA_QUADRE_CONTROL, Parametros.HEIGHT_ESTADISTICA_QUADRE_CONTROL );
			
		}
		        		
        return archivo; 
        
	}

	
	/**
	 * @param idUA
	 * @param archivo
	 * @param chart
	 * @throws IOException
	 */
	private void construirArchivo(Long idUA, Archivo archivo, JFreeChart chart, String nombreArchivo, int ancho, int alto) throws IOException {
		
		// Transformacions de la grafica
		BufferedImage  image = chart.createBufferedImage(ancho, alto);
						
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "png", baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		
		//Creamos archivo
		archivo.setDatos(imageInByte);
		archivo.setId(idUA);
		archivo.setMime("image/png");
		archivo.setNombre(nombreArchivo);
	}
	
}
