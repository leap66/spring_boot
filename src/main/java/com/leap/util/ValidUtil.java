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

  // 非空验证异常
  public static void valid(Object object, String remark) throws BaseException {
    if (null == object || object.equals(""))
      throw new BaseException(ExceptionEnum.DATA_EMPTY.getCode(), remark);
  }

  // 非空验证异常
  public static void valid(Object object) throws BaseException {
    valid(object, ExceptionEnum.DATA_EMPTY.getMsg());
  }

  // 表单验证异常
  public static void valid(BindingResult result) throws BaseException {
    if (result.hasErrors())
      throw new BaseException(ExceptionEnum.DATA_NORM.getCode(),
          result.getFieldError().getDefaultMessage());
  }

  // 手机号验证异常
  public static void validMobile(String mobile) throws BaseException {
    if (!StringUtil.isMobileNO(mobile))
      throw new BaseException(ExceptionEnum.DATA_MOBILE);
  }

  // 数据库查询为空异常
  public static void validDB(Object object) throws BaseException {
    if (null == object)
      throw new BaseException(ExceptionEnum.DAO_EMPTY);
    boolean temp = object instanceof List && ((List) object).size() == 0;
    if (temp)
      throw new BaseException(ExceptionEnum.DAO_EMPTY);
  }

  // 数据库版本验证异常
  public static void validDB(long temp, long version) throws BaseException {
    if (version != temp)
      throw new BaseException(ExceptionEnum.DATA_REPEAT);
  }

  // 认证查询为空异常
  public static void validExist(Object object) throws BaseException {
    if (null == object)
      throw new BaseException(ExceptionEnum.DAO_MOBILE);
    boolean temp = object instanceof List && ((List) object).size() == 0;
    if (temp)
      throw new BaseException(ExceptionEnum.DAO_MOBILE);
  }

  // 数据弃用验证
  public static void validNormal(boolean normal) throws BaseException {
    if (!normal)
      throw new BaseException(ExceptionEnum.DAO_NORMAL);
  }

  // 敏感信息需要操作权限
  public static void validSecret(boolean secret) throws BaseException {
    if (secret)
      throw new BaseException(ExceptionEnum.DAO_SECRET);
  }
}
