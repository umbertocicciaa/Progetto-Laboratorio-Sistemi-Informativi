package logic;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static ui.UIUtil.erroreCaricamentoDatabase;

/**
 * @author umbertodomenicociccia
 */
public enum HibernateSingleton {
    ISTANCE;
    private SessionFactory sessionFactory;

    HibernateSingleton() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .applySetting("hibernate.connection.username", System.getenv("DB_USERNAME"))
                .applySetting("hibernate.connection.password", System.getenv("DB_PASSWORD"))
                .applySetting("hibernate.connection.url",System.getenv("DB_LOCATION_LABORATORIO"))
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            erroreCaricamentoDatabase();
        }
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}


