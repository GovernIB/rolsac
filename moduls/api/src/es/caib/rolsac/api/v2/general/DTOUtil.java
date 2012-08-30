package es.caib.rolsac.api.v2.general;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DTOUtil {
	
	/**
	 * Retorna una nova instancia del DTO donat
	 * creada a partir d'un Object[] (retornat per WS)
	 * 
	 * @param o Objecte amb els atributs de la classe
	 * @param c Classe a la qual pertany l'objecte
	 * @return Object 
	 */
	public static Object object2DTO( Object o, Class<?> c ) {

		Constructor<?> constructorDTO = null;
		Object resultDTO = null;
		
		try {						
			Class<?> dtoClass = Class.forName(c.getName());			
			Constructor<?>[] llistaConstructors = dtoClass.getDeclaredConstructors();
			
			for (Constructor<?> constructor : llistaConstructors) {

				//Nomes hauria d'haver un constructor amb parametres al DTO, en trobar-lo
				//ja no es necessari cercar mes.				
				if (constructor.getParameterTypes().length > 0 ) {
					constructorDTO = constructor;
					break;
				}
			}			
			
			resultDTO = constructorDTO.newInstance( (Object[]) o );
			
		} catch (ClassNotFoundException cne) {
		} catch (InvocationTargetException ite) {			
		} catch (IllegalAccessException iae) {			
		} catch (InstantiationException ie) {			
		}
		
		return resultDTO;
	}
}
