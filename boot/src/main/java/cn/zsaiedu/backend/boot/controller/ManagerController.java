package cn.zsaiedu.backend.boot.controller;

import cn.zsaiedu.backend.boot.bo.*;
import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.service.UserService;
import cn.zsaiedu.backend.boot.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/manager")
@Api(tags = "平台管理")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Resource
    private ManagerService managerService;

    @Resource
    private UserService userService;

    @PostMapping("/token")
    @ApiOperation(value = "获取token")
    public TokenVo getToken() {
        TokenVo tokenVo = managerService.getToken();
        return tokenVo;
    }

    @PostMapping("/address")
    @ApiOperation(value = "获取地址")
    public AddressVo getAddress(@Valid @RequestBody AddressBo addressBo) {
        //TODO 检查userToken
        AddressVo addressVo = managerService.getAddress(addressBo.getPhone(), addressBo.getApplyProfession(), addressBo.getUserToken());
        return addressVo;
    }

    @PostMapping("/exam/location")
    @ApiOperation(value = "获取考点", notes = "获取考点")
    public ExamLocationVo examLocation(@Valid @RequestBody ExamLocationDataBo examLocationDataBo) {
        //TODO 检查userToken
        ExamLocationVo examLocationVo = managerService.getLocation(examLocationDataBo.getExamLocationList(), examLocationDataBo.getUserToken());
        return examLocationVo;
    }

    @PostMapping("/exam/cost")
    @ApiOperation(value = "获取收费信息", notes = "获取收费")
    public ExamCostVo examCost(@Valid @RequestBody CostQueryDataBo costQueryDataBo) {
        //TODO 检查userToken
        ExamCostVo examCostVo = managerService.getCost(costQueryDataBo.getCostBoList(), costQueryDataBo.getApplyProfession(),costQueryDataBo.getUserToken());
        return examCostVo;
    }

    @PostMapping("/synchronization/progress")
    @ApiOperation(value = "学习记录同步", notes = "学习记录同步")
    public ExamCostVo synchronizationProgress(@Valid @RequestBody CostQueryDataBo costQueryDataBo) {
        //TODO 检查userToken
        ExamCostVo examCostVo = managerService.getCost(costQueryDataBo.getCostBoList(), costQueryDataBo.getApplyProfession(),costQueryDataBo.getUserToken());
        return examCostVo;
    }

    @PostMapping("/sync/progress")
    @ApiOperation(value = "学习记录同步", notes = "学习记录同步")
    public BasicVo syncProgress(@Valid @RequestBody CourseProgressBo courseProgressBo) {
        //TODO 检查userToken
        BasicVo basicVo = managerService.syncProgress(courseProgressBo.getCourseInfo(), courseProgressBo.getPhone(),courseProgressBo.getApplyProfession(),courseProgressBo.getUserToken());
        return basicVo;
    }

    @PostMapping("/sync/user")
    @ApiOperation(value = "同步用户信息", notes = "同步用户信息")
    public BasicVo syncUsers(@Valid @RequestBody UserInfoBo1 userInfoBo) {
        //TODO 检查userToken
        BasicVo basicVo = managerService.syncUser(userInfoBo.getUserInfos(), userInfoBo.getUserToken());
        return basicVo;
    }

    @PostMapping("/save/user")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    public UserInfoVo saveUsers(@Valid @RequestBody UserInfoBo userInfoBo) {
        //TODO 检查userToken
        UserInfoVo userInfoVo = managerService.saveUser(userInfoBo);
        return userInfoVo;
    }

}
