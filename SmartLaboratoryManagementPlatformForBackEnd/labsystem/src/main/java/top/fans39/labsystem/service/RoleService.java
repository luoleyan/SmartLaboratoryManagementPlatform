package top.fans39.labsystem.service;

import top.fans39.labsystem.pojo.Roles;

import java.util.List;

public interface RoleService {
    // 获取所有角色
    public List<Roles> getRoleList();
    // 添加角色
    void updateRoleList(Roles roles);
    // 删除角色
    void deleteRoleList(Integer id);
}
