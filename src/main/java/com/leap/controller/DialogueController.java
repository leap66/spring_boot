package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.out.Response;
import com.leap.model.tuling.BChat;
import com.leap.service.connect.IChatServer;
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
@RequestMapping(value = "/leap/app/chat")
public class DialogueController {

  private final IChatServer chatServer;

  @Autowired
  public DialogueController(IChatServer chatServer) {
    this.chatServer = chatServer;
  }

  /**
   * 聊天
   *
   * @return Response
   */
  @PostMapping(value = "/chat")
  public Response chat(@RequestBody @Valid BChat chat, BindingResult result) throws IOException {
    ValidUtil.valid(result);
    return chatServer.chat(chat);
  }

  /**
   * 查询
   *
   * @return Response
   */
  @PostMapping(value = "/query")
  public Response query(@RequestParam("id") String id) throws BaseException {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY);
    return chatServer.query(id);
  }

  /**
   * 删除
   *
   * @return Response
   */
  @PostMapping(value = "/delete")
  public Response delete(@RequestParam("id") String id) throws BaseException {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY);
    return chatServer.delete(id);
  }
}
