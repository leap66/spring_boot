package com.leap.service;

import com.leap.cmp.TokenMgr;
import com.leap.dao.AuthDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Auth;
import com.leap.model.User;
import com.leap.model.in.network.Response;
import com.leap.service.connect.IRedisServer;
import com.leap.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@Service
public class AuthService {

  private final AuthDao authDao;
  private final UserService userService;
  private final IRedisServer redisServer;

  @Autowired
  public AuthService(UserService userService, AuthDao authDao, IRedisServer redisServer) {
    this.userService = userService;
    this.authDao = authDao;
    this.redisServer = redisServer;
  }

  /**
   * 注册
   *
   * @return Response
   */
  public Response register(Auth auth) throws BaseException {
    ValidUtil.validMobile(auth.getMobile());
    auth.setId(UUID.randomUUID().toString());
    auth.setCreated(new Date());
    auth.setEnd(new Date());
    auth.setEnable(true);
    auth.setNormal(true);
    auth.setVersion(1);
    Auth temp1 = authDao.save(auth);
    User user = new User();
    user.setId(auth.getId());
    user.setMobile(auth.getMobile());
    userService.save(user);
    return ResultUtil.success(temp1.getMobile());
  }

  /**
   * 登陆
   *
   * @return Response
   */
  public Response login(String mobile, String password) throws BaseException {
    Auth temp = findByMobile(mobile);
    loginCheck(password, temp.getPassword());
    temp.setEnable(true);
    authDao.update(temp);
    String token = JwtUtil.created(temp.getId());
    TokenMgr.setToken(token);
    redisServer.set(RedisUtil.key(temp.getId()), token);
    return ResultUtil.success(mobile);
  }

  /**
   * 发送验证码
   *
   * @return Response
   */
  public Response sendSms(String mobile, boolean exist, HttpServletRequest request) throws BaseException {
    ValidUtil.validMobile(mobile);
    if (exist) {
      Auth auth = authDao.findByMobile(mobile);
      ValidUtil.validToken(request,redisServer.get(RedisUtil.key(auth.getId())));
    } else {
      authDao.findByMobileCheck(mobile);
    }
    // TODO
    return ResultUtil.success(mobile);
  }

  /**
   * 校验验证码
   *
   * @return Response
   */
  public Response checkSms(String mobile, String code) throws BaseException {
    ValidUtil.validMobile(mobile);
    authDao.findByMobile(mobile);
    ValidUtil.valid(code, ExceptionEnum.DATA_EMPTY_CODE);
    // TODO
    return ResultUtil.success(mobile);
  }

  /**
   * 重置密码
   *
   * @return Response
   */
  public Response pwdReset(String mobile, String password, String code) throws BaseException {
    ValidUtil.validMobile(mobile);
    ValidUtil.valid(code, ExceptionEnum.DATA_EMPTY_CODE);
    Auth temp = authDao.findByMobile(mobile);
    ValidUtil.validToken(temp.getId());
    temp.setPassword(password);
    temp.setVersion(temp.getVersion() + 1);
    temp.setEnd(new Date());
    Auth temp1 = authDao.update(temp);
    return ResultUtil.success(temp1.getMobile());
  }

  /**
   * 修改手机号
   *
   * @return Response
   */
  public Response mobileReset(String mobile, String oldMobile, String code) throws BaseException {
    ValidUtil.validMobile(mobile);
    ValidUtil.valid(code, ExceptionEnum.DATA_EMPTY_CODE);
    authDao.findByMobileCheck(mobile);
    Auth temp = findByMobile(oldMobile);
    temp.setEnd(new Date());
    temp.setHistory(
        (IsEmpty.string(temp.getHistory()) ? "" : temp.getHistory()) + temp.getMobile() + "&");
    temp.setMobile(mobile);
    temp.setVersion(temp.getVersion() + 1);
    Auth temp1 = authDao.update(temp);
    userService.mobileReset(mobile, oldMobile);
    return ResultUtil.success(temp1.getMobile());
  }

  /**
   * 注销登陆
   *
   * @return Response
   */
  public Response logout(String id) throws BaseException {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY_ID);
//    ValidUtil.validToken(JwtUtil.parse(redisServer.get(RedisUtil.key(id))));
    Auth temp = findById(id);
    temp.setEnable(false);
    temp.setEnd(new Date());
    Auth temp1 = authDao.update(temp);
    TokenMgr.clearToken();
    redisServer.del(RedisUtil.key(id));
    return ResultUtil.success(temp1.getMobile());
  }

  /**
   * 删除
   */
  public Response delete(String mobile) throws BaseException {
    Auth temp = findByMobile(mobile);
    temp.setHistory(
        (IsEmpty.string(temp.getHistory()) ? "" : temp.getHistory()) + temp.getMobile() + "&");
    temp.setMobile("11111111111");
    temp.setId("&" + temp.getId());
    temp.setNormal(false);
    authDao.delete(temp);
    userService.delete(mobile);
    return ResultUtil.success(true);
  }

  // 登陆密码验证
  private void loginCheck(String pwd, String password) {
    if (null == password) {
      if (null != pwd) {
        throw new BaseException(ExceptionEnum.DAO_PASSWORD);
      }
    } else {
      if (!password.equals(pwd))
        throw new BaseException(ExceptionEnum.DAO_PASSWORD);
    }
  }

  /**
   * 查-ID
   *
   * @return Auth
   */
  private Auth findById(String id) throws BaseException {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY_ID);
    return authDao.findById(id);
  }

  /**
   * 查-Mobile
   *
   * @return Auth
   */
  private Auth findByMobile(String mobile) throws BaseException {
    ValidUtil.validMobile(mobile);
    return authDao.findByMobile(mobile);
  }
}
