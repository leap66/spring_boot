package com.leap.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : ylwei
 * @time : 2017/9/12
 * @description :
 */
public class Token implements Serializable {
  private Integer tranId; // 序列号
  private String key; // key
  private String value; // value

  public Token() {
  }

  public Token(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public Integer getTranId() {
    return tranId;
  }

  public void setTranId(Integer tranId) {
    this.tranId = tranId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
