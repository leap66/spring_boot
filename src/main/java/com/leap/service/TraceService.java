package com.leap.service;

import com.leap.dao.TraceDao;
import com.leap.model.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/8
 * @description :
 */
@Service
public class TraceService {

  private final TraceDao traceDao;

  @Autowired
  public TraceService(TraceDao traceDao) {
    this.traceDao = traceDao;
  }

  /**
   * 增
   */
  public void save(Trace trace) {
    traceDao.save(trace);
  }

  /**
   * 查
   *
   * @return List<Trace>
   */
  public List<Trace> get(String traceId) {
    return traceDao.get(traceId);
  }
}
