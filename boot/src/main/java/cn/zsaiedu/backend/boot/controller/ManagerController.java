package cn.zsaiedu.backend.boot.controller;

import cn.zsaiedu.backend.boot.bo.AddressBo;
import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.vo.AddressVo;
import cn.zsaiedu.backend.boot.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


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

    @PostMapping("/address")
    @ApiOperation(value = "获取地址", notes = "不需要传入参数")
    public AddressVo getAddress(@Valid @RequestBody AddressBo addressBo) {
        //TODO 检查userToken
        AddressVo addressVo = managerService.getAddress(addressBo.getPhone(), addressBo.getApplyProfession(), addressBo.getUserToken());
        return addressVo;
    }
}
