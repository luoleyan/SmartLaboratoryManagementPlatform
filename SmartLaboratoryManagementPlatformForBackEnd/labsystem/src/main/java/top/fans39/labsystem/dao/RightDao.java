package top.fans39.labsystem.dao;

import com.github.yulichang.base.MPJBaseMapper;
import top.fans39.labsystem.pojo.Right;

import java.util.List;


public interface RightDao extends MPJBaseMapper<Right> {
    List<Right> getRightList();

    void updateRightList(Right right);
}
