package com.leap.model.enums;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public enum TuLingCode {

  ASK_NORMAL(0, "成功"), //
  ERROR_KEY(40001, "参数key错误"), //
  ERROR_INFO(40002, "请求内容info为空"), //
  ERROR_TIME(40004, "当天请求次数已使用完"), //
  ERROR_DATE(40007, "数据格式异常"), //
  TYPE_TEXT(100000, "文本类"), //
  TYPE_LINK(200000, "链接类"), //
  TYPE_NEWS(302000, "新闻类"), //
  TYPE_RECIPE(308000, "菜谱类"), // recipe
  TYPE_SING(313000, "儿歌类"), // （儿童版）
  TYPE_POETRY(314000, "诗词类"),// （儿童版） poetry
  ;

  private Integer code;
  private String message;

  TuLingCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public static TuLingCode getEnum(Integer param) {
    for (TuLingCode code : TuLingCode.values()) {
      if (code.code.equals(param)) {
        return code;
      }
    }
    return null;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
