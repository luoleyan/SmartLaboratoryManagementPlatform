package top.fans39.labsystem;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.fans39.labsystem.dao.RightDao;
import top.fans39.labsystem.service.RightService;
import top.fans39.labsystem.service.RoleService;

@SpringBootTest
@MapperScan("top.fans39.labsystem.dao")
class LabsystemApplicationTests {
    @Autowired
    public RightService rightService;

    @Autowired
    public RoleService roleService;
    @Test
    void contextLoads() {
        System.out.println(rightService.getRightList());
        System.out.println(roleService.getRoleList());
    }

}
