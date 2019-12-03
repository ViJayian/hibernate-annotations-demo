package pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author: wangwenjie
 * @CreateTime: 2019-12-03 10:31
 */
public class Role {
    private Integer roleId;
    private String roleName;

    //多对多
    private Set<UserInfo> userInfoSet = new HashSet<>();

    public Set<UserInfo> getUserInfoSet() {
        return userInfoSet;
    }

    public void setUserInfoSet(Set<UserInfo> userInfoSet) {
        this.userInfoSet = userInfoSet;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
