package com.leap.model.baidu;

/**
 * @author : ylwei
 * @time : 2017/9/20
 * @description :
 */
public class STts {
  private Integer err_no;// 错误码
  private String err_msg;// 错误信息
  private String sn;// 主要用于DEBUG追查问题，如果出现问题，可以提供sn帮助确认问题
  private Integer idx;
  private String[] result;// 识别结果数组，提供1-5 个候选结果， 优先使用第一个结果。utf-8 编码。
  private String corpus_no;

  public Integer getErr_no() {
    return err_no;
  }

  public void setErr_no(Integer err_no) {
    this.err_no = err_no;
  }

  public String getErr_msg() {
    return err_msg;
  }

  public void setErr_msg(String err_msg) {
    this.err_msg = err_msg;
  }

  public String getSn() {
    return sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
  }

  public Integer getIdx() {
    return idx;
  }

  public void setIdx(Integer idx) {
    this.idx = idx;
  }

  public String[] getResult() {
    return result;
  }

  public void setResult(String[] result) {
    this.result = result;
  }

  public String getCorpus_no() {
    return corpus_no;
  }

  public void setCorpus_no(String corpus_no) {
    this.corpus_no = corpus_no;
  }
}
