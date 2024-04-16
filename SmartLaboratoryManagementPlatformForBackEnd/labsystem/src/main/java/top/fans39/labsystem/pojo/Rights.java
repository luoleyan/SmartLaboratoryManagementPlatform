package top.fans39.labsystem.pojo;

import lombok.Data;
import lombok.ToString;
import java.util.List;

@ToString
@Data
public class Rights {
    private Integer id;
    private String title;
    private String path;
    private String icon;
    private List<Rights> children;
}
