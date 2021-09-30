package com.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class MemberBean {
  private int memNo;
  private String memID;
  private String memPw;
  private String memNm;

  public MemberBean() {
  }

  public MemberBean(HttpServletRequest request) {
    this.memID = request.getParameter("memID");
    this.memNm = request.getParameter("memNm");
    this.memPw = request.getParameter("memPw");
  }

  public MemberBean(ResultSet rs) throws SQLException {
    this.memNo = rs.getInt("memNo");
    this.memID = rs.getString("memID");
    this.memNm = rs.getString("memNm");
  }

  public MemberBean(int memNo, String memID, String memNm) {
    this.memNo = memNo;
    this.memID = memID;
    this.memNm = memNm;
  }

  public int getMemNo() {
    return memNo;
  }

  public void setMemNo(int memNo) {
    this.memNo = memNo;
  }

  public String getMemID() {
    return memID;
  }

  public void setMemID(String memID) {
    this.memID = memID;
  }

  public String getMemNm() {
    return memNm;
  }

  public void setMemNm(String memNm) {
    this.memNm = memNm;
  }

  public String getMemPw() {
    return memPw;
  }

  public void setMemPw(String memPw) {
    this.memPw = memPw;
  }

}
