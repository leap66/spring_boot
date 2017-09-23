package com.leap.controller;

import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Auth;
import com.leap.model.out.Response;
import com.leap.model.out.base.BUcn;
import com.leap.service.connect.IAuthServer;
import com.leap.service.connect.IRedisServer;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@RestController
@RequestMapping(value = "/leap/app/auth")
public class AuthController {

  private final IAuthServer authService;
  private final IRedisServer redisServer;

  @Autowired
  public AuthController(IAuthServer authService, IRedisServer redisServer) {
    this.authService = authService;
    this.redisServer = redisServer;
  }

  /**
   * 测试
   *
   * @return Response
   */
  @PostMapping(value = "/test/{id}")
  @ResponseBody
  public Response test(@PathVariable(name = "id") String id) throws BaseException {
    List<BUcn> userList = new ArrayList<>();
    BUcn bUcn = new BUcn();
    bUcn.setId(UUID.randomUUID().toString());
    bUcn.setName("12好的" + id);
    userList.add(bUcn);
    userList.add(bUcn);
    redisServer.setList("user", userList);
    redisServer.expire("user", 100);
    return ResultUtil.success(redisServer.getList("user", BUcn.class));
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
  public Response sendSms(@RequestParam("mobile") String mobile,
      @RequestParam("exist") boolean exist) throws BaseException {
    return authService.sendSms(mobile, exist);
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
      @RequestParam("password") String password, @RequestParam("code") String code)
      throws BaseException {
    return authService.pwdReset(mobile, password, code);
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

  /**
   * 删除账号
   *
   * @return Response
   */
  @PostMapping(value = "/delete")
  public Response delete(@RequestParam("mobile") String mobile) throws BaseException {
    ValidUtil.valid(!MarsConfig.secret, ExceptionEnum.DAO_SECRET);
    return authService.delete(mobile);
  }
}
