package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement pstmt = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	private void getConnection() {
		// 1. 드라이버 로딩 jdbc
		try {
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

//insert========================================================	
	public int personInsert(PersonVo PersonVo) {

		getConnection();
		int count = 0;
		try {

			// SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval,?,?,?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, PersonVo.getName());
			pstmt.setString(2, PersonVo.getHp());
			pstmt.setString(3, PersonVo.getCompany());

			count = pstmt.executeUpdate();

			// 결과처리
			System.out.println("[" + count + "건 등록되었습니다.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();
		return count;
	}

//list========================================================	
	public List<PersonVo> getPersonList() {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();
		try {

			// SQL문 준비 / 바인딩 / 실행
			String query = "";

			query += " SELECT person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " FROM person ";
			query += " order by person_id asc";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String personName = rs.getNString("name");
				String personHp = rs.getNString("hp");
				String personcompany = rs.getNString("company");
				PersonVo vo = new PersonVo(personId, personName, personHp, personcompany);
				personList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();
		return personList;
	}
	
//update========================================================
	public int personUpdate(PersonVo personVo) {
		getConnection();
		int count = 0;
		try {

			//SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "     hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();// 잘 실행되면 1반환 안되면0

			// 결과처리
			System.out.println("[" + count + "건 수정되었습니다.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		// 자원정리
		close();

		return count;
	}
	
//delete=======================================================
	public int parsonDelete(int personId) {
		getConnection();
		int count = 0;
		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + "건 삭제되었습니다.]");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}
	
//search=======================================================
	public List<PersonVo> getsearchList(String search) {
		// 0. import java.sql.*;

		List<PersonVo> personsearchList = new ArrayList<PersonVo>();

		getConnection();
		try {
			String query = "";
			
			query += " select person_id, ";
			query += " 		  name, ";
			query += "        hp, ";
			query += "  	  company ";
			query += " from person ";
			query += " where name LIKE '%" + search + "%' ";
			query += " or hp LIKE '%" + search + "%' ";
			query += " or company LIKE '%" + search + "%' ";
			query += " order by person_id asc";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String personName = rs.getNString("name");
				String personHp = rs.getNString("hp");
				String personcompany = rs.getNString("company");
				PersonVo vo = new PersonVo(personId, personName, personHp, personcompany);
				personsearchList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();
		return personsearchList;
	}
}
