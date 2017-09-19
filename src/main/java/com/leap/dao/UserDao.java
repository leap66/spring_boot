package com.leap.dao;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.User;
import com.leap.repository.UserRepository;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Repository
public class UserDao {

  private final UserRepository userRepository;

  @Autowired
  public UserDao(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * 增
   */
  public void save(User user) throws BaseException {
    userRepository.save(user);
  }

  /**
   * 删
   */
  public void delete(User user) throws BaseException {
    userRepository.save(user);
  }

  /**
   * 改
   *
   * @return User
   */
  public User update(User user) throws BaseException {
    return userRepository.save(user);
  }

  /**
   * 查-ID
   *
   * @return User
   */
  public User get(String id) throws BaseException {
    List<User> user = userRepository.findById(id);
    ValidUtil.valid(user, true, ExceptionEnum.DAO_EMPTY);
    return user.get(0);
  }

  /**
   * 查-Mobile
   *
   * @return User
   */
  public User findByMobile(String mobile) throws BaseException {
    List<User> user = userRepository.findByMobile(mobile);
    ValidUtil.valid(user, true, ExceptionEnum.DAO_EMPTY);
    return user.get(0);
  }

  /**
   * 查询用户
   *
   * @return List
   */
  public List<User> query() throws BaseException {
    return userRepository.findByNormal(true);
  }
}
