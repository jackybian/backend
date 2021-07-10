package cn.zsaiedu.backend.boot.controller;

import cn.zsaiedu.backend.boot.bo.*;
import cn.zsaiedu.backend.boot.entity.User;
import cn.zsaiedu.backend.boot.service.ManagerService;
import cn.zsaiedu.backend.boot.service.UserService;
import cn.zsaiedu.backend.boot.util.HuaweiUtil;
import cn.zsaiedu.backend.boot.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/manager")
@Api(tags = "平台管理")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Resource
    private ManagerService managerService;

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/exam/address")
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
        ExamLocationVo examLocationVo = managerService.getLocation(examLocationDataBo.getExamLocationList(), "");
        return examLocationVo;
    }

    @PostMapping("/exam/cost")
    @ApiOperation(value = "获取收费信息", notes = "获取收费")
    public ExamCostVo examCost(@Valid @RequestBody CostQueryDataBo costQueryDataBo) {
        //TODO 检查userToken
        Cost cost = new Cost();
        cost.setPhone(costQueryDataBo.getPhone());
        List<Cost> costList = new ArrayList<>();
        costList.add(cost);
        ExamCostVo examCostVo = managerService.getCost(costList, costQueryDataBo.getApplyProfession(), "");
        return examCostVo;
    }

    @PostMapping("/sync/progress")
    @ApiOperation(value = "学习记录同步", notes = "学习记录同步")
    public BasicVo syncProgress(@Valid @RequestBody CourseProgressBo courseProgressBo) {
        //TODO 检查userToken
        BasicVo basicVo = managerService.syncProgress(courseProgressBo.getCourseInfo(), courseProgressBo.getPhone(), courseProgressBo.getApplyProfession(), "");
        return basicVo;
    }


    @PostMapping("/user/save")
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    public UserInfoVo saveUsers(@Valid @RequestBody UserInfoBo userInfoBo) {
        //TODO 检查userToken
        UserInfoVo userInfoVo = managerService.saveUser(userInfoBo);
        if (null != userInfoVo && 200 == userInfoVo.getStatus() && userInfoVo.getId() > 0) {
            SyncBo syncBo = new SyncBo();
            syncBo.setId(userInfoVo.getId());
            UserInfoVo userInfoSync = managerService.syncUser(syncBo);
            userInfoSync.setId(userInfoVo.getId());
            return userInfoSync;
        }
        return userInfoVo;
    }

    @PostMapping("/user/sync")
    @ApiOperation(value = "同步用户信息", notes = "同步用户信息")
    public UserInfoVo syncUsers(@Valid @RequestBody SyncBo syncBo) {
        //TODO 检查userToken
        UserInfoVo userInfoVo = managerService.syncUser(syncBo);
        return userInfoVo;
    }

    @PostMapping("/user/query")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    public UserQueryPageVo queryUsers(@RequestBody(required = false) UserQueryBo userQueryBo) {
        //TODO 检查userToken
        if (null == userQueryBo) {
            userQueryBo = new UserQueryBo();
        }
        Page page = PageHelper.startPage(userQueryBo.getPageNum().intValue(), userQueryBo.getPageSize().intValue());
        List<User> userList = managerService.queryUserByConditions(userQueryBo.getIdCard(),
                userQueryBo.getPhone(), page);
        UserQueryPageVo userQueryPageVo = new UserQueryPageVo(page, userList);
        return userQueryPageVo;
    }

    @PostMapping("/user/delete/{id}")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    public BasicVo deleteUser(@PathVariable("id") Long id) {
        //TODO 检查userToken
        int result = userService.deleteUserById(id);
        BasicVo basicVo = new BasicVo();
        return basicVo;
    }

    @PostMapping("/user/update")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    public BasicVo updateUser(@Valid @RequestBody UserInfoBo userInfoBo) {
        //TODO 检查userToken
        int result = userService.updateUserById(userInfoBo);
        BasicVo basicVo = new BasicVo();
        return basicVo;
    }

    @PostMapping("/user/upload/idcard")
    @ApiOperation(value = "用户上传身份证", notes = "用户上传身份证")
    public BasicVo uploadIdCard(@RequestParam MultipartFile file, @RequestParam String fileName) {
        //TODO 检查userToken
        boolean result = HuaweiUtil.upload2Obs(file, fileName);
        BasicVo basicVo = new BasicVo();
        if (!result) {
            basicVo.setStatus(500);
            basicVo.setErrorMessage("上传图片失败");
            basicVo.setMessage("fail");
        } else {
            basicVo.setMessage("success");
        }
        return basicVo;
    }

}
