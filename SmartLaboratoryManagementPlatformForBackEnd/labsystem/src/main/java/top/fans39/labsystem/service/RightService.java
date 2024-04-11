package top.fans39.labsystem.service;

import top.fans39.labsystem.pojo.Right;

import java.util.List;

public interface RightService {
    public List<Right> getRightList();

    public void updateRightList(Right right);
}
