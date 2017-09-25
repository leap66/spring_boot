package com.leap.service;

import com.leap.dao.DialogueDao;
import com.leap.handle.exception.base.BaseException;
import com.leap.model.Dialogue;
import com.leap.model.baidu.BVoice;
import com.leap.model.convert.DialogueConvert;
import com.leap.model.tuling.BChat;
import com.leap.model.tuling.CChat;
import com.leap.network.HttpServlet;
import com.leap.service.connect.IChatServer;
import com.leap.util.GsonUtil;
import com.leap.util.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
  private final HttpServlet servlet;
  private final TtsServer ttsServer;

  @Autowired
  public ChatServer(DialogueDao dialogueDao, HttpServlet servlet, TtsServer ttsServer) {
    this.dialogueDao = dialogueDao;
    this.servlet = servlet;
    this.ttsServer = ttsServer;
  }

  @Override
  public Dialogue chat(BChat chat) throws IOException {
    chat.setTime(IsEmpty.object(chat.getTime()) ? new Date() : chat.getTime());
    dialogueDao.save(DialogueConvert.BChatToD(chat));
    if (!IsEmpty.object(chat.getVoice())) {
      BVoice voice = ttsServer.convertVop(chat.getVoice());
      chat.setInfo(voice.getInfo());
    }
    String result = servlet.tuLingQuest(chat);
    CChat cChat = GsonUtil.parse(result, CChat.class);
    cChat.setUserId(chat.getUserid());
    cChat.setTime(new Date());
    return dialogueDao.save(DialogueConvert.CChatToD(cChat));
  }

  @Override
  public List<Dialogue> query(String userId) throws BaseException {
    return dialogueDao.query(userId, true);
  }

  @Transactional
  @Override
  public boolean delete(String id) throws BaseException {
    Dialogue dialogue = dialogueDao.get(id);
    dialogueDao.delete(dialogue);
    return true;
  }
}
