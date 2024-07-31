package com.tenco.model;

import java.util.List;

public interface TodoDAO {
	
	//저장기능
	void addTodo(TodoDTO dto,int principalId);
	
	TodoDTO getTodoById(int id);
	
	// 사용자 ID 기준으로 TODOList 출력 
	List<TodoDTO> getTodoByUserId(int userId);
	List<TodoDTO> getAllTodos(); // 전체 select 라 파라미터 값 필요없음
	void updateTodo(TodoDTO dto , int principalId);
	void deleteTodo(int id, int principalId);
}
