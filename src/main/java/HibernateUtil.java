import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


    public static Session createHibernateSession() {
        SessionFactory sf = null;
        StandardServiceRegistry srvc = null;
        Session session = null;
        try {
            Configuration conf = new Configuration();
            conf.configure("hibernate.cfg.xml");
            conf.addAnnotatedClass(Company.class);
            conf.addAnnotatedClass(Customer.class);
            conf.addAnnotatedClass(Developer.class);
            conf.addAnnotatedClass(Project.class);
            conf.addAnnotatedClass(Skill.class);

            srvc = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
            sf = conf.buildSessionFactory(srvc);
            session = sf.openSession();
            System.out.println("Создание сессии");
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        return session;
    }


}




