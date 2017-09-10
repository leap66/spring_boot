package com.leap.repository;

import com.leap.model.Trace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public interface TraceRepository extends JpaRepository<Trace, Integer> {

  // 通过id查找
  public List<Trace> findByTraceId(String trace);
}
