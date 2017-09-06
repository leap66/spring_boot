package com.leap.handle.exception.base;

import java.io.Serializable;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public enum ExceptionEnum implements Serializable {
  DATA_EMPTY(361, "表单验证空异常"),
  //
  DATA_NORM(362, "表单验证规则异常"),
  //
  DATA_REPEAT(363, "该数据正在被操作"),

  //
  DAO_EMPTY(360, "数据库查询无此数据"),

  //
  TOKEN_EXPIRE(401, "Token 已过期"),
  //
  REQUEST_PERMISSION(403, "无请求权限"),
  //
  REQUEST_METHOD(405, "请求方式不正确"),;

  private Integer code;
  private String msg;

  ExceptionEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
