package com.leap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
@Entity
@JsonIgnoreProperties(value = {
    "hibernateLazyInitializer", "handler" })
public class User implements Serializable {
  @Id
  @GeneratedValue
  private Integer tranId;// 自动生成序列号
  private String id; // 唯一标示ID
  @Length(min = 1, max = 32, message = "用户名最小为1位，最大为32位!")
  private String name;// 用户名
  @Length(max = 8, message = "昵称最大为8位!")
  private String shortName;// 昵称
  @Length(min = 11, max = 11, message = "手机号不正确!")
  private String mobile;// 手机号
  @Email(message = "邮箱输入不正确!")
  private String email;// 邮箱
  @Length(min = 10, max = 18, message = "身份证号不正确!")
  private String idCard;// 身份证号
  private boolean enabled;// 是否激活可以
  private Date birth;// 出生日期
  private Date created;// 用户创建时间
  private Date lastModified;// 用户最后修改时间
  private String remark;// 备注
  private Integer education;// 学历
  private String photoName;// 头像名称
  private String photoUrl;// 头像URL
  private long version;// 版本号

  public User() {
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getEducation() {
    return education;
  }

  public void setEducation(Integer education) {
    this.education = education;
  }

  public String getPhotoName() {
    return photoName;
  }

  public void setPhotoName(String photoName) {
    this.photoName = photoName;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }
}
