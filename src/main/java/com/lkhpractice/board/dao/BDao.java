package com.lkhpractice.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.lkhpractice.board.constant.Constant;
import com.lkhpractice.board.dto.BDto;

public class BDao {
	
//	DataSource dataSource; //server context.xml 안에서 가져옴
	JdbcTemplate  template;

	public BDao() {
		super();
		
		this.template = Constant.template;
		
//		try {
//			Context context = new InitialContext();
//			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");	
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void write(final String bname, final String btitle, final String bcontent) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				
				String sql = "INSERT INTO mvc_board (bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				
				return pstmt;
			}
		});
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = "INSERT INTO mvc_board (bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bname);
//			pstmt.setString(2, btitle);
//			pstmt.setString(3, bcontent);
//			
//			pstmt.executeUpdate();
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				} 
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		
	}
	
	public ArrayList<BDto> list() {
		
		String sql = "SELECT * FROM mvc_board ORDER BY bgroup DESC, bstep ASC";
		
		ArrayList<BDto> dtos = (ArrayList<BDto>)this.template.query(sql, new BeanPropertyRowMapper(BDto.class));
		// DB에서 sql문의 결과를 모두 가져옴 (ArrayList 형태로)
		
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
//		
//		String sql = "SELECT * FROM mvc_board ORDER BY bgroup DESC, bstep ASC";
//			String sql = "SELECT * FROM mvc_board ORDER BY bgroup DESC";
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			//DB에서 모든 데이터를 bid의 내림차순으로 정렬 후 가져옴
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) { //DB에서 가져온 레코드의 수만큼 반복
//				int bid = rs.getInt("bid");
//				String bname = rs.getString("bname");
//				String btitle = rs.getString("btitle");
//				String bcontent = rs.getString("bcontent");
//				Timestamp bdate = rs.getTimestamp("bdate");
//				int bhit = rs.getInt("bhit");
//				int bgroup = rs.getInt("bgroup");
//				int bstep = rs.getInt("bstep");
//				int bindent = rs.getInt("bindent");
//				
//				BDto dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
//				
//				dtos.add(dto);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				} 
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return dtos;
	}
	
	public BDto content_view(String boardId) {
		
		upHit(boardId); //조회수 증가 메서드 호출
		
		String sql = "SELECT * FROM mvc_board WHERE bid=" + boardId;
		
		BDto dto = this.template.queryForObject(sql, new BeanPropertyRowMapper(BDto.class));
		// DB에서 dto 형태로 레코드 1개 가져오기
		
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		BDto dto = null;
//		
//		String sql = "SELECT * FROM mvc_board WHERE bid=?";
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			//DB에서 모든 데이터를 bid의 내림차순으로 정렬 후 가져옴
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, boardId);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) { //DB에서 가져온 레코드의 수만큼 반복
//				int bid = rs.getInt("bid");
//				String bname = rs.getString("bname");
//				String btitle = rs.getString("btitle");
//				String bcontent = rs.getString("bcontent");
//				Timestamp bdate = rs.getTimestamp("bdate");
//				int bhit = rs.getInt("bhit");
//				int bgroup = rs.getInt("bgroup");
//				int bstep = rs.getInt("bstep");
//				int bindent = rs.getInt("bindent");
//				
//				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
//			} 
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				} 
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return dto;
	}
	
	public void modify(String bname, String btitle, String bcontent, String bid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String bid) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM mvc_board WHERE bid=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bid);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void upHit(String bid) { //호출 시 조회수(bHit)를 1씩 증가
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bid);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep, String bindent) {
		
		replySort(bgroup, bstep);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_board (bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bgroup);
			pstmt.setInt(5, Integer.parseInt(bstep)+1);
			pstmt.setInt(6, Integer.parseInt(bindent)+1);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void replySort(String bgroup, String bstep) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bstep=bstep+1 WHERE bgroup = ? and bstep > ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bgroup);
			pstmt.setString(2, bstep);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
