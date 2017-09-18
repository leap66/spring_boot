package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;
import com.leap.model.in.network.Response;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface IUserServer {

  /**
   * 删除用户
   */
  public void delete(String mobile) throws BaseException;

  /**
   * 更新用户
   *
   * @return Response
   */
  public Response update(User user) throws BaseException;

  /**
   * 查询用户-id
   *
   * @return Response
   */
  public Response get(String id) throws BaseException;

  /**
   * 查询所有用户
   *
   * @return Response
   */
  public Response query() throws BaseException;

  /**
   * 新增用户
   */
  public void save(User user) throws BaseException;

  /**
   * 修改用户手机号
   */
  public void mobileReset(String mobile, String oldMobile) throws BaseException;
}
