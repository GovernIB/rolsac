package org.ibit.rol.sac.persistence.eboib;

public class TrMensaAvisoBean 
{
  private String cabecera="";
  private String subcabecera="";
  private String descripcion="";
  
  public TrMensaAvisoBean()
  {
  }


  public void setCabecera(String cabecera)
  {
    this.cabecera = cabecera;
  }


  public String getCabecera()
  {
    return cabecera;
  }


  public void setSubcabecera(String subcabecera)
  {
    this.subcabecera = subcabecera;
  }


  public String getSubcabecera()
  {
    return subcabecera;
  }


  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }


  public String getDescripcion()
  {
    return descripcion;
  }
}