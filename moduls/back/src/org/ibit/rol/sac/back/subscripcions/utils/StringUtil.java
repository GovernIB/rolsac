package org.ibit.rol.sac.back.subscripcions.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * Utilidades con cadenas
 *
 */
public class StringUtil {

	public final static String FORMATO_FECHA = "dd/MM/yyyy";
	public final static String FORMATO_TIMESTAMP = "dd/MM/yyyy HH:mm:ss";
	public final static String FORMATO_REGISTRO = "yyyyMMddHHmmss";
	public final static String LANG_CA = "ca";
	public final static String REGISTRO_LANG_CA = "2";
	public final static String REGISTRO_LANG_ES = "1";
	
	
	// --- FUNCIONES PARA OBTENER EL IDENTIFICADOR DE IDIOMA PARA EL SISTEMA DE REGISTRO
	
    public static String getIdiomaRegistro( String codigoAlfanumericoIdioma )
    {
    	return LANG_CA.equals( codigoAlfanumericoIdioma ) ? REGISTRO_LANG_CA : REGISTRO_LANG_ES;
    }
	
	// --- FUNCIONES PARA OBTENER MODELO/VERSION PARA IDENTIFICADOR CON FORMATO "MODELO-VERSION"
	
	/**
	 * Devuelve modelo de un identificador con formato: modelo-version
	 * @param identificador
	 * @return modelo
	 */
	public static String getModelo(String identificador){
		int pos = identificador.lastIndexOf('-');
		return identificador.substring(0,pos);
	}
	
	/**
	 * Devuelve version de un identificador con formato: modelo-version
	 * @param identificador
	 * @return version
	 */
	public static  int getVersion(String identificador){
		int pos = identificador.lastIndexOf('-');
		return Integer.parseInt(identificador.substring(pos + 1));
	}
	
	
	//	--- FUNCIONES PARA MANEJO DE FECHA
	
	/**
	 * Convierte fecha a un string con formato FORMATO_FECHA
	 * @param datFecha
	 * @return
	 */
	public static String fechaACadena( Date datFecha )
    {
        return fechaACadena( datFecha, FORMATO_FECHA );
    }
	
	/**
	 * Convierte fecha a un string con formato FORMATO_TIMESTAMP
	 * 
	 * @param datFecha
	 * @return
	 */
	public static String timestampACadena( Date datFecha )
    {
        return fechaACadena( datFecha, FORMATO_TIMESTAMP );
    }
	
	/**
	 * Formatea fecha
	 * @param fecha
	 * @param strFormatoFecha
	 * @return
	 */
	public static String fechaACadena( Date fecha, String strFormatoFecha )
    {
        if ( fecha == null )
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat( strFormatoFecha );               
        return sdf.format( fecha );
    }
	
	/**
	 * Deformatea fecha (la fecha debe ir en formato FORMATO_FECHA)
	 * @param fecha
	 * @return
	 */
	public static Date cadenaAFecha( String strFecha )
    {
        return cadenaAFecha( strFecha, FORMATO_FECHA );
    }
	
	/**
	 * Deformatea fecha (la fecha debe ir en formato FORMATO_TIMESTAMP)
	 * 
	 * @param strFecha
	 * @return
	 */
	public static Date cadenaATimestamp( String strFecha )
    {
        return cadenaAFecha( strFecha, FORMATO_TIMESTAMP );
    }
	
	/**
	 * Deformatea fecha (indicando el formato de la fecha)
	 * @param fecha
	 * @param strFormatoFecha
	 * @return
	 */
	public static Date cadenaAFecha( String fecha, String strFormatoFecha )
    {
		try{
	        if ( fecha == null )
	            return null;
	        SimpleDateFormat sdf = new SimpleDateFormat( strFormatoFecha );               
	        return sdf.parse( fecha );
		}catch(Exception ex){
			return null;
		}
	        
    }
	
	 public static String sqlTimestampACadena( Timestamp timestamp, String strFormatoFecha )
	    {
	    	return ( timestamp != null ) ? fechaACadena( new Date( timestamp.getTime() ), strFormatoFecha  ) : null;
	    }
	    
	    public static String sqlTimestampACadena( Timestamp timestamp )
	    {
	    	return sqlTimestampACadena( timestamp, FORMATO_TIMESTAMP );
	    }
	    
	    public static Timestamp cadenaASqlTimestamp( String strFecha, String strFormatoFecha )
	    {
	    	Date date = cadenaAFecha( strFecha, strFormatoFecha );
	    	
	    	return date != null ? new Timestamp( date.getTime() ) : null; 
	    }
	    
	    
	    public static Timestamp cadenaASqlTimestamp( String strFecha )
	    {
	    	return cadenaASqlTimestamp( strFecha, FORMATO_TIMESTAMP );
	    }
	    
	    public static String sqlDateACadena( java.sql.Date timestamp, String strFormatoFecha )
	    {
	    	return ( timestamp != null ) ? fechaACadena( new Date( timestamp.getTime() ), strFormatoFecha  ) : null;
	    }
	    
	    public static String sqlDateACadena( java.sql.Date timestamp )
	    {
	    	return sqlDateACadena( timestamp, FORMATO_TIMESTAMP );
	    }
	    
	    public static java.sql.Date cadenaASqlDate( String strFecha, String strFormatoFecha )
	    {
	    	Date date = cadenaAFecha( strFecha, strFormatoFecha );
	    	
	    	return date != null ? new java.sql.Date( date.getTime() ) : null; 
	    }
	    
	    
	    public static java.sql.Date cadenaASqlDate( String strFecha )
	    {
	    	return cadenaASqlDate( strFecha, FORMATO_TIMESTAMP );
	    }
	
	// -- FUNCIONES DE MANEJO DE CADENAS
	/**
	 * Funci�n de relleno
	 */
	public static String lpad(String as_texto, int ai_longMinima, char ac_relleno)
	  {
	  	if (as_texto == null) return as_texto;
	  	if (as_texto.length() < ai_longMinima)
	  	for (int i=0; i< ai_longMinima - as_texto.length();i++)
	  	{
	  		as_texto = ac_relleno +as_texto;
	  	}
	  	return as_texto;
	  }
	 
	 /**
	   * M�todo usada para reemplazar todas las ocurrencias de determinada cadena de texto
	   * por otra cadena de texto
	   * @param String Texto origen
	   * @param String Fragmento de texto a reemplazar
	   * @param String  Fragmento de texto con el que se reemplaza
	   * @return String Cadena de texto con el reemplazo de cadenas completado
	   **/
	  public static String replace(String s, String one, String another)
	  throws Exception 
	  {
	  // En el String 's' sustituye 'one' por 'another'
	    if (s == null) 
	    {
	    	if (one == null && another != null)
	    	{
	    		return another; 
	    	}
	    	return null;
	    }
	     
	     
	   	
		if (s.length() == 0) 
		{
			if (one != null && one.length() == 0)
			{
				return another; 
			}
			return "";
		} 
		
		if (one == null || one.length()==0)
		{
			return s;
		}

		
		String res = "";
	    int i = s.indexOf(one,0);
	    int lastpos = 0;
	    while (i != -1) {
	      res += s.substring(lastpos,i) + another;
	      lastpos = i + one.length();
	      i = s.indexOf(one,lastpos);
	    }
	    res += s.substring(lastpos);  // the rest
	    return res;
	  }
	  
	  /**
		 * Funcion que identifica si la cadena es nula o es una cadena vacia
		 * @param string
		 * @return
		 */
		public static boolean esCadenaVacia( String string )
	    {
	        return string == null ? true : "".equals( string.trim() );
	    }
		
		/**
	     * M�todo que devuelve la cadena que se le pasa como primer parametro
	     * o la cadena por defecto que se le pasa como segundo parametro si
	     * la primera es nula o esta vac�a.
	     * @param strCadena Cadena a chequear
	     * @param strPorDefecto Cadena a devolver si la primera cadena es nula.
	     * @return String
	     */
	    public static String obtenerCadenaPorDefecto( String strCadena, String strPorDefecto )
	    {
	      return !esCadenaVacia ( strCadena ) ? strCadena : strPorDefecto;
	    }
	    
	    /**
		 * Descompone caracteres de un String en un array de String
		 * 
		 * @param str
		 * @return
		 */
	    public static String[] splitString( String str )
	    {
	    	int iSize = str.length();
	    	String [] arrResult = new String[ iSize ];
	    	for ( int i = 0; i < iSize; i++ )
	    	{
	    		arrResult[ i ] = str.substring( i, i + 1 );  
	    	}
	    	return arrResult;
	    }
	    
	    /**
	     * Concatena un array de Strings en un �nico String
	     * 
	     * @param arrStr
	     * @return
	     */
	    public static String concatArrString( String[] arrStr )
	    {
	    	StringBuffer sb = new StringBuffer();
	    	for ( int i = 0; i < arrStr.length; i++ )
	    	{
	    		sb.append( arrStr[i] );
	    	}
	    	return sb.toString();
	    }
	    
	    /**
	     * Reemplaza los parametros especificados en la cadena original marcados por <x> (x: numero de parametro)
	     * por el consiguiente parametro en la lista
	     * 
	     * @param cadena
	     * @param lstParametros
	     * @return
	     */
	    public static String expansion( String cadena, List lstParametros )
	    {
	    	for ( int i = 0; i < lstParametros.size(); i++ )
	    	{
	    		cadena = cadena.replaceAll( "<" + i + ">", ( String ) lstParametros.get( i ) );
	    	}
	    	return cadena;
	    }
	  	 
	  
	  // --- FUNCION DE NORMALIZACION DE NOMBRE DE FICHEROS
		/**
		 * Normaliza nombre fichero (sin extensi�n) para que no de problemas eliminando car�cteres que suelen dar problemas
		 * 
		 * @param as_fic
		 * @return
		 * @throws Exception
		 */
		public static String normalizarNombreFichero(String as_fic)
				throws Exception {
			
					
			String ls_fic = as_fic;
			
			// Limitamos extensi�n a 100 caracteres
			if (ls_fic.length() > 100){
				ls_fic = ls_fic.substring(0,99);
			}
			
			ls_fic = StringUtil.replace(ls_fic, "\\", "");
			ls_fic = StringUtil.replace(ls_fic, "/", "");
			ls_fic = StringUtil.replace(ls_fic, ":", "");
			ls_fic = StringUtil.replace(ls_fic, "\"", "");
			ls_fic = StringUtil.replace(ls_fic, "<", "");
			ls_fic = StringUtil.replace(ls_fic, ">", "");
			ls_fic = StringUtil.replace(ls_fic, "*", "");
			ls_fic = StringUtil.replace(ls_fic, "|", "");
			ls_fic = StringUtil.replace(ls_fic, "?", "");
			return ls_fic;
		}		
		
		
		/**
		 * Quita los acentos,dieresis,etc de las vocales de la cadena
		 * 
		 * @param as_texto
		 * @return
		 * @throws Throwable
		 */
		public static String quitaAcentos(String as_texto)
			throws Throwable
		{

			String ls_textoNormalizado = as_texto;
			
			// May�sculas con acento grave
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","A");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","E");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","I");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","O");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","U");


			// May�sculas con acento agudo
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","A");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","E");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","I");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","O");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","U");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","Y");

			// Min�sculas con acento grave
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","a");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","e");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","i");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","u");

			
			// Min�sculas con acento agudo
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","a");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","e");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","i");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","u");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","y");

			// Min�sculas con di�resis
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","a");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","e");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","i");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","u");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","y");

			// May�sculas con di�resis
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","A");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","E");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","I");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","O");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","U");
			
			
			// May�sculas con acento ^
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","A");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","E");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","I");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","O");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","U");
			
			// Min�sculas con acento ^
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","a");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","e");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","i");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","u");
			
			// Mayusculas con tilde
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","A");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","O");
					
			// Minusculas con tilde
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","a");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			
			// Otros
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","A");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","a");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			ls_textoNormalizado = StringUtil.replace(ls_textoNormalizado, "�","o");
			
			return ls_textoNormalizado;	

		}
		
		/**
		 * Elimina los car�cteres que no son alfanumericos
		 * @param cadena
		 * @return
		 */
	    public static String escapeBadCharacters( String cadena )
	    {
	    	return cadena.replaceAll( "[^\\w]", "" );
	    }
	    
	    /**
	     * Reemplaza un caracter de retorno de carro (opcional) + un caracter de nueva linea por un <br/>
	     * @param value
	     * @return
	     */
	    public static String newLineToBr( String value )
		{
			return value.replaceAll( "\\r*\\n", "<br/>" );
		}
	  
	    public static String copyInputStream(InputStream in)
		  throws IOException
		  {
		    byte[] buffer = new byte[1024];
		    int len;

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    while((len = in.read(buffer)) >= 0)
		    {
		      baos.write(buffer, 0, len);
		    }
		    
		    String cadena = new String(baos.toByteArray());
		    baos.close();

		    in.close();
		    return cadena;
		  }


	    
	    	 
}
