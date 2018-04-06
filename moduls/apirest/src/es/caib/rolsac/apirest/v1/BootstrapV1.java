package es.caib.rolsac.apirest.v1;


import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import io.swagger.models.Scheme;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

/**
 * Expecifica propiedades del api para swagger.
 * @author slromero
 *
 */
public class BootstrapV1 extends HttpServlet {
  /** Serial version UID.	 */
	private static final long serialVersionUID = 1L;

@Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Rolsac REST API " )
      .description("Servicio REST API " + Constantes.API_VERSION.toUpperCase() + " para la petición de datos de entidades. " + 
        "Servicio proporcionado por [http://www.caib.es](http://www.caib.es).")
      .version(Constantes.API_VERSION);
    
    Swagger swagger = new Swagger().info(info);
 
    try {
    	URI uri = new URI(Constantes.getUrlPropiedades());
        swagger.addScheme(uri.getScheme().equals("https")?Scheme.HTTPS:Scheme.HTTP);
        swagger.setHost(uri.getAuthority());
        swagger.setBasePath(Constantes.URL_MODULO+Constantes.API_VERSION);
	} catch (Exception e) {
		 e.printStackTrace();
	}


    swagger.externalDocs(new ExternalDocs("Más info contacte con el responsable.", "http://www.caib.es"));

   swagger.tag(new Tag()
      .name("idiomes")
      .description("Servicio para la obtención de información de los idiomas.")
      );
   swagger.tag(new Tag()
		      .name("unitats_administratives")
		      .description("Servicio para la obtención de información de las Unidades Administrativas.")
		      );
    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}