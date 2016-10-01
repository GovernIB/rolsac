package es.caib.rolsac.back2.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Permite acceder al contexto de aplicación.
 * 
 * @author Indra
 * 
 */
@Component(value = "applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {
    /**
     * Aplication context.
     */
    private static ApplicationContext applicationContext;

    /**
     * Obtiene el contexto de aplicación.
     * 
     * @return contexto aplicación
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Establece el contexto de aplicación.
     * 
     * @param papplicationContext
     *            contexto de aplicación
     */
    @Override
    public final void setApplicationContext(
            final ApplicationContext papplicationContext) {
        // Assign the ApplicationContext into a static variable
        ApplicationContextProvider.applicationContext = papplicationContext;
    }
}
