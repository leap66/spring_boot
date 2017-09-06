package com.leap.handle.exception.user;

import com.leap.handle.exception.base.BaseException;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class UserException extends BaseException {

  public UserException(Integer code, String msg) {
    super(code, msg);
  }

}
