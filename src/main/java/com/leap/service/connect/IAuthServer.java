package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Auth;
import com.leap.model.User;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface IAuthServer {

  /**
   * 注册
   *
   * @return String
   */
  public String register(Auth auth) throws BaseException;

  /**
   * 登陆
   *
   * @return User
   */
  public User login(String mobile, String password) throws BaseException;

  /**
   * 注销登陆
   *
   * @return boolean
   */
  public boolean logout(String id) throws BaseException;

  /**
   * 发送验证码
   *
   * @return boolean
   */
  public boolean sendSms(String mobile, boolean exist) throws BaseException;

  /**
   * 校验验证码
   *
   * @return boolean
   */
  public boolean checkSms(String mobile, String code) throws BaseException;

  /**
   * 重置密码
   *
   * @return boolean
   */
  public boolean pwdReset(String mobile, String password, String code) throws BaseException;

  /**
   * 修改手机号
   *
   * @return Response
   */
  public String mobileReset(String mobile, String oldMobile, String code) throws BaseException;

  /**
   * 删除
   */
  public boolean delete(String mobile) throws BaseException;

  /**
   * 查-ID
   *
   * @return Auth
   */
  public Auth findById(String id) throws BaseException;

  /**
   * 查-Mobile
   *
   * @return Auth
   */
  public Auth findByMobile(String mobile) throws BaseException;
}
