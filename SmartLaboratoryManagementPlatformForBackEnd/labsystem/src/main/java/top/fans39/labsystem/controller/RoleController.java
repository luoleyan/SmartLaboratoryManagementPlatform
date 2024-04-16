package top.fans39.labsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.labsystem.pojo.Roles;
import top.fans39.labsystem.service.RoleService;
import top.fans39.labsystem.utils.resultOBJ;


import java.util.List;

@RestController
@RequestMapping("adminapi/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Roles> getRoleList(){
        // 获取所有角色
        return roleService.getRoleList();
    }

    // 添加角色
    @PutMapping(value = "/{id}")
    public resultOBJ updateRightList(@PathVariable Integer id, @RequestBody Roles roles){
        try {
            roles.setId(id);
            roleService.updateRoleList(roles);
            return resultOBJ.UPDATE_ERROR;
        } catch (Exception e) {
            return resultOBJ.UPDATE_ERROR;
        }
    }

    // 删除角色
    @DeleteMapping(value = "{id}")
    public resultOBJ deleteRightList(@PathVariable Integer id){

        try {
            roleService.deleteRoleList(id);
            return resultOBJ.DELETE_SUCCESS;
        } catch (Exception e) {
            return resultOBJ.DELETE_ERROR;
        }
    }
}
