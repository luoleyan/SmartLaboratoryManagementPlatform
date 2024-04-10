package top.fans39.labsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.fans39.labsystem.dao")
public class LabsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabsystemApplication.class, args);
    }

}
