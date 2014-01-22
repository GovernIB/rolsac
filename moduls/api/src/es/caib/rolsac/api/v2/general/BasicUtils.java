package es.caib.rolsac.api.v2.general;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Traduccion;

import es.caib.rolsac.api.v2.general.co.BasicByIdCriteria;
import es.caib.rolsac.api.v2.general.co.BasicByIniciCriteria;
import es.caib.rolsac.api.v2.general.co.BasicByOrdenacioCriteria;
import es.caib.rolsac.api.v2.general.co.BasicByTamanyCriteria;
import es.caib.rolsac.api.v2.general.co.ByDateCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;

public class BasicUtils {

    private static Log log = LogFactory.getLog(BasicUtils.class);
    
    private static String[] GETTERS_INVALIDS = {"getSerializer","getDeserializer", "getTypeDesc"};
    private static String[] SETTERS_INVALIDS = {"setSerializer", "setDeserializer", "setTypeDesc"};    


    public static List<CriteriaObject> parseCriterias(Class<?> criteriaClass, String entityAlias,
            BasicCriteria basicCriteria) throws CriteriaObjectParseException {
        return parseCriterias(criteriaClass, entityAlias, "", basicCriteria);
    }

    /**
     * Use case:
     * if (procedimentCriteria.getTaxa() != null) { 
     *     ProcedimentByTaxaCriteria criteria = new ProcedimentByTaxaCriteria(entityAlias); // i18nALias si es traducible
     *     criteria.parseCriteria(booleanToString(procedimentCriteria.getTaxa()));
     *     criteriaObjects.add(criteria); 
     * }
     */
    public static List<CriteriaObject> parseCriterias(Class<?> criteriaClass, String entityAlias, String i18nAlias,
            BasicCriteria basicCriteria) throws CriteriaObjectParseException {
        List<CriteriaObject> criteriaObjects = new ArrayList<CriteriaObject>();

        parseBasicCriteria(entityAlias, i18nAlias, criteriaObjects, basicCriteria);

        Method[] methods = criteriaClass.getDeclaredMethods();
        Method m = null;
        for (int i = 0; i < methods.length; i++) {
            try {
                m = methods[i];
                if ( (m.getName().startsWith("get") || m.getName().startsWith("is")) &&                		                		
                		!Arrays.asList(GETTERS_INVALIDS).contains( m.getName() ) ) {
                	
                    Object value = m.invoke(basicCriteria);
                    if (value != null) {
                        CriteriaObject co = getParsedCriteria(m, value, criteriaClass, entityAlias, i18nAlias, basicCriteria);
                        if (co != null) {
                            criteriaObjects.add(co);
                        }
                    }
                }
            } catch (Exception e) {
                throw new CriteriaObjectParseException("Error parseando " + m.getName() + " de la clase "
                        + criteriaClass + ".", e);
            }
        }

        return criteriaObjects;
    }

    private static CriteriaObject getParsedCriteria(Method getter, Object value, Class<?> criteriaClass,
            String entityAlias, String i18nAlias, BasicCriteria basicCriteria) throws ClassNotFoundException,
            IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException,
            SecurityException, NoSuchMethodException {

        // Obtener el campo y el alias a usar (traducible o no).
        String alias;
        String fieldName;
        if (getter.getName().startsWith("getT_")) {
            fieldName = getter.getName().substring(5);
            alias = i18nAlias;
        } else if (getter.getName().startsWith("is")) {
            fieldName = getter.getName().substring(2);
            alias = entityAlias;
        } else {
            fieldName = getter.getName().substring(3);
            alias = entityAlias;
        }

        /*
         * Obtener la clase CriteriaObject especifica. Nota: Los criterias objects se encuentran en packages del tipo
         * "es.caib.rolsac.api.v2.fetVital.co.FetVitalByCodigoEstandarCriteria".
         */
        StringBuilder concreteCriteriaClassName = new StringBuilder();
        String[] packageWords = criteriaClass.getName().split("\\.");
        
        // split by unicode capital letter.
        String[] classWords = packageWords[packageWords.length - 1].split("(?=\\p{Lu})");
        
        for (int i = 0; i < (packageWords.length - 1); i++) {
            concreteCriteriaClassName.append(packageWords[i]).append(".");
        }
        concreteCriteriaClassName.append("co.");
        for (int i = 1; i < classWords.length - 1; i++) {
            concreteCriteriaClassName.append(classWords[i]);
        }
        concreteCriteriaClassName.append("By").append(StringUtils.capitalize(fieldName)).append("Criteria");

        // Instanciar nuevo objeto CriteriaObject de la clase especifica.
        Class<?> concreteCriteriaClass = Class.forName(concreteCriteriaClassName.toString());
        Constructor<?> ct = concreteCriteriaClass.getConstructors()[0];
        CriteriaObject concreteCriteria = (CriteriaObject) ct.newInstance(alias);

        if (concreteCriteria != null) {
            // Obtener el metodo de parseo para el campo
            Method parser = concreteCriteriaClass.getMethod("parseCriteria", String.class);

            // Ejecutar el parseo con el valor pasado a String
            String stringValue;
            if (Boolean.class.equals(value.getClass())) {
                stringValue = booleanToString((Boolean) value);
            } else if (Date.class.equals(value.getClass())) {
                stringValue = ByDateCriteria.DATE_CRITERIA_FORMATTER.format(value);
            } else if (Calendar.class.equals(value.getClass()) || GregorianCalendar.class.equals(value.getClass()) ) {
            	stringValue = ByDateCriteria.DATE_CRITERIA_FORMATTER.format(((Calendar) value).getTime());
            } else {
                stringValue = String.valueOf(value);
            }
            
            if (StringUtils.isNotBlank(stringValue)) {
                parser.invoke(concreteCriteria, stringValue);
            } else {
                concreteCriteria = null;
            }
        }

        return concreteCriteria;
    }

    private static void parseBasicCriteria(String entityAlias, String i18nAlias, List<CriteriaObject> criteriaObjects,
            BasicCriteria basicCriteria) throws CriteriaObjectParseException {
        
        if (StringUtils.isNotBlank(basicCriteria.getId())) {
            BasicByIdCriteria criteria = new BasicByIdCriteria(entityAlias);
            criteria.parseCriteria(basicCriteria.getId());
            criteriaObjects.add(criteria);
        }
        basicCriteria.setOrdenacio(controlOrdenar(basicCriteria));
        if (StringUtils.isNotBlank(basicCriteria.getOrdenacio())) {
            BasicByOrdenacioCriteria criteria = new BasicByOrdenacioCriteria(entityAlias, i18nAlias);
            criteria.parseCriteria(basicCriteria.getOrdenacio());
            criteriaObjects.add(criteria);
        }
        if (StringUtils.isNotBlank(basicCriteria.getInici())) {
            BasicByIniciCriteria criteria = new BasicByIniciCriteria();
            criteria.parseCriteria(basicCriteria.getInici());
            criteriaObjects.add(criteria);
        }
        if (StringUtils.isNotBlank(basicCriteria.getTamany())) {
            BasicByTamanyCriteria criteria = new BasicByTamanyCriteria();
            criteria.parseCriteria(basicCriteria.getTamany());
            criteriaObjects.add(criteria);
        }
        if (StringUtils.isBlank(basicCriteria.getIdioma())) {
            basicCriteria.setIdioma(getDefaultLanguage());
        }
    }

    public static Object entityToDTO(Class<?> dtoClass, Object entity) {
        return entityToDTO(dtoClass, entity, null);
    }

    public static Object entityToDTO(Class<?> dtoClass, Object entity, String lang) {
        if (entity == null) {
            return null;
        }

        Constructor<?> dtoConstructor = null;
         
		try {
	        // Para asegurarnos de que es devuelto el constructor por defecto, ya que AXIS genera objetos 
	        // DTO con un constructor mas
			dtoConstructor = dtoClass.getConstructor( (Class[]) null  );
		} catch (SecurityException e1) {
		    log.error(e1);
		} catch (NoSuchMethodException e1) {
		    log.error(e1);
		}                    	
        
        Object dto = null;
                
        try {
            dto = dtoConstructor.newInstance();
            Method[] dtoMethods = dtoClass.getMethods();
            Method method;
                        
            for (int i = 0; i < dtoMethods.length; i++) {
                method = dtoMethods[i];
                if (method.getName().startsWith("set") &&
                		!Arrays.asList(SETTERS_INVALIDS).contains(method.getName()) ) {
                    copyProperty(entity, dto, method, lang);
                }
            }
        } catch (Exception e) {
            log.error("Error al crear " + dtoClass + " a partir de " + entity.getClass() + ".", e);
        }

        return dto;
    }

    private static void copyProperty(Object entity, Object dto, Method setter, String lang) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            NoSuchFieldException {

        String property = StringUtils.uncapitalize(setter.getName().substring(3));
        Object value = null;

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

        // Llamar al setter
        if (value != null) {
            // Parsear el valor segun el tipo de la propiedad del dto.
            Class<?> propertyClass = dto.getClass().getDeclaredField(property).getType();
            Class<?>[] valueClasses = new Class[1];
            if (propertyClass.equals(Boolean.class) || propertyClass.equals(boolean.class)) {
                // En las entidades hay booleanos de tipo String y de tipo Boolean.
                if (!(value.getClass().equals(Boolean.class) || value.getClass().equals(boolean.class))) {
                    value = stringToBoolean((String) value);
                }
                valueClasses[0] = boolean.class;
            } else if (Date.class.equals(propertyClass)) {
                value = (Date) value; // Para evitar problemas con java.sql.Timestamp.
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

    private static String controlOrdenar(BasicCriteria criteria) {

        String ordenaciones = new String();
        try {
            Method idGetter = criteria.getClass().getMethod("getOrdenar");
            Object[] objetos = (Object[]) idGetter.invoke(criteria);
            for (Object objeto : objetos) {
                ordenaciones = ordenaciones.concat(objeto.toString());
                ordenaciones = ordenaciones.concat(",");
            }
            if (ordenaciones != null) {
                ordenaciones = ordenaciones.replaceAll("_", " ");
                ordenaciones = ordenaciones.substring(0, ordenaciones.length()-1);
                ordenaciones = ordenaciones.toString();
                criteria.setOrdenacio(ordenaciones);
            }
            Field field = criteria.getClass().getDeclaredField("ordenar");
            field.setAccessible(true);
            field.set(criteria, null);            

        } catch (SecurityException e) {
            e.printStackTrace();
            ordenaciones = criteria.getOrdenacio();
        } catch (NoSuchMethodException e) {
            log.error(criteria.getClass() + " aún no tiene implementado Ordenar.");
            ordenaciones = criteria.getOrdenacio();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            ordenaciones = criteria.getOrdenacio();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            ordenaciones = criteria.getOrdenacio();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            ordenaciones = criteria.getOrdenacio();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            ordenaciones = criteria.getOrdenacio();
        } finally {}

        return ordenaciones;
    }

}
