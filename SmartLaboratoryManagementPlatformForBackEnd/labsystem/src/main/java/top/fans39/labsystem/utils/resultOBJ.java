package top.fans39.labsystem.utils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class resultOBJ {
    private Integer code;
    private String msg;
    private Object data;

    public resultOBJ (Integer code, String msg, Object data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public resultOBJ (Integer code, String msg) {

        this.code = code;
        this.msg = msg;
    }


    public resultOBJ (Integer code) {
        this.code = code;
    }

    public static final resultOBJ ADD_SUCCESS = new resultOBJ( SYSConstant.CODE_SUCCESS, SYSConstant.ADD_SUCCESS );
    public static final resultOBJ ADD_ERROR = new resultOBJ( SYSConstant.CODE_ERROR, SYSConstant.ADD_ERROR );

    public static final resultOBJ UPDATE_SUCCESS = new resultOBJ( SYSConstant.CODE_SUCCESS, SYSConstant.UPDATE_SUCCESS );
    public static final resultOBJ UPDATE_ERROR = new resultOBJ( SYSConstant.CODE_ERROR, SYSConstant.UPDATE_ERROR );

    public static final resultOBJ DELETE_SUCCESS = new resultOBJ( SYSConstant.CODE_SUCCESS, SYSConstant.DELETE_SUCCESS );
    public static final resultOBJ DELETE_ERROR = new resultOBJ( SYSConstant.CODE_ERROR, SYSConstant.DELETE_ERROR );

    public static final resultOBJ LOGIN_SUCCESS = new resultOBJ( SYSConstant.CODE_SUCCESS, SYSConstant.LOGIN_SUCCESS );
    public static final resultOBJ LOGIN_ERROR = new resultOBJ( SYSConstant.CODE_ERROR, SYSConstant.LOGIN_ERROR );

}
