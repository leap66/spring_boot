package com.leap.controller;

import com.leap.handle.exception.base.BaseException;
import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Dialogue;
import com.leap.model.convert.DialogueConvert;
import com.leap.model.out.OutDialogue;
import com.leap.model.out.Response;
import com.leap.model.tuling.BChat;
import com.leap.service.connect.IChatServer;
import com.leap.util.IsEmpty;
import com.leap.util.ResultUtil;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
   * @return Dialogue
   */
  @PostMapping(value = "/chat")
  public Response chat(@RequestBody @Valid BChat chat, BindingResult result) throws IOException {
    ValidUtil.valid(result);
    OutDialogue dialogue = DialogueConvert.DialogueToA(chatServer.chat(chat));
    return ResultUtil.success(dialogue);
  }

  /**
   * 查询
   *
   * @return List<Dialogue>
   */
  @PostMapping(value = "/query")
  public Response query(@RequestParam("id") String id) throws BaseException {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY);
    List<Dialogue> dialogueList = chatServer.query(id);
    List<OutDialogue> temp = DialogueConvert.ListToA(dialogueList);
    if (IsEmpty.list(temp))
      temp = new ArrayList<>();
    return ResultUtil.success(temp, temp.size(), false);
  }

  /**
   * 删除
   *
   * @return Boolean
   */
  @PostMapping(value = "/delete")
  public Response delete(@RequestParam("id") String id) throws BaseException {
    ValidUtil.valid(id, ExceptionEnum.DATA_EMPTY);
    return ResultUtil.success(chatServer.delete(id));
  }
}
