package top.fans39.labsystem;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.fans39.labsystem.dao.RightDao;
import top.fans39.labsystem.pojo.Labs;
import top.fans39.labsystem.pojo.Users;
import top.fans39.labsystem.service.LabsService;
import top.fans39.labsystem.service.RightService;
import top.fans39.labsystem.service.RoleService;
import top.fans39.labsystem.service.UsersService;

@SpringBootTest
@MapperScan("top.fans39.labsystem.dao")
class LabsystemApplicationTests {
    @Autowired
    public RightService rightService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public UsersService usersService;

    @Autowired
    public LabsService labsService;
    @Test
    void contextLoads() {
        Users users = new Users();
        users.setUsername("admin");
        users.setPassword("123");
        Labs labs = new Labs();
        labs.setTitle("luoleyan");
        labs.setLabType(114514);
        labs.setCapacity(1919810);
        labs.setLabType(19);
        labs.setX(520);
        labs.setY(521);
        labsService.addLabList(labs);
        System.out.println(labsService.getLabList(labs));
//        System.out.println(rightService.getRightList());
//        System.out.println(roleService.getRoleList());
//        System.out.println(usersService.getUserList(users));
    }

}
