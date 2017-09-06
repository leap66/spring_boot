package com.leap.model.network;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class Response<T> implements Serializable {
  private Integer code;
  private boolean success;
  private Map<String, String> fields;
  private List<String> message;
  private T data;
  private int total;
  private boolean more;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public boolean isMore() {
    return more;
  }

  public void setMore(boolean more) {
    this.more = more;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Map<String, String> getFields() {
    return fields;
  }

  public void setFields(Map<String, String> fields) {
    this.fields = fields;
  }

  public List<String> getMessage() {
    return message;
  }

  public void setMessage(List<String> message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
