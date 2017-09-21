package com.leap.model.enums;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */
public enum TtsCode {

  SUCCESS(0, "请求成功"), //
  ERROR_UN_KNOW(400, "未知错误"), //
  ERROR_NO_SUPPORT(500, "不支持输入"), //
  ERROR_PARAM(501, "输入参数不对"), //
  ERROR_TOKEN(502, "token验证失败"), //
  ERROR_SERVER(503, "合成后端错误"), //
  ERROR_INPUT(3300, "输入参数不正确"), // 用户输入错误
  ERROR_MASS(3301, "音频质量过差"), // 用户输入错误
  ERROR_KEY(3302, "鉴权失败"), // 用户输入错误
  ERROR_SERVICE(3303, "语音服务器后端问题"), // 用户输入错误
  ERROR_QPS(3304, "用户的请求QPS超限"), // 用户请求超限
  ERROR_PV(3305, "用户的日pv（日请求量）超限"), // 用户请求超限
  ERROR_DISCERN(3307, "语音服务器后端识别出错问题"), // 服务端问题
  ERROR_TIME(3308, "音频过长"), // 用户输入错误
  ERROR_DATA(3309, "音频数据问题"), // 用户输入错误
  ERROR_TIME_BIG(3310, "输入的音频文件过大"), // 用户输入错误
  ERROR_RATE(3311, "采样率rate参数不在选项里"), // 用户输入错误
  ERROR_FORMAT(3312, "音频格式format参数不在选项里"),// 用户输入错误
  ;
  private Integer code;
  private String message;

  TtsCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public static TtsCode getEnum(Integer param) {
    for (TtsCode code : TtsCode.values()) {
      if (param.equals(code.getCode()))
        return code;
    }
    return ERROR_UN_KNOW;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
