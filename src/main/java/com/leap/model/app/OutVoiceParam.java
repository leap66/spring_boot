package com.leap.model.app;

import com.leap.model.app.base.BEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */
public class OutVoiceParam extends BEntity {

  @NotBlank(message = "用户ID不允许为空")
  private String userId;// 用户标识符
  @NotBlank(message = "采样率不允许为空")
  private String rate;// 采样率，支持 8000 或者 16000
  @NotBlank(message = "语速不允许为空")
  private String spd;// 语速，取值0-9，默认为5中语速
  @NotBlank(message = "音调不允许为空")
  private String pit;// 音调，取值0-9，默认为5中语调
  @NotBlank(message = "音量不允许为空")
  private String vol;// 音量，取值0-15，默认为5中音量
  @NotBlank(message = "per不允许为空")
  private String per;// 发音人选择, 0为普通女声，1为普通男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声

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
