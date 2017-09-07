package com.leap.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : ylwei
 * @time : 2017/9/6
 * @description :
 */
public class LogUtil {

  private static Logger logger;

  public LogUtil(String name) {
    logger = LoggerFactory.getLogger(name);
  }

  public void info(String var1) {
    logger.info(var1);
  }

  public void info(String var1, Object... var2) {
    logger.info(var1, var2);
  }

  public void debug(String var1) {
    logger.debug(var1);
  }

  public void debug(String var1, Object... var2) {
    logger.debug(var1, var2);
  }

  public void error(String var1, Object var2) {
    logger.error(var1, var2);
  }

  public void error(String var1, Throwable var2) {
    logger.error(var1, var2);
  }
}
