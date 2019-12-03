package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 10:31
 */
public class UserInfo {
    private Integer userId;
    private String username;

    private Set<Role> roleSet = new HashSet<>();

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
