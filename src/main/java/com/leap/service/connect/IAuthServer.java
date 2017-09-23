package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Auth;
import com.leap.model.out.Response;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface IAuthServer {

  /**
   * 注册
   *
   * @return Response
   */
  public Response register(Auth auth) throws BaseException;

  /**
   * 登陆
   *
   * @return Response
   */
  public Response login(String mobile, String password) throws BaseException;

  /**
   * 发送验证码
   *
   * @return Response
   */
  public Response sendSms(String mobile, boolean exist) throws BaseException;

  /**
   * 校验验证码
   *
   * @return Response
   */
  public Response checkSms(String mobile, String code) throws BaseException;

  /**
   * 重置密码
   *
   * @return Response
   */
  public Response pwdReset(String mobile, String password, String code) throws BaseException;

  /**
   * 修改手机号
   *
   * @return Response
   */
  public Response mobileReset(String mobile, String oldMobile, String code) throws BaseException;

  /**
   * 注销登陆
   *
   * @return Response
   */
  public Response logout(String id) throws BaseException;

  /**
   * 删除
   */
  public Response delete(String mobile) throws BaseException;

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
