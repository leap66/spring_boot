package com.leap.dao;

import com.leap.model.VoiceParam;
import com.leap.repository.TtsRepository;
import com.leap.util.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */

@Repository
public class TtsDao {

  private final TtsRepository ttsRepository;

  @Autowired
  public TtsDao(TtsRepository ttsRepository) {
    this.ttsRepository = ttsRepository;
  }

  /**
   * 增
   */
  public VoiceParam save(VoiceParam param) {
    return ttsRepository.save(param);
  }

  /**
   * 查-userID
   */
  public VoiceParam get(String userId) {
    List<VoiceParam> paramList = ttsRepository.findByUserId(userId);
    if (!IsEmpty.list(paramList))
      return paramList.get(0);
    return null;
  }

  /**
   * 查-ID
   */
  public VoiceParam getById(String id) {
    List<VoiceParam> paramList = ttsRepository.findById(id);
    if (!IsEmpty.list(paramList))
      return paramList.get(0);
    return null;
  }
}
