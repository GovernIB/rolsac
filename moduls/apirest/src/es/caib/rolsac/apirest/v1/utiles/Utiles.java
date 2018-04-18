package es.caib.rolsac.apirest.v1.utiles;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traduccion;

/**
 * Clase de constantes. 
 * 
 * @author slromero
 *
 */
public class Utiles {

	private static Log log = LogFactory.getLog(Utiles.class);
	//private static String[] GETTERS_INVALIDS = {"getSerializer","getDeserializer", "getTypeDesc"};
    //private static String[] SETTERS_INVALIDS = {"setSerializer", "setDeserializer", "setTypeDesc"};    

	

    /**
     * Método encargado de realizar el casting de listas no tipadas a listas
     * tipadas
     * 
     * @param <T>
     * @param clazz Clase del tipo de objeto contenido en la lista
     * @param c Colección a ser tipada
     * @return Lista tipada
     */
    public static <T>List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>();
        if (c != null) {     
            r = new ArrayList<T>(c.size());            
            for (Object o : c) {
            	r.add(clazz.cast(o));
            }
        }
        return r;
    } 

    /**
     *  Rellena la propiedad definida en el setter del dto con el valor de la propiedad del mismo nombre en entity. 
     *  con excepción de idioma, que normalmente no está en el entity y se usa el que llega como parametro
     *  y el codigo que se usa la propiedad id del entity
     * @param entity
     * @param dto
     * @param setter
     * @param lang
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    public static void copyProperty(Object entity, Object dto, Method setter, String lang) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            NoSuchFieldException {

        String property = StringUtils.uncapitalize(setter.getName().substring(3));
        Object value = null;
        
        // si estamos intentando obtener el idioma, como no está en el dto usamos el que nos llega
        // del request.(en algunos casos si que aparece pero deberia venir a nulo, si no es asi
        // es un caso especial, habria que revisar el código fuente de esta funcion) 
        if("idioma".equals(property)) {
        	value = lang;
        }else {
     
	        /*
	         * Primero se busca la propiedad en la traduccion. Nota 1: Las traducciones no tienen booleanos (no hay getters
	         * que empiecen por isXxx). Nota 2: Se busca primero en la traduccion porque en Documento y DocumentTramit
	         * existe una propiedad con el mismo nombre en la clase y en la traduccion. La propiedad de la clase ya no se
	         * usa, por lo que tiene prioridad el getter de la traduccion.
	         */
	        Method i18nGetter = null;
	        try {
	            Traduccion trad;
	            if (StringUtils.isBlank(lang)) {
	                i18nGetter = entity.getClass().getMethod("getTraduccion");
	                trad = (Traduccion) i18nGetter.invoke(entity);
	            } else {
	                i18nGetter = entity.getClass().getMethod("getTraduccion", lang.getClass());
	                trad = (Traduccion) i18nGetter.invoke(entity, lang);
	            }
	            if (trad != null) {
	                i18nGetter = trad.getClass().getMethod("get" + StringUtils.capitalize(property));
	                value = i18nGetter.invoke(trad);
	            }
	            
	            if (value == null) {
	            	//No hemos encontrado valor en la traduccion principal, buscamos en las traducciones alternativas
	            	String[] langAlternates = getLangAlternates(lang);
	            	if (langAlternates != null) {
	            		for ( String altLang : langAlternates) {
	            			altLang = altLang.trim();
	            			i18nGetter = entity.getClass().getMethod("getTraduccion", altLang.getClass());
	                        trad = (Traduccion) i18nGetter.invoke(entity, altLang);
	            			
	                        if (trad != null) {
	                            i18nGetter = trad.getClass().getMethod("get" + StringUtils.capitalize(property));
	                            value = i18nGetter.invoke(trad);
	                        }
	                        if (value != null) {
	                        	break; //Encontrado valor
	                        }
	            			
	            		}
	            	}
	            	
	            }
	                
	
	        } catch (NoSuchMethodException e) {
	        }
	
	        // Si no se ha encontrado la propiedad en la traduccion, buscamos en la entidad.
	        if (value == null) {
	            // Obtener el valor de la propiedad a traves del getter de entity.
	            Method entityGetter = null;
	            try {
	                entityGetter = entity.getClass().getMethod("get" + StringUtils.capitalize(property));
	                value = entityGetter.invoke(entity);
	            } catch (NoSuchMethodException eGet) {
	                // Si la propiedad es booleana el getter sera isXxx() en vez de getXxx().
	                try {
	                    entityGetter = entity.getClass().getMethod("is" + StringUtils.capitalize(property));
	                    value = entityGetter.invoke(entity);
	                } catch (NoSuchMethodException eIs) {
	                }
	            }
	        }
	
	        // Si value tiene un metodo getId() es que es una FK y hay que recuperar su id.
	        if (value != null) {
	            try {
	                Method idGetter = value.getClass().getMethod("getId");
	                value = idGetter.invoke(value);
	            } catch (NoSuchMethodException e) {
	            }
	        }      
        }

        // Llamar al setter
        if (value != null) {
            // Parsear el valor segun el tipo de la propiedad del dto.
        	Class<?> propertyClass = null;
        	try {
        		propertyClass = dto.getClass().getDeclaredField(property).getType();
			} catch (Exception e) {
				if(property.equals("id")) {
					//Si es el id no existe propiedad (se ha cambiado el nombre a código)
					// intentamos obtener el tipo del "código"
	            	  propertyClass = dto.getClass().getDeclaredField("codigo").getType();
	              }
			}
        	  
            
            Class<?>[] valueClasses = new Class[1];
            if (propertyClass.equals(Boolean.class) || propertyClass.equals(boolean.class)) {
                // En las entidades hay booleanos de tipo String y de tipo Boolean.
                if (!(value.getClass().equals(Boolean.class) || value.getClass().equals(boolean.class))) {
                    value = stringToBoolean((String) value);
                }
                valueClasses[0] = boolean.class;
            } else if (Date.class.equals(propertyClass)) {
                value = value; // Para evitar problemas con java.sql.Timestamp.
                valueClasses[0] = Date.class;
            } else if (Calendar.class.equals(propertyClass) || GregorianCalendar.class.equals(value.getClass())) {
            	// Para evitar problemas con los DTO generados por WSDL
            	Long tmpValue = ((Timestamp)value).getTime();
            	value = Calendar.getInstance(); 
            	((Calendar) value).setTimeInMillis( tmpValue );            	
            	valueClasses[0] = Calendar.class;  
            } else {
                valueClasses[0] = value.getClass();
            }

            /*
             * Ejecutar el setter de la propiedad del dto con el valor obtenido de la propiedad de entity. Se usa el
             * metodo menos restrictivo de BeanUtils en vez del propio de Java debido a un bug relacionado con tipos
             * primitivos: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6176992.
             */
            // Method dtoSetter = dto.getClass().getMethod("set" + StringUtils.capitalize(property), valueClasses[0]);
            Method dtoSetter = MethodUtils.getMatchingAccessibleMethod(dto.getClass(),
                    "set" + StringUtils.capitalize(property), valueClasses);
            dtoSetter.invoke(dto, value);
        }
    }

    
    private static String[] getLangAlternates(String lang) {

    	if (StringUtils.isBlank(lang)) lang = getDefaultLanguage();
    	String alternates = System.getProperty("es.caib.rolsac.api.v2.alternativesIdioma_" + lang);
    	if (alternates == null) {
            log.error("No hay definidas alternativas de idioma para " + lang + ". Usando ca,es,en");
            return new String[]{"ca", "es", "en"};
    	}
		return alternates.split(",");
	}

	public static String booleanToString(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? "1" : "0";
    }

    public static boolean stringToBoolean(String value) {
        return StringUtils.isBlank(value) || value.equals("0") ? false : true;
    } 
    
    public static String getDefaultLanguage() {
        String defaultLanguage = System.getProperty("es.caib.rolsac.api.v2.idiomaPerDefecte");
        if (StringUtils.isBlank(defaultLanguage)) {
            log.error("No hay definido un idioma por defecto en el sistema. Se va a usar 'ca' como idioma.");
            defaultLanguage = "ca";
        }
        return defaultLanguage;
    }
    
    
}






