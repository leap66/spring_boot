package com.leap.dao;

import com.leap.handle.exception.base.ExceptionEnum;
import com.leap.model.Dialogue;
import com.leap.repository.DialogueRepository;
import com.leap.util.ValidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/19
 * @description :
 */
@Repository
public class DialogueDao {

  private final DialogueRepository dialogueRepository;

  @Autowired
  public DialogueDao(DialogueRepository dialogueRepository) {
    this.dialogueRepository = dialogueRepository;
  }

  /**
   * 增
   */
  public Dialogue save(Dialogue dialogue) {
    return dialogueRepository.save(dialogue);
  }

  /**
   * 删
   */
  public void delete(Dialogue dialogue) {
    dialogueRepository.delete(dialogue);
  }

  /**
   * 查-ID
   */
  public Dialogue get(String id) {
    List<Dialogue> dialogueList = dialogueRepository.findById(id);
    ValidUtil.valid(dialogueList, true, ExceptionEnum.DAO_EMPTY);
    return dialogueList.get(0);
  }

  /**
   * 查-全部
   */
  public List<Dialogue> query(String userId, boolean normal) {
    return dialogueRepository.findByUserIdAndNormal(userId, normal);
  }
}
