package org.ibit.rol.sac.persistence.util;

import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;

/**
 * Se encarga de gererar un informe con los datos de el objeto y de la excepcion
 * para a continuaci�n mandarlos por email al destinatario.
 * 
 * Las propiedades pueden ser configuradas antes de compilar en el buil.properties
 * del proyecto. O una vez compilado, deltro del jar de persistence en el archivo
 * email.properties
 * 
 * @author arodrigo
 *
 */
public class ReportarErrorComentario {
	
	//Indica la ruta de ResourceBundle con los datos de los Emails
	private static final String BUNDLE_PATH = "email";
	
	/**
	 * Lanza un proceso que se encargara de mandar el Email con el
	 * informe del Fallo
	 * 
	 * @param object Objeto el el que el proceso fallo
	 * @param borrar Indica si el proceso era de borrado o no
	 * @param destinatario {@link Destinatario} al que enviar el mail
	 * @param exception {@link Exception} ocurrida al intentar la actualizacion
	 */
	public static void reportarError(Object object,String tituloComentario,String contenidoComentario,Usuario informador,String idioma) {
		ReportarErrorComentarioThread reportador = new ReportarErrorComentario().new ReportarErrorComentarioThread(
				object,tituloComentario,contenidoComentario,informador,idioma);
		reportador.run();
	}
	
	/**
	 * proceso que se encargara de mandar el Email con el
	 * informe del Fallo
	 * 
	 * @author arodrigo
	 *
	 */
	private class ReportarErrorComentarioThread extends Thread {

		private final Object object;
		private final String tituloComentario;
		private final String contenidoComentario;
		private final Usuario usuario;
		private final String idioma;

		
		protected final Log log = LogFactory.getLog(ReportarErrorComentarioThread.class);
		
		/**
		 * Constructor con los parametros necesarios para que el proceso mande
		 * el mail con el informe del Fallo
		 * 
		 * @param object Objeto el el que el proceso fallo
		 * @param borrar Indica si el proceso era de borrado o no
		 * @param destinatario {@link Destinatario} al que enviar el mail
		 * @param exception {@link Exception} ocurrida al intentar la actualizacion
		 */
		public ReportarErrorComentarioThread(Object object,String tituloComentario,String contenidoComentario,Usuario informador,String idioma) {
			this.object = object;
			this.tituloComentario = tituloComentario;
			this.contenidoComentario = contenidoComentario;
			this.usuario=informador;
			this.idioma=idioma;
		}
		
		@Override
		public void run() {
			//Leo el archivo con las propiedades del mail
			ResourceBundle resource = ResourceBundle.getBundle(BUNDLE_PATH);
            log.debug("RESOURCE: " + resource);
			
			//Recojo los parametros necesarios del archivo d propiedades
			String jndi = resource.getString("jndi");
            log.debug("JNDI: " + jndi);
			//Obtenemos los e-mails del emisor(informador) y del receptor (responsable de la ficha/procedimiento)
			String emisor = usuario.getEmail();
            log.debug("MAIL EMISOR: " + emisor);
            
			String[] aDestinatario=null;
			
			if(object instanceof FichaRemota){
				FichaRemota ficha = (FichaRemota)object;
				if(ficha!=null){
					aDestinatario=obtenerResponsables(ficha.getResponsable());
					if(aDestinatario==null || aDestinatario.length<=0){
						if(ficha.getAdministracionRemota()!=null){
							if(ficha.getAdministracionRemota().getResponsable()!=null){
								aDestinatario=obtenerResponsables(ficha.getAdministracionRemota().getResponsable());
								log.debug("Opcion 2 : Obtenemos el E-mail del destinatario del responsable de la Unidad Adminitrativa Remota de la Ficha : "+aDestinatario.toString());
							}
						}	
					}
					else{log.debug("Opcion 1 : Obtenemos el E-mail del destinatario del responsable de la Ficha : "+aDestinatario.toString());}
				}
				
			}
				
			 if(object instanceof ProcedimientoRemoto){
				ProcedimientoRemoto procedimiento = (ProcedimientoRemoto)object;
				if(procedimiento!=null){
					aDestinatario=obtenerResponsables(procedimiento.getResponsable());
					if(aDestinatario==null || aDestinatario.length<=0){
						if(procedimiento.getAdministracionRemota()!=null){
							if(procedimiento.getAdministracionRemota().getResponsable()!=null && procedimiento.getAdministracionRemota().getResponsable().trim().length()>0){
								aDestinatario=obtenerResponsables(procedimiento.getAdministracionRemota().getResponsable());
								log.debug("Opcion 2 : Obtenemos el E-mail del destinatario del responsable de la Unidad Adminitrativa Remota del procedimiento: "+aDestinatario.toString());
							}
						}	
					}
					else{log.debug("Opcion 1 : Obtenemos el E-mail del destinatario del responsable del procedimiento : "+aDestinatario.toString());}
				}
			}
			//Una vez pasado por el proceso para obtener el destinatario, si lo ha encontrado mandar� un mail reportanto el error, en caso contrario se le comunicar� la imposibilidad de realizarlo.
			if(aDestinatario!=null && aDestinatario.length >0 && usuario.getEmail()!=null){
				//Construyo un Asunto
				String asunto = "Avís del Portal de l'informador";
				
				try {
					//Genero el EmailUtils con sus parametros necesarios
					EmailUtils emailUtils = new EmailUtils(jndi);
					log.debug("ENVIANDO E-MAIL COMUNICANDO ERROR EN UNA FICHA/COMENTARIO");
					log.debug("DATOS: ** Emisor: "+emisor+" **Receptor: "+aDestinatario.toString() + " **Nombre informador: "+usuario.getNombre()+" **tituloComentario: "+tituloComentario);
					//Envio el Email
					emailUtils.postMail(asunto, generarMensaje(object,emisor,usuario,tituloComentario,contenidoComentario,idioma),emisor,aDestinatario);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			else{
				log.warn("IMPOSIBLE COMUNICAR EL ERROR EN UNA FICHA/COMENTARIO DEL PORTAL DEL INFORMADOR VIA E-MAIL");
				log.warn("MOTIVO: No se ha encontrado una dirección de un responsable válida ");
				
				String asunto = "Imposible comunicar un av�s en portal de l'informador";
				
				try {
					//Genero el EmailUtils con sus parametros necesarios
					EmailUtils emailUtils = new EmailUtils(jndi);
					//Envio el Email
					emailUtils.postMail(asunto, generarMensajeError(object,tituloComentario,contenidoComentario,idioma),emisor, emisor);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			

		}
		
		
		/**
		 * Obtiene los diferentes responsables
		 */
	    private String[] obtenerResponsables(String responsables){
	    	String[] aResponsables=null;
	    	if(responsables!=null){
		    	aResponsables=responsables.split(",");
	    	}
			return aResponsables;
	    }
		
		/**
		 * Construye el cuerpo del informe de Fallo
		 * 
		 * @param objeto : el objeto en el que se ha producido el fallo (Ficha o procedimiento)
		 * ha ocurrido el error
		 * @return El contenido del mail
		 */
		private String generarMensajeError(Object object,String tituloComentario,String contenidoComentario,String idioma) {

			StringBuffer mensaje = new StringBuffer();
			mensaje.append("\nHa sigut imposible reportar l'error degut a que tant la fitxa/procediment i la seva unidat administrativa no tenen el responsable correctament definit.\n");
			mensaje.append("\n***Dades del av�s*** \n\n");

			if(object instanceof Ficha){
				FichaRemota ficha = (FichaRemota)object;
				TraduccionFicha trad=((TraduccionFicha)ficha.getTraduccion("ca"));
				
				//En caso que no tenga titulo, cogemos el default
				if(trad==null || trad.getTitulo()==null || trad.getTitulo().length()==0){
					trad=((TraduccionFicha)ficha.getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault")));
				}
				mensaje.append("\nFitxa: "+trad.getTitulo()+" \n");

				if(ficha.getUrlRemota()!=null && ficha.getUrlRemota().length()>0){
					mensaje.append("\nUrl: "+ficha.getUrlRemota()+idioma+" \n");
				}
				mensaje.append("\nComentari: "+StringEscapeUtils.unescapeHtml(tituloComentario)+"\n");
				mensaje.append("\nContingut: "+StringEscapeUtils.unescapeHtml(contenidoComentario)+"\n");
			}
			else if(object instanceof ProcedimientoRemoto){
				ProcedimientoRemoto procedimiento = (ProcedimientoRemoto)object;
				TraduccionProcedimientoLocal trad=((TraduccionProcedimientoLocal)procedimiento.getTraduccion("ca"));
				
				//En caso que no tenga titulo, cogemos el default
				if(trad==null || trad.getNombre()==null || trad.getNombre().length()==0){
					trad=((TraduccionProcedimientoLocal)procedimiento.getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault")));
				}
				mensaje.append("\nProcedimiento: "+trad.getNombre()+"\n");
				if(procedimiento.getUrlRemota()!=null && procedimiento.getUrlRemota().length()>0){
				mensaje.append("\nUrl: "+procedimiento.getUrlRemota()+idioma+"\n");
				}
				mensaje.append("\nComentario: "+StringEscapeUtils.unescapeHtml(tituloComentario)+"\n");
				mensaje.append("\nContenido: "+StringEscapeUtils.unescapeHtml(contenidoComentario)+"\n");
			}

			return mensaje.toString();
		}

	}
		
		
		/**
		 * Construye el cuerpo del informe de Fallo
		 * 
		 * @param objeto : el objeto en el que se ha producido el fallo (Ficha o procedimiento)
		 * ha ocurrido el error
		 * @return El contenido del mail
		 */
	private String generarMensaje(Object object,String emisor,Usuario usuario,String tituloComentario,String contenidoComentario,String idioma) {

		StringBuffer mensaje = new StringBuffer();
		
		mensaje.append("\nEm complau comunicar-vos que s�ha posat en marxa una aplicaci� inform�tica anomenada Portal de l�informador, dins el marc del projecte del web multiinstitucional, amb la finalitat de facilitar al personal  informador i a la  ciutadania la recerca de la informaci� administrativa de la CAIB i de les entitats locals en un sol web. En aquests moments, la primera Administraci� que ha format part d�aquest projecte impulsat per la Direcci� General de Qualitat dels Serveis ha estat el Consell Insular de Menorca. \n");
		mensaje.append("\nCal tenir en compte que la informaci� de la Comunitat Aut�noma que apareix en el Portal de l�informador  s�obt� directament del ROLSAC, on vosaltres heu introdu�t la informaci�. Aquesta eina, a m�s de facilitar la recerca d�informaci�, permetr� detectar errades i alhora servir� per millorar la informaci� que es troba al web de la CAIB. \n");
		mensaje.append("\n�s important que tengueu cura d�emplenar amb rigor les caselles del ROLSAC per tal que en el Portal de l�informador la informaci� es classifiqui de forma adequada. \n");
		
		mensaje.append("\nAmb la finalitat de millorar la qualitat de la informaci� que es facilita a la ciutadania, el Portal de l�informador ha detectat un error de : ");
		if(object instanceof FichaRemota){
			FichaRemota ficha = (FichaRemota)object;
			TraduccionFicha trad=((TraduccionFicha)ficha.getTraduccion("ca"));
			
			//En caso que no tenga titulo, cogemos el default
			if(trad==null || trad.getTitulo()==null || trad.getTitulo().length()==0){
				trad=((TraduccionFicha)ficha.getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault")));
			}
			String url="";
			if(ficha.getUrlRemota()!=null && ficha.getUrlRemota().length()>0){url=ficha.getUrlRemota()+idioma;}
			mensaje.append(trad.getTitulo()+" "+url);
			if(ficha.getAdministracionRemota()!=null){mensaje.append(" ( "+ficha.getAdministracionRemota().getNombre()+" )");}
		}
		else if(object instanceof ProcedimientoRemoto){
			ProcedimientoRemoto procedimiento = (ProcedimientoRemoto)object;
			TraduccionProcedimientoLocal trad=((TraduccionProcedimientoLocal)procedimiento.getTraduccion("ca"));
			
			//En caso que no tenga titulo, cogemos el default
			if(trad==null || trad.getNombre()==null || trad.getNombre().length()==0){
				trad=((TraduccionProcedimientoLocal)procedimiento.getTraduccion(System.getProperty("es.caib.rolsac.idiomaDefault")));
			}
			String url="";
			if(procedimiento.getUrlRemota()!=null && procedimiento.getUrlRemota().length()>0){url=procedimiento.getUrlRemota()+idioma;}
			mensaje.append(trad.getNombre()+" "+url);
			if(procedimiento.getAdministracionRemota()!=null){mensaje.append(" ( "+procedimiento.getAdministracionRemota().getNombre()+" )");}

		}

		mensaje.append("\nConcretament, l'errada �s la seg�ent: "+StringEscapeUtils.unescapeHtml(tituloComentario)+"\n");
		mensaje.append("\nContingut: "+StringEscapeUtils.unescapeHtml(contenidoComentario)+"\n");
		String datosInformador1=usuario.getNombre();
		mensaje.append("\nUs agrair� que, com m�s aviat millor, corregiu aquesta errada i comuniqueu a l'informador '"+datosInformador1+"' que us ha tram�s aquest missatge electr�nic que l�errada ha estat corregida. En el cas que no sigui una errada, tamb� us agrair� que ho comuniqueu a l�adre�a electr�nica seg�ent: "+usuario.getEmail()+" \n");
		mensaje.append("\nGr�cies per la vostra col.laboraci�.\n");
		mensaje.append("\nAtentament, \n\n");
		mensaje.append("P.D.: Aquest projecte est� impulsat per la Direcci� General de Qualitat dels Serveis del Govern de les Illes Balears. En cas de dubte, us podeu adre�ar al tel�fon : 971 179 576. \n");
		return mensaje.toString();
	}



}
