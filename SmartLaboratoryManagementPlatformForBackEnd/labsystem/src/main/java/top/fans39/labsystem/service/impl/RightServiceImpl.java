package top.fans39.labsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.labsystem.dao.RightDao;
import top.fans39.labsystem.pojo.Right;
import top.fans39.labsystem.service.RightService;

import java.util.List;

@Service("RightService")
public class RightServiceImpl extends ServiceImpl<RightDao, Right> implements RightService {
    @Autowired
    private RightDao rightDao;


    @Override
    public List<Right> getRightList() {
        return rightDao.getRightList();
    }

    @Override
    public void updateRightList(Right right) {

    }
}
