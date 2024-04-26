package top.fans39.labsystem.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Labs {
    private Integer id;
    private String title;
    private Integer capacity;
    private Integer labType;
    private Integer collegeType;
    private Integer x;
    private Integer y;
}
