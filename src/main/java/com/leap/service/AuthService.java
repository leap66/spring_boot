package com.leap.service;

import com.leap.cmp.TokenMgr;
import com.leap.config.MarsConfig;
import com.leap.dao.AuthDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Auth;
import com.leap.model.User;
import com.leap.service.connect.IAuthServer;
import com.leap.service.connect.IRedisServer;
import com.leap.service.connect.IUserServer;
import com.leap.util.IsEmpty;
import com.leap.util.JwtUtil;
import com.leap.util.RedisUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@Service
public class AuthService implements IAuthServer {

  private final AuthDao authDao;
  private final IUserServer userService;
  private final IRedisServer redisServer;

  @Autowired
  public AuthService(IUserServer userService, AuthDao authDao, IRedisServer redisServer) {
    this.userService = userService;
    this.authDao = authDao;
    this.redisServer = redisServer;
  }

  @Transactional
  @Override
  public String register(Auth auth) {
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
    return temp1.getMobile();
  }

  @Transactional
  @Override
  public User login(String mobile, String password) {
    Auth temp = findByMobile(mobile);
    loginCheck(password, temp.getPassword());
    temp.setEnable(true);
    authDao.update(temp);
    String tokenKey = RedisUtil.key(temp.getId());
    String token = JwtUtil.created(temp.getId());
    TokenMgr.setToken(token);
    redisServer.set(tokenKey, token);
    redisServer.expire(tokenKey, MarsConfig.JWT_ttlMillis);
    return userService.get(temp.getId());
  }

  @Override
  public boolean sendSms(String mobile, boolean exist) {
    ValidUtil.validMobile(mobile);
    if (exist) {
      authDao.findByMobile(mobile);
    } else {
      authDao.findByMobileCheck(mobile);
    }
    // TODO
    return true;
  }

  @Override
  public boolean checkSms(String mobile, String code) {
    ValidUtil.validMobile(mobile);
    ValidUtil.valid(code, ExceptionEnum.DATA_EMPTY_CODE);
    // TODO
    return true;
  }

  @Override
  public boolean pwdReset(String mobile, String password, String code) {
    ValidUtil.validMobile(mobile);
    ValidUtil.valid(code, ExceptionEnum.DATA_EMPTY_CODE);
    Auth temp = authDao.findByMobile(mobile);
    temp.setPassword(password);
    temp.setVersion(temp.getVersion() + 1);
    temp.setEnd(new Date());
    authDao.update(temp);
    return true;
  }

  @Transactional
  @Override
  public String mobileReset(String mobile, String oldMobile, String code) {
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
    return temp1.getMobile();
  }

  @Transactional
  @Override
  public boolean logout(String id) {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY_ID);
    Auth temp = findById(id);
    temp.setEnable(false);
    authDao.update(temp);
    TokenMgr.clearToken();
    redisServer.del(RedisUtil.key(id));
    return true;
  }

  @Transactional
  @Override
  public boolean delete(String mobile) {
    Auth temp = findByMobile(mobile);
    temp.setHistory(
        (IsEmpty.string(temp.getHistory()) ? "" : temp.getHistory()) + temp.getMobile() + "&");
    temp.setMobile("11111111111");
    temp.setId("&" + temp.getId());
    temp.setNormal(false);
    authDao.delete(temp);
    userService.delete(mobile);
    return true;
  }

  @Override
  public Auth findById(String id) {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY_ID);
    return authDao.findById(id);
  }

  @Override
  public Auth findByMobile(String mobile) {
    ValidUtil.validMobile(mobile);
    return authDao.findByMobile(mobile);
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
}
