package org.ibit.rol.sac.integracion.uddi;

import java.util.Vector;

import org.apache.juddi.datatype.CategoryBag;
import org.apache.juddi.datatype.Description;
import org.apache.juddi.datatype.Email;
import org.apache.juddi.datatype.KeyedReference;
import org.apache.juddi.datatype.Name;
import org.apache.juddi.datatype.Phone;
import org.apache.juddi.datatype.binding.BindingTemplate;
import org.apache.juddi.datatype.binding.BindingTemplates;
import org.apache.juddi.datatype.binding.TModelInstanceInfo;
import org.apache.juddi.datatype.business.BusinessEntity;
import org.apache.juddi.datatype.business.Contact;
import org.apache.juddi.datatype.business.Contacts;
import org.apache.juddi.datatype.response.BusinessInfo;
import org.apache.juddi.datatype.response.BusinessInfos;
import org.apache.juddi.datatype.response.BusinessList;
import org.apache.juddi.datatype.response.ServiceInfo;
import org.apache.juddi.datatype.response.ServiceInfos;
import org.apache.juddi.datatype.response.ServiceList;
import org.apache.juddi.datatype.response.TModelInfo;
import org.apache.juddi.datatype.response.TModelInfos;
import org.apache.juddi.datatype.response.TModelList;
import org.apache.juddi.datatype.service.BusinessService;
import org.apache.juddi.datatype.tmodel.TModel;

/**
 * Métodos de utilidad de transformación de estructuras de
 * datos UDDI con las usadas por la aplicación.
 */
public class UDDIAssembler {

    /**
     * Construye un objecto {@link Especificacion} a partir de un TModel.
     * @param tModel TModel que representa la especificacion.
     * @return especificación del webservice.
     */
    public static Especificacion getEspecificacion(TModel tModel) {
        final Especificacion espec = new Especificacion();

        espec.setClave(tModel.getTModelKey());
        espec.setNombre(tModel.getName());

        Vector descriptionVector = tModel.getDescriptionVector();
        if (descriptionVector != null && !descriptionVector.isEmpty()) {
            Description desc = (Description) descriptionVector.get(0);
            espec.setDescripcion(desc.getValue());
        }

        espec.setUrlDocumento(tModel.getOverviewDoc().getOverviewURLString());
        Vector docDescriptionVector = tModel.getOverviewDoc().getDescriptionVector();
        if (docDescriptionVector != null && !docDescriptionVector.isEmpty()) {
            Description desc = (Description) docDescriptionVector.get(0);
            espec.setDescripcionDocumento(desc.getValue());
        }

        return espec;
    }

    /**
     * Construye un objecto {@link ServicioWeb} a partir de un BusinessService.
     * El BusinessService debe tener un solo BindingTemplate con el punto de acceso al
     * servicio web y asoaciado un TModel de especificación.
     * @param service Servicio web.
     * @return datos del webservice.
     */
    public static ServicioWeb getServicioWeb(BusinessService service) {
        final ServicioWeb serv = new ServicioWeb();

        serv.setClave(service.getServiceKey());
        serv.setClaveOrganismo(service.getBusinessKey());

        Vector nameVector = service.getNameVector();
        if (nameVector != null && !nameVector.isEmpty()) {
            Name name = (Name) nameVector.get(0);
            serv.setNombre(name.getValue());
        }

        Vector descriptionVector = service.getDescriptionVector();
        if (descriptionVector != null && !descriptionVector.isEmpty()) {
            Description desc = (Description) descriptionVector.get(0);
            serv.setDescripcion(desc.getValue());
        }

        BindingTemplates templates = service.getBindingTemplates();
        Vector bindingTemplateVector = templates.getBindingTemplateVector();
        if (bindingTemplateVector != null && !bindingTemplateVector.isEmpty()) {
            BindingTemplate template = (BindingTemplate) bindingTemplateVector.get(0);
            serv.setPuntoAcceso(template.getAccessPoint().getURL());

            Vector tModelInstanceInfoVector = template.getTModelInstanceDetails().getTModelInstanceInfoVector();
            if (tModelInstanceInfoVector != null && !tModelInstanceInfoVector.isEmpty()) {
                TModelInstanceInfo tModelInstanceInfo = (TModelInstanceInfo) tModelInstanceInfoVector.get(0);
                serv.setClaveEspecificacion(tModelInstanceInfo.getTModelKey());
            }
        }

        return serv;
    }

    /**
     * Construye un objecto {@link Organismo} a partir de un BusinessEntity.
     * @param entity Entidad que representa al organismo.
     * @return datos del organismo.
     */
    public static Organismo getOrganismo(BusinessEntity entity) {
        final Organismo organismo = new Organismo();

        organismo.setClave(entity.getBusinessKey());

        Vector nameVector = entity.getNameVector();
        if (nameVector != null && !nameVector.isEmpty()) {
            Name name = (Name) nameVector.get(0);
            organismo.setNombre(name.getValue());
        }

        Vector descriptionVector = entity.getDescriptionVector();
        if (descriptionVector != null && !descriptionVector.isEmpty()) {
            Description desc = (Description) descriptionVector.get(0);
            organismo.setDescripcion(desc.getValue());
        }

        Contacts contacts = entity.getContacts();
        Vector contactVector = contacts.getContactVector();
        if (contactVector != null && !contactVector.isEmpty()) {
            Contact contact = (Contact) contactVector.get(0);
            organismo.setResponsable(contact.getPersonNameValue());

            Vector phoneVector = contact.getPhoneVector();
            if (phoneVector != null && !phoneVector.isEmpty()) {
                Phone phone = (Phone) phoneVector.get(0);
                organismo.setTelefono(phone.getValue());
            }

            Vector emailVector = contact.getEmailVector();
            if (emailVector != null && !emailVector.isEmpty()) {
                Email email = (Email) emailVector.get(0);
                organismo.setEmail(email.getValue());
            }
        }

        // Buscar la categoria de nivel admnistrativo y fijarla.
        CategoryBag categoryBag = entity.getCategoryBag();
        Vector keyedReferenceVector = categoryBag.getKeyedReferenceVector();
        if (keyedReferenceVector != null) {
            for (int i = 0; i < keyedReferenceVector.size(); i++) {
                KeyedReference keyedReference = (KeyedReference) keyedReferenceVector.elementAt(i);
                if (keyedReference.getKeyName().equals(Constants.NIVEL_ADMINISTRATIVO_NAME)) {
                    organismo.setNivel(keyedReference.getKeyValue());
                    break;
                }
            }
        }

        return organismo;
    }

    /**
     * Obtiene la lista de claves a partir de un TModelList.
     * @param tModelList Resultado de una busqueda de TModel.
     * @return vector de String con las claves de los TModel.
     */
    public static Vector getKeyVector(TModelList tModelList) {
        if (tModelList == null) {
            return null;
        }

        TModelInfos infos = tModelList.getTModelInfos();
        if (infos == null) {
            return null;
        }

        Vector infoVector = infos.getTModelInfoVector();
        if (infoVector == null) {
            return null;
        }

        Vector keys = new Vector(infoVector.size());
        for (int i = 0; i < infoVector.size(); i++) {
            TModelInfo info = (TModelInfo) infoVector.elementAt(i);
            keys.add(info.getTModelKey());
        }

        return keys;
    }

    /**
     * Obtiene la lista de claves a partir de un ServiceList.
     * @param serviceList Resultado de una busqueda de servicios.
     * @return vector de String con las claves de los servicios.
     */
    public static Vector getKeyVector(ServiceList serviceList) {
        if (serviceList == null) {
            return null;
        }

        ServiceInfos infos = serviceList.getServiceInfos();
        if (infos == null) {
            return null;
        }

        Vector infoVector = infos.getServiceInfoVector();
        if (infoVector == null) {
            return null;
        }

        Vector keys = new Vector(infoVector.size());
        for (int i = 0; i < infoVector.size(); i++) {
            ServiceInfo info = (ServiceInfo) infoVector.elementAt(i);
            keys.add(info.getServiceKey());
        }

        return keys;
    }

    /**
     * Obtiene la lista de claves a partir de un BusinessList.
     * @param businessList Resultado de una busqueda de business entity.
     * @return vector de String con las claves de los business entity.
     */
    public static Vector getKeyVector(BusinessList businessList) {
        if (businessList == null) {
            return null;
        }

        return getKeyVector(businessList.getBusinessInfos());

    }

    /**
     * Obtiene la lista de claves a partir de un BusinessInfos.
     * @param infos Resultado de una busqueda de business entity.
     * @return vector de String con las claves de los business entity.
     */
    public static Vector getKeyVector(BusinessInfos infos) {
        if (infos == null) {
            return null;
        }

        Vector infoVector = infos.getBusinessInfoVector();
        if (infoVector == null) {
            return null;
        }

        Vector keys = new Vector(infoVector.size());
        for (int i = 0; i < infoVector.size(); i++) {
            BusinessInfo info = (BusinessInfo) infoVector.elementAt(i);
            keys.add(info.getBusinessKey());
        }

        return keys;
    }

}
