package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Auth;
import com.leap.model.in.network.Response;
import com.leap.service.AuthService;
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
@RequestMapping(value = "/auth")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  /**
   * 登陆
   *
   * @return Response
   */
  @PostMapping(value = "/login")
  public Response login(@RequestParam("mobile") String mobile,
      @RequestParam("password") String password) throws BaseException {
    return authService.login(mobile, password);
  }

  /**
   * 注册
   *
   * @return Response
   */
  @PostMapping(value = "/register")
  public Response register(@RequestBody @Valid Auth auth, BindingResult result)
      throws BaseException {
    ValidUtil.valid(result);
    return authService.register(auth);
  }

  /**
   * 注销登陆
   *
   * @return Response
   */
  @PostMapping(value = "/logout")
  public Response logout(@RequestParam("id") String id) throws BaseException {
    return authService.logout(id);
  }

  /**
   * 发送验证码
   *
   * @return Response
   */
  @PostMapping(value = "/sms/send")
  public Response sendSms(@RequestParam("mobile") String mobile) throws BaseException {
    return authService.sendSms(mobile);
  }

  /**
   * 校验验证码
   *
   * @return Response
   */
  @PostMapping(value = "/sms/check")
  public Response checkSms(@RequestParam("mobile") String mobile, @RequestParam("code") String code)
      throws BaseException {
    return authService.checkSms(mobile, code);
  }

  /**
   * 重置密码
   *
   * @return Response
   */
  @PostMapping(value = "/reset/password")
  public Response pwdReset(@RequestParam("mobile") String mobile,
      @RequestParam("password") String password) throws BaseException {
    return authService.pwdReset(mobile, password);
  }

  /**
   * 修改手机号
   *
   * @return Response
   */
  @PostMapping(value = "/reset/mobile")
  public Response mobileReset(@RequestParam("mobile") String mobile,
      @RequestParam("oldMobile") String oldMobile, @RequestParam("code") String code)
      throws BaseException {
    return authService.mobileReset(mobile, oldMobile, code);
  }
}
