package com.leap.repository;

import com.leap.model.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public interface DialogueRepository extends JpaRepository<Dialogue, Integer> {

  // 通过userId查找
  public List<Dialogue> findByUserIdAndNormal(String userId, Boolean normal);

  // 通过id查找
  public List<Dialogue> findById(String id);
}
