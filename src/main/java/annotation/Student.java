package annotation;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-04 09:45
 */
//类级注解
@Entity
//表名 默认和实体类名称相同
@Table(name = "t_student")
public class Student {

    //属性级别注解 可以使用在字段上或者get方法上
    @Id
    /**
     * 主键生成策略，auto 根据数据库底层选择，若支持自增长，则为自增长，类似于native
     */
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "gen")
    /**
     * @GenericGenerator指定一个Hibernate主键生成器,再使用@GenerateValue的属性generator来引用即可。
     */
    @GenericGenerator(name = "gen", strategy = "increment")
    private Integer sid;

    @Column
    //表示该字段映射到数据库中，默认注解
    @Basic
    private String name;

    //不映射到数据库中
    @Transient
    private String unColumn;

    //多的一方负责维护关系
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //外键
    @JoinColumn(name = "tid")
    private Teacher teacher;

    //是否出现在insert和update语句中
    @Column(insertable = false, updatable = false, columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date insertTime;

    //mysql设置自动插入时间和更新时间
    @Column(insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnColumn() {
        return unColumn;
    }

    public void setUnColumn(String unColumn) {
        this.unColumn = unColumn;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
