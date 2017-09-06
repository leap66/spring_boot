package com.leap.handle;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.network.Response;
import com.leap.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@ControllerAdvice
public class ExceptionHandle {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public Response handle(Exception e) {
    logger.info("Exception = {}", e);
    if (e instanceof BaseException) {
      BaseException exception = (BaseException) e;
      return ResultUtil.error(exception.getCode(), e.getMessage());
    } else if (e instanceof HttpRequestMethodNotSupportedException) {
      return ResultUtil.error(ExceptionEnum.REQUEST_METHOD.getCode(),
          ExceptionEnum.REQUEST_METHOD.getMsg(), e.getMessage());
    } else if (e instanceof InvalidDataAccessApiUsageException) {
      return ResultUtil.error(ExceptionEnum.DATA_EMPTY.getCode(), ExceptionEnum.DATA_EMPTY.getMsg(),
          e.getMessage());
    } else {
      return ResultUtil.error(360, "未知错误", "请根据tran_id查询日志详情", e.getMessage());
    }
  }
}
