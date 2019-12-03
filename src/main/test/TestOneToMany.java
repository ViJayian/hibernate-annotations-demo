import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import pojo.Customer;
import pojo.LinkMan;
import utils.HibernateUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 测试Customer和LinkMan一对多
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 09:22
 */
public class TestOneToMany {
    @Test
    public void test01(){
        Session session = HibernateUtils.getSession();
    }

    //级联保存
    @Test
    public void test02(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();

        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("张三");
        customer.setMobile("123");
        LinkMan linkMan = new LinkMan();
        linkMan.setLinkName("联系人1");
        linkMan.setCustomer(customer);
        Set<LinkMan> linkManSet = new HashSet<>();
        linkManSet.add(linkMan);
        customer.setLinkManSet(linkManSet);
        session.save(customer);
        session.save(linkMan);

        tx.commit();

        session.close();

    }

    //简化写法，通过cascade属性，customer只需要保存linkman即可
    @Test
    public void test03(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();

        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("李四");
        customer.setMobile("123");
        LinkMan linkMan = new LinkMan();
        linkMan.setLinkName("联系人2");
//        linkMan.setCustomer(customer);
        Set<LinkMan> linkManSet = new HashSet<>();
        linkManSet.add(linkMan);
        customer.setLinkManSet(linkManSet);
        session.save(customer);
//        session.save(linkMan);

        tx.commit();

        session.close();

    }

    //级联删除

    /**
     * 删除过程：
     *  根据customer id查询customer，再查询linkman
     *  将linkman中的外键set为null，然后删除customer和linkman
     */
    @Test
    public void testCascadeDelete(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Customer customer = (Customer) session.get(Customer.class, 1);

        session.delete(customer);
        tx.commit();
    }

    //修改 双向维护外键，导致多次执行sql，一对多 让其中一方放弃维护外键， （让一的一方放弃 set标签上使用）
    @Test
    public void testUpdate(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Customer customer = (Customer) session.get(Customer.class, 2);
        LinkMan linkMan = (LinkMan) session.get(LinkMan.class,4);
        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);
        tx.commit();
        session.close();
    }

    //一的一方放弃关系维护
    @Test
    public void testUpdate2(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Customer customer = (Customer) session.get(Customer.class, 2);
        LinkMan linkMan = new LinkMan();
        linkMan.setLinkName("联系人3 inverse");
        linkMan.setCustomer(customer);
        session.save(linkMan);
        tx.commit();
        session.close();
    }
}
