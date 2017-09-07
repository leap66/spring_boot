package com.leap.service;

import com.leap.dao.UserDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;
import com.leap.model.network.Response;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Service
public class UserService {

  private final UserDao userDao;

  @Autowired
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * 查询所有用户
   *
   * @return List
   */
  public Response query() throws BaseException {
    List<User> userList = userDao.query();
    return ResultUtil.success(userList, userList.size(), false);
  }

  /**
   * 查询用户
   *
   * @return List
   */
  public Response get(Integer tranId) throws BaseException {
    User user = userDao.get(tranId);
    return ResultUtil.success(user);
  }

  /**
   * 查询用户-id
   *
   * @return List
   */
  public Response get(String id) throws BaseException {
    User user = userDao.get(id);
    return ResultUtil.success(user);
  }

  /**
   * 新增用户
   *
   * @return List
   */
  public Response save(User user) throws BaseException {
    user.setId(UUID.randomUUID().toString());
    user.setCreated(new Date());
    user.setLastModified(new Date());
    user.setVersion(1);
    user = userDao.save(user);
    return ResultUtil.success(user);
  }

  /**
   * 更新用户
   *
   * @return List
   */
  public Response update(User user) throws BaseException {
    ValidUtil.valid(user.getId(), "ID参数不允许为空");
    User temp = userDao.get(user.getId());
    ValidUtil.validDB(user.getVersion(), temp.getVersion());
    user.setVersion(temp.getVersion() + 1);
    user.setCreated(temp.getCreated());
    user.setLastModified(new Date());
    user.setId(temp.getId());
    user.setTranId(temp.getTranId());
    user = userDao.update(user);
    return ResultUtil.success(user);
  }

  /**
   * 删除用户
   *
   * @return List
   */
  public Response delete(Integer id) throws BaseException {
    userDao.delete(id);
    return ResultUtil.success(null);
  }

  /**
   * 删除用户
   *
   * @return List
   */
  public Response delete(String id) throws BaseException {
    ValidUtil.valid(id, "ID参数不允许为空");
    userDao.delete(id);
    return ResultUtil.success(null);
  }
}
