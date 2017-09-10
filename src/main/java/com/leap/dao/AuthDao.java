package com.leap.dao;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
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
   * 增-注册
   *
   * @return Auth
   */
  public Auth save(Auth auth) throws BaseException {
    findByMobileCheck(auth.getMobile());
    findByIDCheck(auth.getId());
    return authRepository.save(auth);
  }

  /**
   * 删-弃用
   */
  public void delete(Auth auth) throws BaseException {
    authRepository.save(auth);
  }

  /**
   * 改-更新
   *
   * @return Auth
   */
  public Auth update(Auth auth) throws BaseException {
    findById(auth.getId());
    return authRepository.save(auth);
  }

  /**
   * 查-Mobile
   *
   * @return Auth
   */
  public Auth findByMobile(String mobile) throws BaseException {
    List<Auth> authList = authRepository.findByMobile(mobile);
    ValidUtil.valid(authList, true, ExceptionEnum.DAO_MOBILE);
    ValidUtil.valid(authList.get(0).isNormal(), ExceptionEnum.DAO_NORMAL);
    return authList.get(0);
  }

  /**
   * 查-ID
   *
   * @return Auth
   */
  public Auth findById(String id) throws BaseException {
    List<Auth> authList = authRepository.findById(id);
    ValidUtil.valid(authList, true, ExceptionEnum.DAO_EMPTY);
    ValidUtil.valid(authList.get(0).isNormal(), ExceptionEnum.DAO_NORMAL);
    return authList.get(0);
  }

  /**
   * Check-Mobile
   */
  public void findByMobileCheck(String mobile) throws BaseException {
    List<Auth> authList = authRepository.findByMobile(mobile);
    ValidUtil.valid(authList, false, ExceptionEnum.DAO_MOBILE_EXIST);
  }

  /**
   * Check-ID
   */
  private void findByIDCheck(String id) throws BaseException {
    List<Auth> authList = authRepository.findById(id);
    ValidUtil.valid(authList, false, ExceptionEnum.DAO_ID_EXIST);
  }
}
