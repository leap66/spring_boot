package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.in.network.Response;
import com.leap.model.tuling.BChat;

import java.io.IOException;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface IChatServer {

  /**
   * 聊天
   * 
   * @param chat
   *          询问信息
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response chat(BChat chat) throws IOException;

  /**
   * 聊天
   *
   * @param userId
   *          用户ID
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response query(String userId) throws BaseException;

  /**
   * 聊天
   *
   * @param id
   *          信息ID
   * @return Response
   * @throws BaseException
   *           参数错误
   */
  public Response delete(String id) throws BaseException;
}
