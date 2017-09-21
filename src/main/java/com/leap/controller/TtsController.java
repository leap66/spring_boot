package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.baidu.BVoice;
import com.leap.model.in.network.Response;
import com.leap.model.out.OutVoiceParam;
import com.leap.service.connect.ITtsServer;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
@RestController
@RequestMapping(value = "/leap/app/tts")
public class TtsController {

  private final ITtsServer ttsServer;

  @Autowired
  public TtsController(ITtsServer ttsServer) {
    this.ttsServer = ttsServer;
  }

  /**
   * 百度翻译 文字转换为语音
   *
   * @return Response
   */
  @PostMapping(value = "/tts")
  public Response chat(@RequestBody BVoice voice) throws IOException, BaseException {
    return ttsServer.tts(voice);
  }

  /**
   * 百度翻译 语音转换为文字
   *
   * @return Response
   */
  @PostMapping(value = "/vop")
  public Response vop(@RequestBody BVoice voice) throws IOException, BaseException {
    return ttsServer.vop(voice);
  }

  /**
   * 百度 参数设置
   *
   * @return Response
   */
  @PostMapping(value = "/param/edit")
  public Response edit(@RequestBody @Valid OutVoiceParam param, BindingResult result)
      throws BaseException {
    ValidUtil.valid(result);
    return ttsServer.edit(param);
  }

  /**
   * 百度 获取参数设置
   *
   * @return Response
   */
  @PostMapping(value = "/param/get")
  public Response edit(@RequestParam("id") String userId) throws BaseException {
    return ttsServer.get(userId);
  }
}
