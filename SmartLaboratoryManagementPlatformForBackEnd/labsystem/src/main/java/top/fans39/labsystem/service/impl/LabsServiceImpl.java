package top.fans39.labsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fans39.labsystem.dao.LabsDao;
import top.fans39.labsystem.pojo.Labs;
import top.fans39.labsystem.service.LabsService;

import java.util.List;

@Service("LabsService")
public class LabsServiceImpl extends ServiceImpl<LabsDao, Labs> implements LabsService {
    @Autowired
    private LabsDao labsDao;

    @Override
    public List<Labs> getLabList(Labs labs) {
        return labsDao.selectList(null);
    }


    @Override
    public void addLabList(Labs labs) {
        labsDao.insert(labs);
    }
}
