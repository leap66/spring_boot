package com.leap.dao;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Trace;
import com.leap.repository.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
@Repository
public class TraceDao {

  private final TraceRepository traceRepository;

  @Autowired
  public TraceDao(TraceRepository traceRepository) {
    this.traceRepository = traceRepository;
  }

  /**
   * 保存
   */
  public void save(Trace trace) throws BaseException {
    traceRepository.save(trace);
  }

  /**
   * 获取
   * 
   * @return List<Trace>
   */
  public List<Trace> get(String traceId) throws BaseException {
    return traceRepository.findByTraceId(traceId);
  }
}
