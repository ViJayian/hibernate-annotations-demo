import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.junit.Test;
import pojo.Customer;
import pojo.LinkMan;
import utils.HibernateUtils;

import java.util.List;
import java.util.Set;

/**
 * @Description: 对象导航查询
 * 1.对象导航查询，通过id查询customer，再查询linkman
 * 2.OID查询
 * 3.hql查询 Query
 * 4.QBC查询 Criteria
 * 5.本地sql插叙
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 15:31
 */
public class TestHibernateQuery {
    @Test
    public void test01() {
        //对象导航查询
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        //使用session获取指定id数据即为OID查询
        Customer customer = (Customer) session.get(Customer.class, 2);
        Set<LinkMan> linkManSet = customer.getLinkManSet();
        System.out.println(linkManSet);
        tx.commit();
    }

    //hql查询
    @Test
    public void test02() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        //查询所有
        String findAllHql = "from LinkMan";
        Query query = session.createQuery(findAllHql);
        List<LinkMan> list = query.list();
        System.out.println(list);

        //equal条件查询
        String findUnique = "from LinkMan where linkId = ?";
        Query query1 = session.createQuery(findUnique);
        //query的？从0 开始
        query1.setInteger(0, 2);
        LinkMan linkMan = (LinkMan) query1.uniqueResult();
        System.out.println(linkMan);

        //like模糊查询
        String findByLike = "from LinkMan where linkName like ?";
        Query query2 = session.createQuery(findByLike);
        query2.setParameter(0, "联系人%");
        List<LinkMan> list1 = query2.list();
        System.out.println(list1);

        //排序查询
        String findByOrder = "from LinkMan order by linkId desc";
        Query query4 = session.createQuery(findByOrder);
        List<LinkMan> list3 = query4.list();
        System.out.println(list3);

        //分页查询,hql中不能直接写limit
        String limitHql = "from LinkMan";
        Query query3 = session.createQuery(limitHql);
        //从第2条开始，查询2条
        //设置开始位置
        query3.setFirstResult(1);
        //设置每页最大记录数
        query3.setMaxResults(2);
        List<LinkMan> list2 = query3.list();
        System.out.println("分页结果集：" + list2);

        //投影查询 select 实体类属性 from 实体类
        String tyQuery = "select linkName from LinkMan";
        Query query5 = session.createQuery(tyQuery);
        List<LinkMan> list4 = query5.list();
        System.out.println("投影查询结果：" + list4);

        //聚合函数使用 count sum avg max min
        String groupQuery = "select count(*) from LinkMan";
        Query query6 = session.createQuery(groupQuery);
        Long total = (Long) query6.uniqueResult();
        int intValue = total.intValue();
        System.out.println("total:" + total + " intValue:" + intValue);
        tx.commit();
    }

    //QBC查询
    @Test
    public void testQBC() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        //查询所有
        Criteria criteria = session.createCriteria(LinkMan.class);
        List<LinkMan> list = criteria.list();
        System.out.println("find all :" + list);

        //条件查询
        Criteria criteria1 = session.createCriteria(LinkMan.class);
        //add方法添加条件值    属性名-值
        //equal
        criteria1.add(Restrictions.eq("linkId", 2));
        LinkMan linkMan = (LinkMan) criteria1.uniqueResult();
        System.out.println("find unique:" + linkMan);

        //条件like
        Criteria criteria2 = session.createCriteria(LinkMan.class);
        criteria2.add(Restrictions.like("linkName", "联系人%"));
        List<LinkMan> list1 = criteria2.list();
        System.out.println("like:" + list1);

        //排序
        Criteria criteria3 = session.createCriteria(LinkMan.class);
        criteria3.addOrder(Order.desc("linkId"));
        List list2 = criteria3.list();
        System.out.println("order:" + list2);

        //分页
        Criteria criteria4 = session.createCriteria(LinkMan.class);
        criteria4.setFirstResult(1);
        criteria4.setMaxResults(2);
        List list3 = criteria4.list();
        System.out.println("limit:" + list3);

        //聚合函数
        Criteria criteria5 = session.createCriteria(LinkMan.class);
        //获取记录数
        criteria5.setProjection(Projections.rowCount()); // count(*)
//        criteria5.setProjection(Projections.count("linkId")); //count (linkId)
        Object o = criteria5.uniqueResult();
        System.out.println("total:" + o);
    }

    //关联查询
    @Test
    public void testJoin(){
        //内连接 from Customer c inner join c.linkManSet
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        String hql = "from Customer c left join c.linkManSet ";
        Query query = session.createQuery(hql);
        //返回的是数组的形式
        List list = query.list();
        System.out.println(list);

        String hql2 = "from Customer c left join fetch c.linkManSet ";
        Query query1 = session.createQuery(hql2);
        List list1 = query1.list();
        //使用迫切内连接 返回是对象的形式
        System.out.println(list1);
    }

    /**
     * get方法 立即查询
     * load方法，使用对象时进行查询
     */
    @Test
    public void testQuery(){

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
//        Customer customer = (Customer) session.get(Customer.class,2);
        Customer customer = (Customer) session.load(Customer.class,2);

        System.out.println(customer.getCustName());
    }
}
