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
   * 注册
   *
   * @return Auth
   */
  public Auth save(Auth auth) throws BaseException {
    return authRepository.save(auth);
  }

  /**
   * 重置更新
   *
   * @return Auth
   */
  public Auth update(Auth auth) throws BaseException {
    return save(auth);
  }

  /**
   * 通过手机号查找用户
   *
   * @return Auth
   */
  public Auth findByMobile(String mobile) throws BaseException {
    List<Auth> authList = authRepository.findByMobile(mobile);
    ValidUtil.validExist(authList);
    ValidUtil.validNormal(authList.get(0).isNormal());
    return authList.get(0);
  }

  /**
   * 校验新用户，若已存在，则抛出异常 364
   */
  public void findByMobileCheck(String mobile) throws BaseException {
    List<Auth> authList = authRepository.findByMobile(mobile);
    if (null == authList)
      return;
    if (authList.size() != 0)
      throw new BaseException(ExceptionEnum.DAO_MOBILE_EXIST);
  }

  /**
   * 通过ID查找用户
   *
   * @return Auth
   */
  public Auth findById(String id) throws BaseException {
    List<Auth> authList = authRepository.findById(id);
    ValidUtil.validExist(authList);
    ValidUtil.validNormal(authList.get(0).isNormal());
    return authList.get(0);
  }
}
