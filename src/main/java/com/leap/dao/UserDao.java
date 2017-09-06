package com.leap.dao;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.User;
import com.leap.repository.UserRepository;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Service
public class UserDao {

  private final UserRepository userRepository;

  @Autowired
  public UserDao(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * 查询所有用户
   *
   * @return List
   */
  public List<User> query() throws BaseException {
    return userRepository.findAll();
  }

  /**
   * 查询用户
   *
   * @return User
   */
  public User get(Integer tranId) throws BaseException {
    User user = userRepository.getOne(tranId);
    ValidUtil.validDB(user);
    return user;
  }

  /**
   * 查询用户
   *
   * @return User
   */
  public User get(String id) throws BaseException {
    List<User> user = userRepository.findById(id);
    ValidUtil.validDB(user);
    return user.get(0);
  }

  /**
   * 新增用户
   *
   * @return User
   */
  public User save(User user) throws BaseException {
    return userRepository.save(user);
  }

  /**
   * 更新用户
   *
   * @return User
   */
  public User update(User user) throws BaseException {
    return userRepository.save(user);
  }

  /**
   * 删除用户
   */
  public void delete(Integer id) throws BaseException {
    User user = get(id);
    userRepository.delete(user.getTranId());
  }

  /**
   * 删除用户
   */
  public void delete(String id) throws BaseException {
    User user = get(id);
    userRepository.delete(user.getTranId());
  }
}
