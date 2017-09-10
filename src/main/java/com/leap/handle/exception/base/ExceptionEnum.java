package com.leap.handle.exception.base;

import java.io.Serializable;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public enum ExceptionEnum implements Serializable {

  DATA_EMPTY(351, "参数验证空异常!"),
  //
  DATA_MOBILE(352, "手机号参数异常!"),
  //
  DATA_EMPTY_ID(353, "ID参数空异常!"),
  //
  DATA_EMPTY_CODE(354, "验证码参数空异常!"),
  //
  DATA_NORM(355, "表单验证规则异常!"),
  //
  DATA_REPEAT(356, "该数据正在被操作!"),

  //
  DAO_EMPTY(361, "数据库查询无此数据!"),
  //
  DAO_MOBILE(362, "该账户尚未注册!"),
  //
  DAO_PASSWORD(363, "手机号或密码错误!"),
  //
  DAO_MOBILE_EXIST(364, "该账户已注册!"),
  //
  DAO_ID_EXIST(365, "该数据已存在!"),
  //
  DAO_NORMAL(366, "数据库查询无此数据!"),
  //
  DAO_SECRET(367, "无操作权限!"),

  //
  TOKEN_EXPIRE(401, "Token 已过期!"),
  //
  REQUEST_PERMISSION(403, "无请求权限!"),
  //
  REQUEST_METHOD(405, "请求方式不正确!"),
  //
  REQUEST_PARAMEtER(421, "请求参数为空!"),
  //
  REQUEST_MEDIA_TYPE(422, "请求参数格式错误!"),;

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
