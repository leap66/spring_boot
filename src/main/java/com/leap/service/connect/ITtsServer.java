package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.VoiceParam;
import com.leap.model.baidu.BVoice;
import com.leap.model.out.OutVoiceParam;

import java.io.IOException;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface ITtsServer {

  /**
   * 百度翻译 文字转换为语音
   *
   * @param voice
   *          入参
   * @return BVoice
   * @throws BaseException
   *           参数错误
   */
  public BVoice tts(BVoice voice) throws IOException, BaseException;

  /**
   * 百度翻译 语音转换为文字
   *
   * @param voice
   *          入参
   * @return BVoice
   * @throws BaseException
   *           参数错误
   */
  public BVoice vop(BVoice voice) throws IOException, BaseException;

  /**
   * 百度 文字转换为语音
   *
   * @param voice
   *          入参
   * @return BVoice
   * @throws BaseException
   *           参数错误
   */
  public BVoice convertTts(BVoice voice) throws IOException, BaseException;

  /**
   * 百度 语音转换为文字
   *
   * @param voice
   *          入参
   * @return BVoice
   * @throws BaseException
   *           参数错误
   */
  public BVoice convertVop(BVoice voice) throws IOException, BaseException;

  /**
   * 百度 参数设置
   *
   * @param param
   *          入参
   * @return VoiceParam
   * @throws BaseException
   *           参数错误
   */
  public VoiceParam edit(OutVoiceParam param) throws BaseException;

  /**
   * 百度 参数获取
   *
   * @param userId
   *          用户ID
   * @return VoiceParam
   * @throws BaseException
   *           参数错误
   */
  public VoiceParam get(String userId) throws BaseException;

}
