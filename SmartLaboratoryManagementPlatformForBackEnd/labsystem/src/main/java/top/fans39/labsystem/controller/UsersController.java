package top.fans39.labsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.fans39.labsystem.pojo.Users;
import top.fans39.labsystem.service.UsersService;
import top.fans39.labsystem.utils.SYSConstant;
import top.fans39.labsystem.utils.resultOBJ;

import java.util.List;

@RestController
@RequestMapping("/adminapi/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getUserList(Users user){
        // 查询所有用户
        return usersService.getUserList(user);
    }

    @PostMapping("/login")
    public resultOBJ login(@RequestBody Users user){
        try {
            List<Users> list = usersService.getUserList(user);
//            System.out.println(user);
            if (list.isEmpty()){
                throw new Exception("用户名或密码错误");
            }
            return new resultOBJ(SYSConstant.CODE_SUCCESS,SYSConstant.LOGIN_SUCCESS,list.get(0));
        } catch (Exception e) {
            return new resultOBJ(SYSConstant.CODE_ERROR,SYSConstant.LOGIN_ERROR);
        }
    }

    @PostMapping
    public resultOBJ addUserList(@RequestBody Users users){
        try {
            usersService.addUserList(users);
            return resultOBJ.ADD_SUCCESS;
        } catch (Exception e) {
            return resultOBJ.ADD_ERROR;
        }
    }

    @PutMapping(value = "/{id}")
    public resultOBJ updateUserList(@PathVariable("id") Integer id,@RequestBody Users users){

        try {
            users.setId(id);
            usersService.updateUserList(users);
            return resultOBJ.UPDATE_SUCCESS;
        } catch (Exception e) {
            return resultOBJ.UPDATE_ERROR;
        }
    }

    @DeleteMapping(value = "/{id}")
     public resultOBJ deleteUserList(@PathVariable("id") Integer id){
        try {
            usersService.deleteUserList(id);
            return resultOBJ.DELETE_SUCCESS;
        } catch (Exception e) {
            return resultOBJ.DELETE_ERROR;
        }
}
}
