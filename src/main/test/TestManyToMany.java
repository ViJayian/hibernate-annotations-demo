import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import pojo.Role;
import pojo.UserInfo;
import utils.HibernateUtils;

import java.util.Set;

/**
 * @Description: 多对多
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 10:48
 */
public class TestManyToMany {
   //用户中保存角色， 设置用户中cascade 为save-update
    @Test
    public void test01(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("张三");
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUsername("李四");
        Role role = new Role();
        role.setRoleName("java开发");
        Role role1 = new Role();
        role1.setRoleName("vue开发");
        Role role2 = new Role();
        role2.setRoleName("js开发");
        userInfo.getRoleSet().add(role);
        userInfo.getRoleSet().add(role1);
        userInfo.getRoleSet().add(role2);
        userInfo1.getRoleSet().add(role);
        userInfo1.getRoleSet().add(role1);

        session.save(userInfo);
        session.save(userInfo1);

        tx.commit();
    }

    //级联删除 这种方式不使用。。。
    @Test
    public void testCascadeDelete(){
        Session currentSession = HibernateUtils.getCurrentSession();
        Transaction tx = currentSession.getTransaction();
        tx.begin();
        UserInfo userInfo =
                (UserInfo) currentSession.get(UserInfo.class,1);
        currentSession.delete(userInfo);
        tx.commit();
    }

    //手工维护第三张表
    @Test
    public void testDelete2(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        UserInfo userInfo = (UserInfo) session.get(UserInfo.class, 3);
        Role role = (Role) session.get(Role.class,5);
        Set<Role> roleSet = userInfo.getRoleSet();
        roleSet.remove(role);
        tx.commit();
    }

}
