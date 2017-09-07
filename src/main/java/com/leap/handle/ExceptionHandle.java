package com.leap.handle;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.network.Response;
import com.leap.util.LogUtil;
import com.leap.util.ResultUtil;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@ControllerAdvice
public class ExceptionHandle {

  LogUtil logger = new LogUtil(getClass().getName());

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Response handle(Exception e) {
    logger.error("Exception = {}", e);
    if (e instanceof BaseException) {
      BaseException exception = (BaseException) e;
      return ResultUtil.error(exception.getCode(), e.getMessage());
    } else if (e instanceof HttpRequestMethodNotSupportedException) {
      return ResultUtil.error(ExceptionEnum.REQUEST_METHOD.getCode(),
          ExceptionEnum.REQUEST_METHOD.getMsg(), e.getMessage());
    } else if (e instanceof HttpMediaTypeNotSupportedException) {
      return ResultUtil.error(ExceptionEnum.REQUEST_MEDIA_TYPE.getCode(),
          ExceptionEnum.REQUEST_MEDIA_TYPE.getMsg(), e.getMessage());
    } else if (e instanceof MissingServletRequestParameterException) {
      return ResultUtil.error(ExceptionEnum.REQUEST_PARAMEtER.getCode(),
          ExceptionEnum.REQUEST_PARAMEtER.getMsg(), e.getMessage());
    } else if (e instanceof InvalidDataAccessApiUsageException) {
      return ResultUtil.error(ExceptionEnum.DATA_EMPTY.getCode(), ExceptionEnum.DATA_EMPTY.getMsg(),
          e.getMessage());
    } else {
      return ResultUtil.error(500, "未知错误", "请根据tran_id查询日志详情", e.getMessage());
    }
  }
}
