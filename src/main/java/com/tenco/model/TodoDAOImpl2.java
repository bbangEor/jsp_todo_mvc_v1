package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TodoDAOImpl2 implements TodoDAO {

	// 커넥션 객체 필요 
	private DataSource dataSource;
	
	public TodoDAOImpl2()  {
		
		
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void addTodo(TodoDTO dto, int principalId) {
		String sql =" insert into todos (user_id,title,description,due_date,completed)"
			+ " values(?,?,?,?,?) ";
		try (Connection conn = dataSource.getConnection()){
			conn.setAutoCommit(false);
			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1,principalId );
				pstmt.setString(2,dto.getTitle() );
				pstmt.setString(3,dto.getDescription() );
				pstmt.setString(4,dto.getDueDate() );
				pstmt.setString(5,dto.getCompleted() );
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TodoDTO getTodoById(int id) {
		String sql =" SELECT * FROM todos WHERE id = ? ";
		TodoDTO dto = null;
		
		try (Connection conn = dataSource.getConnection()){
			PreparedStatement  pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1 , id);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					
					dto = new TodoDTO(); // 객체생성
					
					dto.setId(rs.getInt(id));
					dto.setUserId(rs.getInt("user_id"));
					dto.setTitle(rs.getString("title"));
					dto.setDescription(rs.getString("description"));
					dto.setDueDate(rs.getString("due_date"));
					dto.setCompleted(rs.getString("completed"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			pstmt.executeQuery();
		} catch (Exception e) {
		}
		
		return dto;
		
	}

	@Override
	public List<TodoDTO> getTodoByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTodo(TodoDTO dto, int principalId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTodo(int id, int principalId) {
		// TODO Auto-generated method stub
		
	}

}
