package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TodoDAOImpl implements TodoDAO {

	// 커넥션객체 필요
	private DataSource dataSource;

	// 생성자
	public TodoDAOImpl() {

		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addTodo(TodoDTO dto, int principalId) {
		String sql = " insert into todos(user_id, title, description, due_date, completed) "
				+ " values(? , ? , ? , ? , ?) "; // 줄 바꿈 처리 시, 한 칸 띄우기
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, principalId);
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getDescription());
				pstmt.setString(4, dto.getDueDate());
				pstmt.setInt(5, dto.getCompleted() == "true" ? 1 : 0);
				pstmt.executeUpdate();
				conn.commit();

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TodoDTO getTodoById(int id) {
		String sql = " select * from todos where id = ? ";
		TodoDTO dto = null;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);

				try (ResultSet rs = pstmt.executeQuery()) {

					if (rs.next()) {

						dto = new TodoDTO(); // 객체 생성

						dto.setId(rs.getInt("id"));
						dto.setUserId(rs.getInt("user_id"));
						dto.setTitle(rs.getString("title"));
						dto.setDescription(rs.getString("description"));
						dto.setDueDate(rs.getString("due_date"));
						dto.setCompleted(rs.getString("completed"));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<TodoDTO> getTodoByUserId(int userId) {

		String sql = " select * from todos where user_id = ? ";

		List<TodoDTO> todos = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					TodoDTO dto = new TodoDTO();
					dto.setId(rs.getInt("id"));
					dto.setUserId(rs.getInt("user_id"));
					dto.setTitle(rs.getString("title"));
					dto.setDescription(rs.getString("description"));
					dto.setDueDate(rs.getString("due_date"));
					dto.setCompleted(rs.getString("completed"));
					todos.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return todos;
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		String sql = " select * from todos ";
		List<TodoDTO> todos = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					TodoDTO dto = new TodoDTO();
					dto.setId(rs.getInt("id"));
					dto.setUserId(rs.getInt("user_id"));
					dto.setTitle(rs.getString("title"));
					dto.setDescription(rs.getString("description"));
					dto.setDueDate(rs.getString("due_date"));
					dto.setCompleted(rs.getString("completed"));
					todos.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return todos;
	}

	@Override
	public void updateTodo(TodoDTO dto, int principalId) {
		String sql = " update todos set title = ?, description = ?, " + " due_date = ?, completed = ? "
				+ " where id = ? and user_id = ? ";
		try (Connection conn = dataSource.getConnection()) {
			// update 쿼리 : 트랜잭션 필요
			conn.setAutoCommit(false);

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getDescription());
				pstmt.setString(3, dto.getDueDate());
				pstmt.setInt(4, dto.getCompleted() == "true" ? 1 : 0);
				pstmt.setInt(5, dto.getId());
				pstmt.setInt(6, dto.getUserId());
				pstmt.executeUpdate();
				conn.commit();

			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Todo 삭제 기능 id - Todos Pk principalId - 세션ID
	 */
	@Override
	public void deleteTodo(int id, int principalId) {
		String sql = " delete from todos where id = ? and user)id = ? ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				pstmt.setInt(2, principalId);
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
