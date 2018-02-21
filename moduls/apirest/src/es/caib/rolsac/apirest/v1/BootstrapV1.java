package es.caib.rolsac.apirest.v1;


import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

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
      .title("Rolsac REST API V1")
      .description("Servicio REST API V1 para la petición de datos de entidades. " + 
        "Servicio proporcionado por [http://www.caib.es](http://www.caib.es).")
      .version("1");
    
    Swagger swagger = new Swagger().info(info);
   // swagger.addScheme(Scheme.HTTP);
   // swagger.setHost("localhost:48080");
    swagger.setBasePath("/rolsac/api/rest/v1");
    swagger.externalDocs(new ExternalDocs("Más info contacte con el responsable.", "http://www.caib.es"));

   swagger.tag(new Tag()
      .name("idiomes")
      .description("Servicio para la obtención de información de los idiomas.")
      );
    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}