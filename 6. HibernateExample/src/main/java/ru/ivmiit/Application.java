package ru.ivmiit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ivmiit.models.Car;
import ru.ivmiit.models.User;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "314159");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(Car.class);
        configuration.setProperty("hibernate.show_sql", "true");

        configuration.addResource("User.hbn.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        User user = session.createQuery("from User user where user.id = 1", User.class).getSingleResult();
        session.beginTransaction();
        session.save(new User("Andrey", "Ivanov", 50));
        session.getTransaction().commit();
        System.out.print(user);

        List<Car> car = session.createQuery("from Car car", Car.class).getResultList();

        int i = 0;
    }
}
