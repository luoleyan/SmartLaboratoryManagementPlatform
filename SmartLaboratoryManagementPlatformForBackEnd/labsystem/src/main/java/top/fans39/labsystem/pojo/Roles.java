package top.fans39.labsystem.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Roles {
    private Integer id;
    private String roleName;
    private Integer roleType;
    private String rights;
}
