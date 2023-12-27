package com.zhangz.demo.spring.cloud.product.controller;

import com.zhangz.demo.spring.cloud.product.dto.wx.WxAuthDTO;
import com.zhangz.demo.spring.cloud.product.service.UserInfoService;
import com.zhangz.demo.spring.cloud.product.vo.user.UserDetailVO;
import com.zhangz.demo.spring.cloud.product.vo.user.UserInfoVO;
import com.zhangz.spring.cloud.common.api.CommonResult;
import com.zhangz.spring.cloud.common.exception.BussinessException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户服务")
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    /**
     *  
     * @param code 
     * @param referrer
     * @return
     */
    @ApiOperation(value = "微信授权", notes = "微信授权")
    @PostMapping("/wxapp/authorize")
    @ResponseBody
    public CommonResult wxappAuthorize(@RequestParam("code") String code, @RequestParam("referrer") String referrer) throws Exception {
        log.info("wxappAuthorize params  code:{},referrer:{}", code, referrer);
        WxAuthDTO wxAuthDTO = userInfoService.wxappAuthorize(code);
        return CommonResult.success(wxAuthDTO);
    }

    /**
     * 手机号注册
     * @param phone
     * @param userName
     * @return
     */
    @ApiOperation(value = "手机号注册", notes = "手机号注册")
    @PostMapping("/register/phone")
    @ResponseBody
    public CommonResult registerByPhone(String phone, String userName, String password, String password2) {
        log.info("registerByPhone params  phone:{},userName:{}", phone, userName);

        return CommonResult.success();
    }

    /**
     * 手机号登录
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation(value = "手机号密码登录", notes = "手机号密码登录")
    @PostMapping("/login/phone")
    @ResponseBody
    public CommonResult<UserInfoVO> loginByPhone(String phone, String password) throws BussinessException {
        log.info("loginByPhone params  phone:{},password:{}", phone, password);
        UserInfoVO userInfoVO = userInfoService.loginByPhone(phone, password);
        return CommonResult.success(userInfoVO);
    }

    /**
     * 校验token
     * @return
     */
    @ApiOperation(value = "校验token", notes = "校验token")
    @GetMapping("/check-token")
    @ResponseBody
    public CommonResult checkToken(String token) throws BussinessException {
        log.info("loginByPhone params  token:{}", token);
        boolean b = userInfoService.checkToken(token);
        return b ? CommonResult.success(b) : CommonResult.failed("登录失效");
    }

    /**
    * 用户详情
    * @return
    */
    @ApiOperation(value = "用户详情", notes = "用户详情")
    @GetMapping("/detail")
    @ResponseBody
    public CommonResult detail() throws BussinessException {

        UserDetailVO userDetailVO = userInfoService.detail();
        return CommonResult.success(userDetailVO);
    }
 

    /**
     * 修改昵称
     * @return
     */
    @ApiOperation(value = "修改昵称", notes = "修改昵称")
    @PostMapping("/modify")
    @ResponseBody
    public CommonResult modify(String nick) throws BussinessException {

         userInfoService.modifyNice(nick);
        return CommonResult.success();
    }
    
}
