package org.ibit.rol.sac.model;

/**
 * Interfaz para los objetos remotos.
 */
public interface Remoto {

   /* public String getParamName();

    public void setParamName(String paramName);

    public String getParamValue();

    public void setParamValue(String paramValue);
*/
    public Long getIdExterno();

    public void setIdExterno(Long idExterno);

    public AdministracionRemota getAdministracionRemota();

    public void setAdministracionRemota(AdministracionRemota administracionRemota);

}
