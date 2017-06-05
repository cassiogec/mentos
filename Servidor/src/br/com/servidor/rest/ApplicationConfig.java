package br.com.servidor.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Mentos Ltda.
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(br.com.servidor.rest.WebServiceRest.class);
        resources.add(br.com.servidor.rest.ResponseCorsFilter.class);
        //addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(br.com.servidor.rest.WebServiceRest.class);
//        resources.add(br.com.servidor.rest.ResponseCorsFilter.class);
//    }
}