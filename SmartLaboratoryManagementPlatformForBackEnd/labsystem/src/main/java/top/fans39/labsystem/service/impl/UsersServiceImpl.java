package top.fans39.labsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.labsystem.dao.UsersDao;
import top.fans39.labsystem.pojo.Users;
import top.fans39.labsystem.service.UsersService;

import java.util.List;

@Service("UsersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Override
    public List<Users> getUserList(Users user) {
        return usersDao.getUserList(user);
    }

    @Override
    public void addUserList(Users users) {
        usersDao.addUserList(users);
    }

    @Override
    public void updateUserList(Users users) {
        usersDao.updateUserList(users);
    }

    @Override
    public void deleteUserList(Integer id) {
        usersDao.deleteById(id);
    }
}
