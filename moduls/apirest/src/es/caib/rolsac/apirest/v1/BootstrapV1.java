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
	
	 //super.init(config);

    Info info = new Info()
      .title("Rolsac REST API V1")
      .description("Servicio REST API V1 para la petición de datos de entidades. " + 
        "Servicio proporcionado por [http://www.caib.es](http://www.caib.es).")
      .version("1")
      //.termsOfService("http://www.caib.es/terms/")
      //.contact(new Contact().email("apiteam@caib.es"))
      //.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
      ;
    
    Swagger swagger = new Swagger().info(info);
    swagger.addScheme(Scheme.HTTP);
    swagger.setHost("localhost:48080");
    swagger.setBasePath("/rolsac/apirest/services/v1");
    swagger.externalDocs(new ExternalDocs("Más info contacte con el responsable.", "http://www.caib.es"));
    /* Capa de seguridad innecesaria.
    swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
    swagger.securityDefinition("petstore_auth", 
      new OAuth2Definition()
        .implicit("http://petstore.swagger.io/api/oauth/dialog")
        .scope("read:pets", "read your pets")
        .scope("write:pets", "modify pets in your account"));*/
    swagger.tag(new Tag()
      .name("idiomas")
      .description("Servicio para la obtención de información de los idiomas.")
      );
    swagger.tag(new Tag()
      .name("fitxes")
      .description("Servicio para la obtención de información de las fichas."));
    swagger.tag(new Tag()
      .name("normativas")
      .description("Servicio para la obtención de información de las normativas."));
    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}