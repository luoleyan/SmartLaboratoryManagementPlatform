package top.fans39.labsystem.dao;

import com.github.yulichang.base.MPJBaseMapper;
import top.fans39.labsystem.pojo.Users;

import java.util.List;

public interface UsersDao extends MPJBaseMapper<Users> {
    public List<Users> getUserList(Users users);
    public void addUserList(Users users);

    void updateUserList(Users users);
}
