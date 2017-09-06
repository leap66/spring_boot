package com.leap.util;

import org.springframework.validation.BindingResult;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
public class ValidUtil {

  // 表单验证异常
  public static void valid(BindingResult result) throws BaseException {
    if (result.hasErrors())
      throw new BaseException(ExceptionEnum.DATA_NORM.getCode(),
          result.getFieldError().getDefaultMessage());
  }

  // 数据库查询为空异常
  public static void validDB(Object object) throws BaseException {
    if (null == object)
      throw new BaseException(ExceptionEnum.DAO_EMPTY.getCode(), ExceptionEnum.DAO_EMPTY.getMsg());
    boolean temp = object instanceof List && ((List) object).size() == 0;
    if (temp)
      throw new BaseException(ExceptionEnum.DAO_EMPTY.getCode(), ExceptionEnum.DAO_EMPTY.getMsg());
  }

  // 数据库版本验证异常
  public static void validDB(long temp, long version) throws BaseException {
    if (version != temp)
      throw new BaseException(ExceptionEnum.DATA_REPEAT);
  }
}
