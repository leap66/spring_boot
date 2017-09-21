package com.leap.model.tuling;

import com.leap.model.baidu.BVoice;
import com.leap.model.out.base.BEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public class BChat extends BEntity {

  @Length(min = 1, max = 32, message = "询问信息最长为32位！")
  @NotBlank(message = "询问信息不能为空！")
  private String info;// 询问信息
  private String loc;// 地址
  @Length(min = 1, max = 36, message = "用户ID最长为32位！")
  @NotBlank(message = "用户ID不能为空！")
  private String userid;// 用户ID
  private Date time;// 时间
  private BVoice bUcn;

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

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public BVoice getbUcn() {
    return bUcn;
  }

  public void setbUcn(BVoice bUcn) {
    this.bUcn = bUcn;
  }
}
