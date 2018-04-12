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

   swagger.tag(new Tag()
      .name(Constantes.ENTIDAD_IDIOMA)
      .description("Servicio para la obtención de información de los idiomas.")
      );
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_UA)
		      .description("Servicio para la obtención de información de las Unidades Administrativas.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ARUPACIO_FET_VITAL)
		      .description("Servicio para la obtención de información de las Agrupaciones de hechos vitales.")
		      );
   
   swagger.tag(new Tag()
		      .name(Constantes.ENTIDAD_ARUPACIO_MATERIES)
		      .description("Servicio para la obtención de información de las Agrupaciones de materias.")
		      );
      
    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}