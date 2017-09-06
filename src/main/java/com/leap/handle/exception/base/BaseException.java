package com.leap.handle.exception.base;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class BaseException extends RuntimeException {
  private Integer code;
  private String msg;

  public BaseException(Integer code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  public BaseException(ExceptionEnum exceptionEnum) {
    super(exceptionEnum.getMsg());
    this.code = exceptionEnum.getCode();
    this.msg = exceptionEnum.getMsg();
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
