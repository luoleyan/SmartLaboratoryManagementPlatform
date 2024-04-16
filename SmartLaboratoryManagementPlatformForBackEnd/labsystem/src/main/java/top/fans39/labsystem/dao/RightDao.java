package top.fans39.labsystem.dao;

import com.github.yulichang.base.MPJBaseMapper;
import top.fans39.labsystem.pojo.Rights;

import java.util.List;


public interface RightDao extends MPJBaseMapper<Rights> {
    List<Rights> getRightList();

    void updateRightList(Rights rights);
}
