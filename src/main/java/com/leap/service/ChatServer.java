package com.leap.service;

import com.leap.config.MarsConfig;
import com.leap.dao.DialogueDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.model.Dialogue;
import com.leap.model.convert.DialogueConvert;
import com.leap.model.in.network.Response;
import com.leap.model.out.OutDialogue;
import com.leap.model.tuling.BChat;
import com.leap.service.connect.IChatServer;
import com.leap.util.IsEmpty;
import com.leap.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
@Service
public class ChatServer implements IChatServer {

  private final DialogueDao dialogueDao;

  @Autowired
  public ChatServer(DialogueDao dialogueDao) {
    this.dialogueDao = dialogueDao;
  }

  @Transactional
  @Override
  public Response chat(BChat chat) throws BaseException {
    String userId = chat.getUserid();
    chat.setKey(MarsConfig.TU_LING_KEY);
    chat.setTime(new Date());
    chat.setUserid(userId);
    Dialogue dialogue = dialogueDao.save(DialogueConvert.BChatToD(chat));
    // TODO
    chat.setUserid(userId.substring(0, 30));
    return ResultUtil.success(DialogueConvert.DialogueToA(dialogue));
  }

  @Override
  public Response query(String userId) throws BaseException {
    List<Dialogue> dialogueList = dialogueDao.query(userId, true);
    List<OutDialogue> outDialogueList = DialogueConvert.UserListToA(dialogueList);
    return ResultUtil.success(outDialogueList,
        IsEmpty.list(outDialogueList) ? 0 : outDialogueList.size(), true);
  }

  @Transactional
  @Override
  public Response delete(String id) throws BaseException {
    Dialogue dialogue = dialogueDao.get(id);
    dialogueDao.delete(dialogue);
    return ResultUtil.success(true);
  }
}
