package top.fans39.labsystem.service;

import top.fans39.labsystem.pojo.Users;

import java.util.List;

public interface UsersService {
    public List<Users> getUserList(Users users);

    void addUserList(Users users);

    void updateUserList(Users users);

    void deleteUserList(Integer id);
}
