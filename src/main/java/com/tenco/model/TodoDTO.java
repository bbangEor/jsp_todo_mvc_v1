package com.tenco.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class TodoDTO {
	private int id;
	private int userId;
	private String title;
	private String description;
	private String dueDate;
	private String completed;
	
	//completed 를 변환하는 메서드 만들기 
	public String completedToString() {
		return completed.equals("1") ? "true" : "false";
	}
}
