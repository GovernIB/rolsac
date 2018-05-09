package es.caib.rolsac.apirest.v1;


import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

/**
 * Expecifica propiedades del api para swagger.
 * @author Indra
 *
 */
public class BootstrapV1 extends HttpServlet {
  /** Serial version UID.	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(BootstrapV1.class);

@Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Rolsac REST API " )
      .description("Servicio REST API " + Constantes.API_VERSION.toUpperCase() + " para la petición de datos de entidades. " + 
        "Servicio proporcionado por [http://www.caib.es](http://www.caib.es).")
      .version(Constantes.API_VERSION);
    
    Swagger swagger = new Swagger().info(info);
 
    try {
    	String url = Constantes.getUrlPropiedades();
    	if(!StringUtils.isEmpty(url)) {    		    
	    	URI uri = new URI(url);
	        swagger.addScheme(uri.getScheme().equals("https")?Scheme.HTTPS:Scheme.HTTP);
	        swagger.setHost(uri.getAuthority());
	        
    	}else {
    		log.error("ERROR No seha podido cargar la URL del API_REST, verifique que se ha definido correctamente la propiedad de URL para el api rest");
    	}
    	swagger.setBasePath(Constantes.URL_MODULO+Constantes.API_VERSION);
	} catch (Exception e) {
		 e.printStackTrace();
	}


    swagger.externalDocs(new ExternalDocs("Más info contacte con el responsable.", "http://www.caib.es"));

    String txt_descripcion = "Servicio para la obtención de información de";
    
   swagger.tag(new Tag()
      .name(Constantes.ENTIDAD_IDIOMA)
      .description(txt_descripcion + " los idiomas.")
      );
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_UA)
		      .description(txt_descripcion + " las unidades administrativas.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_AGRUPACIO_FET_VITAL)
		      .description(txt_descripcion + " las agrupaciones de hechos vitales.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_AGRUPACIO_MATERIES)
		      .description(txt_descripcion + " las agrupaciones de materias.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ARCHIVO)
		      .description(txt_descripcion + " los archivos.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_BOLETINES)
		      .description(txt_descripcion + " los boletines.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_CATALOGO_DOCUMENTOS)
		      .description(txt_descripcion + "l catalogo de documentos.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_DOCUMENTOS)
		      .description(txt_descripcion + " los documentos de fichas y procedimientos.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_DOCUMENTOS_TRAMITES)
		      .description(txt_descripcion + " los documentos de los trámites.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS)
		      .description(txt_descripcion + " los documentos de las normativas.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ESPACIO_TERRITORIAL)
		      .description(txt_descripcion + " los espacios territoriales.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_FAMILIA)
		      .description(txt_descripcion + " las familias.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_TIPO)
		      .description(txt_descripcion + " los tipos de normativas.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_TIPO_AFECTACION)
		      .description(txt_descripcion + " los tipos de afectación.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_FORMULARIO)
		      .description(txt_descripcion + " los formularios.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ICONO_FAMILIA)
		      .description(txt_descripcion + " los iconos familia.")
		      );

   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_MATERIA_AGRUPACION)
		      .description(txt_descripcion + " las materias agrupacion.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_PERFIL)
		      .description(txt_descripcion + " los perfiles.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_UNIDAD_MATERIA)
		      .description(txt_descripcion + " las unidades materias.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_EDIFICIO)
		      .description(txt_descripcion + " los edificios.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ENLACE)
		      .description(txt_descripcion + " los enlaces.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ESTADISTICAS)
		      .description("Servicio para la actualización de estadisticas de uso.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_HECHOS_VITALES)
		      .description(txt_descripcion + " los hechos vitales.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_FICHA)
		      .description(txt_descripcion + " las fichas.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_FICHAUA)
		      .description(txt_descripcion + " las fichasUA.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_MATERIA)
		      .description(txt_descripcion + " las materias.")
		      );
      
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_PERSONAL)
		      .description(txt_descripcion + " el personal.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_PUBLICO)
		      .description(txt_descripcion + " los publicos objetivo.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_SECCION)
		      .description(txt_descripcion + " las secciones.")
		      );

   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_PROCEDIMIENTO)
		      .description(txt_descripcion + " los procedimientos.")
		      );
    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}