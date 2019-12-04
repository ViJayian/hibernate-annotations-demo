package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-04 10:28
 */
public class HibernateAnnotationUtils {
    private static final SessionFactory sessionFacotry;

    static {
        sessionFacotry = new AnnotationConfiguration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
    }

    public static Session getSession(){
        return sessionFacotry.openSession();
    }

    public static Session getCurrentSession(){
        return sessionFacotry.getCurrentSession();
    }
}
