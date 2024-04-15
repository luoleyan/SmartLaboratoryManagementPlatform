package top.fans39.labsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.labsystem.dao.RoleDao;
import top.fans39.labsystem.pojo.Roles;
import top.fans39.labsystem.service.RoleService;

import java.util.List;

@Service("RoleService")
public class RoleSeviceImpl extends ServiceImpl<RoleDao, Roles> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    // 查询所有角色
    @Override
    public List<Roles> getRoleList() {
        return roleDao.selectList(null);
    }
    // 添加角色
    @Override
    public void updateRoleList(Roles roles) {
        roleDao.updateById(roles);
    }
    // 删除角色
    @Override
    public void deleteRoleList(Integer id) {
        roleDao.deleteById(id);
    }


}
