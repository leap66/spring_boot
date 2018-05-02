package com.leap.model.convert;

import com.leap.model.VoiceParam;
import com.leap.model.app.OutVoiceParam;
import com.leap.util.IsEmpty;

import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */
public class VoiceParamConvert {

  // OutVoiceParam To VoiceParam
  public static VoiceParam paramToB(OutVoiceParam param) {
    VoiceParam temp = new VoiceParam();
    temp.setId(IsEmpty.string(param.getId()) ? UUID.randomUUID().toString() : param.getId());
    temp.setUserId(param.getUserId());
    temp.setRate(param.getRate());
    temp.setSpd(param.getSpd());
    temp.setPit(param.getPit());
    temp.setVol(param.getVol());
    temp.setPer(param.getPer());
    // 固定参数
    temp.setChannel("1");
    temp.setLan("zh");
    temp.setCtp("1");
    return temp;
  }

  // VoiceParam To OutVoiceParam
  public static OutVoiceParam paramToA(VoiceParam param) {
    OutVoiceParam temp = new OutVoiceParam();
    temp.setId(param.getId());
    temp.setUserId(param.getUserId());
    temp.setRate(param.getRate());
    temp.setSpd(param.getSpd());
    temp.setPit(param.getPit());
    temp.setVol(param.getVol());
    temp.setPer(param.getPer());
    return temp;
  }
}
