package com.leap.service;

import com.leap.dao.UserDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.model.Auth;
import com.leap.model.User;
import com.leap.model.in.network.Response;
import com.leap.util.ConvertUtil;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Service
public class UserService {

  private final UserDao userDao;
  private final AuthService authService;

  @Autowired
  public UserService(UserDao userDao, AuthService authService) {
    this.userDao = userDao;
    this.authService = authService;
  }

  /**
   * 查询所有用户
   *
   * @return List
   */
  public Response query() throws BaseException {
    List<User> userList = userDao.query();
    return ResultUtil.success(ConvertUtil.UserListToA(userList), userList.size(), false);
  }

  /**
   * 查询用户
   *
   * @return List
   */
  public Response get(Integer tranId) throws BaseException {
    User user = userDao.get(tranId);
    return ResultUtil.success(ConvertUtil.UserToA(user));
  }

  /**
   * 查询用户-id
   *
   * @return List
   */
  public Response get(String id) throws BaseException {
    User user = userDao.get(id);
    return ResultUtil.success(ConvertUtil.UserToA(user));
  }

  /**
   * 新增用户
   *
   * @return List
   */
  public Response save(User user) throws BaseException {
    Auth auth = authService.findByMobile(user.getMobile());
    user.setId(auth.getId());
    user.setCreated(new Date());
    user.setLastModified(new Date());
    user.setVersion(1);
    user.setEnabled(true);
    user.setNormal(true);
    user = userDao.save(user);
    return ResultUtil.success(ConvertUtil.UserToA(user));
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
    temp.setVersion(user.getVersion() + 1);
    temp.setLastModified(new Date());
    temp.setName(user.getName());
    temp.setShortName(user.getShortName());
    temp.setEmail(user.getEmail());
    temp.setIdCard(user.getIdCard());
    temp.setBirth(user.getBirth());
    temp.setRemark(user.getRemark());
    temp.setEducation(user.getEducation());
    temp.setPhotoName(user.getPhotoName());
    temp.setPhotoUrl(user.getPhotoUrl());
    user = userDao.update(temp);
    return ResultUtil.success(ConvertUtil.UserToA(user));
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
