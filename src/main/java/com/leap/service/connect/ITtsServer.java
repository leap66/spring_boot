package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.VoiceParam;
import com.leap.model.baidu.BVoice;
import com.leap.model.in.network.Response;
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
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response tts(BVoice voice) throws IOException, BaseException;

  /**
   * 百度翻译 语音转换为文字
   *
   * @param voice
   *          入参
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response vop(BVoice voice) throws IOException, BaseException;

  /**
   * 百度 文字转换为语音
   *
   * @param voice
   *          入参
   * @return BUcn
   * @throws BaseException
   *           参数错误
   */
  public BVoice convertTts(BVoice voice) throws IOException, BaseException;

  /**
   * 百度 语音转换为文字
   *
   * @param voice
   *          入参
   * @return BUcn
   * @throws BaseException
   *           参数错误
   */
  public BVoice convertVop(BVoice voice) throws IOException, BaseException;

  /**
   * 百度 参数获取
   *
   * @param userId
   *          用户ID
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public VoiceParam convertGet(String userId) throws BaseException;

  /**
   * 百度 参数设置
   *
   * @param param
   *          入参
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response edit(OutVoiceParam param) throws BaseException;

  /**
   * 百度 参数获取
   *
   * @param userId
   *          用户ID
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response get(String userId) throws BaseException;

}
