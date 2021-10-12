package com.models.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;

import com.core.*;
import com.exception.*;
import com.models.dto.Member;

import org.mindrot.jbcrypt.*;

/**
 * 회원 Model
 * 
 */
public class MemberDao {
	
	/**
	 * 로그인을 한 경우 전역에 회원 정보 유지
	 * @param req
	 */
	public static void init(HttpServletRequest req) {
		
	}
	
	/**
	 * 회원 가입 처리
	 * 
	 * @param request
	 * @return
	 */
	public boolean join(HttpServletRequest request) throws AlertException {

		// 입력 데이터 검증
		checkJoinData(request);

		String sql = "INSERT INTO member (memId,memPw,memNm) VALUES (?,?,?)";
		try (Connection conn = DB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			String memId = request.getParameter("memId");
			String memPw = request.getParameter("memPw");
			String memNm = request.getParameter("memNm");

			String hash = BCrypt.hashpw(memPw, BCrypt.gensalt(10));
			pstmt.setString(1, memId);
			pstmt.setString(2, hash);
			pstmt.setString(3, memNm);

			int result = pstmt.executeUpdate();
			if (result < 1)
				return false;
			/*
			 * ResultSet rs = pstmt.getGeneratedKeys(); if(rs.next()) { // 추가된 회원 번호 int
			 * memNo = rs.getInt(1); } rs.close();
			 */
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 회원 가입 유효성 검사
	 * @param request
	 * @throws AlertException
	 */	
	public void checkJoinData(HttpServletRequest request) throws AlertException {
		/**
		 * 0. 필수 항목 체크 ( 아이디, 비밀번호, 비밀번호 확인, 회원명)
		 * 1. 아이디 자리수 (6~20),영문과 숫자만 허용
		 * 2. 비밀번호 자리수 (8자리 이상), 복잡성(최소 영문 1개 이상, 최소 숫자 1개 이상, 최소 특수문자 1개 이상
		 * 3. 아이디 중복 여부 체크 
		 * 4. 비밀번호 확인시 일치 여부
		 */
		
		// 0.필수 항목 체크
		String[] required = {
			"memId//아이디를 입력하세요",
			"memPw//비밀번호를 입력하세요",
			"memPwRe//비밀번호를 확인해 주세요",
			"memNm//회원명을 입력하세요"
		};
		for (String s : required) {
			String[] re = s.split("//");
			if (request.getParameter(re[0]) == null || request.getParameter(re[0]).trim().equals("")) { // 필수 항목 누락
				throw new AlertException(re[1]);
			}
		}
		// 1. 아이디 자리수 영문,숫자로만 구성 체크
		String memId = request.getParameter("memId").trim();
		if (memId.length() < 6 || memId.length() > 20 || memId.matches("[^a-zA-z0-9]")) {
			throw new AlertException("아이디는 숫자와 영문자 6자리 이상 20자리 이하로 입력해 주세요");
		}
		
		// 2. 비밀번호 자리수 체크
		String memPw = request.getParameter("memPw").trim();
		if (memPw.length() < 8) {
			throw new AlertException("비밀번호는 8자리 이상 입력해 주세요.");
		}
		
		// 3. 아이디 중복 여부 체크
		String sql = "SELECT COUNT(*) cnt FROM member WHERE memId = ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt > 0) { // 이미 등록된 아이디인 경우
					throw new AlertException("이미 가입된 아이디 입니다 -" + memId);
				}
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 4. 비밀번호 확인시 일치 여부
		String memPwRe = request.getParameter("memPwRe");
		if (!memPw.equals(memPwRe)) {
			throw new AlertException("비밀번호가 일치하지 않습니다.");
		}		
	}
	
	/**
	 * 로그인 처리
	 * @param HttpServletRequest req - 세션처리(HttpSession)
	 * @param memId
	 * @param memPw
	 * @return true/false 로그인 성공/실패
	 */
	public void login(HttpServletRequest req, String memId, String memPw) throws AlertException {
		/**
		 * 1. 필수 항목 체크( 아이디,비번)
		 * 2. 아이디로 회원 정보를 조회
		 * 3. 회원 정보가 있으면 -> 비밀번호 해시 일치 여부 체크
		 * 4. 모든것이 일치하면 로그인 처리(세션에 memNo 값을 저장 - 전역에 유지)
		 */
		
		// 1. 필수항목 체크
		if (memId == null || memId.trim().equals("")) {
			throw new AlertException("아이디를 입력해 주세요");
		}
		if (memPw == null || memPw.trim().equals("")) {
			throw new AlertException("비밀번호를 입력해 주세요");
		}		
				
		memId = memId.trim();
		memPw = memPw.trim();
		
		// 2. 아이디로 회원 정보를 조회(아이디,회원번호)
		Member member = get(memId);
		if (member == null) {
			throw new AlertException("회원 정보가 없습니다.");
		}
		
		// 3. 비밀번호 해시 체크
		boolean match = BCrypt.checkpw(memPw, member.getMemPw());
		if (!match) { 
			throw new AlertException("비밀번호가 일치하지 않습니다.");
		}
		
		// 4. 로그인 처리
		HttpSession session = req.getSession();
		session.setAttribute("memNo",member.getMemNo());		
	}
	
	public void login(HttpServletRequest req) throws AlertException {
		String memId = req.getParameter("memId");
		String memPw = req.getParameter("memPw");
		
		login(req,memId,memPw);
	}
	
	/**
	 * 회원 정보 조회
	 * @param memNo 회원 번호
	 * @return
	 */
	public Member get(int memNo) {
		Member member = null;
		String sql = "SELECT * FROM member WHERE memNo = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member(rs); // dto.member 15행
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	/**
	 * 회원 정보 조회
	 * @param memId 회원 아이디
	 * @return get(memNo)
	 */
	public Member get(String memId) {
		int memNo = 0;
		String sql = "SELECT memNo FROM member WHERE memId =?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				memNo = rs.getInt("memNo");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return get(memNo);
	}
}
