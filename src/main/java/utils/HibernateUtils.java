package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-02 16:21
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化sessionfacory失败");
        }
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
