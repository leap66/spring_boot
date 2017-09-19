package com.leap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
@Entity
@JsonIgnoreProperties(value = {
    "hibernateLazyInitializer", "handler" })
public class Dialogue implements Serializable{

  @Id
  @GeneratedValue
  private Integer tranId;// 流水号
  private String id;// 唯一标识符
  private String userId;// 用户标识符
  private boolean ask; // 判断是询问还是回答
  private String keyId;// 用户标识符
  private String info;// 询问信息
  private String loc;// 地址
  private Date time;// 时间
  private Integer code;// 标识符
  private String text;// 结果
  private String url;// 链接地址
  private String list;// list
  private boolean normal;// 是否正常显示

  public Dialogue() {
  }

  public Integer getTranId() {
    return tranId;
  }

  public void setTranId(Integer tranId) {
    this.tranId = tranId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public boolean isAsk() {
    return ask;
  }

  public void setAsk(boolean ask) {
    this.ask = ask;
  }

  public String getKey() {
    return keyId;
  }

  public void setKey(String key) {
    this.keyId = key;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getList() {
    return list;
  }

  public void setList(String list) {
    this.list = list;
  }

  public boolean isNormal() {
    return normal;
  }

  public void setNormal(boolean normal) {
    this.normal = normal;
  }
}
