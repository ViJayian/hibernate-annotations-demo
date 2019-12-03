package pojo;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 09:07
 */
public class LinkMan {
    private Integer linkId;
    private String linkName;

    //一对一
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "linkId=" + linkId +
                ", linkName='" + linkName + '\'' +
                ", customer=" + customer +
                '}';
    }
}

