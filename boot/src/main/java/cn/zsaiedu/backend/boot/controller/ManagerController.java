package cn.zsaiedu.backend.boot.controller;

import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api/manager")
@Api(tags = "平台管理", hidden = false)
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @PostMapping("/token")
    @ApiOperation(value = "获取token", notes = "不需要传入参数")

    public TokenVo getToken() {
        TokenVo tokenVo = managerService.getToken();
        return tokenVo;
    }

}
