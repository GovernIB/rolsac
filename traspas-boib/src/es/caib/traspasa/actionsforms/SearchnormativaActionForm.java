package es.caib.traspasa.actionsforms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchnormativaActionForm extends ActionForm 
{

  private String numeroboletin="";
  private String numeroregistro="";
  private String fecha="";


  /**
   * Reset all properties to their default values.
   * @param mapping The ActionMapping used to select this instance.
   * @param request The HTTP Request we are processing.
   */
  public void reset(ActionMapping mapping, HttpServletRequest request)
  {
    super.reset(mapping, request);
  }

  /**
   * Validate all properties to their default values.
   * @param mapping The ActionMapping used to select this instance.
   * @param request The HTTP Request we are processing.
   * @return ActionErrors A list of all errors found.
   */
  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
    return super.validate(mapping, request);
  }


  public void setNumeroboletin(String numeroboletin)
  {
    this.numeroboletin = numeroboletin;
  }


  public String getNumeroboletin()
  {
    return numeroboletin;
  }


  public void setNumeroregistro(String numeroregistro)
  {
    this.numeroregistro = numeroregistro;
  }


  public String getNumeroregistro()
  {
    return numeroregistro;
  }


  public void setFecha(String fecha)
  {
    this.fecha = fecha;
  }


  public String getFecha()
  {
    return fecha;
  }
}