package com.model.dao;

import com.core.DB;
import com.model.dto.MemberBean;
import java.sql.*;
import java.util.ArrayList;

import org.mindrot.jbcrypt.*;
/* 
* 회원 추가,삭제,조회,로그인
 */

public class Member {
  // 회원 추가
  // @param memeber
  // @ return Boolean true - 추가 성공
  public boolean insertMember(MemberBean member) {
    if (member == null)
      return false;
    String sql = "CALL InsertMember(?,?,?)";
    String memPw = BCrypt.hashpw(member.getMemPw(), BCrypt.gensalt(10));
    try (Connection conn = DB.getConnection(); CallableStatement cstmt = conn.prepareCall(sql);) {
      cstmt.setString(1, member.getMemID());
      cstmt.setString(2, memPw);
      cstmt.setString(3, member.getMemNm());
      int rs = cstmt.executeUpdate();
      if (rs > 0)
        return true;
    } catch (ClassNotFoundException | SQLException e) {
      // 로그 기록 ...
      return false;
    }
    return false;
  }

  /*
   * 회원 목록
   * 
   * @ return ArrayList
   */
  public ArrayList<MemberBean> getMembers() {
    ArrayList<MemberBean> list = new ArrayList<>();

    String sql = "SELECT * FROM member2";
    try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(new MemberBean(rs));
      }
      rs.close();
    } catch (ClassNotFoundException | SQLException e) {
      // 로그 기록
      e.printStackTrace();
    }
    return list;
  }
  /*
   * 회원 삭제
   * 
   * @param memID 회원 아이디
   * 
   * @return 삭제 성공 true
   */

  public boolean deleteMember(String memID) {
    String sql = "CAll DeleteMember(?)";
    try (Connection conn = DB.getConnection(); CallableStatement cstmt = conn.prepareCall(sql);) {
      cstmt.setString(1, memID);
      int rs = cstmt.executeUpdate(); // 반영된 투플의 갯수
      if (rs > 0)
        return true;
    } catch (ClassNotFoundException | SQLException e) {
    	e.printStackTrace();
      return false;
    }
    return false;
  }
  /*
   * 로그인 처리
   *
   * @param memID 아이디
   * 
   * @param memPw 비밀번호
   * 
   * @return 로그인 성공 true
   */

  public boolean login(String memID, String memPw) {
    String sql = "SELECT * FROM member2 WHERE memID = ?";
    try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, memID);

      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String hash = rs.getString("memPw");
        return BCrypt.checkpw(memPw, hash);
      }
    } catch (ClassNotFoundException | SQLException e) {
      // 로그 기록
      return false;
    }
    return false;
  }
}
