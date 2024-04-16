package top.fans39.labsystem.service;

import top.fans39.labsystem.pojo.Rights;

import java.util.List;

public interface RightService {
    public List<Rights> getRightList();

    public void updateRightList(Rights rights);

    void deleteRightList(Integer id);
}
