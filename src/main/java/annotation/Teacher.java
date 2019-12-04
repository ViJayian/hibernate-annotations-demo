package annotation;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-04 11:13
 */
@Entity
@Table(name = "t_teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tid;

    @Column
    private String name;

    /**
     * 一对多映射配置
     * mappedBy表示放弃维护权，交给Student方维护
     */
    @OneToMany(targetEntity = Student.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "teacher")
    private List<Student> studentList;

    @Column(insertable = false, updatable = false, columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP")
    private Date insertTime;

    //mysql设置自动插入时间和更新时间
    @Column(insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
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
}
