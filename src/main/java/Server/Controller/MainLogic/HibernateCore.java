package Server.Controller.MainLogic;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateCore {

    private static SessionFactory sessionFactory;


    public static SessionFactory getInstance() {
        return sessionFactory;
    }

    public static void connectToDataBase() {
        if (sessionFactory == null) {
            Configuration configuration;
            final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
    }
}
