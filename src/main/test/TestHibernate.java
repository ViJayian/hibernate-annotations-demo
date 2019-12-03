import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import pojo.User;
import utils.HibernateUtils;

import javax.persistence.Temporal;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-02 15:22
 */
public class TestHibernate {
    @Test
    public void test01() {
        try {
            Configuration configuration = new Configuration().configure("hibernate/hibernate.cfg.xml");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.getTransaction();
            tx.begin();
            User user = new User();
            user.setUsername("张三");
            user.setPassword("123123");
            session.save(user);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        System.out.println("执行查询。。");
        User user = (User) session.get(User.class, 2);
        System.out.println(user);
        System.out.println("执行查询。。");
        User user2 = (User) session.get(User.class, 2);
        System.out.println(user2);
        transaction.commit();
        session.close();
    }

    /**
     * 持久化 id和session
     * 脱管态 只有id
     * 瞬时态 无id，和session无关联，数据库中无记录
     */
    @Test
    public void test03() {
        Session session = HibernateUtils.getSession();
        //持久态对象，session关闭后会将一级缓存中记录和快照区记录进行对比，不同的值，会update
        Transaction tx = session.getTransaction();
        tx.begin();
        User user = (User) session.get(User.class, 2);
        System.out.println("更新前user：" + user);
        user.setUsername("wangwu");
        tx.commit();
        session.close();
        System.out.println("更新后user：" + user);
    }


    @Test
    public void test04(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();

        tx.begin();
        User user = (User) session.get(User.class, 1);
        System.out.println("更新前user：" + user);
        user.setUsername("wangwu");
        tx.commit();
        //和当前线程绑定，当前线程执行完，session关闭
//        session.close();
        System.out.println("更新后user：" + user);
    }

    //hibernate 一级缓存中存储的是持久态的数据
    @Test
    public void testSaveOrUpdate(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        //瞬时态对象 执行insert
//        User user = new User();
//        user.setUsername("张三");
//        user.setPassword("123");

        //脱管态对象 执行update
//        User user = new User();
//        user.setId(1);
//        user.setUsername("李四");
//        user.setPassword("1235");

        //持久态对象 执行update
        User user = (User) session.get(User.class, 2);
        user.setUsername("王五");
        session.saveOrUpdate(user);
        tx.commit();
        session.close();
    }

    @Test
    public void testCache(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        //持久态对象 执行update
        User user = (User) session.get(User.class, 2);
        user.setUsername("王五2");
        //不执行update语句同样可以进行更新，一级缓存和快照对比操作
//        session.update(user);
        tx.commit();
        session.close();
    }

    @Test
    public void hibernateApi(){
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("from User");
        List<User> list = query.list();
        System.out.println(list);

        Criteria criteria = session.createCriteria(User.class);
        List list1 = criteria.list();
        System.out.println(list1);

        SQLQuery sqlQuery = session.createSQLQuery("select * from user");
        List<Object[]> list2 = sqlQuery.list();
        for (Object[] objects:list2){
            System.out.println(Arrays.toString(objects));
        }
    }
}
