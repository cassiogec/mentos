/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author leonardo.rocha
 */
public class HibernateUtil3 {

    private static SessionFactory sessionFactory;
    private static SessionFactory sessionFactory2;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
        {
             try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuration = new Configuration().configure();
            configuration.configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
            
           // sessionFactory = new Configuration().configure().buildSessionFactory();            
            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Falha ao Iniciar BD Principal." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory2() {
        if (sessionFactory2 == null)
        {
             try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration configuration = new Configuration().configure();
            configuration.configure("hibernate.cfg_2.xml");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
            applySettings(configuration.getProperties());
            sessionFactory2 = configuration.buildSessionFactory(builder.build());
            
           // sessionFactory = new Configuration().configure().buildSessionFactory();            
            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Falha ao Iniciar BD Principal." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory2;
    }
}
