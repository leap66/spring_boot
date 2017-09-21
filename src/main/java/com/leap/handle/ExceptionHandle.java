package com.leap.handle;

import com.leap.util.ServletUtil;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.in.network.Response;
import com.leap.util.LogUtil;
import com.leap.util.ResultUtil;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@ControllerAdvice
public class ExceptionHandle {

  private LogUtil logger = new LogUtil(getClass().getName());

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Response handle(Exception e) {
    logger.error("Exception = {}", e);
    ExceptionEnum exceptionEnum;
    if (e instanceof BaseException) {
      BaseException exception = (BaseException) e;
      ServletUtil.getResponse().setStatus(
          (exception.getCode() > 500 || exception.getCode() < 100) ? 500 : exception.getCode());
      return ResultUtil.error(exception.getCode(), exception.getMsg(), e.toString(),
          e.getStackTrace()[0].toString());
    } else if (e instanceof HttpRequestMethodNotSupportedException) {
      exceptionEnum = ExceptionEnum.REQUEST_METHOD;
    } else if (e instanceof HttpMediaTypeNotSupportedException) {
      exceptionEnum = ExceptionEnum.REQUEST_MEDIA_TYPE;
    } else if (e instanceof MissingServletRequestParameterException) {
      exceptionEnum = ExceptionEnum.REQUEST_PARAMEtER;
    } else if (e instanceof InvalidDataAccessApiUsageException) {
      exceptionEnum = ExceptionEnum.DATA_EMPTY;
    } else {
      ServletUtil.getResponse().setStatus(500);
      return ResultUtil.error(500, "未知错误", "请根据tran_id查询日志详情", e.toString(),
          e.getStackTrace()[0].toString());
    }
    ServletUtil.getResponse().setStatus(exceptionEnum.getCode());
    return ResultUtil.error(exceptionEnum.getCode(), exceptionEnum.getMsg(), e.toString(),
        e.getStackTrace()[0].toString());
  }
}
