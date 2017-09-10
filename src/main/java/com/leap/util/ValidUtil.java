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

  // 参数非空验证异常
  public static void valid(Object object, ExceptionEnum exception) throws BaseException {
    if (null == object || "".equals(object))
      throw new BaseException(exception);
  }

  // 查询为空异常
  public static void valid(List list, boolean exist, ExceptionEnum exception) throws BaseException {
    if (exist) {
      if (null == list || list.size() == 0)
        throw new BaseException(exception);
    } else {
      if (null == list)
        return;
      if (list.size() != 0)
        throw new BaseException(exception);
    }
  }

  // boolean 验证
  public static void valid(boolean temp, ExceptionEnum exception) throws BaseException {
    if (!temp)
      throw new BaseException(exception);
  }

  // 手机号验证异常
  public static void validMobile(String mobile) throws BaseException {
    if (!StringUtil.isMobileNO(mobile))
      throw new BaseException(ExceptionEnum.DATA_MOBILE);
  }

  // 数据库版本验证异常
  public static void validDB(long temp, long version) throws BaseException {
    if (version != temp)
      throw new BaseException(ExceptionEnum.DATA_REPEAT);
  }
}
