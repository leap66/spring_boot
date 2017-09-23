package com.leap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/8
 * @description :
 */
@Entity
@JsonIgnoreProperties(value = {
    "hibernateLazyInitializer", "handler" })
public class Trace implements Serializable {
  @Id
  @GeneratedValue
  private Integer tranId;// 流水号
  private String id;// 唯一标识符
  private String ipId;// IP
  private String url;// URL
  private String method;// Method
  private String className;//
  private String arg;// 请求参数
  private String traceId;// 请求识别ID
  private String marsCookie;// Token
  private String Cookies;// Cookies
  private String Session;// Session
  private String response;// 返回参数
  private String throwable;// 崩溃信息
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date created;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date end;
  private boolean crash;// 崩溃

  public Trace() {
  }

  public Integer getTranId() {
    return tranId;
  }

  public void setTranId(Integer tranId) {
    this.tranId = tranId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIpId() {
    return ipId;
  }

  public void setIpId(String ipId) {
    this.ipId = ipId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getArg() {
    return arg;
  }

  public void setArg(String arg) {
    this.arg = arg;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public String getMarsCookie() {
    return marsCookie;
  }

  public void setMarsCookie(String marsCookie) {
    this.marsCookie = marsCookie;
  }

  public Object getCookies() {
    return Cookies;
  }

  public void setCookies(String cookies) {
    Cookies = cookies;
  }

  public String getSession() {
    return Session;
  }

  public void setSession(String session) {
    Session = session;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String getThrowable() {
    return throwable;
  }

  public void setThrowable(String throwable) {
    this.throwable = throwable;
  }

  public boolean isCrash() {
    return crash;
  }

  public void setCrash(boolean crash) {
    this.crash = crash;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }
}
