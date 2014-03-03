package org.ibit.rol.sac.persistence.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.ibit.rol.sac.model.Traduccion;

public class Cadenas {

	   private static String[][] canvis = new String[][] { {" I "  , " i "},
           {" O "  , " o "},
           {" De " , " de "},
           {" Del ", " del "},
           {" D'"  , " d'"},
           {" A "  , " a "},
           {" En " , " en "},
           {" El " , " el "},
           {" L'"  , " l'"},
           {" La " , " la "},
           {" Als ", " als "},
           {" Els ", " els "},
           {" Les ", " les "},
           {" Al " , " al "},
           {" Amb ", " amb "},
           {" Que ", " que "},
           {" Per ", " per "},
           {"l.L"  , "l.l"},
           {"l.L"  , "l.l"}
            };
	
	  /** 
	   * Este m�todo divide el string en palabras y las pasa a un arraylist
	   */ 
	  public static ArrayList getArrayListFromString(String cadena) {
	  
	    ArrayList lista=new ArrayList();
	    if (cadena.length()>0) {
	        String txseparador = ";";
	        String[] listastringcadenas = cadena.split(txseparador);
	        for (int i=0;i<listastringcadenas.length;i++)
	          if (listastringcadenas[i].length()>0) lista.add(listastringcadenas[i]);
	    }
	    
	    return lista;
	  }
	  
	  /** 
	   * Este m�todo divide el string en palabras y las pasa a un hashtable
	   */ 
	  public static Hashtable getHashtableFromString(String cadena) {
	    Hashtable listahash=new Hashtable();

	    if (cadena.length()>0) {
	        String txseparador = ";";
	        String[] listastringcadenas = cadena.split(txseparador);
	        for (int i=0;i<listastringcadenas.length;i++)
	          if (listastringcadenas[i].length()>0) listahash.put(listastringcadenas[i],listastringcadenas[i]);
	    }    
	    return listahash;
	  }  	  
	  
	  /**
	   * M�todo que pone la inicial de cada palabra en mayusculas.
	   * @param texte
	   */
	  public static void initAllTab (StringBuffer texte) {
		      
		      final int LONG = texte.length();
		      boolean primer = true;
		      char c;
		      for (int i=0; i<LONG; i++) {
		         c = texte.charAt(i);
		         if ( c == ' ' || c == '.' || c == '\'' || c == '"' ) {
		            primer = true;
		         } else {
		            if (primer) {
		               texte.setCharAt(i,Character.toUpperCase(c));
		               primer = false;
		            } else {
		               texte.setCharAt(i,Character.toLowerCase(c));
		            }
		         }
		      }
		        
		}	  

		protected static void replace(String original, StringBuffer texte,
			String patro, String canvi) {

			final int LONG_CANVI = canvi.length();
	
			if (patro.length() != LONG_CANVI) {
				throw new RuntimeException(
						"els patrons de canvis a Format han de tenir la mateixa llargada!");
			}
	
			// per cada ocurrencia del canvi feim el canvi en el texte original
			for (int index = original.indexOf(patro); index != -1; index = original
					.indexOf(patro, index + LONG_CANVI)) {
				texte.replace(index, index + LONG_CANVI, canvi);
			}

		}
		
		
		public static String initTab(String texte) {

			// evitam nullPointerExceptions
			if (texte == null)
				return null;
	
			StringBuffer buf = new StringBuffer(texte);
			initAllTab(buf);
			String original = buf.toString();
			for (int i = 0; i < canvis.length; i++) {
				replace(original, buf, canvis[i][0], canvis[i][1]);
			}
	
			return buf.toString();
		}    
		
		/**
		 * M�todo convierte el stack trace de una excepcion en un string
		 * @param mensajes, vector de "StactkTraceElement"
		 * @param numelementos, n�mero de elementos del vector de mensajes que se pasaran al string
		 */
		public static String statcktrace2String(StackTraceElement[] mensajes, int numelementos) {
		    StringBuffer stlog = new StringBuffer("");
		    int mensmostrados = (mensajes.length<numelementos)?mensajes.length:numelementos;
		    for (int x=0;x<mensmostrados;x++) {
		    	stlog.append(mensajes[x].getClassName());
		    	stlog.append(" (");
		    	stlog.append(mensajes[x].getMethodName());
		    	stlog.append(" >> linea:");
		    	stlog.append(mensajes[x].getLineNumber());
		    	stlog.append(") \n");
		    }
		    if (mensajes.length>=numelementos) {
		    	stlog.append(" (mas) ...\n ");
		    }
		    return stlog.toString();
		}
		
	    /**
	     * Comprueba si la cadena pasada por par�metro forma parte de alguno
	     * de los campos multi-idioma de la clase de traducci�n dada.
	     * 
	     * @param clazz Clase que implemente Traduccion
	     * @param cadena Cadena a buscar
	     * @return Booleano Devuelve true si la cadena se ha encontrado en alguno de los campos.
	     */
	    public static boolean isCadenaEnTraduccion(Traduccion o, String cadena) {
	    	
	    	if (o == null) return false;
	    	
	    	List<Method> listaMetodos = Arrays.asList(o.getClass().getDeclaredMethods()); 
	    	
			try {
	    	
		    	for (Method m : listaMetodos ) {
		
		    		if (cadena != null && cadena.length() > 0 && m.getName().startsWith("get")) {
		    			String upperCadena = cadena.toUpperCase();
		    			String valor = ((String) m.invoke(o, (Object[]) null));
		    			
		    			if (valor != null) {
		    				
			    			if ( upperCadena.startsWith("\"") && 
			    				 upperCadena.endsWith("\"") && 
			    				 upperCadena.equals(valor.toUpperCase()) ) {
			    				return true;
			    			} else if ( valor.toUpperCase().contains(upperCadena) ) return true;
			    			
		    			}
		    		}
		    	}
		    	
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	    	
	    	return false;
	    	
	    }
		
}
