package es.caib.traspasa.bean;

public class TrListadoNormativaLocalBean 
{

  private String boib="";
  private String registro="";
  private String titulo="";
  private String trcodificacion="";
  
  public TrListadoNormativaLocalBean()
  {
  }


  public void setBoib(String boib)
  {
    this.boib = boib;
  }


  public String getBoib()
  {
    return boib;
  }


  public void setRegistro(String registro)
  {
    this.registro = registro;
  }


  public String getRegistro()
  {
    return registro;
  }


  public void setTitulo(String titulo)
  {
    this.titulo = titulo;
  }


  public String getTitulo()
  {
    return titulo;
  }


  public void setTrcodificacion(String trcodificacion)
  {
    this.trcodificacion = trcodificacion;
  }


  public String getTrcodificacion()
  {
    return trcodificacion;
  }
}