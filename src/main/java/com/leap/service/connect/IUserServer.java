package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface IUserServer {

  /**
   * 删除用户
   */
  public boolean delete(String mobile) throws BaseException;

  /**
   * 更新用户
   *
   * @return User
   */
  public User update(User user) throws BaseException;

  /**
   * 查询用户-id
   *
   * @return User
   */
  public User get(String id) throws BaseException;

  /**
   * 查询所有用户
   *
   * @return List<User>
   */
  public List<User> query() throws BaseException;

  /**
   * 新增用户
   */
  public void save(User user) throws BaseException;

  /**
   * 修改用户手机号
   */
  public void mobileReset(String mobile, String oldMobile) throws BaseException;
}
