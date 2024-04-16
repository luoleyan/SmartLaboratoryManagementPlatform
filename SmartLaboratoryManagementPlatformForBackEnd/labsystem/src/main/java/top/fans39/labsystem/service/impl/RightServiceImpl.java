package top.fans39.labsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.labsystem.dao.RightDao;
import top.fans39.labsystem.pojo.Rights;
import top.fans39.labsystem.service.RightService;

import java.util.List;

@Service("RightService")
public class RightServiceImpl extends ServiceImpl<RightDao, Rights> implements RightService {
    @Autowired
    private RightDao rightDao;


    @Override
    public List<Rights> getRightList() {
        return rightDao.getRightList();
    }

    @Override
    public void updateRightList(Rights rights) {
        rightDao.updateRightList(rights);
    }

    @Override
    public void deleteRightList(Integer id) {
        rightDao.deleteById(id);
    }
}
