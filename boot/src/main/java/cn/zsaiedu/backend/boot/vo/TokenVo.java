package cn.zsaiedu.backend.boot.vo;

import lombok.Data;

@Data
public class TokenVo extends BasicVo{

    public TokenVo(){
        super();
    }

    private String token;
}
