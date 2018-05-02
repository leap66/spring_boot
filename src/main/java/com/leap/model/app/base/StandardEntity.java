package com.leap.model.app.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author : ylwei
 * @time : 2017/9/5
 * @description :
 */
public class StandardEntity extends VersionedEntity {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date created;
  private BUcn creator;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date lastModified ;
  private BUcn lastModifier;

  public BUcn getCreator() {
    return creator;
  }

  public void setCreator(BUcn creator) {
    this.creator = creator;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public BUcn getLastModifier() {
    return lastModifier;
  }

  public void setLastModifier(BUcn lastModifier) {
    this.lastModifier = lastModifier;
  }
}
