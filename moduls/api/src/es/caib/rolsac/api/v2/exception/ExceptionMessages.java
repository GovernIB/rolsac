package es.caib.rolsac.api.v2.exception;

public class ExceptionMessages {

    // Locators
    public static final String JNDI_NAMING = "Error de nombre JNDI.";
    public static final String JNDI_REMOTE = "Error remoto JNDI.";
    public static final String EJB_CREATE = "Error creando EJB remoto.";
    public static final String EJB_CLASS_CAST = "Error de narrow en EJB.";

    // Delegates
    public static final String REMOTE_SERVICE = "Error localizando el servicio remoto.";
    public static final String REMOTE_CALL = "Error en la llamada remota.";

    // Adapter
    public static final String ADAPTER_CONSTRUCTOR = "Error creando Adapter.";

    // QueryServices
    public static final String OBJECT_GETTER = "Error obteniendo ";
    public static final String LIST_GETTER = OBJECT_GETTER + "listado de ";

}
