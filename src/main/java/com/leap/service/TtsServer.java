package com.leap.service;

import com.leap.config.MarsConfig;
import com.leap.dao.TtsDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.model.VoiceParam;
import com.leap.model.baidu.BVoice;
import com.leap.model.baidu.STts;
import com.leap.model.convert.VoiceParamConvert;
import com.leap.model.out.Response;
import com.leap.model.out.OutVoiceParam;
import com.leap.network.HttpServlet;
import com.leap.service.connect.ITtsServer;
import com.leap.util.GsonUtil;
import com.leap.util.IsEmpty;
import com.leap.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */
@Service
public class TtsServer implements ITtsServer {

  private final HttpServlet httpServlet;
  private final TtsDao ttsDao;
  private final RedisService redisService;

  @Autowired
  public TtsServer(HttpServlet httpServlet, TtsDao ttsDao, RedisService redisService) {
    this.httpServlet = httpServlet;
    this.ttsDao = ttsDao;
    this.redisService = redisService;
  }

  @Override
  public Response tts(BVoice voice) throws IOException, BaseException {
    return ResultUtil.success(convertTts(voice));
  }

  @Override
  public Response vop(BVoice voice) throws IOException, BaseException {
    return ResultUtil.success(convertVop(voice));
  }

  @Override
  public BVoice convertTts(BVoice voice) throws IOException, BaseException {
    voice.setAsk(true);
    VoiceParam param = convertGet(voice.getUserId());
    byte[] result = httpServlet.tts(param, getToken(), voice.getInfo());
    voice.setCode(result);
    voice.setLen(result.length);
    return voice;
  }

  @Override
  public BVoice convertVop(BVoice voice) throws IOException, BaseException {
    VoiceParam param = convertGet(voice.getUserId());
    String result = httpServlet.vop(param, getToken(), voice);
    STts tts = GsonUtil.parse(result, STts.class);
    if (tts.getErr_no().equals(0)) {
      voice.setInfo(tts.getResult()[0]);
      return voice;
    }
    throw new BaseException(tts.getErr_no(), tts.getErr_msg());
  }

  @Override
  public VoiceParam convertGet(String userId) throws BaseException {
    VoiceParam param = ttsDao.get(userId);
    if (!IsEmpty.object(param))
      return param;
    return getVoiceParam(userId);
  }

  @Override
  public Response edit(OutVoiceParam param) throws BaseException {
    VoiceParam temp = ttsDao.save(VoiceParamConvert.paramToB(param));
    return ResultUtil.success(VoiceParamConvert.paramToA(temp));
  }

  @Override
  public Response get(String userId) throws BaseException {
    return ResultUtil.success(VoiceParamConvert.paramToA(convertGet(userId)));
  }

  // 根据userId获取语音参数
  private VoiceParam getVoiceParam(String userId) {
    VoiceParam param = new VoiceParam();
    param.setId(UUID.randomUUID().toString());
    param.setUserId(userId);
    param.setRate("8000");
    param.setChannel("1");
    param.setLan("zh");
    param.setCtp("1");
    param.setSpd("5");
    param.setPit("5");
    param.setVol("5");
    param.setPer("0");
    return param;
  }

  // 获取百度请求token
  private String getToken() throws IOException {
    String token = MarsConfig.BAI_DU_TOKEN;
    if (IsEmpty.string(token)) {
      token = redisService.get(MarsConfig.REDIS_BAI_DU_TOKEN);
      MarsConfig.BAI_DU_TOKEN = token;
      if (IsEmpty.string(token)) {
        token = httpServlet.getToken();
        redisService.set(MarsConfig.REDIS_BAI_DU_TOKEN, token);
        redisService.expire(MarsConfig.REDIS_BAI_DU_TOKEN, MarsConfig.BAI_DU_ttlMillis);
      }
    }
    return token;
  }
}
