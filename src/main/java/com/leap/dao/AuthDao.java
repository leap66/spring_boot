package com.leap.dao;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Auth;
import com.leap.repository.AuthRepository;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@Repository
public class AuthDao {

  private final AuthRepository authRepository;

  @Autowired
  public AuthDao(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  /**
   * 注册
   *
   * @return Auth
   */
  public Auth save(Auth auth) throws BaseException {
    List<Auth> authList = authRepository.findByMobile(auth.getMobile());
    ValidUtil.validNoExist(authList);
    auth.setVersion(auth.getVersion() + 1);
    return authRepository.save(auth);
  }

  /**
   * 重置更新
   *
   * @return Auth
   */
  public Auth update(Auth auth) throws BaseException {
    return authRepository.save(auth);
  }

  /**
   * 通过手机号查找用户
   *
   * @return Auth
   */
  public Auth findByMobile(String mobile) throws BaseException {
    List<Auth> authList = authRepository.findByMobile(mobile);
    ValidUtil.validExist(authList);
    return authList.get(0);
  }

  /**
   * 通过ID查找用户
   *
   * @return Auth
   */
  public Auth findById(String id) throws BaseException {
    List<Auth> authList = authRepository.findById(id);
    ValidUtil.validExist(authList);
    return authList.get(0);
  }
}
