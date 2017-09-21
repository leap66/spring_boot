package com.leap.repository;

import com.leap.model.VoiceParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public interface TtsRepository extends JpaRepository<VoiceParam, Integer> {

  // 通过mobile查找
  public List<VoiceParam> findByUserId(String mobile);

  // 通过id查找
  public List<VoiceParam> findById(String id);
}
