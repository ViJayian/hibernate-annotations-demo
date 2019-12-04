import annotation.Student;
import annotation.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;
import utils.HibernateAnnotationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-04 10:17
 */
public class TestAnnoatation {
    @Test
    public void test01(){
        Session session = HibernateAnnotationUtils.getCurrentSession();
    }

    @Test
    public void test02(){
        Session session = HibernateAnnotationUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Student student = new Student();
        student.setName("张三");
        session.save(student);
        tx.commit();
    }

    @Test
    public void test03(){
        Session currentSession = HibernateAnnotationUtils.getCurrentSession();
        Transaction tx = currentSession.getTransaction();
        tx.begin();
        Student student = (Student) currentSession.get(Student.class,1);
        student.setName("王五");
        tx.commit();
    }

    //注解方式 一对多 多对一双向关联
    //外键的维护权在student多的一方，这样操作外键的值是空的
    @Test
    public void test04(){
        Session session = HibernateAnnotationUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        Teacher teacher = new Teacher();
        teacher.setName("张老师");
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("小1");
        Student student1 = new Student();
        student1.setName("小2");
        list.add(student);
        list.add(student1);
        teacher.setStudentList(list);

        session.save(teacher);

        tx.commit();
    }

    //save 维护外键端的值，通过级联操作即可保存
    @Test
    public void test05(){
        Session session = HibernateAnnotationUtils.getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();

        Teacher teacher = new Teacher();
        teacher.setName("费介");
        Student student = new Student();
        student.setName("范闲");
        student.setTeacher(teacher);
        Student student1 = new Student();
        student1.setName("范思辙");
        student1.setTeacher(teacher);

        session.save(student);
        session.save(student1);

        tx.commit();
    }
}
