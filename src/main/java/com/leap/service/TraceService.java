package com.leap.service;

import com.leap.dao.TraceDao;
import com.leap.model.Trace;
import com.leap.service.connect.ITraceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/8
 * @description :
 */
@Service
public class TraceService implements ITraceServer{

  private final TraceDao traceDao;

  @Autowired
  public TraceService(TraceDao traceDao) {
    this.traceDao = traceDao;
  }

  @Override
  public void save(Trace trace) {
    traceDao.save(trace);
  }

  @Override
  public List<Trace> get(String traceId) {
    return traceDao.get(traceId);
  }
}
