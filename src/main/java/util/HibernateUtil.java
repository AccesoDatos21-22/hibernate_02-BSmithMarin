package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sf ;

    private HibernateUtil(){}

    public static SessionFactory getSessionFactory(){

        //Creacion del session factory a partir del fichero hibernate.cfg.xml
        if (sf == null){
            StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
            sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

        }

        return sf;
    }

    public static void closeSessionFactory(){
        if(sf != null)
            sf.close();
    }

}
