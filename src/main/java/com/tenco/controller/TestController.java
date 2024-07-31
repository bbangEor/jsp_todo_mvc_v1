package com.tenco.controller;

import java.io.IOException;
import java.util.List;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDAOImpl;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test/*")
public class TestController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserDAO userDAO;
	private TodoDAO todoDAO;

	public TestController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// userDAO = new UserDAOImpl();
		todoDAO = new TodoDAOImpl();
	}

	// http://localhost:8080/mvc/test/t1
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getPathInfo();
		// TodoDTO todoDTO = todoDAO.getTodoById(1);
//      TodoDTO todo1 = new TodoDTO();
//      todo1.setTitle("hi");
//      todo1.setDescription("블로그정리");
//      todo1.setCompleted("false");
//      todo1.setDueDate("2024-07-11");
//      
//      todoDAO.addTodo(todo1, 1);
		todoDAO.deleteTodo(1, 1);
		// List<TodoDTO> list = todoDAO.getTodosByUserId(1);
		// System.out.println(todoDAO.getAllTodos().toString());
		// System.out.println(todoDTO.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
