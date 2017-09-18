package com.leap.service.connect;

import com.leap.handle.exception.base.BaseException;
import com.leap.model.Trace;

import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public interface ITraceServer {

  /**
   * 增
   */
  public void save(Trace trace) throws BaseException;

  /**
   * 查
   *
   * @return List<Trace>
   */
  public List<Trace> get(String traceId) throws BaseException;
}
