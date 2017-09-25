package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Dialogue;
import com.leap.model.tuling.BChat;

import java.io.IOException;
import java.util.List;

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
   * @return Dialogue
   * @throws BaseException
   *           参数错误
   */
  public Dialogue chat(BChat chat) throws IOException;

  /**
   * 聊天
   *
   * @param userId
   *          用户ID
   * @return List<Dialogue>
   * @throws BaseException
   *           参数错误
   */
  public List<Dialogue> query(String userId) throws BaseException;

  /**
   * 聊天
   *
   * @param id
   *          信息ID
   * @return boolean
   * @throws BaseException
   *           参数错误
   */
  public boolean delete(String id) throws BaseException;
}
