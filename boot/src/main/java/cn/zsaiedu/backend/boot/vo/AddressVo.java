package cn.zsaiedu.backend.boot.vo;

import lombok.Data;

@Data
public class AddressVo extends BasicVo{

    private String addr;

    private String time;

    public AddressVo(){
        super();
    }

}
