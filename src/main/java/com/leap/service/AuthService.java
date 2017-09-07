package com.leap.service;

import com.leap.dao.AuthDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Auth;
import com.leap.model.network.Response;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@Service
public class AuthService {

  private final AuthDao authDao;

  @Autowired
  public AuthService(AuthDao authDao) {
    this.authDao = authDao;
  }

  /**
   * 登陆
   *
   * @return Response
   */
  public Response login(String mobile, String password) throws BaseException {
    ValidUtil.validMobile(mobile);
    Auth temp = authDao.findByMobile(mobile);
    if (null == password) {
      if (null != temp.getPassword()) {
        throw new BaseException(ExceptionEnum.DAO_PASSWORD);
      }
    } else {
      if (!password.equals(temp.getPassword()))
        throw new BaseException(ExceptionEnum.DAO_PASSWORD);
    }
    temp.setEnable(true);
    authDao.update(temp);
    return ResultUtil.success(mobile);
  }

  /**
   * 注册
   *
   * @return Response
   */
  public Response register(Auth auth) throws BaseException {
    ValidUtil.validMobile(auth.getMobile());
    auth.setId(UUID.randomUUID().toString());
    auth.setMobile(auth.getMobile());
    auth.setPassword(auth.getPassword());
    auth.setEnable(true);
    Auth temp1 = authDao.save(auth);
    return ResultUtil.success(temp1.getMobile());
  }

  /**
   * 发送验证码
   *
   * @return Response
   */
  public Response sendSms(String mobile) throws BaseException {
    ValidUtil.validMobile(mobile);
    authDao.findByMobile(mobile);
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
    ValidUtil.valid(code, "验证码不允许为空");
    // TODO
    return ResultUtil.success(mobile);
  }

  /**
   * 重置密码
   *
   * @return Response
   */
  public Response pwdReset(String mobile, String password) throws BaseException {
    ValidUtil.validMobile(mobile);
    Auth temp = authDao.findByMobile(mobile);
    temp.setPassword(password);
    temp.setVersion(temp.getVersion() + 1);
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
    ValidUtil.validMobile(mobile);
    ValidUtil.valid(code, "验证码不允许为空");
    Auth temp = authDao.findByMobile(oldMobile);
    temp.setMobile(mobile);
    temp.setVersion(temp.getVersion() + 1);
    Auth temp1 = authDao.update(temp);
    return ResultUtil.success(temp1.getMobile());
  }

  /**
   * 通过ID查找
   *
   * @return Auth
   */
  public Auth findById(String id) throws BaseException {
    ValidUtil.valid(id, "ID参数不允许为空");
    return authDao.findById(id);
  }

  /**
   * 注销登陆
   *
   * @return Response
   */
  public Response logout(String id) throws BaseException {
    ValidUtil.valid(id, "ID参数不允许为空");
    Auth temp = authDao.findById(id);
    temp.setEnable(false);
    Auth temp1 = authDao.update(temp);
    return ResultUtil.success(temp1.getMobile());
  }
}
