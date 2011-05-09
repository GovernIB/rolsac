package es.caib.traspasa.util;

import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 
 * Clase estatica que lee del web.xml los mapeos de los tipos de normativa.
 * 
 * Ejemplo: para leer una propiedad 
 *        Configuracion.getPropiedad("norma_1");
 *        
 * Autor: Vicente Roca
 * Copyright INDRA 2006
 */

public class Configuracion extends HttpServlet {
  
	
    public void init() throws ServletException {
        super.init();
    
    }

    public void destroy() {
        super.destroy();
    }	
	
  public static Hashtable propiedades=new Hashtable();


  static {

    try {
    
        javax.naming.Context ctx = new javax.naming.InitialContext();
        javax.naming.Context env = (javax.naming.Context) ctx.lookup("java:comp/env");
    
        propiedades.put("norma_1",(String) env.lookup("norma_1"));
        propiedades.put("norma_2",(String) env.lookup("norma_2"));
        propiedades.put("norma_3",(String) env.lookup("norma_3"));
        propiedades.put("norma_4",(String) env.lookup("norma_4"));
        propiedades.put("norma_5",(String) env.lookup("norma_5"));
        propiedades.put("norma_6",(String) env.lookup("norma_6"));
        propiedades.put("norma_7",(String) env.lookup("norma_7"));

        //System.out.println("WEBCAIB-TRASPASA: leido correctamente del web.xmlel mapeo de tipos de normativa");

    } catch (Exception e) {
       System.out.println("ERROR:WEBCAIB-TRASPASA:configuracion : " + e.getMessage());
    }
  }

  public Configuracion() { }

  public static String getPropiedad(String nombre) throws Exception {

    String valor = (String) propiedades.get(nombre);

    if (valor == null)
      throw new Exception("ERROR:WEBCAIB-TRASPASA: lectura erronea en el parametro: " + nombre);

    return valor;
  }
}