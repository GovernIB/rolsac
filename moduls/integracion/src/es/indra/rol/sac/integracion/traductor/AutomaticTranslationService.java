package  es.indra.rol.sac.integracion.traductor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceStub;
import es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceStub.Param;
import es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceStub.ParamListType;
import es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceStub.Task;
import es.indra.rol.sac.integracion.ws.traductor.AutomaticTranslationServiceStub.TaskE;

/**
 * Clase que parametriza la petición de traducción y la envía al web-service Lucy
 * @author Indra
 *
 */
public class AutomaticTranslationService {
	
	protected static Log log = LogFactory.getLog(AutomaticTranslationService.class);
	
	protected static final String SERVICE_NAME = "TRANSLATE-TEXT",
								  ACTIVE="1", 
								  INACTIVE="0";

	protected static String _translationServerUrl = "http://scatwnt1.caib.es:8080/jaxws-AutomaticTranslationService/AutoTranslate",	//endpoint per defecte, es pot especificar en "es.caib.rolsac.integracion.traductor.servidor"
							_areaGV = "(GV)", 
							_translationDirection = "CATALAN-SPANISH", 
							_colorMarkups = INACTIVE,
							_markUnknowns = INACTIVE, 
							_markConstants = INACTIVE, 
							_markCompounds = INACTIVE, 
							_markAlternatives = INACTIVE;

	/**
	 * Método que guarda los parámetros de la petición de traducción y el texto a traducir, 
	 * envía la petición y devuelve el texto traducido
	 * 
	 * @param input	texto a traducir
	 * @return	String	texto traducido
	 * @throws Exception
	 */
	protected String translate(String input) throws Exception {
		
				
		try 
		{
		AutomaticTranslationServiceStub stub =
			new AutomaticTranslationServiceStub(_translationServerUrl);

		TaskE task13=(TaskE)getObject(TaskE.class);

		Task task = (Task)getObject(Task.class);
		task.setService(SERVICE_NAME);
		task.setVerbose(true);
		
		ParamListType params=(ParamListType)getObject(ParamListType.class);

		params.addParam(setTranslationDirection(_translationDirection));
		params.addParam(setSubjectArea(_areaGV));
		params.addParam(setColorMarkups(_colorMarkups));
		params.addParam(setMarkUnknowns(_markUnknowns));
		params.addParam(setMarkConstants(_markConstants));
		params.addParam(setMarkCompounds(_markCompounds));
		params.addParam(setMarkAlternatives(_markAlternatives));
		params.addParam(setInput(input));

		task.setInputParams(params);
		task13.setTask(task);

		task13 = stub.translate(task13);
		params = task13.getTask().getOutputParams();

		return manageColors(convertStreamToString(getOutput(params)));
	
		} catch (Exception e) {
			
			log.error(e.getMessage());
			throw new Exception(e); 
			
		}
	}

	/**
	 * Método que guarda el parámetro TRANSLATION_DIRECTION del traductor Lucy
	 * 
	 * @param translationDirection	dirección de traducción
	 * @return Param				devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setTranslationDirection(String translationDirection) throws Exception {
		
		String transDirName = "TRANSLATION_DIRECTION";
		String eMessage = "Error al carregar el la direcció de traducció";
		
	try {	
		Param param=(Param)getObject(Param.class); 
		param.setName(transDirName);
		param.setValue(translationDirection);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}	
	
	/**
	 * Método que guarda el parámetro SUBJECT_AREAS del traductor Lucy
	 * 
	 * @param subjectArea	area de traducción
	 * @return Param		devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setSubjectArea(String subjectArea) throws Exception {
	
		String subjectAreaName = "SUBJECT_AREAS";
		String eMessage = "Error al carregar l' àrea de traducció";
		
	try {	
		Param param=(Param)getObject(Param.class); 
		param.setName(subjectAreaName);
		param.setValue(subjectArea);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}
	
	/**
	 * Método que guarda el parámetro INPUT del traductor Lucy
	 * 
	 * @param input		input de traducción (texto a traducir)
	 * @return Param	devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setInput(String input) throws Exception {
		
		String inputName = "INPUT";
		String eMessage = "Error al carregar l' entrada a traduir";
		
	try {	
		Param param =(Param)getObject(Param.class); 
		param.setName(inputName);
		param.setTxtValue(input);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}
	
	/**
	 * Método que guarda el parámetro COLOR_MARKUPS del traductor Lucy
	 * 
	 * @param colorMarkups	Marcadores de color (Si/no)
	 * @return Param		devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setColorMarkups(String colorMarkups) throws Exception {
		
		String inputName = "COLOR_MARKUPS";
		String eMessage = "Error al carregar el paràmetre COLOR_M";
		
	try {	
		Param param =(Param)getObject(Param.class); 
		param.setName(inputName);
		param.setTxtValue(colorMarkups);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}	

	/**
	 * Método que guarda el parámetro MARK_UNKNOWNS del traductor Lucy
	 * 
	 * @param markUnknowns	Marcar las palabras desconocidas (Si/no)
	 * @return Param		devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setMarkUnknowns(String markUnknowns) throws Exception {
		
		String inputName = "MARK_UNKNOWNS";
		String eMessage = "Error al carregar el paràmetre M_UNKN";
		
	try {	
		Param param =(Param)getObject(Param.class); 
		param.setName(inputName);
		param.setTxtValue(markUnknowns);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}
	
	/**
	 * Método que guarda el parámetro MARK_CONSTANTS del traductor Lucy
	 * 
	 * @param markConstants	Marcar las constantes (Si/no)
	 * @return Param		devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setMarkConstants(String markConstants) throws Exception {
		
		String inputName = "MARK_CONSTANTS";
		String eMessage = "Error al carregar el paràmetre M_CONS";
		
	try {	
		Param param =(Param)getObject(Param.class); 
		param.setName(inputName);
		param.setTxtValue(markConstants);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}
	
	/**
	 * Método que guarda el parámetro MARK_COMPOUNDS del traductor Lucy
	 * 
	 * @param markCompounds	Marcar las palabras desconocidas (Si/no)
	 * @return Param		devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setMarkCompounds(String markCompounds) throws Exception {
		
		String inputName = "MARK_COMPOUNDS";
		String eMessage = "Error al carregar el paràmetre M_COMP";
		
	try {	
		Param param =(Param)getObject(Param.class); 
		param.setName(inputName);
		param.setTxtValue(markCompounds);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}
	
	/**
	 * Método que guarda el parámetro MARK_ALTERNATIVES del traductor Lucy
	 * 
	 * @param markAlternatives	Marcar las palabras con más de una acepción (Si/no)
	 * @return Param		devuelve el parámetro con el texto y el valor
	 * @throws Exception
	 */
	private Param setMarkAlternatives(String markAlternatives) throws Exception {
		
		String inputName = "MARK_ALTERNATIVES";
		String eMessage = "Error al carregar el paràmetre M_ALTE";
		
	try {	
		Param param =(Param)getObject(Param.class); 
		param.setName(inputName);
		param.setTxtValue(markAlternatives);
		return param;
		
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}	
	
	/**
	 * Método que devuelve Stream con los datos traducidos del traductor Lucy
	 * 
	 * @param params		Listado de parámetros de traductor
	 * @return InputStream	devuelve los datos traducidos en un Stream
	 * @throws Exception
	 */
	private InputStream getOutput(ParamListType params) throws Exception {

		String outputName = "OUTPUT";
		String eMessage = "Error al recuperar el text traduit";
		InputStream is = null;
		
		try {	

		for(Param param: params.getParam()) {
			if(param.getName().equals(outputName)) {
				is= param.getBinValue().getInputStream();
				break;
			}
		}
		
		return is;
	
	} catch (Exception e) {
		
		log.error(e.getMessage());
		throw new Exception(eMessage);
		
	}
		
	}
    	
	/**
	 * Método que crea una nueva instancia org.apache.axis2.databinding.ADBBean
	 * 
	 * @param type
	 * @return	ADBBean
	 * @throws java.lang.Exception
	 */
    private org.apache.axis2.databinding.ADBBean getObject(java.lang.Class type) throws java.lang.Exception{
        return (org.apache.axis2.databinding.ADBBean) type.newInstance();
     }

    
    /**
     * Método que convierte el Stream de datos traducidos en un String
     * @param is	Stream de datos traducidos por el traductor Lucy
     * @return	String	texto traducido de tipo String
     */
    private String convertStreamToString(InputStream is) {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb = new StringBuilder();
    	String line = null;
    	try {
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			is.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return sb.toString().trim();
    }
    
    /**
     * Método que reemplaza los tags de color rojo por defecto de las palabras desconocidas 
     * del traductor en color azul
     * 
     * @param input	texto traducido por el traductor Lucy
     * @return	String texto con los tags de color azul en el caso de tener tags de color rojo
     */
    private String manageColors(String output) {

    	String red = "<FONT COLOR=#ff0000>";
    	String blue = "<FONT COLOR=#0000ff>";
  	
    	return output.replace(red, blue);

    }
    

}
