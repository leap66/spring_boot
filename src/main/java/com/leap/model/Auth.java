package com.leap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@Entity
@JsonIgnoreProperties(value = {
    "hibernateLazyInitializer", "handler" })
public class Auth {
  @Id
  @GeneratedValue
  private Integer tranId;// 流水号
  private String id;// 唯一标识符
  @Length(min = 11, max = 11, message = "手机号不正确！")
  @NotBlank(message = "手机号不能为空!")
  private String mobile;// 手机号
  @Length(max = 16, message = "密码最长为16位！")
  @NotBlank(message = "密码不能为空!")
  private String password;// 密码
  @Length(min = 6, max = 6, message = "验证码不正确！")
  private String code;// 验证码
  private boolean enable;// 激活
  private long version;// 版本号
  @Length(max = 256, message = "remark最长为256位！")
  private String remark;// 备注
  private String history;
  private Date created;
  private Date end;
  private boolean normal;// 正常账户,非解绑账户

  public Auth() {
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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  public boolean isNormal() {
    return normal;
  }

  public void setNormal(boolean normal) {
    this.normal = normal;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }
}
