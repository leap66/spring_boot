package com.leap.model.tuling;

import com.leap.model.out.base.BEntity;

import java.util.Date;
import java.util.List;

/**
 * @author : ylwei
 * @time : 2017/9/18
 * @description :
 */
public class CChat extends BEntity{
  private String userId;// 用户ID
  private Integer code;// 标识符
  private String text;// 结果
  private String url;// 链接地址
  private List<News> list;// 新闻链接
  private Date time;// 时间

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<News> getList() {
    return list;
  }

  public void setList(List<News> list) {
    this.list = list;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}
