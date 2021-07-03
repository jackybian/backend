package cn.zsaiedu.backend.boot.vo;

import lombok.Data;

@Data
public class BasicVo {
    private int status;

    private String errorMessage;

    private String message;

    public BasicVo(){
        this.status = 200;
        this.errorMessage = "";
        this.message = "";
    }

    public BasicVo(int status, String errorMessage, String message) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.message = message;
    }


}
