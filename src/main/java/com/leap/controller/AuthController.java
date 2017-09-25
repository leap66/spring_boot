package com.leap.controller;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Auth;
import com.leap.model.User;
import com.leap.model.convert.UserConvert;
import com.leap.model.out.Response;
import com.leap.service.connect.IAuthServer;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@RestController
@RequestMapping(value = "/leap/app/auth")
public class AuthController {

  private final IAuthServer authService;

  @Autowired
  public AuthController(IAuthServer authService) {
    this.authService = authService;
  }

  /**
   * 注册
   *
   * @return mobile
   */
  @PostMapping(value = "/register")
  public Response register(@RequestBody @Valid Auth auth, BindingResult result)
      throws BaseException {
    ValidUtil.valid(result);
    return ResultUtil.success(authService.register(auth));
  }

  /**
   * 登陆
   *
   * @return User
   */
  @PostMapping(value = "/login")
  public Response login(@RequestParam("mobile") String mobile,
      @RequestParam("password") String password) throws BaseException {
    User user = authService.login(mobile, password);
    return ResultUtil.success(UserConvert.UserToA(user));
  }

  /**
   * 注销登陆
   *
   * @return boolean
   */
  @PostMapping(value = "/logout")
  public Response logout(@RequestParam("id") String id) throws BaseException {
    return ResultUtil.success(authService.logout(id));
  }

  /**
   * 发送验证码
   *
   * @return boolean
   */
  @PostMapping(value = "/sms/send")
  public Response sendSms(@RequestParam("mobile") String mobile,
      @RequestParam("exist") boolean exist) throws BaseException {
    return ResultUtil.success(authService.sendSms(mobile, exist));
  }

  /**
   * 校验验证码
   *
   * @return boolean
   */
  @PostMapping(value = "/sms/check")
  public Response checkSms(@RequestParam("mobile") String mobile, @RequestParam("code") String code)
      throws BaseException {
    return ResultUtil.success(authService.checkSms(mobile, code));
  }

  /**
   * 重置密码
   *
   * @return boolean
   */
  @PostMapping(value = "/reset/password")
  public Response pwdReset(@RequestParam("mobile") String mobile,
      @RequestParam("password") String password, @RequestParam("code") String code)
      throws BaseException {
    return ResultUtil.success(authService.pwdReset(mobile, password, code));
  }

  /**
   * 修改手机号
   *
   * @return mobile
   */
  @PostMapping(value = "/reset/mobile")
  public Response mobileReset(@RequestParam("mobile") String mobile,
      @RequestParam("oldMobile") String oldMobile, @RequestParam("code") String code)
      throws BaseException {
    return ResultUtil.success(authService.mobileReset(mobile, oldMobile, code));
  }

  /**
   * 删除账号
   *
   * @return boolean
   */
  @PostMapping(value = "/delete")
  public Response delete(@RequestParam("mobile") String mobile) throws BaseException {
    ValidUtil.valid(!MarsConfig.secret, ExceptionEnum.DAO_SECRET);
    return ResultUtil.success(authService.delete(mobile));
  }
}
