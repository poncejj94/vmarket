/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Juan José Ponce
 */
public class HibernateUtil {

    private static SessionFactory buildSessionFactory() {
	try {

		Configuration configuration = new Configuration().configure();
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
                SessionFactory factory = configuration.buildSessionFactory(builder.build());
            return factory;
			
	} catch (Throwable ex) {
	
		System.err.println("Initial SessionFactory creation failed." + ex);
		throw new ExceptionInInitializerError(ex);
	}
}
    
    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }
}
