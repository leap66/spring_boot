package com.leap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */

@Entity
@JsonIgnoreProperties(value = {
    "hibernateLazyInitializer", "handler" })
public class VoiceParam {

  @Id
  @GeneratedValue
  private Integer tranId;// 流水号
  private String id;// 唯一标识符
  private String userId;// 用户标识符
  private String rate;// 采样率，支持 8000 或者 16000
  private String channel;// 声道数，仅支持单声道，请填写固定值 1
  private String lan;// 语种选择，默认中文（zh）。 中文=zh、粤语=ct、英文=en，不区分大小写。
  private String ctp;// 客户端类型选择，web端填写固定值1
  private String spd;// 语速，取值0-9，默认为5中语速
  private String pit;// 音调，取值0-9，默认为5中语调
  private String vol;// 音量，取值0-15，默认为5中音量
  private String per;// 发音人选择, 0为普通女声，1为普通男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声

  public VoiceParam() {
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

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getLan() {
    return lan;
  }

  public void setLan(String lan) {
    this.lan = lan;
  }

  public String getCtp() {
    return ctp;
  }

  public void setCtp(String ctp) {
    this.ctp = ctp;
  }

  public String getSpd() {
    return spd;
  }

  public void setSpd(String spd) {
    this.spd = spd;
  }

  public String getPit() {
    return pit;
  }

  public void setPit(String pit) {
    this.pit = pit;
  }

  public String getVol() {
    return vol;
  }

  public void setVol(String vol) {
    this.vol = vol;
  }

  public String getPer() {
    return per;
  }

  public void setPer(String per) {
    this.per = per;
  }
}
