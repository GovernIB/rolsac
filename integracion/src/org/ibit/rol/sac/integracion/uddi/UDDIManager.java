package org.ibit.rol.sac.integracion.uddi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.datatype.CategoryBag;
import org.apache.juddi.datatype.Description;
import org.apache.juddi.datatype.Email;
import org.apache.juddi.datatype.KeyedReference;
import org.apache.juddi.datatype.Name;
import org.apache.juddi.datatype.OverviewDoc;
import org.apache.juddi.datatype.PersonName;
import org.apache.juddi.datatype.Phone;
import org.apache.juddi.datatype.TModelBag;
import org.apache.juddi.datatype.binding.AccessPoint;
import org.apache.juddi.datatype.binding.BindingTemplate;
import org.apache.juddi.datatype.binding.TModelInstanceDetails;
import org.apache.juddi.datatype.binding.TModelInstanceInfo;
import org.apache.juddi.datatype.business.BusinessEntity;
import org.apache.juddi.datatype.business.Contact;
import org.apache.juddi.datatype.request.AuthInfo;
import org.apache.juddi.datatype.response.BusinessDetail;
import org.apache.juddi.datatype.response.BusinessList;
import org.apache.juddi.datatype.response.ServiceDetail;
import org.apache.juddi.datatype.response.ServiceList;
import org.apache.juddi.datatype.response.TModelDetail;
import org.apache.juddi.datatype.response.TModelList;
import org.apache.juddi.datatype.response.RegisteredInfo;
import org.apache.juddi.datatype.response.BusinessInfos;
import org.apache.juddi.datatype.response.BusinessInfo;
import org.apache.juddi.datatype.response.ServiceInfo;
import org.apache.juddi.datatype.service.BusinessService;
import org.apache.juddi.datatype.tmodel.TModel;
import org.apache.juddi.error.RegistryException;
import org.apache.juddi.proxy.RegistryProxy;

/**
 * Nueva versión de UDDIDelegate, en pruebas.
 */
public class UDDIManager {

    protected static Log log = LogFactory.getLog(UDDIManager.class);

    private RegistryProxy proxy;
    private AuthInfo auth = null;

    public UDDIManager(String user, String password) {
        this();
        try {
            auth = proxy.getAuthToken(user, password).getAuthInfo();
        } catch (RegistryException re) {
            log.info("No ha sido posible obtener autorización", re);
        }

        try {
            initNivelAdministrativo();
        } catch (RegistryException re) {
            log.info("Error en la inicialización", re);
        }
    }

    public UDDIManager() {
        proxy = new RegistryProxy();
        log.info(proxy.getInquiryURL());
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (auth != null) {
            proxy.discardAuthToken(auth);
        }
    }

    /* ======================= */
    /* == Métodos públicos  == */
    /* ======================= */

    /**
     * Publica un TModel de especificación wsdl
     * @param especificacion Datos del TModel
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public void publicarEspecificacion(Especificacion especificacion) throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            TModel tModel = new TModel();
            // Clave
            if (especificacion.getClave() != null && especificacion.getClave().length() > 0) {
                tModel.setTModelKey(especificacion.getClave());
            }

            // Nombre y descripcion del TModel
            tModel.setName(especificacion.getNombre());
            tModel.addDescription(new Description(especificacion.getDescripcion()));

            // URL y descripcion del doucmento WSDL
            OverviewDoc documento = new OverviewDoc(especificacion.getUrlDocumento());
            documento.addDescription(new Description(especificacion.getDescripcionDocumento()));
            tModel.setOverviewDoc(documento);

            // Es un TModel de especificacion WSDL
            tModel.addCategory(getWsdlSpecKeyedReference());

            // Guardar el TModel
            TModelDetail detail = proxy.saveTModel(auth, oneElementVector(tModel));
            TModel result = (TModel) detail.getTModelVector().get(0);
            especificacion.setClave(result.getTModelKey());
            UDDICache.putTModel(result);

        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos de un TModel del tipo espeficicación wsdl.
     * @param clave Clave del TModel.
     * @return especificacion con los datos del TModel.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public Especificacion obtenerEspecificacion(String clave) throws UDDIException {
        try {
            TModel tModel = getTModelByKey(clave);
            if (tModel == null) return null;

            return UDDIAssembler.getEspecificacion(tModel);
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos de los TModel del tipo espeficicación wsdl.
     * @return array de especificaciones con los datos de los TModel.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public Especificacion[] listarEspecificaciones() throws UDDIException {

        try {
            // Lista de las claves de los TModels.
            Vector keys = getWsdlSpecTModelKeys();
            TModel[] tModels = getTModelsByKey(keys);

            // Transformamos los TModel en Especificacion
            Especificacion[] result = new Especificacion[tModels.length];
            for (int i = 0; i < tModels.length; i++) {
                result[i] = UDDIAssembler.getEspecificacion(tModels[i]);
            }

            return result;
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Borra un TModel con una clave determinada.
     * @param clave key del TModel.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public void borrarEspecificacion(String clave) throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            proxy.deleteTModel(auth, oneElementVector(clave));
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Publica un servicio web con un punto de acceso que implementa una interfaz wsdl.
     * @param servicio Datos del BussinessService y el BindingTemplate
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public void publicarServicio(ServicioWeb servicio) throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            BusinessService service = new BusinessService();

            // Clave del servicio.
            if (servicio.getClave() != null && servicio.getClave().length() > 0) {
                service.setServiceKey(servicio.getClave());
            }

            // Nombre y descripción del servicio.
            service.addName(new Name(servicio.getNombre()));
            service.addDescription(new Description(servicio.getDescripcion()));

            // Business al que pertenece el servicio.
            service.setBusinessKey(servicio.getClaveOrganismo());

            // Crear bindingTemplate.
            BindingTemplate template = new BindingTemplate();
            String urlValue = servicio.getPuntoAcceso();
            String urlType = new URL(urlValue).getProtocol();
            template.setAccessPoint(new AccessPoint(urlType, urlValue));

            // Añadir TModel de la especificacion al BindingTemplate.
            TModelInstanceDetails tModelInstanceDetails = new TModelInstanceDetails();
            TModelInstanceInfo tModelInstanceInfo = new TModelInstanceInfo();
            tModelInstanceInfo.setTModelKey(servicio.getClaveEspecificacion());
            tModelInstanceDetails.addTModelInstanceInfo(tModelInstanceInfo);
            template.setTModelInstanceDetails(tModelInstanceDetails);

            // Añadir el BindingTemplate al servicio.
            service.addBindingTemplate(template);

            // Guardar el servicio
            ServiceDetail detail = proxy.saveService(auth, oneElementVector(service));
            BusinessService result = (BusinessService) detail.getBusinessServiceVector().get(0);
            servicio.setClave(result.getServiceKey());
            UDDICache.putService(result);

        } catch (MalformedURLException e) {
            throw new UDDIException(e);
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos de un Servicio definido con WSDL.
     * @param clave Clave del BusinessService.
     * @return servicio web con los datos del BusinessService y su BindingTemplate.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public ServicioWeb obtenerServicio(String clave) throws UDDIException {
        try {
            BusinessService service = getServiceByKey(clave);
            if (service == null) return null;

            ServicioWeb servicioWeb = UDDIAssembler.getServicioWeb(service);

            // Meter el nombre del organismo.
            BusinessEntity entity = getBusinessByKey(service.getBusinessKey());
            Vector nameVector = entity.getNameVector();
            if (nameVector != null && !nameVector.isEmpty()) {
                Name name = (Name) nameVector.get(0);
                servicioWeb.setNombreOrganismo(name.getValue());
            }

            /*
            Vector descrVector = entity.getDescriptionVector();
            if (descrVector != null && !descrVector.isEmpty()) {
                Description descr = (Description) descrVector.get(0);
                servicioWeb.setDescrOrganismo(descr.getValue());
            }
            */


            return servicioWeb;
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos de los Servicios web de un organismo definidos con wsdl.
     * @return array de servicios web con los datos de los BusinessService.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public ServicioWeb[] listarServicios(String claveOrganismo) throws UDDIException {

        try {
            // Lista de especificaciones.
            TModelBag tModelBag = new TModelBag();
            tModelBag.setTModelKeyVector(getWsdlSpecTModelKeys());

            ServiceList list = proxy.findService(claveOrganismo, getAllNameVector(), null, tModelBag, null, 0);

            // Obtenemos todos los servicios con la lista de claves.
            Vector keys = UDDIAssembler.getKeyVector(list);
            BusinessService[] services = getServicesByKey(keys);

            // Transformamos los BusinessService en ServicioWeb
            ServicioWeb[] result = new ServicioWeb[services.length];
            for (int i = 0; i < services.length; i++) {
                result[i] = UDDIAssembler.getServicioWeb(services[i]);

                // Meter el nombre del organismo.
                BusinessEntity entity = getBusinessByKey(services[i].getBusinessKey());
                Vector nameVector = entity.getNameVector();
                if (nameVector != null && !nameVector.isEmpty()) {
                    Name name = (Name) nameVector.get(0);
                    result[i].setNombreOrganismo(name.getValue());
                }

                /*
                Vector descrVector = entity.getDescriptionVector();
                if (descrVector != null && !descrVector.isEmpty()) {
                    Description descr = (Description) descrVector.get(0);
                    result[i].setDescrOrganismo(descr.getValue());
                }
                */

            }

            return result;
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos todos los Servicios web definidos con wsdl.
     * @return array de servicios web con los datos de los BusinessService.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public ServicioWeb[] listarServicios() throws UDDIException {
        return listarServicios("");
    }

    /**
     * Obtiene los datos de los servicios que cumplen una determinada especificación y
     * pertenecen a un organismo con un nivel determinado.
     * @param nombreEspecificacion key del TModel de especificación de un servicio.
     * @param nivel Nivel administrativo del organismo (<code>null</code> o "" si no se debe tener en cuenta).
     * @return array de servicios con los datos de los BusinessService.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public ServicioWeb[] listarServiciosEspecificacionOrganismoNivel(String nombreEspecificacion, String nivel) throws UDDIException {

        try {
            CategoryBag categoryBag = new CategoryBag(1);
            if (nivel != null && nivel.length() > 0) {
                categoryBag.addKeyedReference(getNivelAdministrativoKeyedReference(nivel));
            }

            TModelBag tModelBag = new TModelBag(1);
            tModelBag.addTModelKey(getTModelKeyByName(nombreEspecificacion));


            BusinessList list = proxy.findBusiness(getAllNameVector(), null, null, categoryBag, tModelBag, null, 0);
            BusinessInfos infos = list.getBusinessInfos();
            Vector infoVector = infos.getBusinessInfoVector();
            if (infoVector == null || infoVector.isEmpty()) {
                return new ServicioWeb[0];
            }

            List result = new ArrayList(infoVector.size());
            for (int i = 0; i < infoVector.size(); i++) {
                BusinessInfo info = (BusinessInfo) infoVector.elementAt(i);

                Vector serviceInfoVector = info.getServiceInfos().getServiceInfoVector();
                if (serviceInfoVector == null || serviceInfoVector.isEmpty()) {
                    continue;
                }

                ServiceInfo serviceInfo = (ServiceInfo) serviceInfoVector.get(0);
                ServicioWeb servicio = UDDIAssembler.getServicioWeb(getServiceByKey(serviceInfo.getServiceKey()));

                Vector nameVector = info.getNameVector();
                if (nameVector != null && !nameVector.isEmpty()) {
                    Name name = (Name) nameVector.get(0);
                    servicio.setNombreOrganismo(name.getValue());
                }

                /*
                Vector descrVector = info.getDescriptionVector();
                if (descrVector != null && !descrVector.isEmpty()) {
                    Description descr = (Description) descrVector.get(0);
                    servicio.setDescrOrganismo(descr.getValue());
                }
                */

                result.add(servicio);
            }
            return (ServicioWeb[]) result.toArray(new ServicioWeb[result.size()]);
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Borra un Servicio Web con una clave determinada.
     * @param clave key del BusinessService.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public void borrarServicio(String clave) throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            proxy.deleteService(auth, oneElementVector(clave));
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Publica los datos de un organismo como un BusinessEntity.
     * @param organismo Datos del BusinessEntity
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public void publicarOrganismo(Organismo organismo) throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            BusinessEntity entity = new BusinessEntity();

            // Clave del organismo.
            if (organismo.getClave() != null && organismo.getClave().length() > 0) {
                entity.setBusinessKey(organismo.getClave());

                // Guardar los servicios.
                BusinessEntity oldEntity = getBusinessByKey(organismo.getClave());
                entity.setBusinessServices(oldEntity.getBusinessServices());
            }

            // Nombre y descripcion
            entity.addName(new Name(organismo.getNombre()));
            entity.addDescription(new Description(organismo.getDescripcion()));

            // Información de contacto.
            // TODO Añadir direccion?
            Contact contact = new Contact();
            contact.setPersonName(new PersonName(organismo.getResponsable()));
            contact.addPhone(new Phone(organismo.getTelefono()));
            contact.addEmail(new Email(organismo.getEmail()));
            entity.addContact(contact);

            // TModel de categorización de nivel administrativo.
            entity.addCategory(getNivelAdministrativoKeyedReference(organismo.getNivel()));

            // Guardar la entidad
            BusinessDetail detail = proxy.saveBusiness(auth, oneElementVector(entity));
            BusinessEntity result = (BusinessEntity) detail.getBusinessEntityVector().get(0);
            organismo.setClave(result.getBusinessKey());
            UDDICache.putBusiness(result);

        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos de un Organismo.
     * @param clave Clave del BusinessEntity.
     * @return organismo con los datos del BusinessEntity.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public Organismo obtenerOrganismo(String clave) throws UDDIException {
        try {
            BusinessEntity entity = getBusinessByKey(clave);
            if (entity == null) return null;

            return UDDIAssembler.getOrganismo(entity);
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Obtiene los datos de los Organismos.
     * @return array de organismos con los datos de los BusinessEntity.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public Organismo[] listarOrganismos() throws UDDIException {

        try {

            BusinessList list = proxy.findBusiness(getAllNameVector(), null, null, null, null, null, 0);

            // Obtenemos todos los business con la lista de claves.
            Vector keys = UDDIAssembler.getKeyVector(list);
            BusinessEntity[] entities = getBusinessByKey(keys);

            // Transformamos los BusinessEntity en Organismo.
            Organismo[] result = new Organismo[entities.length];
            for (int i = 0; i < entities.length; i++) {
                result[i] = UDDIAssembler.getOrganismo(entities[i]);
            }

            return result;
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }


    /**
     * Obtiene los datos de los Organismos públicados por el publisher local.
     * @return array de organismos con los datos de los BusinessEntity.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public Organismo[] listarOrganismosPropios() throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            RegisteredInfo registeredInfo = proxy.getRegisteredInfo(auth);

            // Claves de los organismos registrados por el usuario.
            Vector keys = UDDIAssembler.getKeyVector(registeredInfo.getBusinessInfos());
            BusinessEntity[] entities = getBusinessByKey(keys);

            // Transformamos los BusinessEntity en Organismo.
            Organismo[] result = new Organismo[entities.length];
            for (int i = 0; i < entities.length; i++) {
                result[i] = UDDIAssembler.getOrganismo(entities[i]);
            }

            return result;
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /**
     * Borra un Organismo con una clave determinada.
     * @param clave key del BusinessEntity.
     * @throws UDDIException Si se produce un error en el proxy UDDI.
     */
    public void borrarOrganismo(String clave) throws UDDIException {

        if (auth == null) throw new SecurityException("Esta usando una instancia no autenticada");

        try {
            proxy.deleteBusiness(auth, oneElementVector(clave));
        } catch (RegistryException e) {
            throw new UDDIException(e);
        }
    }

    /* ============================================ */
    /* == Métodos privados de obtención de datos == */
    /* ============================================ */

    /**
     * Obtinene la lista de claves de los TModels del tipo wsdlSpec.
     * @return lista de String con las claves.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private Vector getWsdlSpecTModelKeys() throws RegistryException {
        CategoryBag categoryBag = new CategoryBag(1);
        categoryBag.addKeyedReference(getWsdlSpecKeyedReference());

        TModelList list = proxy.findTModel(null, categoryBag, null, null, 0);
        Vector keys = UDDIAssembler.getKeyVector(list);

        return keys;
    }

    /**
     * Método de utilidad para la clave de un TModel con el nombre.
     * @param name Nombre del TModel.
     * @return Clave del tModel.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private String getTModelKeyByName(String name) throws RegistryException {
        String result = UDDICache.getKeyForName(name);
        if (result != null) return result;

        TModelList tModelList = proxy.findTModel(name, null, null, null, 1);
        Vector keys = UDDIAssembler.getKeyVector(tModelList);
        if (keys == null || keys.isEmpty()) {
            return null;
        }

        result = (String) keys.get(0);
        UDDICache.putKeyForName(name, result);

        return result;
    }

    /**
     * Método de utilidad para obtener un TModel directamente con su clave.
     * @param tModelKey Clave del TModel.
     * @return Datos completos del tModel.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private TModel getTModelByKey(String tModelKey) throws RegistryException {
        TModel result = UDDICache.getTModel(tModelKey);
        if (result != null) return result;

        TModelDetail detail = proxy.getTModelDetail(tModelKey);
        Vector vector = detail.getTModelVector();
        if (vector == null || vector.isEmpty()) {
            return null;
        }

        result = (TModel) vector.get(0);
        UDDICache.putTModel(result);

        return result;
    }

    /**
     * Método de utilidad para obtener varios TModel directamente con sus claves.
     * @param keys Vector de String con las claves de los TModels.
     * @return Datos completos de los TModel.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private TModel[] getTModelsByKey(Vector keys) throws RegistryException {
        if (keys == null || keys.isEmpty()) {
            return new TModel[0];
        }

        TModel[] result = UDDICache.getTModels(keys);
        if (result != null) return result;

        TModelDetail detail = proxy.getTModelDetail(keys);
        Vector vector = detail.getTModelVector();

        result = new TModel[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            result[i] = (TModel) vector.elementAt(i);
        }

        UDDICache.putTModels(keys, result);

        return result;
    }

    /**
     * Método de utilidad para obtener un BusinessService directamente con su clave.
     * @param serviceKey Clave del BusinessService.
     * @return Datos completos del BusinessService.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private BusinessService getServiceByKey(String serviceKey) throws RegistryException {
        BusinessService result = UDDICache.getService(serviceKey);
        if (result != null) return result;

        ServiceDetail detail = proxy.getServiceDetail(serviceKey);
        Vector vector = detail.getBusinessServiceVector();
        if (vector == null || vector.isEmpty()) {
            return null;
        }

        result = (BusinessService) vector.get(0);
        UDDICache.putService(result);

        return result;
    }


    /**
     * Método de utilidad para obtener varios BusinessService directamente con sus claves.
     * @param keys Vector de String con las claves de los BusinessService.
     * @return Datos completos de los BusinessService.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private BusinessService[] getServicesByKey(Vector keys) throws RegistryException {
        if (keys == null || keys.isEmpty()) {
            return new BusinessService[0];
        }

        BusinessService[] result = UDDICache.getServices(keys);
        if (result != null) return result;

        ServiceDetail detail = proxy.getServiceDetail(keys);
        Vector vector = detail.getBusinessServiceVector();


        result = new BusinessService[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            result[i] = (BusinessService) vector.elementAt(i);
        }

        UDDICache.putServices(keys, result);

        return result;
    }

    /**
     * Método de utilidad para obtener varios BusinessEntity directamente con sus claves.
     * @param keys Vector de String con las claves de los BusinessEntity.
     * @return Datos completos de los BusinessEntity.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private BusinessEntity[] getBusinessByKey(Vector keys) throws RegistryException {
        if (keys == null || keys.isEmpty()) {
            return new BusinessEntity[0];
        }

        BusinessEntity[] result = UDDICache.getBusiness(keys);
        if (result != null) return result;

        BusinessDetail detail = proxy.getBusinessDetail(keys);
        Vector vector = detail.getBusinessEntityVector();

        result  = new BusinessEntity[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            result[i] = (BusinessEntity) vector.elementAt(i);
        }

        UDDICache.putBusiness(keys, result);

        return result;
    }

    /**
     * Método de utilidad para obtener un BusinessEntity directamente con su clave.
     * @param businessKey Clave del BusinessEntity.
     * @return Datos completos del BusinessEntity.
     * @throws RegistryException Si se produce un error en el proxy UDDI.
     */
    private BusinessEntity getBusinessByKey(String businessKey) throws RegistryException {
        BusinessEntity result = UDDICache.getBusiness(businessKey);
        if (result != null) return result;

        BusinessDetail detail = proxy.getBusinessDetail(businessKey);
        Vector vector = detail.getBusinessEntityVector();
        if (vector == null || vector.isEmpty()) {
            return null;
        }

        result = (BusinessEntity) vector.get(0);
        UDDICache.putBusiness(result);

        return result;
    }

    /* ============================= */
    /* == Métodos privados varios == */
    /* ============================= */

    /**
     * Construye un objeto {@link KeyedReference} para referenciar
     * una espcificion WSDL.
     * @return referencia a una especificacion WSDL.
     */
    private static KeyedReference getWsdlSpecKeyedReference() {
        final KeyedReference referencia = new KeyedReference();
        referencia.setTModelKey(Constants.UDDI_TYPE_TMODEL_KEY);
        referencia.setKeyName("uddi-org:types");
        referencia.setKeyValue("wsdlSpec");
        return referencia;
    }

    private KeyedReference getNivelAdministrativoKeyedReference(String nivel) throws RegistryException {
        final KeyedReference referencia = new KeyedReference();
        referencia.setTModelKey(getTModelKeyByName(Constants.NIVEL_ADMINISTRATIVO_NAME));
        referencia.setKeyName(Constants.NIVEL_ADMINISTRATIVO_NAME);
        referencia.setKeyValue(nivel);
        return referencia;
    }

    private void initNivelAdministrativo() throws RegistryException {

        if (getTModelKeyByName(Constants.NIVEL_ADMINISTRATIVO_NAME) != null) {
            return;
        }

        TModel tModel = new TModel();
        tModel.setName(Constants.NIVEL_ADMINISTRATIVO_NAME);
        tModel.addDescription(new Description("Nivel administrativo de un organismo."));

        CategoryBag bag = new CategoryBag(2);
        bag.addKeyedReference(
                new KeyedReference(
                        "uuid:c1acf26d-9672-4404-9d70-39b756e62ab4",
                        "uddi-org:types",
                        "categorization"));

        bag.addKeyedReference(
                new KeyedReference(
                        "uuid:c1acf26d-9672-4404-9d70-39b756e62ab4",
                        "uddi-org:types",
                        "unchecked"));
        tModel.setCategoryBag(bag);

        proxy.saveTModel(auth, oneElementVector(tModel));
    }

    private static Vector getAllNameVector() {
        return oneElementVector(new Name("%"));
    }

    private static Vector oneElementVector(Object o) {
        final Vector vector = new Vector(1);
        vector.add(o);
        return vector;
    }
}

