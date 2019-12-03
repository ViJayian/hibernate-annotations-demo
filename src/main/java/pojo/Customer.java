package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 09:07
 */
public class Customer {
    private Integer cid;
    private String custName;
    private String mobile;
    //一对多
    private Set<LinkMan> linkManSet = new HashSet<>();

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
