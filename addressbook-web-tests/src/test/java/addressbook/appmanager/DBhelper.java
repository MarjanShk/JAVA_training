package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Admin on 15.04.2016.
 */
public class DBhelper {
    private SessionFactory sessionFactory;

    public DBhelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }
    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> groups = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(groups);
    }
    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery("from ContactData WHERE deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(contacts);
    }

}
