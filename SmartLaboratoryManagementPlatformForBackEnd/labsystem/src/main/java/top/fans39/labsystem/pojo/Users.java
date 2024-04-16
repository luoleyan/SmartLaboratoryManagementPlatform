package top.fans39.labsystem.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Users {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private Integer isDefault;
    private Roles roles;
}
