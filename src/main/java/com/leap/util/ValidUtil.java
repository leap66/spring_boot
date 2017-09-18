package com.leap.util;

import com.leap.cmp.TokenMgr;
import com.leap.config.MarsConfig;
import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import org.springframework.validation.BindingResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
public class ValidUtil {

  public static void validToken(HttpServletRequest request, String token) throws BaseException {
    if (IsEmpty.string(token) || IsEmpty.object(request))
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    String requestToken = "";
    for (Cookie cookie : request.getCookies()) {
      if (MarsConfig.JWT_ID.equals(cookie.getName())) {
        requestToken = cookie.getValue();
        break;
      }
    }
    if (!JwtUtil.parse(token).equals(JwtUtil.parse(requestToken)))
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
  }

  public static void validToken(String userId) throws BaseException {
    if (IsEmpty.string(userId))
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
    if (userId.equals(JwtUtil.parse(TokenMgr.getToken())))
      throw new BaseException(ExceptionEnum.TOKEN_EXPIRE);
  }

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
